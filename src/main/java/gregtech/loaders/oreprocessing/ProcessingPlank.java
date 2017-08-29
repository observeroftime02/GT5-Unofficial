package gregtech.loaders.oreprocessing;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.MatUnifier;
import gregtech.api.util.GT_Utility;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ProcessingPlank implements gregtech.api.interfaces.IOreRecipeRegistrator {
    public ProcessingPlank() {
        OrePrefixes.plank.add(this);
    }

    public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
        if (aOreDictName.startsWith("plankWood")) {
            GT_Values.RA.addLatheRecipe(GT_Utility.copyAmount(1, aStack), MatUnifier.get(OrePrefixes.stick, Materials.Wood, 2), null, 10, 8);
            GT_Values.RA.addCNCRecipe(GT_Utility.copyAmount(4, aStack), MatUnifier.get(OrePrefixes.gearGt, Materials.Wood), 800, 1);
            GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(8, aStack), MatUnifier.get(OrePrefixes.dust, Materials.Redstone), new ItemStack(Blocks.noteblock, 1), 200, 4);
            GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(8, aStack), MatUnifier.get(OrePrefixes.gem, Materials.Diamond), new ItemStack(Blocks.jukebox, 1), 400, 4);
            GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(1, aStack), ItemList.Circuit_Integrated.getWithDamage(0, 1), new ItemStack(Blocks.wooden_button, 1), 100, 4);
            GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(2, aStack), ItemList.Circuit_Integrated.getWithDamage(0, 2), new ItemStack(Blocks.wooden_pressure_plate, 1), 200, 4);
            GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(3, aStack), ItemList.Circuit_Integrated.getWithDamage(0, 3), new ItemStack(Blocks.trapdoor, 1), 300, 4);
            GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(4, aStack), ItemList.Circuit_Integrated.getWithDamage(0, 4), new ItemStack(Blocks.crafting_table, 1), 400, 4);
            GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(6, aStack), ItemList.Circuit_Integrated.getWithDamage(0, 6), new ItemStack(Items.wooden_door, 1), 600, 4);
            GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(8, aStack), ItemList.Circuit_Integrated.getWithDamage(0, 8), new ItemStack(Blocks.chest, 1), 800, 4);
            GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(6, aStack), new ItemStack(Items.book, 3), new ItemStack(Blocks.bookshelf, 1), 400, 4);

            if (aStack.getItemDamage() == 32767) {
                for (byte i = 0; i < 64; i = (byte) (i + 1)) {
                    ItemStack tStack = GT_Utility.copyMetaData(i, aStack);
                    ItemStack tOutput = GT_ModHandler.getRecipeOutput(tStack, tStack, tStack);
                    if ((tOutput != null) && (tOutput.stackSize >= 3)) {
                        GT_Values.RA.addCutterRecipe(GT_Utility.copyAmount(1, tStack), GT_Utility.copyAmount(tOutput.stackSize / 3, tOutput), null, 25, 4);
                        GT_ModHandler.removeRecipe(tStack, tStack, tStack);
                        GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(tOutput.stackSize / 3, tOutput), new Object[]{"sP", Character.valueOf('P'), tStack});
                    }
                    if((tStack == null) && (i >= 16)) break;
                }
            } else {
                ItemStack tOutput = GT_ModHandler.getRecipeOutput(aStack, aStack, aStack);
                if ((tOutput != null) && (tOutput.stackSize >= 3)) {
                    GT_Values.RA.addCutterRecipe(GT_Utility.copyAmount(1, aStack), GT_Utility.copyAmount(tOutput.stackSize / 3, tOutput), null, 25, 4);
                    GT_ModHandler.removeRecipe(aStack, aStack, aStack);
                    GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(tOutput.stackSize / 3, tOutput), new Object[]{"sP", Character.valueOf('P'), aStack});
                }
            }
        }
    }
}
