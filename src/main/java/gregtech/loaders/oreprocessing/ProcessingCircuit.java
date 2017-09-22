package gregtech.loaders.oreprocessing;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.item.ItemStack;

public class ProcessingCircuit implements gregtech.api.interfaces.IOreRecipeRegistrator {
    public ProcessingCircuit() {
        OrePrefixes.circuit.add(this);
    }

    public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
    	if (GT_OreDictUnificator.isBlacklisted(aStack) && aModName.equals("gregtech")) return;
        switch (aMaterial.mName) {
            case "Good":
            case "Data":
            case "Elite":
            case "Master":
            case "Ultimate":
                if (!GT_OreDictUnificator.isBlacklisted(aStack) && !aModName.equals("gregtech"))
                    GT_ModHandler.removeRecipeByOutput(aStack);
                break;
            case "Primitive":
                GT_ModHandler.removeRecipeByOutput(aStack);
               break;
            case "Basic":
                GT_ModHandler.removeRecipeByOutput(aStack);
                //TODO FIX GT_ModHandler.addCraftingRecipe(aStack, new Object[]{"RIR","VBV","CCC",'R',ItemList.Circuit_Parts_Resistor.get(1),'C', MatUnifier.get(OrePrefixes.cableGt01, Materials.RedAlloy, 1),'V', ItemList.Circuit_Parts_Vacuum_Tube.get(1),'B',ItemList.Circuit_Board_Coated.get(1),'I',ItemList.IC2_Item_Casing_Steel.get(1)});
                GT_ModHandler.addShapelessCraftingRecipe(ItemList.Circuit_Basic.get(1), ItemList.Circuit_Integrated.getWildcard(1));
               break;
            case "Advanced":
                GT_ModHandler.removeRecipeByOutput(aStack);
               break;
        }
    }
}
