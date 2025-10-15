package io.github.h3yzack.vivid4jlog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import io.github.h3yzack.vivid4jlog.config.LoggerConfigWrapper;
import io.github.h3yzack.vivid4jlog.config.Vivid4jLogConfig;
import io.github.h3yzack.vivid4jlog.format.MessageFormatter;
import io.github.h3yzack.vivid4jlog.theme.EmojiTheme;

/**
 * Vivid4jLogger is a customizable logger that supports emoji themes and enhanced log levels.
 * It wraps around SLF4J's Logger and provides additional functionality for formatting messages
 * with emojis and colors based on the selected theme.
 * Usage:
 * <pre>
 *     Vivid4jLogger logger = Vivid4jLogger.create(MyClass.class);
 *     logger.info("This is an info message");
 * </pre>
 * Change themes dynamically:
 * <pre>
 *     logger.setTheme(EmojiTheme.FUN);
 * </pre>
 * Custom log levels include SUCCESS and COMPLETED:
 * <pre>
 *     logger.success("Operation was successful!");
 *     logger.completed("Task completed!");
 * </pre>
 * Fun utility methods for common log patterns:
 * <pre>
 *     logger.celebrate("We did it!");
 *     logger.thinking("Hmm, let me think...");
 * </pre>
 * Configuration options are available via LoggerConfigWrapper:
 * <pre>
 *     logger.getConfig().setColorEnabled(true);
 * </pre>
 * This class is designed to be flexible and easy to use, enhancing the logging experience
 * with visual cues and thematic elements.
 * 
 * @author Zuhaimi A.
 * @version 1.0.0
 * 
 */
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

    /**
     * Create a logger for a specific class.
     * @param clazz The class for which the logger is created
     * @return A new instance of Vivid4jLogger
     */
    public static Vivid4jLogger create(Class<?> clazz) {
        return new Vivid4jLogger(clazz);
    }

    /**
     * Create a logger with a specific emoji theme.
     * @param clazz The class for which the logger is created
     * @param theme The emoji theme to use
     * @return A new instance of Vivid4jLogger
     */
    public static Vivid4jLogger create(Class<?> clazz, EmojiTheme theme) {
        return new Vivid4jLogger(clazz, theme);
    }

    /**
     * Create a logger for the calling class (auto-detected).
     * @return A new instance of Vivid4jLogger
     */
    public static Vivid4jLogger create() {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
        try {
            return create(Class.forName(caller.getClassName()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not find calling class", e);
        }
    }

    /**
     * Create a logger for the calling class (auto-detected) with specific theme.
     * @param theme The emoji theme to use
     * @return A new instance of Vivid4jLogger
     */
    public static Vivid4jLogger create(EmojiTheme theme) {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
        try {
            return create(Class.forName(caller.getClassName()), theme);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not find calling class", e);
        }
    }
    
    private String formatMessage(String level, String msg) {
        return formatter.format(level, msg);
    }
    
    /**
     * General format method
     * @param level The log level (e.g., "INFO", "ERROR")
     * @param msg The message to format
     * @return The formatted message
     */
    public String format(String level, String msg) {
        return formatter.format(level, msg);
    }

    // --- TRACE ---
    /** Logs a message at TRACE level. */
    public void trace(String msg) { logger.trace(formatMessage("TRACE", msg)); }
    /** Logs a formatted message at TRACE level. */
    public void trace(String format, Object arg) { logger.trace(formatMessage("TRACE", format), arg); }
    /** Logs a formatted message at TRACE level. */
    public void trace(String format, Object arg1, Object arg2) { logger.trace(formatMessage("TRACE", format), arg1, arg2); }
    /** Logs a formatted message at TRACE level. */
    public void trace(String format, Object... arguments) { logger.trace(formatMessage("TRACE", format), arguments); }
    /** Logs a message with exception at TRACE level. */
    public void trace(String msg, Throwable t) { logger.trace(formatMessage("TRACE", msg), t); }

    // --- DEBUG ---
    /** Logs a message at DEBUG level. */
    public void debug(String msg) { logger.debug(formatMessage("DEBUG", msg)); }
    /** Logs a formatted message at DEBUG level. */
    public void debug(String format, Object arg) { logger.debug(formatMessage("DEBUG", format), arg); }
    /** Logs a formatted message at DEBUG level. */
    public void debug(String format, Object arg1, Object arg2) { logger.debug(formatMessage("DEBUG", format), arg1, arg2); }
    /** Logs a formatted message at DEBUG level. */
    public void debug(String format, Object... arguments) { logger.debug(formatMessage("DEBUG", format), arguments); }
    /** Logs a message with exception at DEBUG level. */
    public void debug(String msg, Throwable t) { logger.debug(formatMessage("DEBUG", msg), t); }

    // --- INFO ---
    /** Logs a message at INFO level. */
    public void info(String msg) { logger.info(formatMessage("INFO", msg)); }
    /** Logs a formatted message at INFO level. */
    public void info(String format, Object arg) { logger.info(formatMessage("INFO", format), arg); }
    /** Logs a formatted message at INFO level. */
    public void info(String format, Object arg1, Object arg2) { logger.info(formatMessage("INFO", format), arg1, arg2); }
    /** Logs a formatted message at INFO level. */
    public void info(String format, Object... arguments) { logger.info(formatMessage("INFO", format), arguments); }
    /** Logs a message with exception at INFO level. */
    public void info(String msg, Throwable t) { logger.info(formatMessage("INFO", msg), t); }

    // --- WARN ---
    /** Logs a message at WARN level. */
    public void warn(String msg) { logger.warn(formatMessage("WARN", msg)); }
    /** Logs a formatted message at WARN level. */
    public void warn(String format, Object arg) { logger.warn(formatMessage("WARN", format), arg); }
    /** Logs a formatted message at WARN level. */
    public void warn(String format, Object arg1, Object arg2) { logger.warn(formatMessage("WARN", format), arg1, arg2); }
    /** Logs a formatted message at WARN level. */
    public void warn(String format, Object... arguments) { logger.warn(formatMessage("WARN", format), arguments); }
    /** Logs a message with exception at WARN level. */
    public void warn(String msg, Throwable t) { logger.warn(formatMessage("WARN", msg), t); }

    // --- ERROR ---
    /** Logs a message at ERROR level. */
    public void error(String msg) { logger.error(formatMessage("ERROR", msg)); }
    /** Logs a formatted message at ERROR level. */
    public void error(String format, Object arg) { logger.error(formatMessage("ERROR", format), arg); }
    /** Logs a formatted message at ERROR level. */
    public void error(String format, Object arg1, Object arg2) { logger.error(formatMessage("ERROR", format), arg1, arg2); }
    /** Logs a formatted message at ERROR level. */
    public void error(String format, Object... arguments) { logger.error(formatMessage("ERROR", format), arguments); }
    /** Logs a message with exception at ERROR level. */
    public void error(String msg, Throwable t) { logger.error(formatMessage("ERROR", msg), t); }

    // --- SUCCESS (Custom Level) ---
    /** Logs a message at SUCCESS level (custom level). */
    public void success(String msg) { logger.info(SUCCESS_MARKER, formatMessage("SUCCESS", msg)); }
    /** Logs a formatted message at SUCCESS level. */
    public void success(String format, Object arg) { logger.info(SUCCESS_MARKER, formatMessage("SUCCESS", format), arg); }
    /** Logs a formatted message at SUCCESS level. */
    public void success(String format, Object arg1, Object arg2) { logger.info(SUCCESS_MARKER, formatMessage("SUCCESS", format), arg1, arg2); }
    /** Logs a formatted message at SUCCESS level. */
    public void success(String format, Object... arguments) { logger.info(SUCCESS_MARKER, formatMessage("SUCCESS", format), arguments); }
    /** Logs a message with exception at SUCCESS level. */
    public void success(String msg, Throwable t) { logger.info(SUCCESS_MARKER, formatMessage("SUCCESS", msg), t); }

    // --- COMPLETED (Custom Level) ---
    /** Logs a message at COMPLETED level (custom level). */
    public void completed(String msg) { logger.info(COMPLETED_MARKER, formatMessage("COMPLETED", msg)); }
    /** Logs a formatted message at COMPLETED level. */
    public void completed(String format, Object arg) { logger.info(COMPLETED_MARKER, formatMessage("COMPLETED", format), arg); }
    /** Logs a formatted message at COMPLETED level. */
    public void completed(String format, Object arg1, Object arg2) { logger.info(COMPLETED_MARKER, formatMessage("COMPLETED", format), arg1, arg2); }
    /** Logs a formatted message at COMPLETED level. */
    public void completed(String format, Object... arguments) { logger.info(COMPLETED_MARKER, formatMessage("COMPLETED", format), arguments); }
    /** Logs a message with exception at COMPLETED level. */
    public void completed(String msg, Throwable t) { logger.info(COMPLETED_MARKER, formatMessage("COMPLETED", msg), t); }

    // --- Configuration Methods ---
    /**
     * Sets the theme and returns this logger for method chaining.
     * 
     * @param theme the emoji theme to set
     * @return this logger instance
     */
    public Vivid4jLogger withTheme(EmojiTheme theme) {
        this.currentTheme = theme;
        return this;
    }
    
    /**
     * Sets the emoji theme for this logger.
     * 
     * @param theme the emoji theme to set
     */
    public void setTheme(EmojiTheme theme) {
        this.currentTheme = theme;
    }
    
    /**
     * Gets the current emoji theme.
     * 
     * @return the current theme
     */
    public EmojiTheme getCurrentTheme() {
        return currentTheme;
    }
    
    /**
     * Gets the configuration wrapper for this logger.
     * 
     * @return the configuration wrapper
     */
    public LoggerConfigWrapper getConfig() {
        return config;
    }

    // --- Fun Utility Methods ---
    /** Logs a celebration message with party emojis. */
    public void celebrate(String msg) {
        success("🎉🎊 " + msg + " 🎊🎉");
    }
    
    /** Logs a thinking message with thinking emoji. */
    public void thinking(String msg) {
        info("🤔 " + msg);
    }
    
    /** Logs a rocket message with rocket emoji. */
    public void rocket(String msg) {
        info("🚀 " + msg);
    }
    
    /** Logs a sparkles message with sparkle emojis. */
    public void sparkles(String msg) {
        info("✨ " + msg + " ✨");
    }
    
    /** Logs a progress message with hourglass emoji. */
    public void progress(String msg) {
        info("⏳ " + msg);
    }
    
    /** Logs a tada message with party emoji. */
    public void tada(String msg) {
        success("🎉 " + msg);
    }

    /**
     * Unwraps and returns the underlying SLF4J logger.
     * 
     * @return the underlying SLF4J logger
     */
    public Logger unwrap() { return logger; }
}
