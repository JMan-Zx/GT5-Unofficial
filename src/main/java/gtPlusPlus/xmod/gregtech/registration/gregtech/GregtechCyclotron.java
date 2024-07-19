package gtPlusPlus.xmod.gregtech.registration.gregtech;

import gtPlusPlus.api.objects.Logger;
import gtPlusPlus.xmod.gregtech.api.enums.GregtechItemList;
import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.production.GregtechMetaTileEntity_Cyclotron;

import static gtPlusPlus.xmod.gregtech.registration.gregtech.MetaTileEntityIDs.COMET_Cyclotron;

public class GregtechCyclotron {

    public static void run() {
        Logger.INFO("Gregtech5u Content | Registering COMET Cyclotron.");
        run1();
    }

    private static void run1() {
        GregtechItemList.COMET_Cyclotron.set(
            new GregtechMetaTileEntity_Cyclotron(COMET_Cyclotron.ID, "cyclotron.tier.single", "COMET - Compact Cyclotron", 6)
                .getStackForm(1L));
    }
}
