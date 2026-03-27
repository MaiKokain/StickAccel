package yuuria.accelstick.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import yuuria.accelstick.AccelStick;

@Mod.EventBusSubscriber(modid = AccelStick.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public void clientSetup(FMLClientSetupEvent event)
    {
    }
}
