package fr.nil.eronya.spigot.games.uhc.gameManager;

import com.avaje.ebeaninternal.server.transaction.BulkEventListenerMap;
import fr.nil.eronya.spigot.games.uhc.CoreClass;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Random;

public class PlayerManager {
    public static void clearPlayers(Player p){
        p.getInventory().clear();
    }
public static void giveEffect(Player p){
    new BukkitRunnable() {
        @Override
        public void run() {


            p.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(200, 255));
            p.addPotionEffect(PotionEffectType.SLOW.createEffect(650, 255));
            p.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(650, 255));

        }


    }.runTaskLater(CoreClass.getPlugin(CoreClass.class), 160);
    }
public static void setGamerule(){
    Bukkit.getServer().getWorld("nillegrosfdp").setGameRuleValue("naturalRegeneration", "false");
}

   public static void counter(Player p){
       new BukkitRunnable() {
int num = 5;
           @Override
           public void run() {
               // What you want to schedule goes here
               p.playSound(p.getLocation(), Sound.CLICK,10,10);

              p.sendMessage(CoreClass.prefix +"L'UHC vas commencer dans  " + num + " secondes !");
           p.setTotalExperience(num);
           }

       }.runTaskLater(CoreClass.getPlugin(CoreClass.class), 20);

       new BukkitRunnable() {
           int num = 4;
           @Override
           public void run() {
               // What you want to schedule goes here
p.playSound(p.getLocation(), Sound.CLICK,10,10);
               p.sendMessage(CoreClass.prefix +"L'UHC vas commencer dans  " + num + " secondes !");
               p.setTotalExperience(num);

           }

       }.runTaskLater(CoreClass.getPlugin(CoreClass.class), 40);
       new BukkitRunnable() {
           int num = 3;
           @Override
           public void run() {
               p.setTotalExperience(num);
               p.playSound(p.getLocation(), Sound.CLICK,10,10);

               p.sendMessage(CoreClass.prefix +"L'UHC vas commencer dans  " + num + " secondes !");
           }

       }.runTaskLater(CoreClass.getPlugin(CoreClass.class), 60);
       new BukkitRunnable() {
           int num = 2;
           @Override
           public void run() {
               p.playSound(p.getLocation(), Sound.CLICK,10,10);
               // What you want to schedule goes here
               p.setTotalExperience(num);
               p.sendMessage(CoreClass.prefix +"L'UHC vas commencer dans  " + num + " secondes !");
           }

       }.runTaskLater(CoreClass.getPlugin(CoreClass.class), 80);
       new BukkitRunnable() {
           int num = 1;
           @Override
           public void run() {

               p.setTotalExperience(num);
               p.playSound(p.getLocation(), Sound.CLICK,10,10);
               // What you want to schedule goes here
               p.sendMessage(CoreClass.prefix +"L'UHC vas commencer dans  " + num + " secondes !");
           }

       }.runTaskLater(CoreClass.getPlugin(CoreClass.class), 100);
       new BukkitRunnable() {

           @Override
           public void run() {
      p.setTotalExperience(0);
               p.playSound(p.getLocation(), Sound.FIREWORK_LARGE_BLAST2,100,1);

               p.sendMessage(CoreClass.prefix +"L'UHC a commancer téléportation des joueurs en cours");
               teleportPlayer();
           }

       }.runTaskLater(CoreClass.getPlugin(CoreClass.class), 120);
    }

public static void teleportPlayer(){
    // The maximums are exclusive, so 1501 will give an integer of max 1500 inclusive (0 also included).
    int maxX = 2001;
    int maxY = 2001;

    Random random = new Random();
    for (Player p : Bukkit.getServer().getOnlinePlayers())
    {
        int x = random.nextInt(maxX);
        int z = random.nextInt(maxY);
        p.teleport(p.getWorld().getHighestBlockAt(x,z).getLocation().add(0,2 ,0));
        if(p.getWorld().getBlockAt(p.getLocation()).getType() == Material.WATER){
            p.getWorld().getBlockAt(p.getLocation()).setType(Material.STONE);
        }
    }
}
    }



