package moe.windleaf.cutelogin.commands;

import moe.windleaf.cutelogin.CuteLogin;
import moe.windleaf.cutelogin.utils.LogUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class logout implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            switch (args.length) {
                case 0:
                    if (CuteLogin.logoutMapManager.getTimes(player) <= 1) {
                        LogUtil.logPlayer(player, "&c注销账号之后一切都没了, 你确定真的要离开嘛...");
                        LogUtil.logPlayer(player, "&c如果确定的话, 再输入一次 &b/logout &c叭...");
                        LogUtil.logPlayer(player, "&a回心转意也可以哒, 只需输入 &b/logout cancel &a就行啦~");
                    }
                    CuteLogin.logoutMapManager.add(player);
                    break;
                case 1:
                    if (args[0].equals("cancel")) {
                        CuteLogin.logoutMapManager.remove(player);
                        LogUtil.logPlayer(player, "&a我就知道你会继续留下来哒 :P");
                    } else {
                        LogUtil.logPlayer(player, "&c你的参数不对喔, 正确用法是 &b/logout [cancel] &c哦, 别忘了~");
                    }
                    break;
                default:
                    LogUtil.logPlayer(player, "&c你的参数不对喔, 正确用法是 &b/logout [cancel] &c哦, 别忘了~");
                    break;
            }
        } else { LogUtil.needPlayer(sender); }
        return true;
    }
}
