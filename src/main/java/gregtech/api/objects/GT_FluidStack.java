package gregtech.api.objects;

import gregtech.api.GregTech_API;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Because Forge fucked this one up royally.
 */
public class GT_FluidStack extends FluidStack {
    private static final Collection<GT_FluidStack> sAllFluidStacks = new ArrayList<GT_FluidStack>(5000);
    private Fluid mFluid;

    public GT_FluidStack(Fluid aFluid, int aAmount) {
        super(aFluid, aAmount);
        mFluid = aFluid;
        if (!GregTech_API.mServerStarted) {
            sAllFluidStacks.add(this);
        }
    }

    public GT_FluidStack(FluidStack aFluid) {
        this(aFluid.getFluid(), aFluid.amount);
    }

    @Override
    public FluidStack copy() {
        return new GT_FluidStack(this);
    }
    
    @Override
    public String toString() {
    	return String.format("GT_FluidStack: %s x %s, ID:%s", this.amount, this.getFluid().getName(), this.getFluidID());
    }
}