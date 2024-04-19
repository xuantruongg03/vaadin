package com.example.application.views.pages.Login;

import com.example.application.Model.ManagerAdmin;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;
// import com.vaadin.flow.server.VaadinServletResponse;
// import com.vaadin.flow.server.VaadinService;
// import jakarta.servlet.http.Cookie;

/**
 * This class represents the login page of the application.
 * It extends the HorizontalLayout class and is annotated with @PageTitle and @Route.
 * The login page is used for user authentication and navigation to the dashboard.
 */
@PageTitle("Đăng nhập")
@Route(value = "login")
public class Login extends HorizontalLayout {
    public Login() {
        // Create a container for the login overlay
        Div container = new Div();
        container.getStyle().set("display", "flex")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("width", "100%")
                .set("height", "100vh");

        // Create a login overlay component
        LoginOverlay loginOverlay = new LoginOverlay(); // Create a login overlay component
        Paragraph text = new Paragraph("Never tell your password to anyone"); // Create a paragraph element with text
        text.addClassName(LumoUtility.TextAlignment.CENTER); // Add a CSS class to center the text
        loginOverlay.getFooter().add(text); // Add the paragraph element to the footer of the login overlay
        add(loginOverlay); // Add the login overlay to the layout
        loginOverlay.setOpened(true);
        loginOverlay.setTitle("Welcome to the Dashboard");
        loginOverlay.setDescription(null);
        loginOverlay.getElement().setAttribute("no-autofocus", "");

        //Add event listener to the login overlay
        loginOverlay.addLoginListener(e -> {
            boolean rs  = ManagerAdmin.checkLogin(e.getUsername(), e.getPassword());
            if (rs) {
                VaadinSession.getCurrent().setAttribute("username", e.getUsername());
                VaadinSession.getCurrent().setAttribute("isLogin", true);
                UI.getCurrent().navigate("");
                //Set cookie
                // Cookie cookie = new Cookie("username", e.getUsername());
                // cookie.setMaxAge(60 * 60 * 24 * 30);
                // cookie.setPath("/");
                // VaadinServletResponse response = (VaadinServletResponse) VaadinService.getCurrentResponse();
                // response.addCookie(cookie);
            } else {
                loginOverlay.setError(true);
            }
        });
    }
}
