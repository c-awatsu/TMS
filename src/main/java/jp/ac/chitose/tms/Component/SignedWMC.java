package jp.ac.chitose.tms.Component;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

public class SignedWMC extends WebMarkupContainer{
	private static final long serialVersionUID = 4458005388244115L;

	public SignedWMC(String id){
		super(id,null);
	}

	public SignedWMC(String id,IModel<?> model){
		super(id,model);
	}
}
