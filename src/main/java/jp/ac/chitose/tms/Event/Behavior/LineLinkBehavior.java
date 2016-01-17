package jp.ac.chitose.tms.Event.Behavior;

import lombok.val;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

public class LineLinkBehavior extends Behavior {

	public LineLinkBehavior() {
	}

	@Override
	public void onComponentTag(Component component, ComponentTag tag) {
		val id = tag.getAttribute("id");
		tag.put("onmouseover", "onover('" + id + "')");
		tag.put("onmouseout", "onout('" + id + "')");
	}

	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		super.renderHead(component, response);
		val js = new JavaScriptResourceReference(LineLinkBehavior.class,
				"LineLinkBehavior.js");
		response.render(JavaScriptReferenceHeaderItem.forReference(js));
		val css = new CssResourceReference(LineLinkBehavior.class,
				"LineLinkBehavior.css");
		response.render(CssReferenceHeaderItem.forReference(css));
	}
}
