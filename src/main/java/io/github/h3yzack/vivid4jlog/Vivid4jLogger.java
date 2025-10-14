package io.github.h3yzack.vivid4jlog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import io.github.h3yzack.vivid4jlog.config.LoggerConfigWrapper;
import io.github.h3yzack.vivid4jlog.config.Vivid4jLogConfig;
import io.github.h3yzack.vivid4jlog.format.MessageFormatter;
import io.github.h3yzack.vivid4jlog.theme.EmojiTheme;

public class Vivid4jLogger {

    private final Logger logger;
    private EmojiTheme currentTheme;
    private final LoggerConfigWrapper config;
    private final MessageFormatter formatter;
    
    // Custom markers for SUCCESS and COMPLETED levels
    private static final Marker SUCCESS_MARKER = MarkerFactory.getMarker("SUCCESS");
    private static final Marker COMPLETED_MARKER = MarkerFactory.getMarker("COMPLETED");

    private Vivid4jLogger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
        this.config = new LoggerConfigWrapper(Vivid4jLogConfig.getInstance());
        this.currentTheme = config.getDefaultTheme();
        this.formatter = new MessageFormatter(currentTheme, config);
    }
    
    private Vivid4jLogger(Class<?> clazz, EmojiTheme theme) {
        this.logger = LoggerFactory.getLogger(clazz);
        this.config = new LoggerConfigWrapper(Vivid4jLogConfig.getInstance());
        this.currentTheme = theme;
        this.formatter = new MessageFormatter(currentTheme, config);
    }

    public static Vivid4jLogger create(Class<?> clazz) {
        return new Vivid4jLogger(clazz);
    }
    
    public static Vivid4jLogger create(Class<?> clazz, EmojiTheme theme) {
        return new Vivid4jLogger(clazz, theme);
    }

    public static Vivid4jLogger create() {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
        try {
            return create(Class.forName(caller.getClassName()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not find calling class", e);
        }
    }

    public static Vivid4jLogger create(EmojiTheme theme) {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
        try {
            return create(Class.forName(caller.getClassName()), theme);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not find calling class", e);
        }
    }
    
    /**
     * Create logger optimized for console output (with colors)
     */
    public static Vivid4jLogger createForConsole() {
        Vivid4jLogger logger = create();
        logger.config.setColorEnabled(true);
        return logger;
    }
    
    /**
     * Create logger optimized for console output with specific theme
     */
    public static Vivid4jLogger createForConsole(EmojiTheme theme) {
        Vivid4jLogger logger = create(theme);
        logger.config.setColorEnabled(true);
        return logger;
    }
    
    /**
     * Create logger optimized for file output (no colors)
     */
    public static Vivid4jLogger createForFile() {
        Vivid4jLogger logger = create();
        logger.config.setColorEnabled(false);
        return logger;
    }
    
    /**
     * Create logger optimized for file output with specific theme
     */
    public static Vivid4jLogger createForFile(EmojiTheme theme) {
        Vivid4jLogger logger = create(theme);
        logger.config.setColorEnabled(false);
        return logger;
    }



    private String formatMessage(String level, String msg) {
        return formatter.format(level, msg);
    }
    
    /**
     * Format message using auto-detection (public method for testing/demo purposes)
     */
    public String format(String level, String msg) {
        return formatter.format(level, msg);
    }
    
    /**
     * Format message specifically for console output (with colors)
     */
    public String formatForConsole(String level, String msg) {
        return formatter.formatForConsole(level, msg);
    }
    
    /**
     * Format message specifically for file output (no colors)
     */
    public String formatForFile(String level, String msg) {
        return formatter.formatForFile(level, msg);
    }

    // --- TRACE ---
    public void trace(String msg) { logger.trace(formatMessage("TRACE", msg)); }
    public void trace(String format, Object arg) { logger.trace(formatMessage("TRACE", format), arg); }
    public void trace(String format, Object arg1, Object arg2) { logger.trace(formatMessage("TRACE", format), arg1, arg2); }
    public void trace(String format, Object... arguments) { logger.trace(formatMessage("TRACE", format), arguments); }
    public void trace(String msg, Throwable t) { logger.trace(formatMessage("TRACE", msg), t); }

    // --- DEBUG ---
    public void debug(String msg) { logger.debug(formatMessage("DEBUG", msg)); }
    public void debug(String format, Object arg) { logger.debug(formatMessage("DEBUG", format), arg); }
    public void debug(String format, Object arg1, Object arg2) { logger.debug(formatMessage("DEBUG", format), arg1, arg2); }
    public void debug(String format, Object... arguments) { logger.debug(formatMessage("DEBUG", format), arguments); }
    public void debug(String msg, Throwable t) { logger.debug(formatMessage("DEBUG", msg), t); }

    // --- INFO ---
    public void info(String msg) { logger.info(formatMessage("INFO", msg)); }
    public void info(String format, Object arg) { logger.info(formatMessage("INFO", format), arg); }
    public void info(String format, Object arg1, Object arg2) { logger.info(formatMessage("INFO", format), arg1, arg2); }
    public void info(String format, Object... arguments) { logger.info(formatMessage("INFO", format), arguments); }
    public void info(String msg, Throwable t) { logger.info(formatMessage("INFO", msg), t); }

    // --- WARN ---
    public void warn(String msg) { logger.warn(formatMessage("WARN", msg)); }
    public void warn(String format, Object arg) { logger.warn(formatMessage("WARN", format), arg); }
    public void warn(String format, Object arg1, Object arg2) { logger.warn(formatMessage("WARN", format), arg1, arg2); }
    public void warn(String format, Object... arguments) { logger.warn(formatMessage("WARN", format), arguments); }
    public void warn(String msg, Throwable t) { logger.warn(formatMessage("WARN", msg), t); }

    // --- ERROR ---
    public void error(String msg) { logger.error(formatMessage("ERROR", msg)); }
    public void error(String format, Object arg) { logger.error(formatMessage("ERROR", format), arg); }
    public void error(String format, Object arg1, Object arg2) { logger.error(formatMessage("ERROR", format), arg1, arg2); }
    public void error(String format, Object... arguments) { logger.error(formatMessage("ERROR", format), arguments); }
    public void error(String msg, Throwable t) { logger.error(formatMessage("ERROR", msg), t); }

    // --- SUCCESS (Custom Level) ---
    public void success(String msg) { logger.info(SUCCESS_MARKER, formatMessage("SUCCESS", msg)); }
    public void success(String format, Object arg) { logger.info(SUCCESS_MARKER, formatMessage("SUCCESS", format), arg); }
    public void success(String format, Object arg1, Object arg2) { logger.info(SUCCESS_MARKER, formatMessage("SUCCESS", format), arg1, arg2); }
    public void success(String format, Object... arguments) { logger.info(SUCCESS_MARKER, formatMessage("SUCCESS", format), arguments); }
    public void success(String msg, Throwable t) { logger.info(SUCCESS_MARKER, formatMessage("SUCCESS", msg), t); }

    // --- COMPLETED (Custom Level) ---
    public void completed(String msg) { logger.info(COMPLETED_MARKER, formatMessage("COMPLETED", msg)); }
    public void completed(String format, Object arg) { logger.info(COMPLETED_MARKER, formatMessage("COMPLETED", format), arg); }
    public void completed(String format, Object arg1, Object arg2) { logger.info(COMPLETED_MARKER, formatMessage("COMPLETED", format), arg1, arg2); }
    public void completed(String format, Object... arguments) { logger.info(COMPLETED_MARKER, formatMessage("COMPLETED", format), arguments); }
    public void completed(String msg, Throwable t) { logger.info(COMPLETED_MARKER, formatMessage("COMPLETED", msg), t); }

    // --- Configuration Methods ---
    public Vivid4jLogger withTheme(EmojiTheme theme) {
        this.currentTheme = theme;
        // Note: The formatter will use the updated theme through the reference
        return this;
    }
    
    public void setTheme(EmojiTheme theme) {
        this.currentTheme = theme;
    }
    
    public EmojiTheme getCurrentTheme() {
        return currentTheme;
    }
    
    public LoggerConfigWrapper getConfig() {
        return config;
    }

    // --- Fun Utility Methods ---
    public void celebrate(String msg) {
        success("🎉🎊 " + msg + " 🎊🎉");
    }
    
    public void thinking(String msg) {
        info("🤔 " + msg);
    }
    
    public void rocket(String msg) {
        info("🚀 " + msg);
    }
    
    public void sparkles(String msg) {
        info("✨ " + msg + " ✨");
    }
    
    public void progress(String msg) {
        info("⏳ " + msg);
    }
    
    public void tada(String msg) {
        success("🎉 " + msg);
    }

    public Logger unwrap() { return logger; }
}
