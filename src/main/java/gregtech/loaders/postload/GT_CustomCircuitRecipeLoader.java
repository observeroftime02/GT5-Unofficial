package gregtech.loaders.postload;

import com.github.bartimaeusnek.bartworks.system.material.CircuitGeneration.BW_Meta_Items;
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

import static gregtech.api.enums.Materials.*;
import static gregtech.api.enums.ItemList.*;

public class GT_CustomCircuitRecipeLoader implements Runnable {
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
                ItemList.Circuit_Board_Wetware_Extreme.get(1L),
                ItemList.Circuit_Wetwarecomputer.get(2L),
                Dio.get(32),
                ItemList.Circuit_Chip_NOR.get(16L),
                ItemList.Circuit_Chip_Ram.get(64L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                ItemList.Circuit_Wetwaresupercomputer.get(1L), 600, 38400);

        // Bioprocessor
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                ItemList.Circuit_Chip_BioCPU.get(1L),
                ItemList.Circuit_Chip_CrystalSoC2.get(1L),
                ItemList.Circuit_Chip_NanoCPU.get(2L),
                ItemList.Circuit_Parts_CapacitorSMD.get(48L),
                ItemList.Circuit_Parts_TransistorSMD.get(48L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                ItemList.Circuit_Bioprocessor.get(1L), 600, 153600);

        // Wetware Processor
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                ItemList.Circuit_Chip_NeuroCPU.get(1L),
                ItemList.Circuit_Chip_CrystalSoC.get(1L),
                ItemList.Circuit_Chip_NanoCPU.get(1L),
                ItemList.Circuit_Parts_CapacitorSMD.get(32L),
                ItemList.Circuit_Parts_TransistorSMD.get(32L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                ItemList.Circuit_Neuroprocessor.get(1L), 400, 38400);

        // Wetware Processor Assembly
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Wetware_Extreme.get(1L),
                Circuit_Neuroprocessor.get(2L),
                Circuit_Parts_Coil.get(32L),
                Cap.get(48),
                Circuit_Chip_Ram.get(24),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                Circuit_Wetwarecomputer.get(1L), 600, 38400);

        // Crystalprocessor
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Elite.get(1L),
                Circuit_Chip_CrystalSoC.get(1L),
                Circuit_Chip_NanoCPU.get(2L),
                Cap.get(24L),
                Tra.get(24L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72L),
                Circuit_Crystalprocessor.get(1L), 200, 9600);

        // Nanoprocessor
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Advanced.get(1L),
                Circuit_Chip_SoC.get(1L),
                GT_OreDictUnificator.get(OrePrefixes.bolt, Platinum, 8L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72L),
                Circuit_Nanoprocessor.get(1L), 200, 600);

        //Quantumprocessor
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Fiberglass_Advanced.get(1L),
                Circuit_Chip_SoC.get(1L),
                GT_OreDictUnificator.get(OrePrefixes.bolt, NiobiumTitanium, 8L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72L),
                Circuit_Quantumprocessor.get(1L), 200, 2400);

        // Integrated procesor
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Plastic.get(1L),
                Circuit_Chip_SoC.get(1L),
                GT_OreDictUnificator.get(OrePrefixes.bolt, AnnealedCopper, 8L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72L),
                Circuit_Processor.get(1L), 200, 60);

        //Nanoprocessor Mainframe
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Aluminium, 2L),
                Circuit_Elitenanocomputer.get(2L),
                Coi.get(16L),
                Cap.get(32L),
                Ram.get(16L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                ItemList.Circuit_Master.get(1L), 1600, 1920);

        //Crystallprocessor Mainframe
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Aluminium, 2L),
                Circuit_Ultimatecrystalcomputer.get(2L),
                Coi.get(32L),
                Cap.get(64L),
                Ram.get(32L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                Circuit_Crystalmainframe.get(1L), 1600, 30720);

        //Elite Nanocomputer
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Advanced.get(1L),
                Circuit_Nanocomputer.get(2L),
                Dio.get(8L),
                Circuit_Chip_NOR.get(4L),
                Ram.get(16L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                Circuit_Elitenanocomputer.get(1L), 400, 600);

        //Nanoprocessor Assembly
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Advanced.get(1L),
                Circuit_Nanoprocessor.get(2L),
                Coi.get(8L),
                Cap.get(8L),
                Ram.get(8L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72L),
                Circuit_Nanocomputer.get(1L), 400, 600);

        //Crystalprocessor Assembly
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Elite.get(1L),
                Circuit_Crystalprocessor.get(2L),
                Coi.get(24L),
                Cap.get(32L),
                Ram.get(24L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72L),
                Circuit_Crystalcomputer.get(1L), 400, 9600);

        //Biowareprocessor Assembly
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Bio_Ultra.get(1L),
                Circuit_Bioprocessor.get(2L),
                Coi.get(48L),
                Cap.get(64L),
                Ram.get(32L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                Circuit_Biowarecomputer.get(1L), 800, 153600);

        //Ultimate Crystalcomputer
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Elite.get(1L),
                Circuit_Crystalcomputer.get(2L),
                Ram.get(4L),
                Circuit_Chip_NOR.get(32L),
                Circuit_Chip_NAND.get(64L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144L),
                Circuit_Ultimatecrystalcomputer.get(1L), 400, 9600);

        //Master Quantumcomputer
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Fiberglass_Advanced.get(1L),
                Circuit_Quantumcomputer.get(2L),
                Dio.get(8L),
                Circuit_Chip_NOR.get(4L),
                Ram.get(16L),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72l),
                Circuit_Masterquantumcomputer.get(1L), 400, 2400);

        //Workstation
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Plastic_Advanced.get(1L),
                Circuit_Advanced.get(2L),
                Dio.get(4l),
                Ram.get(16l),
                GT_OreDictUnificator.get(OrePrefixes.bolt, Platinum, 16l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72l),
                Circuit_Data.get(1L), 400, 120);

        //Processor Assembly
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Plastic_Advanced.get(1L),
                Circuit_Processor.get(2l),
                Coi.get(4l),
                Cap.get(8l),
                Ram.get(4l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72l),
                Circuit_Advanced.get(1l), 400, 96);

        //Mainframe
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Aluminium, 2l),
                Circuit_Data.get(2l),
                Coi.get(12l),
                Cap.get(16l),
                Ram.get(16l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72l),
                Circuit_Elite.get(1l), 1600, 480);

        //Quantumprocessor Assembly
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Fiberglass_Advanced.get(1l),
                Circuit_Quantumprocessor.get(2l),
                Coi.get(12l),
                Cap.get(16l),
                Ram.get(4l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72l),
                Circuit_Quantumcomputer.get(1L), 400, 2400);

        //Microprocessor
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Plastic_Advanced.get(1l),
                Circuit_Chip_SoC.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.bolt, Copper, 4l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(16l),
                Circuit_Microprocessor.get(1l), 200, 60);

        //Quantumprocessor Mainframe
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Aluminium, 2l),
                Circuit_Quantumcomputer.get(2l),
                Coi.get(24l),
                Cap.get(48l),
                Ram.get(24l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144l),
                Circuit_Quantummainframe.get(1l), 1600, 7680);

        //Good Electronic Circuit
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Phenolic_Good.get(1),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Basic, 2l),
                Dio.get(2l),
                GT_OreDictUnificator.get(OrePrefixes.wireGt01, Copper, 1l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(16l),
                Circuit_Good.get(1l), 300, 30);

        //Good Integrated Circuit
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Phenolic_Good.get(1l),
                Circuit_Basic.get(2l),
                Tra.get(4l),
                GT_OreDictUnificator.get(OrePrefixes.bolt, AnnealedCopper, 4l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(16l),
                Circuit_Integrated_Good.get(1l), 400, 24);

        //Advanced Circuit
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Integrated_Good.get(2l),
                Circuit_Chip_ILC.get(2l),
                Ram.get(2l),
                Tra.get(4l),
                GT_OreDictUnificator.get(OrePrefixes.bolt, AnnealedCopper, 8),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(16l),
                GT_ModHandler.getModItem("IC2", "itemPartCircuitAdv", 1L, 0), 600, 32);

        //Electronic Circuit
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Coated_Basic.get(1l),
                Res.get(2l),
                Circuit_Primitive.get(2l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(16l),
                GT_ModHandler.getModItem("IC2", "itemPartCircuit", 1L, 0), 1200, 32);

        //Integrated Logic Circuit
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Coated_Basic.get(1l),
                Circuit_Chip_ILC.get(1l),
                Res.get(2l),
                Dio.get(2l),
                GT_OreDictUnificator.get(OrePrefixes.bolt, Tin, 2l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(16l),
                Circuit_Basic.get(1l), 200, 16);

        //Data Stick
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Plastic_Advanced.get(1l),
                Circuit_Chip_CPU.get(2l),
                Circuit_Chip_NAND.get(32l),
                Ram.get(4l),
                GT_OreDictUnificator.get(OrePrefixes.plate, Plastic, 4l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(72l),
                Tool_DataStick.get(1l), 1200, 2400);

        //Data Orb
        GT_Values.RA.addCircuitAssemblerRecipe(new ItemStack[]{
                Circuit_Board_Advanced.get(1l),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Advanced, 2l),
                Ram.get(4l),
                Circuit_Chip_NOR.get(32l),
                Ram.get(64l),
                GT_Utility.getIntegratedCircuit(20)},
                SolderingAlloy.getMolten(144l),
                Tool_DataOrb.get(1l), 1200, 2400);

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
