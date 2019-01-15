package jp.co.chitose.page.signed.teacher;

import jp.co.chitose.classes.Work;
import jp.co.chitose.page.panel.Header;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.wicketstuff.annotation.mount.MountPath;

import java.time.LocalDate;

@AuthorizeInstantiation(Roles.USER)
@MountPath("RegisterWorkComp")
public class RegisterWorkCompPage extends WebPage {

    public RegisterWorkCompPage(Work work) {

        Header header = new Header("headerPanel");
        add(header);

        String title = work.getTitle();
        LocalDate localDate = work.getLocalDate();
        String type = work.getWorkType();
        String moreType = work.getMoreWorkType();
        String memo = work.getMemo();

        Label titleLabel = new Label("title", title);
        add(titleLabel);

        Label dateLabel = new Label("date", localDate.toString());
        add(dateLabel);

        Label typeLabel = new Label("type", type);
        add(typeLabel);

        Label moreTypeLabel = new Label("moreType", moreType);
        add(moreTypeLabel);

        Label memoLabel = new Label("memo", memo);
        add(memoLabel);

        Link<Void> registerWorkPageLink = new Link<>("toWorkRegister") {
            @Override
            public void onClick() {
                setResponsePage(new RegisterWorkPage());
            }
        };
        add(registerWorkPageLink);

        Link<Void> viewDailyWorksPageLink = new Link<>("toViewDailyWorks") {
            @Override
            public void onClick() {
                setResponsePage(new ViewDailyWorksPage(work.getAccountId(), localDate));
            }
        };
        add(viewDailyWorksPageLink);

        Label workDate = new Label("workDate", localDate.toString());
        viewDailyWorksPageLink.add(workDate);

        Link<Void> teacherHomePageLink = new Link<>("toHome") {
            @Override
            public void onClick() {
                setResponsePage(new TeacherPage(work.getAccountId(), localDate));
            }
        };
        add(teacherHomePageLink);
    }
}
