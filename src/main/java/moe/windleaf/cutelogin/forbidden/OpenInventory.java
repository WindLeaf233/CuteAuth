package moe.windleaf.cutelogin.forbidden;

import moe.windleaf.cutelogin.CuteLogin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class OpenInventory implements Listener {

    @EventHandler
    public void handler(InventoryOpenEvent e) {
        if (!CuteLogin.loginMapManager.isLogin((Player) e.getPlayer())) { e.setCancelled(true); }
    }

}
