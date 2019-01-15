package jp.co.chitose.page.signed.teacher;

import jp.co.chitose.classes.Account;
import jp.co.chitose.classes.Calendar;
import jp.co.chitose.classes.Date;
import jp.co.chitose.page.panel.Header;
import jp.co.chitose.page.signed.MySession;
import jp.co.chitose.page.signed.stuff.SetAddUpTermPage;
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
@MountPath("TeacherHome")
public class TeacherPage extends WebPage {

    @SpringBean
    private IAccountService accountService;

    public TeacherPage(Integer accountId, LocalDate localDate) {

        Header header = new Header("headerPanel");
        add(header);

        Link<Void> prevMonthLink = new Link<>("prevMonthLink") {
            @Override
            public void onClick() {
                setResponsePage(new TeacherPage(accountId, localDate.minusMonths(1)));
            }
        };
        add(prevMonthLink);

        Label monthLabel = new Label("month", localDate.getMonthValue());
        add(monthLabel);

        Link<Void> nextMonthLink = new Link<>("nextMonthLink") {
            @Override
            public void onClick() {
                setResponsePage(new TeacherPage(accountId, localDate.plusMonths(1)));
            }
        };
        add(nextMonthLink);

        Calendar calendar = new Calendar(localDate);
        List<List<Date>> calendarList = calendar.getListFormatted();
        IModel<List<List<Date>>> calendarModel = Model.ofList(calendarList);
        ListView<List<Date>> calendarView = new ListView<>("calendar", calendarModel) {
            @Override
            protected void populateItem(ListItem<List<Date>> item) {
                List<Date> week = item.getModelObject();
                IModel<List<Date>> weekModel = Model.ofList(week);
                ListView<Date> weekView = new ListView<>("week", weekModel) {
                    @Override
                    protected void populateItem(ListItem<Date> item) {

                        Link<Void> viewDailyWorksPageLink = new Link<>("toViewDailyWorks") {
                            @Override
                            public void onClick() {
                                LocalDate localDate = item.getModelObject().getDate();
                                setResponsePage(new ViewDailyWorksPage(accountId, localDate));
                            }
                        };
                        item.add(viewDailyWorksPageLink);

                        Date date = item.getModelObject();
                        int day = date.getDate().getDayOfMonth();
                        Label dayLabel = new Label("dayLabel", day);
                        viewDailyWorksPageLink.add(dayLabel);

                        // <ul></ul>をWorkで埋める
                    }
                };
                item.add(weekView);
            }
        };
        add(calendarView);

        Link<Void> addUpWorkPageLink = new Link<>("toAddUpWork") {
            @Override
            public void onClick() {
                setResponsePage(new SetAddUpTermPage(accountId));
            }
        };
        add(addUpWorkPageLink);

        Account account = accountService.getAccount(accountId);
        Label realNameLabel = new Label("realName", account.getRealName());
        addUpWorkPageLink.add(realNameLabel);
    }
}
