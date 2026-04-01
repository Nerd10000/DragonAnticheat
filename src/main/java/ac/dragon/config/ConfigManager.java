package ac.dragon.config;

import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;

import ac.dragon.DragonAnticheat;

public class ConfigManager {
    
    private final FileConfiguration fileConfiguration;
    private final HashMap<ConfigOptions, Object> cacheMap = new HashMap<>();

    public ConfigManager(FileConfiguration configuration){
        fileConfiguration = configuration;
        loadAll();
    }

    public void loadAll(){
        for (ConfigOptions option : ConfigOptions.values()){
            Object value;

            if (option.getDefault() instanceof String) {
                    value = fileConfiguration.getString(option.getPath(), String.valueOf(option.getDefault()));                
            }else {
                value = fileConfiguration.get(option.getPath(), option.getDefault());
            }
            cacheMap.put(option, value);
        }
    }

    public <T> T get(ConfigOptions o,Class<T> clazz){
        return clazz.cast(cacheMap.get(o));
    }

    public void reload(){
        DragonAnticheat.getPlugin().reloadConfig();
        cacheMap.clear();
        loadAll();
    }
}
