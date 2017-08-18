package gregtech.loaders.oreprocessing;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.objects.MaterialStack;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.item.ItemStack;

public class ProcessingFood implements gregtech.api.interfaces.IOreRecipeRegistrator {
    public ProcessingFood() {
        OrePrefixes.food.add(this);
    }

    public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
        switch (aOreDictName) {
            case "foodCheese":
                GT_OreDictUnificator.addItemData(aStack, new gregtech.api.objects.ItemData(Materials.Cheese, 3628800L, new MaterialStack[0]));
                break;
            case "foodDough":
                GT_ModHandler.removeFurnaceSmelting(aStack);
        }
    }
}
