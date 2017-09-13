package gregtech.loaders.oreprocessing;

import gregtech.GT_Mod;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.SubTag;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingOreSmelting implements gregtech.api.interfaces.IOreRecipeRegistrator {
	private final OrePrefixes[] mSmeltingPrefixes = {OrePrefixes.crushed, OrePrefixes.crushedPurified, OrePrefixes.crushedCentrifuged, OrePrefixes.dust, OrePrefixes.dustImpure, OrePrefixes.dustPure};

	public ProcessingOreSmelting() {
		for (OrePrefixes tPrefix : this.mSmeltingPrefixes) tPrefix.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
		GT_ModHandler.removeFurnaceSmelting(aStack);
		if (!aMaterial.contains(SubTag.NO_SMELTING)) {
			if ((aMaterial.mBlastFurnaceRequired) || (aMaterial.mDirectSmelting.mBlastFurnaceRequired)) {
				GT_Values.RA.addBlastRecipe(GT_Utility.copyAmount(1, aStack), null, null, null, aMaterial.mBlastFurnaceTemp > 1750 ? GT_OreDictUnificator.get(OrePrefixes.ingotHot, aMaterial, GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial), 1L) : GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial), null, (int) Math.max(aMaterial.getMass() / 4L, 1L) * aMaterial.mBlastFurnaceTemp, 120, aMaterial.mBlastFurnaceTemp);
			} else {
				OrePrefixes outputPrefix;
				int outputSize;
				switch (aPrefix) {
					case crushed:
					case crushedPurified:
					case crushedCentrifuged:
						if (aMaterial.mDirectSmelting == aMaterial) {
							outputSize = 10;
							outputPrefix = OrePrefixes.nugget;
						} else {
							if (GT_Mod.gregtechproxy.mMixedOreOnlyYieldsTwoThirdsOfPureOre) {
								outputSize = 6;
								outputPrefix = OrePrefixes.nugget;
							} else {
								outputSize = 1;
								outputPrefix = OrePrefixes.ingot;
							}
						}
						break;
					case dustImpure:
					case dustPure:
						if (aMaterial.mDirectSmelting == aMaterial) {
							outputPrefix = OrePrefixes.ingot;
							outputSize = 1;
						} else {
							if (GT_Mod.gregtechproxy.mMixedOreOnlyYieldsTwoThirdsOfPureOre) {
								outputSize = 6;
								outputPrefix = OrePrefixes.nugget;
							} else {
								outputSize = 1;
								outputPrefix = OrePrefixes.ingot;
							}
						}
						break;
					default:
						outputPrefix = OrePrefixes.ingot;
						outputSize = 1;
						break;
				}
				ItemStack tStack = GT_OreDictUnificator.get(outputPrefix, aMaterial.mDirectSmelting, outputSize);
				if (tStack == null)
					tStack = GT_OreDictUnificator.get(aMaterial.contains(SubTag.SMELTING_TO_GEM) ? OrePrefixes.gem : OrePrefixes.ingot, aMaterial.mDirectSmelting);
				if ((tStack == null) && (!aMaterial.contains(SubTag.SMELTING_TO_GEM)))
					tStack = GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mDirectSmelting);
				GT_ModHandler.addSmeltingRecipe(aStack, tStack);
			}
		}
	}
}