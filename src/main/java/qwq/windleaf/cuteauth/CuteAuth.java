package qwq.windleaf.cuteauth;

import qwq.windleaf.cuteauth.commands.CuteAuthCommand;
import qwq.windleaf.cuteauth.commands.LoginCommand;
import qwq.windleaf.cuteauth.commands.LogoutCommand;
import qwq.windleaf.cuteauth.commands.RegisterCommand;
import qwq.windleaf.cuteauth.events.PlayerJoin;
import qwq.windleaf.cuteauth.events.PlayerQuit;
import qwq.windleaf.cuteauth.filters.ConsoleFilter;
import qwq.windleaf.cuteauth.filters.Log4jFilter;
import qwq.windleaf.cuteauth.forbidden.*;
import qwq.windleaf.cuteauth.managers.DataManager;
import qwq.windleaf.cuteauth.managers.LoginMapManager;
import qwq.windleaf.cuteauth.managers.LogoutMapManager;
import qwq.windleaf.cuteauth.schedules.timeout.ThreadSchedule;
import org.apache.logging.log4j.LogManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import qwq.windleaf.cuteauth.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;

public final class CuteAuth extends JavaPlugin {

    public static String VERSION = "0.3";
    public static String PREFIX = Utils.formatColor("&dCuteAuth &r| ");
    public String PREFIX_PATH = Utils.getPath() + "plugins" + File.separator + "CuteAuth" + File.separator;
    public String path = this.PREFIX_PATH + "data.db";

    public static CuteAuth instance;
    public static DataManager dataManager;
    public static HashMap<String, ArrayList<String>> data;
    public static HashMap<String, Boolean> loginMap = new HashMap<>();
    public static HashMap<Player, Integer> logoutMap = new HashMap<>();
    public static LoginMapManager loginMapManager = new LoginMapManager();
    public static LogoutMapManager logoutMapManager = new LogoutMapManager();
    public PluginManager p = this.getServer().getPluginManager();

    public static ArrayList<String> commands = new ArrayList<>(Arrays.asList("/login", "/l",
                                                                             "/register", "/reg",
                                                                             "/logout", "/lout"));
    public static qwq.windleaf.cuteauth.schedules.timeout.ThreadSchedule threadSchedule;
    public static qwq.windleaf.cuteauth.schedules.autologin.ThreadSchedule autoLoginThreadSchedule;

    @Override
    public void onEnable() {
        long startTime = System.currentTimeMillis();
        instance = this;

        Log.init(PREFIX);
        Log.console = Bukkit.getConsoleSender();
        Log.sendPluginInfo(Log.console);

        Log.console("&f加载数据库中...");
        this.loadJDBC();
        this.saveConfig();
        this.loadFilter();

        Log.console("&f加载数据管理器中...");
        dataManager = new DataManager();
        data = dataManager.loadData();

        if (this.isEnabled()) {
            this.loadEvents();
            this.loadCommands();
            this.loadForbiddenEvents();

            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.setInvulnerable(true);
                threadSchedule = new ThreadSchedule(onlinePlayer, "&a输入 &b/login <密码> &a登录哦 (๑•.•๑)",
                        this.getConfig().getInt("log.intervals-time") * 1000,
                        this.getConfig().getInt("log.limit-time"));
                threadSchedule.start();
            }

            long endTime = System.currentTimeMillis();
            Log.console("&a插件加载完咯~ 用时 {0}{1}!", (endTime - startTime), " ms");
        }
    }

    @Override
    public void onDisable() {
        Log.console("&c卸载插件中...");
        Bukkit.getScheduler().cancelTasks(this);
    }

    private void loadFilter() {
        try {
            Class.forName("org.apache.logging.log4j.core.filter.AbstractFilter");
            org.apache.logging.log4j.core.Logger logger;
            logger = (org.apache.logging.log4j.core.Logger) LogManager.getRootLogger();
            logger.addFilter(new Log4jFilter());
        } catch (ClassNotFoundException | NoClassDefFoundError e) {
            Log.console("&cLog4J 加载失败了 (;′⌒`)");
            ConsoleFilter consoleFilter = new ConsoleFilter();
            this.getLogger().setFilter(consoleFilter);
            Bukkit.getLogger().setFilter(consoleFilter);
            Logger.getLogger("Minecraft").setFilter(consoleFilter);
        }
    }

    private void loadEvents() {
        p.registerEvents(new PlayerJoin(), this);
        p.registerEvents(new PlayerQuit(), this);
    }

    private void loadCommands() {
        Objects.requireNonNull(this.getCommand("cuteauth")).setExecutor(new CuteAuthCommand());
        Objects.requireNonNull(this.getCommand("login")).setExecutor(new LoginCommand());
        Objects.requireNonNull(this.getCommand("register")).setExecutor(new RegisterCommand());
        Objects.requireNonNull(this.getCommand("logout")).setExecutor(new LogoutCommand());
    }

    private void loadForbiddenEvents() {
        p.registerEvents(new Chat(), this);
        p.registerEvents(new ExecuteCommand(), this);
        p.registerEvents(new Interact(), this);
        p.registerEvents(new Move(), this);
        p.registerEvents(new OpenInventory(), this);
    }

    public void saveConfig() {
        if (!(new File(this.PREFIX_PATH + "config.yml").exists())) this.saveResource("config.yml", false);
    }

    private void loadJDBC() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Utils.disableByError("&c加载 JDBC 数据库驱动失败, 请在配置文件关闭数据库储存 o(╥﹏╥)o");
        }
    }

}