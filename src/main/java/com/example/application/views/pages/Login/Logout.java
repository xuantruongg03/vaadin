package com.example.application.views.pages.Login;

import com.example.application.views.CustomLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value = "logout")
public class Logout extends CustomLayout {
    public Logout() {
        VaadinSession session = VaadinSession.getCurrent();
        if(session.getAttribute("isLogin") != null) {
            VaadinSession.getCurrent().getSession().invalidate();
            VaadinSession.getCurrent().close();
            UI.getCurrent().navigate("/login");
        } 
    }
}
