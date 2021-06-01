package mumei.keizai.Commands;

import mumei.keizai.Config;
import mumei.keizai.Keizai;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class keizaireload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("Keizai.command.reload")||sender.isOp()){
            sender.sendMessage("リロードが完了しました");
        }else{
            sender.sendMessage(Keizai.noPermission);
        }
        return true;
    }
}
