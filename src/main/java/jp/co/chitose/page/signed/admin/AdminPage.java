package jp.co.chitose.page.signed.admin;

import jp.co.chitose.page.panel.Header;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.wicketstuff.annotation.mount.MountPath;

@AuthorizeInstantiation(Roles.ADMIN)
@MountPath("AdminHome")
public class AdminPage extends WebPage {

    public AdminPage() {

        Header header = new Header("headerPanel");
        add(header);

        Link<Void> accountMakePageLink = new Link<>("toAccountMake") {
            @Override
            public void onClick() {
                setResponsePage(new MakeAccountPage());
            }
        };
        add(accountMakePageLink);

        Link<Void> accountsViewPageLink = new Link<>("toAccountsView") {
            @Override
            public void onClick() {
                setResponsePage(new ViewAccountsPage());
            }
        };
        add(accountsViewPageLink);
    }
}
