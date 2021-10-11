package qwq.windleaf.cuteauth.utils;

import qwq.windleaf.cuteauth.CuteAuth;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LogUtil {

    public static void log(String message) { CuteAuth.logger.log(message); }

    public static void logPlayer(Player player, String message) {
        player.sendMessage(StringUtil.formatColor(CuteAuth.PREFIX + message));
    }

    public static void logPlayer(CommandSender sender, String message) {
        sender.sendMessage(StringUtil.formatColor(CuteAuth.PREFIX + message));
    }

    @SuppressWarnings("unused")
    public static void needPlayer(CommandSender sender) {
        LogUtil.logPlayer(sender, "&c哦不, 这个命令只能由玩家来执行... OxO");
    }

}
