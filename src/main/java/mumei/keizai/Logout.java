package mumei.keizai;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import java.util.Arrays;
import java.util.List;

public class Logout {
    public static void logouts(Keizai config, Player player, World world, Location loc){
        String locx= (String.valueOf(loc.getX()));
        String locy = (String.valueOf(loc.getY()));
        String locz = (String.valueOf(loc.getZ()));
        if(world.getName().equals("sigen_world1")){
            String[] lists = {locx,locy,locz};
            config.getConfig().set(player.getName(), Arrays.asList(lists));
            config.saveConfig();
            config.reloadConfig();
        }
    }
    public static void sigenChange(Keizai config, String world, Player name,String from,World wd){
        if(world.equals("sigen_world1")){

            List<String> lists = config.getConfig().getStringList(name.getName());
            double x = Double.parseDouble(lists.get(0));
            double y = Double.parseDouble(lists.get(1));
            double z = Double.parseDouble(lists.get(2));
            Location loc = new Location(wd,x,y,z);
            name.teleport(loc);
        }
        if(from.equals("sigen_world1")&&world.equals("lobby")){
            String[] lists = {"-26","68","95"};
            config.getConfig().set(name.getName(), Arrays.asList(lists));
            config.saveConfig();
            config.reloadConfig();
        }

    }

}
