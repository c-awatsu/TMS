package jp.ac.chitose.tms.Event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Wicket の Event システムのハンドラであることを示すアノテーション.
 *
 *  */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
}
