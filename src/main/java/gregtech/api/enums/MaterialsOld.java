//package gregtech.api.enums;
//
//import cpw.mods.fml.common.Loader;
//import gregtech.GT_Mod;
//import gregtech.api.GregTech_API;
//import gregtech.api.enums.TC_Aspects.TC_AspectStack;
//import gregtech.api.interfaces.IColorModulationContainer;
//import gregtech.api.interfaces.IMaterialHandler;
//import gregtech.api.interfaces.ISubTagContainer;
//import gregtech.api.objects.GT_FluidStack;
//import gregtech.api.objects.MaterialStack;
//import gregtech.api.util.GT_ModHandler;
//import gregtech.api.util.GT_OreDictUnificator;
//import gregtech.api.util.GT_Utility;
//import gregtech.loaders.materialprocessing.ProcessingConfig;
//import gregtech.loaders.materialprocessing.ProcessingModSupport;
//import net.minecraft.enchantment.Enchantment;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.fluids.Fluid;
//import net.minecraftforge.fluids.FluidStack;
//
//import java.util.*;
//
//import static gregtech.api.enums.GT_Values.M;
//
//public class MaterialsOld implements IColorModulationContainer, ISubTagContainer {
//    private static MaterialsOld[] MaterialsOld_ALL = new MaterialsOld[]{};
//    private static final Map<String, MaterialsOld> MaterialsOld_MAP = new LinkedHashMap<String, MaterialsOld>();
//    public static final List<IMaterialHandler> mMaterialHandlers = new ArrayList<IMaterialHandler>();
//
//    /**
//     * This is for keeping compatibility with addons mods (Such as TinkersGregworks etc) that looped over the old MaterialsOld enum
//     */
//    @Deprecated
//    public static Collection<MaterialsOld> VALUES = new LinkedHashSet<MaterialsOld>();
//
//    /**
//     * This is the Default Material returned in case no Material has been found or a NullPointer has been inserted at a location where it shouldn't happen.
//     */
//    public static MaterialsOld dyeNULL = new MaterialsOld(-1, TextureSet.NONE, 1.0F, 0, 0, 0, 255, 255, 255, 0,	"NULL", "NULL", 0, 0, 0, 0, false, false, 1, 1, 1, Dyes.dyeNULL, Element.dyeNULL, Arrays.asList(new TC_AspectStack(TC_Aspects.VACUOS, 1)));
//
//    /**
//     * Direct Elements
//     */
//    public static MaterialsOld Aluminium = new MaterialsOld(19, TextureSet.DULL, 	   10.0F, 128, 2, 1|2|8|32|64|128, 128, 200, 240, 0, "Aluminium", "Aluminium", 0, 0, 933, 1700, true, false, 3, 1, 1, Dyes.dyeLightBlue, Element.Al, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.VOLATUS, 1)));
//    public static MaterialsOld Americium = new MaterialsOld(103, TextureSet.METALLIC, 1.0F, 0, 3, 1|2|32, 200, 200, 200, 0, "Americium", "Americium", 0, 0, 1449, 0, false, false, 3, 1, 1, Dyes.dyeLightGray, Element.Am, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.RADIO, 1)));
//    public static MaterialsOld Antimony = new MaterialsOld(58, TextureSet.SHINY, 		1.0F, 0, 2, 1|2|32, 220, 220, 240, 0, "Antimony", "Antimony", 0, 0, 903, 0, false, false, 2, 1, 1, Dyes.dyeLightGray, Element.Sb, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.AQUA, 1)));
//    public static MaterialsOld Argon = new MaterialsOld(24, TextureSet.FLUID, 		1.0F, 0, 2, 16|32, 0, 255, 0, 240, "Argon", "Argon", 0, 0, 83, 0, false, true, 5, 1, 1, Dyes.dyeGreen, Element.Ar, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.AER, 2)));
//    public static MaterialsOld Arsenic = new MaterialsOld(39, TextureSet.DULL, 		1.0F, 0, 2, 1|2|16|32, 255, 255, 255, 0, "Arsenic", "Arsenic", 0, 0, 1090, 0, false, false, 3, 1, 1, Dyes.dyeOrange, Element.As, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.VENENUM, 3)));
//    public static MaterialsOld Barium = new MaterialsOld(63, TextureSet.METALLIC, 	1.0F, 0, 2, 1|32, 255, 255, 255, 0, "Barium", "Barium", 0, 0, 1000, 0, false, false, 1, 1, 1, Dyes.dyeNULL, Element.Ba, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.VINCULUM, 3)));
//    public static MaterialsOld Beryllium = new MaterialsOld(8, TextureSet.METALLIC,  14.0F, 64, 2, 1|2|8|32|64, 100, 180, 100, 0, "Beryllium", "Beryllium", 0, 0, 1560, 0, false, false, 6, 1, 1, Dyes.dyeGreen, Element.Be, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.LUCRUM, 1)));
//    public static MaterialsOld Bismuth = new MaterialsOld(90, TextureSet.METALLIC, 	6.0F, 64, 1, 1|2|8|32|64|128, 100, 160, 160, 0, "Bismuth", "Bismuth", 0, 0, 544, 0, false, false, 2, 1, 1, Dyes.dyeCyan, Element.Bi, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.INSTRUMENTUM, 1)));
//    public static MaterialsOld Boron = new MaterialsOld(9, TextureSet.DULL, 			1.0F, 0, 2, 1|32, 250, 250, 250, 0, "Boron", "Boron", 0, 0, 2349, 0, false, false, 1, 1, 1, Dyes.dyeWhite, Element.B, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 3)));
//    public static MaterialsOld Caesium = new MaterialsOld(62, TextureSet.METALLIC, 	1.0F, 0, 2, 1|2|32, 255, 255, 255, 0, "Caesium", "Caesium", 0, 0, 301, 0, false, false, 4, 1, 1, Dyes.dyeNULL, Element.Cs, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.RADIO, 1)));
//    public static MaterialsOld Calcium = new MaterialsOld(26, TextureSet.METALLIC, 	1.0F, 0, 2, 1 |32, 255, 245, 245, 0, "Calcium", "Calcium", 0, 0, 1115, 0, false, false, 4, 1, 1, Dyes.dyePink, Element.Ca, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.SANO, 1), new TC_AspectStack(TC_Aspects.TUTAMEN, 1)));
//    public static MaterialsOld Carbon = new MaterialsOld(10, TextureSet.DULL, 		1.0F, 64, 2, 1|2|16|32|64|128, 20, 20, 20, 0, "Carbon", "Carbon", 0, 0, 3800, 0, false, false, 2, 1, 1, Dyes.dyeBlack, Element.C, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 1), new TC_AspectStack(TC_Aspects.IGNIS, 1)));
//    public static MaterialsOld Cadmium = new MaterialsOld(55, TextureSet.SHINY, 		1.0F, 0, 2, 1|32, 50, 50, 60, 0, "Cadmium", "Cadmium", 0, 0, 594, 0, false, false, 3, 1, 1, Dyes.dyeGray, Element.Cd, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 1), new TC_AspectStack(TC_Aspects.POTENTIA, 1), new TC_AspectStack(TC_Aspects.VENENUM, 1)));
//    public static MaterialsOld Cerium = new MaterialsOld(65, TextureSet.METALLIC,		1.0F, 0, 2, 1|2|32, 255, 255, 255, 0, "Cerium", "Cerium", 0, 0, 1068, 1068, true, false, 4, 1, 1, Dyes.dyeNULL, Element.Ce, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.RADIO, 1)));
//    public static MaterialsOld Chlorine = new MaterialsOld(23, TextureSet.FLUID, 		1.0F, 0, 2, 16|32, 255, 255, 255, 0, "Chlorine", "Chlorine", 0, 0, 171, 0, false, false, 2, 1, 1, Dyes.dyeCyan, Element.Cl, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.AQUA, 2), new TC_AspectStack(TC_Aspects.PANNUS, 1)));
//    public static MaterialsOld Chrome = new MaterialsOld(30, TextureSet.SHINY, 	   11.0F, 256, 3, 1|2|8|32|64|128, 255, 230, 230, 0, "Chrome", "Chrome", 0, 0, 2180, 1700, true, false, 5, 1, 1, Dyes.dyePink, Element.Cr, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.MACHINA, 1)));
//    public static MaterialsOld Cobalt = new MaterialsOld(33, TextureSet.METALLIC, 	8.0F, 512, 3, 1|2|32|64, 80, 80, 250, 0, "Cobalt", "Cobalt", 0, 0, 1768, 0, false, false, 3, 1, 1, Dyes.dyeBlue, Element.Co, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.INSTRUMENTUM, 1)));
//    public static MaterialsOld Copper = new MaterialsOld(35, TextureSet.SHINY, 		1.0F, 0, 1, 1|2|8|32|128, 255, 100, 0, 0, "Copper", "Copper", 0, 0, 1357, 0, false, false, 3, 1, 1, Dyes.dyeOrange, Element.Cu, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.PERMUTATIO, 1)));
//    public static MaterialsOld Deuterium = new MaterialsOld(2, TextureSet.FLUID, 		1.0F, 0, 2, 16|32, 255, 255, 0, 240, "Deuterium", "Deuterium", 0, 0, 14, 0, false, true, 10, 1, 1, Dyes.dyeYellow, Element.D, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.AQUA, 3)));
//    public static MaterialsOld Dysprosium = new MaterialsOld(73, TextureSet.METALLIC, 1.0F, 0, 2, 1|2|32, 255, 255, 255, 0, "Dysprosium", "Dysprosium", 0, 0, 1680, 1680, true, false, 4, 1, 1, Dyes.dyeNULL, Element.Dy, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 3)));
//    public static MaterialsOld Empty = new MaterialsOld(0, TextureSet.NONE, 			1.0F, 0, 2, 256/*Only for Prefixes which need it*/, 255, 255, 255, 255, "Empty", "Empty", 0, 0, -1, 0, false, true, 1, 1, 1, Dyes.dyeNULL, Element.dyeNULL, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.VACUOS, 2)));
//    public static MaterialsOld Europium = new MaterialsOld(70, TextureSet.METALLIC, 	1.0F, 0, 2, 1|2|32, 255, 255, 255, 0, "Europium", "Europium", 0, 0, 1099, 1099, true, false, 4, 1, 1, Dyes.dyeNULL, Element.Eu, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.RADIO, 1)));
//    public static MaterialsOld Fluorine = new MaterialsOld(14, TextureSet.FLUID, 		1.0F, 0, 2, 16|32, 255, 255, 255, 127, "Fluorine", "Fluorine", 0, 0, 53, 0, false, true, 2, 1, 1, Dyes.dyeGreen, Element.F, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.PERDITIO, 2)));
//    public static MaterialsOld Gallium = new MaterialsOld(37, TextureSet.SHINY, 		1.0F, 64, 2, 1|2|32, 220, 220, 255, 0, "Gallium", "Gallium", 0, 0, 302, 0, false, false, 5, 1, 1, Dyes.dyeLightGray, Element.Ga, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.ELECTRUM, 1)));
//    public static MaterialsOld Gold = new MaterialsOld(86, TextureSet.SHINY, 		   12.0F, 64, 2, 1|2|8|32|64|128, 255, 255, 30, 0, "Gold", "Gold", 0, 0, 1337, 0, false, false, 4, 1, 1, Dyes.dyeYellow, Element.Au, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.LUCRUM, 2)));
//    public static MaterialsOld Hydrogen = new MaterialsOld(1, TextureSet.FLUID, 		1.0F, 0, 2, 16|32, 0, 0, 255, 240, "Hydrogen", "Hydrogen", 1, 20, 14, 0, false, true, 2, 1, 1, Dyes.dyeBlue, Element.H, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.AQUA, 1)));
//    public static MaterialsOld Helium = new MaterialsOld(4, TextureSet.FLUID, 		1.0F, 0, 2, 16|32, 255, 255, 0, 240, "Helium", "Helium", 0, 0, 1, 0, false, true, 5, 1, 1, Dyes.dyeYellow, Element.He, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.AER, 2)));
//    public static MaterialsOld Helium3 = new MaterialsOld(5, TextureSet.FLUID, 		1.0F, 0, 2, 16|32, 255, 255, 0, 240, "Helium3", "Helium-3", 0, 0, 1, 0, false, true, 10, 1, 1, Dyes.dyeYellow, Element.He_3, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.AER, 3)));
//    public static MaterialsOld Indium = new MaterialsOld(56, TextureSet.METALLIC, 	1.0F, 0, 2, 1|2|32, 64, 0, 128, 0, "Indium", "Indium", 0, 0, 429, 0, false, false, 4, 1, 1, Dyes.dyeGray, Element.In, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.RADIO, 1)));
//    public static MaterialsOld Iridium = new MaterialsOld(84, TextureSet.DULL, 		6.0F, 2560, 3, 1|2|8|32|64|128, 240, 240, 245, 0, "Iridium", "Iridium", 0, 0, 2719, 2719, true, false, 10, 1, 1, Dyes.dyeWhite, Element.Ir, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.MACHINA, 1)));
//    public static MaterialsOld Iron = new MaterialsOld(32, TextureSet.METALLIC, 		6.0F, 256, 2, 1|2|8|32|64|128, 200, 200, 200, 0, "Iron", "Iron", 0, 0, 1811, 0, false, false, 3, 1, 1, Dyes.dyeLightGray, Element.Fe, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 3)));
//    public static MaterialsOld Lanthanum = new MaterialsOld(64, TextureSet.METALLIC, 	1.0F, 0, 2, 1|2|32, 255, 255, 255, 0, "Lanthanum", "Lanthanum", 0, 0, 1193, 1193, true, false, 4, 1, 1, Dyes.dyeNULL, Element.La, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.RADIO, 1)));
//    public static MaterialsOld Lead = new MaterialsOld(89, TextureSet.DULL, 			8.0F, 64, 1, 1|2|8|32|64|128, 140, 100, 140, 0, "Lead", "Lead", 0, 0, 600, 0, false, false, 3, 1, 1, Dyes.dyePurple, Element.Pb, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.ORDO, 1)));
//    public static MaterialsOld Lithium = new MaterialsOld(6, TextureSet.DULL, 		1.0F, 0, 2, 1|2|8|32, 225, 220, 255, 0, "Lithium", "Lithium", 0, 0, 454, 0, false, false, 4, 1, 1, Dyes.dyeLightBlue, Element.Li, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 1), new TC_AspectStack(TC_Aspects.POTENTIA, 2)));
//    public static MaterialsOld Lutetium = new MaterialsOld(78, TextureSet.METALLIC, 	1.0F, 0, 2, 1|2|32, 255, 255, 255, 0, "Lutetium", "Lutetium", 0, 0, 1925, 1925, true, false, 4, 1, 1, Dyes.dyeNULL, Element.Lu, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.RADIO, 1)));
//    public static MaterialsOld Magic = new MaterialsOld(-128, TextureSet.SHINY, 		8.0F, 5120, 5, 1|2|4|16|32|64|128, 100, 0, 200, 0, "Magic", "Magic", 5, 32, 5000, 0, false, false, 7, 1, 1, Dyes.dyePurple, Element.Ma, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.PRAECANTATIO, 4)));
//    public static MaterialsOld Magnesium = new MaterialsOld(18, TextureSet.METALLIC, 	1.0F, 0, 2, 1|2|32, 255, 200, 200, 0, "Magnesium", "Magnesium", 0, 0, 923, 0, false, false, 3, 1, 1, Dyes.dyePink, Element.Mg, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.SANO, 1)));
//    public static MaterialsOld Manganese = new MaterialsOld(31, TextureSet.DULL, 		7.0F, 512, 2, 1|2|8|32|64, 250, 250, 250, 0, "Manganese", "Manganese", 0, 0, 1519, 0, false, false, 3, 1, 1, Dyes.dyeWhite, Element.Mn, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 3)));
//    public static MaterialsOld Mercury = new MaterialsOld(87, TextureSet.SHINY, 		1.0F, 0, 0, 16|32, 255, 220, 220, 0, "Mercury", "Mercury", 5, 32, 234, 0, false, false, 3, 1, 1, Dyes.dyeLightGray, Element.Hg, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 1), new TC_AspectStack(TC_Aspects.AQUA, 1), new TC_AspectStack(TC_Aspects.VENENUM, 1)));
//    public static MaterialsOld Molybdenum = new MaterialsOld(48, TextureSet.SHINY, 	7.0F, 512, 2, 1|2|8|32|64, 180, 180, 220, 0, "Molybdenum", "Molybdenum", 0, 0, 2896, 0, false, false, 1, 1, 1, Dyes.dyeBlue, Element.Mo, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.INSTRUMENTUM, 1)));
//    public static MaterialsOld Neodymium = new MaterialsOld(67, TextureSet.METALLIC, 	7.0F, 512, 2, 1|2|8|32|64|128, 100, 100, 100, 0, "Neodymium", "Neodymium", 0, 0, 1297, 1297, true, false, 4, 1, 1, Dyes.dyeNULL, Element.Nd, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.MAGNETO, 2)));
//    public static MaterialsOld Neutronium = new MaterialsOld(129, TextureSet.DULL,   24.0F, 655360, 6, 1|2|32|64|128, 250, 250, 250, 0, "Neutronium", "Neutronium", 0, 0, 10000, 0, false, false, 20, 1, 1, Dyes.dyeWhite, Element.Nt, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 4), new TC_AspectStack(TC_Aspects.VITREUS, 3), new TC_AspectStack(TC_Aspects.ALIENIS, 2)));
//    public static MaterialsOld Nickel = new MaterialsOld(34, TextureSet.METALLIC, 	6.0F, 64, 2, 1|2|8|32|64|128, 200, 200, 250, 0, "Nickel", "Nickel", 0, 0, 1728, 0, false, false, 4, 1, 1, Dyes.dyeLightBlue, Element.Ni, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.IGNIS, 1)));
//    public static MaterialsOld Niobium = new MaterialsOld(47, TextureSet.METALLIC, 	1.0F, 0, 2, 1|2|32, 190, 180, 200, 0, "Niobium", "Niobium", 0, 0, 2750, 2750, true, false, 5, 1, 1, Dyes.dyeNULL, Element.Nb, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.ELECTRUM, 1)));
//    public static MaterialsOld Nitrogen = new MaterialsOld(12, TextureSet.FLUID, 		1.0F, 0, 2, 16|32, 0, 150, 200, 240, "Nitrogen", "Nitrogen", 0, 0, 63, 0, false, true, 2, 1, 1, Dyes.dyeCyan, Element.N, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.AER, 2)));
//    public static MaterialsOld Osmium = new MaterialsOld(83, TextureSet.METALLIC, 	16.0F, 1280, 4, 1|2|8|32|64|128, 50, 50, 255, 0, "Osmium", "Osmium", 0, 0, 3306, 3306, true, false, 10, 1, 1, Dyes.dyeBlue, Element.Os, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.MACHINA, 1), new TC_AspectStack(TC_Aspects.NEBRISUM, 1)));
//    public static MaterialsOld Oxygen = new MaterialsOld(13, TextureSet.FLUID, 		1.0F, 0, 2, 16|32, 0, 100, 200, 240, "Oxygen", "Oxygen", 0, 0, 54, 0, false, true, 1, 1, 1, Dyes.dyeWhite, Element.O, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.AER, 1)));
//    public static MaterialsOld Palladium = new MaterialsOld(52, TextureSet.SHINY, 	8.0F, 512, 2, 1|2|8|32|64|128, 128, 128, 128, 0, "Palladium", "Palladium", 0, 0, 1828, 1828, true, false, 4, 1, 1, Dyes.dyeGray, Element.Pd, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 3)));
//    public static MaterialsOld Phosphor = new MaterialsOld(21, TextureSet.DULL, 		1.0F, 0, 2, 1|32, 255, 255, 0, 0, "Phosphor", "Phosphor", 0, 0, 317, 0, false, false, 2, 1, 1, Dyes.dyeYellow, Element.P, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.IGNIS, 2), new TC_AspectStack(TC_Aspects.POTENTIA, 1)));
//    public static MaterialsOld Platinum = new MaterialsOld(85, TextureSet.SHINY, 		12.0F, 64, 2, 1|2|8|32|64|128, 255, 255, 200, 0, "Platinum", "Platinum", 0, 0, 2041, 0, false, false, 6, 1, 1, Dyes.dyeOrange, Element.Pt, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.NEBRISUM, 1)));
//    public static MaterialsOld Plutonium = new MaterialsOld(100, TextureSet.METALLIC, 6.0F, 512, 3, 1|2|8|32|64, 240, 50, 50, 0, "Plutonium", "Plutonium 239", 0, 0, 912, 0, false, false, 6, 1, 1, Dyes.dyeLime, Element.Pu, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.RADIO, 2)));
//    public static MaterialsOld Plutonium241 = new MaterialsOld(101, TextureSet.SHINY, 6.0F, 512, 3, 1|2|32|64, 250, 70, 70, 0, "Plutonium241", "Plutonium 241", 0, 0, 912, 0, false, false, 6, 1, 1, Dyes.dyeLime, Element.Pu_241, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.RADIO, 3)));
//    public static MaterialsOld Potassium = new MaterialsOld(25, TextureSet.METALLIC, 	1.0F, 0, 1, 1|2|32, 250, 250, 250, 0, "Potassium", "Potassium", 0, 0, 336, 0, false, false, 2, 1, 1, Dyes.dyeWhite, Element.K, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 1), new TC_AspectStack(TC_Aspects.POTENTIA, 1)));
//    public static MaterialsOld Radon = new MaterialsOld(93, TextureSet.FLUID, 		1.0F, 0, 2, 16|32, 255, 0, 255, 240, "Radon", "Radon", 0, 0, 202, 0, false, true, 5, 1, 1, Dyes.dyePurple, Element.Rn, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.AER, 1), new TC_AspectStack(TC_Aspects.RADIO, 1)));
//    public static MaterialsOld Silicon = new MaterialsOld(20, TextureSet.METALLIC, 	1.0F, 0, 2, 1|2|32, 60, 60, 80, 0, "Silicon", "Silicon", 0, 0, 1687, 1687, false, false, 1, 1, 1, Dyes.dyeBlack, Element.Si, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.TENEBRAE, 1)));
//    public static MaterialsOld Silver = new MaterialsOld(54, TextureSet.SHINY, 		10.0F, 64, 2, 1|2|8|32|64|128, 220, 220, 255, 0, "Silver", "Silver", 0, 0, 1234, 0, false, false, 3, 1, 1, Dyes.dyeLightGray, Element.Ag, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.LUCRUM, 1)));
//    public static MaterialsOld Sodium = new MaterialsOld(17, TextureSet.METALLIC, 	1.0F, 0, 2, 1 |32, 0, 0, 150, 0, "Sodium", "Sodium", 0, 0, 370, 0, false, false, 1, 1, 1, Dyes.dyeBlue, Element.Na, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 2), new TC_AspectStack(TC_Aspects.LUX, 1)));
//    public static MaterialsOld Sulfur = new MaterialsOld(22, TextureSet.DULL, 		1.0F, 0, 2, 1 |8|32, 200, 200, 0, 0, "Sulfur", "Sulfur", 0, 0, 388, 0, false, false, 2, 1, 1, Dyes.dyeYellow, Element.S, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.IGNIS, 1)));
//    public static MaterialsOld Tantalum = new MaterialsOld(80, TextureSet.METALLIC, 	1.0F, 0, 2, 1|2|32, 255, 255, 255, 0, "Tantalum", "Tantalum", 0, 0, 3290, 0, false, false, 4, 1, 1, Dyes.dyeNULL, Element.Ta, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.VINCULUM, 1)));
//    public static MaterialsOld Thorium = new MaterialsOld(96, TextureSet.SHINY, 		6.0F, 512, 2, 1|2|8|32|64, 0, 30, 0, 0, "Thorium", "Thorium", 0, 0, 2115, 0, false, false, 4, 1, 1, Dyes.dyeBlack, Element.Th, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.RADIO, 1)));
//    public static MaterialsOld Tin = new MaterialsOld(57, TextureSet.DULL, 			1.0F, 0, 1, 1|2|8|32|128, 220, 220, 220, 0, "Tin", "Tin", 0, 0, 505, 505, false, false, 3, 1, 1, Dyes.dyeWhite, Element.Sn, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.VITREUS, 1)));
//    public static MaterialsOld Titanium = new MaterialsOld(28, TextureSet.METALLIC, 	7.0F, 1600, 3, 1|2|8|32|64|128, 220, 160, 240, 0, "Titanium", "Titanium", 0, 0, 1941, 1940, true, false, 5, 1, 1, Dyes.dyePurple, Element.Ti, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.TUTAMEN, 1)));
//    public static MaterialsOld Tritium = new MaterialsOld(3, TextureSet.METALLIC, 	1.0F, 0, 2, 16|32, 255, 0, 0, 240, "Tritium", "Tritium", 0, 0, 14, 0, false, true, 10, 1, 1, Dyes.dyeRed, Element.T, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.AQUA, 4)));
//    public static MaterialsOld Tungsten = new MaterialsOld(81, TextureSet.METALLIC, 	7.0F, 2560, 3, 1|2|32|64|128, 50, 50, 50, 0, "Tungsten", "Tungsten", 0, 0, 3695, 3000, true, false, 4, 1, 1, Dyes.dyeBlack, Element.W, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 3), new TC_AspectStack(TC_Aspects.TUTAMEN, 1)));
//    public static MaterialsOld Uranium = new MaterialsOld(98, TextureSet.METALLIC, 	6.0F, 512, 3, 1|2|8|32|64, 50, 240, 50, 0, "Uranium", "Uranium 238", 0, 0, 1405, 0, false, false, 4, 1, 1, Dyes.dyeGreen, Element.U, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.RADIO, 1)));
//    public static MaterialsOld Uranium235 = new MaterialsOld(97, TextureSet.SHINY, 	6.0F, 512, 3, 1|2|8|32|64, 70, 250, 70, 0, "Uranium235", "Uranium 235", 0, 0, 1405, 0, false, false, 4, 1, 1, Dyes.dyeGreen, Element.U_235, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.RADIO, 2)));
//    public static MaterialsOld Vanadium = new MaterialsOld(29, TextureSet.METALLIC, 	1.0F, 0, 2, 1|2|32, 50, 50, 50, 0, "Vanadium", "Vanadium", 0, 0, 2183, 2183, true, false, 2, 1, 1, Dyes.dyeBlack, Element.V, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.RADIO, 1)));
//    public static MaterialsOld Yttrium = new MaterialsOld(45, TextureSet.METALLIC, 	1.0F, 0, 2, 1|2|32, 220, 250, 220, 0, "Yttrium", "Yttrium", 0, 0, 1799, 1799, true, false, 4, 1, 1, Dyes.dyeNULL, Element.Y, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.RADIO, 1)));
//    public static MaterialsOld Zinc = new MaterialsOld(36, TextureSet.METALLIC, 		1.0F, 0, 1, 1|2|8|32, 250, 240, 240, 0, "Zinc", "Zinc", 0, 0, 692, 0, false, false, 2, 1, 1, Dyes.dyeWhite, Element.Zn, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.SANO, 1)));
//
//    /**
//     * The "Random Material" ones.
//     */
//    public static MaterialsOld Organic = new MaterialsOld(-1, TextureSet.LEAF, 	1.0F, 0, 1, false, "Organic", "Organic");
//    public static MaterialsOld AnyBronze = new MaterialsOld(-1, TextureSet.SHINY, 1.0F, 0, 3, false, "AnyBronze", "AnyBronze");
//    public static MaterialsOld AnyCopper = new MaterialsOld(-1, TextureSet.SHINY, 1.0F, 0, 3, false, "AnyCopper", "AnyCopper");
//    public static MaterialsOld AnyIron = new MaterialsOld(-1, TextureSet.SHINY, 	1.0F, 0, 3, false, "AnyIron", "AnyIron");
//    public static MaterialsOld AnyRubber = new MaterialsOld(-1, TextureSet.SHINY, 1.0F, 0, 3, false, "AnyRubber", "AnyRubber");
//    public static MaterialsOld AnySyntheticRubber = new MaterialsOld(-1, TextureSet.SHINY, 1.0F, 0, 3, false, "AnySyntheticRubber", "AnySyntheticRubber");
//    public static MaterialsOld Crystal = new MaterialsOld(-1, TextureSet.SHINY, 	1.0F, 0, 3, false, "Crystal", "Crystal");
//    public static MaterialsOld Quartz = new MaterialsOld(-1, TextureSet.QUARTZ, 	1.0F, 0, 2, false, "Quartz", "Quartz");
//    public static MaterialsOld Metal = new MaterialsOld(-1, TextureSet.METALLIC, 	1.0F, 0, 2, false, "Metal", "Metal");
//    public static MaterialsOld Unknown = new MaterialsOld(-1, TextureSet.DULL, 	1.0F, 0, 2, false, "Unknown", "Unknown");
//    public static MaterialsOld Cobblestone = new MaterialsOld(-1, TextureSet.DULL,1.0F, 0, 1, false, "Cobblestone", "Cobblestone");
//    public static MaterialsOld BrickNether = new MaterialsOld(-1, TextureSet.DULL,1.0F, 0, 1, false, "BrickNether", "BrickNether");
//
//    /**
//     * The "I don't care" Section, everything I don't want to do anything with right now, is right here. Just to make the Material Finder shut up about them.
//     * But I do see potential uses in some of these MaterialsOld.
//     */
//    public static MaterialsOld PhasedIron = new MaterialsOld(-1, TextureSet.NONE, 	1.0F, 0, 2, 1|2, 255, 255, 255, 0, "PhasedIron", "Phased Iron", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeNULL);
//    public static MaterialsOld PhasedGold = new MaterialsOld(-1, TextureSet.NONE, 	1.0F, 0, 2, 1|2, 255, 255, 255, 0, "PhasedGold", "Phased Gold", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeNULL);
//    public static MaterialsOld Soularium = new MaterialsOld(-1, TextureSet.NONE, 		1.0F, 0, 2, 1, 255, 255, 255, 0, "Soularium", "Soularium", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeNULL);
//    public static MaterialsOld Endium = new MaterialsOld(770, TextureSet.DULL, 		1.0F, 0, 2, 1|2, 165, 220, 250, 0, "Endium", "Endium", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeYellow);
//    public static MaterialsOld DarkSteel = new MaterialsOld(364, TextureSet.DULL, 	8.0F, 512, 3, 1|2|8 |64, 80, 70, 80, 0, "DarkSteel", "Dark Steel", 0, 0, 1811, 0, false, false, 5, 1, 1, Dyes.dyePurple);
//    public static MaterialsOld ConductiveIron = new MaterialsOld(-1, TextureSet.NONE, 1.0F, 0, 2, 1|2, 255, 255, 255, 0, "ConductiveIron", "Conductive Iron", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeNULL);
//    public static MaterialsOld ElectricalSteel = new MaterialsOld(-1, TextureSet.NONE,1.0F, 0, 2, 1|2, 255, 255, 255, 0, "ElectricalSteel", "Electrical Steel", 0, 0, 1811, 1000, true, false, 3, 1, 1, Dyes.dyeNULL);
//    public static MaterialsOld EnergeticAlloy = new MaterialsOld(-1, TextureSet.NONE, 1.0F, 0, 2, 1|2, 255, 255, 255, 0, "EnergeticAlloy", "Energetic Alloy", 0, 0, 1950, 1950, true, false, 3, 1, 1, Dyes.dyeNULL);
//    public static MaterialsOld VibrantAlloy = new MaterialsOld(-1, TextureSet.NONE, 	1.0F, 0, 2, 1|2, 255, 255, 255, 0, "VibrantAlloy", "Vibrant Alloy", 0, 0, 3300, 3300, true, false, 3, 1, 1, Dyes.dyeNULL);
//    public static MaterialsOld PulsatingIron = new MaterialsOld(-1, TextureSet.NONE, 	1.0F, 0, 2, 1|2, 255, 255, 255, 0, "PulsatingIron", "Pulsating Iron", 0, 0, 1600, 1600, true, false, 3, 1, 1, Dyes.dyeNULL);
//    public static MaterialsOld Fluix = new MaterialsOld(-1, TextureSet.NONE, 			1.0F, 0, 2, 1|4, 255, 255, 255, 0, "Fluix", "Fluix", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeNULL);
//    public static MaterialsOld Ender = new MaterialsOld(-1, TextureSet.NONE, 			1.0F, 0, 2, 1, 255, 255, 255, 0, "Ender", "Ender", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeNULL);
//    public static MaterialsOld IridiumSodiumOxide = new MaterialsOld(-1, TextureSet.NONE,1.0F, 0, 2, 1, 255, 255, 255, 0, "IridiumSodiumOxide", "Iridium Sodium Oxide", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeNULL);
//    public static MaterialsOld PlatinumGroupSludge = new MaterialsOld(241, TextureSet.POWDER, 1.0F, 0, 2, 1, 0, 30, 0, 0, "PlatinumGroupSludge", "Platinum Group Sludge", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeNULL);
//
//    /**
//     * Unknown Material Components. Dead End Section.
//     */
//    public static MaterialsOld Amber = new MaterialsOld(514, TextureSet.RUBY, 		4.0F, 128, 2, 1|4|8 |64, 255, 128, 0, 127, "Amber", "Amber", 5, 3, -1, 0, false, true, 1, 1, 1, Dyes.dyeOrange, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.VINCULUM, 2), new TC_AspectStack(TC_Aspects.VITREUS, 1)));
//    public static MaterialsOld Ardite = new MaterialsOld(-1, TextureSet.NONE, 		6.0F, 64, 2, 1|2|8 |64, 255, 0, 0, 0, "Ardite", "Ardite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeYellow);
//    public static MaterialsOld Black = new MaterialsOld(-1, TextureSet.NONE, 			1.0F, 0, 2, 0, 0, 0, 0, 0, "Black", "Black", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeBlack);
//    public static MaterialsOld CertusQuartz = new MaterialsOld(516, TextureSet.QUARTZ,5.0F, 32, 1, 1|4|8 |64, 210, 210, 230, 0, "CertusQuartz", "Certus Quartz", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeLightGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 1), new TC_AspectStack(TC_Aspects.VITREUS, 1)));
//    public static MaterialsOld ConstructionFoam = new MaterialsOld(854, TextureSet.DULL, 1.0F, 0, 2, 1 |16, 128, 128, 128, 0, "ConstructionFoam", "Construction Foam", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeGray);
//    public static MaterialsOld Desh = new MaterialsOld(884, TextureSet.DULL, 			1.0F, 1280, 3, 1|2|8 |64|128, 40, 40, 40, 0, "Desh", "Desh", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeBlack);
//    public static MaterialsOld Dilithium = new MaterialsOld(515, TextureSet.DIAMOND, 	1.0F, 0, 1, 1|4|16, 255, 250, 250, 127, "Dilithium", "Dilithium", 0, 0, -1, 0, false, true, 1, 1, 1, Dyes.dyeWhite);
//    public static MaterialsOld Duranium = new MaterialsOld(328, TextureSet.METALLIC, 16.0F, 5120, 5, 1|2|64, 255, 255, 255, 0, "Duranium", "Duranium", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightGray);
//    public static MaterialsOld Enderium = new MaterialsOld(321, TextureSet.DULL, 		8.0F, 256, 3, 1|2|64, 89, 145, 135, 0, "Enderium", "Enderium", 0, 0, 3000, 3000, true, false, 1, 1, 1, Dyes.dyeGreen, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.ALIENIS, 1)));
//    public static MaterialsOld Firestone = new MaterialsOld(347, TextureSet.QUARTZ, 	6.0F, 1280, 3, 1|4|8 |64, 200, 20, 0, 0, "Firestone", "Firestone", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeRed);
//    public static MaterialsOld FoolsRuby = new MaterialsOld(512, TextureSet.RUBY, 	1.0F, 0, 2, 1|4|8, 255, 100, 100, 127, "FoolsRuby", "Ruby", 0, 0, -1, 0, false, true, 3, 1, 1, Dyes.dyeRed, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.LUCRUM, 2), new TC_AspectStack(TC_Aspects.VITREUS, 2)));
//    public static MaterialsOld Glowstone = new MaterialsOld(811, TextureSet.SHINY, 	1.0F, 0, 1, 1 |16, 255, 255, 0, 0, "Glowstone", "Glowstone", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeYellow, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.LUX, 2), new TC_AspectStack(TC_Aspects.SENSUS, 1)));
//    public static MaterialsOld Graphite = new MaterialsOld(865, TextureSet.DULL, 		5.0F, 32, 2, 1 |8|16|64, 128, 128, 128, 0, "Graphite", "Graphite", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 2), new TC_AspectStack(TC_Aspects.IGNIS, 1)));
//    public static MaterialsOld Graphene = new MaterialsOld(819, TextureSet.DULL, 		6.0F, 32, 1, 1|64, 128, 128, 128, 0, "Graphene", "Graphene", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 2), new TC_AspectStack(TC_Aspects.ELECTRUM, 1)));
//    public static MaterialsOld InfusedGold = new MaterialsOld(323, TextureSet.SHINY, 12.0F, 64, 3, 1|2|8 |64|128, 255, 200, 60, 0, "InfusedGold", "Infused Gold", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeYellow);
//    public static MaterialsOld InfusedAir = new MaterialsOld(540, TextureSet.SHARDS, 	8.0F, 64, 3, 1|4|8 |64|128, 255, 255, 0, 0, "InfusedAir", "Aer", 5, 160, -1, 0, false, true, 3, 1, 1, Dyes.dyeYellow, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.PRAECANTATIO, 1), new TC_AspectStack(TC_Aspects.AER, 2)));
//    public static MaterialsOld InfusedFire = new MaterialsOld(541, TextureSet.SHARDS, 8.0F, 64, 3, 1|4|8 |64|128, 255, 0, 0, 0, "InfusedFire", "Ignis", 5, 320, -1, 0, false, true, 3, 1, 1, Dyes.dyeRed, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.PRAECANTATIO, 1), new TC_AspectStack(TC_Aspects.IGNIS, 2)));
//    public static MaterialsOld InfusedEarth = new MaterialsOld(542, TextureSet.SHARDS,8.0F, 256, 3, 1|4|8 |64|128, 0, 255, 0, 0, "InfusedEarth", "Terra", 5, 160, -1, 0, false, true, 3, 1, 1, Dyes.dyeGreen, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.PRAECANTATIO, 1), new TC_AspectStack(TC_Aspects.TERRA, 2)));
//    public static MaterialsOld InfusedWater = new MaterialsOld(543, TextureSet.SHARDS,8.0F, 64, 3, 1|4|8 |64|128, 0, 0, 255, 0, "InfusedWater", "Aqua", 5, 160, -1, 0, false, true, 3, 1, 1, Dyes.dyeBlue, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.PRAECANTATIO, 1), new TC_AspectStack(TC_Aspects.AQUA, 2)));
//    public static MaterialsOld InfusedEntropy=new MaterialsOld(544,TextureSet.SHARDS,32.0F, 64, 4, 1|4|8 |64|128, 62, 62, 62, 0, "InfusedEntropy", "Perditio", 5, 320, -1, 0, false, true, 3, 1, 1, Dyes.dyeBlack, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.PRAECANTATIO, 1), new TC_AspectStack(TC_Aspects.PERDITIO, 2)));
//    public static MaterialsOld InfusedOrder = new MaterialsOld(545, TextureSet.SHARDS,8.0F, 64, 3, 1|4|8 |64|128, 252, 252, 252, 0, "InfusedOrder", "Ordo", 5, 240, -1, 0, false, true, 3, 1, 1, Dyes.dyeWhite, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.PRAECANTATIO, 1), new TC_AspectStack(TC_Aspects.ORDO, 2)));
//    public static MaterialsOld InfusedVis = new MaterialsOld(-1, TextureSet.SHARDS, 	8.0F, 64, 3, 1|4|8 |64|128, 255, 0, 255, 0, "InfusedVis", "Auram", 5, 240, -1, 0, false, true, 3, 1, 1, Dyes.dyePurple, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.PRAECANTATIO, 1), new TC_AspectStack(TC_Aspects.AURAM, 2)));
//    public static MaterialsOld InfusedDull = new MaterialsOld(-1, TextureSet.SHARDS, 32.0F, 64, 3, 1|4|8 |64|128, 100, 100, 100, 0, "InfusedDull", "Vacuus", 5, 160, -1, 0, false, true, 3, 1, 1, Dyes.dyeLightGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.PRAECANTATIO, 1), new TC_AspectStack(TC_Aspects.VACUOS, 2)));
//    public static MaterialsOld Jasper = new MaterialsOld(511, TextureSet.EMERALD, 	1.0F, 0, 2, 1|4, 200, 80, 80, 100, "Jasper", "Jasper", 0, 0, -1, 0, false, true, 3, 1, 1, Dyes.dyeRed, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.LUCRUM, 4), new TC_AspectStack(TC_Aspects.VITREUS, 2)));
//    public static MaterialsOld Lava = new MaterialsOld(700, TextureSet.FLUID, 		1.0F, 0, 1, 16, 255, 64, 0, 0, "Lava", "Lava", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeOrange);
//    public static MaterialsOld MeteoricIron =new MaterialsOld(340,TextureSet.METALLIC,6.0F, 384, 2, 1|2|8 |64, 100, 50, 80, 0, "MeteoricIron", "Meteoric Iron", 0, 0, 1811, 0, false, false, 1, 1, 1, Dyes.dyeGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.MAGNETO, 1)));
//    public static MaterialsOld MeteoricSteel=new MaterialsOld(341,TextureSet.METALLIC,6.0F, 768, 2, 1|2|64, 50, 25, 40, 0, "MeteoricSteel", "Meteoric Steel", 0, 0, 1811, 1000, true, false, 1, 1, 1, Dyes.dyeGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.MAGNETO, 1), new TC_AspectStack(TC_Aspects.ORDO, 1)));
//    public static MaterialsOld Naquadah = new MaterialsOld(324, TextureSet.METALLIC, 	6.0F, 1280, 4, 1|2|8|16|64, 50, 50, 50, 0, "Naquadah", "Naquadah", 0, 0, 5400, 5400, true, false, 10, 1, 1, Dyes.dyeBlack, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 3), new TC_AspectStack(TC_Aspects.RADIO, 1), new TC_AspectStack(TC_Aspects.NEBRISUM, 1)));
//    public static MaterialsOld NaquadahAlloy=new MaterialsOld(325,TextureSet.METALLIC,8.0F, 5120, 5, 1|2|64|128, 40, 40, 40, 0, "NaquadahAlloy", "Naquadah Alloy", 0, 0, 7200, 7200, true, false, 10, 1, 1, Dyes.dyeBlack, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 4), new TC_AspectStack(TC_Aspects.NEBRISUM, 1)));
//    public static MaterialsOld NaquadahEnriched=new MaterialsOld(326,TextureSet.METALLIC,6.0F, 1280, 4, 1|2|8|16|64, 50, 50, 50, 0, "NaquadahEnriched", "Enriched Naquadah", 0, 0, 4500, 4500, true, false, 15, 1, 1, Dyes.dyeBlack, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 3), new TC_AspectStack(TC_Aspects.RADIO, 2), new TC_AspectStack(TC_Aspects.NEBRISUM, 2)));
//    public static MaterialsOld Naquadria = new MaterialsOld(327, TextureSet.SHINY, 	1.0F, 512, 4, 1|2|8|16|64, 30, 30, 30, 0, "Naquadria", "Naquadria", 0, 0, 9000, 9000, true, false, 20, 1, 1, Dyes.dyeBlack, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 4), new TC_AspectStack(TC_Aspects.RADIO, 3), new TC_AspectStack(TC_Aspects.NEBRISUM, 3)));
//    public static MaterialsOld Nether = new MaterialsOld(-1, TextureSet.NONE, 		1.0F, 0, 1, 0, 255, 255, 255, 0, "Nether", "Nether", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeNULL);
//    public static MaterialsOld NetherBrick = new MaterialsOld(814, TextureSet.DULL, 	1.0F, 0, 1, 1, 100, 0, 0, 0, "NetherBrick", "Nether Brick", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeRed, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.IGNIS, 1)));
//    public static MaterialsOld NetherQuartz = new MaterialsOld(522, TextureSet.QUARTZ,1.0F, 32, 1, 1|4|8 |64, 230, 210, 210, 0, "NetherQuartz", "Nether Quartz", 0, 0, -1, 0, false, false, 2, 1, 1, Dyes.dyeWhite, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 1), new TC_AspectStack(TC_Aspects.VITREUS, 1)));
//    public static MaterialsOld NetherStar =new MaterialsOld(506,TextureSet.NETHERSTAR,1.0F, 5120, 4, 1|4|64, 255, 255, 255, 0, "NetherStar", "Nether Star", 5, 50000, -1, 0, false, false, 15, 1, 1, Dyes.dyeWhite);
//    public static MaterialsOld Oilsands = new MaterialsOld(878, TextureSet.NONE, 		1.0F, 0, 1, 1|8 , 10, 10, 10, 0, "Oilsands", "Oilsands", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeNULL);
//    public static MaterialsOld Quartzite = new MaterialsOld(523, TextureSet.QUARTZ, 	1.0F, 0, 1, 1|4|8 , 210, 230, 210, 0, "Quartzite", "Quartzite", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack(Silicon, 1), new MaterialStack(Oxygen, 2)));
//    public static MaterialsOld Sand = new MaterialsOld(-1, TextureSet.NONE, 			1.0F, 0, 1, 0, 255, 255, 255, 0, "Sand", "Sand", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeYellow);
//    public static MaterialsOld Tritanium = new MaterialsOld(329, TextureSet.METALLIC,20.0F, 10240, 6, 1|2|64, 255, 255, 255, 0, "Tritanium", "Tritanium", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeWhite, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.ORDO, 2)));
//    public static MaterialsOld UUAmplifier = new MaterialsOld(721, TextureSet.FLUID, 	1.0F, 0, 1, 16, 96, 0, 128, 0, "UUAmplifier", "UU-Amplifier", 0, 0, -1, 0, false, false, 10, 1, 1, Dyes.dyePink);
//    public static MaterialsOld UUMatter = new MaterialsOld(703, TextureSet.FLUID, 	1.0F, 0, 1, 16, 128, 0, 196, 0, "UUMatter", "UU-Matter", 0, 0, -1, 0, false, false, 10, 1, 1, Dyes.dyePink);
//
//    /**
//     * Circuitry, Batteries and other Technical things
//     */
//    public static MaterialsOld Primitive = new MaterialsOld(-1, TextureSet.NONE, 		1.0F, 0, 0, 0, 255, 255, 255, 0, "Primitive", "Primitive", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 1)));
//    public static MaterialsOld Basic = new MaterialsOld(-1, TextureSet.NONE, 			1.0F, 0, 0, 0, 255, 255, 255, 0, "Basic", "Basic", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 2)));
//    public static MaterialsOld Good = new MaterialsOld(-1, TextureSet.NONE, 			1.0F, 0, 0, 0, 255, 255, 255, 0, "Good", "Good", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 3)));
//    public static MaterialsOld Advanced = new MaterialsOld(-1, TextureSet.NONE, 		1.0F, 0, 0, 0, 255, 255, 255, 0, "Advanced", "Advanced", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 4)));
//    public static MaterialsOld Data = new MaterialsOld(-1, TextureSet.NONE, 			1.0F, 0, 0, 0, 255, 255, 255, 0, "Data", "Data", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 5)));
//    public static MaterialsOld Elite = new MaterialsOld(-1, TextureSet.NONE, 			1.0F, 0, 0, 0, 255, 255, 255, 0, "Elite", "Elite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 6)));
//    public static MaterialsOld Master = new MaterialsOld(-1, TextureSet.NONE, 		1.0F, 0, 0, 0, 255, 255, 255, 0, "Master", "Master", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 7)));
//    public static MaterialsOld Ultimate = new MaterialsOld(-1, TextureSet.NONE, 		1.0F, 0, 0, 0, 255, 255, 255, 0, "Ultimate", "Ultimate", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 8)));
//    public static MaterialsOld Superconductor = new MaterialsOld(-1, TextureSet.NONE, 1.0F, 0, 0, 0, 255, 255, 255, 0, "Superconductor", "Superconductor", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 8)));
//    public static MaterialsOld Infinite = new MaterialsOld(-1, TextureSet.NONE, 		1.0F, 0, 0, 0, 255, 255, 255, 0, "Infinite", "Infinite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightGray);
//
//    /**
//     * Not possible to determine exact Components
//     */
//    public static MaterialsOld Antimatter = new MaterialsOld(-1, TextureSet.NONE, 	1.0F, 0, 0, 0, 255, 255, 255, 0, "Antimatter", "Antimatter", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyePink, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 9), new TC_AspectStack(TC_Aspects.PERFODIO, 8)));
//    public static MaterialsOld Biomass = new MaterialsOld(704, TextureSet.FLUID, 		1.0F, 0, 0, 16, 0, 255, 0, 0, "Biomass", "Biomass", 3, 8, -1, 0, false, false, 1, 1, 1, Dyes.dyeGreen);
//    public static MaterialsOld CharcoalByproducts = new MaterialBuilder(675, TextureSet.FLUID, "Charcoal Byproducts").addCell().setRGB(120, 68, 33).setColor(Dyes.dyeBrown).build();
//    public static MaterialsOld Creosote = new MaterialsOld(712, TextureSet.FLUID, 	1.0F, 0, 0, 16, 128, 64, 0, 0, "Creosote", "Creosote", 3, 8, -1, 0, false, false, 1, 1, 1, Dyes.dyeBrown);
//    public static MaterialsOld Ethanol = new MaterialsOld(706, TextureSet.FLUID, 		1.0F, 0, 0, 16, 255, 128, 0, 0, "Ethanol", "Ethanol", 0, 148, -1, 0, false, false, 1, 1, 1, Dyes.dyeOrange, 1, Arrays.asList(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.VENENUM, 1), new TC_AspectStack(TC_Aspects.AQUA, 1)));
//    public static MaterialsOld FermentedBiomass = new MaterialBuilder(691, TextureSet.FLUID, "Fermented Biomass").addCell().addFluid().setRGB(68, 85, 0).setColor(Dyes.dyeBrown).build();
//    public static MaterialsOld FishOil = new MaterialsOld(711, TextureSet.FLUID, 		1.0F, 0, 0, 16, 255, 196, 0, 0, "FishOil", "Fish Oil", 3, 2, -1, 0, false, false, 1, 1, 1, Dyes.dyeYellow, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.CORPUS, 2)));
//    public static MaterialsOld Fuel = new MaterialsOld(708, TextureSet.FLUID, 		1.0F, 0, 0, 16, 255, 255, 0, 0, "Fuel", "Diesel", 0, 128, -1, 0, false, false, 1, 1, 1, Dyes.dyeYellow);
//    public static MaterialsOld Glue = new MaterialsOld(726, TextureSet.FLUID, 		1.0F, 0, 0, 16, 200, 196, 0, 0, "Glue", "Glue", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeOrange, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.LIMUS, 2)));
//    public static MaterialsOld Gunpowder = new MaterialsOld(800, TextureSet.DULL, 	1.0F, 0, 0, 1, 128, 128, 128, 0, "Gunpowder", "Gunpowder", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.PERDITIO, 3), new TC_AspectStack(TC_Aspects.IGNIS, 4)));
//    public static MaterialsOld Honey = new MaterialsOld(725, TextureSet.FLUID, 		1.0F, 0, 0, 16, 210, 200, 0, 0, "Honey", "Honey", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeYellow);
//    public static MaterialsOld Leather = new MaterialsOld(-1, TextureSet.ROUGH, 		1.0F, 0, 0, 1, 150, 150, 80, 127, "Leather", "Leather", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeOrange);
//    public static MaterialsOld Lubricant = new MaterialsOld(724, TextureSet.FLUID, 	1.0F, 0, 0, 16, 255, 196, 0, 0, "Lubricant", "Lubricant", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeOrange, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.AQUA, 2), new TC_AspectStack(TC_Aspects.MACHINA, 1)));
//    public static MaterialsOld Milk = new MaterialsOld(885, TextureSet.FINE, 			1.0F, 0, 0, 1 |16, 254, 254, 254, 0, "Milk", "Milk", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeWhite, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.SANO, 2)));
//    public static MaterialsOld Oil = new MaterialsOld(707, TextureSet.FLUID, 			1.0F, 0, 0, 16, 10, 10, 10, 0, "Oil", "Oil", 3, 16, -1, 0, false, false, 1, 1, 1, Dyes.dyeBlack);
//    public static MaterialsOld Paper = new MaterialsOld(879, TextureSet.PAPER, 		1.0F, 0, 0, 1, 250, 250, 250, 0, "Paper", "Paper", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeWhite, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 1)));
//    public static MaterialsOld RareEarth = new MaterialsOld(891, TextureSet.FINE, 	1.0F, 0, 0, 1, 128, 128, 100, 0, "RareEarth", "Rare Earth", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 1), new TC_AspectStack(TC_Aspects.LUCRUM, 1)));
//    public static MaterialsOld Reinforced = new MaterialsOld(-1, TextureSet.NONE, 	1.0F, 0, 0, 0, 255, 255, 255, 0, "Reinforced", "Reinforced", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeGray);
//    public static MaterialsOld SeedOil = new MaterialsOld(713, TextureSet.FLUID,		1.0F, 0, 0, 16, 196, 255, 0, 0, "SeedOil", "Seed Oil", 3, 2, -1, 0, false, false, 1, 1, 1, Dyes.dyeLime, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.GRANUM, 2)));
//    public static MaterialsOld SeedOilHemp = new MaterialsOld(722, TextureSet.FLUID, 	1.0F, 0, 0, 16, 196, 255, 0, 0, "SeedOilHemp", "Hemp Seed Oil", 3, 2, -1, 0, false, false, 1, 1, 1, Dyes.dyeLime, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.GRANUM, 2)));
//    public static MaterialsOld SeedOilLin = new MaterialsOld(723, TextureSet.FLUID, 	1.0F, 0, 0, 16, 196, 255, 0, 0, "SeedOilLin", "Lin Seed Oil", 3, 2, -1, 0, false, false, 1, 1, 1, Dyes.dyeLime, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.GRANUM, 2)));
//    public static MaterialsOld Stone = new MaterialsOld(299, TextureSet.ROUGH, 		4.0F, 32, 1, 1|64|128, 205, 205, 205, 0, "Stone", "Stone", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.TERRA, 1)));
//    public static MaterialsOld Wheat = new MaterialsOld(881, TextureSet.POWDER, 		1.0F, 0, 0, 1, 255, 255, 196, 0, "Wheat", "Wheat", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeYellow, Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.MESSIS, 2)));
//    public static MaterialsOld WoodGas = new MaterialBuilder(660, TextureSet.FLUID, "Wood RefineryGas").addCell().addGas().setRGB(222, 205, 135).setColor(Dyes.dyeBrown).setFuelType(MaterialBuilder.GAS).setFuelPower(24).build();
//    public static MaterialsOld WoodTar = new MaterialBuilder(662, TextureSet.FLUID, "Wood Tar").addCell().addFluid().setRGB(40, 23, 11).setColor(Dyes.dyeBrown).build();
//    public static MaterialsOld WoodVinegar = new MaterialBuilder(661, TextureSet.FLUID, "Wood Vinegar").addCell().addFluid().setRGB(212, 85, 0).setColor(Dyes.dyeBrown).build();
//
//    /**
//     * TODO: This
//     */
//    public static MaterialsOld AluminiumBrass = new MaterialsOld(-1, TextureSet.METALLIC, 6.0F, 64, 2, 1|2|64, 255, 255, 255, 0, "AluminiumBrass", "Aluminium Brass", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeYellow);
//    public static MaterialsOld Osmiridium = new MaterialsOld(317, TextureSet.METALLIC, 	7.0F, 1600, 3, 1|2|64|128, 100, 100, 255, 0, "Osmiridium", "Osmiridium", 0, 0, 3333, 2500, true, false, 1, 1, 1, Dyes.dyeLightBlue, 1, Arrays.asList(new MaterialStack(Iridium, 3), new MaterialStack(Osmium, 1)));
//    public static MaterialsOld Endstone = new MaterialsOld(808, TextureSet.DULL, 			1.0F, 0, 1, 1, 255, 255, 255, 0, "Endstone", "Endstone", 0, 0, -1, 0, false, false, 0, 1, 1, Dyes.dyeYellow);
//    public static MaterialsOld Netherrack = new MaterialsOld(807, TextureSet.DULL, 		1.0F, 0, 0, 1, 200, 0, 0, 0, "Netherrack", "Netherrack", 0, 0, -1, 0, false, false, 0, 1, 1, Dyes.dyeRed);
//    public static MaterialsOld SoulSand = new MaterialsOld(-1, TextureSet.DULL, 			1.0F, 0, 0, 1, 255, 255, 255, 0, "Soulsand", "Soulsand", 0, 0, -1, 0, false, false, 0, 1, 1, Dyes.dyeBrown);
//
//    /**
//     * First Degree Compounds
//     */
//    public static MaterialsOld Methane = new MaterialsOld(715, TextureSet.FLUID, 			1.0F, 0, 1, 16, 255, 255, 255, 0, "Methane", "Methane", 1, 104, -1, 0, false, false, 3, 1, 1, Dyes.dyeMagenta, 1, Arrays.asList(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 4)));
//    public static MaterialsOld CarbonDioxide = new MaterialsOld(497, TextureSet.FLUID, 	1.0F, 0, 2, 16|32, 169, 208, 245, 240, "CarbonDioxide", "Carbon Dioxide", 0, 0, 25, 1, false, true, 1, 1, 1, Dyes.dyeLightBlue, 1, Arrays.asList(new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 2))).setHasCorrespondingGas(true);
//    public static MaterialsOld NobleGases = new MaterialsOld(496, TextureSet.FLUID, 		1.0F, 0, 2, 16|32, 169, 208, 245, 240, "NobleGases", "Noble Gases", 0, 0, 4, 0, false, true, 1, 1, 1, Dyes.dyeLightBlue, 2, Arrays.asList(new MaterialStack(CarbonDioxide, 21), new MaterialStack(Helium, 9), new MaterialStack(Methane, 3), new MaterialStack(Deuterium, 1))).setHasCorrespondingFluid(true).setLiquidTemperature(79);
//    public static MaterialsOld Air = new MaterialsOld(-1, TextureSet.FLUID, 				1.0F, 0, 2, 16|32, 169, 208, 245, 240, "Air", "Air", 0, 0, -1, 0, false, true, 1, 1, 1, Dyes.dyeLightBlue, 0, Arrays.asList(new MaterialStack(Nitrogen, 40), new MaterialStack(Oxygen, 11), new MaterialStack(Argon, 1), new MaterialStack(NobleGases, 1)));
//    public static MaterialsOld LiquidAir = new MaterialsOld(495, TextureSet.FLUID, 		1.0F, 0, 2, 16|32, 169, 208, 245, 240, "LiquidAir", "Liquid Air", 0, 0, 4, 0, false, true, 1, 1, 1, Dyes.dyeLightBlue, 2, Arrays.asList(new MaterialStack(Nitrogen, 40), new MaterialStack(Oxygen, 11), new MaterialStack(Argon, 1), new MaterialStack(NobleGases, 1))).setHasCorrespondingFluid(true).setLiquidTemperature(79);
//    public static MaterialsOld Almandine = new MaterialsOld(820, TextureSet.ROUGH, 		1.0F, 0, 1, 1 |8 , 255, 0, 0, 0, "Almandine", "Almandine", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeRed, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Iron, 3), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12)));
//    public static MaterialsOld Andradite = new MaterialsOld(821, TextureSet.ROUGH, 		1.0F, 0, 1, 1, 150, 120, 0, 0, "Andradite", "Andradite", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeYellow, 1, Arrays.asList(new MaterialStack(Calcium, 3), new MaterialStack(Iron, 2), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12)));
//    public static MaterialsOld AnnealedCopper = new MaterialsOld(345, TextureSet.SHINY, 	1.0F, 0, 2, 1|2|128, 255, 120, 20, 0, "AnnealedCopper", "Annealed Copper", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeOrange, 2, Arrays.asList(new MaterialStack(Copper, 1)));
//    public static MaterialsOld Asbestos = new MaterialsOld(946, TextureSet.DULL, 			1.0F, 0, 1, 1, 230, 230, 230, 0, "Asbestos", "Asbestos", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack(Magnesium, 3), new MaterialStack(Silicon, 2), new MaterialStack(Hydrogen, 4), new MaterialStack(Oxygen, 9))); // Mg3Si2O5(OH)4
//    public static MaterialsOld Ash = new MaterialsOld(815, TextureSet.DULL, 				1.0F, 0, 1, 1, 150, 150, 150, 0, "Ash", "Ashes", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, Arrays.asList(new TC_AspectStack(TC_Aspects.PERDITIO, 1)));
//    public static MaterialsOld BandedIron = new MaterialsOld(917, TextureSet.DULL, 		1.0F, 0, 2, 1 |8 , 145, 90, 90, 0, "BandedIron", "Banded Iron", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeBrown, 1, Arrays.asList(new MaterialStack(Iron, 2), new MaterialStack(Oxygen, 3)));
//    public static MaterialsOld BatteryAlloy = new MaterialsOld(315, TextureSet.DULL, 		1.0F, 0, 1, 1|2, 156, 124, 160, 0, "BatteryAlloy", "Battery Alloy", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyePurple, 2, Arrays.asList(new MaterialStack(Lead, 4), new MaterialStack(Antimony, 1)));
//    public static MaterialsOld BlueTopaz = new MaterialsOld(513,TextureSet.GEMH,7.0F, 256, 3, 1|4|8 |64, 0, 0, 255, 127, "BlueTopaz", "Blue Topaz", 0, 0, -1, 0, false, true, 3, 1, 1, Dyes.dyeBlue, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 1), new MaterialStack(Fluorine, 2), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 6)), Arrays.asList(new TC_AspectStack(TC_Aspects.LUCRUM, 6), new TC_AspectStack(TC_Aspects.VITREUS, 4)));
//    public static MaterialsOld Bone = new MaterialsOld(806, TextureSet.DULL, 				1.0F, 0, 1, 1, 250, 250, 250, 0, "Bone", "Bone", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 0, Arrays.asList(new MaterialStack(Calcium, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.MORTUUS, 2), new TC_AspectStack(TC_Aspects.CORPUS, 1)));
//    public static MaterialsOld Brass = new MaterialsOld(301, TextureSet.METALLIC, 		7.0F, 96, 1, 1|2|64|128, 255, 180, 0, 0, "Brass", "Brass", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack(Zinc, 1), new MaterialStack(Copper, 3)), Arrays.asList(new TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.INSTRUMENTUM, 1)));
//    public static MaterialsOld Bronze = new MaterialsOld(300, TextureSet.METALLIC, 		6.0F, 192, 2, 1|2|64|128, 255, 128, 0, 0, "Bronze", "Bronze", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeOrange, 2, Arrays.asList(new MaterialStack(Tin, 1), new MaterialStack(Copper, 3)), Arrays.asList(new TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.INSTRUMENTUM, 1)));
//    public static MaterialsOld BrownLimonite = new MaterialsOld(930, TextureSet.METALLIC, 1.0F, 0, 1, 1 |8 , 200, 100, 0, 0, "BrownLimonite", "Brown Limonite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeBrown, 2, Arrays.asList(new MaterialStack(Iron, 1), new MaterialStack(Hydrogen, 1), new MaterialStack(Oxygen, 2))); // FeO(OH)
//    public static MaterialsOld Calcite = new MaterialsOld(823, TextureSet.DULL, 			1.0F, 0, 1, 1 |8 , 250, 230, 220, 0, "Calcite", "Calcite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeOrange, 1, Arrays.asList(new MaterialStack(Calcium, 1), new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 3)));
//    public static MaterialsOld Cassiterite = new MaterialsOld(824, TextureSet.METALLIC, 	1.0F, 0, 1, 8 , 220, 220, 220, 0, "Cassiterite", "Cassiterite", 0, 0, -1, 0, false, false, 4, 3, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack(Tin, 1), new MaterialStack(Oxygen, 2)));
//    public static MaterialsOld Chalcopyrite = new MaterialsOld(855, TextureSet.DULL, 		1.0F, 0, 1, 1 |8 , 160, 120, 40, 0, "Chalcopyrite", "Chalcopyrite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeYellow, 1, Arrays.asList(new MaterialStack(Copper, 1), new MaterialStack(Iron, 1), new MaterialStack(Sulfur, 2)));
//    public static MaterialsOld Charcoal = new MaterialsOld(536, TextureSet.FINE, 			1.0F, 0, 1, 1|4, 100, 70, 70, 0, "Charcoal", "Charcoal", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeBlack, 1, Arrays.asList(new MaterialStack(Carbon, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.POTENTIA, 2), new TC_AspectStack(TC_Aspects.IGNIS, 2)));
//    public static MaterialsOld Chromite = new MaterialsOld(825, TextureSet.METALLIC, 		1.0F, 0, 1, 1, 35, 20, 15, 0, "Chromite", "Chromite", 0, 0, 1700, 1700, true, false, 6, 1, 1, Dyes.dyePink, 1, Arrays.asList(new MaterialStack(Iron, 1), new MaterialStack(Chrome, 2), new MaterialStack(Oxygen, 4)));
//    public static MaterialsOld Cinnabar = new MaterialsOld(826, TextureSet.ROUGH, 		1.0F, 0, 1, 1 |8 , 150, 0, 0, 0, "Cinnabar", "Cinnabar", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeBrown, 2, Arrays.asList(new MaterialStack(Mercury, 1), new MaterialStack(Sulfur, 1)));
//    public static MaterialsOld Water = new MaterialsOld(701, TextureSet.FLUID, 			1.0F, 0, 0, 16, 0, 0, 255, 0, "Water", "Water", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeBlue, 0, Arrays.asList(new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.AQUA, 2)));
//    public static MaterialsOld Clay = new MaterialsOld(805, TextureSet.ROUGH,				1.0F, 0, 1, 1, 200, 200, 220, 0, "Clay", "Clay", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeLightBlue, 1, Arrays.asList(new MaterialStack(Sodium, 2), new MaterialStack(Lithium, 1), new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 2), new MaterialStack(Water, 6)));
//    public static MaterialsOld Coal = new MaterialsOld(535, TextureSet.ROUGH, 			1.0F, 0, 1, 1|4|8, 70, 70, 70, 0, "Coal", "Coal", 0, 0, -1, 0, false, false, 2, 2, 1, Dyes.dyeBlack, 1, Arrays.asList(new MaterialStack(Carbon, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.POTENTIA, 2), new TC_AspectStack(TC_Aspects.IGNIS, 2)));
//    public static MaterialsOld Cobaltite = new MaterialsOld(827, TextureSet.METALLIC, 	1.0F, 0, 1, 1 |8 , 80, 80, 250, 0, "Cobaltite", "Cobaltite", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeBlue, 1, Arrays.asList(new MaterialStack(Cobalt, 1), new MaterialStack(Arsenic, 1), new MaterialStack(Sulfur, 1)));
//    public static MaterialsOld Sheldonite = new MaterialsOld(828, TextureSet.METALLIC, 	1.0F, 0, 1, 1 |8 , 255, 255, 200, 0, "Sheldonite", "Sheldonite", 0, 0, -1, 0, false, false, 5, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack(Platinum, 3), new MaterialStack(Nickel, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Palladium, 1)));
//    public static MaterialsOld Cupronickel = new MaterialsOld(310, TextureSet.METALLIC, 	6.0F, 64, 1, 1|2|64, 227, 150, 128, 0, "Cupronickel", "Cupronickel", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeOrange, 2, Arrays.asList(new MaterialStack(Copper, 1), new MaterialStack(Nickel, 1)));
//    public static MaterialsOld DarkAsh = new MaterialsOld(816, TextureSet.DULL, 			1.0F, 0, 1, 1, 50, 50, 50, 0, "DarkAsh", "Dark Ashes", 0, 0, -1, 0, false, false, 1, 2, 1, Dyes.dyeGray, Arrays.asList(new TC_AspectStack(TC_Aspects.IGNIS, 1), new TC_AspectStack(TC_Aspects.PERDITIO, 1)));
//    public static MaterialsOld Diamond = new MaterialsOld(500, TextureSet.DIAMOND, 		8.0F, 1280, 3, 1|4|8 |64|128, 200, 255, 255, 127, "Diamond", "Diamond", 0, 0, -1, 0, false, true, 5, 64, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack(Carbon, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.VITREUS, 3), new TC_AspectStack(TC_Aspects.LUCRUM, 4)));
//    public static MaterialsOld Electrum = new MaterialsOld(303, TextureSet.SHINY, 	   12.0F, 64, 2, 1|2|64|128, 255, 255, 100, 0, "Electrum", "Electrum", 0, 0, -1, 0, false, false, 4, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack(Silver, 1), new MaterialStack(Gold, 1)));
//    public static MaterialsOld Emerald = new MaterialsOld(501, TextureSet.EMERALD, 		7.0F, 256, 2, 1|4|8 |64, 80, 255, 80, 127, "Emerald", "Emerald", 0, 0, -1, 0, false, true, 5, 1, 1, Dyes.dyeGreen, 1, Arrays.asList(new MaterialStack(Beryllium, 3), new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 6), new MaterialStack(Oxygen, 18)), Arrays.asList(new TC_AspectStack(TC_Aspects.VITREUS, 3), new TC_AspectStack(TC_Aspects.LUCRUM, 5)));
//    public static MaterialsOld Galena = new MaterialsOld(830, TextureSet.DULL, 			1.0F, 0, 3, 1 |8 , 100, 60, 100, 0, "Galena", "Galena", 0, 0, -1, 0, false, false, 4, 1, 1, Dyes.dyePurple, 1, Arrays.asList(new MaterialStack(Lead, 3), new MaterialStack(Silver, 3), new MaterialStack(Sulfur, 2)));
//    public static MaterialsOld Garnierite = new MaterialsOld(906, TextureSet.METALLIC, 	1.0F, 0, 3, 1 |8 , 50, 200, 70, 0, "Garnierite", "Garnierite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightBlue, 1, Arrays.asList(new MaterialStack(Nickel, 1), new MaterialStack(Oxygen, 1)));
//    public static MaterialsOld Glyceryl = new MaterialsOld(714, TextureSet.FLUID, 		1.0F, 0, 1, 16, 0, 150, 150, 0, "Glyceryl", "Glyceryl Trinitrate", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeCyan, 1, Arrays.asList(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 5), new MaterialStack(Nitrogen, 3), new MaterialStack(Oxygen, 9)));
//    public static MaterialsOld GreenSapphire = new MaterialsOld(504, TextureSet.GEMH, 7.0F, 256, 2, 1|4|8 |64, 100, 200, 130, 127, "GreenSapphire", "Green Sapphire", 0, 0, -1, 0, false, true, 5, 1, 1, Dyes.dyeCyan, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 3)), Arrays.asList(new TC_AspectStack(TC_Aspects.LUCRUM, 5), new TC_AspectStack(TC_Aspects.VITREUS, 3)));
//    public static MaterialsOld Grossular = new MaterialsOld(831, TextureSet.ROUGH, 		1.0F, 0, 1, 1 |8 , 200, 100, 0, 0, "Grossular", "Grossular", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeOrange, 1, Arrays.asList(new MaterialStack(Calcium, 3), new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12)));
//    public static MaterialsOld HolyWater = new MaterialsOld(729, TextureSet.FLUID, 		1.0F, 0, 0, 16, 0, 0, 255, 0, "HolyWater", "Holy Water", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeBlue, 0, Arrays.asList(new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.AQUA, 2), new TC_AspectStack(TC_Aspects.AURAM, 1)));
//    public static MaterialsOld Ice = new MaterialsOld(702, TextureSet.SHINY, 				1.0F, 0, 0, 1| 16, 200, 200, 255, 0, "Ice", "Ice", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeBlue, 0, Arrays.asList(new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.GELUM, 2)));
//    public static MaterialsOld Ilmenite = new MaterialsOld(918, TextureSet.METALLIC, 		1.0F, 0, 3, 1 |8 , 70, 55, 50, 0, "Ilmenite", "Ilmenite", 0, 0, -1, 0, false, false, 1, 2, 1, Dyes.dyePurple, 0, Arrays.asList(new MaterialStack(Iron, 1), new MaterialStack(Titanium, 1), new MaterialStack(Oxygen, 3)));
//    public static MaterialsOld Rutile = new MaterialsOld(375, TextureSet.GEMH, 	1.0F, 0, 2, 1, 212, 13, 92, 0, "Rutile", "Rutile", 0, 0, -1, 0, false, false, 1, 2, 1, Dyes.dyeRed, 0, Arrays.asList(new MaterialStack(Titanium, 1), new MaterialStack(Oxygen, 2)));
//    public static MaterialsOld Bauxite = new MaterialsOld(822, TextureSet.DULL, 			1.0F, 0, 1, 1 |8 , 200, 100, 0, 0, "Bauxite", "Bauxite", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeBrown, 1, Arrays.asList(new MaterialStack(Rutile, 2), new MaterialStack(Aluminium, 16), new MaterialStack(Hydrogen, 10), new MaterialStack(Oxygen, 11)));
//    public static MaterialsOld Titaniumtetrachloride =new MaterialsOld(376,TextureSet.FLUID,1.0F, 0, 2, 16 , 212, 13, 92, 0, "Titaniumtetrachloride", "Titaniumtetrachloride", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeRed, 0, Arrays.asList(new MaterialStack(Titanium, 1), new MaterialStack(Chlorine, 4)));
//    public static MaterialsOld Magnesiumchloride = new MaterialsOld(377, TextureSet.DULL, 1.0F, 0, 2, 1|16 , 212, 13, 92, 0, "Magnesiumchloride", "Magnesiumchloride", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeRed, 0, Arrays.asList(new MaterialStack(Magnesium, 1), new MaterialStack(Chlorine, 2)));
//    public static MaterialsOld Invar = new MaterialsOld(302, TextureSet.METALLIC, 		6.0F, 256, 2, 1|2|64|128, 180, 180, 120, 0, "Invar", "Invar", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeBrown, 2, Arrays.asList(new MaterialStack(Iron, 2), new MaterialStack(Nickel, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.GELUM, 1)));
//    public static MaterialsOld Kanthal = new MaterialsOld(312, TextureSet.METALLIC, 		6.0F, 64, 2, 1|2|64, 194, 210, 223, 0, "Kanthal", "Kanthal", 0, 0, 1800, 1800, true, false, 1, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack(Iron, 1), new MaterialStack(Aluminium, 1), new MaterialStack(Chrome, 1)));
//    public static MaterialsOld Lazurite = new MaterialsOld(524, TextureSet.LAPIS, 		1.0F, 0, 1, 1|4|8 , 100, 120, 255, 0, "Lazurite", "Lazurite", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeCyan, 1, Arrays.asList(new MaterialStack(Aluminium, 6), new MaterialStack(Silicon, 6), new MaterialStack(Calcium, 8), new MaterialStack(Sodium, 8)));
//    public static MaterialsOld Magnalium = new MaterialsOld(313, TextureSet.DULL, 		6.0F, 256, 2, 1|2|64|128, 200, 190, 255, 0, "Magnalium", "Magnalium", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightBlue, 2, Arrays.asList(new MaterialStack(Magnesium, 1), new MaterialStack(Aluminium, 2)));
//    public static MaterialsOld Magnesite = new MaterialsOld(908, TextureSet.METALLIC, 	1.0F, 0, 2, 1 |8 , 250, 250, 180, 0, "Magnesite", "Magnesite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyePink, 1, Arrays.asList(new MaterialStack(Magnesium, 1), new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 3)));
//    public static MaterialsOld Magnetite = new MaterialsOld(870, TextureSet.METALLIC, 	1.0F, 0, 2, 1 |8 , 30, 30, 30, 0, "Magnetite", "Magnetite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeGray, 1, Arrays.asList(new MaterialStack(Iron, 3), new MaterialStack(Oxygen, 4)), Arrays.asList(new TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.MAGNETO, 1)));
//    public static MaterialsOld Molybdenite = new MaterialsOld(942, TextureSet.METALLIC, 	1.0F, 0, 2, 1 |8 , 25, 25, 25, 0, "Molybdenite", "Molybdenite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeBlue, 1, Arrays.asList(new MaterialStack(Molybdenum, 1), new MaterialStack(Sulfur, 2))); // MoS2 (also source of Re)
//    public static MaterialsOld Nichrome = new MaterialsOld(311, TextureSet.METALLIC, 		6.0F, 64, 2, 1|2|64, 205, 206, 246, 0, "Nichrome", "Nichrome", 0, 0, 2700, 2700, true, false, 1, 1, 1, Dyes.dyeRed, 2, Arrays.asList(new MaterialStack(Nickel, 4), new MaterialStack(Chrome, 1)));
//    public static MaterialsOld NiobiumTitanium = new MaterialsOld(360, TextureSet.DULL, 	1.0F, 0, 2, 1|2, 29, 29, 41, 0, "NiobiumTitanium", "Niobium-Titanium", 0, 0, 4500, 4500, true, false, 1, 1, 1, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack(Niobium, 1), new MaterialStack(Titanium, 1)));
//    public static MaterialsOld NitrogenDioxide = new MaterialsOld(717, TextureSet.FLUID, 	1.0F, 0, 1, 16, 100, 175, 255, 0, "NitrogenDioxide", "Nitrogen Dioxide", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeCyan, 1, Arrays.asList(new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 2)));
//    public static MaterialsOld Obsidian = new MaterialsOld(804, TextureSet.DULL, 			1.0F, 0, 3, 1, 80, 50, 100, 0, "Obsidian", "Obsidian", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeBlack, 1, Arrays.asList(new MaterialStack(Magnesium, 1), new MaterialStack(Iron, 1), new MaterialStack(Silicon, 2), new MaterialStack(Oxygen, 8)));
//    public static MaterialsOld Phosphate = new MaterialsOld(833, TextureSet.DULL, 		1.0F, 0, 1, 1 |8|16, 255, 255, 0, 0, "Phosphate", "Phosphate", 0, 0, -1, 0, false, false, 2, 1, 1, Dyes.dyeYellow, 1, Arrays.asList(new MaterialStack(Phosphor, 1), new MaterialStack(Oxygen, 4)));
//    public static MaterialsOld PigIron = new MaterialsOld(307, TextureSet.METALLIC, 		6.0F, 384, 2, 1|2|64, 200, 180, 180, 0, "PigIron", "Pig Iron", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyePink, 2, Arrays.asList(new MaterialStack(Iron, 1)));
//    public static MaterialsOld Plastic = new MaterialsOld(874, TextureSet.DULL, 			3.0F, 32, 1, 1|2|64|128, 200, 200, 200, 0, "Plastic", "Polyethylene", 0, 0, 400, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 0, Arrays.asList(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 2)), Arrays.asList(new TC_AspectStack(TC_Aspects.MOTUS, 2)));
//    public static MaterialsOld Epoxid = new MaterialsOld(470, TextureSet.DULL, 			3.0F, 32, 1, 1|2|64|128, 200, 140, 20, 0, "Epoxid", "Epoxy Resin", 0, 0, 400, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 0, Arrays.asList(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 4), new MaterialStack(Oxygen, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.MOTUS, 2)));
//    public static MaterialsOld Polydimethylsiloxane = new MaterialBuilder(633, TextureSet.FLUID, "Polydimethylsiloxane").addDust().setRGB(245, 245, 245).setColor(Dyes.dyeWhite).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 1), new MaterialStack(Silicon, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Silicone = new MaterialsOld(471, TextureSet.DULL, 			3.0F, 128, 1, 1|2|64|128, 220, 220, 220, 0, "Silicone", "Silicone Rubber", 0, 0, 900, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 0, Arrays.asList(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 1), new MaterialStack(Silicon, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.MOTUS, 2)));
//    public static MaterialsOld Polycaprolactam = new MaterialsOld(472, TextureSet.DULL, 	3.0F, 32, 1, 1|2|64|128, 50, 50, 50, 0, "Polycaprolactam", "Polycaprolactam", 0, 0, 500, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 0, Arrays.asList(new MaterialStack(Carbon, 6), new MaterialStack(Hydrogen, 11), new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.MOTUS, 2)));
//    public static MaterialsOld Polytetrafluoroethylene  = new MaterialsOld(473, TextureSet.DULL, 3.0F, 32, 1, 1|2|64|128, 100, 100, 100, 0, "Polytetrafluoroethylene", "Polytetrafluoroethylene", 0, 0, 1400, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 0, Arrays.asList(new MaterialStack(Carbon, 2), new MaterialStack(Fluorine, 4)), Arrays.asList(new TC_AspectStack(TC_Aspects.MOTUS, 2)));
//    public static MaterialsOld Powellite = new MaterialsOld(883, TextureSet.DULL, 		1.0F, 0, 2, 1 |8 , 255, 255, 0, 0, "Powellite", "Powellite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack(Calcium, 1), new MaterialStack(Molybdenum, 1), new MaterialStack(Oxygen, 4)));
//    public static MaterialsOld Pyrite = new MaterialsOld(834, TextureSet.ROUGH, 			1.0F, 0, 1, 1 |8 , 150, 120, 40, 0, "Pyrite", "Pyrite", 0, 0, -1, 0, false, false, 2, 1, 1, Dyes.dyeOrange, 1, Arrays.asList(new MaterialStack(Iron, 1), new MaterialStack(Sulfur, 2)));
//    public static MaterialsOld Pyrolusite = new MaterialsOld(943, TextureSet.DULL, 		1.0F, 0, 2, 1 |8 , 150, 150, 170, 0, "Pyrolusite", "Pyrolusite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, 1, Arrays.asList(new MaterialStack(Manganese, 1), new MaterialStack(Oxygen, 2)));
//    public static MaterialsOld Pyrope = new MaterialsOld(835, TextureSet.METALLIC, 		1.0F, 0, 2, 1 |8 , 120, 50, 100, 0, "Pyrope", "Pyrope", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyePurple, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Magnesium, 3), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12)));
//    public static MaterialsOld RockSalt = new MaterialsOld(944, TextureSet.FINE, 			1.0F, 0, 1, 1 |8 , 240, 200, 200, 0, "RockSalt", "Rock Salt", 0, 0, -1, 0, false, false, 2, 1, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack(Potassium, 1), new MaterialStack(Chlorine, 1)));
//    public static MaterialsOld Rubber = new MaterialsOld(880, TextureSet.SHINY, 			1.5F, 32, 0, 1|2|64|128, 0, 0, 0, 0, "Rubber", "Rubber", 0, 0, 400, 0, false, false, 1, 1, 1, Dyes.dyeBlack, 0, Arrays.asList(new MaterialStack(Carbon, 5), new MaterialStack(Hydrogen, 8)), Arrays.asList(new TC_AspectStack(TC_Aspects.MOTUS, 2)));
//    public static MaterialsOld RawRubber = new MaterialsOld(896, TextureSet.DULL, 		1.0F, 0, 0, 1, 204, 199, 137, 0, "RawRubber", "Raw Rubber", 0, 0, 400, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 0, Arrays.asList(new MaterialStack(Carbon, 5), new MaterialStack(Hydrogen, 8)), Arrays.asList(new TC_AspectStack(TC_Aspects.MOTUS, 2)));
//    public static MaterialsOld Ruby = new MaterialsOld(502, TextureSet.RUBY, 				7.0F, 256, 2, 1|4|8 |64, 255, 100, 100, 127, "Ruby", "Ruby", 0, 0, -1, 0, false, true, 5, 1, 1, Dyes.dyeRed, 1, Arrays.asList(new MaterialStack(Chrome, 1), new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 3)), Arrays.asList(new TC_AspectStack(TC_Aspects.LUCRUM, 6), new TC_AspectStack(TC_Aspects.VITREUS, 4)));
//    public static MaterialsOld Salt = new MaterialsOld(817, TextureSet.FINE, 				1.0F, 0, 1, 1 |8 , 250, 250, 250, 0, "Salt", "Salt", 0, 0, -1, 0, false, false, 2, 1, 1, Dyes.dyeWhite, 0, Arrays.asList(new MaterialStack(Sodium, 1), new MaterialStack(Chlorine, 1)));
//    public static MaterialsOld Saltpeter = new MaterialsOld(836, TextureSet.FINE, 		1.0F, 0, 1, 1 |8 , 230, 230, 230, 0, "Saltpeter", "Saltpeter", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack(Potassium, 1), new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 3)));
//    public static MaterialsOld SaltWater = new MaterialBuilder(692, TextureSet.FLUID, "Salt Water").addCell().addFluid().setRGB(0, 0, 200).setColor(Dyes.dyeBlue).build();
//    public static MaterialsOld Sapphire = new MaterialsOld(503, TextureSet.GEMV, 	7.0F, 256, 2, 1|4|8 |64, 100, 100, 200, 127, "Sapphire", "Sapphire", 0, 0, -1, 0, false, true, 5, 1, 1, Dyes.dyeBlue, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 3)), Arrays.asList(new TC_AspectStack(TC_Aspects.LUCRUM, 5), new TC_AspectStack(TC_Aspects.VITREUS, 3)));
//    public static MaterialsOld Scheelite = new MaterialsOld(910, TextureSet.DULL, 		1.0F, 0, 3, 1 |8 , 200, 140, 20, 0, "Scheelite", "Scheelite", 0, 0, 2500, 2500, false, false, 4, 1, 1, Dyes.dyeBlack, 0, Arrays.asList(new MaterialStack(Tungsten, 1), new MaterialStack(Calcium, 2), new MaterialStack(Oxygen, 4)));
//    public static MaterialsOld SiliconDioxide = new MaterialsOld(837, TextureSet.QUARTZ, 	1.0F, 0, 1, 1 |16, 200, 200, 200, 0, "SiliconDioxide", "Silicon Dioxide", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, 1, Arrays.asList(new MaterialStack(Silicon, 1), new MaterialStack(Oxygen, 2)));
//    public static MaterialsOld Snow = new MaterialsOld(728, TextureSet.FINE, 				1.0F, 0, 0, 1| 16, 250, 250, 250, 0, "Snow", "Snow", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 0, Arrays.asList(new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.GELUM, 1)));
//    public static MaterialsOld Sodalite = new MaterialsOld(525, TextureSet.LAPIS, 		1.0F, 0, 1, 1|4|8 , 20, 20, 255, 0, "Sodalite", "Sodalite", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeBlue, 1, Arrays.asList(new MaterialStack(Aluminium, 3), new MaterialStack(Silicon, 3), new MaterialStack(Sodium, 4), new MaterialStack(Chlorine, 1)));
//    public static MaterialsOld SodiumPersulfate = new MaterialsOld(718, TextureSet.FLUID, 1.0F, 0, 2, 16, 255, 255, 255, 0, "SodiumPersulfate", "Sodium Persulfate", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeOrange, 1, Arrays.asList(new MaterialStack(Sodium, 2), new MaterialStack(Sulfur, 2), new MaterialStack(Oxygen, 8)));
//    public static MaterialsOld SodiumSulfide = new MaterialsOld(719, TextureSet.FLUID, 	1.0F, 0, 2, 1, 255, 230, 128, 0, "SodiumSulfide", "Sodium Sulfide", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeOrange, 1, Arrays.asList(new MaterialStack(Sodium, 2), new MaterialStack(Sulfur, 1)));
//    public static MaterialsOld HydricSulfide = new MaterialsOld(460, TextureSet.FLUID, 	1.0F, 0, 2, 16, 255, 255, 255, 0, "HydricSulfide", "Hydrogen Sulfide", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeOrange, 0, Arrays.asList(new MaterialStack(Hydrogen, 2), new MaterialStack(Sulfur, 1)));
//
//    public static MaterialsOld OilHeavy = new MaterialsOld(730, TextureSet.FLUID, 		1.0F, 0, 0, 16, 10, 10, 10, 0, "OilHeavy", "Heavy Oil", 3, 32, -1, 0, false, false, 1, 1, 1, Dyes.dyeBlack);
//    public static MaterialsOld OilMedium = new MaterialsOld(731, TextureSet.FLUID, 		1.0F, 0, 0, 16, 10, 10, 10, 0, "OilMedium", "Raw Oil", 3, 24, -1, 0, false, false, 1, 1, 1, Dyes.dyeBlack);
//    public static MaterialsOld OilLight = new MaterialsOld(732, TextureSet.FLUID, 		1.0F, 0, 0, 16, 10, 10, 10, 0, "OilLight", "Light Oil", 3, 16, -1, 0, false, false, 1, 1, 1, Dyes.dyeBlack);
//
//    public static MaterialsOld NaturalGas = new MaterialsOld(733, TextureSet.FLUID, 		1.0F, 0, 1, 16, 255, 255, 255, 0, "NaturalGas", "Natural RefineryGas", 1, 15, -1, 0, false, false, 3, 1, 1, Dyes.dyeWhite);
//    public static MaterialsOld SulfuricGas = new MaterialsOld(734, TextureSet.FLUID, 		1.0F, 0, 1, 16, 255, 255, 255, 0, "SulfuricGas", "Sulfuric RefineryGas", 1, 20, -1, 0, false, false, 3, 1, 1, Dyes.dyeWhite);
//    public static MaterialsOld RefineryGas = new MaterialsOld(735, TextureSet.FLUID, 				1.0F, 0, 1, 16, 255, 255, 255, 0, "RefineryGas", "Refinery RefineryGas", 1, 128, -1, 0, false, false, 3, 1, 1, Dyes.dyeWhite).setCanBeCracked(true);
//    public static MaterialsOld SulfuricNaphtha = new MaterialsOld(736, TextureSet.FLUID, 	1.0F, 0, 0, 16, 255, 255, 0, 0, "SulfuricNaphtha", "Sulfuric Naphtha", 1, 32, -1, 0, false, false, 1, 1, 1, Dyes.dyeYellow);
//    public static MaterialsOld SulfuricLightFuel = new MaterialsOld(737, TextureSet.FLUID,1.0F, 0, 0, 16, 255, 255, 0, 0, "SulfuricLightFuel", "Sulfuric Light Fuel", 0, 32, -1, 0, false, false, 1, 1, 1, Dyes.dyeYellow);
//    public static MaterialsOld SulfuricHeavyFuel = new MaterialsOld(738, TextureSet.FLUID,1.0F, 0, 0, 16, 255, 255, 0, 0, "SulfuricHeavyFuel", "Sulfuric Heavy Fuel", 3, 32, -1, 0, false, false, 1, 1, 1, Dyes.dyeBlack);
//    public static MaterialsOld Naphtha = new MaterialsOld(739, TextureSet.FLUID, 			1.0F, 0, 0, 16, 255, 255, 0, 0, "Naphtha", "Naphtha", 1, 256, -1, 0, false, false, 1, 1, 1, Dyes.dyeYellow).setCanBeCracked(true);
//    public static MaterialsOld LightFuel = new MaterialsOld(740, TextureSet.FLUID, 		1.0F, 0, 0, 16, 255, 255, 0, 0, "LightFuel", "Light Fuel", 0, 256, -1, 0, false, false, 1, 1, 1, Dyes.dyeYellow).setCanBeCracked(true);
//    public static MaterialsOld HeavyFuel = new MaterialsOld(741, TextureSet.FLUID, 		1.0F, 0, 0, 16, 255, 255, 0, 0, "HeavyFuel", "Heavy Fuel", 3, 192, -1, 0, false, false, 1, 1, 1, Dyes.dyeBlack).setCanBeCracked(true);
//    public static MaterialsOld LPG = new MaterialsOld(742, TextureSet.FLUID, 				1.0F, 0, 0, 16, 255, 255, 0, 0, "LPG", "LPG", 1, 256, -1, 0, false, false, 1, 1, 1, Dyes.dyeYellow);
//
//    public static MaterialsOld Magnesia = new MaterialBuilder(621, TextureSet.DULL, "Magnesia").addDust().setRGB(255, 225, 225).setColor(Dyes.dyeWhite).addMats(new MaterialStack(Magnesium, 1), new MaterialStack(Oxygen, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Quicklime = new MaterialBuilder(622, TextureSet.DULL, "Quicklime").addDust().setRGB(240, 240, 240).setColor(Dyes.dyeWhite).addMats(new MaterialStack(Calcium, 1), new MaterialStack(Oxygen, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Potash = new MaterialBuilder(623, TextureSet.DULL, "Potash").addDust().setRGB(120, 66, 55).setColor(Dyes.dyeBrown).addMats(new MaterialStack(Potassium, 2), new MaterialStack(Oxygen, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld SodaAsh = new MaterialBuilder(624, TextureSet.DULL, "Soda Ash").addDust().setRGB(220, 220, 255).setColor(Dyes.dyeWhite).addMats(new MaterialStack(Sodium, 2), new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 3)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Brick = new MaterialBuilder(625, TextureSet.ROUGH, "Brick").addDust().setRGB(155, 86, 67).setColor(Dyes.dyeBrown).addMats(new MaterialStack(Aluminium, 4), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12)).build();
//    public static MaterialsOld Fireclay = new MaterialBuilder(626, TextureSet.ROUGH, "Fireclay").addDust().setRGB(173, 160, 155).setColor(Dyes.dyeBrown).addMats(new MaterialStack(Brick, 1)).build();
//    public static MaterialsOld BioDiesel = new MaterialBuilder(627, TextureSet.FLUID, "Bio Diesel").addCell().addFluid().setRGB(255, 128, 0).setColor(Dyes.dyeOrange).setFuelType(MaterialBuilder.DIESEL).setFuelPower(192).build();
//    public static MaterialsOld NitrationMixture = new MaterialBuilder(628, TextureSet.FLUID, "Nitration Mixture").addCell().setRGB(230, 226, 171).setColor(Dyes.dyeBrown).build();
//    public static MaterialsOld Glycerol = new MaterialBuilder(629, TextureSet.FLUID, "Glycerol").addCell().addFluid().setRGB(135, 222, 135).setColor(Dyes.dyeLime).setFuelType(MaterialBuilder.SEMIFLUID).setFuelType(164).addMats(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 8), new MaterialStack(Oxygen, 3)).addElectrolyzerRecipe().build();
//    public static MaterialsOld SodiumBisulfate = new MaterialBuilder(630, TextureSet.FLUID, "Sodium Bisulfate").addDust().setRGB(0, 68, 85).setColor(Dyes.dyeBlue).addMats(new MaterialStack(Sodium, 1), new MaterialStack(Hydrogen, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4)).build();
//    public static MaterialsOld PolyphenyleneSulfide = new MaterialBuilder(631, TextureSet.DULL, "Polyphenylene Sulfide").addDust().addMetal().addTool().addGear().setToolSpeed(3.0f).setDurability(32).setToolQuality(1).setRGB(170, 136, 0).setColor(Dyes.dyeBrown).addMats(new MaterialStack(Carbon, 6), new MaterialStack(Hydrogen, 4), new MaterialStack(Sulfur, 1)).build();
//    public static MaterialsOld Dichlorobenzene = new MaterialBuilder(632, TextureSet.FLUID, "Dichlorobenzene").addCell().addFluid().setRGB(0, 68, 85).setColor(Dyes.dyeBlue).addMats(new MaterialStack(Carbon, 6), new MaterialStack(Hydrogen, 4), new MaterialStack(Chlorine, 2)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Polystyrene = new MaterialBuilder(636, TextureSet.DULL, "Polystyrene").addDust().addMetal().addTool().addGear().setToolSpeed(3.0f).setDurability(32).setToolQuality(1).setRGB(190, 180, 170).setColor(Dyes.dyeLightGray).addMats(new MaterialStack(Carbon, 8), new MaterialStack(Hydrogen, 8)).build();
//    public static MaterialsOld Styrene = new MaterialBuilder(637, TextureSet.FLUID, "Styrene").addCell().addFluid().setRGB(210, 200, 190).setColor(Dyes.dyeBlack).addMats(new MaterialStack(Carbon, 8), new MaterialStack(Hydrogen, 8)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Isoprene = new MaterialBuilder(638, TextureSet.FLUID, "Isoprene").addCell().addFluid().setRGB(20, 20, 20).setColor(Dyes.dyeBlack).addMats(new MaterialStack(Carbon, 5), new MaterialStack(Hydrogen, 8)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Tetranitromethane = new MaterialBuilder(639, TextureSet.FLUID, "Tetranitromethane").addCell().addFluid().setRGB(15, 40, 40).setColor(Dyes.dyeBlack).addMats(new MaterialStack(Carbon, 1), new MaterialStack(Nitrogen, 4), new MaterialStack(Oxygen, 8)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Ethenone = new MaterialBuilder(641, TextureSet.FLUID, "Ethenone").addCell().addGas().setRGB(20, 20, 70).setColor(Dyes.dyeBlack).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Ethane = new MaterialBuilder(642, TextureSet.FLUID, "Ethane").addCell().addGas().setRGB(200, 200, 255).setColor(Dyes.dyeLightBlue).setFuelType(MaterialBuilder.GAS).setFuelPower(168).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 6)).addElectrolyzerRecipe().setCanBeCracked(true).build();
//    public static MaterialsOld Propane = new MaterialBuilder(643, TextureSet.FLUID, "Propane").addCell().addGas().setRGB(250, 226, 80).setColor(Dyes.dyeYellow).setFuelType(MaterialBuilder.GAS).setFuelPower(232).addMats(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 8)).addElectrolyzerRecipe().setCanBeCracked(true).build();
//    public static MaterialsOld Butane = new MaterialBuilder(644, TextureSet.FLUID, "Butane").addCell().addGas().setRGB(182, 55, 30).setColor(Dyes.dyeOrange).setFuelType(MaterialBuilder.GAS).setFuelPower(296).addMats(new MaterialStack(Carbon, 4), new MaterialStack(Hydrogen, 10)).addElectrolyzerRecipe().setCanBeCracked(true).build();
//    public static MaterialsOld Butene = new MaterialBuilder(645, TextureSet.FLUID, "Butene").addCell().addGas().setRGB(207, 80, 5).setColor(Dyes.dyeOrange).setFuelType(MaterialBuilder.GAS).setFuelPower(256).addMats(new MaterialStack(Carbon, 4), new MaterialStack(Hydrogen, 8)).addElectrolyzerRecipe().setCanBeCracked(true).build();
//    public static MaterialsOld Butadiene = new MaterialBuilder(646, TextureSet.FLUID, "Butadiene").addCell().addGas().setRGB(232, 105, 0).setColor(Dyes.dyeOrange).setFuelType(MaterialBuilder.GAS).setFuelPower(206).addMats(new MaterialStack(Carbon, 4), new MaterialStack(Hydrogen, 6)).addElectrolyzerRecipe().setCanBeCracked(true).build();
//    public static MaterialsOld RawStyreneButadieneRubber = new MaterialBuilder(634, TextureSet.SHINY, "Raw Styrene-Butadiene Rubber").addDust().setRGB(84, 64, 61).setColor(Dyes.dyeGray).addMats(new MaterialStack(Styrene, 1), new MaterialStack(Butadiene, 3)).build();
//    public static MaterialsOld StyreneButadieneRubber = new MaterialBuilder(635, TextureSet.SHINY, "Styrene-Butadiene Rubber").addDust().addMetal().addTool().addGear().setToolSpeed(3.0f).setDurability(128).setToolQuality(1).setRGB(33, 26, 24).setColor(Dyes.dyeBlack).addMats(new MaterialStack(Styrene, 1), new MaterialStack(Butadiene, 3)).build();
//    public static MaterialsOld Toluene = new MaterialBuilder(647, TextureSet.FLUID, "Toluene").addCell().setRGB(80, 29, 5).setColor(Dyes.dyeBrown).setFuelType(MaterialBuilder.GAS).setFuelPower(328).addMats(new MaterialStack(Carbon, 7), new MaterialStack(Hydrogen, 8)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Epichlorohydrin = new MaterialBuilder(648, TextureSet.FLUID, "Epichlorohydrin").addCell().setRGB(80, 29, 5).setColor(Dyes.dyeBrown).addMats(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 5), new MaterialStack(Chlorine, 1), new MaterialStack(Oxygen, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld PolyvinylChloride = new MaterialBuilder(649, TextureSet.DULL, "Polyvinyl Chloride").addDust().addDust().addTool().addGear().setToolSpeed(3.0f).setDurability(32).setToolQuality(1).setRGB(215, 230, 230).setColor(Dyes.dyeLightGray).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 3), new MaterialStack(Chlorine, 1)).build();
//    public static MaterialsOld VinylChloride = new MaterialBuilder(650, TextureSet.FLUID, "Vinyl Chloride").addCell().addGas().setRGB(225, 240, 240).setColor(Dyes.dyeLightGray).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 3), new MaterialStack(Chlorine, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld SulfurDioxide = new MaterialBuilder(651, TextureSet.FLUID, "Sulfur Dioxide").addCell().addGas().setRGB(200, 200, 25).setColor(Dyes.dyeYellow).addMats(new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 2)).addElectrolyzerRecipe().build();
//    public static MaterialsOld SulfurTrioxide = new MaterialBuilder(652, TextureSet.FLUID, "Sulfur Trioxide").addCell().addGas().setGasTemperature(344).setRGB(160, 160, 20).setColor(Dyes.dyeYellow).addMats(new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 3)).addElectrolyzerRecipe().build();
//    public static MaterialsOld NitricAcid = new MaterialBuilder(653, TextureSet.FLUID, "Nitric Acid").addCell().setRGB(230, 226, 171).addMats(new MaterialStack(Hydrogen, 1), new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 3)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Dimethylhydrazine = new MaterialBuilder(654, TextureSet.FLUID, "1,1-Dimethylhydrazine").addCell().addFluid().setRGB(0, 0, 85).setColor(Dyes.dyeBlue).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 8), new MaterialStack(Nitrogen, 2)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Chloramine = new MaterialBuilder(655, TextureSet.FLUID, "Chloramine").addCell().addFluid().setRGB(63, 159, 128).setColor(Dyes.dyeCyan).addMats(new MaterialStack(Nitrogen, 1), new MaterialStack(Hydrogen, 2), new MaterialStack(Chlorine, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Dimethylamine = new MaterialBuilder(656, TextureSet.FLUID, "Dimethylamine").addCell().addGas().setRGB(85, 68, 105).setColor(Dyes.dyeGray).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 7), new MaterialStack(Nitrogen, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld DinitrogenTetroxide = new MaterialBuilder(657, TextureSet.FLUID, "Dinitrogen Tetroxide").addCell().addGas().setRGB(0, 65, 132).setColor(Dyes.dyeBlue).addMats(new MaterialStack(Nitrogen, 2), new MaterialStack(Oxygen, 4)).addElectrolyzerRecipe().build();
//    public static MaterialsOld NitricOxide = new MaterialBuilder(658, TextureSet.FLUID, "Nitric Oxide").addCell().addGas().setRGB(125, 200, 240).setColor(Dyes.dyeCyan).addMats(new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Ammonia = new MaterialBuilder(659, TextureSet.FLUID, "Ammonia").addCell().addGas().setRGB(63, 52, 128).setColor(Dyes.dyeBlue).addMats(new MaterialStack(Nitrogen, 1), new MaterialStack(Hydrogen, 3)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Dimethyldichlorosilane = new MaterialBuilder(663, TextureSet.FLUID, "Dimethyldichlorosilane").addCell().addFluid().setRGB(68, 22, 80).setColor(Dyes.dyePurple).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 6), new MaterialStack(Chlorine, 2), new MaterialStack(Silicon, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Chloromethane = new MaterialBuilder(664, TextureSet.FLUID, "Chloromethane").addCell().addGas().setRGB(200, 44, 160).setColor(Dyes.dyeMagenta).addMats(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 3), new MaterialStack(Chlorine, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld PhosphorousPentoxide = new MaterialBuilder(665, TextureSet.FLUID, "Phosphorous Pentoxide").addCell().addDust().setRGB(220, 220, 0).setColor(Dyes.dyeYellow).addMats(new MaterialStack(Phosphor, 4), new MaterialStack(Oxygen, 10)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Tetrafluoroethylene = new MaterialBuilder(666, TextureSet.FLUID, "Tetrafluoroethylene").addCell().addGas().setRGB(125, 125, 125).setColor(Dyes.dyeGray).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Fluorine, 4)).addElectrolyzerRecipe().build();
//    public static MaterialsOld HydrofluoricAcid = new MaterialBuilder(667, TextureSet.FLUID, "Hydrofluoric Acid").setName("HydrofluoricAcid_GT5U").addCell().addFluid().setRGB(0, 136, 170).setColor(Dyes.dyeLightBlue).addMats(new MaterialStack(Hydrogen, 1), new MaterialStack(Fluorine, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Chloroform = new MaterialBuilder(668, TextureSet.FLUID, "Chloroform").addCell().addFluid().setRGB(137, 44, 160).setColor(Dyes.dyePurple).addMats(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 1), new MaterialStack(Chlorine, 3)).addElectrolyzerRecipe().build();
//    public static MaterialsOld BisphenolA = new MaterialBuilder(669, TextureSet.FLUID, "Bisphenol A").addCell().setRGB(212, 170, 0).setColor(Dyes.dyeBrown).addMats(new MaterialStack(Carbon, 15), new MaterialStack(Hydrogen, 16), new MaterialStack(Oxygen, 2)).addElectrolyzerRecipe().build();
//    public static MaterialsOld AceticAcid = new MaterialBuilder(670, TextureSet.FLUID, "Acetic Acid").addCell().addFluid().setRGB(200, 180, 160).setColor(Dyes.dyeWhite).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 4), new MaterialStack(Oxygen, 2)).addElectrolyzerRecipe().build();
//    public static MaterialsOld CalciumAcetate = new MaterialBuilder(671, TextureSet.RUBY, "Calcium Acetate").addDust().addCell().setRGB(255, 255, 255).setColor(Dyes.dyeWhite).addMats(new MaterialStack(Calcium, 1), new MaterialStack(AceticAcid, 2)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Acetone = new MaterialBuilder(672, TextureSet.FLUID, "Acetone").addCell().addFluid().setRGB(175, 175, 175).setColor(Dyes.dyeWhite).addMats(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Methanol = new MaterialBuilder(673, TextureSet.FLUID, "Methanol").addCell().addFluid().setRGB(170, 136, 0).setColor(Dyes.dyeBrown).setFuelPower(84).addMats(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 4), new MaterialStack(Oxygen, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld CarbonMonoxide = new MaterialBuilder(674, TextureSet.FLUID, "Carbon Monoxide").addCell().addGas().setRGB(14, 72, 128).setColor(Dyes.dyeBrown).setFuelType(MaterialBuilder.GAS).setFuelPower(24).addMats(new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld MetalMixture = new MaterialBuilder(676, TextureSet.METALLIC, "Metal Mixture").addDust().setRGB(80, 45, 22).setColor(Dyes.dyeBrown).build();
//    public static MaterialsOld Ethylene = new MaterialBuilder(677, TextureSet.FLUID, "Ethylene").addCell().addGas().setRGB(225, 225, 225).setColor(Dyes.dyeWhite).setFuelType(MaterialBuilder.GAS).setFuelPower(128).addMats(new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 4)).addElectrolyzerRecipe().setCanBeCracked(true).build();
//    public static MaterialsOld Propene = new MaterialBuilder(678, TextureSet.FLUID, "Propene").addCell().addGas().setRGB(255, 221, 85).setColor(Dyes.dyeYellow).setFuelType(MaterialBuilder.GAS).setFuelPower(192).addMats(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 6)).addElectrolyzerRecipe().setCanBeCracked(true).build();
//    public static MaterialsOld VinylAcetate = new MaterialBuilder(679, TextureSet.FLUID, "Vinyl Acetate").addCell().addFluid().setRGB(255, 179, 128).setColor(Dyes.dyeOrange).addMats(new MaterialStack(Carbon, 4), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 2)).addElectrolyzerRecipe().build();
//    public static MaterialsOld PolyvinylAcetate = new MaterialBuilder(680, TextureSet.FLUID, "Polyvinyl Acetate").addCell().addFluid().setRGB(255, 153, 85).setColor(Dyes.dyeOrange).addMats(new MaterialStack(Carbon, 4), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 2)).build();
//    public static MaterialsOld MethylAcetate = new MaterialBuilder(681, TextureSet.FLUID, "Methyl Acetate").addCell().addFluid().setRGB(238, 198, 175).setColor(Dyes.dyeOrange).addMats(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 2)).addElectrolyzerRecipe().build();
//    public static MaterialsOld AllylChloride = new MaterialBuilder(682, TextureSet.FLUID, "Allyl Chloride").addCell().addFluid().setRGB(135, 222, 170).setColor(Dyes.dyeCyan).addMats(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 5), new MaterialStack(Chlorine, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld HydrochloricAcid = new MaterialBuilder(683, TextureSet.FLUID, "Hydrochloric Acid").setName("HydrochloricAcid_GT5U").addCell().addFluid().setRGB(183, 200, 196).setColor(Dyes.dyeLightGray).addMats(new MaterialStack(Hydrogen, 1), new MaterialStack(Chlorine, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld HypochlorousAcid = new MaterialBuilder(684, TextureSet.FLUID, "Hypochlorous Acid").addCell().addFluid().setRGB(111, 138, 145).setColor(Dyes.dyeGray).addMats(new MaterialStack(Hydrogen, 1), new MaterialStack(Chlorine, 1), new MaterialStack(Oxygen, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld SodiumHydroxide = new MaterialBuilder(685, TextureSet.DULL, "Sodium Hydroxide").setName("SodiumHydroxide_GT5U").addDust().setRGB(0, 51, 128).setColor(Dyes.dyeBlue).addMats(new MaterialStack(Sodium, 1), new MaterialStack(Oxygen, 1), new MaterialStack(Hydrogen, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Benzene = new MaterialBuilder(686, TextureSet.FLUID, "Benzene").addCell().addFluid().setRGB(26, 26, 26).setColor(Dyes.dyeGray).setFuelType(MaterialBuilder.GAS).setFuelPower(288).addMats(new MaterialStack(Carbon, 6), new MaterialStack(Hydrogen, 6)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Phenol = new MaterialBuilder(687, TextureSet.FLUID, "Phenol").addCell().addFluid().setRGB(120, 68, 33).setColor(Dyes.dyeBrown).setFuelType(MaterialBuilder.GAS).setFuelPower(288).addMats(new MaterialStack(Carbon, 6), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 1)).addElectrolyzerRecipe().build();
//    public static MaterialsOld Cumene = new MaterialBuilder(688, TextureSet.FLUID, "Cumene").addCell().addFluid().setRGB(85, 34, 0).setColor(Dyes.dyeBrown).addMats(new MaterialStack(Carbon, 9), new MaterialStack(Hydrogen, 12)).addElectrolyzerRecipe().build();
//    public static MaterialsOld PhosphoricAcid = new MaterialBuilder(689, TextureSet.FLUID, "Phosphoric Acid").setName("PhosphoricAcid_GT5U").addCell().addFluid().setRGB(220, 220, 0).setColor(Dyes.dyeYellow).addMats(new MaterialStack(Hydrogen, 3), new MaterialStack(Phosphor, 1), new MaterialStack(Oxygen, 4)).addElectrolyzerRecipe().build();
//
//    public static MaterialsOld SolderingAlloy = new MaterialsOld(314, TextureSet.DULL, 	1.0F, 0, 1, 1|2, 220, 220, 230, 0, "SolderingAlloy", "Soldering Alloy", 0, 0, 400, 400, false, false, 1, 1, 1, Dyes.dyeWhite, 2, Arrays.asList(new MaterialStack(Tin, 9), new MaterialStack(Antimony, 1)));
//    public static MaterialsOld GalliumArsenide = new MaterialsOld(980, TextureSet.DULL, 	1.0F, 0, 1, 1|2, 160, 160, 160, 0, "GalliumArsenide", "Gallium Arsenide", 0, 0, -1, 1200, true, false, 1, 1, 1, Dyes.dyeGray, 2, Arrays.asList(new MaterialStack(Arsenic, 1), new MaterialStack(Gallium, 1)));
//    public static MaterialsOld IndiumGalliumPhosphide = new MaterialsOld(981, TextureSet.DULL, 	1.0F, 0, 1, 1|2, 160, 140, 190, 0, "IndiumGalliumPhosphide", "Indium Gallium Phosphide", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLightGray, 2, Arrays.asList(new MaterialStack(Indium, 1), new MaterialStack(Gallium, 1), new MaterialStack(Phosphor, 1)));
//    public static MaterialsOld Spessartine = new MaterialsOld(838, TextureSet.DULL, 		1.0F, 0, 2, 1 |8 , 255, 100, 100, 0, "Spessartine", "Spessartine", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeRed, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Manganese, 3), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12)));
//    public static MaterialsOld Sphalerite = new MaterialsOld(839, TextureSet.DULL, 		1.0F, 0, 1, 1 |8 , 255, 255, 255, 0, "Sphalerite", "Sphalerite", 0, 0, -1, 0, false, false, 2, 1, 1, Dyes.dyeYellow, 1, Arrays.asList(new MaterialStack(Zinc, 1), new MaterialStack(Sulfur, 1)));
//    public static MaterialsOld StainlessSteel = new MaterialsOld(306, TextureSet.SHINY, 	7.0F, 480, 2, 1|2|64|128, 200, 200, 220, 0, "StainlessSteel", "Stainless Steel", 0, 0, -1, 1700, true, false, 1, 1, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack(Iron, 6), new MaterialStack(Chrome, 1), new MaterialStack(Manganese, 1), new MaterialStack(Nickel, 1)));
//    public static MaterialsOld Steel = new MaterialsOld(305, TextureSet.METALLIC, 		6.0F, 512, 2, 1|2|64|128, 128, 128, 128, 0, "Steel", "Steel", 0, 0, 1811, 1000, true, false, 4, 51, 50, Dyes.dyeGray, 1, Arrays.asList(new MaterialStack(Iron, 50), new MaterialStack(Carbon, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.ORDO, 1)));
//    public static MaterialsOld Stibnite = new MaterialsOld(945, TextureSet.METALLIC, 		1.0F, 0, 2, 1 |8 , 70, 70, 70, 0, "Stibnite", "Stibnite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 2, Arrays.asList(new MaterialStack(Antimony, 2), new MaterialStack(Sulfur, 3)));
//    public static MaterialsOld SulfuricAcid = new MaterialsOld(720, TextureSet.FLUID, 	1.0F, 0, 2, 16, 255, 128, 0, 0, "SulfuricAcid", "Sulfuric Acid", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeOrange, 1, Arrays.asList(new MaterialStack(Hydrogen, 2), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4)));
//    public static MaterialsOld Tanzanite = new MaterialsOld(508, TextureSet.GEMV, 7.0F, 256, 2, 1|4|8 |64, 64, 0, 200, 127, "Tanzanite", "Tanzanite", 0, 0, -1, 0, false, true, 5, 1, 1, Dyes.dyePurple, 1, Arrays.asList(new MaterialStack(Calcium, 2), new MaterialStack(Aluminium, 3), new MaterialStack(Silicon, 3), new MaterialStack(Hydrogen, 1), new MaterialStack(Oxygen, 13)), Arrays.asList(new TC_AspectStack(TC_Aspects.LUCRUM, 5), new TC_AspectStack(TC_Aspects.VITREUS, 3)));
//    public static MaterialsOld Tetrahedrite = new MaterialsOld(840, TextureSet.DULL, 		1.0F, 0, 2, 1 |8 , 200, 32, 0, 0, "Tetrahedrite", "Tetrahedrite", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeRed, 2, Arrays.asList(new MaterialStack(Copper, 3), new MaterialStack(Antimony, 1), new MaterialStack(Sulfur, 3), new MaterialStack(Iron, 1))); //Cu3SbS3 + x(Fe, Zn)6Sb2S9
//    public static MaterialsOld Topaz = new MaterialsOld(507, TextureSet.GEMH, 	7.0F, 256, 3, 1|4|8 |64, 255, 128, 0, 127, "Topaz", "Topaz", 0, 0, -1, 0, false, true, 5, 1, 1, Dyes.dyeOrange, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 1), new MaterialStack(Fluorine, 2), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 6)), Arrays.asList(new TC_AspectStack(TC_Aspects.LUCRUM, 6), new TC_AspectStack(TC_Aspects.VITREUS, 4)));
//    public static MaterialsOld Tungstate = new MaterialsOld(841, TextureSet.DULL, 		1.0F, 0, 3, 1 |8 , 55, 50, 35, 0, "Tungstate", "Tungstate", 0, 0, 2500, 2500, true, false, 4, 1, 1, Dyes.dyeBlack, 0, Arrays.asList(new MaterialStack(Tungsten, 1), new MaterialStack(Lithium, 2), new MaterialStack(Oxygen, 4)));
//    public static MaterialsOld Ultimet = new MaterialsOld(344, TextureSet.SHINY, 			9.0F, 2048, 4, 1|2|64|128, 180, 180, 230, 0, "Ultimet", "Ultimet", 0, 0, 2700, 2700, true, false, 1, 1, 1, Dyes.dyeLightBlue, 1, Arrays.asList(new MaterialStack(Cobalt, 5), new MaterialStack(Chrome, 2), new MaterialStack(Nickel, 1), new MaterialStack(Molybdenum, 1))); // 54% Cobalt, 26% Chromium, 9% Nickel, 5% Molybdenum, 3% Iron, 2% Tungsten, 0.8% Manganese, 0.3% Silicon, 0.08% Nitrogen and 0.06% Carbon
//    public static MaterialsOld Uraninite = new MaterialsOld(922, TextureSet.METALLIC, 	1.0F, 0, 3, 1 |8 , 35, 35, 35, 0, "Uraninite", "Uraninite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeLime, 2, Arrays.asList(new MaterialStack(Uranium, 1), new MaterialStack(Oxygen, 2)));
//    public static MaterialsOld Uvarovite = new MaterialsOld(842, TextureSet.DIAMOND, 		1.0F, 0, 2, 1, 180, 255, 180, 0, "Uvarovite", "Uvarovite", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeGreen, 1, Arrays.asList(new MaterialStack(Calcium, 3), new MaterialStack(Chrome, 2), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12)));
//    public static MaterialsOld VanadiumGallium = new MaterialsOld(357, TextureSet.SHINY, 	1.0F, 0, 2, 1|2, 128, 128, 140, 0, "VanadiumGallium", "Vanadium-Gallium", 0, 0, 4500, 4500, true, false, 1, 1, 1, Dyes.dyeGray, 2, Arrays.asList(new MaterialStack(Vanadium, 3), new MaterialStack(Gallium, 1)));
//    public static MaterialsOld Wood = new MaterialsOld(809, TextureSet.WOOD, 				2.0F, 16, 0, 1|2|64|128, 100, 50, 0, 0, "Wood", "Wood", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeBrown, 0, Arrays.asList(new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 1), new MaterialStack(Hydrogen, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.ARBOR, 2)));
//    public static MaterialsOld WroughtIron = new MaterialsOld(304, TextureSet.METALLIC, 	6.0F, 384, 2, 1|2|64|128, 200, 180, 180, 0, "WroughtIron", "Wrought Iron", 0, 0, 1811, 0, false, false, 3, 1, 1, Dyes.dyeLightGray, 2, Arrays.asList(new MaterialStack(Iron, 1)));
//    public static MaterialsOld Wulfenite = new MaterialsOld(882, TextureSet.DULL, 		1.0F, 0, 3, 1 |8 , 255, 128, 0, 0, "Wulfenite", "Wulfenite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeOrange, 2, Arrays.asList(new MaterialStack(Lead, 1), new MaterialStack(Molybdenum, 1), new MaterialStack(Oxygen, 4)));
//    public static MaterialsOld YellowLimonite = new MaterialsOld(931, TextureSet.METALLIC,1.0F, 0, 2, 1 |8 , 200, 200, 0, 0, "YellowLimonite", "Yellow Limonite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack(Iron, 1), new MaterialStack(Hydrogen, 1), new MaterialStack(Oxygen, 2))); // FeO(OH) + a bit Ni and Co
//    public static MaterialsOld YttriumBariumCuprate  = new MaterialsOld(358, TextureSet.METALLIC, 1.0F, 0, 2, 1|2, 80, 64, 70, 0, "YttriumBariumCuprate", "Yttrium Barium Cuprate", 0, 0, 4500, 4500, true, false, 1, 1, 1, Dyes.dyeGray, 0, Arrays.asList(new MaterialStack(Yttrium, 1), new MaterialStack(Barium, 2), new MaterialStack(Copper, 3), new MaterialStack(Oxygen, 7)));
//
//    /**
//     * Second Degree Compounds
//     */
//    public static MaterialsOld WoodSealed = new MaterialsOld( 889, TextureSet.WOOD, 		3.0F, 24, 0, 1|2|64|128, 80, 40, 0, 0, "WoodSealed", "Sealed Wood", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeBrown, 0, Arrays.asList(new MaterialStack(Wood, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.ARBOR, 2), new TC_AspectStack(TC_Aspects.FABRICO, 1)));
//    public static MaterialsOld Glass = new MaterialsOld( 890, TextureSet.GLASS, 			1.0F, 4, 0, 1|4, 250, 250, 250, 220, "Glass", "Glass", 0, 0, 1500, 0, false, true, 1, 1, 1, Dyes.dyeWhite, 2, Arrays.asList(new MaterialStack(SiliconDioxide, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.VITREUS, 2)));
//    public static MaterialsOld Lignite = new MaterialsOld( 538, TextureSet.LIGNITE, 		1.0F, 0, 0, 1|4|8 , 100, 70, 70, 0, "Lignite", "Lignite Coal", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeBlack, 1, Arrays.asList(new MaterialStack(Carbon, 3), new MaterialStack(Water, 1)));
//    public static MaterialsOld Olivine = new MaterialsOld( 505, TextureSet.RUBY, 			7.0F, 256, 2, 1|4|8 |64, 150, 255, 150, 127, "Olivine", "Olivine", 0, 0, -1, 0, false, true, 5, 1, 1, Dyes.dyeLime, 1, Arrays.asList(new MaterialStack(Magnesium, 2), new MaterialStack(Iron, 1), new MaterialStack(SiliconDioxide, 2)), Arrays.asList(new TC_AspectStack(TC_Aspects.LUCRUM, 4), new TC_AspectStack(TC_Aspects.VITREUS, 2)));
//    public static MaterialsOld Opal = new MaterialsOld( 510, TextureSet.OPAL, 			7.0F, 256, 2, 1|4|8 |64, 0, 0, 255, 0, "Opal", "Opal", 0, 0, -1, 0, false, true, 3, 1, 1, Dyes.dyeBlue, 1, Arrays.asList(new MaterialStack(SiliconDioxide, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.LUCRUM, 5), new TC_AspectStack(TC_Aspects.VITREUS, 3)));
//    public static MaterialsOld Amethyst = new MaterialsOld( 509, TextureSet.FLINT, 		7.0F, 256, 3, 1|4|8 |64, 210, 50, 210, 127, "Amethyst", "Amethyst", 0, 0, -1, 0, false, true, 3, 1, 1, Dyes.dyePink, 1, Arrays.asList(new MaterialStack(SiliconDioxide, 4), new MaterialStack(Iron, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.LUCRUM, 6), new TC_AspectStack(TC_Aspects.VITREUS, 4)));
//    public static MaterialsOld Redstone = new MaterialsOld( 810, TextureSet.ROUGH, 		1.0F, 0, 2, 1 |8 , 200, 0, 0, 0, "Redstone", "Redstone", 0, 0, 500, 0, false, false, 3, 1, 1, Dyes.dyeRed, 2, Arrays.asList(new MaterialStack(Silicon, 1), new MaterialStack(Pyrite, 5), new MaterialStack(Ruby, 1), new MaterialStack(Mercury, 3)), Arrays.asList(new TC_AspectStack(TC_Aspects.MACHINA, 1), new TC_AspectStack(TC_Aspects.POTENTIA, 2)));
//    public static MaterialsOld Lapis = new MaterialsOld( 526, TextureSet.LAPIS, 			1.0F, 0, 1, 1|4|8 , 70, 70, 220, 0, "Lapis", "Lapis", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeBlue, 2, Arrays.asList(new MaterialStack(Lazurite, 12), new MaterialStack(Sodalite, 2), new MaterialStack(Pyrite, 1), new MaterialStack(Calcite, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.SENSUS, 1)));
//    public static MaterialsOld Blaze = new MaterialsOld( 801, TextureSet.POWDER, 			2.0F, 16, 1, 1|64, 255, 200, 0, 0, "Blaze", "Blaze", 0, 0, 6400, 0, false, false, 2, 3, 2, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack(DarkAsh, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Magic, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.PRAECANTATIO, 2), new TC_AspectStack(TC_Aspects.IGNIS, 4)));
//    public static MaterialsOld EnderPearl = new MaterialsOld( 532, TextureSet.SHINY, 		1.0F, 16, 1, 1|4, 108, 220, 200, 0, "EnderPearl", "Enderpearl", 0, 0, -1, 0, false, false, 1, 16, 10, Dyes.dyeGreen, 1, Arrays.asList(new MaterialStack(Beryllium, 1), new MaterialStack(Potassium, 4), new MaterialStack(Nitrogen, 5), new MaterialStack(Magic, 6)), Arrays.asList(new TC_AspectStack(TC_Aspects.ALIENIS, 4), new TC_AspectStack(TC_Aspects.ITER, 4), new TC_AspectStack(TC_Aspects.PRAECANTATIO, 2)));
//    public static MaterialsOld EnderEye = new MaterialsOld( 533, TextureSet.SHINY, 		1.0F, 16, 1, 1|4, 160, 250, 230, 0, "EnderEye", "Endereye", 5, 10, -1, 0, false, false, 1, 2, 1, Dyes.dyeGreen, 2, Arrays.asList(new MaterialStack(EnderPearl, 1), new MaterialStack(Blaze, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.SENSUS, 4), new TC_AspectStack(TC_Aspects.ALIENIS, 4), new TC_AspectStack(TC_Aspects.ITER, 4), new TC_AspectStack(TC_Aspects.PRAECANTATIO, 3), new TC_AspectStack(TC_Aspects.IGNIS, 2)));
//    public static MaterialsOld Flint = new MaterialsOld( 802, TextureSet.FLINT, 			2.5F, 64, 1, 1|64, 0, 32, 64, 0, "Flint", "Flint", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeGray, 2, Arrays.asList(new MaterialStack(SiliconDioxide, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.TERRA, 1), new TC_AspectStack(TC_Aspects.INSTRUMENTUM, 1)));
//    public static MaterialsOld Apatite = new MaterialsOld(530, TextureSet.DIAMOND, 		1.0F, 0, 1, 1|4|8, 200, 200, 255, 0, "Apatite", "Apatite", 0, 0, -1, 0, false, false, 2, 1, 1, Dyes.dyeCyan, 1, Arrays.asList(new MaterialStack(Calcium, 5), new MaterialStack(Phosphate, 3), new MaterialStack(Chlorine, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.MESSIS, 2)));
//    public static MaterialsOld Alumite = new MaterialsOld(-1, TextureSet.METALLIC, 		1.5F, 64, 0, 1|2|64, 255, 255, 255, 0, "Alumite", "Alumite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyePink, 2, Arrays.asList(new MaterialStack(Aluminium, 5), new MaterialStack(Iron, 2), new MaterialStack(Obsidian, 2)), Arrays.asList(new TC_AspectStack(TC_Aspects.STRONTIO, 2)));
//    public static MaterialsOld Manyullyn = new MaterialsOld(-1, TextureSet.METALLIC, 		1.5F, 64, 0, 1|2|64, 255, 255, 255, 0, "Manyullyn", "Manyullyn", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyePurple, 2, Arrays.asList(new MaterialStack(Cobalt, 1), new MaterialStack(Ardite, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.STRONTIO, 2)));
//    public static MaterialsOld SterlingSilver = new MaterialsOld( 350, TextureSet.SHINY, 13.0F, 128, 2, 1|2|64|128, 250, 220, 225, 0, "SterlingSilver", "Sterling Silver", 0, 0, -1, 1700, true, false, 4, 1, 1, Dyes.dyeWhite, 2, Arrays.asList(new MaterialStack(Copper, 1), new MaterialStack(Silver, 4)));
//    public static MaterialsOld RoseGold = new MaterialsOld( 351, TextureSet.SHINY, 	   14.0F, 128, 2, 1|2|64|128, 255, 230, 30, 0, "RoseGold", "Rose Gold", 0, 0, -1, 1600, true, false, 4, 1, 1, Dyes.dyeOrange, 2, Arrays.asList(new MaterialStack(Copper, 1), new MaterialStack(Gold, 4)));
//    public static MaterialsOld BlackBronze = new MaterialsOld( 352, TextureSet.DULL, 	   12.0F, 256, 2, 1|2|64|128, 100, 50, 125, 0, "BlackBronze", "Black Bronze", 0, 0, -1, 2000, true, false, 4, 1, 1, Dyes.dyePurple, 2, Arrays.asList(new MaterialStack(Gold, 1), new MaterialStack(Silver, 1), new MaterialStack(Copper, 3)));
//    public static MaterialsOld BismuthBronze = new MaterialsOld( 353, TextureSet.DULL, 	8.0F, 256, 2, 1|2|64|128, 100, 125, 125, 0, "BismuthBronze", "Bismuth Bronze", 0, 0, -1, 1100, true, false, 4, 1, 1, Dyes.dyeCyan, 2, Arrays.asList(new MaterialStack(Bismuth, 1), new MaterialStack(Zinc, 1), new MaterialStack(Copper, 3)));
//    public static MaterialsOld BlackSteel = new MaterialsOld( 334, TextureSet.METALLIC, 	6.5F, 768, 2, 1|2|64, 100, 100, 100, 0, "BlackSteel", "Black Steel", 0, 0, -1, 1200, true, false, 4, 1, 1, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack(Nickel, 1), new MaterialStack(BlackBronze, 1), new MaterialStack(Steel, 3)));
//    public static MaterialsOld RedSteel = new MaterialsOld( 348, TextureSet.METALLIC, 	7.0F, 896, 2, 1|2|64, 140, 100, 100, 0, "RedSteel", "Red Steel", 0, 0, -1, 1300, true, false, 4, 1, 1, Dyes.dyeRed, 2, Arrays.asList(new MaterialStack(SterlingSilver, 1), new MaterialStack(BismuthBronze, 1), new MaterialStack(Steel, 2), new MaterialStack(BlackSteel, 4)));
//    public static MaterialsOld BlueSteel = new MaterialsOld( 349, TextureSet.METALLIC, 	7.5F, 1024, 2, 1|2|64, 100, 100, 140, 0, "BlueSteel", "Blue Steel", 0, 0, -1, 1400, true, false, 4, 1, 1, Dyes.dyeBlue, 2, Arrays.asList(new MaterialStack(RoseGold, 1), new MaterialStack(Brass, 1), new MaterialStack(Steel, 2), new MaterialStack(BlackSteel, 4)));
//    public static MaterialsOld DamascusSteel = new MaterialsOld( 335, TextureSet.METALLIC,8.0F, 1280, 2, 1|2|64, 110, 110, 110, 0, "DamascusSteel", "Damascus Steel", 0, 0, 2000, 1500, true, false, 4, 1, 1, Dyes.dyeGray, 2, Arrays.asList(new MaterialStack(Steel, 1)));
//    public static MaterialsOld TungstenSteel = new MaterialsOld( 316, TextureSet.METALLIC,8.0F, 2560, 4, 1|2|64|128, 100, 100, 160, 0, "TungstenSteel", "Tungstensteel", 0, 0, -1, 3000, true, false, 4, 1, 1, Dyes.dyeBlue, 2, Arrays.asList(new MaterialStack(Steel, 1), new MaterialStack(Tungsten, 1)));
//    public static MaterialsOld NitroFuel = new MaterialsOld( 709, TextureSet.FLUID, 		1.0F, 0, 2, 16, 200, 255, 0, 0, "NitroFuel", "Cetane-Boosted Diesel", 0, 512, -1, 0, false, false, 1, 1, 1, Dyes.dyeLime);
//    public static MaterialsOld Mithril = new MaterialsOld(331, TextureSet.SHINY, 		   14.0F, 64, 3, 1|2|64, 255, 255, 210, 0, "Mithril", "Mithril", 0, 0, -1, 0, false, false, 4, 3, 2, Dyes.dyeLightBlue, 2, Arrays.asList(new MaterialStack(MaterialsOld.Platinum, 2), new MaterialStack(MaterialsOld.Magic, 1)));
//    public static MaterialsOld RedAlloy = new MaterialsOld( 308, TextureSet.DULL, 		1.0F, 0, 0, 1|2, 200, 0, 0, 0, "RedAlloy", "Red Alloy", 0, 0, -1, 0, false, false, 3, 5, 1, Dyes.dyeRed, 2, Arrays.asList(new MaterialStack(Copper, 1), new MaterialStack(Redstone, 4)), Arrays.asList(new TC_AspectStack(TC_Aspects.MACHINA, 3)));
//    public static MaterialsOld CobaltBrass = new MaterialsOld( 343, TextureSet.METALLIC, 	8.0F, 256, 2, 1|2|64|128, 180, 180, 160, 0, "CobaltBrass", "Cobalt Brass", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeOrange, 2, Arrays.asList(new MaterialStack(Brass, 7), new MaterialStack(Aluminium, 1), new MaterialStack(Cobalt, 1)));
//    public static MaterialsOld Phosphorus = new MaterialsOld( 534, TextureSet.FLINT, 		1.0F, 0, 2, 1|4|8|16, 255, 255, 0, 0, "Phosphorus", "Phosphorus", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack(Calcium, 3), new MaterialStack(Phosphate, 2)));
//    public static MaterialsOld Basalt = new MaterialsOld( 844, TextureSet.ROUGH, 			1.0F, 0, 1, 1, 30, 20, 20, 0, "Basalt", "Basalt", 0, 0, -1, 0, false, false, 2, 1, 1, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack(Olivine, 1), new MaterialStack(Calcite, 3), new MaterialStack(Flint, 8), new MaterialStack(DarkAsh, 4)), Arrays.asList(new TC_AspectStack(TC_Aspects.TENEBRAE, 1)));
//    public static MaterialsOld GarnetRed = new MaterialsOld( 527, TextureSet.RUBY, 		7.0F, 128, 2, 1|4|8 |64, 200, 80, 80, 127, "GarnetRed", "Red Garnet", 0, 0, -1, 0, false, true, 4, 1, 1, Dyes.dyeRed, 2, Arrays.asList(new MaterialStack(Pyrope, 3), new MaterialStack(Almandine, 5), new MaterialStack(Spessartine, 8)), Arrays.asList(new TC_AspectStack(TC_Aspects.VITREUS, 3)));
//    public static MaterialsOld GarnetYellow = new MaterialsOld( 528, TextureSet.RUBY, 	7.0F, 128, 2, 1|4|8 |64, 200, 200, 80, 127, "GarnetYellow", "Yellow Garnet", 0, 0, -1, 0, false, true, 4, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack(Andradite, 5), new MaterialStack(Grossular, 8), new MaterialStack(Uvarovite, 3)), Arrays.asList(new TC_AspectStack(TC_Aspects.VITREUS, 3)));
//    public static MaterialsOld Marble = new MaterialsOld( 845, TextureSet.FINE, 			1.0F, 0, 1, 1, 200, 200, 200, 0, "Marble", "Marble", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 2, Arrays.asList(new MaterialStack(Magnesium, 1), new MaterialStack(Calcite, 7)), Arrays.asList(new TC_AspectStack(TC_Aspects.PERFODIO, 1)));
//    public static MaterialsOld Sugar = new MaterialsOld( 803, TextureSet.FINE, 			1.0F, 0, 1, 1, 250, 250, 250, 0, "Sugar", "Sugar", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeWhite, 1, Arrays.asList(new MaterialStack(Carbon, 12), new MaterialStack(Water, 11)), Arrays.asList(new TC_AspectStack(TC_Aspects.HERBA, 1), new TC_AspectStack(TC_Aspects.AQUA, 1), new TC_AspectStack(TC_Aspects.AER, 1)));
//    public static MaterialsOld Thaumium = new MaterialsOld(330, TextureSet.METALLIC, 	   12.0F, 256, 3, 1|2|64|128, 150, 100, 200, 0, "Thaumium", "Thaumium", 0, 0, -1, 0, false, false, 5, 2, 1, Dyes.dyePurple, 0, Arrays.asList(new MaterialStack(Iron, 1), new MaterialStack(Magic, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.PRAECANTATIO, 1)));
//    public static MaterialsOld PotassiumFeldspar = new MaterialsOld( 847, TextureSet.FINE,1.0F, 0, 1, 1, 120, 40, 40, 0, "PotassiumFeldspar", "Potassium Feldspar", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyePink, 1, Arrays.asList(new MaterialStack(Potassium, 1), new MaterialStack(Aluminium, 1), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 8)));
//    public static MaterialsOld Biotite = new MaterialsOld( 848, TextureSet.METALLIC, 		1.0F, 0, 1, 1, 20, 30, 20, 0, "Biotite", "Biotite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeGray, 1, Arrays.asList(new MaterialStack(Potassium, 1), new MaterialStack(Magnesium, 3), new MaterialStack(Aluminium, 3), new MaterialStack(Fluorine, 2), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 10)));
//    public static MaterialsOld GraniteBlack = new MaterialsOld( 849, TextureSet.ROUGH, 	4.0F, 64, 3, 1|64|128, 10, 10, 10, 0, "GraniteBlack", "Black Granite", 0, 0, -1, 0, false, false, 0, 1, 1, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack(SiliconDioxide, 4), new MaterialStack(Biotite, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.TUTAMEN, 1)));
//    public static MaterialsOld GraniteRed = new MaterialsOld( 850, TextureSet.ROUGH, 		4.0F, 64, 3, 1|64|128, 255, 0, 128, 0, "GraniteRed", "Red Granite", 0, 0, -1, 0, false, false, 0, 1, 1, Dyes.dyeMagenta, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(PotassiumFeldspar, 1), new MaterialStack(Oxygen, 3)), Arrays.asList(new TC_AspectStack(TC_Aspects.TUTAMEN, 1)));
//    public static MaterialsOld VanadiumMagnetite = new MaterialsOld( 923, TextureSet.METALLIC, 1.0F, 0, 2, 1 |8 , 35, 35, 60, 0, "VanadiumMagnetite", "Vanadium Magnetite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack(Magnetite, 1), new MaterialStack(Vanadium, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.MAGNETO, 1))); // Mixture of Fe3O4 and V2O5
//    public static MaterialsOld BasalticMineralSand = new MaterialsOld( 935, TextureSet.SAND, 1.0F, 0, 1, 1, 40, 50, 40, 0, "BasalticMineralSand", "Basaltic Mineral Sand", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack(Magnetite, 1), new MaterialStack(Basalt, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.MAGNETO, 1)));
//    public static MaterialsOld GraniticMineralSand = new MaterialsOld( 936, TextureSet.SAND, 1.0F, 0, 1, 1, 40, 60, 60, 0, "GraniticMineralSand", "Granitic Mineral Sand", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack(Magnetite, 1), new MaterialStack(GraniteBlack, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.MAGNETO, 1)));
//    public static MaterialsOld Bastnasite = new MaterialsOld( 905, TextureSet.FINE, 		1.0F, 0, 2, 1 |8 , 200, 110, 45, 0, "Bastnasite", "Bastnasite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeNULL, 1, Arrays.asList(new MaterialStack(Cerium, 1), new MaterialStack(Carbon, 1), new MaterialStack(Fluorine, 1), new MaterialStack(Oxygen, 3))); // (Ce, La, Y)CO3F
//    public static MaterialsOld Pentlandite = new MaterialsOld( 909, TextureSet.DULL, 		1.0F, 0, 2, 1 |8 , 165, 150, 5, 0, "Pentlandite", "Pentlandite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeNULL, 1, Arrays.asList(new MaterialStack(Nickel, 9), new MaterialStack(Sulfur, 8))); // (Fe, Ni)9S8
//    public static MaterialsOld Spodumene = new MaterialsOld( 920, TextureSet.DULL, 		1.0F, 0, 2, 1 |8 , 190, 170, 170, 0, "Spodumene", "Spodumene", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeNULL, 1, Arrays.asList(new MaterialStack(Lithium, 1), new MaterialStack(Aluminium, 1), new MaterialStack(Silicon, 2), new MaterialStack(Oxygen, 6))); // LiAl(SiO3)2
//    public static MaterialsOld Tantalite = new MaterialsOld( 921, TextureSet.METALLIC, 	1.0F, 0, 3, 1 |8 , 145, 80, 40, 0, "Tantalite", "Tantalite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeNULL, 1, Arrays.asList(new MaterialStack(Manganese, 1), new MaterialStack(Tantalum, 2), new MaterialStack(Oxygen, 6))); // (Fe, Mn)Ta2O6 (also source of Nb)
//    public static MaterialsOld Lepidolite = new MaterialsOld( 907, TextureSet.FINE, 		1.0F, 0, 2, 1 |8 , 240, 50, 140, 0, "Lepidolite", "Lepidolite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeNULL, 1, Arrays.asList(new MaterialStack(Potassium, 1), new MaterialStack(Lithium, 3), new MaterialStack(Aluminium, 4), new MaterialStack(Fluorine, 2), new MaterialStack(Oxygen, 10))); // K(Li, Al, Rb)3(Al, Si)4O10(F, OH)2
//    public static MaterialsOld Glauconite = new MaterialsOld( 933, TextureSet.DULL, 		1.0F, 0, 2, 1 |8 , 130, 180, 60, 0, "Glauconite", "Glauconite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeNULL, 1, Arrays.asList(new MaterialStack(Potassium, 1), new MaterialStack(Magnesium, 2), new MaterialStack(Aluminium, 4), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 12))); // (K, Na)(Fe3+, Al, Mg)2(Si, Al)4O10(OH)2
//    public static MaterialsOld Bentonite = new MaterialsOld( 927, TextureSet.ROUGH, 		1.0F, 0, 2, 1 |8 , 245, 215, 210, 0, "Bentonite", "Bentonite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeNULL, 1, Arrays.asList(new MaterialStack(Sodium, 1), new MaterialStack(Magnesium, 6), new MaterialStack(Silicon, 12), new MaterialStack(Hydrogen, 6), new MaterialStack(Water, 5), new MaterialStack(Oxygen, 36))); // (Na, Ca)0.33(Al, Mg)2(Si4O10)(OH)2 nH2O
//    public static MaterialsOld Pitchblende = new MaterialsOld( 873, TextureSet.DULL, 		1.0F, 0, 3, 1 |8 , 200, 210, 0, 0, "Pitchblende", "Pitchblende", 0, 0, -1, 0, false, false, 5, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack(Uraninite, 3), new MaterialStack(Thorium, 1), new MaterialStack(Lead, 1)));
//    public static MaterialsOld Monazite = new MaterialsOld( 520, TextureSet.DIAMOND, 		1.0F, 0, 1, 1|4|8 , 50, 70, 50, 0, "Monazite", "Monazite", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeGreen, 1, Arrays.asList(new MaterialStack(RareEarth, 1), new MaterialStack(Phosphate, 1))); // Wikipedia: (Ce, La, Nd, Th, Sm, Gd)PO4 Monazite also smelt-extract to Helium, it is brown like the rare earth Item Monazite sand deposits are inevitably of the monazite-(Ce) composition. Typically, the lanthanides in such monazites contain about 45ִ8% cerium, about 24% lanthanum, about 17% neodymium, about 5% praseodymium, and minor quantities of samarium, gadolinium, and yttrium. Europium concentrations tend to be low, about 0.05% Thorium content of monazite is variable and sometimes can be up to 20ֳ0%
//    public static MaterialsOld Malachite = new MaterialsOld( 871, TextureSet.DULL, 		1.0F, 0, 2, 1 |8 , 5, 95, 5, 0, "Malachite", "Malachite", 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeGreen, 1, Arrays.asList(new MaterialStack(Copper, 2), new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 5))); // Cu2CO3(OH)2
//    public static MaterialsOld Barite = new MaterialsOld( 904, TextureSet.DULL, 			1.0F, 0, 2, 1 |8 , 230, 235, 255, 0, "Barite", "Barite", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeNULL, 1, Arrays.asList(new MaterialStack(Barium, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4)));
//    public static MaterialsOld Talc = new MaterialsOld( 902, TextureSet.DULL, 			1.0F, 0, 2, 1 |8, 90, 180, 90, 0, "Talc", "Talc", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeNULL, 1, Arrays.asList(new MaterialStack(Magnesium, 3), new MaterialStack(Silicon, 4), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 12))); // H2Mg3(SiO3)4
//    public static MaterialsOld Soapstone = new MaterialsOld( 877, TextureSet.DULL, 		1.0F, 0, 1, 1 |8 , 95, 145, 95, 0, "Soapstone", "Soapstone", 0, 0, -1, 0, false, false, 1, 1, 1, Dyes.dyeNULL, 1, Arrays.asList(new MaterialStack(Magnesium, 3), new MaterialStack(Silicon, 4), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 12))); // H2Mg3(SiO3)4
//    public static MaterialsOld Concrete = new MaterialsOld( 947, TextureSet.ROUGH, 		1.0F, 0, 1, 1, 100, 100, 100, 0, "Concrete", "Concrete", 0, 0, 300, 0, false, false, 0, 1, 1, Dyes.dyeGray, 0, Arrays.asList(new MaterialStack(Stone, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.TERRA, 1)));
//    public static MaterialsOld IronMagnetic = new MaterialsOld( 354, TextureSet.MAGNETIC, 6.0F, 256, 2, 1|2|64|128, 200, 200, 200, 0, "IronMagnetic", "Magnetic Iron", 0, 0, -1, 0, false, false, 4, 51, 50, Dyes.dyeGray, 1, Arrays.asList(new MaterialStack(Iron, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_AspectStack(TC_Aspects.MAGNETO, 1)));
//    public static MaterialsOld SteelMagnetic = new MaterialsOld( 355, TextureSet.MAGNETIC,6.0F, 512, 2, 1|2|64|128, 128, 128, 128, 0, "SteelMagnetic", "Magnetic Steel", 0, 0, 1000, 1000, true, false, 4, 51, 50, Dyes.dyeGray, 1, Arrays.asList(new MaterialStack(Steel, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.METALLUM, 1), new TC_AspectStack(TC_Aspects.ORDO, 1), new TC_AspectStack(TC_Aspects.MAGNETO, 1)));
//    public static MaterialsOld NeodymiumMagnetic=new MaterialsOld(356,TextureSet.MAGNETIC,7.0F, 512, 2, 1|2|64|128, 100, 100, 100, 0, "NeodymiumMagnetic", "Magnetic Neodymium", 0, 0, 1297, 1297, true, false, 4, 51, 50, Dyes.dyeGray, 1, Arrays.asList(new MaterialStack(Neodymium, 1)), Arrays.asList(new TC_AspectStack(TC_Aspects.METALLUM, 1), new TC_AspectStack(TC_Aspects.MAGNETO, 3)));
//    public static MaterialsOld TungstenCarbide =new MaterialsOld(370,TextureSet.METALLIC,14.0F, 1280, 4, 1|2|64|128, 51, 0, 102, 0, "TungstenCarbide", "Tungstencarbide", 0, 0, 2460, 2460, true, false, 4, 1, 1, Dyes.dyeBlack, 2, Arrays.asList(new MaterialStack(Tungsten, 1), new MaterialStack(Carbon, 1)));
//    public static MaterialsOld VanadiumSteel = new MaterialsOld( 371, TextureSet.METALLIC,3.0F, 1920, 3, 1|2|64|128, 192, 192, 192, 0, "VanadiumSteel", "Vanadiumsteel", 0, 0, 1453, 1453, true, false, 4, 1, 1, Dyes.dyeWhite, 2, Arrays.asList(new MaterialStack(Vanadium, 1), new MaterialStack(Chrome, 1), new MaterialStack(Steel, 7)));
//    public static MaterialsOld HSSG = new MaterialsOld( 372, TextureSet.METALLIC, 	   10.0F, 4000, 3, 1|2|64|128, 153, 153, 0, 0, "HSSG", "HSS-G", 0, 0, 4500, 4500, true, false, 4, 1, 1, Dyes.dyeYellow, 2, Arrays.asList(new MaterialStack(TungstenSteel, 5), new MaterialStack(Chrome, 1), new MaterialStack(Molybdenum, 2), new MaterialStack(Vanadium, 1)));
//    public static MaterialsOld HSSE = new MaterialsOld( 373, TextureSet.METALLIC, 	   10.0F, 5120, 4, 1|2|64|128, 51, 102, 0, 0, "HSSE", "HSS-E", 0, 0, 5400, 5400, true, false, 4, 1, 1, Dyes.dyeBlue, 2, Arrays.asList(new MaterialStack(HSSG, 6), new MaterialStack(Cobalt, 1), new MaterialStack(Manganese, 1), new MaterialStack(Silicon, 1)));
//    public static MaterialsOld HSSS = new MaterialsOld( 374, TextureSet.METALLIC, 	   14.0F, 3000, 4, 1|2|64|128, 102, 0, 51, 0, "HSSS", "HSS-S", 0, 0, 5400, 5400, true, false, 4, 1, 1, Dyes.dyeRed, 2, Arrays.asList(new MaterialStack(HSSG, 6), new MaterialStack(Iridium, 2), new MaterialStack(Osmium, 1)));
//    public static MaterialsOld DilutedSulfuricAcid = new MaterialBuilder(640, TextureSet.FLUID, "Diluted Sulfuric Acid").addCell().addFluid().setRGB(192, 120, 32).setColor(Dyes.dyeOrange).addMats(new MaterialStack(SulfuricAcid, 1)).build();
//
//    /**
//     * MaterialsOld which are renamed automatically
//     */
//    @Deprecated public static MaterialsOld IridiumAndSodiumOxide = new MaterialsOld(IridiumSodiumOxide, false);
//    @Deprecated public static MaterialsOld Ashes = new MaterialsOld(Ash, false);
//    @Deprecated public static MaterialsOld DarkAshes = new MaterialsOld(DarkAsh, false);
//    @Deprecated public static MaterialsOld Abyssal = new MaterialsOld(Basalt, false);
//    @Deprecated public static MaterialsOld AluminumBrass = new MaterialsOld(AluminiumBrass, false);
//    @Deprecated public static MaterialsOld Aluminum = new MaterialsOld(Aluminium, false);
//    @Deprecated public static MaterialsOld NaturalAluminum = new MaterialsOld(Aluminium, false);
//    @Deprecated public static MaterialsOld NaturalAluminium = new MaterialsOld(Aluminium, false);
//    @Deprecated public static MaterialsOld Americum = new MaterialsOld(Americium, false);
//    @Deprecated public static MaterialsOld Beryl = new MaterialsOld(Emerald, false);
//    @Deprecated public static MaterialsOld BlackGranite = new MaterialsOld(GraniteBlack, false);
//    @Deprecated public static MaterialsOld CalciumCarbonate = new MaterialsOld(Calcite, false);
//    @Deprecated public static MaterialsOld CrackedLightFuel = new MaterialsOld(LightFuel, false);
//    @Deprecated public static MaterialsOld CrackedHeavyFuel = new MaterialsOld(HeavyFuel, false);
//    @Deprecated public static MaterialsOld CreosoteOil = new MaterialsOld(Creosote, false);
//    @Deprecated public static MaterialsOld Chromium = new MaterialsOld(Chrome, false);
//    @Deprecated public static MaterialsOld Diesel = new MaterialsOld(Fuel, false);
//    @Deprecated public static MaterialsOld Enderpearl = new MaterialsOld(EnderPearl, false);
//    @Deprecated public static MaterialsOld Endereye = new MaterialsOld(EnderEye, false);
//    @Deprecated public static MaterialsOld EyeOfEnder = new MaterialsOld(EnderEye, false);
//    @Deprecated public static MaterialsOld Eyeofender = new MaterialsOld(EnderEye, false);
//    @Deprecated public static MaterialsOld Flour = new MaterialsOld(Wheat, false);
//    @Deprecated public static MaterialsOld Garnet = new MaterialsOld(GarnetRed, true);
//    @Deprecated public static MaterialsOld Granite = new MaterialsOld(GraniteBlack, false);
//    @Deprecated public static MaterialsOld Goethite = new MaterialsOld(BrownLimonite, false);
//    @Deprecated public static MaterialsOld Kalium = new MaterialsOld(Potassium, false);
//    @Deprecated public static MaterialsOld Lapislazuli = new MaterialsOld(Lapis, false);
//    @Deprecated public static MaterialsOld LapisLazuli = new MaterialsOld(Lapis, false);
//    @Deprecated public static MaterialsOld Monazit = new MaterialsOld(Monazite, false);
//    @Deprecated public static MaterialsOld Natrium = new MaterialsOld(Sodium, false);
//    @Deprecated public static MaterialsOld Mythril = new MaterialsOld(Mithril, false);
//    @Deprecated public static MaterialsOld NitroDiesel = new MaterialsOld(NitroFuel, false);
//    @Deprecated public static MaterialsOld Naquadriah = new MaterialsOld(Naquadria, false);
//    @Deprecated public static MaterialsOld Obby = new MaterialsOld(Obsidian, false);
//    @Deprecated public static MaterialsOld Peridot = new MaterialsOld(Olivine, true);
//    @Deprecated public static MaterialsOld Phosphorite = new MaterialsOld(Phosphorus, true);
//    @Deprecated public static MaterialsOld Quarried = new MaterialsOld(Marble, false);
//    @Deprecated public static MaterialsOld Quicksilver = new MaterialsOld(Mercury, true);
//    @Deprecated public static MaterialsOld QuickSilver = new MaterialsOld(Mercury, false);
//    @Deprecated public static MaterialsOld RefinedIron = new MaterialsOld(Iron, false);
//    @Deprecated public static MaterialsOld RedGranite = new MaterialsOld(GraniteRed, false);
//    @Deprecated public static MaterialsOld Sheldonite = new MaterialsOld(Sheldonite, false);
//    @Deprecated public static MaterialsOld Soulsand = new MaterialsOld(SoulSand, false);
//    @Deprecated public static MaterialsOld Titan = new MaterialsOld(Titanium, false);
//    @Deprecated public static MaterialsOld Uran = new MaterialsOld(Uranium, false);
//    @Deprecated public static MaterialsOld Wolframite = new MaterialsOld(Tungstate, false);
//    @Deprecated public static MaterialsOld Wolframium = new MaterialsOld(Tungsten, false);
//    @Deprecated public static MaterialsOld Wolfram = new MaterialsOld(Tungsten, false);
//
//    public final short[] mRGBa = new short[]{255, 255, 255, 0}, mMoltenRGBa = new short[]{255, 255, 255, 0};
//    public TextureSet mIconSet;
//    public int mMetaItemSubID;
//    public boolean mUnificatable;
//    public MaterialsOld mMaterialInto;
//    public List<MaterialStack> mProcessInto = new ArrayList<MaterialStack>();
//    public List<MaterialsOld> mByProducts = new ArrayList<MaterialsOld>(), mOreReRegistrations = new ArrayList<MaterialsOld>();
//    public List<TC_Aspects.TC_AspectStack> mAspects = new ArrayList<TC_Aspects.TC_AspectStack>();
//    public ArrayList<ItemStack> mMaterialItems = new ArrayList<ItemStack>();
//    public Collection<SubTag> mSubTags = new LinkedHashSet<SubTag>();
//    public Enchantment mEnchantmentTools = null, mEnchantmentArmors = null;
//    public byte mEnchantmentToolsLevel = 0, mEnchantmentArmorsLevel = 0;
//    public boolean mBlastFurnaceRequired = false, mTransparent = false;
//    public float mToolSpeed = 1.0F, mHeatDamage = 0.0F;
//    public String mChemicalFormula = "?", mName = "null", mDefaultLocalName = "null", mCustomID = "null", mConfigSection = "null";
//    public Dyes mColor = Dyes.dyeNULL;
//    public short mMeltingPoint = 0, mBlastFurnaceTemp = 0, mGasTemp = 0;
//    public int mTypes = 0;
//    public int mDurability = 16, mFuelPower = 0, mFuelType = 0, mExtraData = 0, mOreValue = 0, mOreMultiplier = 1, mByProductMultiplier = 1, mSmeltingMultiplier = 1;
//    public int mDensityMultiplier = 1, mDensityDivider = 1;
//    public long mDensity = M;
//    public Element mElement = null;
//    public MaterialsOld mDirectSmelting = this, mOreReplacement = this, mMacerateInto = this, mSmeltInto = this, mArcSmeltInto = this, mHandleMaterial = this;
//    public byte mToolQuality = 0;
//    public boolean mHasParentMod = true, mHasPlasma = false, mHasGas = false, mCustomOre = false;
//    public Fluid mSolid = null, mFluid = null, mGas = null, mPlasma = null;
//
//    private boolean hasCorrespondingFluid = false, hasCorrespondingGas = false, canBeCracked = false;
//    private Fluid[] hydroCrackedFluids = new Fluid[3], steamCrackedFluids = new Fluid[3];
//
//    /**
//     * This Fluid is used as standard Unit for Molten MaterialsOld. 1296 is a Molten Block, that means 144 is one Material Unit worth of fluid.
//     */
//    public Fluid mStandardMoltenFluid = null;
//
//    static {
//        initSubTags();
//        Iron					.mOreReRegistrations.add(AnyIron	);
//        PigIron					.mOreReRegistrations.add(AnyIron	);
//        WroughtIron				.mOreReRegistrations.add(AnyIron	);
//
//        Copper					.mOreReRegistrations.add(AnyCopper	);
//        AnnealedCopper			.mOreReRegistrations.add(AnyCopper	);
//
//        Bronze					.mOreReRegistrations.add(AnyBronze	);
//
//        Rubber					.mOreReRegistrations.add(AnyRubber);
//        StyreneButadieneRubber	.mOreReRegistrations.add(AnyRubber);
//        Silicone				.mOreReRegistrations.add(AnyRubber);
//
//        StyreneButadieneRubber	.mOreReRegistrations.add(AnySyntheticRubber);
//        Silicone				.mOreReRegistrations.add(AnySyntheticRubber);
//
//        WoodSealed				.setMaceratingInto(Wood				);
//        NetherBrick				.setMaceratingInto(Netherrack		);
//
//        NeodymiumMagnetic		.setSmeltingInto(Neodymium			).setMaceratingInto(Neodymium		).setArcSmeltingInto(Neodymium			);
//        SteelMagnetic			.setSmeltingInto(Steel				).setMaceratingInto(Steel			).setArcSmeltingInto(Steel				);
//        Iron					.setSmeltingInto(Iron				).setMaceratingInto(Iron			).setArcSmeltingInto(WroughtIron		);
//        AnyIron					.setSmeltingInto(Iron				).setMaceratingInto(Iron			).setArcSmeltingInto(WroughtIron		);
//        PigIron					.setSmeltingInto(Iron				).setMaceratingInto(Iron			).setArcSmeltingInto(WroughtIron		);
//        WroughtIron				.setSmeltingInto(Iron				).setMaceratingInto(Iron			).setArcSmeltingInto(WroughtIron		);
//        IronMagnetic			.setSmeltingInto(Iron				).setMaceratingInto(Iron			).setArcSmeltingInto(WroughtIron		);
//        Copper					.setSmeltingInto(Copper				).setMaceratingInto(Copper			).setArcSmeltingInto(AnnealedCopper		);
//        AnyCopper				.setSmeltingInto(Copper				).setMaceratingInto(Copper			).setArcSmeltingInto(AnnealedCopper		);
//        AnnealedCopper			.setSmeltingInto(Copper				).setMaceratingInto(Copper			).setArcSmeltingInto(AnnealedCopper		);
//        Netherrack				.setSmeltingInto(NetherBrick		);
//        Sand					.setSmeltingInto(Glass				);
//        Ice						.setSmeltingInto(Water				);
//        Snow					.setSmeltingInto(Water				);
//
//        Mercury					.add(SubTag.SMELTING_TO_GEM);
//        Cinnabar				.setDirectSmelting(Mercury		).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT).add(SubTag.SMELTING_TO_GEM);
//        Tetrahedrite			.setDirectSmelting(Copper		).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
//        Chalcopyrite			.setDirectSmelting(Copper		).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
//        Malachite				.setDirectSmelting(Copper		).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
//        Pentlandite				.setDirectSmelting(Nickel		).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
//        Sphalerite				.setDirectSmelting(Zinc			).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
//        Pyrite					.setDirectSmelting(Iron			).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
//        BasalticMineralSand		.setDirectSmelting(Iron			).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
//        GraniticMineralSand		.setDirectSmelting(Iron			).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
//        YellowLimonite			.setDirectSmelting(Iron			).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
//        BrownLimonite			.setDirectSmelting(Iron			);
//        BandedIron				.setDirectSmelting(Iron			);
//        Cassiterite				.setDirectSmelting(Tin			);
//        Garnierite				.setDirectSmelting(Nickel		);
//        Cobaltite				.setDirectSmelting(Cobalt		);
//        Stibnite				.setDirectSmelting(Antimony		);
//        Sheldonite				.setDirectSmelting(Platinum		);
//        Pyrolusite				.setDirectSmelting(Manganese	);
//        Magnesite				.setDirectSmelting(Magnesium	);
//        Molybdenite				.setDirectSmelting(Molybdenum	);
//
//        Amber					.setOreMultiplier( 2).setSmeltingMultiplier( 2);
//        InfusedAir				.setOreMultiplier( 2).setSmeltingMultiplier( 2);
//        InfusedFire				.setOreMultiplier( 2).setSmeltingMultiplier( 2);
//        InfusedEarth			.setOreMultiplier( 2).setSmeltingMultiplier( 2);
//        InfusedWater			.setOreMultiplier( 2).setSmeltingMultiplier( 2);
//        InfusedEntropy			.setOreMultiplier( 2).setSmeltingMultiplier( 2);
//        InfusedOrder			.setOreMultiplier( 2).setSmeltingMultiplier( 2);
//        InfusedVis				.setOreMultiplier( 2).setSmeltingMultiplier( 2);
//        InfusedDull				.setOreMultiplier( 2).setSmeltingMultiplier( 2);
//        Salt					.setOreMultiplier( 2).setSmeltingMultiplier( 2);
//        RockSalt				.setOreMultiplier( 2).setSmeltingMultiplier( 2);
//        Scheelite				.setOreMultiplier( 2).setSmeltingMultiplier( 2);
//        Tungstate				.setOreMultiplier( 2).setSmeltingMultiplier( 2);
//        Cassiterite				.setOreMultiplier( 2).setSmeltingMultiplier( 2);
//        NetherQuartz			.setOreMultiplier( 2).setSmeltingMultiplier( 2);
//        CertusQuartz			.setOreMultiplier( 2).setSmeltingMultiplier( 2);
//        Phosphorus				.setOreMultiplier( 3).setSmeltingMultiplier( 3);
//        Saltpeter				.setOreMultiplier( 4).setSmeltingMultiplier( 4);
//        Apatite					.setOreMultiplier( 4).setSmeltingMultiplier( 4).setByProductMultiplier(2);
//        Redstone				.setOreMultiplier( 5).setSmeltingMultiplier( 5);
//        Glowstone				.setOreMultiplier( 5).setSmeltingMultiplier( 5);
//        Lapis					.setOreMultiplier( 6).setSmeltingMultiplier( 6).setByProductMultiplier(4);
//        Sodalite				.setOreMultiplier( 6).setSmeltingMultiplier( 6).setByProductMultiplier(4);
//        Lazurite				.setOreMultiplier( 6).setSmeltingMultiplier( 6).setByProductMultiplier(4);
//        Monazite				.setOreMultiplier( 8).setSmeltingMultiplier( 8).setByProductMultiplier(2);
//
//        Plastic					.setEnchantmentForTools(Enchantment.knockback, 1);
//        PolyvinylChloride		.setEnchantmentForTools(Enchantment.knockback, 1);
//        Polystyrene				.setEnchantmentForTools(Enchantment.knockback, 1);
//        Rubber					.setEnchantmentForTools(Enchantment.knockback, 2);
//        StyreneButadieneRubber	.setEnchantmentForTools(Enchantment.knockback, 2);
//        InfusedAir				.setEnchantmentForTools(Enchantment.knockback, 2);
//
//        Mithril					.setEnchantmentForTools(Enchantment.fortune, 3);
//        Thaumium				.setEnchantmentForTools(Enchantment.fortune, 2);
//        InfusedWater			.setEnchantmentForTools(Enchantment.fortune, 3);
//
//        Flint					.setEnchantmentForTools(Enchantment.fireAspect, 1);
//        Firestone				.setEnchantmentForTools(Enchantment.fireAspect, 3);
//        Blaze					.setEnchantmentForTools(Enchantment.fireAspect, 3);
//        InfusedFire				.setEnchantmentForTools(Enchantment.fireAspect, 3);
//
//        Amber					.setEnchantmentForTools(Enchantment.silkTouch, 1);
//        EnderPearl				.setEnchantmentForTools(Enchantment.silkTouch, 1);
//        Enderium				.setEnchantmentForTools(Enchantment.silkTouch, 1);
//        NetherStar				.setEnchantmentForTools(Enchantment.silkTouch, 1);
//        InfusedOrder			.setEnchantmentForTools(Enchantment.silkTouch, 1);
//
//        BlackBronze				.setEnchantmentForTools(Enchantment.smite, 2);
//        Gold					.setEnchantmentForTools(Enchantment.smite, 3);
//        RoseGold				.setEnchantmentForTools(Enchantment.smite, 4);
//        Platinum				.setEnchantmentForTools(Enchantment.smite, 5);
//        InfusedVis				.setEnchantmentForTools(Enchantment.smite, 5);
//
//        Lead					.setEnchantmentForTools(Enchantment.baneOfArthropods, 2);
//        Nickel					.setEnchantmentForTools(Enchantment.baneOfArthropods, 2);
//        Invar					.setEnchantmentForTools(Enchantment.baneOfArthropods, 3);
//        Antimony				.setEnchantmentForTools(Enchantment.baneOfArthropods, 3);
//        BatteryAlloy			.setEnchantmentForTools(Enchantment.baneOfArthropods, 4);
//        Bismuth					.setEnchantmentForTools(Enchantment.baneOfArthropods, 4);
//        BismuthBronze			.setEnchantmentForTools(Enchantment.baneOfArthropods, 5);
//        InfusedEarth			.setEnchantmentForTools(Enchantment.baneOfArthropods, 5);
//
//        Iron					.setEnchantmentForTools(Enchantment.sharpness, 1);
//        Bronze					.setEnchantmentForTools(Enchantment.sharpness, 1);
//        Brass					.setEnchantmentForTools(Enchantment.sharpness, 2);
//        Steel					.setEnchantmentForTools(Enchantment.sharpness, 2);
//        WroughtIron				.setEnchantmentForTools(Enchantment.sharpness, 2);
//        StainlessSteel			.setEnchantmentForTools(Enchantment.sharpness, 3);
//        BlackSteel				.setEnchantmentForTools(Enchantment.sharpness, 4);
//        RedSteel				.setEnchantmentForTools(Enchantment.sharpness, 4);
//        BlueSteel				.setEnchantmentForTools(Enchantment.sharpness, 5);
//        DamascusSteel			.setEnchantmentForTools(Enchantment.sharpness, 5);
//        InfusedEntropy			.setEnchantmentForTools(Enchantment.sharpness, 5);
//        TungstenCarbide			.setEnchantmentForTools(Enchantment.sharpness, 5);
//        HSSE					.setEnchantmentForTools(Enchantment.sharpness, 5);
//        HSSG					.setEnchantmentForTools(Enchantment.sharpness, 4);
//        HSSS					.setEnchantmentForTools(Enchantment.sharpness, 5);
//
//        InfusedAir				.setEnchantmentForArmors(Enchantment.respiration, 3);
//
//        InfusedFire				.setEnchantmentForArmors(Enchantment.featherFalling, 4);
//
//        InfusedEarth			.setEnchantmentForArmors(Enchantment.protection, 4);
//
//        InfusedEntropy			.setEnchantmentForArmors(Enchantment.thorns, 3);
//
//        InfusedWater			.setEnchantmentForArmors(Enchantment.aquaAffinity, 1);
//
//        InfusedOrder			.setEnchantmentForArmors(Enchantment.projectileProtection, 4);
//
//        InfusedDull				.setEnchantmentForArmors(Enchantment.blastProtection, 4);
//
//        InfusedVis				.setEnchantmentForArmors(Enchantment.protection, 4);
//
//        Lava					.setHeatDamage(3.0F);
//        Firestone				.setHeatDamage(5.0F);
//
//        Chalcopyrite			.addOreByProducts(Pyrite				, Cobalt				, Cadmium				, Gold			);
//        Sphalerite				.addOreByProducts(GarnetYellow			, Cadmium				, Gallium				, Zinc			);
//        MeteoricIron			.addOreByProducts(Iron					, Nickel				, Iridium				, Platinum		);
//        Glauconite				.addOreByProducts(Sodium				, Aluminium				, Iron					);
//        Bentonite				.addOreByProducts(Aluminium				, Calcium				, Magnesium				);
//        Uraninite				.addOreByProducts(Uranium				, Thorium				, Uranium235			);
//        Pitchblende				.addOreByProducts(Thorium				, Uranium				, Lead					);
//        Galena					.addOreByProducts(Sulfur				, Silver				, Lead					);
//        Lapis					.addOreByProducts(Lazurite				, Sodalite				, Pyrite				);
//        Pyrite					.addOreByProducts(Sulfur				, Phosphorus			, Iron					);
//        Copper					.addOreByProducts(Cobalt				, Gold					, Nickel				);
//        Nickel					.addOreByProducts(Cobalt				, Platinum				, Iron					);
//        GarnetRed				.addOreByProducts(Spessartine			, Pyrope				, Almandine				);
//        GarnetYellow			.addOreByProducts(Andradite				, Grossular				, Uvarovite				);
//        Sheldonite				.addOreByProducts(Palladium				, Nickel				, Iridium				);
//        Cinnabar				.addOreByProducts(Redstone				, Sulfur				, Glowstone				);
//        Tantalite				.addOreByProducts(Manganese				, Niobium				, Tantalum				);
//        Asbestos				.addOreByProducts(Asbestos				, Silicon				, Magnesium				);
//        Pentlandite				.addOreByProducts(Iron					, Sulfur				, Cobalt				);
//        Uranium					.addOreByProducts(Lead					, Uranium235			, Thorium				);
//        Scheelite				.addOreByProducts(Manganese				, Molybdenum			, Calcium				);
//        Tungstate				.addOreByProducts(Manganese				, Silver				, Lithium				);
//        Bauxite					.addOreByProducts(Grossular				, Rutile				, Gallium				);
//        Redstone				.addOreByProducts(Cinnabar				, RareEarth				, Glowstone				);
//        Monazite				.addOreByProducts(Thorium				, Neodymium				, RareEarth				);
//        Malachite				.addOreByProducts(Copper				, BrownLimonite			, Calcite				);
//        YellowLimonite			.addOreByProducts(Nickel				, BrownLimonite			, Cobalt				);
//        Lepidolite				.addOreByProducts(Lithium				, Caesium				, Boron					);
//        Andradite				.addOreByProducts(GarnetYellow			, Iron					, Boron					);
//        Quartzite				.addOreByProducts(CertusQuartz			, Barite				);
//        CertusQuartz			.addOreByProducts(Quartzite				, Barite				);
//        BrownLimonite			.addOreByProducts(Malachite				, YellowLimonite		);
//        Neodymium				.addOreByProducts(Monazite				, RareEarth				);
//        Bastnasite				.addOreByProducts(Neodymium				, RareEarth				);
//        Glowstone				.addOreByProducts(Redstone				, Gold					);
//        Zinc					.addOreByProducts(Tin					, Gallium				);
//        Tungsten				.addOreByProducts(Manganese				, Molybdenum			);
//        Iron					.addOreByProducts(Nickel				, Tin					);
//        Gold					.addOreByProducts(Copper				, Nickel				);
//        Tin						.addOreByProducts(Iron					, Zinc					);
//        Antimony				.addOreByProducts(Zinc					, Iron					);
//        Silver					.addOreByProducts(Lead					, Sulfur				);
//        Lead					.addOreByProducts(Silver				, Sulfur				);
//        Thorium					.addOreByProducts(Uranium				, Lead					);
//        Plutonium				.addOreByProducts(Uranium				, Lead					);
//        Electrum				.addOreByProducts(Gold					, Silver				);
//        Bronze					.addOreByProducts(Copper				, Tin					);
//        Brass					.addOreByProducts(Copper				, Zinc					);
//        Coal					.addOreByProducts(Lignite				, Thorium				);
//        Ilmenite				.addOreByProducts(Iron					, Rutile				);
//        Manganese				.addOreByProducts(Chrome				, Iron					);
//        Sapphire				.addOreByProducts(Aluminium				, GreenSapphire			);
//        GreenSapphire			.addOreByProducts(Aluminium				, Sapphire				);
//        Platinum				.addOreByProducts(Nickel				, Iridium				);
//        Emerald					.addOreByProducts(Beryllium				, Aluminium				);
//        Olivine					.addOreByProducts(Pyrope				, Magnesium				);
//        Chrome					.addOreByProducts(Iron					, Magnesium				);
//        Tetrahedrite			.addOreByProducts(Antimony				, Zinc					);
//        Magnetite				.addOreByProducts(Iron					, Gold					);
//        GraniticMineralSand		.addOreByProducts(GraniteBlack			, Magnetite				);
//        BasalticMineralSand		.addOreByProducts(Basalt				, Magnetite				);
//        Basalt					.addOreByProducts(Olivine				, DarkAsh				);
//        VanadiumMagnetite		.addOreByProducts(Magnetite				, Vanadium				);
//        Lazurite				.addOreByProducts(Sodalite				, Lapis					);
//        Sodalite				.addOreByProducts(Lazurite				, Lapis					);
//        Spodumene				.addOreByProducts(Aluminium				, Lithium				);
//        Ruby					.addOreByProducts(Chrome				, GarnetRed				);
//        Phosphorus				.addOreByProducts(Apatite				, Phosphate				);
//        Iridium					.addOreByProducts(Platinum				, Osmium				);
//        Pyrope					.addOreByProducts(GarnetRed				, Magnesium				);
//        Almandine				.addOreByProducts(GarnetRed				, Aluminium				);
//        Spessartine				.addOreByProducts(GarnetRed				, Manganese				);
//        Grossular				.addOreByProducts(GarnetYellow			, Calcium				);
//        Uvarovite				.addOreByProducts(GarnetYellow			, Chrome				);
//        Calcite					.addOreByProducts(Andradite				, Malachite				);
//        NaquadahEnriched		.addOreByProducts(Naquadah				, Naquadria				);
//        Naquadah				.addOreByProducts(NaquadahEnriched		);
//        Pyrolusite				.addOreByProducts(Manganese				);
//        Molybdenite				.addOreByProducts(Molybdenum			);
//        Stibnite				.addOreByProducts(Antimony				);
//        Garnierite				.addOreByProducts(Nickel				);
//        Lignite					.addOreByProducts(Coal					);
//        Diamond					.addOreByProducts(Graphite				);
//        Beryllium				.addOreByProducts(Emerald				);
//        Apatite					.addOreByProducts(Phosphorus			);
//        Magnesite				.addOreByProducts(Magnesium				);
//        NetherQuartz			.addOreByProducts(Netherrack			);
//        PigIron					.addOreByProducts(Iron					);
//        MeteoricIron			.addOreByProducts(Iron					);
//        Steel					.addOreByProducts(Iron					);
//        Mithril					.addOreByProducts(Platinum				);
//        Graphite				.addOreByProducts(Carbon				);
//        Netherrack				.addOreByProducts(Sulfur				);
//        Flint					.addOreByProducts(Obsidian				);
//        Cobaltite				.addOreByProducts(Cobalt				);
//        Cobalt					.addOreByProducts(Cobaltite				);
//        Sulfur					.addOreByProducts(Sulfur				);
//        Saltpeter				.addOreByProducts(Saltpeter				);
//        Endstone				.addOreByProducts(Helium3				);
//        Osmium					.addOreByProducts(Iridium				);
//        Magnesium				.addOreByProducts(Olivine				);
//        Aluminium				.addOreByProducts(Bauxite				);
//        Titanium				.addOreByProducts(Almandine				);
//        Obsidian				.addOreByProducts(Olivine				);
//        Ash						.addOreByProducts(Carbon				);
//        DarkAsh					.addOreByProducts(Carbon				);
//        Marble					.addOreByProducts(Calcite				);
//        Clay					.addOreByProducts(Clay					);
//        Cassiterite				.addOreByProducts(Tin					);
//        GraniteBlack			.addOreByProducts(Biotite				);
//        GraniteRed				.addOreByProducts(PotassiumFeldspar		);
//        Phosphate				.addOreByProducts(Phosphor				);
//        Phosphor				.addOreByProducts(Phosphate				);
//        Tanzanite				.addOreByProducts(Opal					);
//        Opal					.addOreByProducts(Tanzanite				);
//        Amethyst				.addOreByProducts(Amethyst				);
//        FoolsRuby				.addOreByProducts(Jasper				);
//        Amber					.addOreByProducts(Amber					);
//        Topaz					.addOreByProducts(BlueTopaz				);
//        BlueTopaz				.addOreByProducts(Topaz					);
//        Dilithium				.addOreByProducts(Dilithium				);
//        Neutronium				.addOreByProducts(Neutronium			);
//        Lithium					.addOreByProducts(Lithium				);
//        Silicon					.addOreByProducts(SiliconDioxide		);
//        Salt					.addOreByProducts(RockSalt				);
//        RockSalt				.addOreByProducts(Salt					);
//
//        Glue.mChemicalFormula = "No Horses were harmed for the Production";
//        UUAmplifier.mChemicalFormula = "Accelerates the Mass Fabricator";
//        WoodSealed.mChemicalFormula = "";
//        Wood.mChemicalFormula = "";
//        FoolsRuby.mChemicalFormula = Ruby.mChemicalFormula;
//
//        Naquadah.mMoltenRGBa[0] = 0;
//        Naquadah.mMoltenRGBa[1] = 255;
//        Naquadah.mMoltenRGBa[2] = 0;
//        Naquadah.mMoltenRGBa[3] = 0;
//        NaquadahEnriched.mMoltenRGBa[0] = 64;
//        NaquadahEnriched.mMoltenRGBa[1] = 255;
//        NaquadahEnriched.mMoltenRGBa[2] = 64;
//        NaquadahEnriched.mMoltenRGBa[3] = 0;
//        Naquadria.mMoltenRGBa[0] = 128;
//        Naquadria.mMoltenRGBa[1] = 255;
//        Naquadria.mMoltenRGBa[2] = 128;
//        Naquadria.mMoltenRGBa[3] = 0;
//
//        NaquadahEnriched.mChemicalFormula = "Nq+";
//        Naquadah.mChemicalFormula = "Nq";
//        Naquadria.mChemicalFormula = "NqX";
//    }
//
//    private static void initSubTags() {
//        SubTag.ELECTROMAGNETIC_SEPERATION_NEODYMIUM.addTo(Bastnasite, Monazite);
//        SubTag.ELECTROMAGNETIC_SEPERATION_GOLD.addTo(Magnetite, VanadiumMagnetite, BasalticMineralSand, GraniticMineralSand);
//        SubTag.ELECTROMAGNETIC_SEPERATION_IRON.addTo(YellowLimonite, BrownLimonite, Pyrite, BandedIron, Nickel, Glauconite, Pentlandite, Tin, Antimony, Ilmenite, Manganese, Chrome, Andradite);
//        SubTag.BLASTFURNACE_CALCITE_DOUBLE.addTo(Pyrite, YellowLimonite, BasalticMineralSand, GraniticMineralSand);
//        SubTag.BLASTFURNACE_CALCITE_TRIPLE.addTo(Iron, PigIron, WroughtIron, MeteoricIron, BrownLimonite);
//        SubTag.WASHING_MERCURY.addTo(Gold, Silver, Osmium, Mithril, Platinum, Sheldonite);
//        SubTag.WASHING_SODIUMPERSULFATE.addTo(Zinc, Nickel, Copper, Cobalt, Cobaltite, Tetrahedrite);
//        SubTag.METAL.addTo(AnyIron, AnyCopper, AnyBronze, Metal, Aluminium, Americium, Antimony, Beryllium, Bismuth, Caesium, Chrome, Cobalt, Copper, Dysprosium, Europium, Gallium, Gold,
//                Iridium, Iron, Lead, Lutetium, Magnesium, Manganese, Mercury, Niobium, Molybdenum, Neodymium, Neutronium, Nickel, Osmium, Palladium, Platinum, Plutonium, Plutonium241,
//                Silicon, Silver, Thorium, Tin, Titanium, Tungsten, Uranium, Uranium235, Vanadium, Yttrium,
//                Zinc, PhasedIron, PhasedGold, DarkSteel, ConductiveIron, ElectricalSteel, EnergeticAlloy, VibrantAlloy,
//                PulsatingIron, Ardite,
//                Desh, Duranium, Enderium, InfusedGold, MeteoricIron,
//                MeteoricSteel, Naquadah, NaquadahAlloy, NaquadahEnriched, Naquadria,
//                Tritanium, AluminiumBrass, Osmiridium, AnnealedCopper, BatteryAlloy, Brass, Bronze, Cupronickel,
//                Electrum, Invar, Kanthal, Magnalium, Nichrome, NiobiumTitanium, PigIron, SolderingAlloy, StainlessSteel, Steel, Ultimet, VanadiumGallium, WroughtIron,
//                YttriumBariumCuprate, Alumite, Manyullyn, SterlingSilver, RoseGold, BlackBronze, BismuthBronze, BlackSteel, RedSteel, BlueSteel, DamascusSteel,
//                TungstenSteel, Mithril, RedAlloy, CobaltBrass, Thaumium, IronMagnetic, SteelMagnetic, NeodymiumMagnetic, HSSG, HSSE, HSSS, TungstenCarbide,
//                VanadiumSteel);
//
//        SubTag.FOOD.addTo(Ice, Water, Salt, Milk, Honey, FishOil, SeedOil, SeedOilLin, SeedOilHemp, Wheat, Sugar);
//
//        Wood.add(SubTag.WOOD, SubTag.FLAMMABLE, SubTag.NO_SMELTING, SubTag.NO_SMASHING);
//        WoodSealed.add(SubTag.WOOD, SubTag.FLAMMABLE, SubTag.NO_SMELTING, SubTag.NO_SMASHING, SubTag.NO_WORKING);
//
//        Snow.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.NO_RECYCLING);
//        Ice.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.NO_RECYCLING);
//        Water.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.NO_RECYCLING);
//        Sulfur.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.FLAMMABLE);
//        Saltpeter.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.FLAMMABLE);
//        Graphite.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.FLAMMABLE, SubTag.NO_SMELTING);
//
//        Wheat.add(SubTag.FLAMMABLE, SubTag.MORTAR_GRINDABLE);
//        Paper.add(SubTag.FLAMMABLE, SubTag.NO_SMELTING, SubTag.NO_SMASHING, SubTag.MORTAR_GRINDABLE, SubTag.PAPER);
//        Coal.add(SubTag.FLAMMABLE, SubTag.NO_SMELTING, SubTag.NO_SMASHING, SubTag.MORTAR_GRINDABLE);
//        Charcoal.add(SubTag.FLAMMABLE, SubTag.NO_SMELTING, SubTag.NO_SMASHING, SubTag.MORTAR_GRINDABLE);
//        Lignite.add(SubTag.FLAMMABLE, SubTag.NO_SMELTING, SubTag.NO_SMASHING, SubTag.MORTAR_GRINDABLE);
//
//        Rubber.add(SubTag.FLAMMABLE, SubTag.NO_SMASHING, SubTag.BOUNCY, SubTag.STRETCHY);
//        StyreneButadieneRubber.add(SubTag.FLAMMABLE, SubTag.NO_SMASHING, SubTag.BOUNCY, SubTag.STRETCHY);
//        Plastic.add(SubTag.FLAMMABLE, SubTag.NO_SMASHING, SubTag.BOUNCY);
//        PolyvinylChloride.add(SubTag.FLAMMABLE, SubTag.NO_SMASHING, SubTag.BOUNCY);
//        Polystyrene.add(SubTag.FLAMMABLE, SubTag.NO_SMASHING, SubTag.BOUNCY);
//        Silicone.add(SubTag.FLAMMABLE, SubTag.NO_SMASHING, SubTag.BOUNCY, SubTag.STRETCHY);
//
//        Gunpowder.add(SubTag.FLAMMABLE, SubTag.EXPLOSIVE, SubTag.NO_SMELTING, SubTag.NO_SMASHING);
//        Glyceryl.add(SubTag.FLAMMABLE, SubTag.EXPLOSIVE, SubTag.NO_SMELTING, SubTag.NO_SMASHING);
//        NitroFuel.add(SubTag.FLAMMABLE, SubTag.EXPLOSIVE, SubTag.NO_SMELTING, SubTag.NO_SMASHING);
//
//        Lead.add(SubTag.MORTAR_GRINDABLE, SubTag.SOLDERING_MATERIAL, SubTag.SOLDERING_MATERIAL_BAD);
//        Tin.add(SubTag.MORTAR_GRINDABLE, SubTag.SOLDERING_MATERIAL);
//        SolderingAlloy.add(SubTag.MORTAR_GRINDABLE, SubTag.SOLDERING_MATERIAL, SubTag.SOLDERING_MATERIAL_GOOD);
//
//        Sugar.add(SubTag.SMELTING_TO_FLUID);
//
//        Concrete.add(SubTag.STONE, SubTag.NO_SMASHING, SubTag.SMELTING_TO_FLUID);
//        ConstructionFoam.add(SubTag.STONE, SubTag.NO_SMASHING, SubTag.EXPLOSIVE, SubTag.NO_SMELTING);
//        Redstone.add(SubTag.STONE, SubTag.NO_SMASHING, SubTag.UNBURNABLE, SubTag.SMELTING_TO_FLUID, SubTag.PULVERIZING_CINNABAR);
//        Glowstone.add(SubTag.STONE, SubTag.NO_SMASHING, SubTag.UNBURNABLE, SubTag.SMELTING_TO_FLUID);
//        Netherrack.add(SubTag.STONE, SubTag.NO_SMASHING, SubTag.UNBURNABLE, SubTag.FLAMMABLE);
//        Stone.add(SubTag.STONE, SubTag.NO_SMASHING, SubTag.NO_RECYCLING);
//        Brick.add(SubTag.STONE, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        NetherBrick.add(SubTag.STONE, SubTag.NO_SMASHING);
//        Endstone.add(SubTag.STONE, SubTag.NO_SMASHING);
//        Marble.add(SubTag.STONE, SubTag.NO_SMASHING);
//        Basalt.add(SubTag.STONE, SubTag.NO_SMASHING);
//        Obsidian.add(SubTag.STONE, SubTag.NO_SMASHING);
//        Flint.add(SubTag.STONE, SubTag.NO_SMASHING, SubTag.MORTAR_GRINDABLE);
//        GraniteRed.add(SubTag.STONE, SubTag.NO_SMASHING);
//        GraniteBlack.add(SubTag.STONE, SubTag.NO_SMASHING);
//        Salt.add(SubTag.STONE, SubTag.NO_SMASHING);
//        RockSalt.add(SubTag.STONE, SubTag.NO_SMASHING);
//
//        Sand.add(SubTag.NO_RECYCLING);
//
//        Gold.add(SubTag.MORTAR_GRINDABLE);
//        Silver.add(SubTag.MORTAR_GRINDABLE);
//        Iron.add(SubTag.MORTAR_GRINDABLE);
//        IronMagnetic.add(SubTag.MORTAR_GRINDABLE);
//        Steel.add(SubTag.MORTAR_GRINDABLE);
//        SteelMagnetic.add(SubTag.MORTAR_GRINDABLE);
//        Zinc.add(SubTag.MORTAR_GRINDABLE);
//        Antimony.add(SubTag.MORTAR_GRINDABLE);
//        Copper.add(SubTag.MORTAR_GRINDABLE);
//        AnnealedCopper.add(SubTag.MORTAR_GRINDABLE);
//        Bronze.add(SubTag.MORTAR_GRINDABLE);
//        Nickel.add(SubTag.MORTAR_GRINDABLE);
//        Invar.add(SubTag.MORTAR_GRINDABLE);
//        Brass.add(SubTag.MORTAR_GRINDABLE);
//        WroughtIron.add(SubTag.MORTAR_GRINDABLE);
//        Electrum.add(SubTag.MORTAR_GRINDABLE);
//        Clay.add(SubTag.MORTAR_GRINDABLE);
//
//        Glass.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_RECYCLING, SubTag.SMELTING_TO_FLUID);
//        Diamond.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.FLAMMABLE);
//        Emerald.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Amethyst.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Tanzanite.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Topaz.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Amber.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        GreenSapphire.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Sapphire.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Ruby.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        FoolsRuby.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Opal.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Olivine.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Jasper.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        GarnetRed.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        GarnetYellow.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Crystal.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Apatite.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE);
//        Lapis.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE);
//        Sodalite.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE);
//        Lazurite.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE);
//        Monazite.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE);
//        Quartzite.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE, SubTag.QUARTZ);
//        Quartz.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE, SubTag.QUARTZ);
//        SiliconDioxide.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE, SubTag.QUARTZ);
//        Dilithium.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE, SubTag.QUARTZ);
//        NetherQuartz.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE, SubTag.QUARTZ);
//        CertusQuartz.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE, SubTag.QUARTZ);
//        Fluix.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE, SubTag.QUARTZ);
//        Phosphorus.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.FLAMMABLE, SubTag.EXPLOSIVE);
//        Phosphate.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.FLAMMABLE, SubTag.EXPLOSIVE);
//        InfusedAir.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
//        InfusedFire.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
//        InfusedEarth.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
//        InfusedWater.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
//        InfusedEntropy.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
//        InfusedOrder.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
//        InfusedVis.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
//        InfusedDull.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
//        NetherStar.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.UNBURNABLE);
//        EnderPearl.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.PEARL);
//        EnderEye.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.MAGICAL, SubTag.PEARL);
//        Firestone.add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE, SubTag.MAGICAL, SubTag.QUARTZ, SubTag.UNBURNABLE, SubTag.BURNING);
//        Magic.add(SubTag.CRYSTAL, SubTag.MAGICAL, SubTag.UNBURNABLE);
//
//        Primitive.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Basic.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Good.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Advanced.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Data.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Elite.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Master.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Ultimate.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Superconductor.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//        Infinite.add(SubTag.NO_SMASHING, SubTag.NO_SMELTING);
//
//        Blaze.add(SubTag.MAGICAL, SubTag.NO_SMELTING, SubTag.SMELTING_TO_FLUID, SubTag.MORTAR_GRINDABLE, SubTag.UNBURNABLE, SubTag.BURNING);
//        Thaumium.add(SubTag.MAGICAL);
//        Enderium.add(SubTag.MAGICAL);
//        Mithril.add(SubTag.MAGICAL);
//    }
//
//    public static void init() {
//        new ProcessingConfig();
//        if (!GT_Mod.gregtechproxy.mEnableAllMaterialsOld) new ProcessingModSupport();
//        for (IMaterialHandler aRegistrator : mMaterialHandlers) {
//            aRegistrator.onMaterialsOldInit(); //This is where addon mods can add/manipulate MaterialsOld
//        }
//        initMaterialProperties(); //No more material addition or manipulation should be done past this point!
//        MaterialsOld_ALL = MaterialsOld_MAP.values().toArray(new MaterialsOld[MaterialsOld_MAP.size()]); //Generate standard object array. This is a lot faster to loop over.
//        VALUES = Arrays.asList(MaterialsOld_ALL);
//        if (!GT_Mod.gregtechproxy.mEnableAllComponents) OrePrefixes.initMaterialComponents();
//        for (MaterialsOld aMaterial : MaterialsOld_ALL) {
//            if (aMaterial.mMetaItemSubID >= 0) {
//                if (aMaterial.mMetaItemSubID < 1000) {
//                    if (aMaterial.mHasParentMod) {
//                        if (GregTech_API.sGeneratedMaterialsOld[aMaterial.mMetaItemSubID] == null) {
//                            GregTech_API.sGeneratedMaterialsOld[aMaterial.mMetaItemSubID] = aMaterial;
//                        } else throw new IllegalArgumentException("The Material Index " + aMaterial.mMetaItemSubID + " for " + aMaterial.mName + " is already used!");
//                    }
//                } else throw new IllegalArgumentException("The Material Index " + aMaterial.mMetaItemSubID + " for " + aMaterial.mName + " is/over the maximum of 1000");
//            }
//        }
//        // Fills empty spaces with MaterialsOld, causes horrible load times.
//        /*for (int i = 0; i < GregTech_API.sGeneratedMaterialsOld.length; i++) {
//            if (GregTech_API.sGeneratedMaterialsOld[i] == null) {
//                GregTech_API.sGeneratedMaterialsOld[i] = new MaterialsOld(i, TextureSet.NONE, 1.0F, 0, 2, 1|2|4|8|16|32|64|128, 92, 0, 168, 0, "TestMat" + i, 0, 0, -1, 0, false, false, 3, 1, 1, Dyes.dyeNULL, "testmat");
//            }
//        }*/
//    }
//
//    public static void initMaterialProperties() {
//        GT_Mod.gregtechproxy.mChangeHarvestLevels = GregTech_API.sMaterialProperties.get("harvestlevel", "ActivateHarvestLevelChange", false);
//        GT_Mod.gregtechproxy.mMaxHarvestLevel = Math.min(15, GregTech_API.sMaterialProperties.get("harvestlevel", "MaxHarvestLevel",7));
//        GT_Mod.gregtechproxy.mGraniteHavestLevel = GregTech_API.sMaterialProperties.get("harvestlevel", "GraniteHarvestLevel", 3);
//        StringBuilder aConfigPathSB = new StringBuilder();
//        for (MaterialsOld aMaterial : MaterialsOld_MAP.values()) { /** The only place where MaterialsOld_MAP should be used to loop over all MaterialsOld. **/
//            if (aMaterial != null && aMaterial != MaterialsOld.dyeNULL && aMaterial != MaterialsOld.Empty) {
//                aConfigPathSB.append("MaterialsOld.").append(aMaterial.mConfigSection).append(".").append(aMaterial.mCustomOre ? aMaterial.mCustomID : aMaterial.mName);
//                String aConfigPath = aConfigPathSB.toString();
//                aMaterial.mMetaItemSubID = GregTech_API.sMaterialProperties.get(aConfigPath, "MaterialID", aMaterial.mCustomOre ? -1 : aMaterial.mMetaItemSubID);
//                aMaterial.mDefaultLocalName = GregTech_API.sMaterialProperties.get(aConfigPath, "MaterialName", aMaterial.mCustomOre ? "CustomOre" + aMaterial.mCustomID : aMaterial.mDefaultLocalName);
//                aMaterial.mMeltingPoint = (short) GregTech_API.sMaterialProperties.get(aConfigPath, "MeltingPoint", aMaterial.mMeltingPoint);
//                aMaterial.mBlastFurnaceRequired = GregTech_API.sMaterialProperties.get(aConfigPath, "BlastFurnaceRequired", aMaterial.mBlastFurnaceRequired);
//                aMaterial.mBlastFurnaceTemp = (short) GregTech_API.sMaterialProperties.get(aConfigPath, "BlastFurnaceTemp", aMaterial.mBlastFurnaceTemp);
//                if (GT_Mod.gregtechproxy.mTEMachineRecipes && aMaterial.mBlastFurnaceRequired && aMaterial.mBlastFurnaceTemp < 1500) GT_ModHandler.ThermalExpansion.addSmelterBlastOre(aMaterial);
//                aMaterial.mFuelPower = GregTech_API.sMaterialProperties.get(aConfigPath, "FuelPower", aMaterial.mFuelPower);
//                aMaterial.mFuelType = GregTech_API.sMaterialProperties.get(aConfigPath, "FuelType", aMaterial.mFuelType);
//                aMaterial.mOreValue = GregTech_API.sMaterialProperties.get(aConfigPath, "OreValue", aMaterial.mOreValue);
//                aMaterial.mDensityMultiplier = GregTech_API.sMaterialProperties.get(aConfigPath, "DensityMultiplier", aMaterial.mDensityMultiplier);
//                aMaterial.mDensityDivider = GregTech_API.sMaterialProperties.get(aConfigPath, "DensityDivider", aMaterial.mDensityDivider);
//                aMaterial.mDensity = (long) GregTech_API.sMaterialProperties.get(aConfigPath, "Density", (M * aMaterial.mDensityMultiplier) / aMaterial.mDensityDivider);
//                aMaterial.mDurability = GregTech_API.sMaterialProperties.get(aConfigPath, "ToolDurability", aMaterial.mDurability);
//                aMaterial.mToolSpeed = (float) GregTech_API.sMaterialProperties.get(aConfigPath, "ToolSpeed", aMaterial.mToolSpeed);
//                aMaterial.mToolQuality = (byte) GregTech_API.sMaterialProperties.get(aConfigPath, "ToolQuality", aMaterial.mToolQuality);
//                //aMaterial.mIconSet = TextureSet.valueOf(GregTech_API.sMaterialProperties.get(aConfigPath.toString(), "IconSet", aMaterial.mIconSet.mSetName));
//                aMaterial.mTransparent = GregTech_API.sMaterialProperties.get(aConfigPath, "Transparent", aMaterial.mTransparent);
//                String aColor = GregTech_API.sMaterialProperties.get(aConfigPath, "DyeColor", aMaterial.mColor == Dyes.dyeNULL ? "None" : aMaterial.mColor.toString());
//                aMaterial.mColor = aColor.equals("None") ? Dyes.dyeNULL : Dyes.get(aColor);
//                String[] aRGBA = GregTech_API.sMaterialProperties.get(aConfigPath, "MatRGBA", String.valueOf(aMaterial.mRGBa[0] + "," + aMaterial.mRGBa[1] + "," + aMaterial.mRGBa[2] + "," + aMaterial.mRGBa[3] + ",")).split(",");
//                aMaterial.mRGBa[0] = Short.parseShort(aRGBA[0]);
//                aMaterial.mRGBa[1] = Short.parseShort(aRGBA[1]);
//                aMaterial.mRGBa[2] = Short.parseShort(aRGBA[2]);
//                aMaterial.mRGBa[3] = Short.parseShort(aRGBA[3]);
//                aMaterial.mTypes = GregTech_API.sMaterialProperties.get(aConfigPath, "MaterialTypes", aMaterial.mCustomOre ? 1|2|4|8|16|32|64|128 : aMaterial.mTypes);
//                aMaterial.mUnificatable = GregTech_API.sMaterialProperties.get(aConfigPath, "Unificatable", aMaterial.mUnificatable);
//                aMaterial.mChemicalFormula = GregTech_API.sMaterialProperties.get(aConfigPath, "ChemicalFormula", aMaterial.mChemicalFormula);
//                aMaterial.mGasTemp = (short) GregTech_API.sMaterialProperties.get(aConfigPath, "GasTemp", aMaterial.mGasTemp);
//                aMaterial.setOreMultiplier(GregTech_API.sMaterialProperties.get(aConfigPath, "OreMultiplier", aMaterial.mOreMultiplier));
//                aMaterial.setSmeltingMultiplier(GregTech_API.sMaterialProperties.get(aConfigPath, "OreSmeltingMultiplier", aMaterial.mSmeltingMultiplier));
//                aMaterial.setByProductMultiplier(GregTech_API.sMaterialProperties.get(aConfigPath, "OreByProductMultiplier", aMaterial.mByProductMultiplier));
//                aMaterial.setHeatDamage((float) GregTech_API.sMaterialProperties.get(aConfigPath, "HeatDamage", aMaterial.mHeatDamage));
//                aMaterial.mSmeltInto = MaterialsOld_MAP.get(GregTech_API.sMaterialProperties.get(aConfigPath, "MaterialsOldmeltInto", aMaterial.mSmeltInto.mName));
//                aMaterial.mMacerateInto = MaterialsOld_MAP.get(GregTech_API.sMaterialProperties.get(aConfigPath, "MaterialMacerateInto", aMaterial.mMacerateInto.mName));
//                aMaterial.mArcSmeltInto = MaterialsOld_MAP.get(GregTech_API.sMaterialProperties.get(aConfigPath, "MaterialArcSmeltInto", aMaterial.mArcSmeltInto.mName));
//                aMaterial.mDirectSmelting = MaterialsOld_MAP.get(GregTech_API.sMaterialProperties.get(aConfigPath, "MaterialDirectSmeltInto", aMaterial.mDirectSmelting.mName));
//                aMaterial.mHasParentMod = GregTech_API.sMaterialProperties.get(aConfigPath, "HasParentMod", aMaterial.mHasParentMod);
//                if (aMaterial.mHasPlasma = GregTech_API.sMaterialProperties.get(aConfigPath, "AddPlasma", aMaterial.mHasPlasma)) GT_Mod.gregtechproxy.addAutogeneratedPlasmaFluid(aMaterial);
//                if (aMaterial.mHasGas = GregTech_API.sMaterialProperties.get(aConfigPath, "AddGas", aMaterial.mHasGas)) GT_Mod.gregtechproxy.addFluid(aMaterial.mName.toLowerCase(), aMaterial.mDefaultLocalName, aMaterial, 2, aMaterial.mGasTemp);
//                aMaterial.mEnchantmentToolsLevel = (byte) GregTech_API.sMaterialProperties.get(aConfigPath, "EnchantmentLevel", aMaterial.mEnchantmentToolsLevel);
//                String aEnchantmentName = GregTech_API.sMaterialProperties.get(aConfigPath, "Enchantment", aMaterial.mEnchantmentTools != null ? aMaterial.mEnchantmentTools.getName() : "");
//                if (aMaterial.mEnchantmentTools != null && !aEnchantmentName.equals(aMaterial.mEnchantmentTools.getName())) {
//                    for (int i = 0; i < Enchantment.enchantmentsList.length; i++) {
//                        if (aEnchantmentName.equals(Enchantment.enchantmentsList[i].getName())) aMaterial.mEnchantmentTools = Enchantment.enchantmentsList[i];
//                    }
//                }
//                /**
//                 * Converts the pre-defined list of SubTags from a material into a list of SubTag names for setting/getting to/from the config.
//                 * It is then converted to a String[] and finally to a singular String for insertion into the config
//                 * If the config string is different from the default, we then want to clear the MaterialsOld SubTags and insert new ones from the config string.
//                 */
//                List<String> aSubTags = new ArrayList<>();
//                for (SubTag aTag : aMaterial.mSubTags) aSubTags.add(aTag.mName);
//                String aDefaultTagString = "," + aSubTags.toString().replace(" ", "").replace("[", "").replace("]", "");
//                String aConfigTagString = GregTech_API.sMaterialProperties.get(aConfigPath, "ListSubTags", aDefaultTagString);
//                if (!aConfigTagString.equals(aDefaultTagString)) {
//                    aMaterial.mSubTags.clear();
//                    if (aConfigTagString.length() > 0) {
//                        aSubTags = new ArrayList<>(Arrays.asList(aConfigTagString.split(",")));
//                        for (String aTagString : aSubTags) {
//                            SubTag aTag = SubTag.sSubTags.get(aTagString);
//                            if (aTag != null) aMaterial.mSubTags.add(aTag);
//                        }
//                    }
//                }
//                /** Same principal as SubTags **/
//                List<String> aOreByProducts = new ArrayList<>();
//                for (MaterialsOld aMat : aMaterial.mByProducts) aOreByProducts.add(aMat.mName);
//                String aDefaultMatByProString = "," + aOreByProducts.toString().replace(" ", "").replace("[", "").replace("]", "");
//                String aConfigMatByProString = GregTech_API.sMaterialProperties.get(aConfigPath, "ListMaterialByProducts", aDefaultMatByProString);
//                if (!aConfigMatByProString.equals(aDefaultMatByProString)) {
//                    aMaterial.mByProducts.clear();
//                    if (aConfigMatByProString.length() > 0) {
//                        aOreByProducts = new ArrayList<>(Arrays.asList(aConfigMatByProString.split(",")));
//                        for (String aMaterialsOldtring : aOreByProducts) {
//                            MaterialsOld aMat = MaterialsOld_MAP.get(aMaterialsOldtring);
//                            if (aMat != null) aMaterial.mByProducts.add(aMat);
//                        }
//                    }
//                }
//                /** Same principal as SubTags **/
//                List<String> aOreReRegistrations = new ArrayList<>();
//                for (MaterialsOld aMat : aMaterial.mOreReRegistrations) aOreReRegistrations.add(aMat.mName);
//                String aDefaultMatReRegString = "," + aOreReRegistrations.toString().replace(" ", "").replace("[", "").replace("]", "");
//                String aConfigMatMatReRegString = GregTech_API.sMaterialProperties.get(aConfigPath, "ListMaterialReRegistrations", aDefaultMatReRegString);
//                if (!aConfigMatMatReRegString.equals(aDefaultMatReRegString)) {
//                    aMaterial.mOreReRegistrations.clear();
//                    if (aConfigMatMatReRegString.length() > 0) {
//                        aOreReRegistrations = new ArrayList<>(Arrays.asList(aConfigMatMatReRegString.split(",")));
//                        for (String aMaterialsOldtring : aOreReRegistrations) {
//                            MaterialsOld aMat = MaterialsOld_MAP.get(aMaterialsOldtring);
//                            if (aMat != null) aMaterial.mOreReRegistrations.add(aMat);
//                        }
//                    }
//                }
//                /** Same principal as SubTags but with two values **/
//                List<String> aAspects = new ArrayList<>();
//                ArrayList<String> aAspectAmounts = new ArrayList<>();
//                for (TC_Aspects.TC_AspectStack aAspectStack : aMaterial.mAspects) {
//                    aAspects.add(aAspectStack.mAspect.toString());
//                    aAspectAmounts.add(String.valueOf(aAspectStack.mAmount));
//                }
//                String aDefaultAspectString = "," + aAspects.toString().replace(" ", "").replace("[", "").replace("]", "");
//                String aDefaultAspectAmountString = "," + aAspectAmounts.toString().replace(" ", "").replace("[", "").replace("]", "");
//                String aConfigAspectString = GregTech_API.sMaterialProperties.get(aConfigPath, "ListTCAspects", aDefaultAspectString);
//                String aConfigAspectAmountString = GregTech_API.sMaterialProperties.get(aConfigPath, "ListTCAspectAmounts", aDefaultAspectAmountString);
//                if (!aConfigAspectString.equals(aDefaultAspectString) || !aConfigAspectAmountString.equals(aDefaultAspectAmountString)) {
//                    aMaterial.mAspects.clear();
//                    if (aConfigAspectString.length() > 0) {
//                        aAspects = new ArrayList<>(Arrays.asList(aConfigAspectString.split(",")));
//                        for (int i = 0; i < aAspects.size(); i++) {
//                            String aAspectString = aAspects.get(i);
//                            long aAspectAmount = Long.parseLong(aAspectAmounts.get(i));
//                            TC_Aspects.TC_AspectStack aAspectStack = new TC_Aspects.TC_AspectStack(TC_Aspects.valueOf(aAspectString), aAspectAmount);
//                            if (aAspectStack != null) aMaterial.mAspects.add(aAspectStack);
//                        }
//                    }
//                }
//                /** Moved the harvest level changes from GT_Mod to have less things iterating over MaterialsOld_ALL **/
//                if (GT_Mod.gregtechproxy.mChangeHarvestLevels && aMaterial.mToolQuality > 0 && aMaterial.mMetaItemSubID < GT_Mod.gregtechproxy.mHarvestLevel.length && aMaterial.mMetaItemSubID >= 0) {
//                    GT_Mod.gregtechproxy.mHarvestLevel[aMaterial.mMetaItemSubID] = GregTech_API.sMaterialProperties.get(aConfigPath, "HarvestLevel", aMaterial.mToolQuality);
//                }
//                /** Moved from GT_Proxy? (Not sure)**/
//                aMaterial.mHandleMaterial = (aMaterial == Desh ? aMaterial.mHandleMaterial : aMaterial == Diamond || aMaterial == Thaumium ? Wood : aMaterial.contains(SubTag.BURNING) ? Blaze : aMaterial.contains(SubTag.MAGICAL) && aMaterial.contains(SubTag.CRYSTAL) && Loader.isModLoaded(GT_Values.MOD_ID_TC) ? Thaumium : aMaterial.getMass() > Element.Tc.getMass() * 2 ? TungstenSteel : aMaterial.getMass() > Element.Tc.getMass() ? Steel : Wood);
//            }
//            aConfigPathSB.setLength(0);
//        }
//    }
//
//    public MaterialsOld(int aMetaItemSubID, TextureSet aIconSet, float aToolSpeed, int aDurability, int aToolQuality, boolean aUnificatable, String aName, String aDefaultLocalName) {
//        this(aMetaItemSubID, aIconSet, aToolSpeed, aDurability, aToolQuality, aUnificatable, aName, aDefaultLocalName, "ore", false, "null");
//    }
//
//    //TODO TEMP?
//    public MaterialsOld(int metaItemSubId, String name, short r, short g, short b, short a, TextureSet materialIconSet, List<MaterialStack> materialComponents, int materialGenerationFlags, Element element) {
//        mRGBa[0] = r;
//        mRGBa[1] = g;
//        mRGBa[2] = b;
//        mRGBa[3] = a;
//        mIconSet = materialIconSet;
//        mProcessInto = materialComponents;
//        mTypes = materialGenerationFlags;
//        mElement = element;
//        //mChemicalFormula = calculateChemicalFormula();
//    }
//    public MaterialsOld(int aMetaItemSubID, TextureSet aIconSet, float aToolSpeed, int aDurability, int aToolQuality, boolean aUnificatable, String aName, String aDefaultLocalName, String aConfigSection, boolean aCustomOre, String aCustomID) {
//        mMetaItemSubID = aMetaItemSubID;
//        mDefaultLocalName = aDefaultLocalName;
//        mName = aName;
//        MaterialsOld_MAP.put(mName, this);
//        mCustomOre = aCustomOre;
//        mCustomID = aCustomID;
//        mConfigSection = aConfigSection;
//        mUnificatable = aUnificatable;
//        mDurability = aDurability;
//        mToolSpeed = aToolSpeed;
//        mToolQuality = (byte) aToolQuality;
//        mMaterialInto = this;
//        mIconSet = aIconSet;
//    }
//
//    public MaterialsOld(MaterialsOld aMaterialInto, boolean aReRegisterIntoThis) {
//        mUnificatable = false;
//        mDefaultLocalName = aMaterialInto.mDefaultLocalName;
//        mName = aMaterialInto.mName;
//        mMaterialInto = aMaterialInto.mMaterialInto;
//        if (aReRegisterIntoThis) mMaterialInto.mOreReRegistrations.add(this);
//        mChemicalFormula = aMaterialInto.mChemicalFormula;
//        mMetaItemSubID = -1;
//        mIconSet = TextureSet.NONE;
//    }
//
//    public MaterialsOld(int aMetaItemSubID, TextureSet aIconSet, float aToolSpeed, int aDurability, int aToolQuality, int aTypes, int aR, int aG, int aB, int aA, String aName, String aDefaultLocalName, int aFuelType, int aFuelPower, int aMeltingPoint, int aBlastFurnaceTemp, boolean aBlastFurnaceRequired, boolean aTransparent, int aOreValue, int aDensityMultiplier, int aDensityDivider, Dyes aColor) {
//        this(aMetaItemSubID, aIconSet, aToolSpeed, aDurability, aToolQuality, aTypes, aR, aG, aB, aA, aName, aDefaultLocalName, aFuelType, aFuelPower, aMeltingPoint, aBlastFurnaceTemp, aBlastFurnaceRequired, aTransparent, aOreValue, aDensityMultiplier, aDensityDivider, aColor, "ore", false, "null");
//    }
//
//    public MaterialsOld(int aMetaItemSubID, TextureSet aIconSet, float aToolSpeed, int aDurability, int aToolQuality, int aTypes, int aR, int aG, int aB, int aA, String aName, String aDefaultLocalName, int aFuelType, int aFuelPower, int aMeltingPoint, int aBlastFurnaceTemp, boolean aBlastFurnaceRequired, boolean aTransparent, int aOreValue, int aDensityMultiplier, int aDensityDivider, Dyes aColor, String aConfigSection) {
//        this(aMetaItemSubID, aIconSet, aToolSpeed, aDurability, aToolQuality, aTypes, aR, aG, aB, aA, aName, aDefaultLocalName, aFuelType, aFuelPower, aMeltingPoint, aBlastFurnaceTemp, aBlastFurnaceRequired, aTransparent, aOreValue, aDensityMultiplier, aDensityDivider, aColor, aConfigSection, false, "null");
//    }
//
//    /**
//     * @param aMetaItemSubID        the Sub-ID used in my own MetaItems. Range 0-1000. -1 for no Material
//     * @param aTypes                which kind of Items should be generated. Bitmask as follows:
//     *                              1 = Dusts of all kinds.
//     *                              2 = Dusts, Ingots, Plates, Rods/Sticks, Machine Components and other Metal specific things.
//     *                              4 = Dusts, Gems, Plates, Lenses (if transparent).
//     *                              8 = Dusts, Impure Dusts, crushed Ores, purified Ores, centrifuged Ores etc.
//     *                              16 = Cells
//     *                              32 = Plasma Cells
//     *                              64 = Tool Heads
//     *                              128 = Gears
//     *                              256 = Designates something as empty (only used for the Empty material)
//     * @param aR,                   aG, aB Color of the Material 0-255 each.
//     * @param aA                    transparency of the Material Texture. 0 = fully visible, 255 = Invisible.
//     * @param aName                 The Name used as Default for localization.
//     * @param aFuelType             Type of Generator to get Energy from this Material.
//     * @param aFuelPower            EU generated. Will be multiplied by 1000, also additionally multiplied by 2 for Gems.
//     * @param aMeltingPoint         Used to determine the smelting Costs in Furnii.
//     * @param aBlastFurnaceTemp     Used to determine the needed Heat capactiy Costs in Blast Furnii.
//     * @param aBlastFurnaceRequired If this requires a Blast Furnace.
//     * @param aColor                Vanilla MC Wool Color which comes the closest to this.
//     */
//    public MaterialsOld(int aMetaItemSubID, TextureSet aIconSet, float aToolSpeed, int aDurability, int aToolQuality, int aTypes, int aR, int aG, int aB, int aA, String aName, String aDefaultLocalName, int aFuelType, int aFuelPower, int aMeltingPoint, int aBlastFurnaceTemp, boolean aBlastFurnaceRequired, boolean aTransparent, int aOreValue, int aDensityMultiplier, int aDensityDivider, Dyes aColor, String aConfigSection, boolean aCustomOre, String aCustomID) {
//        this(aMetaItemSubID, aIconSet, aToolSpeed, aDurability, aToolQuality, true, aName, aDefaultLocalName, aConfigSection, aCustomOre, aCustomID);
//        mMeltingPoint = (short) aMeltingPoint;
//        mBlastFurnaceRequired = aBlastFurnaceRequired;
//        mBlastFurnaceTemp = (short) aBlastFurnaceTemp;
//        mTransparent = aTransparent;
//        mFuelPower = aFuelPower;
//        mFuelType = aFuelType;
//        mOreValue = aOreValue;
//        mDensityMultiplier = aDensityMultiplier;
//        mDensityDivider = aDensityDivider;
//        mDensity = (M * aDensityMultiplier) / aDensityDivider;
//        mColor = aColor;
//        mRGBa[0] = mMoltenRGBa[0] = (short) aR;
//        mRGBa[1] = mMoltenRGBa[1] = (short) aG;
//        mRGBa[2] = mMoltenRGBa[2] = (short) aB;
//        mRGBa[3] = mMoltenRGBa[3] = (short) aA;
//        mTypes = aTypes;
//        if (mColor != null) add(SubTag.HAS_COLOR);
//        if (mTransparent) add(SubTag.TRANSPARENT);
//        if ((mTypes & 2) != 0) add(SubTag.SMELTING_TO_FLUID);
//    }
//
//    public MaterialsOld(int aMetaItemSubID, TextureSet aIconSet, float aToolSpeed, int aDurability, int aToolQuality, int aTypes, int aR, int aG, int aB, int aA, String aName, String aDefaultLocalName, int aFuelType, int aFuelPower, int aMeltingPoint, int aBlastFurnaceTemp, boolean aBlastFurnaceRequired, boolean aTransparent, int aOreValue, int aDensityMultiplier, int aDensityDivider, Dyes aColor, List<TC_Aspects.TC_AspectStack> aAspects) {
//        this(aMetaItemSubID, aIconSet, aToolSpeed, aDurability, aToolQuality, aTypes, aR, aG, aB, aA, aName, aDefaultLocalName, aFuelType, aFuelPower, aMeltingPoint, aBlastFurnaceTemp, aBlastFurnaceRequired, aTransparent, aOreValue, aDensityMultiplier, aDensityDivider, aColor);
//        mAspects.addAll(aAspects);
//    }
//
//    public MaterialsOld(int aMetaItemSubID, TextureSet aIconSet, float aToolSpeed, int aDurability, int aToolQuality, int aTypes, int aR, int aG, int aB, int aA, String aName, String aDefaultLocalName, int aFuelType, int aFuelPower, int aMeltingPoint, int aBlastFurnaceTemp, boolean aBlastFurnaceRequired, boolean aTransparent, int aOreValue, int aDensityMultiplier, int aDensityDivider, Dyes aColor, Element aElement, List<TC_Aspects.TC_AspectStack> aAspects) {
//        this(aMetaItemSubID, aIconSet, aToolSpeed, aDurability, aToolQuality, aTypes, aR, aG, aB, aA, aName, aDefaultLocalName, aFuelType, aFuelPower, aMeltingPoint, aBlastFurnaceTemp, aBlastFurnaceRequired, aTransparent, aOreValue, aDensityMultiplier, aDensityDivider, aColor);
//        mElement = aElement;
//        mElement.mLinkedMaterialsOld.add(this);
//        if (aElement == Element.dyeNULL) {
//            mChemicalFormula = "Empty";
//        } else {
//            mChemicalFormula = aElement.toString();
//            mChemicalFormula = mChemicalFormula.replaceAll("_", "-");
//        }
//        mAspects.addAll(aAspects);
//    }
//
//    public MaterialsOld(int aMetaItemSubID, TextureSet aIconSet, float aToolSpeed, int aDurability, int aToolQuality, int aTypes, int aR, int aG, int aB, int aA, String aName, String aDefaultLocalName, int aFuelType, int aFuelPower, int aMeltingPoint, int aBlastFurnaceTemp, boolean aBlastFurnaceRequired, boolean aTransparent, int aOreValue, int aDensityMultiplier, int aDensityDivider, Dyes aColor, int aExtraData, List<MaterialStack> aMaterialList) {
//        this(aMetaItemSubID, aIconSet, aToolSpeed, aDurability, aToolQuality, aTypes, aR, aG, aB, aA, aName, aDefaultLocalName, aFuelType, aFuelPower, aMeltingPoint, aBlastFurnaceTemp, aBlastFurnaceRequired, aTransparent, aOreValue, aDensityMultiplier, aDensityDivider, aColor, aExtraData, aMaterialList, null);
//    }
//
//    public MaterialsOld(int aMetaItemSubID, TextureSet aIconSet, float aToolSpeed, int aDurability, int aToolQuality, int aTypes, int aR, int aG, int aB, int aA, String aName, String aDefaultLocalName, int aFuelType, int aFuelPower, int aMeltingPoint, int aBlastFurnaceTemp, boolean aBlastFurnaceRequired, boolean aTransparent, int aOreValue, int aDensityMultiplier, int aDensityDivider, Dyes aColor, int aExtraData, List<MaterialStack> aMaterialList, List<TC_Aspects.TC_AspectStack> aAspects) {
//        this(aMetaItemSubID, aIconSet, aToolSpeed, aDurability, aToolQuality, aTypes, aR, aG, aB, aA, aName, aDefaultLocalName, aFuelType, aFuelPower, aMeltingPoint, aBlastFurnaceTemp, aBlastFurnaceRequired, aTransparent, aOreValue, aDensityMultiplier, aDensityDivider, aColor);
//        mExtraData = aExtraData;
//        mProcessInto.addAll(aMaterialList);
//        mChemicalFormula = "";
//        for (MaterialStack tMaterial : mProcessInto) mChemicalFormula += tMaterial.toString();
//        mChemicalFormula = mChemicalFormula.replaceAll("_", "-");
//
//        int tAmountOfComponents = 0, tMeltingPoint = 0;
//        for (MaterialStack tMaterial : mProcessInto) {
//            tAmountOfComponents += tMaterial.mAmount;
//            if (tMaterial.mMaterial.mMeltingPoint > 0)
//                tMeltingPoint += tMaterial.mMaterial.mMeltingPoint * tMaterial.mAmount;
//            if (aAspects == null)
//                for (TC_Aspects.TC_AspectStack tAspect : tMaterial.mMaterial.mAspects) tAspect.addToAspectList(mAspects);
//        }
//
//        if (mMeltingPoint < 0) mMeltingPoint = (short) (tMeltingPoint / tAmountOfComponents);
//
//        tAmountOfComponents *= aDensityMultiplier;
//        tAmountOfComponents /= aDensityDivider;
//        if (aAspects == null) for (TC_Aspects.TC_AspectStack tAspect : mAspects)
//            tAspect.mAmount = Math.max(1, tAspect.mAmount / Math.max(1, tAmountOfComponents));
//        else mAspects.addAll(aAspects);
//    }
//
//    /**
//     * This is for keeping compatibility with addons mods (Such as TinkersGregworks etc) that looped over the old MaterialsOld enum
//     */
//    @Deprecated
//    public String name() {
//        return mName;
//    }
//
//    /**
//     * This is for keeping compatibility with addons mods (Such as TinkersGregworks etc) that looped over the old MaterialsOld enum
//     */
//    @Deprecated
//    public static MaterialsOld valueOf(String aMaterialName) {
//        return getMaterialsOldMap().get(aMaterialName);
//    }
//
//    /**
//     * This is for keeping compatibility with addons mods (Such as TinkersGregworks etc) that looped over the old MaterialsOld enum
//     */
//    public static MaterialsOld[] values() {
//        return MaterialsOld_ALL;
//    }
//
//    /**
//     * This should only be used for getting a Material by its name as a String. Do not loop over this map, use values().
//     */
//    public static Map<String, MaterialsOld> getMaterialsOldMap() {
//        return MaterialsOld_MAP;
//    }
//
//    public static MaterialsOld get(String aMaterialName) {
//        MaterialsOld aMaterial = getMaterialsOldMap().get(aMaterialName);
//        if (aMaterial != null) return aMaterial;
//        return MaterialsOld.dyeNULL;
//    }
//
//    public static MaterialsOld getRealMaterial(String aMaterialName) {
//        return get(aMaterialName).mMaterialInto;
//    }
//
//    public boolean isRadioactive() {
//        if (mElement != null) return mElement.mHalfLifeSeconds >= 0;
//        for (MaterialStack tMaterial : mProcessInto) if (tMaterial.mMaterial.isRadioactive()) return true;
//        return false;
//    }
//
//    public long getProtons() {
//        if (mElement != null) return mElement.getProtons();
//        if (mProcessInto.size() <= 0) return Element.Tc.getProtons();
//        long rAmount = 0, tAmount = 0;
//        for (MaterialStack tMaterial : mProcessInto) {
//            tAmount += tMaterial.mAmount;
//            rAmount += tMaterial.mAmount * tMaterial.mMaterial.getProtons();
//        }
//        return (getDensity() * rAmount) / (tAmount * M);
//    }
//
//    public long getNeutrons() {
//        if (mElement != null) return mElement.getNeutrons();
//        if (mProcessInto.size() <= 0) return Element.Tc.getNeutrons();
//        long rAmount = 0, tAmount = 0;
//        for (MaterialStack tMaterial : mProcessInto) {
//            tAmount += tMaterial.mAmount;
//            rAmount += tMaterial.mAmount * tMaterial.mMaterial.getNeutrons();
//        }
//        return (getDensity() * rAmount) / (tAmount * M);
//    }
//
//    public long getMass() {
//        if (mElement != null) return mElement.getMass();
//        if (mProcessInto.size() <= 0) return Element.Tc.getMass();
//        long rAmount = 0, tAmount = 0;
//        for (MaterialStack tMaterial : mProcessInto) {
//            tAmount += tMaterial.mAmount;
//            rAmount += tMaterial.mAmount * tMaterial.mMaterial.getMass();
//        }
//        return (getDensity() * rAmount) / (tAmount * M);
//    }
//
//    public long getDensity() {
//        return mDensity;
//    }
//
//    public String getToolTip() {
//        return getToolTip(1, false);
//    }
//
//    public String getToolTip(boolean aShowQuestionMarks) {
//        return getToolTip(1, aShowQuestionMarks);
//    }
//
//    public String getToolTip(long aMultiplier) {
//        return getToolTip(aMultiplier, false);
//    }
//
//    public String getToolTip(long aMultiplier, boolean aShowQuestionMarks) {
//        if (!aShowQuestionMarks && mChemicalFormula.equals("?")) return "";
//        if (aMultiplier >= M * 2 && !mProcessInto.isEmpty()) {
//            return ((mElement != null || (mProcessInto.size() < 2 && mProcessInto.get(0).mAmount == 1)) ? mChemicalFormula : "(" + mChemicalFormula + ")") + aMultiplier;
//        }
//        return mChemicalFormula;
//    }
//
//    /**
//     * Adds a Class implementing IMaterialRegistrator to the master list
//     */
//    public static boolean add(IMaterialHandler aRegistrator) {
//        if (aRegistrator == null) return false;
//        return mMaterialHandlers.add(aRegistrator);
//    }
//
//    /**
//     * Adds an ItemStack to this Material.
//     */
//    public MaterialsOld add(ItemStack aStack) {
//        if (aStack != null && !contains(aStack)) mMaterialItems.add(aStack);
//        return this;
//    }
//
//    /**
//     * This is used to determine if any of the ItemStacks belongs to this Material.
//     */
//    public boolean contains(ItemStack... aStacks) {
//        if (aStacks == null || aStacks.length <= 0) return false;
//        for (ItemStack tStack : mMaterialItems)
//            for (ItemStack aStack : aStacks)
//                if (GT_Utility.areStacksEqual(aStack, tStack, !tStack.hasTagCompound())) return true;
//        return false;
//    }
//
//    /**
//     * This is used to determine if an ItemStack belongs to this Material.
//     */
//    public boolean remove(ItemStack aStack) {
//        if (aStack == null) return false;
//        boolean temp = false;
//        int mMaterialItems_sS=mMaterialItems.size();
//        for (int i = 0; i < mMaterialItems_sS; i++)
//            if (GT_Utility.areStacksEqual(aStack, mMaterialItems.get(i))) {
//                mMaterialItems.remove(i--);
//                temp = true;
//            }
//        return temp;
//    }
//
//    /**
//     * Adds a SubTag to this Material
//     */
//    @Override
//    public ISubTagContainer add(SubTag... aTags) {
//        if (aTags != null) for (SubTag aTag : aTags)
//            if (aTag != null && !contains(aTag)) {
//                aTag.addContainerToList(this);
//                mSubTags.add(aTag);
//            }
//        return this;
//    }
//
//    /**
//     * If this Material has this exact SubTag
//     */
//    @Override
//    public boolean contains(SubTag aTag) {
//        return mSubTags.contains(aTag);
//    }
//
//    /**
//     * Removes a SubTag from this Material
//     */
//    @Override
//    public boolean remove(SubTag aTag) {
//        return mSubTags.remove(aTag);
//    }
//
//    /**
//     * Sets the Heat Damage for this Material (negative = frost)
//     */
//    public MaterialsOld setHeatDamage(float aHeatDamage) {
//        mHeatDamage = aHeatDamage;
//        return this;
//    }
//
//    /**
//     * Adds a Material to the List of Byproducts when grinding this Ore.
//     * Is used for more precise Ore grinding, so that it is possible to choose between certain kinds of MaterialsOld.
//     */
//    public MaterialsOld addOreByProduct(MaterialsOld aMaterial) {
//        if (!mByProducts.contains(aMaterial.mMaterialInto)) mByProducts.add(aMaterial.mMaterialInto);
//        return this;
//    }
//
//    /**
//     * Adds multiple MaterialsOld to the List of Byproducts when grinding this Ore.
//     * Is used for more precise Ore grinding, so that it is possible to choose between certain kinds of MaterialsOld.
//     */
//    public MaterialsOld addOreByProducts(MaterialsOld... aMaterialsOld) {
//        for (MaterialsOld tMaterial : aMaterialsOld) if (tMaterial != null) addOreByProduct(tMaterial);
//        return this;
//    }
//
//    /**
//     * If this Ore gives multiple drops of its Main Material.
//     * Lapis Ore for example gives about 6 drops.
//     */
//    public MaterialsOld setOreMultiplier(int aOreMultiplier) {
//        if (aOreMultiplier > 0) mOreMultiplier = aOreMultiplier;
//        return this;
//    }
//
//    /**
//     * If this Ore gives multiple drops of its Byproduct Material.
//     */
//    public MaterialsOld setByProductMultiplier(int aByProductMultiplier) {
//        if (aByProductMultiplier > 0) mByProductMultiplier = aByProductMultiplier;
//        return this;
//    }
//
//    /**
//     * If this Ore gives multiple drops of its Main Material.
//     * Lapis Ore for example gives about 6 drops.
//     */
//    public MaterialsOld setSmeltingMultiplier(int aSmeltingMultiplier) {
//        if (aSmeltingMultiplier > 0) mSmeltingMultiplier = aSmeltingMultiplier;
//        return this;
//    }
//
//    /**
//     * This Ore should be smolten directly into an Ingot of this Material instead of an Ingot of itself.
//     */
//    public MaterialsOld setDirectSmelting(MaterialsOld aMaterial) {
//        if (aMaterial != null) mDirectSmelting = aMaterial.mMaterialInto.mDirectSmelting;
//        return this;
//    }
//
//    /**
//     * This Material should be the Main Material this Ore gets ground into.
//     * Example, Chromite giving Chrome or Tungstate giving Tungsten.
//     */
//    public MaterialsOld setOreReplacement(MaterialsOld aMaterial) {
//        if (aMaterial != null) mOreReplacement = aMaterial.mMaterialInto.mOreReplacement;
//        return this;
//    }
//
//    /**
//     * This Material smelts always into an instance of aMaterial. Used for Magnets.
//     */
//    public MaterialsOld setSmeltingInto(MaterialsOld aMaterial) {
//        if (aMaterial != null) mSmeltInto = aMaterial.mMaterialInto.mSmeltInto;
//        return this;
//    }
//
//    /**
//     * This Material arc smelts always into an instance of aMaterial. Used for Wrought Iron.
//     */
//    public MaterialsOld setArcSmeltingInto(MaterialsOld aMaterial) {
//        if (aMaterial != null) mArcSmeltInto = aMaterial.mMaterialInto.mArcSmeltInto;
//        return this;
//    }
//
//    /**
//     * This Material macerates always into an instance of aMaterial.
//     */
//    public MaterialsOld setMaceratingInto(MaterialsOld aMaterial) {
//        if (aMaterial != null) mMacerateInto = aMaterial.mMaterialInto.mMacerateInto;
//        return this;
//    }
//
//    public MaterialsOld setEnchantmentForTools(Enchantment aEnchantment, int aEnchantmentLevel) {
//        mEnchantmentTools = aEnchantment;
//        mEnchantmentToolsLevel = (byte) aEnchantmentLevel;
//        return this;
//    }
//
//    public MaterialsOld setEnchantmentForArmors(Enchantment aEnchantment, int aEnchantmentLevel) {
//        mEnchantmentArmors = aEnchantment;
//        mEnchantmentArmorsLevel = (byte) aEnchantmentLevel;
//        return this;
//    }
//
//    public FluidStack getSolid(long aAmount) {
//        if (mSolid == null) return null;
//        return new GT_FluidStack(mSolid, (int) aAmount);
//    }
//
//    public FluidStack getFluid(long aAmount) {
//        if (mFluid == null) return null;
//        return new GT_FluidStack(mFluid, (int) aAmount);
//    }
//
//    public FluidStack getGas(long aAmount) {
//        if (mGas == null) return null;
//        return new GT_FluidStack(mGas, (int) aAmount);
//    }
//
//    public FluidStack getPlasma(long aAmount) {
//        if (mPlasma == null) return null;
//        return new GT_FluidStack(mPlasma, (int) aAmount);
//    }
//
//    public FluidStack getMolten(long aAmount) {
//        if (mStandardMoltenFluid == null) return null;
//        return new GT_FluidStack(mStandardMoltenFluid, (int) aAmount);
//    }
//
//    @Override
//    public short[] getRGBA() {
//        return mRGBa;
//    }
//
//    @Override
//    public String toString() {
//        return this.mName;
//    }
//
//    public static volatile int VERSION = 509;
//
//    public static Collection<MaterialsOld> getAll(){
//    	return MaterialsOld_MAP.values();
//    }
//
//	public boolean hasCorrespondingFluid() {
//		return hasCorrespondingFluid;
//	}
//
//	public MaterialsOld setHasCorrespondingFluid(boolean hasCorrespondingFluid) {
//		this.hasCorrespondingFluid = hasCorrespondingFluid;
//		return this;
//	}
//
//	public boolean hasCorrespondingGas() {
//		return hasCorrespondingGas;
//	}
//
//	public MaterialsOld setHasCorrespondingGas(boolean hasCorrespondingGas) {
//		this.hasCorrespondingGas = hasCorrespondingGas;
//		return this;
//	}
//
//	public boolean canBeCracked() {
//		return canBeCracked;
//	}
//
//	public MaterialsOld setCanBeCracked(boolean canBeCracked) {
//		this.canBeCracked = canBeCracked;
//		return this;
//	}
//
//	public int getLiquidTemperature() {
//		return mMeltingPoint == 0 ? 295 : mMeltingPoint;
//	}
//
//	public MaterialsOld setLiquidTemperature(int liquidTemperature) {
//		this.mMeltingPoint = (short) liquidTemperature;
//		return this;
//	}
//
//	public MaterialsOld setHydroCrackedFluids(Fluid[] hydroCrackedFluids) {
//		this.hydroCrackedFluids = hydroCrackedFluids;
//		return this;
//	}
//
//	public FluidStack getLightlyHydroCracked(int amount) {
//		if (hydroCrackedFluids[0] == null) {
//			return null;
//		}
//		return new FluidStack(hydroCrackedFluids[0], amount);
//	}
//
//	public FluidStack getModeratelyHydroCracked(int amount) {
//		if (hydroCrackedFluids[0] == null) {
//			return null;
//		}
//		return new FluidStack(hydroCrackedFluids[1], amount);
//	}
//
//	public FluidStack getSeverelyHydroCracked(int amount) {
//		if (hydroCrackedFluids[0] == null) {
//			return null;
//		}
//		return new FluidStack(hydroCrackedFluids[2], amount);
//	}
//
//	public MaterialsOld setSteamCrackedFluids(Fluid[] steamCrackedFluids) {
//		this.steamCrackedFluids = steamCrackedFluids;
//		return this;
//	}
//
//	public FluidStack getLightlySteamCracked(int amount) {
//		if (hydroCrackedFluids[0] == null) {
//			return null;
//		}
//		return new FluidStack(steamCrackedFluids[0], amount);
//	}
//
//	public FluidStack getModeratelySteamCracked(int amount) {
//		if (hydroCrackedFluids[0] == null) {
//			return null;
//		}
//		return new FluidStack(steamCrackedFluids[1], amount);
//	}
//
//	public FluidStack getSeverelySteamCracked(int amount) {
//		if (hydroCrackedFluids[0] == null) {
//			return null;
//		}
//		return new FluidStack(steamCrackedFluids[2], amount);
//	}
//
//	public int getGasTemperature() {
//		return mGasTemp == 0 ? 295 : mMeltingPoint;
//	}
//
//	public MaterialsOld setGasTemperature(int gasTemperature) {
//		this.mGasTemp = (short) gasTemperature;
//		return this;
//	}
//
//	public ItemStack getCells(int amount){
//		return GT_OreDictUnificator.get(OrePrefixes.cell, this, amount);
//	}
//
//	public ItemStack getDust(int amount){
//		return GT_OreDictUnificator.get(OrePrefixes.dust, this, amount);
//	}
//
//	public ItemStack getDustSmall(int amount){
//		return GT_OreDictUnificator.get(OrePrefixes.dustSmall, this, amount);
//	}
//
//	public ItemStack getDustTiny(int amount){
//		return GT_OreDictUnificator.get(OrePrefixes.dustTiny, this, amount);
//	}
//
//	public ItemStack getGems(int amount){
//		return GT_OreDictUnificator.get(OrePrefixes.gem, this, amount);
//	}
//
//    public ItemStack getIngots(int amount){
//    	return GT_OreDictUnificator.get(OrePrefixes.ingot, this, amount);
//    }
//
//    public ItemStack getBlocks(int amount){
//    	return GT_OreDictUnificator.get(OrePrefixes.block, this, amount);
//    }
//
//    public ItemStack getPlates(int amount){
//    	return GT_OreDictUnificator.get(OrePrefixes.plate, this, amount);
//    }
//
//}
