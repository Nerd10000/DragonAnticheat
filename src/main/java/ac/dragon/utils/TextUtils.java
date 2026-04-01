package ac.dragon.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class TextUtils {
    public static Component color(String s){
        return LegacyComponentSerializer.legacyAmpersand().deserialize(s);
    }
}
