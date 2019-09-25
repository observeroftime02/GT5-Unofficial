package gregtech.common.tools;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.interfaces.IToolStats;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.common.items.GT_MetaGenerated_Tool_01;
import gregtech.common.items.behaviors.Behaviour_Crowbar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

import java.util.Iterator;

public class GT_Tool_Crowbar_LV
        extends GT_Tool_Crowbar {
    public int getToolDamagePerBlockBreak() {
        return 100;
    }

    public int getToolDamagePerDropConversion() {
        return 200;
    }

    public int getToolDamagePerContainerCraft() {
        return 200;
    }

    public int getToolDamagePerEntityAttack() {
        return 400;
    }

    public int getBaseQuality() {
        return 0;
    }

    public float getBaseDamage() {
        return 4.0F;
    }

    public float getSpeedMultiplier() {
        return 2.0F;
    }

    public float getMaxDurabilityMultiplier() {
        return 2.0F;
    }


}
