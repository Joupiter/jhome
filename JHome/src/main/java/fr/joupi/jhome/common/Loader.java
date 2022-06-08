package fr.joupi.jhome.common;

import fr.joupi.jhome.JHome;
import fr.joupi.jhome.common.home.HomeManager;
import fr.joupi.jhome.common.teleport.TeleportationManager;
import fr.joupi.jhome.common.user.listener.UserListener;
import fr.joupi.jhome.utils.loader.AbstractLoader;
import fr.joupi.jhome.common.user.UserManager;
import fr.joupi.jhome.utils.threading.MultiThreading;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Getter
public class Loader extends AbstractLoader<JHome> {

    private final Config config;

    private final UserManager userManager;
    private final HomeManager homeManager;
    private final TeleportationManager teleportationManager;

    public Loader(JHome plugin) {
        super(plugin);
        this.config = new Config(plugin);
        this.userManager = new UserManager(plugin);
        this.homeManager = new HomeManager(plugin);
        this.teleportationManager = new TeleportationManager(plugin);
    }

    @Override
    public void load() {
        registerListeners(new UserListener(getUserManager()));
    }

    @Override
    public void unload() {
        MultiThreading.runAsync(() ->
            Bukkit.getOnlinePlayers()
                    .stream()
                    .map(Player::getUniqueId)
                    .map(getUserManager()::getUser)
                    .forEach(getUserManager().getUserConfig()::saveUser)
        );
    }
}
