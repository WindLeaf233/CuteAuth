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

public class LoginCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args_) {
        ArrayList<String> args = new ArrayList<>(Arrays.asList(args_));
        if (sender instanceof ConsoleCommandSender) LogUtil.logPlayer(sender, "&c该命令只能由玩家执行哦 ╥﹏╥");
        else {
            Player player = (Player) sender;
            if (args.size() == 1) {
                if (CuteAuth.loginMapManager.isLogin(player)) LogUtil.logPlayer(player, "&c你已经登录过了哦...");
                else {
                    if (CuteAuth.dataManager.isRegistered(player)) {
                        if (CuteAuth.dataManager.getSalt(player) == null) LogUtil.logPlayer(player, "&c你还没注册呢, 快去注册一个账号再来登录叭 |·ω·`)");
                        if (CuteAuth.dataManager.canPass(player, args.get(0))) {
                            CuteAuth.loginMapManager.add(player);
                            CuteAuth.threadSchedule.interrupt();
                            CuteAuth.threadSchedule.timer.cancel();
                            if (CuteAuth.autoLoginThreadSchedule != null) {
                                CuteAuth.autoLoginThreadSchedule.interrupt();
                                CuteAuth.autoLoginThreadSchedule.timer.cancel();
                            }
                            player.setInvulnerable(false);
                            LogUtil.logPlayer(player, "&a哇, 看样子登录成功了呢! 快去愉快地玩耍吧 •ᴗ•");
                        } else LogUtil.logPlayer(player, "&c噢... 你的密码不对诶, 你是不是记错了 (¬◡¬)✧");
                    } else LogUtil.logPlayer(player, "&c你还没注册呢, 快去注册一个账号再来登录叭 |·ω·`)");
                }
            } else LogUtil.logPlayer(player, "&c你的参数不对喔, 正确用法是 &b/login <密码> &c哦, 别忘了~");
        }
        return true;
    }

}
