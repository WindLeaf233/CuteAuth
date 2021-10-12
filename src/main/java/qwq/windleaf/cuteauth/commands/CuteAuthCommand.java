package qwq.windleaf.cuteauth.commands;

import qwq.windleaf.cuteauth.CuteAuth;
import qwq.windleaf.cuteauth.Log;
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
        if (args.length == 0) Log.send(sender, "&a输入 &6/cuteauth help &a查看帮助菜单哦 (*^▽^*)");
        else if (args.length == 1) {
            switch (args[0]) {
                case "help":
                    Log.send(sender, " &b/cuteauth help &f» &a显示此帮助");
                    Log.send(sender, " &b/cuteauth &creload &f» &a重载配置");
                    Log.send(sender, " &b/cuteauth version &f» &a查看插件版本信息");
                    Log.send(sender, " &b/login <password> &f» &a登录账号");
                    Log.send(sender, " &b/register <password> <confirm password> &f» &a注册账号");
                    Log.send(sender, " &b/logout [cancel] &f» &a注销账号");
                    break;
                case "reload":
                    if (sender.hasPermission("cuteauth.reload")) {
                        CuteAuth.instance.reloadConfig();
                        Log.send(sender, "&a重载配置成功啦 (￣▽￣)／");
                    } else Log.send(sender, "&c你没有执行这个命令的权限哦 ∑(ﾟДﾟノ)ノ");
                    break;
                case "version":
                    Log.sendPluginInfo(sender);
                    break;
                default:
                    Log.invalidCommand(sender);
                    break;
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length <= 1) {
            String[] subCommands = {"help", "reload", "version"};
            return Arrays.stream(subCommands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
        } else return null;
    }

}
