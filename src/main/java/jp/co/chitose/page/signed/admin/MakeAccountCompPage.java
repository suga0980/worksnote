package jp.co.chitose.page.signed.admin;

import jp.co.chitose.classes.Account;
import jp.co.chitose.enums.Role;
import jp.co.chitose.page.panel.Header;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.wicketstuff.annotation.mount.MountPath;

@AuthorizeInstantiation(Roles.ADMIN)
@MountPath("MakeAccountComp")
public class MakeAccountCompPage extends WebPage {

    public MakeAccountCompPage(Account account) {

        Header header = new Header("headerPanel");
        add(header);

        String accountName = account.getName();
        String mailAddress = account.getMailAddress();
        Role role = account.getRole();
        String realName = account.getRealName();
        String realNameRuby = account.getRealNameRuby();

        Label accountNameLabel = new Label("name", accountName);
        add(accountNameLabel);

        Label mailAddressLabel = new Label("mailAddress", mailAddress);
        add(mailAddressLabel);

        Label accountRoleLabel = new Label("role", role.getString());
        add(accountRoleLabel);

        Label realNameLabel = new Label("realName", realName);
        add(realNameLabel);

        Label realNameRubyLabel = new Label("realNameRuby", realNameRuby);
        add(realNameRubyLabel);

        Link<Void> toMakeAccountPageLink = new Link<>("toMakeAccount") {
            @Override
            public void onClick() {
                setResponsePage(new MakeAccountPage());
            }
        };
        add(toMakeAccountPageLink);

        Link<Void> toViewAccountsPageLink = new Link<>("toViewAccounts") {
            @Override
            public void onClick() {

            }
        };
        add(toViewAccountsPageLink);

        Link<Void> toAdminPageLink = new Link<>("toAdmin") {
            @Override
            public void onClick() {
                setResponsePage(new AdminPage());
            }
        };
        add(toAdminPageLink);
    }
}
