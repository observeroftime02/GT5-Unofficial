package gregtech.api.enums;

public enum ProcessingFlags {
    ELEC(1), CENT(2), CRACK(4);

    public int bit;

    ProcessingFlags(int bit) {
        this.bit = bit;
    }
}
