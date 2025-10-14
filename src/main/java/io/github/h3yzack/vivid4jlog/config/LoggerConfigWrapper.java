package io.github.h3yzack.vivid4jlog.config;

import io.github.h3yzack.vivid4jlog.theme.EmojiTheme;

/**
 * Configuration wrapper that allows per-logger overrides
 */
public class LoggerConfigWrapper {
    private final Vivid4jLogConfig globalConfig;
    private Boolean colorEnabledOverride = null;

    public LoggerConfigWrapper(Vivid4jLogConfig globalConfig) {
        this.globalConfig = globalConfig;
    }
    
    // Override methods
    public LoggerConfigWrapper setColorEnabled(boolean enabled) {
        this.colorEnabledOverride = enabled;
        return this;
    }
    
    // Getter methods that check overrides first, then fall back to global config
    public boolean isColorEnabled() {
        return colorEnabledOverride != null ? colorEnabledOverride : globalConfig.isColorEnabled();
    }
    
    // Delegate all other methods to global config
    public EmojiTheme getDefaultTheme() { return globalConfig.getDefaultTheme(); }
    public String getCustomPrefix() { return globalConfig.getCustomPrefix(); }
    public String getCustomSuffix() { return globalConfig.getCustomSuffix(); }
    
    public boolean shouldApplyColors() {
        return isColorEnabled();
    }
}