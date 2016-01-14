package jp.ac.chitose.tms.ui.Sign;

import static jp.ac.chitose.tms.Constant.Validation.*;
import jp.ac.chitose.tms.Bean.SignUp;
import jp.ac.chitose.tms.Feedback.ErrorAlertPanel;
import lombok.val;

import org.apache.wicket.markup.html.HTML5Attributes;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;

//@SpringBootApplication
@MountPath("/SignUpPage")
public class SignUpPage extends WebPage{
	private static final long serialVersionUID = 1765245004856512150L;

    public SignUpPage(){

    	add(new ErrorAlertPanel("feedback"));

    	val form = new Form<SignUp>("form",new CompoundPropertyModel<SignUp>(new SignUp())){
    		@Override
    		protected void onSubmit() {
    			super.onSubmit();
    			setResponsePage(new SignUpConsentPage(getModelObject()));
    		}
    	};
    	this.add(form);

    	form.add(new RequiredTextField<String>("loginId"){
    		private static final long serialVersionUID = -1003120280881475006L;
    		private static final String LABEL_NAME = "ログインID";

    		@Override
    		protected void onInitialize() {
    			super.onInitialize();
    			setLabel(Model.of(LABEL_NAME));
    			add(new HTML5Attributes());
    			add(StringValidator.lengthBetween(SIGNING_MIN_LENGTH, SIGNING_MAX_LENGTH));
    		};

    	});

    	form.add(new PasswordTextField("passphrase"){
    		private static final long serialVersionUID = 4913292013216830094L;
    		private static final String LABEL_NAME = "パスワード";

    		@Override
    		protected void onInitialize() {
    			super.onInitialize();
    			setLabel(Model.of(LABEL_NAME));
    			add(new HTML5Attributes());
    			add(StringValidator.lengthBetween(SIGNING_MIN_LENGTH, SIGNING_MAX_LENGTH));
    		};
    	});

    	form.add(new RequiredTextField<String>("nickName"){
    		private static final long serialVersionUID = 4913292016830094L;
    		private static final String LABEL_NAME = "ユーザーネーム";
    		@Override
    		protected void onInitialize() {
    			super.onInitialize();
    			setLabel(Model.of(LABEL_NAME));
    			add(new HTML5Attributes());
    			add(StringValidator.lengthBetween(SIGNING_MIN_LENGTH, SIGNING_MAX_LENGTH));
    		}
    	});
    }
}
