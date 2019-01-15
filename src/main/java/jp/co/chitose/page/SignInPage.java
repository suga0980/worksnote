package jp.co.chitose.page;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;
import jp.co.chitose.enums.Role;
import jp.co.chitose.page.signed.MySession;
import jp.co.chitose.page.signed.admin.AdminPage;
import jp.co.chitose.page.signed.stuff.StuffPage;
import jp.co.chitose.page.signed.teacher.TeacherPage;
import jp.co.chitose.service.IAccountService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.time.LocalDate;

@WicketHomePage
@MountPath("SignIn")
public class SignInPage extends WebPage {

    @SpringBean
    private IAccountService accountService;

    public SignInPage() {

        IModel<String> accountNameModel = Model.of("");
        IModel<String> passwordModel = Model.of("");

        Form<Void> signInForm = new Form<>("signInForm") {
            @Override
            protected void onSubmit() {
                String accountName = accountNameModel.getObject();
                String password = passwordModel.getObject();
                if (accountService.authAccount(accountName, password)) {
                    int accountId = accountService.getAccountId(accountName);
                    Role role = accountService.getAccountRole(accountId);
                    MySession.get().signIn(accountId, accountName, role);
                    if (role.equals(Role.ADMIN)) {
                        setResponsePage(new AdminPage());
                    } else if (role.equals(Role.STUFF)) {
                        setResponsePage(new StuffPage());
                    } else if (role.equals(Role.TEACHER)) {
                        setResponsePage(new TeacherPage(accountId, LocalDate.now()));
                    }
                } else {
                    setResponsePage(SignInPage.class);
                }
            }
        };
        add(signInForm);

        TextField<String> accountNameField = new TextField<>("accountName", accountNameModel);
        signInForm.add(accountNameField);

        PasswordTextField passwordTextField = new PasswordTextField("accountPassword", passwordModel);
        signInForm.add(passwordTextField);
    }
}
