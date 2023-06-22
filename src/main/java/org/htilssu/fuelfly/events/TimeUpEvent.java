package org.htilssu.fuelfly.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public final class TimeUpEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    boolean cancel = false;

    public TimeUpEvent(Player player) {
        super(player);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }


    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public String getEventName() {
        return super.getEventName();
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {
        cancel = true;
    }

}