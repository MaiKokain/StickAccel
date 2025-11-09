package yuuria.accelsticks.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;
import yuuria.accelsticks.items.StickDataComponent;
import yuuria.accelsticks.network.AccelSticksNetwork;

public class CreativeAccelSticksScreen extends Screen {
    private final Player player;
    public CreativeAccelSticksScreen(Component title, Player player) {
        super(title);
        this.player = player;
    }

    @Override
    protected void init() {
        super.init();
        EditBox speedInput = new EditBox(font, width / 2 - 50, height / 2 - 10, 100, 20, Component.literal("Speed"));
        ItemStack itemStack = player.getMainHandItem();
        speedInput.setValue(String.valueOf(itemStack.getOrDefault(StickDataComponent.SPEED.get(), 1)));
        addRenderableWidget(speedInput);

        addRenderableWidget(
                Button.builder(Component.literal("Save"), button -> {
                    try {
                        int newSpeed = Integer.parseInt(speedInput.getValue());
                        PacketDistributor.sendToServer(new AccelSticksNetwork(newSpeed));
                        close();
                    } catch (NumberFormatException e) {
                        if (player.getServer() != null) {
                            player.getServer().sendSystemMessage(Component.translatableWithFallback("errors.stickaccel.not_int_input", "Number format failed, Only numbers input are allowed"));
                        }
                    }
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
