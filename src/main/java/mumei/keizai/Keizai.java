package mumei.keizai;

import mumei.keizai.Commands.keizaireload;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;


public final class Keizai extends JavaPlugin implements Listener {
    private static JavaPlugin plugin;
    private Listener listener;
    public static String noPermission = "あなたには権限がありません";
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        this.listener = new Listeners();
        Bukkit.getPluginManager().registerEvents(this.listener,this);
        // config.ymlが存在しない場合はファイルに出力します。
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);

        getServer().getPluginManager().registerEvents(new Listener(){
            @EventHandler
            public void playerlogout(PlayerQuitEvent p){
                Player player = p.getPlayer();
                World world = player.getWorld();
                Location loc = player.getLocation();
                Logout.logouts(Keizai.this,player,world,loc);
            }
            @EventHandler
            public void PlayerChangeWorld(PlayerChangedWorldEvent p){
                String from = p.getFrom().getName();
                String world = p.getPlayer().getWorld().getName();
                Player name = p.getPlayer();
                World wd = getServer().getWorld("sigen_world1");
                Logout.sigenChange(Keizai.this,world,name,from,wd);
                switch (world){
                    case "keizai":
                        name.sendMessage(ChatColor.AQUA+"ショップへようこそ");
                        break;
                    case "build":
                        name.sendMessage(ChatColor.BLUE+"住宅地へようこそ");
                        break;
                    case "sigentyuukei1":
                        name.sendMessage(ChatColor.YELLOW+"資源ワールド中継");
                        break;
                    case "world":
                        name.sendMessage(ChatColor.BLUE+"チュートリアルへようこそ");
                        break;
                    case "sigen_world1":
                        name.sendMessage(ChatColor.BLUE+"メイン資源ワールドへようこそ");
                        break;
                    case "lobby":
                        name.sendMessage(ChatColor.BLUE+"ロビーへようこそ");
                        break;
                    case "world_nether":
                        name.sendMessage(ChatColor.LIGHT_PURPLE+"ネザーへようこそ");
                        break;
                    case "kyokai":
                        break;
                    default:
                        name.sendMessage(ChatColor.RED+"登録されてないワールドです");
                        Location loc = new Location(getServer().getWorld("lobby"),-180,4,-106);
                        name.teleport(loc);
                        getLogger().info(name.getName()+"さんが登録されてないワールドに入りました world:"+world);
                        break;
                }
            }
        },this);
        // この2つは殆ど定型文の様な物なので覚えておきましょう。
        getLogger().info("経済プラグインが起動しました");
        getCommand("keizaireload").setExecutor(new keizaireload());
        getCommand("keizaireload").getPlugin().reloadConfig();
        super.onEnable();
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("経済プラグインが終了しました。");
        super.onDisable();
    }

}
