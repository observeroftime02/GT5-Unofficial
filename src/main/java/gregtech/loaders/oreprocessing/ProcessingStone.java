package gregtech.loaders.oreprocessing;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.IOreRecipeRegistrator;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ProcessingStone
        implements IOreRecipeRegistrator {
    public ProcessingStone() {
        OrePrefixes.stone.add(this);
        OrePrefixes.stoneCobble.add(this);
    }

    public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
        switch (aPrefix) {
            case stone:
                Block aBlock = GT_Utility.getBlockFromStack(aStack);
                switch (aMaterial.mName) {
                    case "NULL":
                        GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(3, aStack), new ItemStack(Blocks.redstone_torch, 2), Materials.Redstone.getMolten(144), new ItemStack(Items.repeater, 1), 100, 4);
                        break;
                    case "Sand":
                        GT_ModHandler.addPulverisationRecipe(aStack, new ItemStack(Blocks.sand, 1, 0), null, 10, false);
                        break;
                    case "Endstone":
                        GT_ModHandler.addPulverisationRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.dustImpure, Materials.Endstone, 1), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Tungsten, 1), 5, false);
                        break;
                    case "Netherrack":
                        GT_ModHandler.addPulverisationRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.dustImpure, Materials.Netherrack, 1), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Gold, 1), 5, false);
                        break;
                    case "NetherBrick":
                        GT_Values.RA.addAssemblerRecipe(aStack, ItemList.Circuit_Integrated.getWithDamage(0, 1), new ItemStack(Blocks.nether_brick_fence, 1), 100, 4);
                        break;
                    case "Obsidian":
                        if (aBlock != Blocks.air) aBlock.setResistance(20.0F);
                        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 2), GT_Utility.copyAmount(5, aStack), Materials.Glass.getMolten(72), GT_ModHandler.getModItem("Forestry", "thermionicTubes", 4, 6), 64, 32);
                        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.NetherStar, 1), GT_Utility.copyAmount(3, aStack), Materials.Glass.getMolten(720), new ItemStack(Blocks.beacon, 1, 0), 32, 16);
                        GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(8, aStack), GT_OreDictUnificator.get(OrePrefixes.gem, Materials.EnderEye, 1), new ItemStack(Blocks.ender_chest, 1), 400, 4);
                        GT_Values.RA.addCutterRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1), null, 200, 32);
                        break;
                    case "Concrete":
                        GT_Values.RA.addCutterRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1), null, 100, 32);
                        GT_ModHandler.addPulverisationRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1));
                        break;
                    case "Marble":
                    case "Basalt":
                    case "Flint":
                        GT_ModHandler.addPulverisationRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 2), new ItemStack(Items.flint, 1), 50, false);
                        break;
                    case "GraniteBlack":
                        GT_Values.RA.addCutterRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1), null, 200, 32);
                        GT_ModHandler.addPulverisationRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Thorium, 1), 1, false);
                        break;
                    case "GraniteRed":
                        GT_Values.RA.addCutterRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1), null, 200, 32);
                        GT_ModHandler.addPulverisationRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 1), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Uranium, 1), 1, false);
                }
            case stoneCobble:
                GT_Values.RA.addAssemblerRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1), new ItemStack(Blocks.lever, 1), 400, 1);
                GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(8, aStack), ItemList.Circuit_Integrated.getWithDamage(0, 8), new ItemStack(Blocks.furnace, 1), 400, 4);
                GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(7, aStack), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1), new ItemStack(Blocks.dropper, 1), 400, 4);
                GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(7, aStack), new ItemStack(Items.bow, 1, 0), Materials.Redstone.getMolten(144), new ItemStack(Blocks.dispenser, 1), 400, 4);

        }
    }
}
