package qwq.windleaf.cuteauth.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import qwq.windleaf.cuteauth.CuteAuth;
import qwq.windleaf.cuteauth.utils.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class LogoutCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args_) {
        ArrayList<String> args = new ArrayList<>(Arrays.asList(args_));
        if (sender instanceof ConsoleCommandSender) LogUtil.logPlayer(sender, "&c该命令只能由玩家执行哦 ╥﹏╥");
        else {
            Player player = (Player) sender;
            if (CuteAuth.dataManager.isRegistered(player)) {
                switch (args.size()) {
                    case 0:
                        if (CuteAuth.logoutMapManager.getTimes(player) < 1) {
                            LogUtil.logPlayer(player, "&c注销账号后一切都会消失的, 你确定真的要离开嘛...");
                            LogUtil.logPlayer(player, "&c如果确定的话, 再输入一次 &b/logout &c叭...");
                            LogUtil.logPlayer(player, "&a回心转意也可以哒, 只需输入 &b/logout cancel &a就行啦~");
                        }
                        CuteAuth.logoutMapManager.add(player);
                        break;
                    case 1:
                        if (args.get(0).equals("cancel")) {
                            CuteAuth.logoutMapManager.remove(player);
                            LogUtil.logPlayer(player, "&a我就知道你会继续留下来哒 :D");
                        } else LogUtil.logPlayer(player, "&c你的参数不对喔, 正确用法是 &b/logout [cancel] &c哦, 别忘了~");
                        break;
                    default:
                        LogUtil.logPlayer(player, "&c你的参数不对喔, 正确用法是 &b/logout [cancel] &c哦, 别忘了~");
                        break;
                }
            } else LogUtil.logPlayer(player, "&c你还没注册呢, 咋注销呢~");
        }
        return true;
    }

}
