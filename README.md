# Vivid4jLog

A flexible emoji and color logging library for Java, supporting both direct logger usage and Logback appender integration.

## Features
- Emoji themes for log levels (INFO, WARN, ERROR, etc.)
- ANSI color support for console output
- Easy integration with Logback via custom appender
- Simple static logger usage for direct control
- Per-logger and global configuration



## Usage Options

Vivid4jLog supports three main usage patterns:

### 1. Logback Appender Integration

Integrate Vivid4jLog with Logback for enterprise or config-driven setups. This allows you to apply emoji themes and color formatting to specific packages or appenders via XML configuration.

```xml
<configuration>
    <!-- Base appenders -->
    <appender name="CONSOLE_BASE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss} %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <appender name="FILE_BASE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/app.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>logs/app.%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <maxFileSize>10MB</maxFileSize>
            <maxHistory>30</maxHistory>
            <totalSizeCap>300MB</totalSizeCap>
        </rollingPolicy>
        <encoder>
            <pattern>%d{HH:mm:ss} %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- Vivid4jLog themed appenders -->
    <appender name="THEMED_CONSOLE" class="io.github.h3yzack.vivid4jlog.appender.Vivid4jLogAppender">
        <theme>TECH</theme>
        <colorEnabled>true</colorEnabled>
        <appender-ref ref="CONSOLE_BASE"/>
    </appender>

    <appender name="THEMED_FILE" class="io.github.h3yzack.vivid4jlog.appender.Vivid4jLogAppender">
        <theme>CLASSIC</theme>
        <colorEnabled>false</colorEnabled>
        <appender-ref ref="FILE_BASE"/>
    </appender>

    <!-- Package-specific logger -->
    <logger name="my.custom.package" level="DEBUG" additivity="false">
        <appender-ref ref="THEMED_CONSOLE"/>
        <appender-ref ref="THEMED_FILE"/>
    </logger>

    <!-- Root logger (plain) -->
    <root level="INFO">
        <appender-ref ref="CONSOLE_BASE"/>
        <appender-ref ref="FILE_BASE"/>
    </root>
</configuration>
```

- **Customize theme and color per appender** using `<theme>` and `<colorEnabled>`.
- **Apply emojis/colors only to selected packages** by configuring the logger section.
- **No color in files** (set `<colorEnabled>false</colorEnabled>` for file appenders).

### 2. Global Defaults via vivid4jlog.properties

Set global emoji theme and color settings using a properties file. This is useful for simple projects or when you want to control defaults without modifying code or XML config.

Create a file named `vivid4jlog.properties` in your classpath:

```properties
vivid4jlog.theme=TECH
vivid4jlog.color.enabled=false
vivid4jlog.custom.prefix=[APP]
vivid4jlog.custom.suffix=| MyService
```

These settings will be picked up automatically by Vivid4jLog and applied globally.

### 3. Static Logger Usage

Directly use the logger in your Java code for per-class or per-logger customization. This approach is recommended when you want explicit control in code.

```java
import io.github.h3yzack.vivid4jlog.Vivid4jLogger;
import io.github.h3yzack.vivid4jlog.theme.EmojiTheme;

public class MyService {
    private static final Vivid4jLogger log = Vivid4jLogger.create(EmojiTheme.TECH);
    // or: Vivid4jLogger.create(); for default theme

    public void doSomething() {
        log.info("Service started"); // 💡 Service started
        log.success("Operation completed"); // 🚀 Operation completed
        log.warn("Potential issue detected"); // ⚡ Potential issue detected
        log.error("Error occurred"); // 🔥 Error occurred
    }
}
```

- **Choose theme per logger**: `Vivid4jLogger.create(EmojiTheme.GAMING)`
- **Enable/disable colors globally**: `Vivid4jLogConfig.getInstance().setColorEnabled(true/false)`

## Emoji Themes
- CLASSIC
- NATURE
- TECH
- GAMING
- MINIMAL
- COLORFUL



