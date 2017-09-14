package gregtech.common;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.TC_Aspects.TC_AspectStack;
import gregtech.api.enums.*;
import gregtech.api.interfaces.IProjectileItem;
import gregtech.api.interfaces.internal.IGT_Mod;
import gregtech.api.interfaces.internal.IThaumcraftCompat;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.items.GT_MetaGenerated_Item;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.api.net.GT_Packet_Pollution;
import gregtech.api.objects.GT_Fluid;
import gregtech.api.objects.GT_UO_DimensionList;
import gregtech.api.objects.ItemData;
import gregtech.api.util.*;
import gregtech.common.entities.GT_Entity_Arrow;
import gregtech.common.items.GT_MetaGenerated_Tool_01;
import gregtech.common.items.armor.ModularArmor_Item;
import gregtech.common.items.armor.gui.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.io.File;
import java.text.DateFormat;
import java.util.*;

public abstract class GT_Proxy implements IGT_Mod, IGuiHandler, IFuelHandler {
    private static final EnumSet<OreGenEvent.GenerateMinable.EventType> PREVENTED_ORES = EnumSet.of(OreGenEvent.GenerateMinable.EventType.COAL,
            OreGenEvent.GenerateMinable.EventType.IRON, OreGenEvent.GenerateMinable.EventType.GOLD,
            OreGenEvent.GenerateMinable.EventType.DIAMOND, OreGenEvent.GenerateMinable.EventType.REDSTONE, OreGenEvent.GenerateMinable.EventType.LAPIS,
            OreGenEvent.GenerateMinable.EventType.QUARTZ);
    public final HashSet<ItemStack> mRegisteredOres = new HashSet<ItemStack>(10000);
    public final ArrayList<String> mSoundNames = new ArrayList<String>();
    public final ArrayList<ItemStack> mSoundItems = new ArrayList<ItemStack>();
    public final ArrayList<Integer> mSoundCounts = new ArrayList<Integer>();
    private final Collection<OreDictEventContainer> mEvents = new HashSet<OreDictEventContainer>();
    private final Collection<String> mIgnoredItems = new HashSet<String>(Arrays.asList(new String[]{"itemGhastTear", "itemFlint", "itemClay", "itemBucketSaltWater",
            "itemBucketFreshWater", "itemBucketWater", "itemRock", "itemReed", "itemArrow", "itemSaw", "itemKnife", "itemHammer", "itemChisel", "itemRubber",
            "itemEssence", "itemIlluminatedPanel", "itemSkull", "itemRawRubber", "itemBacon", "itemJetpackAccelerator", "itemLazurite", "itemIridium",
            "itemTear", "itemClaw", "itemFertilizer", "itemTar", "itemSlimeball", "itemCoke", "itemBeeswax", "itemBeeQueen", "itemForcicium", "itemForcillium",
            "itemRoyalJelly", "itemHoneydew", "itemHoney", "itemPollen", "itemReedTypha", "itemSulfuricAcid", "itemPotash", "itemCompressedCarbon",
            "itemBitumen", "itemBioFuel", "itemCokeSugar", "itemCokeCactus", "itemCharcoalSugar", "itemCharcoalCactus", "itemSludge", "itemEnrichedAlloy",
            "itemQuicksilver", "itemMercury", "itemOsmium", "itemUltimateCircuit", "itemEnergizedStar", "itemAntimatterMolecule", "itemAntimatterGlob",
            "itemCoal", "itemBoat", "itemHerbalMedicineCake", "itemCakeSponge", "itemFishandPumpkinCakeSponge", "itemSoulCleaver", "itemInstantCake",
            "itemWhippingCream", "itemGlisteningWhippingCream", "itemCleaver", "itemHerbalMedicineWhippingCream", "itemStrangeWhippingCream",
            "itemBlazeCleaver", "itemBakedCakeSponge", "itemMagmaCake", "itemGlisteningCake", "itemOgreCleaver", "itemFishandPumpkinCake",
            "itemMagmaWhippingCream", "itemMultimeter", "itemSuperconductor"}));
    private final Collection<String> mIgnoredNames = new HashSet<String>(Arrays.asList(new String[]{"grubBee", "chainLink", "candyCane", "bRedString", "bVial",
            "bFlask", "anorthositeSmooth", "migmatiteSmooth", "slateSmooth", "travertineSmooth", "limestoneSmooth", "orthogneissSmooth", "marbleSmooth",
            "honeyDrop", "lumpClay", "honeyEqualssugar", "flourEqualswheat", "bluestoneInsulated", "blockWaterstone", "blockSand", "blockTorch",
            "blockPumpkin", "blockClothRock", "blockStainedHardenedClay", "blockQuartzPillar", "blockQuartzChiselled", "blockSpawner", "blockCloth", "mobHead",
            "mobEgg", "enderFlower", "enderChest", "clayHardened", "dayGemMaterial", "nightGemMaterial", "snowLayer", "bPlaceholder", "hardenedClay",
            "eternalLifeEssence", "sandstone", "wheatRice", "transdimBlock", "bambooBasket", "lexicaBotania", "livingwoodTwig", "redstoneCrystal",
            "pestleAndMortar", "glowstone", "whiteStone", "stoneSlab", "transdimBlock", "clayBowl", "clayPlate", "ceramicBowl", "ceramicPlate", "ovenRack",
            "clayCup", "ceramicCup", "batteryBox", "transmutationStone", "torchRedstoneActive", "coal", "charcoal", "cloth", "cobblestoneSlab",
            "stoneBrickSlab", "cobblestoneWall", "stoneBrickWall", "cobblestoneStair", "stoneBrickStair", "blockCloud", "blockDirt", "blockTyrian",
            "blockCarpet", "blockFft", "blockLavastone", "blockHolystone", "blockConcrete", "sunnariumPart", "brSmallMachineCyaniteProcessor", "meteoriteCoal",
            "blockCobble", "pressOreProcessor", "crusherOreProcessor", "grinderOreProcessor", "blockRubber", "blockHoney", "blockHoneydew", "blockPeat",
            "blockRadioactive", "blockSlime", "blockCocoa", "blockSugarCane", "blockLeather", "blockClayBrick", "solarPanelHV", "cableRedNet", "stoneBowl",
            "crafterWood", "taintedSoil", "brickXyEngineering", "breederUranium", "wireMill", "chunkLazurite", "aluminumNatural", "aluminiumNatural",
            "naturalAluminum", "naturalAluminium", "antimatterMilligram", "antimatterGram", "strangeMatter", "coalGenerator", "electricFurnace",
            "unfinishedTank", "valvePart", "aquaRegia", "leatherSeal", "leatherSlimeSeal", "hambone", "slimeball", "clay", "enrichedUranium", "camoPaste",
            "antiBlock", "burntQuartz", "salmonRaw", "blockHopper", "blockEnderObsidian", "blockIcestone", "blockMagicWood", "blockEnderCore", "blockHeeEndium",
            "oreHeeEndPowder", "oreHeeStardust", "oreHeeIgneousRock", "oreHeeInstabilityOrb", "crystalPureFluix", "shardNether", "gemFluorite",
            "stickObsidian", "caveCrystal", "shardCrystal", "dyeCrystal","shardFire","shardWater","shardAir","shardEarth","ingotRefinedIron","blockMarble","ingotUnstable"}));
    private final Collection<String> mInvalidNames = new HashSet<String>(Arrays.asList(new String[]{"diamondShard", "redstoneRoot", "obsidianStick", "bloodstoneOre",
            "universalCable", "bronzeTube", "ironTube", "netherTube", "obbyTube", "infiniteBattery", "eliteBattery", "advancedBattery", "10kEUStore",
            "blueDye", "MonazitOre", "quartzCrystal", "whiteLuminiteCrystal", "darkStoneIngot", "invisiumIngot", "demoniteOrb", "enderGem", "starconiumGem",
            "osmoniumIngot", "tapaziteGem", "zectiumIngot", "foolsRubyGem", "rubyGem", "meteoriteGem", "adamiteShard", "sapphireGem", "copperIngot",
            "ironStick", "goldStick", "diamondStick", "reinforcedStick", "draconicStick", "emeraldStick", "copperStick", "tinStick", "silverStick",
            "bronzeStick", "steelStick", "leadStick", "manyullynStick", "arditeStick", "cobaltStick", "aluminiumStick", "alumiteStick", "oilsandsOre",
            "copperWire", "superconductorWire", "sulfuricAcid", "conveyorBelt", "ironWire", "aluminumWire", "aluminiumWire", "silverWire", "tinWire",
            "dustSiliconSmall", "AluminumOre", "plateHeavyT2", "blockWool", "alloyPlateEnergizedHardened", "gasWood", "alloyPlateEnergized", "SilverOre",
            "LeadOre", "TinOre", "CopperOre", "silverOre", "leadOre", "tinOre", "copperOre", "bauxiteOre", "HSLivingmetalIngot", "oilMoving", "oilStill",
            "oilBucket", "petroleumOre", "dieselFuel", "diamondNugget", "planks", "wood", "stick", "sticks", "naquadah", "obsidianRod", "stoneRod",
            "thaumiumRod", "steelRod", "netherrackRod", "woodRod", "ironRod", "cactusRod", "flintRod", "copperRod", "cobaltRod", "alumiteRod", "blueslimeRod",
            "arditeRod", "manyullynRod", "bronzeRod", "boneRod", "slimeRod", "redalloyBundled", "bluestoneBundled", "infusedteslatiteInsulated",
            "redalloyInsulated", "infusedteslatiteBundled"}));
    private final DateFormat mDateFormat = DateFormat.getInstance();
    public ArrayList<String> mBufferedPlayerActivity = new ArrayList<>();
    public boolean mHardcoreCables = false;
    public boolean mDisableVanillaOres = true;
    public boolean mNerfDustCrafting = true;
    public boolean mSortToTheEnd = true;
    public boolean mCraftingUnification = true;
    public boolean mInventoryUnification = true;
    public boolean mIncreaseDungeonLoot = true;
    public boolean mAxeWhenAdventure = true;
    public boolean mSurvivalIntoAdventure = false;
    public boolean mNerfedWoodPlank = true;
    public boolean mNerfedVanillaTools = true;
    public boolean mHardRock = false;
    public boolean mHungerEffect = true;
    public boolean mOnline = true;
    public boolean mIgnoreTcon = true;
    public boolean mDisableIC2Cables = false;
    public boolean mAchievements = true;
    public boolean mAE2Integration = true;
    public boolean mAE2Tunnel = true;
    public boolean mArcSmeltIntoAnnealed = true;
    private boolean isFirstServerWorldTick = true;
    private boolean mOreDictActivated = false;
    public boolean mChangeHarvestLevels = false;
    public boolean mNerfedCombs = true;
    public boolean mNerfedCrops = true;
    public boolean mGTBees = true;
    public boolean mHideUnusedOres = true;
    public boolean mHideRecyclingRecipes = true;
    public boolean mPollution = true;
    public boolean mExplosionItemDrop = false;
    public int mSkeletonsShootGTArrows = 16;
    public int mMaxEqualEntitiesAtOneSpot = 3;
    public int mFlintChance = 30;
    public int mItemDespawnTime = 6000;
    public int mUpgradeCount = 4;
    public int[] mHarvestLevel= new int[1000];
    public int mGraniteHavestLevel = 3;
    public int mMaxHarvestLevel = 7;
    public int mWireHeatingTicks = 4;
    public int mPollutionSmogLimit = 500000;
    public int mPollutionPoisonLimit = 750000;
    public int mPollutionVegetationLimit = 1000000;
    public int mPollutionSourRainLimit = 2000000;
    public final GT_UO_DimensionList mUndergroundOil = new GT_UO_DimensionList();
    public int mTicksUntilNextCraftSound = 0;
    private World mUniverse = null;
    public boolean mEnableAllMaterials = false;
    public boolean mEnableAllComponents = false;
    public boolean mAddGTRecipesToIC2Machines = true;
	public boolean mEnableCleanroom = true;
    public boolean mLowGravProcessing = false;
    public boolean mCropNeedBlock = true;
    public boolean mDisableOldChemicalRecipes = false;
    public boolean mAMHInteraction = true;
    public boolean mForceFreeFace = false;
    public boolean mEasierIVPlusCables = false;
    public boolean mBrickedBlastFurnace = true;
    public boolean mMixedOreOnlyYieldsTwoThirdsOfPureOre = false;
    public boolean enableBlackGraniteOres = true;
    public boolean enableRedGraniteOres = true;
    public boolean enableMarbleOres = true;
    public boolean enableBasaltOres = true;
    public boolean enableGCOres = true;
    public boolean enableUBOres = true;
    
    public GT_Proxy() {
        GameRegistry.registerFuelHandler(this);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.ORE_GEN_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
        GregTech_API.sThaumcraftCompat = (IThaumcraftCompat) GT_Utility.callConstructor("gregtech.common.GT_ThaumcraftCompat", 0, null, GT_Values.D1);
        for (FluidContainerRegistry.FluidContainerData tData : FluidContainerRegistry.getRegisteredFluidContainerData()) {
            onFluidContainerRegistration(new FluidContainerRegistry.FluidContainerRegisterEvent(tData));
        }
        for (String tOreName : OreDictionary.getOreNames()) {
            for (ItemStack aOreStack : OreDictionary.getOres(tOreName)) {
                registerOre(new OreDictionary.OreRegisterEvent(tOreName, aOreStack));
            }
        }
    }

    private static void registerRecipes(OreDictEventContainer aOre) {
        if ((aOre.mEvent.Ore == null) || (aOre.mEvent.Ore.getItem() == null)) {
            return;
        }
        if (aOre.mEvent.Ore.stackSize != 1) {
            aOre.mEvent.Ore.stackSize = 1;
        }
        if (aOre.mPrefix != null) {
            if (!aOre.mPrefix.isIgnored(aOre.mMaterial) && aOre.mModID != null && !aOre.mModID.equals("gregtech")) {
                aOre.mPrefix.processOre(aOre.mMaterial == null ? Materials._NULL : aOre.mMaterial, aOre.mEvent.Name, aOre.mModID, GT_Utility.copyAmount(1, aOre.mEvent.Ore));
            }
        }
    }

    public void onPreLoad() {
        GT_Log.out.println("GT_Mod: Preload-Phase started!");
        GT_Log.ore.println("GT_Mod: Preload-Phase started!");

        GregTech_API.sPreloadStarted = true;
        this.mIgnoreTcon = GregTech_API.sOPStuff.get(ConfigCategories.general, "ignoreTConstruct", true);
        this.mWireHeatingTicks = GregTech_API.sOPStuff.get(ConfigCategories.general, "WireHeatingTicks", 4);
        NetworkRegistry.INSTANCE.registerGuiHandler(GT_Values.GT, this);
        for (FluidContainerRegistry.FluidContainerData tData : FluidContainerRegistry.getRegisteredFluidContainerData()) {
            if ((tData.filledContainer.getItem() == Items.potionitem) && (tData.filledContainer.getItemDamage() == 0)) {
                tData.fluid.amount = 0;
                break;
            }
        }
        GT_Log.out.println("GT_Mod: Getting required Items of other Mods.");
        String aTextForestry = "Forestry";
        ItemList.FR_Lemon.set(GT_ModHandler.getModItem(aTextForestry, "fruits", 1, 3));
        ItemList.FR_Mulch.set(GT_ModHandler.getModItem(aTextForestry, "mulch", 1));
        ItemList.FR_Fertilizer.set(GT_ModHandler.getModItem(aTextForestry, "fertilizerCompound", 1));
        ItemList.FR_Compost.set(GT_ModHandler.getModItem(aTextForestry, "fertilizerBio", 1));
        ItemList.FR_Silk.set(GT_ModHandler.getModItem(aTextForestry, "craftingMaterial", 1, 2));
        ItemList.FR_Wax.set(GT_ModHandler.getModItem(aTextForestry, "beeswax", 1));
        ItemList.FR_WaxCapsule.set(GT_ModHandler.getModItem(aTextForestry, "waxCapsule", 1));
        ItemList.FR_RefractoryWax.set(GT_ModHandler.getModItem(aTextForestry, "refractoryWax", 1));
        ItemList.FR_RefractoryCapsule.set(GT_ModHandler.getModItem(aTextForestry, "refractoryEmpty", 1));
        ItemList.FR_Bee_Drone.set(GT_ModHandler.getModItem(aTextForestry, "beeDroneGE", 1));
        ItemList.FR_Bee_Princess.set(GT_ModHandler.getModItem(aTextForestry, "beePrincessGE", 1));
        ItemList.FR_Bee_Queen.set(GT_ModHandler.getModItem(aTextForestry, "beeQueenGE", 1));
        ItemList.FR_Tree_Sapling.set(GT_ModHandler.getModItem(aTextForestry, "sapling", 1, GT_ModHandler.getModItem(aTextForestry, "saplingGE", 1)));
        ItemList.FR_Butterfly.set(GT_ModHandler.getModItem(aTextForestry, "butterflyGE", 1));
        ItemList.FR_Larvae.set(GT_ModHandler.getModItem(aTextForestry, "beeLarvaeGE", 1));
        ItemList.FR_Serum.set(GT_ModHandler.getModItem(aTextForestry, "serumGE", 1));
        ItemList.FR_Caterpillar.set(GT_ModHandler.getModItem(aTextForestry, "caterpillarGE", 1));
        ItemList.FR_PollenFertile.set(GT_ModHandler.getModItem(aTextForestry, "pollenFertile", 1));
        ItemList.FR_Stick.set(GT_ModHandler.getModItem(aTextForestry, "oakStick", 1));
        ItemList.FR_Casing_Impregnated.set(GT_ModHandler.getModItem(aTextForestry, "impregnatedCasing", 1));
        ItemList.FR_Casing_Sturdy.set(GT_ModHandler.getModItem(aTextForestry, "sturdyMachine", 1));
        ItemList.FR_Casing_Hardened.set(GT_ModHandler.getModItem(aTextForestry, "hardenedMachine", 1));

        ItemList.Dye_Bonemeal.set(new ItemStack(Items.dye, 1, 15));
        ItemList.Dye_SquidInk.set(new ItemStack(Items.dye, 1, 0));
        ItemList.Dye_Cocoa.set(new ItemStack(Items.dye, 1, 3));

        ItemList.Book_Written_00.set(new ItemStack(Items.written_book, 1, 0));

        GregTech_API.sElectroHazmatList.add(new ItemStack(Items.chainmail_helmet, 1, 32767));
        GregTech_API.sElectroHazmatList.add(new ItemStack(Items.chainmail_chestplate, 1, 32767));
        GregTech_API.sElectroHazmatList.add(new ItemStack(Items.chainmail_leggings, 1, 32767));
        GregTech_API.sElectroHazmatList.add(new ItemStack(Items.chainmail_boots, 1, 32767));

        GT_ModHandler.sNonReplaceableItems.add(new ItemStack(Items.bow, 1, 32767));
        GT_ModHandler.sNonReplaceableItems.add(new ItemStack(Items.fishing_rod, 1, 32767));
        GT_ModHandler.sNonReplaceableItems.add(GT_ModHandler.getModItem("appliedenergistics2", "item.ToolCertusQuartzCuttingKnife", 1, 32767));
        GT_ModHandler.sNonReplaceableItems.add(GT_ModHandler.getModItem("appliedenergistics2", "item.ToolNetherQuartzCuttingKnife", 1, 32767));
        GT_ModHandler.sNonReplaceableItems.add(GT_ModHandler.getModItem(aTextForestry, "apiaristHelmet", 1, 32767));
        GT_ModHandler.sNonReplaceableItems.add(GT_ModHandler.getModItem(aTextForestry, "apiaristChest", 1, 32767));
        GT_ModHandler.sNonReplaceableItems.add(GT_ModHandler.getModItem(aTextForestry, "apiaristLegs", 1, 32767));
        GT_ModHandler.sNonReplaceableItems.add(GT_ModHandler.getModItem(aTextForestry, "apiaristBoots", 1, 32767));
        GT_ModHandler.sNonReplaceableItems.add(GT_ModHandler.getModItem(aTextForestry, "frameUntreated", 1, 32767));
        GT_ModHandler.sNonReplaceableItems.add(GT_ModHandler.getModItem(aTextForestry, "frameImpregnated", 1, 32767));
        GT_ModHandler.sNonReplaceableItems.add(GT_ModHandler.getModItem(aTextForestry, "frameProven", 1, 32767));
        GT_ModHandler.sNonReplaceableItems.add(GT_ModHandler.getModItem(aTextForestry, "waxCast", 1, 32767));
        GT_ModHandler.sNonReplaceableItems.add(GT_ModHandler.getModItem("GalacticraftCore", "item.sensorGlasses", 1, 32767));
        GT_ModHandler.sNonReplaceableItems.add(GT_ModHandler.getModItem("IC2NuclearControl", "ItemToolThermometer", 1, 32767));

        RecipeSorter.register("gregtech:shaped", GT_Shaped_Recipe.class, RecipeSorter.Category.SHAPED, "after:minecraft:shaped before:minecraft:shapeless");
        RecipeSorter.register("gregtech:shapeless", GT_Shapeless_Recipe.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
    }

    public void onLoad() {
        GT_Log.out.println("GT_Mod: Beginning Load-Phase.");
        GT_Log.ore.println("GT_Mod: Beginning Load-Phase.");

        GregTech_API.sLoadStarted = true;
        for (FluidContainerRegistry.FluidContainerData tData : FluidContainerRegistry.getRegisteredFluidContainerData()) {
            if ((tData.filledContainer.getItem() == Items.potionitem) && (tData.filledContainer.getItemDamage() == 0)) {
                tData.fluid.amount = 0;
                break;
            }
        }
        GT_LanguageManager.writePlaceholderStrings();
    }

    public static long tBits = GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.ONLY_ADD_IF_RESULT_IS_NOT_NULL | GT_ModHandler.RecipeBits.NOT_REMOVABLE;
    public void onPostLoad() {
        GT_Log.out.println("GT_Mod: Beginning PostLoad-Phase.");
        GT_Log.ore.println("GT_Mod: Beginning PostLoad-Phase.");
        GregTech_API.sPostloadStarted = true;
        for (FluidContainerRegistry.FluidContainerData tData : FluidContainerRegistry.getRegisteredFluidContainerData()) {
            if ((tData.filledContainer.getItem() == Items.potionitem) && (tData.filledContainer.getItemDamage() == 0)) {
                tData.fluid.amount = 0;
                break;
            }
        }
        GT_Log.out.println("GT_Mod: Adding Configs specific for MetaTileEntities");
        for (int i = 1; i < GregTech_API.METATILEENTITIES.length; i++) {
            for (; i < GregTech_API.METATILEENTITIES.length; i++) {
                if (GregTech_API.METATILEENTITIES[i] != null) {
                    GregTech_API.METATILEENTITIES[i].onConfigLoad(GregTech_API.sMachineFile);
                }
            }
        }
    }

    public void onServerStarting() {
        GT_Log.out.println("GT_Mod: ServerStarting-Phase started!");
        GT_Log.ore.println("GT_Mod: ServerStarting-Phase started!");

        this.mUniverse = null;
        this.isFirstServerWorldTick = true;
        for (FluidContainerRegistry.FluidContainerData tData : FluidContainerRegistry.getRegisteredFluidContainerData()) {
            if ((tData.filledContainer.getItem() == Items.potionitem) && (tData.filledContainer.getItemDamage() == 0)) {
                tData.fluid.amount = 0;
                break;
            }
        }
        for (int i = 1; i < GregTech_API.METATILEENTITIES.length; i++) {
            for (; i < GregTech_API.METATILEENTITIES.length; i++) {
                if (GregTech_API.METATILEENTITIES[i] != null) {
                    GregTech_API.METATILEENTITIES[i].onServerStart();
                }
            }
        }

        dimensionWiseChunkData.clear();//!!! IMPORTANT for map switching...
        dimensionWisePollution.clear();//!!! IMPORTANT for map switching...
    }

    public void onServerStarted() {
        GregTech_API.sWirelessRedstone.clear();

        GT_Log.out.println("GT_Mod: Cleaning up all OreDict Crafting Recipes, which have an empty List in them, since they are never meeting any Condition.");
        List tList = CraftingManager.getInstance().getRecipeList();
        for (int i = 0; i < tList.size(); i++) {
            if ((tList.get(i) instanceof ShapedOreRecipe)) {
                for (Object tObject : ((ShapedOreRecipe) tList.get(i)).getInput()) {
                    if (((tObject instanceof List)) && (((List) tObject).isEmpty())) {
                        tList.remove(i--);
                        break;
                    }
                }
            } else if ((tList.get(i) instanceof ShapelessOreRecipe)) {
                for (Object tObject : ((ShapelessOreRecipe) tList.get(i)).getInput()) {
                    if (((tObject instanceof List)) && (((List) tObject).isEmpty())) {
                        tList.remove(i--);
                        break;
                    }
                }
            }
        }
    }

    public void onServerStopping() {
        File tSaveDirectory = getSaveDirectory();
        GregTech_API.sWirelessRedstone.clear();
        if (tSaveDirectory != null) {
            for (int i = 1; i < GregTech_API.METATILEENTITIES.length; i++) {
                for (; i < GregTech_API.METATILEENTITIES.length; i++) {
                    if (GregTech_API.METATILEENTITIES[i] != null) {
                        GregTech_API.METATILEENTITIES[i].onWorldSave(tSaveDirectory);
                    }
                }
            }
        }
        this.mUniverse = null;
    }

    @SubscribeEvent
    public void onClientConnectedToServerEvent(FMLNetworkEvent.ClientConnectedToServerEvent aEvent) {
    }

    @SubscribeEvent
    public void onArrowNockEvent(ArrowNockEvent aEvent) {
        if ((!aEvent.isCanceled()) && (GT_Utility.isStackValid(aEvent.result)) && (GT_Utility.getProjectile(SubTag.PROJECTILE_ARROW, aEvent.entityPlayer.inventory) != null)) {
            aEvent.entityPlayer.setItemInUse(aEvent.result, aEvent.result.getItem().getMaxItemUseDuration(aEvent.result));
            aEvent.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onArrowLooseEvent(ArrowLooseEvent aEvent) {
        ItemStack aArrow = GT_Utility.getProjectile(SubTag.PROJECTILE_ARROW, aEvent.entityPlayer.inventory);
        if (!aEvent.isCanceled() && GT_Utility.isStackValid(aEvent.bow) && aArrow != null && (aEvent.bow.getItem() instanceof ItemBow)) {
            float tSpeed = aEvent.charge / 20.0F;
            tSpeed = (tSpeed * tSpeed + tSpeed * 2.0F) / 3.0F;
            if (tSpeed < 0.1D) {
                return;
            }
            if (tSpeed > 1.0D) {
                tSpeed = 1.0F;
            }
            EntityArrow tArrowEntity = ((IProjectileItem) aArrow.getItem()).getProjectile(SubTag.PROJECTILE_ARROW, aArrow, aEvent.entityPlayer.worldObj, aEvent.entityPlayer, tSpeed * 2.0F);
            if (tSpeed >= 1.0F) {
                tArrowEntity.setIsCritical(true);
            }
            int tLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, aEvent.bow);
            if (tLevel > 0) {
                tArrowEntity.setDamage(tArrowEntity.getDamage() + tLevel * 0.5D + 0.5D);
            }
            tLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, aEvent.bow);
            if (tLevel > 0) {
                tArrowEntity.setKnockbackStrength(tLevel);
            }
            tLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, aEvent.bow);
            if (tLevel > 0) {
                tArrowEntity.setFire(tLevel * 100);
            }
            aEvent.bow.damageItem(1, aEvent.entityPlayer);
            aEvent.bow.getItem();
            aEvent.entityPlayer.worldObj.playSoundAtEntity(aEvent.entityPlayer, "random.bow", 1.0F, 0.64893958288F + tSpeed * 0.5F);
            tArrowEntity.canBePickedUp = 1;
            if (!aEvent.entityPlayer.capabilities.isCreativeMode) {
                aArrow.stackSize -= 1;
            }
            if (aArrow.stackSize == 0) {
                GT_Utility.removeNullStacksFromInventory(aEvent.entityPlayer.inventory);
            }
            if (!aEvent.entityPlayer.worldObj.isRemote) {
                aEvent.entityPlayer.worldObj.spawnEntityInWorld(tArrowEntity);
            }
            aEvent.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onEndermanTeleportEvent(EnderTeleportEvent aEvent) {
        if (((aEvent.entityLiving instanceof EntityEnderman)) && (aEvent.entityLiving.getActivePotionEffect(Potion.weakness) != null)) {
            aEvent.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onEntitySpawningEvent(EntityJoinWorldEvent aEvent) {
        if (aEvent.entity != null && !aEvent.entity.worldObj.isRemote) {
            if (aEvent.entity instanceof EntityItem) {
                ((EntityItem) aEvent.entity).setEntityItemStack(GT_OreDictUnificator.get(((EntityItem) aEvent.entity).getEntityItem()));
            }
            if (this.mSkeletonsShootGTArrows > 0 && (aEvent.entity.getClass() == EntityArrow.class) && (aEvent.entity.worldObj.rand.nextInt(this.mSkeletonsShootGTArrows) == 0) && ((((EntityArrow) aEvent.entity).shootingEntity instanceof EntitySkeleton))) {
                aEvent.entity.worldObj.spawnEntityInWorld(new GT_Entity_Arrow((EntityArrow) aEvent.entity, new ItemStack(Items.arrow, 1)));
                aEvent.entity.setDead();
            }
        }
    }

    @SubscribeEvent
    public void onOreGenEvent(OreGenEvent.GenerateMinable aGenerator) {
        if (this.mDisableVanillaOres && (aGenerator.generator instanceof WorldGenMinable) && PREVENTED_ORES.contains(aGenerator.type)) {
            aGenerator.setResult(Result.DENY);
        }
    }

    private String getDataAndTime() {
        return this.mDateFormat.format(new Date());
    }

    @SubscribeEvent
    public void onPlayerInteraction(PlayerInteractEvent aEvent) {
        if ((aEvent.entityPlayer == null) || (aEvent.entityPlayer.worldObj == null) || (aEvent.action == null) || (aEvent.world.provider == null)) {
            return;
        }
        ItemStack aStack = aEvent.entityPlayer.getCurrentEquippedItem();
        if ((aStack != null) && (aEvent.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) && (aStack.getItem() == Items.flint_and_steel)) {
            if ((!aEvent.world.isRemote) && (!aEvent.entityPlayer.capabilities.isCreativeMode) && (aEvent.world.rand.nextInt(100) >= this.mFlintChance)) {
                aEvent.setCanceled(true);
                aStack.damageItem(1, aEvent.entityPlayer);
                if (aStack.getItemDamage() >= aStack.getMaxDamage()) {
                    aStack.stackSize -= 1;
                }
                if (aStack.stackSize <= 0) {
                    ForgeEventFactory.onPlayerDestroyItem(aEvent.entityPlayer, aStack);
                }
            }
        }
    }

    @SubscribeEvent
    public void onBlockHarvestingEvent(BlockEvent.HarvestDropsEvent aEvent) {
        if (aEvent.harvester != null) {
            ItemStack aStack = aEvent.harvester.getCurrentEquippedItem();
            if (aStack != null) {
                if ((aStack.getItem() instanceof GT_MetaGenerated_Tool)) {
                    ((GT_MetaGenerated_Tool) aStack.getItem()).onHarvestBlockEvent(aEvent.drops, aStack, aEvent.harvester, aEvent.block, aEvent.x, aEvent.y, aEvent.z, (byte) aEvent.blockMetadata, aEvent.fortuneLevel, aEvent.isSilkTouching, aEvent);
                }
                if (EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, aStack) > 2) {
                    for (ItemStack tDrop : aEvent.drops) {
                        ItemStack tSmeltingOutput = GT_ModHandler.getSmeltingOutput(tDrop, false, null);
                        if (tSmeltingOutput != null) {
                            tDrop.stackSize *= tSmeltingOutput.stackSize;
                            tSmeltingOutput.stackSize = tDrop.stackSize;
                            GT_Utility.setStack(tDrop, tSmeltingOutput);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void registerOre(OreDictionary.OreRegisterEvent aEvent) {
        ModContainer tContainer = Loader.instance().activeModContainer();
        String aMod = tContainer == null ? "UNKNOWN" : tContainer.getModId();
        String aOriginalMod = aMod;
        if (GT_OreDictUnificator.isRegisteringOres()) {
            aMod = "gregtech";
        } else if (aMod.equals("gregtech")) {
            aMod = "UNKNOWN";
        }
        if ((aEvent == null) || (aEvent.Ore == null) || (aEvent.Ore.getItem() == null) || (aEvent.Name == null) || (aEvent.Name.isEmpty()) || (aEvent.Name.replaceAll("_", "").length() - aEvent.Name.length() == 9)) {
            if (aOriginalMod.equals("gregtech")) {
                aOriginalMod = "UNKNOWN";
            }
            throw new IllegalArgumentException(aOriginalMod + " did something very bad! The registration is too invalid to even be shown properly. This happens only if you register null, invalid Items, empty Strings or even nonexisting Events to the OreDict.");
        }
        aEvent.Ore.stackSize = 1;
        if (this.mIgnoreTcon || aEvent.Ore.getUnlocalizedName().startsWith("item.oreberry")) {
            if (aOriginalMod.toLowerCase(Locale.ENGLISH).contains("tconstruct") || (aOriginalMod.toLowerCase(Locale.ENGLISH).contains("natura") && !aOriginalMod.toLowerCase(Locale.ENGLISH).contains("natural"))) {
                return;
            }
        }
        if (((aEvent.Ore.getItem() instanceof ItemBlock)) || (GT_Utility.getBlockFromStack(aEvent.Ore) != Blocks.air)) {
            GT_OreDictUnificator.addToBlacklist(aEvent.Ore);
        }
        this.mRegisteredOres.add(aEvent.Ore);
        if (this.mIgnoredItems.contains(aEvent.Name)) {
            /*if ((aEvent.Name.startsWith("item"))) {
                if (aEvent.Name.equals("itemCopperWire")) {
                    MatUnifier.registerOre(OreDictNames.craftingWireCopper, aEvent.Ore);
                }
                if (aEvent.Name.equals("itemRubber")) {
                    MatUnifier.registerOre(OrePrefixes.ingot, Materials.Rubber, aEvent.Ore);
                }
                return;
            }*/
        } else if (this.mIgnoredNames.contains(aEvent.Name)){
            return;
        } else if (this.mInvalidNames.contains(aEvent.Name)) {
            return;
        } else if (aEvent.Name.contains("|") || aEvent.Name.contains("*") || aEvent.Name.contains(":") || aEvent.Name.contains(".") || aEvent.Name.contains("$")) {
            return;
        } else if (aEvent.Name.equals("stone")) {
            GT_OreDictUnificator.registerOre("stoneSmooth", aEvent.Ore);
            return;
        } else if (aEvent.Name.equals("cobblestone")) {
            GT_OreDictUnificator.registerOre("stoneCobble", aEvent.Ore);
            return;
        } else if (aEvent.Name.equals("copperWire")) {
            GT_OreDictUnificator.registerOre(OreDictNames.craftingWireCopper, aEvent.Ore);
        } else if (aEvent.Name.equals("sheetPlastic")) {
            GT_OreDictUnificator.registerOre(OrePrefixes.plate, Materials.Plastic, aEvent.Ore);
        }

        OrePrefixes aPrefix = OrePrefixes.getOrePrefix(aEvent.Name);
        Materials aMaterial = Materials._NULL;
        if ((aPrefix == OrePrefixes.nugget) && (aMod.equals("Thaumcraft")) && (aEvent.Ore.getItem().getUnlocalizedName().contains("ItemResource"))) {
            return;
        }
        if (aPrefix == null) {
            if (aEvent.Name.toLowerCase().equals(aEvent.Name)) {
                return;
            } else if (aEvent.Name.toUpperCase().equals(aEvent.Name)) {
                return;
            } else if (Character.isUpperCase(aEvent.Name.charAt(0))) {
                return;
            }
        } else {
            if (aPrefix.mDontUnificateActively) {
                GT_OreDictUnificator.addToBlacklist(aEvent.Ore);
            }
            if (aPrefix != aPrefix.mPrefixInto) {
                String tNewName = aEvent.Name.replaceFirst(aPrefix.toString(), aPrefix.mPrefixInto.toString());
                GT_OreDictUnificator.registerOre(tNewName, aEvent.Ore);
                return;
            }
            String tName = aEvent.Name.replaceFirst(aPrefix.toString(), "");
            if (tName.length() > 0) {
                char firstChar = tName.charAt(0);
                if (Character.isUpperCase(firstChar) || Character.isLowerCase(firstChar) || firstChar == '_') {
                    if (aPrefix.mIsMaterialBased) {
                        aMaterial = Materials.get(tName);
                        if (aMaterial != aMaterial.mMaterialInto) {
                            GT_OreDictUnificator.registerOre(aPrefix, aMaterial.mMaterialInto, aEvent.Ore);
                            return;
                        }
                        if (!aPrefix.isIgnored(aMaterial)) {
                            aPrefix.add(GT_Utility.copyAmount(1, aEvent.Ore));
                        }
                        if (aMaterial != Materials._NULL) {
                            Materials tReRegisteredMaterial;
                            for (Iterator i$ = aMaterial.mOreReRegistrations.iterator(); i$.hasNext(); GT_OreDictUnificator.registerOre(aPrefix,
                                    tReRegisteredMaterial, aEvent.Ore)) {
                                tReRegisteredMaterial = (Materials) i$.next();
                            }
                            aMaterial.add(GT_Utility.copyAmount(1, aEvent.Ore));

                            if (GregTech_API.sThaumcraftCompat != null && aPrefix.doGenerateItem(aMaterial) && !aPrefix.isIgnored(aMaterial)) {
                                List<TC_AspectStack> tAspects = new ArrayList<TC_AspectStack>();
                                for (TC_AspectStack tAspect : aPrefix.mAspects) tAspect.addToAspectList(tAspects);
                                if (aPrefix.mMaterialAmount >= 3628800 || aPrefix.mMaterialAmount < 0) for (TC_AspectStack tAspect : aMaterial.mAspects) tAspect.addToAspectList(tAspects);
                                GregTech_API.sThaumcraftCompat.registerThaumcraftAspectsToItem(GT_Utility.copyAmount(1, aEvent.Ore), tAspects, aEvent.Name);
                            }

                            switch (aPrefix) {
                                case gem:
                                    if (aMaterial == Materials.Lapis || aMaterial == Materials.Sodalite) {
                                        GT_OreDictUnificator.registerOre(Dyes.dyeBlue, aEvent.Ore);
                                    } else if (aMaterial == Materials.Lazurite) {
                                        GT_OreDictUnificator.registerOre(Dyes.dyeCyan, aEvent.Ore);
                                    } else if (aMaterial == Materials.InfusedAir || aMaterial == Materials.InfusedWater || aMaterial == Materials.InfusedFire || aMaterial == Materials.InfusedEarth || aMaterial == Materials.InfusedOrder || aMaterial == Materials.InfusedEntropy) {
                                        GT_OreDictUnificator.registerOre(aMaterial.mName.replaceFirst("Infused", "shard"), aEvent.Ore);
                                    } else if (aMaterial == Materials.CertusQuartz || aMaterial == Materials.NetherQuartz) {
                                        GT_OreDictUnificator.registerOre(OrePrefixes.item.get(aMaterial), aEvent.Ore);
                                        GT_OreDictUnificator.registerOre(OreDictNames.craftingQuartz, aEvent.Ore);
                                    } else if (aMaterial == Materials.Fluix || aMaterial == Materials.Quartz || aMaterial == Materials.Quartzite) {
                                        GT_OreDictUnificator.registerOre(OreDictNames.craftingQuartz, aEvent.Ore);
                                    }
                                    break;
                                case plate:
                                    if (aMaterial == Materials.Plastic || aMaterial == Materials.Rubber) {
                                        GT_OreDictUnificator.registerOre(OrePrefixes.sheet, aMaterial, aEvent.Ore);
                                    } else if (aMaterial == Materials.Silicon) {
                                        GT_OreDictUnificator.registerOre(OrePrefixes.item, aMaterial, aEvent.Ore);
                                    } else if (aMaterial == Materials.Wood) {
                                        GT_OreDictUnificator.addToBlacklist(aEvent.Ore);
                                        GT_OreDictUnificator.registerOre(OrePrefixes.plank, aMaterial, aEvent.Ore);
                                    }
                                    break;
                                case stick:
                                    if (!GT_RecipeRegistrator.sRodMaterialList.contains(aMaterial)) {
                                        GT_RecipeRegistrator.sRodMaterialList.add(aMaterial);
                                    } else if (aMaterial == Materials.Wood) {
                                        GT_OreDictUnificator.addToBlacklist(aEvent.Ore);
                                    }
                                    break;
                                case dust:
                                    if (aMaterial == Materials.Salt) {
                                        GT_OreDictUnificator.registerOre("itemSalt", aEvent.Ore);
                                    } else if (aMaterial == Materials.Wood) {
                                        GT_OreDictUnificator.registerOre("pulpWood", aEvent.Ore);
                                    } else if (aMaterial == Materials.Wheat) {
                                        GT_OreDictUnificator.registerOre("foodFlour", aEvent.Ore);
                                    }
                                    break;
                                case ingot:
                                    if (aMaterial == Materials.Rubber) {
                                        GT_OreDictUnificator.registerOre("itemRubber", aEvent.Ore);
                                    } else if ((aMaterial == Materials.Brass) && (aEvent.Ore.getItemDamage() == 2) && (aEvent.Ore.getUnlocalizedName().equals("item.ingotBrass")) && (new ItemStack(aEvent.Ore.getItem(), 1, 0).getUnlocalizedName().contains("red"))) {
                                        GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.RedAlloy, new ItemStack(aEvent.Ore.getItem(), 1, 0));
                                        GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.Brass, new ItemStack(aEvent.Ore.getItem(), 1, 2));
                                        GT_Values.RA.addCutterRecipe(new ItemStack(aEvent.Ore.getItem(), 1, 3), new ItemStack(aEvent.Ore.getItem(), 16, 4), null, 400, 8);
                                    }
                                    break;
                                default:
                                    break;
                            }
                            if (aPrefix.mIsUnificatable && !aMaterial.mUnificatable) {
                                return;
                            }
                        } else {
                            for (Dyes tDye : Dyes.VALUES) {
                                if (aEvent.Name.endsWith(tDye.name().replaceFirst("dye", ""))) {
                                    GT_OreDictUnificator.addToBlacklist(aEvent.Ore);
                                    return;
                                }
                            }
                            return;
                        }
                    } else {
                        aPrefix.add(GT_Utility.copyAmount(1, aEvent.Ore));
                    }
                }
            } else if (aPrefix.mIsSelfReferencing) {
                aPrefix.add(GT_Utility.copyAmount(1, aEvent.Ore));
            } else {
                return;
            }
            switch (aPrefix) {
                case dye:
                    if (GT_Utility.isStringValid(tName)) {
                        GT_OreDictUnificator.registerOre(OrePrefixes.dye, aEvent.Ore);
                    }
                    break;
                case stoneSmooth:
                    GT_OreDictUnificator.registerOre("stone", aEvent.Ore);
                    break;
                case stoneCobble:
                    GT_OreDictUnificator.registerOre("cobblestone", aEvent.Ore);
                    break;
                case plank:
                    if (tName.equals("Wood")) {
                        GT_OreDictUnificator.addItemData(aEvent.Ore, new ItemData(Materials.Wood, 3628800));
                    }
                    break;
                case slab:
                    if (tName.equals("Wood")) {
                        GT_OreDictUnificator.addItemData(aEvent.Ore, new ItemData(Materials.Wood, 1814400));
                    }
                    break;
                case sheet:
                    if (tName.equals("Plastic")) {
                        GT_OreDictUnificator.registerOre(OrePrefixes.plate, Materials.Plastic, aEvent.Ore);
                    } else if (tName.equals("Rubber")) {
                        GT_OreDictUnificator.registerOre(OrePrefixes.plate, Materials.Rubber, aEvent.Ore);
                    }
                    break;
                case crafting:
                    if (tName.equals("ToolSolderingMetal")) {
                        GregTech_API.registerSolderingMetal(aEvent.Ore);
                    } else if (tName.equals("IndustrialDiamond")) {
                        GT_OreDictUnificator.addToBlacklist(aEvent.Ore);
                    } else if (tName.equals("WireCopper")) {
                        GT_OreDictUnificator.registerOre(OrePrefixes.wire, Materials.Copper, aEvent.Ore);
                    }
                    break;
                case wood:
                    if (tName.equals("Rubber")) {
                        GT_OreDictUnificator.registerOre("logRubber", aEvent.Ore);
                    }
                    break;
                default:
                    break;
            }
        }

        OreDictEventContainer tOre = new OreDictEventContainer(aEvent, aPrefix, aMaterial, aMod);
        if ((!this.mOreDictActivated) || (!GregTech_API.sUnificationEntriesRegistered)) {
            this.mEvents.add(tOre);
        } else {
            this.mEvents.clear();
        }
        if (this.mOreDictActivated) {
            registerRecipes(tOre);
        }
    }

    @SubscribeEvent
    public void onFluidContainerRegistration(FluidContainerRegistry.FluidContainerRegisterEvent aFluidEvent) {
        if ((aFluidEvent.data.filledContainer.getItem() == Items.potionitem) && (aFluidEvent.data.filledContainer.getItemDamage() == 0)) {
            aFluidEvent.data.fluid.amount = 0;
        }
        GT_OreDictUnificator.addToBlacklist(aFluidEvent.data.emptyContainer);
        GT_OreDictUnificator.addToBlacklist(aFluidEvent.data.filledContainer);
        GT_Utility.addFluidContainerData(aFluidEvent.data);
    }

    @SubscribeEvent
    public void onServerTickEvent(TickEvent.ServerTickEvent aEvent) {
    }

    @SubscribeEvent
    public void onWorldTickEvent(TickEvent.WorldTickEvent aEvent) {
    	if(aEvent.world.provider.dimensionId == 0) {
            mTicksUntilNextCraftSound--;
        }
        if (aEvent.side.isServer()) {
            if (this.mUniverse == null) {
                this.mUniverse = aEvent.world;
            }         
            if (this.isFirstServerWorldTick) {
                File tSaveDiretory = getSaveDirectory();
                if (tSaveDiretory != null) {
                    this.isFirstServerWorldTick = false;
                    for (IMetaTileEntity tMetaTileEntity : GregTech_API.METATILEENTITIES) {
                        if (tMetaTileEntity != null) {
                            tMetaTileEntity.onWorldLoad(tSaveDiretory);
                        }
                    }
                }
            }
            if ((aEvent.world.getTotalWorldTime() % 100L == 0L) && ((this.mItemDespawnTime != 6000) || (this.mMaxEqualEntitiesAtOneSpot > 0))) {
                for (int i = 0; i < aEvent.world.loadedEntityList.size(); i++) {
                    if ((aEvent.world.loadedEntityList.get(i) instanceof Entity)) {
                        Entity tEntity = (Entity) aEvent.world.loadedEntityList.get(i);
                        if (((tEntity instanceof EntityItem)) && (this.mItemDespawnTime != 6000) && (((EntityItem) tEntity).lifespan == 6000)) {
                            ((EntityItem) tEntity).lifespan = this.mItemDespawnTime;
                        } else if (((tEntity instanceof EntityLivingBase)) && (this.mMaxEqualEntitiesAtOneSpot > 0) && (!(tEntity instanceof EntityPlayer)) && (tEntity.canBePushed()) && (((EntityLivingBase) tEntity).getHealth() > 0.0F)) {
                            List tList = tEntity.worldObj.getEntitiesWithinAABBExcludingEntity(tEntity, tEntity.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
                            Class tClass = tEntity.getClass();
                            int tEntityCount = 1;
                            if (tList != null) {
                                for (int j = 0; j < tList.size(); j++) {
                                    if ((tList.get(j) != null) && (tList.get(j).getClass() == tClass)) {
                                        tEntityCount++;
                                    }
                                }
                            }
                            if (tEntityCount > this.mMaxEqualEntitiesAtOneSpot) {
                                tEntity.attackEntityFrom(DamageSource.inWall, tEntityCount - this.mMaxEqualEntitiesAtOneSpot);
                            }
                        }
                    }
                }
            }
            GT_Pollution.onWorldTick(aEvent);
        }
    }

    @SubscribeEvent
    public void onPlayerTickEventServer(TickEvent.PlayerTickEvent aEvent) {
        if ((aEvent.side.isServer()) && (aEvent.phase == TickEvent.Phase.END) && (!aEvent.player.isDead)) {
            if ((aEvent.player.ticksExisted % 200 == 0) && (aEvent.player.capabilities.allowEdit) && (!aEvent.player.capabilities.isCreativeMode) && (this.mSurvivalIntoAdventure)) {
                aEvent.player.setGameType(GameType.ADVENTURE);
                aEvent.player.capabilities.allowEdit = false;
                if (this.mAxeWhenAdventure) {
                    GT_Utility.sendChatToPlayer(aEvent.player, GT_LanguageManager.addStringLocalization("Interaction_DESCRIPTION_Index_097", "It's dangerous to go alone! Take this.", false));
                    aEvent.player.worldObj.spawnEntityInWorld(new EntityItem(aEvent.player.worldObj, aEvent.player.posX, aEvent.player.posY, aEvent.player.posZ, GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.AXE, 1, Materials.Flint, Materials.Wood, null)));
                }
            }
            boolean tHungerEffect = (this.mHungerEffect) && (aEvent.player.ticksExisted % 2400 == 1200);
            if (aEvent.player.ticksExisted % 120 == 0) {
                int tCount = 64;
                for (int i = 0; i < 36; i++) {
                    ItemStack tStack;
                    if ((tStack = aEvent.player.inventory.getStackInSlot(i)) != null) {
                        if (!aEvent.player.capabilities.isCreativeMode) {
                            GT_Utility.applyRadioactivity(aEvent.player, GT_Utility.getRadioactivityLevel(tStack), tStack.stackSize);
                            float tHeat = GT_Utility.getHeatDamageFromItem(tStack);
                            if (tHeat != 0.0F) {
                                if (tHeat > 0.0F) {
                                    GT_Utility.applyHeatDamage(aEvent.player, tHeat);
                                } else {
                                    GT_Utility.applyFrostDamage(aEvent.player, -tHeat);
                                }
                            }
                        }
                        if (tHungerEffect) {
                            tCount += tStack.stackSize * 64 / Math.max(1, tStack.getMaxStackSize());
                        }
                        if (this.mInventoryUnification) {
                            GT_OreDictUnificator.setStack(true, tStack);
                        }
                    }
                }
                for (int i = 0; i < 4; i++) {
                    ItemStack tStack;
                    if ((tStack = aEvent.player.inventory.armorInventory[i]) != null) {
                        if (!aEvent.player.capabilities.isCreativeMode) {
                            GT_Utility.applyRadioactivity(aEvent.player, GT_Utility.getRadioactivityLevel(tStack), tStack.stackSize);
                            float tHeat = GT_Utility.getHeatDamageFromItem(tStack);
                            if (tHeat != 0.0F) {
                                if (tHeat > 0.0F) {
                                    GT_Utility.applyHeatDamage(aEvent.player, tHeat);
                                } else {
                                    GT_Utility.applyFrostDamage(aEvent.player, -tHeat);
                                }
                            }
                        }
                        if (tHungerEffect) {
                            tCount += 256;
                        }
                    }
                }
                if (tHungerEffect) {
                    aEvent.player.addExhaustion(Math.max(1.0F, tCount / 666.6F));
                }
            }
            if (aEvent.player.ticksExisted % 10 == 0) {
                int tPollution = GT_Pollution.getPollution(new ChunkCoordIntPair(aEvent.player.chunkCoordX,aEvent.player.chunkCoordZ), aEvent.player.dimension);
                if(aEvent.player instanceof EntityPlayerMP) {
                    GT_Values.NW.sendToPlayer(new GT_Packet_Pollution(tPollution), (EntityPlayerMP) aEvent.player);
                }
            }
        }
    }

    public Object getServerGuiElement(int aID, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ) {
        if (aID >= 1000) {
            int ID = aID - 1000;
            switch(ID) {
                case 0:
                    return new ContainerBasicArmor(aPlayer, new InventoryArmor(ModularArmor_Item.class, aPlayer.getCurrentEquippedItem()));
                case 1:
                    return new ContainerElectricArmor1(aPlayer, new InventoryArmor(ModularArmor_Item.class, aPlayer.getCurrentEquippedItem()));
                case 2:
                    return new ContainerElectricArmor1(aPlayer, new InventoryArmor(ModularArmor_Item.class, aPlayer.getCurrentEquippedItem()));
                default:
                    return getRightItem(aPlayer, ID);
            }
        }
        if (aID >= 100) {
        	int tSlot = aID / 100;
            int ID = aID % 100;
            switch(ID) {
                case 0:
                    return new ContainerBasicArmor(aPlayer, new InventoryArmor(ModularArmor_Item.class, aPlayer.getEquipmentInSlot(tSlot)));
                case 1:
                    return new ContainerElectricArmor1(aPlayer, new InventoryArmor(ModularArmor_Item.class, aPlayer.getEquipmentInSlot(tSlot)));
                case 2:
                    return new ContainerElectricArmor1(aPlayer, new InventoryArmor(ModularArmor_Item.class, aPlayer.getEquipmentInSlot(tSlot)));
                default:
                    return getRightItem(aPlayer, ID);
            }
        }
        TileEntity tTileEntity = aWorld.getTileEntity(aX, aY, aZ);
        if ((tTileEntity instanceof IGregTechTileEntity)) {
            IMetaTileEntity tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity();
            if (tMetaTileEntity != null) {
                return tMetaTileEntity.getServerGUI(aID, aPlayer.inventory, (IGregTechTileEntity) tTileEntity);
            }
        }
        return null;
    }

    public Object getRightItem(EntityPlayer player, int ID){
        ItemStack mStack = player.getEquipmentInSlot(ID / 100);
        if (mStack == null || !(mStack.getItem() instanceof ModularArmor_Item)) {
            return null;
        }
        switch(ID % 100) {
            case 0:
                return new ContainerBasicArmor(player, new InventoryArmor(ModularArmor_Item.class, mStack));
            case 1:
                return new ContainerElectricArmor1(player, new InventoryArmor(ModularArmor_Item.class, mStack));
            case 2:
                return new ContainerElectricArmor1(player, new InventoryArmor(ModularArmor_Item.class, mStack));
        }
        return null;
    }

    public Object getClientGuiElement(int aID, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ) {
        if (aID >= 1000) {
            int ID = aID - 1000;
            switch(ID) {
                case 0:
                    return new GuiModularArmor(new ContainerBasicArmor(aPlayer, new InventoryArmor(ModularArmor_Item.class, aPlayer.getCurrentEquippedItem())), aPlayer);
                case 1:
                    return new GuiElectricArmor1(new ContainerElectricArmor1(aPlayer, new InventoryArmor(ModularArmor_Item.class, aPlayer.getCurrentEquippedItem())), aPlayer);
                case 2:
                    return new GuiElectricArmor1(new ContainerElectricArmor1(aPlayer, new InventoryArmor(ModularArmor_Item.class, aPlayer.getCurrentEquippedItem())), aPlayer);
                default:
                    return getRightItemGui(aPlayer, ID);
            }
        }
        if( aID >= 100) {
        	int tSlot = aID / 100;
            int ID = aID % 100;
            switch(ID) {
            case 0:
                return new GuiModularArmor(new ContainerBasicArmor(aPlayer, new InventoryArmor(ModularArmor_Item.class, aPlayer.getEquipmentInSlot(tSlot))), aPlayer);
            case 1:
                return new GuiElectricArmor1(new ContainerElectricArmor1(aPlayer, new InventoryArmor(ModularArmor_Item.class, aPlayer.getEquipmentInSlot(tSlot))), aPlayer);
            case 2:
                return new GuiElectricArmor1(new ContainerElectricArmor1(aPlayer, new InventoryArmor(ModularArmor_Item.class, aPlayer.getEquipmentInSlot(tSlot))), aPlayer);
            default:
                return getRightItem(aPlayer, ID);
            }
        }
        TileEntity tTileEntity = aWorld.getTileEntity(aX, aY, aZ);
        if ((tTileEntity instanceof IGregTechTileEntity)) {
            IMetaTileEntity tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity();
            if (tMetaTileEntity != null) {
                return tMetaTileEntity.getClientGUI(aID, aPlayer.inventory, (IGregTechTileEntity) tTileEntity);
            }
        }
        return null;
    }

    public Object getRightItemGui(EntityPlayer player, int ID){
        ItemStack mStack = player.getEquipmentInSlot(ID / 100);
        if (mStack == null || !(mStack.getItem() instanceof ModularArmor_Item)) {
            return null;
        }
        switch(ID % 100){
            case 0:
                return new GuiModularArmor(new ContainerBasicArmor(player, new InventoryArmor(ModularArmor_Item.class, mStack)),player);
            case 1:
                return new GuiElectricArmor1(new ContainerElectricArmor1(player, new InventoryArmor(ModularArmor_Item.class, mStack)), player);
            case 2:
                return new GuiElectricArmor1(new ContainerElectricArmor1(player, new InventoryArmor(ModularArmor_Item.class, mStack)), player);
        }
        return null;
    }

    public int getBurnTime(ItemStack aFuel) {
        if ((aFuel == null) || (aFuel.getItem() == null)) {
            return 0;
        }
        int rFuelValue = 0;
        if ((aFuel.getItem() instanceof GT_MetaGenerated_Item)) {
            Short tFuelValue = ((GT_MetaGenerated_Item) aFuel.getItem()).mBurnValues.get((short) aFuel.getItemDamage());
            if (tFuelValue != null) {
                rFuelValue = Math.max(rFuelValue, tFuelValue);
            }
        }
        NBTTagCompound tNBT = aFuel.getTagCompound();
        if (tNBT != null) {
            short tValue = tNBT.getShort("GT.ItemFuelValue");
            rFuelValue = Math.max(rFuelValue, tValue);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "gemSodium")) {
            rFuelValue =   Math.max(rFuelValue, 4000);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "crushedSodium")) {
            rFuelValue =   Math.max(rFuelValue, 4000);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustImpureSodium")) {
            rFuelValue =   Math.max(rFuelValue, 4000);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustSodium")) {
            rFuelValue =   Math.max(rFuelValue, 400);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustSmallSodium")) {
            rFuelValue =   Math.max(rFuelValue, 100);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustTinySodium")) {
            rFuelValue =   Math.max(rFuelValue, 44);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustSulfur")) {
            rFuelValue =   Math.max(rFuelValue, 1600);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "gemLithium")) {
            rFuelValue =   Math.max(rFuelValue, 6000);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "crushedLithium")) {
            rFuelValue =   Math.max(rFuelValue, 6000);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustImpureLithium")) {
            rFuelValue =   Math.max(rFuelValue, 6000);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustLithium")) {
            rFuelValue =   Math.max(rFuelValue, 6000);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustSmallLithium")) {
            rFuelValue =   Math.max(rFuelValue, 2000);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustTinyLithium")) {
            rFuelValue =   Math.max(rFuelValue, 888);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "gemCaesium")) {
            rFuelValue =   Math.max(rFuelValue, 6000);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "crushedCaesium")) {
            rFuelValue =   Math.max(rFuelValue, 6000);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustImpureCaesium")) {
            rFuelValue =   Math.max(rFuelValue, 6000);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustCaesium")) {
            rFuelValue =   Math.max(rFuelValue, 6000);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustSmallCaesium")) {
            rFuelValue =   Math.max(rFuelValue, 2000);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustTinyCaesium")) {
            rFuelValue =   Math.max(rFuelValue, 888);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "gemLignite")) {
            rFuelValue =   Math.max(rFuelValue, 1200);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "crushedLignite")) {
            rFuelValue =   Math.max(rFuelValue, 1200);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustImpureLignite")) {
            rFuelValue =   Math.max(rFuelValue, 1200);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustLignite")) {
            rFuelValue =   Math.max(rFuelValue, 1200);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustSmallLignite")) {
            rFuelValue =   Math.max(rFuelValue, 375);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustTinyLignite")) {
            rFuelValue =   Math.max(rFuelValue, 166);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "gemCoal")) {
            rFuelValue =   Math.max(rFuelValue, 1600);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "crushedCoal")) {
            rFuelValue =   Math.max(rFuelValue, 1600);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustImpureCoal")) {
            rFuelValue =   Math.max(rFuelValue, 1600);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustCoal")) {
            rFuelValue =   Math.max(rFuelValue, 1600);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustSmallCoal")) {
            rFuelValue =   Math.max(rFuelValue, 400);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustTinyCoal")) {
            rFuelValue =   Math.max(rFuelValue, 177);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "gemCharcoal")) {
            rFuelValue =   Math.max(rFuelValue, 1600);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "crushedCharcoal")) {
            rFuelValue =   Math.max(rFuelValue, 1600);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustImpureCharcoal")) {
            rFuelValue =   Math.max(rFuelValue, 1600);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustCharcoal")) {
            rFuelValue =   Math.max(rFuelValue, 1600);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustSmallCharcoal")) {
            rFuelValue =   Math.max(rFuelValue, 400);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustTinyCharcoal")) {
            rFuelValue =   Math.max(rFuelValue, 177);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustWood")) {
            rFuelValue =   Math.max(rFuelValue, 100);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustSmallWood")) {
            rFuelValue =   Math.max(rFuelValue, 25);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustTinyWood")) {
            rFuelValue =   Math.max(rFuelValue, 11);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "plateWood")) {
            rFuelValue =   Math.min(rFuelValue, 300);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "blockLignite")) {
            rFuelValue =   Math.max(rFuelValue, 12000);
        } else if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "blockCharcoal")) {
            rFuelValue =   Math.max(rFuelValue, 16000);
        } else if (GT_Utility.areStacksEqual(aFuel, new ItemStack(Blocks.wooden_button, 1))) {
            rFuelValue =   Math.max(rFuelValue, 150);
        } else if (GT_Utility.areStacksEqual(aFuel, new ItemStack(Blocks.ladder, 1))) {
            rFuelValue =   Math.max(rFuelValue, 100);
        } else if (GT_Utility.areStacksEqual(aFuel, new ItemStack(Items.sign, 1))) {
            rFuelValue =   Math.max(rFuelValue, 600);
        } else if (GT_Utility.areStacksEqual(aFuel, new ItemStack(Items.wooden_door, 1))) {
            rFuelValue =   Math.max(rFuelValue, 600);
        } else if (GT_Utility.areStacksEqual(aFuel, ItemList.Block_MSSFUEL.get(1))) {
            rFuelValue = Math.max(rFuelValue, 150000);
        }if (GT_Utility.areStacksEqual(aFuel, ItemList.Block_SSFUEL.get(1))) {
            rFuelValue = Math.max(rFuelValue, 100000);
        }

        return rFuelValue;
    }

    public Fluid addAutoGeneratedCorrespondingFluid(Materials aMaterial){
        return addFluid(aMaterial.mName.toLowerCase(Locale.ENGLISH), "molten.autogenerated", aMaterial.mDefaultLocalName, aMaterial, aMaterial.mRGBa, 1, aMaterial.getLiquidTemperature(), GT_OreDictUnificator.get(OrePrefixes.cell, aMaterial, 1), ItemList.Cell_Empty.get(1), 1000);
    }

	public Fluid addAutoGeneratedCorrespondingGas(Materials aMaterial) {
		return addFluid(aMaterial.mName.toLowerCase(Locale.ENGLISH), "molten.autogenerated", aMaterial.mDefaultLocalName, aMaterial, aMaterial.mRGBa, 2, aMaterial.getGasTemperature(), GT_OreDictUnificator.get(OrePrefixes.cell, aMaterial, 1), ItemList.Cell_Empty.get(1), 1000);
	}
    
    public Fluid addAutogeneratedMoltenFluid(Materials aMaterial) {
        return addFluid("molten." + aMaterial.mName.toLowerCase(Locale.ENGLISH), "molten.autogenerated", "Molten " + aMaterial.mDefaultLocalName, aMaterial, aMaterial.mMoltenRGBa, 4, aMaterial.mMeltingPoint <= 0 ? 1000 : aMaterial.mMeltingPoint, null, null, 0);
    }

    public Fluid addAutogeneratedPlasmaFluid(Materials aMaterial) {
        return addFluid("plasma." + aMaterial.mName.toLowerCase(Locale.ENGLISH), "plasma.autogenerated", aMaterial.mDefaultLocalName + " Plasma", aMaterial, aMaterial.mMoltenRGBa, 3, 10000, GT_OreDictUnificator.get(OrePrefixes.cellPlasma, aMaterial, 1), ItemList.Cell_Empty.get(1), 1000);
    }
    
    public void addAutoGeneratedHydroCrackedFluids(Materials aMaterial){
    	Fluid[] crackedFluids = new Fluid[3];
    	String[] prefixes = {"lightlyhydrocracked.", "moderatelyhydrocracked.", "severelyhydrocracked."};
    	String[] localPrefixes = {"Lightly Hydro-Cracked ", "Moderately Hydro-Cracked ", "Severely Hydro-Cracked "};
    	GT_Fluid uncrackedFluid = null;
    	if (aMaterial.mFluid != null) {
    		uncrackedFluid = (GT_Fluid) aMaterial.mFluid;
    	} else if (aMaterial.mGas != null) {
    		uncrackedFluid = (GT_Fluid) aMaterial.mGas;
    	}
    	for (int i = 0; i < 3; i++) {
    		crackedFluids[i] = addFluid(prefixes[i] + aMaterial.mName.toLowerCase(Locale.ENGLISH),
                    uncrackedFluid.mTextureName,
                    localPrefixes[i] + aMaterial.mDefaultLocalName, null, aMaterial.mRGBa, 2, 775, null, null, 0);
    		int hydrogenAmount = 2 * i + 2;
    		GT_Values.RA.addCrackingRecipe(i + 1, new FluidStack(uncrackedFluid, 1000), Materials.Hydrogen.getGas(hydrogenAmount * 1000), new FluidStack(crackedFluids[i], 1000), 40 + 20 * i, 120 + 60 * i);
    		GT_Values.RA.addChemicalRecipe(Materials.Hydrogen.getCells(hydrogenAmount), GT_Utility.getIntegratedCircuit(i + 1), new FluidStack(uncrackedFluid, 1000), new FluidStack(crackedFluids[i], 800), ItemList.Cell_Empty.get(hydrogenAmount), 160 + 80 * i, 30);
    		GT_Values.RA.addChemicalRecipe(aMaterial.getCells(1), GT_Utility.getIntegratedCircuit(i + 1), Materials.Hydrogen.getGas(hydrogenAmount * 1000), new FluidStack(crackedFluids[i], 800), ItemList.Cell_Empty.get(1), 160 + 80 * i, 30);
    	}
    	aMaterial.setHydroCrackedFluids(crackedFluids);
    }
    
    public void addAutoGeneratedSteamCrackedFluids(Materials aMaterial){
    	Fluid[] crackedFluids = new Fluid[3];
    	String[] prefixes = {"lightlysteamcracked.", "moderatelysteamcracked.", "severelysteamcracked."};
    	String[] localPrefixes = {"Lightly Steam-Cracked ", "Moderately Steam-Cracked ", "Severely Steam-Cracked "};
    	GT_Fluid uncrackedFluid = null;
    	if (aMaterial.mFluid != null) {
    		uncrackedFluid = (GT_Fluid) aMaterial.mFluid;
    	} else if (aMaterial.mGas != null) {
    		uncrackedFluid = (GT_Fluid) aMaterial.mGas;
    	}
    	for (int i = 0; i < 3; i++) {
    		crackedFluids[i] = addFluid(prefixes[i] + aMaterial.mName.toLowerCase(Locale.ENGLISH), uncrackedFluid.mTextureName, localPrefixes[i] + aMaterial.mDefaultLocalName, null, aMaterial.mRGBa, 2, 775, null, null, 0);
    		GT_Values.RA.addCrackingRecipe(i + 1, new FluidStack(uncrackedFluid, 1000), GT_ModHandler.getSteam(1000), new FluidStack(crackedFluids[i], 1000), 40 + 20 * i, 240 + 120 * i);
    		GT_Values.RA.addChemicalRecipe(ItemList.Cell_Steam.get(1), GT_Utility.getIntegratedCircuit(i + 1), new FluidStack(uncrackedFluid, 1000), new FluidStack(crackedFluids[i], 800), ItemList.Cell_Empty.get(1), 160 + 80 * i, 30);
    		GT_Values.RA.addChemicalRecipe(aMaterial.getCells(1), GT_Utility.getIntegratedCircuit(i + 1), GT_ModHandler.getSteam(1000), new FluidStack(crackedFluids[i], 800), ItemList.Cell_Empty.get(1), 160 + 80 * i, 30);
    	}
    	aMaterial.setSteamCrackedFluids(crackedFluids);
    }
    
    public Fluid addFluid(String aName, String aLocalized, Materials aMaterial, int aState, int aTemperatureK) {
        return addFluid(aName, aLocalized, aMaterial, aState, aTemperatureK, null, null, 0);
    }

    public Fluid addFluid(String aName, String aLocalized, Materials aMaterial, int aState, int aTemperatureK, ItemStack aFullContainer, ItemStack aEmptyContainer, int aFluidAmount) {
        return addFluid(aName, aName.toLowerCase(Locale.ENGLISH), aLocalized, aMaterial, null, aState, aTemperatureK, aFullContainer, aEmptyContainer, aFluidAmount);
    }

    public Fluid addFluid(String aName, String aTexture, String aLocalized, Materials aMaterial, short[] aRGBa, int aState, int aTemperatureK, ItemStack aFullContainer, ItemStack aEmptyContainer, int aFluidAmount) {
        aName = aName.toLowerCase(Locale.ENGLISH);
        Fluid rFluid = new GT_Fluid(aName, aTexture, aRGBa != null ? aRGBa : Dyes.dyeNULL.getRGBA());
        GT_LanguageManager.addStringLocalization(rFluid.getUnlocalizedName(), aLocalized == null ? aName : aLocalized);
        if (FluidRegistry.registerFluid(rFluid)) {
            switch (aState) {
                case 0:
                    rFluid.setGaseous(false);
                    rFluid.setViscosity(10000);
                    break;
                case 1:
                case 4:
                    rFluid.setGaseous(false);
                    rFluid.setViscosity(1000);
                    break;
                case 2:
                    rFluid.setGaseous(true);
                    rFluid.setDensity(-100);
                    rFluid.setViscosity(200);
                    break;
                case 3:
                    rFluid.setGaseous(true);
                    rFluid.setDensity(55536);
                    rFluid.setViscosity(10);
                    rFluid.setLuminosity(15);
            }
        } else {
            rFluid = FluidRegistry.getFluid(aName);
        }
        if (rFluid.getTemperature() == new Fluid("test").getTemperature()) {
            rFluid.setTemperature(aTemperatureK);
        }
        if (aMaterial != null) {
            switch (aState) {
                case 0:
                    aMaterial.mSolid = rFluid;
                    break;
                case 1:
                    aMaterial.mFluid = rFluid;
                    break;
                case 2:
                    aMaterial.mGas = rFluid;
                    break;
                case 3:
                    aMaterial.mPlasma = rFluid;
                    break;
                case 4:
                    aMaterial.mStandardMoltenFluid = rFluid;
            }
        }
        if ((aFullContainer != null) && (aEmptyContainer != null) && (!FluidContainerRegistry.registerFluidContainer(new FluidStack(rFluid, aFluidAmount), aFullContainer, aEmptyContainer)) && aMaterial != Materials.Air) {
            GT_Values.RA.addFluidCannerRecipe(aFullContainer, GT_Utility.getContainerItem(aFullContainer, false), null, new FluidStack(rFluid, aFluidAmount));
        }
        return rFluid;
    }

    public File getSaveDirectory() {
        return this.mUniverse == null ? null : this.mUniverse.getSaveHandler().getWorldDirectory();
    }

    public void registerUnificationEntries() {
        GregTech_API.sUnification.mConfig.save();
        GregTech_API.sUnification.mConfig.load();
        GT_OreDictUnificator.resetUnificationEntries();
        for (OreDictEventContainer tOre : this.mEvents) {
            if (tOre.mPrefix != null && tOre.mPrefix.mIsUnificatable && tOre.mMaterial != null) {
                if (!(tOre.mEvent.Ore.getItem() instanceof GT_MetaGenerated_Item)) {
                    if (GT_OreDictUnificator.isBlacklisted(tOre.mEvent.Ore)) {
                        GT_OreDictUnificator.addAssociation(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, true);
                    } else {
                        GT_OreDictUnificator.addAssociation(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, false);
                        GT_OreDictUnificator.set(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, (tOre.mModID != null) && GregTech_API.sUnification.get(String.valueOf(ConfigCategories.specialunificationtargets) + "." + tOre.mModID, tOre.mEvent.Name, false), true);
                    }
                } else {
                    if (GT_OreDictUnificator.isBlacklisted(tOre.mEvent.Ore)) {
                        GT_OreDictUnificator.addAssociation(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, true);
                    } else {
                        GT_OreDictUnificator.addAssociation(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, false);
                        GT_OreDictUnificator.set(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, (tOre.mModID != null) && GregTech_API.sUnification.get(new StringBuilder().append(ConfigCategories.specialunificationtargets).append(".").append(tOre.mModID), tOre.mEvent.Name, false), true);
                    }
                }
            }
        }
        GregTech_API.sUnificationEntriesRegistered = true;
        GregTech_API.sUnification.mConfig.save();
        GT_Recipe.reInit();
    }

    public void activateOreDictHandler() {
        ProgressManager.ProgressBar progressBar = ProgressManager.push("Processing Ore Dict: ", this.mEvents.size());
        this.mOreDictActivated = true;
        for (OreDictEventContainer aEvent : this.mEvents) {
            progressBar.step(aEvent.mEvent.Name);
            if (aEvent.mEvent.Name.equals("plankWood")) {
                GT_Values.RA.addLatheRecipe(GT_Utility.copyAmount(1, aEvent.mEvent.Ore), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2), null, 10, 8);
                GT_Values.RA.addCNCRecipe(GT_Utility.copyAmount(4, aEvent.mEvent.Ore), GT_OreDictUnificator.get(OrePrefixes.gearGt, Materials.Wood), 800, 1);
                GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(8, aEvent.mEvent.Ore), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone), new ItemStack(Blocks.noteblock, 1), 200, 4);
                GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(8, aEvent.mEvent.Ore), GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Diamond), new ItemStack(Blocks.jukebox, 1), 400, 4);
                GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(1, aEvent.mEvent.Ore), ItemList.Circuit_Integrated.getWithDamage(0, 1), new ItemStack(Blocks.wooden_button, 1), 100, 4);
                GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(2, aEvent.mEvent.Ore), ItemList.Circuit_Integrated.getWithDamage(0, 2), new ItemStack(Blocks.wooden_pressure_plate, 1), 200, 4);
                GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(3, aEvent.mEvent.Ore), ItemList.Circuit_Integrated.getWithDamage(0, 3), new ItemStack(Blocks.trapdoor, 1), 300, 4);
                GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(4, aEvent.mEvent.Ore), ItemList.Circuit_Integrated.getWithDamage(0, 4), new ItemStack(Blocks.crafting_table, 1), 400, 4);
                GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(6, aEvent.mEvent.Ore), ItemList.Circuit_Integrated.getWithDamage(0, 6), new ItemStack(Items.wooden_door, 1), 600, 4);
                GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(8, aEvent.mEvent.Ore), ItemList.Circuit_Integrated.getWithDamage(0, 8), new ItemStack(Blocks.chest, 1), 800, 4);
                GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(6, aEvent.mEvent.Ore), new ItemStack(Items.book, 3), new ItemStack(Blocks.bookshelf, 1), 400, 4);

                if (aEvent.mEvent.Ore.getItemDamage() == 32767) {
                    for (byte i = 0; i < 64; i = (byte) (i + 1)) {
                        ItemStack tStack = GT_Utility.copyMetaData(i, aEvent.mEvent.Ore);
                        ItemStack tOutput = GT_ModHandler.getRecipeOutput(tStack, tStack, tStack);
                        if ((tOutput != null) && (tOutput.stackSize >= 3)) {
                            GT_Values.RA.addCutterRecipe(GT_Utility.copyAmount(1, tStack), GT_Utility.copyAmount(tOutput.stackSize / 3, tOutput), null, 25, 4);
                            GT_ModHandler.removeRecipe(tStack, tStack, tStack);
                            GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(tOutput.stackSize / 3, tOutput), new Object[]{"sP", Character.valueOf('P'), tStack});
                        }
                        if((tStack == null) && (i >= 16)) break;
                    }
                } else {
                    ItemStack tOutput = GT_ModHandler.getRecipeOutput(aEvent.mEvent.Ore, aEvent.mEvent.Ore, aEvent.mEvent.Ore);
                    if ((tOutput != null) && (tOutput.stackSize >= 3)) {
                        GT_Values.RA.addCutterRecipe(GT_Utility.copyAmount(1, aEvent.mEvent.Ore), GT_Utility.copyAmount(tOutput.stackSize / 3, tOutput), null, 25, 4);
                        GT_ModHandler.removeRecipe(aEvent.mEvent.Ore, aEvent.mEvent.Ore, aEvent.mEvent.Ore);
                        GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(tOutput.stackSize / 3, tOutput), new Object[]{"sP", Character.valueOf('P'), aEvent.mEvent.Ore});
                    }
                }
            } else if (aEvent.mEvent.Name.equals("logWood")) {
                GT_Values.RA.addAssemblerRecipe(aEvent.mEvent.Ore, ItemList.Circuit_Integrated.getWithDamage(0, 2), Materials.SeedOil.getFluid(50), ItemList.FR_Stick.get(1), 16, 8);
                GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(8, aEvent.mEvent.Ore), ItemList.Circuit_Integrated.getWithDamage(0, 8), Materials.SeedOil.getFluid(250), ItemList.FR_Casing_Impregnated.get(1), 64, 16);

                short aMeta = (short) aEvent.mEvent.Ore.getItemDamage();
                if (aMeta == Short.MAX_VALUE) {
                    if ((GT_Utility.areStacksEqual(GT_ModHandler.getSmeltingOutput(aEvent.mEvent.Ore, false, null), new ItemStack(Items.coal, 1, 1)))) {
                        GT_ModHandler.addPyrolyeOvenRecipes(aEvent.mEvent.Ore);
                        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "wood2charcoalsmelting", true)) {
                            GT_ModHandler.removeFurnaceSmelting(aEvent.mEvent.Ore);
                        }
                    }
                    for (int i = 0; i < 32767; i++) {
                        if ((GT_Utility.areStacksEqual(GT_ModHandler.getSmeltingOutput(new ItemStack(aEvent.mEvent.Ore.getItem(), 1, i), false, null), new ItemStack(Items.coal, 1, 1)))) {
                            GT_ModHandler.addPyrolyeOvenRecipes(aEvent.mEvent.Ore);
                            if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "wood2charcoalsmelting", true)) {
                                GT_ModHandler.removeFurnaceSmelting(new ItemStack(aEvent.mEvent.Ore.getItem(), 1, i));
                            }
                        }
                        ItemStack tStack = GT_ModHandler.getRecipeOutput(new ItemStack(aEvent.mEvent.Ore.getItem(), 1, i));
                        if (tStack == null || i>= 16) {
                            break;
                        } else {
                            ItemStack tPlanks = GT_Utility.copy(tStack);
                            tPlanks.stackSize = (tPlanks.stackSize * 3 / 2);
                            GT_Values.RA.addCutterRecipe(new ItemStack(aEvent.mEvent.Ore.getItem(), 1, i), Materials.Lubricant.getFluid(1), GT_Utility.copy(tPlanks), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood), 200, 8);
                            GT_Values.RA.addCutterRecipe(new ItemStack(aEvent.mEvent.Ore.getItem(), 1, i), GT_Utility.copyAmount(GT_Mod.gregtechproxy.mNerfedWoodPlank ? tStack.stackSize : tStack.stackSize * 5 / 4, tStack), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 2), 200, 8);
                            GT_ModHandler.removeRecipe(new ItemStack(aEvent.mEvent.Ore.getItem(), 1, i));
                            GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(GT_Mod.gregtechproxy.mNerfedWoodPlank ? tStack.stackSize : tStack.stackSize * 5 / 4, tStack), new Object[]{"s", "L", 'L', new ItemStack(aEvent.mEvent.Ore.getItem(), 1, i)});
                            GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(tStack.stackSize / (GT_Mod.gregtechproxy.mNerfedWoodPlank ? 2 : 1), tStack), new Object[]{new ItemStack(aEvent.mEvent.Ore.getItem(), 1, i)});
                        }
                    }
                } else {
                    if ((GT_Utility.areStacksEqual(GT_ModHandler.getSmeltingOutput(aEvent.mEvent.Ore, false, null), new ItemStack(Items.coal, 1, 1)))) {
                        GT_ModHandler.addPyrolyeOvenRecipes(aEvent.mEvent.Ore);
                        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "wood2charcoalsmelting", true)) {
                            GT_ModHandler.removeFurnaceSmelting(aEvent.mEvent.Ore);
                        }
                    }
                    ItemStack tStack = GT_ModHandler.getRecipeOutput(aEvent.mEvent.Ore);
                    if (tStack != null) {
                        ItemStack tPlanks = GT_Utility.copy(tStack);
                        tPlanks.stackSize = (tPlanks.stackSize * 3 / 2);
                        GT_Values.RA.addCutterRecipe(aEvent.mEvent.Ore, Materials.Lubricant.getFluid(1), GT_Utility.copy(tPlanks), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood), 200, 8);
                        GT_Values.RA.addCutterRecipe(aEvent.mEvent.Ore, GT_Utility.copyAmount(GT_Mod.gregtechproxy.mNerfedWoodPlank ? tStack.stackSize : tStack.stackSize * 5 / 4, tStack), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 2), 200, 8);
                        GT_ModHandler.removeRecipe(aEvent.mEvent.Ore);
                        GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(GT_Mod.gregtechproxy.mNerfedWoodPlank ? tStack.stackSize : tStack.stackSize * 5 / 4, tStack), new Object[]{"s", "L", 'L', aEvent.mEvent.Ore});
                        GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(tStack.stackSize / (GT_Mod.gregtechproxy.mNerfedWoodPlank ? 2 : 1), tStack), new Object[]{aEvent.mEvent.Ore});
                    }
                }

                if ((GT_Utility.areStacksEqual(GT_ModHandler.getSmeltingOutput(aEvent.mEvent.Ore, false, null), new ItemStack(Items.coal, 1, 1)))) {
                    GT_ModHandler.addPyrolyeOvenRecipes(aEvent.mEvent.Ore);
                    if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "wood2charcoalsmelting", true)) {
                        GT_ModHandler.removeFurnaceSmelting(aEvent.mEvent.Ore);
                    }
                }
            } else if (aEvent.mEvent.Name.equals("logRubber")) {
                GT_Values.RA.addCentrifugeRecipe(aEvent.mEvent.Ore, null, null, Materials.Methane.getGas(60), ItemList.Resin.get(1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood), null, null, null, new int[]{5000, 2500, 2500}, 200, 20);
                GT_ModHandler.addExtractionRecipe(aEvent.mEvent.Ore, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.RawRubber));
                GT_ModHandler.addPulverisationRecipe(aEvent.mEvent.Ore, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 6), ItemList.Resin.get(1), 33, false);
            } else if (aEvent.mEvent.Name.equals("treeSapling")) {
                GT_ModHandler.addPulverisationRecipe(aEvent.mEvent.Ore, GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Wood, 2), null, 0, false);
                GT_Values.RA.addLatheRecipe(aEvent.mEvent.Ore, GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Wood), 16, 8);
            }
            registerRecipes(aEvent);
        }
        ProgressManager.pop(progressBar);
    }

    public static final HashMap<Integer, HashMap<ChunkCoordIntPair, int[]>> dimensionWiseChunkData = new HashMap<>(16); //stores chunk data that is loaded/saved
    public static final HashMap<Integer, GT_Pollution> dimensionWisePollution = new HashMap<>(16); //stores GT_Polluttors objects
	public static final byte GTOIL = 3, GTOILFLUID = 2, GTPOLLUTION = 1, GTMETADATA = 0, NOT_LOADED = 0, LOADED = 1; //consts

    private static final byte oilVer = (byte) 20; //non zero plz

    //TO get default's fast
    public static int[] getDefaultChunkDataOnCreation(){
        return new int[]{NOT_LOADED, 0, -1, -1};
    }
    public static int[] getDefaultChunkDataOnLoad(){
        return new int[]{LOADED, 0, -1, -1};
    }

    @SubscribeEvent
    public void handleChunkSaveEvent(ChunkDataEvent.Save event) { //ALWAYS SAVE FROM THE HASH MAP DATA
        HashMap<ChunkCoordIntPair, int[]> chunkData = dimensionWiseChunkData.get(event.world.provider.dimensionId);
        if (chunkData == null) return; //no dim info stored

        int[] tInts = chunkData.get(event.getChunk().getChunkCoordIntPair());
        if (tInts == null) return;//no chunk data stored
        //assuming len of this array 4
        if (tInts[3] >= 0) {
            event.getData().setInteger("GTOIL", tInts[GTOIL]);
        } else {
            event.getData().removeTag("GTOIL");
        }
        if (tInts[2] >= 0) {
            event.getData().setInteger("GTOILFLUID", tInts[GTOILFLUID]);
        } else {
            event.getData().removeTag("GTOILFLUID");
        }
        if (tInts[1] > 0) {
            event.getData().setInteger("GTPOLLUTION", tInts[GTPOLLUTION]);
        } else {
            event.getData().removeTag("GTPOLLUTION");
        }
        event.getData().setByte("GTOILVER", oilVer);//version mark
    }

    @SubscribeEvent
    public void handleChunkLoadEvent(ChunkDataEvent.Load event) {
        final int worldID = event.world.provider.dimensionId;
        HashMap<ChunkCoordIntPair, int[]> chunkData = dimensionWiseChunkData.get(worldID);
        if (chunkData == null) {
            chunkData = new HashMap<>(1024);
            dimensionWiseChunkData.put(worldID, chunkData);
        }
        if (dimensionWisePollution.get(worldID) == null) {
            dimensionWisePollution.put(worldID, new GT_Pollution(event.world));
        }

        int[] tInts = chunkData.get(event.getChunk().getChunkCoordIntPair());
        if (tInts == null) {
            //NOT LOADED and NOT PROCESSED by pollution algorithms
            //regular load
            tInts = getDefaultChunkDataOnLoad();

            if (event.getData().getByte("GTOILVER") == oilVer) {
                if (event.getData().hasKey("GTOIL")) {
                    tInts[GTOIL] = event.getData().getInteger("GTOIL");
                }
                if (event.getData().hasKey("GTOILFLUID")) {
                    tInts[GTOILFLUID] = event.getData().getInteger("GTOILFLUID");
                }
            }

            tInts[GTPOLLUTION] = event.getData().getInteger("GTPOLLUTION"); //Defaults to 0

            //store in HASH MAP if has useful data
            if (tInts[GTPOLLUTION] > 0 || tInts[GTOIL] >= 0 || tInts[GTOILFLUID] >= 0) {
                chunkData.put(event.getChunk().getChunkCoordIntPair(), tInts);
            }
        } else if (tInts[GTMETADATA] == NOT_LOADED) { //was NOT loaded from chunk save game data
            //NOT LOADED but generated
            //append load
            if (event.getData().getByte("GTOILVER") == oilVer) {
                if (tInts[GTOIL] < 0 && event.getData().hasKey("GTOIL")) { //if was not yet initialized
                    tInts[GTOIL] = event.getData().getInteger("GTOIL");
                }

                if (tInts[GTOILFLUID] < 0 && event.getData().hasKey("GTOILFLUID")) { //if was not yet initialized
                    tInts[GTOILFLUID] = event.getData().getInteger("GTOILFLUID");
                }
            } else {
                tInts[GTOIL] = -1;
                tInts[GTOILFLUID] = -1;
            }

            tInts[GTPOLLUTION] += event.getData().getInteger("GTPOLLUTION"); //Defaults to 0, add stored pollution to data
            tInts[GTMETADATA] = LOADED; //mark as = loaded
            //store in HASHMAP

            chunkData.put(event.getChunk().getChunkCoordIntPair(), tInts);
        }
    }
    
    @SubscribeEvent
    public void onBlockBreakSpeedEvent(PlayerEvent.BreakSpeed aEvent) {
        if (aEvent.newSpeed > 0.0F) {
            if (aEvent.entityPlayer != null) {
                ItemStack aStack = aEvent.entityPlayer.getCurrentEquippedItem();
                if ((aStack != null) && ((aStack.getItem() instanceof GT_MetaGenerated_Tool))) {
                    aEvent.newSpeed = ((GT_MetaGenerated_Tool)aStack.getItem()).onBlockBreakSpeedEvent(aEvent.newSpeed, aStack, aEvent.entityPlayer, aEvent.block, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.metadata, aEvent);
                }
            }
        }
    }

    public static class OreDictEventContainer {
        public final OreDictionary.OreRegisterEvent mEvent;
        public final OrePrefixes mPrefix;
        public final Materials mMaterial;
        public final String mModID;

        public OreDictEventContainer(OreDictionary.OreRegisterEvent aEvent, OrePrefixes aPrefix, Materials aMaterial, String aModID) {
            this.mEvent = aEvent;
            this.mPrefix = aPrefix;
            this.mMaterial = aMaterial;
            this.mModID = ((aModID == null) || (aModID.equals("UNKNOWN")) ? null : aModID);
        }
    }
}