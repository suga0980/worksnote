package jp.co.chitose.page.panel;

import jp.co.chitose.classes.Account;
import jp.co.chitose.page.SignInPage;
import jp.co.chitose.page.signed.MySession;
import jp.co.chitose.service.IAccountService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class Header extends Panel {

    @SpringBean
    IAccountService accountService;

    private Account account;

    public Header(String id) {
        super(id);
        account = accountService.getAccount(MySession.get().getAccountId());

        String realName = account.getRealName();
        Label realNameLabel = new Label("realName", realName);
        add(realNameLabel);

        Link<Void> signOutLink = new Link<>("signOut") {
            @Override
            public void onClick() {
                MySession.get().invalidate();
                setResponsePage(SignInPage.class);
            }
        };
        add(signOutLink);
    }
}
