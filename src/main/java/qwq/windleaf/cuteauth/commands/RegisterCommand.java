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

public class RegisterCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args_) {
        ArrayList<String> args = new ArrayList<>(Arrays.asList(args_));
        if (sender instanceof ConsoleCommandSender) LogUtil.logPlayer(sender, "&c该命令只能由玩家执行哦 ╥﹏╥");
        else {
            Player player = (Player) sender;
            if (CuteAuth.dataManager.isRegistered(player)) LogUtil.logPlayer(player, "&c嗯... 你似乎注册过了诶!");
            else {
                if (args.size() == 2) {
                    if (args.get(0).equals(args.get(1))) {
                        ArrayList<String> list = CuteAuth.dataManager.encryptPassword(args.get(0));
                        CuteAuth.dataManager.add(player, list.get(0), list.get(1));
                        CuteAuth.dataManager.save();
                        CuteAuth.loginMapManager.add(player);
                        CuteAuth.threadSchedule.interrupt();
                        CuteAuth.threadSchedule.timer.cancel();
                        player.setInvulnerable(false);
                        LogUtil.logPlayer(player, "&a叮~ 你的账号创建成功! 快去 HAPPY 吧 (๑•ᴗ•๑)♡");
                    } else LogUtil.logPlayer(player, "&c呃, 看样子你两次输入的密码不相同哦! 再试试吧 ๐·°(৹˃̵﹏˂̵৹)°·๐");
                } else LogUtil.logPlayer(player, "&c你的参数不对喔, 正确用法是 &b/register <密码> <确认密码> &c哦, 别忘了~");
            }
        }
        return true;
    }

}
