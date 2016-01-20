package jp.ac.chitose.tms;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import jp.ac.chitose.tms.Bean.Sign;
import lombok.ToString;


@ToString
public class WicketSession extends AbstractAuthenticatedWebSession {
	private static final long serialVersionUID = 2292251890809020570L;


	private Sign sign;

	public static WicketSession get() {
		return (WicketSession) Session.get();
	}

	public WicketSession(Request request) {
		super(request);
		sign = null;
	}

	@Override
	public final void invalidate() {
		replaceSession();
		sign = null;
		super.invalidate();
	}
	@Override
	public boolean isSignedIn(){
		return sign != null;
	}

	public final void signIn(Sign sign) {
		this.sign = sign;
		if(this.sign != null){
			replaceSession();
			dirty();
		}
	}
	public final Sign getSign(){
		return sign;
	}
	public final int getAccountId() {
		return sign.getAccountId();
	}

	public final String getLoginId() {
		return sign.getLoginId();
	}

	public final String getNickname(){
		return sign.getNickname();
	}

	@Override
	public Roles getRoles() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}



}
