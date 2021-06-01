package mumei.keizai;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listeners implements Listener {
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent p){
        Player e =p.getPlayer();
        Location location = new Location(e.getServer().getWorld("lobby"),-181,5,-107);
        if(!(e.getWorld().getName().equals("sigen_world1"))){
            e.teleport(location);
        }
        p.setJoinMessage(ChatColor.YELLOW+e.getName()+"さんが参加しました。");
        e.sendTitle(ChatColor.BLUE + "経済サーバーへ", "ようこそ" + e.getName() + "さん!!" ,10,40, 10);
    }

    @EventHandler
    public void PlayerChangeWorld(PlayerChangedWorldEvent p){


    }

}
