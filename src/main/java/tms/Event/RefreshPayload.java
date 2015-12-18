package tms.Event;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * Wicket の Event システムを用いてコンポーネントを更新する payload.
 *
 * @author CIST yamakawa
 * @see https://github.com/tyano/wicket15-snippet
 * @see http://apache-wicket.1842946.n4.nabble.com/Wicket-Events-td4650065.html
 *
 * @param <T>
 *          type of event source.
 */
public class RefreshPayload<T> extends AbstractAjaxEventPayload<T> {

	/**
	 * Construct.
	 *
	 * @param source
	 *          the event source.
	 * @param target
	 *          {@link AjaxRequestTarget}.
	 */
	public RefreshPayload(T source, AjaxRequestTarget target) {
		super(source, target);
	}

	@Override
	public void addComponent(Component... components) {
		getTarget().add(components);
	}

	/**
	 * Factory method.
	 * 
	 * @param source
	 *          the event source.
	 * @param target
	 *          {@link AjaxRequestTarget}.
	 * @return instance of {@link RefreshPayload}.
	 */
	public static <T> RefreshPayload<T> of(T source, AjaxRequestTarget target) {
		return new RefreshPayload<>(source, target);
	}

}
