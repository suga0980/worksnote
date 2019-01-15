package jp.co.chitose.page.signed.admin;

import jp.co.chitose.classes.Account;
import jp.co.chitose.enums.Role;
import jp.co.chitose.page.panel.Header;
import jp.co.chitose.service.IAccountService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.ArrayList;
import java.util.List;

@AuthorizeInstantiation(Roles.ADMIN)
@MountPath("ViewAccounts")
public class ViewAccountsPage extends WebPage {

    @SpringBean
    private IAccountService accountService;

    public ViewAccountsPage() {

        Header header = new Header("headerPanel");
        add(header);

        List<Integer> ids;

        ids = accountService.getIds(Role.ADMIN);
        List<Account> admins = new ArrayList<>(ids.size());
        for (Integer id : ids) {
            admins.add(accountService.getAccount(id));
        }
        IModel<List<Account>> adminModel = Model.ofList(admins);
        ListView<Account> adminView = new ListView<>("admin", adminModel) {
            @Override
            protected void populateItem(ListItem<Account> item) {
                Account account = item.getModelObject();
                Link<Void> editAccountPageLink = new Link<>("toEditAccount") {
                    @Override
                    public void onClick() {
                        setResponsePage(new EditAccountPage(account));
                    }
                };
                item.add(editAccountPageLink);
                Label label = new Label("realName", account.getRealName());
                editAccountPageLink.add(label);
            }
        };
        add(adminView);

        ids = accountService.getIds(Role.STUFF);
        List<Account> stuffs = new ArrayList<>(ids.size());
        for (Integer id : ids) {
            stuffs.add(accountService.getAccount(id));
        }
        IModel<List<Account>> stuffModel = Model.ofList(stuffs);
        ListView<Account> stuffView = new ListView<>("stuff", stuffModel) {
            @Override
            protected void populateItem(ListItem<Account> item) {
                Account account = item.getModelObject();
                Link<Void> editAccountPageLink = new Link<>("toEditAccount") {
                    @Override
                    public void onClick() {
                        setResponsePage(new EditAccountPage(account));
                    }
                };
                item.add(editAccountPageLink);
                Label label = new Label("realName", account.getRealName());
                editAccountPageLink.add(label);
            }
        };
        add(stuffView);

        ids = accountService.getIds(Role.TEACHER);
        List<Account> teachers = new ArrayList<>(ids.size());
        for (Integer id : ids) {
            teachers.add(accountService.getAccount(id));
        }
        IModel<List<Account>> teacherModel = Model.ofList(teachers);
        ListView<Account> teacherView = new ListView<>("teacher", teacherModel) {
            @Override
            protected void populateItem(ListItem<Account> item) {
                Account account = item.getModelObject();
                Link<Void> editAccountPageLink = new Link<>("toEditAccount") {
                    @Override
                    public void onClick() {
                        setResponsePage(new EditAccountPage(account));
                    }
                };
                item.add(editAccountPageLink);
                Label label = new Label("realName", account.getRealName());
                editAccountPageLink.add(label);
            }
        };
        add(teacherView);

        Link<Void> adminPageLink = new Link<>("toAdmin") {
            @Override
            public void onClick() {
                setResponsePage(new AdminPage());
            }
        };
        add(adminPageLink);
    }
}
