package yuuria.accelsticks.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.gui.widget.ExtendedSlider;
import net.neoforged.neoforge.network.PacketDistributor;
import yuuria.accelsticks.items.StickDataComponent;
import yuuria.accelsticks.items.sticks.BaseAccelSticks;
import yuuria.accelsticks.network.AccelSticksNetwork;

public class AccelSticksScreen extends Screen {
    private final Player player;
    public AccelSticksScreen(Component title, Player player) {
        super(title);
        this.player = player;
    }

    @Override
    protected void init() {
        super.init();
        ItemStack itemStack = player.getMainHandItem();
        BaseAccelSticks item = (BaseAccelSticks) itemStack.getItem();

        ExtendedSlider slider = this.addRenderableWidget(new ExtendedSlider(
            width / 2 - 50,
            height / 2 - 10,
            100,
            20,
            Component.literal(""),
            Component.literal(""),
            1,
            item.getMaxSpeed(),
            itemStack.getOrDefault(StickDataComponent.SPEED.get(), 1),
            1,
            0,
                true
        ));

        addRenderableWidget(
                Button.builder(Component.literal("Save"), button -> {
                    int newSpeed = slider.getValueInt();
                    PacketDistributor.sendToServer(new AccelSticksNetwork(newSpeed));
                    close();
                }).pos(width / 2 - 50, height / 2 + 20).size(100, 20).build()
        );

    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(font, title, width / 2, height / 2 - 30, 0xFFFFFF);
    }

    private void close() {
        Minecraft.getInstance().setScreen(null);
    }
}
