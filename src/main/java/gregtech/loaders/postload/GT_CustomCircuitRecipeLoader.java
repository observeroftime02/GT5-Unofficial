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



        }







        }
}
