package io.github.h3yzack.vivid4jlog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.h3yzack.vivid4jlog.config.LoggerConfigWrapper;
import io.github.h3yzack.vivid4jlog.theme.EmojiTheme;

import static org.assertj.core.api.Assertions.assertThat;

public class Vivid4jLoggerTest {

    private Vivid4jLogger logger;

    @BeforeEach
    void setUp() {
        logger = Vivid4jLogger.create(Vivid4jLoggerTest.class);
    }

    @Test
    void testLoggerCreation() {
        assertThat(logger).isNotNull();
        assertThat(logger.unwrap()).isNotNull();
    }

    @Test
    void testThemeConfiguration() {
        logger.withTheme(EmojiTheme.NATURE);
        assertThat(logger.getCurrentTheme()).isEqualTo(EmojiTheme.NATURE);
        
        logger.withTheme(EmojiTheme.TECH);
        assertThat(logger.getCurrentTheme()).isEqualTo(EmojiTheme.TECH);
    }

    @Test
    void testEmojiThemeMapping() {
        for (EmojiTheme theme : EmojiTheme.values()) {
            assertThat(theme.getEmojiMap()).isNotEmpty();
            assertThat(theme.getEmojiMap()).containsKeys(
                "TRACE", "DEBUG", "INFO", "WARN", "ERROR", "SUCCESS", "COMPLETED"
            );
        }
    }

    @Test
    void testStaticFactoryMethods() {
        Vivid4jLogger logger1 = Vivid4jLogger.create();
        Vivid4jLogger logger2 = Vivid4jLogger.create(EmojiTheme.GAMING);
        Vivid4jLogger logger3 = Vivid4jLogger.create(Vivid4jLoggerTest.class);
        Vivid4jLogger logger4 = Vivid4jLogger.create(Vivid4jLoggerTest.class, EmojiTheme.NATURE);

        assertThat(logger1).isNotNull();
        assertThat(logger2).isNotNull();
        assertThat(logger3).isNotNull();
        assertThat(logger4).isNotNull();
        
        assertThat(logger2.getCurrentTheme()).isEqualTo(EmojiTheme.GAMING);
        assertThat(logger4.getCurrentTheme()).isEqualTo(EmojiTheme.NATURE);
    }

    @Test
    void testConfiguration() {
        LoggerConfigWrapper config = logger.getConfig();
        assertThat(config).isNotNull();
        assertThat(config.getDefaultTheme()).isNotNull();
    }

    @Test
    void testLoggingMethods() {
        // Test that all logging methods can be called without throwing exceptions
        logger.trace("Trace message");
        logger.debug("Debug message");
        logger.info("Info message");  
        logger.warn("Warn message");
        logger.error("Error message");
        logger.success("Success message");
        logger.completed("Completed message");
        
        // Test parameterized messages
        logger.info("User {} logged in", "john");
        logger.success("Processed {} items", 42);
        
        // Test utility methods
        logger.celebrate("Celebration message");
        logger.thinking("Thinking message");
        logger.rocket("Rocket message");
        logger.sparkles("Sparkles message");
        logger.progress("Progress message");
        logger.tada("Tada message");
    }
}
