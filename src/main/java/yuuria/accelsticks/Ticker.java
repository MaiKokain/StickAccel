package yuuria.accelsticks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class Ticker {
    public void tickBlock(BlockPos blockPos, Level level, int speed) {
        if (level.isClientSide()) return;
        BlockState blockState = level.getBlockState(blockPos);
        Block block = blockState.getBlock();

        if (blockState.isRandomlyTicking()) {
            blockState.randomTick((ServerLevel) level, blockPos, level.getRandom());
        }

        if (!(block instanceof EntityBlock entityBlock)) {
            return;
        }
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        BlockEntityTicker<BlockEntity> blockEntityTicker = null;

        if (blockEntity != null) blockEntityTicker = blockEntity.getBlockState().getTicker(level, (BlockEntityType<BlockEntity>) blockEntity.getType());

        if (blockEntityTicker == null) return;

        for (int i = 0; i < speed; i++) {
            blockEntityTicker.tick(level, blockPos, blockState, blockEntity);
        }

    }
}
