package tms.Feedback;


import org.apache.wicket.Component;

import tms.Event.Behavior.RefreshBehavior;

/**
 * Bootstrapのalertスタイルを適用したFeedbackPanel. Ajax/Eventでの更新対応.
 *
 * @author Hiroto Yamakawa;
 *
 */
public class TargetedErrorAlertPanel extends ErrorAlertPanel {
	private static final long serialVersionUID = 4901444212509112778L;

	public TargetedErrorAlertPanel(String id, Component fence) {
		super(id, fence);
	}

	public TargetedErrorAlertPanel(String id) {
		this(id, null);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		setOutputMarkupId(true);
		add(new RefreshBehavior());
	}

}
