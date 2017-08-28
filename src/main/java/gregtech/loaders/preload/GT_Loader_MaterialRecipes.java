package gregtech.loaders.preload;

import appeng.api.config.TunnelType;
import appeng.core.Api;
import cpw.mods.fml.common.ProgressManager;
import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.*;
import gregtech.api.objects.GT_CopiedBlockTexture;
import gregtech.api.objects.GT_MultiTexture;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.objects.MaterialStack;
import gregtech.api.util.*;
import gregtech.common.GT_Proxy;
import gregtech.common.covers.GT_Cover_Lens;
import gregtech.common.items.GT_MetaGenerated_Tool_01;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static gregtech.common.GT_Proxy.tBits;

public class GT_Loader_MaterialRecipes implements Runnable {
    private boolean aNoSmelting;
    private boolean aNoWorking;
    private boolean aNoSmashing;
    private boolean aWashingMercury;
    private boolean aWashingSodium;

    private ItemStack aNormalDustStack = null;
    private ItemStack aSmallDustStack = null;
    private ItemStack aTinyDustStack = null;
    private ItemStack aIngotStack = null;
    private ItemStack aNuggetStack = null;
    private ItemStack aPlateStack = null;

    public void run() {
        GT_Log.out.println("GT_Mod: Register Ore processing.");
        Materials.SodiumHydroxide.mSmeltInto = Materials.SodiumHydroxide;

        List<Materials> aTempList = new ArrayList<>();
        for (Materials aMaterial : Materials.values()) {
            if (aMaterial.mMetaItemSubID > -1 && (aMaterial.mElement != null || (aMaterial.hasFlag(MaterialFlags.DUST.bit) || aMaterial.hasFlag(MaterialFlags.SOLID.bit)))) {
                aTempList.add(aMaterial);
            }
        }
        Materials[] aSolidAndDustArray = aTempList.toArray(new Materials[aTempList.size()]);

        ProgressManager.ProgressBar progressBar = ProgressManager.push("Registering Material Recipes: ", aSolidAndDustArray.length);
        for (Materials aMaterial : aSolidAndDustArray) {
            progressBar.step(aMaterial.mName);
            if (aMaterial.mMetaItemSubID <= -1) continue;
            aNoSmelting = aMaterial.contains(SubTag.NO_SMELTING);
            aNoWorking = aMaterial.contains(SubTag.NO_WORKING);
            aNoSmashing = aMaterial.contains(SubTag.NO_SMASHING);
            aWashingMercury = aMaterial.contains(SubTag.WASHING_MERCURY);
            aWashingSodium = aMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE);

            if (aMaterial.hasFlag(MaterialFlags.DUST.bit)) {
                aNormalDustStack = GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1);
                aSmallDustStack = GT_OreDictUnificator.get(OrePrefixes.dustSmall, aMaterial, 1);
                aTinyDustStack = GT_OreDictUnificator.get(OrePrefixes.dustTiny, aMaterial, 1);
                addDustRecipes(aMaterial);
                if (aMaterial.hasFlag(MaterialFlags.SOLID.bit)) {
                    aIngotStack = GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1);
                    aNuggetStack = GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial, 1);
                    addSolidRecipes(aMaterial);
                    addShapingRecipes(aMaterial);
                    if (aMaterial.hasFlag(MaterialFlags.ORE.bit)) {
                        addOreRecipes(aMaterial, false);
                    }
                    if (aMaterial.hasFlag(MaterialFlags.PLATE.bit)) {
                        aPlateStack = GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1);
                        addPlateRecipes(aMaterial);
                        if (aMaterial.hasFlag(MaterialFlags.FOIL.bit)) addFoilRecipes(aMaterial);
                        if (aMaterial.hasFlag(MaterialFlags.DPLATE.bit)) addDensePlateRecipes(aMaterial);
                    }
                    if (aMaterial.hasFlag(MaterialFlags.CELL.bit)) addCellRecipes(aMaterial);
                    if (aMaterial.hasFlag(MaterialFlags.TOOL.bit)) addToolRecipes(aMaterial);
                    if (aMaterial.hasFlag(MaterialFlags.STICK.bit)) {
                        addStickRecipes(aMaterial);
                        if (aMaterial.hasFlag(MaterialFlags.RING.bit)) addRingRecipes(aMaterial);
                        if (aMaterial.hasFlag(MaterialFlags.SPRING.bit)) addSpringRecipes(aMaterial);
                        if (aMaterial.hasFlag(MaterialFlags.BOLT.bit)) addBoltRecipes(aMaterial);
                        if (aMaterial.hasFlag(MaterialFlags.SCREW.bit)) addScrewRecipes(aMaterial);
                    }
                    if (aMaterial.hasFlag(MaterialFlags.GEAR.bit)) addGearRecipes(aMaterial);
                    if (aMaterial.hasFlag(MaterialFlags.SGEAR.bit)) addGearSmallRecipes(aMaterial);
                    if (aMaterial.hasFlag(MaterialFlags.FWIRE.bit)) addFineWireRecipes(aMaterial);
                    if (aMaterial.hasFlag(MaterialFlags.ROTOR.bit)) addRotorRecipes(aMaterial);
                    if (aMaterial.hasFlag(MaterialFlags.HINGOT.bit)) addHotIngotRecipes(aMaterial);
                    if (aMaterial.hasFlag(MaterialFlags.WIRE.bit)) addWireRecipes(aMaterial);
                }
                if (aMaterial.hasFlag(MaterialFlags.GEM.bit)) addGemRecipes(aMaterial);
            }
        }
        ProgressManager.pop(progressBar);
        addMaterialSpecificRecipes();
    }

    public void addHotIngotRecipes(Materials aMaterial) {
        GT_Values.RA.addVacuumFreezerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingotHot, aMaterial, 1), aIngotStack, (int) Math.max(aMaterial.getMass() * 3L, 1L));
    }

    public void addSpringRecipes(Materials aMaterial) {
        GT_Values.RA.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial, 1), GT_OreDictUnificator.get(OrePrefixes.spring, aMaterial, 1L), 200, 16);
    }

    public void addDensePlateRecipes(Materials aMaterial) {
        ItemStack aDenseStack = GT_OreDictUnificator.get(OrePrefixes.plateDense, aMaterial, 1);
        GT_ModHandler.removeRecipeByOutput(aDenseStack);
        GT_Utility.removeSimpleIC2MachineRecipe(GT_Utility.copyAmount(9L, aPlateStack), GT_ModHandler.getCompressorRecipeList(), aDenseStack);
        if (aMaterial.mUnificatable && aMaterial.mMaterialInto == aMaterial && aNoSmashing) {
            GT_Values.RA.addBenderRecipe(GT_Utility.copyAmount(9L, aPlateStack), aDenseStack, (int) Math.max(aMaterial.getMass() * 9L, 1L), 96);
        }
        GregTech_API.registerCover(aDenseStack, new GT_RenderedTexture(aMaterial.mIconSet.mTextures[76], aMaterial.mRGBa, false), null);
    }

    public void addRotorRecipes(Materials aMaterial) {
        ItemStack aRotorStack = GT_OreDictUnificator.get(OrePrefixes.rotor, aMaterial, 1);
        ItemStack aRingStack = GT_OreDictUnificator.get(OrePrefixes.ring, aMaterial, 1);
        if ((aMaterial.mUnificatable) && (aMaterial.mMaterialInto == aMaterial) && !aNoWorking) {
            GT_ModHandler.addCraftingRecipe(aRotorStack, tBits, new Object[]{"PhP", "SRf", "PdP", Character.valueOf('P'), aMaterial == Materials.Wood ? OrePrefixes.plank.get(aMaterial) : aPlateStack, Character.valueOf('R'), OrePrefixes.ring.get(aMaterial), Character.valueOf('S'), OrePrefixes.screw.get(aMaterial)});
            GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(4L, aPlateStack), aRingStack, Materials.Tin.getMolten(32), aRotorStack, 240, 24);
            GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(4L, aPlateStack), aRingStack, Materials.Lead.getMolten(48), aRotorStack, 240, 24);
            GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(4L, aPlateStack), aRingStack, Materials.SolderingAlloy.getMolten(16), aRotorStack, 240, 24);
        }
    }

    public void addFineWireRecipes(Materials aMaterial) {
        ItemStack aWireStack = GT_OreDictUnificator.get(OrePrefixes.wireFine, aMaterial, 1);
        if (!aNoSmashing) {
            GT_Values.RA.addWiremillRecipe(aIngotStack, GT_Utility.copy(GT_OreDictUnificator.get(OrePrefixes.wireGt01, aMaterial, 2L), aWireStack), 100, 4);
            GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial, 1L), GT_Utility.copy(GT_OreDictUnificator.get(OrePrefixes.wireGt01, aMaterial, 1L), aWireStack), 50, 4);
        }
        if ((aMaterial.mUnificatable) && (aMaterial.mMaterialInto == aMaterial) && !aNoWorking) {
            GT_ModHandler.addCraftingRecipe(aWireStack, tBits, new Object[]{"Xx", Character.valueOf('X'), OrePrefixes.foil.get(aMaterial)});
        }
    }

    public void addGearSmallRecipes(Materials aMaterial) {
        ItemStack aGearSmallStack = GT_OreDictUnificator.get(OrePrefixes.gearGtSmall, aMaterial, 1);
        if (aMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Gear_Small.get(0L), aMaterial.getMolten(144L), GT_Utility.copyAmount(1L, aGearSmallStack), 16, 8);
        }
        if (aMaterial.mUnificatable && (aMaterial.mMaterialInto == aMaterial) && !aNoWorking) {
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.gearGtSmall, aMaterial, 1L), tBits, new Object[]{"P ", aMaterial.contains(SubTag.WOOD) ? " s" : " h", Character.valueOf('P'), OrePrefixes.plate.get(aMaterial)});
        }
    }

    public void addGearRecipes(Materials aMaterial) {
        ItemStack aGearStack = GT_OreDictUnificator.get(OrePrefixes.gearGt, aMaterial, 1);
        GT_ModHandler.removeRecipeByOutput(aGearStack);
        if (aMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Gear.get(0L), aMaterial.getMolten(576L), aGearStack, 128, 8);
        }
        if (aMaterial.mUnificatable && (aMaterial.mMaterialInto == aMaterial) && !aNoWorking) {
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.gearGt, aMaterial, 1L), tBits, new Object[]{"SPS", "PwP", "SPS", Character.valueOf('P'), OrePrefixes.plate.get(aMaterial), Character.valueOf('S'), OrePrefixes.stick.get(aMaterial)});
        }
        GT_OreDictUnificator.registerOre(OrePrefixes.gear, aMaterial, aEvent.Ore);
    }

    public void addScrewRecipes(Materials aMaterial) {
        ItemStack aScrewStack = GT_OreDictUnificator.get(OrePrefixes.screw, aMaterial, 1);
        if (!aNoWorking) {
            GT_Values.RA.addLatheRecipe(GT_OreDictUnificator.get(OrePrefixes.bolt, aMaterial, 1L), aScrewStack, null, (int) Math.max(aMaterial.getMass() / 8L, 1L), 4);
            if ((aMaterial.mUnificatable) && (aMaterial.mMaterialInto == aMaterial)) {
                GT_ModHandler.addCraftingRecipe(aScrewStack, tBits, new Object[]{"fX", "X ", Character.valueOf('X'), OrePrefixes.bolt.get(aMaterial)});
            }
        }
    }

    public void addFoilRecipes(Materials aMaterial) {
        ItemStack aFoilStack = GT_OreDictUnificator.get(OrePrefixes.foil, aMaterial, 1);
        GT_Values.RA.addBenderRecipe(GT_Utility.copyAmount(1L, GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 4L)), GT_Utility.copyAmount(4L, aFoilStack), (int) Math.max(aMaterial.getMass(), 1L), 24);
        GregTech_API.registerCover(aFoilStack, new GT_RenderedTexture(aMaterial.mIconSet.mTextures[70], aMaterial.mRGBa, false), null);
    }

    public void addBoltRecipes(Materials aMaterial) {
        ItemStack aBoltStack = GT_OreDictUnificator.get(OrePrefixes.bolt, aMaterial, 1);
        if ((aMaterial.mUnificatable) && (aMaterial.mMaterialInto == aMaterial) && !aNoWorking) {
            GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(2L, aBoltStack), tBits, new Object[]{"s ", " X", Character.valueOf('X'), OrePrefixes.stick.get(aMaterial)});
        }
    }

    public void addRingRecipes(Materials aMaterial) {
        if ((aMaterial.mUnificatable) && (aMaterial.mMaterialInto == aMaterial) && !aNoSmashing) {
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.ring, aMaterial, 1L), tBits, new Object[]{"h ", " X", Character.valueOf('X'), OrePrefixes.stick.get(aMaterial)});
        }
    }

    public void addStickRecipes(Materials aMaterial) {
        ItemStack aStickStack = GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial, 1);
        if (!aNoWorking) {
            GT_Values.RA.addLatheRecipe(aMaterial.contains(SubTag.CRYSTAL) ? GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 1L) : aIngotStack, aStickStack, GT_OreDictUnificator.get(OrePrefixes.dustSmall, aMaterial.mMacerateInto, 2L), (int) Math.max(aMaterial.getMass() * 5L, 1L), 16);
            GT_Values.RA.addCutterRecipe(aStickStack, GT_OreDictUnificator.get(OrePrefixes.bolt, aMaterial, 4L), null, (int) Math.max(aMaterial.getMass() * 2L, 1L), 4);
            if ((aMaterial.mUnificatable) && (aMaterial.mMaterialInto == aMaterial)) {
                GT_ModHandler.addCraftingRecipe(aStickStack, tBits, new Object[]{"f ", " X", Character.valueOf('X'), aIngotStack});
            }
        }
    }

    public void addPlateRecipes(Materials aMaterial) {
        GT_ModHandler.removeRecipeByOutput(aPlateStack);
        GT_ModHandler.removeRecipe(aPlateStack);
        if (aMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Plate.get(0L), aMaterial.getMolten(144L), GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1L), 32, 8);
        }
        if (aMaterial.mFuelPower > 0) {
            GT_Values.RA.addFuel(aPlateStack, null, aMaterial.mFuelPower, aMaterial.mFuelType);
        }
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(aMaterial == Materials.MeteoricIron ? 1 : 2, aPlateStack), 2, GT_OreDictUnificator.get(OrePrefixes.compressed, aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 1L));
        GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.foil, aMaterial, 2L), tBits, new Object[]{"hX", 'X', aPlateStack});
        if ((aMaterial.mUnificatable) && (aMaterial.mMaterialInto == aMaterial)) {
            if (!aNoSmashing) {
                if (GregTech_API.sRecipeFile.get(ConfigCategories.Tools.hammerplating, aMaterial.toString(), true)) {
                    GT_ModHandler.addCraftingRecipe(aPlateStack, tBits, new Object[]{"h", "X", "X", 'X', OrePrefixes.ingot.get(aMaterial)});
                    GT_ModHandler.addCraftingRecipe(aPlateStack, tBits, new Object[]{"H", "X", 'H', ToolDictNames.craftingToolForgeHammer, 'X', OrePrefixes.ingot.get(aMaterial)});
                    GT_ModHandler.addCraftingRecipe(aPlateStack, tBits, new Object[]{"h", "X", 'X', OrePrefixes.gem.get(aMaterial)});
                    GT_ModHandler.addCraftingRecipe(aPlateStack, tBits, new Object[]{"H", "X", 'H', ToolDictNames.craftingToolForgeHammer, 'X', OrePrefixes.gem.get(aMaterial)});
                }
            }
            if ((aMaterial.contains(SubTag.MORTAR_GRINDABLE)) && (GregTech_API.sRecipeFile.get(ConfigCategories.Tools.mortar, aMaterial.mName, true))) {
                GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1L), tBits, new Object[]{"X", "m", 'X', aPlateStack});
            }
        }
        GregTech_API.registerCover(aPlateStack, new GT_RenderedTexture(aMaterial.mIconSet.mTextures[71], aMaterial.mRGBa, false), null);
    }

    public void addToolRecipes(Materials aMaterial) {
        ItemStack aStickStack = GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial, 1);
        ItemStack aStainlessScrew = GT_OreDictUnificator.get(OrePrefixes.screw, Materials.StainlessSteel, 1);
        ItemStack aTitaniumScrew = GT_OreDictUnificator.get(OrePrefixes.screw, Materials.Titanium, 1);
        ItemStack aTungstensteelScrew = GT_OreDictUnificator.get(OrePrefixes.screw, Materials.TungstenSteel, 1);
        ItemStack aStainlessPlate = GT_OreDictUnificator.get(OrePrefixes.plate, Materials.StainlessSteel, 1);
        ItemStack aTitaniumPlate = GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Titanium, 1);
        ItemStack aTungstensteelPlate = GT_OreDictUnificator.get(OrePrefixes.plate, Materials.TungstenSteel, 1);
        ItemStack aStainlessSmallGear = GT_OreDictUnificator.get(OrePrefixes.gearGtSmall, Materials.StainlessSteel, 1);
        ItemStack aTitaniumSmallGear = GT_OreDictUnificator.get(OrePrefixes.gearGtSmall, Materials.Titanium, 1);
        ItemStack aTungstensteelSmallGear = GT_OreDictUnificator.get(OrePrefixes.gearGtSmall, Materials.TungstenSteel, 1);
        ItemStack aTitaniumSpring = GT_OreDictUnificator.get(OrePrefixes.spring, Materials.Titanium, 1);
        boolean aSpecialRecipeReq1 = aMaterial.mUnificatable && (aMaterial.mMaterialInto == aMaterial) && !aMaterial.contains(SubTag.NO_SMASHING);
        boolean aSpecialRecipeReq2 = aMaterial.mUnificatable && (aMaterial.mMaterialInto == aMaterial) && !aMaterial.contains(SubTag.NO_WORKING);
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.BUZZSAW, 1, aMaterial, Materials.StainlessSteel, new long[]{100000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"PBM", "dXG", "SGP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_LV.get(1L), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Lithium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.BUZZSAW, 1, aMaterial, Materials.StainlessSteel, new long[]{75000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"PBM", "dXG", "SGP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_LV.get(1L), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Cadmium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.BUZZSAW, 1, aMaterial, Materials.StainlessSteel, new long[]{50000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"PBM", "dXG", "SGP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_LV.get(1L), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Sodium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{100000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_LV.get(1L), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Lithium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_MV, 1, aMaterial, Materials.Titanium, new long[]{400000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_MV.get(1L), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Lithium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1600000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_HV.get(1L), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Lithium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{75000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_LV.get(1L), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Cadmium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_MV, 1, aMaterial, Materials.Titanium, new long[]{300000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_MV.get(1L), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Cadmium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1200000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_HV.get(1L), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Cadmium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{50000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_LV.get(1L), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Sodium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_MV, 1, aMaterial, Materials.Titanium, new long[]{200000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_MV.get(1L), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Sodium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{800000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_HV.get(1L), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Sodium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{100000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_LV.get(1L), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Lithium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{75000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_LV.get(1L), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Cadmium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{50000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_LV.get(1L), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Sodium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_MV, 1, aMaterial, Materials.Titanium, new long[]{400000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_MV.get(1L), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Lithium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_MV, 1, aMaterial, Materials.Titanium, new long[]{300000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_MV.get(1L), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Cadmium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_MV, 1, aMaterial, Materials.Titanium, new long[]{200000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_MV.get(1L), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Sodium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1600000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_HV.get(1L), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Lithium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1200000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_HV.get(1L), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Cadmium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{800000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_HV.get(1L), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Sodium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.JACKHAMMER, 1, aMaterial, Materials.Titanium, new long[]{1600000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "PRP", "MPB", 'X', aStickStack, 'M', ItemList.Electric_Piston_HV.get(1L), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'R', aTitaniumSpring, 'B', ItemList.Battery_RE_HV_Lithium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.JACKHAMMER, 1, aMaterial, Materials.Titanium, new long[]{1200000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "PRP", "MPB", 'X', aStickStack, 'M', ItemList.Electric_Piston_HV.get(1L), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'R', aTitaniumSpring, 'B', ItemList.Battery_RE_HV_Cadmium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.JACKHAMMER, 1, aMaterial, Materials.Titanium, new long[]{800000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "PRP", "MPB", 'X', aStickStack, 'M', ItemList.Electric_Piston_HV.get(1L), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'R', aTitaniumSpring, 'B', ItemList.Battery_RE_HV_Sodium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{100000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_LV.get(1L), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Lithium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_MV, 1, aMaterial, Materials.Titanium, new long[]{400000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_MV.get(1L), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Lithium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1600000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_HV.get(1L), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Lithium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{75000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_LV.get(1L), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Cadmium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_MV, 1, aMaterial, Materials.Titanium, new long[]{300000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_MV.get(1L), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Cadmium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1200000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_HV.get(1L), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Cadmium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{50000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_LV.get(1L), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Sodium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_MV, 1, aMaterial, Materials.Titanium, new long[]{200000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_MV.get(1L), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Sodium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{800000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_HV.get(1L), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Sodium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.SCREWDRIVER_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{100000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"PdX", "MGS", "GBP", 'X', aStickStack, 'M', ItemList.Electric_Motor_LV.get(1L), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Lithium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.SCREWDRIVER_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{75000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"PdX", "MGS", "GBP", 'X', aStickStack, 'M', ItemList.Electric_Motor_LV.get(1L), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Cadmium.get(1L)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.SCREWDRIVER_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{50000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"PdX", "MGS", "GBP", 'X', aStickStack, 'M', ItemList.Electric_Motor_LV.get(1L), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Sodium.get(1L)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.AXE, 1, aMaterial, aMaterial.mHandleMaterial, null), new Object[]{ToolDictNames.craftingToolAxe, OrePrefixes.stick.get(aMaterial.mHandleMaterial)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.FILE, 1, aMaterial, aMaterial.mHandleMaterial, null), new Object[]{ToolDictNames.craftingToolFile, OrePrefixes.stick.get(aMaterial.mHandleMaterial)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.HOE, 1, aMaterial, aMaterial.mHandleMaterial, null), new Object[]{ToolDictNames.craftingToolHoe, OrePrefixes.stick.get(aMaterial.mHandleMaterial)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.PICKAXE, 1, aMaterial, aMaterial.mHandleMaterial, null), new Object[]{ToolDictNames.craftingToolPickaxe, OrePrefixes.stick.get(aMaterial.mHandleMaterial)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.PLOW, 1, aMaterial, aMaterial.mHandleMaterial, null), new Object[]{ToolDictNames.craftingToolPlow, OrePrefixes.stick.get(aMaterial.mHandleMaterial)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.SAW, 1, aMaterial, aMaterial.mHandleMaterial, null), new Object[]{ToolDictNames.craftingToolSaw, OrePrefixes.stick.get(aMaterial.mHandleMaterial)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.SENSE, 1, aMaterial, aMaterial.mHandleMaterial, null), new Object[]{ToolDictNames.craftingToolBranchCutter, OrePrefixes.stick.get(aMaterial.mHandleMaterial)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.SHOVEL, 1, aMaterial, aMaterial.mHandleMaterial, null), new Object[]{ToolDictNames.craftingToolShovel, OrePrefixes.stick.get(aMaterial.mHandleMaterial)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.SWORD, 1, aMaterial, aMaterial.mHandleMaterial, null), new Object[]{ToolDictNames.craftingToolSword, OrePrefixes.stick.get(aMaterial.mHandleMaterial)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.UNIVERSALSPADE, 1, aMaterial, aMaterial, null), new Object[]{ToolDictNames.craftingToolShovel, aStickStack, OrePrefixes.bolt.get(aMaterial), ToolDictNames.craftingToolScrewdriver});
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.turbineBlade, aMaterial, 8L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Magnalium, 1L), GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(170, 1, aMaterial, aMaterial, null), 160, 100);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.turbineBlade, aMaterial, 16L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Titanium, 1L), GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(172, 1, aMaterial, aMaterial, null), 320, 400);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.turbineBlade, aMaterial, 24L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.TungstenSteel, 1L), GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(174, 1, aMaterial, aMaterial, null), 640, 1600);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.turbineBlade, aMaterial, 32L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Americium, 1L), GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(176, 1, aMaterial, aMaterial, null), 1280, 6400);
        if ((!aMaterial.contains(SubTag.NO_SMASHING)) && (!aMaterial.contains(SubTag.BOUNCY))) {
            GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.FILE, 1, aMaterial, aMaterial.mHandleMaterial, null), GT_ModHandler.RecipeBits.MIRRORED | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"P", "P", "S", 'P', aPlateStack, 'S', OrePrefixes.stick.get(aMaterial.mHandleMaterial)});
        }
        if ((aMaterial != Materials.Stone) && (aMaterial != Materials.Flint)) {
            GT_ModHandler.addShapelessCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats((aMaterial.contains(SubTag.BOUNCY)) || (aMaterial.contains(SubTag.WOOD)) ? GT_MetaGenerated_Tool_01.SOFTHAMMER : GT_MetaGenerated_Tool_01.HARDHAMMER, 1, aMaterial, aMaterial.mHandleMaterial, null), GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{ToolDictNames.craftingToolHardHammer, OrePrefixes.stick.get(aMaterial.mHandleMaterial)});
            GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats((aMaterial.contains(SubTag.BOUNCY)) || (aMaterial.contains(SubTag.WOOD)) ? GT_MetaGenerated_Tool_01.SOFTHAMMER : GT_MetaGenerated_Tool_01.HARDHAMMER, 1, aMaterial, aMaterial.mHandleMaterial, null), GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"XX ", "XXS", "XX ", 'X', aMaterial == Materials.Wood ? OrePrefixes.plank.get(Materials.Wood) : aIngotStack, 'S', OrePrefixes.stick.get(aMaterial.mHandleMaterial)});
            GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats((aMaterial.contains(SubTag.BOUNCY)) || (aMaterial.contains(SubTag.WOOD)) ? GT_MetaGenerated_Tool_01.SOFTHAMMER : GT_MetaGenerated_Tool_01.HARDHAMMER, 1, aMaterial, aMaterial.mHandleMaterial, null), GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"XX ", "XXS", "XX ", 'X', aMaterial == Materials.Wood ? OrePrefixes.plank.get(Materials.Wood) : OrePrefixes.gem.get(aMaterial), 'S', OrePrefixes.stick.get(aMaterial.mHandleMaterial)});
        }
        if (!aNoWorking) {
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, aMaterial, 1L), tBits, new Object[]{"GG ", "G  ", "f  ", 'G', OrePrefixes.gem.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, aMaterial, 1L), tBits, new Object[]{"GG ", "f  ", "   ", 'G', OrePrefixes.gem.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, aMaterial, 1L), tBits, new Object[]{"GGG", "f  ", 'G', OrePrefixes.gem.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadPlow, aMaterial, 1L), tBits, new Object[]{"GG", "GG", " f", 'G', OrePrefixes.gem.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSaw, aMaterial, 1L), tBits, new Object[]{"GGf", 'G', OrePrefixes.gem.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSense, aMaterial, 1L), tBits, new Object[]{"GGG", " f ", "   ", 'G', OrePrefixes.gem.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, aMaterial, 1L), tBits, new Object[]{"fG", 'G', OrePrefixes.gem.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, aMaterial, 1L), tBits, new Object[]{" G", "fG", 'G', OrePrefixes.gem.get(aMaterial)});
        }
        if (aSpecialRecipeReq1) {
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, aMaterial, 1L), tBits, new Object[]{"PIh", "P  ", "f  ", 'P', aPlateStack, 'I', aIngotStack});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, aMaterial, 1L), tBits, new Object[]{"PIh", "f  ", 'P', aPlateStack, 'I', aIngotStack});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, aMaterial, 1L), tBits, new Object[]{"PII", "f h", 'P', aPlateStack, 'I', aIngotStack});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadPlow, aMaterial, 1L), tBits, new Object[]{"PP", "PP", "hf", 'P', aPlateStack});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSaw, aMaterial, 1L), tBits, new Object[]{"PP ", "fh ", 'P', aPlateStack, 'I', aIngotStack});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSense, aMaterial, 1L), tBits, new Object[]{"PPI", "hf ", 'P', aPlateStack, 'I', aIngotStack});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, aMaterial, 1L), tBits, new Object[]{"fPh", 'P', aPlateStack, 'I', aIngotStack});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, aMaterial, 1L), tBits, new Object[]{" P ", "fPh", 'P', aPlateStack, 'I', aIngotStack});
        }
        if (aSpecialRecipeReq2) {
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadBuzzSaw, aMaterial, 1L), tBits, new Object[]{"wXh", "X X", "fXx", 'X', aPlateStack});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadChainsaw, aMaterial, 1L), tBits, new Object[]{"SRS", "XhX", "SRS", 'X', aPlateStack, 'S', OrePrefixes.plate.get(Materials.Steel), 'R', OrePrefixes.ring.get(Materials.Steel)});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadDrill, aMaterial, 1L), tBits, new Object[]{"XSX", "XSX", "ShS", 'X', aPlateStack, 'S', OrePrefixes.plate.get(Materials.Steel)});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadUniversalSpade, aMaterial, 1L), tBits, new Object[]{"fX", 'X', OrePrefixes.toolHeadShovel.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadWrench, aMaterial, 1L), tBits, new Object[]{"hXW", "XRX", "WXd", 'X', aPlateStack, 'S', OrePrefixes.plate.get(Materials.Steel), 'R', OrePrefixes.ring.get(Materials.Steel), 'W', OrePrefixes.screw.get(Materials.Steel)});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.turbineBlade, aMaterial, 1L), tBits, new Object[]{"fPd", "SPS", " P ", 'P', aMaterial == Materials.Wood ? OrePrefixes.plank.get(aMaterial) : aPlateStack, 'R', OrePrefixes.ring.get(aMaterial), 'S', OrePrefixes.bolt.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHammer, aMaterial, 1L), tBits, new Object[]{"II ", "IIh", "II ", 'P', aPlateStack, 'I', aIngotStack});
        }
    }

    public void addCellRecipes(Materials aMaterial) {
        ItemStack aCellStack = GT_OreDictUnificator.get(OrePrefixes.cell, aMaterial, 1);
        ItemStack aPlasmaStack = GT_OreDictUnificator.get(OrePrefixes.cellPlasma, aMaterial, 1);
        if (aMaterial == Materials.Empty) {
            GT_ModHandler.removeRecipeByOutput(aCellStack);
            GT_ModHandler.removeRecipeByOutput(aPlasmaStack);
        } else {
            GT_Values.RA.addFuel(aPlasmaStack, GT_Utility.getFluidForFilledItem(aPlasmaStack, true) == null ? GT_Utility.getContainerItem(aPlasmaStack, true) : null, (int) Math.max(1024L, 1024L * aMaterial.getMass()), 4);
            GT_Values.RA.addVacuumFreezerRecipe(aPlasmaStack, aCellStack, (int) Math.max(aMaterial.getMass() * 2L, 1L));

            if (aMaterial.mFuelPower > 0) {
                GT_Values.RA.addFuel(aCellStack, GT_Utility.getFluidForFilledItem(aCellStack, true) == null ? GT_Utility.getContainerItem(aCellStack, true) : null, aMaterial.mFuelPower, aMaterial.mFuelType);
            }
            if (aMaterial.mMaterialList.size() > 0 && aMaterial.hasFlag(MaterialFlags.ELEC.bit) && aMaterial.hasFlag(MaterialFlags.CENT.bit)) {
                int tAllAmount = 0;
                MaterialStack tMat2;
                for (Iterator i$ = aMaterial.mMaterialList.iterator(); i$.hasNext(); tAllAmount = (int) (tAllAmount + tMat2.mAmount)) {
                    tMat2 = (MaterialStack) i$.next();
                }
                long tItemAmount = 0L;
                long tCapsuleCount = GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(aCellStack) * -tAllAmount;
                long tDensityMultiplier = aMaterial.getDensity() > 3628800L ? aMaterial.getDensity() / 3628800L : 1L;
                ArrayList<ItemStack> tList = new ArrayList();
                for (MaterialStack tMat : aMaterial.mMaterialList) {
                    if (tMat.mAmount > 0L) {
                        ItemStack tStack;
                        if (tMat.mMaterial == Materials.Air) {
                            tStack = ItemList.Cell_Air.get(tMat.mAmount * tDensityMultiplier / 2L);
                        } else {
                            tStack = GT_OreDictUnificator.get(OrePrefixes.dust, tMat.mMaterial, tMat.mAmount);
                            if (tStack == null) {
                                tStack = GT_OreDictUnificator.get(OrePrefixes.cell, tMat.mMaterial, tMat.mAmount);
                            }
                        }
                        if (tItemAmount + tMat.mAmount * 3628800L <= aCellStack.getMaxStackSize() * aMaterial.getDensity()) {
                            tItemAmount += tMat.mAmount * 3628800L;
                            if (tStack != null) {
                                ItemStack tmp397_395 = tStack;
                                tmp397_395.stackSize = ((int) (tmp397_395.stackSize * tDensityMultiplier));
                                while ((tStack.stackSize > 64) && (tCapsuleCount + GT_ModHandler.getCapsuleCellContainerCount(tStack) * 64 < 0L ? tList.size() < 5 : tList.size() < 6) && (tCapsuleCount + GT_ModHandler.getCapsuleCellContainerCount(tStack) * 64 <= 64L)) {
                                    tCapsuleCount += GT_ModHandler.getCapsuleCellContainerCount(tStack) * 64;
                                    tList.add(GT_Utility.copyAmount(64L, tStack));
                                    tStack.stackSize -= 64;
                                }
                                if ((tStack.stackSize > 0) && tCapsuleCount + GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(tStack) <= 64L) {
                                    if (tCapsuleCount + GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(tStack) < 0L ? tList.size() < 5 : tList.size() < 6) {
                                        tCapsuleCount += GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(tStack);
                                        tList.add(tStack);
                                    }
                                }
                            }
                        }
                    }
                }
                tItemAmount = (tItemAmount * tDensityMultiplier % aMaterial.getDensity() > 0L ? 1 : 0) + tItemAmount * tDensityMultiplier / aMaterial.getDensity();
                if (tList.size() > 0) {
                    if (aMaterial.hasFlag(MaterialFlags.ELEC.bit)) {
                        GT_Values.RA.addElectrolyzerRecipe(GT_Utility.copyAmount(tItemAmount, aCellStack), tCapsuleCount <= 0L ? 0 : (int) tCapsuleCount, tList.get(0), tList.size() >= 2 ? tList.get(1) : null, tList.size() >= 3 ? tList.get(2) : null, tList.size() >= 4 ? tList.get(3) : null, tList.size() >= 5 ? tList.get(4) : null, tCapsuleCount >= 0L ? tList.size() >= 6 ? tList.get(5) : null : ItemList.Cell_Empty.get(-tCapsuleCount), (int) Math.max(1L, Math.abs(aMaterial.getProtons() * 8L * tItemAmount)), Math.min(4, tList.size()) * 30);
                    }
                    if (aMaterial.hasFlag(MaterialFlags.CENT.bit)) {
                        GT_Values.RA.addCentrifugeRecipe(GT_Utility.copyAmount(tItemAmount, aCellStack), tCapsuleCount <= 0L ? 0 : (int) tCapsuleCount, tList.get(0), tList.size() >= 2 ? tList.get(1) : null, tList.size() >= 3 ? tList.get(2) : null, tList.size() >= 4 ? tList.get(3) : null, tList.size() >= 5 ? tList.get(4) : null, tCapsuleCount >= 0L ? tList.size() >= 6 ? tList.get(5) : null : ItemList.Cell_Empty.get(-tCapsuleCount), (int) Math.max(1L, Math.abs(aMaterial.getMass() * 2L * tItemAmount)));
                    }
                }
            }
        }
    }

    public void addOreRecipes(Materials aMaterial, boolean aIsRich) { //Ore, Crushed, CrushedCent, CrushedPure
        ArrayList<Materials> aAlreadyListedOres = new ArrayList(1000);
        Materials tMaterial = aMaterial.mOreReplacement;
        Materials tPrimaryByMaterial = null;
        int aMultiplier = Math.max(1, Math.max(1, GregTech_API.sOPStuff.get(ConfigCategories.Materials.oreprocessingoutputmultiplier, aMaterial.toString(), 1)) * (aIsRich ? 2 : 1));
        ItemStack aOreStack = GT_OreDictUnificator.get(OrePrefixes.ore, aMaterial, 1);
        ItemStack aIngotStack = GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mDirectSmelting, 1L);
        ItemStack aGemStack = GT_OreDictUnificator.get(OrePrefixes.gem, tMaterial, 1L);
        ItemStack aSmeltIntoStack = aIngotStack == null ? null : aMaterial.contains(SubTag.SMELTING_TO_GEM) ? GT_OreDictUnificator.get(OrePrefixes.gem, tMaterial.mDirectSmelting, GT_OreDictUnificator.get(OrePrefixes.crushedCentrifuged, tMaterial.mDirectSmelting, GT_OreDictUnificator.get(OrePrefixes.gem, tMaterial, GT_OreDictUnificator.get(OrePrefixes.crushedCentrifuged, tMaterial, 1L), 1L), 1L), 1L) : aIngotStack;
        ItemStack aDustStack = GT_OreDictUnificator.get(OrePrefixes.dust, tMaterial, aGemStack, 1L);
        ItemStack aCrushedPureStack = GT_OreDictUnificator.get(OrePrefixes.crushedPurified, tMaterial, aDustStack, 1L);
        ItemStack aCrushedStack = GT_OreDictUnificator.get(OrePrefixes.crushed, tMaterial, aMaterial.mOreMultiplier * aMultiplier);
        ItemStack aCrushedCentStack = GT_OreDictUnificator.get(OrePrefixes.crushedCentrifuged, aMaterial, 1); //TODO MULTI?
        ItemStack aDustStone = GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Stone, 1L);
        
        ItemStack tPrimaryByProduct = null;

        ArrayList<ItemStack> tByProductStacks = new ArrayList();

        for (Materials tMat : aMaterial.mOreByProducts) {
            ItemStack tByProduct = GT_OreDictUnificator.get(OrePrefixes.dust, tMat, 1L);
            if (tByProduct != null) tByProductStacks.add(tByProduct);
            if (tPrimaryByProduct == null) {
                tPrimaryByMaterial = tMat;
                tPrimaryByProduct = GT_OreDictUnificator.get(OrePrefixes.dust, tMat, 1L);
                if (GT_OreDictUnificator.get(OrePrefixes.dustSmall, tMat, 1L) == null) {
                    GT_OreDictUnificator.get(OrePrefixes.dustTiny, tMat, GT_OreDictUnificator.get(OrePrefixes.nugget, tMat, 2L), 2L);
                }
            }
            GT_OreDictUnificator.get(OrePrefixes.dust, tMat, 1L);
            if (GT_OreDictUnificator.get(OrePrefixes.dustSmall, tMat, 1L) == null) {
                GT_OreDictUnificator.get(OrePrefixes.dustTiny, tMat, GT_OreDictUnificator.get(OrePrefixes.nugget, tMat, 2L), 2L);
            }
            if (tMat.contains(SubTag.WASHING_MERCURY)) {
                GT_Values.RA.addChemicalBathRecipe(aCrushedStack, Materials.Mercury.getFluid(1000L), aCrushedPureStack/*TODO CHECK GT_OreDictUnificator.get(aPrefix == OrePrefixes.crushed ? OrePrefixes.crushedPurified : OrePrefixes.dustPure, aMaterial, 1L)*/, GT_OreDictUnificator.get(OrePrefixes.dust, tMat.mMacerateInto, 1L), aDustStone, new int[]{10000, 7000, 4000}, 800, 8);
            }
            if (tMat.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
                GT_Values.RA.addChemicalBathRecipe(aCrushedStack, Materials.SodiumPersulfate.getFluid(GT_Mod.gregtechproxy.mReenableSimplifiedChemicalRecipes ? 1000L : 100L), aCrushedPureStack/*TODO CHECK GT_OreDictUnificator.get(aPrefix == OrePrefixes.crushed ? OrePrefixes.crushedPurified : OrePrefixes.dustPure, aMaterial, 1L)*/, GT_OreDictUnificator.get(OrePrefixes.dust, tMat.mMacerateInto, 1L), aDustStone, new int[]{10000, 7000, 4000}, 800, 8);
            }
        }
        if (!tByProductStacks.isEmpty() && !aAlreadyListedOres.contains(aMaterial)) {
            aAlreadyListedOres.add(aMaterial);
            GT_Recipe.GT_Recipe_Map.sByProductList.addFakeRecipe(false, new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.ore, aMaterial, aOreStack, 1L)}, tByProductStacks.toArray(new ItemStack[tByProductStacks.size()]), null, null, null, null, 0, 0, 0);
        }

        if (tPrimaryByMaterial == null) tPrimaryByMaterial = tMaterial;
        if (tPrimaryByProduct == null) tPrimaryByProduct = aDustStack;
        boolean tHasSmelting = false;

        if (aSmeltIntoStack != null) {
            if ((aMaterial.mBlastFurnaceRequired) || (aMaterial.mDirectSmelting.mBlastFurnaceRequired)) {
                GT_ModHandler.removeFurnaceSmelting(aOreStack);
            } else {
                if (GT_Mod.gregtechproxy.mTEMachineRecipes) {
                    GT_ModHandler.addInductionSmelterRecipe(aOreStack, new ItemStack(net.minecraft.init.Blocks.sand, 1), GT_Utility.mul(aMultiplier * (aMaterial.contains(SubTag.INDUCTIONSMELTING_LOW_OUTPUT) ? 1 : 2) * aMaterial.mSmeltingMultiplier, aSmeltIntoStack), ItemList.TE_Slag_Rich.get(1L), 300 * aMultiplier, 10 * aMultiplier);
                    GT_ModHandler.addInductionSmelterRecipe(aOreStack, ItemList.TE_Slag_Rich.get(aMultiplier), GT_Utility.mul(aMultiplier * (aMaterial.contains(SubTag.INDUCTIONSMELTING_LOW_OUTPUT) ? 2 : 3) * aMaterial.mSmeltingMultiplier, aSmeltIntoStack), ItemList.TE_Slag.get(aMultiplier), 300 * aMultiplier, 95);
                }
                tHasSmelting = GT_ModHandler.addSmeltingRecipe(aOreStack, GT_Utility.copyAmount(aMultiplier * aMaterial.mSmeltingMultiplier, aSmeltIntoStack));
            }

            if (aMaterial.contains(SubTag.BLASTFURNACE_CALCITE_TRIPLE)) {
                GT_Values.RA.addBlastRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, aMultiplier), null, null, GT_Utility.mul(aMultiplier * 3 * aMaterial.mSmeltingMultiplier, aSmeltIntoStack), ItemList.TE_Slag.get(1L, GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.DarkAsh, 1L)), aSmeltIntoStack.stackSize * 500, 120, 1500);
                GT_Values.RA.addBlastRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Quicklime, aMultiplier * 3), null, null, GT_Utility.mul(aMultiplier * 3 * aMaterial.mSmeltingMultiplier, aSmeltIntoStack), ItemList.TE_Slag.get(1L, GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.DarkAsh, 1L)), aSmeltIntoStack.stackSize * 500, 120, 1500);
            } else if (aMaterial.contains(SubTag.BLASTFURNACE_CALCITE_DOUBLE)) {
                GT_Values.RA.addBlastRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, aMultiplier), null, null, GT_Utility.mul(aMultiplier * 2 * aMaterial.mSmeltingMultiplier, aSmeltIntoStack), ItemList.TE_Slag.get(1L, GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.DarkAsh, 1L)), aSmeltIntoStack.stackSize * 500, 120, 1500);
                GT_Values.RA.addBlastRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Quicklime, aMultiplier * 3), null, null, GT_Utility.mul(aMultiplier * 2 * aMaterial.mSmeltingMultiplier, aSmeltIntoStack), ItemList.TE_Slag.get(1L, GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.DarkAsh, 1L)), aSmeltIntoStack.stackSize * 500, 120, 1500);
            }
        }
        if (!tHasSmelting) {
            GT_ModHandler.addSmeltingRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.gem, tMaterial.mDirectSmelting, Math.max(1, aMultiplier * aMaterial.mSmeltingMultiplier / 2)));
        }
        if (aCrushedStack == null) {
            aCrushedStack = GT_OreDictUnificator.get(OrePrefixes.dustImpure, tMaterial, GT_Utility.copyAmount(aMaterial.mOreMultiplier * aMultiplier, aCrushedPureStack, aDustStack, aGemStack), aMaterial.mOreMultiplier * aMultiplier);
            //TODO NULLPOINTER GT_Values.RA.addForgeHammerRecipe(aOreStack, GT_Utility.copy(GT_Utility.copyAmount(aCrushedStack.stackSize, aGemStack), aCrushedStack), 16, 10);
            GT_ModHandler.addPulverisationRecipe(aOreStack, GT_Utility.mul(2L, aCrushedStack), tMaterial.contains(SubTag.PULVERIZING_CINNABAR) ? GT_OreDictUnificator.get(OrePrefixes.crushedCentrifuged, Materials.Cinnabar, GT_OreDictUnificator.get(OrePrefixes.gem, tPrimaryByMaterial, GT_Utility.copyAmount(1L, tPrimaryByProduct), 1L), 1L) : GT_OreDictUnificator.get(OrePrefixes.gem, tPrimaryByMaterial, GT_Utility.copyAmount(1L, tPrimaryByProduct), 1L), tPrimaryByProduct == null ? 0 : tPrimaryByProduct.stackSize * 10 * aMultiplier * aMaterial.mByProductMultiplier, GT_OreDictUnificator.getDust(OrePrefixes.ore.mSecondaryMaterial), 50, true);
        }
        if (aWashingMercury) {
            GT_Values.RA.addChemicalBathRecipe(aCrushedStack, Materials.Mercury.getFluid(1000L), aCrushedPureStack/*TODO CHECK GT_OreDictUnificator.get(aPrefix == OrePrefixes.crushed ? OrePrefixes.crushedPurified : OrePrefixes.dustPure, aMaterial, 1L)*/, GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial.mMacerateInto, 1L), aDustStone, new int[]{10000, 7000, 4000}, 800, 8);
        }
        if (aWashingSodium) {
            GT_Values.RA.addChemicalBathRecipe(aCrushedStack, Materials.SodiumPersulfate.getFluid(GT_Mod.gregtechproxy.mReenableSimplifiedChemicalRecipes ? 1000L : 100L), aCrushedPureStack/*TODO CHECK GT_OreDictUnificator.get(aPrefix == OrePrefixes.crushed ? OrePrefixes.crushedPurified : OrePrefixes.dustPure, aMaterial, 1L)*/, GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial.mMacerateInto, 1L), aDustStone, new int[]{10000, 7000, 4000}, 800, 8);
        }
        GT_Values.RA.addForgeHammerRecipe(GT_Utility.copyAmount(1L, aCrushedStack), GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial.mMacerateInto, 1L), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(GT_Utility.copyAmount(1L, aCrushedCentStack), GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial.mMacerateInto, 1L), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(GT_Utility.copyAmount(1L, aCrushedPureStack), GT_OreDictUnificator.get(OrePrefixes.dustPure, aMaterial.mMacerateInto, 1L), 10, 16);
        GT_Values.RA.addSifterRecipe(GT_Utility.copyAmount(1L, aCrushedPureStack), new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.gemExquisite, aMaterial, aGemStack, 1L), GT_OreDictUnificator.get(OrePrefixes.gemFlawless, aMaterial, aGemStack, 1L), aGemStack, GT_OreDictUnificator.get(OrePrefixes.gemFlawed, aMaterial, aGemStack, 1L), GT_OreDictUnificator.get(OrePrefixes.gemChipped, aMaterial, aGemStack, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, aGemStack, 1L)}, new int[]{100, 400, 1500, 2000, 4000, 5000}, 800, 16);
        GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, aCrushedStack), GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial.mMacerateInto, GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial.mMacerateInto, 1L), 1L), GT_OreDictUnificator.get(OrePrefixes.dust, GT_Utility.selectItemInList(0, aMaterial.mMacerateInto, aMaterial.mOreByProducts), 1L), 10, false);
        GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, aCrushedCentStack), GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial.mMacerateInto, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, GT_Utility.selectItemInList(2, aMaterial.mMacerateInto, aMaterial.mOreByProducts), 1L), 10, false);
        GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, aCrushedPureStack), GT_OreDictUnificator.get(OrePrefixes.dustPure, aMaterial.mMacerateInto, GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial.mMacerateInto, 1L), 1L), GT_OreDictUnificator.get(OrePrefixes.dust, GT_Utility.selectItemInList(1, aMaterial.mMacerateInto, aMaterial.mOreByProducts), 1L), 10, false);
        GT_ModHandler.addThermalCentrifugeRecipe(GT_Utility.copyAmount(1L, aCrushedStack), (int) Math.min(5000L, Math.abs(aMaterial.getMass() * 20L)), aCrushedPureStack/*TODO CHECK GT_OreDictUnificator.get(aPrefix == OrePrefixes.crushed ? OrePrefixes.crushedPurified : OrePrefixes.dustPure, aMaterial, 1L)*/, GT_OreDictUnificator.get(OrePrefixes.dustTiny, GT_Utility.selectItemInList(1, aMaterial.mMacerateInto, aMaterial.mOreByProducts), 1L), aDustStone);
        GT_ModHandler.addThermalCentrifugeRecipe(GT_Utility.copyAmount(1L, aCrushedPureStack), (int) Math.min(5000L, Math.abs(aMaterial.getMass() * 20L)), GT_OreDictUnificator.get(OrePrefixes.crushedCentrifuged, aMaterial.mMacerateInto, GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial.mMacerateInto, 1L), 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, GT_Utility.selectItemInList(1, aMaterial.mMacerateInto, aMaterial.mOreByProducts), 1L));
        GT_ModHandler.addOreWasherRecipe(GT_Utility.copyAmount(1L, aCrushedStack), 1000, aCrushedPureStack/*TODO CHECK GT_OreDictUnificator.get(aPrefix == OrePrefixes.crushed ? OrePrefixes.crushedPurified : OrePrefixes.dustPure, aMaterial, 1L)*/, GT_OreDictUnificator.get(OrePrefixes.dustTiny, GT_Utility.selectItemInList(0, aMaterial.mMacerateInto, aMaterial.mOreByProducts), 1L), aDustStone);
        GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustPure, aMaterial.mMacerateInto, 1L), tBits, new Object[]{"h", "X", 'X', aCrushedPureStack});
        GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial.mMacerateInto, 1L), tBits, new Object[]{"h", "X", 'X', aCrushedStack});
        GT_ModHandler.removeFurnaceSmelting(aCrushedStack);
        GT_ModHandler.removeFurnaceSmelting(aCrushedPureStack);
        GT_ModHandler.removeFurnaceSmelting(aCrushedCentStack);
        GT_ModHandler.removeFurnaceSmelting(GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 1));
        GT_ModHandler.removeFurnaceSmelting(GT_OreDictUnificator.get(OrePrefixes.dustPure, aMaterial, 1));
        ItemStack tStack = GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial.mDirectSmelting, aMaterial.mDirectSmelting == aMaterial ? 10L : 3L);
        if (tStack == null) {
            tStack = GT_OreDictUnificator.get(aMaterial.contains(SubTag.SMELTING_TO_GEM) ? OrePrefixes.gem : OrePrefixes.ingot, aMaterial.mDirectSmelting, 1L);
        }
        if ((tStack == null) && (!aMaterial.contains(SubTag.SMELTING_TO_GEM))) {
            tStack = GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mDirectSmelting, 1L);
        }
        GT_ModHandler.addSmeltingRecipe(aCrushedStack, tStack);
        GT_ModHandler.addSmeltingRecipe(aCrushedPureStack, tStack);
        GT_ModHandler.addSmeltingRecipe(aCrushedCentStack, tStack);
        GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 1), GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mDirectSmelting, 1L));
        GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustPure, aMaterial, 1), GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mDirectSmelting, 1L));
    }

    public void addGemRecipes(Materials aMaterial) {
        long aMaterialMass = aMaterial.getMass();
        ItemStack aGemStack = GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 1);
        ItemStack aChippedStack = GT_OreDictUnificator.get(OrePrefixes.gemChipped, aMaterial, 1);
        ItemStack aFlawedStack = GT_OreDictUnificator.get(OrePrefixes.gemFlawed, aMaterial, 1);
        ItemStack aFlawlessStack = GT_OreDictUnificator.get(OrePrefixes.gemFlawless, aMaterial, 1);
        ItemStack aExquisiteStack = GT_OreDictUnificator.get(OrePrefixes.gemExquisite, aMaterial, 1);
        ItemStack aPlateStack = GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1);
        ItemStack aBoltStack = GT_OreDictUnificator.get(OrePrefixes.bolt, aMaterial, 1);
        if (aMaterial.mFuelPower > 0) {
            GT_Values.RA.addFuel(aGemStack, null, aMaterial.mFuelPower * 2, aMaterial.mFuelType);
            GT_Values.RA.addFuel(aChippedStack, null, aMaterial.mFuelPower / 2, aMaterial.mFuelType);
            GT_Values.RA.addFuel(aFlawedStack, null, aMaterial.mFuelPower, aMaterial.mFuelType);
            GT_Values.RA.addFuel(aFlawlessStack, null, aMaterial.mFuelPower * 4, aMaterial.mFuelType);
            GT_Values.RA.addFuel(aExquisiteStack, null, aMaterial.mFuelPower * 8, aMaterial.mFuelType);
        }
        if (!OrePrefixes.block.isIgnored(aMaterial)) {
            GT_ModHandler.addCompressionRecipe(GT_Utility.copyAmount(9L, aGemStack), GT_OreDictUnificator.get(OrePrefixes.block, aMaterial, 1L));
        }
        if (!aNoSmelting) {
            GT_ModHandler.addSmeltingRecipe(aGemStack, GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mSmeltInto, 1L));
        }
        if (!aNoSmashing) {
            GT_Values.RA.addForgeHammerRecipe(aGemStack, aPlateStack, (int) Math.max(aMaterialMass, 1L), 16);
            GT_Values.RA.addBenderRecipe(aGemStack, aPlateStack, (int) Math.max(aMaterialMass * 2L, 1L), 24);
            GT_Values.RA.addBenderRecipe(GT_Utility.copyAmount(9L, aGemStack), GT_OreDictUnificator.get(OrePrefixes.plateDense, aMaterial, 1L), (int) Math.max(aMaterialMass * 9L, 1L), 96);
        } else {
            GT_Values.RA.addForgeHammerRecipe(aGemStack, GT_Utility.copyAmount(2L, aFlawedStack), 64, 16);
        }
        if (!aNoWorking) {
            GT_Values.RA.addLatheRecipe(aChippedStack, aBoltStack, aTinyDustStack, (int) Math.max(aMaterialMass, 1L), 8);
            GT_Values.RA.addLatheRecipe(aFlawedStack, GT_Utility.copyAmount(2L, aBoltStack), aSmallDustStack, (int) Math.max(aMaterialMass, 1L), 12);
            if (aMaterial.mUnificatable && (aMaterial.mMaterialInto == aMaterial)) {
                GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(2L, aGemStack), tBits, new Object[]{"h", "X", Character.valueOf('X'), aFlawlessStack});
                GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(2L, aChippedStack), tBits, new Object[]{"h", "X", Character.valueOf('X'), aFlawedStack});
                GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(2L, aFlawedStack), tBits, new Object[]{"h", "X", Character.valueOf('X'), aGemStack});
                GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(2L, aFlawlessStack), tBits, new Object[]{"h", "X", Character.valueOf('X'), aExquisiteStack});
                if (aMaterial.contains(SubTag.MORTAR_GRINDABLE) && GregTech_API.sRecipeFile.get(ConfigCategories.Tools.mortar, aMaterial.mName, true)) {
                    GT_ModHandler.addCraftingRecipe(aNormalDustStack, tBits, new Object[]{"X", "m", Character.valueOf('X'), aGemStack});
                    GT_ModHandler.addCraftingRecipe(aSmallDustStack, tBits, new Object[]{"X", "m", Character.valueOf('X'), aChippedStack});
                    GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(2L, aSmallDustStack), tBits, new Object[]{"X", "m", Character.valueOf('X'), aFlawedStack});
                    GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(2L, aNormalDustStack), tBits, new Object[]{"X", "m", Character.valueOf('X'), aFlawlessStack});
                    GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(4L, aNormalDustStack), tBits, new Object[]{"X", "m", Character.valueOf('X'), aExquisiteStack});
                }
                if (aMaterial.contains(SubTag.SMELTING_TO_GEM)) {
                    GT_ModHandler.addCraftingRecipe(aGemStack, tBits, new Object[]{"XXX", "XXX", "XXX", Character.valueOf('X'), OrePrefixes.nugget.get(aMaterial)});
                }
            }
        } else {
            GT_Values.RA.addLatheRecipe(aGemStack, GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial, 1L), GT_Utility.copyAmount(2L, aSmallDustStack), (int) Math.max(aMaterialMass, 1L), 16);
        }
        GT_Values.RA.addForgeHammerRecipe(aFlawedStack, GT_Utility.copyAmount(2L, aChippedStack), 64, 16);
        GT_Values.RA.addForgeHammerRecipe(aFlawlessStack, GT_Utility.copyAmount(2L, aGemStack), 64, 16);
        //TODO NPE GT_RecipeRegistrator.registerUsagesForMaterials(aGemStack, aPlateStack.toString(), !aNoSmashing);
        if (aMaterial.mTransparent) {
            ItemStack aLensStack = GT_OreDictUnificator.get(OrePrefixes.lens, aMaterial, 1L);
            GT_Values.RA.addLatheRecipe(aPlateStack, aLensStack, aSmallDustStack, (int) Math.max(aMaterial.getMass() / 2L, 1L), 480);
            GT_Values.RA.addLatheRecipe(aExquisiteStack, aLensStack, GT_Utility.copyAmount(2L, aNormalDustStack), (int) Math.max(aMaterial.getMass() , 1L), 24);
            GregTech_API.registerCover(aLensStack, new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_LENS, aMaterial.mRGBa, false)), new GT_Cover_Lens(aMaterial.mColor.mIndex));
        }
    }

    public void addShapingRecipes(Materials aMaterial) {
        if ((aMaterial == Materials.Glass || aIngotStack != null) && (!aMaterial.contains(SubTag.NO_SMELTING))) {
            long aMaterialMass = aMaterial.getMass();
            int tAmount = (int) (OrePrefixes.ingot.mMaterialAmount / 3628800L);
            if ((tAmount > 0) && (tAmount <= 64) && (OrePrefixes.ingot.mMaterialAmount % 3628800L == 0L)) {
                int tVoltageMultiplier = aMaterial.mBlastFurnaceTemp >= 2800 ? 64 : 16;

                if (aMaterial.contains(SubTag.NO_SMASHING)) {
                    tVoltageMultiplier /= 4;
                }

                if (!OrePrefixes.block.isIgnored(aMaterial.mSmeltInto)) {
                    GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(9L, aIngotStack), ItemList.Shape_Extruder_Block.get(0L), GT_OreDictUnificator.get(OrePrefixes.block, aMaterial.mSmeltInto, tAmount), 10 * tAmount, 8 * tVoltageMultiplier);
                    GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(9L, aIngotStack), ItemList.Shape_Mold_Block.get(0L), GT_OreDictUnificator.get(OrePrefixes.block, aMaterial.mSmeltInto, tAmount), 5 * tAmount, 4 * tVoltageMultiplier);
                }
                if (aMaterial != aMaterial.mSmeltInto) {
                    GT_Values.RA.addExtruderRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1), ItemList.Shape_Extruder_Ingot.get(0L), GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mSmeltInto, tAmount), 10, 4 * tVoltageMultiplier);
                }

                GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Pipe_Tiny.get(0L), GT_OreDictUnificator.get(OrePrefixes.pipeTiny, aMaterial.mSmeltInto, tAmount * 2), 4 * tAmount, 8 * tVoltageMultiplier);
                if (!(aMaterial == Materials.Redstone || aMaterial == Materials.Glowstone)) {
                    GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Pipe_Small.get(0L), GT_OreDictUnificator.get(OrePrefixes.pipeSmall, aMaterial.mSmeltInto, tAmount), 8 * tAmount, 8 * tVoltageMultiplier);
                    GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(3L, aIngotStack), ItemList.Shape_Extruder_Pipe_Medium.get(0L), GT_OreDictUnificator.get(OrePrefixes.pipeMedium, aMaterial.mSmeltInto, tAmount), 24 * tAmount, 8 * tVoltageMultiplier);
                    GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(6L, aIngotStack), ItemList.Shape_Extruder_Pipe_Large.get(0L), GT_OreDictUnificator.get(OrePrefixes.pipeLarge, aMaterial.mSmeltInto, tAmount), 48 * tAmount, 8 * tVoltageMultiplier);
                }
                GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(12L, aIngotStack), ItemList.Shape_Extruder_Pipe_Huge.get(0L), GT_OreDictUnificator.get(OrePrefixes.pipeHuge, aMaterial.mSmeltInto, tAmount), 96 * tAmount, 8 * tVoltageMultiplier);
                GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Plate.get(0L), GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * tAmount, tAmount), 8 * tVoltageMultiplier);
                if (tAmount * 2 <= 64) {
                    GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Rod.get(0L), GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial.mSmeltInto, tAmount * 2), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 6 * tVoltageMultiplier);
                    GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Wire.get(0L), GT_OreDictUnificator.get(OrePrefixes.wireGt01, aMaterial.mSmeltInto, tAmount * 2), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 6 * tVoltageMultiplier);
                }
                if (tAmount * 8 <= 64) {
                    GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Bolt.get(0L), GT_OreDictUnificator.get(OrePrefixes.bolt, aMaterial.mSmeltInto, tAmount * 8), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 8 * tVoltageMultiplier);
                }
                if (tAmount * 4 <= 64) {
                    GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Ring.get(0L), GT_OreDictUnificator.get(OrePrefixes.ring, aMaterial.mSmeltInto, tAmount * 4), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 6 * tVoltageMultiplier);
                }
                GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(2L, aIngotStack), ItemList.Shape_Extruder_Sword.get(0L), GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 8 * tVoltageMultiplier);
                GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(3L, aIngotStack), ItemList.Shape_Extruder_Pickaxe.get(0L), GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 3L * tAmount, tAmount), 8 * tVoltageMultiplier);
                GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Shovel.get(0L), GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * tAmount, tAmount), 8 * tVoltageMultiplier);
                GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(3L, aIngotStack), ItemList.Shape_Extruder_Axe.get(0L), GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 3L * tAmount, tAmount), 8 * tVoltageMultiplier);
                GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(2L, aIngotStack), ItemList.Shape_Extruder_Hoe.get(0L), GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 8 * tVoltageMultiplier);
                GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(6L, aIngotStack), ItemList.Shape_Extruder_Hammer.get(0L), GT_OreDictUnificator.get(OrePrefixes.toolHeadHammer, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 6L * tAmount, tAmount), 8 * tVoltageMultiplier);
                GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(2L, aIngotStack), ItemList.Shape_Extruder_File.get(0L), GT_OreDictUnificator.get(OrePrefixes.toolHeadFile, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 8 * tVoltageMultiplier);
                GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(2L, aIngotStack), ItemList.Shape_Extruder_Saw.get(0L), GT_OreDictUnificator.get(OrePrefixes.toolHeadSaw, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 8 * tVoltageMultiplier);
                GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(4L, aIngotStack), ItemList.Shape_Extruder_Gear.get(0L), GT_OreDictUnificator.get(OrePrefixes.gearGt, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 5L * tAmount, tAmount), 8 * tVoltageMultiplier);

                GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(2L, aIngotStack), ItemList.Shape_Mold_Plate.get(0L), GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 2 * tVoltageMultiplier);
                GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(8L, aIngotStack), ItemList.Shape_Mold_Gear.get(0L), GT_OreDictUnificator.get(OrePrefixes.gearGt, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 10L * tAmount, tAmount), 2 * tVoltageMultiplier);
            }
        }
    }

    public void addSolidRecipes(Materials aMaterial) { //Nugget & Ingot Processing
        long aMaterialMass = aMaterial.getMass();
        if (aMaterial.mFuelPower > 0) {
            GT_Values.RA.addFuel(GT_Utility.copyAmount(1L, aIngotStack), null, aMaterial.mFuelPower, aMaterial.mFuelType);
        }
        if (aMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Ingot.get(0L), aMaterial.getMolten(144L), aIngotStack, 32, 8);
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Nugget.get(0L), aMaterial.getMolten(16L), aNuggetStack, 16, 4);
        }
        GT_RecipeRegistrator.registerReverseFluidSmelting(aIngotStack, aMaterial, OrePrefixes.ingot.mMaterialAmount, null);
        GT_RecipeRegistrator.registerReverseMacerating(aIngotStack, aMaterial, OrePrefixes.ingot.mMaterialAmount, null, null, null, false);
        GT_RecipeRegistrator.registerReverseFluidSmelting(aNuggetStack, aMaterial, OrePrefixes.nugget.mMaterialAmount, null);
        GT_RecipeRegistrator.registerReverseMacerating(aNuggetStack, aMaterial, OrePrefixes.nugget.mMaterialAmount, null, null, null, false);
        if (aMaterial.mSmeltInto.mArcSmeltInto != aMaterial) {
            GT_RecipeRegistrator.registerReverseArcSmelting(aIngotStack, aMaterial, OrePrefixes.ingot.mMaterialAmount, null, null, null);
        }
        ItemStack tStack = GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial.mMacerateInto, 1L);
        if (tStack != null && (aMaterial.mBlastFurnaceRequired || aNoSmelting)) {
            GT_ModHandler.removeFurnaceSmelting(tStack);
        }
        if (aMaterial.mUnificatable && (aMaterial.mMaterialInto == aMaterial) && !aNoWorking) {
            if (!aMaterial.contains(SubTag.SMELTING_TO_GEM)) {
                GT_ModHandler.addCraftingRecipe(aIngotStack, tBits, new Object[]{"XXX", "XXX", "XXX", Character.valueOf('X'), aNuggetStack});
            }
            if ((aMaterial.contains(SubTag.MORTAR_GRINDABLE)) && (GregTech_API.sRecipeFile.get(ConfigCategories.Tools.mortar, aMaterial.mName, true))) {
                GT_ModHandler.addCraftingRecipe(aNormalDustStack, tBits, new Object[]{"X", "m", Character.valueOf('X'), aIngotStack});
            }
        }
        if (!aNoSmashing) {
            GT_Values.RA.addForgeHammerRecipe(GT_Utility.copyAmount(3L, aIngotStack), GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 2L), (int) Math.max(aMaterialMass, 1L), 16);
            GT_Values.RA.addBenderRecipe(aIngotStack, GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1L), (int) Math.max(aMaterialMass, 1L), 24);
            GT_Values.RA.addBenderRecipe(GT_Utility.copyAmount(9L, aIngotStack), GT_OreDictUnificator.get(OrePrefixes.plateDense, aMaterial, 1L), (int) Math.max(aMaterialMass * 9L, 1L), 96);
        }
        GT_RecipeRegistrator.registerUsagesForMaterials(aIngotStack, OrePrefixes.plate.get(aMaterial).toString(), !aNoSmashing);
        GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(9L, aNuggetStack), aMaterial.contains(SubTag.SMELTING_TO_GEM) ? ItemList.Shape_Mold_Ball.get(0L) : ItemList.Shape_Mold_Ingot.get(0L), GT_OreDictUnificator.get(aMaterial.contains(SubTag.SMELTING_TO_GEM) ? OrePrefixes.gem : OrePrefixes.ingot, aMaterial.mSmeltInto, 1L), 200, 2);
        if (!aNoSmelting) {
            GT_Values.RA.addAlloySmelterRecipe(aIngotStack, ItemList.Shape_Mold_Nugget.get(0L), GT_Utility.copyAmount(9L, aNuggetStack), 100, 1);
        }
    }

    public void addDustRecipes(Materials aMaterial) { //Tiny, Small, Normal, Impure & Pure Dust Processing
        int aBlastDuration = (int) Math.max(aMaterial.getMass() / 40L, 1L) * aMaterial.mBlastFurnaceTemp;
        ItemStack aPureDust = GT_OreDictUnificator.get(OrePrefixes.dustPure, aMaterial, 1);
        ItemStack aImpureDust = GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 1);
        GT_Values.RA.addBoxingRecipe(GT_Utility.copyAmount(4L, aSmallDustStack), ItemList.Schematic_Dust.get(0L), aNormalDustStack, 100, 4);
        GT_Values.RA.addBoxingRecipe(GT_Utility.copyAmount(9L, aTinyDustStack), ItemList.Schematic_Dust.get(0L), aNormalDustStack, 100, 4);
        GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial.mMacerateInto, 1L), tBits, new Object[]{"h", "X", 'X', OrePrefixes.crushedCentrifuged.get(aMaterial)});
        GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(4L, aSmallDustStack), tBits, new Object[]{" X", "  ", 'X', aNormalDustStack});
        GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(9L, aTinyDustStack), tBits, new Object[]{"X ", "  ", 'X', aNormalDustStack});
        GT_ModHandler.addCraftingRecipe(aNormalDustStack, tBits, new Object[]{"XX", "XX", 'X', aSmallDustStack});
        GT_ModHandler.addCraftingRecipe(aNormalDustStack, tBits, new Object[]{"XXX", "XXX", "XXX", 'X', aTinyDustStack});
        if (!aMaterial.mBlastFurnaceRequired) {
            if (aMaterial.mSmeltInto != null) {
                GT_RecipeRegistrator.registerReverseFluidSmelting(aNormalDustStack, aMaterial, OrePrefixes.dust.mMaterialAmount, null);
                GT_RecipeRegistrator.registerReverseFluidSmelting(aSmallDustStack, aMaterial, OrePrefixes.dustSmall.mMaterialAmount, null);
                GT_RecipeRegistrator.registerReverseFluidSmelting(aTinyDustStack, aMaterial, OrePrefixes.dustTiny.mMaterialAmount, null);
            }
            GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copyAmount(4L, aSmallDustStack), ItemList.Shape_Mold_Ingot.get(0L), GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mSmeltInto, 1L), 130, 3, true);
            if (!aNoSmelting) {
                GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, aTinyDustStack), GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial.mSmeltInto, 1L));
                GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copyAmount(9L, aTinyDustStack), ItemList.Shape_Mold_Ingot.get(0L), GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mSmeltInto, 1L), 130, 3, true);
            }
            if (aMaterial.mSmeltInto != null && aMaterial.mSmeltInto.mArcSmeltInto != aMaterial) {
                GT_RecipeRegistrator.registerReverseArcSmelting(GT_Utility.copyAmount(1L, aNormalDustStack), aMaterial, OrePrefixes.dust.mMaterialAmount, null, null, null);
                GT_RecipeRegistrator.registerReverseArcSmelting(GT_Utility.copyAmount(1L, aSmallDustStack), aMaterial, OrePrefixes.dustSmall.mMaterialAmount, null, null, null);
                GT_RecipeRegistrator.registerReverseArcSmelting(GT_Utility.copyAmount(1L, aTinyDustStack), aMaterial, OrePrefixes.dustTiny.mMaterialAmount, null, null, null);
            }
        } else {
            //TODO MOVE TO HOT INGOT SECTION?
            ItemStack aBlastStack = aMaterial.mBlastFurnaceTemp > 1750 ? GT_OreDictUnificator.get(OrePrefixes.ingotHot, aMaterial.mSmeltInto, GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mSmeltInto, 1L), 1L) : GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mSmeltInto, 1L);
            GT_Values.RA.addBlastRecipe(GT_Utility.copyAmount(4L, aSmallDustStack), null, null, null, aBlastStack, null, aBlastDuration, 120, aMaterial.mBlastFurnaceTemp);
            if (!aNoSmelting) {
                GT_Values.RA.addBlastRecipe(GT_Utility.copyAmount(9L, aTinyDustStack), null, null, null, aBlastStack, null, aBlastDuration, 120, aMaterial.mBlastFurnaceTemp);
                GT_ModHandler.removeFurnaceSmelting(aTinyDustStack);
            }
        }
        if (aMaterial.mFuelPower > 0) {
            GT_Values.RA.addFuel(GT_Utility.copyAmount(1L, aNormalDustStack), null, aMaterial.mFuelPower, aMaterial.mFuelType);
        }
        if (GT_Utility.getFluidForFilledItem(GT_OreDictUnificator.get(OrePrefixes.cell, aMaterial, 1L), true) == null) {
            GT_Values.RA.addCannerRecipe(aNormalDustStack, ItemList.Cell_Empty.get(1L), GT_OreDictUnificator.get(OrePrefixes.cell, aMaterial, 1L), null, 100, 1);
        }
        if (aMaterial.contains(SubTag.CRYSTALLISABLE)) {
            FluidStack aWaterStack = Materials.Water.getFluid(200L);
            FluidStack aDistilledStack = GT_ModHandler.getDistilledWater(200L);
            ItemStack aGemStack = GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 1);
            GT_Values.RA.addAutoclaveRecipe(aNormalDustStack, aWaterStack, aGemStack, 7000, 2000, 24);
            GT_Values.RA.addAutoclaveRecipe(aNormalDustStack, aDistilledStack, aGemStack, 9000, 1500, 24);
            GT_Values.RA.addAutoclaveRecipe(aPureDust, aWaterStack, aGemStack, 9000, 2000, 24);
            GT_Values.RA.addAutoclaveRecipe(aPureDust, aDistilledStack, aGemStack, 9500, 1500, 24);
            GT_Values.RA.addAutoclaveRecipe(aImpureDust, aWaterStack, aGemStack, 9000, 2000, 24);
            GT_Values.RA.addAutoclaveRecipe(aImpureDust, aDistilledStack, aGemStack, 9500, 1500, 24);
        }
        if (aMaterial.contains(SubTag.ELECTROMAGNETIC_SEPERATION_GOLD))
            GT_Values.RA.addElectromagneticSeparatorRecipe(GT_Utility.copyAmount(1L, aPureDust), GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Gold, 1L), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Gold, 1L), new int[]{10000, 4000, 2000}, 400, 24);
        if (aMaterial.contains(SubTag.ELECTROMAGNETIC_SEPERATION_IRON))
            GT_Values.RA.addElectromagneticSeparatorRecipe(GT_Utility.copyAmount(1L, aPureDust), GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Iron, 1L), new int[]{10000, 4000, 2000}, 400, 24);
        if (aMaterial.contains(SubTag.ELECTROMAGNETIC_SEPERATION_NEODYMIUM)) {
            GT_Values.RA.addElectromagneticSeparatorRecipe(GT_Utility.copyAmount(1L, aPureDust), GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Neodymium, 1L), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Neodymium, 1L), new int[]{10000, 4000, 2000}, 400, 24);
        }
        ItemStack aDustSmeltInto = aMaterial.mSmeltInto.getIngots(1);
        if (aDustSmeltInto != null && !aNoSmelting) {
            if (aMaterial.mBlastFurnaceRequired) {
                GT_ModHandler.removeFurnaceSmelting(aNormalDustStack);
                GT_Values.RA.addBlastRecipe(GT_Utility.copyAmount(1L, aNormalDustStack), null, null, null, aMaterial.mBlastFurnaceTemp > 1750 ? GT_OreDictUnificator.get(OrePrefixes.ingotHot, aMaterial.mSmeltInto, aDustSmeltInto, 1L) : GT_Utility.copyAmount(1L, aDustSmeltInto), null, aBlastDuration, 120, aMaterial.mBlastFurnaceTemp);
                if (aMaterial.mBlastFurnaceTemp <= 1000) {
                    //TODO ?GT_ModHandler.addRCBlastFurnaceRecipe(GT_Utility.copyAmount(1L, aNormalDustStack), GT_Utility.copyAmount(1L, aDustSmeltInto), aMaterial.mBlastFurnaceTemp);
                }
            } else {
                GT_ModHandler.addSmeltingRecipe(aNormalDustStack, aDustSmeltInto);
            }
        } else if (!aNoWorking) {
            if ((!OrePrefixes.block.isIgnored(aMaterial)) && (null == GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 1L))) {
                GT_ModHandler.addCompressionRecipe(GT_Utility.copyAmount(9L, aNormalDustStack), GT_OreDictUnificator.get(OrePrefixes.block, aMaterial, 1L));
            }
            if (((OrePrefixes.block.isIgnored(aMaterial)) || (null == GT_OreDictUnificator.get(OrePrefixes.block, aMaterial, 1L))) && (aMaterial != Materials.GraniteRed) && (aMaterial != Materials.GraniteBlack) && (aMaterial != Materials.Glass) && (aMaterial != Materials.Obsidian) && (aMaterial != Materials.Glowstone) && (aMaterial != Materials.Paper)) {
                GT_ModHandler.addCompressionRecipe(GT_Utility.copyAmount(1L, aNormalDustStack), GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1L));
            }
        }
        if (aMaterial.mMaterialList.size() > 0 && aMaterial.hasFlag(MaterialFlags.CENT.bit) && aMaterial.hasFlag(MaterialFlags.ELEC.bit)) {
            long tItemAmount = 0L;
            long tCapsuleCount = 0L;
            long tDensityMultiplier = aMaterial.getDensity() > 3628800L ? aMaterial.getDensity() / 3628800L : 1L;
            ArrayList<ItemStack> tList = new ArrayList<>();
            ItemStack tDustStack;
            for (MaterialStack tMat : aMaterial.mMaterialList) {
                if (tMat.mAmount > 0L) {
                    if (tMat.mMaterial == Materials.Air) {
                        tDustStack = ItemList.Cell_Air.get(tMat.mAmount / 2L);
                    } else {
                        tDustStack = GT_OreDictUnificator.get(OrePrefixes.dust, tMat.mMaterial, tMat.mAmount);
                        if (tDustStack == null)
                            tDustStack = GT_OreDictUnificator.get(OrePrefixes.cell, tMat.mMaterial, tMat.mAmount);
                    }
                    if (tItemAmount + tMat.mAmount * 3628800L <= aNormalDustStack.getMaxStackSize() * aMaterial.getDensity()) {
                        tItemAmount += tMat.mAmount * 3628800L;
                        if (tDustStack != null) {
                            tDustStack.stackSize = ((int) (tDustStack.stackSize * tDensityMultiplier));
                            while ((tDustStack.stackSize > 64) && (tList.size() < 6) && (tCapsuleCount + GT_ModHandler.getCapsuleCellContainerCount(tDustStack) * 64 <= 64L)) {
                                tCapsuleCount += GT_ModHandler.getCapsuleCellContainerCount(tDustStack) * 64;
                                tList.add(GT_Utility.copyAmount(64L, tDustStack));
                                tDustStack.stackSize -= 64;
                            }
                            if ((tDustStack.stackSize > 0) && (tList.size() < 6) && (tCapsuleCount + GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(tDustStack) <= 64L)) {
                                tCapsuleCount += GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(tDustStack);
                                tList.add(tDustStack);
                            }
                        }
                    }
                }
            }
            tItemAmount = (tItemAmount * tDensityMultiplier % aMaterial.getDensity() > 0L ? 1 : 0) + tItemAmount * tDensityMultiplier / aMaterial.getDensity();
            if (tList.size() > 0) {
                FluidStack tFluid = null;
                int tList_sS = tList.size();
                for (int i = 0; i < tList_sS; i++) {
                    if ((!ItemList.Cell_Air.isStackEqual(tList.get(i))) && ((tFluid = GT_Utility.getFluidForFilledItem(tList.get(i), true)) != null)) {
                        tFluid.amount *= tList.get(i).stackSize;
                        tCapsuleCount -= GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(tList.get(i));
                        tList.remove(i);
                        break;
                    }
                }
                if (aMaterial.hasFlag(MaterialFlags.ELEC.bit)) {
                    GT_Values.RA.addElectrolyzerRecipe(GT_Utility.copyAmount(tItemAmount, aNormalDustStack), tCapsuleCount > 0L ? ItemList.Cell_Empty.get(tCapsuleCount) : null, null, tFluid, tList.size() < 1 ? null : tList.get(0), tList.size() < 2 ? null : tList.get(1), tList.size() < 3 ? null : tList.get(2), tList.size() < 4 ? null : tList.get(3), tList.size() < 5 ? null : tList.get(4), tList.size() < 6 ? null : tList.get(5), null, (int) Math.max(1L, Math.abs(aMaterial.getProtons() * 2L * tItemAmount)), Math.min(4, tList.size()) * 30);
                }
                if (aMaterial.hasFlag(MaterialFlags.CENT.bit)) {
                    GT_Values.RA.addCentrifugeRecipe(GT_Utility.copyAmount(tItemAmount, aNormalDustStack), tCapsuleCount > 0L ? ItemList.Cell_Empty.get(tCapsuleCount) : null, null, tFluid, tList.size() < 1 ? null : tList.get(0), tList.size() < 2 ? null : tList.get(1), tList.size() < 3 ? null : tList.get(2), tList.size() < 4 ? null : tList.get(3), tList.size() < 5 ? null : tList.get(4), tList.size() < 6 ? null : tList.get(5), null, (int) Math.max(1L, Math.abs(aMaterial.getMass() * 4L * tItemAmount)), Math.min(4, tList.size()) * 5);
                }
            }
        }

        for (OrePrefixes aPrefix : new ArrayList<>(Arrays.asList(new OrePrefixes[]{OrePrefixes.dustImpure, OrePrefixes.dustPure}))) {
            ItemStack aStack = aPrefix == OrePrefixes.dustImpure ? GT_OreDictUnificator.get(OrePrefixes.dustImpure, 1) : GT_OreDictUnificator.get(OrePrefixes.dustPure, 1);

            //Pure/Impure Dust
            Materials tByProduct = GT_Utility.selectItemInList(aPrefix == OrePrefixes.dustPure ? 1 : 0, aMaterial, aMaterial.mOreByProducts);
            ItemStack tImpureStack = GT_OreDictUnificator.get(OrePrefixes.dustTiny, tByProduct, GT_OreDictUnificator.get(OrePrefixes.nugget, tByProduct, 1L), 1L);
            if (tImpureStack == null) {
                tImpureStack = GT_OreDictUnificator.get(OrePrefixes.dustSmall, tByProduct, 1L);
                if (tImpureStack == null) {
                    tImpureStack = GT_OreDictUnificator.get(OrePrefixes.dust, tByProduct, GT_OreDictUnificator.get(OrePrefixes.gem, tByProduct, 1L), 1L);
                    if (tImpureStack == null) {
                        tImpureStack = GT_OreDictUnificator.get(OrePrefixes.cell, tByProduct, 1L);
                        if (tImpureStack == null) {
                            GT_Values.RA.addCentrifugeRecipe(aStack, 0, aNormalDustStack, null, null, null, null, null, (int) Math.max(1L, aMaterial.getMass()));
                        } else {
                            FluidStack tFluid = GT_Utility.getFluidForFilledItem(tImpureStack, true);
                            if (tFluid == null) {
                                GT_Values.RA.addCentrifugeRecipe(GT_Utility.copyAmount(9L, aStack), 1, GT_Utility.copyAmount(9L, aNormalDustStack), tImpureStack, null, null, null, null, (int) Math.max(1L, aMaterial.getMass() * 72L));
                            } else {
                                tFluid.amount /= 10;
                                GT_Values.RA.addCentrifugeRecipe(aStack, null, null, tFluid, aNormalDustStack, null, null, null, null, null, null, (int) Math.max(1L, aMaterial.getMass() * 8L), 5);
                            }
                        }
                    } else {
                        GT_Values.RA.addCentrifugeRecipe(GT_Utility.copyAmount(9L, aStack), 0, GT_Utility.copyAmount(9L, aNormalDustStack), tImpureStack, null, null, null, null, (int) Math.max(1L, aMaterial.getMass() * 72L));
                    }
                } else {
                    GT_Values.RA.addCentrifugeRecipe(GT_Utility.copyAmount(2L, aStack), 0, GT_Utility.copyAmount(2L, aNormalDustStack), tImpureStack, null, null, null, null, (int) Math.max(1L, aMaterial.getMass() * 16L));
                }
            } else {
                GT_Values.RA.addCentrifugeRecipe(aStack, 0, aNormalDustStack, tImpureStack, null, null, null, null, (int) Math.max(1L, aMaterial.getMass() * 8L));
            }
        }
    }

    public void addWireRecipes(Materials aMaterial) {
        ItemStack aIngotStack = GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1);
        ItemStack aPlateStack = GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1);
        ItemStack aFineWireStack = GT_OreDictUnificator.get(OrePrefixes.wireFine, aMaterial, 1);
        ItemStack aWire1Stack = GT_OreDictUnificator.get(OrePrefixes.wireGt01, aMaterial, 1);
        ItemStack aWire2Stack = GT_OreDictUnificator.get(OrePrefixes.wireGt02, aMaterial, 1);
        ItemStack aWire4Stack = GT_OreDictUnificator.get(OrePrefixes.wireGt04, aMaterial, 1);
        ItemStack aWire8Stack = GT_OreDictUnificator.get(OrePrefixes.wireGt08, aMaterial, 1);
        ItemStack aWire12Stack = GT_OreDictUnificator.get(OrePrefixes.wireGt12, aMaterial, 1);
        ItemStack aWire16Stack = GT_OreDictUnificator.get(OrePrefixes.wireGt16, aMaterial, 1);
        if (!aNoSmashing) {
            GT_Values.RA.addWiremillRecipe(aWire1Stack, GT_Utility.copyAmount(4L, aFineWireStack), 200, 8);
            GT_Values.RA.addWiremillRecipe(aIngotStack, GT_Utility.copy(GT_Utility.copyAmount(2L, aWire1Stack), GT_Utility.copyAmount(8L, aFineWireStack)), 100, 4);
            GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial, 1L), GT_Utility.copy(aWire1Stack, GT_Utility.copyAmount(4L, aFineWireStack)), 50, 4);
        }
        if (aMaterial.mUnificatable && (aMaterial.mMaterialInto == aMaterial) && !aMaterial.contains(SubTag.NO_WORKING)) {
            GT_ModHandler.addCraftingRecipe(aWire1Stack, GT_Proxy.tBits, new Object[]{"Xx", Character.valueOf('X'), aPlateStack});
        }
        GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(2L, aWire1Stack), ItemList.Circuit_Integrated.getWithDamage(0L, 2L), aWire2Stack, 150, 8);
        GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(4L, aWire1Stack), ItemList.Circuit_Integrated.getWithDamage(0L, 4L), aWire4Stack, 200, 8);
        GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(8L, aWire1Stack), ItemList.Circuit_Integrated.getWithDamage(0L, 8L), aWire8Stack, 300, 8);
        GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(12L, aWire1Stack), ItemList.Circuit_Integrated.getWithDamage(0L, 12L), aWire12Stack, 400, 8);
        GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(16L, aWire1Stack), ItemList.Circuit_Integrated.getWithDamage(0L, 16L), aWire16Stack, 500, 8);

        GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(2L, aWire1Stack), new Object[]{aOreDictName});
        GT_ModHandler.addShapelessCraftingRecipe(aWire2Stack, new Object[]{aWire1Stack, aWire1Stack});

        GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(4L, aWire1Stack), new Object[]{aOreDictName});
        GT_ModHandler.addShapelessCraftingRecipe(aWire4Stack, new Object[]{aWire1Stack, aWire1Stack, aWire1Stack, aWire1Stack});
        GT_ModHandler.addShapelessCraftingRecipe(aWire4Stack, new Object[]{aWire2Stack, aWire2Stack});

        GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(8L, aWire1Stack), new Object[]{aOreDictName});
        GT_ModHandler.addShapelessCraftingRecipe(aWire8Stack, new Object[]{aWire1Stack, aWire1Stack, aWire1Stack, aWire1Stack, aWire1Stack, aWire1Stack, aWire1Stack, aWire1Stack});
        GT_ModHandler.addShapelessCraftingRecipe(aWire8Stack, new Object[]{aWire4Stack, aWire4Stack});

        GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(12L, aWire1Stack), new Object[]{aOreDictName});
        GT_ModHandler.addShapelessCraftingRecipe(aWire12Stack, new Object[]{aWire8Stack, aWire4Stack});

        GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(16L, aWire1Stack), new Object[]{aOreDictName});
        GT_ModHandler.addShapelessCraftingRecipe(aWire16Stack, new Object[]{aWire8Stack, aWire8Stack});
        GT_ModHandler.addShapelessCraftingRecipe(aWire16Stack, new Object[]{aWire12Stack, aWire4Stack});
        if (GT_Mod.gregtechproxy.mAE2Integration) {
            Api.INSTANCE.registries().p2pTunnel().addNewAttunement(aWire16Stack, TunnelType.IC2_POWER);
        }
    }

    public void addMaterialSpecificRecipes() {
        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Iron, 1)), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.WroughtIron, 1L));
        //TODO FIX RICH?
        GT_Values.RA.addCentrifugeRecipe(GT_Utility.copyAmount(1L, GT_OreDictUnificator.get(OrePrefixes.ore, Materials.Oilsands, 1)), null, null, Materials.Oil.getFluid(/*tIsRich ? 1000L : */500L), new ItemStack(net.minecraft.init.Blocks.sand, 1, 0), null, null, null, null, null, new int[]{'?'}, /*tIsRich ? 2000 : */1000, 5);

        if (gregtech.api.GregTech_API.sRecipeFile.get(gregtech.api.enums.ConfigCategories.Recipes.disabledrecipes, "QuartzDustSmeltingIntoAESilicon", true)) {
            GT_ModHandler.removeFurnaceSmelting(Materials.NetherQuartz.getDust(1));
            GT_ModHandler.removeFurnaceSmelting(Materials.Quartz.getDust(1));
            GT_ModHandler.removeFurnaceSmelting(Materials.CertusQuartz.getDust(1));
        }
        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, Materials.Glass.getDust(1)), new ItemStack(Blocks.glass));
        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, Materials.Tetrahedrite.getDust(1)), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Copper, 6L));
        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, Materials.Chalcopyrite.getDust(1)), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Copper, 6L));
        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, Materials.Malachite.getDust(1)), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Copper, 6L));
        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, Materials.Pentlandite.getDust(1)), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Nickel, 6L));
        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, Materials.Garnierite.getDust(1)), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Nickel, 1L));
        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, Materials.Cassiterite.getDust(1)), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 1L));
        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, Materials.Magnetite.getDust(1)), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Iron, 3L));
        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, Materials.VanadiumMagnetite.getDust(1)), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Iron, 3L));
        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, Materials.BasalticMineralSand.getDust(1)), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Iron, 3L));
        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, Materials.GraniticMineralSand.getDust(1)), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Iron, 3L));
        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, Materials.YellowLimonite.getDust(1)), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1L));
        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, Materials.BrownLimonite.getDust(1)), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1L));
        GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, Materials.BandedIron.getDust(1)), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1L));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4L, Materials.Diamond.getDust(1)), 32, ItemList.IC2_Industrial_Diamond.get(3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 16L));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4L, Materials.Opal.getDust(1)), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Opal, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12L));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4L, Materials.Olivine.getDust(1)), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Olivine, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12L));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4L, Materials.Emerald.getDust(1)), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Emerald, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12L));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4L, Materials.Ruby.getDust(1)), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Ruby, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12L));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4L, Materials.Sapphire.getDust(1)), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Sapphire, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12L));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4L, Materials.GreenSapphire.getDust(1)), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.GreenSapphire, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12L));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4L, Materials.Topaz.getDust(1)), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Topaz, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12L));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4L, Materials.BlueTopaz.getDust(1)), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.BlueTopaz, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12L));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4L, Materials.Tanzanite.getDust(1)), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Tanzanite, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12L));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4L, Materials.FoolsRuby.getDust(1)), 16, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.FoolsRuby, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 8L));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4L, Materials.GarnetRed.getDust(1)), 16, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.GarnetRed, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 8L));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4L, Materials.GarnetYellow.getDust(1)), 16, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.GarnetYellow, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 8L));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4L, Materials.Jasper.getDust(1)), 16, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Jasper, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 8L));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4L, Materials.Amber.getDust(1)), 16, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Amber, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 8L));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4L, Materials.Monazite.getDust(1)), 16, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Monazite, 3L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 8L));
        GT_Values.RA.addElectrolyzerRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.CertusQuartz, 1), 0, GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 1), null, null, null, null, null, 2000, 30);
        if (gregtech.api.GregTech_API.sRecipeFile.get(gregtech.api.enums.ConfigCategories.Recipes.disabledrecipes, "torchesFromCoal", false)) {
            GT_ModHandler.removeRecipe(new ItemStack(Items.coal, 1, 0), null, null, new ItemStack(net.minecraft.init.Items.stick, 1, 0));
            GT_ModHandler.removeRecipe(new ItemStack(Items.coal, 1, 1), null, null, new ItemStack(net.minecraft.init.Items.stick, 1, 0));
        }
        Materials[] aSifterGemMaterials = new Materials[]{Materials.Tanzanite, Materials.Sapphire, Materials.GreenSapphire, Materials.Opal, Materials.Amethyst, Materials.Emerald, Materials.Ruby, Materials.Amber, Materials.Diamond, Materials.FoolsRuby, Materials.BlueTopaz, Materials.GarnetRed, Materials.Topaz, Materials.Jasper, Materials.GarnetYellow};
        for (Materials aGemMaterial : aSifterGemMaterials) {
            ItemStack aGem = GT_OreDictUnificator.get(OrePrefixes.gem, aGemMaterial, 1);
            GT_Values.RA.addSifterRecipe(GT_OreDictUnificator.get(OrePrefixes.crushedPurified, aGemMaterial, 1), new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.gemExquisite, aGemMaterial, aGem, 1L), GT_OreDictUnificator.get(OrePrefixes.gemFlawless, aGemMaterial, aGem, 1L), aGem, GT_OreDictUnificator.get(OrePrefixes.gemFlawed, aGemMaterial, aGem, 1L), GT_OreDictUnificator.get(OrePrefixes.gemChipped, aGemMaterial, aGem, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, aGemMaterial, aGem, 1L)}, new int[]{300, 1200, 4500, 1400, 2800, 3500}, 800, 16);
        }
        /*if (aModName.equalsIgnoreCase("AtomicScience")) {
            GT_ModHandler.addExtractionRecipe(ItemList.Cell_Empty.get(1L), aStack);
        }*/
        GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(GregTech_API.sRecipeFile.get(gregtech.api.enums.ConfigCategories.Recipes.harderrecipes, Materials.Paper.getPlates(1), true) ? 2L : 3L, Materials.Paper.getPlates(1)), new Object[]{"XXX", 'X', new ItemStack(net.minecraft.init.Items.reeds, 1, 32767)});
        GregTech_API.registerCover(Materials.Iron.getPlates(1), new GT_CopiedBlockTexture(Blocks.iron_block, 1, 0), null);
        GregTech_API.registerCover(Materials.Gold.getPlates(1), new GT_CopiedBlockTexture(Blocks.gold_block, 1, 0), null);
        GregTech_API.registerCover(Materials.Diamond.getPlates(1), new GT_CopiedBlockTexture(Blocks.diamond_block, 1, 0), null);
        GregTech_API.registerCover(Materials.Emerald.getPlates(1), new GT_CopiedBlockTexture(Blocks.emerald_block, 1, 0), null);
        GregTech_API.registerCover(Materials.Lapis.getPlates(1), new GT_CopiedBlockTexture(Blocks.lapis_block, 1, 0), null);
        GregTech_API.registerCover(Materials.Coal.getPlates(1), new GT_CopiedBlockTexture(Blocks.coal_block, 1, 0), null);
        GregTech_API.registerCover(Materials.Redstone.getPlates(1), new GT_CopiedBlockTexture(Blocks.redstone_block, 1, 0), null);
        GregTech_API.registerCover(Materials.Glowstone.getPlates(1), new GT_CopiedBlockTexture(Blocks.glowstone, 1, 0), null);
        GregTech_API.registerCover(Materials.NetherQuartz.getPlates(1), new GT_CopiedBlockTexture(Blocks.quartz_block, 1, 0), null);
        GregTech_API.registerCover(Materials.Obsidian.getPlates(1), new GT_CopiedBlockTexture(Blocks.obsidian, 1, 0), null);
        GregTech_API.registerCover(Materials.Stone.getPlates(1), new GT_CopiedBlockTexture(Blocks.stone, 1, 0), null);
        GregTech_API.registerCover(Materials.GraniteBlack.getPlates(1), new GT_RenderedTexture(gregtech.api.enums.Textures.BlockIcons.GRANITE_BLACK_SMOOTH), null);
        GregTech_API.registerCover(Materials.GraniteRed.getPlates(1), new GT_RenderedTexture(gregtech.api.enums.Textures.BlockIcons.GRANITE_RED_SMOOTH), null);
        GregTech_API.registerCover(Materials.Concrete.getPlates(1), new GT_RenderedTexture(gregtech.api.enums.Textures.BlockIcons.CONCRETE_LIGHT_SMOOTH), null);
        GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.gearGt, Materials.Wood, 1L), tBits, new Object[]{"SPS", "PsP", "SPS", Character.valueOf('P'), OrePrefixes.plank.get(Materials.Wood), Character.valueOf('S'), OrePrefixes.stick.get(Materials.Wood)});
        GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.gearGt, Materials.Stone, 1L), tBits, new Object[]{"SPS", "PfP", "SPS", Character.valueOf('P'), OrePrefixes.stoneSmooth, Character.valueOf('S'), new ItemStack(Blocks.stone_button, 1, 32767)});
        GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.gearGtSmall, Materials.Wood, 1L), tBits, new Object[]{"P ", " s", Character.valueOf('P'), OrePrefixes.plank.get(Materials.Wood)});
        GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.gearGtSmall, Materials.Stone, 1L), tBits, new Object[]{"P ", " f", Character.valueOf('P'), OrePrefixes.stoneSmooth});
        /*switch (aOreDictName) {
        case "plateAlloyCarbon":
            GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("generator", 1L), GT_Utility.copyAmount(4L, aStack), GT_ModHandler.getIC2Item("windMill", 1L), 6400, 8);
        case "plateAlloyAdvanced":
            GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copyAmount(1L, aStack), new ItemStack(Blocks.glass, 3, 32767), GT_ModHandler.getIC2Item("reinforcedGlass", 4L), 400, 4, false);
            GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copyAmount(1L, aStack), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glass, 3L), GT_ModHandler.getIC2Item("reinforcedGlass", 4L), 400, 4, false);
        case "plateAlloyIridium":
            GT_ModHandler.removeRecipeByOutput(aStack);
        }*/
        //GT_OreDictUnificator.addItemData(aStack, new ItemData(Materials.Silicon, 3628800L, new MaterialStack[0]));
        //GT_Values.RA.addFormingPressRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 0L, 19), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 20), 200, 16);

        //if (aOreDictName.equals("waxMagical"))
            //GT_Values.RA.addFuel(GT_Utility.copyAmount(1L, aStack), null, 6, 5);
        /*switch (aOreDictName) {
            case "craftingQuartz":
                GT_Values.RA.addAssemblerRecipe(new ItemStack(Blocks.redstone_torch, 3, 32767), GT_Utility.copyAmount(1L, aStack), Materials.Concrete.getMolten(144L), new ItemStack(net.minecraft.init.Items.comparator, 1, 0), 800, 1);
                break;
            case "craftingWireCopper":
                GT_Values.RA.addAssemblerRecipe(ItemList.Circuit_Basic.get(1L, new Object[0]), GT_Utility.copyAmount(1L, aStack), GT_ModHandler.getIC2Item("frequencyTransmitter", 1L), 800, 1);
                break;
            case "craftingWireTin":
                GT_Values.RA.addAssemblerRecipe(ItemList.Circuit_Basic.get(1L, new Object[0]), GT_Utility.copyAmount(1L, aStack), GT_ModHandler.getIC2Item("frequencyTransmitter", 1L), 800, 1);
                break;
            case "craftingLensBlue":
                GT_Values.RA.addLaserEngraverRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Iron, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 13), 2000, 1920);
                GT_Values.RA.addLaserEngraverRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.WroughtIron, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 13), 2000, 1920);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.IC2_LapotronCrystal.getWildcard(1L), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Parts_Crystal_Chip_Master.get(3L), 256, 480,true);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer2.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_PIC.get(1), 500, 480,true);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer3.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_PIC.get(4), 200, 1920,true);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Chip_CrystalCPU.get(1L), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Chip_CrystalSoC.get(1), 100, 40000,true);
                break;
            case "craftingLensYellow":
                GT_Values.RA.addLaserEngraverRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Iron, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 14), 2000, 1920);
                GT_Values.RA.addLaserEngraverRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.WroughtIron, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 14), 2000, 1920);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer3.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_SoC.get(1), 200, 1920,true);
                break;
            case "craftingLensOrange":
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer3.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_SoC2.get(1), 200, 1920,true);

                break;
            case "craftingLensCyan":
                GT_Values.RA.addLaserEngraverRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Iron, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 15), 2000, 1920);
                GT_Values.RA.addLaserEngraverRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.WroughtIron, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 15), 2000, 1920);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_Ram.get(1), 900, 120,false);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer2.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_Ram.get(4), 500, 480,true);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer3.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_Ram.get(8), 200, 1920,true);

                break;
            case "craftingLensRed":
                GT_Values.RA.addLaserEngraverRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Redstone, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1L, 0), 50, 120);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_ILC.get(1), 900, 120,false);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer2.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_ILC.get(4), 500, 480,true);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer3.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_ILC.get(8), 200, 1920,true);
                break;
            case "craftingLensGreen":
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Parts_Crystal_Chip_Elite.get(1L), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Chip_CrystalCPU.get(1), 100, 10000,true);
                break;
            case "craftingLensWhite":
                GT_Values.RA.addLaserEngraverRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Iron, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 19), 2000, 1920);
                GT_Values.RA.addLaserEngraverRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.WroughtIron, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 19), 2000, 1920);

                GT_Values.RA.addLaserEngraverRecipe(new ItemStack(Blocks.sandstone, 1, 2), GT_Utility.copyAmount(0L, aStack), new ItemStack(Blocks.sandstone, 1, 1), 50, 16);
                GT_Values.RA.addLaserEngraverRecipe(new ItemStack(Blocks.stone, 1, 0), GT_Utility.copyAmount(0L, aStack), new ItemStack(Blocks.stonebrick, 1, 3), 50, 16);
                GT_Values.RA.addLaserEngraverRecipe(new ItemStack(Blocks.quartz_block, 1, 0), GT_Utility.copyAmount(0L, aStack), new ItemStack(Blocks.quartz_block, 1, 1), 50, 16);
                GT_Values.RA.addLaserEngraverRecipe(GT_ModHandler.getModItem("appliedenergistics2", "tile.BlockQuartz", 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("appliedenergistics2", "tile.BlockQuartzChiseled", 1L), 50, 16);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_CPU.get(1), 900, 120,true);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer2.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_CPU.get(4), 500, 480,true);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer3.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_CPU.get(8), 200, 1920,true);
                break;
        }*/
        /*if ((aMaterial.contains(SubTag.TRANSPARENT)) && (aMaterial.mColor != Dyes.dyeNULL)) {
            GT_OreDictUnificator.registerOre("craftingLens" + aMaterial.mColor.toString().replaceFirst("dye", ""), aEvent.Ore);
        }*/

        int tAmount = (int) (OrePrefixes.ingot.mMaterialAmount / 3628800L);
        if ((tAmount > 0) && (tAmount <= 64) && (OrePrefixes.ingot.mMaterialAmount % 3628800L == 0L)) {
            GT_Values.RA.addExtruderRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glass, 1), ItemList.Shape_Extruder_Bottle.get(0L), new ItemStack(Items.glass_bottle, 1), tAmount * 32, 16);
            GT_Values.RA.addAlloySmelterRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glass, 1), ItemList.Shape_Mold_Bottle.get(0L), new ItemStack(Items.glass_bottle, 1), tAmount * 64, 4);

            GT_Values.RA.addExtruderRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 2), ItemList.Shape_Extruder_Cell.get(0L), ItemList.Cell_Empty.get(tAmount), tAmount * 128, 32);
            GT_Values.RA.addExtruderRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Polytetrafluoroethylene, 2), ItemList.Shape_Extruder_Cell.get(0L), ItemList.Cell_Empty.get(tAmount), tAmount * 128, 32);
        }

        /*switch (aMaterial.mSmeltInto.mName) {
            case "Steel":
                if (tAmount * 2 <= 64)
                    GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(1L, aStack), ItemList.Shape_Extruder_Casing.get(0L), GT_ModHandler.getIC2Item("casingadviron", tAmount * 2), tAmount * 32, 3 * tVoltageMultiplier);
                if (tAmount * 2 <= 64)
                    GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(2L, aStack), ItemList.Shape_Mold_Casing.get(0L), GT_ModHandler.getIC2Item("casingadviron", tAmount * 3), tAmount * 128, 1 * tVoltageMultiplier);
                break;
            case "Iron":
            case "WroughtIron":
                GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(1L, aStack), ItemList.Shape_Extruder_Cell.get(0L), GT_ModHandler.getIC2Item("fuelRod", tAmount), tAmount * 128, 32);
                if (tAmount * 2 <= 64)
                    GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(1L, aStack), ItemList.Shape_Extruder_Casing.get(0L), GT_ModHandler.getIC2Item("casingiron", tAmount * 2), tAmount * 32, 3 * tVoltageMultiplier);
                if (tAmount * 2 <= 64)
                    GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(2L, aStack), ItemList.Shape_Mold_Casing.get(0L), GT_ModHandler.getIC2Item("casingiron", tAmount * 3), tAmount * 128, 1 * tVoltageMultiplier);
                if (tAmount * 31 <= 64)
                    GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(31L, aStack), ItemList.Shape_Mold_Anvil.get(0L), new ItemStack(Blocks.anvil, 1, 0), tAmount * 512, 4 * tVoltageMultiplier);
                break;
            case "Tin":
                GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(2L, aStack), ItemList.Shape_Extruder_Cell.get(0L), ItemList.Cell_Empty.get(tAmount), tAmount * 128, 32);
                if (tAmount * 2 <= 64)
                    GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(1L, aStack), ItemList.Shape_Extruder_Casing.get(0L), GT_ModHandler.getIC2Item("casingtin", tAmount * 2), tAmount * 32, 3 * tVoltageMultiplier);
                if (tAmount * 2 <= 64)
                    GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(2L, aStack), ItemList.Shape_Mold_Casing.get(0L), GT_ModHandler.getIC2Item("casingtin", tAmount * 3), tAmount * 128, 1 * tVoltageMultiplier);
                break;
            case "Lead":
                if (tAmount * 2 <= 64)
                    GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(1L, aStack), ItemList.Shape_Extruder_Casing.get(0L), GT_ModHandler.getIC2Item("casinglead", tAmount * 2), tAmount * 32, 3 * tVoltageMultiplier);
                if (tAmount * 2 <= 64)
                    GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(2L, aStack), ItemList.Shape_Mold_Casing.get(0L), GT_ModHandler.getIC2Item("casinglead", tAmount * 3), tAmount * 128, 1 * tVoltageMultiplier);
                break;
            case "Copper":
            case "AnnealedCopper":
                if (tAmount * 2 <= 64)
                    GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(1L, aStack), ItemList.Shape_Extruder_Casing.get(0L), GT_ModHandler.getIC2Item("casingcopper", tAmount * 2), tAmount * 32, 3 * tVoltageMultiplier);
                if (tAmount * 2 <= 64)
                    GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(2L, aStack), ItemList.Shape_Mold_Casing.get(0L), GT_ModHandler.getIC2Item("casingcopper", tAmount * 3), tAmount * 128, 1 * tVoltageMultiplier);
                break;
            case "Bronze":
                if (tAmount * 2 <= 64)
                    GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(1L, aStack), ItemList.Shape_Extruder_Casing.get(0L), GT_ModHandler.getIC2Item("casingbronze", tAmount * 2), tAmount * 32, 3 * tVoltageMultiplier);
                if (tAmount * 2 <= 64)
                    GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(2L, aStack), ItemList.Shape_Mold_Casing.get(0L), GT_ModHandler.getIC2Item("casingbronze", tAmount * 3), tAmount * 128, 1 * tVoltageMultiplier);
                break;
            case "Gold":
                if (tAmount * 2 <= 64)
                    GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(1L, aStack), ItemList.Shape_Extruder_Casing.get(0L), GT_ModHandler.getIC2Item("casinggold", tAmount * 2), tAmount * 32, 3 * tVoltageMultiplier);
                if (tAmount * 2 <= 64)
                    GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(2L, aStack), ItemList.Shape_Mold_Casing.get(0L), GT_ModHandler.getIC2Item("casinggold", tAmount * 3), tAmount * 128, 1 * tVoltageMultiplier);
                break;
        }*/

        //TODO MOVE TO ITEMDATALOADER?
        /*if (tOre.mModID.equalsIgnoreCase("enderio") && tOre.mPrefix == OrePrefixes.ingot && tOre.mMaterial == Materials.DarkSteel) {
            GT_OreDictUnificator.addAssociation(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, false);
            GT_OreDictUnificator.set(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, (GregTech_API.sUnification.get(new StringBuilder().append(ConfigCategories.specialunificationtargets).append(".").append(tOre.mModID).toString(), tOre.mEvent.Name, true)), true);continue;
        } else if (tOre.mModID.equalsIgnoreCase("appliedenergistics2") && tOre.mPrefix == OrePrefixes.gem && tOre.mMaterial == Materials.CertusQuartz) {
            GT_OreDictUnificator.addAssociation(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, false);
            GT_OreDictUnificator.set(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, (GregTech_API.sUnification.get(new StringBuilder().append(ConfigCategories.specialunificationtargets).append(".").append(tOre.mModID).toString(), tOre.mEvent.Name, true)), true);continue;
        } else if (tOre.mModID.equalsIgnoreCase("appliedenergistics2") && tOre.mPrefix == OrePrefixes.dust && tOre.mMaterial == Materials.CertusQuartz) {
            GT_OreDictUnificator.addAssociation(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, false);
            GT_OreDictUnificator.set(tOre.mPrefix, tOre.mMaterial, tOre.mEvent.Ore, (GregTech_API.sUnification.get(new StringBuilder().append(ConfigCategories.specialunificationtargets).append(".").append(tOre.mModID).toString(), tOre.mEvent.Name, true)), true);continue;
        }*/

        if (aMaterial == Materials.Tin) {
            GT_OreDictUnificator.registerOre(OreDictNames.craftingWireTin, aEvent.Ore);
        } else if (aMaterial == Materials.AnyCopper) {
            GT_OreDictUnificator.registerOre(OreDictNames.craftingWireCopper, aEvent.Ore);
        } else if (aMaterial == Materials.Gold) {
            GT_OreDictUnificator.registerOre(OreDictNames.craftingWireGold, aEvent.Ore);
        } else if (aMaterial == Materials.AnyIron) {
            GT_OreDictUnificator.registerOre(OreDictNames.craftingWireIron, aEvent.Ore);
        }

        //case cell
        if (aMaterial == Materials.Empty) {
            GT_OreDictUnificator.addToBlacklist(aEvent.Ore);
        }



        else if (aMaterial == Materials.Lapis) {
            GT_OreDictUnificator.registerOre(Dyes.dyeBlue, aEvent.Ore);
        } else if (aMaterial == Materials.Lazurite) {
            GT_OreDictUnificator.registerOre(Dyes.dyeCyan, aEvent.Ore);
        } else if (aMaterial == Materials.Sodalite) {
            GT_OreDictUnificator.registerOre(Dyes.dyeBlue, aEvent.Ore);
        } else if (aMaterial == Materials.BrownLimonite) {
            GT_OreDictUnificator.registerOre(Dyes.dyeBrown, aEvent.Ore);
        } else if (aMaterial == Materials.YellowLimonite) {
            GT_OreDictUnificator.registerOre(Dyes.dyeYellow, aEvent.Ore);
        }
        else if ((aMaterial == Materials.Tin) || (aMaterial == Materials.Lead) || (aMaterial == Materials.SolderingAlloy)) {
            GT_OreDictUnificator.registerOre(ToolDictNames.craftingToolSolderingMetal, aEvent.Ore);
        }

        else if (aEvent.Name.equals("compressedAluminum")) {
            GT_OreDictUnificator.registerOre(OrePrefixes.compressed, Materials.Aluminium, aEvent.Ore);
            return;
        }
        if (aEvent.Name.equals("shardAir")) {
            GT_OreDictUnificator.registerOre(OrePrefixes.gem, Materials.InfusedAir, aEvent.Ore);
            return;
        } else if (aEvent.Name.equals("shardWater")) {
            GT_OreDictUnificator.registerOre(OrePrefixes.gem, Materials.InfusedWater, aEvent.Ore);
            return;
        } else if (aEvent.Name.equals("shardFire")) {
            GT_OreDictUnificator.registerOre(OrePrefixes.gem, Materials.InfusedFire, aEvent.Ore);
            return;
        } else if (aEvent.Name.equals("shardEarth")) {
            GT_OreDictUnificator.registerOre(OrePrefixes.gem, Materials.InfusedEarth, aEvent.Ore);
            return;
        } else if (aEvent.Name.equals("shardOrder")) {
            GT_OreDictUnificator.registerOre(OrePrefixes.gem, Materials.InfusedOrder, aEvent.Ore);
            return;
        } else if (aEvent.Name.equals("shardEntropy")) {
            GT_OreDictUnificator.registerOre(OrePrefixes.gem, Materials.InfusedEntropy, aEvent.Ore);
            return;
        }
    }
}
