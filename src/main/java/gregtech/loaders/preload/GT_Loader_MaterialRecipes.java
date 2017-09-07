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
import gregtech.common.items.behaviors.Behaviour_DataOrb;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static List<Materials> aSolidAndDustList = new ArrayList<>();

    public void run() {
        Materials[] aSolidAndDustArray = aSolidAndDustList.toArray(new Materials[aSolidAndDustList.size()]);
        ProgressManager.ProgressBar progressBar = ProgressManager.push("Registering Material Recipes: ", aSolidAndDustArray.length);
        for (Materials aMaterial : aSolidAndDustArray) {
            progressBar.step(aMaterial.mName);
            addBasicDustRecipes(aMaterial);
            if (aMaterial.mElement != null && !aMaterial.mElement.mIsIsotope && aMaterial.mMetaItemSubID != -128 && aMaterial.getMass() > 0) {
                ItemStack tOutput = ItemList.Tool_DataOrb.get(1);
                Behaviour_DataOrb.setDataTitle(tOutput, "Elemental-Scan");
                Behaviour_DataOrb.setDataName(tOutput, aMaterial.mElement.name());
                ItemStack tInput = aMaterial.hasFlag(MaterialFlags.CELL.bit) ? MatUnifier.get(OrePrefixes.cell, aMaterial) : MatUnifier.get(OrePrefixes.dust, aMaterial);
                GT_Recipe.GT_Recipe_Map.sScannerFakeRecipes.addFakeRecipe(false, new ItemStack[]{tInput}, new ItemStack[]{tOutput}, ItemList.Tool_DataOrb.get(1), null, null, (int) (aMaterial.getMass() * 8192), 32, 0);
                GT_Recipe.GT_Recipe_Map.sReplicatorFakeRecipes.addFakeRecipe(false, null, new ItemStack[]{tInput}, new ItemStack[]{tOutput}, new FluidStack[]{Materials.UUMatter.getFluid((int)aMaterial.getMass())}, null, (int) (aMaterial.getMass() * 512), 32, 0);
            }
            if (aMaterial.hasFlag(MaterialFlags.DUST.bit)) {
                aNoSmelting = aMaterial.contains(SubTag.NO_SMELTING);
                aNoWorking = aMaterial.contains(SubTag.NO_WORKING);
                aNormalDustStack = MatUnifier.get(OrePrefixes.dust, aMaterial);
                aSmallDustStack = MatUnifier.get(OrePrefixes.dustSmall, aMaterial);
                aTinyDustStack = MatUnifier.get(OrePrefixes.dustTiny, aMaterial);
                addDustRecipes(aMaterial);
            }
            if (aMaterial.hasFlag(MaterialFlags.SOLID.bit)) {
                aNoSmashing = aMaterial.contains(SubTag.NO_SMASHING);
                aIngotStack = MatUnifier.get(OrePrefixes.ingot, aMaterial);
                aNuggetStack = MatUnifier.get(OrePrefixes.nugget, aMaterial);
                addSolidRecipes(aMaterial);
                addShapingRecipes(aMaterial);
                if (aMaterial.hasFlag(MaterialFlags.PLATE.bit)) {
                    aPlateStack = MatUnifier.get(OrePrefixes.plate, aMaterial);
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
            if (aMaterial.hasFlag(MaterialFlags.ORE.bit)) {
                aWashingMercury = aMaterial.contains(SubTag.WASHING_MERCURY);
                aWashingSodium = aMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE);
                addOreRecipes(aMaterial, false);
            }
            if (aMaterial.hasFlag(MaterialFlags.GEM.bit)) addGemRecipes(aMaterial);
        }
        ProgressManager.pop(progressBar);
        addMaterialSpecificRecipes();
    }

    public void addHotIngotRecipes(Materials aMaterial) {
        int aBlastDuration = (int) Math.max(aMaterial.getMass() / 40L, 1L) * aMaterial.mBlastFurnaceTemp;
        GT_ModHandler.removeFurnaceSmelting(aNormalDustStack);
        GT_Values.RA.addBlastRecipe(aNormalDustStack, null, null, null, MatUnifier.get(OrePrefixes.ingotHot, aMaterial.mSmeltInto, aMaterial.mSmeltInto.getIngots(1)), null, aBlastDuration, 120, aMaterial.mBlastFurnaceTemp);
        GT_Values.RA.addVacuumFreezerRecipe(MatUnifier.get(OrePrefixes.ingotHot, aMaterial), aIngotStack, (int) Math.max(aMaterial.getMass() * 3L, 1L));
    }

    public void addSpringRecipes(Materials aMaterial) {
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.stick, aMaterial), MatUnifier.get(OrePrefixes.spring, aMaterial), 200, 16);
    }

    public void addDensePlateRecipes(Materials aMaterial) {
        ItemStack aDenseStack = MatUnifier.get(OrePrefixes.plateDense, aMaterial);
        GT_ModHandler.removeRecipeByOutput(aDenseStack);
        if (aMaterial.mUnificatable && aMaterial.mMaterialInto == aMaterial && aNoSmashing) {
            GT_Values.RA.addBenderRecipe(GT_Utility.copyAmount(9, aPlateStack), aDenseStack, (int) Math.max(aMaterial.getMass() * 9L, 1L), 96);
        }
        GregTech_API.registerCover(aDenseStack, new GT_RenderedTexture(aMaterial.mIconSet.mTextures[76], aMaterial.mRGBa, false), null);
    }

    public void addRotorRecipes(Materials aMaterial) {
        ItemStack aRotorStack = MatUnifier.get(OrePrefixes.rotor, aMaterial);
        ItemStack aRingStack = MatUnifier.get(OrePrefixes.ring, aMaterial);
        if ((aMaterial.mUnificatable) && (aMaterial.mMaterialInto == aMaterial) && !aNoWorking) {
            GT_ModHandler.addCraftingRecipe(aRotorStack, tBits, new Object[]{"PhP", "SRf", "PdP", Character.valueOf('P'), aMaterial == Materials.Wood ? OrePrefixes.plank.get(aMaterial) : aPlateStack, Character.valueOf('R'), OrePrefixes.ring.get(aMaterial), Character.valueOf('S'), OrePrefixes.screw.get(aMaterial)});
            GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(4, aPlateStack), aRingStack, Materials.Tin.getMolten(32), aRotorStack, 240, 24);
            GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(4, aPlateStack), aRingStack, Materials.Lead.getMolten(48), aRotorStack, 240, 24);
            GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(4, aPlateStack), aRingStack, Materials.SolderingAlloy.getMolten(16), aRotorStack, 240, 24);
        }
    }

    public void addFineWireRecipes(Materials aMaterial) {
        ItemStack aWireStack = MatUnifier.get(OrePrefixes.wireFine, aMaterial);
        if (!aNoSmashing) {
            GT_Values.RA.addWiremillRecipe(aIngotStack, GT_Utility.copy(MatUnifier.get(OrePrefixes.wireGt01, aMaterial, 2), aWireStack), 100, 4);
            GT_Values.RA.addWiremillRecipe(MatUnifier.get(OrePrefixes.stick, aMaterial), GT_Utility.copy(MatUnifier.get(OrePrefixes.wireGt01, aMaterial, 1), aWireStack), 50, 4);
        }
        if ((aMaterial.mUnificatable) && (aMaterial.mMaterialInto == aMaterial) && !aNoWorking) {
            GT_ModHandler.addCraftingRecipe(aWireStack, tBits, new Object[]{"Xx", Character.valueOf('X'), OrePrefixes.foil.get(aMaterial)});
        }
    }

    public void addGearSmallRecipes(Materials aMaterial) {
        ItemStack aGearSmallStack = MatUnifier.get(OrePrefixes.gearGtSmall, aMaterial);
        if (aMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Gear_Small.get(0), aMaterial.getMolten(144), aGearSmallStack, 16, 8);
        }
        if (aMaterial.mUnificatable && (aMaterial.mMaterialInto == aMaterial) && !aNoWorking) {
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.gearGtSmall, aMaterial, 1), tBits, new Object[]{"P ", aMaterial.contains(SubTag.WOOD) ? " s" : " h", Character.valueOf('P'), OrePrefixes.plate.get(aMaterial)});
        }
    }

    public void addGearRecipes(Materials aMaterial) {
        ItemStack aGearStack = MatUnifier.get(OrePrefixes.gearGt, aMaterial);
        GT_ModHandler.removeRecipeByOutput(aGearStack);
        if (aMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Gear.get(0), aMaterial.getMolten(576), aGearStack, 128, 8);
        }
        if (aMaterial.mUnificatable && (aMaterial.mMaterialInto == aMaterial) && !aNoWorking) {
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.gearGt, aMaterial), tBits, new Object[]{"SPS", "PwP", "SPS", Character.valueOf('P'), OrePrefixes.plate.get(aMaterial), Character.valueOf('S'), OrePrefixes.stick.get(aMaterial)});
        }
        if (!aNoSmelting) {
            //GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(4, aIngotStack), ItemList.Shape_Extruder_Gear.get(0), MatUnifier.get(OrePrefixes.gearGt, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 5L * tAmount, tAmount), 8 * tVoltageMultiplier);
            //GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(8, aIngotStack), ItemList.Shape_Mold_Gear.get(0), MatUnifier.get(OrePrefixes.gearGt, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 10L * tAmount, tAmount), 2 * tVoltageMultiplier);
        }
        MatUnifier.registerOre(OrePrefixes.gear, aGearStack);
    }

    public void addScrewRecipes(Materials aMaterial) {
        ItemStack aScrewStack = MatUnifier.get(OrePrefixes.screw, aMaterial);
        if (!aNoWorking) {
            GT_Values.RA.addLatheRecipe(MatUnifier.get(OrePrefixes.bolt, aMaterial), aScrewStack, null, (int) Math.max(aMaterial.getMass() / 8L, 1L), 4);
            if ((aMaterial.mUnificatable) && (aMaterial.mMaterialInto == aMaterial)) {
                GT_ModHandler.addCraftingRecipe(aScrewStack, tBits, new Object[]{"fX", "X ", Character.valueOf('X'), OrePrefixes.bolt.get(aMaterial)});
            }
        }
    }

    public void addFoilRecipes(Materials aMaterial) {
        ItemStack aFoilStack = MatUnifier.get(OrePrefixes.foil, aMaterial);
        GT_Values.RA.addBenderRecipe(MatUnifier.get(OrePrefixes.plate, aMaterial), GT_Utility.copyAmount(4, aFoilStack), (int) Math.max(aMaterial.getMass(), 1L), 24);
        GregTech_API.registerCover(aFoilStack, new GT_RenderedTexture(aMaterial.mIconSet.mTextures[70], aMaterial.mRGBa, false), null);
    }

    public void addBoltRecipes(Materials aMaterial) {
        ItemStack aBoltStack = MatUnifier.get(OrePrefixes.bolt, aMaterial);
        if ((aMaterial.mUnificatable) && (aMaterial.mMaterialInto == aMaterial) && !aNoWorking) {
            GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(2, aBoltStack), tBits, new Object[]{"s ", " X", Character.valueOf('X'), OrePrefixes.stick.get(aMaterial)});
        }
        if (!aNoSmelting) {
            //GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Bolt.get(0), MatUnifier.get(OrePrefixes.bolt, aMaterial.mSmeltInto, tAmount * 8), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 8 * tVoltageMultiplier);
        }
    }

    public void addRingRecipes(Materials aMaterial) {
        if ((aMaterial.mUnificatable) && (aMaterial.mMaterialInto == aMaterial) && !aNoSmashing) {
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.ring, aMaterial), tBits, new Object[]{"h ", " X", Character.valueOf('X'), OrePrefixes.stick.get(aMaterial)});
        }
        if (!aNoSmelting) {
            //GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Ring.get(0), MatUnifier.get(OrePrefixes.ring, aMaterial.mSmeltInto, tAmount * 4), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 6 * tVoltageMultiplier);
        }
    }

    public void addStickRecipes(Materials aMaterial) {
        ItemStack aStickStack = MatUnifier.get(OrePrefixes.stick, aMaterial);
        if (!aNoWorking) {
            GT_Values.RA.addLatheRecipe(aMaterial.contains(SubTag.CRYSTAL) ? MatUnifier.get(OrePrefixes.gem, aMaterial) : aIngotStack, aStickStack, MatUnifier.get(OrePrefixes.dustSmall, aMaterial.mMacerateInto, 2), (int) Math.max(aMaterial.getMass() * 5L, 1L), 16);
            GT_Values.RA.addCutterRecipe(aStickStack, MatUnifier.get(OrePrefixes.bolt, aMaterial, 4), null, (int) Math.max(aMaterial.getMass() * 2L, 1L), 4);
            if ((aMaterial.mUnificatable) && (aMaterial.mMaterialInto == aMaterial)) {
                GT_ModHandler.addCraftingRecipe(aStickStack, tBits, new Object[]{"f ", " X", Character.valueOf('X'), aIngotStack});
            }
        }
        if (!aNoSmelting) {
            //GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Rod.get(0), MatUnifier.get(OrePrefixes.stick, aMaterial.mSmeltInto, tAmount * 2), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 6 * tVoltageMultiplier);
            //GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Wire.get(0), MatUnifier.get(OrePrefixes.wireGt01, aMaterial.mSmeltInto, tAmount * 2), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 6 * tVoltageMultiplier);
        }
    }

    public void addPlateRecipes(Materials aMaterial) {
        GT_ModHandler.removeRecipeByOutput(aPlateStack);
        GT_ModHandler.removeRecipe(aPlateStack);
        if (aMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Plate.get(0), aMaterial.getMolten(144), MatUnifier.get(OrePrefixes.plate, aMaterial, 1), 32, 8);
        }
        if (aMaterial.mFuelPower > 0) {
            GT_Values.RA.addFuel(aPlateStack, null, aMaterial.mFuelPower, aMaterial.mFuelType);
        }
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(aMaterial == Materials.MeteoricIron ? 1 : 2, aPlateStack), 2, MatUnifier.get(OrePrefixes.compressed, aMaterial), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 1));
        GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.foil, aMaterial, 2), tBits, new Object[]{"hX", 'X', aPlateStack});
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
                GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.dust, aMaterial), tBits, new Object[]{"X", "m", 'X', aPlateStack});
            }
        }
        if (!aNoSmelting) {
            //GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Plate.get(0), MatUnifier.get(OrePrefixes.plate, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * tAmount, tAmount), 8 * tVoltageMultiplier);
            //GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(2, aIngotStack), ItemList.Shape_Mold_Plate.get(0), MatUnifier.get(OrePrefixes.plate, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 2 * tVoltageMultiplier);
        }
        GregTech_API.registerCover(aPlateStack, new GT_RenderedTexture(aMaterial.mIconSet.mTextures[71], aMaterial.mRGBa, false), null);
    }

    public void addToolRecipes(Materials aMaterial) {
        ItemStack aStickStack = MatUnifier.get(OrePrefixes.stick, aMaterial);
        ItemStack aStainlessScrew = MatUnifier.get(OrePrefixes.screw, Materials.StainlessSteel);
        ItemStack aTitaniumScrew = MatUnifier.get(OrePrefixes.screw, Materials.Titanium);
        ItemStack aTungstensteelScrew = MatUnifier.get(OrePrefixes.screw, Materials.TungstenSteel);
        ItemStack aStainlessPlate = MatUnifier.get(OrePrefixes.plate, Materials.StainlessSteel);
        ItemStack aTitaniumPlate = MatUnifier.get(OrePrefixes.plate, Materials.Titanium);
        ItemStack aTungstensteelPlate = MatUnifier.get(OrePrefixes.plate, Materials.TungstenSteel);
        ItemStack aStainlessSmallGear = MatUnifier.get(OrePrefixes.gearGtSmall, Materials.StainlessSteel);
        ItemStack aTitaniumSmallGear = MatUnifier.get(OrePrefixes.gearGtSmall, Materials.Titanium);
        ItemStack aTungstensteelSmallGear = MatUnifier.get(OrePrefixes.gearGtSmall, Materials.TungstenSteel);
        ItemStack aTitaniumSpring = MatUnifier.get(OrePrefixes.spring, Materials.Titanium);
        boolean aSpecialRecipeReq1 = aMaterial.mUnificatable && (aMaterial.mMaterialInto == aMaterial) && !aNoSmashing;
        boolean aSpecialRecipeReq2 = aMaterial.mUnificatable && (aMaterial.mMaterialInto == aMaterial) && !aNoWorking;
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.BUZZSAW, 1, aMaterial, Materials.StainlessSteel, new long[]{100000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"PBM", "dXG", "SGP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Lithium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.BUZZSAW, 1, aMaterial, Materials.StainlessSteel, new long[]{75000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"PBM", "dXG", "SGP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Cadmium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.BUZZSAW, 1, aMaterial, Materials.StainlessSteel, new long[]{50000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"PBM", "dXG", "SGP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Sodium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{100000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Lithium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_MV, 1, aMaterial, Materials.Titanium, new long[]{400000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Lithium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1600000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Lithium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{75000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Cadmium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_MV, 1, aMaterial, Materials.Titanium, new long[]{300000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Cadmium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1200000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Cadmium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{50000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Sodium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_MV, 1, aMaterial, Materials.Titanium, new long[]{200000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Sodium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.CHAINSAW_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{800000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolSaw, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Sodium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{100000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Lithium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{75000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Cadmium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{50000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Sodium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_MV, 1, aMaterial, Materials.Titanium, new long[]{400000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Lithium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_MV, 1, aMaterial, Materials.Titanium, new long[]{300000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Cadmium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_MV, 1, aMaterial, Materials.Titanium, new long[]{200000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Sodium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1600000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Lithium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1200000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Cadmium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.DRILL_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{800000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolDrill, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Sodium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.JACKHAMMER, 1, aMaterial, Materials.Titanium, new long[]{1600000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "PRP", "MPB", 'X', aStickStack, 'M', ItemList.Electric_Piston_HV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'R', aTitaniumSpring, 'B', ItemList.Battery_RE_HV_Lithium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.JACKHAMMER, 1, aMaterial, Materials.Titanium, new long[]{1200000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "PRP", "MPB", 'X', aStickStack, 'M', ItemList.Electric_Piston_HV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'R', aTitaniumSpring, 'B', ItemList.Battery_RE_HV_Cadmium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.JACKHAMMER, 1, aMaterial, Materials.Titanium, new long[]{800000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "PRP", "MPB", 'X', aStickStack, 'M', ItemList.Electric_Piston_HV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'R', aTitaniumSpring, 'B', ItemList.Battery_RE_HV_Sodium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{100000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Lithium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_MV, 1, aMaterial, Materials.Titanium, new long[]{400000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Lithium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1600000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Lithium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{75000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Cadmium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_MV, 1, aMaterial, Materials.Titanium, new long[]{300000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Cadmium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1200000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Cadmium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{50000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Sodium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_MV, 1, aMaterial, Materials.Titanium, new long[]{200000L, 128L, 2L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Sodium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.WRENCH_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{800000L, 512L, 3L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"SXd", "GMG", "PBP", 'X', ToolDictNames.craftingToolWrench, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Sodium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.SCREWDRIVER_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{100000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"PdX", "MGS", "GBP", 'X', aStickStack, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Lithium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.SCREWDRIVER_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{75000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"PdX", "MGS", "GBP", 'X', aStickStack, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Cadmium.get(1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.SCREWDRIVER_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{50000L, 32L, 1L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"PdX", "MGS", "GBP", 'X', aStickStack, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Sodium.get(1)});
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
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.turbineBlade, aMaterial, 8), MatUnifier.get(OrePrefixes.stick, Materials.Magnalium), GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(170, 1, aMaterial, aMaterial, null), 160, 100);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.turbineBlade, aMaterial, 16), MatUnifier.get(OrePrefixes.stick, Materials.Titanium), GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(172, 1, aMaterial, aMaterial, null), 320, 400);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.turbineBlade, aMaterial, 24), MatUnifier.get(OrePrefixes.stick, Materials.TungstenSteel), GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(174, 1, aMaterial, aMaterial, null), 640, 1600);
        GT_Values.RA.addAssemblerRecipe(MatUnifier.get(OrePrefixes.turbineBlade, aMaterial, 32), MatUnifier.get(OrePrefixes.stick, Materials.Americium), GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(176, 1, aMaterial, aMaterial, null), 1280, 6400);
        if ((!aMaterial.contains(SubTag.NO_SMASHING)) && (!aMaterial.contains(SubTag.BOUNCY))) {
            GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.FILE, 1, aMaterial, aMaterial.mHandleMaterial, null), GT_ModHandler.RecipeBits.MIRRORED | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"P", "P", "S", 'P', aPlateStack, 'S', OrePrefixes.stick.get(aMaterial.mHandleMaterial)});
        }
        if ((aMaterial != Materials.Stone) && (aMaterial != Materials.Flint)) {
            GT_ModHandler.addShapelessCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats((aMaterial.contains(SubTag.BOUNCY)) || (aMaterial.contains(SubTag.WOOD)) ? GT_MetaGenerated_Tool_01.SOFTHAMMER : GT_MetaGenerated_Tool_01.HARDHAMMER, 1, aMaterial, aMaterial.mHandleMaterial, null), GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{ToolDictNames.craftingToolHardHammer, OrePrefixes.stick.get(aMaterial.mHandleMaterial)});
            GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats((aMaterial.contains(SubTag.BOUNCY)) || (aMaterial.contains(SubTag.WOOD)) ? GT_MetaGenerated_Tool_01.SOFTHAMMER : GT_MetaGenerated_Tool_01.HARDHAMMER, 1, aMaterial, aMaterial.mHandleMaterial, null), GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"XX ", "XXS", "XX ", 'X', aMaterial == Materials.Wood ? OrePrefixes.plank.get(Materials.Wood) : aIngotStack, 'S', OrePrefixes.stick.get(aMaterial.mHandleMaterial)});
            GT_ModHandler.addCraftingRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats((aMaterial.contains(SubTag.BOUNCY)) || (aMaterial.contains(SubTag.WOOD)) ? GT_MetaGenerated_Tool_01.SOFTHAMMER : GT_MetaGenerated_Tool_01.HARDHAMMER, 1, aMaterial, aMaterial.mHandleMaterial, null), GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"XX ", "XXS", "XX ", 'X', aMaterial == Materials.Wood ? OrePrefixes.plank.get(Materials.Wood) : OrePrefixes.gem.get(aMaterial), 'S', OrePrefixes.stick.get(aMaterial.mHandleMaterial)});
        }
        if (!aNoWorking) {
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadAxe, aMaterial), tBits, new Object[]{"GG ", "G  ", "f  ", 'G', OrePrefixes.gem.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadHoe, aMaterial), tBits, new Object[]{"GG ", "f  ", "   ", 'G', OrePrefixes.gem.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadPickaxe, aMaterial), tBits, new Object[]{"GGG", "f  ", 'G', OrePrefixes.gem.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadPlow, aMaterial), tBits, new Object[]{"GG", "GG", " f", 'G', OrePrefixes.gem.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadSaw, aMaterial), tBits, new Object[]{"GGf", 'G', OrePrefixes.gem.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadSense, aMaterial), tBits, new Object[]{"GGG", " f ", "   ", 'G', OrePrefixes.gem.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadShovel, aMaterial), tBits, new Object[]{"fG", 'G', OrePrefixes.gem.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadSword, aMaterial), tBits, new Object[]{" G", "fG", 'G', OrePrefixes.gem.get(aMaterial)});
        }
        if (aSpecialRecipeReq1) {
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadAxe, aMaterial), tBits, new Object[]{"PIh", "P  ", "f  ", 'P', aPlateStack, 'I', aIngotStack});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadHoe, aMaterial), tBits, new Object[]{"PIh", "f  ", 'P', aPlateStack, 'I', aIngotStack});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadPickaxe, aMaterial), tBits, new Object[]{"PII", "f h", 'P', aPlateStack, 'I', aIngotStack});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadPlow, aMaterial), tBits, new Object[]{"PP", "PP", "hf", 'P', aPlateStack});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadSaw, aMaterial), tBits, new Object[]{"PP ", "fh ", 'P', aPlateStack, 'I', aIngotStack});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadSense, aMaterial), tBits, new Object[]{"PPI", "hf ", 'P', aPlateStack, 'I', aIngotStack});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadShovel, aMaterial), tBits, new Object[]{"fPh", 'P', aPlateStack, 'I', aIngotStack});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadSword, aMaterial), tBits, new Object[]{" P ", "fPh", 'P', aPlateStack, 'I', aIngotStack});
        }
        if (aSpecialRecipeReq2) {
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadBuzzSaw, aMaterial), tBits, new Object[]{"wXh", "X X", "fXx", 'X', aPlateStack});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadChainsaw, aMaterial), tBits, new Object[]{"SRS", "XhX", "SRS", 'X', aPlateStack, 'S', OrePrefixes.plate.get(Materials.Steel), 'R', OrePrefixes.ring.get(Materials.Steel)});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadDrill, aMaterial), tBits, new Object[]{"XSX", "XSX", "ShS", 'X', aPlateStack, 'S', OrePrefixes.plate.get(Materials.Steel)});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadUniversalSpade, aMaterial), tBits, new Object[]{"fX", 'X', OrePrefixes.toolHeadShovel.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadWrench, aMaterial), tBits, new Object[]{"hXW", "XRX", "WXd", 'X', aPlateStack, 'S', OrePrefixes.plate.get(Materials.Steel), 'R', OrePrefixes.ring.get(Materials.Steel), 'W', OrePrefixes.screw.get(Materials.Steel)});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.turbineBlade, aMaterial), tBits, new Object[]{"fPd", "SPS", " P ", 'P', aMaterial == Materials.Wood ? OrePrefixes.plank.get(aMaterial) : aPlateStack, 'R', OrePrefixes.ring.get(aMaterial), 'S', OrePrefixes.bolt.get(aMaterial)});
            GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.toolHeadHammer, aMaterial), tBits, new Object[]{"II ", "IIh", "II ", 'P', aPlateStack, 'I', aIngotStack});
        }
        /*if (!aNoSmelting) {
            GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(2, aIngotStack), ItemList.Shape_Extruder_Sword.get(0), MatUnifier.get(OrePrefixes.toolHeadSword, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 8 * tVoltageMultiplier);
            GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(3, aIngotStack), ItemList.Shape_Extruder_Pickaxe.get(0), MatUnifier.get(OrePrefixes.toolHeadPickaxe, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 3L * tAmount, tAmount), 8 * tVoltageMultiplier);
            GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Shovel.get(0), MatUnifier.get(OrePrefixes.toolHeadShovel, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * tAmount, tAmount), 8 * tVoltageMultiplier);
            GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(3, aIngotStack), ItemList.Shape_Extruder_Axe.get(0), MatUnifier.get(OrePrefixes.toolHeadAxe, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 3L * tAmount, tAmount), 8 * tVoltageMultiplier);
            GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(2, aIngotStack), ItemList.Shape_Extruder_Hoe.get(0), MatUnifier.get(OrePrefixes.toolHeadHoe, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 8 * tVoltageMultiplier);
            GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(6, aIngotStack), ItemList.Shape_Extruder_Hammer.get(0), MatUnifier.get(OrePrefixes.toolHeadHammer, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 6L * tAmount, tAmount), 8 * tVoltageMultiplier);
            GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(2, aIngotStack), ItemList.Shape_Extruder_File.get(0), MatUnifier.get(OrePrefixes.toolHeadFile, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 8 * tVoltageMultiplier);
            GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(2, aIngotStack), ItemList.Shape_Extruder_Saw.get(0), MatUnifier.get(OrePrefixes.toolHeadSaw, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 8 * tVoltageMultiplier);
        }*/
    }

    public void addCellRecipes(Materials aMaterial) {
        ItemStack aCellStack = MatUnifier.get(OrePrefixes.cell, aMaterial);
        ItemStack aPlasmaStack = MatUnifier.get(OrePrefixes.cellPlasma, aMaterial);
        if (aMaterial == Materials.Empty) {
            GT_ModHandler.removeRecipeByOutput(aCellStack);
            GT_ModHandler.removeRecipeByOutput(aPlasmaStack);
        } else {
            GT_Values.RA.addFuel(aPlasmaStack, GT_Utility.getFluidForFilledItem(aPlasmaStack, true) == null ? GT_Utility.getContainerItem(aPlasmaStack, true) : null, (int) Math.max(1024L, 1024L * aMaterial.getMass()), 4);
            GT_Values.RA.addVacuumFreezerRecipe(aPlasmaStack, aCellStack, (int) Math.max(aMaterial.getMass() * 2L, 1L));
        }
        if (GT_Utility.getFluidForFilledItem(MatUnifier.get(OrePrefixes.cell, aMaterial), true) == null) {
            GT_Values.RA.addCannerRecipe(aNormalDustStack, ItemList.Cell_Empty.get(1), MatUnifier.get(OrePrefixes.cell, aMaterial), null, 100, 1);
        }
    }

    public void addOreRecipes(Materials aMaterial, boolean aIsRich) { //Ore, Crushed, CrushedCent, CrushedPure
        ArrayList<Materials> aAlreadyListedOres = new ArrayList(1000);
        Materials tMaterial = aMaterial.mOreReplacement;
        Materials tPrimaryByMaterial = null;
        int aMultiplier = Math.max(1, Math.max(1, GregTech_API.sOPStuff.get(ConfigCategories.Materials.oreprocessingoutputmultiplier, aMaterial.toString(), 1)) * (aIsRich ? 2 : 1));
        ItemStack aOreStack = MatUnifier.get(OrePrefixes.ore, aMaterial);
        ItemStack aIngotStack = MatUnifier.get(OrePrefixes.ingot, aMaterial.mDirectSmelting);
        ItemStack aGemStack = MatUnifier.get(OrePrefixes.gem, tMaterial);
        ItemStack aSmeltIntoStack = aIngotStack == null ? null : aMaterial.contains(SubTag.SMELTING_TO_GEM) ? MatUnifier.get(OrePrefixes.gem, tMaterial.mDirectSmelting, MatUnifier.get(OrePrefixes.crushedCentrifuged, tMaterial.mDirectSmelting, MatUnifier.get(OrePrefixes.gem, tMaterial, MatUnifier.get(OrePrefixes.crushedCentrifuged, tMaterial)))) : aIngotStack;
        ItemStack aDustStack = MatUnifier.get(OrePrefixes.dust, tMaterial, aGemStack);
        ItemStack aCrushedPureStack = MatUnifier.get(OrePrefixes.crushedPurified, tMaterial, aDustStack);
        ItemStack aCrushedStack = MatUnifier.get(OrePrefixes.crushed, tMaterial, aMaterial.mOreMultiplier * aMultiplier);
        ItemStack aCrushedCentStack = MatUnifier.get(OrePrefixes.crushedCentrifuged, aMaterial, 1); //TODO MULTI?
        ItemStack aDustStone = MatUnifier.get(OrePrefixes.dust, Materials.Stone);

        ItemStack tPrimaryByProduct = null;

        ArrayList<ItemStack> tByProductStacks = new ArrayList();

        if (aMaterial.contains(SubTag.WASHING_MERCURY)) {
            //TODO FIX GT_Values.RA.addChemicalBathRecipe(GT_Utility.copyAmount(1, aStack), Materials.Mercury.getFluid(1000), MatUnifier.get(aPrefix == OrePrefixes.crushed ? OrePrefixes.crushedPurified : OrePrefixes.dustPure, aMaterial, 1L), MatUnifier.get(OrePrefixes.dust, aMaterial.mMacerateInto, 1L), MatUnifier.get(OrePrefixes.dust, Materials.Stone, 1L), new int[]{10000, 7000, 4000}, 800, 8);
        }
        if (aMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
            //TODO FIX GT_Values.RA.addChemicalBathRecipe(GT_Utility.copyAmount(1, aStack), Materials.SodiumPersulfate.getFluid(GT_Mod.gregtechproxy.mDisableOldChemicalRecipes ? 1000 : 100), MatUnifier.get(aPrefix == OrePrefixes.crushed ? OrePrefixes.crushedPurified : OrePrefixes.dustPure, aMaterial, 1L), MatUnifier.get(OrePrefixes.dust, aMaterial.mMacerateInto, 1L), MatUnifier.get(OrePrefixes.dust, Materials.Stone, 1L), new int[]{10000, 7000, 4000}, 800, 8);
        }
        for (Materials tMat : aMaterial.mOreByProducts) {
            ItemStack tByProduct = MatUnifier.get(OrePrefixes.dust, tMat);
            if (tByProduct != null) tByProductStacks.add(tByProduct);
            if (tPrimaryByProduct == null) {
                tPrimaryByMaterial = tMat;
                tPrimaryByProduct = MatUnifier.get(OrePrefixes.dust, tMat);
                if (MatUnifier.get(OrePrefixes.dustSmall, tMat) == null) {
                    MatUnifier.get(OrePrefixes.dustTiny, tMat, MatUnifier.get(OrePrefixes.nugget, tMat), 2);
                }
            }
            MatUnifier.get(OrePrefixes.dust, tMat);
            if (MatUnifier.get(OrePrefixes.dustSmall, tMat) == null) {
                MatUnifier.get(OrePrefixes.dustTiny, tMat, MatUnifier.get(OrePrefixes.nugget, tMat), 2);
            }
            if (tMat.contains(SubTag.WASHING_MERCURY)) {
                GT_Values.RA.addChemicalBathRecipe(aCrushedStack, Materials.Mercury.getFluid(1000), aCrushedPureStack/*TODO CHECK GT_OreDictUnificator.get(aPrefix == OrePrefixes.crushed ? OrePrefixes.crushedPurified : OrePrefixes.dustPure, aMaterial, 1L)*/, MatUnifier.get(OrePrefixes.dust, tMat.mMacerateInto), aDustStone, new int[]{10000, 7000, 4000}, 800, 8);
            }
            if (tMat.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
                GT_Values.RA.addChemicalBathRecipe(aCrushedStack, Materials.SodiumPersulfate.getFluid(GT_Mod.gregtechproxy.mDisableOldChemicalRecipes ? 1000 : 100), aCrushedPureStack/*TODO CHECK GT_OreDictUnificator.get(aPrefix == OrePrefixes.crushed ? OrePrefixes.crushedPurified : OrePrefixes.dustPure, aMaterial, 1L)*/, MatUnifier.get(OrePrefixes.dust, tMat.mMacerateInto), aDustStone, new int[]{10000, 7000, 4000}, 800, 8);
            }
        }
        if (!tByProductStacks.isEmpty() && !aAlreadyListedOres.contains(aMaterial)) {
            aAlreadyListedOres.add(aMaterial);
            GT_Recipe.GT_Recipe_Map.sByProductList.addFakeRecipe(false, new ItemStack[]{MatUnifier.get(OrePrefixes.ore, aMaterial, aOreStack)}, tByProductStacks.toArray(new ItemStack[tByProductStacks.size()]), null, null, null, null, 0, 0, 0);
        }

        if (tPrimaryByMaterial == null) tPrimaryByMaterial = tMaterial;
        if (tPrimaryByProduct == null) tPrimaryByProduct = aDustStack;
        boolean tHasSmelting = false;

        if (aSmeltIntoStack != null) {
            if ((aMaterial.mBlastFurnaceRequired) || (aMaterial.mDirectSmelting.mBlastFurnaceRequired)) {
                GT_ModHandler.removeFurnaceSmelting(aOreStack);
            } else {
                tHasSmelting = GT_ModHandler.addSmeltingRecipe(aOreStack, GT_Utility.copyAmount(aMultiplier * aMaterial.mSmeltingMultiplier, aSmeltIntoStack));
            }

            if (aMaterial.contains(SubTag.BLASTFURNACE_CALCITE_TRIPLE)) {
                GT_Values.RA.addBlastRecipe(aOreStack, MatUnifier.get(OrePrefixes.dust, Materials.Calcite, aMultiplier), null, null, GT_Utility.mul(aMultiplier * 3 * aMaterial.mSmeltingMultiplier, aSmeltIntoStack), MatUnifier.get(OrePrefixes.dustSmall, Materials.DarkAsh), aSmeltIntoStack.stackSize * 500, 120, 1500);
                GT_Values.RA.addBlastRecipe(aOreStack, MatUnifier.get(OrePrefixes.dust, Materials.Quicklime, aMultiplier), null, null, GT_Utility.mul(aMultiplier * 3 * aMaterial.mSmeltingMultiplier, aSmeltIntoStack), MatUnifier.get(OrePrefixes.dustSmall, Materials.DarkAsh), aSmeltIntoStack.stackSize * 500, 120, 1500);
            } else if (aMaterial.contains(SubTag.BLASTFURNACE_CALCITE_DOUBLE)) {
                GT_Values.RA.addBlastRecipe(aOreStack, MatUnifier.get(OrePrefixes.dust, Materials.Calcite, aMultiplier), null, null, GT_Utility.mul(aMultiplier * 2 * aMaterial.mSmeltingMultiplier, aSmeltIntoStack), MatUnifier.get(OrePrefixes.dustSmall, Materials.DarkAsh), aSmeltIntoStack.stackSize * 500, 120, 1500);
                GT_Values.RA.addBlastRecipe(aOreStack, MatUnifier.get(OrePrefixes.dust, Materials.Quicklime, aMultiplier), null, null, GT_Utility.mul(aMultiplier * 2 * aMaterial.mSmeltingMultiplier, aSmeltIntoStack), MatUnifier.get(OrePrefixes.dustSmall, Materials.DarkAsh), aSmeltIntoStack.stackSize * 500, 120, 1500);
            }
        }
        if (!tHasSmelting) {
            GT_ModHandler.addSmeltingRecipe(aOreStack, MatUnifier.get(OrePrefixes.gem, tMaterial.mDirectSmelting, Math.max(1, aMultiplier * aMaterial.mSmeltingMultiplier / 2)));
        }
        if (aCrushedStack == null) {
            aCrushedStack = MatUnifier.get(OrePrefixes.dustImpure, tMaterial, GT_Utility.copyAmount(aMaterial.mOreMultiplier * aMultiplier, aCrushedPureStack, aDustStack, aGemStack), aMaterial.mOreMultiplier * aMultiplier);
            //TODO NULLPOINTER GT_Values.RA.addForgeHammerRecipe(aOreStack, GT_Utility.copy(GT_Utility.copyAmount(aCrushedStack.stackSize, aGemStack), aCrushedStack), 16, 10);
            GT_ModHandler.addPulverisationRecipe(aOreStack, GT_Utility.mul(2L, aCrushedStack), tMaterial.contains(SubTag.PULVERIZING_CINNABAR) ? MatUnifier.get(OrePrefixes.crushedCentrifuged, Materials.Cinnabar, MatUnifier.get(OrePrefixes.gem, tPrimaryByMaterial, GT_Utility.copyAmount(1, tPrimaryByProduct))) : MatUnifier.get(OrePrefixes.gem, tPrimaryByMaterial, GT_Utility.copyAmount(1, tPrimaryByProduct), 1), tPrimaryByProduct == null ? 0 : tPrimaryByProduct.stackSize * 10 * aMultiplier * aMaterial.mByProductMultiplier, MatUnifier.getDust(OrePrefixes.ore.mSecondaryMaterial), 50, true);
        }
        if (aWashingMercury) {
            GT_Values.RA.addChemicalBathRecipe(aCrushedStack, Materials.Mercury.getFluid(1000), aCrushedPureStack/*TODO CHECK GT_OreDictUnificator.get(aPrefix == OrePrefixes.crushed ? OrePrefixes.crushedPurified : OrePrefixes.dustPure, aMaterial, 1L)*/, MatUnifier.get(OrePrefixes.dust, aMaterial.mMacerateInto), aDustStone, new int[]{10000, 7000, 4000}, 800, 8);
        }
        if (aWashingSodium) {
            GT_Values.RA.addChemicalBathRecipe(aCrushedStack, Materials.SodiumPersulfate.getFluid(GT_Mod.gregtechproxy.mDisableOldChemicalRecipes ? 1000 : 100), aCrushedPureStack/*TODO CHECK GT_OreDictUnificator.get(aPrefix == OrePrefixes.crushed ? OrePrefixes.crushedPurified : OrePrefixes.dustPure, aMaterial, 1L)*/, MatUnifier.get(OrePrefixes.dust, aMaterial.mMacerateInto), aDustStone, new int[]{10000, 7000, 4000}, 800, 8);
        }
        GT_Values.RA.addForgeHammerRecipe(aCrushedStack, MatUnifier.get(OrePrefixes.dustImpure, aMaterial.mMacerateInto), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(aCrushedCentStack, MatUnifier.get(OrePrefixes.dust, aMaterial.mMacerateInto), 10, 16);
        GT_Values.RA.addForgeHammerRecipe(aCrushedPureStack, MatUnifier.get(OrePrefixes.dustPure, aMaterial.mMacerateInto), 10, 16);
        GT_Values.RA.addSifterRecipe(aCrushedPureStack, new ItemStack[]{MatUnifier.get(OrePrefixes.gemExquisite, aMaterial, aGemStack), MatUnifier.get(OrePrefixes.gemFlawless, aMaterial, aGemStack, 1), aGemStack, MatUnifier.get(OrePrefixes.gemFlawed, aMaterial, aGemStack), MatUnifier.get(OrePrefixes.gemChipped, aMaterial, aGemStack), MatUnifier.get(OrePrefixes.dust, aMaterial, aGemStack)}, new int[]{100, 400, 1500, 2000, 4000, 5000}, 800, 16);
        GT_ModHandler.addPulverisationRecipe(aCrushedStack, MatUnifier.get(OrePrefixes.dustImpure, aMaterial.mMacerateInto, MatUnifier.get(OrePrefixes.dust, aMaterial.mMacerateInto)), MatUnifier.get(OrePrefixes.dust, GT_Utility.selectItemInList(0, aMaterial.mMacerateInto, aMaterial.mOreByProducts)), 10, false);
        GT_ModHandler.addPulverisationRecipe(aCrushedCentStack, MatUnifier.get(OrePrefixes.dust, aMaterial.mMacerateInto), MatUnifier.get(OrePrefixes.dust, GT_Utility.selectItemInList(2, aMaterial.mMacerateInto, aMaterial.mOreByProducts)), 10, false);
        GT_ModHandler.addPulverisationRecipe(aCrushedPureStack, MatUnifier.get(OrePrefixes.dustPure, aMaterial.mMacerateInto, MatUnifier.get(OrePrefixes.dust, aMaterial.mMacerateInto)), MatUnifier.get(OrePrefixes.dust, GT_Utility.selectItemInList(1, aMaterial.mMacerateInto, aMaterial.mOreByProducts)), 10, false);
        GT_ModHandler.addPulverisationRecipe(aCrushedPureStack, MatUnifier.get(OrePrefixes.dustPure, aMaterial.mMacerateInto, MatUnifier.get(OrePrefixes.dust, aMaterial.mMacerateInto)), MatUnifier.get(OrePrefixes.dust, GT_Utility.selectItemInList(1, aMaterial.mMacerateInto, aMaterial.mOreByProducts)), 10, false);
        GT_ModHandler.addOreWasherRecipe(aCrushedStack, 1000, aCrushedPureStack/*TODO CHECK GT_OreDictUnificator.get(aPrefix == OrePrefixes.crushed ? OrePrefixes.crushedPurified : OrePrefixes.dustPure, aMaterial, 1L)*/, MatUnifier.get(OrePrefixes.dustTiny, GT_Utility.selectItemInList(0, aMaterial.mMacerateInto, aMaterial.mOreByProducts)), aDustStone);
        GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.dustPure, aMaterial.mMacerateInto), tBits, new Object[]{"h", "X", 'X', aCrushedPureStack});
        GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.dustImpure, aMaterial.mMacerateInto), tBits, new Object[]{"h", "X", 'X', aCrushedStack});
        GT_ModHandler.removeFurnaceSmelting(aCrushedStack);
        GT_ModHandler.removeFurnaceSmelting(aCrushedPureStack);
        GT_ModHandler.removeFurnaceSmelting(aCrushedCentStack);
        GT_ModHandler.removeFurnaceSmelting(MatUnifier.get(OrePrefixes.dustImpure, aMaterial, 1));
        GT_ModHandler.removeFurnaceSmelting(MatUnifier.get(OrePrefixes.dustPure, aMaterial, 1));
        ItemStack tStack = MatUnifier.get(OrePrefixes.nugget, aMaterial.mDirectSmelting, aMaterial.mDirectSmelting == aMaterial ? 10 : 3);
        if (tStack == null) {
            tStack = MatUnifier.get(aMaterial.contains(SubTag.SMELTING_TO_GEM) ? OrePrefixes.gem : OrePrefixes.ingot, aMaterial.mDirectSmelting);
        }
        GT_ModHandler.addSmeltingRecipe(aCrushedStack, tStack);
        GT_ModHandler.addSmeltingRecipe(aCrushedPureStack, tStack);
        GT_ModHandler.addSmeltingRecipe(aCrushedCentStack, tStack);
        GT_ModHandler.addSmeltingRecipe(MatUnifier.get(OrePrefixes.dustImpure, aMaterial, 1), MatUnifier.get(OrePrefixes.ingot, aMaterial.mDirectSmelting));
        GT_ModHandler.addSmeltingRecipe(MatUnifier.get(OrePrefixes.dustPure, aMaterial, 1), MatUnifier.get(OrePrefixes.ingot, aMaterial.mDirectSmelting));

        for (OrePrefixes aPrefix : new ArrayList<>(Arrays.asList(new OrePrefixes[]{OrePrefixes.dustImpure, OrePrefixes.dustPure}))) {
            ItemStack aStack = aPrefix == OrePrefixes.dustImpure ? MatUnifier.get(OrePrefixes.dustImpure, aMaterial) : MatUnifier.get(OrePrefixes.dustPure, aMaterial);

            //Pure/Impure Dust
            Materials tByProduct = GT_Utility.selectItemInList(aPrefix == OrePrefixes.dustPure ? 1 : 0, aMaterial, aMaterial.mOreByProducts);
            ItemStack tImpureStack = MatUnifier.get(OrePrefixes.dustTiny, tByProduct, MatUnifier.get(OrePrefixes.nugget, tByProduct));
            if (tImpureStack == null) {
                tImpureStack = MatUnifier.get(OrePrefixes.dustSmall, tByProduct);
                if (tImpureStack == null) {
                    tImpureStack = MatUnifier.get(OrePrefixes.dust, tByProduct, MatUnifier.get(OrePrefixes.gem, tByProduct));
                    if (tImpureStack == null) {
                        tImpureStack = MatUnifier.get(OrePrefixes.cell, tByProduct);
                        if (tImpureStack == null) {
                            GT_Values.RA.addCentrifugeRecipe(aStack, 0, aNormalDustStack, null, null, null, null, null, (int) Math.max(1L, aMaterial.getMass()));
                        } else {
                            FluidStack tFluid = GT_Utility.getFluidForFilledItem(tImpureStack, true);
                            if (tFluid == null) {
                                GT_Values.RA.addCentrifugeRecipe(GT_Utility.copyAmount(9, aStack), 1, GT_Utility.copyAmount(9, aNormalDustStack), tImpureStack, null, null, null, null, (int) Math.max(1L, aMaterial.getMass() * 72L));
                            } else {
                                tFluid.amount /= 10;
                                GT_Values.RA.addCentrifugeRecipe(aStack, null, null, tFluid, aNormalDustStack, null, null, null, null, null, null, (int) Math.max(1L, aMaterial.getMass() * 8L), 5);
                            }
                        }
                    } else {
                        GT_Values.RA.addCentrifugeRecipe(GT_Utility.copyAmount(9, aStack), 0, GT_Utility.copyAmount(9, aNormalDustStack), tImpureStack, null, null, null, null, (int) Math.max(1L, aMaterial.getMass() * 72L));
                    }
                } else {
                    GT_Values.RA.addCentrifugeRecipe(GT_Utility.copyAmount(2, aStack), 0, GT_Utility.copyAmount(2, aNormalDustStack), tImpureStack, null, null, null, null, (int) Math.max(1L, aMaterial.getMass() * 16L));
                }
            } else {
                GT_Values.RA.addCentrifugeRecipe(aStack, 0, aNormalDustStack, tImpureStack, null, null, null, null, (int) Math.max(1L, aMaterial.getMass() * 8L));
            }
        }
        if (aMaterial.contains(SubTag.ELECTROMAGNETIC_SEPERATION_GOLD))
            GT_Values.RA.addElectromagneticSeparatorRecipe(MatUnifier.get(OrePrefixes.dustPure, aMaterial), MatUnifier.get(OrePrefixes.dust, aMaterial), MatUnifier.get(OrePrefixes.dustSmall, Materials.Gold), MatUnifier.get(OrePrefixes.nugget, Materials.Gold), new int[]{10000, 4000, 2000}, 400, 24);
        if (aMaterial.contains(SubTag.ELECTROMAGNETIC_SEPERATION_IRON))
            GT_Values.RA.addElectromagneticSeparatorRecipe(MatUnifier.get(OrePrefixes.dustPure, aMaterial), MatUnifier.get(OrePrefixes.dust, aMaterial), MatUnifier.get(OrePrefixes.dustSmall, Materials.Iron), MatUnifier.get(OrePrefixes.nugget, Materials.Iron), new int[]{10000, 4000, 2000}, 400, 24);
        if (aMaterial.contains(SubTag.ELECTROMAGNETIC_SEPERATION_NEODYMIUM)) {
            GT_Values.RA.addElectromagneticSeparatorRecipe(MatUnifier.get(OrePrefixes.dustPure, aMaterial), MatUnifier.get(OrePrefixes.dust, aMaterial), MatUnifier.get(OrePrefixes.dustSmall, Materials.Neodymium), MatUnifier.get(OrePrefixes.nugget, Materials.Neodymium), new int[]{10000, 4000, 2000}, 400, 24);
        }
    }

    public void addGemRecipes(Materials aMaterial) {
        long aMaterialMass = aMaterial.getMass();
        ItemStack aGemStack = MatUnifier.get(OrePrefixes.gem, aMaterial, 1);
        ItemStack aChippedStack = MatUnifier.get(OrePrefixes.gemChipped, aMaterial, 1);
        ItemStack aFlawedStack = MatUnifier.get(OrePrefixes.gemFlawed, aMaterial, 1);
        ItemStack aFlawlessStack = MatUnifier.get(OrePrefixes.gemFlawless, aMaterial, 1);
        ItemStack aExquisiteStack = MatUnifier.get(OrePrefixes.gemExquisite, aMaterial, 1);
        ItemStack aPlateStack = MatUnifier.get(OrePrefixes.plate, aMaterial, 1);
        ItemStack aBoltStack = MatUnifier.get(OrePrefixes.bolt, aMaterial, 1);
        if (aMaterial.mFuelPower > 0) {
            GT_Values.RA.addFuel(aGemStack, null, aMaterial.mFuelPower * 2, aMaterial.mFuelType);
            GT_Values.RA.addFuel(aChippedStack, null, aMaterial.mFuelPower / 2, aMaterial.mFuelType);
            GT_Values.RA.addFuel(aFlawedStack, null, aMaterial.mFuelPower, aMaterial.mFuelType);
            GT_Values.RA.addFuel(aFlawlessStack, null, aMaterial.mFuelPower * 4, aMaterial.mFuelType);
            GT_Values.RA.addFuel(aExquisiteStack, null, aMaterial.mFuelPower * 8, aMaterial.mFuelType);
        }
        if (!OrePrefixes.block.isIgnored(aMaterial)) {
            GT_ModHandler.addCompressionRecipe(GT_Utility.copyAmount(9, aGemStack), MatUnifier.get(OrePrefixes.block, aMaterial));
        }
        if (!aNoSmelting) {
            GT_ModHandler.addSmeltingRecipe(aGemStack, MatUnifier.get(OrePrefixes.ingot, aMaterial.mSmeltInto));
        }
        if (!aNoSmashing) {
            GT_Values.RA.addForgeHammerRecipe(aGemStack, aPlateStack, (int) Math.max(aMaterialMass, 1L), 16);
            GT_Values.RA.addBenderRecipe(aGemStack, aPlateStack, (int) Math.max(aMaterialMass * 2L, 1L), 24);
            GT_Values.RA.addBenderRecipe(GT_Utility.copyAmount(9, aGemStack), MatUnifier.get(OrePrefixes.plateDense, aMaterial), (int) Math.max(aMaterialMass * 9L, 1L), 96);
        } else {
            GT_Values.RA.addForgeHammerRecipe(aGemStack, GT_Utility.copyAmount(2, aFlawedStack), 64, 16);
        }
        if (!aNoWorking) {
            GT_Values.RA.addLatheRecipe(aChippedStack, aBoltStack, aTinyDustStack, (int) Math.max(aMaterialMass, 1L), 8);
            GT_Values.RA.addLatheRecipe(aFlawedStack, GT_Utility.copyAmount(2, aBoltStack), aSmallDustStack, (int) Math.max(aMaterialMass, 1L), 12);
            if (aMaterial.mUnificatable && (aMaterial.mMaterialInto == aMaterial)) {
                GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(2, aGemStack), tBits, new Object[]{"h", "X", Character.valueOf('X'), aFlawlessStack});
                GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(2, aChippedStack), tBits, new Object[]{"h", "X", Character.valueOf('X'), aFlawedStack});
                GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(2, aFlawedStack), tBits, new Object[]{"h", "X", Character.valueOf('X'), aGemStack});
                GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(2, aFlawlessStack), tBits, new Object[]{"h", "X", Character.valueOf('X'), aExquisiteStack});
                if (aMaterial.contains(SubTag.MORTAR_GRINDABLE) && GregTech_API.sRecipeFile.get(ConfigCategories.Tools.mortar, aMaterial.mName, true)) {
                    GT_ModHandler.addCraftingRecipe(aNormalDustStack, tBits, new Object[]{"X", "m", Character.valueOf('X'), aGemStack});
                    GT_ModHandler.addCraftingRecipe(aSmallDustStack, tBits, new Object[]{"X", "m", Character.valueOf('X'), aChippedStack});
                    GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(2, aSmallDustStack), tBits, new Object[]{"X", "m", Character.valueOf('X'), aFlawedStack});
                    GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(2, aNormalDustStack), tBits, new Object[]{"X", "m", Character.valueOf('X'), aFlawlessStack});
                    GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(4, aNormalDustStack), tBits, new Object[]{"X", "m", Character.valueOf('X'), aExquisiteStack});
                }
                if (aMaterial.contains(SubTag.SMELTING_TO_GEM)) {
                    GT_ModHandler.addCraftingRecipe(aGemStack, tBits, new Object[]{"XXX", "XXX", "XXX", Character.valueOf('X'), OrePrefixes.nugget.get(aMaterial)});
                }
            }
        } else {
            GT_Values.RA.addLatheRecipe(aGemStack, MatUnifier.get(OrePrefixes.stick, aMaterial), GT_Utility.copyAmount(2, aSmallDustStack), (int) Math.max(aMaterialMass, 1L), 16);
        }
        GT_Values.RA.addForgeHammerRecipe(aFlawedStack, GT_Utility.copyAmount(2, aChippedStack), 64, 16);
        GT_Values.RA.addForgeHammerRecipe(aFlawlessStack, GT_Utility.copyAmount(2, aGemStack), 64, 16);
        //TODO NPE GT_RecipeRegistrator.registerUsagesForMaterials(aGemStack, aPlateStack.toString(), !aNoSmashing);
        if (aMaterial.mTransparent) {
            ItemStack aLensStack = MatUnifier.get(OrePrefixes.lens, aMaterial);
            GT_Values.RA.addLatheRecipe(aPlateStack, aLensStack, aSmallDustStack, (int) Math.max(aMaterial.getMass() / 2L, 1L), 480);
            GT_Values.RA.addLatheRecipe(aExquisiteStack, aLensStack, GT_Utility.copyAmount(2, aNormalDustStack), (int) Math.max(aMaterial.getMass() , 1L), 24);
            GregTech_API.registerCover(aLensStack, new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_LENS, aMaterial.mRGBa, false)), new GT_Cover_Lens(aMaterial.mColor.mIndex));
        }
        FluidStack aWaterStack = Materials.Water.getFluid(200);
        FluidStack aDistilledStack = GT_ModHandler.getDistilledWater(200);

        if (aMaterial.contains(SubTag.CRYSTALLISABLE)) {
            ItemStack aPureDust = MatUnifier.get(OrePrefixes.dustPure, aMaterial, 1);
            ItemStack aImpureDust = MatUnifier.get(OrePrefixes.dustImpure, aMaterial, 1);
            GT_Values.RA.addAutoclaveRecipe(aNormalDustStack, aWaterStack, aGemStack, 7000, 2000, 24);
            GT_Values.RA.addAutoclaveRecipe(aNormalDustStack, aDistilledStack, aGemStack, 9000, 1500, 24);
            GT_Values.RA.addAutoclaveRecipe(aPureDust, aWaterStack, aGemStack, 9000, 2000, 24);
            GT_Values.RA.addAutoclaveRecipe(aPureDust, aDistilledStack, aGemStack, 9500, 1500, 24);
            GT_Values.RA.addAutoclaveRecipe(aImpureDust, aWaterStack, aGemStack, 9000, 2000, 24);
            GT_Values.RA.addAutoclaveRecipe(aImpureDust, aDistilledStack, aGemStack, 9500, 1500, 24);
        }
    }

    public void addShapingRecipes(Materials aMaterial) {
        if ((aMaterial == Materials.Glass || aIngotStack != null) && (!aNoSmelting)) {
            long aMaterialMass = aMaterial.getMass();
            int tAmount = (int) (OrePrefixes.ingot.mMaterialAmount / 3628800L);
            if ((tAmount > 0) && (tAmount <= 64) && (OrePrefixes.ingot.mMaterialAmount % 3628800L == 0L)) {
                int tVoltageMultiplier = aMaterial.mBlastFurnaceTemp >= 2800 ? 64 : 16;

                if (aNoSmashing) {
                    tVoltageMultiplier /= 4;
                }

                if (!OrePrefixes.block.isIgnored(aMaterial.mSmeltInto)) {
                    ItemStack a9xIngotStack = GT_Utility.copyAmount(9, aIngotStack);
                    ItemStack aBlockStack = MatUnifier.get(OrePrefixes.block, aMaterial.mSmeltInto, tAmount);
                    GT_Values.RA.addExtruderRecipe(a9xIngotStack, ItemList.Shape_Extruder_Block.get(0), aBlockStack, 10 * tAmount, 8 * tVoltageMultiplier);
                    GT_Values.RA.addAlloySmelterRecipe(a9xIngotStack, ItemList.Shape_Mold_Block.get(0), aBlockStack, 5 * tAmount, 4 * tVoltageMultiplier);
                }
                if (aMaterial != aMaterial.mSmeltInto) {
                    GT_Values.RA.addExtruderRecipe(aNormalDustStack, ItemList.Shape_Extruder_Ingot.get(0), MatUnifier.get(OrePrefixes.ingot, aMaterial.mSmeltInto, tAmount), 10, 4 * tVoltageMultiplier);
                }

                GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Pipe_Tiny.get(0), MatUnifier.get(OrePrefixes.pipeTiny, aMaterial.mSmeltInto, tAmount * 2), 4 * tAmount, 8 * tVoltageMultiplier);
                if (!(aMaterial == Materials.Redstone || aMaterial == Materials.Glowstone)) {
                    GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Pipe_Small.get(0), MatUnifier.get(OrePrefixes.pipeSmall, aMaterial.mSmeltInto, tAmount), 8 * tAmount, 8 * tVoltageMultiplier);
                    GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(3, aIngotStack), ItemList.Shape_Extruder_Pipe_Medium.get(0), MatUnifier.get(OrePrefixes.pipeMedium, aMaterial.mSmeltInto, tAmount), 24 * tAmount, 8 * tVoltageMultiplier);
                    GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(6, aIngotStack), ItemList.Shape_Extruder_Pipe_Large.get(0), MatUnifier.get(OrePrefixes.pipeLarge, aMaterial.mSmeltInto, tAmount), 48 * tAmount, 8 * tVoltageMultiplier);
                }
                GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(12, aIngotStack), ItemList.Shape_Extruder_Pipe_Huge.get(0), MatUnifier.get(OrePrefixes.pipeHuge, aMaterial.mSmeltInto, tAmount), 96 * tAmount, 8 * tVoltageMultiplier);
            }
        }
    }

    public void addSolidRecipes(Materials aMaterial) { //Nugget & Ingot Processing
        long aMaterialMass = aMaterial.getMass();
        if (aMaterial.mFuelPower > 0) {
            GT_Values.RA.addFuel(aIngotStack, null, aMaterial.mFuelPower, aMaterial.mFuelType);
        }
        if (aMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Ingot.get(0), aMaterial.getMolten(144), aIngotStack, 32, 8);
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Nugget.get(0), aMaterial.getMolten(16), aNuggetStack, 16, 4);
        }
        GT_RecipeRegistrator.registerReverseFluidSmelting(aIngotStack, aMaterial, OrePrefixes.ingot.mMaterialAmount, null);
        GT_RecipeRegistrator.registerReverseMacerating(aIngotStack, aMaterial, OrePrefixes.ingot.mMaterialAmount, null, null, null, false);
        GT_RecipeRegistrator.registerReverseFluidSmelting(aNuggetStack, aMaterial, OrePrefixes.nugget.mMaterialAmount, null);
        GT_RecipeRegistrator.registerReverseMacerating(aNuggetStack, aMaterial, OrePrefixes.nugget.mMaterialAmount, null, null, null, false);
        if (aMaterial.mSmeltInto.mArcSmeltInto != aMaterial) {
            GT_RecipeRegistrator.registerReverseArcSmelting(aIngotStack, aMaterial, OrePrefixes.ingot.mMaterialAmount, null, null, null);
        }
        ItemStack tStack = MatUnifier.get(OrePrefixes.dust, aMaterial.mMacerateInto);
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
            GT_Values.RA.addForgeHammerRecipe(GT_Utility.copyAmount(3, aIngotStack), MatUnifier.get(OrePrefixes.plate, aMaterial, 2), (int) Math.max(aMaterialMass, 1L), 16);
            GT_Values.RA.addBenderRecipe(aIngotStack, MatUnifier.get(OrePrefixes.plate, aMaterial), (int) Math.max(aMaterialMass, 1L), 24);
            GT_Values.RA.addBenderRecipe(GT_Utility.copyAmount(9, aIngotStack), MatUnifier.get(OrePrefixes.plateDense, aMaterial), (int) Math.max(aMaterialMass * 9L, 1L), 96);
        }
        GT_RecipeRegistrator.registerUsagesForMaterials(aIngotStack, OrePrefixes.plate.get(aMaterial).toString(), !aNoSmashing);
        GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(9, aNuggetStack), aMaterial.contains(SubTag.SMELTING_TO_GEM) ? ItemList.Shape_Mold_Ball.get(0) : ItemList.Shape_Mold_Ingot.get(0), MatUnifier.get(aMaterial.contains(SubTag.SMELTING_TO_GEM) ? OrePrefixes.gem : OrePrefixes.ingot, aMaterial.mSmeltInto, 1), 200, 2);
        if (!aNoSmelting) {
            GT_Values.RA.addAlloySmelterRecipe(aIngotStack, ItemList.Shape_Mold_Nugget.get(0), GT_Utility.copyAmount(9, aNuggetStack), 100, 1);
        }
        if (aMaterial.mStandardMoltenFluid == null && aMaterial.contains(SubTag.SMELTING_TO_FLUID) && !aNoSmelting) {
            GT_Mod.gregtechproxy.addAutogeneratedMoltenFluid(aMaterial);
            if (aMaterial.mSmeltInto != aMaterial && aMaterial.mSmeltInto.mStandardMoltenFluid == null) {
                GT_Mod.gregtechproxy.addAutogeneratedMoltenFluid(aMaterial.mSmeltInto);
            }
        }
    }

    public void addBasicDustRecipes(Materials aMaterial) {
        GT_Values.RA.addBoxingRecipe(GT_Utility.copyAmount(4, aSmallDustStack), ItemList.Schematic_Dust.get(0), aNormalDustStack, 100, 4);
        GT_Values.RA.addBoxingRecipe(GT_Utility.copyAmount(9, aTinyDustStack), ItemList.Schematic_Dust.get(0), aNormalDustStack, 100, 4);
        GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.dust, aMaterial.mMacerateInto), tBits, new Object[]{"h", "X", 'X', OrePrefixes.crushedCentrifuged.get(aMaterial)});
        GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(4, aSmallDustStack), tBits, new Object[]{" X", "  ", 'X', aNormalDustStack});
        GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(9, aTinyDustStack), tBits, new Object[]{"X ", "  ", 'X', aNormalDustStack});
        GT_ModHandler.addCraftingRecipe(aNormalDustStack, tBits, new Object[]{"XX", "XX", 'X', aSmallDustStack});
        GT_ModHandler.addCraftingRecipe(aNormalDustStack, tBits, new Object[]{"XXX", "XXX", "XXX", 'X', aTinyDustStack});
    }

    public void addDustRecipes(Materials aMaterial) { //Tiny, Small, Normal, Impure & Pure Dust Processing
        addBasicDustRecipes(aMaterial);
        if (!aMaterial.mBlastFurnaceRequired) {
            if (aMaterial.mSmeltInto != null) {
                GT_RecipeRegistrator.registerReverseFluidSmelting(aNormalDustStack, aMaterial, OrePrefixes.dust.mMaterialAmount, null);
                GT_RecipeRegistrator.registerReverseFluidSmelting(aSmallDustStack, aMaterial, OrePrefixes.dustSmall.mMaterialAmount, null);
                GT_RecipeRegistrator.registerReverseFluidSmelting(aTinyDustStack, aMaterial, OrePrefixes.dustTiny.mMaterialAmount, null);
            }
            GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copyAmount(4, aSmallDustStack), ItemList.Shape_Mold_Ingot.get(0), MatUnifier.get(OrePrefixes.ingot, aMaterial.mSmeltInto), 130, 3, true);
            if (!aNoSmelting) {
                GT_ModHandler.addSmeltingRecipe(aTinyDustStack, MatUnifier.get(OrePrefixes.nugget, aMaterial.mSmeltInto));
                GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copyAmount(9, aTinyDustStack), ItemList.Shape_Mold_Ingot.get(0), MatUnifier.get(OrePrefixes.ingot, aMaterial.mSmeltInto), 130, 3, true);
            }
            if (aMaterial.mSmeltInto != null && aMaterial.mSmeltInto.mArcSmeltInto != aMaterial) {
                GT_RecipeRegistrator.registerReverseArcSmelting(aNormalDustStack, aMaterial, OrePrefixes.dust.mMaterialAmount, null, null, null);
                GT_RecipeRegistrator.registerReverseArcSmelting(aSmallDustStack, aMaterial, OrePrefixes.dustSmall.mMaterialAmount, null, null, null);
                GT_RecipeRegistrator.registerReverseArcSmelting(aTinyDustStack, aMaterial, OrePrefixes.dustTiny.mMaterialAmount, null, null, null);
            }
        } else {
            //TODO MOVE TO HOT INGOT SECTION?
            int aBlastDuration = (int) Math.max(aMaterial.getMass() / 40L, 1L) * aMaterial.mBlastFurnaceTemp;
            ItemStack aBlastStack = aMaterial.mBlastFurnaceTemp > 1750 ? MatUnifier.get(OrePrefixes.ingotHot, aMaterial.mSmeltInto, MatUnifier.get(OrePrefixes.ingot, aMaterial.mSmeltInto)) : MatUnifier.get(OrePrefixes.ingot, aMaterial.mSmeltInto);
            GT_Values.RA.addBlastRecipe(GT_Utility.copyAmount(4, aSmallDustStack), null, null, null, aBlastStack, null, aBlastDuration, 120, aMaterial.mBlastFurnaceTemp);
            if (!aNoSmelting) {
                GT_Values.RA.addBlastRecipe(GT_Utility.copyAmount(9, aTinyDustStack), null, null, null, aBlastStack, null, aBlastDuration, 120, aMaterial.mBlastFurnaceTemp);
                GT_ModHandler.removeFurnaceSmelting(aTinyDustStack);
            }
        }
        if (aMaterial.mFuelPower > 0) {
            GT_Values.RA.addFuel(aNormalDustStack, null, aMaterial.mFuelPower, aMaterial.mFuelType);
        }
        ItemStack aDustSmeltInto = aMaterial.mSmeltInto.getIngots(1);
        if (aDustSmeltInto != null && !aNoSmelting) {
            GT_ModHandler.addSmeltingRecipe(aNormalDustStack, aDustSmeltInto);
        } else if (!aNoWorking) {
            if ((!OrePrefixes.block.isIgnored(aMaterial)) && (null == MatUnifier.get(OrePrefixes.gem, aMaterial))) {
                GT_ModHandler.addCompressionRecipe(GT_Utility.copyAmount(9, aNormalDustStack), MatUnifier.get(OrePrefixes.block, aMaterial));
            }
            if (((OrePrefixes.block.isIgnored(aMaterial)) || (null == MatUnifier.get(OrePrefixes.block, aMaterial))) && (aMaterial != Materials.GraniteRed) && (aMaterial != Materials.GraniteBlack) && (aMaterial != Materials.Glass) && (aMaterial != Materials.Obsidian) && (aMaterial != Materials.Glowstone) && (aMaterial != Materials.Paper)) {
                GT_ModHandler.addCompressionRecipe(aNormalDustStack, MatUnifier.get(OrePrefixes.plate, aMaterial));
            }
        }
        if (aMaterial.mMaterialList.size() > 0 && aMaterial.hasFlag(MaterialFlags.CENT.bit) && aMaterial.hasFlag(MaterialFlags.ELEC.bit)) {
            int tItemAmount = 0;
            int tCapsuleCount = 0;
            int tDensityMultiplier = aMaterial.getDensity() > 3628800 ? (int)aMaterial.getDensity() / 3628800 : 1;
            ArrayList<ItemStack> tList = new ArrayList<>();
            ItemStack tDustStack;
            for (MaterialStack tMat : aMaterial.mMaterialList) {
                if (tMat.mAmount > 0L) {
                    if (tMat.mMaterial == Materials.Air) {
                        tDustStack = Materials.Air.getCells((int)tMat.mAmount / 2);//TODO REMOVE CAST TO INT
                    } else {
                        tDustStack = MatUnifier.get(OrePrefixes.dust, tMat.mMaterial, (int)tMat.mAmount); //TODO REMOVE CAST TO INT
                        if (tDustStack == null)
                            tDustStack = MatUnifier.get(OrePrefixes.cell, tMat.mMaterial,(int) tMat.mAmount);//TODO REMOVE CAST TO INT
                    }
                    if (tItemAmount + tMat.mAmount * 3628800L <= aNormalDustStack.getMaxStackSize() * aMaterial.getDensity()) {
                        tItemAmount += tMat.mAmount * 3628800L;
                        if (tDustStack != null) {
                            tDustStack.stackSize = tDustStack.stackSize * tDensityMultiplier;
                            while ((tDustStack.stackSize > 64) && (tList.size() < 6) && (tCapsuleCount + GT_ModHandler.getCapsuleCellContainerCount(tDustStack) * 64 <= 64L)) {
                                tCapsuleCount += GT_ModHandler.getCapsuleCellContainerCount(tDustStack) * 64;
                                tList.add(GT_Utility.copyAmount(64, tDustStack));
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
            tItemAmount = (tItemAmount * tDensityMultiplier % aMaterial.getDensity() > 0L ? 1 : 0) + tItemAmount * tDensityMultiplier / (int) aMaterial.getDensity();
            if (tList.size() > 0) {
                FluidStack tFluid = null;
                int tList_sS = tList.size();
                for (int i = 0; i < tList_sS; i++) {
                    if ((!Materials.Air.getCells(1).isItemEqual(tList.get(i))) && ((tFluid = GT_Utility.getFluidForFilledItem(tList.get(i), true)) != null)) {
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
    }

    public void addWireRecipes(Materials aMaterial) {
        ItemStack aIngotStack = MatUnifier.get(OrePrefixes.ingot, aMaterial, 1);
        ItemStack aPlateStack = MatUnifier.get(OrePrefixes.plate, aMaterial, 1);
        ItemStack aFineWireStack = MatUnifier.get(OrePrefixes.wireFine, aMaterial, 1);
        ItemStack aWire1Stack = MatUnifier.get(OrePrefixes.wireGt01, aMaterial, 1);
        ItemStack aWire2Stack = MatUnifier.get(OrePrefixes.wireGt02, aMaterial, 1);
        ItemStack aWire4Stack = MatUnifier.get(OrePrefixes.wireGt04, aMaterial, 1);
        ItemStack aWire8Stack = MatUnifier.get(OrePrefixes.wireGt08, aMaterial, 1);
        ItemStack aWire12Stack = MatUnifier.get(OrePrefixes.wireGt12, aMaterial, 1);
        ItemStack aWire16Stack = MatUnifier.get(OrePrefixes.wireGt16, aMaterial, 1);
        if (!aNoSmashing) {
            GT_Values.RA.addWiremillRecipe(aWire1Stack, GT_Utility.copyAmount(4, aFineWireStack), 200, 8);
            GT_Values.RA.addWiremillRecipe(aIngotStack, GT_Utility.copy(GT_Utility.copyAmount(2, aWire1Stack), GT_Utility.copyAmount(8, aFineWireStack)), 100, 4);
            GT_Values.RA.addWiremillRecipe(MatUnifier.get(OrePrefixes.stick, aMaterial), GT_Utility.copy(aWire1Stack, GT_Utility.copyAmount(4, aFineWireStack)), 50, 4);
        }
        if (aMaterial.mUnificatable && (aMaterial.mMaterialInto == aMaterial) && !aMaterial.contains(SubTag.NO_WORKING)) {
            GT_ModHandler.addCraftingRecipe(aWire1Stack, GT_Proxy.tBits, new Object[]{"Xx", Character.valueOf('X'), aPlateStack});
        }
        GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(2, aWire1Stack), ItemList.Circuit_Integrated.getWithDamage(0, 2), aWire2Stack, 150, 8);
        GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(8, aWire1Stack), ItemList.Circuit_Integrated.getWithDamage(0, 4), aWire4Stack, 200, 8);
        GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(8, aWire1Stack), ItemList.Circuit_Integrated.getWithDamage(0, 8), aWire8Stack, 300, 8);
        GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(12, aWire1Stack), ItemList.Circuit_Integrated.getWithDamage(0, 12), aWire12Stack, 400, 8);
        GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(16, aWire1Stack), ItemList.Circuit_Integrated.getWithDamage(0, 16), aWire16Stack, 500, 8);

        //GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(2, aWire1Stack), new Object[]{aOreDictName});
        GT_ModHandler.addShapelessCraftingRecipe(aWire2Stack, new Object[]{aWire1Stack, aWire1Stack});

        //GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(4, aWire1Stack), new Object[]{aOreDictName});
        GT_ModHandler.addShapelessCraftingRecipe(aWire4Stack, new Object[]{aWire1Stack, aWire1Stack, aWire1Stack, aWire1Stack});
        GT_ModHandler.addShapelessCraftingRecipe(aWire4Stack, new Object[]{aWire2Stack, aWire2Stack});

        //GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(8, aWire1Stack), new Object[]{aOreDictName});
        GT_ModHandler.addShapelessCraftingRecipe(aWire8Stack, new Object[]{aWire1Stack, aWire1Stack, aWire1Stack, aWire1Stack, aWire1Stack, aWire1Stack, aWire1Stack, aWire1Stack});
        GT_ModHandler.addShapelessCraftingRecipe(aWire8Stack, new Object[]{aWire4Stack, aWire4Stack});

        //GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(12, aWire1Stack), new Object[]{aOreDictName});
        GT_ModHandler.addShapelessCraftingRecipe(aWire12Stack, new Object[]{aWire8Stack, aWire4Stack});

        //GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(16, aWire1Stack), new Object[]{aOreDictName});
        GT_ModHandler.addShapelessCraftingRecipe(aWire16Stack, new Object[]{aWire8Stack, aWire8Stack});
        GT_ModHandler.addShapelessCraftingRecipe(aWire16Stack, new Object[]{aWire12Stack, aWire4Stack});
        if (GT_Mod.gregtechproxy.mAE2Integration) {
            Api.INSTANCE.registries().p2pTunnel().addNewAttunement(aWire16Stack, TunnelType.IC2_POWER);
        }
    }

    public void addMaterialSpecificRecipes() {
        GT_ModHandler.addSmeltingRecipe(MatUnifier.get(OrePrefixes.nugget, Materials.Iron, 1), MatUnifier.get(OrePrefixes.nugget, Materials.WroughtIron, 1));
        //TODO FIX RICH?
        GT_Values.RA.addCentrifugeRecipe(MatUnifier.get(OrePrefixes.ore, Materials.Oilsands, 1), null, null, Materials.Oil.getFluid(/*tIsRich ? 1000L : */500), new ItemStack(net.minecraft.init.Blocks.sand, 1, 0), null, null, null, null, null, new int[]{'?'}, /*tIsRich ? 2000 : */1000, 5);

        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "QuartzDustSmeltingIntoAESilicon", true)) {
            GT_ModHandler.removeFurnaceSmelting(Materials.NetherQuartz.getDust(1));
            GT_ModHandler.removeFurnaceSmelting(Materials.Quartz.getDust(1));
            GT_ModHandler.removeFurnaceSmelting(Materials.CertusQuartz.getDust(1));
        }
        GT_ModHandler.addSmeltingRecipe(Materials.Glass.getDust(1), new ItemStack(Blocks.glass));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4, Materials.Opal.getDust(1)), 24, MatUnifier.get(OrePrefixes.gem, Materials.Opal, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4, Materials.Olivine.getDust(1)), 24, MatUnifier.get(OrePrefixes.gem, Materials.Olivine, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4, Materials.Emerald.getDust(1)), 24, MatUnifier.get(OrePrefixes.gem, Materials.Emerald, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4, Materials.Ruby.getDust(1)), 24, MatUnifier.get(OrePrefixes.gem, Materials.Ruby, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4, Materials.Sapphire.getDust(1)), 24, MatUnifier.get(OrePrefixes.gem, Materials.Sapphire, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4, Materials.GreenSapphire.getDust(1)), 24, MatUnifier.get(OrePrefixes.gem, Materials.GreenSapphire, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4, Materials.Topaz.getDust(1)), 24, MatUnifier.get(OrePrefixes.gem, Materials.Topaz, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4, Materials.BlueTopaz.getDust(1)), 24, MatUnifier.get(OrePrefixes.gem, Materials.BlueTopaz, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4, Materials.Tanzanite.getDust(1)), 24, MatUnifier.get(OrePrefixes.gem, Materials.Tanzanite, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4, Materials.FoolsRuby.getDust(1)), 16, MatUnifier.get(OrePrefixes.gem, Materials.FoolsRuby, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 8));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4, Materials.GarnetRed.getDust(1)), 16, MatUnifier.get(OrePrefixes.gem, Materials.GarnetRed, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 8));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4, Materials.GarnetYellow.getDust(1)), 16, MatUnifier.get(OrePrefixes.gem, Materials.GarnetYellow, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 8));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4, Materials.Jasper.getDust(1)), 16, MatUnifier.get(OrePrefixes.gem, Materials.Jasper, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 8));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4, Materials.Amber.getDust(1)), 16, MatUnifier.get(OrePrefixes.gem, Materials.Amber, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 8));
        GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(4, Materials.Monazite.getDust(1)), 16, MatUnifier.get(OrePrefixes.gem, Materials.Monazite, 3), MatUnifier.get(OrePrefixes.dustTiny, Materials.DarkAsh, 8));
        GT_Values.RA.addElectrolyzerRecipe(MatUnifier.get(OrePrefixes.gem, Materials.CertusQuartz, 1), 0, GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 1), null, null, null, null, null, 2000, 30);
        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "torchesFromCoal", false)) {
            GT_ModHandler.removeRecipe(new ItemStack(Items.coal, 1, 0), null, null, new ItemStack(net.minecraft.init.Items.stick, 1, 0));
            GT_ModHandler.removeRecipe(new ItemStack(Items.coal, 1, 1), null, null, new ItemStack(net.minecraft.init.Items.stick, 1, 0));
        }
        Materials[] aSifterGemMaterials = new Materials[]{Materials.Tanzanite, Materials.Sapphire, Materials.GreenSapphire, Materials.Opal, Materials.Amethyst, Materials.Emerald, Materials.Ruby, Materials.Amber, Materials.Diamond, Materials.FoolsRuby, Materials.BlueTopaz, Materials.GarnetRed, Materials.Topaz, Materials.Jasper, Materials.GarnetYellow};
        for (Materials aGemMaterial : aSifterGemMaterials) {
            ItemStack aGem = MatUnifier.get(OrePrefixes.gem, aGemMaterial, 1);
            GT_Values.RA.addSifterRecipe(MatUnifier.get(OrePrefixes.crushedPurified, aGemMaterial, 1), new ItemStack[]{MatUnifier.get(OrePrefixes.gemExquisite, aGemMaterial, aGem), MatUnifier.get(OrePrefixes.gemFlawless, aGemMaterial, aGem), aGem, MatUnifier.get(OrePrefixes.gemFlawed, aGemMaterial, aGem), MatUnifier.get(OrePrefixes.gemChipped, aGemMaterial, aGem), MatUnifier.get(OrePrefixes.dust, aGemMaterial, aGem)}, new int[]{300, 1200, 4500, 1400, 2800, 3500}, 800, 16);
        }
        /*if (aModName.equalsIgnoreCase("AtomicScience")) {
            GT_ModHandler.addExtractionRecipe(ItemList.Cell_Empty.get(1L), aStack);
        }*/
        GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.harderrecipes, Materials.Paper.getPlates(1), true) ? 2 : 3, Materials.Paper.getPlates(1)), new Object[]{"XXX", 'X', new ItemStack(net.minecraft.init.Items.reeds, 1, 32767)});
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
        GregTech_API.registerCover(Materials.GraniteBlack.getPlates(1), new GT_RenderedTexture(Textures.BlockIcons.GRANITE_BLACK_SMOOTH), null);
        GregTech_API.registerCover(Materials.GraniteRed.getPlates(1), new GT_RenderedTexture(Textures.BlockIcons.GRANITE_RED_SMOOTH), null);
        GregTech_API.registerCover(Materials.Concrete.getPlates(1), new GT_RenderedTexture(Textures.BlockIcons.CONCRETE_LIGHT_SMOOTH), null);
        GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.gearGt, Materials.Wood), tBits, new Object[]{"SPS", "PsP", "SPS", Character.valueOf('P'), OrePrefixes.plank.get(Materials.Wood), Character.valueOf('S'), OrePrefixes.stick.get(Materials.Wood)});
        GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.gearGt, Materials.Stone), tBits, new Object[]{"SPS", "PfP", "SPS", Character.valueOf('P'), OrePrefixes.stoneSmooth, Character.valueOf('S'), new ItemStack(Blocks.stone_button, 1, 32767)});
        GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.gearGtSmall, Materials.Wood), tBits, new Object[]{"P ", " s", Character.valueOf('P'), OrePrefixes.plank.get(Materials.Wood)});
        GT_ModHandler.addCraftingRecipe(MatUnifier.get(OrePrefixes.gearGtSmall, Materials.Stone), tBits, new Object[]{"P ", " f", Character.valueOf('P'), OrePrefixes.stoneSmooth});
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
            ItemStack aGlassDust = MatUnifier.get(OrePrefixes.dust, Materials.Glass, 1);
            GT_Values.RA.addExtruderRecipe(aGlassDust, ItemList.Shape_Extruder_Bottle.get(0), new ItemStack(Items.glass_bottle, 1), tAmount * 32, 16);
            GT_Values.RA.addAlloySmelterRecipe(aGlassDust, ItemList.Shape_Mold_Bottle.get(0), new ItemStack(Items.glass_bottle, 1), tAmount * 64, 4);

            GT_Values.RA.addExtruderRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Steel, 2), ItemList.Shape_Extruder_Cell.get(0), ItemList.Cell_Empty.get(tAmount), tAmount * 128, 32);
            GT_Values.RA.addExtruderRecipe(MatUnifier.get(OrePrefixes.ingot, Materials.Polytetrafluoroethylene, 2), ItemList.Shape_Extruder_Cell.get(0), ItemList.Cell_Empty.get(tAmount), tAmount * 128, 32);
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

        /*if (aMaterial == Materials.Tin) {
            MatUnifier.registerOre(OreDictNames.craftingWireTin, aEvent.Ore);
        } else if (aMaterial == Materials.AnyCopper) {
            MatUnifier.registerOre(OreDictNames.craftingWireCopper, aEvent.Ore);
        } else if (aMaterial == Materials.Gold) {
            MatUnifier.registerOre(OreDictNames.craftingWireGold, aEvent.Ore);
        } else if (aMaterial == Materials.AnyIron) {
            MatUnifier.registerOre(OreDictNames.craftingWireIron, aEvent.Ore);
        }

        //case cell
        if (aMaterial == Materials.Empty) {
            MatUnifier.addToBlacklist(aEvent.Ore);
        }



        else if (aMaterial == Materials.Lapis) {
            MatUnifier.registerOre(Dyes.dyeBlue, aEvent.Ore);
        } else if (aMaterial == Materials.Lazurite) {
            MatUnifier.registerOre(Dyes.dyeCyan, aEvent.Ore);
        } else if (aMaterial == Materials.Sodalite) {
            MatUnifier.registerOre(Dyes.dyeBlue, aEvent.Ore);
        } else if (aMaterial == Materials.BrownLimonite) {
            MatUnifier.registerOre(Dyes.dyeBrown, aEvent.Ore);
        } else if (aMaterial == Materials.YellowLimonite) {
            MatUnifier.registerOre(Dyes.dyeYellow, aEvent.Ore);
        }
        else if ((aMaterial == Materials.Tin) || (aMaterial == Materials.Lead) || (aMaterial == Materials.SolderingAlloy)) {
            MatUnifier.registerOre(ToolDictNames.craftingToolSolderingMetal, aEvent.Ore);
        }

        else if (aEvent.Name.equals("compressedAluminum")) {
            MatUnifier.registerOre(OrePrefixes.compressed, Materials.Aluminium, aEvent.Ore);
            return;
        }
        if (aEvent.Name.equals("shardAir")) {
            MatUnifier.registerOre(OrePrefixes.gem, Materials.InfusedAir, aEvent.Ore);
            return;
        } else if (aEvent.Name.equals("shardWater")) {
            MatUnifier.registerOre(OrePrefixes.gem, Materials.InfusedWater, aEvent.Ore);
            return;
        } else if (aEvent.Name.equals("shardFire")) {
            MatUnifier.registerOre(OrePrefixes.gem, Materials.InfusedFire, aEvent.Ore);
            return;
        } else if (aEvent.Name.equals("shardEarth")) {
            MatUnifier.registerOre(OrePrefixes.gem, Materials.InfusedEarth, aEvent.Ore);
            return;
        } else if (aEvent.Name.equals("shardOrder")) {
            MatUnifier.registerOre(OrePrefixes.gem, Materials.InfusedOrder, aEvent.Ore);
            return;
        } else if (aEvent.Name.equals("shardEntropy")) {
            MatUnifier.registerOre(OrePrefixes.gem, Materials.InfusedEntropy, aEvent.Ore);
            return;
        }*/
    }
}
