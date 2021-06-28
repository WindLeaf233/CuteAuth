package moe.windleaf.cutelogin.commands;

import moe.windleaf.cutelogin.utils.LogUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CuteLogin implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if ("reload".equals(args[0])) {
                moe.windleaf.cutelogin.CuteLogin.INSTANCE.reloadConfig();
                LogUtil.logPlayer(sender, "&a重载配置成功!");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length <= 1) {
            ArrayList<String> commands = new ArrayList<>();
            commands.add("reload");
            return commands;
        } else { return null; }
    }
}
