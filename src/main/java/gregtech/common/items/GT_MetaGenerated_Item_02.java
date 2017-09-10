package gregtech.common.items;

import gregtech.api.GregTech_API;
import gregtech.api.enums.*;
import gregtech.api.items.GT_MetaGenerated_Item_X32;
import gregtech.api.util.*;
import gregtech.common.items.behaviors.Behaviour_Arrow;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

public class GT_MetaGenerated_Item_02 extends GT_MetaGenerated_Item_X32 {
    public static GT_MetaGenerated_Item_02 INSTANCE;

    public GT_MetaGenerated_Item_02() {
        super("metaitem.02", OrePrefixes.toolHeadSword, OrePrefixes.toolHeadPickaxe, OrePrefixes.toolHeadShovel, OrePrefixes.toolHeadAxe, OrePrefixes.toolHeadHoe, OrePrefixes.toolHeadHammer, OrePrefixes.toolHeadFile, OrePrefixes.toolHeadSaw, OrePrefixes.toolHeadDrill, OrePrefixes.toolHeadChainsaw, OrePrefixes.toolHeadWrench, OrePrefixes.toolHeadUniversalSpade, OrePrefixes.toolHeadSense, OrePrefixes.toolHeadPlow, null, OrePrefixes.toolHeadBuzzSaw, OrePrefixes.turbineBlade, null, null, OrePrefixes.wireFine, OrePrefixes.gearGtSmall, OrePrefixes.rotor, null, null, OrePrefixes.spring, null, null, OrePrefixes.gemChipped, OrePrefixes.gemFlawed, OrePrefixes.gemFlawless, OrePrefixes.gemExquisite, OrePrefixes.gearGt);
        INSTANCE = this;

        int tLastID = 0;

        ItemList.GelledToluene.set(addItem(tLastID = 10, "Gelled Toluene", "Raw Explosive", new Object[]{}));
        ItemList.Bottle_Purple_Drink.set(addItem(tLastID = 100, "Purple Drink", "How about Lemonade. Or some Ice Tea? I got Purple Drink!", new Object[]{new GT_FoodStat(8, 0.2F, EnumAction.drink, new ItemStack(Items.glass_bottle, 1), GregTech_API.sDrinksAlwaysDrinkable, false, false, Potion.moveSlowdown.id, 400, 1, 90), new Aspects.AspectStack(Aspects.VITREUS, 1L), new Aspects.AspectStack(Aspects.AQUA, 1L), new Aspects.AspectStack(Aspects.VINCULUM, 1L)}));

        ItemList.Dye_Indigo.set(addItem(tLastID = 410, "Indigo Dye", "Blue Dye", new Object[]{new Aspects.AspectStack(Aspects.SENSUS, 1L), Dyes.dyeBlue}));
        for (byte i = 0; i < 16; i = (byte) (i + 1)) {
            ItemList.DYE_ONLY_ITEMS[i].set(addItem(tLastID = 414 + i, Dyes.get(i).mName + " Dye", "", new Object[]{Dyes.get(i).name(), new Aspects.AspectStack(Aspects.SENSUS, 1L)}));
        }

        ItemList.Bottle_Dragon_Blood.set(addItem(tLastID = 114, "Dragon Blood", "FUS RO DAH!", new Object[]{SubTag.INVISIBLE, new GT_FoodStat(4, 0.4F, EnumAction.drink, new ItemStack(Items.glass_bottle, 1), GregTech_API.sDrinksAlwaysDrinkable, false, false, Potion.confusion.id, 300, 2, 90, Potion.damageBoost.id, 300, 2, 90, Potion.poison.id, 200, 2, 10, Potion.harm.id, 0, 2, 5), new Aspects.AspectStack(Aspects.VITREUS, 1L), new Aspects.AspectStack(Aspects.VENENUM, 2L), new Aspects.AspectStack(Aspects.POTENTIA, 2L)}));

        ItemList.SFMixture.set(addItem(tLastID = 270, "Super Fuel Binder", "Raw Material", new Object[]{}));
        ItemList.MSFMixture.set(addItem(tLastID = 271, "Magic Super Fuel Binder", "Raw Material", new Object[]{}));

        ItemList.Crop_Drop_Plumbilia.set(addItem(tLastID = 500, "Plumbilia Leaf", "Source of Lead", new Object[]{new Aspects.AspectStack(Aspects.MESSIS, 1L), new Aspects.AspectStack(Aspects.METALLUM, 1L), new Aspects.AspectStack(Aspects.ORDO, 1L)}));
        ItemList.Crop_Drop_Argentia.set(addItem(tLastID = 501, "Argentia Leaf", "Source of Silver", new Object[]{new Aspects.AspectStack(Aspects.MESSIS, 1L), new Aspects.AspectStack(Aspects.METALLUM, 1L), new Aspects.AspectStack(Aspects.LUCRUM, 1L)}));
        ItemList.Crop_Drop_Indigo.set(addItem(tLastID = 502, "Indigo Blossom", "Used for making Blue Dye", new Object[]{new Aspects.AspectStack(Aspects.MESSIS, 1L), new Aspects.AspectStack(Aspects.SENSUS, 1L)}));
        ItemList.Crop_Drop_Ferru.set(addItem(tLastID = 503, "Ferru Leaf", "Source of Iron", new Object[]{new Aspects.AspectStack(Aspects.MESSIS, 1L), new Aspects.AspectStack(Aspects.METALLUM, 2L)}));
        ItemList.Crop_Drop_Aurelia.set(addItem(tLastID = 504, "Aurelia Leaf", "Source of Gold", new Object[]{new Aspects.AspectStack(Aspects.MESSIS, 1L), new Aspects.AspectStack(Aspects.METALLUM, 1L), new Aspects.AspectStack(Aspects.LUCRUM, 1L)}));

        ItemList.Crop_Drop_OilBerry.set(addItem(tLastID = 510, "Oil Berry", "Oil in Berry form", new Object[]{new Aspects.AspectStack(Aspects.MESSIS, 1L), new Aspects.AspectStack(Aspects.AQUA, 1L), new Aspects.AspectStack(Aspects.POTENTIA, 1L)}));
        ItemList.Crop_Drop_BobsYerUncleRanks.set(addItem(tLastID = 511, "Bobs-Yer-Uncle-Berry", "Source of Emeralds", new Object[]{new Aspects.AspectStack(Aspects.MESSIS, 1L), new Aspects.AspectStack(Aspects.VITREUS, 1L), new Aspects.AspectStack(Aspects.LUCRUM, 1L)}));
        ItemList.Crop_Drop_UUMBerry.set(addItem(tLastID = 512, "UUM Berry", "UUM in Berry form", new Object[]{new Aspects.AspectStack(Aspects.MESSIS, 1L), new Aspects.AspectStack(Aspects.AQUA, 1L), new Aspects.AspectStack(Aspects.POTENTIA, 1L)}));
        ItemList.Crop_Drop_UUABerry.set(addItem(tLastID = 513, "UUA Berry", "UUA in Berry form", new Object[]{new Aspects.AspectStack(Aspects.MESSIS, 1L), new Aspects.AspectStack(Aspects.AQUA, 1L), new Aspects.AspectStack(Aspects.POTENTIA, 1L)}));

        ItemList.Crop_Drop_MilkWart.set(addItem(tLastID = 520, "Milk Wart", "Source of Milk", new Object[]{new Aspects.AspectStack(Aspects.MESSIS, 1L), new Aspects.AspectStack(Aspects.AQUA, 1L), new Aspects.AspectStack(Aspects.SANO, 1L)}));

        ItemList.Crop_Drop_Coppon.set(addItem(tLastID = 530, "Coppon Fiber", "ORANGE WOOOOOOOL!!!", new Object[]{new Aspects.AspectStack(Aspects.MESSIS, 1L), new Aspects.AspectStack(Aspects.METALLUM, 1L), new Aspects.AspectStack(Aspects.PERMUTATIO, 1L)}));

        ItemList.Crop_Drop_Tine.set(addItem(tLastID = 540, "Tine Twig", "Source of Tin", new Object[]{new Aspects.AspectStack(Aspects.MESSIS, 1L), new Aspects.AspectStack(Aspects.METALLUM, 1L), new Aspects.AspectStack(Aspects.ARBOR, 1L)}));
        setBurnValue(32000 + tLastID, 100);

        ItemList.Crop_Drop_Bauxite.set(addItem(tLastID = 521, "Bauxia Leaf", "Source of Aluminium", new Object[]{}));
        ItemList.Crop_Drop_Ilmenite.set(addItem(tLastID = 522, "Titania Leaf", "Source of Titanium", new Object[]{}));
        ItemList.Crop_Drop_Pitchblende.set(addItem(tLastID = 523, "Reactoria Leaf", "Source of Uranium", new Object[]{}));
        ItemList.Crop_Drop_Uraninite.set(addItem(tLastID = 524, "Uranium Leaf", "Source of Uranite", new Object[]{}));
        ItemList.Crop_Drop_Thorium.set(addItem(tLastID = 526, "Thunder Leaf", "Source of Thorium", new Object[]{}));
        ItemList.Crop_Drop_Nickel.set(addItem(tLastID = 527, "Nickelback Leaf", "Source of Nickel", new Object[]{}));
        ItemList.Crop_Drop_Zinc.set(addItem(tLastID = 528, "Galvania Leaf", "Source of Zinc", new Object[]{}));
        ItemList.Crop_Drop_Manganese.set(addItem(tLastID = 529, "Pyrolusium Leaf", "Source of Manganese", new Object[]{}));
        ItemList.Crop_Drop_Scheelite.set(addItem(tLastID = 531, "Scheelinium Leaf", "Source of Tungsten", new Object[]{}));
        ItemList.Crop_Drop_Platinum.set(addItem(tLastID = 532, "Platina Leaf", "Source of Platinum", new Object[]{}));
        ItemList.Crop_Drop_Iridium.set(addItem(tLastID = 533, "Quantaria Leaf", "Source of Iridium", new Object[]{}));
        ItemList.Crop_Drop_Osmium.set(addItem(tLastID = 534, "Quantaria Leaf", "Source of Osmium", new Object[]{}));
        ItemList.Crop_Drop_Naquadah.set(addItem(tLastID = 535, "Stargatium Leaf", "Source of Naquadah", new Object[]{}));

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
        GT_ModHandler.addExtractionRecipe(ItemList.Crop_Drop_Plumbilia.get(1), MatUnifier.get(OrePrefixes.dustTiny, Materials.Lead));
        GT_ModHandler.addExtractionRecipe(ItemList.Crop_Drop_Argentia.get(1), MatUnifier.get(OrePrefixes.dustTiny, Materials.Silver));
        GT_ModHandler.addExtractionRecipe(ItemList.Crop_Drop_Indigo.get(1), ItemList.Dye_Indigo.get(1));
        GT_ModHandler.addExtractionRecipe(ItemList.Crop_Drop_MilkWart.get(1), MatUnifier.get(OrePrefixes.dustSmall, Materials.Milk));
        GT_ModHandler.addExtractionRecipe(ItemList.Crop_Drop_Coppon.get(1), MatUnifier.get(OrePrefixes.dustTiny, Materials.Copper));
        GT_ModHandler.addExtractionRecipe(ItemList.Crop_Drop_Tine.get(1), MatUnifier.get(OrePrefixes.dustTiny, Materials.Tin));

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

        GT_ModHandler.addPulverisationRecipe(ItemList.Crop_Drop_Tine.get(1), MatUnifier.get(OrePrefixes.dustSmall, Materials.Wood, 2));
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.reeds, 1), new ItemStack(Items.sugar, 1), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.melon_block, 1, 0), new ItemStack(Items.melon, 8, 0), new ItemStack(Items.melon_seeds, 1), 80, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.pumpkin, 1, 0), new ItemStack(Items.pumpkin_seeds, 4, 0), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.melon, 1, 0), new ItemStack(Items.melon_seeds, 1, 0), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.wheat, 1, 0), MatUnifier.get(OrePrefixes.dust, Materials.Wheat), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.stick, 1), MatUnifier.get(OrePrefixes.dustSmall, Materials.Wood, 2), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.wool, 1, 32767), new ItemStack(Items.string, 2), new ItemStack(Items.string, 1), 50, false);
        try {
            Object tCrop;
            GT_Utility.getField(tCrop = ic2.api.crops.Crops.instance.getCropList()[13], "mDrop").set(tCrop, ItemList.Crop_Drop_Ferru.get(1));
            GT_Utility.getField(tCrop = ic2.api.crops.Crops.instance.getCropList()[14], "mDrop").set(tCrop, ItemList.Crop_Drop_Aurelia.get(1));
        } catch (Throwable e) {
            if (GT_Values.D1) {
                e.printStackTrace(GT_Log.err);
            }
        }
        ItemList.Display_ITS_FREE.set(addItem(tLastID = 766, "ITS FREE", "(or at least almost free)", new Object[]{SubTag.INVISIBLE, new Aspects.AspectStack(Aspects.LUCRUM, 1L)}));
    }

    public boolean onLeftClickEntity(ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
        super.onLeftClickEntity(aStack, aPlayer, aEntity);
        int aDamage = aStack.getItemDamage();
        if ((aDamage >= 25000) && (aDamage < 27000)) {
            if (aDamage >= 26000) {
                return Behaviour_Arrow.DEFAULT_PLASTIC.onLeftClickEntity(this, aStack, aPlayer, aEntity);
            }
            return Behaviour_Arrow.DEFAULT_WOODEN.onLeftClickEntity(this, aStack, aPlayer, aEntity);
        }
        return false;
    }

    public boolean hasProjectile(SubTag aProjectileType, ItemStack aStack) {
        int aDamage = aStack.getItemDamage();
        return ((aDamage >= 25000) && (aDamage < 27000)) || (super.hasProjectile(aProjectileType, aStack));
    }

    public EntityArrow getProjectile(SubTag aProjectileType, ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
        int aDamage = aStack.getItemDamage();
        if ((aDamage >= 25000) && (aDamage < 27000)) {
            if (aDamage >= 26000) {
                return Behaviour_Arrow.DEFAULT_PLASTIC.getProjectile(this, aProjectileType, aStack, aWorld, aX, aY, aZ);
            }
            return Behaviour_Arrow.DEFAULT_WOODEN.getProjectile(this, aProjectileType, aStack, aWorld, aX, aY, aZ);
        }
        return super.getProjectile(aProjectileType, aStack, aWorld, aX, aY, aZ);
    }

    public EntityArrow getProjectile(SubTag aProjectileType, ItemStack aStack, World aWorld, EntityLivingBase aEntity, float aSpeed) {
        int aDamage = aStack.getItemDamage();
        if ((aDamage >= 25000) && (aDamage < 27000)) {
            if (aDamage >= 26000) {
                return Behaviour_Arrow.DEFAULT_PLASTIC.getProjectile(this, aProjectileType, aStack, aWorld, aEntity, aSpeed);
            }
            return Behaviour_Arrow.DEFAULT_WOODEN.getProjectile(this, aProjectileType, aStack, aWorld, aEntity, aSpeed);
        }
        return super.getProjectile(aProjectileType, aStack, aWorld, aEntity, aSpeed);
    }

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

    public boolean doesShowInCreative(OrePrefixes aPrefix, Materials aMaterial, boolean aDoShowAllItems) {
        return (aDoShowAllItems) || (!aPrefix.name().startsWith("toolHead"));
    }

    public ItemStack onDispense(IBlockSource aSource, ItemStack aStack) {
        int aDamage = aStack.getItemDamage();
        if ((aDamage >= 25000) && (aDamage < 27000)) {
            if (aDamage >= 26000) {
                return Behaviour_Arrow.DEFAULT_PLASTIC.onDispense(this, aSource, aStack);
            }
            return Behaviour_Arrow.DEFAULT_WOODEN.onDispense(this, aSource, aStack);
        }
        return super.onDispense(aSource, aStack);
    }

    public final ItemStack getContainerItem(ItemStack aStack) {
        int aDamage = aStack.getItemDamage();
        if (aDamage < 32000) {
            return null;
        }
        return null;
    }
}
