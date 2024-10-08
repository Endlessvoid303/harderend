package test.endlessvoid.endfight;

import org.bukkit.plugin.java.JavaPlugin;

public final class Endfight extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new dragonevents(),this);
        getLogger().info("endfight plugin started");

    }

    @Override
    public void onDisable() {
        getLogger().info("endfight plugin stopped");
    }
}
