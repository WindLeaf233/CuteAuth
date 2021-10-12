package qwq.windleaf.cuteauth.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import qwq.windleaf.cuteauth.CuteAuth;
import qwq.windleaf.cuteauth.Log;

public class LogoutCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Log.onlyByExecutePlayer(sender, () -> {
            Player player = (Player) sender;
            if (CuteAuth.dataManager.isRegistered(player)) {
                if (args.length == 0) {
                    if (CuteAuth.logoutMapManager.getTimes(player) < 1) {
                        Log.send(player, "&c注销账号后一切都会消失的, 你确定真的要离开嘛...");
                        Log.send(player, "&c如果确定的话, 那就再输入一次 &b/logout &c吧!");
                        Log.send(player, "&a如果想回心转意的话, 只需输入 &b/logout cancel &a就行啦~");
                    }
                    CuteAuth.logoutMapManager.add(player);
                } else if (args.length == 1) {
                    if (args[0].equals("cancel")) {
                        CuteAuth.logoutMapManager.remove(player);
                        Log.send(player, "&a好耶!");
                    } else Log.invalidCommand(sender);
                } else Log.invalidCommand(sender);
            } else Log.send(player, "&c你还没注册呢, 咋注销呢 ╮(─▽─)╭");
        });
        return true;
    }

}
