package qwq.windleaf.cuteauth.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import qwq.windleaf.cuteauth.CuteAuth;
import qwq.windleaf.cuteauth.Log;

public class LoginCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Log.onlyByExecutePlayer(sender, () -> {
            Player player = (Player) sender;
            if (args.length == 1) {
                if (CuteAuth.loginMapManager.isLogin(player)) Log.send(player, "&c你已经登录过了哦 (´･_･`)");
                else {
                    if (CuteAuth.dataManager.isRegistered(player)) {
                        if (CuteAuth.dataManager.getSalt(player) == null) Log.send(player, "&c你还没注册呢, 快去注册一个账号再来登录叭 |·ω·`)");
                        if (CuteAuth.dataManager.canPass(player, args[0])) {
                            CuteAuth.loginMapManager.add(player);
                            CuteAuth.threadSchedule.interrupt();
                            CuteAuth.threadSchedule.timer.cancel();
                            if (CuteAuth.autoLoginThreadSchedule != null) {
                                CuteAuth.autoLoginThreadSchedule.interrupt();
                                CuteAuth.autoLoginThreadSchedule.timer.cancel();
                            }
                            player.setInvulnerable(false);
                            Log.send(player, "&a哇, 看样子登录成功了呢! 快去愉快地玩耍吧 ٩(๑>◡<๑)۶ ");
                        } else Log.send(player, "&c噢... 你的密码不对诶, 你是不是记错了 (」ﾟヘﾟ)」");
                    } else Log.send(player, "&c你还没注册呢, 快去注册一个账号再来登录叭 |·ω·`)");
                }
            } else Log.invalidCommand(player);
        });
        return true;
    }

}
