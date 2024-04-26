package com.example.application.views.pages.Login;

import com.example.application.views.CustomLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

/**
 * This class represents the logout functionality in the application.
 * It extends the CustomLayout class and is annotated with @Route to define the route for accessing this page.
 */
@Route(value = "logout")
public class Logout extends CustomLayout {
    
    /**
     * Constructs a new Logout object.
     * This constructor is responsible for logging out the user by invalidating the session, closing the session, and navigating to the login page.
     */
    public Logout() {
        VaadinSession session = VaadinSession.getCurrent();
        if(session.getAttribute("isLogin") != null) {
            VaadinSession.getCurrent().getSession().invalidate();
            VaadinSession.getCurrent().close();
            UI.getCurrent().navigate("/login");
        } 
    }
}
