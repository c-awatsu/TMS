package jp.ac.chitose.tms.ui.Sign;

import java.util.stream.Collectors;

import jp.ac.chitose.tms.Bean.SignUp;
import jp.ac.chitose.tms.Feedback.TargetedErrorAlertPanel;
import jp.ac.chitose.tms.Service.ISignService;
import lombok.NonNull;
import lombok.val;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/SignUpConsentPage")
public class SignUpConsentPage extends WebPage{
	//TODO アカウント作成確認ページの作成 基本他の見ながら出来るはず コンストラクタの引数にSignUp
	@SpringBean
	private ISignService signService;

	public SignUpConsentPage(@NonNull SignUp signUp){
		setDefaultModel(new CompoundPropertyModel<SignUp>(signUp));
		val feedback = new TargetedErrorAlertPanel("feedback");
		add(feedback);
		add(new Label("loginId"));

		val shadowPassphraseModel = new AbstractReadOnlyModel<String>(){
			private static final long serialVersionUID = 1L;
			@Override
			public String getObject() {
				return signUp.getPassphrase().chars()
						.mapToObj(varargs -> "*")
						.collect(Collectors.joining());
			}
		};

		add(new Label("passphrase",shadowPassphraseModel));
		add(new Label("nickName"));
		add(new AjaxLink<SignUp>("toSignInPage",new Model<SignUp>(signUp)) {

			@Override
			public void onClick(AjaxRequestTarget target) {
				if(signService.createAccount(getModelObject())){
					setResponsePage(SignInPage.class);
				}
				if(!hasErrorMessage()){
					error("アカウント作成に失敗しました。ログインIDが他のユーザと重複している可能性があります。変更してください");
					target.add(feedback);
				}
			}
		});

		add(new Link<SignUp>("toSignUpPage"){
			@Override
			public void onClick() {
				//TODO SignUpを引数に持ったSignUpPageコンストラクタを呼べるようにする
				setResponsePage(SignUpPage.class);
			}

		});
	}
}
