package gregtech.loaders.postload;

import com.dreammaster.gthandler.CustomItemList;
import com.github.bartimaeusnek.bartworks.common.loaders.ItemRegistry;
import com.github.bartimaeusnek.bartworks.util.BW_Util;
import cpw.mods.fml.common.Loader;
import gregtech.GT_Mod;
import gregtech.api.enums.*;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import com.github.bartimaeusnek.bartworks.system.material.WerkstoffLoader;
import com.github.technus.tectech.recipe.TT_recipeAdder;
import com.github.technus.tectech.thing.metaTileEntity.hatch.GT_MetaTileEntity_Hatch_Rack;
import scala.xml.MalformedAttributeException;


import static gregtech.api.enums.GTNH_ExtraMaterials.*;
import static gregtech.api.enums.OrePrefixes.*;



public class GT_CustomRecipeLoader implements Runnable {

    private static final long bits = GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE | GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.BUFFERED;


    public void run() {

        new GT_CustomCircuitRecipeLoader().run();

        GT_Values.RA.addTestmachineRecipe(GT_OreDictUnificator.get(dust, Materials.Salt, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(dust, Materials.SodiumHydroxide, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, 20, 16);
        GT_Values.RA.addTestmachineRecipe(GT_OreDictUnificator.get(dust, Materials.Iron, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.Water.getFluid(144), Materials.DilutedHydrochloricAcid.getFluid(144L), GT_OreDictUnificator.get(dust, Materials.Steel, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, 20, 16);
        GT_Values.RA.addTestmachineRecipe(new ItemStack[]{GT_OreDictUnificator.get(dust, Materials.Phosphorus, 1L), GT_OreDictUnificator.get(dust, Materials.Gallium, 1L)}, GT_Values.NF, GT_Values.NF, new ItemStack[]{GT_OreDictUnificator.get(dust, Materials.TricalciumPhosphate, 1L), GT_OreDictUnificator.get(bolt, Materials.HSSG, 2L)}, new int[]{5000, 1000}, 10, (int)GT_Values.V[2]);
        // GT_Values.RA.addTestmachineRecipe(new ItemStack[]{GT_OreDictUnificator.get(dust, Materials.MysteriousCrystal, 1L), GT_OreDictUnificator.get(dust, Materials.Neutronium, 1L)}, Materials.SulfuricAcid.getFluid(144L), Materials.DilutedSulfuricAcid.getFluid(144L), new ItemStack[]{GT_OreDictUnificator.get(dust, Materials.InfinityCatalyst, 1L), GT_OreDictUnificator.get(dust, Materials.Naquadah, 1L)}, new int[]{7500,7500}, 15, 16);
        GT_Values.RA.addMultiblockTestmachineRecipe( new ItemStack[]{GT_OreDictUnificator.get(dust, Materials.MysteriousCrystal, 1L), GT_OreDictUnificator.get(dust, Materials.Neutronium, 1L)}, new FluidStack[]{Materials.Water.getFluid(144L), Materials.SulfuricAcid.getFluid(144L)}, new FluidStack[]{Materials.DilutedSulfuricAcid.getFluid(144L)}, new ItemStack[]{GT_OreDictUnificator.get(dust, Materials.InfinityCatalyst, 1L), GT_OreDictUnificator.get(dust, Materials.Naquadah, 1L)}, new int[]{7500,7500,7500}, 10, 10);
        GT_Values.RA.addMultiblockTestmachineRecipe(new ItemStack[]{GT_OreDictUnificator.get(dust, PMagium, 32L), GT_OreDictUnificator.get(dust, AGEssence, 16L), GT_Utility.getIntegratedCircuit(21)}, new FluidStack[]{Bathwater.getFluid(288L), Materials.Infinity.getMolten(144L)}, new FluidStack[]{Sweat.getFluid(144L), Urine.getFluid(144L)}, new ItemStack[]{GT_OreDictUnificator.get(dustTiny, CursedEntropy, 8), ItemList.Grief_Seed.get(1L)}, new int[]{7500,7500,7500,7500}, 10, 16);
        GT_Values.RA.addMultiblockTestmachineRecipe(
                GT_OreDictUnificator.get(dust, Materials.TricalciumPhosphate, 1L),
                GT_OreDictUnificator.get(dust, Materials.HSSS, 1L),
                GT_Utility.getIntegratedCircuit(2),
                GT_Values.NI,
                Bathwater.getFluid(144l),
                GT_Values.NF,
                Urine.getFluid(72L),
                GT_Values.NF,
                ItemList.Special_Oredrill_Circuit.get(1L),
                GT_OreDictUnificator.get(bolt, Materials.Steel, 1L),
                GT_Values.NI,
                GT_Values.NI,
                new int[]{7500,7500,0,0}, 10, 16);


        // Blast Furnace Recipes
        GT_Values.RA.addBlastRecipe(GT_OreDictUnificator.get(dust, Materials.UUAmplifier, 32), GT_Utility.getIntegratedCircuit(11), Materials.Helium.getPlasma(144L), null, GT_OreDictUnificator.get(ingotHot, Materials.UUAmplifier, 1), null, 400, 19753, 10800);
        GT_Values.RA.addBlastRecipe(GT_OreDictUnificator.get(dust, Materials.UUMatter, 32), GT_Utility.getIntegratedCircuit(11), Bathwater.getFluid(1L), null, GT_OreDictUnificator.get(ingotHot, Materials.UUMatter, 1), null, 400, 19753, 10800);
        GT_Values.RA.addBlastRecipe(GT_OreDictUnificator.get(dust, Weebium, 1L), GT_Utility.getIntegratedCircuit(11), AGEssence.getFluid(144L), null, GT_OreDictUnificator.get(ingotHot, Weebium, 1L), null, 125, 498073, 9001);
        GT_Values.RA.addBlastRecipe(PMagium.getDust(1), GT_Utility.getIntegratedCircuit(11), AGEssence.getFluid(144), null, GT_OreDictUnificator.get(ingotHot, PMagium, 1L), null, 12500, 2000000, 10800);
        GT_Values.RA.addBlastRecipe(GT_OreDictUnificator.get(block, Materials.Silicon, 64), GT_OreDictUnificator.get(ingot, Weebium, 16), AGEssence.getFluid(32000), null, ItemList.Weebium_Silicon_Boule.get(1), null, 36000, 2000000, 10800);

        // Vacuum Freezer Recipes
        GT_Values.RA.addVacuumFreezerRecipe(GT_OreDictUnificator.get(ingotHot, Materials.UUMatter, 1L), GT_OreDictUnificator.get(ingot, Materials.UUMatter, 1L), 400, 13169);
        GT_Values.RA.addVacuumFreezerRecipe(GT_OreDictUnificator.get(ingotHot, Weebium, 1L), GT_OreDictUnificator.get(ingot, Weebium, 1L), 120, 480);
        GT_Values.RA.addVacuumFreezerRecipe(GT_OreDictUnificator.get(ingotHot, Materials.UUAmplifier, 1L), GT_OreDictUnificator.get(ingot, Materials.UUAmplifier, 1L), 400, 13169);
        GT_Values.RA.addVacuumFreezerRecipe(GT_OreDictUnificator.get(ingotHot, PMagium, 1L), GT_OreDictUnificator.get(ingot, PMagium, 1L), 5000, 2000000);


        // Fluid Extractor Recipes
        GT_Values.RA.addFluidExtractionRecipe(GT_OreDictUnificator.get(ingot, Materials.UUMatter, 1L), GT_Values.NI, Materials.UUMatter.getFluid(32L),10000, 10, 32);
        GT_Values.RA.addFluidExtractionRecipe(GT_OreDictUnificator.get(ingot, Materials.UUAmplifier, 1L), GT_Values.NI, Materials.UUAmplifier.getFluid(16L),10000, 10, 32);
        GT_Values.RA.addFluidExtractionRecipe(ItemList.Gamer_girl_Panties.get(1L), GT_Values.NI, GTNH_ExtraMaterials.Bathwater.getFluid(2500L), 10000, 250, 480);
        GT_Values.RA.addFluidExtractionRecipe(AGEssence.getDust(1), GT_Values.NI, AGEssence.getFluid(144L), 10000, 20, 408);
        GT_Values.RA.addFluidExtractionRecipe(GT_OreDictUnificator.get(block, Weebium, 1L), null, Weebium.getMolten(1296L), 10000, 1200, 524000);
        GT_Values.RA.addFluidExtractionRecipe(GT_OreDictUnificator.get(ingot, Weebium, 1L), null, Weebium.getMolten(144L), 10000, 1200, 524000);
        GT_Values.RA.addFluidExtractionRecipe(GT_OreDictUnificator.get(plate, Weebium, 1L), null, Weebium.getMolten(144L), 10000, 1200, 524000);
        GT_Values.RA.addFluidExtractionRecipe(ItemList.Casing_Weebium.get(1L), null, Weebium.getMolten(144L), 10000, 1200, 524000);


        // Canner Recipes
        //GT_Values.RA.addCannerRecipe(ItemList.Large_Fluid_Cell_Neutronium.get(1L), GT_OreDictUnificator.get(gemExquisite, SoulGem, 2L), ItemList.PmCell1.get(1L), GT_Values.NI, 120, 480);

        // Mixer Recipes
        GT_Values.RA.addMixerRecipe(Materials.Europium.getDust(16), Materials.Tartarite.getDust(8), Materials.ElectrumFlux.getDust(6), Materials.Infinity.getDustTiny(1), Materials.CosmicNeutronium.getDust(6), GT_ModHandler.getModItem("dreamcraft", "item.TCetiESeaweedExtract", 16L, 0), Materials.GrowthMediumRaw.getFluid(20000L), Materials.BioMediumRaw.getFluid(20000L), GT_Values.NI, 300, 500000);
        GT_Values.RA.addMixerRecipe(GT_OreDictUnificator.get(dust, AGEssence, 3L), GT_OreDictUnificator.get(dust, Weebium, 7L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(dust, PMagium, 10L), 750, 131072);


        // Chem Reactor Recipes
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{GT_Utility.getIntegratedCircuit(1), ItemList.Circuit_Chip_Stemcell.get(64L), Materials.MysteriousCrystal.getDust(16)}, new FluidStack[]{Materials.BioMediumRaw.getFluid(4000L)}, null, new ItemStack[]{ItemList.Circuit_Chip_Biocell.get(32L)}, 300, 30700);
        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(dust,PMagium, 1L), ItemList.Cell_Empty.get(1L), Materials.NitricAcid.getFluid(144L), GT_Values.NF, GT_OreDictUnificator.get(cell, PMSlurry, 1L), GT_Values.NI, 120, 480);
        GT_Values.RA.addChemicalRecipe(GT_OreDictUnificator.get(cell,PMSlurry, 1L), GT_OreDictUnificator.get(dustTiny, Materials.Infinity, 1L).copy().splitStack(0), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(cell, RedPMSlurry, 1L), GT_Values.NI, 120, 480);
        GT_Values.RA.addMultiblockChemicalRecipe( new ItemStack[]{GT_OreDictUnificator.get(dust, RefPMagium, 1L), GT_OreDictUnificator.get(dust, Materials.CosmicNeutronium, 1L)}, new FluidStack[]{Materials.HydrofluoricAcid.getFluid(144L)}, new FluidStack[]{RefPMagium.getFluid(144)}, new ItemStack[]{GT_OreDictUnificator.get(dustTiny, Materials.CosmicNeutronium, 1L)}, 120, 480);
        GT_Values.RA.addMultiblockChemicalRecipe( new ItemStack[]{GT_Utility.getIntegratedCircuit(2), GT_OreDictUnificator.get(dustTiny, Materials.Infinity, 1L)}, new FluidStack[]{Materials.AntiKnock.getFluid(4000L)}, new FluidStack[]{InfiniteDiesel.getFluid(4000L)}, new ItemStack[]{GT_OreDictUnificator.get(dustTiny, Materials.InfinityCatalyst, 1L)}, 36000, 7680);
        //GT_Values.RA.addChemicalRecipe(ItemList.Weebium_Wafer_Raw.get(1L), GT_OreDictUnificator.get(dust, AGEssence, 1L), Bathwater.getFluid(144), GT_Values.NF, ItemList.Weebium_Wafer.get(1L), 3600, (int)GT_Values.V[9]);
        GT_Values.RA.addMultiblockChemicalRecipe( new ItemStack[]{ItemList.Weebium_Wafer_Raw.get(1L), GT_OreDictUnificator.get(dust, Weebium, 1L), GT_OreDictUnificator.get(dust, Materials.MysteriousCrystal, 4L), CustomItemList.TCetiESeaweedExtract.get(4L), GT_Utility.copyAmount(0, GT_OreDictUnificator.get(dustTiny, Materials.Infinity, 1))}, new FluidStack[]{Bathwater.getFluid(144L)}, null, new ItemStack[]{ItemList.Weebium_Wafer.get(1)}, 3600, (int)GT_Values.V[9]);
        if (Loader.isModLoaded("bartworks")) {
            GT_Values.RA.addChemicalRecipe(GT_Utility.getIntegratedCircuit(1), GT_Values.NI,  WerkstoffLoader.AcidicOsmiumSolution.getFluidOrGas(1000), WerkstoffLoader.OsmiumSolution.getFluidOrGas(100), GT_Values.NI, 150, 7680);
        }


        // Centrifuge Recipes
        GT_Values.RA.addCentrifugeRecipe(ItemList.Gamer_girl_Panties.get(1L), GT_OreDictUnificator.get(cell, Materials.Water, 3L), GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(cell, Bathwater, 3L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{10000,0,0,0,0,0}, 20, 480);
        GT_Values.RA.addCentrifugeRecipe(ItemList.Doritos_Empty.get(1L), GT_Utility.getIntegratedCircuit(12), Materials.HydrochloricAcid.getFluid(16L), Materials.DilutedHydrochloricAcid.getFluid(16L), GT_OreDictUnificator.get(foil, Materials.Plastic, 1L), GT_OreDictUnificator.get(foil, Materials.Aluminium, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{2500,2500,0,0,0,0}, 20, 120);
        GT_Values.RA.addCentrifugeRecipe(GT_OreDictUnificator.get(dust, RedPMSlurry, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(dustSmall, RefPMagium, 1L), GT_OreDictUnificator.get(dustSmall, AGEssence, 1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{6500,5500,0,0,0,0}, 120, 480);
        if (Loader.isModLoaded("bartworks")){
            GT_Values.RA.addCentrifugeRecipe(GT_Utility.getIntegratedCircuit(8), WerkstoffLoader.PTMetallicPowder.get(dust, 8), GT_Values.NF, GT_Values.NF, Materials.Platinum.getDust(2), Materials.Palladium.getDust(2), Materials.Osmium.getDust(1), Materials.Iridium.getDust(1), WerkstoffLoader.Rhodium.get(dust, 1), WerkstoffLoader.Ruthenium.get(dust, 1), new int[]{10000,10000,10000,10000,10000,10000}, 1400, 7690);
        }

        // Distillation Recipes
        GT_Values.RA.addUniversalDistillationRecipe(GTNH_ExtraMaterials.Bathwater.getFluid(1000L), new FluidStack[]{AGEssence.getFluid(144L), Urine.getFluid(144L), Sweat.getFluid(144L), Materials.Water.getFluid(568)}, AGEssence.getDustTiny(1), 120, 480);
        GT_Values.RA.addUniversalDistillationRecipe(RefPMagium.getFluid(144), new FluidStack[]{Materials.HydrofluoricAcid.getFluid(120L)}, GT_OreDictUnificator.get(dustSmall, ConcPMagium, 1L), 120, 480);
        if (Loader.isModLoaded("bartworks")) {
            GT_Values.RA.addUniversalDistillationRecipe(WerkstoffLoader.AcidicOsmiumSolution.getFluidOrGas(1000), new FluidStack[]{WerkstoffLoader.OsmiumSolution.getFluidOrGas(100), Materials.Water.getFluid(900L)}, GT_Values.NI, 150, 7680);
            GT_Values.RA.addUniversalDistillationRecipe(WerkstoffLoader.HotRutheniumTetroxideSollution.getFluidOrGas(9000), new FluidStack[]{Materials.Water.getFluid(1800L), WerkstoffLoader.RutheniumTetroxide.getFluidOrGas(7200)}, GT_OreDictUnificator.get(dust, Materials.Salt, 6L), 1500, 480);
        }



        // Electrolyzer Recipes
        GT_Values.RA.addElectrolyzerRecipe(GT_OreDictUnificator.get(cell, Bathwater, 1L), GT_Values.NI, GT_Values.NF, GT_Values.NF, ItemList.Cell_Empty.get(1L), Materials.Salt.getDust(2), AGEssence.getDust(1), GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{10000,9500,6500,0,0,0}, 20, 480);
        GT_Values.RA.addElectrolyzerRecipe(GT_OreDictUnificator.get(cell, RedPMSlurry, 1L), GT_OreDictUnificator.get(dust, GAGPanties, 1L), GT_Values.NF, Materials.NitricAcid.getFluid(144), GT_OreDictUnificator.get(dust, RedPMSlurry, 1L), ItemList.Cell_Empty.get(1L), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{10000,10000,0,0,0,0}, 120, 480);

        // Assembler Recipes
       // GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(plate, Weebium, 6L), GT_OreDictUnificator.get(frameGt, Weebium, 1L), ItemList.Casing_Weebium.get(1L), 50, 16);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(plate, Materials.UUMatter, 6L), GT_OreDictUnificator.get(frameGt, Materials.UUMatter, 1L), ItemList.Casing_UUM.get(1L), 50, 16);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Large_Fluid_Cell_Neutronium.get(1L), GT_OreDictUnificator.get(gemExquisite, SoulGem, 2L), ItemList.Gamer_girl_Panties.get(2L)}, GT_Values.NF, ItemList.PmCell1.get(1L), 2400, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.PmCell1.get(2L), GT_OreDictUnificator.get(stickLong, Materials.Neutronium, 4L), ItemList.Gamer_girl_Panties.get(2L)}, GT_Values.NF, ItemList.PmCell2.get(1L), 2400, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.PmCell2.get(2L), GT_OreDictUnificator.get(stickLong, Materials.Neutronium, 4L), ItemList.Gamer_girl_Panties.get(2L)}, GT_Values.NF, ItemList.PmCell4.get(1L), 2400, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{GT_OreDictUnificator.get(stick, Materials.Neutronium, 2L), ItemList.Field_Generator_UHV.get(2L), GT_OreDictUnificator.get(cableGt04, Materials.Bedrockium, 2L), GT_OreDictUnificator.get(circuit, Materials.Quantum, 2L), ItemList.Hull_MAX.get(1L)}, GT_Values.NF, ItemList.Generator_Naquadah_Mark_X.get(1L), 120, 480);
        //GT_Values.RA.addAssemblerRecipe(new ItemStack[]{GT_OreDictUnificator.get(stick, Materials.Iron, 4L), GT_Utility.getIntegratedCircuit(17)}, GT_Values.NF, GT_ModHandler.getModItem("gregtech", "gt.blockmachines", 1L, 12012), 120, 480);
        //GT_Values.RA.addAssemblerRecipe(new ItemStack[]{GT_OreDictUnificator.get(stick, Materials.Steel, 4L), GT_Utility.getIntegratedCircuit(17)}, GT_Values.NF, GT_ModHandler.getModItem("gregtech", "gt.blockmachines", 1L, 12013), 120, 480);
        //GT_Values.RA.addAssemblerRecipe(new ItemStack[]{GT_OreDictUnificator.get(stick, Materials.HSSG, 4L), GT_Utility.getIntegratedCircuit(17)}, GT_Values.NF, GT_ModHandler.getModItem("gregtech", "gt.blockmachines", 1L, 12014), 120, 480);
        if (Loader.isModLoaded("dreamcraft")) {
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Quantum_Tank_IV.get(1L), CustomItemList.Hull_UEV.get(1L), ItemList.Hatch_Output_MAX.get(64L), GT_Utility.getIntegratedCircuit(2)}, Materials.Polybenzimidazole.getMolten(16000), ItemList.Hatch_Output_Mega.get(1L), 1200, 7864320);
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{CustomItemList.Hull_UEV.get(1L), GT_OreDictUnificator.get(OrePrefixes.pipeLarge, Materials.DraconiumAwakened, 1L), ItemList.Electric_Motor_UEV.get(1L), GT_OreDictUnificator.get(OrePrefixes.rotor, Materials.InfinityCatalyst, 1L), GT_Utility.getIntegratedCircuit(3)}, GT_Values.NF, ItemList.Hatch_Muffler_UEV.get(1L), 200, 2000000);
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{CustomItemList.Hull_UIV.get(1L), GT_OreDictUnificator.get(OrePrefixes.pipeLarge, Materials.Infinity, 1L), ItemList.Electric_Motor_UIV.get(1L), GT_OreDictUnificator.get(OrePrefixes.rotor, Materials.Infinity, 1L), GT_Utility.getIntegratedCircuit(3)}, GT_Values.NF, ItemList.Hatch_Muffler_UIV.get(1L), 200, 2000000);
        }
                // Solar Pannels
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Cover_SolarPanel_LV.get(14L), ItemList.Weebium_Chip.get(2L), ItemList.Gamer_girl_Panties.get(2L), ItemList.Circuit_Parts_DiodeSMD.get(2L), GT_OreDictUnificator.get(cableGt16, Materials.RedstoneAlloy, 2L)}, GT_Values.NF, ItemList.Cover_SolarPanel_LVx16.get(1L), 32, 32, true);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Cover_SolarPanel_MV.get(14L), ItemList.Weebium_Chip.get(4L), ItemList.Gamer_girl_Panties.get(4L), ItemList.Circuit_Parts_DiodeSMD.get(4L), GT_OreDictUnificator.get(wireGt16, Materials.SuperconductorMV, 4L)}, GT_Values.NF, ItemList.Cover_SolarPanel_MVx16.get(1L), 128, 128, true);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Cover_SolarPanel_HV.get(14L), ItemList.Weebium_Chip.get(8L), ItemList.Gamer_girl_Panties.get(8L), ItemList.Circuit_Parts_DiodeSMD.get(8L), GT_OreDictUnificator.get(wireGt16, Materials.SuperconductorHV, 8L)}, GT_Values.NF, ItemList.Cover_SolarPanel_HVx16.get(1L), 512, 512, true);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Cover_SolarPanel_EV.get(14L), ItemList.Weebium_Chip.get(12L), ItemList.Gamer_girl_Panties.get(12L), ItemList.Circuit_Parts_DiodeSMD.get(12L), GT_OreDictUnificator.get(wireGt16, Materials.SuperconductorEV, 12L)}, GT_Values.NF, ItemList.Cover_SolarPanel_EVx16.get(1L), 2048, 2048, true);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Cover_SolarPanel_IV.get(14L), ItemList.Weebium_Chip.get(20L), ItemList.Gamer_girl_Panties.get(20L), ItemList.Circuit_Parts_DiodeSMD.get(20L), GT_OreDictUnificator.get(wireGt16, Materials.SuperconductorIV, 20L)}, GT_Values.NF, ItemList.Cover_SolarPanel_IVx16.get(1L), 8192, 8192, true);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Cover_SolarPanel_LuV.get(14L), ItemList.Weebium_Chip.get(26L), ItemList.Gamer_girl_Panties.get(26L), ItemList.Circuit_Parts_DiodeSMD.get(26L), GT_OreDictUnificator.get(wireGt16, Materials.SuperconductorLuV, 26L)}, GT_Values.NF, ItemList.Cover_SolarPanel_LuVx16.get(1L), 32768, 32768, true);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Cover_SolarPanel_ZPM.get(14L), ItemList.Weebium_Chip.get(32L), ItemList.Gamer_girl_Panties.get(32L), ItemList.Circuit_Parts_DiodeSMD.get(32L), GT_OreDictUnificator.get(wireGt16, Materials.SuperconductorZPM, 32L)}, GT_Values.NF, ItemList.Cover_SolarPanel_ZPMx16.get(1L), 131072, 131072, true);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Cover_SolarPanel_UV.get(14L), ItemList.Weebium_Chip.get(40L), ItemList.Gamer_girl_Panties.get(40L), ItemList.Circuit_Parts_DiodeSMD.get(40L), GT_OreDictUnificator.get(wireGt16, Materials.SuperconductorUV, 40L)}, GT_Values.NF, ItemList.Cover_SolarPanel_UVx16.get(1L), 524288, 524288, true);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Cover_SolarPanel_UHV.get(14L), ItemList.Weebium_Chip.get(48L), ItemList.Gamer_girl_Panties.get(48L), ItemList.Circuit_Parts_DiodeSMD.get(48L), GT_OreDictUnificator.get(wireGt16, Materials.SuperconductorUHV, 48L)}, GT_Values.NF, ItemList.Cover_SolarPanel_UHVx16.get(1L), 2097152, 2097152, true);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(wireGt02, PMagium, 8L), CustomItemList.MicaInsulatorFoil.get(48L), Materials.Infinity.getMolten(144L), ItemList.Casing_Coil_Infinity.get(1L), 1000, 2000000);


        if (Loader.isModLoaded("harvestcraft")) {
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{GT_ModHandler.getModItem("harvestcraft", "cornmealItem", 16L, 0), GT_ModHandler.getModItem("harvestcraft", "cheeseItem", 1L, 0), GT_ModHandler.getModItem("harvestcraft", "chilipepperItem", 1L, 0), Materials.Salt.getDustSmall(1), GT_OreDictUnificator.get(foil, Materials.Aluminium, 1L), GT_OreDictUnificator.get(foil, Materials.Plastic, 1L)}, Materials.Nitrogen.getGas(16L), ItemList.Doritos.get(4L), 20, 120);
        }


        // Compressor Recipes
        GT_Values.RA.addCompressorRecipe(GT_OreDictUnificator.get(ingot, Weebium, 9L), GT_OreDictUnificator.get(block, Weebium, 1L), 1200, 524000);

        // Fusion Reactor Recipes
        GT_Values.RA.addFusionReactorRecipe(Materials.Neutronium.getMolten(32), Materials.Europium.getMolten(40), GTNH_ExtraMaterials.Weebium.getPlasma(72), 20, 95000, 500000000);//FT1


        // Wiremill Recipes
        GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(wireGt01, Weebium, 1L), GT_OreDictUnificator.get(wireFine, Weebium, 4L), 1200, 524000);
        GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(ingot, Weebium, 1L), GT_OreDictUnificator.get(wireFine, Weebium, 8L), 2300, 524000);
        GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(ingot, PMagium, 1L), GT_OreDictUnificator.get(wireGt01, PMagium, 2L), 1200, 524000);
        GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(wireGt01, PMagium, 1L), GT_OreDictUnificator.get(wireFine, PMagium, 4L), 1200, 524000);
        GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(ingot, PMagium, 1L), GT_OreDictUnificator.get(wireFine, PMagium, 8L), 1200, 524000);


        // Bending Machine
        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(ingot, Weebium, 1L), GT_OreDictUnificator.get(plate, Weebium, 1L), 1200, 524000);
        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(plate, Weebium, 1L), GT_OreDictUnificator.get(foil, Weebium, 4L), 1200, 524000);

        // Extruder
        GT_Values.RA.addExtruderRecipe(GT_OreDictUnificator.get(ingot, Weebium, 9L), ItemList.Shape_Extruder_Block.get(0L), GT_OreDictUnificator.get(block, Weebium, 1L), 1200, 524000);
        GT_Values.RA.addExtruderRecipe(GT_OreDictUnificator.get(ingot, Weebium, 1L), ItemList.Shape_Extruder_Plate.get(0L), GT_OreDictUnificator.get(plate, Weebium, 1L), 1200, 524000);
        GT_Values.RA.addExtruderRecipe(GT_OreDictUnificator.get(ingot, Weebium, 1), ItemList.Shape_Extruder_Small_Gear.get(0L), GT_OreDictUnificator.get(gearGtSmall, Weebium, 1L), 1200, 524000);
        GT_Values.RA.addExtruderRecipe(GT_OreDictUnificator.get(ingot, Weebium, 6L), ItemList.Shape_Extruder_Turbine_Blade.get(0L), GT_OreDictUnificator.get(turbineBlade, Weebium, 1L), 1200, 524000);

        // Alloy Smelter
        GT_Values.RA.addAlloySmelterRecipe(GT_OreDictUnificator.get(ingot, Weebium, 9L), ItemList.Shape_Mold_Block.get(0), GT_OreDictUnificator.get(block, Weebium, 1L), 1200, 524000);
        GT_Values.RA.addAlloySmelterRecipe(GT_OreDictUnificator.get(ingot, Weebium, 2L), ItemList.Shape_Mold_Plate.get(0), GT_OreDictUnificator.get(plate, Weebium, 1L), 1200, 524000);

        // Cutting Machine
        GT_Values.RA.addCutterRecipe(GT_OreDictUnificator.get(block, Weebium, 1L), GT_OreDictUnificator.get(plate, Weebium, 9L), null, 1200, 524000);
        GT_Values.RA.addCutterRecipe(ItemList.Weebium_Silicon_Boule.get(1), GT_Values.NI, ItemList.Weebium_Wafer_Raw.get(64), ItemList.Weebium_Wafer_Raw.get(64), 18000, 2000000, true);
        GT_Values.RA.addCutterRecipe(ItemList.Engraved_Weebium_Wafer.get(1), GT_Values.NI, ItemList.Weebium_Chip.get(8), GT_Values.NI, 3200, 2000000, true);


        // Circuit Assembler
       // GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.Circuit_Board_Wetware_Extreme.get(2L), ItemList.Circuit_Wetwarecomputer.get(2L), ItemList.Circuit_Parts_DiodeSMD.get(32L), ItemList.Weebium_Chip.get(16L), ItemList.Circuit_Chip_Ram.get(64L), GT_OreDictUnificator.get(wireFine, Materials.YttriumBariumCuprate, 24)}, Materials.SolderingAlloy.getMolten(144L), ItemList.Special_Oredrill_Circuit.get(1L), 600, 38400, true);
          GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{ItemList.VOLUMETRIC_FLASK.get(1L), GT_OreDictUnificator.get(circuit, Materials.Advanced, 1L), GT_OreDictUnificator.get(foil, Materials.Polybenzimidazole, 4L), GT_OreDictUnificator.get(wireFine, Materials.ElectrumFlux, 16L)}, Materials.SolderingAlloy.getMolten(432), ItemList.VOLUMETRIC_FLASK_ADVANCED.get(1L), 2800, (int)GT_Values.V[3]);

        // Macerator Recipes
        GT_Values.RA.addPulveriserRecipe(ItemList.Gamer_girl_Panties.get(1L), new ItemStack[]{GT_OreDictUnificator.get(dust, GAGPanties, 1L)}, new int[]{10000}, 120, 480);
        GT_Values.RA.addPulveriserRecipe(GT_OreDictUnificator.get(gemExquisite, SoulGem, 1L), new ItemStack[]{GT_OreDictUnificator.get(dust, SoulGem, 4L)}, new int[]{10000}, 120, 480);
        GT_Values.RA.addPulveriserRecipe(GT_OreDictUnificator.get(bolt, SoulGem, 1L), new ItemStack[]{GT_OreDictUnificator.get(dustTiny, SoulGem, 1)}, new int[]{10000}, 120, 480);
        GT_Values.RA.addPulveriserRecipe(GT_OreDictUnificator.get(block, Materials.Mica, 1L), new ItemStack[]{GT_OreDictUnificator.get(dust, Materials.Mica, 9L)}, new int[]{10000}, 120, 32);

        // Forge Hammer
        GT_Values.RA.addForgeHammerRecipe(GT_OreDictUnificator.get(ingot, Weebium, 3L), GT_OreDictUnificator.get(plate, Weebium, 2L), 1200, 524000);

        // Laser Engraver
        GT_Values.RA.addLaserEngraverRecipe(ItemList.Weebium_Wafer.get(1L), GT_Utility.copyAmount(0, GT_OreDictUnificator.get(lens, GTNH_ExtraMaterials.AGEssence, 1)), ItemList.Engraved_Weebium_Wafer.get(1L), 48000, 8300000, true);

        // Autoclave Recipes
        GT_Values.RA.addAutoclaveRecipe(GT_OreDictUnificator.get(dust, ConcPMagium, 16L), Bathwater.getFluid(144L), GT_OreDictUnificator.get(gemExquisite, SoulGem, 1L), 7500, 216000, 131000);
        GT_Values.RA.addAutoclaveRecipe(GT_OreDictUnificator.get(dustTiny, SoulGem, 1L), Materials.Infinity.getMolten(8L), GT_OreDictUnificator.get(gemExquisite, SoulGem, 1L), 7500, 216000, 131000);
        GT_Values.RA.addAutoclaveRecipe(GT_OreDictUnificator.get(dust, CursedEntropy, 1L), Materials.Americium.getMolten(8L), GT_OreDictUnificator.get(gem, CursedEntropy, 1L), 7500, 1000,131000);
        GT_Values.RA.addAutoclaveRecipe(GT_OreDictUnificator.get(dust, CursedEntropy, 1L), WerkstoffLoader.Ruthenium.getMolten(16), GT_OreDictUnificator.get(gem, CursedEntropy, 1L), 7500, 1200,131000);


        new GT_CustomCraftingRecipeLoader().run();
           }

    public void run2(){


        //Assline Recipes
        if (Loader.isModLoaded("harvestcraft")) {
            GT_Values.RA.addAssemblylineRecipe(ItemList.Dorito_Chip.get(1L, new Object[]{}), 288000, new ItemStack[]{
                            GT_ModHandler.getModItem("harvestcraft", "cornmealItem", 16L, 16),
                            GT_ModHandler.getModItem("harvestcraft", "cheeseItem", 1L, 0),
                            GT_ModHandler.getModItem("harvestcraft", "chilipepperItem", 1L, 0),
                            Materials.Salt.getDustSmall(1),
                            GT_OreDictUnificator.get(foil, Materials.Aluminium, 1L),
                            GT_OreDictUnificator.get(foil, Materials.Plastic, 1L)}, new FluidStack[]{
                            Materials.FryingOilHot.getFluid(1440),
                            Materials.Nitrogen.getGas(1440)},
                    ItemList.Doritos.get(16), 1200, 32000);
        }




        if (Loader.isModLoaded("dreamcraft") && Loader.isModLoaded("tectech")) {

            // T5 Drilling Rig
            TT_recipeAdder.addResearchableAssemblylineRecipe(
                    ItemList.OreDrill4.get(1L),
                    1228800,
                    4096,
                    BW_Util.getMachineVoltageFromTier(11),
                    32,
                    new Object[]{
                            ItemList.OreDrill4.get(1L),
                            GT_OreDictUnificator.get(frameGt, Weebium, 16L),
                            GT_OreDictUnificator.get(plate, Weebium, 32L),
                            GT_OreDictUnificator.get(stick, Weebium, 32L),
                            GT_OreDictUnificator.get(gearGt, Weebium, 8L),
                            GT_OreDictUnificator.get(gearGtSmall, Materials.Infinity, 16L),
                            ItemList.Special_Oredrill_Circuit.get(1L),
                            CustomItemList.QuantumCircuit.get(4L),
                            CustomItemList.PikoCircuit.get(8L),
                            CustomItemList.NanoCircuit.get(16L),
                            GT_OreDictUnificator.get(plateDense, Materials.Tritanium, 8L),
                            GT_OreDictUnificator.get(wireFine, Weebium, 64L),
                            GT_OreDictUnificator.get(wireFine, Weebium, 64L),
                            GT_OreDictUnificator.get(wireFine, Weebium, 64L),
                            GT_OreDictUnificator.get(wireFine, Weebium, 64L)
                    },
                    new FluidStack[]{
                            Materials.Lubricant.getFluid(9216L),
                            Materials.SolderingAlloy.getMolten(9216L),
                            Materials.Neutronium.getMolten(18432),
                            Materials.UUMatter.getFluid(128000L)
                    },
                    ItemList.OreDrill5.get(1L),
                    240000,
                    BW_Util.getMachineVoltageFromTier(11)
            );


            // Special Oredrill Circuit
            TT_recipeAdder.addResearchableAssemblylineRecipe(
                    CustomItemList.QuantumCircuit.get(1L),
                    1228800,
                    4096,
                    BW_Util.getMachineVoltageFromTier(11),
                    16,
                    new Object[]{
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
                            GT_OreDictUnificator.get(wireFine, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(wireFine, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(wireFine, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(plateDense, Weebium, 8L)
                    },
                    new FluidStack[]{
                            Materials.SolderingAlloy.getMolten(1440L),
                            Materials.BioMediumSterilized.getFluid(64000L),
                            Bathwater.getFluid(64000L)
                    },
                    ItemList.Special_Oredrill_Circuit.get(1L),
                    240000,
                    BW_Util.getMachineVoltageFromTier(11)
            );



            // Motor UIV
            TT_recipeAdder.addResearchableAssemblylineRecipe(
                    ItemList.Electric_Motor_UEV.get(1L),
                    64000,
                    128,
                    BW_Util.getMachineVoltageFromTier(9),
                    8,
                    new Object[]{
                            GT_OreDictUnificator.get(OrePrefixes.stickLong, Materials.SamariumMagnetic, 16L),
                            GT_OreDictUnificator.get(OrePrefixes.stickLong, Weebium, 4L),
                            GT_OreDictUnificator.get(OrePrefixes.ring, Materials.Infinity, 4L),
                            GT_OreDictUnificator.get(OrePrefixes.round, Materials.Infinity, 16L),
                            GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Tartarite, 64L),
                            GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Tartarite, 64L),
                            GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Tartarite, 64L),
                            GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Tartarite, 64L),
                            GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Tartarite, 64L),
                            GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Tartarite, 64L),
                            GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Tartarite, 64L),
                            GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Tartarite, 64L),
                            GT_OreDictUnificator.get(OrePrefixes.cableGt04, Materials.DraconiumAwakened, 8L)
                    },
                    new FluidStack[]{
                            Materials.Naquadria.getMolten(1296),
                            Materials.SolderingAlloy.getMolten(1296),
                            Materials.Lubricant.getFluid(2000)
                    },
                    ItemList.Electric_Motor_UIV.get(1L),
                    240000,
                    BW_Util.getMachineVoltageFromTier(9)
            );


            // UIV Conveyor
            TT_recipeAdder.addResearchableAssemblylineRecipe(
                    ItemList.Conveyor_Module_UEV.get(1L),
                    64000,
                    128,
                    BW_Util.getMachineVoltageFromTier(9),
                    8,
                    new Object[]{
                            ItemList.Electric_Motor_UIV.get(2, new Object(){}),
                            GT_OreDictUnificator.get(OrePrefixes.plate, Weebium, 2L),
                            GT_OreDictUnificator.get(OrePrefixes.ring, Materials.Infinity, 4L),
                            GT_OreDictUnificator.get(OrePrefixes.round, Materials.Infinity, 32L),
                            GT_OreDictUnificator.get(OrePrefixes.cableGt04, Materials.DraconiumAwakened, 2L)
                    },
                    new FluidStack[]{
                            Materials.SolderingAlloy.getMolten(4608),
                            Materials.Lubricant.getFluid(16000),
                            Materials.StyreneButadieneRubber.getMolten(23040)
                    },
                    ItemList.Conveyor_Module_UIV.get(1L),
                    240000,
                    BW_Util.getMachineVoltageFromTier(9)
            );


            // UIV Pump
            TT_recipeAdder.addResearchableAssemblylineRecipe(
                    ItemList.Electric_Pump_UEV.get(1L),
                    64000,
                    128,
                    BW_Util.getMachineVoltageFromTier(9),
                    8,
                    new Object[]{
                            GT_OreDictUnificator.get(OrePrefixes.pipeLarge, Materials.DraconiumAwakened, 2L),
                            GT_OreDictUnificator.get(OrePrefixes.plate, Weebium, 2L),
                            GT_OreDictUnificator.get(OrePrefixes.screw, Materials.Infinity, 8L),
                            new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.ring, (Materials.StyreneButadieneRubber), 16L), GT_OreDictUnificator.get(OrePrefixes.ring, (Materials.Silicone), 16L)},
                            GT_OreDictUnificator.get(OrePrefixes.rotor, Materials.InfinityCatalyst, 2L),
                            GT_OreDictUnificator.get(OrePrefixes.cableGt04, Materials.DraconiumAwakened, 2L)
                    },
                    new FluidStack[]{
                            Materials.Naquadria.getMolten(4608),
                            Materials.SolderingAlloy.getMolten(4608),
                            Materials.Lubricant.getFluid(16000)
                    },
                    ItemList.Electric_Pump_UIV.get(1L),
                    240000,
                    BW_Util.getMachineVoltageFromTier(9)
            );


            // UIV Piston
            TT_recipeAdder.addResearchableAssemblylineRecipe(
                    ItemList.Electric_Piston_UEV.get(1L),
                    64000,
                    128,
                    BW_Util.getMachineVoltageFromTier(9),
                    8,
                    new Object[]{
                            ItemList.Electric_Motor_UEV.get(1L),
                            GT_OreDictUnificator.get(plate, Materials.Neutronium, 12L),
                            GT_OreDictUnificator.get(ring, Materials.Neutronium, 16L),
                            GT_OreDictUnificator.get(round, Materials.Neutronium, 64L),
                            GT_OreDictUnificator.get(stick, Materials.Neutronium, 16L),
                            GT_OreDictUnificator.get(gearGt, Materials.Neutronium, 4L),
                            GT_OreDictUnificator.get(gearGtSmall, Materials.Neutronium, 8L),
                            GT_OreDictUnificator.get(cableGt04, Materials.DraconiumAwakened, 8L),
                    },
                    new FluidStack[]{
                            Materials.Naquadria.getMolten(5184),
                            Materials.SolderingAlloy.getMolten(10368),
                            Materials.Lubricant.getMolten(16000)
                    },
                    ItemList.Electric_Piston_UIV.get(1L),
                    240000,
                    BW_Util.getMachineVoltageFromTier(9)
            );

            // UIV Sensor
            TT_recipeAdder.addResearchableAssemblylineRecipe(
                    ItemList.Sensor_UEV.get(1L),
                    64000,
                    128,
                    BW_Util.getMachineVoltageFromTier(9),
                    8,
                    new Object[]{
                            GT_OreDictUnificator.get(frameGt, Materials.Infinity, 12L),
                            ItemList.Electric_Motor_UIV.get(1L),
                            GT_OreDictUnificator.get(plate, Materials.Infinity, 16L),
                            ItemList.Gravistar.get(32L),
                            CustomItemList.NanoCircuit.get(8L),
                            GT_OreDictUnificator.get(foil, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(foil, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(foil, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(foil, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(cableGt04, Materials.DraconiumAwakened, 14L),
                    },
                    new FluidStack[]{
                            Materials.Naquadria.getMolten(5184),
                            Materials.SolderingAlloy.getMolten(10368),
                            Weebium.getMolten(16000)
                    },
                    ItemList.Sensor_UIV.get(1L),
                    240000,
                    BW_Util.getMachineVoltageFromTier(9)
            );

            // UEV Emitter
            TT_recipeAdder.addResearchableAssemblylineRecipe(
                    ItemList.Emitter_UEV.get(1L),
                    64000,
                    128,
                    BW_Util.getMachineVoltageFromTier(9),
                    8,
                    new Object[]{
                            GT_OreDictUnificator.get(frameGt, Materials.Infinity, 12L),
                            ItemList.Electric_Motor_UIV.get(1L),
                            GT_OreDictUnificator.get(stick, Materials.Infinity, 16L),
                            ItemList.Gravistar.get(32L),
                            CustomItemList.NanoCircuit.get(8L),
                            GT_OreDictUnificator.get(foil, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(foil, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(foil, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(foil, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(cableGt04, Materials.DraconiumAwakened, 14L),
                    },
                    new FluidStack[]{
                            Materials.Naquadria.getMolten(5184),
                            Materials.SolderingAlloy.getMolten(10368),
                            Weebium.getMolten(16000)
                    },
                    ItemList.Emitter_UIV.get(1L),
                    240000,
                    BW_Util.getMachineVoltageFromTier(9)
            );

            // Smug Weebium Casing
            TT_recipeAdder.addResearchableAssemblylineRecipe(
                    ItemList.Casing_MiningOsmiridium.get(1L),
                    2147483646,
                    4096,
                    BW_Util.getMachineVoltageFromTier(11),
                    8,
                    new Object[]{
                            GT_OreDictUnificator.get(frameGt, Weebium, 1L),
                            GT_OreDictUnificator.get(plate, Weebium, 6L),
                            GT_OreDictUnificator.get(foil, Materials.Osmiridium, 8L),
                            GT_OreDictUnificator.get(foil, Materials.Infinity, 4L),
                            GT_OreDictUnificator.get(stickLong, Materials.Neutronium, 32L),
                            ItemList.Field_Generator_UIV.get(1L)
                    },
                    new FluidStack[]{
                            Materials.Tritanium.getMolten(2304),
                            WerkstoffLoader.LuVTierMaterial.getMolten(23040),
                            WerkstoffLoader.Ruridit.getMolten(2304)
                    },
                    ItemList.Casing_Weebium.get(1L),
                    240000,
                    BW_Util.getMachineVoltageFromTier(11)
            );

            // Field Gen XI
            TT_recipeAdder.addResearchableAssemblylineRecipe(
                    ItemList.Field_Generator_UEV.get(1L),
                    1048576,
                    4096,
                    BW_Util.getMachineVoltageFromTier(11),
                    8,
                    new Object[]{
                            GT_OreDictUnificator.get(frameGt, Weebium, 1L),
                            GT_OreDictUnificator.get(plate, Weebium, 6L),
                            ItemList.Special_Oredrill_Circuit.get(8L),
                            GT_OreDictUnificator.get(wireFine, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(wireFine, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(wireFine, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(wireFine, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(wireFine, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(wireFine, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(wireFine, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(wireFine, Materials.Infinity, 64L),
                            GT_OreDictUnificator.get(cableGt08, Materials.DraconiumAwakened, 8L),
                    },
                    new FluidStack[]{
                            Materials.Quantum.getMolten(3456L),
                            Materials.SolderingAlloy.getMolten(18432L),
                    },
                    ItemList.Field_Generator_UIV.get(1L),
                    240000,
                    BW_Util.getMachineVoltageFromTier(11)
            );

            // Highly Ultimate Battery
            TT_recipeAdder.addResearchableAssemblylineRecipe(
                    CustomItemList.BatteryHull_UHV_Full.get(1L),
                    12345678,
                    512,
                    BW_Util.getMachineVoltageFromTier(9),
                    8,
                    new Object[]{
                            GT_OreDictUnificator.get(OrePrefixes.plateDouble, Materials.Neutronium, 64L),
                            new Object[]{OrePrefixes.circuit.get(Materials.Nano), 1L},
                            new Object[]{OrePrefixes.circuit.get(Materials.Nano), 1L},
                            new Object[]{OrePrefixes.circuit.get(Materials.Nano), 1L},
                            new Object[]{OrePrefixes.circuit.get(Materials.Nano), 1L},
                            ItemList.ZPM2.get(8),
                            ItemList.Field_Generator_UEV.get(4),
                            ItemList.Circuit_Wafer_UHPIC.get(64),
                            ItemList.Circuit_Wafer_UHPIC.get(64),
                            ItemList.Weebium_Wafer.get(32),
                            ItemList.Circuit_Parts_DiodeSMD.get(64),
                            GT_OreDictUnificator.get(OrePrefixes.wireGt02, Weebium, 64),
                    },
                    new FluidStack[]{
                            Materials.SolderingAlloy.getMolten(3760),
                            Materials.Naquadria.getMolten(9000),
                            new FluidStack(FluidRegistry.getFluid("ic2coolant"), 32000)
                    },
                    ItemList.ZPM4.get(1L),
                    24000,
                    BW_Util.getMachineVoltageFromTier(9)
            );



            //new GT_MetaTileEntity_Hatch_Rack.RackComponent(ItemList.Special_Oredrill_Circuit.get(1), 256, 0, -.8F, 12000, true);
        }


        }



    }



