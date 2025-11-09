package yuuria.accelsticks.items.sticks;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import yuuria.accelsticks.guis.CreativeAccelSticksScreen;

public class CreativeAccelSticks extends BaseAccelSticks {
    public CreativeAccelSticks(Properties properties) {
        super(properties);
    }

    @Override
    public int getMaxSpeed() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void showSettingScreen(Player player) {
        Minecraft.getInstance().setScreen(new CreativeAccelSticksScreen(Component.translatableWithFallback("ui.stickaccel.stick_screen", "Acceleration Stick settings"), player));
    }
}
