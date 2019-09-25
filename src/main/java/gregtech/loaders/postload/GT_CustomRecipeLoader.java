package gregtech.loaders.postload;

import cpw.mods.fml.common.Loader;
import gregtech.api.enums.*;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static gregtech.api.enums.GTNH_ExtraMaterials.*;


public class GT_CustomRecipeLoader implements Runnable {

    public void run() {

        // Blast Furnace Recipes
        GT_Values.RA.addBlastRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.UUAmplifier, 32), null, Materials.Helium.getPlasma(144L), null, GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.UUAmplifier, 1), null, 400, 19753, 10800);
        GT_Values.RA.addBlastRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.UUMatter, 32), null, Materials.Helium.getPlasma(144L), null, GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.UUMatter, 1), null, 400, 19753, 10800);
        GT_Values.RA.addBlastRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Weebium, 1L), GT_Utility.getIntegratedCircuit(1), GGEssence.getFluid(144L), null, GT_OreDictUnificator.get(OrePrefixes.ingotHot, Weebium, 1L), null, 125, 498073, 9001);
        GT_Values.RA.addBlastRecipe(PMagium.getDust(1), GT_Utility.getIntegratedCircuit(11), GGEssence.getFluid(144), null, GT_OreDictUnificator.get(OrePrefixes.ingotHot, PMagium, 1L), null, 12500, 2000000, 10800);


        // Vacuum Freezer Recipes
        GT_Values.RA.addVacuumFreezerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.UUMatter, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.UUMatter, 1L), 400, 13169);
        GT_Values.RA.addVacuumFreezerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingotHot, Weebium, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Weebium, 1L), 120, 480);
        GT_Values.RA.addVacuumFreezerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.UUAmplifier, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.UUAmplifier, 1L), 400, 13169);
        GT_Values.RA.addVacuumFreezerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingotHot, PMagium, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, PMagium, 1L), 5000, 2000000);


        // Fluid Extractor Recipes
        GT_Values.RA.addFluidExtractionRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.UUMatter, 1L), GT_Values.NI, Materials.UUMatter.getFluid(16L),10000, 10, 32);
        GT_Values.RA.addFluidExtractionRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.UUAmplifier, 1L), GT_Values.NI, Materials.UUAmplifier.getFluid(16L),10000, 10, 32);
        GT_Values.RA.addFluidExtractionRecipe(ItemList.Gamer_girl_Panties.get(1L), GT_Values.NI, GTNH_ExtraMaterials.Bathwater.getFluid(2500L), 10000, 250, 480);
        GT_Values.RA.addFluidExtractionRecipe(GGEssence.getDust(1), GT_Values.NI, GGEssence.getFluid(144L), 10000, 20, 408);


        // Mixer Recipes
        GT_Values.RA.addMixerRecipe(Materials.Europium.getDust(16), Materials.Tartarite.getDust(8), Materials.ElectrumFlux.getDust(6), Materials.Infinity.getDustTiny(1), Materials.CosmicNeutronium.getDust(6), GT_ModHandler.getModItem("dreamcraft", "item.TCetiESeaweedExtract", 16L, 0), Materials.GrowthMediumRaw.getFluid(20000L), Materials.BioMediumRaw.getFluid(20000L), GT_Values.NI, 300, 500000);
        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, GGEssence, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Weebium, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.dust, PMagium, 2L), 750, 131072);


        // Chem Reactor Recipes
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{GT_Utility.getIntegratedCircuit(1), ItemList.Circuit_Chip_Stemcell.get(64L), Materials.MysteriousCrystal.getDust(16)}, new FluidStack[]{Materials.BioMediumRaw.getFluid(4000L)}, null, new ItemStack[]{ItemList.Circuit_Chip_Biocell.get(32L)}, 300, 30700);


        // Centrifuge Recipes
        GT_Values.RA.addCentrifugeRecipe(ItemList.Gamer_girl_Panties.get(1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Water, 3L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.cell, Bathwater, 3L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{10000,0,0,0,0,0}, 20, 480);
        GT_Values.RA.addCentrifugeRecipe(ItemList.Doritos_Empty.get(1L), GT_Utility.getIntegratedCircuit(12), Materials.HydrochloricAcid.getFluid(16L), Materials.DilutedHydrochloricAcid.getFluid(16L), GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Plastic, 1L), GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{2500,2500,0,0,0,0}, 20, 120);


        // Distillation Recipes
        GT_Values.RA.addUniversalDistillationRecipe(GTNH_ExtraMaterials.Bathwater.getFluid(1000L), new FluidStack[]{GGEssence.getFluid(144L), Urine.getFluid(144L), Sweat.getFluid(144L), Materials.Water.getFluid(568)}, GGEssence.getDustTiny(1), 120, 480);


        // Electrolyzer Recipes
        GT_Values.RA.addElectrolyzerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Bathwater, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, ItemList.Cell_Empty.get(1L), Materials.Salt.getDust(2), GGEssence.getDust(1), GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{10000,9500,6500,0,0,0}, 20, 480);


        // Assembler Recipes
        if (Loader.isModLoaded("harvestcraft")) {
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{GT_ModHandler.getModItem("harvestcraft", "cornmealItem", 16L, 0), GT_ModHandler.getModItem("harvestcraft", "cheeseItem", 1L, 0), GT_ModHandler.getModItem("harvestcraft", "chilipepperItem", 1L, 0), Materials.Salt.getDustSmall(1), GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 1L), GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Plastic, 1L)}, Materials.Nitrogen.getGas(16L), ItemList.Doritos.get(4L), 20, 120);
        }


    }
}
