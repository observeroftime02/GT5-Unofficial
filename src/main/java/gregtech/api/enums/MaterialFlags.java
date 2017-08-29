package gregtech.api.enums;

public enum MaterialFlags {
    DUST(1), SOLID(2), GEM(4), ORE(8), CELL(16), PLASMA(32), TOOL(64), PLATE(128), STICK(512), RING(1024), BOLT(2048), FOIL(4096), SCREW(8192), GEAR(16384), SGEAR(32768), FWIRE(65536), ROTOR(131072), DPLATE(262144), SPRING(524288), HINGOT(1048576), WIRE(2097152), EMPTY(4194304), ELEC(8388608), CENT(8388608), CRACK(8388608), REF(8388608);

    public int bit;

    MaterialFlags(int bit) {
        this.bit = bit;
    }
}


