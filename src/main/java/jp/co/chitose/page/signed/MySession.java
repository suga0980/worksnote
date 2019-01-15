package jp.co.chitose.page.signed;

import jp.co.chitose.enums.Role;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import java.util.Objects;

public class MySession extends AbstractAuthenticatedWebSession {

    private Integer accountId;
    private String accountName;
    private Role role;

    public MySession(Request request) {
        super(request);
        this.accountId = null;
        this.accountName = null;
        this.role = null;
    }

    public static MySession get() {
        return (MySession) Session.get();
    }

    @Override
    public Roles getRoles() {
        if (isSignedIn()) {
            if (role.equals(Role.ADMIN)) {
                return new Roles(Roles.ADMIN);
            }
            return new Roles(Roles.USER);
        }
        return new Roles();
    }

    @Override
    public boolean isSignedIn() {
        return Objects.nonNull(this.accountId);
    }

    public void signIn(Integer accountId, String accountName, Role role) {
        replaceSession();
        this.accountId = accountId;
        this.accountName = accountName;
        this.role = role;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public Role getRole() {
        return role;
    }
}
