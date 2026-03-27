package yuuria.accelstick.events;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yuuria.accelstick.AccelStick;
import yuuria.accelstick.network.AccelStickPacketHandler;

@Mod.EventBusSubscriber(modid = AccelStick.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event)
    {
        event.enqueueWork(AccelStickPacketHandler::register);
    }
}
