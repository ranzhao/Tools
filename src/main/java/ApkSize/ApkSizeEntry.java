package ApkSize;

/**
 * Created by zhaoran on 5/11/16.
 */
public class ApkSizeEntry {
    public String entryName() {
        return entryName;
    }

    public long dexSize() {
        return dexSize;
    }

    public long resSize() {
        return resSize;
    }

    public long soSize() {
        return soSize;
    }

    final private String entryName;
    final private long dexSize;
    final private long resSize;
    final private long soSize;

    final static ApkSizeEntry EMPTY = new ApkSizeEntry("", 0, 0, 0);

    ApkSizeEntry(String entryName, long dexSize, long resSize, long soSize) {
        this.entryName = entryName;
        this.dexSize = dexSize;
        this.resSize = resSize;
        this.soSize = soSize;
    }

    @Override
    public String toString() {
        return "\n" + entryName +
                "\ndexSize:\t" + dexSize +
                "\nresSize:\t" + resSize +
                "\nsoSize:\t" + soSize + "\n";
    }
}
