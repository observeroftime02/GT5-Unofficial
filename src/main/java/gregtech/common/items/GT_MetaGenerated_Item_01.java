package gregtech.common.items;

import cpw.mods.fml.common.Loader;
import gregtech.api.GregTech_API;
import gregtech.api.enums.*;
import gregtech.api.interfaces.IItemBehaviour;
import gregtech.api.items.GT_MetaBase_Item;
import gregtech.api.items.GT_MetaGenerated_Item_X32;
import gregtech.api.objects.GT_MultiTexture;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.objects.ItemData;
import gregtech.api.util.*;
import gregtech.common.covers.*;
import gregtech.common.items.behaviors.*;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import java.util.Collection;
import java.util.List;

public class GT_MetaGenerated_Item_01 extends GT_MetaGenerated_Item_X32 {
    public static GT_MetaGenerated_Item_01 INSTANCE;
    private final String mToolTipPurify = GT_LanguageManager.addStringLocalization("metaitem.01.tooltip.purify", "Throw into Cauldron to get clean Dust");
    private final static String aTextEmptyRow = "   "; private final static String aTextShape = " P ";

    public GT_MetaGenerated_Item_01() {
        super("metaitem.01", OrePrefixes.dustTiny, OrePrefixes.dustSmall, OrePrefixes.dust, OrePrefixes.dustImpure, OrePrefixes.dustPure, OrePrefixes.crushed, OrePrefixes.crushedPurified, OrePrefixes.crushedCentrifuged, OrePrefixes.gem, OrePrefixes.nugget, OrePrefixes.oreChunk, OrePrefixes.ingot, OrePrefixes.ingotHot, null, null, null, null, OrePrefixes.plate, null, null, null, null, OrePrefixes.plateDense, OrePrefixes.stick, OrePrefixes.lens, null, OrePrefixes.bolt, OrePrefixes.screw, OrePrefixes.ring, OrePrefixes.foil, OrePrefixes.cell, OrePrefixes.cellPlasma);
        INSTANCE = this;

        int tLastID = 0;

        setBurnValue(17000 + Materials.Wood.mMetaItemSubID, 1600);
        GT_OreDictUnificator.addToBlacklist(new ItemStack(this, 1, 17000 + Materials.Wood.mMetaItemSubID));
        GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood), new ItemStack(this, 1, 17000 + Materials.Wood.mMetaItemSubID));
        GregTech_API.registerCover(new ItemStack(this, 1, 17000 + Materials.Wood.mMetaItemSubID), new GT_RenderedTexture(Textures.BlockIcons.COVER_WOOD_PLATE), null);

        ItemStack tStack = new ItemStack(this, 1, 17000 + Materials.Wood.mMetaItemSubID);
        tStack.setStackDisplayName("The holy Planks of Sengir");
        GT_Utility.ItemNBT.addEnchantment(tStack, Enchantment.smite, 10);
        GT_ModHandler.addCraftingRecipe(tStack, GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{"XXX", "XDX", "XXX", 'X', OrePrefixes.gem.get(Materials.NetherStar), 'D', new ItemStack(Blocks.dragon_egg, 1, 32767)});

        ItemList.Coin_Doge.set(addItem(tLastID = 9, "Doge Coin", "wow much coin how money so crypto plz mine v rich very currency wow", new Object[]{new ItemData(Materials.Brass, 907200), new TC_Aspects.TC_AspectStack(TC_Aspects.LUCRUM, 1)}));

        ItemList.Component_Minecart_Wheels_Iron.set(addItem(tLastID = 100, "Iron Minecart Wheels", "To get things rolling", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2)}));
        ItemList.Component_Minecart_Wheels_Steel.set(addItem(tLastID = 101, "Steel Minecart Wheels", "To get things rolling", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2)}));

        GT_ModHandler.addCraftingRecipe(ItemList.Component_Minecart_Wheels_Iron.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{" h ", "RSR", " w ", 'R', OrePrefixes.ring.get(Materials.AnyIron), 'S', OrePrefixes.stick.get(Materials.AnyIron)});
        GT_ModHandler.addCraftingRecipe(ItemList.Component_Minecart_Wheels_Steel.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{" h ", "RSR", " w ", 'R', OrePrefixes.ring.get(Materials.Steel), 'S', OrePrefixes.stick.get(Materials.Steel)});

        ItemList.CompressedFireclay.set(addItem(tLastID = 110, "Compressed Fireclay", "Brick-shaped"));
        ItemList.Firebrick.set(addItem(tLastID = 111, "Firebrick", "Heat resistant"));

        ItemList.Shape_Empty.set(addItem(tLastID = 300, "Empty Shape Plate", "Raw Plate to make Molds and Extruder Shapes", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.FABRICO, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 4)}));

        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Empty.get(1), GT_ModHandler.RecipeBits.MIRRORED | GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"hf", "PP", "PP", 'P', OrePrefixes.plate.get(Materials.Steel)});

        ItemList.Shape_Mold_Plate.set(addItem(tLastID = 301, "Mold (Plate)", "Mold for making Plates"));
        ItemList.Shape_Mold_Casing.set(addItem(tLastID = 302, "Mold (Casing)", "Mold for making Item Casings"));
        ItemList.Shape_Mold_Gear.set(addItem(tLastID = 303, "Mold (Gear)", "Mold for making Gears"));
        ItemList.Shape_Mold_Credit.set(addItem(tLastID = 304, "Mold (Coinage)", "Secure Mold for making Coins (Don't lose it!)"));
        ItemList.Shape_Mold_Bottle.set(addItem(tLastID = 305, "Mold (Bottle)", "Mold for making Bottles"));
        ItemList.Shape_Mold_Ingot.set(addItem(tLastID = 306, "Mold (Ingot)", "Mold for making Ingots"));
        ItemList.Shape_Mold_Ball.set(addItem(tLastID = 307, "Mold (Ball)", "Mold for making Balls"));
        ItemList.Shape_Mold_Block.set(addItem(tLastID = 308, "Mold (Block)", "Mold for making Blocks"));
        ItemList.Shape_Mold_Nugget.set(addItem(tLastID = 309, "Mold (Nuggets)", "Mold for making Nuggets"));
        ItemList.Shape_Mold_Bun.set(addItem(tLastID = 310, "Mold (Buns)", "Mold for shaping Buns"));
        ItemList.Shape_Mold_Bread.set(addItem(tLastID = 311, "Mold (Bread)", "Mold for shaping Breads"));
        ItemList.Shape_Mold_Baguette.set(addItem(tLastID = 312, "Mold (Baguette)", "Mold for shaping Baguettes"));
        ItemList.Shape_Mold_Cylinder.set(addItem(tLastID = 313, "Mold (Cylinder)", "Mold for shaping Cylinders"));
        ItemList.Shape_Mold_Anvil.set(addItem(tLastID = 314, "Mold (Anvil)", "Mold for shaping Anvils"));
        ItemList.Shape_Mold_Name.set(addItem(tLastID = 315, "Mold (Name)", "Mold for naming Items (rename Mold with Anvil)"));
        ItemList.Shape_Mold_Arrow.set(addItem(tLastID = 316, "Mold (Arrow Head)", "Mold for making Arrow Heads"));
        ItemList.Shape_Mold_Gear_Small.set(addItem(tLastID = 317, "Mold (Small Gear)", "Mold for making small Gears"));

        GT_ModHandler.removeRecipe(new ItemStack(Blocks.glass), null, new ItemStack(Blocks.glass), null, new ItemStack(Blocks.glass));

        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Mold_Credit.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"h  ", aTextShape, aTextEmptyRow, 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Mold_Plate.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{" h ", aTextShape, aTextEmptyRow, 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Mold_Casing.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"  h", aTextShape, aTextEmptyRow, 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Mold_Gear.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{aTextEmptyRow, " Ph", aTextEmptyRow, 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Mold_Bottle.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{aTextEmptyRow, aTextShape, "  h", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Mold_Ingot.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{aTextEmptyRow, aTextShape, " h ", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Mold_Ball.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{aTextEmptyRow, aTextShape, "h  ", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Mold_Block.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{aTextEmptyRow, "hP ", aTextEmptyRow, 'P', ItemList.Shape_Empty});

        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Mold_Nugget.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"P h", aTextEmptyRow, aTextEmptyRow, 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Mold_Bun.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"P  ", "  h", aTextEmptyRow, 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Mold_Bread.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"P  ", aTextEmptyRow, "  h", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Mold_Baguette.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"P  ", aTextEmptyRow, " h ", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Mold_Cylinder.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"  P", aTextEmptyRow, "  h", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Mold_Anvil.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"  P", aTextEmptyRow, " h ", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Mold_Name.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"  P", aTextEmptyRow, "h  ", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Mold_Arrow.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"  P", "h  ", aTextEmptyRow, 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Mold_Gear_Small.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{aTextEmptyRow, aTextEmptyRow, "h P", 'P', ItemList.Shape_Empty});

        ItemList.Shape_Extruder_Plate.set(addItem(tLastID = 350, "Extruder Shape (Plate)", "Extruder Shape for making Plates"));
        ItemList.Shape_Extruder_Rod.set(addItem(tLastID = 351, "Extruder Shape (Rod)", "Extruder Shape for making Rods"));
        ItemList.Shape_Extruder_Bolt.set(addItem(tLastID = 352, "Extruder Shape (Bolt)", "Extruder Shape for making Bolts"));
        ItemList.Shape_Extruder_Ring.set(addItem(tLastID = 353, "Extruder Shape (Ring)", "Extruder Shape for making Rings"));
        ItemList.Shape_Extruder_Cell.set(addItem(tLastID = 354, "Extruder Shape (Cell)", "Extruder Shape for making Cells"));
        ItemList.Shape_Extruder_Ingot.set(addItem(tLastID = 355, "Extruder Shape (Ingot)", "Extruder Shape for, wait, can't we just use a Furnace?"));
        ItemList.Shape_Extruder_Wire.set(addItem(tLastID = 356, "Extruder Shape (Wire)", "Extruder Shape for making Wires"));
        ItemList.Shape_Extruder_Casing.set(addItem(tLastID = 357, "Extruder Shape (Casing)", "Extruder Shape for making Item Casings"));
        ItemList.Shape_Extruder_Pipe_Tiny.set(addItem(tLastID = 358, "Extruder Shape (Tiny Pipe)", "Extruder Shape for making tiny Pipes"));
        ItemList.Shape_Extruder_Pipe_Small.set(addItem(tLastID = 359, "Extruder Shape (Small Pipe)", "Extruder Shape for making small Pipes"));
        ItemList.Shape_Extruder_Pipe_Medium.set(addItem(tLastID = 360, "Extruder Shape (Normal Pipe)", "Extruder Shape for making Pipes"));
        ItemList.Shape_Extruder_Pipe_Large.set(addItem(tLastID = 361, "Extruder Shape (Large Pipe)", "Extruder Shape for making large Pipes"));
        ItemList.Shape_Extruder_Pipe_Huge.set(addItem(tLastID = 362, "Extruder Shape (Huge Pipe)", "Extruder Shape for making full Block Pipes"));
        ItemList.Shape_Extruder_Block.set(addItem(tLastID = 363, "Extruder Shape (Block)", "Extruder Shape for making Blocks"));
        ItemList.Shape_Extruder_Sword.set(addItem(tLastID = 364, "Extruder Shape (Sword Blade)", "Extruder Shape for making Swords"));
        ItemList.Shape_Extruder_Pickaxe.set(addItem(tLastID = 365, "Extruder Shape (Pickaxe Head)", "Extruder Shape for making Pickaxes"));
        ItemList.Shape_Extruder_Shovel.set(addItem(tLastID = 366, "Extruder Shape (Shovel Head)", "Extruder Shape for making Shovels"));
        ItemList.Shape_Extruder_Axe.set(addItem(tLastID = 367, "Extruder Shape (Axe Head)", "Extruder Shape for making Axes"));
        ItemList.Shape_Extruder_Hoe.set(addItem(tLastID = 368, "Extruder Shape (Hoe Head)", "Extruder Shape for making Hoes"));
        ItemList.Shape_Extruder_Hammer.set(addItem(tLastID = 369, "Extruder Shape (Hammer Head)", "Extruder Shape for making Hammers"));
        ItemList.Shape_Extruder_File.set(addItem(tLastID = 370, "Extruder Shape (File Head)", "Extruder Shape for making Files"));
        ItemList.Shape_Extruder_Saw.set(addItem(tLastID = 371, "Extruder Shape (Saw Blade)", "Extruder Shape for making Saws"));
        ItemList.Shape_Extruder_Gear.set(addItem(tLastID = 372, "Extruder Shape (Gear)", "Extruder Shape for making Gears"));
        ItemList.Shape_Extruder_Bottle.set(addItem(tLastID = 373, "Extruder Shape (Bottle)", "Extruder Shape for making Bottles"));

        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Bolt.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"x  ", aTextShape, aTextEmptyRow, 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Cell.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{" x ", aTextShape, aTextEmptyRow, 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Ingot.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"  x", aTextShape, aTextEmptyRow, 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Ring.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{aTextEmptyRow, " Px", aTextEmptyRow, 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Rod.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{aTextEmptyRow, aTextShape, "  x", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Wire.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{aTextEmptyRow, aTextShape, " x ", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Casing.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{aTextEmptyRow, aTextShape, "x  ", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Plate.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{aTextEmptyRow, "xP ", aTextEmptyRow, 'P', ItemList.Shape_Empty});

        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Block.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"P x", aTextEmptyRow, aTextEmptyRow, 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Pipe_Small.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"P  ", "  x", aTextEmptyRow, 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Pipe_Large.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"P  ", aTextEmptyRow, "  x", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Pipe_Medium.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"P  ", aTextEmptyRow, " x ", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Sword.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"  P", aTextEmptyRow, "  x", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Pickaxe.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"  P", aTextEmptyRow, " x ", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Shovel.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"  P", aTextEmptyRow, "x  ", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Axe.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"  P", "x  ", aTextEmptyRow, 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Hoe.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{aTextEmptyRow, aTextEmptyRow, "x P", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Hammer.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{aTextEmptyRow, "x  ", "  P", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_File.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"x  ", aTextEmptyRow, "  P", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Saw.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{" x ", aTextEmptyRow, "  P", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Gear.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"x  ", aTextEmptyRow, "P  ", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Pipe_Tiny.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{" x ", aTextEmptyRow, "P  ", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Pipe_Huge.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"  x", aTextEmptyRow, "P  ", 'P', ItemList.Shape_Empty});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Extruder_Bottle.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{aTextEmptyRow, "  x", "P  ", 'P', ItemList.Shape_Empty});

        ItemList.Shape_Slicer_Flat.set(addItem(tLastID = 398, "Slicer Blade (Flat)", "Slicer Blade for cutting Flat"));
        ItemList.Shape_Slicer_Stripes.set(addItem(tLastID = 399, "Slicer Blade (Stripes)", "Slicer Blade for cutting Stripes"));

        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Slicer_Flat.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"hXS", aTextShape, "fXd", 'P', ItemList.Shape_Extruder_Block, 'X', OrePrefixes.plate.get(Materials.StainlessSteel), 'S', OrePrefixes.screw.get(Materials.StainlessSteel)});
        GT_ModHandler.addCraftingRecipe(ItemList.Shape_Slicer_Stripes.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"hXS", "XPX", "fXd", 'P', ItemList.Shape_Extruder_Block, 'X', OrePrefixes.plate.get(Materials.StainlessSteel), 'S', OrePrefixes.screw.get(Materials.StainlessSteel)});

        ItemList.Large_Fluid_Cell_Steel.set(addItem(tLastID = 405, "Large Steel Fluid Cell", "", new Object[]{new ItemData(Materials.Steel, OrePrefixes.plate.mMaterialAmount * 2 + 2 * OrePrefixes.ring.mMaterialAmount), new TC_Aspects.TC_AspectStack(TC_Aspects.VACUOS, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.AQUA, 2)}));
        setFluidContainerStats(32000 + tLastID, 16000L, 16);

        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 2), GT_OreDictUnificator.get(OrePrefixes.ring, Materials.Steel, 2), ItemList.Large_Fluid_Cell_Steel.get(1), 100, 64);

        ItemList.Large_Fluid_Cell_TungstenSteel.set(addItem(tLastID = 406, "Large Tungstensteel Fluid Cell", "", new Object[]{new ItemData(Materials.TungstenSteel, OrePrefixes.plate.mMaterialAmount * 2 + 2 * OrePrefixes.ring.mMaterialAmount), new TC_Aspects.TC_AspectStack(TC_Aspects.VACUOS, 6), new TC_Aspects.TC_AspectStack(TC_Aspects.AQUA, 3)}));
        setFluidContainerStats(32000 + tLastID, 64000L, 16);

        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.TungstenSteel, 2), GT_OreDictUnificator.get(OrePrefixes.ring, Materials.TungstenSteel, 2), ItemList.Large_Fluid_Cell_TungstenSteel.get(1), 200, 256);

        ItemList.Tool_Lighter_Invar_Empty.set(addItem(tLastID = 474, "Lighter (Empty)", "", new Object[]{new ItemData(Materials.Invar, OrePrefixes.plate.mMaterialAmount * 2), new TC_Aspects.TC_AspectStack(TC_Aspects.IGNIS, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.VACUOS, 1)}));
        ItemList.Tool_Lighter_Invar_Used.set(addItem(tLastID = 475, "Lighter", "", new Object[]{new ItemData(Materials.Invar, OrePrefixes.plate.mMaterialAmount * 2), new TC_Aspects.TC_AspectStack(TC_Aspects.IGNIS, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 1), SubTag.INVISIBLE}));
        ItemList.Tool_Lighter_Invar_Full.set(addItem(tLastID = 476, "Lighter (Full)", "", new Object[]{new ItemData(Materials.Invar, OrePrefixes.plate.mMaterialAmount * 2), new TC_Aspects.TC_AspectStack(TC_Aspects.IGNIS, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 2)}));

        IItemBehaviour<GT_MetaBase_Item> tBehaviour = new Behaviour_Lighter(ItemList.Tool_Lighter_Invar_Empty.get(1), ItemList.Tool_Lighter_Invar_Used.get(1), ItemList.Tool_Lighter_Invar_Full.get(1), 100L);
        addItemBehavior(32475, tBehaviour);
        addItemBehavior(32476, tBehaviour);

        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Invar, 2), new ItemStack(Items.flint, 1), ItemList.Tool_Lighter_Invar_Empty.get(1), 256, 16);

        ItemList.Tool_Lighter_Platinum_Empty.set(addItem(tLastID = 477, "Platinum Lighter (Empty)", "A known Prank Master is engraved on it", new Object[]{new ItemData(Materials.Platinum, OrePrefixes.plate.mMaterialAmount * 2), new TC_Aspects.TC_AspectStack(TC_Aspects.IGNIS, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.NEBRISUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.VACUOS, 1)}));
        ItemList.Tool_Lighter_Platinum_Used.set(addItem(tLastID = 478, "Platinum Lighter", "A known Prank Master is engraved on it", new Object[]{new ItemData(Materials.Platinum, OrePrefixes.plate.mMaterialAmount * 2), new TC_Aspects.TC_AspectStack(TC_Aspects.IGNIS, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.NEBRISUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 1), SubTag.INVISIBLE}));
        ItemList.Tool_Lighter_Platinum_Full.set(addItem(tLastID = 479, "Platinum Lighter (Full)", "A known Prank Master is engraved on it", new Object[]{new ItemData(Materials.Platinum, OrePrefixes.plate.mMaterialAmount * 2), new TC_Aspects.TC_AspectStack(TC_Aspects.IGNIS, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.NEBRISUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 2)}));

        tBehaviour = new Behaviour_Lighter(ItemList.Tool_Lighter_Platinum_Empty.get(1), ItemList.Tool_Lighter_Platinum_Used.get(1), ItemList.Tool_Lighter_Platinum_Full.get(1), 1000L);
        addItemBehavior(32478, tBehaviour);
        addItemBehavior(32479, tBehaviour);

        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Platinum, 2), new ItemStack(Items.flint, 1), ItemList.Tool_Lighter_Platinum_Empty.get(1), 256, 256);

        if (Loader.isModLoaded("GalacticraftMars")) {
            ItemList.Ingot_Heavy1.set(addItem(tLastID = 462, "Heavy Duty Alloy Ingot", "Used to make Heavy Duty Plates"));
            ItemList.Ingot_Heavy2.set(addItem(tLastID = 463, "Heavy Duty Alloy Ingot T2", "Used to make Heavy Duty Plates T2"));
            ItemList.Ingot_Heavy3.set(addItem(tLastID = 464, "Heavy Duty Alloy Ingot T3", "Used to make Heavy Duty Plates T3"));
        }

        ItemList.CoalCoke.set(addItem(tLastID = 410, "Coal Coke", ""));
        ItemList.Plantball.set(addItem(tLastID = 411, "Plantball", ""));
        ItemList.Scrap.set(addItem(tLastID = 412, "Scrap", "Used to create UUAmplifier"));
        ItemList.Dynamite.set(addItem(tLastID = 413, "Dynamite", "Used in the Implosion Compressor"));
        ItemList.IndustrialExplosive.set(addItem(tLastID = 414, "Industrial Explosive", "Used in the Implosion Compressor"));
        ItemList.Resin.set(addItem(tLastID = 415, "Sticky Resin", ""));
        ItemList.Fertilizer.set(addItem(tLastID = 416, "Fertilizer", ""));
        ItemList.Cell_Empty.set(addItem(tLastID = 417, "Empty Cell", ""));
        ItemList.Cell_Steam.set(addItem(tLastID = 418, "Steam Cell", ""));
        ItemList.Cell_Electrolyzed_Water.set(addItem(tLastID = 419, "Electrolyzed Water Cell", ""));
        ItemList.RawCarbonFibers.set(addItem(tLastID = 420, "Raw Carbon Fibers", ""));
        ItemList.RawCarbonMesh.set(addItem(tLastID = 421, "Raw Carbon Mesh", ""));
        ItemList.Plate_CarbonAlloy.set(addItem(tLastID = 422, "Carbon Plate", ""));
        ItemList.Plate_AdvancedAlloy.set(addItem(tLastID = 423, "Advanced Alloy", ""));
        ItemList.Plate_IridiumAlloy.set(addItem(tLastID = 424, "Iridium Alloy Plate", "1.4.7s Most Expensive Component"));
        ItemList.Ingot_IridiumAlloy.set(addItem(tLastID = 480, "Iridium Alloy Ingot", "Used to make Iridium Alloy Plates", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.TUTAMEN, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 4)}));

        GT_ModHandler.addCraftingRecipe(ItemList.Ingot_IridiumAlloy.get(1), GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{"IAI", "ADA", "IAI", 'D', GregTech_API.sRecipeFile.get(ConfigCategories.Recipes.harderrecipes, "iridiumplate", true) ? OreDictNames.craftingIndustrialDiamond : OrePrefixes.dust.get(Materials.Diamond), 'A', ItemList.Plate_AdvancedAlloy.get(1), 'I', OrePrefixes.plate.get(Materials.Iridium)});

        OrePrefixes.cell.mContainerItem = ItemList.Cell_Empty.get(1);
        OrePrefixes.cellPlasma.mContainerItem = ItemList.Cell_Empty.get(1);
        GT_OreDictUnificator.addItemData(ItemList.Cell_Empty.get(1), new ItemData(Materials.Tin, 7257600));


        ItemList.Paper_Printed_Pages.set(addItem(tLastID = 481, "Printed Pages", "Used to make written Books", new Object[]{new ItemData(Materials.Paper, 10886400), new Behaviour_PrintedPages(), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 2)}));
        ItemList.Paper_Magic_Empty.set(addItem(tLastID = 482, "Magic Paper", "", new Object[]{SubTag.INVISIBLE, new ItemData(Materials.Paper, 3628800), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.PRAECANTATIO, 1)}));
        ItemList.Paper_Magic_Page.set(addItem(tLastID = 483, "Enchanted Page", "", new Object[]{SubTag.INVISIBLE, new ItemData(Materials.Paper, 3628800), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.PRAECANTATIO, 2)}));
        ItemList.Paper_Magic_Pages.set(addItem(tLastID = 484, "Enchanted Pages", "", new Object[]{SubTag.INVISIBLE, new ItemData(Materials.Paper, 10886400), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.PRAECANTATIO, 4)}));
        ItemList.Paper_Punch_Card_Empty.set(addItem(tLastID = 485, "Punch Card", "", new Object[]{SubTag.INVISIBLE, new ItemData(Materials.Paper, 7257600), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 1)}));
        ItemList.Paper_Punch_Card_Encoded.set(addItem(tLastID = 486, "Punched Card", "", new Object[]{SubTag.INVISIBLE, new ItemData(Materials.Paper, 7257600), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 2)}));
        ItemList.Book_Written_01.set(addItem(tLastID = 487, "Book", "", new Object[]{new ItemData(Materials.Paper, 10886400), "bookWritten", OreDictNames.craftingBook, new Behaviour_WrittenBook(), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 2)}));
        ItemList.Book_Written_02.set(addItem(tLastID = 488, "Book", "", new Object[]{new ItemData(Materials.Paper, 10886400), "bookWritten", OreDictNames.craftingBook, new Behaviour_WrittenBook(), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 2)}));
        ItemList.Book_Written_03.set(addItem(tLastID = 489, "Book", "", new Object[]{new ItemData(Materials.Paper, 10886400), "bookWritten", OreDictNames.craftingBook, new Behaviour_WrittenBook(), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 2)}));

        ItemList.Schematic.set(addItem(tLastID = 490, "Schematic", "EMPTY", new Object[]{new ItemData(Materials.StainlessSteel, 7257600), new TC_Aspects.TC_AspectStack(TC_Aspects.STRONTIO, 1)}));
        ItemList.Schematic_Crafting.set(addItem(tLastID = 491, "Schematic (Crafting)", "Crafts the Programmed Recipe", new Object[]{new ItemData(Materials.StainlessSteel, 7257600), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.FABRICO, 1)}));
        ItemList.Schematic_1by1.set(addItem(tLastID = 495, "Schematic (1x1)", "Crafts 1 Items as 1x1 (use in Packager)", new Object[]{new ItemData(Materials.StainlessSteel, 7257600), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.FABRICO, 1)}));
        ItemList.Schematic_2by2.set(addItem(tLastID = 496, "Schematic (2x2)", "Crafts 4 Items as 2x2 (use in Packager)", new Object[]{new ItemData(Materials.StainlessSteel, 7257600), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.FABRICO, 1)}));
        ItemList.Schematic_3by3.set(addItem(tLastID = 497, "Schematic (3x3)", "Crafts 9 Items as 3x3 (use in Packager)", new Object[]{new ItemData(Materials.StainlessSteel, 7257600), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.FABRICO, 1)}));
        ItemList.Schematic_Dust.set(addItem(tLastID = 498, "Schematic (Dusts)", "Combines Dusts (use in Packager)", new Object[]{new ItemData(Materials.StainlessSteel, 7257600), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.FABRICO, 1)}));

        GT_ModHandler.addCraftingRecipe(ItemList.Schematic_1by1.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{"d  ", aTextShape, aTextEmptyRow, 'P', ItemList.Schematic});
        GT_ModHandler.addCraftingRecipe(ItemList.Schematic_2by2.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{" d ", aTextShape, aTextEmptyRow, 'P', ItemList.Schematic});
        GT_ModHandler.addCraftingRecipe(ItemList.Schematic_3by3.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{"  d", aTextShape, aTextEmptyRow, 'P', ItemList.Schematic});
        GT_ModHandler.addCraftingRecipe(ItemList.Schematic_Dust.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{aTextEmptyRow, aTextShape, "  d", 'P', ItemList.Schematic});

        GT_ModHandler.addShapelessCraftingRecipe(ItemList.Schematic.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{ItemList.Schematic_Crafting});
        GT_ModHandler.addShapelessCraftingRecipe(ItemList.Schematic.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{ItemList.Schematic_1by1});
        GT_ModHandler.addShapelessCraftingRecipe(ItemList.Schematic.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{ItemList.Schematic_2by2});
        GT_ModHandler.addShapelessCraftingRecipe(ItemList.Schematic.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{ItemList.Schematic_3by3});
        GT_ModHandler.addShapelessCraftingRecipe(ItemList.Schematic.get(1), GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{ItemList.Schematic_Dust});

        ItemList.Battery_Hull_LV.set(addItem(tLastID = 500, "Small Battery Hull", "An empty LV Battery Hull", new Object[]{new ItemData(Materials.BatteryAlloy, OrePrefixes.plate.mMaterialAmount), new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.VACUOS, 1)}));
        ItemList.Battery_Hull_MV.set(addItem(tLastID = 501, "Medium Battery Hull", "An empty MV Battery Hull", new Object[]{new ItemData(Materials.BatteryAlloy, OrePrefixes.plate.mMaterialAmount * 3), new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.VACUOS, 1)}));
        ItemList.Battery_Hull_HV.set(addItem(tLastID = 502, "Large Battery Hull", "An empty HV Battery Hull", new Object[]{new ItemData(Materials.BatteryAlloy, OrePrefixes.plate.mMaterialAmount * 9), new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.VACUOS, 1)}));

        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Hull_LV.get(1), GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{"C", "P", "P", 'P', OrePrefixes.plate.get(Materials.BatteryAlloy), 'C', OreDictNames.craftingWireTin});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Hull_MV.get(1), GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{"C C", "PPP", "PPP", 'P', OrePrefixes.plate.get(Materials.BatteryAlloy), 'C', OreDictNames.craftingWireCopper});

        ItemList.Battery_RE_ULV_Tantalum.set(addItem(tLastID = 499, "Tantalum Capacitor", "Reusable", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 1)}));
        setElectricStats(32000 + tLastID, 1000L, GT_Values.V[0], 0L, -3L, false);

        ItemList.Battery_SU_LV_SulfuricAcid.set(addItem(tLastID = 510, "Small Acid Battery", "Single Use", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 2)}));
        setElectricStats(32000 + tLastID, 18000L, GT_Values.V[1], 1, -2L, true);
        ItemList.Battery_SU_LV_Mercury.set(addItem(tLastID = 511, "Small Mercury Battery", "Single Use", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 2)}));
        setElectricStats(32000 + tLastID, 32000L, GT_Values.V[1], 1, -2L, true);

        ItemList.Battery_RE_LV_Cadmium.set(addItem(tLastID = 517, "Small Cadmium Battery", "Reusable", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 1), "calclavia:ADVANCED_BATTERY"}));
        setElectricStats(32000 + tLastID, 75000L, GT_Values.V[1], 1, -3L, true);
        ItemList.Battery_RE_LV_Lithium.set(addItem(tLastID = 518, "Small Lithium Battery", "Reusable", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 1), "calclavia:ADVANCED_BATTERY"}));
        setElectricStats(32000 + tLastID, 100000L, GT_Values.V[1], 1, -3L, true);
        ItemList.Battery_RE_LV_Sodium.set(addItem(tLastID = 519, "Small Sodium Battery", "Reusable", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 1), "calclavia:ADVANCED_BATTERY"}));
        setElectricStats(32000 + tLastID, 50000L, GT_Values.V[1], 1, -3L, true);

        ItemList.Battery_SU_MV_SulfuricAcid.set(addItem(tLastID = 520, "Medium Acid Battery", "Single Use", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 4)}));
        setElectricStats(32000 + tLastID, 72000L, GT_Values.V[2], 2, -2L, true);
        ItemList.Battery_SU_MV_Mercury.set(addItem(tLastID = 521, "Medium Mercury Battery", "Single Use", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 4)}));
        setElectricStats(32000 + tLastID, 128000L, GT_Values.V[2], 2, -2L, true);

        ItemList.Battery_RE_MV_Cadmium.set(addItem(tLastID = 527, "Medium Cadmium Battery", "Reusable", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 2)}));
        setElectricStats(32000 + tLastID, 300000L, GT_Values.V[2], 2, -3L, true);
        ItemList.Battery_RE_MV_Lithium.set(addItem(tLastID = 528, "Medium Lithium Battery", "Reusable", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 2)}));
        setElectricStats(32000 + tLastID, 400000L, GT_Values.V[2], 2, -3L, true);
        ItemList.Battery_RE_MV_Sodium.set(addItem(tLastID = 529, "Medium Sodium Battery", "Reusable", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 2)}));
        setElectricStats(32000 + tLastID, 200000L, GT_Values.V[2], 2, -3L, true);

        ItemList.Battery_SU_HV_SulfuricAcid.set(addItem(tLastID = 530, "Large Acid Battery", "Single Use", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 8)}));
        setElectricStats(32000 + tLastID, 288000L, GT_Values.V[3], 3L, -2L, true);
        ItemList.Battery_SU_HV_Mercury.set(addItem(tLastID = 531, "Large Mercury Battery", "Single Use", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 8)}));
        setElectricStats(32000 + tLastID, 512000L, GT_Values.V[3], 3L, -2L, true);

        ItemList.Battery_RE_HV_Cadmium.set(addItem(tLastID = 537, "Large Cadmium Battery", "Reusable", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 4)}));
        setElectricStats(32000 + tLastID, 1200000L, GT_Values.V[3], 3L, -3L, true);
        ItemList.Battery_RE_HV_Lithium.set(addItem(tLastID = 538, "Large Lithium Battery", "Reusable", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 4)}));
        setElectricStats(32000 + tLastID, 1600000L, GT_Values.V[3], 3L, -3L, true);
        ItemList.Battery_RE_HV_Sodium.set(addItem(tLastID = 539, "Large Sodium Battery", "Reusable", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 4)}));
        setElectricStats(32000 + tLastID, 800000L, GT_Values.V[3], 3L, -3L, true);
        
        GT_ModHandler.addExtractionRecipe(ItemList.Battery_SU_LV_SulfuricAcid.get(1), ItemList.Battery_Hull_LV.get(1));
        GT_ModHandler.addExtractionRecipe(ItemList.Battery_SU_LV_Mercury.get(1), ItemList.Battery_Hull_LV.get(1));
        GT_ModHandler.addExtractionRecipe(ItemList.Battery_SU_MV_SulfuricAcid.get(1), ItemList.Battery_Hull_MV.get(1));
        GT_ModHandler.addExtractionRecipe(ItemList.Battery_SU_MV_Mercury.get(1), ItemList.Battery_Hull_MV.get(1));
        GT_ModHandler.addExtractionRecipe(ItemList.Battery_SU_HV_SulfuricAcid.get(1), ItemList.Battery_Hull_HV.get(1));
        GT_ModHandler.addExtractionRecipe(ItemList.Battery_SU_HV_Mercury.get(1), ItemList.Battery_Hull_HV.get(1));
        GT_ModHandler.addExtractionRecipe(ItemList.Battery_RE_LV_Cadmium.get(1), ItemList.Battery_Hull_LV.get(1));
        GT_ModHandler.addExtractionRecipe(ItemList.Battery_RE_LV_Lithium.get(1), ItemList.Battery_Hull_LV.get(1));
        GT_ModHandler.addExtractionRecipe(ItemList.Battery_RE_LV_Sodium.get(1), ItemList.Battery_Hull_LV.get(1));
        GT_ModHandler.addExtractionRecipe(ItemList.Battery_RE_MV_Cadmium.get(1), ItemList.Battery_Hull_MV.get(1));
        GT_ModHandler.addExtractionRecipe(ItemList.Battery_RE_MV_Lithium.get(1), ItemList.Battery_Hull_MV.get(1));
        GT_ModHandler.addExtractionRecipe(ItemList.Battery_RE_MV_Sodium.get(1), ItemList.Battery_Hull_MV.get(1));
        GT_ModHandler.addExtractionRecipe(ItemList.Battery_RE_HV_Cadmium.get(1), ItemList.Battery_Hull_HV.get(1));
        GT_ModHandler.addExtractionRecipe(ItemList.Battery_RE_HV_Lithium.get(1), ItemList.Battery_Hull_HV.get(1));
        GT_ModHandler.addExtractionRecipe(ItemList.Battery_RE_HV_Sodium.get(1), ItemList.Battery_Hull_HV.get(1));

        GT_Values.RA.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cadmium, 2), ItemList.Battery_Hull_LV.get(1), ItemList.Battery_RE_LV_Cadmium.get(1), null, 100, 2);
        GT_Values.RA.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lithium, 2), ItemList.Battery_Hull_LV.get(1), ItemList.Battery_RE_LV_Lithium.get(1), null, 100, 2);
        GT_Values.RA.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 2), ItemList.Battery_Hull_LV.get(1), ItemList.Battery_RE_LV_Sodium.get(1), null, 100, 2);
        GT_Values.RA.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cadmium, 8), ItemList.Battery_Hull_MV.get(1), ItemList.Battery_RE_MV_Cadmium.get(1), null, 400, 2);
        GT_Values.RA.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lithium, 8), ItemList.Battery_Hull_MV.get(1), ItemList.Battery_RE_MV_Lithium.get(1), null, 400, 2);
        GT_Values.RA.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 8), ItemList.Battery_Hull_MV.get(1), ItemList.Battery_RE_MV_Sodium.get(1), null, 400, 2);
        GT_Values.RA.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cadmium, 32), ItemList.Battery_Hull_HV.get(1), ItemList.Battery_RE_HV_Cadmium.get(1), null, 1600, 2);
        GT_Values.RA.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lithium, 32), ItemList.Battery_Hull_HV.get(1), ItemList.Battery_RE_HV_Lithium.get(1), null, 1600, 2);
        GT_Values.RA.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 32), ItemList.Battery_Hull_HV.get(1), ItemList.Battery_RE_HV_Sodium.get(1), null, 1600, 2);

        ItemList.Energy_LapotronicOrb.set(addItem(tLastID = 597, "Lapotronic Energy Orb", "Reusable battery", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 16), OrePrefixes.battery.get(Materials.Ultimate)}));
        setElectricStats(32000 + tLastID, 100000000L, GT_Values.V[5], 5L, -3L, true);

        ItemList.ZPM.set(addItem(tLastID = 598, "Zero Point Module", "Single use battery", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 64), new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 64)}));
        setElectricStats(32000 + tLastID, 2000000000000L, GT_Values.V[7], 7L, -2L, true);

        ItemList.Energy_LapotronicOrb2.set(addItem(tLastID = 599, "Lapotronic Energy Orb Cluster", "Reusable battery", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 16), OrePrefixes.battery.get(Materials.Ultimate)}));
        setElectricStats(32000 + tLastID, 1000000000L, GT_Values.V[6], 6L, -3L, true);
        


        ItemList.Energy_Module.set(addItem(tLastID = 736, "Energy Module", "Reusable battery", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 16), OrePrefixes.battery.get(Materials.Ultimate)}));
        setElectricStats(32000 + tLastID, 10000000000L, GT_Values.V[7], 7L, -3L, true);

        ItemList.Energy_Cluster.set(addItem(tLastID = 737, "Energy Cluster", "Reusable battery", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 64), new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 64)}));
        setElectricStats(32000 + tLastID, 100000000000L, GT_Values.V[8], 8, -3L, true);

        ItemList.Electric_Motor_LV.set(addItem(tLastID = 600, "Electric Motor (LV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 1)}));
        ItemList.Electric_Motor_MV.set(addItem(tLastID = 601, "Electric Motor (MV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 2)}));
        ItemList.Electric_Motor_HV.set(addItem(tLastID = 602, "Electric Motor (HV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 4)}));
        ItemList.Electric_Motor_EV.set(addItem(tLastID = 603, "Electric Motor (EV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 8)}));
        ItemList.Electric_Motor_IV.set(addItem(tLastID = 604, "Electric Motor (IV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 16)}));
        ItemList.Electric_Motor_LuV.set(addItem(tLastID = 606, "Electric Motor (LuV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 24), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 24), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 24)}));
        ItemList.Electric_Motor_ZPM.set(addItem(tLastID = 607, "Electric Motor (ZPM)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 32), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 32), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 32)}));
        ItemList.Electric_Motor_UV.set(addItem(tLastID = 608, "Electric Motor (UV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 48), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 48), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 48)}));

        GT_ModHandler.addCraftingRecipe(ItemList.Electric_Motor_LV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"CWR", "WIW", "RWC", 'I', OrePrefixes.stick.get(Materials.IronMagnetic), 'R', OrePrefixes.stick.get(Materials.AnyIron), 'W', OrePrefixes.wireGt01.get(Materials.AnyCopper), 'C', OrePrefixes.cableGt01.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(ItemList.Electric_Motor_LV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{"CWR", "WIW", "RWC", 'I', OrePrefixes.stick.get(Materials.SteelMagnetic), 'R', OrePrefixes.stick.get(Materials.Steel), 'W', OrePrefixes.wireGt01.get(Materials.AnyCopper), 'C', OrePrefixes.cableGt01.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(ItemList.Electric_Motor_MV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"CWR", "WIW", "RWC", 'I', OrePrefixes.stick.get(Materials.SteelMagnetic), 'R', OrePrefixes.stick.get(Materials.Aluminium), 'W', OrePrefixes.wireGt02.get(Materials.AnyCopper), 'C', OrePrefixes.cableGt01.get(Materials.AnyCopper)});
        GT_ModHandler.addCraftingRecipe(ItemList.Electric_Motor_HV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"CWR", "WIW", "RWC", 'I', OrePrefixes.stick.get(Materials.SteelMagnetic), 'R', OrePrefixes.stick.get(Materials.StainlessSteel), 'W', OrePrefixes.wireGt04.get(Materials.AnyCopper), 'C', OrePrefixes.cableGt01.get(Materials.Gold)});
        GT_ModHandler.addCraftingRecipe(ItemList.Electric_Motor_EV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"CWR", "WIW", "RWC", 'I', OrePrefixes.stick.get(Materials.NeodymiumMagnetic), 'R', OrePrefixes.stick.get(Materials.Titanium), 'W', OrePrefixes.wireGt08.get(Materials.AnnealedCopper), 'C', OrePrefixes.cableGt01.get(Materials.Aluminium)});
        GT_ModHandler.addCraftingRecipe(ItemList.Electric_Motor_IV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"CWR", "WIW", "RWC", 'I', OrePrefixes.stick.get(Materials.NeodymiumMagnetic), 'R', OrePrefixes.stick.get(Materials.TungstenSteel), 'W', OrePrefixes.wireGt16.get(Materials.AnnealedCopper), 'C', OrePrefixes.cableGt01.get(Materials.Tungsten)});

        ItemList.Electric_Pump_LV.set(addItem(tLastID = 610, "Electric Pump (LV)", "640 L/sec (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.AQUA, 1)}));
        ItemList.Electric_Pump_MV.set(addItem(tLastID = 611, "Electric Pump (MV)", "2560 L/sec (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.AQUA, 2)}));
        ItemList.Electric_Pump_HV.set(addItem(tLastID = 612, "Electric Pump (HV)", "10240 L/sec (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.AQUA, 4)}));
        ItemList.Electric_Pump_EV.set(addItem(tLastID = 613, "Electric Pump (EV)", "40.960 L/sec (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.AQUA, 8)}));
        ItemList.Electric_Pump_IV.set(addItem(tLastID = 614, "Electric Pump (IV)", "163.840 L/sec (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.AQUA, 16)}));
        ItemList.Electric_Pump_LuV.set(addItem(tLastID = 620, "Electric Pump (LuV)", "655.360 L/sec (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.AQUA, 16)}));
        ItemList.Electric_Pump_ZPM.set(addItem(tLastID = 621, "Electric Pump (ZPM)", "2.621.440 L/sec (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.AQUA, 16)}));
        ItemList.Electric_Pump_UV.set(addItem(tLastID = 622, "Electric Pump (UV)", "10.485.760 L/sec (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.AQUA, 16)}));

        GregTech_API.registerCover(ItemList.Electric_Pump_LV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[1][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_PUMP)), new GT_Cover_Pump(32));
        GregTech_API.registerCover(ItemList.Electric_Pump_MV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_PUMP)), new GT_Cover_Pump(128));
        GregTech_API.registerCover(ItemList.Electric_Pump_HV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[3][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_PUMP)), new GT_Cover_Pump(512));
        GregTech_API.registerCover(ItemList.Electric_Pump_EV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[4][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_PUMP)), new GT_Cover_Pump(2048));
        GregTech_API.registerCover(ItemList.Electric_Pump_IV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[5][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_PUMP)), new GT_Cover_Pump(8192));
        GregTech_API.registerCover(ItemList.Electric_Pump_LuV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[6][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_PUMP)), new GT_Cover_Pump(32768));
        GregTech_API.registerCover(ItemList.Electric_Pump_ZPM.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[7][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_PUMP)), new GT_Cover_Pump(131072));
        GregTech_API.registerCover(ItemList.Electric_Pump_UV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[8][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_PUMP)), new GT_Cover_Pump(524288));

        ItemList.FluidRegulator_LV.set(addItem(tLastID = 615, "Fluid Regulator (LV)", "Configuable up to 640 L/sec (as Cover)/n Rightclick/Screwdriver-rightclick/Shift-screwdriver-rightclick/n to adjust the pump speed by 1/16/256 L/sec per click", new Object[]{}));
        ItemList.FluidRegulator_MV.set(addItem(tLastID = 616, "Fluid Regulator (MV)", "Configuable up to 2560 L/sec (as Cover)/n Rightclick/Screwdriver-rightclick/Shift-screwdriver-rightclick/n to adjust the pump speed by 1/16/256 L/sec per click", new Object[]{}));
        ItemList.FluidRegulator_HV.set(addItem(tLastID = 617, "Fluid Regulator (HV)", "Configuable up to 10240 L/sec (as Cover)/n Rightclick/Screwdriver-rightclick/Shift-screwdriver-rightclick/n to adjust the pump speed by 1/16/256 L/sec per click", new Object[]{}));
        ItemList.FluidRegulator_EV.set(addItem(tLastID = 618, "Fluid Regulator (EV)", "Configuable up to 40960 L/sec (as Cover)/n Rightclick/Screwdriver-rightclick/Shift-screwdriver-rightclick/n to adjust the pump speed by 1/16/256 L/sec per click", new Object[]{}));
        ItemList.FluidRegulator_IV.set(addItem(tLastID = 619, "Fluid Regulator (IV)", "Configuable up to 163840 L/sec (as Cover)/n Rightclick/Screwdriver-rightclick/Shift-screwdriver-rightclick/n to adjust the pump speed by 1/16/256 L/sec per click", new Object[]{}));

        GregTech_API.registerCover(ItemList.FluidRegulator_LV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[1][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_PUMP)), new GT_Cover_FluidRegulator(32));
        GregTech_API.registerCover(ItemList.FluidRegulator_MV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_PUMP)), new GT_Cover_FluidRegulator(128));
        GregTech_API.registerCover(ItemList.FluidRegulator_HV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[3][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_PUMP)), new GT_Cover_FluidRegulator(512));
        GregTech_API.registerCover(ItemList.FluidRegulator_EV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[4][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_PUMP)), new GT_Cover_FluidRegulator(2048));
        GregTech_API.registerCover(ItemList.FluidRegulator_IV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[5][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_PUMP)), new GT_Cover_FluidRegulator(8192));

        ItemList.FluidFilter.set(addItem(tLastID = 635, "Fluid Filter", "Set with Fluid Container to only accept one Fluid Type", new Object[]{}));
        GregTech_API.registerCover(ItemList.FluidFilter.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[1][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_SHUTTER)), new GT_Cover_Fluidfilter());

        /**ItemList.Rotor_LV.set(addItem(tLastID = 620, "Tin Rotor", "", new Object[] { OrePrefixes.rotor.get(Materials.Tin), new Aspects.AspectStack(Aspects.METALLUM, 1), new Aspects.AspectStack(Aspects.MOTUS, 1) }));
         ItemList.Rotor_MV.set(addItem(tLastID = 621, "Bronze Rotor", "", new Object[] { OrePrefixes.rotor.get(Materials.Bronze), new Aspects.AspectStack(Aspects.METALLUM, 2), new Aspects.AspectStack(Aspects.MOTUS, 2) }));
         ItemList.Rotor_HV.set(addItem(tLastID = 622, "Steel Rotor", "", new Object[] { OrePrefixes.rotor.get(Materials.Steel), new Aspects.AspectStack(Aspects.METALLUM, 4), new Aspects.AspectStack(Aspects.MOTUS, 4) }));
         ItemList.Rotor_EV.set(addItem(tLastID = 623, "Stainless Steel Rotor", "", new Object[] { OrePrefixes.rotor.get(Materials.StainlessSteel), new Aspects.AspectStack(Aspects.METALLUM, 8), new Aspects.AspectStack(Aspects.MOTUS, 8) }));
         ItemList.Rotor_IV.set(addItem(tLastID = 624, "Tungstensteel Rotor", "", new Object[] { OrePrefixes.rotor.get(Materials.TungstenSteel), new Aspects.AspectStack(Aspects.METALLUM, 16), new Aspects.AspectStack(Aspects.MOTUS, 16) }));
         ItemList.Rotor_LuV.set(ItemList.Rotor_IV.get(1));
         ItemList.Rotor_ZPM.set(ItemList.Rotor_LuV.get(1));
         ItemList.Rotor_UV.set(ItemList.Rotor_ZPM.get(1));**/

        GT_ModHandler.addCraftingRecipe(ItemList.Electric_Pump_LV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"SXO", "dPw", "OMW", 'M', ItemList.Electric_Motor_LV, 'O', OrePrefixes.ring.get(Materials.Paper ), 'X', OrePrefixes.rotor.get(Materials.Tin), 'S', OrePrefixes.screw.get(Materials.Tin), 'W', OrePrefixes.cableGt01.get(Materials.Tin), 'P', OrePrefixes.pipeMedium.get(Materials.Bronze)});
        GT_ModHandler.addCraftingRecipe(ItemList.Electric_Pump_LV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"SXO", "dPw", "OMW", 'M', ItemList.Electric_Motor_LV, 'O', OrePrefixes.ring.get(Materials.AnyRubber), 'X', OrePrefixes.rotor.get(Materials.Tin), 'S', OrePrefixes.screw.get(Materials.Tin), 'W', OrePrefixes.cableGt01.get(Materials.Tin), 'P', OrePrefixes.pipeMedium.get(Materials.Bronze)});
        GT_ModHandler.addCraftingRecipe(ItemList.Electric_Pump_MV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"SXO", "dPw", "OMW", 'M', ItemList.Electric_Motor_MV, 'O', OrePrefixes.ring.get(Materials.AnyRubber), 'X', OrePrefixes.rotor.get(Materials.Bronze), 'S', OrePrefixes.screw.get(Materials.Bronze), 'W', OrePrefixes.cableGt01.get(Materials.AnyCopper), 'P', OrePrefixes.pipeMedium.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(ItemList.Electric_Pump_HV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"SXO", "dPw", "OMW", 'M', ItemList.Electric_Motor_HV, 'O', OrePrefixes.ring.get(Materials.AnyRubber), 'X', OrePrefixes.rotor.get(Materials.Steel), 'S', OrePrefixes.screw.get(Materials.Steel), 'W', OrePrefixes.cableGt01.get(Materials.Gold), 'P', OrePrefixes.pipeMedium.get(Materials.StainlessSteel)});
        GT_ModHandler.addCraftingRecipe(ItemList.Electric_Pump_EV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"SXO", "dPw", "OMW", 'M', ItemList.Electric_Motor_EV, 'O', OrePrefixes.ring.get(Materials.AnyRubber), 'X', OrePrefixes.rotor.get(Materials.StainlessSteel), 'S', OrePrefixes.screw.get(Materials.StainlessSteel), 'W', OrePrefixes.cableGt01.get(Materials.Aluminium), 'P', OrePrefixes.pipeMedium.get(Materials.Titanium)});
        GT_ModHandler.addCraftingRecipe(ItemList.Electric_Pump_IV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"SXO", "dPw", "OMW", 'M', ItemList.Electric_Motor_IV, 'O', OrePrefixes.ring.get(Materials.AnySyntheticRubber), 'X', OrePrefixes.rotor.get(Materials.TungstenSteel), 'S', OrePrefixes.screw.get(Materials.TungstenSteel), 'W', OrePrefixes.cableGt01.get(Materials.Tungsten), 'P', OrePrefixes.pipeMedium.get(Materials.TungstenSteel)});

        ItemList.Conveyor_Module_LV.set(addItem(tLastID = 630, "Conveyor Module (LV)", "1 Stack every 20 secs (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 1)}));
        ItemList.Conveyor_Module_MV.set(addItem(tLastID = 631, "Conveyor Module (MV)", "1 Stack every 5 secs (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 2)}));
        ItemList.Conveyor_Module_HV.set(addItem(tLastID = 632, "Conveyor Module (HV)", "1 Stack every 1 sec (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 4)}));
        ItemList.Conveyor_Module_EV.set(addItem(tLastID = 633, "Conveyor Module (EV)", "1 Stack every 1/5 sec (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 8)}));
        ItemList.Conveyor_Module_IV.set(addItem(tLastID = 634, "Conveyor Module (IV)", "1 Stack every 1/20 sec (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 16)}));
        ItemList.Conveyor_Module_LuV.set(addItem(tLastID = 636, "Conveyor Module (LuV)", "1 Stack every 1/20 sec (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 16)}));
        ItemList.Conveyor_Module_ZPM.set(addItem(tLastID = 637, "Conveyor Module (ZPM)", "1 Stack every 1/20 sec (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 16)}));
        ItemList.Conveyor_Module_UV.set(addItem(tLastID = 638, "Conveyor Module (UV)", "1 Stack every 1/20 sec (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 16)}));

        GT_ModHandler.addCraftingRecipe(ItemList.Conveyor_Module_LV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"RRR", "MCM", "RRR", 'M', ItemList.Electric_Motor_LV, 'C', OrePrefixes.cableGt01.get(Materials.Tin), 'R', OrePrefixes.plate.get(Materials.AnyRubber)});
        GT_ModHandler.addCraftingRecipe(ItemList.Conveyor_Module_MV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"RRR", "MCM", "RRR", 'M', ItemList.Electric_Motor_MV, 'C', OrePrefixes.cableGt01.get(Materials.AnyCopper), 'R', OrePrefixes.plate.get(Materials.AnyRubber)});
        GT_ModHandler.addCraftingRecipe(ItemList.Conveyor_Module_HV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"RRR", "MCM", "RRR", 'M', ItemList.Electric_Motor_HV, 'C', OrePrefixes.cableGt01.get(Materials.Gold), 'R', OrePrefixes.plate.get(Materials.AnyRubber)});
        GT_ModHandler.addCraftingRecipe(ItemList.Conveyor_Module_EV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"RRR", "MCM", "RRR", 'M', ItemList.Electric_Motor_EV, 'C', OrePrefixes.cableGt01.get(Materials.Aluminium), 'R', OrePrefixes.plate.get(Materials.AnyRubber)});
        GT_ModHandler.addCraftingRecipe(ItemList.Conveyor_Module_IV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"RRR", "MCM", "RRR", 'M', ItemList.Electric_Motor_IV, 'C', OrePrefixes.cableGt01.get(Materials.Tungsten), 'R', OrePrefixes.plate.get(Materials.AnySyntheticRubber)});

        GregTech_API.registerCover(ItemList.Conveyor_Module_LV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[1][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_CONVEYOR)), new GT_Cover_Conveyor(400));
        GregTech_API.registerCover(ItemList.Conveyor_Module_MV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_CONVEYOR)), new GT_Cover_Conveyor(100));
        GregTech_API.registerCover(ItemList.Conveyor_Module_HV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[3][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_CONVEYOR)), new GT_Cover_Conveyor(20));
        GregTech_API.registerCover(ItemList.Conveyor_Module_EV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[4][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_CONVEYOR)), new GT_Cover_Conveyor(4));
        GregTech_API.registerCover(ItemList.Conveyor_Module_IV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[5][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_CONVEYOR)), new GT_Cover_Conveyor(1));
        GregTech_API.registerCover(ItemList.Conveyor_Module_LuV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[6][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_CONVEYOR)), new GT_Cover_Conveyor(1));
        GregTech_API.registerCover(ItemList.Conveyor_Module_ZPM.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[7][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_CONVEYOR)), new GT_Cover_Conveyor(1));
        GregTech_API.registerCover(ItemList.Conveyor_Module_UV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[8][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_CONVEYOR)), new GT_Cover_Conveyor(1));

        ItemList.Electric_Piston_LV.set(addItem(tLastID = 640, "Electric Piston (LV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 1)}));
        ItemList.Electric_Piston_MV.set(addItem(tLastID = 641, "Electric Piston (MV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 2)}));
        ItemList.Electric_Piston_HV.set(addItem(tLastID = 642, "Electric Piston (HV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 4)}));
        ItemList.Electric_Piston_EV.set(addItem(tLastID = 643, "Electric Piston (EV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 8)}));
        ItemList.Electric_Piston_IV.set(addItem(tLastID = 644, "Electric Piston (IV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 32), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 16)}));
        ItemList.Electric_Piston_LuV.set(addItem(tLastID = 645, "Electric Piston (LuV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 32), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 16)}));
        ItemList.Electric_Piston_ZPM.set(addItem(tLastID = 646, "Electric Piston (ZPM)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 32), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 16)}));
        ItemList.Electric_Piston_UV.set(addItem(tLastID = 647, "Electric Piston (UV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 32), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 16)}));

        GT_ModHandler.addCraftingRecipe(ItemList.Electric_Piston_LV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"PPP", "CSS", "CMG", 'P', OrePrefixes.plate.get(Materials.Steel), 'S', OrePrefixes.stick.get(Materials.Steel), 'G', OrePrefixes.gearGtSmall.get(Materials.Steel), 'M', ItemList.Electric_Motor_LV, 'C', OrePrefixes.cableGt01.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(ItemList.Electric_Piston_MV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"PPP", "CSS", "CMG", 'P', OrePrefixes.plate.get(Materials.Aluminium), 'S', OrePrefixes.stick.get(Materials.Aluminium), 'G', OrePrefixes.gearGtSmall.get(Materials.Aluminium), 'M', ItemList.Electric_Motor_MV, 'C', OrePrefixes.cableGt01.get(Materials.AnyCopper)});
        GT_ModHandler.addCraftingRecipe(ItemList.Electric_Piston_HV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"PPP", "CSS", "CMG", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'S', OrePrefixes.stick.get(Materials.StainlessSteel), 'G', OrePrefixes.gearGtSmall.get(Materials.StainlessSteel), 'M', ItemList.Electric_Motor_HV, 'C', OrePrefixes.cableGt01.get(Materials.Gold)});
        GT_ModHandler.addCraftingRecipe(ItemList.Electric_Piston_EV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"PPP", "CSS", "CMG", 'P', OrePrefixes.plate.get(Materials.Titanium), 'S', OrePrefixes.stick.get(Materials.Titanium), 'G', OrePrefixes.gearGtSmall.get(Materials.Titanium), 'M', ItemList.Electric_Motor_EV, 'C', OrePrefixes.cableGt01.get(Materials.Aluminium)});
        GT_ModHandler.addCraftingRecipe(ItemList.Electric_Piston_IV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"PPP", "CSS", "CMG", 'P', OrePrefixes.plate.get(Materials.TungstenSteel), 'S', OrePrefixes.stick.get(Materials.TungstenSteel), 'G', OrePrefixes.gearGtSmall.get(Materials.TungstenSteel), 'M', ItemList.Electric_Motor_IV, 'C', OrePrefixes.cableGt01.get(Materials.Tungsten)});

        ItemList.Robot_Arm_LV.set(addItem(tLastID = 650, "Robot Arm (LV)", "Inserts into specific Slots (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 1)}));
        ItemList.Robot_Arm_MV.set(addItem(tLastID = 651, "Robot Arm (MV)", "Inserts into specific Slots (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 2)}));
        ItemList.Robot_Arm_HV.set(addItem(tLastID = 652, "Robot Arm (HV)", "Inserts into specific Slots (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 4)}));
        ItemList.Robot_Arm_EV.set(addItem(tLastID = 653, "Robot Arm (EV)", "Inserts into specific Slots (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 8)}));
        ItemList.Robot_Arm_IV.set(addItem(tLastID = 654, "Robot Arm (IV)", "Inserts into specific Slots (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 32), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 16)}));
        ItemList.Robot_Arm_LuV.set(addItem(tLastID = 655, "Robot Arm (LuV)", "Inserts into specific Slots (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 32), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 16)}));
        ItemList.Robot_Arm_ZPM.set(addItem(tLastID = 656, "Robot Arm (ZPM)", "Inserts into specific Slots (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 32), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 16)}));
        ItemList.Robot_Arm_UV.set(addItem(tLastID = 657, "Robot Arm (UV)", "Inserts into specific Slots (as Cover)", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 32), new TC_Aspects.TC_AspectStack(TC_Aspects.MOTUS, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 16)}));

        GT_ModHandler.addCraftingRecipe(ItemList.Robot_Arm_LV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"CCC", "MSM", "PES", 'S', OrePrefixes.stick.get(Materials.Steel), 'M', ItemList.Electric_Motor_LV, 'P', ItemList.Electric_Piston_LV, 'E', OrePrefixes.circuit.get(Materials.Basic), 'C', OrePrefixes.cableGt01.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(ItemList.Robot_Arm_MV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"CCC", "MSM", "PES", 'S', OrePrefixes.stick.get(Materials.Aluminium), 'M', ItemList.Electric_Motor_MV, 'P', ItemList.Electric_Piston_MV, 'E', OrePrefixes.circuit.get(Materials.Good), 'C', OrePrefixes.cableGt01.get(Materials.AnyCopper)});
        GT_ModHandler.addCraftingRecipe(ItemList.Robot_Arm_HV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"CCC", "MSM", "PES", 'S', OrePrefixes.stick.get(Materials.StainlessSteel), 'M', ItemList.Electric_Motor_HV, 'P', ItemList.Electric_Piston_HV, 'E', OrePrefixes.circuit.get(Materials.Advanced), 'C', OrePrefixes.cableGt01.get(Materials.Gold)});
        GT_ModHandler.addCraftingRecipe(ItemList.Robot_Arm_EV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"CCC", "MSM", "PES", 'S', OrePrefixes.stick.get(Materials.Titanium), 'M', ItemList.Electric_Motor_EV, 'P', ItemList.Electric_Piston_EV, 'E', OrePrefixes.circuit.get(Materials.Data), 'C', OrePrefixes.cableGt01.get(Materials.Aluminium)});
        GT_ModHandler.addCraftingRecipe(ItemList.Robot_Arm_IV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"CCC", "MSM", "PES", 'S', OrePrefixes.stick.get(Materials.TungstenSteel), 'M', ItemList.Electric_Motor_IV, 'P', ItemList.Electric_Piston_IV, 'E', OrePrefixes.circuit.get(Materials.Elite), 'C', OrePrefixes.cableGt01.get(Materials.Tungsten)});

        GregTech_API.registerCover(ItemList.Robot_Arm_LV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[1][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ARM)), new GT_Cover_Arm(400));
        GregTech_API.registerCover(ItemList.Robot_Arm_MV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ARM)), new GT_Cover_Arm(100));
        GregTech_API.registerCover(ItemList.Robot_Arm_HV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[3][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ARM)), new GT_Cover_Arm(20));
        GregTech_API.registerCover(ItemList.Robot_Arm_EV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[4][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ARM)), new GT_Cover_Arm(4));
        GregTech_API.registerCover(ItemList.Robot_Arm_IV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[5][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ARM)), new GT_Cover_Arm(1));
        GregTech_API.registerCover(ItemList.Robot_Arm_LuV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[6][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ARM)), new GT_Cover_Arm(1));
        GregTech_API.registerCover(ItemList.Robot_Arm_ZPM.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[7][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ARM)), new GT_Cover_Arm(1));
        GregTech_API.registerCover(ItemList.Robot_Arm_UV.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[8][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ARM)), new GT_Cover_Arm(1));

        ItemList.Field_Generator_LV.set(addItem(tLastID = 670, "Field Generator (LV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.TUTAMEN, 1)}));
        ItemList.Field_Generator_MV.set(addItem(tLastID = 671, "Field Generator (MV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.TUTAMEN, 2)}));
        ItemList.Field_Generator_HV.set(addItem(tLastID = 672, "Field Generator (HV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.TUTAMEN, 4)}));
        ItemList.Field_Generator_EV.set(addItem(tLastID = 673, "Field Generator (EV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.TUTAMEN, 8)}));
        ItemList.Field_Generator_IV.set(addItem(tLastID = 674, "Field Generator (IV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 32), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.TUTAMEN, 16)}));
        ItemList.Field_Generator_LuV.set(addItem(tLastID = 675, "Field Generator (LuV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 32), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.TUTAMEN, 16)}));
        ItemList.Field_Generator_ZPM.set(addItem(tLastID = 676, "Field Generator (ZPM)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 32), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.TUTAMEN, 16)}));
        ItemList.Field_Generator_UV.set(addItem(tLastID = 677, "Field Generator (UV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 32), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.TUTAMEN, 16)}));

        GT_ModHandler.addCraftingRecipe(ItemList.Field_Generator_LV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"WCW", "CGC", "WCW", 'G', OrePrefixes.gem.get(Materials.EnderPearl), 'C', OrePrefixes.circuit.get(Materials.Basic), 'W', OrePrefixes.wireGt01.get(Materials.Osmium)});
        GT_ModHandler.addCraftingRecipe(ItemList.Field_Generator_MV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"WCW", "CGC", "WCW", 'G', OrePrefixes.gem.get(Materials.EnderEye), 'C', OrePrefixes.circuit.get(Materials.Good), 'W', OrePrefixes.wireGt02.get(Materials.Osmium)});
        
        ItemList.Emitter_LV.set(addItem(tLastID = 680, "Emitter (LV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.LUX, 1)}));
        ItemList.Emitter_MV.set(addItem(tLastID = 681, "Emitter (MV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.LUX, 2)}));
        ItemList.Emitter_HV.set(addItem(tLastID = 682, "Emitter (HV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.LUX, 4)}));
        ItemList.Emitter_EV.set(addItem(tLastID = 683, "Emitter (EV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.LUX, 8)}));
        ItemList.Emitter_IV.set(addItem(tLastID = 684, "Emitter (IV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.LUX, 16)}));
        ItemList.Emitter_LuV.set(addItem(tLastID = 685, "Emitter (LuV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.LUX, 16)}));
        ItemList.Emitter_ZPM.set(addItem(tLastID = 686, "Emitter (ZPM)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.LUX, 16)}));
        ItemList.Emitter_UV.set(addItem(tLastID = 687, "Emitter (UV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.LUX, 16)}));

        GT_ModHandler.addCraftingRecipe(ItemList.Emitter_LV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"SSC", "WQS", "CWS", 'Q', OrePrefixes.gem.get(Materials.Quartzite), 'S', OrePrefixes.stick.get(Materials.Brass), 'C', OrePrefixes.circuit.get(Materials.Basic), 'W', OrePrefixes.cableGt01.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(ItemList.Emitter_MV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"SSC", "WQS", "CWS", 'Q', OrePrefixes.gem.get(Materials.NetherQuartz), 'S', OrePrefixes.stick.get(Materials.Electrum), 'C', OrePrefixes.circuit.get(Materials.Good), 'W', OrePrefixes.cableGt01.get(Materials.AnyCopper)});
        GT_ModHandler.addCraftingRecipe(ItemList.Emitter_HV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"SSC", "WQS", "CWS", 'Q', OrePrefixes.gem.get(Materials.Emerald), 'S', OrePrefixes.stick.get(Materials.Chrome), 'C', OrePrefixes.circuit.get(Materials.Advanced), 'W', OrePrefixes.cableGt01.get(Materials.Gold)});
        GT_ModHandler.addCraftingRecipe(ItemList.Emitter_EV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"SSC", "WQS", "CWS", 'Q', OrePrefixes.gem.get(Materials.EnderPearl), 'S', OrePrefixes.stick.get(Materials.Platinum), 'C', OrePrefixes.circuit.get(Materials.Data), 'W', OrePrefixes.cableGt01.get(Materials.Aluminium)});
        GT_ModHandler.addCraftingRecipe(ItemList.Emitter_IV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"SSC", "WQS", "CWS", 'Q', OrePrefixes.gem.get(Materials.EnderEye), 'S', OrePrefixes.stick.get(Materials.Osmium), 'C', OrePrefixes.circuit.get(Materials.Elite), 'W', OrePrefixes.cableGt01.get(Materials.Tungsten)});
        
        ItemList.Sensor_LV.set(addItem(tLastID = 690, "Sensor (LV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.SENSUS, 1)}));
        ItemList.Sensor_MV.set(addItem(tLastID = 691, "Sensor (MV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.SENSUS, 2)}));
        ItemList.Sensor_HV.set(addItem(tLastID = 692, "Sensor (HV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.SENSUS, 4)}));
        ItemList.Sensor_EV.set(addItem(tLastID = 693, "Sensor (EV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.SENSUS, 8)}));
        ItemList.Sensor_IV.set(addItem(tLastID = 694, "Sensor (IV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.SENSUS, 16)}));
        ItemList.Sensor_LuV.set(addItem(tLastID = 695, "Sensor (LuV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.SENSUS, 16)}));
        ItemList.Sensor_ZPM.set(addItem(tLastID = 696, "Sensor (ZPM)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.SENSUS, 16)}));
        ItemList.Sensor_UV.set(addItem(tLastID = 697, "Sensor (UV)", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.SENSUS, 16)}));

        GT_ModHandler.addCraftingRecipe(ItemList.Sensor_LV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"P Q", "PS ", "CPP", 'Q', OrePrefixes.gem.get(Materials.Quartzite), 'S', OrePrefixes.stick.get(Materials.Brass), 'P', OrePrefixes.plate.get(Materials.Steel), 'C', OrePrefixes.circuit.get(Materials.Basic)});
        GT_ModHandler.addCraftingRecipe(ItemList.Sensor_MV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"P Q", "PS ", "CPP", 'Q', OrePrefixes.gem.get(Materials.NetherQuartz), 'S', OrePrefixes.stick.get(Materials.Electrum), 'P', OrePrefixes.plate.get(Materials.Aluminium), 'C', OrePrefixes.circuit.get(Materials.Good)});
        GT_ModHandler.addCraftingRecipe(ItemList.Sensor_HV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"P Q", "PS ", "CPP", 'Q', OrePrefixes.gem.get(Materials.Emerald), 'S', OrePrefixes.stick.get(Materials.Chrome), 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'C', OrePrefixes.circuit.get(Materials.Advanced)});
        GT_ModHandler.addCraftingRecipe(ItemList.Sensor_EV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"P Q", "PS ", "CPP", 'Q', OrePrefixes.gem.get(Materials.EnderPearl), 'S', OrePrefixes.stick.get(Materials.Platinum), 'P', OrePrefixes.plate.get(Materials.Titanium), 'C', OrePrefixes.circuit.get(Materials.Data)});
        GT_ModHandler.addCraftingRecipe(ItemList.Sensor_IV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"P Q", "PS ", "CPP", 'Q', OrePrefixes.gem.get(Materials.EnderEye), 'S', OrePrefixes.stick.get(Materials.Osmium), 'P', OrePrefixes.plate.get(Materials.TungstenSteel), 'C', OrePrefixes.circuit.get(Materials.Elite)});

        ItemList.Circuit_Primitive.set(addItem(tLastID = 700, "Vacuum Tube", "A very simple Circuit", new Object[]{OrePrefixes.circuit.get(Materials.Primitive), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Parts_Vacuum_Tube.set(ItemList.Circuit_Primitive.get(1));
        ItemList.Circuit_Basic.set(addItem(tLastID = 701, "Integrated Logic Circuit", "A Basic Circuit", new Object[]{OrePrefixes.circuit.get(Materials.Basic), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Good.set(addItem(tLastID = 702, "Good Electronic Circuit", "A Good Circuit", new Object[]{OrePrefixes.circuit.get(Materials.Good), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Advanced.set(addItem(tLastID = 703, "Processor Assembly", "An Advanced Circuit", new Object[]{OrePrefixes.circuit.get(Materials.Advanced), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Computer.set(ItemList.Circuit_Advanced.get(1));
        ItemList.Circuit_Data.set(addItem(tLastID = 704, "Workstation", "An Extreme Circuit", new Object[]{OrePrefixes.circuit.get(Materials.Data), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Elite.set(addItem(tLastID = 705, "Mainframe", "An Elite Circuit", new Object[]{OrePrefixes.circuit.get(Materials.Elite), SubTag.NO_UNIFICATION}));
        ItemList.Circuit_Master.set(addItem(tLastID = 706, "Nanoprocessor Mainframe", "A Master Circuit", new Object[]{OrePrefixes.circuit.get(Materials.Master), SubTag.NO_UNIFICATION}));
        ItemList.Tool_DataOrb.set(addItem(tLastID = 707, "Data Orb", "A High Capacity Data Storage", new Object[]{SubTag.NO_UNIFICATION, new Behaviour_DataOrb()}));
        GT_ModHandler.addShapelessCraftingRecipe(ItemList.Tool_DataOrb.get(1), GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{ItemList.Tool_DataOrb.get(1)});
        ItemList.Tool_DataStick.set(addItem(tLastID = 708, "Data Stick", "A Low Capacity Data Storage", new Object[]{SubTag.NO_UNIFICATION, new Behaviour_DataStick()}));
        GT_ModHandler.addShapelessCraftingRecipe(ItemList.Tool_DataStick.get(1), GT_ModHandler.RecipeBits.NOT_REMOVABLE, new Object[]{ItemList.Tool_DataStick.get(1)});
        
        ItemList.Circuit_Board_Basic.set(addItem(tLastID = 710, "Coated Circuit Board", "A Basic Board", new Object[0])); ItemList.Circuit_Board_Coated.set(ItemList.Circuit_Board_Basic.get(1));
        ItemList.Circuit_Board_Advanced.set(addItem(tLastID = 711, "Epoxy Circuit Board", "An Advanced Board", new Object[0])); ItemList.Circuit_Board_Epoxy.set(ItemList.Circuit_Board_Advanced.get(1));
        ItemList.Circuit_Board_Elite.set(addItem(tLastID = 712, "Multilayer Fiber-Reinforced Circuit Board", "An Elite Board", new Object[0])); ItemList.Circuit_Board_Multifiberglass.set(ItemList.Circuit_Board_Elite.get(1));
        ItemList.Circuit_Parts_Crystal_Chip_Elite.set(addItem(tLastID = 713, "Engraved Crystal Chip", "Needed for Circuits", new Object[0]));
        ItemList.Circuit_Parts_Crystal_Chip_Master.set(addItem(tLastID = 714, "Engraved Lapotron Chip", "Needed for Circuits"));
        ItemList.Circuit_Parts_Advanced.set(addItem(tLastID = 715, "Diode", "Basic Electronic Component", new Object[0])); ItemList.Circuit_Parts_Diode.set(ItemList.Circuit_Parts_Advanced.get(1));
        ItemList.Circuit_Parts_Wiring_Basic.set(addItem(tLastID = 716, "Resistor", "Basic Electronic Component", new Object[0])); ItemList.Circuit_Parts_Resistor.set(ItemList.Circuit_Parts_Wiring_Basic.get(1));
        ItemList.Circuit_Parts_Wiring_Advanced.set(addItem(tLastID = 717, "Transistor", "Basic Electronic Component", new Object[0])); ItemList.Circuit_Parts_Transistor.set(ItemList.Circuit_Parts_Wiring_Advanced.get(1));
        ItemList.Circuit_Parts_Wiring_Elite.set(addItem(tLastID = 718, "Capacitor", "Electronic Component", new Object[0])); ItemList.Circuit_Parts_Capacitor.set(ItemList.Circuit_Parts_Wiring_Elite.get(1));
        ItemList.Empty_Board_Basic.set(addItem(tLastID = 719, "Phenolic Circuit Board", "A Good Board", new Object[0])); ItemList.Circuit_Board_Phenolic.set(ItemList.Empty_Board_Basic.get(1));
        ItemList.Empty_Board_Elite.set(addItem(tLastID = 720, "Fiber-Reinforced Circuit Board", "An Extreme Board", new Object[0])); ItemList.Circuit_Board_Fiberglass.set(ItemList.Empty_Board_Elite.get(1));


        ItemList.Component_Sawblade_Diamond.set(addItem(tLastID = 721, "Diamond Sawblade", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.INSTRUMENTUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.PERDITIO, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 4), OreDictNames.craftingDiamondBlade}));
        ItemList.Component_Grinder_Diamond.set(addItem(tLastID = 722, "Diamond Grinding Head", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.INSTRUMENTUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.PERDITIO, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 6), OreDictNames.craftingGrinder}));
        ItemList.Component_Grinder_Tungsten.set(addItem(tLastID = 723, "Tungsten Grinding Head", "", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.INSTRUMENTUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.PERDITIO, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.METALLUM, 6), OreDictNames.craftingGrinder}));

        ItemList.QuantumEye.set(addItem(tLastID = 724, "Quantum Eye", "Improved Ender Eye", new Object[0]));
        ItemList.QuantumStar.set(addItem(tLastID = 725, "Quantum Star", "Improved Nether Star", new Object[0]));
        ItemList.Gravistar.set(addItem(tLastID = 726, "Gravi Star", "Ultimate Nether Star", new Object[0]));
               
        GT_ModHandler.addCraftingRecipe(ItemList.Field_Generator_HV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"WCW", "CGC", "WCW", 'G', ItemList.QuantumEye.get(1), 'C', OrePrefixes.circuit.get(Materials.Advanced), 'W', OrePrefixes.wireGt04.get(Materials.Osmium)});
        GT_ModHandler.addCraftingRecipe(ItemList.Field_Generator_EV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"WCW", "CGC", "WCW", 'G', OrePrefixes.gem.get(Materials.NetherStar), 'C', OrePrefixes.circuit.get(Materials.Data), 'W', OrePrefixes.wireGt08.get(Materials.Osmium)});
        GT_ModHandler.addCraftingRecipe(ItemList.Field_Generator_IV.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"WCW", "CGC", "WCW", 'G', ItemList.QuantumStar.get(1), 'C', OrePrefixes.circuit.get(Materials.Elite), 'W', OrePrefixes.wireGt16.get(Materials.Osmium)});
        
        GT_ModHandler.addCraftingRecipe(ItemList.Component_Sawblade_Diamond.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{" D ", "DGD", " D ", 'D', OrePrefixes.dustSmall.get(Materials.Diamond), 'G', OrePrefixes.gearGt.get(Materials.CobaltBrass)});
        GT_ModHandler.addCraftingRecipe(ItemList.Component_Grinder_Diamond.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"DSD", "SIS", "DSD", 'I', OreDictNames.craftingIndustrialDiamond, 'D', OrePrefixes.dust.get(Materials.Diamond), 'S', OrePrefixes.plate.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(ItemList.Component_Grinder_Tungsten.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"TST", "SIS", "TST", 'I', OreDictNames.craftingIndustrialDiamond, 'T', OrePrefixes.plate.get(Materials.Tungsten), 'S', OrePrefixes.plate.get(Materials.Steel)});

        ItemList.Upgrade_Muffler.set(addItem(tLastID = 727, "Muffler Upgrade", "Makes Machines silent", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.SENSUS, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.VACUOS, 2)}));
        ItemList.Upgrade_Lock.set(addItem(tLastID = 728, "Lock Upgrade", "Protects your Machines", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.TUTAMEN, 4)}));

        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Plastic, 2), ItemList.Upgrade_Muffler.get(1), 1600, 2);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 2), ItemList.Upgrade_Muffler.get(1), 1600, 2);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Plastic, 2), ItemList.Upgrade_Muffler.get(1), 1600, 2);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 2), ItemList.Upgrade_Muffler.get(1), 1600, 2);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.WroughtIron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Plastic, 2), ItemList.Upgrade_Muffler.get(1), 1600, 2);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.WroughtIron), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 2), ItemList.Upgrade_Muffler.get(1), 1600, 2);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iridium, 1), ItemList.Upgrade_Lock.get(1), 6400, 16);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iridium, 1), ItemList.Upgrade_Lock.get(1), 6400, 16);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.WroughtIron), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iridium, 1), ItemList.Upgrade_Lock.get(1), 6400, 16);

        ItemList.Component_Filter.set(addItem(tLastID = 729, "Item Filter", "", new Object[]{new ItemData(Materials.Zinc, OrePrefixes.foil.mMaterialAmount * 16), new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.SENSUS, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 1), OreDictNames.craftingFilter}));

        GT_Values.RA.addAssemblerRecipe(ItemList.RawCarbonMesh.get(4), GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Zinc, 16), Materials.Plastic.getMolten(144), ItemList.Component_Filter.get(1), 1600, 32);

        ItemList.Cover_Controller.set(addItem(tLastID = 730, "Machine Controller", "Turns Machines ON/OFF", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ORDO, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 1)}));
        ItemList.Cover_ActivityDetector.set(addItem(tLastID = 731, "Activity Detector", "Gives out Activity as Redstone", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.SENSUS, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 1)}));
        ItemList.Cover_FluidDetector.set(addItem(tLastID = 732, "Fluid Detector", "Gives out Fluid Amount as Redstone", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.SENSUS, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.AQUA, 1)}));
        ItemList.Cover_ItemDetector.set(addItem(tLastID = 733, "Item Detector", "Gives out Item Amount as Redstone", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.SENSUS, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.TERRA, 1)}));
        ItemList.Cover_EnergyDetector.set(addItem(tLastID = 734, "Energy Detector", "Gives out Energy Amount as Redstone", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.SENSUS, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 1)}));
        ItemList.Cover_PlayerDetector.set(addItem(tLastID = 735, "Player Detector", "Gives out close Players as Redstone", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.SENSUS, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 1)}));
        GT_Values.RA.addAssemblerRecipe(ItemList.Sensor_EV.get(1), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Titanium), ItemList.Cover_PlayerDetector.get(1), 3200, 128);

        GregTech_API.registerCover(ItemList.Cover_Controller.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_CONTROLLER)), new GT_Cover_ControlsWork());
        GregTech_API.registerCover(ItemList.Cover_ActivityDetector.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ACTIVITYDETECTOR)), new GT_Cover_DoesWork());
        GregTech_API.registerCover(ItemList.Cover_FluidDetector.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_FLUIDDETECTOR)), new GT_Cover_LiquidMeter());
        GregTech_API.registerCover(ItemList.Cover_ItemDetector.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ITEMDETECTOR)), new GT_Cover_ItemMeter());
        GregTech_API.registerCover(ItemList.Cover_EnergyDetector.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ENERGYDETECTOR)), new GT_Cover_EUMeter());
        GregTech_API.registerCover(ItemList.Cover_PlayerDetector.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ACTIVITYDETECTOR)), new GT_Cover_PlayerDetector());

        ItemList.Cover_Screen.set(addItem(tLastID = 740, "Computer Monitor", "Displays Data", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.COGNITIO, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.LUX, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 1)}));
        ItemList.Cover_Crafting.set(addItem(tLastID = 744, "Crafting Table Cover", "Better than a wooden Workbench", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.FABRICO, 4)}));
        ItemList.Cover_Drain.set(addItem(tLastID = 745, "Drain", "Absorbs Fluids and collects Rain", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.VACUOS, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.AQUA, 2)}));

        ItemList.Cover_Shutter.set(addItem(tLastID = 749, "Shutter Module", "Blocks Inventory/Tank Side. Usage together with Machine Controller.", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ORDO, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.ITER, 1)}));

        GT_ModHandler.addCraftingRecipe(ItemList.Cover_Screen.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"AGA", "RPB", "ALA", 'A', OrePrefixes.plate.get(Materials.Aluminium), 'L', OrePrefixes.dust.get(Materials.Glowstone), 'R', Dyes.dyeRed, 'G', Dyes.dyeLime, 'B', Dyes.dyeBlue, 'P', OrePrefixes.plate.get(Materials.Glass)});

        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 2), new ItemStack(Blocks.iron_bars, 2), ItemList.Cover_Drain.get(1), 800, 16);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 2), new ItemStack(Blocks.iron_bars, 2), ItemList.Cover_Drain.get(1), 800, 16);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.WroughtIron, 2), new ItemStack(Blocks.iron_bars, 2), ItemList.Cover_Drain.get(1), 800, 16);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium), new ItemStack(Blocks.crafting_table, 1), ItemList.Cover_Crafting.get(1), 800, 16);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron), new ItemStack(Blocks.crafting_table, 1), ItemList.Cover_Crafting.get(1), 800, 16);
        GT_Values.RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.WroughtIron), new ItemStack(Blocks.crafting_table, 1), ItemList.Cover_Crafting.get(1), 800, 16);
          
        GregTech_API.registerCover(ItemList.Cover_Screen.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_SCREEN)), new GT_Cover_Screen());
        GregTech_API.registerCover(ItemList.Cover_Crafting.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[1][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_CRAFTING)), new GT_Cover_Crafting());
        GregTech_API.registerCover(ItemList.Cover_Drain.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[0][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_DRAIN)), new GT_Cover_Drain());
        GregTech_API.registerCover(ItemList.Cover_Shutter.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[1][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_SHUTTER)), new GT_Cover_Shutter());

        ItemList.Cover_SolarPanel.set(addItem(tLastID = 750, "Solar Panel", "May the Sun be with you", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 1), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 1)}));
        ItemList.Cover_SolarPanel_8V.set(addItem(tLastID = 751, "Solar Panel (8V)", "8 Volt Solar Panel", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 2)}));
        ItemList.Cover_SolarPanel_LV.set(addItem(tLastID = 752, "Solar Panel (LV)", "Low Voltage Solar Panel", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 4), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 4)}));
        ItemList.Cover_SolarPanel_MV.set(addItem(tLastID = 753, "Solar Panel (MV)", "Medium Voltage Solar Panel", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 8), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 8)}));
        ItemList.Cover_SolarPanel_HV.set(addItem(tLastID = 754, "Solar Panel (HV)", "High Voltage Solar Panel", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 16)}));
        ItemList.Cover_SolarPanel_EV.set(addItem(tLastID = 755, "Solar Panel (EV)", "Extreme Solar Panel", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 32), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 32), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 32)}));
        ItemList.Cover_SolarPanel_IV.set(addItem(tLastID = 756, "Solar Panel (IV)", "Insane Solar Panel", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 64), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 64), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 64)}));
        ItemList.Cover_SolarPanel_LuV.set(addItem(tLastID = 757, "Solar Panel (LuV)", "Ludicrous Solar Panel", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 64), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 64), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 64)}));
        ItemList.Cover_SolarPanel_ZPM.set(addItem(tLastID = 758, "Solar Panel (ZPM)", "ZPM Voltage Solar Panel", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 64), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 64), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 64)}));
        ItemList.Cover_SolarPanel_UV.set(addItem(tLastID = 759, "Solar Panel (UV)", "Ultimate Solar Panel", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 64), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 64), new TC_Aspects.TC_AspectStack(TC_Aspects.TENEBRAE, 64)}));

        GregTech_API.registerCover(ItemList.Cover_SolarPanel.get(1), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL), new GT_Cover_SolarPanel(1));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_8V.get(1), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_8V), new GT_Cover_SolarPanel(8));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_LV.get(1), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_LV), new GT_Cover_SolarPanel(32));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_MV.get(1), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_MV), new GT_Cover_SolarPanel(128));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_HV.get(1), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_HV), new GT_Cover_SolarPanel(512));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_EV.get(1), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_EV), new GT_Cover_SolarPanel(2048));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_IV.get(1), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_IV), new GT_Cover_SolarPanel(8192));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_LuV.get(1), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_LuV), new GT_Cover_SolarPanel(32768));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_ZPM.get(1), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_ZPM), new GT_Cover_SolarPanel(131072));
        GregTech_API.registerCover(ItemList.Cover_SolarPanel_UV.get(1), new GT_RenderedTexture(Textures.BlockIcons.SOLARPANEL_UV), new GT_Cover_SolarPanel(524288));

        ItemList.Tool_Sonictron.set(addItem(tLastID = 760, "Sonictron", "Bring your Music with you", new Object[]{Behaviour_Sonictron.INSTANCE, new TC_Aspects.TC_AspectStack(TC_Aspects.SENSUS, 4)}));
        ItemList.Tool_Cheat.set(addItem(tLastID = 761, "Debug Scanner", "Also an Infinite Energy Source", new Object[]{Behaviour_Scanner.INSTANCE, new TC_Aspects.TC_AspectStack(TC_Aspects.NEBRISUM, 64)}));
        setElectricStats(32000 + tLastID, -2000000000L, 1000000000L, -1L, -3L, false);
        ItemList.Tool_Scanner.set(addItem(tLastID = 762, "Portable Scanner", "Tricorder", new Object[]{Behaviour_Scanner.INSTANCE, new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 6), new TC_Aspects.TC_AspectStack(TC_Aspects.SENSUS, 6)}));
        setElectricStats(32000 + tLastID, 400000L, GT_Values.V[2], 2, -1L, false);
        GT_ModHandler.addCraftingRecipe(ItemList.Tool_Scanner.get(1), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"EPR", "CSC", "PBP", 'C', OrePrefixes.circuit.get(Materials.Advanced), 'P', OrePrefixes.plate.get(Materials.Aluminium), 'E', ItemList.Emitter_MV, 'R', ItemList.Sensor_MV, 'S', ItemList.Cover_Screen, 'B', ItemList.Battery_RE_MV_Lithium});
        ItemList.NC_SensorKit.set(addItem(tLastID = 763, "GregTech Sensor Kit", "", new Object[]{new Behaviour_SensorKit()}));
        ItemList.Duct_Tape.set(addItem(tLastID = 764, "BrainTech Aerospace Advanced Reinforced Duct Tape FAL-84", "If you can't fix it with this, use more of it!", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ORDO, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.INSTRUMENTUM, 2), OreDictNames.craftingDuctTape}));

       GT_Values.RA.addAssemblerRecipe(ItemList.Sensor_LV.get(1), ItemList.Emitter_LV.get(1), ItemList.NC_SensorKit.get(1), 1600, 2);

        ItemList.Cover_RedstoneTransmitterExternal.set(addItem(tLastID = 741, "Redstone Transmitter (Out)", "Transfers Redstonesignals wireless", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ORDO, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 1)}));
        ItemList.Cover_RedstoneTransmitterInternal.set(addItem(tLastID = 742, "Redstone Transmitter (In)", "Transfers Redstonesignals wireless", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ORDO, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 1)}));
        ItemList.Cover_RedstoneReceiverExternal.set(addItem(tLastID = 746, "Redstone Receiver (Out)", "Transfers Redstonesignals wireless", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ORDO, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 1)}));
        ItemList.Cover_RedstoneReceiverInternal.set(addItem(tLastID = 747, "Redstone Receiver (In)", "Transfers Redstonesignals wireless", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ORDO, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 1)}));

        GregTech_API.registerCover(ItemList.Cover_RedstoneTransmitterExternal.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ACTIVITYDETECTOR)), new GT_Cover_RedstoneTransmitterExternal());
        GregTech_API.registerCover(ItemList.Cover_RedstoneTransmitterInternal.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ACTIVITYDETECTOR)), new GT_Cover_RedstoneTransmitterInternal());
        GregTech_API.registerCover(ItemList.Cover_RedstoneReceiverExternal.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_FLUIDDETECTOR)), new GT_Cover_RedstoneReceiverExternal());
        GregTech_API.registerCover(ItemList.Cover_RedstoneReceiverInternal.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_FLUIDDETECTOR)), new GT_Cover_RedstoneReceiverInternal());

        GT_Values.RA.addAssemblerRecipe(ItemList.Emitter_EV.get(1), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.StainlessSteel, 1), ItemList.Cover_RedstoneTransmitterExternal.get(1), 3200, 128);
        GT_Values.RA.addAssemblerRecipe(ItemList.Sensor_EV.get(1), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.StainlessSteel, 1), ItemList.Cover_RedstoneReceiverExternal.get(1), 3200, 128);
        GT_ModHandler.addShapelessCraftingRecipe(ItemList.Cover_RedstoneTransmitterInternal.get(1), new Object[]{ItemList.Cover_RedstoneTransmitterExternal.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(ItemList.Cover_RedstoneReceiverInternal.get(1), new Object[]{ItemList.Cover_RedstoneReceiverExternal.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(ItemList.Cover_RedstoneTransmitterExternal.get(1), new Object[]{ItemList.Cover_RedstoneTransmitterInternal.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(ItemList.Cover_RedstoneReceiverExternal.get(1), new Object[]{ItemList.Cover_RedstoneReceiverInternal.get(1)});

        ItemList.Cover_NeedsMaintainance.set(addItem(tLastID = 748, "Needs Maintenance Cover", "Attach to Multiblock Controller. Emits Redstone Signal if needs Maintenance", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ORDO, 2), new TC_Aspects.TC_AspectStack(TC_Aspects.MACHINA, 1)}));
        GregTech_API.registerCover(ItemList.Cover_NeedsMaintainance.get(1), new GT_MultiTexture(Textures.BlockIcons.MACHINE_CASINGS[2][0], new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ACTIVITYDETECTOR)), new GT_Cover_NeedMaintainance());
        GT_Values.RA.addAssemblerRecipe(ItemList.Emitter_MV.get(1), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium), ItemList.Cover_NeedsMaintainance.get(1), 600, 24);
    
        ItemList.Energy_Module.set(addItem(tLastID = 736, "Energy Module", "Reusable battery", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 16), OrePrefixes.battery.get(Materials.Ultimate)}));
        setElectricStats(32000 + tLastID, 10000000000L, GT_Values.V[7], 7L, -3L, true);

        ItemList.Energy_Cluster.set(addItem(tLastID = 737, "Energy Cluster", "Reusable battery", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 64), new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 64)}));
        setElectricStats(32000 + tLastID, 100000000000L, GT_Values.V[8], 8, -3L, true);

        ItemList.ZPM2.set(addItem(tLastID = 605, "Ultimate Battery", "Fill this to win minecraft", new Object[]{new TC_Aspects.TC_AspectStack(TC_Aspects.ELECTRUM, 64), new TC_Aspects.TC_AspectStack(TC_Aspects.VITREUS, 16), new TC_Aspects.TC_AspectStack(TC_Aspects.POTENTIA, 64)}));
        setElectricStats(32000 + tLastID, Long.MAX_VALUE, GT_Values.V[8], 8, -3L, true);

    }

    public boolean onEntityItemUpdate(EntityItem aItemEntity) {
        int aDamage = aItemEntity.getEntityItem().getItemDamage();
        if ((aDamage < 32000) && (aDamage >= 0) && (!aItemEntity.worldObj.isRemote)) {
            Materials aMaterial = GregTech_API.sGeneratedMaterials[(aDamage % 1000)];
            if (aMaterial != null && aMaterial != Materials._NULL) {
                int tX = MathHelper.floor_double(aItemEntity.posX);
                int tY = MathHelper.floor_double(aItemEntity.posY);
                int tZ = MathHelper.floor_double(aItemEntity.posZ);
                OrePrefixes aPrefix = this.mGeneratedPrefixList[(aDamage / 1000)];
                if ((aPrefix == OrePrefixes.dustImpure) || (aPrefix == OrePrefixes.dustPure)) {
                    Block tBlock = aItemEntity.worldObj.getBlock(tX, tY, tZ);
                    byte tMetaData = (byte) aItemEntity.worldObj.getBlockMetadata(tX, tY, tZ);
                    if ((tBlock == Blocks.cauldron) && (tMetaData > 0)) {
                        aItemEntity.setEntityItemStack(GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, aItemEntity.getEntityItem().stackSize));
                        aItemEntity.worldObj.setBlockMetadataWithNotify(tX, tY, tZ, tMetaData - 1, 3);
                        return true;
                    }
                } else if (aPrefix == OrePrefixes.crushed) {
                    Block tBlock = aItemEntity.worldObj.getBlock(tX, tY, tZ);
                    byte tMetaData = (byte) aItemEntity.worldObj.getBlockMetadata(tX, tY, tZ);
                    if ((tBlock == Blocks.cauldron) && (tMetaData > 0)) {
                        aItemEntity.setEntityItemStack(GT_OreDictUnificator.get(OrePrefixes.crushedPurified, aMaterial, aItemEntity.getEntityItem().stackSize));
                        aItemEntity.worldObj.setBlockMetadataWithNotify(tX, tY, tZ, tMetaData - 1, 3);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected void addAdditionalToolTips(List aList, ItemStack aStack, EntityPlayer aPlayer) {
        super.addAdditionalToolTips(aList, aStack, aPlayer);
        int aDamage = aStack.getItemDamage();
        if ((aDamage < 32000) && (aDamage >= 0)) {
            Materials aMaterial = GregTech_API.sGeneratedMaterials[(aDamage % 1000)];
            if (aMaterial != null && aMaterial != Materials._NULL) {
                OrePrefixes aPrefix = this.mGeneratedPrefixList[(aDamage / 1000)];
                if (aPrefix == OrePrefixes.dustImpure || aPrefix == OrePrefixes.dustPure) {
                    aList.add(this.mToolTipPurify);
                }
            }
        }
    }

    public boolean isPlasmaCellUsed(OrePrefixes aPrefix, Materials aMaterial) {
        Collection<GT_Recipe> fusionRecipes = GT_Recipe.GT_Recipe_Map.sFusionRecipes.mRecipeList;
        if (aPrefix == OrePrefixes.cellPlasma && aMaterial.getPlasma(1) != null) { //Materials has a plasma fluid
            for (GT_Recipe recipe : fusionRecipes) { //Loop through fusion recipes
                if (recipe.getFluidOutput(0) != null) { //Make sure fluid output can't be null (not sure if possible)
                    if (recipe.getFluidOutput(0).isFluidEqual(aMaterial.getPlasma(1))) {
                        return true; //Fusion recipe output matches current plasma cell fluid
                    }
                }
            }
        }
        return false;
    }

    public boolean doesShowInCreative(OrePrefixes aPrefix, Materials aMaterial, boolean aDoShowAllItems) {
        return (aDoShowAllItems) || (((aPrefix != OrePrefixes.gem) || (!aMaterial.mName.startsWith("Infused"))) && (aPrefix != OrePrefixes.dustTiny) && (aPrefix != OrePrefixes.dustSmall) && (aPrefix != OrePrefixes.dustImpure) && (aPrefix != OrePrefixes.dustPure) && (aPrefix != OrePrefixes.crushed) && (aPrefix != OrePrefixes.crushedPurified) && (aPrefix != OrePrefixes.crushedCentrifuged) && (aPrefix != OrePrefixes.ingotHot) && !(aPrefix == OrePrefixes.cellPlasma && !isPlasmaCellUsed(aPrefix, aMaterial)));
    }

    public ItemStack getContainerItem(ItemStack aStack) {
        int aDamage = aStack.getItemDamage();
        if ((aDamage == 32479) || (aDamage == 32476)) {
            return new ItemStack(this, 1, aDamage - 2);
        }
        if (aDamage == 32401) {
            return new ItemStack(this, 1, aDamage - 1);
        }
        return super.getContainerItem(aStack);
    }

    public boolean doesMaterialAllowGeneration(OrePrefixes aPrefix, Materials aMaterial) {
        return (super.doesMaterialAllowGeneration(aPrefix, aMaterial));
    }
}
