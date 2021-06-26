package moe.windleaf.cutelogin;

import moe.windleaf.cutelogin.events.PlayerJoin;
import moe.windleaf.cutelogin.events.PlayerPreCommand;
import moe.windleaf.cutelogin.filters.ConsoleFilter;
import moe.windleaf.cutelogin.filters.Log4jFilter;
import moe.windleaf.cutelogin.forbidden.*;
import moe.windleaf.cutelogin.schedule.ThreadSchedule;
import moe.windleaf.cutelogin.utils.FileUtil;
import moe.windleaf.cutelogin.utils.LogUtil;
import moe.windleaf.cutelogin.utils.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public final class CuteLogin extends JavaPlugin {

    /*
     * TODO: 玩家离线后开启一个线程, 时长为 30minutes (开启后过 30minutes 结束),
     *       结束后将玩家登录状态设为 `false`
     */

    public static CuteLogin INSTANCE;
    public static String PREFIX = StringUtil.formatColor("&d&lCuteLogin &r>> ");
    public static Logger logger = new Logger(PREFIX);
    public String PREFIX_PATH = FileUtil.getPath() + "plugins" + File.separator + this.getName() + File.separator;

    public String path = this.PREFIX_PATH + "data.db";
    @SuppressWarnings("unchecked") public HashMap<String, ArrayList<String>> data =
            (HashMap<String, ArrayList<String>>) FileUtil.loadHashMap(this.path);

    public static DataManager dataManager = new DataManager();
    public static HashMap<String, Boolean> loginMap = new HashMap<>();
    public static HashMap<Player, Integer> logoutMap = new HashMap<>();
    public static LoginMapManager loginMapManager = new LoginMapManager();
    public static LogoutMapManager logoutMapManager = new LogoutMapManager();
    public PluginManager p = this.getServer().getPluginManager();

    public static ArrayList<String> commands = new ArrayList<>(Arrays.asList("/login", "/l",
                                                                             "/register", "/reg",
                                                                             "/logout", "/lout"));
    public static ThreadSchedule threadSchedule;

    @Override
    public void onEnable() {
        INSTANCE = this;

        this.saveConfig();
        this.loadFilter();

        p.registerEvents(new PlayerJoin(), this);
        p.registerEvents(new PlayerPreCommand(), this);
        this.loadForbiddenEvents();

        LogUtil.log("&a呐呐呐, 插件加载完咯~");
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        LogUtil.log("要被卸载了呢...");
    }

    private void loadFilter() {
        try {
            Class.forName("org.apache.logging.log4j.core.filter.AbstractFilter");
            org.apache.logging.log4j.core.Logger logger;
            logger = (org.apache.logging.log4j.core.Logger) LogManager.getRootLogger();
            logger.addFilter(new Log4jFilter());
        } catch (ClassNotFoundException | NoClassDefFoundError e) {
            LogUtil.log("&e你好像在 Minecraft 1.6.x 或者更旧的版本上运行这个插件... 我可能无法支持 Log4J :(");
            ConsoleFilter consoleFilter = new ConsoleFilter();
            getLogger().setFilter(consoleFilter);
            Bukkit.getLogger().setFilter(consoleFilter);
            java.util.logging.Logger.getLogger("Minecraft").setFilter(consoleFilter);

        }
    }

    private void loadForbiddenEvents() {
        p.registerEvents(new Chat(), this);
        p.registerEvents(new ExecuteCommand(), this);
        p.registerEvents(new Interact(), this);
        p.registerEvents(new Move(), this);
        p.registerEvents(new OpenInventory(), this);
    }

    public void saveConfig() {
        if (!(new File(this.PREFIX_PATH + "config.yml").exists())) { this.saveResource("config.yml", false); }
    }

}