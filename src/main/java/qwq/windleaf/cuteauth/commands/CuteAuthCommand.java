package qwq.windleaf.cuteauth.commands;

import qwq.windleaf.cuteauth.CuteAuth;
import qwq.windleaf.cuteauth.utils.LogUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CuteAuthCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("cuteauth.cuteauth")) {
            if (args.length == 1) {
                if ("reload".equals(args[0])) {
                    CuteAuth.instance.reloadConfig();
                    LogUtil.logPlayer(sender, "&a重载配置成功!");
                } else if ("version".equals(args[0])) {
                    LogUtil.logPlayer(sender, "&b============================");
                    LogUtil.logPlayer(sender, "&a CuteAuth &r- &aversion &6" + CuteAuth.VERSION);
                    LogUtil.logPlayer(sender, "&a Written by &eWindLeaf_qwq");
                    LogUtil.logPlayer(sender, "&b============================");}
            }
        } else LogUtil.logPlayer(sender, "&c你没有权限!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length <= 1) {
            String[] subCommands = {"reload", "version"};
            return Arrays.stream(subCommands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
        } else return null;
    }

}
