package CrazySaying.MobEnhanced;

import lotr.common.entity.npc.LOTREntityNPC;
import net.minecraft.entity.EntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import static CrazySaying.MobEnhanced.RewardUtil.PlayerList;
import static CrazySaying.MobEnhanced.RewardUtil.DeathBroadCast;
import static CrazySaying.MobEnhanced.RewardUtil.EcoReward;
import static CrazySaying.MobEnhanced.RewardUtil.CommandReward;
import static CrazySaying.MobEnhanced.main.LOTRMOD;


public class listener
        implements Listener {
    private boolean Enable = false;

    @EventHandler
    public void onSpawn(CreatureSpawnEvent e) {
        LivingEntity entity = e.getEntity();
        net.minecraft.server.v1_7_R4.EntityLiving nmsentity=((CraftLivingEntity)entity).getHandle();
        EntityLiving forgeentity = (EntityLiving)(Object)nmsentity;
        String var = e.getEntityType().toString();
        if (main.spawnlist.contains(var)) {
            this.Enable = main.config.getBoolean("MOBS." + var + ".Enable");
            if (this.Enable) {
                Bukkit.broadcastMessage(var);
                entity.setMaxHealth(main.config.getDouble("MOBS." + var + ".Health") * (double)(main.config.getBoolean("EnableMPlayerHealth") ? PlayerList(entity.getNearbyEntities(10.0, 10.0, 7.0)).size():1));
                entity.setHealth(main.config.getDouble("MOBS." + var + ".Health") * (double)(main.config.getBoolean("EnableMPlayerHealth") ? PlayerList(entity.getNearbyEntities(10.0, 10.0, 7.0)).size():1));
                if(LOTRMOD){//检测LOTRMOD加载
                    if(forgeentity instanceof LOTREntityNPC) {
                        LOTREntityNPC npc = (LOTREntityNPC) forgeentity;
                        npc.loadingFromNBT=true;
                        //npc.func_94058_c(main.config.getString("MOBS." + var + ".name"));
                        entity.setCustomName(main.config.getString("MOBS." + var + ".name"));
                        npc.loadingFromNBT=false;
                    }
                }
                else
                {
                    entity.setCustomName(main.config.getString("MOBS." + var + ".name"));
                }
                entity.setCustomNameVisible(true);
                DeathBroadCast(entity, "Messages.spawn_1");
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        String var = e.getEntityType().toString();
        if (main.spawnlist.contains(var) && this.Enable) {
            Player player = entity.getKiller();
            DeathBroadCast(entity, "Messages.death_1");
            EcoReward(player,entity);//给玩家来自于entity的金钱奖励
            CommandReward(player,entity);//给玩家来自于entity的命令奖励
        }
    }

    @EventHandler
    public void Defense(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        String var = entity.getType().toString();
        if (main.spawnlist.contains(var) && this.Enable) {
            e.setDamage(e.getDamage(EntityDamageEvent.DamageModifier.BASE) - main.config.getDouble("MOBS." + var + ".Defense"));
        }
    }

    @EventHandler
    public void Damage(EntityDamageByEntityEvent e) {
        Entity entity = e.getDamager();
        String var = entity.getType().toString();
        if (main.spawnlist.contains(var) && this.Enable) {
            e.setDamage(e.getDamage(EntityDamageEvent.DamageModifier.BASE) + main.config.getDouble("MOBS." + var + ".Attack"));
        }
    }

    public static String prefix() {
        return main.config.getString("Prefix");
    }
}
