package moe.windleaf.cutelogin.commands;

import moe.windleaf.cutelogin.CuteLogin;
import moe.windleaf.cutelogin.utils.LogUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class register implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (CuteLogin.dataManager.isRegistered(player)) {
                LogUtil.logPlayer(player, "&c嗯... 你似乎注册过了诶!");
            } else {
                if (args.length == 2) {
                    if (args[0].equals(args[1])) {
                        ArrayList<String> list = CuteLogin.dataManager.encryptPassword(args[0]);
                        CuteLogin.dataManager.add(player, list.get(0), list.get(1));
                        CuteLogin.dataManager.save();
                        LogUtil.logPlayer(player, "&a叮~ 你的账号创建成功! 快去 happy 吧 (๑•ᴗ•๑)♡");
                    } else { LogUtil.logPlayer(player, "&c呃, 看样子你两次输入的密码不相同哦! 再试试吧 ๐·°(৹˃̵﹏˂̵৹)°·๐"); }
                } else { LogUtil.logPlayer(player, "&c你的参数不对喔, 正确用法是 &b/register <密码> <确认密码> &c哦, 别忘了~"); }
            }
        } else { LogUtil.needPlayer(sender); }
        return true;
    }
}
