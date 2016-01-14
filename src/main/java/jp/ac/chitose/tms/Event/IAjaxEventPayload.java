package jp.ac.chitose.tms.Event;

import org.apache.wicket.Component;

/**
 * Wicket の Evnet システムの payload のインターフェース.
 *
 * @author Hiroto Yamakawa
 * @see https://github.com/tyano/wicket15-snippet
 * @see http://apache-wicket.1842946.n4.nabble.com/Wicket-Events-td4650065.html
 * @param <T>
 *          type of event source.
 */
public interface IAjaxEventPayload<T> {

	public T getEventSource();

	public void addComponent(Component... components);

}
