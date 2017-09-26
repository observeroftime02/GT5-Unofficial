package gregtech.common.items;

import gregtech.api.GregTech_API;
import gregtech.api.enums.*;
import gregtech.api.enums.TC_Aspects.TC_AspectStack;
import gregtech.api.items.GT_MetaGenerated_Item_X32;
import gregtech.api.util.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;

public class GT_MetaGenerated_Item_02 extends GT_MetaGenerated_Item_X32 {
    public static GT_MetaGenerated_Item_02 INSTANCE;

    public GT_MetaGenerated_Item_02() {
        super("metaitem.02", OrePrefixes.toolHeadSword, OrePrefixes.toolHeadPickaxe, OrePrefixes.toolHeadShovel, OrePrefixes.toolHeadAxe, OrePrefixes.toolHeadHoe, OrePrefixes.toolHeadHammer, OrePrefixes.toolHeadFile, OrePrefixes.toolHeadSaw, OrePrefixes.toolHeadDrill, OrePrefixes.toolHeadChainsaw, OrePrefixes.toolHeadWrench, OrePrefixes.toolHeadUniversalSpade, OrePrefixes.toolHeadSense, OrePrefixes.toolHeadPlow, null, OrePrefixes.toolHeadBuzzSaw, OrePrefixes.turbineBlade, null, null, OrePrefixes.wireFine, OrePrefixes.gearGtSmall, OrePrefixes.rotor, null, null, OrePrefixes.spring, null, null, OrePrefixes.gemChipped, OrePrefixes.gemFlawed, OrePrefixes.gemFlawless, OrePrefixes.gemExquisite, OrePrefixes.gearGt);
        INSTANCE = this;

        int tLastID = 0;

        ItemList.GelledToluene.set(addItem(10, "Gelled Toluene", "Raw Explosive"));
        ItemList.Bottle_Purple_Drink.set(addItem(100, "Purple Drink", "How about Lemonade. Or some Ice Tea? I got Purple Drink!", new GT_FoodStat(8, 0.2F, EnumAction.drink, new ItemStack(Items.glass_bottle, 1), GregTech_API.sDrinksAlwaysDrinkable, false, false, Potion.moveSlowdown.id, 400, 1, 90), new TC_AspectStack(TC_Aspects.VITREUS, 1), new TC_AspectStack(TC_Aspects.AQUA, 1), new TC_AspectStack(TC_Aspects.VINCULUM, 1)));

        ItemList.Dye_Indigo.set(addItem(410, "Indigo Dye", "Blue Dye", new TC_AspectStack(TC_Aspects.SENSUS, 1), Dyes.dyeBlue));
        for (byte i = 0; i < 16; i = (byte) (i + 1)) {
            ItemList.DYE_ONLY_ITEMS[i].set(addItem(414 + i, Dyes.get(i).mName + " Dye", "", Dyes.get(i).name(), new TC_AspectStack(TC_Aspects.SENSUS, 1)));
        }

        ItemList.Bottle_Dragon_Blood.set(addItem(114, "Dragon Blood", "FUS RO DAH!", SubTag.INVISIBLE, new GT_FoodStat(4, 0.4F, EnumAction.drink, new ItemStack(Items.glass_bottle, 1), GregTech_API.sDrinksAlwaysDrinkable, false, false, Potion.confusion.id, 300, 2, 90, Potion.damageBoost.id, 300, 2, 90, Potion.poison.id, 200, 2, 10, Potion.harm.id, 0, 2, 5), new TC_AspectStack(TC_Aspects.VITREUS, 1), new TC_AspectStack(TC_Aspects.VENENUM, 2), new TC_AspectStack(TC_Aspects.POTENTIA, 2)));

        ItemList.SFMixture.set(addItem(270, "Super Fuel Binder", "Raw Material"));
        ItemList.MSFMixture.set(addItem(271, "Magic Super Fuel Binder", "Raw Material"));

        ItemList.Crop_Drop_Plumbilia.set(addItem(500, "Plumbilia Leaf", "Source of Lead", new TC_AspectStack(TC_Aspects.MESSIS, 1), new TC_AspectStack(TC_Aspects.METALLUM, 1), new TC_AspectStack(TC_Aspects.ORDO, 1)));
        ItemList.Crop_Drop_Argentia.set(addItem(501, "Argentia Leaf", "Source of Silver", new TC_AspectStack(TC_Aspects.MESSIS, 1), new TC_AspectStack(TC_Aspects.METALLUM, 1), new TC_AspectStack(TC_Aspects.LUCRUM, 1)));
        ItemList.Crop_Drop_Indigo.set(addItem(502, "Indigo Blossom", "Used for making Blue Dye", new TC_AspectStack(TC_Aspects.MESSIS, 1), new TC_AspectStack(TC_Aspects.SENSUS, 1)));
        ItemList.Crop_Drop_Ferru.set(addItem(503, "Ferru Leaf", "Source of Iron", new TC_AspectStack(TC_Aspects.MESSIS, 1), new TC_AspectStack(TC_Aspects.METALLUM, 2)));
        ItemList.Crop_Drop_Aurelia.set(addItem(504, "Aurelia Leaf", "Source of Gold", new TC_AspectStack(TC_Aspects.MESSIS, 1), new TC_AspectStack(TC_Aspects.METALLUM, 1), new TC_AspectStack(TC_Aspects.LUCRUM, 1)));

        ItemList.Crop_Drop_OilBerry.set(addItem(510, "Oil Berry", "Oil in Berry form", new TC_AspectStack(TC_Aspects.MESSIS, 1), new TC_AspectStack(TC_Aspects.AQUA, 1), new TC_AspectStack(TC_Aspects.POTENTIA, 1)));
        ItemList.Crop_Drop_BobsYerUncleRanks.set(addItem(511, "Bobs-Yer-Uncle-Berry", "Source of Emeralds", new TC_AspectStack(TC_Aspects.MESSIS, 1), new TC_AspectStack(TC_Aspects.VITREUS, 1), new TC_AspectStack(TC_Aspects.LUCRUM, 1)));
        ItemList.Crop_Drop_UUMBerry.set(addItem(512, "UUM Berry", "UUM in Berry form", new TC_AspectStack(TC_Aspects.MESSIS, 1), new TC_AspectStack(TC_Aspects.AQUA, 1), new TC_AspectStack(TC_Aspects.POTENTIA, 1)));
        ItemList.Crop_Drop_UUABerry.set(addItem(513, "UUA Berry", "UUA in Berry form", new TC_AspectStack(TC_Aspects.MESSIS, 1), new TC_AspectStack(TC_Aspects.AQUA, 1), new TC_AspectStack(TC_Aspects.POTENTIA, 1)));

        ItemList.Crop_Drop_MilkWart.set(addItem(520, "Milk Wart", "Source of Milk", new TC_AspectStack(TC_Aspects.MESSIS, 1), new TC_AspectStack(TC_Aspects.AQUA, 1), new TC_AspectStack(TC_Aspects.SANO, 1)));

        ItemList.Crop_Drop_Coppon.set(addItem(530, "Coppon Fiber", "ORANGE WOOOOOOOL!!!", new TC_AspectStack(TC_Aspects.MESSIS, 1), new TC_AspectStack(TC_Aspects.METALLUM, 1), new TC_AspectStack(TC_Aspects.PERMUTATIO, 1)));

        ItemList.Crop_Drop_Tine.set(addItem(tLastID = 540, "Tine Twig", "Source of Tin", new TC_AspectStack(TC_Aspects.MESSIS, 1), new TC_AspectStack(TC_Aspects.METALLUM, 1), new TC_AspectStack(TC_Aspects.ARBOR, 1)));
        setBurnValue(32000 + tLastID, 100);

        ItemList.Crop_Drop_Bauxite.set(addItem(521, "Bauxia Leaf", "Source of Aluminium"));
        ItemList.Crop_Drop_Ilmenite.set(addItem(522, "Titania Leaf", "Source of Titanium"));
        ItemList.Crop_Drop_Pitchblende.set(addItem(523, "Reactoria Leaf", "Source of Uranium"));
        ItemList.Crop_Drop_Uraninite.set(addItem(524, "Uranium Leaf", "Source of Uranite"));
        ItemList.Crop_Drop_Thorium.set(addItem(526, "Thunder Leaf", "Source of Thorium"));
        ItemList.Crop_Drop_Nickel.set(addItem(527, "Nickelback Leaf", "Source of Nickel"));
        ItemList.Crop_Drop_Zinc.set(addItem(528, "Galvania Leaf", "Source of Zinc"));
        ItemList.Crop_Drop_Manganese.set(addItem(529, "Pyrolusium Leaf", "Source of Manganese"));
        ItemList.Crop_Drop_Scheelite.set(addItem(531, "Scheelinium Leaf", "Source of Tungsten"));
        ItemList.Crop_Drop_Platinum.set(addItem(532, "Platina Leaf", "Source of Platinum"));
        ItemList.Crop_Drop_Iridium.set(addItem(533, "Quantaria Leaf", "Source of Iridium"));
        ItemList.Crop_Drop_Osmium.set(addItem(534, "Quantaria Leaf", "Source of Osmium"));
        ItemList.Crop_Drop_Naquadah.set(addItem(535, "Stargatium Leaf", "Source of Naquadah"));

        GT_ModHandler.addExtractionRecipe(new ItemStack(Blocks.red_flower, 1, 0), new ItemStack(Items.dye, 2, 1));
        GT_ModHandler.addExtractionRecipe(new ItemStack(Blocks.red_flower, 1, 1), new ItemStack(Items.dye, 2, 12));
        GT_ModHandler.addExtractionRecipe(new ItemStack(Blocks.red_flower, 1, 2), new ItemStack(Items.dye, 2, 13));
        GT_ModHandler.addExtractionRecipe(new ItemStack(Blocks.red_flower, 1, 3), new ItemStack(Items.dye, 2, 7));
        GT_ModHandler.addExtractionRecipe(new ItemStack(Blocks.red_flower, 1, 4), new ItemStack(Items.dye, 2, 1));
        GT_ModHandler.addExtractionRecipe(new ItemStack(Blocks.red_flower, 1, 5), new ItemStack(Items.dye, 2, 14));
        GT_ModHandler.addExtractionRecipe(new ItemStack(Blocks.red_flower, 1, 6), new ItemStack(Items.dye, 2, 7));
        GT_ModHandler.addExtractionRecipe(new ItemStack(Blocks.red_flower, 1, 7), new ItemStack(Items.dye, 2, 9));
        GT_ModHandler.addExtractionRecipe(new ItemStack(Blocks.red_flower, 1, 8), new ItemStack(Items.dye, 2, 7));
        GT_ModHandler.addExtractionRecipe(new ItemStack(Blocks.yellow_flower, 1, 0), new ItemStack(Items.dye, 2, 11));
        GT_ModHandler.addExtractionRecipe(new ItemStack(Blocks.double_plant, 1, 0), new ItemStack(Items.dye, 3, 11));
        GT_ModHandler.addExtractionRecipe(new ItemStack(Blocks.double_plant, 1, 1), new ItemStack(Items.dye, 3, 13));
        GT_ModHandler.addExtractionRecipe(new ItemStack(Blocks.double_plant, 1, 4), new ItemStack(Items.dye, 3, 1));
        GT_ModHandler.addExtractionRecipe(new ItemStack(Blocks.double_plant, 1, 5), new ItemStack(Items.dye, 3, 9));
        GT_ModHandler.addExtractionRecipe(ItemList.Crop_Drop_Plumbilia.get(1), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Lead));
        GT_ModHandler.addExtractionRecipe(ItemList.Crop_Drop_Argentia.get(1), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Silver));
        GT_ModHandler.addExtractionRecipe(ItemList.Crop_Drop_Indigo.get(1), ItemList.Dye_Indigo.get(1));
        GT_ModHandler.addExtractionRecipe(ItemList.Crop_Drop_Coppon.get(1), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Copper));
        GT_ModHandler.addExtractionRecipe(ItemList.Crop_Drop_Tine.get(1), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Tin));

        GT_ModHandler.addCompressionRecipe(ItemList.Crop_Drop_Coppon.get(4), new ItemStack(Blocks.wool, 1, 1));
        /*TODO
        GT_ModHandler.addCompressionRecipe(ItemList.Crop_Drop_Plumbilia.get(8), ItemList.IC2_PlantballCompressed.get(1));
        GT_ModHandler.addCompressionRecipe(ItemList.Crop_Drop_Argentia.get(8), ItemList.IC2_PlantballCompressed.get(1));
        GT_ModHandler.addCompressionRecipe(ItemList.Crop_Drop_Indigo.get(8), ItemList.IC2_PlantballCompressed.get(1));
        GT_ModHandler.addCompressionRecipe(ItemList.Crop_Drop_Ferru.get(8), ItemList.IC2_PlantballCompressed.get(1));
        GT_ModHandler.addCompressionRecipe(ItemList.Crop_Drop_Aurelia.get(8), ItemList.IC2_PlantballCompressed.get(1));
        GT_ModHandler.addCompressionRecipe(ItemList.Crop_Drop_OilBerry.get(8), ItemList.IC2_PlantballCompressed.get(1));
        GT_ModHandler.addCompressionRecipe(ItemList.Crop_Drop_BobsYerUncleRanks.get(8), ItemList.IC2_PlantballCompressed.get(1));
        GT_ModHandler.addCompressionRecipe(ItemList.Crop_Drop_Tine.get(4), ItemList.IC2_PlantballCompressed.get(1));
        GT_ModHandler.addCompressionRecipe(new ItemStack(Blocks.red_flower, 8, 32767), ItemList.IC2_PlantballCompressed.get(1));
        GT_ModHandler.addCompressionRecipe(new ItemStack(Blocks.yellow_flower, 8, 32767), ItemList.IC2_PlantballCompressed.get(1));*/

        GT_ModHandler.addPulverisationRecipe(ItemList.Crop_Drop_Tine.get(1), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Wood, 2));
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.reeds, 1), new ItemStack(Items.sugar, 1), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.melon_block, 1, 0), new ItemStack(Items.melon, 8, 0), new ItemStack(Items.melon_seeds, 1), 80, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.pumpkin, 1, 0), new ItemStack(Items.pumpkin_seeds, 4, 0), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.melon, 1, 0), new ItemStack(Items.melon_seeds, 1, 0), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.stick, 1), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Wood, 2), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.wool, 1, 32767), new ItemStack(Items.string, 2), new ItemStack(Items.string, 1), 50, false);
        
        ItemList.Display_ITS_FREE.set(addItem(766, "ITS FREE", "(or at least almost free)", SubTag.INVISIBLE, new TC_AspectStack(TC_Aspects.LUCRUM, 1)));
    }

    @Override
    public boolean isItemStackUsable(ItemStack aStack) {
        int aDamage = aStack.getItemDamage();
        Materials aMaterial = GregTech_API.sGeneratedMaterials[(aDamage % 1000)];
        if ((aDamage >= 25000) && (aDamage < 27000) && (aMaterial != null) && (aMaterial.mEnchantmentTools != null)) {
            Enchantment tEnchant = aMaterial.mEnchantmentTools == Enchantment.fortune ? Enchantment.looting : aMaterial.mEnchantmentTools;
            if (tEnchant.type == EnumEnchantmentType.weapon) {
                NBTTagCompound tNBT = GT_Utility.ItemNBT.getNBT(aStack);
                if (!tNBT.getBoolean("GT.HasBeenUpdated")) {
                    tNBT.setBoolean("GT.HasBeenUpdated", true);
                    GT_Utility.ItemNBT.setNBT(aStack, tNBT);
                    GT_Utility.ItemNBT.addEnchantment(aStack, tEnchant, aMaterial.mEnchantmentToolsLevel);
                }
            }
        }
        return super.isItemStackUsable(aStack);
    }

    @Override
    public boolean doesShowInCreative(OrePrefixes aPrefix, Materials aMaterial, boolean aDoShowAllItems) {
        return (aDoShowAllItems) || (!aPrefix.name().startsWith("toolHead"));
    }

    @Override
    public final ItemStack getContainerItem(ItemStack aStack) {
        int aDamage = aStack.getItemDamage();
        if (aDamage < 32000) {
            return null;
        }
        return null;
    }
}
