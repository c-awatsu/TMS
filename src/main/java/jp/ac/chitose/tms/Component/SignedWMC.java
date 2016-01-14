package jp.ac.chitose.tms.Component;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

public class SignedWMC extends WebMarkupContainer{

	public SignedWMC(String id){
		super(id,null);
	}

	public SignedWMC(String id,IModel<?> model){
		super(id,model);
	}
}
