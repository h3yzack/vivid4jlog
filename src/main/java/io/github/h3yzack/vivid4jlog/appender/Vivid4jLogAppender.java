package io.github.h3yzack.vivid4jlog.appender;

import java.util.Iterator;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.spi.AppenderAttachable;
import ch.qos.logback.core.spi.AppenderAttachableImpl;
import io.github.h3yzack.vivid4jlog.theme.EmojiTheme;

public class Vivid4jLogAppender extends AppenderBase<ILoggingEvent>
        implements AppenderAttachable<ILoggingEvent> {

    private final AppenderAttachableImpl<ILoggingEvent> attachable = new AppenderAttachableImpl<>();
    private EmojiTheme theme = EmojiTheme.CLASSIC;
    private boolean colorEnabled = true;

    // setters used from logback-spring.xml
    public void setTheme(String themeName) {
        this.theme = EmojiTheme.fromString(themeName);
    }
    public void setColorEnabled(boolean colorEnabled) {
        this.colorEnabled = colorEnabled;
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        // wrap event message with emojis/colors based on config
        Vivid4jLoggingEvent wrapped = new Vivid4jLoggingEvent(eventObject, theme, colorEnabled);
        attachable.appendLoopOnAppenders(wrapped);
    }

    // AppenderAttachable methods (enable <appender-ref/>)
    @Override
    public void addAppender(Appender<ILoggingEvent> newAppender) {
        attachable.addAppender(newAppender);
    }
    @Override
    public Appender<ILoggingEvent> getAppender(String name) {
        return attachable.getAppender(name);
    }
    @Override
    public boolean isAttached(Appender<ILoggingEvent> appender) {
        return attachable.isAttached(appender);
    }
    @Override
    public void detachAndStopAllAppenders() {
        attachable.detachAndStopAllAppenders();
    }
    @Override
    public boolean detachAppender(Appender<ILoggingEvent> appender) {
        return attachable.detachAppender(appender);
    }
    @Override
    public boolean detachAppender(String name) {
        return attachable.detachAppender(name);
    }
    @Override
    public Iterator<Appender<ILoggingEvent>> iteratorForAppenders() {
        return attachable.iteratorForAppenders();
    }
}