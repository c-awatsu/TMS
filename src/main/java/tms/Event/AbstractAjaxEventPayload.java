package tms.Event;

import lombok.Getter;

import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * {@link IAjaxEventPayload} の abstract実装クラス.
 *
 * @author CIST yamakawa
 * @see https://github.com/tyano/wicket15-snippet
 * @see http://apache-wicket.1842946.n4.nabble.com/Wicket-Events-td4650065.html
 *
 * @param <T>
 *          type of event source.
 */
public abstract class AbstractAjaxEventPayload<T> implements IAjaxEventPayload<T> {

	@Getter
	private T source;
	@Getter
	private AjaxRequestTarget target;

	/**
	 * Construct.
	 *
	 * @param source
	 *          the event source.
	 */
	public AbstractAjaxEventPayload(T source, AjaxRequestTarget target) {
		this.source = source;
		this.target = target;
	}

	/**
	 * @return the source
	 */
	@Override
	public T getEventSource() {
		return source;
	}

}
