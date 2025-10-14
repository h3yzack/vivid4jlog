package io.github.h3yzack.vivid4jlog.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.github.h3yzack.vivid4jlog.theme.EmojiTheme;

/**
 * Configuration class for Vivid4jLogConfig.
 * Handles loading and managing logger configuration settings.
 */
public class Vivid4jLogConfig {
    private static final String CONFIG_FILE = "vivid4jlog.properties";
    private static final Vivid4jLogConfig INSTANCE = new Vivid4jLogConfig();
    
    private EmojiTheme defaultTheme = EmojiTheme.CLASSIC;
    private boolean colorEnabled = true; // Default enabled as requested
    private String customPrefix = "";
    private String customSuffix = "";
    
    private Vivid4jLogConfig() {
        loadConfig();
    }
    
    public static Vivid4jLogConfig getInstance() {
        return INSTANCE;
    }
    
    private void loadConfig() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                Properties props = new Properties();
                props.load(input);
                
                String themeStr = props.getProperty("vivid4jlog.theme", "CLASSIC");
                this.defaultTheme = EmojiTheme.fromString(themeStr);
                
                this.colorEnabled = Boolean.parseBoolean(props.getProperty("vivid4jlog.color.enabled", "true"));
                this.customPrefix = props.getProperty("vivid4jlog.custom.prefix", "");
                this.customSuffix = props.getProperty("vivid4jlog.custom.suffix", "");
            }
        } catch (IOException e) {
            // Use defaults if config file is not found or cannot be read
        }
    }
    
    // Getters
    public EmojiTheme getDefaultTheme() { return defaultTheme; }
    public boolean isColorEnabled() { return colorEnabled; }
    public String getCustomPrefix() { return customPrefix; }
    public String getCustomSuffix() { return customSuffix; }
    
    // Setters for runtime configuration
    public void setDefaultTheme(EmojiTheme theme) { this.defaultTheme = theme; }
    public void setColorEnabled(boolean enabled) { this.colorEnabled = enabled; }
    public void setCustomPrefix(String prefix) { this.customPrefix = prefix; }
    public void setCustomSuffix(String suffix) { this.customSuffix = suffix; }
    
    /**
     * Determines if colors should be applied based on configuration
     */
    public boolean shouldApplyColors() {
        return colorEnabled;
    }
}