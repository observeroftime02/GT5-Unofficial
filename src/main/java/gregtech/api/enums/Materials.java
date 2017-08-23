package gregtech.api.enums;

import cpw.mods.fml.common.Loader;
import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.Aspects.AspectStack;
import gregtech.api.interfaces.IColorModulationContainer;
import gregtech.api.interfaces.IMaterialHandler;
import gregtech.api.interfaces.ISubTagContainer;
import gregtech.api.objects.GT_FluidStack;
import gregtech.api.objects.MaterialStack;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import gregtech.loaders.materialprocessing.ProcessingConfig;
import gregtech.loaders.materialprocessing.ProcessingModSupport;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.*;

import static gregtech.api.enums.Dyes.*;
import static gregtech.api.enums.GT_Values.M;
import static gregtech.api.enums.MaterialFlags.*;
import static gregtech.api.enums.TextureSet.*;

public class Materials implements IColorModulationContainer, ISubTagContainer {
    private static Materials[] MATERIALS_ARRAY = new Materials[]{};
    private static final Map<String, Materials> MATERIALS_MAP = new LinkedHashMap<String, Materials>();
    public static final List<IMaterialHandler> mMaterialHandlers = new ArrayList<IMaterialHandler>();

    /**
     * This is for keeping compatibility with addons mods (Such as TinkersGregworks etc) that looped over the old materials enum
     */
    @Deprecated
    public static Collection<Materials> VALUES = new LinkedHashSet<Materials>();

    /**
     * This is the Default Material returned in case no Material has been found or a NullPointer has been inserted at a location where it shouldn't happen.
     */
    public static Materials _NULL = new Materials(-1, "NULL", 255, 255, 255, 0, dyeNULL, NONE, Element._NULL);

    //TODO SEPERATE SMALL AND MORMAL ORES?
    //TODO FIX EMPTY FLAG
    //TODO JACKHAMMER USE NORMAL ROD
    //TODO FIX REPLICATOR OUTPUTIING CELLS? MAYBE ONLY FLUID?
    //TODO IF HAS METALLIC ICON, ADD METAL SUBTAG?
    //TODO POSSIBLE MISSED ADDING -1 FOR MELTING POINTS?
    //TODO CRASH IF GETTING NULL COMP VIA GTOREDICTUNI.GET TO CHECK BROKEN RECIPES

    /**
     * Direct Elements
     */
    public static Materials Aluminium = new Materials(19, "Aluminium", 128, 200, 240, dyeLightBlue, DULL, Element.Al).asMetal(933, 1700).addTools(10.0F, 128, 2).addFlags(PLATE, ROD, BOLT, RING, FOIL, SGEAR, LROD, GEAR);
    public static Materials Americium = new Materials(103, "Americium", 200, 200, 200, dyeLightGray, METALLIC, Element.Am).asMetal(1149, 0).addFlags(PLATE, ROD, LROD);
    public static Materials Antimony = new Materials(58, "Antimony", 220, 220, 240, dyeLightGray, SHINY, Element.Sb).asMetal(1449, 0);
    public static Materials Argon = new Materials(24, "Argon", 0, 255, 0, 240, dyeGreen, FLUID, Element.Ar).asGas();
    public static Materials Arsenic = new Materials(39, "Arsenic", 255, 255, 255, dyeOrange, DULL, Element.As).asSolid(1090).addFlags(PLATE, CELL);
    public static Materials Barium = new Materials(63, "Barium", 255, 255, 255, dyeNULL, METALLIC, Element.Ba).asDust(1000).addFlags(PLATE, FOIL, FWIRE);
    public static Materials Beryllium = new Materials(8, "Beryllium", 100, 180, 100, dyeGreen, METALLIC, Element.Be).asSolidOre(1560).addTools(14.0F, 64, 2).addFlags(PLATE, ROD, BOLT, LROD);
    public static Materials Bismuth = new Materials(90, "Bismuth", 100, 160, 160, dyeCyan, METALLIC, Element.Bi).asSolidOre(544).addTools(6.0F, 64, 1).addFlags(PLATE, ROD, BOLT, LROD);
    public static Materials Boron = new Materials(9, "Boron", 250, 250, 250, dyeWhite, DULL, Element.B).asDust(2349);
    public static Materials Caesium = new Materials(62, "Caesium", 0, 0, 301, dyeNULL, METALLIC, Element.Cs).asSolid(2349);
    public static Materials Calcium = new Materials(26, "Calcium", 255, 245, 245, dyePink, METALLIC, Element.Ca).asDust(1115);
    public static Materials Carbon = new Materials(10, "Carbon", 20, 20, 20, dyeBlack, DULL, Element.C).asSolid(3800).addTools(1.0F, 64, 2).addFlags(PLATE, ROD, BOLT, LROD);
    public static Materials Cadmium = new Materials(55, "Cadmium", 50, 50, 60, dyeGray, SHINY, Element.Cd).asDust(594);
    public static Materials Cerium = new Materials(65, "Cerium", 255, 255, 255, dyeNULL, METALLIC, Element.Ce).asSolid(1068, 1068);
    public static Materials Chlorine = new Materials(23, "Chlorine", 255, 255, 255, dyeCyan, FLUID, Element.Cr).asFluid();
    public static Materials Chrome = new Materials(30, "Chrome", 255, 230, 230, dyePink, SHINY, Element.Cr).asSolidOre(2180, 1700).addTools(11.0F, 256, 3).addFlags(PLATE, ROD, BOLT, SCREW, RING, ROTOR, LROD);
    public static Materials Cobalt = new Materials(33, "Cobalt", 80, 80, 250, dyeBlue, METALLIC, Element.Co).asSolid(1768).addTools(8.0F, 512, 3).addFlags(PLATE, ROD, BOLT, LROD);
    public static Materials Copper = new Materials(35, "Copper", 255, 100, 0, dyeOrange, SHINY, Element.Cu).asSolidOre(1357).addFlags(PLATE, DPLATE, ROD, FOIL, FWIRE, GEAR);
    public static Materials Deuterium = new Materials(2, "Deuterium", 255, 255, 0, 240, dyeYellow, FLUID, Element.D).asFluid();
    public static Materials Dysprosium = new Materials(73, "Dysprosium", 255, 255, 255, dyeNULL, METALLIC, Element.D).asSolid(1680, 1680);
    public static Materials Empty = new Materials(0, "Empty", 255, 255, 255, 255, dyeNULL, NONE).addFlags(EMPTY);
    public static Materials Europium = new Materials(70, "Europium", 255, 255, 255, dyeNULL, METALLIC, Element.Eu).asSolid(1099, 1099).addFlags(PLATE, ROD);
    public static Materials Fluorine = new Materials(14, "Fluorine", 255, 255, 255, 127, dyeGreen, FLUID, Element.F).asFluid();
    public static Materials Gallium = new Materials(37, "Gallium", 220, 220, 255, dyeLightGray, SHINY, Element.Ga).asSolid(302);
    public static Materials Gold = new Materials(86, "Gold", 255, 255, 30, dyeYellow, SHINY, Element.Au).asSolidOre(1337).addTools(12.0F, 64, 2);
    public static Materials Hydrogen = new Materials(1, "Hydrogen", 0, 0, 255, 240, dyeBlue, FLUID, Element.H).asFluid();
    public static Materials Helium = new Materials(4, "Helium", 255, 255, 0, 240, dyeYellow, FLUID, Element.He).asFluid();
    public static Materials Helium3 = new Materials(5, "Helium-3", 255, 255, 255, 240, dyeYellow, FLUID, Element.He_3).asFluid();
    public static Materials Indium = new Materials(56, "Indium", 64, 0, 128, dyeGray, METALLIC, Element.In).asSolid(429);
    public static Materials Iridium = new Materials(84, "Iridium", 240, 240, 245, dyeWhite, DULL, Element.Ir).asSolidOre(2719, 2719).addTools(6.0F, 2560, 3);
    public static Materials Iron = new Materials(32, "Iron", 200, 200, 200, dyeLightGray, METALLIC, Element.Fe).asSolidOre(1811).addTools(6.0F, 256, 2);
    public static Materials Lanthanum = new Materials(64, "Lanthanum", 255, 255, 255, dyeNULL, METALLIC, Element.La).asSolid(1193, 1193);
    public static Materials Lead = new Materials(89, "Lead", 140, 100, 140, dyePurple, DULL, Element.Pb).asSolidOre(600).addTools(8.0F, 64, 1);
    public static Materials Lithium = new Materials(6, "Lithium", 225, 220, 255, dyeLightBlue, DULL, Element.Li).asSolidOre(454);
    public static Materials Lutetium = new Materials(78, "Lutetium", 225, 220, 255, dyeLightBlue, DULL, Element.Lu).asSolid(1925, 1925);
    public static Materials Magic = new Materials(-128, "Magic", 100, 0, 200, dyePurple, SHINY, Element.Ma);
    public static Materials Magnesium = new Materials(18, "Magnesium", 255, 200, 200, dyePink, METALLIC, Element.Mg).asSolid(923);
    public static Materials Manganese = new Materials(31, "Manganese", 250, 250, 250, dyeWhite, DULL, Element.Mn).asSolidOre(1519).addTools(7.0F, 512, 2);
    public static Materials Mercury = new Materials(87, "Mercury", 255, 220, 220, dyeLightGray, SHINY, Element.Hg).asFluid();
    public static Materials Molybdenum = new Materials(48, "Molybdenum", 180, 180, 220, dyeBlue, SHINY, Element.Mo).asSolidOre(2896).addTools(7.0F, 512, 2);
    public static Materials Neodymium = new Materials(67, "Neodymium", 100, 100, 100, dyeNULL, METALLIC, Element.Nd).asSolidOre(1297, 1297).addTools(7.0F, 512, 2);
    public static Materials Neutronium = new Materials(129, "Neutronium", 250, 250, 250, dyeWhite, DULL, Element.Nt).asSolid(10000, 10000).addTools(24.0F, 655360, 6);
    public static Materials Nickel = new Materials(34, "Nickel", 200, 200, 250, dyeLightBlue, METALLIC, Element.Ni).asSolid(1728).addTools(6.0F, 64, 2);
    public static Materials Niobium = new Materials(47, "Niobium", 190, 180, 200, dyeNULL, METALLIC, Element.Nb).asSolid(2750, 2750);
    public static Materials Nitrogen = new Materials(12, "Nitrogen", 0, 150, 200, 240, dyeCyan, FLUID, Element.N).asGas();
    public static Materials Osmium = new Materials(83, "Osmium", 50, 50, 255, dyeBlue, METALLIC, Element.Os).asSolid(3306, 3306).addTools(16.0F, 1280, 4);
    public static Materials Oxygen = new Materials(13, "Oxygen", 0, 100, 200, 240, dyeWhite, FLUID, Element.O).asGas();
    public static Materials Palladium = new Materials(52, "Palladium", 128, 128, 128, dyeGray, SHINY, Element.Pd).asSolidOre(1828, 1828).addTools(8.0F, 512, 2);
    public static Materials Phosphor = new Materials(21, "Phosphor", 255, 255, 0, dyeYellow, DULL, Element.P).asDust(317);
    public static Materials Platinum = new Materials(85, "Platinum", 255, 255, 200, dyeOrange, SHINY, Element.Pt).asSolidOre(2041).addTools(12.0F, 64, 2);
    public static Materials Plutonium = new Materials(100, "Plutonium 239", 240, 50, 50, dyeLime, METALLIC, Element.Pu).asSolidOre(912).addTools(6.0F, 512, 3);
    public static Materials Plutonium241 = new Materials(101, "Plutonium 241", 250, 70, 70, dyeLime, SHINY, Element.Pu_241).asSolid(912).addTools(6.0F, 512, 3);
    public static Materials Potassium = new Materials(25, "Potassium", 250, 250, 250, dyeWhite, METALLIC, Element.K).asSolid(336);
    public static Materials Radon = new Materials(93, "Radon", 255, 0, 255, 240, dyePurple, FLUID, Element.Rn).asGas();
    public static Materials Silicon = new Materials(20, "Silicon", 60, 60, 80, dyeBlack, METALLIC, Element.Si).asSolid(1687, 1687);
    public static Materials Silver = new Materials(54, "Silver", 220, 220, 255, dyeLightGray, SHINY, Element.Ag).asSolidOre(1234).addTools(10.0F, 64, 2);
    public static Materials Sodium = new Materials(17, "Sodium", 0, 0, 150, dyeBlue, METALLIC, Element.Na).asDust(370);
    public static Materials Sulfur = new Materials(22, "Sulfur", 200, 200, 0, dyeYellow, DULL, Element.S).asDust(388);
    public static Materials Tantalum = new Materials(80, "Tantalum", 255, 255, 255, dyeNULL, METALLIC, Element.Ta).asSolid(3290);
    public static Materials Thorium = new Materials(96, "Thorium", 0, 30, 0, dyeBlack, SHINY, Element.Th).asSolidOre(2115).addTools(6.0F, 512, 2);
    public static Materials Tin = new Materials(57, "Tin", 220, 220, 220, dyeWhite, DULL, Element.Sn).asSolidOre(505, 505);
    public static Materials Titanium = new Materials(28, "Titanium", 220, 160, 240, dyePurple, METALLIC, Element.Ti).asSolidOre(1941, 1940);
    public static Materials Tritium = new Materials(3, "Tritium", 255, 0, 0, 240, dyeRed, METALLIC, Element.T).asFluid().addTools(7.0F, 1600, 3);
    public static Materials Tungsten = new Materials(81, "Tungsten", 50, 50, 50, dyeBlack, METALLIC, Element.W).asSolid(3695, 3000).addTools(7.0F, 2560, 3);
    public static Materials Uranium = new Materials(98, "Uranium 238", 50, 240, 50, dyeGreen, METALLIC, Element.U).asSolidOre(1405).addTools(6.0F, 512, 3);
    public static Materials Uranium235 = new Materials(97, "Uranium 235", 70, 250, 70, dyeGreen, METALLIC, Element.U_235).asSolidOre(1405).addTools(6.0F, 512, 3);
    public static Materials Vanadium = new Materials(29, "Vanadium", 50, 50, 50, dyeBlack, METALLIC, Element.V).asSolid(2183, 2183);
    public static Materials Yttrium = new Materials(45, "Yttrium", 220, 250, 220, dyeNULL, METALLIC, Element.Y).asSolid(1799, 1799);
    public static Materials Zinc = new Materials(36, "Zinc", 250, 240, 240, dyeWhite, METALLIC, Element.Zn).asSolidOre(692);

    /**
     * Dusts
     */


    /**
     * The "Random Material" ones.
     */
    public static Materials AnyBronze = new Materials("AnyBronze", TextureSet.SHINY, false);
    public static Materials AnyCopper = new Materials("AnyCopper", TextureSet.SHINY, false);
    public static Materials AnyIron = new Materials("AnyIron", TextureSet.SHINY, false);
    public static Materials AnyRubber = new Materials("AnyRubber", TextureSet.SHINY, false);
    public static Materials AnySyntheticRubber = new Materials("AnySyntheticRubber", TextureSet.SHINY, false);
    public static Materials Crystal = new Materials("Crystal", TextureSet.SHINY, false);
    public static Materials Quartz = new Materials("Quartz", TextureSet.QUARTZ, false);
    public static Materials BrickNether = new Materials("BrickNether", TextureSet.DULL, false);

    /**
     * The "I don't care" Section, everything I don't want to do anything with right now, is right here. Just to make the Material Finder shut up about them.
     * But I do see potential uses in some of these Materials.
     */
    public static Materials PhasedIron = new Materials("Phased Iron").asSolid();
    public static Materials PhasedGold = new Materials("Phases Gold").asSolid();
    public static Materials Soularium = new Materials("Soularium").asDust();
    public static Materials Endium = new Materials(770, "Endium", 165, 220, 250, dyeYellow, DULL).asSolid();
    public static Materials DarkSteel = new Materials(364, "Dark Steel", 80, 70, 80, dyePurple, DULL).asSolidOre();
    public static Materials ConductiveIron = new Materials("Conductive Iron").asSolid();
    public static Materials ElectricalSteel = new Materials("Electrical Steel").asSolid();
    public static Materials EnergeticAlloy = new Materials("Energetic Alloy").asSolid();
    public static Materials VibrantAlloy = new Materials("Vibrant Alloy").asSolid();
    public static Materials PulsatingIron = new Materials("Pulsating Iron").asSolid();
    public static Materials Fluix = new Materials("Fluix").asGem(false);
    public static Materials Ender = new Materials("Ender").asDust();
    public static Materials IridiumSodiumOxide = new Materials(-1, "IridiumSodiumOxide", 255, 255, 255, dyeNULL, NONE).asDust();
    public static Materials PlatinumGroupSludge = new Materials(241, "PlatinumGroupSludge", 0, 30, 0, dyeNULL, POWDER).asDust();

    /**
     * Unknown Material Components. Dead End Section.
     */
    public static Materials Amber = new Materials(514, "Amber", 255, 128, 0, 127, dyeOrange, RUBY).asGemOre(true).addTools(4.0F, 128, 2);
    public static Materials Ardite = new Materials(-1, "Ardite", 255, 0, 0, dyeYellow, NONE).asSolidOre().addTools(6.0F, 64, 2);
    public static Materials Black = new Materials(-1, "Black", 0, 0, 0, dyeBlack, NONE);
    public static Materials CertusQuartz = new Materials(516, "Certus Quartz", 210, 210, 230, dyeLightGray, QUARTZ).asGemOre(false).addTools(5.0F, 32, 1);
    public static Materials ConstructionFoam = new Materials(854, "ConstructionFoam", 128, 128, 128, dyeGray, DULL).asDust().addFlags(CELL);
    public static Materials Desh = new Materials(884, "Desh", 40, 40, 40, dyeBlack, DULL).asSolidOre().addFlags(GEAR).addTools(1.0F, 1280, 3);
    public static Materials Dilithium = new Materials(515, "Dilithium", 255, 250, 250, 127, dyeWhite, DIAMOND).asGem(true).addFlags(CELL);
    public static Materials Duranium = new Materials(328, "Duranium", 255, 255, 255, dyeLightGray, METALLIC).asSolid().addTools(16.0F, 5120, 5);
    public static Materials Enderium = new Materials(321, "Enderium", 89, 145, 135, dyeGreen, DULL).asSolid(3000, 3000).addTools(8.0F, 256, 3);
    public static Materials Firestone = new Materials(347, "Firestone", 200, 20, 0, dyeRed, QUARTZ).asGemOre(false).addTools(6.0F, 1280, 3);
    public static Materials FoolsRuby = new Materials(512, "Ruby", 255, 100, 100, 127, dyeRed, RUBY).asGemOre(true);
    public static Materials Glowstone = new Materials(811, "Glowstone", 255, 255, 0, dyeYellow, SHINY).asDust().addFlags(CELL);
    public static Materials Graphite = new Materials(865, "Graphite", 128, 128, 128, dyeGray, DULL).asDustOre().addFlags(CELL).addTools(5.0F, 32, 2);
    public static Materials Graphene = new Materials(819, "Graphene", 128, 128, 128, dyeGray, DULL).asDust();
    public static Materials InfusedGold = new Materials(323, "Infused Gold", 255, 200, 60, dyeYellow, SHINY).asSolidOre().addFlags(GEAR).addTools(12.0F, 64, 3);
    public static Materials InfusedAir = new Materials(540, "Infused Air", 255, 255, 0, dyeYellow, SHARDS).asGemOre(true).addFlags(GEAR).addTools(8.0F, 64, 3);
    public static Materials InfusedFire = new Materials(541, "Infused Fire", 255, 0, 0, dyeRed, SHARDS).asGemOre(true).addFlags(GEAR).addTools(8.0F, 64, 3);
    public static Materials InfusedEarth = new Materials(542, "Infused Earth", 0, 255, 0, dyeGreen, SHARDS).asGemOre(true).addFlags(GEAR).addTools(8.0F, 256, 3);
    public static Materials InfusedWater = new Materials(543, "Infused Water", 0, 0, 255, dyeYellow, SHARDS).asGemOre(true).addFlags(GEAR).addTools(8.0F, 64, 3);
    public static Materials InfusedEntropy = new Materials(544, "Infused Entropy", 62, 62, 62, dyeBlack, SHARDS).asGemOre(true).addFlags(GEAR).addTools(32.0F, 64, 4);
    public static Materials InfusedOrder = new Materials(545, "Infused Order", 252, 252, 252, dyeWhite, SHARDS).asGemOre(true).addFlags(GEAR).addTools(8.0F, 64, 3);
    public static Materials InfusedVis = new Materials(-1, "Infused Vis", 255, 0, 255, dyePurple, SHARDS).asGemOre(true).addFlags(GEAR).addTools(8.0F, 64, 3);
    public static Materials InfusedDull = new Materials(-1, "Infused Dull", 100, 100, 100, dyeLightGray, SHARDS).asGemOre(true).addFlags(GEAR).addTools(32.0F, 64, 3);
    public static Materials Jasper = new Materials(511, "Jasper", 200, 80, 80, 100, dyeRed, EMERALD).asGem(true);
    public static Materials Lava = new Materials(700, "Lava", 255, 64, 0, dyeOrange, FLUID).asFluid();
    public static Materials MeteoricIron = new Materials(340, "Meteoric Iron", 100, 50, 80, dyeGray, METALLIC).asSolidOre(1811).addTools(6.0F, 384, 2);
    public static Materials MeteoricSteel = new Materials(341, "Meteoric Steel", 50, 25, 40, dyeGray, METALLIC).asSolid(1811, 1000).addTools(6.0F, 768, 2);
    public static Materials Naquadah = new Materials(324, "Naquadah", 50, 50, 50, dyeBlack, METALLIC).asSolidOre(5400, 5400).addFlags(CELL).addTools(6.0F, 1280, 4);
    public static Materials NaquadahAlloy = new Materials(325, "Naquadah Alloy", 40, 40, 40, dyeBlack, METALLIC).asSolid(7200, 7200).addFlags(GEAR).addTools(8.0F, 5120, 5);
    public static Materials NaquadahEnriched = new Materials(326, "Naquadah Enriched", 50, 50, 50, dyeBlack, METALLIC).asSolidOre(4500, 4500).addFlags(CELL).addTools(6.0F, 1280, 4);
    public static Materials Naquadria = new Materials(327, "Naqiadria", 30, 30, 30, dyeBlack, SHINY).asSolidOre(9000, 9000).addFlags(CELL).addTools(1.0F, 512, 4);
    public static Materials Nether = new Materials("Nether").asRef();
    public static Materials NetherBrick = new Materials(814, "Nether Brick", 100, 0, 0, dyeRed, DULL).asDust();
    public static Materials NetherQuartz = new Materials(522, "Nether Quartz", 230, 210, 210, dyeWhite, QUARTZ).asGemOre(false).addTools(1.0F, 32, 1);
    public static Materials NetherStar = new Materials(506, "Nether Star", 255, 255, 255, dyeWhite, NETHERSTAR).asGem(false).addTools(1.0F, 5120, 4);
    public static Materials Oilsands = new Materials(878, "Oilsands", 10, 10, 10, dyeNULL, NONE).asDustOre();
    public static Materials Quartzite = new Materials(523, "Quartzite", 210, 230, 210, dyeWhite, QUARTZ).asGemOre(false).addMats(new MaterialStack(Silicon, 1), new MaterialStack(Oxygen, 2));
    public static Materials Sand = new Materials("Sand").asRef();
    public static Materials Tritanium = new Materials(329, "Tritanium", 255, 255, 255, dyeWhite, METALLIC).asSolid().addTools(20.0F, 10240, 6);
    public static Materials UUAmplifier = new Materials(721, "UU-Amplifier", 96, 0, 128, dyePink, FLUID).asFluid();
    public static Materials UUMatter = new Materials(703, "UU-Matter", 128, 0, 196, dyePink, FLUID).asFluid();

    /**
     * Circuitry, Batteries and other Technical things
     */
    public static Materials Primitive = new Materials("Primitive").asRef();
    public static Materials Basic = new Materials("Basic").asRef();
    public static Materials Good = new Materials("Good").asRef();
    public static Materials Advanced = new Materials("Advanced").asRef();
    public static Materials Data = new Materials("Data").asRef();
    public static Materials Elite = new Materials("Elite").asRef();
    public static Materials Master = new Materials("Master").asRef();
    public static Materials Ultimate = new Materials("Ultimate").asRef();
    public static Materials Superconductor = new Materials("Superconductor").asRef();
    public static Materials Infinite = new Materials("Infinite").asRef();

    /**
     * Not possible to determine exact Components
     */
    public static Materials Antimatter = new Materials(-1, "Antimatter", 255, 255, 255, dyePink, FLUID).asFluid();
    public static Materials Biomass = new Materials(704, "Biomass", 0, 255, 0, dyeGreen, FLUID).asSemi(8);
    public static Materials CharcoalByproducts = new Materials(675, "Charcoal Byproducts", 120, 68, 33, dyeBrown, FLUID).asFluid();
    public static Materials Creosote = new Materials(712, "Creosote", 128, 64, 0, dyeBrown, FLUID).asSemi(8);
    public static Materials Ethanol = new Materials(706, "Ethanol", 255, 128, 0, dyeOrange, FLUID).asFluid(148);
    public static Materials FermentedBiomass = new Materials(691, "Fermented Biomass", 68, 85, 0, dyeBrown, FLUID).asFluid();
    public static Materials FishOil = new Materials(711, "Fish Oil", 255, 196, 0, dyeYellow, FLUID).asSemi(2);
    public static Materials Fuel = new Materials(708, "Diesel", 255, 255, 0, dyeYellow, FLUID).asFluid(128);
    public static Materials Glue = new Materials(726, "Glue", 200, 196, 0, dyeOrange, FLUID).asFluid();
    public static Materials Gunpowder = new Materials(800, "Gunpower", 128, 128, 128, dyeGray, DULL).asDust();
    public static Materials Honey = new Materials(725, "Honey", 210, 200, 0, dyeYellow, FLUID).asFluid();
    public static Materials Leather = new Materials(-1, "Leather", 150, 150, 80, 127, dyeOrange, ROUGH);
    public static Materials Lubricant = new Materials(724, "Lubricant", 255, 196, 0, dyeOrange, FLUID).asFluid();
    public static Materials Milk = new Materials(885, "Milk", 254, 254, 254, dyeWhite, FINE).asFluid().asDust();
    public static Materials Oil = new Materials(707, "Oil", 10, 10, 10, dyeBrown, FLUID).asSemi(16);
    public static Materials Paper = new Materials(879, "Paper", 250, 250, 250, dyeWhite, PAPER).asDust();
    public static Materials RareEarth = new Materials(891, "Rare Earth", 128, 128, 100, dyeGray, FINE).asDust();
    public static Materials Reinforced = new Materials(-1, "Reinforced", 255, 255, 255, dyeGray, NONE);
    public static Materials SeedOil = new Materials(713, "Seed Oil", 196, 255, 0, dyeLime, FLUID).asSemi(2);
    public static Materials SeedOilHemp = new Materials(722, "Hemp Seed Oil", 196, 255, 0, dyeLime, FLUID).asSemi(2);
    public static Materials SeedOilLin = new Materials(723, "Lin Seed Oil", 196, 255, 0, dyeLime, FLUID).asSemi(2);
    public static Materials Stone = new Materials(299, "Stone", 205, 205, 205, dyeLightGray, ROUGH).asDust().addTools(4.0F, 32, 1);
    public static Materials Wheat = new Materials(881, "Wheat", 255, 255, 196, dyeYellow, POWDER).asDust();
    public static Materials WoodGas = new Materials(660, "Wood Gas", 222, 205, 135, dyeBrown, FLUID).asGas(24);
    public static Materials WoodTar = new Materials(662, "Wood Tar", 40, 23, 11, dyeBrown, FLUID).asFluid();
    public static Materials WoodVinegar = new Materials(661, "Wood Vinegar", 212, 85, 0, dyeBrown, FLUID).asFluid();

    /**
     * TODO: This
     */
    public static Materials AluminiumBrass = new Materials(-1, "Aluminium Brass", 255, 255, 255, dyeYellow, METALLIC).asSolid().addTools(6.0F, 64, 2);
    public static Materials Osmiridium = new Materials(317, "Osmiridium", 100, 100, 255, dyeLightBlue, METALLIC).asSolid(3333, 2500).addFlags(GEAR).addTools(7.0F, 1600, 3);
    public static Materials Endstone = new Materials(808, "Endstone", 255, 255, 255, dyeYellow, DULL).asDust();
    public static Materials Netherrack = new Materials(807, "Netherrack", 200, 0, 0, dyeRed, DULL).asDust();
    public static Materials SoulSand = new Materials(-1, "Soulsand", 255, 255, 255, dyeBrown, DULL).asDust();

    /**
     * First Degree Compounds
     */
    public static Materials Methane = new Materials(715, "Methane", 255, 255, 255, dyeMagenta, FLUID).asGas(104).addMats(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 4));
    public static Materials CarbonDioxide = new Materials(497, "Carbon Dioxide", 169, 208, 245, 240, dyeLightBlue, FLUID).asGas().addFlags(CELL, PLASMA).addMats(new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 2));
    public static Materials NobleGases = new Materials(496, "Noble Gases", 169, 208, 245, 240, dyeLightBlue, FLUID).asGas().addFlags(CELL, PLASMA).setTemp(79, 0).addMats(new MaterialStack(CarbonDioxide, 21), new MaterialStack(Helium, 9), new MaterialStack(Methane, 3), new MaterialStack(Deuterium, 1));
    public static Materials Air = new Materials(-1, "Air", 169, 208, 245, 240, dyeLightBlue, FLUID).asFluid().addFlags(CELL, PLASMA).addMats(new MaterialStack(Nitrogen, 40), new MaterialStack(Oxygen, 11), new MaterialStack(Argon, 1), new MaterialStack(NobleGases, 1));
    public static Materials LiquidAir = new Materials(495, "Liquid Air", 169, 208, 245, 240, dyeLightBlue, FLUID).asFluid().addFlags(CELL, PLASMA).setTemp(79, 0).addMats(new MaterialStack(Nitrogen, 40), new MaterialStack(Oxygen, 11), new MaterialStack(Argon, 1), new MaterialStack(NobleGases, 1));
    public static Materials Almandine = new Materials(820, "Almandine", 255, 0, 0, dyeRed, ROUGH).asDustOre().addMats(new MaterialStack(Aluminium, 2), new MaterialStack(Iron, 3), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12));
    public static Materials Andradite = new Materials(821, "Andradite", 150, 120, 0, dyeYellow, ROUGH).asDust().addMats(new MaterialStack(Calcium, 3), new MaterialStack(Iron, 2), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12));
    public static Materials AnnealedCopper = new Materials(345, "Annealed Copper", 255, 120, 20, dyeOrange, SHINY).asSolidOre().addFlags(GEAR).addMats(new MaterialStack(Copper, 1));
    public static Materials Asbestos = new Materials(946, "Asbestos", 230, 230, 230, dyeWhite, DULL).asDust().addMats(new MaterialStack(Magnesium, 3), new MaterialStack(Silicon, 2), new MaterialStack(Hydrogen, 4), new MaterialStack(Oxygen, 9));
    public static Materials Ash = new Materials(815, "Ash", 150, 150, 150, dyeLightGray, DULL).asDust();
    public static Materials BandedIron = new Materials(917, "Banded Iron", 145, 90, 90, dyeBrown, DULL).asDustOre().addMats(new MaterialStack(Iron, 2), new MaterialStack(Oxygen, 3));
    public static Materials BatteryAlloy = new Materials(315, "Battery Alloy", 156, 124, 160, dyePurple, DULL).asSolid().addMats(new MaterialStack(Lead, 4), new MaterialStack(Antimony, 1));
    public static Materials BlueTopaz = new Materials(513, "Blue Topaz", 0, 0, 255, 127, dyeBlue, GEMH).asGemOre(true).addTools(7.0F, 256, 3).addMats(new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 1), new MaterialStack(Fluorine, 2), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 6));
    public static Materials Bone = new Materials(806, "Bone", 250, 250, 250, dyeWhite, DULL).asDust().addMats(new MaterialStack(Calcium, 1));
    public static Materials Brass = new Materials(301, "Brass", 255, 180, 0, dyeYellow, METALLIC).asSolid().addFlags(GEAR).addTools(7.0F, 96, 1).addMats(new MaterialStack(Zinc, 1), new MaterialStack(Copper, 3));
    public static Materials Bronze = new Materials(300, "Bronze", 255, 128, 0, dyeOrange, METALLIC).asSolid().addFlags(GEAR).addTools(6.0F, 192, 2).addMats(new MaterialStack(Tin, 1), new MaterialStack(Copper, 3));
    public static Materials BrownLimonite = new Materials(930, "Brown Limonite", 200, 100, 0, dyeBrown, METALLIC).asDustOre().addMats(new MaterialStack(Iron, 1), new MaterialStack(Hydrogen, 1), new MaterialStack(Oxygen, 2));
    public static Materials Calcite = new Materials(823, "Calcite", 250, 230, 220, dyeOrange, DULL).asDustOre().addMats(new MaterialStack(Calcium, 1), new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 3));
    public static Materials Cassiterite = new Materials(824, "Cassiterite", 220, 220, 220, dyeWhite, METALLIC).addFlags(ORE).addMats(new MaterialStack(Tin, 1), new MaterialStack(Oxygen, 2));
    public static Materials Chalcopyrite = new Materials(855, "Chalcopyrite", 160, 120, 40, dyeYellow, DULL).asDustOre().addMats(new MaterialStack(Copper, 1), new MaterialStack(Iron, 1), new MaterialStack(Sulfur, 2));
    public static Materials Charcoal = new Materials(536, "Charcoal", 100, 70, 70, dyeBlack, FINE).asGem(false).addMats(new MaterialStack(Carbon, 1));
    public static Materials Chromite = new Materials(825, "Chromite", 30, 20, 15, dyePink, METALLIC).asDust(1700, 1700).addMats(new MaterialStack(Iron, 1), new MaterialStack(Chrome, 2), new MaterialStack(Oxygen, 4));
    public static Materials Cinnabar = new Materials(826, "Cinnabar", 150, 0, 0, dyeBrown, ROUGH).asDustOre().addMats(new MaterialStack(Mercury, 1), new MaterialStack(Sulfur, 1));
    public static Materials Water = new Materials(701, "Water", 0, 0, 255, dyeBlue, FLUID).asFluid().addMats(new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1));
    public static Materials Clay = new Materials(805, "Clay", 200, 200, 220, dyeLightBlue, ROUGH).asDust().addMats(new MaterialStack(Sodium, 2), new MaterialStack(Lithium, 1), new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 2), new MaterialStack(Water, 6));
    public static Materials Coal = new Materials(535, "Coal", 70, 70, 70, dyeBlack, ROUGH).asGemOre(false).addMats(new MaterialStack(Carbon, 1));
    public static Materials Cobaltite = new Materials(827, "Cobaltite", 80, 80, 250, dyeBlue, METALLIC).asDustOre().addMats(new MaterialStack(Cobalt, 1), new MaterialStack(Arsenic, 1), new MaterialStack(Sulfur, 1));
    public static Materials Cooperite = new Materials(828, "Cooperite", 255, 255, 200, dyeYellow, METALLIC).asDustOre().addMats(new MaterialStack(Platinum, 3), new MaterialStack(Nickel, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Palladium, 1));
    public static Materials Cupronickel = new Materials(310, "Cupronickel", 227, 150, 128, dyeOrange, METALLIC).asSolid().addTools(6.0F, 64, 1).addMats(new MaterialStack(Copper, 1), new MaterialStack(Nickel, 1));
    public static Materials DarkAsh = new Materials(816, "Dark Ash", 50, 50, 50, dyeGray, DULL).asDust();
    public static Materials Diamond = new Materials(500, "Diamond", 200, 255, 255, 127, dyeWhite, DIAMOND).asGemOre(true).addFlags(GEAR).addTools(8.0F, 1280, 3).addMats(new MaterialStack(Carbon, 1));
    public static Materials Electrum = new Materials(303, "Electrum", 255, 255, 100, dyeYellow, SHINY).asSolid().addFlags(GEAR).addTools(12.0F, 64, 2).addMats(new MaterialStack(Silver, 1), new MaterialStack(Gold, 1));
    public static Materials Emerald = new Materials(501, "Emerald", 80, 255, 80, 127, dyeGreen, EMERALD).asGemOre(true).addTools(7.0F, 256, 2).addMats(new MaterialStack(Silver, 1), new MaterialStack(Gold, 1));
    public static Materials Galena = new Materials(830, "Galena", 100, 60, 100, dyePurple, DULL).asDustOre().addMats(new MaterialStack(Lead, 3), new MaterialStack(Silver, 3), new MaterialStack(Sulfur, 2));
    public static Materials Garnierite = new Materials(906, "Garnierite", 50, 200, 70, dyeLightBlue, METALLIC).asDustOre().addMats(new MaterialStack(Nickel, 1), new MaterialStack(Oxygen, 1));
    public static Materials Glyceryl = new Materials(714, "Glyceryl", 0, 150, 150, dyeCyan, FLUID).asFluid().addMats(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 5), new MaterialStack(Nitrogen, 3), new MaterialStack(Oxygen, 9));
    public static Materials GreenSapphire = new Materials(504, "Green Sapphire", 100, 200, 130, 127, dyeCyan, GEMH).asGemOre(true).addTools(7.0F, 256, 2).addMats(new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 3));
    public static Materials Grossular = new Materials(831, "Grossular", 200, 100, 0, dyeOrange, ROUGH).asDustOre().addMats(new MaterialStack(Calcium, 3), new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12));
    public static Materials Ice = new Materials(702, "Ice", 200, 200, 255, dyeBlue, SHINY).asDust().addFlags(CELL).addMats(new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1));
    public static Materials Ilmenite = new Materials(918, "Ilmenite", 70, 55, 50, dyePurple, METALLIC).asDustOre().addMats(new MaterialStack(Iron, 1), new MaterialStack(Titanium, 1), new MaterialStack(Oxygen, 3));
    public static Materials Rutile = new Materials(375, "Rutile", 212, 13, 92, dyeRed, GEMH).asDust().addMats(new MaterialStack(Titanium, 1), new MaterialStack(Oxygen, 2));
    public static Materials Bauxite = new Materials(822, "Bauxite", 200, 100, 0, dyeBrown, DULL).asDustOre().addMats(new MaterialStack(Rutile, 2), new MaterialStack(Aluminium, 16), new MaterialStack(Hydrogen, 10), new MaterialStack(Oxygen, 11));
    public static Materials Titaniumtetrachloride = new Materials(376, "Titaniumtetrachloride", 212, 13, 92, dyeRed, FLUID).asFluid().addMats(new MaterialStack(Titanium, 1), new MaterialStack(Chlorine, 4));
    public static Materials Magnesiumchloride = new Materials(377, "Magnesiumchloride", 212, 13, 92, dyeRed, DULL).asDust().addFlags(CELL).addMats(new MaterialStack(Magnesium, 1), new MaterialStack(Chlorine, 2));
    public static Materials Invar = new Materials(302, "Invar", 180, 180, 120, dyeBrown, METALLIC).asSolid().addFlags(GEAR).addTools(6.0F, 256, 2).addMats(new MaterialStack(Iron, 2), new MaterialStack(Nickel, 1));
    public static Materials Kanthal = new Materials(312, "Kanthalm", 194, 210, 223, dyeYellow, METALLIC).asSolid(1800, 1800).addTools(6.0F, 64, 2).addMats(new MaterialStack(Iron, 1), new MaterialStack(Aluminium, 1), new MaterialStack(Chrome, 1));
    public static Materials Lazurite = new Materials(524, "Lazurite", 100, 120, 255, dyeCyan, LAPIS).asGemOre(false).addMats(new MaterialStack(Aluminium, 6), new MaterialStack(Silicon, 6), new MaterialStack(Calcium, 8), new MaterialStack(Sodium, 8));
    public static Materials Magnalium = new Materials(313, "Magnalium", 200, 190, 255, dyeLightBlue, DULL).asSolid().addFlags(GEAR).addTools(6.0F, 256, 2).addMats(new MaterialStack(Magnesium, 1), new MaterialStack(Aluminium, 2));
    public static Materials Magnesite = new Materials(908, "Magnesite", 250, 250, 180, dyePink, METALLIC).asDustOre().addMats(new MaterialStack(Magnesium, 1), new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 3));
    public static Materials Magnetite = new Materials(870, "Magnetite", 30, 30, 30, dyeGray, METALLIC).asDustOre().addMats(new MaterialStack(Iron, 3), new MaterialStack(Oxygen, 4));
    public static Materials Molybdenite = new Materials(942, "Molybdenite", 25, 25, 25, dyeBlue, METALLIC).asDustOre().addMats(new MaterialStack(Molybdenum, 1), new MaterialStack(Sulfur, 2));
    public static Materials Nichrome = new Materials(311, "Nichrome", 205, 206, 246, dyeRed, METALLIC).asSolid(2700, 2700).addTools(6.0F, 64, 2).addMats(new MaterialStack(Nickel, 4), new MaterialStack(Chrome, 1));
    public static Materials NiobiumTitanium = new Materials(360, "Niobium Titanium", 29, 29, 41, dyeBlack, DULL).asSolid(4500, 4500).addMats(new MaterialStack(Nickel, 4), new MaterialStack(Chrome, 1));
    public static Materials NitrogenDioxide = new Materials(717, "Nitrogen Dioxide", 100, 175, 255, dyeCyan, FLUID).asGas().addMats(new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 2));
    public static Materials Obsidian = new Materials(804, "Obsidian", 80, 50, 100, dyeBlack, DULL).asDust().addMats(new MaterialStack(Magnesium, 1), new MaterialStack(Iron, 1), new MaterialStack(Silicon, 2), new MaterialStack(Oxygen, 8));
    public static Materials Phosphate = new Materials(833, "Phosphate", 255, 255, 0, dyeYellow, DULL).asDustOre().addFlags(CELL).addMats(new MaterialStack(Phosphor, 1), new MaterialStack(Oxygen, 4));
    public static Materials PigIron = new Materials(307, "Pig Iron", 255, 255, 0, dyeYellow, DULL).asDustOre().addFlags(CELL).addMats(new MaterialStack(Phosphor, 1), new MaterialStack(Oxygen, 4));
    public static Materials Plastic = new Materials(874, "Plastic", 200, 200, 200, dyeWhite, DULL).asSolid().addFlags(GEAR).addMats(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 2));
    public static Materials Epoxid = new Materials(470, "Epoxid", 200, 140, 20, dyeWhite, DULL).asSolid(400).addFlags(GEAR).addTools(3.0F, 32, 1).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 4), new MaterialStack(Oxygen, 1));
    public static Materials Polydimethylsiloxane = new Materials(633, "Polydimethylsiloxane", 245, 245, 245, dyeWhite, FLUID).asDust().addFlags(ELEC).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 1), new MaterialStack(Silicon, 1));
    public static Materials Silicone = new Materials(471, "Silicone", 220, 220, 220, dyeWhite, DULL).asSolid(900).addFlags(GEAR).addTools(3.0F, 128, 1).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 1), new MaterialStack(Silicon, 1));
    public static Materials Polycaprolactam = new Materials(472, "Polycaprolactam", 50, 50, 50, dyeWhite, DULL).asSolid(500).addFlags(GEAR).addTools(3.0F, 32, 1).addMats(new MaterialStack(Carbon, 6), new MaterialStack(Hydrogen, 11), new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 1));
    public static Materials Polytetrafluoroethylene = new Materials(473, "Polytetrafluoroethylene", 100, 100, 100, dyeWhite, DULL).asSolid(1400).addFlags(GEAR).addTools(3.0F, 32, 1).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Fluorine, 4));
    public static Materials Powellite = new Materials(883, "Powellite", 255, 255, 0, dyeYellow, DULL).asDustOre().addMats(new MaterialStack(Calcium, 1), new MaterialStack(Molybdenum, 1), new MaterialStack(Oxygen, 4));
    public static Materials Pyrite = new Materials(834, "Pyrite", 150, 120, 40, dyeOrange, ROUGH).asDustOre().addMats(new MaterialStack(Iron, 1), new MaterialStack(Sulfur, 2));
    public static Materials Pyrolusite = new Materials(943, "Pyrolusite", 150, 150, 170, dyeLightGray, DULL).asDustOre().addMats(new MaterialStack(Manganese, 1), new MaterialStack(Oxygen, 2));
    public static Materials Pyrope = new Materials(835, "Pyrope", 120, 50, 100, dyePurple, METALLIC).asDustOre().addMats(new MaterialStack(Aluminium, 2), new MaterialStack(Magnesium, 3), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12));
    public static Materials RockSalt = new Materials(944, "Rock Salt", 240, 200, 200, dyeWhite, FINE).asDustOre().addMats(new MaterialStack(Potassium, 1), new MaterialStack(Chlorine, 1));
    public static Materials Rubber = new Materials(880, "Rubber", 0, 0, 0, dyeBlack, SHINY).asSolid().addFlags(GEAR).addTools(1.5F, 32, 0).addMats(new MaterialStack(Carbon, 5), new MaterialStack(Hydrogen, 8));
    public static Materials RawRubber = new Materials(896, "Raw Rubber", 0, 0, 400, dyeWhite, DULL).asDust().addMats(new MaterialStack(Carbon, 5), new MaterialStack(Hydrogen, 8));
    public static Materials Ruby = new Materials(502, "Ruby", 255, 100, 100, 127, dyeRed, RUBY).asGemOre(true).addTools(7.0F, 256, 2);
    public static Materials Salt = new Materials(817, "Salt", 250, 250, 250, dyeWhite, FINE).asDustOre().addMats(new MaterialStack(Sodium, 1), new MaterialStack(Chlorine, 1));
    public static Materials Saltpeter = new Materials(836, "Saltpeter", 230, 230, 230, dyeWhite, FINE).asDustOre().addMats(new MaterialStack(Potassium, 1), new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 3));
    public static Materials SaltWater = new Materials(692, "Salt Water", 0, 0, 200, dyeBlue, FLUID).asFluid();
    public static Materials Sapphire = new Materials(503, "Sapphire", 100, 100, 200, 127, dyeBlue, GEMV).asGemOre(true).addTools(7.0F, 256, 2).addMats(new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 3));
    public static Materials Scheelite = new Materials(910, "Scheelite", 200, 140, 20, dyeBlack, DULL).asDustOre(2500, 2500).addMats(new MaterialStack(Tungsten, 1), new MaterialStack(Calcium, 2), new MaterialStack(Oxygen, 4));
    public static Materials SiliconDioxide = new Materials(837, "SiliconDioxide", 200, 200, 200, dyeLightGray, QUARTZ).asDust().addFlags(CELL).addMats(new MaterialStack(Silicon, 1), new MaterialStack(Oxygen, 2));
    public static Materials Snow = new Materials(728, "Snow", 250, 250, 250, dyeWhite, FINE).asDust().addFlags(CELL).addMats(new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1));
    public static Materials Sodalite = new Materials(525, "Sodalite", 20, 20, 255, dyeBlue, LAPIS).asGemOre(false).addMats(new MaterialStack(Aluminium, 3), new MaterialStack(Silicon, 3), new MaterialStack(Sodium, 4), new MaterialStack(Chlorine, 1));
    public static Materials SodiumPersulfate = new Materials(718, "SodiumPersulfate", 255, 255, 255, dyeOrange, FLUID).asFluid().addMats(new MaterialStack(Sodium, 2), new MaterialStack(Sulfur, 2), new MaterialStack(Oxygen, 8));
    public static Materials SodiumSulfide = new Materials(719, "SodiumSulfide", 255, 230, 128, dyeOrange, FLUID).asFluid().addMats(new MaterialStack(Sodium, 2), new MaterialStack(Sulfur, 1));
    public static Materials HydricSulfide = new Materials(460, "HydricSulfide", 255, 255, 255, dyeOrange, FLUID).asFluid().addMats(new MaterialStack(Hydrogen, 2), new MaterialStack(Sulfur, 1));

    public static Materials OilHeavy = new Materials(730, "Heavy Oil", 10, 10, 10, dyeBlack, FLUID).asSemi(32);
    public static Materials OilMedium = new Materials(731, "Raw Oil", 10, 10, 10, dyeBlack, FLUID).asSemi(24);
    public static Materials OilLight = new Materials(732, "Light Oil", 10, 10, 10, dyeBlack, FLUID).asSemi(16);

    public static Materials NaturalGas = new Materials(733, "Natural Gas", 255, 255, 255, dyeWhite, FLUID).asGas(15);
    public static Materials SulfuricGas = new Materials(734, "Sulfuric Gas", 255, 255, 255, dyeWhite, FLUID).asGas(20);
    public static Materials Gas = new Materials(735, "Refinery Gas", 255, 255, 255, dyeWhite, FLUID).asGas(128).addFlags(CRACK);
    public static Materials SulfuricNaphtha = new Materials(736, "Sulfuric Naphtha", 255, 255, 0, dyeYellow, FLUID).asGas(32);
    public static Materials SulfuricLightFuel = new Materials(737, "Sulfuric Light Fuel", 255, 255, 0, dyeYellow, FLUID).asFluid(32);
    public static Materials SulfuricHeavyFuel = new Materials(738, "Sulfuric Heavy Fuel", 255, 255, 0, dyeBlack, FLUID).asSemi(32);
    public static Materials Naphtha = new Materials(739, "Naphtha", 255, 255, 0, dyeYellow, FLUID).asGas(256).addFlags(CRACK);
    public static Materials LightFuel = new Materials(740, "Light Fuel", 255, 255, 0, dyeYellow, FLUID).asFluid(256).addFlags(CRACK);
    public static Materials HeavyFuel = new Materials(741, "Heavy Fuel", 255, 255, 0, dyeBlack, FLUID).asSemi(192).addFlags(CRACK);
    public static Materials LPG = new Materials(742, "LPG", 255, 255, 0, dyeYellow, FLUID).asGas(256);
    public static Materials Magnesia = new Materials(621, "Magnesia", 255, 225, 225, dyeWhite, DULL).asDust().addFlags(ELEC).addMats(new MaterialStack(Magnesium, 1), new MaterialStack(Oxygen, 1));
    public static Materials Quicklime = new Materials(622, "Quicklime", 240, 240, 240, dyeWhite, DULL).asDust().addFlags(ELEC).addMats(new MaterialStack(Calcium, 1), new MaterialStack(Oxygen, 1));
    public static Materials Potash = new Materials(623, "Potash", 120, 66, 55, dyeBrown, DULL).asDust().addFlags(ELEC).addMats(new MaterialStack(Potassium, 2), new MaterialStack(Oxygen, 1));
    public static Materials SodaAsh = new Materials(624, "Soda Ash", 220, 220, 255, dyeWhite, DULL).asDust().addFlags(ELEC).addMats(new MaterialStack(Sodium, 2), new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 3));
    public static Materials Brick = new Materials(625, "Brick", 155, 86, 67, dyeBrown, ROUGH).asDust().addMats(new MaterialStack(Aluminium, 4), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12));
    public static Materials Fireclay = new Materials(626, "Fireclay", 173, 160, 155, dyeBrown, ROUGH).asDust().addMats(new MaterialStack(Brick, 1));
    public static Materials BioDiesel = new Materials(627, "Bio Diesel", 255, 128, 0, dyeOrange, FLUID).asDiesel(192);
    public static Materials NitrationMixture = new Materials(628, "Nitration Mixture", 230, 226, 171, dyeBrown, FLUID).asFluid();
    public static Materials Glycerol = new Materials(629, "Glycerol", 135, 222, 135, dyeLime, FLUID).asSemi(164).addFlags(ELEC).addMats(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 8), new MaterialStack(Oxygen, 3));
    public static Materials SodiumBisulfate = new Materials(630, "Sodium Bisulfate", 0, 68, 85, dyeBlue, FLUID).asDust().addMats(new MaterialStack(Sodium, 1), new MaterialStack(Hydrogen, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4));
    public static Materials PolyphenyleneSulfide = new Materials(631, "PolyphenyleneSulfide", 170, 136, 0, dyeBrown, DULL).asSolid().addTools(3.0F, 32, 1).addMats(new MaterialStack(Carbon, 6), new MaterialStack(Hydrogen, 4), new MaterialStack(Sulfur, 1));
    public static Materials Dichlorobenzene = new Materials(632, "Dichlorobenzene", 0, 68, 85, dyeBlue, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Carbon, 6), new MaterialStack(Hydrogen, 4), new MaterialStack(Chlorine, 2));
    public static Materials Polystyrene = new Materials(636, "Polystyrene", 190, 180, 170, dyeLightGray, DULL).asSolid().addTools(3.0F, 32, 1).addMats(new MaterialStack(Carbon, 8), new MaterialStack(Hydrogen, 8));
    public static Materials Styrene = new Materials(637, "Styrene", 210, 200, 190, dyeBlack, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Carbon, 8), new MaterialStack(Hydrogen, 8));
    public static Materials Isoprene = new Materials(638, "Isoprene", 20, 20, 20, dyeBlack, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Carbon, 8), new MaterialStack(Hydrogen, 8));
    public static Materials Tetranitromethane = new Materials(639, "Tetranitromethane", 15, 40, 40, dyeBlack, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Carbon, 1), new MaterialStack(Nitrogen, 4), new MaterialStack(Oxygen, 8));
    public static Materials Ethenone = new Materials(641, "Ethenone", 20, 20, 70, dyeBlack, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1));
    public static Materials Ethane = new Materials(642, "Ethane", 200, 200, 255, dyeLightBlue, FLUID).asGas(168).addFlags(CRACK).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 6));
    public static Materials Propane = new Materials(643, "Propane", 250, 226, 80, dyeYellow, FLUID).asGas(232).addFlags(CRACK).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 6));
    public static Materials Butane = new Materials(644, "Butane", 182, 55, 30, dyeOrange, FLUID).asGas(296).addFlags(CRACK).addMats(new MaterialStack(Carbon, 4), new MaterialStack(Hydrogen, 10));
    public static Materials Butene = new Materials(645, "Butene", 207, 80, 5, dyeOrange, FLUID).asGas(256).addFlags(CRACK).addMats(new MaterialStack(Carbon, 4), new MaterialStack(Hydrogen, 8));
    public static Materials Butadiene = new Materials(646, "Butadiene", 232, 105, 0, dyeGray, FLUID).asGas(206).addMats(new MaterialStack(Carbon, 4), new MaterialStack(Hydrogen, 6));
    public static Materials RawStyreneButadieneRubber = new Materials(634, "Raw Styrene-Butadiene Rubber", 84, 64, 61, dyeGray, SHINY).asDust().addMats(new MaterialStack(Styrene, 1), new MaterialStack(Butadiene, 3));
    public static Materials StyreneButadieneRubber = new Materials(635, "Styrene-Butadiene Rubber", 33, 26, 24, dyeBlack, SHINY).asSolid().addTools(3.0F, 128, 1).addMats(new MaterialStack(Styrene, 1), new MaterialStack(Butadiene, 3));
    public static Materials Toluene = new Materials(647, "Toluene", 80, 29, 5, dyeBrown, FLUID).asGas(328).addFlags(ELEC).addMats(new MaterialStack(Carbon, 7), new MaterialStack(Hydrogen, 8));
    public static Materials Epichlorohydrin = new Materials(648, "Epichlorohydrin", 80, 29, 5, dyeBrown, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 5), new MaterialStack(Chlorine, 1), new MaterialStack(Oxygen, 1));
    public static Materials PolyvinylChloride = new Materials(649, "Polyvinyl Chloride", 215, 230, 230, dyeLightGray, FLUID).asSolid().addTools(3.0F, 32, 1).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 3), new MaterialStack(Chlorine, 1));
    public static Materials VinylChloride = new Materials(650, "Vinyl Chloride", 255, 240, 240, dyeLightGray, FLUID).asGas().addFlags(ELEC).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 3), new MaterialStack(Chlorine, 1));
    public static Materials SulfurDioxide = new Materials(651, "Sulfur Dioxide", 200, 200, 25, dyeYellow, FLUID).asGas().addFlags(ELEC).addMats(new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 2));
    public static Materials SulfurTrioxide = new Materials(652, "Sulfur Trioxide", 160, 160, 20, dyeYellow, FLUID).asGas().addFlags(ELEC).setTemp(344, 1).addMats(new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 3));
    public static Materials NitricAcid = new Materials(653, "Nitric Acid", 230, 226, 171, dyeNULL, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Hydrogen, 1), new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 3));
    public static Materials Dimethylhydrazine = new Materials(654, "1,1-Dimethylhydrazine", 0, 0, 85, dyeBlue, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 8), new MaterialStack(Nitrogen, 2));
    public static Materials Chloramine = new Materials(655, "Chloramine", 63, 159, 128, dyeCyan, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Nitrogen, 1), new MaterialStack(Hydrogen, 2), new MaterialStack(Chlorine, 1));
    public static Materials Dimethylamine = new Materials(656, "Dimethylamine", 85, 68, 105, dyeGray, FLUID).asGas().addFlags(ELEC).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 7), new MaterialStack(Nitrogen, 1));
    public static Materials DinitrogenTetroxide = new Materials(657, "Dinitrogen Tetroxide", 0, 65, 132, dyeBlue, FLUID).asGas().addFlags(ELEC).addMats(new MaterialStack(Nitrogen, 2), new MaterialStack(Oxygen, 4));
    public static Materials NitricOxide = new Materials(658, "Nitric Oxide", 125, 200, 240, dyeCyan, FLUID).asGas().addFlags(ELEC).addMats(new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 1));
    public static Materials Ammonia = new Materials(659, "Ammonia", 63, 52, 128, dyeBlue, FLUID).asGas().addFlags(ELEC).addMats(new MaterialStack(Nitrogen, 1), new MaterialStack(Hydrogen, 3));
    public static Materials Dimethyldichlorosilane = new Materials(663, "Dimethyldichlorosilane", 68, 22, 80, dyePurple, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 6), new MaterialStack(Chlorine, 2), new MaterialStack(Silicon, 1));
    public static Materials Chloromethane = new Materials(664, "Chloromethane", 200, 44, 160, dyeMagenta, FLUID).asGas().addFlags(ELEC).addMats(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 3), new MaterialStack(Chlorine, 1));
    public static Materials PhosphorousPentoxide = new Materials(665, "Phosphorous Pentoxide", 220, 220, 0, dyeYellow, FLUID).asDust().addFlags(CELL, ELEC).addMats(new MaterialStack(Phosphor, 4), new MaterialStack(Oxygen, 10));
    public static Materials Tetrafluoroethylene = new Materials(666, "Tetrafluoroethylene", 125, 125, 125, dyeGray, FLUID).asGas().addFlags(ELEC).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Fluorine, 4));
    public static Materials HydrofluoricAcid = new Materials(667, "Hydrofluoric Acid", 0, 136, 170, dyeLightBlue, FLUID).setName("HydrofluoricAcid_GT5U").asFluid().addFlags(ELEC).addMats(new MaterialStack(Hydrogen, 1), new MaterialStack(Fluorine, 1));
    public static Materials Chloroform = new Materials(668, "Chloroform", 137, 44, 160, dyePurple, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 1), new MaterialStack(Chlorine, 3));
    public static Materials BisphenolA = new Materials(669, "Bisphenol A", 212, 179, 0, dyeBrown, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Carbon, 15), new MaterialStack(Hydrogen, 16), new MaterialStack(Oxygen, 2));
    public static Materials AceticAcid = new Materials(670, "Acetic Acid", 200, 180, 160, dyeWhite, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 4), new MaterialStack(Oxygen, 2));
    public static Materials CalciumAcetate = new Materials(671, "Calcium Acetate", 255, 255, 255, dyeWhite, FLUID).asDust().addFlags(CELL, ELEC).addMats(new MaterialStack(Calcium, 1), new MaterialStack(AceticAcid, 2));
    public static Materials Acetone = new Materials(672, "Acetone", 175, 175, 175, dyeWhite, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 1));
    public static Materials Methanol = new Materials(673, "Methanol", 170, 136, 0, dyeBrown, FLUID).asFluid(84).addFlags(ELEC).addMats(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 4), new MaterialStack(Oxygen, 1));
    public static Materials CarbonMonoxide = new Materials(674, "Carbon Monoxide", 14, 72, 128, dyeBrown, FLUID).asGas(24).addFlags(ELEC).addMats(new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 1));
    public static Materials MetalMixture = new Materials(676, "Metal Mixture", 80, 45, 22, dyeBrown, METALLIC).asDust();
    public static Materials Ethylene = new Materials(677, "Ethylene", 225, 225, 225, dyeWhite, FLUID).asGas(128).addFlags(ELEC, CRACK).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 4));
    public static Materials Propene = new Materials(678, "Propene", 255, 221, 85, dyeYellow, FLUID).asGas(192).addFlags(ELEC, CRACK).addMats(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 6));
    public static Materials VinylAcetate = new Materials(679, "Vinyl Acetate", 255, 179, 128, dyeOrange, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Carbon, 4), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 2));
    public static Materials PolyvinylAcetate = new Materials(680, "Polyvinyl Acetate", 255, 153, 85, dyeOrange, FLUID).asFluid().addMats(new MaterialStack(Carbon, 4), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 2));
    public static Materials MethylAcetate = new Materials(681, "Methyl Acetate", 238, 198, 175, dyeOrange, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 2));
    public static Materials AllylChloride = new Materials(682, "Allyl Chloride", 135, 222, 170, dyeCyan, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 5), new MaterialStack(Chlorine, 1));
    public static Materials HydrochloricAcid = new Materials(683, "Hydrochloric Acid", 111, 138, 145, dyeLightGray, FLUID).setName("HydrochloricAcid_GT5U").asFluid().addFlags(ELEC).addMats(new MaterialStack(Hydrogen, 1), new MaterialStack(Chlorine, 1));
    public static Materials HypochlorousAcid = new Materials(684, "Hypochlorous Acid", 111, 138, 145, dyeGray, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Hydrogen, 1), new MaterialStack(Chlorine, 1), new MaterialStack(Oxygen, 1));
    public static Materials SodiumHydroxide = new Materials(685, "Sodium Hydroxide", 0, 51, 128, dyeBlue, FLUID).setName("SodiumHydroxide_GT5U").asDust().addFlags(ELEC).addMats(new MaterialStack(Sodium, 1), new MaterialStack(Oxygen, 1), new MaterialStack(Hydrogen, 1));
    public static Materials Benzene = new Materials(686, "Benzene", 26, 26, 26, dyeGray, FLUID).asGas(288).addFlags(ELEC).addMats(new MaterialStack(Carbon, 6), new MaterialStack(Hydrogen, 6));
    public static Materials Phenol = new Materials(687, "Phenol", 120, 68, 33, dyeBrown, FLUID).asGas(288).addFlags(ELEC).addMats(new MaterialStack(Carbon, 6), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 1));
    public static Materials Cumene = new Materials(688, "Cumene", 85, 34, 0, dyeBrown, FLUID).asFluid().addFlags(ELEC).addMats(new MaterialStack(Carbon, 9), new MaterialStack(Hydrogen, 12));
    public static Materials PhosphoricAcid = new Materials(689, "Phosphoric Acid", 220, 220, 0, dyeYellow, FLUID).setName("PhosphoricAcid_GT5U").asFluid().addFlags(ELEC).addMats(new MaterialStack(Hydrogen, 3), new MaterialStack(Phosphor, 1), new MaterialStack(Oxygen, 4));

    /* NEW MATS */

    public static Materials SolderingAlloy = new Materials(314, "Soldering Alloy", 220, 220, 230, dyeWhite, DULL).asMetal(400, 400).addMats(new MaterialStack(Tin, 9), new MaterialStack(Antimony, 1));
    public static Materials GalliumArsenide = new Materials(980, "Gallium Arsenide", 160, 160, 160, dyeGray, DULL).asMetal(-1, 1200).addMats(new MaterialStack(Arsenic, 1), new MaterialStack(Gallium, 1));
    public static Materials IndiumGalliumPhosphide = new Materials(981, "Indium Gallium Phosphide", 160, 140, 190, dyeLightGray, DULL).asSolid().addMats(new MaterialStack(Indium, 1), new MaterialStack(Gallium, 1), new MaterialStack(Phosphor, 1));
    public static Materials Spessartine = new Materials(838, "Spessartine", 255, 100, 100, dyeRed, DULL).asDustOre().addMats(new MaterialStack(Aluminium, 2), new MaterialStack(Manganese, 3), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12));
    public static Materials Sphalerite = new Materials(839, "Sphalerite", 255, 255, 255, dyeYellow, DULL).asDustOre().addMats(new MaterialStack(Zinc, 1), new MaterialStack(Sulfur, 1));
    public static Materials StainlessSteel = new Materials(306, "StainlessSteel", 200, 200, 220, dyeWhite, SHINY).asMetal(-1, 1700).addTools(7.0F, 480, 2).addMats(new MaterialStack(Iron, 6), new MaterialStack(Chrome, 1), new MaterialStack(Manganese, 1), new MaterialStack(Nickel, 1));
    public static Materials Steel = new Materials(305, "Steel", 128, 128, 128, dyeGray, METALLIC).asMetal(1811, 1000).addTools(6.0F, 512, 2).addMats(new MaterialStack(Iron, 50), new MaterialStack(Carbon, 1));
    public static Materials Stibnite = new Materials(945, "Stibnite", 70, 70, 70, dyeWhite, METALLIC).asDustOre().addMats(new MaterialStack(Antimony, 2), new MaterialStack(Sulfur, 3));
    public static Materials SulfuricAcid = new Materials(720, "SulfuricAcid", 255, 128, 0, dyeOrange, FLUID).asFluid().addMats(new MaterialStack(Hydrogen, 2), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4));
    public static Materials Tanzanite = new Materials(508, "Tanzanite", 64, 0, 200, 127, dyePurple, GEMV).asGemOre(true).addTools(7.0F, 256, 2).addMats(new MaterialStack(Calcium, 2), new MaterialStack(Aluminium, 3), new MaterialStack(Silicon, 3), new MaterialStack(Hydrogen, 1), new MaterialStack(Oxygen, 13));
    public static Materials Tetrahedrite = new Materials(840, "Tetrahedrite", 200, 32, 0, dyeRed, DULL).asDustOre().addMats(new MaterialStack(Copper, 3), new MaterialStack(Antimony, 1), new MaterialStack(Sulfur, 3), new MaterialStack(Iron, 1));
    public static Materials Topaz = new Materials(507, "Topaz", 255, 128, 0, 127, dyePurple, GEMH).asGemOre(true).addTools(7.0F, 256, 3).addMats(new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 1), new MaterialStack(Fluorine, 2), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 6));
    public static Materials Tungstate = new Materials(841, "Tungstate", 55, 50, 35, dyeBlack, DULL).asDustOre().addMats(new MaterialStack(Tungsten, 1), new MaterialStack(Lithium, 2), new MaterialStack(Oxygen, 4));
    public static Materials Ultimet = new Materials(344, "Ultimet", 180, 180, 230, dyeLightBlue, SHINY).asMetal(2700, 2700).addMats(new MaterialStack(Cobalt, 5), new MaterialStack(Chrome, 2), new MaterialStack(Nickel, 1), new MaterialStack(Molybdenum, 1));
    public static Materials Uraninite = new Materials(922, "Uraninite", 35, 35, 35, dyeLime, METALLIC).asDustOre().addMats(new MaterialStack(Uranium, 1), new MaterialStack(Oxygen, 2));
    public static Materials Uvarovite = new Materials(842, "Uvarovite", 180, 255, 180, dyeGreen, DIAMOND).asDust().addMats(new MaterialStack(Calcium, 3), new MaterialStack(Chrome, 2), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12));
    public static Materials VanadiumGallium = new Materials(357, "VanadiumGallium", 128, 128, 140, dyeGray, SHINY).asMetal(4500, 4500).addMats(new MaterialStack(Vanadium, 3), new MaterialStack(Gallium, 1));
    public static Materials Wood = new Materials(809, "Wood", 100, 50, 0, dyeBrown, WOOD).asSolid().addTools(2.0F, 16, 0).addMats(new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 1), new MaterialStack(Hydrogen, 1));
    public static Materials WroughtIron = new Materials(304, "WroughtIron", 200, 180, 180, dyeLightGray, METALLIC).asMetal(1811, 0).addTools(6.0F, 384, 2).addMats(new MaterialStack(Iron, 1));
    public static Materials Wulfenite = new Materials(882, "Wulfenite", 255, 128, 0, dyeOrange, DULL).asDustOre().addMats(new MaterialStack(Lead, 1), new MaterialStack(Molybdenum, 1), new MaterialStack(Oxygen, 4));
    public static Materials YellowLimonite = new Materials(931, "YellowLimonite", 200, 200, 0, dyeYellow, METALLIC).asDustOre().addMats(new MaterialStack(Iron, 1), new MaterialStack(Hydrogen, 1), new MaterialStack(Oxygen, 2));
    public static Materials YttriumBariumCuprate = new Materials(358, "YttriumBariumCuprate", 80, 64, 70, dyeGray, METALLIC).asMetal(4500, 4500).addMats(new MaterialStack(Yttrium, 1), new MaterialStack(Barium, 2), new MaterialStack(Copper, 3), new MaterialStack(Oxygen, 7));

    /**
     * Second Degree Compounds
     */
    public static Materials WoodSealed = new Materials(889, "Sealed Wood", 80, 40, 0, dyeBrown, WOOD).asSolid().addFlags(GEAR).addTools(3.0F, 24, 0).addMats(new MaterialStack(Wood, 1));
    public static Materials Glass = new Materials(890, "Glass", 250, 250, 250, 220, dyeWhite, GLASS).asGem(true).addMats(new MaterialStack(SiliconDioxide, 1));
    public static Materials Lignite = new Materials(538, "Lignite Coal", 100, 70, 70, dyeBlack, LIGNITE).asGemOre(false).addMats(new MaterialStack(Carbon, 3), new MaterialStack(Water, 1));
    public static Materials Olivine = new Materials(505, "Olivine", 150, 255, 150, 127, dyeLime, RUBY).asGemOre(true).addTools(7.0F, 256, 2).addMats(new MaterialStack(Magnesium, 2), new MaterialStack(Iron, 1), new MaterialStack(SiliconDioxide, 2));
    public static Materials Opal = new Materials (510, "Opal", 0, 0, 255, dyeBlue, OPAL).asGemOre(true).addTools(7.0F, 256, 2).addMats(new MaterialStack(SiliconDioxide, 1));
    public static Materials Amethyst = new Materials(509, "Amethyst", 210, 50, 210, 127, dyePink, FLINT).asGemOre(true).addTools(7.0F, 256, 3).addMats(new MaterialStack(SiliconDioxide, 4), new MaterialStack(Iron, 1));
    public static Materials Redstone = new Materials(810, "Redstone", 200, 0, 0, dyeRed, ROUGH).asDustOre().addMats(new MaterialStack(Silicon, 1), new MaterialStack(Pyrite, 5), new MaterialStack(Ruby, 1), new MaterialStack(Mercury, 3));
    public static Materials Lapis = new Materials(526, "Lapis", 70, 70, 220, dyeBlue, LAPIS).asGemOre(false).addMats(new MaterialStack(Lazurite, 12), new MaterialStack(Sodalite, 2), new MaterialStack(Pyrite, 1), new MaterialStack(Calcite, 1));
    public static Materials Blaze = new Materials(801, "Blaze", 255, 200, 0, dyeYellow, POWDER).asDust().addMats(new MaterialStack(DarkAsh, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Magic, 1));
    public static Materials EnderPearl = new Materials(532, "Enderpearl", 108, 220, 200, dyeGreen, SHINY).asGem(false).addMats(new MaterialStack(Beryllium, 1), new MaterialStack(Potassium, 4), new MaterialStack(Nitrogen, 5), new MaterialStack(Magic, 6));
    public static Materials EnderEye = new Materials(533, "Endereye", 160, 250, 230, dyeGreen, SHINY).asGem(false).addMats(new MaterialStack(EnderPearl, 1), new MaterialStack(Blaze, 1));
    public static Materials Flint = new Materials(802, "Flint", 0, 32, 64, dyeGray, FLINT).asDust().addTools(2.5F, 64, 1).addMats(new MaterialStack(SiliconDioxide, 1));
    public static Materials Apatite = new Materials(530, "Apatite", 200, 200, 255, dyeCyan, DIAMOND).asGemOre(false).addMats(new MaterialStack(Calcium, 5), new MaterialStack(Phosphate, 3), new MaterialStack(Chlorine, 1));
    public static Materials Alumite = new Materials(-1, "Alumite", 255, 255, 255, dyePink, METALLIC).asMetal().addTools(1.5F, 64, 0).addMats(new MaterialStack(Aluminium, 5), new MaterialStack(Iron, 2), new MaterialStack(Obsidian, 2));
    public static Materials Manyullyn = new Materials(-1, "Manyullyn", 255, 255, 255, dyePurple, METALLIC).asMetal().addTools(1.5F, 64, 0).addMats(new MaterialStack(Cobalt, 1), new MaterialStack(Ardite, 1));
    public static Materials SterlingSilver = new Materials(350, "Sterling Silver", 250, 220, 225, dyeWhite, SHINY).asSolid(-1, 1700).addFlags(GEAR).addTools(13.0F, 128, 2).addMats(new MaterialStack(Copper, 1), new MaterialStack(Silver, 4));
    public static Materials RoseGold = new Materials(351, "Rose Gold", 255, 230, 30, dyeOrange, SHINY).asSolid(-1, 1600).addFlags(GEAR).addTools(14.0F, 128, 2).addMats(new MaterialStack(Copper, 1), new MaterialStack(Gold, 4));
    public static Materials BlackBronze = new Materials(352, "Black Bronze", 100, 50, 125, dyePurple, DULL).asSolid(-1, 2000).addFlags(GEAR).addTools(12.0F, 256, 2).addMats(new MaterialStack(Gold, 1), new MaterialStack(Silver, 1), new MaterialStack(Copper, 3));
    public static Materials BismuthBronze = new Materials(353, "Bismuth Bronze", 100, 125, 125, dyeCyan, DULL).asSolid(-1, 1100).addFlags(GEAR).addTools(8.0F, 256, 2).addMats(new MaterialStack(Bismuth, 1), new MaterialStack(Zinc, 1), new MaterialStack(Copper, 3));
    public static Materials BlackSteel = new Materials(334, "Black Steel", 100, 100, 100, dyeBlack, METALLIC).asSolid(-1, 1200).addFlags(GEAR).addTools(6.5F, 768, 2).addMats(new MaterialStack(Nickel, 1), new MaterialStack(BlackBronze, 1), new MaterialStack(Steel, 3));
    public static Materials RedSteel = new Materials(348, "Red Steel", 140, 100, 100, dyeRed, METALLIC).asSolid(-1, 1300).addTools(7.0F, 896, 2).addMats(new MaterialStack(SterlingSilver, 1), new MaterialStack(BismuthBronze, 1), new MaterialStack(Steel, 2), new MaterialStack(BlackSteel, 4));
    public static Materials BlueSteel = new Materials(349, "Blue Steel", 100, 100, 140, dyeBlue, METALLIC).asSolid(-1, 1400).addTools(7.5F, 1024, 2).addMats(new MaterialStack(RoseGold, 1), new MaterialStack(Brass, 1), new MaterialStack(Steel, 2), new MaterialStack(BlackSteel, 4));
    public static Materials DamascusSteel = new Materials(335, "Damascus Steel", 110, 110, 110, dyeGray, METALLIC).asSolid(2500, 1500).addTools(8.0F, 1280, 2).addMats(new MaterialStack(Steel, 1));
    public static Materials TungstenSteel = new Materials(316, "Tungstensteel", 200, 255, 0, dyeBlue, METALLIC).asSolid(-1, 3000).addFlags(GEAR).addTools(8.0F, 2560, 4).addMats(new MaterialStack(Steel, 1), new MaterialStack(Tungsten, 1));
    public static Materials NitroFuel = new Materials(709, "Cetane-Boosted Diesel", 200, 255, 0, dyeLime, FLUID).asFluid(512);
    public static Materials Mithril = new Materials(331, "Mithril", 255, 255, 210, dyeLightBlue, SHINY).asSolid().addTools(14.0F, 64, 3).addMats(new MaterialStack(Materials.Platinum, 2), new MaterialStack(Materials.Magic, 1));
    public static Materials RedAlloy = new Materials(308, "Red Alloy", 200, 0, 0, dyeRed, DULL).asSolid().addMats(new MaterialStack(Copper, 1), new MaterialStack(Redstone, 4));
    public static Materials CobaltBrass = new Materials(343, "Cobalt Brass", 180, 180, 160, dyeOrange, METALLIC).asSolid().addFlags(GEAR).addTools(8.0F, 256, 2).addMats(new MaterialStack(Brass, 7), new MaterialStack(Aluminium, 1), new MaterialStack(Cobalt, 1));
    public static Materials Phosphorus = new Materials(534, "Phosphorus", 255, 255, 0, dyeYellow, FLINT).asGemOre(false).addFlags(CELL).addMats(new MaterialStack(Calcium, 3), new MaterialStack(Phosphate, 2));
    public static Materials Basalt = new Materials(844, "Basalt", 30, 20, 20, dyeBlack, ROUGH).asDust().addMats(new MaterialStack(Olivine, 1), new MaterialStack(Calcite, 3), new MaterialStack(Flint, 8), new MaterialStack(DarkAsh, 4));
    public static Materials GarnetRed = new Materials(527, "Red Garnet", 200, 80, 80, 127, dyeRed, RUBY).asGemOre(true).addTools(7.0F, 128, 2).addMats(new MaterialStack(Pyrope, 3), new MaterialStack(Almandine, 5), new MaterialStack(Spessartine, 8));
    public static Materials GarnetYellow = new Materials(528, "Yellow Garnet", 200, 200, 80, 127, dyeYellow, RUBY).asGemOre(true).addTools(7.0F, 128, 2).addMats(new MaterialStack(Andradite, 5), new MaterialStack(Grossular, 8), new MaterialStack(Uvarovite, 3));
    public static Materials Marble = new Materials(845, "Marble", 200, 200, 200, dyeWhite, FINE).asDust().addMats(new MaterialStack(Magnesium, 1), new MaterialStack(Calcite, 7));
    public static Materials Sugar = new Materials(803, "Sugar", 250, 250, 250, dyeWhite, FINE).asDust().addMats(new MaterialStack(Carbon, 12), new MaterialStack(Water, 11));
    public static Materials Thaumium = new Materials(330, "Thaumium", 150, 100, 200, dyePurple, METALLIC).asSolid().addFlags(GEAR).addTools(12.0F, 256, 3).addMats(new MaterialStack(Iron, 1), new MaterialStack(Magic, 1));
    public static Materials PotassiumFeldspar = new Materials(847, "Potassium Feldspar", 120, 40, 40, dyePink, FINE).asDust().addMats(new MaterialStack(Potassium, 1), new MaterialStack(Aluminium, 1), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 8));
    public static Materials Biotite = new Materials(848, "Biotite", 20, 30, 20, dyeGray, METALLIC).asDust().addMats(new MaterialStack(Potassium, 1), new MaterialStack(Magnesium, 3), new MaterialStack(Aluminium, 3), new MaterialStack(Fluorine, 2), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 10));
    public static Materials GraniteBlack = new Materials(849, "Black Granite", 10, 10, 10, dyeBlack, ROUGH).asDust().addFlags(GEAR).addTools(4.0F, 64, 3).addMats(new MaterialStack(SiliconDioxide, 4), new MaterialStack(Biotite, 1));
    public static Materials GraniteRed = new Materials(850, "Red Granite", 255, 0, 128, dyeMagenta, ROUGH).asDust().addFlags(GEAR).addTools(4.0F, 64, 3).addMats(new MaterialStack(Aluminium, 2), new MaterialStack(PotassiumFeldspar, 1), new MaterialStack(Oxygen, 3));
    public static Materials VanadiumMagnetite = new Materials(923, "Vanadium Magnetite", 35, 35, 60, dyeBlack, METALLIC).asDustOre().addMats(new MaterialStack(Magnetite, 1), new MaterialStack(Vanadium, 1));
    public static Materials BasalticMineralSand = new Materials(935, "Basaltic Mineral Sand", 40, 50, 40, dyeBlack, SAND).asDust().addMats(new MaterialStack(Magnetite, 1), new MaterialStack(Basalt, 1));
    public static Materials GraniticMineralSand = new Materials(936, "Granitic Mineral Sand", 40, 60, 60, dyeBlack, SAND).asDust().addMats(new MaterialStack(Magnetite, 1), new MaterialStack(GraniteBlack, 1));
    public static Materials Bastnasite = new Materials(905, "Bastnasite", 200, 110, 45, dyeNULL, FINE).asDustOre().addMats(new MaterialStack(Cerium, 1), new MaterialStack(Carbon, 1), new MaterialStack(Fluorine, 1), new MaterialStack(Oxygen, 3));
    public static Materials Pentlandite = new Materials(909, "Pentlandite", 165, 150, 5, dyeNULL, DULL).asDustOre().addMats(new MaterialStack(Nickel, 9), new MaterialStack(Sulfur, 8));
    public static Materials Spodumene = new Materials(920, "Spodumene", 190, 170, 170, dyeNULL, DULL).asDustOre().addMats(new MaterialStack(Lithium, 1), new MaterialStack(Aluminium, 1), new MaterialStack(Silicon, 2), new MaterialStack(Oxygen, 6));
    public static Materials Tantalite = new Materials(921, "Tantalite", 145, 80, 40, dyeNULL, METALLIC).asDustOre().addMats(new MaterialStack(Manganese, 1), new MaterialStack(Tantalum, 2), new MaterialStack(Oxygen, 6));
    public static Materials Lepidolite = new Materials(907, "Lepidolite", 240, 50, 140, dyeNULL, FINE).asDustOre().addMats(new MaterialStack(Potassium, 1), new MaterialStack(Lithium, 3), new MaterialStack(Aluminium, 4), new MaterialStack(Fluorine, 2), new MaterialStack(Oxygen, 10));
    public static Materials Glauconite = new Materials(933, "Glauconite", 130, 180, 60, dyeNULL, DULL).asDustOre().addMats(new MaterialStack(Potassium, 1), new MaterialStack(Magnesium, 2), new MaterialStack(Aluminium, 4), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 12));
    public static Materials Bentonite = new Materials(927, "Bentonite", 245, 215, 210, dyeNULL, ROUGH).asDustOre().addMats(new MaterialStack(Sodium, 1), new MaterialStack(Magnesium, 6), new MaterialStack(Silicon, 12), new MaterialStack(Hydrogen, 6), new MaterialStack(Water, 5), new MaterialStack(Oxygen, 36));
    public static Materials Pitchblende = new Materials(873, "Pitchblende", 200, 210, 0, dyeYellow, DULL).asDustOre().addMats(new MaterialStack(Uraninite, 3), new MaterialStack(Thorium, 1), new MaterialStack(Lead, 1));
    public static Materials Monazite = new Materials(520, "Monazite", 50, 70, 50, dyeGreen, DIAMOND).asGemOre(false).addMats(new MaterialStack(RareEarth, 1), new MaterialStack(Phosphate, 1));
    public static Materials Malachite = new Materials(871, "Malachite", 5, 95, 5, dyeGreen, DULL).asDustOre().addMats(new MaterialStack(Copper, 2), new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 5));
    public static Materials Barite = new Materials(904, "Barite", 230, 235, 255, dyeNULL, DULL).asDustOre().addMats(new MaterialStack(Barium, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4));
    public static Materials Talc = new Materials(902, "Talc", 90, 180, 90, dyeNULL, DULL).asDustOre().addMats(new MaterialStack(Magnesium, 3), new MaterialStack(Silicon, 4), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 12));
    public static Materials Soapstone = new Materials(877, "Soapstone", 95, 145, 95, 9, dyeNULL, DULL).asDustOre().addMats(new MaterialStack(Magnesium, 3), new MaterialStack(Silicon, 4), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 12));
    public static Materials Concrete = new Materials(947, "Concrete", 100, 100, 100, dyeGray, ROUGH).asDust(300).addMats(new MaterialStack(Stone, 1));
    public static Materials IronMagnetic = new Materials(354, "Magnetic Iron", 200, 200, 200, dyeGray, MAGNETIC).asSolid().addFlags(GEAR).addTools(6.0F, 256, 2).addMats(new MaterialStack(Iron, 1));
    public static Materials SteelMagnetic = new Materials(355, "Magnetic Steel", 128, 128, 128, dyeGray, MAGNETIC).asSolid(1000, 1000).addFlags(GEAR).addTools(6.0F, 512, 2).addMats(new MaterialStack(Steel, 1));
    public static Materials NeodymiumMagnetic = new Materials(356, "Magnetic Neodymium", 100, 100, 100, dyeGray, MAGNETIC).asSolid(1297, 1297).addFlags(GEAR).addTools(7.0F, 512, 2).addMats(new MaterialStack(Neodymium, 1));
    public static Materials TungstenCarbide = new Materials(370, "Tungsten Carbide", 51, 0, 102, dyeBlack, METALLIC).asSolid(2460, 2460).addFlags(GEAR).addTools(14.0F, 1280, 4).addMats(new MaterialStack(Tungsten, 1), new MaterialStack(Carbon, 1));
    public static Materials VanadiumSteel = new Materials(371, "Vanadium Steel", 192, 192, 192, dyeYellow, METALLIC).asSolid(1453, 1453).addFlags(GEAR).addTools(3.0F, 1920, 3).addMats(new MaterialStack(Vanadium, 1), new MaterialStack(Chrome, 1), new MaterialStack(Steel, 7));
    public static Materials HSSG = new Materials(372, "HSSG", 153, 153, 0, dyeYellow, METALLIC).asSolid(4500, 4500).addFlags(GEAR).addTools(10.0F, 4000, 3).addMats(new MaterialStack(TungstenSteel, 5), new MaterialStack(Chrome, 1), new MaterialStack(Molybdenum, 2), new MaterialStack(Vanadium, 1));
    public static Materials HSSE = new Materials(373, "HSSE", 51, 102, 0, dyeBlue, METALLIC).asSolid(5400, 5400).addFlags(GEAR).addTools(10.0F, 5120, 4).addMats(new MaterialStack(HSSG, 6), new MaterialStack(Cobalt, 1), new MaterialStack(Manganese, 1), new MaterialStack(Silicon, 1));
    public static Materials HSSS = new Materials(374, "HSSS", 102, 0, 51, dyeRed, METALLIC).asSolid(5400, 5400).addFlags(GEAR).addTools(14.0F, 3000, 4).addMats(new MaterialStack(HSSG, 6), new MaterialStack(Iridium, 2), new MaterialStack(Osmium, 1));
    public static Materials DilutedSulfuricAcid = new Materials(640, "DilutedSulfuricAcid", 192, 120, 32, dyeOrange, FLUID).asFluid().addMats(new MaterialStack(SulfuricAcid, 1));

    public final short[] mRGBa = new short[]{255, 255, 255, 0}, mMoltenRGBa = new short[]{255, 255, 255, 0};
    public TextureSet mIconSet = NONE;
    public int mMetaItemSubID = -1;
    public boolean mUnificatable = true;
    public Materials mMaterialInto = this;
    public List<MaterialStack> mMaterialList = new ArrayList<MaterialStack>();
    public List<Materials> mOreByProducts = new ArrayList<Materials>(), mOreReRegistrations = new ArrayList<Materials>();
    public List<AspectStack> mAspects = new ArrayList<AspectStack>();
    public ArrayList<ItemStack> mMaterialItems = new ArrayList<ItemStack>();
    public Collection<SubTag> mSubTags = new LinkedHashSet<SubTag>();
    public Enchantment mEnchantmentTools = null, mEnchantmentArmors = null;
    public byte mEnchantmentToolsLevel = 0, mEnchantmentArmorsLevel = 0;
    public boolean mBlastFurnaceRequired = false, mTransparent = false;
    public float mToolSpeed = 1.0F, mHeatDamage = 0.0F;
    public String mChemicalFormula = "?", mName = "null", mDefaultLocalName = "null", mCustomID = "null", mConfigSection = "null";
    public Dyes mColor = Dyes.dyeNULL;
    public short mMeltingPoint = 0, mBlastFurnaceTemp = 0, mGasTemp = 0;
    public int mTypes = 0;
    public int mDurability = 16, mFuelPower = 0, mFuelType = 0, mExtraData = 0, mOreMultiplier = 1, mByProductMultiplier = 1, mSmeltingMultiplier = 1;
    public long mDensity = M;
    public Element mElement = null;
    public Materials mDirectSmelting = this, mOreReplacement = this, mMacerateInto = this, mSmeltInto = this, mArcSmeltInto = this, mHandleMaterial = this;
    public byte mToolQuality = 0;
    public boolean mHasParentMod = true, mHasPlasma = false, mHasGas = false, mCustomOre = false;
    public Fluid mSolid = null, mFluid = null, mGas = null, mPlasma = null;

    private boolean hasCorrespondingFluid = false, hasCorrespondingGas = false, canBeCracked = false;
    private Fluid[] hydroCrackedFluids = new Fluid[3], steamCrackedFluids = new Fluid[3];

    public MaterialType mMatType = null;

    /**
     * This Fluid is used as standard Unit for Molten Materials. 1296 is a Molten Block, that means 144 is one Material Unit worth of fluid.
     */
    public Fluid mStandardMoltenFluid = null;

    static {
        initSubTags();
        Iron					.mOreReRegistrations.add(AnyIron	);
        PigIron					.mOreReRegistrations.add(AnyIron	);
        WroughtIron				.mOreReRegistrations.add(AnyIron	);
        Copper					.mOreReRegistrations.add(AnyCopper	);
        AnnealedCopper			.mOreReRegistrations.add(AnyCopper	);
        Bronze					.mOreReRegistrations.add(AnyBronze	);
        Rubber					.mOreReRegistrations.add(AnyRubber);
        StyreneButadieneRubber	.mOreReRegistrations.add(AnyRubber);
        Silicone				.mOreReRegistrations.add(AnyRubber);
        StyreneButadieneRubber	.mOreReRegistrations.add(AnySyntheticRubber);
        Silicone				.mOreReRegistrations.add(AnySyntheticRubber);
        WoodSealed				.setMaceratingInto(Wood				);
        NetherBrick				.setMaceratingInto(Netherrack		);
        NeodymiumMagnetic		.setSmeltingInto(Neodymium			).setMaceratingInto(Neodymium		).setArcSmeltingInto(Neodymium			);
        SteelMagnetic			.setSmeltingInto(Steel				).setMaceratingInto(Steel			).setArcSmeltingInto(Steel				);
        Iron					.setSmeltingInto(Iron				).setMaceratingInto(Iron			).setArcSmeltingInto(WroughtIron		);
        AnyIron					.setSmeltingInto(Iron				).setMaceratingInto(Iron			).setArcSmeltingInto(WroughtIron		);
        PigIron					.setSmeltingInto(Iron				).setMaceratingInto(Iron			).setArcSmeltingInto(WroughtIron		);
        WroughtIron				.setSmeltingInto(Iron				).setMaceratingInto(Iron			).setArcSmeltingInto(WroughtIron		);
        IronMagnetic			.setSmeltingInto(Iron				).setMaceratingInto(Iron			).setArcSmeltingInto(WroughtIron		);
        Copper					.setSmeltingInto(Copper				).setMaceratingInto(Copper			).setArcSmeltingInto(AnnealedCopper		);
        AnyCopper				.setSmeltingInto(Copper				).setMaceratingInto(Copper			).setArcSmeltingInto(AnnealedCopper		);
        AnnealedCopper			.setSmeltingInto(Copper				).setMaceratingInto(Copper			).setArcSmeltingInto(AnnealedCopper		);
        Netherrack				.setSmeltingInto(NetherBrick		);
        Sand					.setSmeltingInto(Glass				);
        Ice						.setSmeltingInto(Water				);
        Snow					.setSmeltingInto(Water				);
        Mercury					.add(SubTag.SMELTING_TO_GEM);
        Cinnabar				.setDirectSmelting(Mercury		).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT).add(SubTag.SMELTING_TO_GEM);
        Tetrahedrite			.setDirectSmelting(Copper		).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
        Chalcopyrite			.setDirectSmelting(Copper		).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
        Malachite				.setDirectSmelting(Copper		).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
        Pentlandite				.setDirectSmelting(Nickel		).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
        Sphalerite				.setDirectSmelting(Zinc			).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
        Pyrite					.setDirectSmelting(Iron			).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
        BasalticMineralSand		.setDirectSmelting(Iron			).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
        GraniticMineralSand		.setDirectSmelting(Iron			).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
        YellowLimonite			.setDirectSmelting(Iron			).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
        BrownLimonite			.setDirectSmelting(Iron			);
        BandedIron				.setDirectSmelting(Iron			);
        Cassiterite				.setDirectSmelting(Tin			);
        Garnierite				.setDirectSmelting(Nickel		);
        Cobaltite				.setDirectSmelting(Cobalt		);
        Stibnite				.setDirectSmelting(Antimony		);
        Cooperite				.setDirectSmelting(Platinum		);
        Pyrolusite				.setDirectSmelting(Manganese	);
        Magnesite				.setDirectSmelting(Magnesium	);
        Molybdenite				.setDirectSmelting(Molybdenum	);
        Amber					.setOreMultiplier( 2).setSmeltingMultiplier( 2);
        InfusedAir				.setOreMultiplier( 2).setSmeltingMultiplier( 2);
        InfusedFire				.setOreMultiplier( 2).setSmeltingMultiplier( 2);
        InfusedEarth			.setOreMultiplier( 2).setSmeltingMultiplier( 2);
        InfusedWater			.setOreMultiplier( 2).setSmeltingMultiplier( 2);
        InfusedEntropy			.setOreMultiplier( 2).setSmeltingMultiplier( 2);
        InfusedOrder			.setOreMultiplier( 2).setSmeltingMultiplier( 2);
        InfusedVis				.setOreMultiplier( 2).setSmeltingMultiplier( 2);
        InfusedDull				.setOreMultiplier( 2).setSmeltingMultiplier( 2);
        Salt					.setOreMultiplier( 2).setSmeltingMultiplier( 2);
        RockSalt				.setOreMultiplier( 2).setSmeltingMultiplier( 2);
        Scheelite				.setOreMultiplier( 2).setSmeltingMultiplier( 2);
        Tungstate				.setOreMultiplier( 2).setSmeltingMultiplier( 2);
        Cassiterite				.setOreMultiplier( 2).setSmeltingMultiplier( 2);
        NetherQuartz			.setOreMultiplier( 2).setSmeltingMultiplier( 2);
        CertusQuartz			.setOreMultiplier( 2).setSmeltingMultiplier( 2);
        Phosphorus				.setOreMultiplier( 3).setSmeltingMultiplier( 3);
        Saltpeter				.setOreMultiplier( 4).setSmeltingMultiplier( 4);
        Apatite					.setOreMultiplier( 4).setSmeltingMultiplier( 4).setByProductMultiplier(2);
        Redstone				.setOreMultiplier( 5).setSmeltingMultiplier( 5);
        Glowstone				.setOreMultiplier( 5).setSmeltingMultiplier( 5);
        Lapis					.setOreMultiplier( 6).setSmeltingMultiplier( 6).setByProductMultiplier(4);
        Sodalite				.setOreMultiplier( 6).setSmeltingMultiplier( 6).setByProductMultiplier(4);
        Lazurite				.setOreMultiplier( 6).setSmeltingMultiplier( 6).setByProductMultiplier(4);
        Monazite				.setOreMultiplier( 8).setSmeltingMultiplier( 8).setByProductMultiplier(2);
        Plastic					.setEnchantmentForTools(Enchantment.knockback, 1);
        PolyvinylChloride		.setEnchantmentForTools(Enchantment.knockback, 1);
        Polystyrene				.setEnchantmentForTools(Enchantment.knockback, 1);
        Rubber					.setEnchantmentForTools(Enchantment.knockback, 2);
        StyreneButadieneRubber	.setEnchantmentForTools(Enchantment.knockback, 2);
        InfusedAir				.setEnchantmentForTools(Enchantment.knockback, 2);
        Mithril					.setEnchantmentForTools(Enchantment.fortune, 3);
        Thaumium				.setEnchantmentForTools(Enchantment.fortune, 2);
        InfusedWater			.setEnchantmentForTools(Enchantment.fortune, 3);
        Flint					.setEnchantmentForTools(Enchantment.fireAspect, 1);
        Firestone				.setEnchantmentForTools(Enchantment.fireAspect, 3);
        Blaze					.setEnchantmentForTools(Enchantment.fireAspect, 3);
        InfusedFire				.setEnchantmentForTools(Enchantment.fireAspect, 3);
        Amber					.setEnchantmentForTools(Enchantment.silkTouch, 1);
        EnderPearl				.setEnchantmentForTools(Enchantment.silkTouch, 1);
        Enderium				.setEnchantmentForTools(Enchantment.silkTouch, 1);
        NetherStar				.setEnchantmentForTools(Enchantment.silkTouch, 1);
        InfusedOrder			.setEnchantmentForTools(Enchantment.silkTouch, 1);
        BlackBronze				.setEnchantmentForTools(Enchantment.smite, 2);
        Gold					.setEnchantmentForTools(Enchantment.smite, 3);
        RoseGold				.setEnchantmentForTools(Enchantment.smite, 4);
        Platinum				.setEnchantmentForTools(Enchantment.smite, 5);
        InfusedVis				.setEnchantmentForTools(Enchantment.smite, 5);
        Lead					.setEnchantmentForTools(Enchantment.baneOfArthropods, 2);
        Nickel					.setEnchantmentForTools(Enchantment.baneOfArthropods, 2);
        Invar					.setEnchantmentForTools(Enchantment.baneOfArthropods, 3);
        Antimony				.setEnchantmentForTools(Enchantment.baneOfArthropods, 3);
        BatteryAlloy			.setEnchantmentForTools(Enchantment.baneOfArthropods, 4);
        Bismuth					.setEnchantmentForTools(Enchantment.baneOfArthropods, 4);
        BismuthBronze			.setEnchantmentForTools(Enchantment.baneOfArthropods, 5);
        InfusedEarth			.setEnchantmentForTools(Enchantment.baneOfArthropods, 5);
        Iron					.setEnchantmentForTools(Enchantment.sharpness, 1);
        Bronze					.setEnchantmentForTools(Enchantment.sharpness, 1);
        Brass					.setEnchantmentForTools(Enchantment.sharpness, 2);
        Steel					.setEnchantmentForTools(Enchantment.sharpness, 2);
        WroughtIron				.setEnchantmentForTools(Enchantment.sharpness, 2);
        StainlessSteel			.setEnchantmentForTools(Enchantment.sharpness, 3);
        BlackSteel				.setEnchantmentForTools(Enchantment.sharpness, 4);
        RedSteel				.setEnchantmentForTools(Enchantment.sharpness, 4);
        BlueSteel				.setEnchantmentForTools(Enchantment.sharpness, 5);
        DamascusSteel			.setEnchantmentForTools(Enchantment.sharpness, 5);
        InfusedEntropy			.setEnchantmentForTools(Enchantment.sharpness, 5);
        TungstenCarbide			.setEnchantmentForTools(Enchantment.sharpness, 5);
        HSSE					.setEnchantmentForTools(Enchantment.sharpness, 5);
        HSSG					.setEnchantmentForTools(Enchantment.sharpness, 4);
        HSSS					.setEnchantmentForTools(Enchantment.sharpness, 5);
        InfusedAir				.setEnchantmentForArmors(Enchantment.respiration, 3);
        InfusedFire				.setEnchantmentForArmors(Enchantment.featherFalling, 4);
        InfusedEarth			.setEnchantmentForArmors(Enchantment.protection, 4);
        InfusedEntropy			.setEnchantmentForArmors(Enchantment.thorns, 3);
        InfusedWater			.setEnchantmentForArmors(Enchantment.aquaAffinity, 1);
        InfusedOrder			.setEnchantmentForArmors(Enchantment.projectileProtection, 4);
        InfusedDull				.setEnchantmentForArmors(Enchantment.blastProtection, 4);
        InfusedVis				.setEnchantmentForArmors(Enchantment.protection, 4);
        Lava					.setHeatDamage(3.0F);
        Firestone				.setHeatDamage(5.0F);
        Chalcopyrite			.addOreByProducts(Pyrite				, Cobalt				, Cadmium				, Gold			);
        Sphalerite				.addOreByProducts(GarnetYellow			, Cadmium				, Gallium				, Zinc			);
        MeteoricIron			.addOreByProducts(Iron					, Nickel				, Iridium				, Platinum		);
        Glauconite				.addOreByProducts(Sodium				, Aluminium				, Iron					);
        Bentonite				.addOreByProducts(Aluminium				, Calcium				, Magnesium				);
        Uraninite				.addOreByProducts(Uranium				, Thorium				, Uranium235			);
        Pitchblende				.addOreByProducts(Thorium				, Uranium				, Lead					);
        Galena					.addOreByProducts(Sulfur				, Silver				, Lead					);
        Lapis					.addOreByProducts(Lazurite				, Sodalite				, Pyrite				);
        Pyrite					.addOreByProducts(Sulfur				, Phosphorus			, Iron					);
        Copper					.addOreByProducts(Cobalt				, Gold					, Nickel				);
        Nickel					.addOreByProducts(Cobalt				, Platinum				, Iron					);
        GarnetRed				.addOreByProducts(Spessartine			, Pyrope				, Almandine				);
        GarnetYellow			.addOreByProducts(Andradite				, Grossular				, Uvarovite				);
        Cooperite				.addOreByProducts(Palladium				, Nickel				, Iridium				);
        Cinnabar				.addOreByProducts(Redstone				, Sulfur				, Glowstone				);
        Tantalite				.addOreByProducts(Manganese				, Niobium				, Tantalum				);
        Asbestos				.addOreByProducts(Asbestos				, Silicon				, Magnesium				);
        Pentlandite				.addOreByProducts(Iron					, Sulfur				, Cobalt				);
        Uranium					.addOreByProducts(Lead					, Uranium235			, Thorium				);
        Scheelite				.addOreByProducts(Manganese				, Molybdenum			, Calcium				);
        Tungstate				.addOreByProducts(Manganese				, Silver				, Lithium				);
        Bauxite					.addOreByProducts(Grossular				, Rutile				, Gallium				);
        Redstone				.addOreByProducts(Cinnabar				, RareEarth				, Glowstone				);
        Monazite				.addOreByProducts(Thorium				, Neodymium				, RareEarth				);
        Malachite				.addOreByProducts(Copper				, BrownLimonite			, Calcite				);
        YellowLimonite			.addOreByProducts(Nickel				, BrownLimonite			, Cobalt				);
        Lepidolite				.addOreByProducts(Lithium				, Caesium				, Boron					);
        Andradite				.addOreByProducts(GarnetYellow			, Iron					, Boron					);
        Quartzite				.addOreByProducts(CertusQuartz			, Barite				);
        CertusQuartz			.addOreByProducts(Quartzite				, Barite				);
        BrownLimonite			.addOreByProducts(Malachite				, YellowLimonite		);
        Neodymium				.addOreByProducts(Monazite				, RareEarth				);
        Bastnasite				.addOreByProducts(Neodymium				, RareEarth				);
        Glowstone				.addOreByProducts(Redstone				, Gold					);
        Zinc					.addOreByProducts(Tin					, Gallium				);
        Tungsten				.addOreByProducts(Manganese				, Molybdenum			);
        Iron					.addOreByProducts(Nickel				, Tin					);
        Gold					.addOreByProducts(Copper				, Nickel				);
        Tin						.addOreByProducts(Iron					, Zinc					);
        Antimony				.addOreByProducts(Zinc					, Iron					);
        Silver					.addOreByProducts(Lead					, Sulfur				);
        Lead					.addOreByProducts(Silver				, Sulfur				);
        Thorium					.addOreByProducts(Uranium				, Lead					);
        Plutonium				.addOreByProducts(Uranium				, Lead					);
        Electrum				.addOreByProducts(Gold					, Silver				);
        Bronze					.addOreByProducts(Copper				, Tin					);
        Brass					.addOreByProducts(Copper				, Zinc					);
        Coal					.addOreByProducts(Lignite				, Thorium				);
        Ilmenite				.addOreByProducts(Iron					, Rutile				);
        Manganese				.addOreByProducts(Chrome				, Iron					);
        Sapphire				.addOreByProducts(Aluminium				, GreenSapphire			);
        GreenSapphire			.addOreByProducts(Aluminium				, Sapphire				);
        Platinum				.addOreByProducts(Nickel				, Iridium				);
        Emerald					.addOreByProducts(Beryllium				, Aluminium				);
        Olivine					.addOreByProducts(Pyrope				, Magnesium				);
        Chrome					.addOreByProducts(Iron					, Magnesium				);
        Tetrahedrite			.addOreByProducts(Antimony				, Zinc					);
        Magnetite				.addOreByProducts(Iron					, Gold					);
        GraniticMineralSand		.addOreByProducts(GraniteBlack			, Magnetite				);
        BasalticMineralSand		.addOreByProducts(Basalt				, Magnetite				);
        Basalt					.addOreByProducts(Olivine				, DarkAsh				);
        VanadiumMagnetite		.addOreByProducts(Magnetite				, Vanadium				);
        Lazurite				.addOreByProducts(Sodalite				, Lapis					);
        Sodalite				.addOreByProducts(Lazurite				, Lapis					);
        Spodumene				.addOreByProducts(Aluminium				, Lithium				);
        Ruby					.addOreByProducts(Chrome				, GarnetRed				);
        Phosphorus				.addOreByProducts(Apatite				, Phosphate				);
        Iridium					.addOreByProducts(Platinum				, Osmium				);
        Pyrope					.addOreByProducts(GarnetRed				, Magnesium				);
        Almandine				.addOreByProducts(GarnetRed				, Aluminium				);
        Spessartine				.addOreByProducts(GarnetRed				, Manganese				);
        Grossular				.addOreByProducts(GarnetYellow			, Calcium				);
        Uvarovite				.addOreByProducts(GarnetYellow			, Chrome				);
        Calcite					.addOreByProducts(Andradite				, Malachite				);
        NaquadahEnriched		.addOreByProducts(Naquadah				, Naquadria				);
        Naquadah				.addOreByProducts(NaquadahEnriched		);
        Pyrolusite				.addOreByProducts(Manganese				);
        Molybdenite				.addOreByProducts(Molybdenum			);
        Stibnite				.addOreByProducts(Antimony				);
        Garnierite				.addOreByProducts(Nickel				);
        Lignite					.addOreByProducts(Coal					);
        Diamond					.addOreByProducts(Graphite				);
        Beryllium				.addOreByProducts(Emerald				);
        Apatite					.addOreByProducts(Phosphorus			);
        Magnesite				.addOreByProducts(Magnesium				);
        NetherQuartz			.addOreByProducts(Netherrack			);
        PigIron					.addOreByProducts(Iron					);
        MeteoricIron			.addOreByProducts(Iron					);
        Steel					.addOreByProducts(Iron					);
        Mithril					.addOreByProducts(Platinum				);
        Graphite				.addOreByProducts(Carbon				);
        Netherrack				.addOreByProducts(Sulfur				);
        Flint					.addOreByProducts(Obsidian				);
        Cobaltite				.addOreByProducts(Cobalt				);
        Cobalt					.addOreByProducts(Cobaltite				);
        Sulfur					.addOreByProducts(Sulfur				);
        Saltpeter				.addOreByProducts(Saltpeter				);
        Endstone				.addOreByProducts(Helium3);
        Osmium					.addOreByProducts(Iridium				);
        Magnesium				.addOreByProducts(Olivine				);
        Aluminium				.addOreByProducts(Bauxite				);
        Titanium				.addOreByProducts(Almandine				);
        Obsidian				.addOreByProducts(Olivine				);
        Ash						.addOreByProducts(Carbon				);
        DarkAsh					.addOreByProducts(Carbon				);
        Marble					.addOreByProducts(Calcite				);
        Clay					.addOreByProducts(Clay					);
        Cassiterite				.addOreByProducts(Tin					);
        GraniteBlack			.addOreByProducts(Biotite				);
        GraniteRed				.addOreByProducts(PotassiumFeldspar		);
        Phosphate				.addOreByProducts(Phosphor				);
        Phosphor				.addOreByProducts(Phosphate				);
        Tanzanite				.addOreByProducts(Opal					);
        Opal					.addOreByProducts(Tanzanite				);
        Amethyst				.addOreByProducts(Amethyst				);
        FoolsRuby				.addOreByProducts(Jasper				);
        Amber					.addOreByProducts(Amber					);
        Topaz					.addOreByProducts(BlueTopaz				);
        BlueTopaz				.addOreByProducts(Topaz					);
        Dilithium				.addOreByProducts(Dilithium				);
        Neutronium				.addOreByProducts(Neutronium			);
        Lithium					.addOreByProducts(Lithium				);
        Silicon					.addOreByProducts(SiliconDioxide		);
        Salt					.addOreByProducts(RockSalt				);
        RockSalt				.addOreByProducts(Salt					);

        Glue.mChemicalFormula = "No Horses were harmed for the Production";
        UUAmplifier.mChemicalFormula = "Accelerates the Mass Fabricator";
        WoodSealed.mChemicalFormula = "";
        Wood.mChemicalFormula = "";
        FoolsRuby.mChemicalFormula = Ruby.mChemicalFormula;

        Naquadah.mMoltenRGBa[0] = 0;
        Naquadah.mMoltenRGBa[1] = 255;
        Naquadah.mMoltenRGBa[2] = 0;
        Naquadah.mMoltenRGBa[3] = 0;
        NaquadahEnriched.mMoltenRGBa[0] = 64;
        NaquadahEnriched.mMoltenRGBa[1] = 255;
        NaquadahEnriched.mMoltenRGBa[2] = 64;
        NaquadahEnriched.mMoltenRGBa[3] = 0;
        Naquadria.mMoltenRGBa[0] = 128;
        Naquadria.mMoltenRGBa[1] = 255;
        Naquadria.mMoltenRGBa[2] = 128;
        Naquadria.mMoltenRGBa[3] = 0;

        NaquadahEnriched.mChemicalFormula = "Nq+";
        Naquadah.mChemicalFormula = "Nq";
        Naquadria.mChemicalFormula = "NqX";
    }

    private static void initSubTags() {
        SubTag.ELECTROMAGNETIC_SEPERATION_NEODYMIUM.addTo(Bastnasite, Monazite);
        SubTag.ELECTROMAGNETIC_SEPERATION_GOLD.addTo(Magnetite, VanadiumMagnetite, BasalticMineralSand, GraniticMineralSand);
        SubTag.ELECTROMAGNETIC_SEPERATION_IRON.addTo(YellowLimonite, BrownLimonite, Pyrite, BandedIron, Nickel, Glauconite, Pentlandite, Tin, Antimony, Ilmenite, Manganese, Chrome, Andradite);
        SubTag.BLASTFURNACE_CALCITE_DOUBLE.addTo(Pyrite, YellowLimonite, BasalticMineralSand, GraniticMineralSand);
        SubTag.BLASTFURNACE_CALCITE_TRIPLE.addTo(Iron, PigIron, WroughtIron, MeteoricIron, BrownLimonite);
        SubTag.WASHING_MERCURY.addTo(Gold, Silver, Osmium, Mithril, Platinum, Cooperite);
        SubTag.WASHING_SODIUMPERSULFATE.addTo(Zinc, Nickel, Copper, Cobalt, Cobaltite, Tetrahedrite);
        SubTag.METAL.addTo(AnyIron, AnyCopper, AnyBronze, Aluminium, Americium, Antimony, Beryllium, Bismuth, Caesium, Chrome, Cobalt, Copper, Dysprosium, Europium, Gallium, Gold,
                Iridium, Iron, Lead, Lutetium, Magnesium, Manganese, Mercury, Niobium, Molybdenum, Neodymium, Neutronium, Nickel, Osmium, Palladium, Platinum, Plutonium, Plutonium241,
                Silicon, Silver, Thorium, Tin, Titanium, Tungsten, Uranium, Uranium235, Vanadium, Yttrium,
                Zinc, PhasedIron, PhasedGold, DarkSteel, ConductiveIron, ElectricalSteel, EnergeticAlloy, VibrantAlloy,
                PulsatingIron, Ardite,
                Desh, Duranium, Enderium, InfusedGold, MeteoricIron,
                MeteoricSteel, Naquadah, NaquadahAlloy, NaquadahEnriched, Naquadria,
                Tritanium, AluminiumBrass, Osmiridium, AnnealedCopper, BatteryAlloy, Brass, Bronze, Cupronickel,
                Electrum, Invar, Kanthal, Magnalium, Nichrome, NiobiumTitanium, PigIron, SolderingAlloy, StainlessSteel, Steel, Ultimet, VanadiumGallium, WroughtIron,
                YttriumBariumCuprate, Alumite, Manyullyn, SterlingSilver, RoseGold, BlackBronze, BismuthBronze, BlackSteel, RedSteel, BlueSteel, DamascusSteel,
                TungstenSteel, Mithril, RedAlloy, CobaltBrass, Thaumium, IronMagnetic, SteelMagnetic, NeodymiumMagnetic, HSSG, HSSE, HSSS, TungstenCarbide,
                VanadiumSteel);

        SubTag.FOOD.addTo(Ice, Water, Salt, Milk, Honey, FishOil, SeedOil, SeedOilLin, SeedOilHemp, Wheat, Sugar);

        Wood.add(SubTag.WOOD, SubTag.FLAMMABLE, SubTag.NO_SMELTING, SubTag.NO_SMASHING);
        WoodSealed.add(SubTag.WOOD, SubTag.FLAMMABLE, SubTag.NO_SMELTING, SubTag.NO_SMASHING, SubTag.NO_WORKING);

        Snow.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.NO_RECYCLING);
        Ice.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.NO_RECYCLING);
        Water.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.NO_RECYCLING);
        Sulfur.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.FLAMMABLE);
        Saltpeter.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.FLAMMABLE);
        Graphite.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.FLAMMABLE, SubTag.NO_SMELTING);

        Wheat.add(SubTag.FLAMMABLE, SubTag.MORTAR_GRINDABLE);
        Paper.add(SubTag.FLAMMABLE, SubTag.NO_SMELTING, SubTag.NO_SMASHING, SubTag.MORTAR_GRINDABLE, SubTag.PAPER);
        Coal.add(SubTag.FLAMMABLE, SubTag.NO_SMELTING, SubTag.NO_SMASHING, SubTag.MORTAR_GRINDABLE);
        Charcoal.add(SubTag.FLAMMABLE, SubTag.NO_SMELTING, SubTag.NO_SMASHING, SubTag.MORTAR_GRINDABLE);
        Lignite.add(SubTag.FLAMMABLE, SubTag.NO_SMELTING, SubTag.NO_SMASHING, SubTag.MORTAR_GRINDABLE);

        Rubber.add(SubTag.FLAMMABLE, SubTag.NO_SMASHING, SubTag.BOUNCY, SubTag.STRETCHY);
        StyreneButadieneRubber.add(SubTag.FLAMMABLE, SubTag.NO_SMASHING, SubTag.BOUNCY, SubTag.STRETCHY);
        Plastic.add(SubTag.FLAMMABLE, SubTag.NO_SMASHING, SubTag.BOUNCY);
        PolyvinylChloride.add(SubTag.FLAMMABLE, SubTag.NO_SMASHING, SubTag.BOUNCY);
        Polystyrene.add(SubTag.FLAMMABLE, SubTag.NO_SMASHING, SubTag.BOUNCY);
        Silicone.add(SubTag.FLAMMABLE, SubTag.NO_SMASHING, SubTag.BOUNCY, SubTag.STRETCHY);

        Gunpowder.add(SubTag.FLAMMABLE, SubTag.EXPLOSIVE, SubTag.NO_SMELTING, SubTag.NO_SMASHING);
        Glyceryl.add(SubTag.FLAMMABLE, SubTag.EXPLOSIVE, SubTag.NO_SMELTING, SubTag.NO_SMASHING);
        NitroFuel.add(SubTag.FLAMMABLE, SubTag.EXPLOSIVE, SubTag.NO_SMELTING, SubTag.NO_SMASHING);

        Lead.add(SubTag.MORTAR_GRINDABLE, SubTag.SOLDERING_MATERIAL, SubTag.SOLDERING_MATERIAL_BAD);
        Tin.add(SubTag.MORTAR_GRINDABLE, SubTag.SOLDERING_MATERIAL);
        SolderingAlloy.add(SubTag.MORTAR_GRINDABLE, SubTag.SOLDERING_MATERIAL, SubTag.SOLDERING_MATERIAL_GOOD);

        Sugar.add(SubTag.SMELTING_TO_FLUID);

        Concrete.add(SubTag.STONE, SubTag.NO_SMASHING, SubTag.SMELTING_TO_FLUID);
        ConstructionFoam.add(SubTag.STONE, SubTag.NO_SMASHING, SubTag.EXPLOSIVE, SubTag.NO_SMELTING);
        Redstone.add(SubTag.STONE, SubTag.NO_SMASHING, SubTag.UNBURNABLE, SubTag.SMELTING_TO_FLUID, SubTag.PULVERIZING_CINNABAR);
        Glowstone.add(SubTag.STONE, SubTag.NO_SMASHING, SubTag.UNBURNABLE, SubTag.SMELTING_TO_FLUID);
        Netherrack.add(SubTag.STONE, SubTag.NO_SMASHING, SubTag.UNBURNABLE, SubTag.FLAMMABLE);
        Stone.add(SubTag.STONE, SubTag.NO_SMASHING, SubTag.NO_RECYCLING);
        Brick.add(SubTag.STONE, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        NetherBrick.add(SubTag.STONE, SubTag.NO_SMASHING);
        Endstone.add(SubTag.STONE, SubTag.NO_SMASHING);
        Marble.add(SubTag.STONE, SubTag.NO_SMASHING);
        Basalt.add(SubTag.STONE, SubTag.NO_SMASHING);
        Obsidian.add(SubTag.STONE, SubTag.NO_SMASHING);
        Flint.add(SubTag.STONE, SubTag.NO_SMASHING, SubTag.MORTAR_GRINDABLE);
        GraniteRed.add(SubTag.STONE, SubTag.NO_SMASHING);
        GraniteBlack.add(SubTag.STONE, SubTag.NO_SMASHING);
        Salt.add(SubTag.STONE, SubTag.NO_SMASHING);
        RockSalt.add(SubTag.STONE, SubTag.NO_SMASHING);

        Sand.add(SubTag.NO_RECYCLING);

        Gold.add(SubTag.MORTAR_GRINDABLE);
        Silver.add(SubTag.MORTAR_GRINDABLE);
        Iron.add(SubTag.MORTAR_GRINDABLE);
        IronMagnetic.add(SubTag.MORTAR_GRINDABLE);
        Steel.add(SubTag.MORTAR_GRINDABLE);
        SteelMagnetic.add(SubTag.MORTAR_GRINDABLE);
        Zinc.add(SubTag.MORTAR_GRINDABLE);
        Antimony.add(SubTag.MORTAR_GRINDABLE);
        Copper.add(SubTag.MORTAR_GRINDABLE);
        AnnealedCopper.add(SubTag.MORTAR_GRINDABLE);
        Bronze.add(SubTag.MORTAR_GRINDABLE);
        Nickel.add(SubTag.MORTAR_GRINDABLE);
        Invar.add(SubTag.MORTAR_GRINDABLE);
        Brass.add(SubTag.MORTAR_GRINDABLE);
        WroughtIron.add(SubTag.MORTAR_GRINDABLE);
        Electrum.add(SubTag.MORTAR_GRINDABLE);
        Clay.add(SubTag.MORTAR_GRINDABLE);

        Glass.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_RECYCLING, SubTag.SMELTING_TO_FLUID);
        Diamond.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.FLAMMABLE);
        Emerald.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Amethyst.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Tanzanite.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Topaz.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Amber.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        GreenSapphire.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Sapphire.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Ruby.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        FoolsRuby.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Opal.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Olivine.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Jasper.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        GarnetRed.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        GarnetYellow.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Crystal.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Apatite.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE);
        Lapis.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE);
        Sodalite.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE);
        Lazurite.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE);
        Monazite.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE);
        Quartzite.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE, SubTag.QUARTZ);
        Quartz.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE, SubTag.QUARTZ);
        SiliconDioxide.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE, SubTag.QUARTZ);
        Dilithium.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE, SubTag.QUARTZ);
        NetherQuartz.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE, SubTag.QUARTZ);
        CertusQuartz.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE, SubTag.QUARTZ);
        Fluix.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE, SubTag.QUARTZ);
        Phosphorus.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.FLAMMABLE, SubTag.EXPLOSIVE);
        Phosphate.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.FLAMMABLE, SubTag.EXPLOSIVE);
        InfusedAir.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
        InfusedFire.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
        InfusedEarth.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
        InfusedWater.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
        InfusedEntropy.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
        InfusedOrder.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
        InfusedVis.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
        InfusedDull.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
        NetherStar.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
        EnderPearl.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.PEARL);
        EnderEye.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.PEARL);
        Firestone.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE, SubTag.MAGICAL, SubTag.QUARTZ, SubTag.UNBURNABLE, SubTag.BURNING);
        Magic.add(SubTag.CRYSTAL, SubTag.MAGICAL, SubTag.UNBURNABLE);

        Primitive.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Basic.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Good.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Advanced.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Data.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Elite.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Master.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Ultimate.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Superconductor.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Infinite.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);

        Blaze.add(SubTag.MAGICAL, SubTag.NO_SMELTING, SubTag.SMELTING_TO_FLUID, SubTag.MORTAR_GRINDABLE, SubTag.UNBURNABLE, SubTag.BURNING);
        Thaumium.add(SubTag.MAGICAL);
        Enderium.add(SubTag.MAGICAL);
        Mithril.add(SubTag.MAGICAL);
    }

    public static void init() {
        new ProcessingConfig();
        if (!GT_Mod.gregtechproxy.mEnableAllMaterials) new ProcessingModSupport();
        for (IMaterialHandler aRegistrator : mMaterialHandlers) {
            aRegistrator.onMaterialsInit(); //This is where addon mods can add/manipulate materials
        }
        initMaterialProperties(); //No more material addition or manipulation should be done past this point!
        MATERIALS_ARRAY = MATERIALS_MAP.values().toArray(new Materials[MATERIALS_MAP.size()]); //Generate standard object array. This is a lot faster to loop over.
        VALUES = Arrays.asList(MATERIALS_ARRAY);
        if (!GT_Mod.gregtechproxy.mEnableAllComponents) OrePrefixes.initMaterialComponents();
        for (Materials aMaterial : MATERIALS_ARRAY) {
            if (aMaterial.mMetaItemSubID >= 0) {
                if (aMaterial.mMetaItemSubID < 1000) {
                    if (aMaterial.mHasParentMod) {
                        if (GregTech_API.sGeneratedMaterials[aMaterial.mMetaItemSubID] == null) {
                            GregTech_API.sGeneratedMaterials[aMaterial.mMetaItemSubID] = aMaterial;
                        } else throw new IllegalArgumentException("The Material Index " + aMaterial.mMetaItemSubID + " for " + aMaterial.mName + " is already used!");
                    }
                } else throw new IllegalArgumentException("The Material Index " + aMaterial.mMetaItemSubID + " for " + aMaterial.mName + " is/over the maximum of 1000");
            }
        }
    }

    public static void initMaterialProperties() {
        GT_Mod.gregtechproxy.mChangeHarvestLevels = GregTech_API.sMaterialProperties.get("harvestlevel", "ActivateHarvestLevelChange", false);
        GT_Mod.gregtechproxy.mMaxHarvestLevel = Math.min(15, GregTech_API.sMaterialProperties.get("harvestlevel", "MaxHarvestLevel",7));
        GT_Mod.gregtechproxy.mGraniteHavestLevel = GregTech_API.sMaterialProperties.get("harvestlevel", "GraniteHarvestLevel", 3);
        StringBuilder aConfigPathSB = new StringBuilder();
        for (Materials aMaterial : MATERIALS_MAP.values()) { /** The only place where MATERIALS_MAP should be used to loop over all materials. **/
            if (aMaterial != null && aMaterial != Materials._NULL && aMaterial != Materials.Empty) {
                aConfigPathSB.append("materials.").append(aMaterial.mConfigSection).append(".").append(aMaterial.mCustomOre ? aMaterial.mCustomID : aMaterial.mName);
                String aConfigPath = aConfigPathSB.toString();
                aMaterial.mMetaItemSubID = GregTech_API.sMaterialProperties.get(aConfigPath, "MaterialID", aMaterial.mCustomOre ? -1 : aMaterial.mMetaItemSubID);
                aMaterial.mDefaultLocalName = GregTech_API.sMaterialProperties.get(aConfigPath, "MaterialName", aMaterial.mCustomOre ? "CustomOre" + aMaterial.mCustomID : aMaterial.mDefaultLocalName);
                aMaterial.mMeltingPoint = (short) GregTech_API.sMaterialProperties.get(aConfigPath, "MeltingPoint", aMaterial.mMeltingPoint);
                aMaterial.mBlastFurnaceRequired = GregTech_API.sMaterialProperties.get(aConfigPath, "BlastFurnaceRequired", aMaterial.mBlastFurnaceRequired);
                aMaterial.mBlastFurnaceTemp = (short) GregTech_API.sMaterialProperties.get(aConfigPath, "BlastFurnaceTemp", aMaterial.mBlastFurnaceTemp);
                if (GT_Mod.gregtechproxy.mTEMachineRecipes && aMaterial.mBlastFurnaceRequired && aMaterial.mBlastFurnaceTemp < 1500) GT_ModHandler.ThermalExpansion.addSmelterBlastOre(aMaterial);
                aMaterial.mFuelPower = GregTech_API.sMaterialProperties.get(aConfigPath, "FuelPower", aMaterial.mFuelPower);
                aMaterial.mFuelType = GregTech_API.sMaterialProperties.get(aConfigPath, "FuelType", aMaterial.mFuelType);
                //aMaterial.mOreValue = GregTech_API.sMaterialProperties.get(aConfigPath, "OreValue", aMaterial.mOreValue);
                //aMaterial.mDensityMultiplier = GregTech_API.sMaterialProperties.get(aConfigPath, "DensityMultiplier", aMaterial.mDensityMultiplier);
                //aMaterial.mDensityDivider = GregTech_API.sMaterialProperties.get(aConfigPath, "DensityDivider", aMaterial.mDensityDivider);
                //aMaterial.mDensity = (long) GregTech_API.sMaterialProperties.get(aConfigPath, "Density", (M * aMaterial.mDensityMultiplier) / aMaterial.mDensityDivider);
                aMaterial.mDurability = GregTech_API.sMaterialProperties.get(aConfigPath, "ToolDurability", aMaterial.mDurability);
                aMaterial.mToolSpeed = (float) GregTech_API.sMaterialProperties.get(aConfigPath, "ToolSpeed", aMaterial.mToolSpeed);
                aMaterial.mToolQuality = (byte) GregTech_API.sMaterialProperties.get(aConfigPath, "ToolQuality", aMaterial.mToolQuality);
                //aMaterial.mIconSet = TextureSet.valueOf(GregTech_API.sMaterialProperties.get(aConfigPath.toString(), "IconSet", aMaterial.mIconSet.mSetName));
                aMaterial.mTransparent = GregTech_API.sMaterialProperties.get(aConfigPath, "Transparent", aMaterial.mTransparent);
                String aColor = GregTech_API.sMaterialProperties.get(aConfigPath, "DyeColor", aMaterial.mColor == Dyes.dyeNULL ? "None" : aMaterial.mColor.toString());
                aMaterial.mColor = aColor.equals("None") ? Dyes.dyeNULL : Dyes.get(aColor);
                String[] aRGBA = GregTech_API.sMaterialProperties.get(aConfigPath, "MatRGBA", String.valueOf(aMaterial.mRGBa[0] + "," + aMaterial.mRGBa[1] + "," + aMaterial.mRGBa[2] + "," + aMaterial.mRGBa[3] + ",")).split(",");
                aMaterial.mRGBa[0] = Short.parseShort(aRGBA[0]);
                aMaterial.mRGBa[1] = Short.parseShort(aRGBA[1]);
                aMaterial.mRGBa[2] = Short.parseShort(aRGBA[2]);
                aMaterial.mRGBa[3] = Short.parseShort(aRGBA[3]);
                aMaterial.mTypes = GregTech_API.sMaterialProperties.get(aConfigPath, "MaterialTypes", aMaterial.mCustomOre ? 1|2|4|8|16|32|64|128 : aMaterial.mTypes);
                aMaterial.mUnificatable = GregTech_API.sMaterialProperties.get(aConfigPath, "Unificatable", aMaterial.mUnificatable);
                aMaterial.mChemicalFormula = GregTech_API.sMaterialProperties.get(aConfigPath, "ChemicalFormula", aMaterial.mChemicalFormula);
                aMaterial.mGasTemp = (short) GregTech_API.sMaterialProperties.get(aConfigPath, "GasTemp", aMaterial.mGasTemp);
                aMaterial.setOreMultiplier(GregTech_API.sMaterialProperties.get(aConfigPath, "OreMultiplier", aMaterial.mOreMultiplier));
                aMaterial.setSmeltingMultiplier(GregTech_API.sMaterialProperties.get(aConfigPath, "OreSmeltingMultiplier", aMaterial.mSmeltingMultiplier));
                aMaterial.setByProductMultiplier(GregTech_API.sMaterialProperties.get(aConfigPath, "OreByProductMultiplier", aMaterial.mByProductMultiplier));
                aMaterial.setHeatDamage((float) GregTech_API.sMaterialProperties.get(aConfigPath, "HeatDamage", aMaterial.mHeatDamage));
                aMaterial.mSmeltInto = MATERIALS_MAP.get(GregTech_API.sMaterialProperties.get(aConfigPath, "MaterialSmeltInto", aMaterial.mSmeltInto.mName));
                aMaterial.mMacerateInto = MATERIALS_MAP.get(GregTech_API.sMaterialProperties.get(aConfigPath, "MaterialMacerateInto", aMaterial.mMacerateInto.mName));
                aMaterial.mArcSmeltInto = MATERIALS_MAP.get(GregTech_API.sMaterialProperties.get(aConfigPath, "MaterialArcSmeltInto", aMaterial.mArcSmeltInto.mName));
                aMaterial.mDirectSmelting = MATERIALS_MAP.get(GregTech_API.sMaterialProperties.get(aConfigPath, "MaterialDirectSmeltInto", aMaterial.mDirectSmelting.mName));
                aMaterial.mHasParentMod = GregTech_API.sMaterialProperties.get(aConfigPath, "HasParentMod", aMaterial.mHasParentMod);
                if (aMaterial.mHasPlasma = GregTech_API.sMaterialProperties.get(aConfigPath, "AddPlasma", aMaterial.mHasPlasma)) GT_Mod.gregtechproxy.addAutogeneratedPlasmaFluid(aMaterial);
                if (aMaterial.mHasGas = GregTech_API.sMaterialProperties.get(aConfigPath, "AddGas", aMaterial.mHasGas)) GT_Mod.gregtechproxy.addFluid(aMaterial.mName.toLowerCase(), aMaterial.mDefaultLocalName, aMaterial, 2, aMaterial.mGasTemp);
                aMaterial.mEnchantmentToolsLevel = (byte) GregTech_API.sMaterialProperties.get(aConfigPath, "EnchantmentLevel", aMaterial.mEnchantmentToolsLevel);
                String aEnchantmentName = GregTech_API.sMaterialProperties.get(aConfigPath, "Enchantment", aMaterial.mEnchantmentTools != null ? aMaterial.mEnchantmentTools.getName() : "");
                if (aMaterial.mEnchantmentTools != null && !aEnchantmentName.equals(aMaterial.mEnchantmentTools.getName())) {
                    for (int i = 0; i < Enchantment.enchantmentsList.length; i++) {
                        if (aEnchantmentName.equals(Enchantment.enchantmentsList[i].getName())) aMaterial.mEnchantmentTools = Enchantment.enchantmentsList[i];
                    }
                }
                /**
                 * Converts the pre-defined list of SubTags from a material into a list of SubTag names for setting/getting to/from the config.
                 * It is then converted to a String[] and finally to a singular String for insertion into the config
                 * If the config string is different from the default, we then want to clear the Materials SubTags and insert new ones from the config string.
                 */
                List<String> aSubTags = new ArrayList<>();
                for (SubTag aTag : aMaterial.mSubTags) aSubTags.add(aTag.mName);
                String aDefaultTagString = "," + aSubTags.toString().replace(" ", "").replace("[", "").replace("]", "");
                String aConfigTagString = GregTech_API.sMaterialProperties.get(aConfigPath, "ListSubTags", aDefaultTagString);
                if (!aConfigTagString.equals(aDefaultTagString)) {
                    aMaterial.mSubTags.clear();
                    if (aConfigTagString.length() > 0) {
                        aSubTags = new ArrayList<>(Arrays.asList(aConfigTagString.split(",")));
                        for (String aTagString : aSubTags) {
                            SubTag aTag = SubTag.sSubTags.get(aTagString);
                            if (aTag != null) aMaterial.mSubTags.add(aTag);
                        }
                    }
                }
                /** Same principal as SubTags **/
                List<String> aOreByProducts = new ArrayList<>();
                for (Materials aMat : aMaterial.mOreByProducts) aOreByProducts.add(aMat.mName);
                String aDefaultMatByProString = "," + aOreByProducts.toString().replace(" ", "").replace("[", "").replace("]", "");
                String aConfigMatByProString = GregTech_API.sMaterialProperties.get(aConfigPath, "ListMaterialByProducts", aDefaultMatByProString);
                if (!aConfigMatByProString.equals(aDefaultMatByProString)) {
                    aMaterial.mOreByProducts.clear();
                    if (aConfigMatByProString.length() > 0) {
                        aOreByProducts = new ArrayList<>(Arrays.asList(aConfigMatByProString.split(",")));
                        for (String aMaterialString : aOreByProducts) {
                            Materials aMat = MATERIALS_MAP.get(aMaterialString);
                            if (aMat != null) aMaterial.mOreByProducts.add(aMat);
                        }
                    }
                }
                /** Same principal as SubTags **/
                List<String> aOreReRegistrations = new ArrayList<>();
                for (Materials aMat : aMaterial.mOreReRegistrations) aOreReRegistrations.add(aMat.mName);
                String aDefaultMatReRegString = "," + aOreReRegistrations.toString().replace(" ", "").replace("[", "").replace("]", "");
                String aConfigMatMatReRegString = GregTech_API.sMaterialProperties.get(aConfigPath, "ListMaterialReRegistrations", aDefaultMatReRegString);
                if (!aConfigMatMatReRegString.equals(aDefaultMatReRegString)) {
                    aMaterial.mOreReRegistrations.clear();
                    if (aConfigMatMatReRegString.length() > 0) {
                        aOreReRegistrations = new ArrayList<>(Arrays.asList(aConfigMatMatReRegString.split(",")));
                        for (String aMaterialString : aOreReRegistrations) {
                            Materials aMat = MATERIALS_MAP.get(aMaterialString);
                            if (aMat != null) aMaterial.mOreReRegistrations.add(aMat);
                        }
                    }
                }
                /** Same principal as SubTags but with two values **/
                List<String> aAspects = new ArrayList<>();
                ArrayList<String> aAspectAmounts = new ArrayList<>();
                for (AspectStack aAspectStack : aMaterial.mAspects) {
                    aAspects.add(aAspectStack.mAspect.toString());
                    aAspectAmounts.add(String.valueOf(aAspectStack.mAmount));
                }
                String aDefaultAspectString = "," + aAspects.toString().replace(" ", "").replace("[", "").replace("]", "");
                String aDefaultAspectAmountString = "," + aAspectAmounts.toString().replace(" ", "").replace("[", "").replace("]", "");
                String aConfigAspectString = GregTech_API.sMaterialProperties.get(aConfigPath, "ListTCAspects", aDefaultAspectString);
                String aConfigAspectAmountString = GregTech_API.sMaterialProperties.get(aConfigPath, "ListTCAspectAmounts", aDefaultAspectAmountString);
                if (!aConfigAspectString.equals(aDefaultAspectString) || !aConfigAspectAmountString.equals(aDefaultAspectAmountString)) {
                    aMaterial.mAspects.clear();
                    if (aConfigAspectString.length() > 0) {
                        aAspects = new ArrayList<>(Arrays.asList(aConfigAspectString.split(",")));
                        for (int i = 0; i < aAspects.size(); i++) {
                            String aAspectString = aAspects.get(i);
                            long aAspectAmount = Long.parseLong(aAspectAmounts.get(i));
                            AspectStack aAspectStack = new AspectStack(Aspects.valueOf(aAspectString), aAspectAmount);
                            if (aAspectStack != null) aMaterial.mAspects.add(aAspectStack);
                        }
                    }
                }
                /** Moved the harvest level changes from GT_Mod to have less things iterating over MATERIALS_ARRAY **/
                if (GT_Mod.gregtechproxy.mChangeHarvestLevels && aMaterial.mToolQuality > 0 && aMaterial.mMetaItemSubID < GT_Mod.gregtechproxy.mHarvestLevel.length && aMaterial.mMetaItemSubID >= 0) {
                    GT_Mod.gregtechproxy.mHarvestLevel[aMaterial.mMetaItemSubID] = GregTech_API.sMaterialProperties.get(aConfigPath, "HarvestLevel", aMaterial.mToolQuality);
                }
                /** Moved from GT_Proxy? (Not sure)**/
                aMaterial.mHandleMaterial = (aMaterial == Desh ? aMaterial.mHandleMaterial : aMaterial == Diamond || aMaterial == Thaumium ? Wood : aMaterial.contains(SubTag.BURNING) ? Blaze : aMaterial.contains(SubTag.MAGICAL) && aMaterial.contains(SubTag.CRYSTAL) && Loader.isModLoaded(GT_Values.MOD_ID_TC) ? Thaumium : aMaterial.getMass() > Element.Tc.getMass() * 2 ? TungstenSteel : aMaterial.getMass() > Element.Tc.getMass() ? Steel : Wood);
            }
            aConfigPathSB.setLength(0);
        }
    }

    public Materials(int metaItemSubId, String defaultLocalName, int r, int g, int b, int a, Dyes color, TextureSet materialIconSet) {
        mMetaItemSubID = metaItemSubId;
        mName = defaultLocalName.replace(" ", "").replace("-", "");
        mDefaultLocalName = defaultLocalName;
        mRGBa[0] = (short) r;
        mRGBa[1] = (short) g;
        mRGBa[2] = (short) b;
        mRGBa[3] = (short) a;
        mColor = color;
        mIconSet = materialIconSet;
        if (mColor != null) add(SubTag.HAS_COLOR);
        MATERIALS_MAP.put(mName, this);
    }

    public Materials(int metaItemSubId, String defaultLocalName, int r, int g, int b, Dyes color, TextureSet materialIconSet) {
        this(metaItemSubId, defaultLocalName, r, g, b, 0, color, materialIconSet);
    }

    public Materials(int metaItemSubId, String defaultLocalName, int r, int g, int b, int a, Dyes color, TextureSet materialIconSet, Element element) {
        this(metaItemSubId, defaultLocalName, r, g, b, a, color, materialIconSet);
        mElement = element;
        addFlags(PLASMA);
    }

    public Materials(int metaItemSubId, String defaultLocalName, int r, int g, int b, Dyes color, TextureSet materialIconSet, Element element) {
        this(metaItemSubId, defaultLocalName, r, g, b, 0, color, materialIconSet, element);
    }

    public Materials(String defaultLocalName, TextureSet materialIconSet, boolean unificatable) {
        this(-1, defaultLocalName, 255, 255, 255, 0, dyeNULL, materialIconSet);
        mUnificatable = unificatable;
    }

    public Materials(String defaultLocalName) {
        this(-1, defaultLocalName, 255, 255, 255, 0, dyeNULL, NONE);
    }

    public enum MaterialType {
        REF, //SOLID, METAL
    }

    public Materials asFluid(int... fuelPower) {
        hasCorrespondingFluid = true;
        mTypes = mTypes | CELL.value;
        if (fuelPower.length >= 1) mFuelPower = fuelPower[0];
        mIconSet = FLUID;
        return this;
    }

    public Materials asSemi(int fuelPower) {
        asFluid(fuelPower);
        mFuelType = 3;
        return this;
    }

    public Materials asDiesel(int fuelPower) {
        asFluid(fuelPower);
        mFuelType = 0;
        return this;
    }

    public Materials asGas(int... fuelPower) {
        hasCorrespondingGas = true;
        mTypes = mTypes | CELL.value;
        if (fuelPower.length >= 1) mFuelPower = fuelPower[0];
        mFuelType = 1;
        mIconSet = FLUID;
        return this;
    }

    public Materials asDust(int... temps) { //Dust, SmallDust, TinyDust
        //asFluid();
        mTypes = mTypes | DUST.value;
        if (temps.length >= 1) mMeltingPoint = (short) temps[0];
        return this;
    }

    public Materials asSolid(int... temps) { //Ingot, Nugget
        asDust();
        mTypes = mTypes | SOLID.value;
        if (temps.length >= 1) mMeltingPoint = (short) temps[0];
        if (temps.length >= 2) {
            mBlastFurnaceRequired = temps[1] >= 1000;
            mBlastFurnaceTemp = (short) temps[1];
        }
        add(SubTag.SMELTING_TO_FLUID);
        return this;
    }

    public Materials asMetal(int... temps) {
        asSolid(temps);
        mSubTags.add(SubTag.METAL);
        return this;
    }

    public Materials asGem(boolean transparent) {
        asDust();
        mTypes = mTypes | GEM.value;
        mTransparent = transparent;
        if (transparent) add(SubTag.TRANSPARENT);
        return this;
    }

    public Materials asDustOre(int... temps) {
        asDust(temps);
        mTypes = mTypes | ORE.value;
        return this;
    }

    public Materials asSolidOre(int... temps) {
        asSolid(temps);
        mTypes = mTypes | ORE.value;
        return this;
    }
    
    public Materials asGemOre(boolean transparent) {
        asGem(transparent);
        mTypes = mTypes | ORE.value;
        return this;
    }

    public Materials asRef() {
        mMatType = MaterialType.REF;
        return this;
    }

    public Materials addMats(MaterialStack... materials) {
        mMaterialList = Arrays.asList(materials);
        return this;
    }

    public Materials addAspects(AspectStack... aspects) {
        mAspects = Arrays.asList(aspects);
        return this;
    }

    public Materials addFlags(MaterialFlags... flags) {
        for (MaterialFlags flag : flags) {
            mTypes = mTypes | flag.value;
        }
        return this;
    }

    public Materials addTools(float toolSpeed, int toolDurability, int toolQuality) {
        mTypes = mTypes | 64;
        mToolSpeed = toolSpeed;
        mDurability = toolDurability;
        mToolQuality = (byte) toolQuality;
        return this;
    }

    public Materials setTemp(int temp, int fluidType) {
        switch (fluidType) {
            case 0: mMeltingPoint = (short) temp;
            case 1: mGasTemp = (short) temp;
        }
        return this;
    }

    public Materials setName(String name) {
        mName = name;
        return this;
    }

    /**
     * This is for keeping compatibility with addons mods (Such as TinkersGregworks etc) that looped over the old materials enum
     */
    @Deprecated
    public String name() {
        return mName;
    }

    /**
     * This is for keeping compatibility with addons mods (Such as TinkersGregworks etc) that looped over the old materials enum
     */
    @Deprecated
    public static Materials valueOf(String aMaterialName) {
        return getMaterialsMap().get(aMaterialName);
    }

    /**
     * This is for keeping compatibility with addons mods (Such as TinkersGregworks etc) that looped over the old materials enum
     */
    public static Materials[] values() {
        return MATERIALS_ARRAY;
    }

    /**
     * This should only be used for getting a Material by its name as a String. Do not loop over this map, use values().
     */
    public static Map<String, Materials> getMaterialsMap() {
        return MATERIALS_MAP;
    }

    public static Materials get(String aMaterialName) {
        Materials aMaterial = getMaterialsMap().get(aMaterialName);
        if (aMaterial != null) return aMaterial;
        return Materials._NULL;
    }

    public static Materials getRealMaterial(String aMaterialName) {
        return get(aMaterialName).mMaterialInto;
    }

    public boolean isRadioactive() {
        if (mElement != null) return mElement.mHalfLifeSeconds >= 0;
        for (MaterialStack tMaterial : mMaterialList) if (tMaterial.mMaterial.isRadioactive()) return true;
        return false;
    }

    public long getProtons() {
        if (mElement != null) return mElement.getProtons();
        if (mMaterialList.size() <= 0) return Element.Tc.getProtons();
        long rAmount = 0, tAmount = 0;
        for (MaterialStack tMaterial : mMaterialList) {
            tAmount += tMaterial.mAmount;
            rAmount += tMaterial.mAmount * tMaterial.mMaterial.getProtons();
        }
        return (getDensity() * rAmount) / (tAmount * M);
    }

    public long getNeutrons() {
        if (mElement != null) return mElement.getNeutrons();
        if (mMaterialList.size() <= 0) return Element.Tc.getNeutrons();
        long rAmount = 0, tAmount = 0;
        for (MaterialStack tMaterial : mMaterialList) {
            tAmount += tMaterial.mAmount;
            rAmount += tMaterial.mAmount * tMaterial.mMaterial.getNeutrons();
        }
        return (getDensity() * rAmount) / (tAmount * M);
    }

    public long getMass() {
        if (mElement != null) return mElement.getMass();
        if (mMaterialList.size() <= 0) return Element.Tc.getMass();
        long rAmount = 0, tAmount = 0;
        for (MaterialStack tMaterial : mMaterialList) {
            tAmount += tMaterial.mAmount;
            rAmount += tMaterial.mAmount * tMaterial.mMaterial.getMass();
        }
        return (getDensity() * rAmount) / (tAmount * M);
    }

    public long getDensity() {
        return mDensity;
    }

    public String getToolTip() {
        return getToolTip(1, false);
    }

    public String getToolTip(boolean aShowQuestionMarks) {
        return getToolTip(1, aShowQuestionMarks);
    }

    public String getToolTip(long aMultiplier) {
        return getToolTip(aMultiplier, false);
    }

    public String getToolTip(long aMultiplier, boolean aShowQuestionMarks) {
        if (!aShowQuestionMarks && mChemicalFormula.equals("?")) return "";
        if (aMultiplier >= M * 2 && !mMaterialList.isEmpty()) {
            return ((mElement != null || (mMaterialList.size() < 2 && mMaterialList.get(0).mAmount == 1)) ? mChemicalFormula : "(" + mChemicalFormula + ")") + aMultiplier;
        }
        return mChemicalFormula;
    }

    /**
     * Adds a Class implementing IMaterialRegistrator to the master list
     */
    public static boolean add(IMaterialHandler aRegistrator) {
        if (aRegistrator == null) return false;
        return mMaterialHandlers.add(aRegistrator);
    }

    /**
     * Adds an ItemStack to this Material.
     */
    public Materials add(ItemStack aStack) {
        if (aStack != null && !contains(aStack)) mMaterialItems.add(aStack);
        return this;
    }

    /**
     * This is used to determine if any of the ItemStacks belongs to this Material.
     */
    public boolean contains(ItemStack... aStacks) {
        if (aStacks == null || aStacks.length <= 0) return false;
        for (ItemStack tStack : mMaterialItems)
            for (ItemStack aStack : aStacks)
                if (GT_Utility.areStacksEqual(aStack, tStack, !tStack.hasTagCompound())) return true;
        return false;
    }

    /**
     * This is used to determine if an ItemStack belongs to this Material.
     */
    public boolean remove(ItemStack aStack) {
        if (aStack == null) return false;
        boolean temp = false;
        int mMaterialItems_sS=mMaterialItems.size();
        for (int i = 0; i < mMaterialItems_sS; i++)
            if (GT_Utility.areStacksEqual(aStack, mMaterialItems.get(i))) {
                mMaterialItems.remove(i--);
                temp = true;
            }
        return temp;
    }

    /**
     * Adds a SubTag to this Material
     */
    @Override
    public ISubTagContainer add(SubTag... aTags) {
        if (aTags != null) for (SubTag aTag : aTags)
            if (aTag != null && !contains(aTag)) {
                aTag.addContainerToList(this);
                mSubTags.add(aTag);
            }
        return this;
    }

    /**
     * If this Material has this exact SubTag
     */
    @Override
    public boolean contains(SubTag aTag) {
        return mSubTags.contains(aTag);
    }

    /**
     * Removes a SubTag from this Material
     */
    @Override
    public boolean remove(SubTag aTag) {
        return mSubTags.remove(aTag);
    }

    /**
     * Sets the Heat Damage for this Material (negative = frost)
     */
    public Materials setHeatDamage(float aHeatDamage) {
        mHeatDamage = aHeatDamage;
        return this;
    }

    /**
     * Adds a Material to the List of Byproducts when grinding this Ore.
     * Is used for more precise Ore grinding, so that it is possible to choose between certain kinds of Materials.
     */
    public Materials addOreByProduct(Materials aMaterial) {
        if (!mOreByProducts.contains(aMaterial.mMaterialInto)) mOreByProducts.add(aMaterial.mMaterialInto);
        return this;
    }

    /**
     * Adds multiple Materials to the List of Byproducts when grinding this Ore.
     * Is used for more precise Ore grinding, so that it is possible to choose between certain kinds of Materials.
     */
    public Materials addOreByProducts(Materials... aMaterials) {
        for (Materials tMaterial : aMaterials) if (tMaterial != null) addOreByProduct(tMaterial);
        return this;
    }

    /**
     * If this Ore gives multiple drops of its Main Material.
     * Lapis Ore for example gives about 6 drops.
     */
    public Materials setOreMultiplier(int aOreMultiplier) {
        if (aOreMultiplier > 0) mOreMultiplier = aOreMultiplier;
        return this;
    }

    /**
     * If this Ore gives multiple drops of its Byproduct Material.
     */
    public Materials setByProductMultiplier(int aByProductMultiplier) {
        if (aByProductMultiplier > 0) mByProductMultiplier = aByProductMultiplier;
        return this;
    }

    /**
     * If this Ore gives multiple drops of its Main Material.
     * Lapis Ore for example gives about 6 drops.
     */
    public Materials setSmeltingMultiplier(int aSmeltingMultiplier) {
        if (aSmeltingMultiplier > 0) mSmeltingMultiplier = aSmeltingMultiplier;
        return this;
    }

    /**
     * This Ore should be smolten directly into an Ingot of this Material instead of an Ingot of itself.
     */
    public Materials setDirectSmelting(Materials aMaterial) {
        if (aMaterial != null) mDirectSmelting = aMaterial.mMaterialInto.mDirectSmelting;
        return this;
    }

    /**
     * This Material should be the Main Material this Ore gets ground into.
     * Example, Chromite giving Chrome or Tungstate giving Tungsten.
     */
    public Materials setOreReplacement(Materials aMaterial) {
        if (aMaterial != null) mOreReplacement = aMaterial.mMaterialInto.mOreReplacement;
        return this;
    }

    /**
     * This Material smelts always into an instance of aMaterial. Used for Magnets.
     */
    public Materials setSmeltingInto(Materials aMaterial) {
        if (aMaterial != null) mSmeltInto = aMaterial.mMaterialInto.mSmeltInto;
        return this;
    }

    /**
     * This Material arc smelts always into an instance of aMaterial. Used for Wrought Iron.
     */
    public Materials setArcSmeltingInto(Materials aMaterial) {
        if (aMaterial != null) mArcSmeltInto = aMaterial.mMaterialInto.mArcSmeltInto;
        return this;
    }

    /**
     * This Material macerates always into an instance of aMaterial.
     */
    public Materials setMaceratingInto(Materials aMaterial) {
        if (aMaterial != null) mMacerateInto = aMaterial.mMaterialInto.mMacerateInto;
        return this;
    }

    public Materials setEnchantmentForTools(Enchantment aEnchantment, int aEnchantmentLevel) {
        mEnchantmentTools = aEnchantment;
        mEnchantmentToolsLevel = (byte) aEnchantmentLevel;
        return this;
    }

    public Materials setEnchantmentForArmors(Enchantment aEnchantment, int aEnchantmentLevel) {
        mEnchantmentArmors = aEnchantment;
        mEnchantmentArmorsLevel = (byte) aEnchantmentLevel;
        return this;
    }

    public FluidStack getSolid(long aAmount) {
        if (mSolid == null) return null;
        return new GT_FluidStack(mSolid, (int) aAmount);
    }

    public FluidStack getFluid(long aAmount) {
        if (mFluid == null) return null;
        return new GT_FluidStack(mFluid, (int) aAmount);
    }

    public FluidStack getGas(long aAmount) {
        if (mGas == null) return null;
        return new GT_FluidStack(mGas, (int) aAmount);
    }

    public FluidStack getPlasma(long aAmount) {
        if (mPlasma == null) return null;
        return new GT_FluidStack(mPlasma, (int) aAmount);
    }

    public FluidStack getMolten(long aAmount) {
        if (mStandardMoltenFluid == null) return null;
        return new GT_FluidStack(mStandardMoltenFluid, (int) aAmount);
    }

    @Override
    public short[] getRGBA() {
        return mRGBa;
    }

    @Override
    public String toString() {
        return this.mName;
    }

    public static volatile int VERSION = 509;

    public static Collection<Materials> getAll(){
        return MATERIALS_MAP.values();
    }

    public boolean hasCorrespondingFluid() {
        return hasCorrespondingFluid;
    }

    public Materials setHasCorrespondingFluid(boolean hasCorrespondingFluid) {
        this.hasCorrespondingFluid = hasCorrespondingFluid;
        return this;
    }

    public boolean hasCorrespondingGas() {
        return hasCorrespondingGas;
    }

    public Materials setHasCorrespondingGas(boolean hasCorrespondingGas) {
        this.hasCorrespondingGas = hasCorrespondingGas;
        return this;
    }

    public boolean canBeCracked() {
        return canBeCracked;
    }

    public Materials setHydroCrackedFluids(Fluid[] hydroCrackedFluids) {
        this.hydroCrackedFluids = hydroCrackedFluids;
        return this;
    }

    public FluidStack getLightlyHydroCracked(int amount) {
        if (hydroCrackedFluids[0] == null) {
            return null;
        }
        return new FluidStack(hydroCrackedFluids[0], amount);
    }

    public FluidStack getModeratelyHydroCracked(int amount) {
        if (hydroCrackedFluids[0] == null) {
            return null;
        }
        return new FluidStack(hydroCrackedFluids[1], amount);
    }

    public FluidStack getSeverelyHydroCracked(int amount) {
        if (hydroCrackedFluids[0] == null) {
            return null;
        }
        return new FluidStack(hydroCrackedFluids[2], amount);
    }

    public Materials setSteamCrackedFluids(Fluid[] steamCrackedFluids) {
        this.steamCrackedFluids = steamCrackedFluids;
        return this;
    }

    public FluidStack getLightlySteamCracked(int amount) {
        if (hydroCrackedFluids[0] == null) {
            return null;
        }
        return new FluidStack(steamCrackedFluids[0], amount);
    }

    public FluidStack getModeratelySteamCracked(int amount) {
        if (hydroCrackedFluids[0] == null) {
            return null;
        }
        return new FluidStack(steamCrackedFluids[1], amount);
    }

    public FluidStack getSeverelySteamCracked(int amount) {
        if (hydroCrackedFluids[0] == null) {
            return null;
        }
        return new FluidStack(steamCrackedFluids[2], amount);
    }

    public int getLiquidTemperature() {
		return mMeltingPoint == 0 ? 295 : mMeltingPoint;
	}

    public int getGasTemperature() {
        return mGasTemp == 0 ? 295 : mMeltingPoint;
    }

    public ItemStack getCells(int amount){
        return GT_OreDictUnificator.get(OrePrefixes.cell, this, amount);
    }

    public ItemStack getDust(int amount){
        return GT_OreDictUnificator.get(OrePrefixes.dust, this, amount);
    }

    public ItemStack getDustSmall(int amount){
        return GT_OreDictUnificator.get(OrePrefixes.dustSmall, this, amount);
    }

    public ItemStack getDustTiny(int amount){
        return GT_OreDictUnificator.get(OrePrefixes.dustTiny, this, amount);
    }

    public ItemStack getGems(int amount){
        return GT_OreDictUnificator.get(OrePrefixes.gem, this, amount);
    }

    public ItemStack getIngots(int amount){
        return GT_OreDictUnificator.get(OrePrefixes.ingot, this, amount);
    }

    public ItemStack getBlocks(int amount){
        return GT_OreDictUnificator.get(OrePrefixes.block, this, amount);
    }

    public ItemStack getPlates(int amount){
        return GT_OreDictUnificator.get(OrePrefixes.plate, this, amount);
    }
}