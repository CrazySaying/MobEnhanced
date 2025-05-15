package CrazySaying.MobEnhanced;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import cpw.mods.fml.common.Loader;

public class main
        extends JavaPlugin {
    public static List<String> spawnlist = new ArrayList<>();
    public static HashMap<String, PotionEffectType> ysmap = new HashMap();
    public static FileConfiguration config;
    public static Economy econ;
    public static boolean LOTRMOD=false;

    public void onEnable() {
        this.getLogger().info("生物增强插件启动！");
        File file = new File(this.getDataFolder(), "config.yml");
        if (!file.exists()) {
            this.saveDefaultConfig();
        }
        if (this.setupEconomy()) {
            this.getLogger().info("检测到Vault插件");
        }
        if(HasLOTR())
        {
            LOTRMOD = true;
            this.getLogger().info("检测到魔戒模组");
        }
        this.reload();
        Bukkit.getPluginManager().registerEvents(new listener(), this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("mobenhanced") && args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            this.reloadConfig();
            spawnlist.clear();
            ysmap.clear();
            this.reload();
            sender.sendMessage(config.getString("Prefix") + "§4配置文件已重载！");
            return true;
        }
        return false;
    }

    public void reload() {
        config = this.getConfig();
        spawnAPI.load();
        spawnAPI.ysload();
    }

    private boolean setupEconomy() {
        if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = this.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = (Economy)rsp.getProvider();
        return econ != null;
    }
    private boolean HasLOTR() {
        return Loader.isModLoaded("lotr");
    }

}
