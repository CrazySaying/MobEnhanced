package CrazySaying.MobEnhanced;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static CrazySaying.MobEnhanced.main.econ;

public class RewardUtil {
    public static boolean EcoReward(Player player, LivingEntity entity){
        String var=entity.getType().toString();
        if(econ!=null) {
            double aDouble = main.config.getDouble("MOBS." + var + ".reward.fund");
            econ.depositPlayer(player, aDouble);
            player.sendMessage(listener.prefix() + " §a击败" + entity.getCustomName() + ",§a系统给予§e" + aDouble + "§a金币");
            return true;
        }
        else {
            return false;
        }
    }
    public static void CommandReward(Player player, LivingEntity entity){
        String var=entity.getType().toString();
        for (String s : main.config.getStringList("MOBS." + var + ".reward.command")) {
            if(player!=null)
            {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("{player}", player.getName()));
            }

        }
    }
    public static void DeathBroadCast(LivingEntity entity,String Messages){
        List<Entity> nearbyEntities = entity.getNearbyEntities(10.0, 10.0, 7.0);
        List<String> playerlist = PlayerList(nearbyEntities);
        String var=entity.getType().toString();
        String finalmessage=listener.prefix() + main.config.getString(Messages)
                .replace("{name}", entity.getCustomName()==null?main.config.getString("MOBS." + var + ".name"):entity.getCustomName())
                .replace("{player}", playerlist.toString()==null?"":playerlist.toString());
        Bukkit.broadcastMessage(finalmessage);
    }
    public static List<String> PlayerList(List<Entity> s) {
        ArrayList<String> playerlist = new ArrayList<String>();
        for (Entity entity : s) {
            if (!(entity instanceof Player)) continue;
            playerlist.add(((Player)((Object)entity)).getDisplayName());
        }
        return playerlist;
    }

}
