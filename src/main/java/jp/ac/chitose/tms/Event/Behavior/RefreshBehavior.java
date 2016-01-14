package jp.ac.chitose.tms.Event.Behavior;


import jp.ac.chitose.tms.Event.EventHandler;
import jp.ac.chitose.tms.Event.RefreshPayload;
import lombok.NonNull;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;

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
