package jp.ac.chitose.tms;

import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.crypt.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import de.agilecoders.wicket.webjars.WicketWebjars;
import jp.ac.chitose.tms.Event.EventHandlerDispatcher;
import jp.ac.chitose.tms.ui.Sign.SignInPage;

@SpringBootApplication
public class WicketApplication extends AuthenticatedWebApplication {

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public Class<? extends WebPage> getHomePage() {
		return SignInPage.class;
	}

	public static void main(String[] args) {
		SpringApplication.run(WicketApplication.class, args);
	}

	@Override
	public void init() {
		super.init();
		// サーバ・クライアント間のリクエスト・レスポンス時の文字エンコード
		getRequestCycleSettings().setResponseRequestEncoding(CharEncoding.UTF_8);
		// Wicketに取り込まれるHTMLファイルのエンコード
		getMarkupSettings().setDefaultMarkupEncoding(CharEncoding.UTF_8);
		// SpringBeanの走査
		getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));
		// WicketStaff-Annotaion の走査
		new AnnotatedMountScanner().scanPackage("jp.ac.chitose.tms.ui").mount(this);
		// Twitter, JQuery用のWebJars
		WicketWebjars.install(this);
		// 自作のEventDispatcherを登録.
		getFrameworkSettings().add(new EventHandlerDispatcher());
	}

	/**
	 * クラス名をファイルパスとしてページをマウントする.
	 *
	 * @param page
	 *          Pageのサブクラス.
	 */
	protected void mountPage(Class<? extends Page> page) {
		mountPage("/" + page.getSimpleName(), page);
	}


	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return WicketSession.class;
	}


	protected Class<? extends WebPage> getSignInPageClass() {
		return SignInPage.class;
	}

	 @Override
	    public RuntimeConfigurationType getConfigurationType(){
	         return RuntimeConfigurationType.DEPLOYMENT;
	     }


}
