package gregtech.loaders.materialprocessing;

import cpw.mods.fml.common.Loader;
import gregtech.api.enums.MaterialFlags;
import gregtech.api.enums.Materials;

public class ProcessingModSupport implements gregtech.api.interfaces.IMaterialHandler {
    public static boolean aEnableThaumcraftMats = Loader.isModLoaded("Thaumcraft");
    public static boolean aEnableGCMarsMats = Loader.isModLoaded("GalacticraftMars");

    public ProcessingModSupport() {
        Materials.add(this);
    }

    @Override
    public void onMaterialsInit() {
        //Disable Materials if Parent Mod is not loaded
        if (!aEnableThaumcraftMats) {
            Materials.Amber.mHasParentMod = false;
            Materials.Thaumium.mHasParentMod = false;
            Materials.InfusedAir.mHasParentMod = false;
            Materials.InfusedFire.mHasParentMod = false;
            Materials.InfusedEarth.mHasParentMod = false;
            Materials.InfusedWater.mHasParentMod = false;
            Materials.InfusedEntropy.mHasParentMod = false;
            Materials.InfusedOrder.mHasParentMod = false;
        }
        if (!aEnableGCMarsMats) {
            Materials.Desh.mHasParentMod = false;
            Materials.MeteoricIron.mHasParentMod = false;
            Materials.MeteoricSteel.mHasParentMod = false;
        }
    }

    @Override
    public void onComponentInit() {
        if (Loader.isModLoaded("computronics")) {
            Materials.RedAlloy.add(MaterialFlags.RING);
            Materials.NiobiumTitanium.add(MaterialFlags.RING);
            Materials.StainlessSteel.add(MaterialFlags.FOIL);
            Materials.Iron.add(MaterialFlags.FOIL);
            Materials.Copper.add(MaterialFlags.SCREW);
        }
    }

    @Override
    public void onComponentIteration(Materials aMaterial) {
        //NOOP
    }
}
