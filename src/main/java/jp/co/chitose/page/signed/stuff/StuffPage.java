package jp.co.chitose.page.signed.stuff;

import jp.co.chitose.classes.Account;
import jp.co.chitose.enums.Role;
import jp.co.chitose.page.panel.Header;
import jp.co.chitose.page.signed.teacher.TeacherPage;
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

import java.time.LocalDate;
import java.util.List;

@AuthorizeInstantiation(Roles.USER)
@MountPath("StuffHome")
public class StuffPage extends WebPage {

    @SpringBean
    private IAccountService accountService;

    public StuffPage() {

        Header header = new Header("headerPanel");
        add(header);

        List<Integer> teachersId = accountService.getIds(Role.TEACHER);
        IModel<List<Integer>> teachersModel = Model.ofList(teachersId);
        ListView<Integer> teachersView = new ListView<>("teachers", teachersModel) {
            @Override
            protected void populateItem(ListItem<Integer> item) {

                Integer accountId = item.getModelObject();

                Link<Void> calendarPageLink = new Link<>("toCalendar") {
                    @Override
                    public void onClick() {
                        setResponsePage(new TeacherPage(accountId, LocalDate.now()));
                    }
                };
                item.add(calendarPageLink);

                Account account = accountService.getAccount(accountId);
                String realName = account.getRealName();
                Label realNameLabel = new Label("realName", realName);
                calendarPageLink.add(realNameLabel);
            }
        };
        add(teachersView);
    }
}
