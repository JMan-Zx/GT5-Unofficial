package gregtech.api.multitileentity.multiblock.base;

import net.minecraft.nbt.NBTTagCompound;

import gregtech.api.enums.GT_Values;
import gregtech.api.logic.PowerLogic;
import gregtech.api.logic.interfaces.PowerLogicHost;

public abstract class PowerController<T extends PowerController<T>> extends Controller<T> implements PowerLogicHost {

    public PowerController() {
        super();
        power = new PowerLogic().setType(PowerLogic.RECEIVER);
    }

    protected PowerLogic power;

    @Override
    public void writeMultiTileNBT(NBTTagCompound nbt) {
        super.writeMultiTileNBT(nbt);
        power.writeToNBT(nbt);
    }

    @Override
    public void readMultiTileNBT(NBTTagCompound nbt) {
        super.readMultiTileNBT(nbt);
        power.loadFromNBT(nbt);
    }

    @Override
    public PowerLogic getPowerLogic(byte side) {
        return power;
    }

    @Override
    public boolean checkMachine() {
        boolean result = super.checkMachine();
        power.setEnergyCapacity(GT_Values.V[tier] * 2 * 60 * 20);
        power.setAmperage(2);
        power.setMaxVoltage(GT_Values.V[tier]);
        return result;
    }
}
