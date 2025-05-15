package CrazySaying.MobEnhanced;
import java.util.ArrayList;
import org.bukkit.potion.PotionEffectType;

public class spawnAPI {
    public static void load() {
        main.spawnlist = new ArrayList<String>(main.config.getConfigurationSection("MOBS").getKeys(false));
    }

    public static void ysload() {
        main.ysmap.put("伤害吸收", PotionEffectType.ABSORPTION);
        main.ysmap.put("失明", PotionEffectType.BLINDNESS);
        main.ysmap.put("反胃", PotionEffectType.CONFUSION);
        main.ysmap.put("急迫", PotionEffectType.FAST_DIGGING);
        main.ysmap.put("火焰保护", PotionEffectType.FIRE_RESISTANCE);
        main.ysmap.put("瞬间恢复", PotionEffectType.HEAL);
        main.ysmap.put("隐身", PotionEffectType.INVISIBILITY);
        main.ysmap.put("跳跃提升", PotionEffectType.JUMP);
        main.ysmap.put("夜视", PotionEffectType.NIGHT_VISION);
        main.ysmap.put("中毒", PotionEffectType.POISON);
        main.ysmap.put("生命恢复", PotionEffectType.REGENERATION);
        main.ysmap.put("缓慢", PotionEffectType.SLOW);
        main.ysmap.put("挖掘疲劳", PotionEffectType.SLOW_DIGGING);
        main.ysmap.put("速度", PotionEffectType.SPEED);
        main.ysmap.put("水下呼吸", PotionEffectType.WATER_BREATHING);
        main.ysmap.put("凋零", PotionEffectType.WITHER);
        main.ysmap.put("抗性提升", PotionEffectType.DAMAGE_RESISTANCE);
        main.ysmap.put("饥饿", PotionEffectType.HUNGER);
    }
}
