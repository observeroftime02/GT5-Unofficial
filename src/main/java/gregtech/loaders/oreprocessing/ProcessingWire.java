package gregtech.loaders.oreprocessing;

import appeng.api.config.TunnelType;
import appeng.core.Api;
import gregtech.GT_Mod;
import gregtech.api.enums.*;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import gregtech.common.GT_Proxy;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class ProcessingWire implements gregtech.api.interfaces.IOreRecipeRegistrator {

	private Materials[] dielectrics = {Materials.PolyvinylChloride, Materials.Polydimethylsiloxane};
	private Materials[] rubbers = {Materials.Rubber, Materials.StyreneButadieneRubber, Materials.Silicone};
	private Materials[] syntheticRubbers = {Materials.StyreneButadieneRubber, Materials.Silicone};
	
	public ProcessingWire() {
        OrePrefixes.wireGt01.add(this);
        OrePrefixes.wireGt02.add(this);
        OrePrefixes.wireGt04.add(this);
        OrePrefixes.wireGt08.add(this);
        OrePrefixes.wireGt12.add(this);
        OrePrefixes.wireGt16.add(this);
    }

    public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {

        
        int costMultiplier = cableWidth / 4 + 1;
        
        switch (aMaterial.mName) {
            case "RedAlloy":
                ArrayList<Object> craftingListPaper = new ArrayList<Object>();
                craftingListPaper.add(aOreDictName);
                for (int i = 0; i < costMultiplier; i++) {
                    craftingListPaper.add(OrePrefixes.plate.get(Materials.Paper));
                }
                GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(correspondingCable, aMaterial, 1L), craftingListPaper.toArray());
                GT_Values.RA.addBoxingRecipe(GT_Utility.copyAmount(1L, aStack), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Paper, costMultiplier), GT_OreDictUnificator.get(correspondingCable, aMaterial, 1L), 100, 8);
            case "Cobalt": case "Lead": case "Tin": case "Zinc":case "SolderingAlloy":
                ArrayList<Object> craftingListCarpet = new ArrayList<Object>();
                craftingListCarpet.add(aOreDictName);
                for (int i = 0; i < costMultiplier; i++) {
                    craftingListCarpet.add(new ItemStack(Blocks.carpet, 1, 15));
                }
                craftingListCarpet.add(new ItemStack(Items.string, 1));
                GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(correspondingCable, aMaterial, 1L), craftingListCarpet.toArray());
                GT_Values.RA.addBoxingRecipe(GT_Utility.copyAmount(1L, aStack), new ItemStack(Blocks.carpet, costMultiplier, 15), GT_OreDictUnificator.get(correspondingCable, aMaterial, 1L), 100, 8);
            case "Iron": case "Nickel": case "Cupronickel": case "Copper": case "AnnealedCopper":
            case "Kanthal": case "Gold": case "Electrum": case "Silver": case "Blue Alloy":
                for (Materials rubber : rubbers) {
                    GT_Values.RA.addAssemblerRecipe(aStack, ItemList.Circuit_Integrated.getWithDamage(0L, 24L), rubber.getMolten(144 * costMultiplier), GT_OreDictUnificator.get(correspondingCable, aMaterial, 1L), 100, 8);
                }
                for (Materials dielectric : dielectrics) {
                    for (Materials syntheticRubber : syntheticRubbers) {
                        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{aStack, dielectric.getDustSmall(costMultiplier)}, syntheticRubber.getMolten(costMultiplier * 36), GT_OreDictUnificator.get(correspondingCable, aMaterial, 1L), 100, 8);
                    }
                }
                break;
            default:
                if (GT_Mod.gregtechproxy.mEasierEVPlusCables) {
                    for (Materials rubber : rubbers) {
                        GT_Values.RA.addAssemblerRecipe(aStack, ItemList.Circuit_Integrated.getWithDamage(0L, 24L), rubber.getMolten(144 * costMultiplier), GT_OreDictUnificator.get(correspondingCable, aMaterial, 1L), 100, 8);
                    }
                    for (Materials dielectric : dielectrics) {
                        for (Materials syntheticRubber : syntheticRubbers) {
                            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{aStack, dielectric.getDustSmall(costMultiplier)}, syntheticRubber.getMolten(costMultiplier * 36), GT_OreDictUnificator.get(correspondingCable, aMaterial, 1L), 100, 8);
                        }
                    }
                } else {
                    for (Materials dielectric : dielectrics) {
                        for (Materials syntheticRubber : syntheticRubbers) {
                            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{aStack, dielectric.getDustSmall(costMultiplier), GT_OreDictUnificator.get(OrePrefixes.foil, aMaterial, costMultiplier)}, syntheticRubber.getMolten(costMultiplier * 36), GT_OreDictUnificator.get(correspondingCable, aMaterial, 1L), 100, 8);
                            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{aStack, dielectric.getDustSmall(costMultiplier), GT_OreDictUnificator.get(OrePrefixes.foil, Materials.PolyphenyleneSulfide, costMultiplier)}, syntheticRubber.getMolten(costMultiplier * 36), GT_OreDictUnificator.get(correspondingCable, aMaterial, 1L), 100, 8);
                        }
                    }
                }
                break;
        }
        GT_Values.RA.addUnboxingRecipe(GT_OreDictUnificator.get(correspondingCable, aMaterial, 1L), GT_Utility.copyAmount(1L, aStack), null, 100, 8);
        if (GT_Mod.gregtechproxy.mAE2Integration) {
            Api.INSTANCE.registries().p2pTunnel().addNewAttunement(aStack, TunnelType.IC2_POWER);
            Api.INSTANCE.registries().p2pTunnel().addNewAttunement(GT_OreDictUnificator.get(correspondingCable, aMaterial, 1L), TunnelType.IC2_POWER);
        }
    }    
}
