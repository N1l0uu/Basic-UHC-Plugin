package fr.nil.eronya.spigot.games.uhc.listeners;

import fr.nil.eronya.spigot.games.uhc.CoreClass;
import fr.nil.eronya.spigot.games.uhc.gameManager.GameStart;
import fr.nil.eronya.spigot.games.uhc.utils.Messages;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import java.lang.reflect.Constructor;
import java.util.UUID;

public class BasicListeners implements Listener {

    public void sendTitle(Player player, String text, int fadeInTime, int showTime, int fadeOutTime, ChatColor color)
    {
        try
        {
            Object chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\": \"" + text + "\",color:" + color.name().toLowerCase() + "}");

            Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
            Object packet = titleConstructor.newInstance(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), chatTitle, fadeInTime, showTime, fadeOutTime);

            sendPacket(player, packet);
        }

        catch (Exception ex)
        {
            //Do something
        }
    }

    private void sendPacket(Player player, Object packet)
    {
        try
        {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        }
        catch(Exception ex)
        {
            //Do something
        }
    }

    /**
     * Get NMS class using reflection
     * @param name Name of the class
     * @return Class
     */
    private Class<?> getNMSClass(String name)
    {
        try
        {
            return Class.forName("net.minecraft.server" + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
        }
        catch(ClassNotFoundException ex)
        {
            //Do something
        }
        return null;
    }



    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        UUID u = p.getUniqueId();
        if (CoreClass.isGame()) {
            if (CoreClass.getGAMEP().contains(u)) {
                e.setJoinMessage(CoreClass.prefix + p.getName() + " s'est reconnecter au serveur !");
            } else {
                p.kickPlayer(Messages.UHC_ALREADYSTART.getMessage());
            }
        } else if (!CoreClass.isGame()) {
            e.setJoinMessage(CoreClass.prefix + p.getName() + " a rejoint le serveur !");
            CoreClass.getGAMEP().add(u);
        }
        // ->Auto Start
        int online = Bukkit.getServer().getOnlinePlayers().size();
                int maxp = Bukkit.getMaxPlayers();
                    int middle = maxp /2;
        if(middle <= online){
            GameStart.startGame();
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        for (Player p : Bukkit.getOnlinePlayers()) {

            if (!CoreClass.isGame()) {
            e.setCancelled(true);
        } else {
                if (p.hasPermission(CoreClass.permision) || p.isOp()) {
            if (e.getBlock().getType() == Material.DIAMOND_ORE) {

TextComponent tp = new TextComponent("§8§l[§2Se téléporter§8§l]");
                tp.setClickEvent(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/tp "+ e.getPlayer().getName()));
                        p.sendMessage(CoreClass.prefix + e.getPlayer().getName() + " a miné un minerais de §bdiamants");
                        p.spigot().sendMessage(tp);
                    }else if(e.getBlock().getType() == Material.GOLD_ORE){
                TextComponent tp = new TextComponent("§8§l[§2Se téléporter§8§l]");
                tp.setClickEvent(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/tp "+ e.getPlayer().getName()));
                p.sendMessage(CoreClass.prefix + e.getPlayer().getName() + " a miné un minerais de §e§ld'or");
                p.spigot().sendMessage(tp);
            }
                }
            }
        }

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (CoreClass.isGame() == false) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (CoreClass.isPvp() == false) {
            if (event.getDamager() instanceof Player) {
                if (event.getEntity() instanceof Player) {
                    event.setCancelled(true);
                    event.getDamager().sendMessage(Messages.UHC_PVPOFF.getMessage());
                }
            }
        } else {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onLeft(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        UUID u = p.getUniqueId();
        if (CoreClass.isGame()) {
            CoreClass.getGAMEP().add(u);
        } else {
            if (CoreClass.getGAMEP().contains(u)) {
                CoreClass.getGAMEP().remove(u);
            }
        }
    }

    public static ItemStack spawnHead(Player p) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        MaterialData data = item.getData();
        data.setData((byte) 3);
        item.setData(data);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(p.getName());
        item.setItemMeta(meta);


        return item;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player victim = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();
        if (CoreClass.isGame() == true && e.getEntity().getKiller() != null) {

            victim.getWorld().strikeLightningEffect(victim.getLocation());
            victim.setGameMode(GameMode.SPECTATOR);
            victim.teleport(Bukkit.getServer().getWorld("nillegrosfdp").getSpawnLocation());
            victim.sendMessage(Messages.UHC_DEATH.getMessage());
            e.setDeathMessage(CoreClass.prefix + victim.getName() + " à été tué par " + killer.getName());

            if ((e.getEntity() instanceof Player)) {
                Player p = e.getEntity();

                p.getWorld().getBlockAt(p.getLocation()).setType(Material.NETHER_FENCE);
                p.getWorld().getBlockAt(p.getLocation().add(0.0D, 1.0D, 0.0D)).setType(Material.SKULL);

                Block b = p.getWorld().getBlockAt(p.getLocation().add(0.0D, 1.0D, 0.0D));
                b.setData((byte) 1);
                BlockState state = p.getWorld().getBlockAt(p.getLocation().add(0.0D, 1.0D, 0.0D)).getState();
                if ((state instanceof Skull)) {
                    Skull skull = (Skull) state;
                    skull.setSkullType(SkullType.PLAYER);
                    skull.setOwner(p.getName());
                    skull.update();
                    p.spigot().respawn();
                    sendTitle(p,"Vous êtes mort",5,10,5,ChatColor.GRAY);
                }
            } else {
                e.setDeathMessage(CoreClass.prefix + e.getEntity().getPlayer().getName() + " est mort");
                victim.spigot().respawn();
                sendTitle(victim,"Vous êtes mort",5,10,5,ChatColor.GRAY);
            }
int gm0 = 0;
            int gm3 = 0;
            for(Player ps : Bukkit.getOnlinePlayers()){
       if(ps.getGameMode() == GameMode.SURVIVAL){
                gm0 ++;
            }
            if(ps.getGameMode() == GameMode.SPECTATOR){
       gm3++;
       }

       if(gm0 == 1 && Bukkit.getOnlinePlayers().size() <= 2 ){//Il reste un seul joueur en vie
        CoreClass.setEnd(true);



       }else if(ps.getGameMode() == GameMode.SURVIVAL){
        this.sendTitle(ps,"Vous avez gagné", 5,10,5,ChatColor.GOLD);
       }
            }
        }
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e){
        if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lGolden Head")){
            e.getPlayer().sendMessage("AMAZING !");
        }
    }
}


