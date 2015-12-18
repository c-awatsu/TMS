package tms.Event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Wicket の Event システムのハンドラであることを示すアノテーション.
 *
 * @author Hiroto Yamakawa
 * @see https://github.com/tyano/wicket15-snippet
 * @see http://apache-wicket.1842946.n4.nabble.com/Wicket-Events-td4650065.html
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
}
