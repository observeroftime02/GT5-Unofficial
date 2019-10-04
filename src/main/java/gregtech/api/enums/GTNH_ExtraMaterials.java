package gregtech.api.enums;

import gregtech.api.interfaces.IMaterialHandler;
import gregtech.api.objects.MaterialStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.Arrays;

import static gregtech.GT_Mod.GT_FML_LOGGER;
import static gregtech.api.enums.Materials.*;
import static gregtech.api.enums.OrePrefixes.*;

public class GTNH_ExtraMaterials implements IMaterialHandler {

    public GTNH_ExtraMaterials(){
        GT_FML_LOGGER.info("Registering GTNH-Materials (post Java 64kb limit)");
        Materials.add(this);
    }

    /**
     * This Class is for adding new Materials since Java has a Limiation of 64kb per Method / Class header
     */
    public final static String PuellaMagium = EnumChatFormatting.LIGHT_PURPLE + "Puella Magium" + EnumChatFormatting.RESET;
    public final static String CEntropy     = EnumChatFormatting.DARK_PURPLE + "C" + EnumChatFormatting.OBFUSCATED + "u" + EnumChatFormatting.RESET + EnumChatFormatting.DARK_PURPLE + "r" + EnumChatFormatting.OBFUSCATED + "s" + EnumChatFormatting.RESET + EnumChatFormatting.DARK_PURPLE + "ed " + EnumChatFormatting.DARK_PURPLE + "Entropy" + EnumChatFormatting.RESET;

    public static Materials Signalum                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     2, 1|2                       , 255, 255, 255,   0,   "Signalum"                ,   "Signalum"                      ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Lumium                  = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     2, 1|2                       , 255, 255, 255,   0,   "Lumium"                  ,   "Lumium"                        ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials EnrichedCopper          = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     2, 1|2                       , 255, 255, 255,   0,   "EnrichedCopper"          ,   "Enriched Copper"               ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials DiamondCopper           = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     2, 1|2                       , 255, 255, 255,   0,   "DiamondCopper"           ,   "Diamond Copper"                ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials TarPitch                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     2, 1|2                       , 255, 255, 255,   0,   "TarPitch"                ,   "Tar Pitch"                     ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials LimePure                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     0, 0                         , 255, 255, 255,   0,   "LimePure"                ,   "Pure Lime"                     ,    0,       0,         -1,    0,      false, false,   1,   1,   1, Dyes.dyeLime        );
    public static Materials Wimalite                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     2, 8                         , 255, 255, 255,   0,   "Wimalite"                ,   "Wimalite"                      ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes.dyeYellow      );
    public static Materials Yellorite               = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     2, 8                         , 255, 255, 255,   0,   "Yellorite"               ,   "Yellorite"                     ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes.dyeYellow      );
    public static Materials Quantum                 = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     0, 0                         , 255, 255, 255,   0,   "Quantum"                 ,   "Quantum"                       ,    0,       0,         -1,    0,      false, false,   1,   1,   1, Dyes.dyeWhite       );
    public static Materials Turquoise               = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     1, 1                         , 255, 255, 255,   0,   "Turquoise"               ,   "Turquoise"                     ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Tapazite                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     1, 1                         , 255, 255, 255,   0,   "Tapazite"                ,   "Tapazite"                      ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes.dyeGreen       );
    public static Materials Thyrium                 = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     1, 1|2  |8                   , 255, 255, 255,   0,   "Thyrium"                 ,   "Thyrium"                       ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Tourmaline              = new Materials(  -1, TextureSet.SET_RUBY              ,   1.0F,      0,     1, 1                         , 255, 255, 255,   0,   "Tourmaline"              ,   "Tourmaline"                    ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Spinel                  = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     1, 0                         , 255, 255, 255,   0,   "Spinel"                  ,   "Spinel"                        ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Starconium              = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     1, 1|2  |8                   , 255, 255, 255,   0,   "Starconium"              ,   "Starconium"                    ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Sugilite                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     1, 1                         , 255, 255, 255,   0,   "Sugilite"                ,   "Sugilite"                      ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Prismarine              = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     2, 1  |4                     , 255, 255, 255,   0,   "Prismarine"              ,   "Prismarine"                    ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials GraveyardDirt           = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     2, 1                         , 255, 255, 255,   0,   "GraveyardDirt"           ,   "Graveyard Dirt"                ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Tennantite              = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     2, 1                         , 255, 255, 255,   0,   "Tennantite"              ,   "Tennantite"                    ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Fairy                   = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     2, 1|2                       , 255, 255, 255,   0,   "Fairy"                   ,   "Fairy"                         ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Ludicrite               = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     2, 1|2                       , 255, 255, 255,   0,   "Ludicrite"               ,   "Ludicrite"                     ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials AquaRegia               = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     2, 0                         , 255, 255, 255,   0,   "AquaRegia"               ,   "Aqua Regia"                    ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials SolutionBlueVitriol     = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     2, 0                         , 255, 255, 255,   0,   "SolutionBlueVitriol"     ,   "Blue Vitriol Solution"         ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials SolutionNickelSulfate   = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     2, 0                         , 255, 255, 255,   0,   "SolutionNickelSulfate"   ,   "Nickel Sulfate Solution"       ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Lodestone               = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     1, 1    |8                   , 255, 255, 255,   0,   "Lodestone"               ,   "Lodestone"                     ,    0,       0,         -1,    0,      false, false,   1,   1,   1, Dyes._NULL          );
    public static Materials Luminite                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,     1, 1    |8                   , 250, 250, 250,   0,   "Luminite"                ,   "Luminite"                      ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes.dyeWhite       );
    public static Materials Bathwater               = new Materials(  766, TextureSet.SET_FLUID             ,  1.0F,      0,     1, 16                        , 250, 250, 250,   0,   "AnimeGirlBathwater"      ,   "Anime Girl Bathwater"          ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes.dyeWhite      );
    public static Materials Sweat                   = new Materials(  767, TextureSet.SET_FLUID             ,  1.0F,      0,     1, 16                        , 250, 250, 250,   0,   "Sweat"                   ,   "Sweat"                         ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes.dyeWhite       );
    public static Materials Urine                   = new Materials(  768, TextureSet.SET_FLUID             ,  1.0F,     0,      1, 16                        , 255, 218, 155,   0,   "Urine"                   ,   "Urine"                         ,    0,       0,         -1,    0,      false, false,   3,   1,   1, Dyes.dyeWhite       );
    public static Materials AGEssence               = new Materials(  769, TextureSet.SET_RUBY              ,  7.0F,     256,    2, 1|4|8                     , 251, 181, 255,   127,   "AnimeGirlEssence"      ,   "Anime Girl Essence"            ,    0,       0,        -1,     0,     false, true,    5,   1,   1, Dyes.dyePink     );
    public static Materials Weebium                 = new Materials(  765, TextureSet.SET_METALLIC          ,  25.0F,    512000, 8, 1|2|8|16|32|64|128        , 247, 158, 247,   0,   "Weebium"                 ,   "Weebium"                       ,    0,       0,       3695,     5400,  true,   false,   2,   1,   1, Dyes.dyePink       , Element.Wb        , Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.ALIENIS, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.VICTUS, 1))).disableAutoGeneratedBlastFurnaceRecipes();
    public static Materials PMagium                 = new Materials(  743, TextureSet.SET_METALLIC          ,  512.0F,   1024000, 9,1|2        |64            , 248, 186, 173,   0,   "PuellaMagium"            ,    PuellaMagium                                    ,    5,      750000,    7200,  7200,    true,   false,   2,   1,   1, Dyes.dyePink      , 2, Arrays.asList(new MaterialStack(AGEssence, 3), new MaterialStack(Weebium, 7)), Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.ALIENIS, 15))).disableAutoGeneratedBlastFurnaceRecipes();
    public static Materials GAGPanties              = new Materials( 783, TextureSet.SET_SHINY              ,   1.0F,    0,      1, 1                         , 179, 255, 252,   0,   "GAGPanties"              ,   "Fine Anime Girl Panties"       ,    0,       0,         -1,    0,      false,  false,   3,   1,   1, Dyes.dyeLightBlue  );
    public static Materials RefPMagium              = new Materials( 784, TextureSet.SET_SHINY              ,   1.0F,    0,      1, 1        |16              , 255, 164, 145,   0,   "RedfinedPuellamagium"    ,   "Refined Puella Magium"         ,    0,       0,         -1,    0,       false,  false,   3,   1,   1, Dyes.dyePink  );
    public static Materials ConcPMagium             = new Materials( 785, TextureSet.SET_SHINY              ,   1.0F,    0,      1, 1                         , 255, 164, 145,   0,   "ConcentratedPmagium"     ,   "Concentrated Puella Magium"    ,    0,       0,         -1,    0,       false,  false,   3,   1,   1, Dyes.dyePink  );
    public static Materials SoulGem                 = new Materials( 786, TextureSet.SET_DIAMOND            ,   1.0F,    0,      1, 1  |4                     , 235, 145, 255,   0,   "SoulGem"                 ,   "Mahou Shoujou Soul Gem"        ,    0,       0,         -1,    0,       false,  true,   3,   1,   1, Dyes.dyePink  );
    public static Materials CursedEntropy           = new Materials( 788, TextureSet.SET_RUBY               ,   7.0F,    256,    2, 1  |4                     , 47 , 25,  79,    127, "CursedEntropy"           ,   CEntropy                                         ,    0,       0,         -1,    0,      false,  true,   5,   1,   1, Dyes.dyeBlack         , 1, Arrays.asList(new MaterialStack(Chrome, 1), new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 3)), Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.LUCRUM, 6), new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 4)));
    public static Materials InfiniteDiesel          = new Materials( 789, TextureSet.SET_FLUID              ,   7.0F,    256,  2,   16                        , 252, 250, 167,   127,  "InfinityBooster"         ,   "Infinity Fuel Booster"         ,    0,       0,         -1,    0,      false,  false,   5,   1,   1, Dyes.dyeRed         , 1, Arrays.asList(new MaterialStack(Chrome, 1), new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 3)), Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.LUCRUM, 6), new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 4)));


    // public static Materials Testmaterial            = new Materials( 780, TextureSet.SET_RUBY              ,   7.0F,    256,  2, 1  |4|8      |64          , 255, 100, 100, 127,   "Testmaterial"                    ,   "Testmaterial"          ,    0,       0,         -1,    0, false,  true,   5,   1,   1, Dyes.dyeRed         , 1, Arrays.asList(new MaterialStack(Chrome, 1), new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 3)), Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.LUCRUM, 6), new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 4)));
    public static Materials PMSlurry                = new Materials( 781, TextureSet.SET_FLUID              ,   1.0F,    0,  1, 16                            , 248, 186, 173, 0,   "PuellaMagiumSlurry"         ,   "Puella Magium Slurry"          ,    0,       0,         -1,    0, false,  false,   3,   1,   1, Dyes.dyePink         );
    public static Materials RedPMSlurry             = new Materials( 782, TextureSet.SET_FLUID              ,   1.0F,    0,  1, 1|16                          , 248, 186, 173, 0,   "ReducedPMSlurry"         ,   "Reduced Puella Magium Slurry"  ,    0,       0,         -1,    0, false,  false,   3,   1,   1, Dyes.dyePink         );



    public final static String SpWbUwU= EnumChatFormatting.RESET + "" + EnumChatFormatting.DARK_PURPLE + "S" + EnumChatFormatting.LIGHT_PURPLE + "p" + EnumChatFormatting.DARK_RED + "W" + EnumChatFormatting.RED + "b" + EnumChatFormatting.YELLOW + "U" + EnumChatFormatting.GREEN + "w" + EnumChatFormatting.AQUA + "U";





    private static void initSubTags() {



        SubTag.METAL.addTo(Signalum, Lumium, EnrichedCopper, DiamondCopper, Weebium);

        // Testmaterial                 .add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING);
        Weebium                      .add(SubTag.NO_PLATE_SMASHING, SubTag.NO_WIRE_SNIPPING, SubTag.NO_FOIL_SMASHING, SubTag.NO_BLOCK_SMASHING);
        AGEssence                    .add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.CRYSTALLISABLE);
        SoulGem                      .add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.NO_GEM_RECIPE_GEN);
        CursedEntropy                .add(SubTag.CRYSTAL, SubTag.NO_SMASHING, SubTag.NO_SMELTING, SubTag.NO_GEM_RECIPE_GEN);



        PMagium.disableAutoGeneratedBlastFurnaceRecipes();


        SubTag.NO_SMASHING.addTo(TarPitch);
        SubTag.NO_PLATE_SMASHING.addTo(Weebium);
        SubTag.NO_WIRE_SNIPPING.addTo(Weebium);
        SubTag.NO_FOIL_SMASHING.addTo(Weebium);
        SubTag.NO_BLOCK_SMASHING.addTo(Weebium);



        Weebium                 .addOreByProducts(UUMatter, Oriharukon, Indium);
        Weebium                 .setOreMultiplier( 5).setSmeltingMultiplier( 5);
        AGEssence               .addOreByProducts(Weebium, PMagium);


        Weebium.mChemicalFormula=SpWbUwU;

    }

    @Override
    public void onMaterialsInit() {
        initSubTags();
    }

    @Override
    public void onComponentInit() {
    }

    @Override
    public void onComponentIteration(Materials aMaterial) {

    }







}