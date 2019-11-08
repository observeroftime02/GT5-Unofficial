package gregtech.loaders.postload;

import com.dreammaster.gthandler.CustomItemList;
import com.github.bartimaeusnek.bartworks.system.material.CircuitGeneration.BW_Meta_Items;
import com.github.technus.tectech.recipe.TT_recipeAdder;
import cpw.mods.fml.common.Loader;
import gregtech.GT_Mod;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import static gregtech.api.enums.Materials.*;
import static gregtech.api.enums.ItemList.*;

public class GT_CustomCircuitRecipeLoader implements Runnable {

    private final static long mMultiplier = GT_Mod.gregtechproxy.mCircuitMultiplier;

    @Override
    public void run() {



        ItemList Dio = ItemList.Circuit_Parts_DiodeSMD;
        ItemList Cap = ItemList.Circuit_Parts_CapacitorSMD;
        ItemList Tra = ItemList.Circuit_Parts_TransistorSMD;
        ItemList Res = ItemList.Circuit_Parts_ResistorSMD;
        ItemList Coi = ItemList.Circuit_Parts_Coil;
        ItemList Ram = ItemList.Circuit_Chip_Ram;

        //Wetware Supercomputer
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Wetware_Extreme.get(1L),
                Circuit_Wetwarecomputer.get(2L),
                Dio.get(32),
                Circuit_Chip_NOR.get(16L),
                Circuit_Chip_Ram.get(64L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                Circuit_Wetwaresupercomputer.get(mMultiplier <= 0 ? 1 : mMultiplier), 600, 38400);

        // Bioprocessor
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Chip_BioCPU.get(1L),
                Circuit_Chip_CrystalSoC2.get(1L),
                Circuit_Chip_NanoCPU.get(2L),
                Circuit_Parts_CapacitorSMD.get(48L),
                Circuit_Parts_TransistorSMD.get(48L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                Circuit_Bioprocessor.get(mMultiplier <= 0 ? 1 : mMultiplier), 600, 153600);

        // Wetware Processor
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Chip_NeuroCPU.get(1L),
                Circuit_Chip_CrystalSoC.get(1L),
                Circuit_Chip_NanoCPU.get(1L),
                Circuit_Parts_CapacitorSMD.get(32L),
                Circuit_Parts_TransistorSMD.get(32L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                Circuit_Neuroprocessor.get(mMultiplier <= 0 ? 1 : mMultiplier), 400, 38400);

        // Wetware Processor Assembly
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Wetware_Extreme.get(1L),
                Circuit_Neuroprocessor.get(2L),
                Circuit_Parts_Coil.get(32L),
                Cap.get(48),
                Circuit_Chip_Ram.get(24),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                Circuit_Wetwarecomputer.get(mMultiplier <= 0 ? 1 : mMultiplier), 600, 38400);

        // Crystalprocessor
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Elite.get(1L),
                Circuit_Chip_CrystalSoC.get(1L),
                Circuit_Chip_NanoCPU.get(2L),
                Cap.get(24L),
                Tra.get(24L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72L),
                Circuit_Crystalprocessor.get(mMultiplier <= 0 ? 1 : mMultiplier), 200, 9600);

        // Nanoprocessor
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Advanced.get(1L),
                Circuit_Chip_SoC.get(1L),
                GT_OreDictUnificator.get(OrePrefixes.bolt, Platinum, 8L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72L),
                Circuit_Nanoprocessor.get(mMultiplier <= 0 ? 1 : mMultiplier), 200, 600);

        //Quantumprocessor
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Fiberglass_Advanced.get(1L),
                Circuit_Chip_SoC.get(1L),
                GT_OreDictUnificator.get(OrePrefixes.bolt, NiobiumTitanium, 8L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72L),
                Circuit_Quantumprocessor.get(mMultiplier <= 0 ? 1 : mMultiplier), 200, 2400);

        // Integrated procesor
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Plastic.get(1L),
                Circuit_Chip_SoC.get(1L),
                GT_OreDictUnificator.get(OrePrefixes.bolt, AnnealedCopper, 8L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72L),
                Circuit_Processor.get(mMultiplier <= 0 ? 1 : mMultiplier), 200, 60);

        //Nanoprocessor Mainframe
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Aluminium, 2L),
                Circuit_Elitenanocomputer.get(2L),
                Coi.get(16L),
                Cap.get(32L),
                Ram.get(16L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                ItemList.Circuit_Master.get(mMultiplier <= 0 ? 1 : mMultiplier), 1600, 1920);

        //Crystallprocessor Mainframe
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Aluminium, 2L),
                Circuit_Ultimatecrystalcomputer.get(2L),
                Coi.get(32L),
                Cap.get(64L),
                Ram.get(32L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                Circuit_Crystalmainframe.get(mMultiplier <= 0 ? 1 : mMultiplier), 1600, 30720);

        //Elite Nanocomputer
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Advanced.get(1L),
                Circuit_Nanocomputer.get(2L),
                Dio.get(8L),
                Circuit_Chip_NOR.get(4L),
                Ram.get(16L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                Circuit_Elitenanocomputer.get(mMultiplier <= 0 ? 1 : mMultiplier), 400, 600);

        //Nanoprocessor Assembly
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Advanced.get(1L),
                Circuit_Nanoprocessor.get(2L),
                Coi.get(8L),
                Cap.get(8L),
                Ram.get(8L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72L),
                Circuit_Nanocomputer.get(mMultiplier <= 0 ? 1 : mMultiplier), 400, 600);

        //Crystalprocessor Assembly
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Elite.get(1L),
                Circuit_Crystalprocessor.get(2L),
                Coi.get(24L),
                Cap.get(32L),
                Ram.get(24L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72L),
                Circuit_Crystalcomputer.get(mMultiplier <= 0 ? 1 : mMultiplier), 400, 9600);

        //Biowareprocessor Assembly
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Bio_Ultra.get(1L),
                Circuit_Bioprocessor.get(2L),
                Coi.get(48L),
                Cap.get(64L),
                Ram.get(32L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                Circuit_Biowarecomputer.get(mMultiplier <= 0 ? 1 : mMultiplier), 800, 153600);

        //Ultimate Crystalcomputer
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Elite.get(1L),
                Circuit_Crystalcomputer.get(2L),
                Ram.get(4L),
                Circuit_Chip_NOR.get(32L),
                Circuit_Chip_NAND.get(64L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                Circuit_Ultimatecrystalcomputer.get(mMultiplier <= 0 ? 1 : mMultiplier), 400, 9600);

        //Master Quantumcomputer
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Fiberglass_Advanced.get(1L),
                Circuit_Quantumcomputer.get(2L),
                Dio.get(8L),
                Circuit_Chip_NOR.get(4L),
                Ram.get(16L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72l),
                Circuit_Masterquantumcomputer.get(mMultiplier <= 0 ? 1 : mMultiplier), 400, 2400);

        //Workstation
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Plastic_Advanced.get(1L),
                Circuit_Advanced.get(2L),
                Dio.get(4l),
                Ram.get(16l),
                GT_OreDictUnificator.get(OrePrefixes.bolt, Platinum, 16l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72l),
                Circuit_Data.get(mMultiplier <= 0 ? 1 : mMultiplier), 400, 120);

        //Processor Assembly
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Plastic_Advanced.get(1L),
                Circuit_Processor.get(2l),
                Coi.get(4l),
                Cap.get(8l),
                Ram.get(4l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72l),
                Circuit_Advanced.get(mMultiplier <= 0 ? 1 : mMultiplier), 400, 96);

        //Mainframe
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Aluminium, 2l),
                Circuit_Data.get(2l),
                Coi.get(12l),
                Cap.get(16l),
                Ram.get(16l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72l),
                Circuit_Elite.get(mMultiplier <= 0 ? 1 : mMultiplier), 1600, 480);

        //Quantumprocessor Assembly
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Fiberglass_Advanced.get(1l),
                Circuit_Quantumprocessor.get(2l),
                Coi.get(12l),
                Cap.get(16l),
                Ram.get(4l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72l),
                Circuit_Quantumcomputer.get(mMultiplier <= 0 ? 1 : mMultiplier), 400, 2400);

        //Microprocessor
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Plastic_Advanced.get(1l),
                Circuit_Chip_SoC.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.bolt, Copper, 4l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(16l),
                Circuit_Microprocessor.get(mMultiplier <= 0 ? 1 : mMultiplier), 200, 60);

        //Quantumprocessor Mainframe
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Aluminium, 2l),
                Circuit_Quantumcomputer.get(2l),
                Coi.get(24l),
                Cap.get(48l),
                Ram.get(24l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144l),
                Circuit_Quantummainframe.get(mMultiplier <= 0 ? 1 : mMultiplier), 1600, 7680);

        //Good Electronic Circuit
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Phenolic_Good.get(1),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Basic, 2l),
                Dio.get(2l),
                GT_OreDictUnificator.get(OrePrefixes.wireGt01, Copper, 1l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(16l),
                Circuit_Good.get(mMultiplier <= 0 ? 1 : mMultiplier), 300, 30);

        //Good Integrated Circuit
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Phenolic_Good.get(1l),
                Circuit_Basic.get(2l),
                Tra.get(4l),
                GT_OreDictUnificator.get(OrePrefixes.bolt, AnnealedCopper, 4l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(16l),
                Circuit_Integrated_Good.get(mMultiplier <= 0 ? 1 : mMultiplier), 400, 24);

        //Advanced Circuit
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Integrated_Good.get(2l),
                Circuit_Chip_ILC.get(2l),
                Ram.get(2l),
                Tra.get(4l),
                GT_OreDictUnificator.get(OrePrefixes.bolt, AnnealedCopper, 8),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(16l),
                GT_ModHandler.getModItem("IC2", "itemPartCircuitAdv", mMultiplier <= 0 ? 1 : mMultiplier, 0), 600, 32);

        //Electronic Circuit
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Coated_Basic.get(1l),
                Res.get(2l),
                Circuit_Primitive.get(2l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(16l),
                GT_ModHandler.getModItem("IC2", "itemPartCircuit", mMultiplier <= 0 ? 1 : mMultiplier, 0), 1200, 32);

        //Integrated Logic Circuit
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Coated_Basic.get(1l),
                Circuit_Chip_ILC.get(1l),
                Res.get(2l),
                Dio.get(2l),
                GT_OreDictUnificator.get(OrePrefixes.bolt, Tin, 2l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(16l),
                Circuit_Basic.get(mMultiplier <= 0 ? 1 : mMultiplier), 200, 16);

        //Data Stick
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Plastic_Advanced.get(1l),
                Circuit_Chip_CPU.get(2l),
                Circuit_Chip_NAND.get(32l),
                Ram.get(4l),
                GT_OreDictUnificator.get(OrePrefixes.plate, Plastic, 4l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72l),
                Tool_DataStick.get(mMultiplier <= 0 ? 1 : mMultiplier), 1200, 2400);

        //Data Orb
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Advanced.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Advanced, 2l),
                Ram.get(4l),
                Circuit_Chip_NOR.get(32l),
                Ram.get(64l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144l),
                Tool_DataOrb.get(mMultiplier <= 0 ? 1 : mMultiplier), 1200, 2400);

        //Neuro Processing Unit
        GT_Values.RA.addAssemblylineRecipe(
                Circuit_Crystalmainframe.get(1l), 72000, new ItemStack[]{
                        Circuit_Board_Wetware_Extreme.get(1l),
                        Circuit_Chip_Stemcell.get(16l),
                        Circuit_Parts_Reinforced_Glass_Tube.get(16l),
                        GT_OreDictUnificator.get(OrePrefixes.pipeTiny, Polybenzimidazole, 8l),
                        GT_OreDictUnificator.get(OrePrefixes.itemCasing, ElectrumFlux, 4l),
                        GT_OreDictUnificator.get(OrePrefixes.foil, StyreneButadieneRubber, 32l),
                        GT_OreDictUnificator.get(OrePrefixes.bolt, HSSS, 32l)}, new FluidStack[]{
                        GrowthMediumSterilized.getFluid(250l),
                        UUMatter.getFluid(250l),
                        new FluidStack(FluidRegistry.getFluid("ic2coolant"), 1000)},
                Circuit_Chip_NeuroCPU.get(mMultiplier <= 0 ? 1 : mMultiplier), 1200, 80000);

        //Bio Processing Unit
        GT_Values.RA.addAssemblylineRecipe(
                Circuit_Chip_NeuroCPU.get(1l), 144000, new ItemStack[]{
                        Circuit_Board_Bio_Ultra.get(1l),
                        Circuit_Chip_Biocell.get(32l),
                        Circuit_Parts_Reinforced_Glass_Tube.get(16l),
                        GT_OreDictUnificator.get(OrePrefixes.pipeTiny, Polybenzimidazole, 16l),
                        GT_OreDictUnificator.get(OrePrefixes.itemCasing, Tungsten, 16l),
                        GT_OreDictUnificator.get(OrePrefixes.foil, Silicone, 64l),
                        GT_OreDictUnificator.get(OrePrefixes.bolt, TungstenSteel, 32l)}, new FluidStack[]{
                        BioMediumSterilized.getFluid(500l),
                        UUMatter.getFluid(500l),
                        new FluidStack(FluidRegistry.getFluid("ic2coolant"), 2000)},
                Circuit_Chip_BioCPU.get(mMultiplier <= 0 ? 1 : mMultiplier), 2400, 160000);

        if (Loader.isModLoaded("tectech")) {

            // Bioware Supercomputer
            TT_recipeAdder.addResearchableAssemblylineRecipe(
                    Circuit_Biowarecomputer.get(1l),
                    48000, 128, 500000, 8, new ItemStack[]{
                            Circuit_Board_Bio_Ultra.get(2l),
                            Circuit_Biowarecomputer.get(2l),
                            Tra.get(16l),
                            Res.get(16l),
                            Cap.get(16l),
                            Dio.get(48l),
                            Circuit_Chip_NOR.get(32l),
                            Ram.get(64l),
                            GT_OreDictUnificator.get(OrePrefixes.wireFine, NiobiumTitanium, 32l),
                            GT_OreDictUnificator.get(OrePrefixes.foil, Silicone, 16l)}, new FluidStack[]{
                            SolderingAlloy.getMolten(1440l),
                            BioMediumSterilized.getFluid(1440l),
                            new FluidStack(FluidRegistry.getFluid("ic2coolant"), 10000)},
                            Circuit_Biowaresupercomputer.get(mMultiplier <= 0 ? 1 : mMultiplier), 4000, 500000);



            //Bio Mainframe
            TT_recipeAdder.addResearchableAssemblylineRecipe(
                    Circuit_Biowaresupercomputer.get(1l),
                    96000, 256, 1000000, 16, new ItemStack[]{
                            GT_OreDictUnificator.get(OrePrefixes.frameGt, Tritanium, 4L),
                            Circuit_Biowaresupercomputer.get(2l),
                            UV_Coil.get(2l),
                            Dio.get(64l),
                            Res.get(64l),
                            Tra.get(64l),
                            Cap.get(64l),
                            Ram.get(64l),
                            GT_OreDictUnificator.get(OrePrefixes.wireGt01, SuperconductorUHV, 64l),
                            GT_OreDictUnificator.get(OrePrefixes.foil, Silicone, 64l),
                            GT_OreDictUnificator.get(OrePrefixes.foil, Polybenzimidazole, 64l)},
                    new FluidStack[]{SolderingAlloy.getMolten(2880l),
                            BioMediumSterilized.getFluid(2880l),
                            new FluidStack(FluidRegistry.getFluid("ic2coolant"), 20000)},
                    Circuit_Biomainframe.get(mMultiplier <= 0 ? 1 : mMultiplier), 6000, 2000000);

            //Wetware MainFrame
            TT_recipeAdder.addResearchableAssemblylineRecipe(
                    Circuit_Wetwaresupercomputer.get(1l),
                    24000, 64, 50000, 4, new ItemStack[]{
                            GT_OreDictUnificator.get(OrePrefixes.frameGt, Tritanium, 2l),
                            Circuit_Wetwaresupercomputer.get(2l),
                            ZPM_Coil.get(16l),
                            Cap.get(64l),
                            Res.get(64l),
                            Tra.get(64l),
                            Dio.get(64l),
                            Ram.get(64l),
                            GT_OreDictUnificator.get(OrePrefixes.wireGt01, SuperconductorZPM, 64l),
                            GT_OreDictUnificator.get(OrePrefixes.foil, Silicone, 64l)}, new FluidStack[]{
                            SolderingAlloy.getMolten(2880l),
                            new FluidStack(FluidRegistry.getFluid("ic2coolant"), 10000),
                            Radon.getGas(2500l)},
                    Circuit_Wetwaremainframe.get(mMultiplier <= 0 ? 1 : mMultiplier), 2000, 300000);





        if (Loader.isModLoaded("dreamcraft")){
            //Nano Circuit
            TT_recipeAdder.addResearchableAssemblylineRecipe(
                    Circuit_Biomainframe.get(1l),
                    192000, 512, 2000000, 32, new ItemStack[]{
                            GT_OreDictUnificator.get(OrePrefixes.frameGt, Tritanium, 8l),
                            Circuit_Biomainframe.get(2l),
                            Cap.get(64l),
                            Dio.get(64l),
                            Res.get(64l),
                            Tra.get(64l),
                            Ram.get(64l),
                            Circuit_Chip_NPIC.get(64l),
                            GT_OreDictUnificator.get(OrePrefixes.wireGt01, Draconium, 64l),
                            GT_OreDictUnificator.get(OrePrefixes.wireGt02, SuperconductorUHV, 64l),
                            GT_OreDictUnificator.get(OrePrefixes.foil, Silicone, 64l),
                            GT_OreDictUnificator.get(OrePrefixes.foil, Polybenzimidazole, 64l)}, new FluidStack[]{
                            SolderingAlloy.getMolten(3760l),
                            Naquadria.getMolten(4032l),
                            new FluidStack(FluidRegistry.getFluid("ic2coolant"), 20000)},
                    CustomItemList.NanoCircuit.get(mMultiplier <= 0 ? 1 : mMultiplier), 80000,8000000);

            //Piko Circuit
            TT_recipeAdder.addResearchableAssemblylineRecipe(
                    CustomItemList.PicoWafer.get(1l),
                    384000, 1024, 4000000, 64, new ItemStack[]{
                            Circuit_Board_Bio_Ultra.get(1l),
                            CustomItemList.PicoWafer.get(4l),
                            CustomItemList.NanoCircuit.get(2l),
                            Tra.get(64l),
                            Res.get(64l),
                            Cap.get(64l),
                            Dio.get(64l),
                            Circuit_Chip_PPIC.get(64l),
                            GT_OreDictUnificator.get(OrePrefixes.foil, NiobiumTitanium, 16l),
                            GT_OreDictUnificator.get(OrePrefixes.bolt, Osmium, 32l),
                            GT_OreDictUnificator.get(OrePrefixes.bolt, Neutronium, 16l),
                            GT_OreDictUnificator.get(OrePrefixes.wireFine, Lanthanum, 64l)}, new FluidStack[]{
                            SolderingAlloy.getMolten(3760l),
                            UUMatter.getFluid(8000l),
                            Osmium.getMolten(1152l)},
                    CustomItemList.PikoCircuit.get(mMultiplier <= 0 ? 1 : mMultiplier), 100000, 8000000);

            //Quantum Circuit
            TT_recipeAdder.addResearchableAssemblylineRecipe(
                    CustomItemList.PikoCircuit.get(1l),
                    720000, 2048, 8000000, 128, new ItemStack[]{
                            GT_OreDictUnificator.get(OrePrefixes.frameGt, Neutronium, 16l),
                            CustomItemList.PikoCircuit.get(8l),
                            Cap.get(64l),
                            Dio.get(64l),
                            Tra.get(64l),
                            Res.get(64l),
                            Circuit_Chip_QPIC.get(64l),
                            GT_OreDictUnificator.get(OrePrefixes.foil, NiobiumTitanium, 64l),
                            GT_OreDictUnificator.get(OrePrefixes.bolt, Indium, 64l),
                            GT_OreDictUnificator.get(OrePrefixes.wireGt01, Bedrockium, 16l),
                            GT_OreDictUnificator.get(OrePrefixes.wireFine, Lanthanum, 64l)}, new FluidStack[]{
                            SolderingAlloy.getMolten(3760l),
                            UUMatter.getFluid(24000l),
                            Osmium.getMolten(2304l)},
                    CustomItemList.QuantumCircuit.get(mMultiplier <= 0 ? 1 : mMultiplier), 20000, 32000000);

        }



        }

        // Good Circuit Board
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                Empty_Board_Basic.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.foil, Gold, 4l)},
                IronIIIChloride.getFluid(100l),
                Circuit_Board_Phenolic_Good.get(1l), 600, 30);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                Empty_Board_Basic.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.foil, Gold, 4l)},
                SodiumPersulfate.getFluid(200l),
                Circuit_Board_Phenolic_Good.get(1l), 600, 30);

        //Advanced Circuit Board
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Advanced.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.foil, Electrum, 8l)},
                IronIIIChloride.getFluid(500l),
                Circuit_Board_Epoxy_Advanced.get(1l), 1200, 30);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Advanced.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.foil, Electrum, 8l)},
                SodiumPersulfate.getFluid(1000l),
                Circuit_Board_Epoxy_Advanced.get(1l), 1200, 30);

        //More Advanced Circuit Board
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                Empty_Board_Elite.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.foil, AnnealedCopper, 12l)},
                IronIIIChloride.getFluid(1000l),
                Circuit_Board_Fiberglass_Advanced.get(1l), 1200, 30);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                Empty_Board_Elite.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.foil, AnnealedCopper, 12l)},
                SodiumPersulfate.getFluid(2000l),
                Circuit_Board_Fiberglass_Advanced.get(1l), 1800, 30);

        //Elite Circuit Board
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Elite.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.foil, Platinum, 12l)},
                IronIIIChloride.getFluid(2000l),
                Circuit_Board_Multifiberglass_Elite.get(1l), 1200, 30);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Elite.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.foil, Platinum, 12l)},
                SodiumPersulfate.getFluid(4000l),
                Circuit_Board_Multifiberglass_Elite.get(1l), 1200, 30);

        //Extreme Wetware Lifesupport Circuit Board
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Wetware.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.foil, NiobiumTitanium, 32l)},
                IronIIIChloride.getFluid(5000l),
                Circuit_Board_Wetware_Extreme.get(1l), 1200, 30);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Wetware.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.foil, NiobiumTitanium, 32l)},
                SodiumPersulfate.getFluid(10000l),
                Circuit_Board_Wetware_Extreme.get(1l), 1200, 30);

        //Plastic Circuit Board
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Plastic.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.foil, Copper, 6l)},
                IronIIIChloride.getFluid(250l),
                Circuit_Board_Plastic_Advanced.get(1l), 800, 30);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Plastic.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.foil, Copper, 6l)},
                SodiumPersulfate.getFluid(500l),
                Circuit_Board_Plastic_Advanced.get(1l), 800, 30);

        //Ultra Bio Mutated Circuit Board
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Bio.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.foil, Neutronium, 48l)},
                IronIIIChloride.getFluid(7500l),
                Circuit_Board_Bio_Ultra.get(1l), 3200, 30);
        //Ultra Bio Mutated Circuit Board
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Bio.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.foil, Neutronium, 48l)},
                SodiumPersulfate.getFluid(15000l),
                Circuit_Board_Bio_Ultra.get(1l), 3200, 30);



        // BARTWORKS CIRCUITS //
        if (Loader.isModLoaded("bartworks")) {


            // Primitive
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                    GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 2l, 3),
                    GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedgem", 1l, 36),
                    Dio.get(4l),
                    Cap.get(4l),
                    Tra.get(4l),
                    GT_Utility.getIntegratedCircuit(5)},
                    SolderingAlloy.getMolten(36l),
                    GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 4), 600, 30);

            // Basic
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 3),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedgem", 1l, 36),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 4),
                            Dio.get(8l),
                            Cap.get(8l),
                            Tra.get(8l),
                            GT_Utility.getIntegratedCircuit(6)},
                    SolderingAlloy.getMolten(72l),
                    GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 5), 1500, 120);

            // Good
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 3),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedgem", 1l, 36),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 5),
                            Dio.get(12l),
                            Cap.get(12l),
                            Tra.get(12l),
                            GT_Utility.getIntegratedCircuit(6)},
                    SolderingAlloy.getMolten(108l),
                    GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 6), 2250, 480);

            // Advanced
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 3),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedgem", 1l, 36),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 6),
                            Dio.get(16l),
                            Cap.get(16l),
                            Tra.get(16l),
                            GT_Utility.getIntegratedCircuit(6)},
                    SolderingAlloy.getMolten(108l),
                    GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 7), 3000, 1920);

            // Data
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 3),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedgem", 1l, 36),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 7),
                            Dio.get(20l),
                            Cap.get(20l),
                            Tra.get(20l),
                            GT_Utility.getIntegratedCircuit(6)},
                    SolderingAlloy.getMolten(108l),
                    GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 8), 3750, 7680);

            // Elite
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 3),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedgem", 1l, 36),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 8),
                            Dio.get(24l),
                            Cap.get(24l),
                            Tra.get(24l),
                            GT_Utility.getIntegratedCircuit(6)},
                    SolderingAlloy.getMolten(216l),
                    GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 9), 4500, 30720);

            // Master
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 3),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedgem", 1l, 36),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 9),
                            Dio.get(28l),
                            Cap.get(28l),
                            Tra.get(28l),
                            GT_Utility.getIntegratedCircuit(6)},
                    SolderingAlloy.getMolten(252l),
                    GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 10), 5250, 128880);

            // Ultimate
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 3),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedgem", 1l, 36),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 10),
                            Dio.get(32l),
                            Cap.get(32l),
                            Tra.get(32l),
                            GT_Utility.getIntegratedCircuit(6)},
                    SolderingAlloy.getMolten(288l),
                    GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 11), 6000, 491520);

            // Superconductor
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 3),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedgem", 1l, 36),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 11),
                            Dio.get(36l),
                            Cap.get(36l),
                            Tra.get(36l),
                            GT_Utility.getIntegratedCircuit(6)},
                    SolderingAlloy.getMolten(324l),
                    GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 12), 6750, 1966080);

            // Infinite
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 3),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedgem", 1l, 36),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 12),
                            Dio.get(40l),
                            Cap.get(40l),
                            Tra.get(40l),
                            GT_Utility.getIntegratedCircuit(6)},
                    SolderingAlloy.getMolten(324l),
                    GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 13), 7500, 7864320);

            // Bio
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 3),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedgem", 1l, 36),
                            GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 13),
                            Dio.get(44l),
                            Cap.get(44l),
                            Tra.get(44l),
                            GT_Utility.getIntegratedCircuit(6)},
                    SolderingAlloy.getMolten(396l),
                    GT_ModHandler.getModItem("bartworks", "gt.bwMetaGeneratedItem0", 1l, 14), 8250, 7864320);



        }







        }
}
