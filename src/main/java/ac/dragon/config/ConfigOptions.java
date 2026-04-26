package ac.dragon.config;

import java.util.List;

public enum ConfigOptions {
    
    PREFIX("prefix","&#D154F4&l&oD&#C855F0&l&or&#BF55EB&l&oa&#B656E7&l&og&#AD57E2&l&oo&#A458DE&l&on&#9B58D9&l&oA&#9359D5&l&on&#8A5AD1&l&ot&#815ACC&l&oi&#785BC8&l&oc&#6F5CC3&l&oh&#665DBF&l&oe&#5D5DBA&l&oa&#545EB6&l&ot &7|&r "),
    HELP_COMMAND("help_command", List.of(
    "&#D154F4&l&oD&#C855F0&l&or&#BF55EB&l&oa&#B656E7&l&og&#AD57E2&l&oo&#A458DE&l&on&#9B58D9&l&oA&#9359D5&l&on&#8A5AD1&l&ot&#815ACC&l&oi&#785BC8&l&oc&#6F5CC3&l&oh&#665DBF&l&oe&#5D5DBA&l&oa&#545EB6&l&ot&r &5Help ", 
    " &7Coming soon...")),
    FORMAT("format", "%prefix% %p% &dfailed &5%check% &7(&d%vl% &8/ &5%max%&7)"),
    INCORRECT_USAGE("incorrect_usage", "%prefix% &cIncorrect usage! For help run &8/dragon help&c!"),
    CONFIG_CHECK_FORMAT("check_config_format", "&8- &5&l%checkname%&r &7maxVL: &5%maxVL% &7maxBuffer: &5%max_buffer% &7decay: &5%decay%"),
    CONFIG_COMMAND_HEADER("config_command_header", "&5&lCheck settings: "),
    RELOAD_COMMAND("reload_command", "%prefix% &5Reloaded the configuration file!");


    private final String path;
    private final Object defaultvalue;

    ConfigOptions(String path, Object defaultvalue){
        this.defaultvalue = defaultvalue;
        this.path = path;
    }
    public String getPath(){
        return path;
    }
    public Object getDefault(){
        return defaultvalue;
    }

}
