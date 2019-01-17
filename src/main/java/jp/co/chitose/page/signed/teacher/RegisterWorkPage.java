package jp.co.chitose.page.signed.teacher;

import jp.co.chitose.classes.Work;
import jp.co.chitose.enums.Type;
import jp.co.chitose.page.panel.Header;
import jp.co.chitose.page.signed.MySession;
import jp.co.chitose.service.IWorkService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@AuthorizeInstantiation(Roles.USER)
@MountPath("RegisterWork")
public class RegisterWorkPage extends WebPage {

    @SpringBean
    private IWorkService workService;

    // 登録用
    public RegisterWorkPage() {

        Header header = new Header("headerPanel");
        add(header);

        IModel<String> titleModel = Model.of("");
        IModel<String> yearModel = Model.of();
        IModel<String> monthModel = Model.of();
        IModel<String> dayModel = Model.of();
        IModel<String> typeModel = Model.of("");
        IModel<String> moreTypeModel = Model.of("");
        IModel<File> fileModel = Model.of();
        IModel<String> memoModel = Model.of("");

        Form<Void> registerWorkForm = new Form<>("registerWorkForm") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                Integer accountId = MySession.get().getAccountId();
                String title = titleModel.getObject();
                int year = Integer.parseInt(yearModel.getObject());
                int month = Integer.parseInt(monthModel.getObject());
                int day = Integer.parseInt(dayModel.getObject());
                LocalDate date = LocalDate.of(year, month, day);
                String type = typeModel.getObject();
                String moreType = moreTypeModel.getObject();
                String memo = memoModel.getObject();
                Work work = new Work(accountId, title, date, type, moreType, memo);
                workService.registerWork(accountId, work);
                setResponsePage(new RegisterWorkCompPage(work));
            }
        };
        add(registerWorkForm);

        TextField<String> titleField = new TextField<>("title", titleModel);
        registerWorkForm.add(titleField);

        TextField<String> yearField = new TextField<>("year", yearModel);
        registerWorkForm.add(yearField);

        TextField<String> monthField = new TextField<>("month", monthModel);
        registerWorkForm.add(monthField);

        TextField<String> dayField = new TextField<>("day", dayModel);
        registerWorkForm.add(dayField);

        List<String> typeList = Type.getTexts();
        DropDownChoice typeChoice = new DropDownChoice("type", typeModel, typeList);
        registerWorkForm.add(typeChoice);

        List<String> moreTypeList = Type.FD.getList();
        DropDownChoice moreTypeChoice = new DropDownChoice("moreType", moreTypeModel, moreTypeList);
        registerWorkForm.add(moreTypeChoice);

        FileUploadField fileUploadField = new FileUploadField("file");
        registerWorkForm.add(fileUploadField);

        TextField<String> memoField = new TextField<>("memo", memoModel);
        registerWorkForm.add(memoField);
    }

    // 更新用
    public RegisterWorkPage(Work work) {

        Header header = new Header("headerPanel");
        add(header);

        IModel<String> titleModel = Model.of(work.getTitle());
        IModel<String> yearModel = Model.of(work.getLocalDate().getYear() + "");
        IModel<String> monthModel = Model.of(work.getLocalDate().getMonthValue() + "");
        IModel<String> dayModel = Model.of(work.getLocalDate().getDayOfMonth() + "");
        IModel<String> typeModel = Model.of(work.getWorkType());
        IModel<String> moreTypeModel = Model.of(work.getMoreWorkType());
        IModel<File> fileModel = Model.of();
        IModel<String> memoModel = Model.of(work.getMemo());

        Form<Void> registerWorkForm = new Form<>("registerWorkForm") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                Integer accountId = MySession.get().getAccountId();
                String title = titleModel.getObject();
                int year = Integer.parseInt(yearModel.getObject());
                int month = Integer.parseInt(monthModel.getObject());
                int day = Integer.parseInt(dayModel.getObject());
                LocalDate date = LocalDate.of(year, month, day);
                String type = typeModel.getObject();
                String moreType = moreTypeModel.getObject();
                String memo = memoModel.getObject();
                Work work = new Work(accountId, title, date, type, moreType, memo);
                // Workを更新する
                // 更新完了ページに移動する
                setResponsePage(new RegisterWorkCompPage(work));
            }
        };
        add(registerWorkForm);

        TextField<String> titleField = new TextField<>("title", titleModel);
        registerWorkForm.add(titleField);

        TextField<String> yearField = new TextField<>("year", yearModel);
        registerWorkForm.add(yearField);

        TextField<String> monthField = new TextField<>("month", monthModel);
        registerWorkForm.add(monthField);

        TextField<String> dayField = new TextField<>("day", dayModel);
        registerWorkForm.add(dayField);

        List<String> typeList = Type.getTexts();
        DropDownChoice typeChoice = new DropDownChoice("type", typeModel, typeList);
        registerWorkForm.add(typeChoice);

        List<String> moreTypeList = Type.FD.getList();
        DropDownChoice moreTypeChoice = new DropDownChoice("moreType", moreTypeModel, moreTypeList);
        registerWorkForm.add(moreTypeChoice);

        FileUploadField fileUploadField = new FileUploadField("file");
        registerWorkForm.add(fileUploadField);

        TextField<String> memoField = new TextField<>("memo", memoModel);
        registerWorkForm.add(memoField);
    }
}
