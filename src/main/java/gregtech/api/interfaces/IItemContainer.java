package gregtech.api.interfaces;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IItemContainer {
    Item getItem();

    Block getBlock();

    boolean isStackEqual(ItemStack aStack);

    boolean isStackEqual(ItemStack aStack, boolean aWildcard, boolean aIgnoreNBT);

    ItemStack get(long aAmount, ItemStack... aReplacements);

    ItemStack getWildcard(long aAmount, ItemStack... aReplacements);

    ItemStack getUndamaged(long aAmount, ItemStack... aReplacements);

    ItemStack getAlmostBroken(long aAmount, ItemStack... aReplacements);

    ItemStack getWithDamage(long aAmount, long aMetaValue, ItemStack... aReplacements);

    IItemContainer set(Item aItem);

    IItemContainer set(ItemStack aStack);

    IItemContainer registerOre(Object... aOreNames);

    IItemContainer registerWildcardAsOre(Object... aOreNames);

    ItemStack getWithCharge(long aAmount, int aEnergy, ItemStack... aReplacements);

    ItemStack getWithName(long aAmount, String aDisplayName, ItemStack... aReplacements);

    boolean hasBeenSet();
}