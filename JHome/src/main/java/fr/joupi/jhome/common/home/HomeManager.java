package fr.joupi.jhome.common.home;

import fr.joupi.jhome.JHome;
import fr.joupi.jhome.common.home.core.Home;
import fr.joupi.jhome.common.user.User;
import fr.joupi.jhome.utils.manager.AbstractManager;

import java.util.List;
import java.util.stream.Collectors;

public class HomeManager extends AbstractManager<JHome> {

    public HomeManager(JHome plugin) {
        super(plugin);
    }

    public Home findHome(User user, String homeName) {
        return user
                .getHomes()
                .stream()
                .filter(home -> home.getName().equalsIgnoreCase(homeName))
                .findFirst()
                .orElse(null);
    }

    public boolean homeExists(User user, String homeName) {
        return findHome(user, homeName) != null;
    }

    public List<String> getHomesNames(User user) {
        return getHomes(user)
                .stream()
                .map(Home::getName)
                .collect(Collectors.toList());
    }

    public List<Home> getHomes(User user) {
        return user.getHomes();
    }

    public void addHome(User user, String homeName) {
        if (!homeExists(user, homeName))
            getHomes(user).add(new Home(homeName, user.getPlayer().getLocation()));
    }

    public void deleteHome(User user, String homeName) {
        getHomes(user).remove(findHome(user, homeName));
    }

}
