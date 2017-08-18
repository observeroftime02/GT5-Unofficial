package gregtech.loaders.preload;

import gregtech.api.util.GT_Log;
import gregtech.loaders.oreprocessing.*;

public class GT_Loader_OreProcessing
        implements Runnable {
    public void run() {
        GT_Log.out.println("GT_Mod: Register Ore processing.");
        //new ProcessingAll();
        new ProcessingBlock();
        new ProcessingBolt();
        new ProcessingCell();
        new ProcessingCircuit();
        new ProcessingCompressed();
        new ProcessingCrafting();
        new ProcessingCrop();
        new ProcessingCrushedOre();
        new ProcessingCrystallized();
        new ProcessingDust();
        //new ProcessingDye();
        new ProcessingFoil();
        new ProcessingFineWire();
        new ProcessingLens();
        new ProcessingShaping();
        new ProcessingGem();
        new ProcessingGear();
        new ProcessingIngot();
        new ProcessingItem();
        new ProcessingLog();
        new ProcessingTransforming();
        new ProcessingNugget();
        new ProcessingOre();
        //new ProcessingOrePoor();
        new ProcessingOreSmelting();
        new ProcessingPipe();
        new ProcessingPlank();
        new ProcessingPlate();
        new ProcessingRecycling();
        new ProcessingRound();
        new ProcessingRotor();
        new ProcessingSaplings();
        new ProcessingScrew();
        new ProcessingStick();
        new ProcessingStone();
        new ProcessingStoneVarious();
        new ProcessingToolHead();
        new ProcessingToolOther();
        new ProcessingWax();
        new ProcessingWire();
    }
}
