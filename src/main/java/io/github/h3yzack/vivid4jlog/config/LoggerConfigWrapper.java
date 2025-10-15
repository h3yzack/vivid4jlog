package io.github.h3yzack.vivid4jlog.config;

import io.github.h3yzack.vivid4jlog.theme.EmojiTheme;

/**
 * Configuration wrapper that allows per-logger overrides while falling back to global configuration.
 * Provides a way to customize individual logger settings without affecting global defaults.
 * 
 * @author Zuhaimi A.
 */
public class LoggerConfigWrapper {
    private final Vivid4jLogConfig globalConfig;
    private Boolean colorEnabledOverride = null;

    /**
     * Creates a configuration wrapper.
     * 
     * @param globalConfig the global configuration to wrap
     */
    public LoggerConfigWrapper(Vivid4jLogConfig globalConfig) {
        this.globalConfig = globalConfig;
    }
    
    /**
     * Sets the color enabled override for this logger.
     * 
     * @param enabled true to enable colors, false to disable
     * @return this wrapper for method chaining
     */
    public LoggerConfigWrapper setColorEnabled(boolean enabled) {
        this.colorEnabledOverride = enabled;
        return this;
    }
    
    /**
     * Checks if colors are enabled, using override if set, otherwise global config.
     * 
     * @return true if colors are enabled
     */
    public boolean isColorEnabled() {
        return colorEnabledOverride != null ? colorEnabledOverride : globalConfig.isColorEnabled();
    }
    
    /**
     * Gets the default theme from global config.
     * 
     * @return the default emoji theme
     */
    public EmojiTheme getDefaultTheme() { return globalConfig.getDefaultTheme(); }
    
    /**
     * Gets the custom prefix from global config.
     * 
     * @return the custom prefix string
     */
    public String getCustomPrefix() { return globalConfig.getCustomPrefix(); }
    
    /**
     * Gets the custom suffix from global config.
     * 
     * @return the custom suffix string
     */
    public String getCustomSuffix() { return globalConfig.getCustomSuffix(); }
    
    /**
     * Determines if colors should be applied to log output.
     * 
     * @return true if colors should be applied
     */
    public boolean shouldApplyColors() {
        return isColorEnabled();
    }
}