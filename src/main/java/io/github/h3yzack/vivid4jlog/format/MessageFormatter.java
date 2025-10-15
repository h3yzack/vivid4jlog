package io.github.h3yzack.vivid4jlog.format;

import java.util.Map;

import io.github.h3yzack.vivid4jlog.config.LoggerConfigWrapper;
import io.github.h3yzack.vivid4jlog.theme.AnsiColors;
import io.github.h3yzack.vivid4jlog.theme.EmojiTheme;

/**
 * Formats log messages with emoji and optional ANSI colors.
 * Supports different output formats for console and file logging.
 * 
 * @author Zuhaimi A.
 */
public class MessageFormatter {
    
    private final EmojiTheme theme;
    private final LoggerConfigWrapper config;
    
    /**
     * Creates a message formatter with the specified theme and configuration.
     * 
     * @param theme the emoji theme to use
     * @param config the logger configuration
     */
    public MessageFormatter(EmojiTheme theme, LoggerConfigWrapper config) {
        this.theme = theme;
        this.config = config;
    }
    
    /**
     * Formats a message using configuration settings to determine color usage.
     * 
     * @param level the log level
     * @param msg the message to format
     * @return the formatted message
     */
    public String format(String level, String msg) {
        boolean useColors = config.shouldApplyColors();
        return formatMessage(level, msg, useColors);
    }
    
    /**
     * Internal method to format a message with emoji and optional colors.
     * 
     * @param level the log level
     * @param msg the message to format
     * @param useColors whether to apply ANSI colors
     * @return the formatted message
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