package gregtech.api.enums;

public enum MaterialFlags {
    DUST(1), SOLID(2), GEM(4), ORE(8), CELL(16), PLASMA(32), TOOL(64), PLATE(128), ROD(512), RING(1024), BOLT(2048), FOIL(4096), SCREW(8192), GEAR(16384), SGEAR(32768), FWIRE(65536), LROD(131072), ROTOR(262144), DPLATE(524288), ELEC(1048576), CENT(2097152), CRACK(4194304), EMPTY(8388608);

    public int value;

    MaterialFlags(int value) {
        this.value = value;
    }
}
