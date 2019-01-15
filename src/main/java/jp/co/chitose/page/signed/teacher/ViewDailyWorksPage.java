package jp.co.chitose.page.signed.teacher;

import jp.co.chitose.classes.Work;
import jp.co.chitose.page.panel.Header;
import jp.co.chitose.service.IWorkService;
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
@MountPath("ViewDailyWorks")
public class ViewDailyWorksPage extends WebPage {

    @SpringBean
    private IWorkService workService;

    public ViewDailyWorksPage(Integer accountId, LocalDate localDate) {

        Header header = new Header("headerPanel");
        add(header);

        Link<Void> registerWorkPageLink = new Link<>("toRegisterWork") {
            @Override
            public void onClick() {
                setResponsePage(new RegisterWorkPage());
            }
        };
        add(registerWorkPageLink);

        Label dateLabel = new Label("date", localDate.toString());
        add(dateLabel);

        List<Work> works = workService.getDailyWorks(accountId, localDate);
        IModel<List<Work>> worksModel = Model.ofList(works);
        ListView<Work> worksView = new ListView<>("dailyWorks", worksModel) {
            @Override
            protected void populateItem(ListItem<Work> item) {
                Work work = item.getModelObject();

                Link<Void> workUpdateLink = new Link<Void>("toWorkUpdate") {
                    @Override
                    public void onClick() {
                        setResponsePage(new RegisterWorkPage(work));
                    }
                };
                item.add(workUpdateLink);

                String title = work.getTitle();
                Label titleLabel = new Label("title", title);
                workUpdateLink.add(titleLabel);

                String types = work.getWorkType() + " / " + work.getMoreWorkType();
                Label typesLabel = new Label("types", types);
                item.add(typesLabel);

                String memo = work.getMemo();
                Label memoLabel = new Label("memo", memo);
                item.add(memoLabel);
            }
        };
        add(worksView);

        Link<Void> calendarPageLink = new Link<>("toCalendar") {
            @Override
            public void onClick() {
                setResponsePage(new TeacherPage(accountId, localDate));
            }
        };
        add(calendarPageLink);
    }
}
