package gregtech.common.tileentities.storage;


import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

public class GT_TileEntity_SuperTankLong extends TileEntity implements IFluidTank, IFluidHandler {

    // This was written by the one and only Bartimaeusnek, I take no credit for it's creation.

    private Fluid fluid;
    private long fluidamount;

   /* public GT_TileEntity_SuperTankLong() {
        GameRegistry.registerTileEntity(this.getClass(), "LONGTANK");
    }*/

    public boolean registerTank() {
        GameRegistry.registerTileEntity(this.getClass(), "LONGTANK");
        return true;
    }


    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return this.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return this.drain(resource.amount,doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return this.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return (this.fluid == null || fluid.equals(this.fluid)) && this.fluidamount < Long.MAX_VALUE;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return this.fluid != null && this.fluidamount < Long.MAX_VALUE;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[]{this.getInfo()};
    }

    @Override
    public FluidStack getFluid() {
        return fluid == null ? null : new FluidStack(this.fluid,this.getFluidAmount());
    }

    @Override
    public int getFluidAmount() {
        return this.fluidamount > Integer.MAX_VALUE ? Integer.MAX_VALUE - 1 : (int) this.fluidamount;
    }

    @Override
    public int getCapacity() {
        return this.fluidamount > Integer.MAX_VALUE ? Integer.MAX_VALUE : Integer.MAX_VALUE - 1;
    }

    @Override
    public FluidTankInfo getInfo() {
        return new FluidTankInfo(this);
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        if (this.worldObj.isRemote || resource == null || resource.amount == 0)
            return 0;
        if (!doFill){
            if ((fluid == null || fluid.equals(resource.getFluid())) && (long) resource.amount + fluidamount < Long.MAX_VALUE)
                return resource.amount;
            return 0;
        }
        if (!(fluid == null || fluid.equals(resource.getFluid())))
            return 0;
        if ((long)resource.amount + fluidamount >= Long.MAX_VALUE)
            return 0;

        if (fluid == null)
            this.fluid = resource.getFluid();

        long tmp = fluidamount;

        this.fluidamount += resource.amount;

        return (int) (this.fluidamount - tmp);
    }

    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        if (this.fluid == null)
            return null;
        if ((long) maxDrain <= fluidamount) {
            if (doDrain)
                fluidamount -= maxDrain;
            return new FluidStack(this.fluid, maxDrain);
        } else {
            int tmp = (int) fluidamount;
            if (doDrain)
                fluidamount = 0;
            return new FluidStack(this.fluid, tmp);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound p_145841_1_) {
        p_145841_1_.setLong("amount",fluidamount);
        if (fluid != null)
            p_145841_1_.setString("fluidname",fluid.getName());
    }

    @Override
    public void readFromNBT(NBTTagCompound p_145839_1_) {
        String fluidname;
        if ((fluidname = p_145839_1_.getString("fluidname")) != null)
            this.fluid = FluidRegistry.getFluid(fluidname);
        this.fluidamount =   p_145839_1_.getLong("amount");
    }



}