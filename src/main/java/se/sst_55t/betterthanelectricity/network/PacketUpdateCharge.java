package se.sst_55t.betterthanelectricity.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import se.sst_55t.betterthanelectricity.block.table.TileEntityTable;

/**
 * @author shadowfacts
 */
public class PacketUpdateCharge implements IMessage {

  private int charge;
  private int maxCharge;

  public PacketUpdateCharge(int charge, int maxCharge) {
    this.charge = charge;
    this.maxCharge = maxCharge;
  }

  public PacketUpdateCharge() {
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeInt(charge);
    buf.writeInt(maxCharge);
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    charge = buf.readInt();
    maxCharge = buf.readInt();
  }

  public static class Handler implements IMessageHandler<PacketUpdateCharge, IMessage> {

    @Override
    public IMessage onMessage(PacketUpdateCharge message, MessageContext ctx) {
      Minecraft.getMinecraft().addScheduledTask(() -> {
        TileEntityTable te = (TileEntityTable) Minecraft.getMinecraft().world.getTileEntity(message.pos);
        te.inventory.setStackInSlot(0, message.stack);
        te.lastChangeTime = message.lastChangeTime;
      });
      return null;
    }

  }

}