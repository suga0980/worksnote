package jp.co.chitose.page.signed.admin;

import jp.co.chitose.classes.Account;
import jp.co.chitose.enums.Role;
import jp.co.chitose.service.IAccountService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.Arrays;
import java.util.List;

@AuthorizeInstantiation(Roles.ADMIN)
@MountPath("EditAccount")
public class EditAccountPage extends WebPage {

    @SpringBean
    IAccountService accountService;

    public EditAccountPage(Account account) {
        IModel<String> nameModel = Model.of(account.getName());
        IModel<String> mailAddressModel = Model.of(account.getMailAddress());
        IModel<String> passwordModel = Model.of(account.getPassword());
        IModel<String> roleModel = Model.of(account.getRole().toString());
        IModel<String> realNameModel = Model.of(account.getRealName());
        IModel<String> realNameRubyModel = Model.of(account.getRealNameRuby());

        Form<Void> makeAccountForm = new Form<>("signUpForm") {
            @Override
            protected void onSubmit() {
                super.onSubmit();
                String name = nameModel.getObject();
                String mailAddress = mailAddressModel.getObject();
                String password = passwordModel.getObject();
                Role role;
                if (roleModel.getObject().equals(Role.ADMIN.getString())) {
                    role = Role.ADMIN;
                } else if (roleModel.getObject().equals(Role.STUFF.getString())) {
                    role = Role.STUFF;
                } else if (roleModel.getObject().equals(Role.TEACHER.getString())) {
                    role = Role.TEACHER;
                } else {
                    role = null;
                }
                String realName = realNameModel.getObject();
                String realNameRuby = realNameRubyModel.getObject();
                Account account = new Account(name, mailAddress, password, role, realName, realNameRuby);
                // TODO 01 account_idのアカウントを更新する

                setResponsePage(new MakeAccountCompPage(account));
            }
        };
        add(makeAccountForm);

        TextField<String> userNameField = new TextField<>("accountName", nameModel);
        makeAccountForm.add(userNameField);

        EmailTextField emailTextField = new EmailTextField("mailAddress", mailAddressModel);
        makeAccountForm.add(emailTextField);

        PasswordTextField passwordTextField = new PasswordTextField("password", passwordModel);
        makeAccountForm.add(passwordTextField);

        List<String> roles = Arrays.asList("教員", "事務員", "管理者");
        DropDownChoice<String> roleChoice = new DropDownChoice<>("role", roleModel, roles);
        makeAccountForm.add(roleChoice);

        TextField<String> realNameField = new TextField<>("realName", realNameModel);
        makeAccountForm.add(realNameField);

        TextField<String> realNameRubyField = new TextField<>("realNameRuby", realNameRubyModel);
        makeAccountForm.add(realNameRubyField);

        Link<Void> viewAccountsPageLink = new Link<Void>("toViewAccounts") {
            @Override
            public void onClick() {
                setResponsePage(new ViewAccountsPage());
            }
        };
        add(viewAccountsPageLink);

        Link<Void> deleteAccountPageLink = new Link<>("toDeleteAccount") {
            @Override
            public void onClick() {
                setResponsePage(new DeleteAccountPage(account));
            }
        };
        add(deleteAccountPageLink);
    }
}
