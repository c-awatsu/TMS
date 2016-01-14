package jp.ac.chitose.tms;

import static java.util.Optional.*;

import java.util.Optional;

import jp.ac.chitose.tms.Bean.Sign;
import lombok.Getter;
import lombok.ToString;

import org.apache.wicket.Session;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;


@ToString
public class WicketSession extends AbstractAuthenticatedWebSession {
	private static final long serialVersionUID = 2292251890809020570L;

	@Getter
	private Optional<Sign> sign;

	public static WicketSession get() {
		return (WicketSession) Session.get();
	}

	public WicketSession(Request request) {
		super(request);
		sign = empty();
	}

	@Override
	public final void invalidate() {
		replaceSession();
		sign = empty();
		super.invalidate();
	}

	@Override
	public boolean isSignedIn() {
		return sign.isPresent();
	}

	public final void signIn(Optional<Sign> sign) {
		this.sign = sign;
		this.sign.ifPresent(s -> {
			replaceSession();
			dirty();
		});
	}

	public final int getAccountId() {
		return sign.map(s -> s.getAccountId()).orElseThrow(() -> new WicketRuntimeException("session is empty!"));
	}

	public final String getLoginId() {
		return sign.map(s -> s.getLoginId()).orElseThrow(() -> new WicketRuntimeException("session is empty!"));
	}

	@Override
	public Roles getRoles() {
		return null;
	}

}
