package gregtech.loaders.oreprocessing;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;

public class ProcessingCrop implements gregtech.api.interfaces.IOreRecipeRegistrator {
    public ProcessingCrop() {
        OrePrefixes.crop.add(this);
    }

    public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, net.minecraft.item.ItemStack aStack) {
        GT_ModHandler.addCompressionRecipe(gregtech.api.util.GT_Utility.copyAmount(8L, aStack), ItemList.IC2_PlantballCompressed.get(1L));
    }
}
