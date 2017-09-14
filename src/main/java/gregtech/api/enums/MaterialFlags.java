package gregtech.api.enums;

public enum MaterialFlags {
    DUST(1),
    SOLID(DUST.bit*2),
    GEM(SOLID.bit*2),
    ORE(GEM.bit*2),
    CELL(ORE.bit*2),
    PLASMA(CELL.bit*2),
    TOOL(PLASMA.bit*2),
    PLATE(TOOL.bit*2),
    STICK(PLATE.bit*2),
    RING(STICK.bit*2),
    BOLT(RING.bit*2),
    FOIL(BOLT.bit*2),
    SCREW(FOIL.bit*2),
    GEAR(SCREW.bit*2),
    SGEAR(GEAR.bit*2),
    FWIRE(SGEAR.bit*2),
    ROTOR(FWIRE.bit*2),
    DPLATE(ROTOR.bit*2),
    SPRING(DPLATE.bit*2),
    HINGOT(SPRING.bit*2),
    ELEC(HINGOT.bit*2),
    CENT(ELEC.bit*2),
    CRACK(CENT.bit*2),
    CFLUID(CRACK.bit*2),
    CGAS(CFLUID.bit*2),
    LIQUID(CGAS.bit*2),
    FRAME(LIQUID.bit*2),
    SORE(FRAME.bit*2),
    REF(SORE.bit*2);

    public int bit;

    MaterialFlags(int bit) {
        this.bit = bit;
    }

    public static MaterialFlags getFlagForValue(int bit) {
        for (MaterialFlags flag : values()) {
            if (flag.bit == bit) {
                return flag;
            }
        }
        return null;
    }
}


