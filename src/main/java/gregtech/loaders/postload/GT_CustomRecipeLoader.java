package gregtech.loaders.postload;

import com.dreammaster.gthandler.CustomItemList;
import cpw.mods.fml.common.Loader;
import gregtech.GT_Mod;
import gregtech.api.enums.*;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;

import static gregtech.api.enums.GTNH_ExtraMaterials.*;
import static gregtech.api.enums.OrePrefixes.*;


public class GT_CustomRecipeLoader implements Runnable {

    public void run() {

        // Blast Furnace Recipes
        GT_Values.RA.addBlastRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.UUAmplifier, 32), GT_Utility.getIntegratedCircuit(11), Materials.Helium.getPlasma(144L), null, GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.UUAmplifier, 1), null, 400, 19753, 10800);
        GT_Values.RA.addBlastRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.UUMatter, 32), GT_Utility.getIntegratedCircuit(11), Materials.Helium.getPlasma(144L), null, GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.UUMatter, 1), null, 400, 19753, 10800);
        GT_Values.RA.addBlastRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Weebium, 1L), GT_Utility.getIntegratedCircuit(11), AGEssence.getFluid(144L), null, GT_OreDictUnificator.get(OrePrefixes.ingotHot, Weebium, 1L), null, 125, 498073, 9001);
        GT_Values.RA.addBlastRecipe(PMagium.getDust(1), GT_Utility.getIntegratedCircuit(11), AGEssence.getFluid(144), null, GT_OreDictUnificator.get(OrePrefixes.ingotHot, PMagium, 1L), null, 12500, 2000000, 10800);
        GT_Values.RA.addBlastRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Silicon, 64), GT_OreDictUnificator.get(ingot, Weebium, 16), AGEssence.getFluid(32000), null, ItemList.Weebium_Silicon_Boule.get(1), null, 36000, 2000000, 10800);

        // Vacuum Freezer Recipes
        GT_Values.RA.addVacuumFreezerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.UUMatter, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.UUMatter, 1L), 400, 13169);
        GT_Values.RA.addVacuumFreezerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingotHot, Weebium, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Weebium, 1L), 120, 480);
        GT_Values.RA.addVacuumFreezerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.UUAmplifier, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.UUAmplifier, 1L), 400, 13169);
        GT_Values.RA.addVacuumFreezerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingotHot, PMagium, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, PMagium, 1L), 5000, 2000000);


        // Fluid Extractor Recipes
        GT_Values.RA.addFluidExtractionRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.UUMatter, 1L), GT_Values.NI, Materials.UUMatter.getFluid(16L),10000, 10, 32);
        GT_Values.RA.addFluidExtractionRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.UUAmplifier, 1L), GT_Values.NI, Materials.UUAmplifier.getFluid(16L),10000, 10, 32);
        GT_Values.RA.addFluidExtractionRecipe(ItemList.Gamer_girl_Panties.get(1L), GT_Values.NI, GTNH_ExtraMaterials.Bathwater.getFluid(2500L), 10000, 250, 480);
        GT_Values.RA.addFluidExtractionRecipe(AGEssence.getDust(1), GT_Values.NI, AGEssence.getFluid(144L), 10000, 20, 408);
        GT_Values.RA.addFluidExtractionRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Weebium, 1L), null, Weebium.getMolten(1296L), 10000, 1200, 524000);
        GT_Values.RA.addFluidExtractionRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Weebium, 1L), null, Weebium.getMolten(144L), 10000, 1200, 524000);
        GT_Values.RA.addFluidExtractionRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Weebium, 1L), null, Weebium.getMolten(144L), 10000, 1200, 524000);
        GT_Values.RA.addFluidExtractionRecipe(ItemList.Casing_Weebium.get(1L), null, Weebium.getMolten(144L), 10000, 1200, 524000);


        // Mixer Recipes
        GT_Values.RA.addMixerRecipe(Materials.Europium.getDust(16), Materials.Tartarite.getDust(8), Materials.ElectrumFlux.getDust(6), Materials.Infinity.getDustTiny(1), Materials.CosmicNeutronium.getDust(6), GT_ModHandler.getModItem("dreamcraft", "item.TCetiESeaweedExtract", 16L, 0), Materials.GrowthMediumRaw.getFluid(20000L), Materials.BioMediumRaw.getFluid(20000L), GT_Values.NI, 300, 500000);
        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, AGEssence, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Weebium, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.dust, PMagium, 2L), 750, 131072);


        // Chem Reactor Recipes
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{GT_Utility.getIntegratedCircuit(1), ItemList.Circuit_Chip_Stemcell.get(64L), Materials.MysteriousCrystal.getDust(16)}, new FluidStack[]{Materials.BioMediumRaw.getFluid(4000L)}, null, new ItemStack[]{ItemList.Circuit_Chip_Biocell.get(32L)}, 300, 30700);
        if (Loader.isModLoaded("bartworks")) {
            GT_Values.RA.addChemicalRecipe(GT_Utility.getIntegratedCircuit(1), GT_Values.NI,  WerkstoffLoader.AcidicOsmiumSolution.getFluidOrGas(1000), WerkstoffLoader.OsmiumSolution.getFluidOrGas(100), GT_Values.NI, 150, 7680);
        }


        // Centrifuge Recipes
        GT_Values.RA.addCentrifugeRecipe(ItemList.Gamer_girl_Panties.get(1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Water, 3L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.cell, Bathwater, 3L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{10000,0,0,0,0,0}, 20, 480);
        GT_Values.RA.addCentrifugeRecipe(ItemList.Doritos_Empty.get(1L), GT_Utility.getIntegratedCircuit(12), Materials.HydrochloricAcid.getFluid(16L), Materials.DilutedHydrochloricAcid.getFluid(16L), GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Plastic, 1L), GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{2500,2500,0,0,0,0}, 20, 120);
        if (Loader.isModLoaded("bartworks")){
            GT_Values.RA.addCentrifugeRecipe(GT_Utility.getIntegratedCircuit(8), WerkstoffLoader.PTMetallicPowder.get(dust, 8), GT_Values.NF, GT_Values.NF, Materials.Platinum.getDust(2), Materials.Palladium.getDust(2), Materials.Osmium.getDust(1), Materials.Iridium.getDust(1), WerkstoffLoader.Rhodium.get(dust, 1), WerkstoffLoader.Ruthenium.get(dust, 1), new int[]{10000,10000,10000,10000,10000,10000,}, 1400, 7690);
        }

        // Distillation Recipes
        GT_Values.RA.addUniversalDistillationRecipe(GTNH_ExtraMaterials.Bathwater.getFluid(1000L), new FluidStack[]{AGEssence.getFluid(144L), Urine.getFluid(144L), Sweat.getFluid(144L), Materials.Water.getFluid(568)}, AGEssence.getDustTiny(1), 120, 480);
        if (Loader.isModLoaded("bartworks")) {
            GT_Values.RA.addUniversalDistillationRecipe(WerkstoffLoader.AcidicOsmiumSolution.getFluidOrGas(1000), new FluidStack[]{WerkstoffLoader.OsmiumSolution.getFluidOrGas(100), Materials.Water.getFluid(900L)}, GT_Values.NI, 150, 7680);
            GT_Values.RA.addUniversalDistillationRecipe(WerkstoffLoader.HotRutheniumTetroxideSollution.getFluidOrGas(9000), new FluidStack[]{Materials.Water.getFluid(1800L), WerkstoffLoader.RutheniumTetroxide.getFluidOrGas(7200)}, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Salt, 6L), 1500, 480);
        }



        // Electrolyzer Recipes
        GT_Values.RA.addElectrolyzerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Bathwater, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, ItemList.Cell_Empty.get(1L), Materials.Salt.getDust(2), AGEssence.getDust(1), GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{10000,9500,6500,0,0,0}, 20, 480);


        // Assembler Recipes
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Weebium, 6L), GT_OreDictUnificator.get(OrePrefixes.frameGt, Weebium, 1L), ItemList.Casing_Weebium.get(1L), 50, 16);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.UUMatter, 6L), GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.UUMatter, 1L), ItemList.Casing_UUM.get(1L), 50, 16);

        if (Loader.isModLoaded("harvestcraft")) {
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{GT_ModHandler.getModItem("harvestcraft", "cornmealItem", 16L, 0), GT_ModHandler.getModItem("harvestcraft", "cheeseItem", 1L, 0), GT_ModHandler.getModItem("harvestcraft", "chilipepperItem", 1L, 0), Materials.Salt.getDustSmall(1), GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 1L), GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Plastic, 1L)}, Materials.Nitrogen.getGas(16L), ItemList.Doritos.get(4L), 20, 120);
        }

        // Compressor Recipes
        GT_Values.RA.addCompressorRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Weebium, 9L), GT_OreDictUnificator.get(OrePrefixes.block, Weebium, 1L), 1200, 524000);

        // Fusion Reactor Recipes
        GT_Values.RA.addFusionReactorRecipe(Materials.Neutronium.getMolten(98), Materials.Europium.getMolten(98), GTNH_ExtraMaterials.Weebium.getPlasma(169), 16, 8192, 150000000);//FT1


        // Wiremill Recipes
        GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.wireGt01, Weebium, 1L), GT_OreDictUnificator.get(OrePrefixes.wireFine, Weebium, 4L), 1200, 524000);
        GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Weebium, 1L), GT_OreDictUnificator.get(OrePrefixes.wireFine, Weebium, 8L), 2300, 524000);

        // Bending Machine
        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Weebium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Weebium, 1L), 1200, 524000);

        // Extruder
        GT_Values.RA.addExtruderRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Weebium, 9L), ItemList.Shape_Extruder_Block.get(0L), GT_OreDictUnificator.get(OrePrefixes.block, Weebium, 1L), 1200, 524000);
        GT_Values.RA.addExtruderRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Weebium, 1L), ItemList.Shape_Extruder_Plate.get(0L), GT_OreDictUnificator.get(OrePrefixes.plate, Weebium, 1L), 1200, 524000);


        // Alloy Smelter
        GT_Values.RA.addAlloySmelterRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Weebium, 9L), ItemList.Shape_Mold_Block.get(0), GT_OreDictUnificator.get(OrePrefixes.block, Weebium, 1L), 1200, 524000);
        GT_Values.RA.addAlloySmelterRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Weebium, 2L), ItemList.Shape_Mold_Plate.get(0), GT_OreDictUnificator.get(OrePrefixes.plate, Weebium, 1L), 1200, 524000);

        // Cutting Machine
        GT_Values.RA.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Weebium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Weebium, 9L), null, 1200, 524000);
        GT_Values.RA.addCutterRecipe(ItemList.Weebium_Silicon_Boule.get(1), GT_Values.NI, ItemList.Weebium_Wafer.get(64), ItemList.Weebium_Wafer.get(64), 18000, 2000000, true);
        GT_Values.RA.addCutterRecipe(ItemList.Engraved_Weebium_Wafer.get(1), GT_Values.NI, ItemList.Weebium_Chip.get(8), GT_Values.NI, 3200, 2000000, true);


        // Circuit Assembler
       // GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Wetware_Extreme.get(2L), ItemList.Circuit_Wetwarecomputer.get(2L), ItemList.Circuit_Parts_DiodeSMD.get(32L), ItemList.Weebium_Chip.get(16L), ItemList.Circuit_Chip_Ram.get(64L), GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.YttriumBariumCuprate, 24)}, Materials.SolderingAlloy.getMolten(144L), ItemList.Special_Oredrill_Circuit.get(1L), 600, 38400, true);


        // Forge Hammer
        GT_Values.RA.addForgeHammerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Weebium, 3L), GT_OreDictUnificator.get(OrePrefixes.plate, Weebium, 2L), 1200, 524000);

        // Laser Engraver
        GT_Values.RA.addLaserEngraverRecipe(ItemList.Weebium_Wafer.get(1L), GT_Utility.copyAmount(0, GT_OreDictUnificator.get(OrePrefixes.lens, GTNH_ExtraMaterials.AGEssence, 1)), ItemList.Engraved_Weebium_Wafer.get(1L), 48000, 8300000, true);

    }

    public void run2(){


        //Assline Recipes
        if (Loader.isModLoaded("harvestcraft")) {
            GT_Values.RA.addAssemblylineRecipe(ItemList.Dorito_Chip.get(1L, new Object[]{}), 288000, new ItemStack[]{
                            GT_ModHandler.getModItem("harvestcraft", "cornmealItem", 16L, 16),
                            GT_ModHandler.getModItem("harvestcraft", "cheeseItem", 1L, 0),
                            GT_ModHandler.getModItem("harvestcraft", "chilipepperItem", 1L, 0),
                            Materials.Salt.getDustSmall(1),
                            GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 1L),
                            GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Plastic, 1L)}, new FluidStack[]{
                            Materials.FryingOilHot.getFluid(1440),
                            Materials.Nitrogen.getGas(1440)},
                    ItemList.Doritos.get(16), 1200, 32000);
        }

        //T5 Drilling Rig
        GT_Values.RA.addAssemblylineRecipe(ItemList.OreDrill4.get(1L, new Object[]{}), 288000, new ItemStack[]{
                        ItemList.OreDrill4.get(1L),
                        GT_OreDictUnificator.get(OrePrefixes.frameGt, Weebium, 16L),
                        GT_OreDictUnificator.get(OrePrefixes.plate, Weebium, 32L),
                        GT_OreDictUnificator.get(OrePrefixes.stick, Weebium, 32L),
                        GT_OreDictUnificator.get(OrePrefixes.gearGt, Weebium, 8L),
                        GT_OreDictUnificator.get(OrePrefixes.gearGtSmall, Materials.Infinity, 16L),
                        ItemList.Special_Oredrill_Circuit.get(1L),
                        CustomItemList.QuantumCircuit.get(4L),
                        CustomItemList.PikoCircuit.get(8L),
                        CustomItemList.NanoCircuit.get(16L),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Tritanium, 8L),
                        GT_OreDictUnificator.get(OrePrefixes.wireFine, Weebium, 64L),
                        GT_OreDictUnificator.get(OrePrefixes.wireFine, Weebium, 64L),
                        GT_OreDictUnificator.get(OrePrefixes.wireFine, Weebium, 64L),
                        GT_OreDictUnificator.get(OrePrefixes.wireFine, Weebium, 64L),}, new FluidStack[]{
                        Materials.Lubricant.getFluid(9216L),
                        Materials.SolderingAlloy.getMolten(9216L),
                        Materials.Neutronium.getMolten(18432),
                        Materials.UUMatter.getFluid(128000L)},
                ItemList.OreDrill5.get(1L), 36000, 1992000);

        //Ore Drilling Control Circuit
        GT_Values.RA.addAssemblylineRecipe(CustomItemList.QuantumCircuit.get(1L, new Object[]{}), 576000, new ItemStack[]{
                    ItemList.Gamer_girl_Panties.get(64L),
                    CustomItemList.QuantumCircuit.get(8L),
                    CustomItemList.PikoCircuit.get(16L),
                    CustomItemList.NanoCircuit.get(32L),
                    ItemList.Circuit_Wetwaremainframe.get(64L),
                    ItemList.Circuit_Wetwaresupercomputer.get(64L),
                    ItemList.Circuit_Wetwaresupercomputer.get(64L),
                    ItemList.Robot_Arm_UEV.get(32L),
                    ItemList.Sensor_UEV.get(32L),
                    ItemList.Emitter_UEV.get(32L),
                    ItemList.Circuit_Board_Wetware_Extreme.get(64),
                    GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Infinity, 64L),
                    GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Infinity, 64L),
                    GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Infinity, 64L),
                    GT_OreDictUnificator.get(OrePrefixes.plateDense, Weebium, 8L),}, new FluidStack[]{
                    Materials.SolderingAlloy.getMolten(1440L),
                    Materials.BioMediumSterilized.getFluid(64000L),
                    Bathwater.getFluid(64000L)},
                ItemList.Special_Oredrill_Circuit.get(1L), 72000, 1992000);






        }



    }



