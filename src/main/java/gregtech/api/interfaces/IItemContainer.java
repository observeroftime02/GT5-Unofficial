package gregtech.api.interfaces;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IItemContainer {
    Item getItem();

    Block getBlock();

    boolean isStackEqual(ItemStack aStack);

    boolean isStackEqual(ItemStack aStack, boolean aWildcard, boolean aIgnoreNBT);

    ItemStack get(int aAmount, ItemStack... aReplacements);

    ItemStack getWildcard(int aAmount, ItemStack... aReplacements);

    ItemStack getUndamaged(int aAmount, ItemStack... aReplacements);

    ItemStack getAlmostBroken(int aAmount, ItemStack... aReplacements);

    ItemStack getWithDamage(int aAmount, int aMetaValue, ItemStack... aReplacements);

    IItemContainer set(Item aItem);

    IItemContainer set(ItemStack aStack);

    IItemContainer registerOre(Object... aOreNames);

    IItemContainer registerWildcardAsOre(Object... aOreNames);

    ItemStack getWithCharge(int aAmount, int aEnergy, ItemStack... aReplacements);

    ItemStack getWithName(int aAmount, String aDisplayName, ItemStack... aReplacements);

    boolean hasBeenSet();
}