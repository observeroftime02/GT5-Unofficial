package gregtech.loaders.preload;

import gregtech.api.util.GT_Log;
import gregtech.loaders.oreprocessing.*;

public class GT_Loader_OreProcessing
        implements Runnable {
    public void run() {
        GT_Log.out.println("GT_Mod: Register Ore processing.");
        new ProcessingOreSmelting();
        new ProcessingCircuit();
        new ProcessingWire();
    }
}
