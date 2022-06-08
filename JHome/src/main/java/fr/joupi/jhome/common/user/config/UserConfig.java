package fr.joupi.jhome.common.user.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.joupi.jhome.utils.file.FileUtils;
import fr.joupi.jhome.common.home.adapter.HomeAdapter;
import fr.joupi.jhome.common.home.core.Home;
import fr.joupi.jhome.common.user.UserManager;
import fr.joupi.jhome.common.user.User;
import fr.joupi.jhome.utils.threading.MultiThreading;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.LinkedList;

@Getter
public class UserConfig {

    private final UserManager userManager;
    private final Gson gson;

    public UserConfig(UserManager userManager) {
        this.userManager = userManager;
        this.gson = buildGson();
    }

    private Gson buildGson() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .serializeNulls()
                .registerTypeAdapter(Home.class, new HomeAdapter())
                .create();
    }

    public void loadUser(Player player) {
        File file = new File(getSaveDir(), player.getUniqueId().toString() + ".json");

        if (!file.exists()) {
            User newUser = new User(player.getUniqueId(), new LinkedList<>(), 1);

            FileUtils.createFile(file);
            saveUser(newUser);
        }

        MultiThreading.runAsync(() -> getUserManager().addUser(deserialize(FileUtils.loadContent(file))));
    }

    public void saveUser(User user) {
        FileUtils.save(new File(getSaveDir(), user.getUuid().toString() + ".json"), serialize(user));
    }

    public String serialize(User profile) {
        return getGson().toJson(profile);
    }

    public User deserialize(String json) {
        return getGson().fromJson(json, User.class);
    }

    public File getSaveDir() {
        return new File(getUserManager().getPlugin().getDataFolder(), "/profiles/");
    }

}
