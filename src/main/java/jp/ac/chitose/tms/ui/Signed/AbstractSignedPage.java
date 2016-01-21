package jp.ac.chitose.tms.ui.Signed;

import jp.ac.chitose.tms.WicketSession;
import jp.ac.chitose.tms.ui.Sign.SignInPage;
import lombok.val;

import org.apache.wicket.markup.html.WebPage;

//ページへアクセス時に認証情報が無かった場合にSignInPageへ飛ばす
public abstract class AbstractSignedPage extends WebPage {

	public AbstractSignedPage(){
		val session = WicketSession.get();
		if(session.getSign() == null){
			setResponsePage(SignInPage.class);
		}
	}
}
