package gregtech.loaders.preload;

import cpw.mods.fml.common.ProgressManager;
import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.*;
import gregtech.api.objects.MaterialStack;
import gregtech.api.util.*;
import gregtech.common.items.behaviors.Behaviour_DataOrb;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Arrays;

import static gregtech.api.enums.MaterialFlags.*;
import static gregtech.common.items.GT_MetaGenerated_Tool_01.*;

public class GT_Loader_MaterialRecipes implements Runnable {
    private static FluidStack aWaterStack = Materials.Water.getFluid(200);
    private static FluidStack aDistilledStack = GT_ModHandler.getDistilledWater(200);
    private static int aSodiumFluidAmount = GT_Mod.gregtechproxy.mDisableOldChemicalRecipes ? 100 : 1000;
    private static int aMixedOreYieldCount = GT_Mod.gregtechproxy.mMixedOreOnlyYieldsTwoThirdsOfPureOre ? 2 : 3;

    private boolean aNoSmelting, aNoWorking, aNoSmashing, aWashingMercury, aWashingSodium;
    private ItemStack aDustStack, aSDustStack, aTDustStack, aIngotStack, aNuggetStack, aPlateStack, aBlockStack, aGemStack, aChippedStack, aFlawedStack, aFlawlessStack, aExquisiteStack, aBoltStack, aStickStack, aDenseStack, aRingStack, aFoilStack, aScrewStack, aCellStack, aLensStack = null;

    public void run() {
        ProgressManager.ProgressBar progressBar = ProgressManager.push("Registering Material Recipes: ", Materials.MATERIALS_DUST.length);
        for (Materials aMaterial : Materials.MATERIALS_DUST) {
            progressBar.step(aMaterial.mName);
            aDustStack = aSDustStack = aTDustStack = aIngotStack = aNuggetStack = aPlateStack = aBlockStack = aGemStack = aChippedStack = aFlawedStack = aFlawlessStack = aExquisiteStack = aBoltStack = aStickStack = aDenseStack = aRingStack = aFoilStack = aScrewStack = aCellStack = aLensStack = null;
            if (aMaterial.hasFlag(CELL)) {
                aCellStack = GT_OreDictUnificator.get(OrePrefixes.cell, aMaterial);
                addCellRecipes(aMaterial);
            }
            if (aMaterial.hasFlag(DUST)) {
                aDustStack = GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial);
                aSDustStack = GT_OreDictUnificator.get(OrePrefixes.dustSmall, aMaterial);
                aTDustStack = GT_OreDictUnificator.get(OrePrefixes.dustTiny, aMaterial);
                if (aMaterial.mElement != null && !aMaterial.mElement.mIsIsotope && aMaterial != Materials.Magic && aMaterial.getMass() > 0) {
                    ItemStack tOutput = ItemList.Tool_DataOrb.get(1);
                    Behaviour_DataOrb.setDataTitle(tOutput, "Elemental-Scan");
                    Behaviour_DataOrb.setDataName(tOutput, aMaterial.mElement.name());
                    ItemStack tInput = aMaterial.mPrimaryState == MaterialState.FLUID ? aCellStack : aDustStack;
                    int aMass = aMaterial.getMass();
                    GT_Recipe.GT_Recipe_Map.sScannerFakeRecipes.addFakeRecipe(false, new ItemStack[]{tInput}, new ItemStack[]{tOutput}, ItemList.Tool_DataOrb.get(1), null, null, aMass * 8192, 32, 0);
                    GT_Recipe.GT_Recipe_Map.sReplicatorFakeRecipes.addFakeRecipe(false, null, new ItemStack[]{tInput}, new ItemStack[]{tOutput}, new FluidStack[]{Materials.UUMatter.getFluid(aMass)}, null, aMass * 512, 32, 0);
                    ItemStack aPlasmaStack = GT_OreDictUnificator.get(OrePrefixes.cellPlasma, aMaterial);
                    GT_Values.RA.addFuel(aPlasmaStack, ItemList.Cell_Empty.get(1), Math.max(1024, 1024 * aMass), 4);
                    GT_Values.RA.addVacuumFreezerRecipe(aPlasmaStack, aCellStack, Math.max(aMass * 2, 1));
                }
                aNoSmelting = aMaterial.contains(SubTag.NO_SMELTING);
                aNoWorking = aMaterial.contains(SubTag.NO_WORKING);
                addDustRecipes(aMaterial);
                if (aMaterial.hasFlag(SOLID)) {
                    aNoSmashing = aMaterial.contains(SubTag.NO_SMASHING);
                    aIngotStack = GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial);
                    aNuggetStack = GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial);
                    aBlockStack = GT_OreDictUnificator.get(OrePrefixes.block, aMaterial);
                    addSolidRecipes(aMaterial);
                    if (aMaterial.hasFlag(PLATE)) {
                        aPlateStack = GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial);
                        addPlateRecipes(aMaterial);
                    }
                    if (aMaterial.hasFlag(DPLATE)) {
                        aDenseStack = GT_OreDictUnificator.get(OrePrefixes.plateDense, aMaterial);
                        addDensePlateRecipes(aMaterial);
                    }
                    if (aMaterial.hasFlag(STICK)) {
                        aStickStack = GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial);
                        addStickRecipes(aMaterial);
                        if (aMaterial.hasFlag(RING)) {
                            aRingStack = GT_OreDictUnificator.get(OrePrefixes.ring, aMaterial);
                            addRingRecipes(aMaterial);
                        }
                        if (aMaterial.hasFlag(SPRING)) {
                            addSpringRecipes(aMaterial);
                        }
                        if (aMaterial.hasFlag(BOLT)) {
                            aBoltStack = GT_OreDictUnificator.get(OrePrefixes.bolt, aMaterial);
                            addBoltRecipes(aMaterial);
                        }
                        if (aMaterial.hasFlag(SCREW)) {
                            aScrewStack = GT_OreDictUnificator.get(OrePrefixes.screw, aMaterial);
                            addScrewRecipes(aMaterial);
                        }
                        if (aMaterial.hasFlag(ROTOR)) addRotorRecipes(aMaterial);
                    }
                    if (aMaterial.hasFlag(HINGOT)) addHotIngotRecipes(aMaterial);
                    if (aMaterial.hasFlag(FOIL)) {
                        aFoilStack = GT_OreDictUnificator.get(OrePrefixes.foil, aMaterial);
                        addFoilRecipes(aMaterial);
                        if (aMaterial.hasFlag(FWIRE)) addFineWireRecipes(aMaterial);
                    }
                    if (aMaterial.hasFlag(GEAR) && aMaterial.hasFlag(STICK)) addGearRecipes(aMaterial);
                    if (aMaterial.hasFlag(SGEAR)) addGearSmallRecipes(aMaterial);
                    if (aMaterial.hasFlag(TOOL)) addToolRecipes(aMaterial);
                }
                if (aMaterial.hasFlag(BGEM)) {
                    /*if (aMaterial.hasFlag(MaterialFlags.PLATE)) {
                        aPlateStack = GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial);
                        addPlateRecipes(aMaterial);
                    }*/
                    aGemStack = GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial);
                    addBasicGemRecipes(aMaterial);
                    if (aMaterial.hasFlag(GEM)) {
                        aChippedStack = GT_OreDictUnificator.get(OrePrefixes.gemChipped, aMaterial);
                        aFlawedStack = GT_OreDictUnificator.get(OrePrefixes.gemFlawed, aMaterial);
                        aFlawlessStack = GT_OreDictUnificator.get(OrePrefixes.gemFlawless, aMaterial);
                        aExquisiteStack = GT_OreDictUnificator.get(OrePrefixes.gemExquisite, aMaterial);
                        addGemRecipes(aMaterial);
                    }
                }
                if (aMaterial.hasFlag(ORE)) {
                    aWashingMercury = aMaterial.contains(SubTag.WASHING_MERCURY);
                    aWashingSodium = aMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE);
                    addOreRecipes(aMaterial, false);
                }
            }
        }
        ProgressManager.pop(progressBar);
        addMaterialSpecificRecipes();
    }

    public void addHotIngotRecipes(Materials aMaterial) {
        //int aBlastDuration = Math.max(aMaterial.getMass() / 4, 1) * aMaterial.mBlastFurnaceTemp;
        //GT_ModHandler.removeFurnaceSmelting(aDustStack);
        //GT_Values.RA.addBlastRecipe(aDustStack, null, null, null, GT_OreDictUnificator.get(OrePrefixes.ingotHot, aMaterial.mSmeltInto, aMaterial.mSmeltInto.getIngots(1)), null, aBlastDuration, 120, aMaterial.mBlastFurnaceTemp);
        GT_Values.RA.addVacuumFreezerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingotHot, aMaterial), aIngotStack, Math.max(aMaterial.getMass() * 3, 1));
    }

    private void addSpringRecipes(Materials aMaterial) {
        GT_Values.RA.addBenderRecipe(aStickStack, GT_OreDictUnificator.get(OrePrefixes.spring, aMaterial), 200, 16);
    }

    private void addDensePlateRecipes(Materials aMaterial) {
        GT_ModHandler.removeRecipeByOutput(aDenseStack);
        if (aMaterial.mUnificatable && aMaterial.mMaterialInto == aMaterial && aNoSmashing) {
            GT_Values.RA.addBenderRecipe(GT_Utility.copyAmount(9, aPlateStack), aDenseStack, Math.max(aMaterial.getMass() * 9, 1), 96);
        }
        if (!aNoSmashing) {
            GT_Values.RA.addBenderRecipe(GT_Utility.copyAmount(9, aIngotStack), aDenseStack, Math.max(aMaterial.getMass() * 9, 1), 96);
        }
        //GregTech_API.registerCover(aDenseStack, new GT_RenderedTexture(aMaterial.mIconSet.mTextures[76], aMaterial.mRGBa, false), null);
    }

    private void addRotorRecipes(Materials aMaterial) {
        ItemStack aRotorStack = GT_OreDictUnificator.get(OrePrefixes.rotor, aMaterial);
        if (aMaterial.mUnificatable && aMaterial.mMaterialInto == aMaterial && !aNoWorking) {
            GT_ModHandler.addShapedToolRecipe(aRotorStack, "PhP", "SRf", "PdP", 'P', aPlateStack, 'R', aRingStack, 'S', aScrewStack);
            ItemStack a4xPlateStack = GT_Utility.copyAmount(4, aPlateStack);
            GT_Values.RA.addAssemblerRecipe(a4xPlateStack, aRingStack, Materials.Tin.getMolten(32), aRotorStack, 240, 24);
            GT_Values.RA.addAssemblerRecipe(a4xPlateStack, aRingStack, Materials.Lead.getMolten(48), aRotorStack, 240, 24);
            GT_Values.RA.addAssemblerRecipe(a4xPlateStack, aRingStack, Materials.SolderingAlloy.getMolten(16), aRotorStack, 240, 24);
        }
    }

    private void addFineWireRecipes(Materials aMaterial) {
        ItemStack aFWireStack = GT_OreDictUnificator.get(OrePrefixes.wireFine, aMaterial);
        if (!aNoSmashing) {
            GT_Values.RA.addWiremillRecipe(aIngotStack, GT_Utility.copy(GT_OreDictUnificator.get(OrePrefixes.wireGt01, aMaterial, 2), aFWireStack), 100, 4);
            GT_Values.RA.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial), GT_Utility.copy(GT_OreDictUnificator.get(OrePrefixes.wireGt01, aMaterial), aFWireStack), 50, 4);
        }
        if (aMaterial.mUnificatable && aMaterial.mMaterialInto == aMaterial && !aNoWorking) {
            GT_ModHandler.addShapedToolRecipe(aFWireStack, "Xx ", "   ", "   ", 'X', GT_OreDictUnificator.get(OrePrefixes.foil, aMaterial));
        }
    }

    private void addGearSmallRecipes(Materials aMaterial) {
        ItemStack aGearSmallStack = GT_OreDictUnificator.get(OrePrefixes.gearGtSmall, aMaterial);
        if (aMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Gear_Small.get(0), aMaterial.getMolten(144), aGearSmallStack, 16, 8);
        }
        if (aMaterial.mUnificatable && aMaterial.mMaterialInto == aMaterial && !aNoWorking) {
            GT_ModHandler.addShapedToolRecipe(aGearSmallStack, "P  ", aMaterial.contains(SubTag.WOOD) ? " s " : " h ", "   ", 'P', aPlateStack);
        }
    }

    private void addGearRecipes(Materials aMaterial) {
        ItemStack aGearStack = GT_OreDictUnificator.get(OrePrefixes.gearGt, aMaterial);
        GT_ModHandler.removeRecipeByOutput(aGearStack);
        if (aMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Gear.get(0), aMaterial.getMolten(576), aGearStack, 128, 8);
        }
        if (aMaterial.mUnificatable && aMaterial.mMaterialInto == aMaterial && !aNoWorking) {
            GT_ModHandler.addShapedToolRecipe(aGearStack, "SPS", "PwP", "SPS", 'P', aPlateStack, 'S', aStickStack);
        }
        if (!aNoSmelting) {
            int aVoltageMulti = aNoSmashing ? aMaterial.mBlastFurnaceTemp >= 2800 ? 16 : 4 : aMaterial.mBlastFurnaceTemp >= 2800 ? 64 : 16;
            int tAmount = OrePrefixes.gearGt.mMaterialAmount / 3628800;
            ItemStack aGearSmeltInto = GT_OreDictUnificator.get(OrePrefixes.gearGt, aMaterial.mSmeltInto, tAmount);
            GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(4, aIngotStack), ItemList.Shape_Extruder_Gear.get(0), aGearSmeltInto, Math.max(aMaterial.getMass() * 5 * tAmount, tAmount), 8 * aVoltageMulti);
            GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(8, aIngotStack), ItemList.Shape_Mold_Gear.get(0), aGearSmeltInto, Math.max(aMaterial.getMass() * 10 * tAmount, tAmount), 2 * aVoltageMulti);
        }
        GT_OreDictUnificator.registerOre(OrePrefixes.gear, aGearStack);
    }

    private void addScrewRecipes(Materials aMaterial) {
        if (!aNoWorking) {
            GT_Values.RA.addLatheRecipe(aBoltStack, aScrewStack, null, Math.max(aMaterial.getMass() / 8, 1), 4);
            if (aMaterial.mUnificatable && aMaterial.mMaterialInto == aMaterial) {
                GT_ModHandler.addShapedToolRecipe(aScrewStack, "fX ", "X  ", "   ", 'X', aBoltStack);
            }
        }
    }

    private void addFoilRecipes(Materials aMaterial) {
        GT_Values.RA.addBenderRecipe(aPlateStack, GT_Utility.copyAmount(2, aFoilStack), Math.max(aMaterial.getMass(), 1), 24);
        GT_ModHandler.addShapedToolRecipe(GT_Utility.copyAmount(4, aFoilStack), "hX ", "   ", "   ", 'X', aPlateStack);
        //GregTech_API.registerCover(aFoilStack, new GT_RenderedTexture(aMaterial.mIconSet.mTextures[70], aMaterial.mRGBa, false), null);
    }

    private void addBoltRecipes(Materials aMaterial) {
        if (aMaterial.mUnificatable && aMaterial.mMaterialInto == aMaterial && !aNoWorking) {
            GT_ModHandler.addShapedToolRecipe(GT_Utility.copyAmount(2, aBoltStack), "s  ", " X ", "   ", 'X', aStickStack);
        }
        if (!aNoSmelting) {
            //GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Bolt.get(0), MatUnifier.get(OrePrefixes.bolt, aMaterial.mSmeltInto, tAmount * 8), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 8 * tVoltageMultiplier);
        }
    }

    private void addRingRecipes(Materials aMaterial) {
        if (aMaterial.mUnificatable && aMaterial.mMaterialInto == aMaterial && !aNoSmashing) {
            GT_ModHandler.addShapedToolRecipe(aRingStack, "h  ", " X ", "   ", 'X', aStickStack);
        }
        if (!aNoSmelting) {
            //GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Ring.get(0), MatUnifier.get(OrePrefixes.ring, aMaterial.mSmeltInto, tAmount * 4), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 6 * tVoltageMultiplier);
        }
    }

    private void addStickRecipes(Materials aMaterial) {
        if (!aNoWorking) {
            GT_Values.RA.addLatheRecipe(aMaterial.contains(SubTag.CRYSTAL) ? GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial) : aIngotStack, aStickStack, GT_OreDictUnificator.get(OrePrefixes.dustSmall, aMaterial.mMacerateInto, 2), Math.max(aMaterial.getMass() * 5, 1), 16);
            GT_Values.RA.addCutterRecipe(aStickStack, GT_OreDictUnificator.get(OrePrefixes.bolt, aMaterial, 4), null, Math.max(aMaterial.getMass() * 2, 1), 4);
            if (aMaterial.mUnificatable && aMaterial.mMaterialInto == aMaterial && !aMaterial.hasFlag(GEM)) {
                GT_ModHandler.addShapedToolRecipe(aStickStack, "f  ", " X ", "   ", 'X', aIngotStack);
            }
        }
        if (!aNoSmelting && !aMaterial.hasFlag(GEM)) {
            //GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Rod.get(0), MatUnifier.get(OrePrefixes.stick, aMaterial.mSmeltInto, tAmount * 2), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 6 * tVoltageMultiplier);
            //GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Wire.get(0), MatUnifier.get(OrePrefixes.wireGt01, aMaterial.mSmeltInto, tAmount * 2), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 6 * tVoltageMultiplier);
        }
    }

    private void addPlateRecipes(Materials aMaterial) {
        GT_ModHandler.removeRecipeByOutput(aPlateStack);
        GT_ModHandler.removeRecipe(aPlateStack);
        if (aMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Plate.get(0), aMaterial.getMolten(144), aPlateStack, 32, 8);
        }
        if (aMaterial.mFuelPower > 0) {
            GT_Values.RA.addFuel(aPlateStack, null, aMaterial.mFuelPower, aMaterial.mFuelType);
        }
        //TODO MOVRE GT_Values.RA.addImplosionRecipe(GT_Utility.copyAmount(aMaterial == Materials.MeteoricIron ? 1 : 2, aPlateStack), 2, GT_OreDictUnificator.get(OrePrefixes.compressed, aMaterial), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 1));
        if (aMaterial.mUnificatable && aMaterial.mMaterialInto == aMaterial) {
            if (!aNoSmashing) {
                //if (aMaterial.mPrimaryState != MaterialState.CRYSTAL) {
                    GT_ModHandler.addShapedToolRecipe(aPlateStack, "h  ", "X  ", "X  ", 'X', aIngotStack);
                //} else {
                    //GT_ModHandler.addShapedToolRecipe(aPlateStack, "h  ", "X  ", "X  ", 'X', aGemStack);
                //}
            }
            if (aMaterial.contains(SubTag.MORTAR_GRINDABLE)) {
                GT_ModHandler.addShapedToolRecipe(aDustStack, "X  ", "m  ", "   ", 'X', aPlateStack);
            }
        }
        if (!aNoSmashing) {
            GT_Values.RA.addForgeHammerRecipe(GT_Utility.copyAmount(3, aIngotStack), GT_Utility.copyAmount(2, aPlateStack), Math.max(aMaterial.getMass(), 1), 16);
            GT_Values.RA.addBenderRecipe(aIngotStack, aPlateStack, Math.max(aMaterial.getMass(), 1), 24);
        }
        if (!aNoSmelting) {
            //GT_Values.RA.addExtruderRecipe(aIngotStack, ItemList.Shape_Extruder_Plate.get(0), MatUnifier.get(OrePrefixes.plate, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * tAmount, tAmount), 8 * tVoltageMultiplier);
            //GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(2, aIngotStack), ItemList.Shape_Mold_Plate.get(0), MatUnifier.get(OrePrefixes.plate, aMaterial.mSmeltInto, tAmount), (int) Math.max(aMaterialMass * 2L * tAmount, tAmount), 2 * tVoltageMultiplier);


            //TODO WUT?
            if (OrePrefixes.block.isIgnored(aMaterial) && aMaterial != Materials.GraniteRed && aMaterial != Materials.GraniteBlack && aMaterial != Materials.Glass && aMaterial != Materials.Obsidian && aMaterial != Materials.Glowstone && aMaterial != Materials.Paper) {
                GT_ModHandler.addCompressionRecipe(aDustStack, aPlateStack);
            }
        }
        GT_Values.RA.addCutterRecipe(aBlockStack, GT_Utility.copyAmount(9, aPlateStack), null, Math.max(aMaterial.getMass() * 10, 1), 30);
        //GregTech_API.registerCover(aPlateStack, new GT_RenderedTexture(aMaterial.mIconSet.mTextures[71], aMaterial.mRGBa, false), null);
    }

    private void addToolRecipes(Materials aMaterial) {
        ItemStack aStainlessScrew = GT_OreDictUnificator.get(OrePrefixes.screw, Materials.StainlessSteel);
        ItemStack aTitaniumScrew = GT_OreDictUnificator.get(OrePrefixes.screw, Materials.Titanium);
        ItemStack aTungstensteelScrew = GT_OreDictUnificator.get(OrePrefixes.screw, Materials.TungstenSteel);
        ItemStack aStainlessPlate = GT_OreDictUnificator.get(OrePrefixes.plate, Materials.StainlessSteel);
        ItemStack aTitaniumPlate = GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Titanium);
        ItemStack aTungstensteelPlate = GT_OreDictUnificator.get(OrePrefixes.plate, Materials.TungstenSteel);
        ItemStack aStainlessSmallGear = GT_OreDictUnificator.get(OrePrefixes.gearGtSmall, Materials.StainlessSteel);
        ItemStack aTitaniumSmallGear = GT_OreDictUnificator.get(OrePrefixes.gearGtSmall, Materials.Titanium);
        ItemStack aTungstensteelSmallGear = GT_OreDictUnificator.get(OrePrefixes.gearGtSmall, Materials.TungstenSteel);
        ItemStack aTitaniumSpring = GT_OreDictUnificator.get(OrePrefixes.spring, Materials.Titanium);
        ItemStack aHandleStack = GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial.mHandleMaterial);

        ItemStack aHeadAxe = GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, aMaterial);
        ItemStack aHeadHoe = GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, aMaterial);
        ItemStack aHeadPick = GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, aMaterial);
        ItemStack aHeadPlow = GT_OreDictUnificator.get(OrePrefixes.toolHeadPlow, aMaterial);
        ItemStack aHeadSaw = GT_OreDictUnificator.get(OrePrefixes.toolHeadSaw, aMaterial);
        ItemStack aHeadSense = GT_OreDictUnificator.get(OrePrefixes.toolHeadSense, aMaterial);
        ItemStack aHeadShovel = GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, aMaterial);
        ItemStack aHeadSword = GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, aMaterial);
        ItemStack aHeadFile = GT_OreDictUnificator.get(OrePrefixes.toolHeadFile, aMaterial);
        ItemStack aHeadHammer = GT_OreDictUnificator.get(OrePrefixes.toolHeadHammer, aMaterial);
        ItemStack aHeadTurbine = GT_OreDictUnificator.get(OrePrefixes.turbineBlade, aMaterial);

        boolean aSpecialRecipeReq1 = aMaterial.hasFlag(SOLID) && aMaterial.hasFlag(PLATE) && aMaterial.mUnificatable && aMaterial.mMaterialInto == aMaterial && !aNoSmashing;
        boolean aSpecialRecipeReq2 = aMaterial.hasFlag(SOLID) && aMaterial.hasFlag(PLATE) && aMaterial.mUnificatable && aMaterial.mMaterialInto == aMaterial && !aNoWorking;
        ItemStack aBuzzsawHead = GT_OreDictUnificator.get(OrePrefixes.toolHeadBuzzSaw, aMaterial);
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(BUZZSAW, 1, aMaterial, Materials.StainlessSteel, new long[]{100000L, 32L, 1L, -1L}), "PBM", "dXG", "SGP", 'X', aBuzzsawHead, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Lithium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(BUZZSAW, 1, aMaterial, Materials.StainlessSteel, new long[]{75000L, 32L, 1L, -1L}), "PBM", "dXG", "SGP", 'X', aBuzzsawHead, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Cadmium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(BUZZSAW, 1, aMaterial, Materials.StainlessSteel, new long[]{50000L, 32L, 1L, -1L}), "PBM", "dXG", "SGP", 'X', aBuzzsawHead, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Sodium.get(1));
        ItemStack aChainsawHead = GT_OreDictUnificator.get(OrePrefixes.toolHeadChainsaw, aMaterial);
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(CHAINSAW_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{100000L, 32L, 1L, -1L}), "SXd", "GMG", "PBP", 'X', aChainsawHead, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Lithium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(CHAINSAW_MV, 1, aMaterial, Materials.Titanium, new long[]{400000L, 128L, 2L, -1L}), "SXd", "GMG", "PBP", 'X', aChainsawHead, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Lithium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(CHAINSAW_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1600000L, 512L, 3L, -1L}), "SXd", "GMG", "PBP", 'X', aChainsawHead, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Lithium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(CHAINSAW_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{75000L, 32L, 1L, -1L}), "SXd", "GMG", "PBP", 'X', aChainsawHead, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Cadmium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(CHAINSAW_MV, 1, aMaterial, Materials.Titanium, new long[]{300000L, 128L, 2L, -1L}), "SXd", "GMG", "PBP", 'X', aChainsawHead, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Cadmium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(CHAINSAW_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1200000L, 512L, 3L, -1L}), "SXd", "GMG", "PBP", 'X', aChainsawHead, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Cadmium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(CHAINSAW_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{50000L, 32L, 1L, -1L}), "SXd", "GMG", "PBP", 'X', aChainsawHead, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Sodium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(CHAINSAW_MV, 1, aMaterial, Materials.Titanium, new long[]{200000L, 128L, 2L, -1L}), "SXd", "GMG", "PBP", 'X', aChainsawHead, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Sodium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(CHAINSAW_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{800000L, 512L, 3L, -1L}), "SXd", "GMG", "PBP", 'X', aChainsawHead, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Sodium.get(1));
        ItemStack aDrillHead = GT_OreDictUnificator.get(OrePrefixes.toolHeadDrill, aMaterial);
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(DRILL_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{100000L, 32L, 1L, -1L}), "SXd", "GMG", "PBP", 'X', aDrillHead, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Lithium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(DRILL_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{75000L, 32L, 1L, -1L}), "SXd", "GMG", "PBP", 'X', aDrillHead, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Cadmium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(DRILL_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{50000L, 32L, 1L, -1L}), "SXd", "GMG", "PBP", 'X', aDrillHead, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Sodium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(DRILL_MV, 1, aMaterial, Materials.Titanium, new long[]{400000L, 128L, 2L, -1L}), "SXd", "GMG", "PBP", 'X', aDrillHead, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Lithium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(DRILL_MV, 1, aMaterial, Materials.Titanium, new long[]{300000L, 128L, 2L, -1L}), "SXd", "GMG", "PBP", 'X', aDrillHead, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Cadmium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(DRILL_MV, 1, aMaterial, Materials.Titanium, new long[]{200000L, 128L, 2L, -1L}), "SXd", "GMG", "PBP", 'X', aDrillHead, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Sodium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(DRILL_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1600000L, 512L, 3L, -1L}), "SXd", "GMG", "PBP", 'X', aDrillHead, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Lithium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(DRILL_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1200000L, 512L, 3L, -1L}), "SXd", "GMG", "PBP", 'X', aDrillHead, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Cadmium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(DRILL_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{800000L, 512L, 3L, -1L}), "SXd", "GMG", "PBP", 'X', aDrillHead, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Sodium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(JACKHAMMER, 1, aMaterial, Materials.Titanium, new long[]{1600000L, 512L, 3L, -1L}), "SXd", "PRP", "MPB", 'X', aStickStack, 'M', ItemList.Electric_Piston_HV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'R', aTitaniumSpring, 'B', ItemList.Battery_RE_HV_Lithium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(JACKHAMMER, 1, aMaterial, Materials.Titanium, new long[]{1200000L, 512L, 3L, -1L}), "SXd", "PRP", "MPB", 'X', aStickStack, 'M', ItemList.Electric_Piston_HV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'R', aTitaniumSpring, 'B', ItemList.Battery_RE_HV_Cadmium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(JACKHAMMER, 1, aMaterial, Materials.Titanium, new long[]{800000L, 512L, 3L, -1L}), "SXd", "PRP", "MPB", 'X', aStickStack, 'M', ItemList.Electric_Piston_HV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'R', aTitaniumSpring, 'B', ItemList.Battery_RE_HV_Sodium.get(1));
        ItemStack aWrenchHead = GT_OreDictUnificator.get(OrePrefixes.toolHeadWrench, aMaterial);
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(WRENCH_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{100000L, 32L, 1L, -1L}), "SXd", "GMG", "PBP", 'X', aWrenchHead, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Lithium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(WRENCH_MV, 1, aMaterial, Materials.Titanium, new long[]{400000L, 128L, 2L, -1L}), "SXd", "GMG", "PBP", 'X', aWrenchHead, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Lithium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(WRENCH_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1600000L, 512L, 3L, -1L}), "SXd", "GMG", "PBP", 'X', aWrenchHead, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Lithium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(WRENCH_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{75000L, 32L, 1L, -1L}), "SXd", "GMG", "PBP", 'X', aWrenchHead, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Cadmium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(WRENCH_MV, 1, aMaterial, Materials.Titanium, new long[]{300000L, 128L, 2L, -1L}), "SXd", "GMG", "PBP", 'X', aWrenchHead, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Cadmium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(WRENCH_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{1200000L, 512L, 3L, -1L}), "SXd", "GMG", "PBP", 'X', aWrenchHead, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Cadmium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(WRENCH_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{50000L, 32L, 1L, -1L}), "SXd", "GMG", "PBP", 'X', aWrenchHead, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Sodium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(WRENCH_MV, 1, aMaterial, Materials.Titanium, new long[]{200000L, 128L, 2L, -1L}), "SXd", "GMG", "PBP", 'X', aWrenchHead, 'M', ItemList.Electric_Motor_MV.get(1), 'S', aTitaniumScrew, 'P', aTitaniumPlate, 'G', aTitaniumSmallGear, 'B', ItemList.Battery_RE_MV_Sodium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(WRENCH_HV, 1, aMaterial, Materials.TungstenSteel, new long[]{800000L, 512L, 3L, -1L}), "SXd", "GMG", "PBP", 'X', aWrenchHead, 'M', ItemList.Electric_Motor_HV.get(1), 'S', aTungstensteelScrew, 'P', aTungstensteelPlate, 'G', aTungstensteelSmallGear, 'B', ItemList.Battery_RE_HV_Sodium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(SCREWDRIVER_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{100000L, 32L, 1L, -1L}), "PdX", "MGS", "GBP", 'X', aStickStack, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Lithium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(SCREWDRIVER_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{75000L, 32L, 1L, -1L}), "PdX", "MGS", "GBP", 'X', aStickStack, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Cadmium.get(1));
        GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(SCREWDRIVER_LV, 1, aMaterial, Materials.StainlessSteel, new long[]{50000L, 32L, 1L, -1L}), "PdX", "MGS", "GBP", 'X', aStickStack, 'M', ItemList.Electric_Motor_LV.get(1), 'S', aStainlessScrew, 'P', aStainlessPlate, 'G', aStainlessSmallGear, 'B', ItemList.Battery_RE_LV_Sodium.get(1));

        GT_ModHandler.addBasicShapelessRecipe(INSTANCE.getToolWithStats(AXE, 1, aMaterial, aMaterial.mHandleMaterial, null), aHeadAxe, aHandleStack);
        GT_ModHandler.addBasicShapelessRecipe(INSTANCE.getToolWithStats(FILE, 1, aMaterial, aMaterial.mHandleMaterial, null), aHeadFile, aHandleStack);
        GT_ModHandler.addBasicShapelessRecipe(INSTANCE.getToolWithStats(HOE, 1, aMaterial, aMaterial.mHandleMaterial, null), aHeadHoe, aHandleStack);
        GT_ModHandler.addBasicShapelessRecipe(INSTANCE.getToolWithStats(PICKAXE, 1, aMaterial, aMaterial.mHandleMaterial, null), aHeadPick, aHandleStack);
        GT_ModHandler.addBasicShapelessRecipe(INSTANCE.getToolWithStats(PLOW, 1, aMaterial, aMaterial.mHandleMaterial, null), aHeadPlow, aHandleStack);
        GT_ModHandler.addBasicShapelessRecipe(INSTANCE.getToolWithStats(SAW, 1, aMaterial, aMaterial.mHandleMaterial, null), aHeadSaw, aHandleStack);
        //TODO GT_ModHandler.addBasicShapelessRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.SENSE, 1, aMaterial, aMaterial.mHandleMaterial, null), new Object[]{ToolDictNames.craftingToolBranchCutter, aHandleStack);
        GT_ModHandler.addBasicShapelessRecipe(INSTANCE.getToolWithStats(SHOVEL, 1, aMaterial, aMaterial.mHandleMaterial, null), aHeadShovel, aHandleStack);
        GT_ModHandler.addBasicShapelessRecipe(INSTANCE.getToolWithStats(SWORD, 1, aMaterial, aMaterial.mHandleMaterial, null), aHeadSword, aHandleStack);
        //TODO GT_ModHandler.addBasicShapelessRecipe(GT_MetaGenerated_Tool_01.INSTANCE.getToolWithStats(GT_MetaGenerated_Tool_01.UNIVERSALSPADE, 1, aMaterial, aMaterial, null), aHeadShovel, aStickStack, GT_OreDictUnificator.get(OrePrefixes.bolt, aMaterial), GT_OreDictUnificator.get(OrePrefixes.toolHeadScrewdriver, aMaterial));
        GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(8, aHeadTurbine), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Magnalium), INSTANCE.getToolWithStats(TURBINE_SMALL, 1, aMaterial, aMaterial, null), 160, 100);
        GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(16, aHeadTurbine), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Titanium), INSTANCE.getToolWithStats(TURBINE, 1, aMaterial, aMaterial, null), 320, 400);
        GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(24, aHeadTurbine), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.TungstenSteel), INSTANCE.getToolWithStats(TURBINE_LARGE, 1, aMaterial, aMaterial, null), 640, 1600);
        GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(32, aHeadTurbine), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Americium), INSTANCE.getToolWithStats(TURBINE_HUGE, 1, aMaterial, aMaterial, null), 1280, 6400);
        if (!aMaterial.contains(SubTag.NO_SMASHING) && !aMaterial.contains(SubTag.BOUNCY)) {
            GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(FILE, 1, aMaterial, aMaterial.mHandleMaterial, null), "P  ", "P  ", "S  ", 'P', aPlateStack, 'S', aHandleStack);
        }
        if (aMaterial != Materials.Stone && aMaterial != Materials.Flint) {
            GT_ModHandler.addBasicShapelessRecipe(INSTANCE.getToolWithStats(aMaterial.contains(SubTag.BOUNCY) || aMaterial.contains(SubTag.WOOD) ? SOFTHAMMER : HARDHAMMER, 1, aMaterial, aMaterial.mHandleMaterial, null), aHeadHammer, aHandleStack);
            GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(aMaterial.contains(SubTag.BOUNCY) || aMaterial.contains(SubTag.WOOD) ? SOFTHAMMER : HARDHAMMER, 1, aMaterial, aMaterial.mHandleMaterial, null), "XX ", "XXS", "XX ", 'X', aMaterial == Materials.Wood ? GT_OreDictUnificator.get(OrePrefixes.plank, Materials.Wood) : aIngotStack, 'S', aHandleStack);
            if (aMaterial != Materials.Rubber) {
                GT_ModHandler.addCraftingRecipe(INSTANCE.getToolWithStats(PLUNGER, 1, aMaterial, aMaterial, null), "xRR", " SR", "S f", 'S', aStickStack, 'R', OrePrefixes.plate.get(Materials.AnyRubber));
            }
            if (!aMaterial.contains(SubTag.WOOD) && !aMaterial.contains(SubTag.BOUNCY) && !aNoSmashing) {
                GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(WRENCH, 1, aMaterial, aMaterial, null), "IhI", "III", " I ", 'I', aIngotStack);
                GT_ModHandler.addCraftingRecipe(INSTANCE.getToolWithStats(CROWBAR, 1, aMaterial, aMaterial, null), "hDS", "DSD", "SDf", 'S', aStickStack, 'D', Dyes.dyeBlue);
                GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(SCREWDRIVER, 1, aMaterial, aMaterial.mHandleMaterial, null), " fS", " Sh", "W  ", 'S', aStickStack, 'W', aHandleStack);
                GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(WIRECUTTER, 1, aMaterial, aMaterial, null), "PfP", "hPd", "STS", 'S', aStickStack, 'P', aPlateStack, 'T', aBoltStack);
                GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(SCOOP, 1, aMaterial, aMaterial, null), "SWS", "SSS", "xSh", 'S', aStickStack, 'W', new ItemStack(Blocks.wool, 1, 32767));
                GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(BRANCHCUTTER, 1, aMaterial, aMaterial, null), "PfP", "PdP", "STS", 'S', aStickStack, 'P', aPlateStack, 'T', aBoltStack);
                GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(KNIFE, 1, aMaterial, aMaterial, null), "fPh", " S ", 'S', aStickStack, 'P', aPlateStack);
                GT_ModHandler.addBasicShapedRecipe(INSTANCE.getToolWithStats(BUTCHERYKNIFE, 1, aMaterial, aMaterial, null), "PPf", "PP ", "Sh ", 'S', aStickStack, 'P', aPlateStack);
                GT_ModHandler.addCraftingRecipe(INSTANCE.getToolWithStats(SOLDERING_IRON_LV, 1, aMaterial, Materials.Rubber, new long[]{100000L, 32L, 1L, -1L}), "LBf", "Sd ", "P  ", 'B', aBoltStack, 'P', OrePrefixes.plate.get(Materials.AnyRubber), 'S', GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron), 'L', ItemList.Battery_RE_LV_Lithium.get(1));
            }
        }
        if (!aNoWorking && aMaterial.hasFlag(BGEM)) {
            GT_ModHandler.addShapedToolRecipe(aHeadAxe, "GG ", "G  ", "f  ", 'G', aGemStack);
            GT_ModHandler.addShapedToolRecipe(aHeadHoe, "GG ", "f  ", "   ", 'G', aGemStack);
            GT_ModHandler.addShapedToolRecipe(aHeadPick, "GGG", "f  ", 'G', aGemStack);
            GT_ModHandler.addShapedToolRecipe(aHeadPlow, "GG", "GG", " f", 'G', aGemStack);
            GT_ModHandler.addShapedToolRecipe(aHeadSaw, "GGf", 'G', aGemStack);
            GT_ModHandler.addShapedToolRecipe(aHeadSense, "GGG", " f ", "   ", 'G', aGemStack);
            GT_ModHandler.addShapedToolRecipe(aHeadShovel, "fG", 'G', aGemStack);
            GT_ModHandler.addShapedToolRecipe(aHeadSword, " G", "fG", 'G', aGemStack);
        }
        if (aSpecialRecipeReq1) {
            GT_ModHandler.addShapedToolRecipe(aHeadAxe, "PIh", "P  ", "f  ", 'P', aPlateStack, 'I', aIngotStack);
            GT_ModHandler.addShapedToolRecipe(aHeadHoe, "PIh", "f  ", 'P', aPlateStack, 'I', aIngotStack);
            GT_ModHandler.addShapedToolRecipe(aHeadPick, "PII", "f h", 'P', aPlateStack, 'I', aIngotStack);
            GT_ModHandler.addShapedToolRecipe(aHeadPlow, "PP", "PP", "hf", 'P', aPlateStack);
            GT_ModHandler.addShapedToolRecipe(aHeadSaw, "PP ", "fh ", 'P', aPlateStack, 'I', aIngotStack);
            GT_ModHandler.addShapedToolRecipe(aHeadSense, "PPI", "hf ", 'P', aPlateStack, 'I', aIngotStack);
            GT_ModHandler.addShapedToolRecipe(aHeadShovel, "fPh", 'P', aPlateStack, 'I', aIngotStack);
            GT_ModHandler.addShapedToolRecipe(aHeadSword, " P ", "fPh", 'P', aPlateStack, 'I', aIngotStack);
        }
        if (aSpecialRecipeReq2) {
            ItemStack aSteelPlate = GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Steel);
            ItemStack aSteelRing = GT_OreDictUnificator.get(OrePrefixes.ring, Materials.Steel);
            GT_ModHandler.addShapedToolRecipe(aBuzzsawHead, "wXh", "X X", "fXx", 'X', aPlateStack);
            GT_ModHandler.addShapedToolRecipe(aChainsawHead, "SRS", "XhX", "SRS", 'X', aPlateStack, 'S', aSteelPlate, 'R', aSteelRing);
            GT_ModHandler.addShapedToolRecipe(aDrillHead, "XSX", "XSX", "ShS", 'X', aPlateStack, 'S', aSteelPlate);
            GT_ModHandler.addShapedToolRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadUniversalSpade, aMaterial), "fX", 'X', aHeadShovel);
            GT_ModHandler.addShapedToolRecipe(aHeadTurbine, "fPd", "SPS", " P ", 'P', aPlateStack, 'S', aBoltStack);
            GT_ModHandler.addShapedToolRecipe(aWrenchHead, "hXW", "XRX", "WXd", 'X', aPlateStack, 'S', aSteelPlate, 'R', aSteelRing, 'W', GT_OreDictUnificator.get(OrePrefixes.screw, Materials.Steel));
        }
        if (!aNoSmelting) {
            for (OrePrefixes aPrefix : Arrays.asList(new OrePrefixes[]{OrePrefixes.dust, OrePrefixes.ingot})) {
                int tAmount = aPrefix.mMaterialAmount / 3628800;
                int tVoltageMultiplier = 8 * (aMaterial.mBlastFurnaceTemp >= 2800 ? 64 : 16);
                if (tAmount > 0 && tAmount <= 64) {
                    ItemStack aInputStack = GT_OreDictUnificator.get(aPrefix, aMaterial);
                    ItemStack a2xStack = GT_Utility.copyAmount(2, aInputStack);
                    ItemStack a3xStack = GT_Utility.copyAmount(3, aInputStack);
                    int aRecipeTime = Math.max(aMaterial.getMass() * 2 * tAmount, tAmount);
                    GT_Values.RA.addExtruderRecipe(a2xStack, ItemList.Shape_Extruder_Sword.get(0), aMaterial == aMaterial.mSmeltInto ? aHeadSword : GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, aMaterial.mSmeltInto, tAmount), aRecipeTime, tVoltageMultiplier);
                    GT_Values.RA.addExtruderRecipe(a3xStack, ItemList.Shape_Extruder_Pickaxe.get(0), aMaterial == aMaterial.mSmeltInto ? aHeadPick : GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, aMaterial.mSmeltInto, tAmount), Math.max(aMaterial.getMass() * 3 * tAmount, tAmount), tVoltageMultiplier);
                    GT_Values.RA.addExtruderRecipe(aInputStack, ItemList.Shape_Extruder_Shovel.get(0), GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, aMaterial.mSmeltInto, tAmount), Math.max(aMaterial.getMass() * tAmount, tAmount), tVoltageMultiplier);
                    GT_Values.RA.addExtruderRecipe(a3xStack, ItemList.Shape_Extruder_Axe.get(0), aMaterial == aMaterial.mSmeltInto ? aHeadAxe : GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, aMaterial.mSmeltInto, tAmount), Math.max(aMaterial.getMass() * 3 * tAmount, tAmount), tVoltageMultiplier);
                    GT_Values.RA.addExtruderRecipe(a2xStack, ItemList.Shape_Extruder_Hoe.get(0), aMaterial == aMaterial.mSmeltInto ? aHeadHoe : GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, aMaterial.mSmeltInto, tAmount), aRecipeTime, tVoltageMultiplier);
                    GT_Values.RA.addExtruderRecipe(GT_Utility.copyAmount(6, aInputStack), ItemList.Shape_Extruder_Hammer.get(0), aMaterial == aMaterial.mSmeltInto ? aHeadHammer : GT_OreDictUnificator.get(OrePrefixes.toolHeadHammer, aMaterial.mSmeltInto, tAmount), Math.max(aMaterial.getMass() * 6 * tAmount, tAmount), tVoltageMultiplier);
                    GT_Values.RA.addExtruderRecipe(a2xStack, ItemList.Shape_Extruder_File.get(0), aMaterial == aMaterial.mSmeltInto ? aHeadFile : GT_OreDictUnificator.get(OrePrefixes.toolHeadFile, aMaterial.mSmeltInto, tAmount), aRecipeTime, tVoltageMultiplier);
                    GT_Values.RA.addExtruderRecipe(a2xStack, ItemList.Shape_Extruder_Saw.get(0), aMaterial == aMaterial.mSmeltInto ? aHeadSaw : GT_OreDictUnificator.get(OrePrefixes.toolHeadSaw, aMaterial.mSmeltInto, tAmount), aRecipeTime, tVoltageMultiplier);
                }
            }
        }
    }

    private void addCellRecipes(Materials aMaterial) { //TODO ADD DCELL FLAG (DUST CELL) FOR CANNER RECIPES THAT USE DUST INSTEAD OF FLUIDS
        ItemStack aCellStack = GT_OreDictUnificator.get(OrePrefixes.cell, aMaterial);
        if (GT_Utility.getFluidForFilledItem(aCellStack, true) == null && aMaterial != Materials.Air) {
            GT_Values.RA.addCannerRecipe(aDustStack, ItemList.Cell_Empty.get(1), aCellStack, null, 100, 1);
        }
    }

    /*private void addGemOreRecipes(Materials aMaterial, boolean aIsRich) {
        int aMultiplier = aIsRich ? 2 : 1;

        ItemStack aOreStack = GT_OreDictUnificator.get(OrePrefixes.oreChunk, aMaterial);
        ItemStack aGemStack = aMaterial == aMaterial.mDirectSmelting ? this.aGemStack : GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial.mDirectSmelting);
        ItemStack aCleanedStack = GT_OreDictUnificator.get(OrePrefixes.crushedPurified, aMaterial);
        ItemStack aCentStack = GT_OreDictUnificator.get(OrePrefixes.crushedCentrifuged, aMaterial);
        ItemStack aImpureStack = GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial);
        ItemStack aPureStack = GT_OreDictUnificator.get(OrePrefixes.dustPure, aMaterial);
        ItemStack aCrushedStack = GT_OreDictUnificator.get(OrePrefixes.crushed, aMaterial, aMaterial.mOreMultiplier * aMultiplier);
        ItemStack aStoneDust = GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Stone);
        ItemStack aDustImpureMacInto = aMaterial == aMaterial.mMacerateInto ? aImpureStack : GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial.mMacerateInto);
        ItemStack aDustMacInto = aMaterial == aMaterial.mMacerateInto ? aDustStack : GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial.mMacerateInto);
        ItemStack aPrimaryByProductStack = null;

        GT_ModHandler.addShapedToolRecipe(aDustStack, "h  ", "X  ", "   ", 'X', aCentStack);
        GT_ModHandler.addShapedToolRecipe(aCrushedStack, "h  ", "X  ", "   ", 'X', aImpureStack);
        GT_ModHandler.addShapedToolRecipe(aCleanedStack, "h  ", "X  ", "   ", 'X', aPureStack);

        ArrayList<ItemStack> tByProductStacks = new ArrayList<>();
        for (Materials aCurrByProductMaterial : aMaterial.mByProducts) {
            if (aCurrByProductMaterial.mPrimaryState != MaterialState.FLUID) {
                ItemStack aByProductDust = GT_OreDictUnificator.get(OrePrefixes.dust, aCurrByProductMaterial);
                tByProductStacks.add(aByProductDust);
                if (aPrimaryByProductStack == null) aPrimaryByProductStack = aByProductDust;
            }
        }
        if (aPrimaryByProductStack == null) aPrimaryByProductStack = aDustStack;


        if (tByProductStacks.size() > 0) GT_Recipe.GT_Recipe_Map.sByProductList.addFakeRecipe(false, new ItemStack[]{aOreStack}, tByProductStacks.toArray(new ItemStack[tByProductStacks.size()]), null, null, null, null, 0, 0, 0);

        if (aGemStack != null) {
            GT_ModHandler.addSmeltingRecipe(aOreStack, GT_Utility.copyAmount(aMultiplier * aMaterial.mSmeltingMultiplier, aGemStack));
        }

        GT_Values.RA.addForgeHammerRecipe(aOreStack, aCrushedStack, 16, 10);
        GT_Values.RA.addForgeHammerRecipe(aCrushedStack, aDustImpureMacInto, 10, 16);
        GT_Values.RA.addForgeHammerRecipe(aCentStack, aDustMacInto, 10, 16);

        Materials aOreByProduct1 = aMaterial.mByProducts.size() >= 1 ? aMaterial.mByProducts.get(0) : aMaterial.mMacerateInto;
        Materials aOreByProduct2 = aMaterial.mByProducts.size() >= 2 ? aMaterial.mByProducts.get(1) : aOreByProduct1;
        Materials aOreByProduct3 = aMaterial.mByProducts.size() >= 3 ? aMaterial.mByProducts.get(2) : aOreByProduct2;

        GT_ModHandler.addPulverisationRecipe(aOreStack, GT_Utility.copyAmount(aCrushedStack.stackSize * 2, aCrushedStack), GT_Utility.copyAmount(1, aPrimaryByProductStack), aPrimaryByProductStack.stackSize * 10 * aMultiplier * aMaterial.mByProductMultiplier, aStoneDust, 50, true);
        GT_ModHandler.addPulverisationRecipe(aCrushedStack, aDustImpureMacInto, GT_OreDictUnificator.get(OrePrefixes.dust, aOreByProduct1), 10, false);
        GT_ModHandler.addPulverisationRecipe(aCentStack, aDustMacInto, GT_OreDictUnificator.get(OrePrefixes.dust, aOreByProduct3), 10, false);

        GT_ModHandler.addOreWasherRecipe(aCrushedStack, 1000, aCleanedStack, GT_OreDictUnificator.get(OrePrefixes.dustTiny, aOreByProduct1), aStoneDust);
        ItemStack aThermalBonus = GT_OreDictUnificator.get(OrePrefixes.dustTiny, aOreByProduct2);
        GT_ModHandler.addThermalCentrifugeRecipe(aCrushedStack, aCentStack, aThermalBonus, aStoneDust);
        GT_ModHandler.addThermalCentrifugeRecipe(aCleanedStack, GT_OreDictUnificator.get(OrePrefixes.crushedCentrifuged, aMaterial.mMacerateInto), aThermalBonus);

        if (aWashingMercury) {
            GT_Values.RA.addChemicalBathRecipe(aCrushedStack, Materials.Mercury.getFluid(1000), aCleanedStack, aDustMacInto, aStoneDust, new int[]{10000, 7000, 4000}, 800, 8);
        }
        if (aWashingSodium) {
            GT_Values.RA.addChemicalBathRecipe(aCrushedStack, Materials.SodiumPersulfate.getFluid(aSodiumFluidAmount), aCleanedStack, aDustMacInto, aStoneDust, new int[]{10000, 7000, 4000}, 800, 8);
        }

        switch (aMaterial.mName) {
            case "Tanzanite": case "Sapphire": case "Olivine": case "GreenSapphire": case "Opal": case "Amethyst": case "Emerald": case "Ruby":
            case "Amber": case "Diamond": case "FoolsRuby": case "BlueTopaz": case "GarnetRed": case "Topaz": case "Jasper": case "GarnetYellow":
                GT_Values.RA.addSifterRecipe(aCleanedStack, new ItemStack[]{aExquisiteStack, aFlawlessStack, this.aGemStack, aFlawedStack, aChippedStack, aDustStack}, new int[]{300, 1200, 4500, 1400, 2800, 3500}, 800, 16);
                break;
            default:
                GT_Values.RA.addSifterRecipe(aCleanedStack, new ItemStack[]{aExquisiteStack, aFlawlessStack, aFlawedStack, aChippedStack, aDustStack}, new int[]{100, 400, 1500, 2000, 4000, 5000}, 800, 16);
                break;
        }
    }*/

    private void addOreRecipes(Materials aMaterial, boolean aIsRich) {
        int aMultiplier = aIsRich ? 2 : 1;
        boolean isMaterialAlsoDirect = aMaterial == aMaterial.mDirectSmelting;
        boolean aNeedsBlastFurnace = aMaterial.mBlastFurnaceRequired || aMaterial.mDirectSmelting.mBlastFurnaceRequired;

        ItemStack aOreStack = GT_OreDictUnificator.get(OrePrefixes.oreChunk, aMaterial);
        ItemStack aIngotOrGemStack = aMaterial == aMaterial.mDirectSmelting ? aMaterial.mPrimaryState == MaterialState.CRYSTAL ? this.aGemStack : this.aIngotStack : aMaterial.mPrimaryState == MaterialState.CRYSTAL ? GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial.mDirectSmelting) : GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mDirectSmelting);
        ItemStack aCleanedStack = GT_OreDictUnificator.get(OrePrefixes.crushedPurified, aMaterial);
        ItemStack aCentStack = GT_OreDictUnificator.get(OrePrefixes.crushedCentrifuged, aMaterial);
        ItemStack aImpureStack = GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial);
        ItemStack aPureStack = GT_OreDictUnificator.get(OrePrefixes.dustPure, aMaterial);
        ItemStack aCrushedStack = GT_OreDictUnificator.get(OrePrefixes.crushed, aMaterial);
        ItemStack aStoneDust = GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Stone);
        ItemStack aDustImpureMacInto = aMaterial == aMaterial.mMacerateInto ? aImpureStack : GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial.mMacerateInto);
        ItemStack aDustMacInto = aMaterial == aMaterial.mMacerateInto ? aDustStack : GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial.mMacerateInto);
        ItemStack aPrimaryByProductStack = null;

        GT_ModHandler.addShapedToolRecipe(aDustStack, "h  ", "X  ", "   ", 'X', aCentStack);
        GT_ModHandler.addShapedToolRecipe(aCrushedStack, "h  ", "X  ", "   ", 'X', aImpureStack); //TODO BROKEN?
        GT_ModHandler.addShapedToolRecipe(aCleanedStack, "h  ", "X  ", "   ", 'X', aPureStack); //TODO BROKEN?
        GT_ModHandler.addShapedToolRecipe(aCentStack, "h  ", "X  ", "   ", 'X', aDustStack);

        ArrayList<ItemStack> tByProductStacks = new ArrayList<>();
        for (Materials aCurrByProductMaterial : aMaterial.mByProducts) {
            if (aCurrByProductMaterial.mPrimaryState != MaterialState.FLUID) {
                ItemStack aByProductDust = GT_OreDictUnificator.get(OrePrefixes.dust, aCurrByProductMaterial);
                tByProductStacks.add(aByProductDust);
                if (aPrimaryByProductStack == null) aPrimaryByProductStack = aByProductDust;
            }
        }
        if (aPrimaryByProductStack == null) aPrimaryByProductStack = aDustStack;

        if (tByProductStacks.size() > 0) GT_Recipe.GT_Recipe_Map.sByProductList.addFakeRecipe(false, new ItemStack[]{aOreStack}, tByProductStacks.toArray(new ItemStack[tByProductStacks.size()]), null, null, null, null, 0, 0, 0);

        if (!aNeedsBlastFurnace) {
            ItemStack aNonDirectSmeltingOutput = GT_Mod.gregtechproxy.mMixedOreOnlyYieldsTwoThirdsOfPureOre ? GT_Utility.copyAmount(6, aNuggetStack) : GT_OreDictUnificator.get(aMaterial.mPrimaryState == MaterialState.CRYSTAL ? OrePrefixes.gem : OrePrefixes.ingot, aMaterial.mDirectSmelting);
            ItemStack aCrushedSmeltingOutput = isMaterialAlsoDirect ? GT_Utility.copyAmount(10, aNuggetStack) : aNonDirectSmeltingOutput;
            ItemStack aDustSmeltingOutput = isMaterialAlsoDirect ? aIngotOrGemStack : aNonDirectSmeltingOutput;
            GT_ModHandler.addSmeltingRecipe(aCrushedStack, aCrushedSmeltingOutput);
            GT_ModHandler.addSmeltingRecipe(aCleanedStack, aCrushedSmeltingOutput);
            GT_ModHandler.addSmeltingRecipe(aCentStack, aCrushedSmeltingOutput);
            GT_ModHandler.addSmeltingRecipe(aPureStack, aDustSmeltingOutput);
            GT_ModHandler.addSmeltingRecipe(aImpureStack, aDustSmeltingOutput);
            GT_ModHandler.addSmeltingRecipe(aDustStack, aIngotOrGemStack);
            GT_ModHandler.addSmeltingRecipe(aOreStack, GT_Utility.copyAmount(aMultiplier * aMaterial.mSmeltingMultiplier, aIngotOrGemStack));
        }

        GT_Values.RA.addForgeHammerRecipe(aOreStack, aMaterial.mPrimaryState == MaterialState.CRYSTAL ? aGemStack : aCrushedStack, 16, 10);
        GT_Values.RA.addForgeHammerRecipe(aCrushedStack, aImpureStack, 10, 16);
        GT_Values.RA.addForgeHammerRecipe(aCleanedStack, aPureStack, 10, 16);
        GT_Values.RA.addForgeHammerRecipe(aCentStack, aDustMacInto, 10, 16);

        Materials aOreByProduct1 = aMaterial.mByProducts.size() >= 1 ? aMaterial.mByProducts.get(0) : aMaterial.mMacerateInto;
        Materials aOreByProduct2 = aMaterial.mByProducts.size() >= 2 ? aMaterial.mByProducts.get(1) : aOreByProduct1;
        Materials aOreByProduct3 = aMaterial.mByProducts.size() >= 3 ? aMaterial.mByProducts.get(2) : aOreByProduct2;

        GT_ModHandler.addPulverisationRecipe(aOreStack, GT_Utility.copyAmount((aCrushedStack.stackSize * aMaterial.mOreMultiplier * aMultiplier) * 2, aCrushedStack), GT_Utility.copyAmount(1, aPrimaryByProductStack), aPrimaryByProductStack.stackSize * 10 * aMultiplier * aMaterial.mByProductMultiplier, aStoneDust, 50, true);
        GT_ModHandler.addPulverisationRecipe(aCrushedStack, aDustImpureMacInto, GT_OreDictUnificator.get(OrePrefixes.dust, aOreByProduct1), 10, false);
        GT_ModHandler.addPulverisationRecipe(aCleanedStack, aPureStack, GT_OreDictUnificator.get(OrePrefixes.dust, aOreByProduct2), 10, false);
        GT_ModHandler.addPulverisationRecipe(aCentStack, aDustMacInto, GT_OreDictUnificator.get(OrePrefixes.dust, aOreByProduct3), 10, false);

        GT_ModHandler.addOreWasherRecipe(aCrushedStack, 1000, aCleanedStack, GT_OreDictUnificator.get(OrePrefixes.dustTiny, aOreByProduct1), aStoneDust);
        ItemStack aThermalBonus = GT_OreDictUnificator.get(OrePrefixes.dustTiny, aOreByProduct2);
        GT_ModHandler.addThermalCentrifugeRecipe(aCrushedStack, aCentStack, aThermalBonus, aStoneDust);
        GT_ModHandler.addThermalCentrifugeRecipe(aCleanedStack, GT_OreDictUnificator.get(OrePrefixes.crushedCentrifuged, aMaterial.mMacerateInto), aThermalBonus);

        if (aMaterial.mPrimaryState == MaterialState.CRYSTAL) { //Gem Specific Recipes
            GT_ModHandler.addSmeltingRecipe(aOreStack, GT_Utility.copyAmount(aMultiplier * aMaterial.mSmeltingMultiplier, aIngotOrGemStack));
            if (aMaterial.hasFlag(MaterialFlags.GEM)) {
                GT_Values.RA.addSifterRecipe(aCleanedStack, new ItemStack[]{aExquisiteStack, aFlawlessStack, aIngotOrGemStack, aFlawedStack, aChippedStack, aDustStack}, new int[]{300, 1200, 4500, 1400, 2800, 3500}, 800, 16);
            } else if (aMaterial.hasFlag(MaterialFlags.BGEM)) {
                GT_Values.RA.addSifterRecipe(aCleanedStack, new ItemStack[]{aIngotOrGemStack, aIngotOrGemStack, aIngotOrGemStack, aIngotOrGemStack, aIngotOrGemStack, aDustStack}, new int[]{100, 400, 1500, 2000, 4000, 5000}, 800, 16);
            }
            GT_Values.RA.addAutoclaveRecipe(aPureStack, aWaterStack, aGemStack, 9000, 2000, 24);
            GT_Values.RA.addAutoclaveRecipe(aPureStack, aDistilledStack, aGemStack, 9500, 1500, 24);
            GT_Values.RA.addAutoclaveRecipe(aImpureStack, aWaterStack, aGemStack, 9000, 2000, 24);
            GT_Values.RA.addAutoclaveRecipe(aImpureStack, aDistilledStack, aGemStack, 9500, 1500, 24);
        } else { //Solid Specific Recipes
            if (aNeedsBlastFurnace) {
                ItemStack aIngotSmeltInto = aMaterial == aMaterial.mSmeltInto ? aIngotOrGemStack : GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mSmeltInto);
                int aBlastDuration = Math.max(aMaterial.getMass() / 4, 1) * aMaterial.mBlastFurnaceTemp;
                ItemStack aBlastStack = aMaterial.mBlastFurnaceTemp > 1750 ? GT_OreDictUnificator.get(OrePrefixes.ingotHot, aMaterial.mSmeltInto, aIngotSmeltInto) : aIngotSmeltInto;
                GT_Values.RA.addBlastRecipe(aCrushedStack, null, null, null, aBlastStack, null, aBlastDuration, 120, aMaterial.mBlastFurnaceTemp);
                GT_Values.RA.addBlastRecipe(aCrushedStack, null, null, null, aBlastStack, null, aBlastDuration, 120, aMaterial.mBlastFurnaceTemp);
                GT_Values.RA.addBlastRecipe(aCleanedStack, null, null, null, aBlastStack, null, aBlastDuration, 120, aMaterial.mBlastFurnaceTemp);
                GT_Values.RA.addBlastRecipe(aCentStack, null, null, null, aBlastStack, null, aBlastDuration, 120, aMaterial.mBlastFurnaceTemp);
                GT_Values.RA.addBlastRecipe(aPureStack, null, null, null, aBlastStack, null, aBlastDuration, 120, aMaterial.mBlastFurnaceTemp);
                GT_Values.RA.addBlastRecipe(aImpureStack, null, null, null, aBlastStack, null, aBlastDuration, 120, aMaterial.mBlastFurnaceTemp);
                GT_Values.RA.addBlastRecipe(aDustStack, null, null, null, aBlastStack, null, aBlastDuration, 120, aMaterial.mBlastFurnaceTemp);
            }
            if (aMaterial.contains(SubTag.BLASTFURNACE_CALCITE_TRIPLE)) {
                ItemStack aMultiStack = GT_Utility.mul(aMultiplier * 3 * aMaterial.mSmeltingMultiplier, aIngotOrGemStack);
                ItemStack aDarkAsh = GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.DarkAsh);
                GT_Values.RA.addBlastRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, aMultiplier), null, null, aMultiStack, aDarkAsh, aIngotOrGemStack.stackSize * 500, 120, 1500);
                GT_Values.RA.addBlastRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Quicklime, aMultiplier), null, null, aMultiStack, aDarkAsh, aIngotOrGemStack.stackSize * 500, 120, 1500);
            } else if (aMaterial.contains(SubTag.BLASTFURNACE_CALCITE_DOUBLE)) {
                ItemStack aDarkAsh = GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.DarkAsh);
                GT_Values.RA.addBlastRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, aMultiplier), null, null, GT_Utility.mul(aMultiplier * aMixedOreYieldCount * aMaterial.mSmeltingMultiplier, aIngotOrGemStack), aDarkAsh, aIngotOrGemStack.stackSize * 500, 120, 1500);
                GT_Values.RA.addBlastRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Quicklime, aMultiplier * 3), null, null, GT_Utility.mul(aMultiplier * 3 * aMaterial.mSmeltingMultiplier, aIngotOrGemStack), aDarkAsh, aIngotOrGemStack.stackSize * 500, 120, 1500);
            }
            if (aWashingMercury) { //TODO MANUAL RECIPE SECTION? BROKEN? IDKDJPAKfs
                GT_Values.RA.addChemicalBathRecipe(aCrushedStack, Materials.Mercury.getFluid(1000), aCleanedStack, GT_OreDictUnificator.get(OrePrefixes.dust, aOreByProduct2), aStoneDust, new int[]{10000, 7000, 4000}, 800, 8);
            }
            if (aWashingSodium) {
                GT_Values.RA.addChemicalBathRecipe(aCrushedStack, Materials.SodiumPersulfate.getFluid(aSodiumFluidAmount), aCleanedStack, GT_OreDictUnificator.get(OrePrefixes.dust, aOreByProduct2), aStoneDust, new int[]{10000, 7000, 4000}, 800, 8);
            }
        }
    }

    private void addBasicGemRecipes(Materials aMaterial) {
        if (aMaterial.mTransparent) { //TODO PLATE > LENS BROKEN
            aLensStack = GT_OreDictUnificator.get(OrePrefixes.lens, aMaterial);
            GT_Values.RA.addLatheRecipe(aPlateStack, aLensStack, aSDustStack, Math.max(aMaterial.getMass() / 2, 1), 480);
            //GregTech_API.registerCover(aLensStack, new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_LENS, aMaterial.mRGBa, false)), new GT_Cover_Lens(aMaterial.mColor.mIndex));
        }
        if (!OrePrefixes.block.isIgnored(aMaterial)) {
            GT_ModHandler.addCompressionRecipe(GT_Utility.copyAmount(9, aGemStack), aBlockStack);
        }
        GT_Values.RA.addForgeHammerRecipe(aBlockStack, GT_Utility.copyAmount(9, aGemStack), 100, 24);
        if (aMaterial.contains(SubTag.CRYSTALLISABLE)) {
            GT_Values.RA.addAutoclaveRecipe(aDustStack, aWaterStack, aGemStack, 7000, 2000, 24);
            GT_Values.RA.addAutoclaveRecipe(aDustStack, aDistilledStack, aGemStack, 9000, 1500, 24);
        }
    }

    private void addGemRecipes(Materials aMaterial) {
        int aMaterialMass = aMaterial.getMass();
        if (aMaterial.mFuelPower > 0) {
            GT_Values.RA.addFuel(aGemStack, null, aMaterial.mFuelPower * 2, aMaterial.mFuelType);
            GT_Values.RA.addFuel(aChippedStack, null, aMaterial.mFuelPower / 2, aMaterial.mFuelType);
            GT_Values.RA.addFuel(aFlawedStack, null, aMaterial.mFuelPower, aMaterial.mFuelType);
            GT_Values.RA.addFuel(aFlawlessStack, null, aMaterial.mFuelPower * 4, aMaterial.mFuelType);
            GT_Values.RA.addFuel(aExquisiteStack, null, aMaterial.mFuelPower * 8, aMaterial.mFuelType);
        }
        if (aNoSmashing) {
            GT_Values.RA.addForgeHammerRecipe(aGemStack, GT_Utility.copyAmount(2, aFlawedStack), 64, 16);
        }
        if (!aNoWorking) {
            GT_Values.RA.addLatheRecipe(aChippedStack, aBoltStack, aTDustStack, Math.max(aMaterialMass, 1), 8);
            GT_Values.RA.addLatheRecipe(aFlawedStack, GT_Utility.copyAmount(2, aBoltStack), aSDustStack, Math.max(aMaterialMass, 1), 12);
            GT_Values.RA.addLatheRecipe(aExquisiteStack, aLensStack, GT_Utility.copyAmount(2, aDustStack), Math.max(aMaterial.getMass(), 1), 24);
            if (aMaterial.mUnificatable && aMaterial.mMaterialInto == aMaterial) {
                //TODO FIX
                //GT_ModHandler.addShapedToolRecipe(GT_Utility.copyAmount(2, aGemStack), "h  ", "X  ", "   ", 'X', aFlawlessStack);
                //GT_ModHandler.addShapedToolRecipe(GT_Utility.copyAmount(2, aChippedStack), "h  ", "X  ", "   ", 'X', aFlawedStack);
                //GT_ModHandler.addShapedToolRecipe(GT_Utility.copyAmount(2, aFlawedStack), "h  ", "X  ", "   ", 'X', aGemStack);
                //GT_ModHandler.addShapedToolRecipe(GT_Utility.copyAmount(2, aFlawlessStack), "h  ", "X  ", "   ", 'X', aExquisiteStack);
                if (aMaterial.contains(SubTag.MORTAR_GRINDABLE)) {
                    //GT_ModHandler.addShapedToolRecipe(aDustStack, "X  ", "m  ", "   ", 'X', aGemStack);
                    //GT_ModHandler.addShapedToolRecipe(aSDustStack, "X  ", "m   ", "   ", 'X', aChippedStack);
                    //GT_ModHandler.addShapedToolRecipe(GT_Utility.copyAmount(2, aSDustStack), "X  ", "m  ", "   ", 'X', aFlawedStack);
                    //GT_ModHandler.addShapedToolRecipe(GT_Utility.copyAmount(2, aDustStack), "X  ", "m  ", "   ", 'X', aFlawlessStack);
                    //GT_ModHandler.addShapedToolRecipe(GT_Utility.copyAmount(4, aDustStack), "X  ", "m  ", "   ", 'X', aExquisiteStack);
                }
            }
        } else {
            GT_Values.RA.addLatheRecipe(aGemStack, aStickStack, GT_Utility.copyAmount(2, aSDustStack), Math.max(aMaterialMass, 1), 16);
        }
        GT_Values.RA.addForgeHammerRecipe(aFlawedStack, GT_Utility.copyAmount(2, aChippedStack), 64, 16);
        GT_Values.RA.addForgeHammerRecipe(aFlawlessStack, GT_Utility.copyAmount(2, aGemStack), 64, 16);
        //TODO NPE GT_RecipeRegistrator.registerUsagesForMaterials(aGemStack, aPlateStack.toString(), !aNoSmashing);
    }

    private void addSolidRecipes(Materials aMaterial) { //Nugget & Ingot Processing
        if (aMaterial.mFuelPower > 0) {
            GT_Values.RA.addFuel(aIngotStack, null, aMaterial.mFuelPower, aMaterial.mFuelType);
        }
        if (aMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Nugget.get(0), aMaterial.getMolten(16), aNuggetStack, 16, 4);
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Ingot.get(0), aMaterial.getMolten(144), aIngotStack, 32, 8);
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Block.get(0), aMaterial.getMolten(1296), aBlockStack, 288, 8);
        }
        GT_RecipeRegistrator.registerReverseFluidSmelting(aIngotStack, aMaterial, OrePrefixes.ingot.mMaterialAmount, null);
        GT_RecipeRegistrator.registerReverseMacerating(aIngotStack, aMaterial, OrePrefixes.ingot.mMaterialAmount, null, null, null, false);
        GT_RecipeRegistrator.registerReverseFluidSmelting(aNuggetStack, aMaterial, OrePrefixes.nugget.mMaterialAmount, null);
        GT_RecipeRegistrator.registerReverseMacerating(aNuggetStack, aMaterial, OrePrefixes.nugget.mMaterialAmount, null, null, null, false);
        if (aMaterial.mSmeltInto.mArcSmeltInto != aMaterial) {
            GT_RecipeRegistrator.registerReverseArcSmelting(aIngotStack, aMaterial, OrePrefixes.ingot.mMaterialAmount, null, null, null);
        }
        ItemStack tStack = GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial.mMacerateInto);
        if (tStack != null && (aMaterial.mBlastFurnaceRequired || aNoSmelting)) {
            GT_ModHandler.removeFurnaceSmelting(tStack);
        }
        if (aMaterial.mUnificatable && aMaterial.mMaterialInto == aMaterial && !aNoWorking) {
            if (!aMaterial.contains(SubTag.SMELTING_TO_GEM)) {
                GT_ModHandler.addBasicShapedRecipe(aIngotStack, "XXX", "XXX", "XXX", 'X', aNuggetStack);
            }
            if (aMaterial.contains(SubTag.MORTAR_GRINDABLE)) {
                GT_ModHandler.addShapedToolRecipe(aDustStack, "X  ", "m  ", "   ", 'X', aIngotStack);
            }
        }
        ItemStack aIngotSmeltInto = GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mSmeltInto);
        GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copyAmount(4, aSDustStack), ItemList.Shape_Mold_Ingot.get(0), aIngotSmeltInto, 130, 3, true);
        GT_RecipeRegistrator.registerUsagesForMaterials(aIngotStack, OrePrefixes.plate.get(aMaterial).toString(), !aNoSmashing);
        GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(9, aNuggetStack), aMaterial.contains(SubTag.SMELTING_TO_GEM) ? ItemList.Shape_Mold_Ball.get(0) : ItemList.Shape_Mold_Ingot.get(0), aIngotSmeltInto, 200, 2);
        if (!aNoSmelting) {
            GT_Values.RA.addAlloySmelterRecipe(aIngotStack, ItemList.Shape_Mold_Nugget.get(0), GT_Utility.copyAmount(9, aNuggetStack), 100, 1);
            GT_ModHandler.addSmeltingRecipe(aTDustStack, GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial.mSmeltInto));
            GT_Values.RA.addAlloySmelterRecipe(GT_Utility.copyAmount(9, aTDustStack), ItemList.Shape_Mold_Ingot.get(0), aIngotSmeltInto, 130, 3, true);
            if (aMaterial.mStandardMoltenFluid == null && aMaterial.contains(SubTag.SMELTING_TO_FLUID)) {
                GT_Mod.gregtechproxy.addAutogeneratedMoltenFluid(aMaterial);
                if (aMaterial.mSmeltInto != aMaterial && aMaterial.mSmeltInto.mStandardMoltenFluid == null) {
                    GT_Mod.gregtechproxy.addAutogeneratedMoltenFluid(aMaterial.mSmeltInto);
                }
            }
            ItemStack aDustSmeltInto = aMaterial.mSmeltInto.getIngots(1);
            if (aDustSmeltInto != null) {
                GT_ModHandler.addSmeltingRecipe(aDustStack, aDustSmeltInto);
            }
            int aVoltageMulti = aNoSmashing ? aMaterial.mBlastFurnaceTemp >= 2800 ? 16 : 4 : aMaterial.mBlastFurnaceTemp >= 2800 ? 64 : 16;
            if (aMaterial != aMaterial.mSmeltInto) {
                GT_Values.RA.addExtruderRecipe(aDustStack, ItemList.Shape_Extruder_Ingot.get(0), aIngotSmeltInto, 10, 4 * aVoltageMulti);
            }
            if (!OrePrefixes.block.isIgnored(aMaterial.mSmeltInto)) {
                ItemStack a9xIngotStack = GT_Utility.copyAmount(9, aIngotStack);
                ItemStack aBlockStack = GT_OreDictUnificator.get(OrePrefixes.block, aMaterial.mSmeltInto);
                GT_Values.RA.addExtruderRecipe(a9xIngotStack, ItemList.Shape_Extruder_Block.get(0), aBlockStack, 10, 8 * aVoltageMulti);
                GT_Values.RA.addAlloySmelterRecipe(a9xIngotStack, ItemList.Shape_Mold_Block.get(0), aBlockStack, 5, 4 * aVoltageMulti);
            }
        }
        if (!OrePrefixes.block.isIgnored(aMaterial)) {
            GT_ModHandler.addCompressionRecipe(GT_Utility.copyAmount(9, aIngotStack), aBlockStack);
        }
        if (aMaterial.mBlastFurnaceRequired) {
            int aBlastDuration = Math.max(aMaterial.getMass() / 4, 1) * aMaterial.mBlastFurnaceTemp;
            ItemStack aBlastStack = aMaterial.mBlastFurnaceTemp > 1750 ? GT_OreDictUnificator.get(OrePrefixes.ingotHot, aMaterial.mSmeltInto, aIngotSmeltInto) : aIngotSmeltInto;
            GT_Values.RA.addBlastRecipe(aDustStack, null, null, null, aBlastStack, null, aBlastDuration, 120, aMaterial.mBlastFurnaceTemp);
            GT_Values.RA.addBlastRecipe(GT_Utility.copyAmount(4, aSDustStack), null, null, null, aBlastStack, null, aBlastDuration, 120, aMaterial.mBlastFurnaceTemp);
            if (!aNoSmelting) { //TODO WUT?
                GT_Values.RA.addBlastRecipe(GT_Utility.copyAmount(9, aTDustStack), null, null, null, aBlastStack, null, aBlastDuration, 120, aMaterial.mBlastFurnaceTemp);
                GT_ModHandler.removeFurnaceSmelting(aTDustStack);
            }
        }
    }

    private void addDustRecipes(Materials aMaterial) { //Tiny, Small, Normal Dust Processing
        GT_ModHandler.addBasicShapedRecipe(GT_Utility.copyAmount(4, aSDustStack), " X", "  ", 'X', aDustStack);
        GT_ModHandler.addBasicShapedRecipe(GT_Utility.copyAmount(9, aTDustStack), "X ", "  ", 'X', aDustStack);
        GT_ModHandler.addBasicShapelessRecipe(aDustStack, aSDustStack, aSDustStack, aSDustStack, aSDustStack);
        GT_ModHandler.addBasicShapelessRecipe(aDustStack, aTDustStack, aTDustStack, aTDustStack, aTDustStack, aTDustStack, aTDustStack, aTDustStack, aTDustStack, aTDustStack);
        if (aMaterial.mFuelPower > 0) {
            GT_Values.RA.addFuel(aDustStack, null, aMaterial.mFuelPower, aMaterial.mFuelType);
        }
        if (!aMaterial.mBlastFurnaceRequired) {
            if (aMaterial.mSmeltInto != null) {
                GT_RecipeRegistrator.registerReverseFluidSmelting(aDustStack, aMaterial, OrePrefixes.dust.mMaterialAmount, null);
                GT_RecipeRegistrator.registerReverseFluidSmelting(aSDustStack, aMaterial, OrePrefixes.dustSmall.mMaterialAmount, null);
                GT_RecipeRegistrator.registerReverseFluidSmelting(aTDustStack, aMaterial, OrePrefixes.dustTiny.mMaterialAmount, null);
                if (aMaterial.mSmeltInto.mArcSmeltInto != aMaterial) {
                    GT_RecipeRegistrator.registerReverseArcSmelting(aDustStack, aMaterial, OrePrefixes.dust.mMaterialAmount, null, null, null);
                    GT_RecipeRegistrator.registerReverseArcSmelting(aSDustStack, aMaterial, OrePrefixes.dustSmall.mMaterialAmount, null, null, null);
                    GT_RecipeRegistrator.registerReverseArcSmelting(aTDustStack, aMaterial, OrePrefixes.dustTiny.mMaterialAmount, null, null, null);
                }
            }
        }
        if (aMaterial.mDirectSmelting != aMaterial && !aNoSmelting && !(aMaterial.mBlastFurnaceRequired || aMaterial.mDirectSmelting.mBlastFurnaceRequired) && !aMaterial.contains(SubTag.DONT_ADD_DEFAULT_BBF_RECIPE)) {
            GT_Values.RA.addPrimitiveBlastRecipe(GT_Utility.copyAmount(2, aDustStack), GT_Values.NI, 2, aMaterial.mDirectSmelting.getIngots(aMixedOreYieldCount), GT_Values.NI, 2400);
        }
        if (aMaterial.mProcessInto.size() > 0 || (aMaterial.hasFlag(CENT) || aMaterial.hasFlag(ELEC))) {
            int aInputStackCount = 0;
            int aInputCellCount = 0;
            int tDensityMultiplier = aMaterial.getDensity() > 3628800 ? aMaterial.getDensity() / 3628800 : 1;
            ArrayList<ItemStack> aOutputs = new ArrayList<>();
            FluidStack aSingleFluidStack = null;
            for (MaterialStack aMatStack : aMaterial.mProcessInto) {
                if (aMatStack.mAmount > 0 && aMatStack.mMaterial != Materials.Magic) {
                    ItemStack aCurrentOutputStack = null;
                    int aCurrentStackCount = aMatStack.mAmount * tDensityMultiplier;
                    if (aMatStack.mMaterial.mPrimaryState == MaterialState.FLUID) {
                        if (aSingleFluidStack == null && aMatStack.mMaterial != Materials.Air) {
                            if (aMatStack.mMaterial.hasFlag(CFLUID)) {
                                aSingleFluidStack = aMatStack.mMaterial.getFluid(1000 * aCurrentStackCount);
                            } else {
                                aSingleFluidStack = aMatStack.mMaterial.getGas(1000 * aCurrentStackCount);
                            }
                        } else {
                            aCurrentOutputStack = GT_OreDictUnificator.get(OrePrefixes.cell, aMatStack.mMaterial, aCurrentStackCount);
                            aInputCellCount += aCurrentStackCount;
                        }
                    } else {
                        aCurrentOutputStack = GT_OreDictUnificator.get(OrePrefixes.dust, aMatStack.mMaterial, aCurrentStackCount);
                    }
                    if (aCurrentStackCount < 1) continue;
                    if (aInputStackCount + aMatStack.mAmount <= (aCurrentOutputStack != null ? aCurrentOutputStack.getMaxStackSize() : 64)) {
                        aInputStackCount += aMatStack.mAmount;
                        aOutputs.add(aCurrentOutputStack);
                    }
                }
            }
            if (aOutputs.size() > 0) {
                ItemStack aInputStack = GT_Utility.copyAmount(aInputStackCount, aDustStack);
                ItemStack aCellStack = aInputCellCount > 0 ? ItemList.Cell_Empty.get(aInputCellCount) : null;
                if (aMaterial.hasFlag(ELEC)) {
                    GT_Values.RA.addElectrolyzerRecipe(aInputStack, aCellStack, null, aSingleFluidStack, aOutputs.size() < 1 ? null : aOutputs.get(0), aOutputs.size() < 2 ? null : aOutputs.get(1), aOutputs.size() < 3 ? null : aOutputs.get(2), aOutputs.size() < 4 ? null : aOutputs.get(3), aOutputs.size() < 5 ? null : aOutputs.get(4), aOutputs.size() < 6 ? null : aOutputs.get(5), null, Math.max(1, Math.abs(aMaterial.getProtons() * 2 * aInputStackCount)), Math.min(4, aOutputs.size()) * 30);
                }
                if (aMaterial.hasFlag(CENT)) {
                    GT_Values.RA.addCentrifugeRecipe(aInputStack, aCellStack, null, aSingleFluidStack, aOutputs.size() < 1 ? null : aOutputs.get(0), aOutputs.size() < 2 ? null : aOutputs.get(1), aOutputs.size() < 3 ? null : aOutputs.get(2), aOutputs.size() < 4 ? null : aOutputs.get(3), aOutputs.size() < 5 ? null : aOutputs.get(4), aOutputs.size() < 6 ? null : aOutputs.get(5), null, Math.max(1, Math.abs(aMaterial.getMass() * 4 * aInputStackCount)), Math.min(4, aOutputs.size()) * 5);
                }
            }
        }
    }

    private void addMaterialSpecificRecipes() {
        /*public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
            //TODO MOVE
            if (aOreDictName.equals(OreDictNames.craftingLensWhite.toString())) {
                GT_Values.RA.addLaserEngraverRecipe(new ItemStack(this, 1, 7), GT_Utility.copyAmount(0, aStack), new ItemStack(this, 1, 6), 50, 16);
                GT_Values.RA.addLaserEngraverRecipe(new ItemStack(this, 1, 15), GT_Utility.copyAmount(0, aStack), new ItemStack(this, 1, 14), 50, 16);
            }
        }*/

        ItemStack aChalcopyriteDust = Materials.Chalcopyrite.getDust(2);
        ItemStack aChalcopyriteDirectIngot = Materials.Chalcopyrite.mDirectSmelting.getIngots(aMixedOreYieldCount);
        GT_Values.RA.addPrimitiveBlastRecipe(aChalcopyriteDust, new ItemStack(Blocks.sand, 2), 2, aChalcopyriteDirectIngot, Materials.Ferrosilite.getDustSmall(2 * aMixedOreYieldCount), 2400);
        GT_Values.RA.addPrimitiveBlastRecipe(aChalcopyriteDust, Materials.Glass.getDust(2), 2, aChalcopyriteDirectIngot, Materials.Ferrosilite.getDustTiny(7 * aMixedOreYieldCount), 2400);
        GT_Values.RA.addPrimitiveBlastRecipe(aChalcopyriteDust, Materials.SiliconDioxide.getDust(2), 2, aChalcopyriteDirectIngot, Materials.Ferrosilite.getDust(aMixedOreYieldCount), 2400);
        GT_Values.RA.addPrimitiveBlastRecipe(Materials.Tetrahedrite.getDust(2), GT_Values.NI, 2, Materials.Tetrahedrite.mDirectSmelting.getIngots(aMixedOreYieldCount), Materials.Antimony.getNuggets(3 * aMixedOreYieldCount), 2400);
        GT_Values.RA.addPrimitiveBlastRecipe(Materials.Galena.getDust(2), GT_Values.NI, 2, Materials.Galena.mDirectSmelting.getIngots(aMixedOreYieldCount), Materials.Silver.getNuggets(6 * aMixedOreYieldCount), 2400);


        GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.WroughtIron));
        //TODO FIX RICH?
        GT_Values.RA.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.ore, Materials.Oilsands), null, null, Materials.Oil.getFluid(/*tIsRich ? 1000L : */500), new ItemStack(net.minecraft.init.Blocks.sand, 1, 0), null, null, null, null, null, new int[]{'?'}, /*tIsRich ? 2000 : */1000, 5);

        GT_Values.RA.addCompressorRecipe(ItemList.Cell_Empty.get(1), Materials.Air.getCells(1), 300, 2);

        /*ItemStack aSealedWoodStack = MatUnifier.get(aPrefix, Materials.WoodSealed);
        GT_Values.RA.addChemicalBathRecipe(aStack, Materials.SeedOil.getFluid(GT_Utility.translateMaterialToAmount(aPrefix.mMaterialAmount, 120L, true)), aSealedWoodStack, GT_Values.NI, GT_Values.NI, null, 100, 8);
        GT_Values.RA.addChemicalBathRecipe(aStack, Materials.SeedOilLin.getFluid(GT_Utility.translateMaterialToAmount(aPrefix.mMaterialAmount, 80L, true)), aSealedWoodStack, GT_Values.NI, GT_Values.NI, null, 100, 8);
        GT_Values.RA.addChemicalBathRecipe(aStack, Materials.SeedOilHemp.getFluid(GT_Utility.translateMaterialToAmount(aPrefix.mMaterialAmount, 80L, true)), aSealedWoodStack, GT_Values.NI, GT_Values.NI, null, 100, 8);*/


        int aMaterialAmount = 128 / 3628800;
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.IronMagnetic), Math.max(16, OrePrefixes.dust.mMaterialAmount * aMaterialAmount), 16);
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.IronMagnetic), Math.max(16, OrePrefixes.nugget.mMaterialAmount * aMaterialAmount), 16);
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.IronMagnetic), Math.max(16, OrePrefixes.ingot.mMaterialAmount * aMaterialAmount), 16);
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.IronMagnetic), Math.max(16, OrePrefixes.plate.mMaterialAmount * aMaterialAmount), 16);
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.IronMagnetic), Math.max(16, OrePrefixes.stick.mMaterialAmount * aMaterialAmount), 16);
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.IronMagnetic), Math.max(16, OrePrefixes.bolt.mMaterialAmount * aMaterialAmount), 16);
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.SteelMagnetic), Math.max(16, OrePrefixes.dust.mMaterialAmount * aMaterialAmount), 16);
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.SteelMagnetic), Math.max(16, OrePrefixes.nugget.mMaterialAmount * aMaterialAmount), 16);
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.SteelMagnetic), Math.max(16, OrePrefixes.ingot.mMaterialAmount * aMaterialAmount), 16);
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.SteelMagnetic), Math.max(16, OrePrefixes.plate.mMaterialAmount * aMaterialAmount), 16);
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.SteelMagnetic), Math.max(16, OrePrefixes.stick.mMaterialAmount * aMaterialAmount), 16);
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.Steel), GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.SteelMagnetic), Math.max(16, OrePrefixes.bolt.mMaterialAmount * aMaterialAmount), 16);
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Neodymium), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.NeodymiumMagnetic), Math.max(16, OrePrefixes.dust.mMaterialAmount * aMaterialAmount), 256);
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Neodymium), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.NeodymiumMagnetic), Math.max(16, OrePrefixes.nugget.mMaterialAmount * aMaterialAmount), 256);
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Neodymium), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.NeodymiumMagnetic), Math.max(16, OrePrefixes.ingot.mMaterialAmount * aMaterialAmount), 256);
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Neodymium), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.NeodymiumMagnetic), Math.max(16, OrePrefixes.plate.mMaterialAmount * aMaterialAmount), 256);
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Neodymium), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.NeodymiumMagnetic), Math.max(16, OrePrefixes.stick.mMaterialAmount * aMaterialAmount), 256);
        GT_Values.RA.addPolarizerRecipe(GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.Neodymium), GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.NeodymiumMagnetic), Math.max(16, OrePrefixes.bolt.mMaterialAmount * aMaterialAmount), 256);

        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "QuartzDustSmeltingIntoAESilicon", true)) {
            GT_ModHandler.removeFurnaceSmelting(Materials.NetherQuartz.getDust(1));
            GT_ModHandler.removeFurnaceSmelting(Materials.Quartz.getDust(1));
            GT_ModHandler.removeFurnaceSmelting(Materials.CertusQuartz.getDust(1));
        }

        GT_ModHandler.addSmeltingRecipe(Materials.Glass.getDust(1), new ItemStack(Blocks.glass));
        ItemStack aDarkAsh12Stack = GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12);
        ItemStack aDarkAsh8Stack = GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.DarkAsh, 12);
        GT_Values.RA.addImplosionRecipe(Materials.Opal.getDust(4), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Opal, 3), aDarkAsh12Stack);
        GT_Values.RA.addImplosionRecipe(Materials.Olivine.getDust(4), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Olivine, 3), aDarkAsh12Stack);
        GT_Values.RA.addImplosionRecipe(Materials.Emerald.getDust(4), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Emerald, 3), aDarkAsh12Stack);
        GT_Values.RA.addImplosionRecipe(Materials.Ruby.getDust(4), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Ruby, 3), aDarkAsh12Stack);
        GT_Values.RA.addImplosionRecipe(Materials.Sapphire.getDust(4), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Sapphire, 3), aDarkAsh12Stack);
        GT_Values.RA.addImplosionRecipe(Materials.GreenSapphire.getDust(4), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.GreenSapphire, 3), aDarkAsh12Stack);
        GT_Values.RA.addImplosionRecipe(Materials.Topaz.getDust(4), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Topaz, 3), aDarkAsh12Stack);
        GT_Values.RA.addImplosionRecipe(Materials.BlueTopaz.getDust(4), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.BlueTopaz, 3), aDarkAsh12Stack);
        GT_Values.RA.addImplosionRecipe(Materials.Tanzanite.getDust(4), 24, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Tanzanite, 3), aDarkAsh12Stack);
        GT_Values.RA.addImplosionRecipe(Materials.GarnetRed.getDust(4), 16, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.GarnetRed, 3), aDarkAsh8Stack);
        GT_Values.RA.addImplosionRecipe(Materials.GarnetYellow.getDust(4), 16, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.GarnetYellow, 3), aDarkAsh8Stack);
        GT_Values.RA.addImplosionRecipe(Materials.Amber.getDust(4), 16, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Amber, 3), aDarkAsh8Stack);
        GT_Values.RA.addImplosionRecipe(Materials.Monazite.getDust(4), 16, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Monazite, 3), aDarkAsh8Stack);
        GT_Values.RA.addElectrolyzerRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.CertusQuartz), 0, GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 1), null, null, null, null, null, 2000, 30);

        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.disabledrecipes, "torchesFromCoal", false)) {
            GT_ModHandler.removeRecipe(new ItemStack(Items.coal, 1, 0), null, null, new ItemStack(net.minecraft.init.Items.stick, 1, 0));
            GT_ModHandler.removeRecipe(new ItemStack(Items.coal, 1, 1), null, null, new ItemStack(net.minecraft.init.Items.stick, 1, 0));
        }

        //TODO MOVE TO COMB RECIPE AREA?
        Materials[] aSifterGemMaterials = new Materials[]{Materials.Sapphire, Materials.GreenSapphire, Materials.Emerald, Materials.Ruby, Materials.Amber, Materials.Diamond};
        for (Materials aGemMaterial : aSifterGemMaterials) {
            ItemStack aGem = GT_OreDictUnificator.get(OrePrefixes.gem, aGemMaterial);
            GT_Values.RA.addSifterRecipe(GT_OreDictUnificator.get(OrePrefixes.crushedPurified, aGemMaterial), new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.gemExquisite, aGemMaterial, aGem), GT_OreDictUnificator.get(OrePrefixes.gemFlawless, aGemMaterial, aGem), aGem, GT_OreDictUnificator.get(OrePrefixes.gemFlawed, aGemMaterial, aGem), GT_OreDictUnificator.get(OrePrefixes.gemChipped, aGemMaterial, aGem), GT_OreDictUnificator.get(OrePrefixes.dust, aGemMaterial, aGem)}, new int[]{300, 1200, 4500, 1400, 2800, 3500}, 800, 16);
        }

        /*if (aModName.equalsIgnoreCase("AtomicScience")) {
            GT_ModHandler.addExtractionRecipe(ItemList.Cell_Empty.get(1L), aStack);
        }*/

        GT_ModHandler.addBasicShapedRecipe(GT_Utility.copyAmount(GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.harderrecipes, Materials.Paper.getPlates(1), true) ? 2 : 3, Materials.Paper.getPlates(1)), "XXX", "   ", "   ", 'X', new ItemStack(Items.reeds, 1, 32767));

        /*GregTech_API.registerCover(Materials.Iron.getPlates(1), new GT_CopiedBlockTexture(Blocks.iron_block, 1, 0), null);
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
        GregTech_API.registerCover(Materials.Concrete.getPlates(1), new GT_RenderedTexture(Textures.BlockIcons.CONCRETE_LIGHT_SMOOTH), null);*/

        GT_ModHandler.addBasicShapedRecipe(GT_OreDictUnificator.get(OrePrefixes.gearGt, Materials.Wood), "SPS", "PsP", "SPS", 'P', GT_OreDictUnificator.get(OrePrefixes.plank, Materials.Wood), 'S', GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood));
        //TODO CHECK GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.gearGt, Materials.Wood), "SPS", "PsP", "SPS", 'P', OrePrefixes.plank.get(Materials.Wood), 'S', OrePrefixes.stick.get(Materials.Wood));
        GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.gearGt, Materials.Stone), "SPS", "PfP", "SPS", 'P', OrePrefixes.stoneSmooth, 'S', new ItemStack(Blocks.stone_button, 1, 32767));
        GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.gearGtSmall, Materials.Wood), "P ", " s", 'P', OrePrefixes.plank.get(Materials.Wood));
        GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.gearGtSmall, Materials.Stone), "P ", " f", 'P', OrePrefixes.stoneSmooth);
        /*switch (aOreDictName) {
        case "plateAlloyCarbon":
            GT_Values.RA.addAssemblerRecipe(GT_ModHandler.getIC2Item("generator", 1L), GT_Utility.copyAmount(4L, aStack), GT_ModHandler.getIC2Item("windMill", 1L), 6400, 8);
        case "plateAlloyAdvanced":
            GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copyAmount(1L, aStack), new ItemStack(Blocks.glass, 3, 32767), GT_ModHandler.getIC2Item("reinforcedGlass", 4L), 400, 4, false);
            GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copyAmount(1L, aStack), MatUnifier.get(OrePrefixes.dust, Materials.Glass, 3L), GT_ModHandler.getIC2Item("reinforcedGlass", 4L), 400, 4, false);
        case "plateAlloyIridium":
            GT_ModHandler.removeRecipeByOutput(aStack);
        }*/
        //MatUnifier.addItemData(aStack, new ItemData(Materials.Silicon, 3628800L, new MaterialStack[0]));
        //GT_Values.RA.addFormingPressRecipe(aCrushedStack, GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 0L, 19), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 20), 200, 16);

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
                GT_Values.RA.addLaserEngraverRecipe(MatUnifier.get(OrePrefixes.block, Materials.Iron, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 13), 2000, 1920);
                GT_Values.RA.addLaserEngraverRecipe(MatUnifier.get(OrePrefixes.block, Materials.WroughtIron, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 13), 2000, 1920);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.IC2_LapotronCrystal.getWildcard(1L), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Parts_Crystal_Chip_Master.get(3L), 256, 480,true);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer2.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_PIC.get(1), 500, 480,true);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer3.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_PIC.get(4), 200, 1920,true);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Chip_CrystalCPU.get(1L), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Chip_CrystalSoC.get(1), 100, 40000,true);
                break;
            case "craftingLensYellow":
                GT_Values.RA.addLaserEngraverRecipe(MatUnifier.get(OrePrefixes.block, Materials.Iron, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 14), 2000, 1920);
                GT_Values.RA.addLaserEngraverRecipe(MatUnifier.get(OrePrefixes.block, Materials.WroughtIron, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 14), 2000, 1920);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer3.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_SoC.get(1), 200, 1920,true);
                break;
            case "craftingLensOrange":
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer3.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_SoC2.get(1), 200, 1920,true);

                break;
            case "craftingLensCyan":
                GT_Values.RA.addLaserEngraverRecipe(MatUnifier.get(OrePrefixes.block, Materials.Iron, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 15), 2000, 1920);
                GT_Values.RA.addLaserEngraverRecipe(MatUnifier.get(OrePrefixes.block, Materials.WroughtIron, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 15), 2000, 1920);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_Ram.get(1), 900, 120,false);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer2.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_Ram.get(4), 500, 480,true);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer3.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_Ram.get(8), 200, 1920,true);

                break;
            case "craftingLensRed":
                GT_Values.RA.addLaserEngraverRecipe(MatUnifier.get(OrePrefixes.plate, Materials.Redstone, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("BuildCraft|Silicon", "redstoneChipset", 1L, 0), 50, 120);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_ILC.get(1), 900, 120,false);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer2.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_ILC.get(4), 500, 480,true);
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Silicon_Wafer3.get(1), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Wafer_ILC.get(8), 200, 1920,true);
                break;
            case "craftingLensGreen":
                GT_Values.RA.addLaserEngraverRecipe(ItemList.Circuit_Parts_Crystal_Chip_Elite.get(1L), GT_Utility.copyAmount(0L, aStack), ItemList.Circuit_Chip_CrystalCPU.get(1), 100, 10000,true);
                break;
            case "craftingLensWhite":
                GT_Values.RA.addLaserEngraverRecipe(MatUnifier.get(OrePrefixes.block, Materials.Iron, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 19), 2000, 1920);
                GT_Values.RA.addLaserEngraverRecipe(MatUnifier.get(OrePrefixes.block, Materials.WroughtIron, 1L), GT_Utility.copyAmount(0L, aStack), GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1L, 19), 2000, 1920);

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
            MatUnifier.registerOre("craftingLens" + aMaterial.mColor.toString().replaceFirst("dye", ""), aEvent.Ore);
        }*/

        int tAmount = OrePrefixes.ingot.mMaterialAmount / 3628800;
        if (tAmount > 0 && tAmount <= 64 && OrePrefixes.ingot.mMaterialAmount % 3628800 == 0) {
            ItemStack aGlassDust = GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glass);
            GT_Values.RA.addExtruderRecipe(aGlassDust, ItemList.Shape_Extruder_Bottle.get(0), new ItemStack(Items.glass_bottle, 1), tAmount * 32, 16);
            GT_Values.RA.addAlloySmelterRecipe(aGlassDust, ItemList.Shape_Mold_Bottle.get(0), new ItemStack(Items.glass_bottle, 1), tAmount * 64, 4);

            GT_Values.RA.addExtruderRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 2), ItemList.Shape_Extruder_Cell.get(0), ItemList.Cell_Empty.get(tAmount), tAmount * 128, 32);
            GT_Values.RA.addExtruderRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Polytetrafluoroethylene, 2), ItemList.Shape_Extruder_Cell.get(0), ItemList.Cell_Empty.get(tAmount), tAmount * 128, 32);
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

        GT_RecipeRegistrator.registerUsagesForMaterials(new ItemStack(Blocks.planks, 1), null, false);
        GT_RecipeRegistrator.registerUsagesForMaterials(new ItemStack(Blocks.cobblestone, 1), null, false);
        GT_RecipeRegistrator.registerUsagesForMaterials(new ItemStack(Blocks.stone, 1), null, false);
        GT_RecipeRegistrator.registerUsagesForMaterials(new ItemStack(Items.leather, 1), null, false);

        if (!GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.storageblockcrafting, "tile.glowstone", false)) {
            GT_ModHandler.removeRecipe(new ItemStack(Items.glowstone_dust, 1), new ItemStack(Items.glowstone_dust, 1), null, new ItemStack(Items.glowstone_dust, 1), new ItemStack(Items.glowstone_dust, 1));
        }
        GT_ModHandler.removeRecipe(new ItemStack(Blocks.wooden_slab, 1, 0), new ItemStack(Blocks.wooden_slab, 1, 1), new ItemStack(Blocks.wooden_slab, 1, 2));
        GT_ModHandler.addBasicShapedRecipe(new ItemStack(Blocks.wooden_slab, 6, 0), "WWW", "   ", "   ", 'W', new ItemStack(Blocks.planks, 1, 0));
    }
}
