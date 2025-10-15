package io.github.h3yzack.vivid4jlog.theme;

import java.util.Map;

/**
 * Defines different emoji themes for log messages.
 * Each theme provides a unique set of emojis for various log levels.
 * 
 * @author Zuhaimi A.
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
    
    /**
     * Gets the name of this theme.
     * 
     * @return the theme name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns a map of log levels to their corresponding emoji symbols.
     * 
     * @return the emoji mappings for this theme
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
    
    /**
     * Converts a string to the corresponding EmojiTheme.
     * 
     * @param name the theme name
     * @return the matching EmojiTheme, or CLASSIC if no match is found
     */
    public static EmojiTheme fromString(String name) {
        for (EmojiTheme theme : values()) {
            if (theme.name.equalsIgnoreCase(name)) {
                return theme;
            }
        }
        return CLASSIC; // Default fallback
    }
}