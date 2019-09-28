package gregtech.api.enums;

import gregtech.api.interfaces.IMaterialHandler;
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

    public static Materials Signalum                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 1|2                       , 255, 255, 255,   0,   "Signalum"                ,   "Signalum"                      ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Lumium                  = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 1|2                       , 255, 255, 255,   0,   "Lumium"                  ,   "Lumium"                        ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials EnrichedCopper          = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 1|2                       , 255, 255, 255,   0,   "EnrichedCopper"          ,   "Enriched Copper"               ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials DiamondCopper           = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 1|2                       , 255, 255, 255,   0,   "DiamondCopper"           ,   "Diamond Copper"                ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials TarPitch                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 1|2                       , 255, 255, 255,   0,   "TarPitch"                ,   "Tar Pitch"                     ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials LimePure                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  0, 0                         , 255, 255, 255,   0,   "LimePure"                ,   "Pure Lime"                     ,    0,       0,         -1,    0, false, false,   1,   1,   1, Dyes.dyeLime        );
    public static Materials Wimalite                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2,       8                   , 255, 255, 255,   0,   "Wimalite"                ,   "Wimalite"                      ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes.dyeYellow      );
    public static Materials Yellorite               = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2,       8                   , 255, 255, 255,   0,   "Yellorite"               ,   "Yellorite"                     ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes.dyeYellow      );
    public static Materials Quantum                 = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  0, 0                         , 255, 255, 255,   0,   "Quantum"                 ,   "Quantum"                       ,    0,       0,         -1,    0, false, false,   1,   1,   1, Dyes.dyeWhite       );
    public static Materials Turquoise               = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  1, 1                         , 255, 255, 255,   0,   "Turquoise"               ,   "Turquoise"                     ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Tapazite                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  1, 1                         , 255, 255, 255,   0,   "Tapazite"                ,   "Tapazite"                      ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes.dyeGreen       );
    public static Materials Thyrium                 = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  1, 1|2  |8                   , 255, 255, 255,   0,   "Thyrium"                 ,   "Thyrium"                       ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Tourmaline              = new Materials(  -1, TextureSet.SET_RUBY              ,   1.0F,      0,  1, 1                         , 255, 255, 255,   0,   "Tourmaline"              ,   "Tourmaline"                    ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Spinel                  = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  1, 0                         , 255, 255, 255,   0,   "Spinel"                  ,   "Spinel"                        ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Starconium              = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  1, 1|2  |8                   , 255, 255, 255,   0,   "Starconium"              ,   "Starconium"                    ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Sugilite                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  1, 1                         , 255, 255, 255,   0,   "Sugilite"                ,   "Sugilite"                      ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Prismarine              = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 1  |4                     , 255, 255, 255,   0,   "Prismarine"              ,   "Prismarine"                    ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials GraveyardDirt           = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 1                         , 255, 255, 255,   0,   "GraveyardDirt"           ,   "Graveyard Dirt"                ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Tennantite              = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 1                         , 255, 255, 255,   0,   "Tennantite"              ,   "Tennantite"                    ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Fairy                   = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 1|2                       , 255, 255, 255,   0,   "Fairy"                   ,   "Fairy"                         ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Ludicrite               = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 1|2                       , 255, 255, 255,   0,   "Ludicrite"               ,   "Ludicrite"                     ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials AquaRegia               = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 0                         , 255, 255, 255,   0,   "AquaRegia"               ,   "Aqua Regia"                    ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials SolutionBlueVitriol     = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 0                         , 255, 255, 255,   0,   "SolutionBlueVitriol"     ,   "Blue Vitriol Solution"         ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials SolutionNickelSulfate   = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 0                         , 255, 255, 255,   0,   "SolutionNickelSulfate"   ,   "Nickel Sulfate Solution"       ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Lodestone               = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  1, 1    |8                   , 255, 255, 255,   0,   "Lodestone"               ,   "Lodestone"                     ,    0,       0,         -1,    0, false, false,   1,   1,   1, Dyes._NULL          );
    public static Materials Luminite                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  1, 1    |8                   , 250, 250, 250,   0,   "Luminite"                ,   "Luminite"                      ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes.dyeWhite       );
    public static Materials Bathwater               = new Materials(  766, TextureSet.SET_FLUID             ,  1.0F,      0,  1,  16,                       250, 250, 250,   0,   "AnimeGirlBathwater"       ,   "Anime Girl Bathwater"           ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes.dyeWhite      );
    public static Materials Sweat                   = new Materials(  767, TextureSet.SET_FLUID             ,  1.0F,      0,  1,  16,                       250, 250, 250,   0,   "Sweat"                    ,   "Sweat"                         ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes.dyeWhite       );
    public static Materials Urine                   = new Materials(  768, TextureSet.SET_FLUID             ,  1.0F,      0,  1,  16,                       255, 218, 155,   0,   "Urine"                    ,   "Urine"                         ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes.dyeWhite       );
    public static Materials GGEssence               = new Materials(  769, TextureSet.SET_METALLIC             ,  1.0F,      0,  1,  1|4,                       251, 181, 255,   0,   "GGessence"                ,   "Gamer Girl Essence"            ,    0,    0,       1200,    0, false, false,   3,   1,   1, Dyes.dyePink     );
    public static Materials Weebium                 = new Materials(  765, TextureSet.SET_METALLIC          ,  25.0F,     512000, 8,1|2|8|16|32|64|128     , 247, 158, 247,   0,   "Weebium"                    ,   "Weebium"                     ,    0,       0,       3695,  5400, true, false,   2,   1,   1, Dyes.dyePink       , Element.Wb        , Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.ALIENIS, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.VICTUS, 1))).disableAutoGeneratedBlastFurnaceRecipes();
    public static Materials PMagium                 = new Materials(  743, TextureSet.SET_METALLIC          ,  512.0F,     1024000, 8,1|2        |64           , 248, 186, 173,   0,   "PuellaMagium"             ,    PuellaMagium                  ,    5,       750000,    7200,  7200, true, false,   2,   1,   1, Dyes.dyePink      , Arrays.asList(new TC_Aspects.TC_AspectStack(TC_Aspects.ALIENIS, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.VICTUS, 1))).disableAutoGeneratedBlastFurnaceRecipes();


    public final static String SpWbUwU= EnumChatFormatting.RESET + "" + EnumChatFormatting.DARK_PURPLE + "S" + EnumChatFormatting.LIGHT_PURPLE + "p" + EnumChatFormatting.DARK_RED + "W" + EnumChatFormatting.RED + "b" + EnumChatFormatting.YELLOW + "U" + EnumChatFormatting.GREEN + "w" + EnumChatFormatting.AQUA + "U";




    private static void initSubTags() {



        SubTag.METAL.addTo(Signalum, Lumium, EnrichedCopper, DiamondCopper, Weebium);

        Weebium     .add(SubTag.NO_PLATE_SMASHING, SubTag.NO_WIRE_SNIPPING, SubTag.NO_FOIL_SMASHING, SubTag.NO_BLOCK_SMASHING);

        SubTag.NO_SMASHING.addTo(TarPitch);
        SubTag.NO_PLATE_SMASHING.addTo(Weebium);
        SubTag.NO_WIRE_SNIPPING.addTo(Weebium);
        SubTag.NO_FOIL_SMASHING.addTo(Weebium);
        SubTag.NO_BLOCK_SMASHING.addTo(Weebium);
        // SubTag.NO_WORKING.addTo(Weebium);
        // SubTag.NO_SMASHING.addTo(Weebium);
        // SubTag.NO_SMELTING.addTo(Weebium);



        Weebium                 .addOreByProducts(UUMatter, Oriharukon, Indium);
        Weebium                .setOreMultiplier( 5).setSmeltingMultiplier( 5);



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