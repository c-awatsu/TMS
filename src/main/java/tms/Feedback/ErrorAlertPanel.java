package tms.Feedback;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FencedFeedbackPanel;

/**
 * Bootstrapのalertスタイルを適用したFeedbackPanel
 * 
 * @author Hiroto Yamakawa;
 *
 */
public class ErrorAlertPanel extends FencedFeedbackPanel {
	private static final long serialVersionUID = 4901444212509112778L;

	public ErrorAlertPanel(String id, Component fence) {
		super(id, fence, new ErrorLevelFeedbackMessageFilter(FeedbackMessage.ERROR));
	}

	public ErrorAlertPanel(String id) {
		this(id, null);
	}

}
