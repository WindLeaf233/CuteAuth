package moe.windleaf.cutelogin.commands;

import moe.windleaf.cutelogin.CuteLogin;
import moe.windleaf.cutelogin.utils.LogUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class login implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                if (CuteLogin.loginMapManager.isLogin(player)) {
                    LogUtil.logPlayer(player, "&c你已经登录过了哦...");
                } else {
                    if (CuteLogin.dataManager.isRegistered(player)) {
                        if (CuteLogin.dataManager.getSalt(player) == null) {
                            LogUtil.logPlayer(player, "&c你还没注册呢, 快去注册一个账号再来登录叭 |·ω·`)");
                        }
                        if (CuteLogin.dataManager.canPass(player, args[0])) {
                            CuteLogin.loginMapManager.add(player);
                            LogUtil.logPlayer(player, "&a哇, 看样子登录成功了呢! 快去愉快地玩耍吧 •ᴗ•");
                        } else { LogUtil.logPlayer(player, "&c噢... 你的密码不对诶, 你是不是记错了 (¬◡¬)✧"); }
                    } else { LogUtil.logPlayer(player, "&c你还没注册呢, 快去注册一个账号再来登录叭 |·ω·`)"); }
                }
            } else { LogUtil.logPlayer(player, "&c你的参数不对喔, 正确用法是 &b/login <密码> &c哦, 别忘了~"); }
        } else { LogUtil.needPlayer(sender); }
        return true;
    }
}
