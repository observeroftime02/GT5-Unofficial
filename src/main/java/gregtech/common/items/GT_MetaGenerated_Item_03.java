package gregtech.common.items;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.SubTag;
import gregtech.api.items.GT_MetaGenerated_Item_X32;

public class GT_MetaGenerated_Item_03 extends GT_MetaGenerated_Item_X32 {
    public static GT_MetaGenerated_Item_03 INSTANCE;

    public GT_MetaGenerated_Item_03() {
        super("metaitem.03");
        INSTANCE = this;
        
         /* circuit boards tier 1-7:
         * coated circuit board / wood plate + resin
         * Plastic Circuit Board / Plastic + Copper Foil + Sulfuric Acid
         * phenolic circuit board /carton+glue+chemical bath
         * epoxy circuit board /epoxy plate + copper foil + sulfuric acid
         * fiberglass circuit board (simple + multilayer) / glass + plastic + electrum foil + sulfurci acid
         * wetware lifesupport board / fiberglass CB + teflon + */
        //ItemList.Circuit_Board_Coated.set(addItem(tLastID = 1, "Coated Circuit Board", "A basic Board"));
        //ItemList.Circuit_Board_Phenolic.set(addItem(tLastID = 2, "Phenolic Circuit Board", "A good Board"));
        //ItemList.Circuit_Board_Epoxy.set(addItem(tLastID = 3, "Epoxy Circuit Board", "An advanced Board"));
        //ItemList.Circuit_Board_Fiberglass.set(addItem(tLastID = 4, "Fiberglass Circuit Board", "An advanced Board"));
        //ItemList.Circuit_Board_Multifiberglass.set(addItem(tLastID = 5, "Multilayer Fiberglass Circuit Board", "A elite Board"));
        ItemList.Circuit_Board_Wetware.set(addItem(6, "Wetware Lifesupport Circuit Board", "The Board that keeps life"));
        ItemList.Circuit_Board_Plastic.set(addItem(7, "Plastic Circuit Board", "A Good Board"));
        
         /* electronic components:
         * vacuum tube (glass tube + red alloy cables)
         * basic electronic circuits normal+smd
         * coils
         * diodes normal+smd
         * transistors normal+smd
         * capacitors normal+smd
         * Glass Fibers */
        //ItemList.Circuit_Parts_Resistor.set(addItem(10, "Resistor", "Basic Electronic Component")); //wiring mv
        ItemList.Circuit_Parts_ResistorSMD.set(addItem(11, "SMD Resistor", "Electronic Component"));
        ItemList.Circuit_Parts_Glass_Tube.set(addItem(12, "Glass Tube", ""));
        //ItemList.Circuit_Parts_Vacuum_Tube.set(addItem(13, "Vacuum Tube", "Basic Electronic Component")); //Circuit_Primitive
        ItemList.Circuit_Parts_Coil.set(addItem(14, "Small Coil", "Basic Electronic Component"));
        //ItemList.Circuit_Parts_Diode.set(addItem(15, "Diode", "Basic Electronic Component"));
        ItemList.Circuit_Parts_DiodeSMD.set(addItem(16, "SMD Diode", "Electronic Component"));
        //ItemList.Circuit_Parts_Transistor.set(addItem(17, "Transistor", "Basic Electronic Component")); //wiring hv
        ItemList.Circuit_Parts_TransistorSMD.set(addItem(18, "SMD Transistor", "Electronic Component"));
        //ItemList.Circuit_Parts_Capacitor.set(addItem(19, "Capacitor", "Electronic Component")); //wiring ev
        ItemList.Circuit_Parts_CapacitorSMD.set(addItem(20, "SMD Capacitor", "Electronic Component"));
        ItemList.Circuit_Parts_GlassFiber.set(addItem(21, "Glass Fiber", Materials.BorosilicateGlass.mChemicalFormula));
        ItemList.Circuit_Parts_PetriDish.set(addItem(22, "Petri Dish", "For cultivating cells"));
        
        
         /* ICs
         * Lenses made from perfect crystals first instead of plates
         * Monocrystalline silicon ingot (normal+glowstone+naquadah) EBF, normal silicon no EBF need anymore
         * wafer(normal+glowstone+naquadah) cut mono silicon ingot in cutting machine
         * 
         * Integrated Logic Circuit(8bit DIP)
         * RAM
         * NAND Memory
         * NOR Memory
         * CPU (4 sizes)
         * SoCs(2 sizes, high tier cheap low tech component)
         * Power IC/High Power IC 
         * 
         * nanotube interconnected circuit (H-IC + nanotubes)
         * 
         * quantum chips */
        ItemList.Circuit_Silicon_Ingot.set(addItem(30, "Monocrystalline Silicon Boule", "Raw Circuit"));
        ItemList.Circuit_Silicon_Ingot2.set(addItem(31, "Glowstone doped Monocrystalline Silicon Boule", "Raw Circuit"));
        ItemList.Circuit_Silicon_Ingot3.set(addItem(32, "Naquadah doped Monocrystalline Silicon Boule", "Raw Circuit"));

        ItemList.Circuit_Silicon_Wafer.set(addItem(33, "Wafer", "Raw Circuit"));
        ItemList.Circuit_Silicon_Wafer2.set(addItem(34, "Glowstone doped Wafer", "Raw Circuit"));
        ItemList.Circuit_Silicon_Wafer3.set(addItem(35, "Naquadah doped Wafer", "Raw Circuit"));
             
        ItemList.Circuit_Wafer_ILC.set(addItem(36, "Integrated Logic Circuit (Wafer)", "Raw Circuit"));
        ItemList.Circuit_Chip_ILC.set(addItem(37, "Integrated Logic Circuit", "Integrated Circuit"));
        
        ItemList.Circuit_Wafer_Ram.set(addItem(38, "Random Access Memory Chip (Wafer)", "Raw Circuit"));
        ItemList.Circuit_Chip_Ram.set(addItem(39, "Random Access Memory Chip", "Integrated Circuit"));
        
        ItemList.Circuit_Wafer_NAND.set(addItem(40, "NAND Memory Chip (Wafer)", "Raw Circuit"));
        ItemList.Circuit_Chip_NAND.set(addItem(41, "NAND Memory Chip", "Integrated Circuit"));

        ItemList.Circuit_Wafer_NOR.set(addItem(42, "NOR Memory Chip (Wafer)", "Raw Circuit"));
        ItemList.Circuit_Chip_NOR.set(addItem(43, "NOR Memory Chip", "Integrated Circuit"));

        ItemList.Circuit_Wafer_CPU.set(addItem(44, "Central Processing Unit (Wafer)", "Raw Circuit"));
        ItemList.Circuit_Chip_CPU.set(addItem(45, "Central Processing Unit", "Integrated Circuit"));

        ItemList.Circuit_Wafer_SoC.set(addItem(46, "SoC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_SoC.set(addItem(47, "SoC", "System on a Chip"));

        ItemList.Circuit_Wafer_SoC2.set(addItem(48, "ASoC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_SoC2.set(addItem(49, "ASoC", "Advanced System on a Chip"));

        ItemList.Circuit_Wafer_PIC.set(addItem(50, "PIC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_PIC.set(addItem(51, "Power IC", "Power Circuit"));

        ItemList.Circuit_Wafer_HPIC.set(addItem(52, "HPIC Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_HPIC.set(addItem(53, "High Power IC", "High Power Circuit"));

        ItemList.Circuit_Wafer_NanoCPU.set(addItem(54, "NanoCPU Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_NanoCPU.set(addItem(55, "Nanocomponent Central Processing Unit", "Power Circuit"));

        ItemList.Circuit_Wafer_QuantumCPU.set(addItem(56, "QBit Wafer", "Raw Circuit"));
        ItemList.Circuit_Chip_QuantumCPU.set(addItem(57, "QBit Processing Unit", "Quantum CPU"));
        
        
        /* Engraved Crystal Chip
        *  Engraved Lapotron Chip
        *  Crystal CPU
        *  SoCrystal
        *  stem cells (disassemble eggs) */
        ItemList.Circuit_Parts_RawCrystalChip.set(addItem(69, "Raw Crystal Chip", "Raw Crystal Processor"));
        ItemList.Circuit_Chip_CrystalCPU.set(addItem(70, "Crystal Processing Unit", "Crystal CPU")); //Crystal chip elite part
        ItemList.Circuit_Chip_CrystalSoC.set(addItem(71, "Crystal SoC", "Crystal System on a Chip"));
        ItemList.Circuit_Chip_NeuroCPU.set(addItem(72, "Neuro Processing Unit", "Neuro CPU"));
        ItemList.Circuit_Chip_Stemcell.set(addItem(73, "Stemcells", "Raw Intiligence (Disassembled Eggs)"));

        //Vacuum Tube				Item01
        //Basic Circuit				IC2
        //Good Circuit				Item01
        
        //Integrated Logic Circuit  Item01 
        ItemList.Circuit_Integrated_Good.set(addItem(79, "Good Integrated Circuit", "A Good Circuit",  OrePrefixes.circuit.get(Materials.Good), SubTag.NO_UNIFICATION));
        //Good Integrated Circuit   Item01
        //Advanced Circuit			IC2
        
        ItemList.Circuit_Microprocessor.set(addItem(78, "Microprocessor", "A Basic Circuit",  OrePrefixes.circuit.get(Materials.Basic), SubTag.NO_UNIFICATION));
        ItemList.Circuit_Processor.set(addItem(80, "Integrated Processor", "A Good Circuit",  OrePrefixes.circuit.get(Materials.Good), SubTag.NO_UNIFICATION));
        //ItemList.Circuit_Computer.set(addItem(81, "Processor Assembly", "Advanced Circuit",  OrePrefixes.circuit.get(Materials.Advanced), SubTag.NO_UNIFICATION));
        //Workstation				Item01 Datacircuit
        //Mainframe					Item01 DataProcessor
        
        ItemList.Circuit_Nanoprocessor.set(addItem(82, "Nanoprocessor", "An Advanced Circuit",  OrePrefixes.circuit.get(Materials.Advanced), SubTag.NO_UNIFICATION));
        ItemList.Circuit_Nanocomputer.set(addItem(83, "Nanoprocessor Assembly", "An Extreme Circuit",  OrePrefixes.circuit.get(Materials.Data), SubTag.NO_UNIFICATION));
        ItemList.Circuit_Elitenanocomputer.set(addItem(84, "Elite Nanocomputer", "An Elite Circuit",  OrePrefixes.circuit.get(Materials.Elite), SubTag.NO_UNIFICATION));
        //Nanoprocessor Mainframe  	Item01 Energy Flow Circuit
        
        ItemList.Circuit_Quantumprocessor.set(addItem(85, "Quantumprocessor", "An Extreme Circuit",  OrePrefixes.circuit.get(Materials.Data), SubTag.NO_UNIFICATION));
        ItemList.Circuit_Quantumcomputer.set(addItem(86, "Quantumprocessor Assembly", "An Elite Circuit",  OrePrefixes.circuit.get(Materials.Elite), SubTag.NO_UNIFICATION));
        ItemList.Circuit_Masterquantumcomputer.set(addItem(87, "Master Quantumcomputer", "A Master Circuit",  OrePrefixes.circuit.get(Materials.Master), SubTag.NO_UNIFICATION));
        ItemList.Circuit_Quantummainframe.set(addItem(88, "Quantumprocessor Mainframe", "An Ultimate Circuit",  OrePrefixes.circuit.get(Materials.Ultimate), SubTag.NO_UNIFICATION));
        
        ItemList.Circuit_Crystalprocessor.set(addItem(89, "Crystalprocessor", "An Elite Circuit",  OrePrefixes.circuit.get(Materials.Elite), SubTag.NO_UNIFICATION));
        ItemList.Circuit_Crystalcomputer.set(addItem(96, "Crystalprocessor Assembly", "A Master Circuit",  OrePrefixes.circuit.get(Materials.Master), SubTag.NO_UNIFICATION));
        ItemList.Circuit_Ultimatecrystalcomputer.set(addItem(90, "Ultimate Crystalcomputer", "An Ultimate Circuit",  OrePrefixes.circuit.get(Materials.Ultimate), SubTag.NO_UNIFICATION));
        ItemList.Circuit_Crystalmainframe.set(addItem(91, "Crystalprocessor Mainframe", "A Super Circuit",  OrePrefixes.circuit.get(Materials.Superconductor), SubTag.NO_UNIFICATION));

        ItemList.Circuit_Neuroprocessor.set(addItem(92, "Wetwareprocessor", "A Master Circuit",  OrePrefixes.circuit.get(Materials.Master), SubTag.NO_UNIFICATION));
        ItemList.Circuit_Wetwarecomputer.set(addItem(93, "Wetwareprocessor Assembly", "An Ultimate Circuit",  OrePrefixes.circuit.get(Materials.Ultimate), SubTag.NO_UNIFICATION));
        ItemList.Circuit_Wetwaresupercomputer.set(addItem(94, "Wetware Supercomputer", "A Super Circuit",  OrePrefixes.circuit.get(Materials.Superconductor), SubTag.NO_UNIFICATION));
        ItemList.Circuit_Wetwaremainframe.set(addItem(95, "Wetware Mainframe", "An Infinite Circuit",  OrePrefixes.circuit.get(Materials.Infinite), SubTag.NO_UNIFICATION));
        ItemList.Circuit_Ultimate.set(ItemList.Circuit_Ultimatecrystalcomputer.get(1));//maybe should be removed
    }

    @Override
    public boolean doesShowInCreative(OrePrefixes aPrefix, Materials aMaterial, boolean aDoShowAllItems) {
        return aDoShowAllItems;
    }
}
