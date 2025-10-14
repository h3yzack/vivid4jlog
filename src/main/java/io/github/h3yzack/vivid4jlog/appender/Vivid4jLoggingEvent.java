package io.github.h3yzack.vivid4jlog.appender;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggerContextVO;
import io.github.h3yzack.vivid4jlog.theme.AnsiColors;
import io.github.h3yzack.vivid4jlog.theme.EmojiTheme;

import org.slf4j.Marker;


import java.util.Map;

/**
 * Wrapper for ILoggingEvent that applies emoji formatting to the message
 */
public class Vivid4jLoggingEvent implements ILoggingEvent {
    
    private final ILoggingEvent originalEvent;
    private final String formattedMessage;
    
    public Vivid4jLoggingEvent(ILoggingEvent originalEvent, EmojiTheme theme, boolean applyColors) {
        this.originalEvent = originalEvent;
        this.formattedMessage = formatMessage(originalEvent, theme, applyColors);
    }
    
    private String formatMessage(ILoggingEvent event, EmojiTheme theme, boolean applyColors) {
        String level = event.getLevel().toString();
        String originalMessage = event.getFormattedMessage();
        
        // Get emoji for the level
        Map<String, String> emojiMap = theme.getEmojiMap();
        String emoji = emojiMap.getOrDefault(level, " ");
        
        // Build the formatted message
        String formattedMsg = emoji + originalMessage;
        
        // Apply colors if requested
        if (applyColors) {
            String color = AnsiColors.getColorForLevel(level);
            formattedMsg = AnsiColors.colorize(formattedMsg, color);
        }
        
        return formattedMsg;
    }
    
    @Override
    public String getFormattedMessage() {
        return formattedMessage;
    }
    
    @Override
    public String getMessage() {
        return formattedMessage;
    }
    
    // Delegate all other methods to the original event
    @Override
    public String getThreadName() {
        return originalEvent.getThreadName();
    }
    
    @Override
    public Level getLevel() {
        return originalEvent.getLevel();
    }
    
    @Override
    public String getLoggerName() {
        return originalEvent.getLoggerName();
    }
    
    @Override
    public LoggerContextVO getLoggerContextVO() {
        return originalEvent.getLoggerContextVO();
    }
    
    @Override
    public IThrowableProxy getThrowableProxy() {
        return originalEvent.getThrowableProxy();
    }
    
    @Override
    public StackTraceElement[] getCallerData() {
        return originalEvent.getCallerData();
    }
    
    @Override
    public boolean hasCallerData() {
        return originalEvent.hasCallerData();
    }
    
    @Override
    public java.util.List<Marker> getMarkerList() {
        return originalEvent.getMarkerList();
    }
    
    @Override
    public Map<String, String> getMDCPropertyMap() {
        return originalEvent.getMDCPropertyMap();
    }
    
    @Override
    public long getSequenceNumber() {
        return originalEvent.getSequenceNumber();
    }
    
    @Override
    public java.util.List<org.slf4j.event.KeyValuePair> getKeyValuePairs() {
        return originalEvent.getKeyValuePairs();
    }
    
    @Override
    public int getNanoseconds() {
        return originalEvent.getNanoseconds();
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public Map<String, String> getMdc() {
        return originalEvent.getMdc();
    }
    
    @Override
    public long getTimeStamp() {
        return originalEvent.getTimeStamp();
    }
    
    @Override
    public void prepareForDeferredProcessing() {
        originalEvent.prepareForDeferredProcessing();
    }
    
    @Override
    public Object[] getArgumentArray() {
        return originalEvent.getArgumentArray();
    }
}