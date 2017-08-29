package gregtech.loaders.oreprocessing;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.MatUnifier;
import net.minecraft.item.ItemStack;

public class ProcessingSaplings implements gregtech.api.interfaces.IOreRecipeRegistrator {
    public ProcessingSaplings() {
        OrePrefixes.treeSapling.add(this);
    }

    public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
        GT_ModHandler.addPulverisationRecipe(aStack, MatUnifier.get(OrePrefixes.dustSmall, Materials.Wood, 2), null, 0, false);
        GT_Values.RA.addLatheRecipe(aStack, MatUnifier.get(OrePrefixes.stick, Materials.Wood), MatUnifier.get(OrePrefixes.dustTiny, Materials.Wood), 16, 8);
    }
}
