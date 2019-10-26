package gregtech.common.items;

import gregtech.api.GregTech_API;
import gregtech.api.enums.*;
import gregtech.api.items.GT_MetaGenerated_Item_X32;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.common.covers.GT_Cover_SolarPanel;
import net.minecraft.item.Item;

public class GT_MetaGenerated_Item_03
        extends GT_MetaGenerated_Item_X32 {
    public static GT_MetaGenerated_Item_03 INSTANCE;

    public GT_MetaGenerated_Item_03() {
        super("metaitem.03", new OrePrefixes[]{OrePrefixes.crateGtDust, OrePrefixes.crateGtIngot, OrePrefixes.crateGtGem, OrePrefixes.crateGtPlate});
        INSTANCE = this;
        int tLastID = 0;
        Object[] o = new Object[0];

        /**
         * circuit boards tier 1-7:
         * coated circuit board / wood plate + resin
         * Plastic Circuit Board / Plastic + Copper Foil + Sulfuric Acid
         * phenolic circuit board /carton+glue+chemical bath
         * epoxy circuit board /epoxy plate + copper foil + sulfuric acid
         * fiberglass circuit board (simple + multilayer) / glass + plastic + electrum foil + sulfurci acid
         * wetware lifesupport board / fiberglass CB + teflon +
         */
//        ItemList.Circuit_Board_Coated.set(addItem(tLastID = 1, "Coated Circuit Board", "A basic Board", o));
//        ItemList.Circuit_Board_Phenolic.set(addItem(tLastID = 2, "Phenolic Circuit Board", "A good Board", o));
//        ItemList.Circuit_Board_Epoxy.set(addItem(tLastID = 3, "Epoxy Circuit Board", "An advanced Board", o));
//        ItemList.Circuit_Board_Fiberglass.set(addItem(tLastID = 4, "Fiberglass Circuit Board", "An advanced Board", o));
//        ItemList.Circuit_Board_Multifiberglass.set(addItem(tLastID = 5, "Multilayer Fiberglass Circuit Board", "A elite Board", o));
        ItemList.Circuit_Board_Wetware.set(addItem(tLastID = 6, "Wetware Lifesupport Circuit Board", "The Board that keeps life", o));
        ItemList.Circuit_Board_Plastic.set(addItem(tLastID = 7, "Plastic Circuit Board", "A Good Board", o));
        ItemList.Circuit_Board_Bio.set(addItem(tLastID = 8, "Bio Circuit Board", "Bio genetic mutated Board", o));

        /**
         * electronic components:
         * vacuum tube (glass tube + red alloy cables)
         * basic electronic circuits normal+smd
         * coils
         * diodes normal+smd
         * transistors normal+smd
         * capacitors normal+smd
         * Glass Fibers
         */
//        ItemList.Circuit_Parts_Resistor.set(addItem(tLastID = 10, "Resistor", "Basic Electronic Component", o)); //wiring mv
        ItemList.Circuit_Parts_ResistorSMD.set(addItem(tLastID = 11, "SMD Resistor", "Electronic Component", o));
        ItemList.Circuit_Parts_Glass_Tube.set(addItem(tLastID = 12, "Glass Tube", "", o));
//        ItemList.Circuit_Parts_Vacuum_Tube.set(addItem(tLastID = 13, "Vacuum Tube", "Basic Electronic Component", o)); //Circuit_Primitive
        ItemList.Circuit_Parts_Coil.set(addItem(tLastID = 14, "Small Coil", "Basic Electronic Component", o));
//        ItemList.Circuit_Parts_Diode.set(addItem(tLastID = 15, "Diode", "Basic Electronic Component", o));
        ItemList.Circuit_Parts_DiodeSMD.set(addItem(tLastID = 16, "SMD Diode", "Electronic Component", o));
//        ItemList.Circuit_Parts_Transistor.set(addItem(tLastID = 17, "Transistor", "Basic Electronic Component", o)); //wiring hv
        ItemList.Circuit_Parts_TransistorSMD.set(addItem(tLastID = 18, "SMD Transistor", "Electronic Component", o));
//        ItemList.Circuit_Parts_Capacitor.set(addItem(tLastID = 19, "Capacitor", "Electronic Component", o)); //wiring ev
        ItemList.Circuit_Parts_CapacitorSMD.set(addItem(tLastID = 20, "SMD Capacitor", "Electronic Component", o));
        ItemList.Circuit_Parts_GlassFiber.set(addItem(tLastID = 21, "Glass Fiber", Materials.BorosilicateGlass.mChemicalFormula, o));
        ItemList.Circuit_Parts_PetriDish.set(addItem(tLastID = 22, "Petri Dish", "For cultivating cells", o));
        ItemList.Circuit_Parts_Reinforced_Glass_Tube.set(addItem(tLastID = 23, "Reinforced Glass Tube", "", o));
//

        /**
         * ICs
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
         * Power IC/High Power IC/Ultra High power
         *
         * nanotube interconnected circuit (H-IC + nanotubes)
         *
         * quantum chips
         */
        ItemList.Circuit_Silicon_Ingot.set(addItem(tLastID = 30, "Monocrystalline Silicon Boule", "Raw Circuit", o));
        ItemList.Circuit_Silicon_Ingot2.set(addItem(tLastID = 31, "Glowstone doped Monocrystalline Silicon Boule", "Raw Circuit", o));
        ItemList.Circuit_Silicon_Ingot3.set(addItem(tLastID = 32, "Naquadah doped Monocrystalline Silicon Boule", "Raw Circuit", o));
        ItemList.Circuit_Silicon_Ingot4.set(addItem(tLastID = 150, "Europium doped Monocrystalline Silicon Boule", "Raw Circuit", o));
        ItemList.Circuit_Silicon_Ingot5.set(addItem(tLastID = 152, "Americium doped Monocrystalline Silicon Boule", "Raw Circuit", o));

        ItemList.Circuit_Silicon_Wafer.set(addItem(tLastID = 33, "Wafer", "Raw Circuit", o));
        ItemList.Circuit_Silicon_Wafer2.set(addItem(tLastID = 34, "Glowstone doped Wafer", "Raw Circuit", o));
        ItemList.Circuit_Silicon_Wafer3.set(addItem(tLastID = 35, "Naquadah doped Wafer", "Raw Circuit", o));
        ItemList.Circuit_Silicon_Wafer4.set(addItem(tLastID = 151, "Europium doped Wafer", "Raw Circuit", o));
        ItemList.Circuit_Silicon_Wafer5.set(addItem(tLastID = 153, "Americium doped Wafer", "Raw Circuit", o));

        ItemList.Circuit_Wafer_ILC.set(addItem(tLastID = 36, "Integrated Logic Circuit (Wafer)", "Raw Circuit", o));
        ItemList.Circuit_Chip_ILC.set(addItem(tLastID = 37, "Integrated Logic Circuit", "Integrated Circuit", o));

        ItemList.Circuit_Wafer_Ram.set(addItem(tLastID = 38, "Random Access Memory Chip (Wafer)", "Raw Circuit", o));
        ItemList.Circuit_Chip_Ram.set(addItem(tLastID = 39, "Random Access Memory Chip", "Integrated Circuit", o));

        ItemList.Circuit_Wafer_NAND.set(addItem(tLastID = 40, "NAND Memory Chip (Wafer)", "Raw Circuit", o));
        ItemList.Circuit_Chip_NAND.set(addItem(tLastID = 41, "NAND Memory Chip", "Integrated Circuit", o));

        ItemList.Circuit_Wafer_NOR.set(addItem(tLastID = 42, "NOR Memory Chip (Wafer)", "Raw Circuit", o));
        ItemList.Circuit_Chip_NOR.set(addItem(tLastID = 43, "NOR Memory Chip", "Integrated Circuit", o));

        ItemList.Circuit_Wafer_CPU.set(addItem(tLastID = 44, "Central Processing Unit (Wafer)", "Raw Circuit", o));
        ItemList.Circuit_Chip_CPU.set(addItem(tLastID = 45, "Central Processing Unit", "Integrated Circuit", o));

        ItemList.Circuit_Wafer_SoC.set(addItem(tLastID = 46, "SoC Wafer", "Raw Circuit", o));
        ItemList.Circuit_Chip_SoC.set(addItem(tLastID = 47, "SoC", "System on a Chip", o));

        ItemList.Circuit_Wafer_SoC2.set(addItem(tLastID = 48, "ASoC Wafer", "Raw Circuit", o));
        ItemList.Circuit_Chip_SoC2.set(addItem(tLastID = 49, "ASoC", "Advanced System on a Chip", o));

        ItemList.Circuit_Wafer_PIC.set(addItem(tLastID = 50, "PIC Wafer", "Raw Circuit", o));
        ItemList.Circuit_Chip_PIC.set(addItem(tLastID = 51, "Power IC", "Power Circuit", o));

        ItemList.Circuit_Wafer_HPIC.set(addItem(tLastID = 52, "HPIC Wafer", "Raw Circuit", o));
        ItemList.Circuit_Chip_HPIC.set(addItem(tLastID = 53, "High Power IC", "High Power Circuit", o));

        ItemList.Circuit_Wafer_NanoCPU.set(addItem(tLastID = 54, "NanoCPU Wafer", "Raw Circuit", o));
        ItemList.Circuit_Chip_NanoCPU.set(addItem(tLastID = 55, "Nanocomponent Central Processing Unit", "Power Circuit", o));

        ItemList.Circuit_Wafer_QuantumCPU.set(addItem(tLastID = 56, "QBit Wafer", "Raw Circuit", o));
        ItemList.Circuit_Chip_QuantumCPU.set(addItem(tLastID = 57, "QBit Processing Unit", "Quantum CPU", o));

        ItemList.Circuit_Wafer_UHPIC.set(addItem(tLastID = 58, "UHPIC Wafer", "Raw Circuit", o));
        ItemList.Circuit_Chip_UHPIC.set(addItem(tLastID = 59, "Ultra High Power IC", "Ultra High Power Circuit", o));

        ItemList.Circuit_Wafer_Simple_SoC.set(addItem(tLastID = 60, "Simple SoC Wafer", "Raw Primitive Circuit", o));
        ItemList.Circuit_Chip_Simple_SoC.set(addItem(tLastID = 61, "Simple SoC", "Simple System on a Chip", o));

        ItemList.Circuit_Wafer_ULPIC.set(addItem(tLastID = 62, "ULPIC Wafer", "Raw Circuit", o));
        ItemList.Circuit_Chip_ULPIC.set(addItem(tLastID = 63, "Ultra Low Power IC", "Ultra Low Power Circuit", o));

        ItemList.Circuit_Wafer_LPIC.set(addItem(tLastID = 64, "LPIC Wafer", "Raw Circuit", o));
        ItemList.Circuit_Chip_LPIC.set(addItem(tLastID = 65, "Low Power IC", "Low Power Circuit", o));

        ItemList.Circuit_Wafer_NPIC.set(addItem(tLastID = 160, "NPIC Wafer", "Raw Circuit", o));
        ItemList.Circuit_Chip_NPIC.set(addItem(tLastID = 161, "Nano Power IC", "Nano Power Circuit", o));

        ItemList.Circuit_Wafer_PPIC.set(addItem(tLastID = 162, "PPIC Wafer", "Raw Circuit", o));
        ItemList.Circuit_Chip_PPIC.set(addItem(tLastID = 163, "Piko Power IC", "Piko Power Circuit", o));

        ItemList.Circuit_Wafer_QPIC.set(addItem(tLastID = 164, "QPIC Wafer", "Raw Circuit", o));
        ItemList.Circuit_Chip_QPIC.set(addItem(tLastID = 165, "Quantum Power IC", "Quantum Power Circuit", o));
        /**
         * Engraved Crystal Chip
         * Engraved Lapotron Chip
         * Crystal CPU
         * SoCrystal
         * stem cells (disassemble eggs)
         */
        ItemList.Circuit_Chip_CrystalSoC2.set(addItem(tLastID = 68, "Raw Advanced Crystal Chip", "Raw Advanced Crystal Processor", o));
        ItemList.Circuit_Parts_RawCrystalChip.set(addItem(tLastID = 69, "Raw Crystal Chip", "Raw Crystal Processor", o));
        ItemList.Circuit_Chip_CrystalCPU.set(addItem(tLastID = 70, "Crystal Processing Unit", "Crystal CPU", o)); //Crystal chip elite part
        ItemList.Circuit_Chip_CrystalSoC.set(addItem(tLastID = 71, "Crystal SoC", "Crystal System on a Chip", o));
        ItemList.Circuit_Chip_NeuroCPU.set(addItem(tLastID = 72, "Neuro Processing Unit", "Neuro CPU", o));
        ItemList.Circuit_Chip_Stemcell.set(addItem(tLastID = 73, "Stemcells", "Raw inteligence", o));
        ItemList.Circuit_Parts_RawCrystalParts.set(addItem(tLastID = 74, "Raw Crystal Chip Parts", "Raw Crystal Processor Parts", o));
        ItemList.Circuit_Chip_Biocell.set(addItem(tLastID = 76, "Biocells", "Mutated Raw inteligence", o));
        ItemList.Circuit_Chip_BioCPU.set(addItem(tLastID = 77, "Bio Processing Unit", "Bio CPU", o));

        //Nand Chip
        ItemList.NandChip.set(addItem(tLastID = 75, "Nand Chip", "A very simple Circuit", new Object[]{OrePrefixes.circuit.get(Materials.Primitive), SubTag.NO_UNIFICATION}));

        //Vacuum Tube				Item01
        //Basic Circuit				IC2
        //Good Circuit				Item01

        //Integrated Logic Circuit  Item01
        ItemList.Circuit_Integrated_Good.set(addItem(tLastID = 79, "Good Integrated Circuit", "Good Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Good), SubTag.NO_UNIFICATION}));
        //Good Integrated Circuit   Item01
        //Advanced Circuit			IC2

        ItemList.Circuit_Microprocessor.set(addItem(tLastID = 78, "Microprocessor", "A Basic Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Basic), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Processor.set(addItem(tLastID = 80, "Integrated Processor", "A Good Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Good), SubTag.NO_UNIFICATION}));
        //ItemList.Circuit_Computer.set(addItem(tLastID = 81, "Processor Assembly", "Advanced Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Advanced), SubTag.NO_UNIFICATION}));
        //Workstation/          	Item01 Datacircuit
        //Mainframe					Item01 DataProcessor

        ItemList.Circuit_Nanoprocessor.set(addItem(tLastID = 82, "Nanoprocessor", "An Advanced Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Advanced), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Nanocomputer.set(addItem(tLastID = 83, "Nanoprocessor Assembly", "An Extreme Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Data), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Elitenanocomputer.set(addItem(tLastID = 84, "Elite Nanocomputer", "An Elite Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Elite), SubTag.NO_UNIFICATION}));
        //Nanoprocessor Mainframe  	Item01 Energy Flow Circuit

        ItemList.Circuit_Quantumprocessor.set(addItem(tLastID = 85, "Quantumprocessor", "An Extreme Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Data), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Quantumcomputer.set(addItem(tLastID = 86, "Quantumprocessor Assembly", "An Elite Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Elite), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Masterquantumcomputer.set(addItem(tLastID = 87, "Master Quantumcomputer", "A Master Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Master), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Quantummainframe.set(addItem(tLastID = 88, "Quantumprocessor Mainframe", "An Ultimate Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Ultimate), SubTag.NO_UNIFICATION}));

        ItemList.Circuit_Crystalprocessor.set(addItem(tLastID = 89, "Crystalprocessor", "An Elite Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Elite), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Crystalcomputer.set(addItem(tLastID = 96, "Crystalprocessor Assembly", "A Master Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Master), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Ultimatecrystalcomputer.set(addItem(tLastID = 90, "Ultimate Crystalcomputer", "An Ultimate Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Ultimate), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Crystalmainframe.set(addItem(tLastID = 91, "Crystalprocessor Mainframe", "A Super Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Superconductor), SubTag.NO_UNIFICATION}));

        ItemList.Circuit_Neuroprocessor.set(addItem(tLastID = 92, "Wetwareprocessor", "A Master Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Master), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Wetwarecomputer.set(addItem(tLastID = 93, "Wetwareprocessor Assembly", "An Ultimate Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Ultimate), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Wetwaresupercomputer.set(addItem(tLastID = 94, "Wetware Supercomputer", "A Super Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Superconductor), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Wetwaremainframe.set(addItem(tLastID = 95, "Wetware Mainframe", "An Infinite Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Infinite), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Ultimate.set(ItemList.Circuit_Ultimatecrystalcomputer.get(1L, new Object[0]));//maybe should be removed
        ItemList.Circuit_Biowarecomputer.set(addItem(tLastID = 98, "Biowareprocessor Assembly", "A Super Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Superconductor), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Biowaresupercomputer.set(addItem(tLastID = 99, "Bioware Supercomputer", "An Infinite Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Infinite), SubTag.NO_UNIFICATION}));

        ItemList.Circuit_Bioprocessor.set(addItem(tLastID = 97, "Bioprocessor", "An Ultimate Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Ultimate), SubTag.NO_UNIFICATION}));
        ItemList.Special_Oredrill_Circuit.set(addItem(tLastID = 170, "Ore Drilling Controll Circuit", "Special control circuit for the Ore Drilling Plant V", new Object[]{OrePrefixes.circuit.get(Materials.Quantum), SubTag.NO_UNIFICATION}));
        ItemList.Weebium_Wafer.set(addItem(tLastID = 171, "Weebium Wafer", "A Weebium Wafer", o));
        ItemList.Weebium_Chip.set(addItem(tLastID = 172,"Weebium Chip", "A cut and processed Weebium Chip",o));
        ItemList.Weebium_Silicon_Boule.set(addItem(tLastID = 173, "Weebium Doped Silicon Boule", "A raw Silicon boule Doped with Weebium", o));
        ItemList.Engraved_Weebium_Wafer.set(addItem(tLastID = 174, "Engraved Weebium Wafer", "An engraved Weebium Wafer", o));
        ItemList.Weebium_Wafer_Raw.set(addItem(tLastID = 175, "Raw Weebium Wafer", "A Raw Weebium Wafer", o));


        ItemList.Circuit_Board_Coated_Basic.set(addItem(tLastID = 100, "Circuit Board", "A basic Circuit Board", o));
        ItemList.Circuit_Board_Phenolic_Good.set(addItem(tLastID = 101, "Good Circuit Board", "A good Circuit Board", o));
        ItemList.Circuit_Board_Epoxy_Advanced.set(addItem(tLastID = 102, "Advanced Circuit Board", "A advanced Circuit Board", o));
        ItemList.Circuit_Board_Fiberglass_Advanced.set(addItem(tLastID = 103, "More Advanced Circuit Board", "A more advanced Circuit Board", o));
        ItemList.Circuit_Board_Multifiberglass_Elite.set(addItem(tLastID = 104, "Elite Circuit Board", "A elite Circuit Board", o));
        ItemList.Circuit_Board_Wetware_Extreme.set(addItem(tLastID = 105, "Extreme Wetware Lifesupport Circuit Board", "The Board that keeps life", o));
        ItemList.Circuit_Board_Plastic_Advanced.set(addItem(tLastID = 106, "Plastic Circuit Board", "A good Board", o));
        ItemList.Circuit_Board_Bio_Ultra.set(addItem(tLastID = 107, "Ultra Bio Mutated Circuit Board", "Bio genetic mutated Board", o));
        ItemList.Circuit_Biomainframe.set(addItem(tLastID = 120, "Bio Mainframe", "A Bio Circuit",  new Object[]{OrePrefixes.circuit.get(Materials.Bio), SubTag.NO_UNIFICATION}));

        ItemList.Tube_Wires.set(addItem(tLastID = 110, "Tube Wires", "For the Vacuum Tubes", o));

        ItemList.Cover_SolarPanel_UHV.set(addItem(tLastID = 130, "Solar Panel (UHV)", "Ultimate High Voltage Solar Panel (Needs cleaning with right click)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 128L)}));
        ItemList.Cover_SolarPanel_UEV.set(addItem(tLastID = 131, "Solar Panel (UEV)", "Ultimate Extreme Voltage Solar Panel (Needs cleaning with right click)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 256L), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 256L), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 256L)}));
        ItemList.Cover_SolarPanel_UIV.set(addItem(tLastID = 132, "Solar Panel (UIV)", "Ultimate Insane Voltage Solar Panel (Needs cleaning with right click)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 512L), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 512L), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 512L)}));
        ItemList.Cover_SolarPanel_UMV.set(addItem(tLastID = 169, "Solar Panel (UMV)", "Ultimate Mega Voltage Solar Panel (Needs cleaning with right click)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 512L), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 512L), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 512L)}));


        ItemList.Cover_SolarPanel_LVx16.set(addItem(tLastID = 200, "Hi-Amp Solar Panel (LV)", "16A Low Voltage Solar Panel (Needs cleaning with right click)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 128L)}));
        ItemList.Cover_SolarPanel_MVx16.set(addItem(tLastID = 201, "Hi-Amp Solar Panel (MV)", "16A Medium Voltage Solar Panel (Needs cleaning with right click)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 128L)}));
        ItemList.Cover_SolarPanel_HVx16.set(addItem(tLastID = 202, "Hi-Amp Solar Panel (HV)", "16A High Voltage Solar Panel (Needs cleaning with right click)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 128L)}));
        ItemList.Cover_SolarPanel_EVx16.set(addItem(tLastID = 203, "Hi-Amp Solar Panel (EV)", "16A Extreme Voltage Solar Panel (Needs cleaning with right click)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 128L)}));
        ItemList.Cover_SolarPanel_IVx16.set(addItem(tLastID = 204, "Hi-Amp Solar Panel (IV)", "16A Insane Voltage Solar Panel (Needs cleaning with right click)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 128L)}));
        ItemList.Cover_SolarPanel_LuVx16.set(addItem(tLastID = 205, "Hi-Amp Solar Panel (LuV)", "16A Ludicrous Voltage Solar Panel (Needs cleaning with right click)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 128L)}));
        ItemList.Cover_SolarPanel_ZPMx16.set(addItem(tLastID = 206, "Hi-Amp Solar Panel (ZPM)", "16A ZPM Voltage Solar Panel (Needs cleaning with right click)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 128L)}));
        ItemList.Cover_SolarPanel_UVx16.set(addItem(tLastID = 207, "Hi-Amp Solar Panel (UV)", "16A Ultimate Voltage Solar Panel (Needs cleaning with right click)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 128L)}));
        ItemList.Cover_SolarPanel_UHVx16.set(addItem(tLastID = 208, "Hi-Amp Solar Panel (UHV)", "16A Ultimate High Voltage Solar Panel (Needs cleaning with right click)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 128L)}));
        ItemList.Cover_SolarPanel_UEVx16.set(addItem(tLastID = 209, "Hi-Amp Solar Panel (UEV)", "16A Ultimate Extreme Voltage Solar Panel (Needs cleaning with right click)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 128L)}));
        ItemList.Cover_SolarPanel_UIVx16.set(addItem(tLastID = 210, "Hi-Amp Solar Panel (UIV)", "16A Ultimate Insane Voltage Solar Panel (Needs cleaning with right click)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 128L)}));
        ItemList.Cover_SolarPanel_UMVx16.set(addItem(tLastID = 211, "Hi-Amp Solar Panel (UMV)", "16A Ultimate Mega Voltage Solar Panel (Needs cleaning with right click)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 128L), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 128L)}));



        GregTech_API.registerCover(ItemList.Cover_SolarPanel_LVx16.get(1L, new Object[0]), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_LVx16), new GT_Cover_SolarPanel(32, true, 16L));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_MVx16.get(1L, new Object[0]), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_MVx16), new GT_Cover_SolarPanel(128, true, 16L));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_HVx16.get(1L, new Object[0]), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_HVx16), new GT_Cover_SolarPanel(512, true, 16L));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_EVx16.get(1L, new Object[0]), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_EVx16), new GT_Cover_SolarPanel(2048, true, 16L));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_IVx16.get(1L, new Object[0]), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_IVx16), new GT_Cover_SolarPanel(8192, true, 16L));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_LuVx16.get(1L, new Object[0]), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_LuVx16), new GT_Cover_SolarPanel(32768, true, 16L));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_ZPMx16.get(1L, new Object[0]), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_ZPMx16), new GT_Cover_SolarPanel(131072, true, 16L));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_UVx16.get(1L, new Object[0]), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_UVx16), new GT_Cover_SolarPanel(524288, true, 16L));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_UHVx16.get(1L, new Object[0]), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_UHVx16), new GT_Cover_SolarPanel(2097152, true, 16L));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_UEVx16.get(1L, new Object[0]), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_UEVx16), new GT_Cover_SolarPanel(8388608, true, 16L));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_UIVx16.get(1L, new Object[0]), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_UIVx16), new GT_Cover_SolarPanel(33554432, true, 16L));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_UMVx16.get(1L, new Object[0]), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_UMVx16), new GT_Cover_SolarPanel(134217728, true, 16L));



        GregTech_API.registerCover(ItemList.Cover_SolarPanel_UHV.get(1L, new Object[0]), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_UHV), new GT_Cover_SolarPanel(2097152));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_UEV.get(1L, new Object[0]), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_UEV), new GT_Cover_SolarPanel(8388608));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_UIV.get(1L, new Object[0]), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_UIV), new GT_Cover_SolarPanel(33554432));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_UMV.get(1L, new Object[0]), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_UMV), new GT_Cover_SolarPanel(134217728));


        ItemList.ULV_Coil.set(addItem(tLastID = 140, "Ultra Low Voltage Coil", "Primitive Coil", o));
        ItemList.LV_Coil.set(addItem(tLastID = 141, "Low Voltage Coil", "Basic Coil", o));
        ItemList.MV_Coil.set(addItem(tLastID = 142, "Medium Voltage Coil", "Good Coil", o));
        ItemList.HV_Coil.set(addItem(tLastID = 143, "High Voltage Coil", "Advanced Coil", o));
        ItemList.EV_Coil.set(addItem(tLastID = 144, "Extreme Voltage Coil", "Extreme Coil", o));
        ItemList.IV_Coil.set(addItem(tLastID = 145, "Insane Voltage Coil", "Elite Coil", o));
        ItemList.LuV_Coil.set(addItem(tLastID = 146, "Ludicrous Voltage Coil", "Master Coil", o));
        ItemList.ZPM_Coil.set(addItem(tLastID = 147, "ZPM Voltage Coil", "Ultimate Coil", o));
        ItemList.UV_Coil.set(addItem(tLastID = 148, "Ultimate Voltage Coil", "Super Coil", o));
        ItemList.UHV_Coil.set(addItem(tLastID = 149, "Highly Ultimate Voltage Coil", "Infinite Coil", o));
    }

    public boolean doesShowInCreative(OrePrefixes aPrefix, Materials aMaterial, boolean aDoShowAllItems) {
        return aDoShowAllItems;
    }
}