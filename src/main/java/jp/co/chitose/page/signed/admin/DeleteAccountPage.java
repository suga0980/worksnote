package jp.co.chitose.page.signed.admin;

import jp.co.chitose.classes.Account;
import jp.co.chitose.service.IAccountService;
import jp.co.chitose.service.IWorkService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@AuthorizeInstantiation(Roles.ADMIN)
@MountPath("DeleteAccount")
public class DeleteAccountPage extends WebPage {

    @SpringBean
    IAccountService accountService;

    @SpringBean
    IWorkService workService;

    public DeleteAccountPage(Account account) {

        Label accountNameLabel = new Label("accountName", account.getName());
        add(accountNameLabel);

        Label roleLabel = new Label("role", account.getRole().getString());
        add(roleLabel);

        Label mailAddressLabel = new Label("mailAddress", account.getMailAddress());
        add(mailAddressLabel);

        Label realNameLabel = new Label("realName", account.getRealName());
        add(realNameLabel);

        Label realNameRubyLabel = new Label("realNameRuby", account.getRealNameRuby());
        add(realNameRubyLabel);

        int workCount = workService.getWorkCount(account.getId());
        Label workCountLabel = new Label("workCount", workCount);
        add(workCountLabel);

        Link<Void> viewAccountsPageLink = new Link<>("toViewAccounts") {
            @Override
            public void onClick() {
                setResponsePage(new ViewAccountsPage());
            }
        };
        add(viewAccountsPageLink);

        Link<Void> deleteAccountCompPageLink = new Link<>("toDeleteAccountComp") {
            @Override
            public void onClick() {

            }
        };
        add(deleteAccountCompPageLink);
    }
}
