package tms.Sign;

import static tms.Constant.Validation.*;
import lombok.val;

import org.apache.wicket.markup.html.HTML5Attributes;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.wicketstuff.annotation.mount.MountPath;

import tms.Bean.SignIn;
import tms.Config.WicketSession;
import tms.Feedback.ErrorAlertPanel;
import tms.Service.ISignService;
import tms.Signed.TopPage;
@SpringBootApplication
@MountPath("/SignInPage")
public class SignInPage extends WebPage{
	private static final long serialVersionUID = 1765245004856243150L;

	public static final String SIGN_ERROR = "ログインIDかパスワードが間違っています";

	@Autowired
	private ISignService signService;

	public SignInPage(){
		add(new ErrorAlertPanel("feedback"));
		add(new Link<Void>("toSignUpPage"){
			@Override
			public void onClick() {
				setResponsePage(new SignUpPage());
			}
		});
		val form = new Form<SignIn>("form"){
			@Override
			protected void onSubmit() {
				super.onSubmit();
				val sign = signService.authenticate(getModelObject());
				WicketSession.get().signIn(sign);
				if(WicketSession.get().isSignedIn()){
					setResponsePage(TopPage.class);
				}
			}
		};
		add(form);

		form.add(new RequiredTextField<String>("loginId"){
			private static final long serialVersionUID = -1003120280882785006L;
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
			};
		});
	}
}
