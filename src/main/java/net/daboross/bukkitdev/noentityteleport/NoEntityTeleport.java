/*
 * Copyright (C) 2013 Dabo Ross <http://www.daboross.net/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.daboross.bukkitdev.noentityteleport;

import java.util.logging.Level;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author daboross
 */
public class NoEntityTeleport extends JavaPlugin implements Listener {
    
    private boolean debug = false;
    
    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this, this);
    }
    
    @Override
    public void onDisable() {
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equals("debugnet")) {
            debug = !debug;
            sender.sendMessage("NoEntityTeleport debug is now " + (debug ? "enabled" : "disabled") + ".");
        } else {
            sender.sendMessage("NoEntityTeleport doesn't know about the command /" + cmd.getName());
        }
        return true;
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityTeleport(EntityPortalEvent epe) {
        if (debug) {
            getLogger().log(Level.INFO, "Teleport event from {0} to {1} for entity {2} was canceled", new Object[]{epe.getFrom().getWorld().getName(), epe.getTo().getWorld().getName(), epe.getEntityType()});
        }
        epe.setCancelled(true);
    }
}
