package gregtech.loaders.preload;

import gregtech.api.util.GT_Log;
import gregtech.loaders.oreprocessing.ProcessingOreSmelting;

public class GT_Loader_OreProcessing
        implements Runnable {
    public void run() {
        GT_Log.out.println("GT_Mod: Register Ore processing.");
        new ProcessingOreSmelting();
        //new ProcessingBlock();
        //new ProcessingCircuit();
        //new ProcessingCompressed();
        //new ProcessingDye();
        //new ProcessingLog();
        //new ProcessingTransforming();
        //new ProcessingPlank();
        //new ProcessingRecycling();
        //new ProcessingSaplings();
        //new ProcessingStone();
        //new ProcessingStoneVarious();
        //new ProcessingToolOther();
        //new ProcessingWire();
    }
}
