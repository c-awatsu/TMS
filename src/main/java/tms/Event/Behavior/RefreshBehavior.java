package tms.Event.Behavior;


import lombok.NonNull;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;

import tms.Event.EventHandler;
import tms.Event.RefreshPayload;

/**
 * イベントシステムで RefreshPayload が send された時にコンポーネントを更新する Behavior.
 *
 * @author Hiroto Yamakawa
 */
public class RefreshBehavior extends Behavior {
	private static final long serialVersionUID = 1L;

	private Component component;

	@Override
	public final void bind(@NonNull Component component) {
		this.component = component;
	}

	@EventHandler
	public void refresh(RefreshPayload<?> payload) {
		payload.addComponent(this.component);
	}

}
