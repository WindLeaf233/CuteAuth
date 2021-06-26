package moe.windleaf.cutelogin.forbidden;

import moe.windleaf.cutelogin.CuteLogin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class Interact implements Listener {

    @EventHandler
    public void harvest(PlayerHarvestBlockEvent e) { if (!CuteLogin.loginMapManager.isLogin(e.getPlayer())) { e.setCancelled(true); } }

    @EventHandler
    public void changeWorld(PlayerInteractEvent e) { if (!CuteLogin.loginMapManager.isLogin(e.getPlayer())) { e.setCancelled(true); } }

    @EventHandler
    public void bucketFill(PlayerBucketFillEvent e) { if (!CuteLogin.loginMapManager.isLogin(e.getPlayer())) { e.setCancelled(true); } }

    @EventHandler
    public void bucketEmpty(PlayerBucketEmptyEvent e) { if (!CuteLogin.loginMapManager.isLogin(e.getPlayer())) { e.setCancelled(true); } }

    @EventHandler
    public void bedEnter(PlayerBedEnterEvent e) { if (!CuteLogin.loginMapManager.isLogin(e.getPlayer())) { e.setCancelled(true); } }

    @EventHandler
    public void dropItem(PlayerDropItemEvent e) { if (!CuteLogin.loginMapManager.isLogin(e.getPlayer())) { e.setCancelled(true); } }

    @EventHandler
    public void teleport(PlayerTeleportEvent e) { if (!CuteLogin.loginMapManager.isLogin(e.getPlayer())) { e.setCancelled(true); } }

    @EventHandler
    public void editBook(PlayerEditBookEvent e) { if (!CuteLogin.loginMapManager.isLogin(e.getPlayer())) { e.setCancelled(true); } }

    @EventHandler
    public void eggThrow(PlayerEggThrowEvent e) { if (!CuteLogin.loginMapManager.isLogin(e.getPlayer())) { e.setHatching(false); } }

    @EventHandler
    public void fish(PlayerFishEvent e) { if (!CuteLogin.loginMapManager.isLogin(e.getPlayer())) { e.setCancelled(true); } }

    @EventHandler
    public void gameModeChange(PlayerGameModeChangeEvent e) { if (!CuteLogin.loginMapManager.isLogin(e.getPlayer())) { e.setCancelled(true); } }

    @EventHandler
    public void interactEntity(PlayerInteractEntityEvent e) { if (!CuteLogin.loginMapManager.isLogin(e.getPlayer())) { e.setCancelled(true); } }

    @EventHandler
    public void interactAtEntity(PlayerInteractAtEntityEvent e) { if (!CuteLogin.loginMapManager.isLogin(e.getPlayer())) { e.setCancelled(true); } }

}
