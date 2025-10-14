package io.github.h3yzack.vivid4jlog.format;


import java.util.Map;

import io.github.h3yzack.vivid4jlog.config.LoggerConfigWrapper;
import io.github.h3yzack.vivid4jlog.theme.AnsiColors;
import io.github.h3yzack.vivid4jlog.theme.EmojiTheme;

/**
 * Message formatter that handles different output formats
 */
public class MessageFormatter {
    
    private final EmojiTheme theme;
    private final LoggerConfigWrapper config;
    
    public MessageFormatter(EmojiTheme theme, LoggerConfigWrapper config) {
        this.theme = theme;
        this.config = config;
    }
    
    /**
     * Format message for console output (with colors)
     */
    public String formatForConsole(String level, String msg) {
        return formatMessage(level, msg, true);
    }
    
    /**
     * Format message for file output (no colors)
     */
    public String formatForFile(String level, String msg) {
        return formatMessage(level, msg, false);
    }
    
    /**
     * Format message with auto-detection
     */
    public String format(String level, String msg) {
        boolean useColors = config.shouldApplyColors();
        return formatMessage(level, msg, useColors);
    }
    
    /**
     * Internal formatting method
     */
    private String formatMessage(String level, String msg, boolean useColors) {
        StringBuilder formatted = new StringBuilder();
        
        // Add custom prefix
        if (!config.getCustomPrefix().isEmpty()) {
            formatted.append(config.getCustomPrefix()).append(" ");
        }
        
        // Add emoji
        Map<String, String> emojiMap = theme.getEmojiMap();
        formatted.append(emojiMap.getOrDefault(level, " "));
        
        // Add the message
        String finalMessage = formatted.append(msg).toString();
        
        // Add custom suffix
        if (!config.getCustomSuffix().isEmpty()) {
            finalMessage += " " + config.getCustomSuffix();
        }
        
        // Apply colors if requested and enabled
        if (useColors && config.isColorEnabled()) {
            String color = AnsiColors.getColorForLevel(level);
            finalMessage = AnsiColors.colorize(finalMessage, color);
        }
        
        return finalMessage;
    }
}