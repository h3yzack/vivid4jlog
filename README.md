# Vivid4jLog

A lightweight Java logging library that adds emoji themes and optional ANSI colors to log messages. Use directly in code or integrate via a Logback appender.

## Features

- Multiple emoji themes for common log levels
- Optional ANSI color output for consoles
- Works with or without Logback
- Per-logger and global configuration
- Extra markers: SUCCESS and COMPLETED

## Installation

Add the dependency to your build.

Maven:

```xml
<dependency>
	<groupId>io.github.h3yzack</groupId>
	<artifactId>vivid4jlog</artifactId>
	<version>${latest.version}</version>
</dependency>
```

Gradle:

```gradle
implementation 'io.github.h3yzack:vivid4jlog:${latest.version}'
```

## Quick start

```java
import io.github.h3yzack.vivid4jlog.Vivid4jLogger;

public class MyApp {
		private static final Vivid4jLogger log = Vivid4jLogger.create(MyApp.class);

		public static void main(String[] args) {
				log.info("Starting up");
				log.success("Connected");
				log.warn("Low disk space");
				log.error("Failed to write file");
		}
}
```

## Configuration

Choose one or mix as needed.

### 1) Programmatic

```java
import io.github.h3yzack.vivid4jlog.Vivid4jLogger;
import io.github.h3yzack.vivid4jlog.theme.EmojiTheme;

Vivid4jLogger log = Vivid4jLogger.create(MyService.class, EmojiTheme.TECH);
log.getConfig().setColorEnabled(true);     // enable colors for this logger
log.setTheme(EmojiTheme.GAMING);           // switch theme at runtime
```

### 2) Properties file (global defaults)

Create `vivid4jlog.properties` on the classpath:

```properties
vivid4jlog.theme=TECH
vivid4jlog.color.enabled=true
vivid4jlog.custom.prefix=[APP]
vivid4jlog.custom.suffix=
```

### 3) Logback integration

Wrap your base appenders with the Vivid4jLog appender.

```xml
<configuration>
	<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
		<encoder>
			<pattern>%d{HH:mm:ss} %-5level %logger{36} - %msg%n</pattern>
			<charset>UTF-8</charset>
		</encoder>
	</appender>

	<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
		<file>logs/app.log</file>
		<rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
			<fileNamePattern>logs/app.%d{yyyy-MM-dd}.%i.log</fileNamePattern>
			<maxFileSize>10MB</maxFileSize>
			<maxHistory>30</maxHistory>
			<totalSizeCap>300MB</totalSizeCap>
		</rollingPolicy>
		<encoder>
			<pattern>%d{HH:mm:ss} %-5level %logger{36} - %msg%n</pattern>
			<charset>UTF-8</charset>
		</encoder>
	</appender>

	<!-- Themed wrappers -->
	<appender name="THEMED_CONSOLE" class="io.github.h3yzack.vivid4jlog.appender.Vivid4jLogAppender">
		<theme>TECH</theme>
		<colorEnabled>true</colorEnabled>
		<appender-ref ref="CONSOLE"/>
	</appender>

	<appender name="THEMED_FILE" class="io.github.h3yzack.vivid4jlog.appender.Vivid4jLogAppender">
		<theme>CLASSIC</theme>
		<colorEnabled>false</colorEnabled>
		<appender-ref ref="FILE"/>
	</appender>

	<!-- Apply to selected packages -->
	<logger name="com.myapp" level="DEBUG" additivity="false">
		<appender-ref ref="THEMED_CONSOLE"/>
		<appender-ref ref="THEMED_FILE"/>
	</logger>

	<root level="INFO">
		<appender-ref ref="CONSOLE"/>
		<appender-ref ref="FILE"/>
	</root>
</configuration>
```

## Themes

Built-in themes and their emojis per level:

| Theme      | TRACE | DEBUG | INFO | WARN | ERROR | SUCCESS | COMPLETED |
|------------|:-----:|:-----:|:----:|:----:|:-----:|:-------:|:---------:|
| CLASSIC    | 🔍    | 🐛    | ✅   | ⚠️   | ❌    | 🎉      | ✨        |
| TECH       | 🔬    | ⚙️    | 💡   | ⚡   | 🔥    | 🚀      | ⭐        |
| GAMING     | 🎯    | 🎮    | 🏆   | ⚔️   | 💀    | 🎊      | 👑        |
| NATURE     | 🌱    | 🍃    | 🌸   | 🌰   | 🌋    | 🌺      | 🌈        |
| MINIMAL    | ·     | -     | ✓    | !    | ✗     | ✓       | ✓         |
| COLORFUL   | 🔮    | 🎨    | 💙   | 💛   | 💥    | 💚      | 💜        |


## Color mapping

When colors are enabled:

- TRACE: bright black
- DEBUG: cyan
- INFO: blue
- WARN: yellow
- ERROR: red
- SUCCESS: green
- COMPLETED: purple

## Requirements

- Java 17+
- SLF4J 2.0+
- Logback 1.4+ (only for appender integration)

## License

MIT — see [LICENSE](LICENSE).

## Author

@h3yzack

## Contributing

Small, focused improvements are welcome. Please open a PR.

