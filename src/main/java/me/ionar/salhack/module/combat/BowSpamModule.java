package me.ionar.salhack.module.combat;

import me.ionar.salhack.events.client.EventClientTick;
import me.ionar.salhack.module.Module;

import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.math.BlockPos;

import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;

public class BowSpamModule extends Module
{
    /// (String displayName, String[] alias, String key, int color, ModuleType type)
    public BowSpamModule()
    {
        super("BowSpam", new String[]
        { "BS" }, "Releases the bow as fast as possible", "NONE", 0xDB2424, ModuleType.COMBAT);
    }

    @EventHandler
    private Listener<EventClientTick> OnTick = new Listener<>(p_Event ->
    {
        if (mc.player.getHeldItemMainhand().getItem() instanceof net.minecraft.item.ItemBow && mc.player.isHandActive() && mc.player.getItemInUseMaxCount() >= 3)
        {
            mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, mc.player.getHorizontalFacing()));
            mc.player.connection.sendPacket(new CPacketPlayerTryUseItem(mc.player.getActiveHand()));
            mc.player.stopActiveHand();
        }
    });
}
