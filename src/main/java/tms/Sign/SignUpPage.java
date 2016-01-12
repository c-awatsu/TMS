package tms.Sign;

import static tms.Constant.Validation.*;
import lombok.val;

import org.apache.wicket.markup.html.HTML5Attributes;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.wicketstuff.annotation.mount.MountPath;

import tms.Bean.SignUp;
import tms.Feedback.ErrorAlertPanel;
import tms.Service.ISignService;

@SpringBootApplication
@MountPath("/SignUpPage")
public class SignUpPage extends WebPage{
	private static final long serialVersionUID = 1765245004856512150L;

	@Autowired
	private ISignService signService;

    public SignUpPage(){

    	add(new ErrorAlertPanel("feedback"));

    	val form = new Form<SignUp>("form",new CompoundPropertyModel<>(new SignUp())){
    		@Override
    		protected void onSubmit() {
    			super.onSubmit();
    			signService.createAccount(getModelObject());
    		}
    	};

    	this.add(form);

    	val loginId =  new RequiredTextField<String>("loginId"){
    		private static final long serialVersionUID = -1003120280881475006L;
    		private static final String LABEL_NAME = "ログインID";

    		@Override
    		protected void onInitialize() {
    			super.onInitialize();
    			setLabel(Model.of(LABEL_NAME));
    			add(new HTML5Attributes());
    			add(StringValidator.lengthBetween(SIGNING_MIN_LENGTH, SIGNING_MAX_LENGTH));
    		};

    	};

    	val passphrase =  new PasswordTextField("passphrase"){
    		private static final long serialVersionUID = 4913292013216830094L;
    		private static final String LABEL_NAME = "パスワード";

    		@Override
    		protected void onInitialize() {
    			super.onInitialize();
    			setLabel(Model.of(LABEL_NAME));
    			add(new HTML5Attributes());
    			add(StringValidator.lengthBetween(SIGNING_MIN_LENGTH, SIGNING_MAX_LENGTH));
    		};
    	};

    	val nickName = new RequiredTextField<String>("nickName"){
    		private static final long serialVersionUID = 4913292016830094L;
    		private static final String LABEL_NAME = "ユーザーネーム";
    		@Override
    		protected void onInitialize() {
    			super.onInitialize();
    			setLabel(Model.of(LABEL_NAME));
    			add(new HTML5Attributes());
    			add(StringValidator.lengthBetween(SIGNING_MIN_LENGTH, SIGNING_MAX_LENGTH));
    		}
    	};

    	form.add(loginId);
		form.add(passphrase);
		form.add(nickName);
    }
}
