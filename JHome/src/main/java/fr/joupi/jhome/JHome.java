package fr.joupi.jhome;

import fr.joupi.jhome.common.Loader;
import fr.joupi.jhome.utils.threading.MultiThreading;
import org.bukkit.plugin.java.JavaPlugin;

public final class JHome extends JavaPlugin {

    private Loader loader;

    @Override
    public void onEnable() {
        this.loader = new Loader(this);
        this.loader.load();
    }

    public Loader get() {
        return loader;
    }

    @Override
    public void onDisable() {
        this.loader.unload();
    }

}
