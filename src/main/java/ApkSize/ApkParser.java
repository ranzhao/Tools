package ApkSize;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by zhaoran on 5/11/16.
 */
public class ApkParser {
    public static final String BUNDLE_TAG = "com_ali_music";
    private final List<ApkSizeEntry> sizeEntries = new ArrayList<>();

    public static List<ApkSizeEntry> parse(String apkFile) throws IOException {
        ApkParser parser = new ApkParser(apkFile);
        return parser.sizeEntries;
    }

    private ApkParser(String apkFile) throws IOException {
        try (final ZipFile zipFile = new ZipFile(apkFile)) {
//            zipFile.stream().forEach(e -> System.out.println(e.getName() + ": \t" + e.getCompressedSize()));
            sizeEntries.add(parseMain(zipFile));
            sizeEntries.addAll(parseBundles(zipFile));
        }
    }

    private List<ApkSizeEntry> parseBundles(ZipFile zipFile) {
        return zipFile.stream()
                .filter(this::isBundle)
                .map(entry -> parseBundleSize(zipFile, entry))
                .collect(Collectors.toList());
    }

    private ApkSizeEntry parseMain(ZipFile zipFile) {
        return new ApkSizeEntry(zipFile.getName().substring(zipFile.getName().lastIndexOf('/') + 1), sizeOf(zipFile, this::isDex)
                , sizeOf(zipFile, this::isRes)
                , sizeOf(zipFile, this::isSo));
    }

    private ApkSizeEntry parseBundleSize(ZipFile zipFile, ZipEntry bundleEntry) {
        final String unzippedFile = getUnzippedFileName(bundleEntry);
        try {
            unzipEntry(zipFile.getInputStream(bundleEntry), unzippedFile);
            return ApkParser.parse(unzippedFile).stream().findFirst().orElse(ApkSizeEntry.EMPTY);
        } catch (IOException e) {
            e.printStackTrace();
            return ApkSizeEntry.EMPTY;
        } finally {
            new File(unzippedFile).delete();
        }
    }

    private String getUnzippedFileName(ZipEntry zipEntry) {
        final String bundleEntryName = zipEntry.getName();
        return bundleEntryName.substring(bundleEntryName.lastIndexOf('/') + 1);
    }

    private void unzipEntry(InputStream zipInputStream, String unzippedFile) throws IOException {
        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(unzippedFile))) {
            int b = 0;
            while ((b = zipInputStream.read()) != -1) {
                bufferedOutputStream.write(b);
            }
        }
    }

    private long sizeOf(ZipFile zipFile, Predicate<ZipEntry> predicate) {
        return zipFile.stream()
                .filter(predicate)
                .mapToLong(ZipEntry::getCompressedSize)
                .sum();
    }

    private boolean isRes(ZipEntry zipEntry) {
        return !isDex(zipEntry) && !zipEntry.getName().endsWith(".so");
    }

    private boolean isSo(ZipEntry zipEntry) {
        return zipEntry.getName().endsWith(".so") && !isBundle(zipEntry);
    }

    private boolean isDex(ZipEntry zipEntry) {
        return zipEntry.getName().endsWith(".dex");
    }

    private boolean isBundle(ZipEntry zipEntry) {
        return zipEntry.getName().contains(BUNDLE_TAG);
    }
}
