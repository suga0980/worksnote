package jp.co.chitose.page.signed.stuff;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.util.resource.AbstractResourceStreamWriter;
import org.apache.wicket.util.resource.IResourceStream;
import org.wicketstuff.annotation.mount.MountPath;

import java.io.IOException;
import java.io.OutputStream;

@MountPath("ViewAddUpResult")
public class ViewAddUpResultPage extends WebPage {

    public ViewAddUpResultPage() {

        IModel<String> messageModel = Model.of("sasas");
        TextField<String> message = new TextField<>("message", messageModel);

        Form<Void> form = new Form<>("form") {

            @Override
            public void onSubmit() {
                IResourceStream resourceStream = new AbstractResourceStreamWriter() {
                    @Override
                    public void write(OutputStream output) throws IOException {
                        try {
                            Workbook workbook = new HSSFWorkbook();
                            // TODO WorkBookに設定を書き込む

                            workbook.write(output);
                        } catch (InvalidFormatException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public String getContentType() {
                        // see http://www.microsoft.com/japan/technet/prodtechnol/office/ork/library/f88d06fb-c9a4-413c-a1d3-40c97e340c5a.mspx?mfr=true
                        return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                    }
                };

                getRequestCycle().scheduleRequestHandlerAfterCurrent(new ResourceStreamRequestHandler(resourceStream, "sample.xlsx"));
            }
        };
        add(form);
        form.add(message);
    }
}

