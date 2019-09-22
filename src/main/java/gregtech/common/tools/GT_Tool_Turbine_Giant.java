package gregtech.common.tools;

import gregtech.api.enums.Textures;
import gregtech.api.interfaces.IIconContainer;

public class GT_Tool_Turbine_Giant extends GT_Tool_Turbine {
    @Override
    public float getSpeedMultiplier() {
        return 10.0F;
    }

    @Override
    public float getMaxDurabilityMultiplier() {
        return 10.0F;
    }

    @Override
    public float getBaseDamage() {
        return 5.0F;
    }

    @Override
    public IIconContainer getTurbineIcon() {
        return Textures.ItemIcons.TURBINE_HUGE;
    }
}
