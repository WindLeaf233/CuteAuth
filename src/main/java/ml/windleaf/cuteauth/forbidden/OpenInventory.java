package ml.windleaf.cuteauth.forbidden;

import ml.windleaf.cuteauth.CuteAuth;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class OpenInventory implements Listener {

    @EventHandler
    public void handler(InventoryOpenEvent e) {
        if (!CuteAuth.loginMapManager.isLogin((Player) e.getPlayer())) e.setCancelled(true);
    }

}
