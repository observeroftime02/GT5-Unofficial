package gregtech.common;

import codechicken.lib.vec.Rotation;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.interfaces.tileentity.ICoverable;
import gregtech.api.interfaces.tileentity.ITurnable;
import gregtech.api.metatileentity.BaseMetaPipeEntity;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_PlayedSound;
import gregtech.api.util.GT_Utility;
import gregtech.common.entities.GT_Entity_Arrow;
import gregtech.common.entities.GT_Entity_Arrow_Potion;
import gregtech.common.render.*;
import ic2.api.tile.IWrenchable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.opengl.GL11;

import java.util.*;

public class GT_Client extends GT_Proxy {

    private static List ROTATABLE_VANILLA_BLOCKS;

    static {
        ROTATABLE_VANILLA_BLOCKS = Arrays.asList(Blocks.piston, Blocks.sticky_piston, Blocks.furnace, Blocks.lit_furnace, Blocks.dropper, Blocks.dispenser, Blocks.chest, Blocks.trapped_chest, Blocks.ender_chest, Blocks.hopper, Blocks.pumpkin, Blocks.lit_pumpkin);
    }

    private final HashSet mCapeList = new HashSet();
    private final GT_CapeRenderer mCapeRenderer;
    private final List mPosR;
    private final List mPosG;
    private final List mPosB;
    private final List mPosA = Arrays.asList();
    private final List mNegR;
    private final List mNegG;
    private final List mNegB;
    private final List mNegA = Arrays.asList();
    private final List mMoltenPosR;
    private final List mMoltenPosG;
    private final List mMoltenPosB;
    private final List mMoltenPosA = Arrays.asList();
    private final List mMoltenNegR;
    private final List mMoltenNegG;
    private final List mMoltenNegB;
    private final List mMoltenNegA = Arrays.asList();
    /**This is the place to def the value used below**/
    private long mAnimationTick;
    private boolean mAnimationDirection;
    
    public GT_Client() {
    	mCapeRenderer = new GT_CapeRenderer(mCapeList);
        mAnimationTick = 0L;
        mAnimationDirection = false;
        mPosR = Arrays.asList(Materials.Enderium, Materials.Uranium235, Materials.InfusedGold, Materials.Plutonium241, Materials.NaquadahEnriched, Materials.Naquadria, Materials.InfusedOrder, Materials.Glowstone, Materials.Thaumium, Materials.InfusedVis, Materials.InfusedAir, Materials.InfusedFire);
        mPosG = Arrays.asList(Materials.Enderium, Materials.Uranium235, Materials.InfusedGold, Materials.Plutonium241, Materials.NaquadahEnriched, Materials.Naquadria, Materials.InfusedOrder, Materials.Glowstone, Materials.InfusedAir, Materials.InfusedEarth);
        mPosB = Arrays.asList(Materials.Enderium, Materials.Uranium235, Materials.InfusedGold, Materials.Plutonium241, Materials.NaquadahEnriched, Materials.Naquadria, Materials.InfusedOrder, Materials.InfusedVis, Materials.InfusedWater, Materials.Thaumium);
        mNegR = Arrays.asList(Materials.InfusedEntropy, Materials.NetherStar);
        mNegG = Arrays.asList(Materials.InfusedEntropy, Materials.NetherStar);
        mNegB = Arrays.asList(Materials.InfusedEntropy, Materials.NetherStar);
        mMoltenPosR = Arrays.asList(Materials.Enderium, Materials.NetherStar, Materials.Uranium235, Materials.InfusedGold, Materials.Plutonium241, Materials.NaquadahEnriched, Materials.Naquadria, Materials.InfusedOrder, Materials.Glowstone, Materials.Thaumium, Materials.InfusedVis, Materials.InfusedAir, Materials.InfusedFire);
        mMoltenPosG = Arrays.asList(Materials.Enderium, Materials.NetherStar, Materials.Uranium235, Materials.InfusedGold, Materials.Plutonium241, Materials.NaquadahEnriched, Materials.Naquadria, Materials.InfusedOrder, Materials.Glowstone, Materials.InfusedAir, Materials.InfusedEarth);
        mMoltenPosB = Arrays.asList(Materials.Enderium, Materials.NetherStar, Materials.Uranium235, Materials.InfusedGold, Materials.Plutonium241, Materials.NaquadahEnriched, Materials.Naquadria, Materials.InfusedOrder, Materials.InfusedVis, Materials.InfusedWater, Materials.Thaumium);
        mMoltenNegR = Arrays.asList(Materials.InfusedEntropy);
        mMoltenNegG = Arrays.asList(Materials.InfusedEntropy);
        mMoltenNegB = Arrays.asList(Materials.InfusedEntropy);
    }

    private static void drawGrid(DrawBlockHighlightEvent aEvent) {
        GL11.glPushMatrix();
        GL11.glTranslated(-(aEvent.player.lastTickPosX + (aEvent.player.posX - aEvent.player.lastTickPosX) * (double) aEvent.partialTicks), -(aEvent.player.lastTickPosY + (aEvent.player.posY - aEvent.player.lastTickPosY) * (double) aEvent.partialTicks), -(aEvent.player.lastTickPosZ + (aEvent.player.posZ - aEvent.player.lastTickPosZ) * (double) aEvent.partialTicks));
        GL11.glTranslated((float) aEvent.target.blockX + 0.5F, (float) aEvent.target.blockY + 0.5F, (float) aEvent.target.blockZ + 0.5F);
        Rotation.sideRotations[aEvent.target.sideHit].glApply();
        GL11.glTranslated(0.0D, -0.501D, 0.0D);
        GL11.glLineWidth(2.0F);
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.5F);
        GL11.glBegin(1);
        GL11.glVertex3d(0.5D, 0.0D, -0.25D);
        GL11.glVertex3d(-0.5D, 0.0D, -0.25D);
        GL11.glVertex3d(0.5D, 0.0D, 0.25D);
        GL11.glVertex3d(-0.5D, 0.0D, 0.25D);
        GL11.glVertex3d(0.25D, 0.0D, -0.5D);
        GL11.glVertex3d(0.25D, 0.0D, 0.5D);
        GL11.glVertex3d(-0.25D, 0.0D, -0.5D);
        GL11.glVertex3d(-0.25D, 0.0D, 0.5D);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    @SubscribeEvent
    public void manipulateDensity(EntityViewRenderEvent.FogDensity event) {
    	if (GT_Pollution.mPlayerPollution > (GT_Mod.gregtechproxy.mPollutionSmogLimit)) {
            event.density = (0.15f * (Math.min(GT_Pollution.mPlayerPollution / ((float) GT_Mod.gregtechproxy.mPollutionSourRainLimit), 1.0f))) + 0.1f;
            event.setCanceled(true);
    	}
    }

    @SubscribeEvent
    public void manipulateColor(EntityViewRenderEvent.FogColors event) {
    	if (GT_Pollution.mPlayerPollution > GT_Mod.gregtechproxy.mPollutionSmogLimit) {
            event.red = 140f / 255f;
            event.green = 80f / 255f;
            event.blue = 40f / 255f;
    	}
    }
    
    @SubscribeEvent
    public void manipulateGrassColor(BiomeEvent.GetGrassColor event) {
    	if (GT_Pollution.mPlayerPollution > GT_Mod.gregtechproxy.mPollutionSmogLimit) {
            event.newColor = 0xD2691E;
    	}
    }

    @SubscribeEvent
    public void manipulateWaterColor(BiomeEvent.GetWaterColor event) {
    	if (GT_Pollution.mPlayerPollution > GT_Mod.gregtechproxy.mPollutionSmogLimit) {
            event.newColor = 0x556B2F;
    	}
    }

    @SubscribeEvent
    public void manipulateFoliageColor(BiomeEvent.GetFoliageColor event) {
    	if (GT_Pollution.mPlayerPollution > GT_Mod.gregtechproxy.mPollutionSmogLimit) {
            event.newColor = 0xCD853F;
    	}
    }

    public boolean isServerSide() {
        return true;
    }

    public boolean isClientSide() {
        return true;
    }

    public boolean isBukkitSide() {
        return false;
    }

    public EntityPlayer getThePlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    public int addArmor(String aPrefix) {
        return RenderingRegistry.addNewArmourRendererPrefix(aPrefix);
    }

    public void onLoad() {
        super.onLoad();
        new GT_Renderer_Block();
        new GT_MetaGenerated_Item_Renderer();
        new GT_MetaGenerated_Tool_Renderer();
        new GT_Renderer_Entity_Arrow(GT_Entity_Arrow.class, "arrow");
        new GT_Renderer_Entity_Arrow(GT_Entity_Arrow_Potion.class, "arrow_potions");
    }

    public void onPostLoad() {
        super.onPostLoad();
        label0:
        for (int i = 1; i < GregTech_API.METATILEENTITIES.length; i++) {
            do {
                if (i >= GregTech_API.METATILEENTITIES.length) {
                    continue label0;
                }
                if (GregTech_API.METATILEENTITIES[i] != null) {
                    GregTech_API.METATILEENTITIES[i].getStackForm(1L).getTooltip(null, true);
                }
                i++;
            } while (true);
        }
    }
    
    @SubscribeEvent
    public void receiveRenderSpecialsEvent(net.minecraftforge.client.event.RenderPlayerEvent.Specials.Pre aEvent) {
        mCapeRenderer.receiveRenderSpecialsEvent(aEvent);
    }

    @SubscribeEvent
    public void onPlayerTickEventClient(TickEvent.PlayerTickEvent aEvent) {
        if ((aEvent.side.isClient()) && (aEvent.phase == TickEvent.Phase.END) && (!aEvent.player.isDead)) {
            ArrayList tList = new ArrayList();
            for (Map.Entry<GT_PlayedSound, Integer> tEntry : GT_Utility.sPlayedSoundMap.entrySet()) {
                if (tEntry.getValue() < 0) {
                    tList.add(tEntry.getKey());
                } else {
                    tEntry.setValue(tEntry.getValue() - 1);
                }
            }
            GT_PlayedSound tKey;
            for (Iterator i$ = tList.iterator(); i$.hasNext(); GT_Utility.sPlayedSoundMap.remove(tKey)) {
                tKey = (GT_PlayedSound) i$.next();
            }
            if (!GregTech_API.mServerStarted)GregTech_API.mServerStarted = true;
        }
    }

    @SubscribeEvent
    public void onDrawBlockHighlight(DrawBlockHighlightEvent aEvent) {
        if (GT_Utility.isStackValid(aEvent.currentItem)) {
            Block aBlock = aEvent.player.worldObj.getBlock(aEvent.target.blockX, aEvent.target.blockY, aEvent.target.blockZ);
            TileEntity aTileEntity = aEvent.player.worldObj.getTileEntity(aEvent.target.blockX, aEvent.target.blockY, aEvent.target.blockZ);
            try {
                Class.forName("codechicken.lib.vec.Rotation");
                if (((aTileEntity instanceof BaseMetaPipeEntity)) && (((ICoverable) aTileEntity).getCoverIDAtSide((byte) aEvent.target.sideHit) == 0) && ((GT_Utility.isStackInList(aEvent.currentItem, GregTech_API.sCovers.keySet())) || (GT_Utility.isStackInList(aEvent.currentItem, GregTech_API.sCrowbarList)) || (GT_Utility.isStackInList(aEvent.currentItem, GregTech_API.sScrewdriverList)))) {
                    drawGrid(aEvent);
                    return;
                }
                if ((((aTileEntity instanceof ITurnable)) || (ROTATABLE_VANILLA_BLOCKS.contains(aBlock)) || ((aTileEntity instanceof IWrenchable))) && (GT_Utility.isStackInList(aEvent.currentItem, GregTech_API.sWrenchList))) {
                    drawGrid(aEvent);
                    return;
                }
            } catch (Throwable e) {
                if (GT_Values.D1) {
                    e.printStackTrace(GT_Log.err);
                }
            }
        }
    }

    @SubscribeEvent
    public void receiveRenderEvent(net.minecraftforge.client.event.RenderPlayerEvent.Pre aEvent) {
        if (GT_Utility.getFullInvisibility(aEvent.entityPlayer)) {
            aEvent.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onClientTickEvent(TickEvent.ClientTickEvent aEvent) {
        if (aEvent.phase == TickEvent.Phase.END) {
            if (changeDetected > 0) changeDetected--;
            int newHideValue = shouldHeldItemHideThings();
            if (newHideValue != hideValue) {
                hideValue = newHideValue;
                changeDetected = 5;
            }
            mAnimationTick++;
            if (mAnimationTick % 50L == 0L) {
                mAnimationDirection = !mAnimationDirection;
            }
            int tDirection = mAnimationDirection ? 1 : -1;
            for (Object aMPosR : mPosR) {
                Materials tMaterial = (Materials) aMPosR;
                tMaterial.mRGBa[0] += tDirection;
            }
            for (Object aMPosG : mPosG) {
                Materials tMaterial = (Materials) aMPosG;
                tMaterial.mRGBa[1] += tDirection;
            }
            for (Object aMPosB : mPosB) {
                Materials tMaterial = (Materials) aMPosB;
                tMaterial.mRGBa[2] += tDirection;
            }
            for (Object aMPosA : mPosA) {
                Materials tMaterial = (Materials) aMPosA;
                tMaterial.mRGBa[3] += tDirection;
            }
            for (Object aMNegR : mNegR) {
                Materials tMaterial = (Materials) aMNegR;
                tMaterial.mRGBa[0] -= tDirection;
            }
            for (Object aMNegG : mNegG) {
                Materials tMaterial = (Materials) aMNegG;
                tMaterial.mRGBa[1] -= tDirection;
            }
            for (Object aMNegB : mNegB) {
                Materials tMaterial = (Materials) aMNegB;
                tMaterial.mRGBa[2] -= tDirection;
            }
            for (Object aMNegA : mNegA) {
                Materials tMaterial = (Materials) aMNegA;
                tMaterial.mRGBa[3] -= tDirection;
            }
            for (Object aMMoltenPosR : mMoltenPosR) {
                Materials tMaterial = (Materials) aMMoltenPosR;
                tMaterial.mMoltenRGBa[0] += tDirection;
            }
            for (Object aMMoltenPosG : mMoltenPosG) {
                Materials tMaterial = (Materials) aMMoltenPosG;
                tMaterial.mMoltenRGBa[1] += tDirection;
            }
            for (Object aMMoltenPosB : mMoltenPosB) {
                Materials tMaterial = (Materials) aMMoltenPosB;
                tMaterial.mMoltenRGBa[2] += tDirection;
            }
            for (Object aMMoltenPosA : mMoltenPosA) {
                Materials tMaterial = (Materials) aMMoltenPosA;
                tMaterial.mMoltenRGBa[3] += tDirection;
            }
            for (Object aMMoltenNegR : mMoltenNegR) {
                Materials tMaterial = (Materials) aMMoltenNegR;
                tMaterial.mMoltenRGBa[0] -= tDirection;
            }
            for (Object aMMoltenNegG : mMoltenNegG) {
                Materials tMaterial = (Materials) aMMoltenNegG;
                tMaterial.mMoltenRGBa[1] -= tDirection;
            }
            for (Object aMMoltenNegB : mMoltenNegB) {
                Materials tMaterial = (Materials) aMMoltenNegB;
                tMaterial.mMoltenRGBa[2] -= tDirection;
            }
            for (Object aMMoltenNegA : mMoltenNegA) {
                Materials tMaterial = (Materials) aMMoltenNegA;
                tMaterial.mMoltenRGBa[3] -= tDirection;
            }
        }
    }

    public void doSonictronSound(ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
        if (GT_Utility.isStackInvalid(aStack)) return;
        String tString = "note.harp";
        int i = 0;
        int j = mSoundItems.size();
        do {
            if (i >= j)
                break;
            if (GT_Utility.areStacksEqual(mSoundItems.get(i), aStack)) {
                tString = mSoundNames.get(i);
                break;
            }
            i++;
        } while (true);
        if (tString.startsWith("random.explode")) {
            if (aStack.stackSize == 3) {
                tString = "random.fuse";
            } else if (aStack.stackSize == 2) {
                tString = "random.old_explode";
            }
        }
        if (tString.startsWith("streaming.")) {
            switch (aStack.stackSize) {
                case 1: // '\001'
                    tString = (new StringBuilder()).append(tString).append("13").toString();
                    break;
                case 2: // '\002'
                    tString = (new StringBuilder()).append(tString).append("cat").toString();
                    break;
                case 3: // '\003'
                    tString = (new StringBuilder()).append(tString).append("blocks").toString();
                    break;
                case 4: // '\004'
                    tString = (new StringBuilder()).append(tString).append("chirp").toString();
                    break;
                case 5: // '\005'
                    tString = (new StringBuilder()).append(tString).append("far").toString();
                    break;
                case 6: // '\006'
                    tString = (new StringBuilder()).append(tString).append("mall").toString();
                    break;
                case 7: // '\007'
                    tString = (new StringBuilder()).append(tString).append("mellohi").toString();
                    break;
                case 8: // '\b'
                    tString = (new StringBuilder()).append(tString).append("stal").toString();
                    break;
                case 9: // '\t'
                    tString = (new StringBuilder()).append(tString).append("strad").toString();
                    break;
                case 10: // '\n'
                    tString = (new StringBuilder()).append(tString).append("ward").toString();
                    break;
                case 11: // '\013'
                    tString = (new StringBuilder()).append(tString).append("11").toString();
                    break;
                case 12: // '\f'
                    tString = (new StringBuilder()).append(tString).append("wait").toString();
                    break;
                default:
                    tString = (new StringBuilder()).append(tString).append("wherearewenow").toString();
                    break;
            }
        }
        if (tString.startsWith("streaming.")) {
            aWorld.playRecord(tString.substring(10, tString.length()), (int) aX, (int) aY, (int) aZ);
        } else {
            aWorld.playSound(aX, aY, aZ, tString, 3F, tString.startsWith("note.") ? (float) Math.pow(2D, (double) (aStack.stackSize - 13) / 12D) : 1.0F, false);
        }
    }

    public static int hideValue = 0;
    public static int changeDetected = 0;

    private static int shouldHeldItemHideThings() {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if (player == null) return 0;
        ItemStack held = player.getCurrentEquippedItem();
        if (held == null) return 0;
        int[] ids = OreDictionary.getOreIDs(held);
        int hide = 0;
        for (int i : ids) {
            if (OreDictionary.getOreName(i).equals("craftingToolSolderingIron")) {
                hide |= 0x1;
                break;
            }
        }
        return hide;
    }
}