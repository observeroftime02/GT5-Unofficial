package gregtech.api.enums;

import gregtech.api.interfaces.IItemContainer;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

import static gregtech.api.enums.GT_Values.W;

/**
 * Class containing all non-OreDict Items of GregTech.
 */
public enum ItemList implements IItemContainer {
    Color_00, Color_01, Color_02, Color_03, Color_04, Color_05, Color_06, Color_07, Color_08, Color_09, Color_10, Color_11, Color_12, Color_13, Color_14, Color_15,
    Display_ITS_FREE,
    Display_Fluid,
    FR_Lemon,
    FR_Mulch,
    FR_Fertilizer,
    FR_Compost,
    FR_Silk,
    FR_Wax,
    FR_RefractoryWax,
    FR_WaxCapsule,
    FR_RefractoryCapsule,
    FR_Stick,
    FR_Casing_Impregnated,
    FR_Casing_Sturdy,
    FR_Casing_Hardened,
    FR_Bee_Drone,
    FR_Bee_Princess,
    FR_Bee_Queen,
    FR_Tree_Sapling,
    FR_Butterfly,
    FR_Larvae,
    FR_Serum,
    FR_Caterpillar,
    FR_PollenFertile,
    Scrap,
    Dynamite,
    IndustrialExplosive,
    Cell_Empty,
    Cell_Steam,
    Cell_Electrolyzed_Water,
    RawCarbonFibers,
    RawCarbonMesh,
    Fertilizer,
    Resin,
    Plantball,
    CoalCoke,
    Shape_Empty,
    Shape_Mold_Bottle,
    Shape_Mold_Plate,
    Shape_Mold_Ingot,
    Shape_Mold_Casing,
    Shape_Mold_Gear,
    Shape_Mold_Gear_Small,
    Shape_Mold_Credit,
    Shape_Mold_Nugget,
    Shape_Mold_Block,
    Shape_Mold_Ball,
    Shape_Mold_Bun,
    Shape_Mold_Bread,
    Shape_Mold_Baguette,
    Shape_Mold_Cylinder,
    Shape_Mold_Anvil,
    Shape_Mold_Arrow,
    Shape_Mold_Name,
    Shape_Slicer_Flat,
    Shape_Slicer_Stripes,
    Shape_Extruder_Bottle,
    Shape_Extruder_Plate,
    Shape_Extruder_Cell,
    Shape_Extruder_Ring,
    Shape_Extruder_Rod,
    Shape_Extruder_Bolt,
    Shape_Extruder_Ingot,
    Shape_Extruder_Wire,
    Shape_Extruder_Casing,
    Shape_Extruder_Pipe_Tiny,
    Shape_Extruder_Pipe_Small,
    Shape_Extruder_Pipe_Medium,
    Shape_Extruder_Pipe_Large,
    Shape_Extruder_Pipe_Huge,
    Shape_Extruder_Block,
    Shape_Extruder_Sword,
    Shape_Extruder_Pickaxe,
    Shape_Extruder_Shovel,
    Shape_Extruder_Axe,
    Shape_Extruder_Hoe,
    Shape_Extruder_Hammer,
    Shape_Extruder_File,
    Shape_Extruder_Saw,
    Shape_Extruder_Gear,
    Bottle_Dragon_Blood,
    Coin_Doge,
    Bottle_Purple_Drink,
    Large_Fluid_Cell_Steel,
    Large_Fluid_Cell_TungstenSteel,
    Crop_Drop_Argentia,
    Crop_Drop_Plumbilia,
    Crop_Drop_Indigo,
    Crop_Drop_Ferru,
    Crop_Drop_Aurelia,
    Crop_Drop_OilBerry,
    Crop_Drop_MilkWart,
    Crop_Drop_BobsYerUncleRanks,
    Crop_Drop_Coppon,
    Crop_Drop_Tine,
    Schematic,
    Schematic_Crafting,
    Schematic_1by1,
    Schematic_2by2,
    Schematic_3by3,
    Schematic_Dust,
    Circuit_Integrated,
    Circuit_Board_Basic,
    Circuit_Board_Advanced,
    Circuit_Board_Elite,
    Circuit_Parts_Advanced,
    Circuit_Parts_Wiring_Basic,
    Circuit_Parts_Wiring_Advanced,
    Circuit_Parts_Wiring_Elite,
    Circuit_Parts_Crystal_Chip_Elite,
    Circuit_Parts_Crystal_Chip_Master,
    Circuit_Primitive,
    Circuit_Basic,
    Circuit_Good,
    Circuit_Advanced,
    Circuit_Data,
    Circuit_Elite,
    Circuit_Master,
    Circuit_Ultimate,
    Rotor_LV, Rotor_MV, Rotor_HV, Rotor_EV, Rotor_IV, Rotor_LuV, Rotor_ZPM, Rotor_UV,
    Electric_Motor_LV, Electric_Motor_MV, Electric_Motor_HV, Electric_Motor_EV, Electric_Motor_IV, Electric_Motor_LuV, Electric_Motor_ZPM, Electric_Motor_UV,
    Electric_Pump_LV, Electric_Pump_MV, Electric_Pump_HV, Electric_Pump_EV, Electric_Pump_IV, Electric_Pump_LuV, Electric_Pump_ZPM, Electric_Pump_UV,
    Conveyor_Module_LV, Conveyor_Module_MV, Conveyor_Module_HV, Conveyor_Module_EV, Conveyor_Module_IV, Conveyor_Module_LuV, Conveyor_Module_ZPM, Conveyor_Module_UV,
    Electric_Piston_LV, Electric_Piston_MV, Electric_Piston_HV, Electric_Piston_EV, Electric_Piston_IV, Electric_Piston_LuV, Electric_Piston_ZPM, Electric_Piston_UV,
    Field_Generator_LV, Field_Generator_MV, Field_Generator_HV, Field_Generator_EV, Field_Generator_IV, Field_Generator_LuV, Field_Generator_ZPM, Field_Generator_UV,
    Robot_Arm_LV, Robot_Arm_MV, Robot_Arm_HV, Robot_Arm_EV, Robot_Arm_IV, Robot_Arm_LuV, Robot_Arm_ZPM, Robot_Arm_UV,
    Emitter_LV, Emitter_MV, Emitter_HV, Emitter_EV, Emitter_IV, Emitter_LuV, Emitter_ZPM, Emitter_UV,
    Sensor_LV, Sensor_MV, Sensor_HV, Sensor_EV, Sensor_IV, Sensor_LuV, Sensor_ZPM, Sensor_UV,
    Battery_Hull_LV, Battery_Hull_MV, Battery_Hull_HV,
    Battery_SU_LV_SulfuricAcid,
    Battery_SU_LV_Mercury,
    Battery_SU_MV_SulfuricAcid,
    Battery_SU_MV_Mercury,
    Battery_SU_HV_SulfuricAcid,
    Battery_SU_HV_Mercury,
    Battery_RE_ULV_Tantalum,
    Battery_RE_LV_Cadmium,
    Battery_RE_LV_Lithium,
    Battery_RE_LV_Sodium,
    Battery_RE_MV_Cadmium,
    Battery_RE_MV_Lithium,
    Battery_RE_MV_Sodium,
    Battery_RE_HV_Cadmium,
    Battery_RE_HV_Lithium,
    Battery_RE_HV_Sodium,
    ZPM,
    Upgrade_Battery,
    Upgrade_Overclocker,
    Upgrade_Muffler,
    Upgrade_SteamEngine,
    Upgrade_Lock,
    Cover_Controller,
    Cover_ActivityDetector,
    Cover_FluidDetector,
    Cover_ItemDetector,
    Cover_EnergyDetector,
    Cover_Drain,
    Cover_Shutter,
    Cover_Crafting,
    Cover_Screen,
    Cover_SolarPanel,
    Cover_SolarPanel_8V,
    Cover_SolarPanel_LV,
    Cover_SolarPanel_MV,
    Cover_SolarPanel_HV,
    Cover_SolarPanel_EV,
    Cover_SolarPanel_IV,
    Cover_SolarPanel_LuV,
    Cover_SolarPanel_ZPM,
    Cover_SolarPanel_UV,
    Ingot_IridiumAlloy,
    Plate_IridiumAlloy,
    Plate_AdvancedAlloy,
    Plate_CarbonAlloy,
    Dye_Indigo,
    Dye_SquidInk,
    Dye_Bonemeal,
    Dye_Cocoa,
    Duct_Tape,
    Book_Written_00,
    Book_Written_01,
    Book_Written_02,
    Book_Written_03,
    Paper_Printed_Pages,
    Paper_Magic_Empty,
    Paper_Magic_Page,
    Paper_Magic_Pages,
    Paper_Punch_Card_Empty,
    Paper_Punch_Card_Encoded,
    NC_SensorCard,
    NC_SensorKit,
    Tool_Lighter_Invar_Empty,
    Tool_Lighter_Invar_Used,
    Tool_Lighter_Invar_Full,
    Tool_Lighter_Platinum_Empty,
    Tool_Lighter_Platinum_Used,
    Tool_Lighter_Platinum_Full,
    Tool_Cheat,
    Tool_Scanner,
    Tool_DataOrb,
    Tool_DataStick,
    Tool_Sonictron,

    Armor_Cheat,
    Armor_Cloaking,
    Armor_Lamp,
    Armor_LithiumPack,
    Armor_LapotronicPack,
    Armor_ForceField,
    Energy_LapotronicOrb,
    Reactor_NeutronReflector,
    Component_Turbine_Bronze,
    Component_Turbine_Steel,
    Component_Turbine_Magnalium,
    Component_Turbine_TungstenSteel,
    Component_Turbine_Carbon,
    Component_LavaFilter,
    Component_Sawblade_Diamond,
    Component_Grinder_Diamond,
    Component_Grinder_Tungsten,
    Component_Filter,
    Component_Minecart_Wheels_Iron,
    Component_Minecart_Wheels_Steel,

    Generator_Diesel_LV,
    Generator_Diesel_MV,
    Generator_Diesel_HV,
    Generator_Gas_Turbine_LV,
    Generator_Gas_Turbine_MV,
    Generator_Gas_Turbine_HV,
    Generator_Steam_Turbine_LV,
    Generator_Steam_Turbine_MV,
    Generator_Steam_Turbine_HV,
    Generator_Naquadah_Mark_I,
    Generator_Naquadah_Mark_II,
    Generator_Naquadah_Fluid,

    Machine_Bronze_Boiler,
    Machine_Bronze_Boiler_Solar,
    Machine_Bronze_CraftingTable,
    Machine_Bronze_Furnace,
    Machine_Bronze_Macerator,
    Machine_Bronze_Extractor,
    Machine_Bronze_Hammer,
    Machine_Bronze_Compressor,
    Machine_Bronze_AlloySmelter,
    Machine_Bronze_BlastFurnace,
    Machine_Bricked_BlastFurnace,
    Machine_Steel_Boiler_Lava,
    Machine_Steel_Boiler,
    Machine_Steel_Furnace,
    Machine_Steel_Macerator,
    Machine_Steel_Extractor,
    Machine_Steel_Hammer,
    Machine_Steel_Compressor,
    Machine_Steel_AlloySmelter,

    Hull_Bronze, Hull_Steel, Hull_Bronze_Bricks, Hull_Steel_Bricks,

    Transformer_LV_ULV, Transformer_MV_LV, Transformer_HV_MV, Transformer_EV_HV, Transformer_IV_EV, Transformer_LuV_IV, Transformer_ZPM_LuV, Transformer_UV_ZPM, Transformer_MAX_UV,

    Casing_ULV, Casing_LV, Casing_MV, Casing_HV, Casing_EV, Casing_IV, Casing_LuV, Casing_ZPM, Casing_UV, Casing_MAX, Casing_BronzePlatedBricks, Casing_HeatProof, Casing_Coil_Superconductor,
    Casing_SolidSteel, Casing_FrostProof, Casing_Gearbox_Bronze, Casing_Gearbox_Steel, Casing_Gearbox_Titanium, Casing_Gearbox_TungstenSteel, Casing_Processor, Casing_DataDrive, Casing_ContainmentField, Casing_Assembler, Casing_Pump, Casing_Motor, Casing_Pipe_Bronze, Casing_Pipe_Steel, Casing_Pipe_Titanium, Casing_Pipe_TungstenSteel, Casing_Pipe_Polytetrafluoroethylene,
    Casing_Stripes_A, Casing_Stripes_B, Casing_RadioactiveHazard, Casing_BioHazard, Casing_ExplosionHazard, Casing_FireHazard, Casing_AcidHazard, Casing_MagicHazard, Casing_FrostHazard, Casing_NoiseHazard, Casing_Grate, Casing_Vent, Casing_RadiationProof, Casing_Firebox_Bronze, Casing_Firebox_Steel, Casing_Firebox_TungstenSteel, Casing_Chemically_Inert,
    Casing_MiningOsmiridium, Casing_RobustTungstenSteel, Casing_CleanStainlessSteel, Casing_StableTitanium, Casing_Firebox_Titanium,
    Hull_ULV, Hull_LV, Hull_MV, Hull_HV, Hull_EV, Hull_IV, Hull_LuV, Hull_ZPM, Hull_UV, Hull_MAX,
    CompressedFireclay, Firebrick, Casing_Firebricks,
    
    Automation_Filter_ULV, Automation_Filter_LV, Automation_Filter_MV, Automation_Filter_HV, Automation_Filter_EV, Automation_Filter_IV,
    Automation_TypeFilter_ULV, Automation_TypeFilter_LV, Automation_TypeFilter_MV, Automation_TypeFilter_HV, Automation_TypeFilter_EV, Automation_TypeFilter_IV,
    Automation_ChestBuffer_ULV, Automation_ChestBuffer_LV, Automation_ChestBuffer_MV, Automation_ChestBuffer_HV, Automation_ChestBuffer_EV, Automation_ChestBuffer_IV,
    Automation_SuperBuffer_ULV, Automation_SuperBuffer_LV, Automation_SuperBuffer_MV, Automation_SuperBuffer_HV, Automation_SuperBuffer_EV, Automation_SuperBuffer_IV,
    Automation_Regulator_ULV, Automation_Regulator_LV, Automation_Regulator_MV, Automation_Regulator_HV, Automation_Regulator_EV, Automation_Regulator_IV,
    Automation_ItemDistributor_ULV, Automation_ItemDistributor_LV, Automation_ItemDistributor_MV, Automation_ItemDistributor_HV, Automation_ItemDistributor_EV, Automation_ItemDistributor_IV,

    Hatch_Dynamo_ULV, Hatch_Dynamo_LV, Hatch_Dynamo_MV, Hatch_Dynamo_HV, Hatch_Dynamo_EV, Hatch_Dynamo_IV, Hatch_Dynamo_LuV, Hatch_Dynamo_ZPM, Hatch_Dynamo_UV, Hatch_Dynamo_MAX,
    Hatch_Energy_ULV, Hatch_Energy_LV, Hatch_Energy_MV, Hatch_Energy_HV, Hatch_Energy_EV, Hatch_Energy_IV, Hatch_Energy_LuV, Hatch_Energy_ZPM, Hatch_Energy_UV, Hatch_Energy_MAX,
    Hatch_Input_ULV, Hatch_Input_LV, Hatch_Input_MV, Hatch_Input_HV, Hatch_Input_EV, Hatch_Input_IV, Hatch_Input_LuV, Hatch_Input_ZPM, Hatch_Input_UV, Hatch_Input_MAX,
    Hatch_Input_Bus_ULV, Hatch_Input_Bus_LV, Hatch_Input_Bus_MV, Hatch_Input_Bus_HV, Hatch_Input_Bus_EV, Hatch_Input_Bus_IV, Hatch_Input_Bus_LuV, Hatch_Input_Bus_ZPM, Hatch_Input_Bus_UV, Hatch_Input_Bus_MAX,
    Hatch_Output_ULV, Hatch_Output_LV, Hatch_Output_MV, Hatch_Output_HV, Hatch_Output_EV, Hatch_Output_IV, Hatch_Output_LuV, Hatch_Output_ZPM, Hatch_Output_UV, Hatch_Output_MAX,
    Hatch_Output_Bus_ULV, Hatch_Output_Bus_LV, Hatch_Output_Bus_MV, Hatch_Output_Bus_HV, Hatch_Output_Bus_EV, Hatch_Output_Bus_IV, Hatch_Output_Bus_LuV, Hatch_Output_Bus_ZPM, Hatch_Output_Bus_UV, Hatch_Output_Bus_MAX,
    Hatch_Muffler_LV, Hatch_Muffler_MV, Hatch_Muffler_HV, Hatch_Muffler_EV, Hatch_Muffler_IV, Hatch_Muffler_LuV, Hatch_Muffler_ZPM, Hatch_Muffler_UV, Hatch_Muffler_MAX,
    Hatch_Maintenance, Hatch_DataAccess_EV, Hatch_DataAccess_LuV,

    Battery_Buffer_1by1_ULV, Battery_Buffer_1by1_LV, Battery_Buffer_1by1_MV, Battery_Buffer_1by1_HV, Battery_Buffer_1by1_EV, Battery_Buffer_1by1_IV, Battery_Buffer_1by1_LuV, Battery_Buffer_1by1_ZPM, Battery_Buffer_1by1_UV, Battery_Buffer_1by1_MAX,
    Battery_Buffer_2by2_ULV, Battery_Buffer_2by2_LV, Battery_Buffer_2by2_MV, Battery_Buffer_2by2_HV, Battery_Buffer_2by2_EV, Battery_Buffer_2by2_IV, Battery_Buffer_2by2_LuV, Battery_Buffer_2by2_ZPM, Battery_Buffer_2by2_UV, Battery_Buffer_2by2_MAX,
    Battery_Buffer_3by3_ULV, Battery_Buffer_3by3_LV, Battery_Buffer_3by3_MV, Battery_Buffer_3by3_HV, Battery_Buffer_3by3_EV, Battery_Buffer_3by3_IV, Battery_Buffer_3by3_LuV, Battery_Buffer_3by3_ZPM, Battery_Buffer_3by3_UV, Battery_Buffer_3by3_MAX,
    Battery_Buffer_4by4_ULV, Battery_Buffer_4by4_LV, Battery_Buffer_4by4_MV, Battery_Buffer_4by4_HV, Battery_Buffer_4by4_EV, Battery_Buffer_4by4_IV, Battery_Buffer_4by4_LuV, Battery_Buffer_4by4_ZPM, Battery_Buffer_4by4_UV, Battery_Buffer_4by4_MAX,

    Locker_ULV, Locker_LV, Locker_MV, Locker_HV, Locker_EV, Locker_IV,

    Machine_Multi_LargeBoiler_Bronze, Machine_Multi_LargeBoiler_Steel, Machine_Multi_LargeBoiler_Titanium, Machine_Multi_LargeBoiler_TungstenSteel, Machine_Multi_BlastFurnace, Machine_Multi_ImplosionCompressor, Machine_Multi_VacuumFreezer, Machine_Multi_Furnace,
    Machine_LV_AlloySmelter, Machine_MV_AlloySmelter, Machine_HV_AlloySmelter, Machine_EV_AlloySmelter, Machine_IV_AlloySmelter,
    Machine_LV_Assembler, Machine_MV_Assembler, Machine_HV_Assembler, Machine_EV_Assembler, Machine_IV_Assembler,
    Machine_LV_Bender, Machine_MV_Bender, Machine_HV_Bender, Machine_EV_Bender, Machine_IV_Bender,
    Machine_LV_Canner, Machine_MV_Canner, Machine_HV_Canner, Machine_EV_Canner, Machine_IV_Canner,
    Machine_LV_Compressor, Machine_MV_Compressor, Machine_HV_Compressor, Machine_EV_Compressor, Machine_IV_Compressor,
    Machine_LV_Cutter, Machine_MV_Cutter, Machine_HV_Cutter, Machine_EV_Cutter, Machine_IV_Cutter,
    Machine_LV_Slicer, Machine_MV_Slicer, Machine_HV_Slicer, Machine_EV_Slicer, Machine_IV_Slicer,
    Machine_LV_Sifter, Machine_MV_Sifter, Machine_HV_Sifter, Machine_EV_Sifter, Machine_IV_Sifter,
    Machine_LV_ArcFurnace, Machine_MV_ArcFurnace, Machine_HV_ArcFurnace, Machine_EV_ArcFurnace, Machine_IV_ArcFurnace,
    Machine_LV_PlasmaArcFurnace, Machine_MV_PlasmaArcFurnace, Machine_HV_PlasmaArcFurnace, Machine_EV_PlasmaArcFurnace, Machine_IV_PlasmaArcFurnace,
    Machine_LV_Oven, Machine_MV_Oven, Machine_HV_Oven, Machine_EV_Oven, Machine_IV_Oven,
    Machine_LV_E_Furnace, Machine_MV_E_Furnace, Machine_HV_E_Furnace, Machine_EV_E_Furnace, Machine_IV_E_Furnace,
    Machine_LV_Extractor, Machine_MV_Extractor, Machine_HV_Extractor, Machine_EV_Extractor, Machine_IV_Extractor,
    Machine_LV_Extruder, Machine_MV_Extruder, Machine_HV_Extruder, Machine_EV_Extruder, Machine_IV_Extruder,
    Machine_LV_Lathe, Machine_MV_Lathe, Machine_HV_Lathe, Machine_EV_Lathe, Machine_IV_Lathe,
    Machine_LV_Macerator, Machine_MV_Macerator, Machine_HV_Macerator, Machine_EV_Macerator, Machine_IV_Macerator,
    Machine_LV_Microwave, Machine_MV_Microwave, Machine_HV_Microwave, Machine_EV_Microwave, Machine_IV_Microwave,
    Machine_LV_Printer, Machine_MV_Printer, Machine_HV_Printer, Machine_EV_Printer, Machine_IV_Printer,
    Machine_LV_Recycler, Machine_MV_Recycler, Machine_HV_Recycler, Machine_EV_Recycler, Machine_IV_Recycler,
    Machine_LV_Scanner, Machine_MV_Scanner, Machine_HV_Scanner, Machine_EV_Scanner, Machine_IV_Scanner,
    Machine_LV_Wiremill, Machine_MV_Wiremill, Machine_HV_Wiremill, Machine_EV_Wiremill, Machine_IV_Wiremill,
    Machine_LV_Electrolyzer, Machine_MV_Electrolyzer, Machine_HV_Electrolyzer, Machine_EV_Electrolyzer, Machine_IV_Electrolyzer,
    Machine_LV_Centrifuge, Machine_MV_Centrifuge, Machine_HV_Centrifuge, Machine_EV_Centrifuge, Machine_IV_Centrifuge,
    Machine_LV_ThermalCentrifuge, Machine_MV_ThermalCentrifuge, Machine_HV_ThermalCentrifuge, Machine_EV_ThermalCentrifuge, Machine_IV_ThermalCentrifuge,
    Machine_LV_OreWasher, Machine_MV_OreWasher, Machine_HV_OreWasher, Machine_EV_OreWasher, Machine_IV_OreWasher,
    Machine_LV_RockBreaker, Machine_MV_RockBreaker, Machine_HV_RockBreaker, Machine_EV_RockBreaker, Machine_IV_RockBreaker,
    Machine_LV_ChemicalReactor, Machine_MV_ChemicalReactor, Machine_HV_ChemicalReactor, Machine_EV_ChemicalReactor, Machine_IV_ChemicalReactor,
    Machine_Multi_LargeChemicalReactor,
    Machine_LV_FluidCanner, Machine_MV_FluidCanner, Machine_HV_FluidCanner, Machine_EV_FluidCanner, Machine_IV_FluidCanner,
    Machine_LV_Disassembler, Machine_MV_Disassembler, Machine_HV_Disassembler, Machine_EV_Disassembler, Machine_IV_Disassembler,
    Machine_LV_Bundler, Machine_MV_Bundler, Machine_HV_Bundler, Machine_EV_Bundler, Machine_IV_Bundler,
    Machine_LV_Massfab, Machine_MV_Massfab, Machine_HV_Massfab, Machine_EV_Massfab, Machine_IV_Massfab,
    Machine_LV_Amplifab, Machine_MV_Amplifab, Machine_HV_Amplifab, Machine_EV_Amplifab, Machine_IV_Amplifab,
    Machine_LV_Replicator, Machine_MV_Replicator, Machine_HV_Replicator, Machine_EV_Replicator, Machine_IV_Replicator,
    Machine_LV_Brewery, Machine_MV_Brewery, Machine_HV_Brewery, Machine_EV_Brewery, Machine_IV_Brewery,
    Machine_LV_Fermenter, Machine_MV_Fermenter, Machine_HV_Fermenter, Machine_EV_Fermenter, Machine_IV_Fermenter,
    Machine_LV_FluidExtractor, Machine_MV_FluidExtractor, Machine_HV_FluidExtractor, Machine_EV_FluidExtractor, Machine_IV_FluidExtractor,
    Machine_LV_FluidSolidifier, Machine_MV_FluidSolidifier, Machine_HV_FluidSolidifier, Machine_EV_FluidSolidifier, Machine_IV_FluidSolidifier,
    Machine_LV_Distillery, Machine_MV_Distillery, Machine_HV_Distillery, Machine_EV_Distillery, Machine_IV_Distillery,
    Machine_LV_ChemicalBath, Machine_MV_ChemicalBath, Machine_HV_ChemicalBath, Machine_EV_ChemicalBath, Machine_IV_ChemicalBath,
    Machine_LV_Polarizer, Machine_MV_Polarizer, Machine_HV_Polarizer, Machine_EV_Polarizer, Machine_IV_Polarizer,
    Machine_LV_ElectromagneticSeparator, Machine_MV_ElectromagneticSeparator, Machine_HV_ElectromagneticSeparator, Machine_EV_ElectromagneticSeparator, Machine_IV_ElectromagneticSeparator,
    Machine_LV_Autoclave, Machine_MV_Autoclave, Machine_HV_Autoclave, Machine_EV_Autoclave, Machine_IV_Autoclave,
    Machine_LV_Mixer, Machine_MV_Mixer, Machine_HV_Mixer, Machine_EV_Mixer, Machine_IV_Mixer,
    Machine_LV_LaserEngraver, Machine_MV_LaserEngraver, Machine_HV_LaserEngraver, Machine_EV_LaserEngraver, Machine_IV_LaserEngraver,
    Machine_LV_Press, Machine_MV_Press, Machine_HV_Press, Machine_EV_Press, Machine_IV_Press,
    Machine_LV_Hammer, Machine_MV_Hammer, Machine_HV_Hammer, Machine_EV_Hammer, Machine_IV_Hammer,
    Machine_LV_FluidHeater, Machine_MV_FluidHeater, Machine_HV_FluidHeater, Machine_EV_FluidHeater, Machine_IV_FluidHeater,
    Machine_LV_Miner, Machine_MV_Miner,

    Neutron_Reflector,
    //Reactor_Coolant_He_1, Reactor_Coolant_He_3, Reactor_Coolant_He_6, Reactor_Coolant_NaK_1, Reactor_Coolant_NaK_3, Reactor_Coolant_NaK_6,
    ThoriumCell_1, ThoriumCell_2, ThoriumCell_4,
    FusionComputer_LuV, FusionComputer_ZPMV, FusionComputer_UV,
    Casing_Fusion_Coil, Casing_Fusion, Casing_Fusion2,
    Generator_Plasma_IV, Generator_Plasma_LuV, Generator_Plasma_ZPMV,
    MagicEnergyConverter_LV, MagicEnergyConverter_MV, MagicEnergyConverter_HV,
    MagicEnergyAbsorber_LV, MagicEnergyAbsorber_MV, MagicEnergyAbsorber_HV, MagicEnergyAbsorber_EV,
    Depleted_Thorium_1, Depleted_Thorium_2, Depleted_Thorium_4,
    Processing_Array, Distillation_Tower, Energy_LapotronicOrb2,
    Energy_Module, Energy_Cluster,
    ZPM2, Quantum_Tank_LV, Quantum_Tank_MV, Quantum_Tank_HV, Quantum_Tank_EV, Quantum_Tank_IV, Quantum_Chest_LV, Quantum_Chest_MV, Quantum_Chest_HV, Quantum_Chest_EV, Quantum_Chest_IV,

    NULL, Cover_RedstoneTransmitterExternal, Cover_RedstoneTransmitterInternal, Cover_RedstoneReceiverExternal, Cover_RedstoneReceiverInternal,
    LargeSteamTurbine, LargeGasTurbine, LargeHPSteamTurbine, LargePlasmaTurbine,
    Ingot_Heavy1, Ingot_Heavy2, Ingot_Heavy3,
    Pump_LV, Pump_MV, Pump_HV, Pump_EV, Pump_IV, Pump_LuV, Pump_ZPM, Pump_UV,
    Teleporter, Cover_NeedsMaintainance, Casing_Turbine, Casing_Turbine1, Casing_Turbine2, Casing_Turbine3, Casing_EngineIntake,
    Casing_Coil_Cupronickel, Casing_Coil_Kanthal, Casing_Coil_Nichrome, Casing_Coil_TungstenSteel, Casing_Coil_HSSG, Casing_Coil_Naquadah, Casing_Coil_NaquadahAlloy,
    MobRep_LV, MobRep_MV, MobRep_HV, MobRep_EV, MobRep_IV, MobRep_LuV, MobRep_ZPM, MobRep_UV, Cover_PlayerDetector, Machine_Multi_HeatExchanger,
    Block_BronzePlate, Block_IridiumTungstensteel, Block_Plascrete, Block_TungstenSteelReinforced,
    Honeycomb, Charcoal_Pile, Block_BrittleCharcoal, Seismic_Prospector, Seismic_Prospector_Adv, OilDrill, OreDrill1, OreDrill2, OreDrill3, OreDrill4, PyrolyseOven, OilCracker, Crop_Drop_UUMBerry, Crop_Drop_UUABerry, Empty_Board_Basic, Empty_Board_Elite,
    Battery_Charger_4by4_ULV, Battery_Charger_4by4_LV, Battery_Charger_4by4_MV, Battery_Charger_4by4_HV, Battery_Charger_4by4_EV, Battery_Charger_4by4_IV, Battery_Charger_4by4_LuV, Battery_Charger_4by4_ZPM, Battery_Charger_4by4_UV, Battery_Charger_4by4_MAX,
    MicroTransmitter_HV, MicroTransmitter_EV, MicroTransmitter_IV, MicroTransmitter_LUV, MicroTransmitter_ZPM,
    Crop_Drop_Bauxite, Crop_Drop_Ilmenite, Crop_Drop_Pitchblende, Crop_Drop_Uraninite, Crop_Drop_Thorium, Crop_Drop_Nickel, Crop_Drop_Zinc, Crop_Drop_Manganese, Crop_Drop_Scheelite, Crop_Drop_Platinum, Crop_Drop_Iridium, Crop_Drop_Osmium, Crop_Drop_Naquadah, Uraniumcell_1, Uraniumcell_2, Uraniumcell_4, Moxcell_1, Moxcell_2, Moxcell_4,
    ModularBasicHelmet, ModularBasicChestplate, ModularBasicLeggings, ModularBasicBoots,
    ModularElectric1Helmet, ModularElectric1Chestplate, ModularElectric1Leggings, ModularElectric1Boots,
    ModularElectric2Helmet, ModularElectric2Chestplate, ModularElectric2Leggings, ModularElectric2Boots, Block_Powderbarrel, Block_MiningPipe, Block_MiningPipeTip, GelledToluene,
    FluidRegulator_LV, FluidRegulator_MV, FluidRegulator_HV, FluidRegulator_EV, FluidRegulator_IV, FluidRegulator_LuV, FluidRegulator_ZPM, FluidRegulator_UV, FluidFilter, CuringOven, Machine_Multi_Assemblyline, Machine_Multi_DieselEngine, QuantumEye, QuantumStar, Gravistar, Block_SSFUEL, Block_MSSFUEL, SFMixture, MSFMixture, Depleted_Naquadah_1, Depleted_Naquadah_2, Depleted_Naquadah_4, NaquadahCell_1, NaquadahCell_2, NaquadahCell_4, Hatch_AutoMaintenance,
    Machine_Multi_Cleanroom, Circuit_Board_Coated, Circuit_Board_Phenolic, Circuit_Board_Epoxy, Circuit_Board_Fiberglass, Circuit_Board_Multifiberglass, Circuit_Board_Wetware, Circuit_Board_Plastic,
    Circuit_Parts_Resistor, Circuit_Parts_ResistorSMD, Circuit_Parts_Glass_Tube, Circuit_Parts_Vacuum_Tube, Circuit_Parts_Coil, Circuit_Parts_Diode, Circuit_Parts_DiodeSMD, Circuit_Parts_Transistor, Circuit_Parts_TransistorSMD, Circuit_Parts_Capacitor, Circuit_Parts_CapacitorSMD, Circuit_Parts_GlassFiber, Circuit_Parts_PetriDish,
    Circuit_Silicon_Ingot, Circuit_Silicon_Ingot2, Circuit_Silicon_Ingot3, Circuit_Silicon_Wafer, Circuit_Silicon_Wafer2, Circuit_Silicon_Wafer3, Circuit_Wafer_ILC, Circuit_Chip_ILC, Circuit_Wafer_Ram, Circuit_Chip_Ram, 
    Circuit_Wafer_NAND, Circuit_Chip_NAND, Circuit_Wafer_NOR, Circuit_Chip_NOR, Circuit_Wafer_CPU, Circuit_Chip_CPU, Circuit_Wafer_SoC, Circuit_Chip_SoC, Circuit_Wafer_SoC2, Circuit_Chip_SoC2, Circuit_Wafer_PIC, Circuit_Chip_PIC, 
    Circuit_Wafer_HPIC, Circuit_Chip_HPIC, Circuit_Wafer_NanoCPU, Circuit_Chip_NanoCPU, Circuit_Wafer_QuantumCPU, Circuit_Chip_QuantumCPU, 
    Circuit_Chip_CrystalCPU, Circuit_Chip_CrystalSoC, Circuit_Chip_NeuroCPU, Circuit_Chip_Stemcell,
    Circuit_Microprocessor, Circuit_Processor, Circuit_Computer, Circuit_Nanoprocessor, Circuit_Nanocomputer, Circuit_Elitenanocomputer, Circuit_Quantumprocessor, Circuit_Quantumcomputer, Circuit_Masterquantumcomputer, 
    Circuit_Quantummainframe, Circuit_Crystalprocessor, Circuit_Crystalcomputer, Circuit_Ultimatecrystalcomputer, Circuit_Crystalmainframe, Circuit_Neuroprocessor, Circuit_Wetwarecomputer, Circuit_Wetwaresupercomputer, Circuit_Wetwaremainframe, Circuit_Parts_RawCrystalChip,
    Machine_LV_CircuitAssembler, Machine_MV_CircuitAssembler, Machine_HV_CircuitAssembler, Machine_EV_CircuitAssembler, Machine_IV_CircuitAssembler, Machine_LuV_CircuitAssembler, Machine_ZPM_CircuitAssembler, Machine_UV_CircuitAssembler, Circuit_Integrated_Good, Machine_IV_LightningRod, Machine_HV_LightningRod, Machine_EV_LightningRod;

    public static final ItemList[] DYE_ONLY_ITEMS = {Color_00, Color_01, Color_02, Color_03, Color_04, Color_05, Color_06, Color_07, Color_08, Color_09, Color_10, Color_11, Color_12, Color_13, Color_14, Color_15};
    public static Fluid sOilExtraHeavy, sEpichlorhydrin, sDrillingFluid, sNitricAcid, sBlueVitriol, sNickelSulfate, sToluene, sNitrationMixture, sRocketFuel, sHydricSulfur, sIndiumConcentrate, sLeadZincSolution;
    private ItemStack mStack;
    private boolean mHasNotBeenSet = true;

    @Override
    public IItemContainer set(Item aItem) {
        mHasNotBeenSet = false;
        if (aItem == null) return this;
        ItemStack aStack = new ItemStack(aItem, 1, 0);
        mStack = GT_Utility.copyAmount(1, aStack);
        return this;
    }

    @Override
    public IItemContainer set(ItemStack aStack) {
        mHasNotBeenSet = false;
        mStack = GT_Utility.copyAmount(1, aStack);
        return this;
    }

    @Override
    public Item getItem() {
        if (mHasNotBeenSet)
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        if (GT_Utility.isStackInvalid(mStack)) return null;
        return mStack.getItem();
    }

    @Override
    public Block getBlock() {
        if (mHasNotBeenSet)
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        return GT_Utility.getBlockFromItem(getItem());
    }

    @Override
    public final boolean hasBeenSet() {
        return !mHasNotBeenSet;
    }

    @Override
    public boolean isStackEqual(ItemStack aStack) {
        return isStackEqual(aStack, false, false);
    }

    @Override
    public boolean isStackEqual(ItemStack aStack, boolean aWildcard, boolean aIgnoreNBT) {
        if (GT_Utility.isStackInvalid(aStack)) return false;
        return GT_Utility.areUnificationsEqual(aStack, aWildcard ? getWildcard(1) : get(1), aIgnoreNBT);
    }

    @Override
    public ItemStack get(int aAmount, ItemStack... aReplacements) {
        if (mHasNotBeenSet)
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        if (GT_Utility.isStackInvalid(mStack)) return GT_Utility.copyAmount(aAmount, aReplacements);
        return GT_Utility.copyAmount(aAmount, GT_OreDictUnificator.get(mStack));
    }

    @Override
    public ItemStack getWildcard(int aAmount, ItemStack... aReplacements) {
        if (mHasNotBeenSet)
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        if (GT_Utility.isStackInvalid(mStack)) return GT_Utility.copyAmount(aAmount, aReplacements);
        return GT_Utility.copyAmountAndMetaData(aAmount, W, GT_OreDictUnificator.get(mStack));
    }

    @Override
    public ItemStack getUndamaged(int aAmount, ItemStack... aReplacements) {
        if (mHasNotBeenSet)
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        if (GT_Utility.isStackInvalid(mStack)) return GT_Utility.copyAmount(aAmount, aReplacements);
        return GT_Utility.copyAmountAndMetaData(aAmount, 0, GT_OreDictUnificator.get(mStack));
    }

    @Override
    public ItemStack getAlmostBroken(int aAmount, ItemStack... aReplacements) {
        if (mHasNotBeenSet)
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        if (GT_Utility.isStackInvalid(mStack)) return GT_Utility.copyAmount(aAmount, aReplacements);
        return GT_Utility.copyAmountAndMetaData(aAmount, mStack.getMaxDamage() - 1, GT_OreDictUnificator.get(mStack));
    }

    @Override
    public ItemStack getWithName(int aAmount, String aDisplayName, ItemStack... aReplacements) {
        ItemStack rStack = get(1, aReplacements);
        if (GT_Utility.isStackInvalid(rStack)) return null;
        rStack.setStackDisplayName(aDisplayName);
        return GT_Utility.copyAmount(aAmount, rStack);
    }

    @Override
    public ItemStack getWithCharge(int aAmount, int aEnergy, ItemStack... aReplacements) {
        ItemStack rStack = get(1, aReplacements);
        if (GT_Utility.isStackInvalid(rStack)) return null;
        GT_ModHandler.chargeElectricItem(rStack, aEnergy, Integer.MAX_VALUE, true, false);
        return GT_Utility.copyAmount(aAmount, rStack);
    }

    @Override
    public ItemStack getWithDamage(int aAmount, int aMetaValue, ItemStack... aReplacements) {
        if (mHasNotBeenSet)
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        if (GT_Utility.isStackInvalid(mStack)) return GT_Utility.copyAmount(aAmount, aReplacements);
        return GT_Utility.copyAmountAndMetaData(aAmount, aMetaValue, GT_OreDictUnificator.get(mStack));
    }

    @Override
    public IItemContainer registerOre(Object... aOreNames) {
        if (mHasNotBeenSet)
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        for (Object tOreName : aOreNames) GT_OreDictUnificator.registerOre(tOreName, get(1));
        return this;
    }

    @Override
    public IItemContainer registerWildcardAsOre(Object... aOreNames) {
        if (mHasNotBeenSet)
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        for (Object tOreName : aOreNames) GT_OreDictUnificator.registerOre(tOreName, getWildcard(1));
        return this;
    }
}
