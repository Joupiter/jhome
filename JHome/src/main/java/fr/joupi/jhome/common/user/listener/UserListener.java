package fr.joupi.jhome.common.user.listener;

import fr.joupi.jhome.common.user.UserManager;
import fr.joupi.jhome.common.user.User;
import fr.joupi.jhome.utils.threading.MultiThreading;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@Getter
@AllArgsConstructor
public class UserListener implements Listener {

    private final UserManager userManager;

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        MultiThreading.runAsync(
                () -> getUserManager().getUserConfig().loadUser(player));
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        User user = getUserManager().getUser(player.getUniqueId());

        MultiThreading.runAsync(
                () -> getUserManager().getUserConfig().saveUser(user));
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        User user = getUserManager().getUser(player.getUniqueId());

        if (event.getMessage().equalsIgnoreCase("!test")) {

            getUserManager().getPlugin().get().getHomeManager().addHome(user, "cc");
            user.sendMessage("&aCréation du home test");

            event.setCancelled(true);
        }

    }


}
