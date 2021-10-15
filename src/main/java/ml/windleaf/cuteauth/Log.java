package ml.windleaf.cuteauth;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import ml.windleaf.cuteauth.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Log {

    public static String PREFIX;
    public static CommandSender console;

    public static void init(String prefix) {
        PREFIX = prefix;
    }

    public static void console(String message) {
        console.sendMessage(Utils.formatColor(PREFIX + message));
    }

    public static void console(String message, Object... object) {
        ArrayList<Object> objects = new ArrayList<>(Arrays.asList(object));
        for (Object o : objects)
            message = message.replace("{" + objects.indexOf(o) + "}", o.toString());
        console.sendMessage(Utils.formatColor(PREFIX + message));
    }

    public static void send(CommandSender p, String message) {
        p.sendMessage(Utils.formatColor(PREFIX + message));
    }

    public static void send(CommandSender p, String message, Object... object) {
        ArrayList<Object> objects = new ArrayList<>(Arrays.asList(object));
        for (Object o : objects)
            message = message.replace("{" + objects.indexOf(o) + "}", o.toString());
        p.sendMessage(Utils.formatColor(PREFIX + message));
    }

    public static void invalidCommand(CommandSender p) {
        Log.send(p, "&c你的命令好像输错了, 要不输入 &6/cuteauth help &c看看帮助吧 ￣へ￣");
    }

    public static void onlyByExecutePlayer(CommandSender p, Runnable execute) {
        if (p instanceof ConsoleCommandSender) Log.send(p, "&c该命令只能由玩家执行哦 ╥﹏╥");
        else execute.run();
    }

    public static void sendPluginInfo(CommandSender p) {
        Log.send(p, "&b=============================");
        Log.send(p, "&a    &dCuteAuth&r | &aversion &6{0}", CuteAuth.VERSION);
        Log.send(p, "&a   Written by &5WindLeaf_qwq");
        Log.send(p, "&b=============================");
    }

}