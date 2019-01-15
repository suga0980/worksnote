package jp.co.chitose.page.signed.stuff;

import jp.co.chitose.classes.Work;
import jp.co.chitose.enums.Type;
import jp.co.chitose.page.panel.Header;
import jp.co.chitose.service.IAccountService;
import jp.co.chitose.service.IWorkService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SetAddUpTermPage extends WebPage {

    @SpringBean
    private IAccountService accountService;

    @SpringBean
    private IWorkService workService;

    public SetAddUpTermPage(Integer accountId) {

        Header header = new Header("headerPanel");
        add(header);

        IModel<String> bYearModel = Model.of("");
        IModel<String> bMonthModel = Model.of("");
        IModel<String> bDayModel = Model.of("");
        IModel<String> aYearModel = Model.of("");
        IModel<String> aMonthModel = Model.of("");
        IModel<String> aDayModel = Model.of("");

        Form<Void> termForm = new Form<>("termForm") {
            @Override
            protected void onSubmit() {
                int bYear = Integer.parseInt(bYearModel.getObject());
                int bMonth = Integer.parseInt(bMonthModel.getObject());
                int bDay = Integer.parseInt(bDayModel.getObject());
                int aYear = Integer.parseInt(aYearModel.getObject());
                int aMonth = Integer.parseInt(aMonthModel.getObject());
                int aDay = Integer.parseInt(aDayModel.getObject());
                LocalDate bDate = LocalDate.of(bYear, bMonth, bDay);
                LocalDate aDate = LocalDate.of(aYear, aMonth, aDay);
                List<Work> works = new ArrayList<>();
                while (bDate.toString().equals(aDate.toString())) {
                    List<Work> w = workService.getDailyWorks(accountId, bDate);
                    for (Work work : w) {
                        works.add(work);
                    }
                    bDate = bDate.plusDays(1);
                }
                int fdCount = 0;
                int classAdviserCount = 0;
                int studentSupportCount = 0;
                int jobSupportCount = 0;
                int getStudentCount = 0;
                int regionSupportCount = 0;
                for (Work work : works) {
                    if (work.getWorkType().equals(Type.FD.getText())) {
                        fdCount++;
                    } else if (work.getWorkType().equals(Type.CLASS_ADVICER.getText())) {
                        classAdviserCount++;
                    } else if (work.getWorkType().equals(Type.STUDENT_SUPPORT.getText())) {
                        studentSupportCount++;
                    } else if (work.getWorkType().equals(Type.JOB_SUPPORT.getText())) {
                        jobSupportCount++;
                    } else if (work.getWorkType().equals(Type.GET_STUDENT.getText())) {
                        getStudentCount++;
                    } else if (work.getWorkType().equals(Type.REGION_SUPPORT.getText())) {
                        regionSupportCount++;
                    }
                }
                System.out.println(Type.FD.getText() + " : " + fdCount);
                System.out.println(Type.CLASS_ADVICER.getText() + " : " + classAdviserCount);
                System.out.println(Type.STUDENT_SUPPORT.getText() + " : " + studentSupportCount);
                System.out.println(Type.JOB_SUPPORT.getText() + " : " + jobSupportCount);
                System.out.println(Type.GET_STUDENT.getText() + " : " + getStudentCount);
                System.out.println(Type.REGION_SUPPORT.getText() + " : " + regionSupportCount);
            }
        };
        add(termForm);

        TextField<String> bYearField = new TextField<>("bYear", bYearModel);
        termForm.add(bYearField);

        TextField<String> bMonthField = new TextField<>("bMonth", bMonthModel);
        termForm.add(bMonthField);

        TextField<String> bDayField = new TextField<>("bDay", bDayModel);
        termForm.add(bDayField);

        TextField<String> aYearField = new TextField<>("aYear", aYearModel);
        termForm.add(aYearField);

        TextField<String> aMonthField = new TextField<>("aMonth", aMonthModel);
        termForm.add(aMonthField);

        TextField<String> aDayField = new TextField<>("aDay", aDayModel);
        termForm.add(aDayField);
    }
}
