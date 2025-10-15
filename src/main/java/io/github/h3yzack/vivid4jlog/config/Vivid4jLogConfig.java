package io.github.h3yzack.vivid4jlog.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.github.h3yzack.vivid4jlog.theme.EmojiTheme;

/**
 * Global configuration class for Vivid4jLog.
 * Loads settings from vivid4jlog.properties and provides default configuration values.
 * This is a singleton class.
 * 
 * @author Zuhaimi A.
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
    
    /**
     * Returns the singleton instance of this configuration.
     * 
     * @return the configuration instance
     */
    public static Vivid4jLogConfig getInstance() {
        return INSTANCE;
    }
    
    /**
     * Loads configuration from vivid4jlog.properties file.
     * Uses defaults if the file is not found or cannot be read.
     */
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
    
    /**
     * Gets the default emoji theme.
     * 
     * @return the default theme
     */
    public EmojiTheme getDefaultTheme() { return defaultTheme; }
    
    /**
     * Checks if color output is enabled.
     * 
     * @return true if colors are enabled
     */
    public boolean isColorEnabled() { return colorEnabled; }
    
    /**
     * Gets the custom prefix for log messages.
     * 
     * @return the custom prefix
     */
    public String getCustomPrefix() { return customPrefix; }
    
    /**
     * Gets the custom suffix for log messages.
     * 
     * @return the custom suffix
     */
    public String getCustomSuffix() { return customSuffix; }
    
    /**
     * Sets the default theme.
     * 
     * @param theme the theme to set
     */
    public void setDefaultTheme(EmojiTheme theme) { this.defaultTheme = theme; }
    
    /**
     * Enables or disables color output.
     * 
     * @param enabled true to enable colors, false to disable
     */
    public void setColorEnabled(boolean enabled) { this.colorEnabled = enabled; }
    
    /**
     * Sets the custom prefix for log messages.
     * 
     * @param prefix the prefix to set
     */
    public void setCustomPrefix(String prefix) { this.customPrefix = prefix; }
    
    /**
     * Sets the custom suffix for log messages.
     * 
     * @param suffix the suffix to set
     */
    public void setCustomSuffix(String suffix) { this.customSuffix = suffix; }
    
    /**
     * Determines if colors should be applied based on configuration.
     * 
     * @return true if colors should be applied
     */
    public boolean shouldApplyColors() {
        return colorEnabled;
    }
}