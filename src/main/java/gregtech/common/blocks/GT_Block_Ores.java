package gregtech.common.blocks;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.ITexture;
import gregtech.api.items.GT_Generic_Block;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.objects.OreGenContainer;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.common.render.GT_Renderer_Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class GT_Block_Ores extends GT_Generic_Block {
    public static boolean aHideOres = Loader.isModLoaded("NotEnoughItems");
    public static boolean enableGCOres = Loader.isModLoaded("GalacticraftCore") && Loader.isModLoaded("GalacticraftMars") && GT_Mod.gregtechproxy.enableGCOres;
    public static int aContainerCount = -1;
    public static OreGenContainer[] aContainerArray = new OreGenContainer[0];
    public Materials aMaterial;

    public GT_Block_Ores(Materials aMaterial) {
        super(GT_Item_Ores.class, "gt.blockores." + aMaterial.mName.toLowerCase(), Material.rock);
        setStepSound(soundTypeStone);
        setCreativeTab(GregTech_API.TAB_GREGTECH_ORES);
        this.aMaterial = aMaterial;
        for (int i = 0; i < aContainerCount; i++) {
            GT_LanguageManager.addStringLocalization(getUnlocalizedName() + "." + i + ".name", getLocalizedName(aMaterial));
            if (aHideOres) codechicken.nei.api.API.hideItem(new ItemStack(this, 1, i));
        }
    }

    public String getLocalizedName(Materials aMaterial) {
        switch (aMaterial.mName) {
            case "InfusedAir":
            case "InfusedEarth":
            case "InfusedEntropy":
            case "InfusedFire":
            case "InfusedOrder":
            case "InfusedWater":
                return aMaterial.mDefaultLocalName + " Infused Stone";
            case "Bentonite":
            case "Talc":
            case "BasalticMineralSand":
            case "GraniticMineralSand":
            case "GlauconiteSand":
            case "CassiteriteSand":
            case "Pitchblende":
                return aMaterial.mDefaultLocalName;
            default:
                return aMaterial.mDefaultLocalName + OrePrefixes.ore.mLocalizedMaterialPost;
        }
    }

    public boolean onBlockEventReceived(World world, int x, int y, int z, int p_149696_5_, int p_149696_6_) {
        super.onBlockEventReceived(world, x, y, z, p_149696_5_, p_149696_6_);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null && tileentity.receiveClientEvent(p_149696_5_, p_149696_6_);
    }

    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return (!(entity instanceof EntityDragon)) && (super.canEntityDestroy(world, x, y, z, entity));
    }

    public String getHarvestTool(int aMeta) {
        return aMeta < 8 ? "pickaxe" : "shovel";
    }

    public int getHarvestLevel(int aMeta) {
        return aMeta == 5 || aMeta == 6 ? 2 : aMeta % 8;
    }

    public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {
        return 1.0F + getHarvestLevel(aWorld.getBlockMetadata(aX, aY, aZ)) * 1.0F;
    }

    public float getExplosionResistance(Entity par1Entity, World aWorld, int aX, int aY, int aZ, double explosionX, double explosionY, double explosionZ) {
        return 1.0F + getHarvestLevel(aWorld.getBlockMetadata(aX, aY, aZ)) * 1.0F;
    }

    protected boolean canSilkHarvest() {
        return false;
    }

    public String getUnlocalizedName() {
        return "gt.blockores." + aMaterial.mName.toLowerCase();
    }

    public String getLocalizedName() {
        return StatCollector.translateToLocal(getUnlocalizedName() + ".name");
    }

    public int getRenderType() {
        if (GT_Renderer_Block.INSTANCE == null) {
            return super.getRenderType();
        }
        return GT_Renderer_Block.INSTANCE.mRenderID;
    }

    public boolean canBeReplacedByLeaves(IBlockAccess aWorld, int aX, int aY, int aZ) {
        return false;
    }

    public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ) {
        return true;
    }

    public boolean renderAsNormalBlock() {
        return true;
    }

    public boolean isOpaqueCube() {
        return true;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
        ArrayList<ItemStack> aDrops = new ArrayList<>();
        ItemStack aChunk = GT_OreDictUnificator.get(OrePrefixes.oreChunk, aMaterial);
        aDrops.add(aChunk != null ? aChunk : new ItemStack(Blocks.cobblestone, 1));
        return aDrops;
    }

    public OrePrefixes[] getProcessingPrefix() { //Must have 8 entries; an entry can be null to disable automatic recipes.
        return new OrePrefixes[]{OrePrefixes.ore, OrePrefixes.oreNetherrack, OrePrefixes.oreEndstone, OrePrefixes.oreBlackgranite, OrePrefixes.oreRedgranite, OrePrefixes.oreMarble, OrePrefixes.oreBasalt, null};
    }

    public int getBaseBlockHarvestLevel(int aMeta) {
        switch (aMeta) {
            case 3:
            case 4:return 3;
            case 0:
            case 1:
            case 2:
            case 5:
            case 6:
            default:return 0;
        }
    }

    public Materials[] getOreMaterials() { //Must have 8 entries; can be null.
        return new Materials[]{Materials.Stone, Materials.Netherrack, Materials.Endstone, Materials.GraniteBlack, Materials.GraniteRed, Materials.Marble, Materials.Basalt, Materials.Stone};
    }

    public ITexture[] getTexture(int aMeta) {
        return new ITexture[]{aContainerArray[aMeta].tTexture, new GT_RenderedTexture(aMaterial.mIconSet.mTextures[OrePrefixes.ore.mTextureIndex], aMaterial.mRGBa)};
    }

    @Override
    public IIcon getIcon(int aSide, int aMeta) {
        return aContainerArray[aMeta].tTargetBlock.getIcon(aSide, aContainerArray[aMeta].tTargetMeta);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister aIconRegister) {
        aIconRegister.registerIcon(GT_Values.MOD_ID + ":" + mUnlocalizedName);
    }

    @Override
    public void getSubBlocks(Item aItem, CreativeTabs aTab, List aList) {
        for (int i = 0; i < aContainerCount; i++) {
            aList.add(new ItemStack(aItem, 1, i));
        }
    }
}