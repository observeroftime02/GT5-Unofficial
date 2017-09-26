package gregtech.api.objects;

import gregtech.api.enums.Materials;

public class MaterialStack implements Cloneable {
    public int mAmount;
    public Materials mMaterial;

    public MaterialStack(Materials aMaterial, int aAmount) {
        mMaterial = aMaterial == null ? Materials._NULL : aMaterial;
        mAmount = aAmount;
    }

    public MaterialStack copy(int aAmount) {
        return new MaterialStack(mMaterial, aAmount);
    }

    @Override
    public MaterialStack clone() {
        try { return (MaterialStack) super.clone(); } catch (Exception e) { return new MaterialStack(mMaterial, mAmount); }
    }

    @Override
    public boolean equals(Object aObject) {
        if (aObject == this) return true;
        if (aObject == null) return false;
        if (aObject instanceof Materials) return aObject == mMaterial;
        return aObject instanceof MaterialStack && ((MaterialStack) aObject).mMaterial == mMaterial && (mAmount < 0 || ((MaterialStack) aObject).mAmount < 0 || ((MaterialStack) aObject).mAmount == mAmount);
    }

    @Override
    public String toString() {
         String temp1 = "", temp2 = mMaterial.getToolTip(true), temp3 = "", temp4 = "";
         if (mAmount > 1) {
             temp4 = String.valueOf(mAmount);
             
             if (mMaterial.mProcessInto.size() > 1 || isMaterialListComplex(this)) {
                temp1 = "(";
                temp3 = ")";
             }
         }
        return String.valueOf(new StringBuilder().append(temp1).append(temp2).append(temp3).append(temp4));
    }

    private boolean isMaterialListComplex(MaterialStack materialStack) {
        return materialStack.mMaterial.mProcessInto.size() > 1 || materialStack.mMaterial.mProcessInto.size() != 0 && isMaterialListComplex(materialStack.mMaterial.mProcessInto.get(0));
    }
    
    @Override
    public int hashCode() {
        return mMaterial.hashCode();
    }
}