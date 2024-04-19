package com.example.application.views.pages.Blogs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.example.application.Model.Blog;
import com.example.application.Model.BlogsManager;
import com.example.application.views.CustomLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@Route(value = "blog/edit")
public class Edit extends CustomLayout {
    public Edit() {
        Div container = new Div();
        container.getStyle().set("display", "flex");
        container.getStyle().set("flex-direction", "column").setMarginRight("30px").setMarginBottom("50px");

        setTitle("Sản phẩm");

        // TextField
        TextField title = new TextField("Tiêu đề");

        // TextArea
        TextArea des = new TextArea("Mô tả");
        des.getStyle().set("height", "150px");

        Image image = new Image("https://via.placeholder.com/150", "Placeholder image");
        image.getStyle().set("width", "150px").set("height", "150px");

        Button uploadButton = new Button("Tải ảnh lên");
        uploadButton.addClickListener(e -> {
            uploadButton.getUI().ifPresent(ui -> {
                MemoryBuffer buffer = new MemoryBuffer();
                Upload upload = new Upload(buffer);
                upload.setAcceptedFileTypes("image/*");
                upload.setMaxFiles(1);
                upload.addSucceededListener(event -> {
                    String fileName = event.getFileName();
                    try {
                        FileOutputStream outputStream = new FileOutputStream(
                                new File("src/main/resources/static/images/" + fileName));
                        IOUtils.copy(buffer.getInputStream(), outputStream);
                        outputStream.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    File file = new File("src/main/resources/static/images/" + fileName);
                    if (file.exists()) {
                        image.setSrc(new StreamResource(fileName, () -> {
                            try {
                                return new FileInputStream(file);
                            } catch (FileNotFoundException ex) {
                                ex.printStackTrace();
                                return null;
                            }
                        }));
                    }
                });

                upload.getElement().executeJs("this.children[0].style.display = 'none';");
                upload.getElement().executeJs("this.addEventListener('submit', function(e) { e.preventDefault(); });");
                ui.add(upload);
                // upload.getElement()
                        // .executeJs("this.children[0].addEventListener('click', function(e) { e.stopPropagation(); });");

                // Kích hoạt sự kiện click giả mạo để mở hộp thoại tải lên
                upload.getElement().executeJs("this.children[0].click()");
                // upload.getElement().executeJs("this.addEventListener('submit', function(e) { e.preventDefault(); });");
                // upload.addFinishedListener(event -> {
                //     ui.remove(upload);
                // });
            });
        });

        Div l2 = new Div(image, uploadButton);
        l2.getStyle().set("align-items", "center")
                .set("justify-content", "center")
                .set("display", "flex")
                .set("flex-direction", "column");

        // TextArea
        TextArea content = new TextArea("Nội dung");
        content.getStyle().set("height", "350px").setWidth("100%");

        Div l1 = new Div();
        l1.getStyle().set("width", "30%");
        l1.add(l2);

        Div a = new Div();
        a.getStyle().set("display", "flex")
                .set("flex-direction", "column")
                .setWidth("70%")
                .set("margin-right", "30px");
        a.add(title, des);

        Div b = new Div();
        b.getStyle().set("display", "flex")
                .set("flex-direction", "row")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("width", "100%")
                .set("margin-right", "30px");
        b.add(a, l1);

        Div containerContent = new Div();
        containerContent.add(content);

        Button save = new Button("Lưu");
        save.getStyle().set("width", "100px").set("background-color", "skyblue").set("color", "white")
                .set("border", "none").set("border-radius", "5px").set("margin-top", "20px").set("cursor", "pointer");

        save.addClickListener(e -> {
            String titleValue = title.getValue();
            String desValue = des.getValue();
            String contentValue = content.getValue();
            String imageUrl = "https://via.placeholder.com/150";
            if (titleValue.isEmpty() || desValue.isEmpty() || contentValue.isEmpty()) {
                Notification notification = new Notification("Vui lòng điền đầy đủ thông tin", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.open();
            } else {
                Blog blog = new Blog(titleValue, contentValue, desValue, imageUrl);
                BlogsManager.addBlog(blog);
                Notification notification = new Notification("Lưu thành công", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                notification.open();
            }
        });

        container.add(b, containerContent, save);
        addToBody(container);
    }
}
