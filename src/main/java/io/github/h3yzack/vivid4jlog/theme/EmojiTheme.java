package io.github.h3yzack.vivid4jlog.theme;

import java.util.Map;

/**
 * Enum representing different emoji themes for logging.
 * Each theme provides a distinct set of emojis for different log levels.
 */
public enum EmojiTheme {
    CLASSIC("classic"),
    NATURE("nature"),
    TECH("tech"),
    GAMING("gaming"),
    MINIMAL("minimal"),
    COLORFUL("colorful");
    
    private final String name;
    
    EmojiTheme(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    /**
     * Get emoji mappings for this theme
     */
    public Map<String, String> getEmojiMap() {
        return switch (this) {
            case CLASSIC -> Map.of(
                "TRACE", " 🔍 ",
                "DEBUG", " 🐛 ",
                "INFO",  " ✅ ",
                "WARN",  " ⚠️ ",
                "ERROR", " ❌ ",
                "SUCCESS", " 🎉 ",
                "COMPLETED", " ✨ "
            );
            case NATURE -> Map.of(
                "TRACE", " 🌱 ",
                "DEBUG", " 🍃 ",
                "INFO",  " 🌸 ",
                "WARN",  " 🌰 ",
                "ERROR", " 🌋 ",
                "SUCCESS", " 🌺 ",
                "COMPLETED", " 🌈 "
            );
            case TECH -> Map.of(
                "TRACE", " 🔬 ",
                "DEBUG", " ⚙️ ",
                "INFO",  " 💡 ",
                "WARN",  " ⚡ ",
                "ERROR", " 🔥 ",
                "SUCCESS", " 🚀 ",
                "COMPLETED", " ⭐ "
            );
            case GAMING -> Map.of(
                "TRACE", " 🎯 ",
                "DEBUG", " 🎮 ",
                "INFO",  " 🏆 ",
                "WARN",  " ⚔️ ",
                "ERROR", " 💀 ",
                "SUCCESS", " 🎊 ",
                "COMPLETED", " 👑 "
            );
            case MINIMAL -> Map.of(
                "TRACE", " · ",
                "DEBUG", " - ",
                "INFO",  " ✓ ",
                "WARN",  " ! ",
                "ERROR", " ✗ ",
                "SUCCESS", " ✓ ",
                "COMPLETED", " ✓ "
            );
            case COLORFUL -> Map.of(
                "TRACE", " 🔮 ",
                "DEBUG", " 🎨 ",
                "INFO",  " 💙 ",
                "WARN",  " 💛 ",
                "ERROR", " 💥 ",
                "SUCCESS", " 💚 ",
                "COMPLETED", " 💜 "
            );
        };
    }
    
    public static EmojiTheme fromString(String name) {
        for (EmojiTheme theme : values()) {
            if (theme.name.equalsIgnoreCase(name)) {
                return theme;
            }
        }
        return CLASSIC; // Default fallback
    }
}