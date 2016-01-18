package jp.ac.chitose.tms;

import jp.ac.chitose.tms.Bean.Sign;
import lombok.ToString;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;


@ToString
public class WicketSession extends WebSession {
	private static final long serialVersionUID = 2292251890809020570L;


	private Sign sign;

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



}
