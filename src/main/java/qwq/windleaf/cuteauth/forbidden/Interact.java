package qwq.windleaf.cuteauth.forbidden;

import qwq.windleaf.cuteauth.CuteAuth;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.event.Cancellable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Interact implements Listener {

    private void checkCancel(PlayerEvent cancellableEvent) {
        if (!CuteAuth.loginMapManager.isLogin(cancellableEvent.getPlayer())) {
            try {
                Class<?> d = cancellableEvent.getClass();
                Class<?>[] interfaces = d.getInterfaces();
                for (Class<?> item : interfaces) {
                    if (item == Cancellable.class) {
                        Method cancelled = d.getMethod("cancelled");
                        cancelled.invoke(d);
                    }
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {}
        }
    }

    @EventHandler
    public void harvest(PlayerHarvestBlockEvent e) {
        checkCancel(e);
    }

    @EventHandler
    public void changeWorld(PlayerInteractEvent e) {
        checkCancel(e);
    }

    @EventHandler
    public void bucketFill(PlayerBucketFillEvent e) {
        checkCancel(e);
    }

    @EventHandler
    public void bucketEmpty(PlayerBucketEmptyEvent e) {
        checkCancel(e);
    }

    @EventHandler
    public void bedEnter(PlayerBedEnterEvent e) {
        checkCancel(e);
    }

    @EventHandler
    public void dropItem(PlayerDropItemEvent e) {
        checkCancel(e);
    }

    @EventHandler
    public void teleport(PlayerTeleportEvent e) {
        checkCancel(e);
    }

    @EventHandler
    public void editBook(PlayerEditBookEvent e) {
        checkCancel(e);
    }

    @EventHandler
    public void eggThrow(PlayerEggThrowEvent e) {
        if (!CuteAuth.loginMapManager.isLogin(e.getPlayer())) e.setHatching(false);
    }

    @EventHandler
    public void fish(PlayerFishEvent e) {
        checkCancel(e);
    }

    @EventHandler
    public void gameModeChange(PlayerGameModeChangeEvent e) {
        checkCancel(e);
    }

    @EventHandler
    public void interactEntity(PlayerInteractEntityEvent e) {
        checkCancel(e);
    }

    @EventHandler
    public void interactAtEntity(PlayerInteractAtEntityEvent e) {
        checkCancel(e);
    }

}
