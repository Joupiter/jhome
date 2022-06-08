package fr.joupi.jhome.common.user;

import com.google.common.collect.Maps;
import fr.joupi.jhome.JHome;
import fr.joupi.jhome.common.user.config.UserConfig;
import fr.joupi.jhome.utils.manager.AbstractManager;
import lombok.Getter;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

@Getter
public class UserManager extends AbstractManager<JHome> {

    private final UserConfig userConfig;
    private final ConcurrentMap<UUID, User> users;

    public UserManager(JHome plugin) {
        super(plugin);
        this.userConfig = new UserConfig(this);
        this.users = Maps.newConcurrentMap();
    }

    public User getUser(UUID uuid) {
        return getUsers().get(uuid);
    }

    public Optional<User> getUser(String name) {
        return getUsers()
                .values()
                .stream()
                .filter(user -> user.getPlayer().getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public void addUser(User user) {
        getUsers().put(user.getUuid(), user);
    }

}
