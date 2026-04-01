package ac.dragon.config;

import java.util.*;

public enum ConfigOptions {
    
    PREFIX("prefix","&#D154F4&l&oD&#C855F0&l&or&#BF55EB&l&oa&#B656E7&l&og&#AD57E2&l&oo&#A458DE&l&on&#9B58D9&l&oA&#9359D5&l&on&#8A5AD1&l&ot&#815ACC&l&oi&#785BC8&l&oc&#6F5CC3&l&oh&#665DBF&l&oe&#5D5DBA&l&oa&#545EB6&l&ot &7|&r "),
    HELP_COMMAND("help_command", List.of(
    "&#D154F4&l&oD&#C855F0&l&or&#BF55EB&l&oa&#B656E7&l&og&#AD57E2&l&oo&#A458DE&l&on&#9B58D9&l&oA&#9359D5&l&on&#8A5AD1&l&ot&#815ACC&l&oi&#785BC8&l&oc&#6F5CC3&l&oh&#665DBF&l&oe&#5D5DBA&l&oa&#545EB6&l&ot&r &5Help ", 
    " &7Coming soon...")),
    FORMAT("format", "%prefix% %p% &dfailed &5%check% &7(&d%vl% &8/ &5%max%&7)");
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
