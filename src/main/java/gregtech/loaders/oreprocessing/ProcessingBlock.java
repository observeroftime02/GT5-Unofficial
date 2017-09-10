package gregtech.loaders.oreprocessing;

import gregtech.api.GregTech_API;
import gregtech.api.enums.*;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.MatUnifier;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingBlock implements gregtech.api.interfaces.IOreRecipeRegistrator {
    public ProcessingBlock() {
        OrePrefixes.block.add(this);
    }

    public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
        ItemStack aIngotStack = MatUnifier.get(OrePrefixes.ingot, aMaterial);
        ItemStack aGemStack = MatUnifier.get(OrePrefixes.gem, aMaterial);
        ItemStack aDustStack = MatUnifier.get(OrePrefixes.dust, aMaterial);

        GT_ModHandler.removeRecipe(aStack);

        if (aIngotStack != null)
            GT_ModHandler.removeRecipe(aIngotStack, aIngotStack, aIngotStack, aIngotStack, aIngotStack, aIngotStack, aIngotStack, aIngotStack, aIngotStack);
        if (aGemStack != null)
            GT_ModHandler.removeRecipe(aGemStack, aGemStack, aGemStack, aGemStack, aGemStack, aGemStack, aGemStack, aGemStack, aGemStack);
        if (aDustStack != null) {
            GT_ModHandler.removeRecipe(aDustStack, aDustStack, aDustStack, aDustStack, aDustStack, aDustStack, aDustStack, aDustStack, aDustStack);
        }

        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.storageblockcrafting, aStack.toString(), false)) {
            if ((aIngotStack == null) && (aGemStack == null) && (aDustStack != null))
                GT_ModHandler.addCraftingRecipe(aStack, new Object[]{"XXX", "XXX", "XXX", 'X', aDustStack});
            if (aGemStack != null)
                GT_ModHandler.addCraftingRecipe(aStack, new Object[]{"XXX", "XXX", "XXX", 'X', aGemStack});
            if (aIngotStack != null) {
                GT_ModHandler.addCraftingRecipe(aStack, new Object[]{"XXX", "XXX", "XXX", 'X', aIngotStack});
            }
        }

        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.storageblockdecrafting, OrePrefixes.block.get(aMaterial).toString(), aGemStack != null)) {
            if (aDustStack != null)
                GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(9, aDustStack), new Object[]{aStack});
            if (aGemStack != null)
                GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(9, aGemStack), new Object[]{aStack});
            if (aIngotStack != null) {
                GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(9, aIngotStack), new Object[]{aStack});
            }
        }

    }
}
