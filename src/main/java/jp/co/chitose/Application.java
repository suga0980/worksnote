package jp.co.chitose;

import com.giffing.wicket.spring.boot.starter.app.WicketBootSecuredWebApplication;
import jp.co.chitose.page.signed.MySession;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application extends WicketBootSecuredWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return MySession.class;
    }
}
