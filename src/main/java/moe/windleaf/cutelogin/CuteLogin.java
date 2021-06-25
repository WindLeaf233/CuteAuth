package moe.windleaf.cutelogin;

import moe.windleaf.cutelogin.commands.login;
import moe.windleaf.cutelogin.commands.logout;
import moe.windleaf.cutelogin.commands.register;
import moe.windleaf.cutelogin.events.PlayerCommand;
import moe.windleaf.cutelogin.events.PlayerJoin;
import moe.windleaf.cutelogin.utils.FileUtil;
import moe.windleaf.cutelogin.utils.LogUtil;
import moe.windleaf.cutelogin.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Filter;

public final class CuteLogin extends JavaPlugin {

    /*
     * TODO: 玩家离线后开启一个线程, 时长为 30minutes (开启后过 30minutes 结束),
     *       结束后将玩家登录状态设为 `false`
     *
     * TODO: 将 /login /register 等命令隐藏, 不要在控制台中出现
     *
     * TODO: 增加几个 event, 对没有登录的玩家实施限制, 无法移动等
     */

    public static CuteLogin INSTANCE;
    public static String PREFIX = StringUtil.formatColor("&d&lCuteLogin &r>> ");
    public static Logger logger = new Logger(PREFIX);
    public String PREFIX_PATH = FileUtil.getPath() + "plugins" + File.separator + this.getName() + File.separator;

    public String path = this.PREFIX_PATH + "data.db";
    @SuppressWarnings("unchecked") public HashMap<String, ArrayList<String>> data =
            (HashMap<String, ArrayList<String>>) FileUtil.loadHashMap(this.path);

    public static DataManager dataManager = new DataManager();
    public static HashMap<Player, Boolean> loginMap = new HashMap<>();
    public static HashMap<Player, Integer> logoutMap = new HashMap<>();
    public static LoginMapManager loginMapManager = new LoginMapManager();
    public static LogoutMapManager logoutMapManager = new LogoutMapManager();

    public PluginManager p = this.getServer().getPluginManager();

    @Override
    public void onEnable() {
        INSTANCE = this;

        this.registerFilter();

        Objects.requireNonNull(this.getCommand("login")).setExecutor(new login());
        Objects.requireNonNull(this.getCommand("register")).setExecutor(new register());
        Objects.requireNonNull(this.getCommand("logout")).setExecutor(new logout());

        p.registerEvents(new PlayerJoin(), this);
        p.registerEvents(new PlayerCommand(), this);

        LogUtil.log("&a呐呐呐, 插件加载完咯~");
    }

    @Override
    public void onDisable() {
        LogUtil.log("要被卸载了呢...");
    }

    private void registerFilter() {
        Filter f = record -> !record.getMessage().contains("/login")
                && !record.getMessage().contains("/l")
                && !record.getMessage().contains("/register")
                && !record.getMessage().contains("/reg")
                && !record.getMessage().contains("/logout")
                && !record.getMessage().contains("/lout");
        Bukkit.getLogger().setFilter(f);
    }
}