package fr.nil.eronya.spigot.games.uhc.task;

import com.avaje.ebeaninternal.server.core.Message;
import fr.nil.eronya.spigot.games.uhc.CoreClass;
import fr.nil.eronya.spigot.games.uhc.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AttackTask {



    int task;
    int time = 20;

    public static void startPVP(Player p) {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(CoreClass.getPlugin(CoreClass.class), new Runnable() {
            @Override
            public void run() {
       int min = 5;
       int time = 0;
       p.sendMessage(CoreClass.prefix + "Le PVP sera actif dans " + min + "minutes");
       p.playSound(p.getLocation(),Sound.NOTE_PIANO,10,1);
            min--;
            time++;
            if(time == 5){//reapeat 5 times
                CoreClass.setPvp(true);
                p.sendMessage(CoreClass.prefix + "Le PVP est maintenant activé !");
                p.playSound(p.getLocation(),Sound.ANVIL_BREAK , 10,1);
            }

            }
        }, 1200, 5);


}
}


