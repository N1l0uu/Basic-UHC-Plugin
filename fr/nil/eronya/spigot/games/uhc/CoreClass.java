package fr.nil.eronya.spigot.games.uhc;

import fr.nil.eronya.spigot.games.uhc.commands.CommandPVP;
import fr.nil.eronya.spigot.games.uhc.commands.CommandStart;
import fr.nil.eronya.spigot.games.uhc.listeners.BasicListeners;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class CoreClass extends JavaPlugin {
private static boolean game = false;
private static boolean pvp = false;
private static boolean end = false;


    public static final String prefix = "§8[§l§6§lEronya§8] §c>> ";
    public static final String permision = "uhc.mod";

private static Set<UUID> GAMEP = new HashSet<UUID>();

/*
* si game -> false = non -> wait
* si game -> true = oui -> Lancer la game
 */
    @Override
    public void onDisable() {
        setGame(false);

    }

    @Override
    public void onEnable() {
        setGame(false);
        getCommand("start").setExecutor(new CommandStart());
        getCommand("pvp").setExecutor(new CommandPVP());
setupHeads();
        Bukkit.getPluginManager().registerEvents(new BasicListeners() , this);
    }

    public static boolean isGame() {
        return game;
    }

    public static void setGame(boolean game) {
        CoreClass.game = game;
    }

    public static Set<UUID> getGAMEP() {
        return GAMEP;
    }

    public static boolean isPvp() {
        return pvp;
    }

    public static void setPvp(boolean pvp) {
        CoreClass.pvp = pvp;
    }

    public static boolean isEnd() {
        return end;
    }

    public static void setEnd(boolean end) {
        CoreClass.end = end;
    }


    public void setupHeads()
    {
        ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta applem = apple.getItemMeta();
        applem.setDisplayName("§6§lGolden Head");
        apple.setItemMeta(applem);

        ShapedRecipe goldenhead = new ShapedRecipe(apple);
        goldenhead.shape(new String[] { "@@@", "@#@", "@@@" });
        goldenhead.setIngredient('@', Material.GOLD_INGOT);
        goldenhead.setIngredient('#', Material.SKULL_ITEM, 3);
        Bukkit.getServer().addRecipe(goldenhead);
    }
}
