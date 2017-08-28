package gregtech.loaders.oreprocessing;

import gregtech.api.GregTech_API;
import gregtech.api.enums.*;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingBlock implements gregtech.api.interfaces.IOreRecipeRegistrator {
    public ProcessingBlock() {
        OrePrefixes.block.add(this);
    }

    public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
        GT_Values.RA.addCutterRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 9L), null, (int) Math.max(aMaterial.getMass() * 10L, 1L), 30);

        ItemStack aIngotStack = GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L);
        ItemStack aGemStack = GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 1L);
        ItemStack aDustStack = GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1L);

        GT_ModHandler.removeRecipe(aStack);

        if (aIngotStack != null)
            GT_ModHandler.removeRecipe(aIngotStack, aIngotStack, aIngotStack, aIngotStack, aIngotStack, aIngotStack, aIngotStack, aIngotStack, aIngotStack);
        if (aGemStack != null)
            GT_ModHandler.removeRecipe(aGemStack, aGemStack, aGemStack, aGemStack, aGemStack, aGemStack, aGemStack, aGemStack, aGemStack);
        if (aDustStack != null) {
            GT_ModHandler.removeRecipe(aDustStack, aDustStack, aDustStack, aDustStack, aDustStack, aDustStack, aDustStack, aDustStack, aDustStack);
        }
        if (aMaterial.mStandardMoltenFluid != null) {
            GT_Values.RA.addFluidSolidifierRecipe(ItemList.Shape_Mold_Block.get(0L), aMaterial.getMolten(1296L), aStack, 288, 8);
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

        GT_Values.RA.addForgeHammerRecipe(aStack, GT_Utility.copyAmount(9L, aGemStack), 100, 24);

        if (GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.storageblockdecrafting, OrePrefixes.block.get(aMaterial).toString(), aGemStack != null)) {
            if (aDustStack != null)
                GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(9L, aDustStack), new Object[]{aStack});
            if (aGemStack != null)
                GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(9L, aGemStack), new Object[]{aStack});
            if (aIngotStack != null) {
                GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(9L, aIngotStack), new Object[]{aStack});
            }
        }
        if (!OrePrefixes.block.isIgnored(aMaterial))
            GT_ModHandler.addCompressionRecipe(GT_Utility.copyAmount(9L, aIngotStack), aStack);
        switch (aMaterial.mName) {
            case "Iron":
            case "WroughtIron":
                GT_Values.RA.addExtruderRecipe(aStack, ItemList.Shape_Extruder_Rod.get(0L), ItemList.IC2_ShaftIron.get(1L), 640, 120);
                GT_Values.RA.addAssemblerRecipe(ItemList.IC2_Compressed_Coal_Ball.get(8L), aStack, ItemList.IC2_Compressed_Coal_Chunk.get(1L), 400, 4);
                break;
            case "Steel":
                GT_Values.RA.addExtruderRecipe(aStack, ItemList.Shape_Extruder_Rod.get(0L), ItemList.IC2_ShaftSteel.get(1L), 1280, 120);
                GT_Values.RA.addAssemblerRecipe(ItemList.IC2_Compressed_Coal_Ball.get(8L), aStack, ItemList.IC2_Compressed_Coal_Chunk.get(1L), 400, 4);
        }
    }
}
