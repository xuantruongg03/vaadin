package com.example.application.views.pages.Blogs;

import java.util.List;

import com.example.application.Model.Blog;
import com.example.application.Model.BlogsManager;
import com.example.application.views.CustomLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Bài viết")
@Route(value = "")
public class BlogsView extends CustomLayout {

    /**
     * Represents a view for displaying a list of blogs.
     * This view includes a grid for displaying blog information,
     * a search field for filtering blogs, and a button for adding new blogs.
     */
    public BlogsView() {
        setTitle("Bài viết");

        Grid<Blog> grid = new Grid<>(Blog.class, false);
        grid.setHeight("70vh");
        grid.addColumn(createImageBlogRenderer()).setHeader("Hình ảnh").setFlexGrow(0)
                .setWidth("90px");
        grid.addColumn(Blog::getTitle).setHeader("Tiêu đề").setWidth("550px");
        // grid.addColumn(Blog::getDes).setHeader("Mô tả");
        grid.addColumn(Blog::getAuthor).setHeader("Tác giả");
        grid.addColumn(Blog::getUpdatedAt).setHeader("Ngày cập nhật");
        grid.addColumn(blog -> blog.getStatus() == 1 ? "Đã duyệt" : "Chưa duyệt").setHeader("Trạng thái");
        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, blog) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> this.removeInvitation(blog.getBlogId()));
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })).setHeader("Xóa bài viết").setWidth("50px");

        List<Blog> people = BlogsManager.getBlogs(); // get data from database
        GridListDataView<Blog> dataView = grid.setItems(people); // set data to grid

        TextField searchField = new TextField();
        searchField.setWidth("50%");
        searchField.getStyle().set("margin-bottom", "20px");
        searchField.setPlaceholder("Tìm kiếm");
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.addValueChangeListener(e -> dataView.refreshAll()); // refresh grid when search value changes

        //Filter grid data based on search field value
        dataView.addFilter(blog -> {
            String searchTerm = searchField.getValue().trim();

            if (searchTerm.isEmpty())
                return true;

            boolean matchesTitle = matchesTerm(blog.getTitle(),
                    searchTerm);
            boolean matchesDes = matchesTerm(blog.getDes(),
                    searchTerm);
            boolean matchesContent = matchesTerm(blog.getContent(),
                    searchTerm);

            return matchesTitle || matchesDes || matchesContent;
        });

        Button add = new Button("Thêm bài viết", new Icon(VaadinIcon.PLUS));
        add.getStyle().set("margin-right", "20px");
        add.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate("blog/edit"));
        });

        Div subl = new Div();
        subl.getStyle().set("display", "flex").set("justify-content", "space-between");
        subl.add(searchField, add);

        Div layout = new Div(subl, grid);
        // layout.setPadding(false);
        // layout.getStyle().set("display", "flex")

        addToBody(layout);
    }


    /**
     * Creates a renderer for displaying image blogs.
     *
     * @return The renderer for image blogs.
     */
    private static Renderer<Blog> createImageBlogRenderer() {
        return LitRenderer.<Blog>of(
                "<vaadin-horizontal-layout style='align-items: center; height: 50px; width: 70px;' theme='spacing'>"
                        + "  <img src=\"${item.pictureUrl}\" style='width: 100%;' />\r\n"
                        + "</vaadin-horizontal-layout>")
                .withProperty("pictureUrl", Blog::getImage);
    }

    /**
     * Checks if the given value matches the search term, ignoring case sensitivity.
     *
     * @param value     the value to be checked
     * @param searchTerm the search term to match against
     * @return true if the value contains the search term (ignoring case), false otherwise
     */
    private boolean matchesTerm(String value, String searchTerm) {
        return value.toLowerCase().contains(searchTerm.toLowerCase());
    }

    /**
     * Removes an invitation for a blog.
     *
     * @param b the blog to remove the invitation for
     */
    private void removeInvitation(String b) {
        if(b != null) {
            BlogsManager.removeBlog(b);
            UI.getCurrent().getPage().reload();
        }
    }
}
