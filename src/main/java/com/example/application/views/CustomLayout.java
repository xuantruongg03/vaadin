package com.example.application.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class CustomLayout extends HorizontalLayout implements BeforeEnterObserver {

        @Override
        public void beforeEnter(BeforeEnterEvent event) {
                if (!isUserLoggedIn()) {
                        event.forwardTo("login");
                }
        }

        private boolean isUserLoggedIn() {
                VaadinSession session = VaadinSession.getCurrent();
                if (session.getAttribute("isLogin") != null) {
                        return (boolean) session.getAttribute("isLogin");
                }
                return false;
        }

        private H1 title;
        private Div body;

        public CustomLayout() {
                Div to = new Div();
                to.getStyle().set("display", "flex")
                                .set("align-items", "center")
                                .set("justify-content", "space-between")
                                .set("margin-bottom", "10px")
                                .set("border-bottom", "1px solid var(--lumo-contrast-10pct)")
                                .set("padding-bottom", "10px");
                Div drawer = new Div();
                drawer.getStyle().set("display", "flex")
                                .set("align-items", "flex-end")
                                .set("flex-direction", "column")
                                .set("justify-content", "center");
                Span username = new Span("Xin chào,");
                Span name = new Span(VaadinSession.getCurrent().getAttribute("username") + "");
                drawer.add(username, name);
                
                //Avatar
                Image avatar = new Image("https://via.placeholder.com/150", "Avatar");
                avatar.getStyle().set("border-radius", "50%")
                                .set("width", "50px")
                                .set("height", "50px");

                to.add(avatar, drawer);

                title = new H1("Title");
                title.getStyle().set("font-size", "40px")
                                .set("margin-top", "2rem");

                SideNav nav = getSideNav();

                Scroller scroller = new Scroller(nav);
                scroller.setClassName(LumoUtility.Padding.SMALL);

                Div container = new Div();
                container.setMinHeight("100vh");
                container.getStyle().setBackgroundColor("var(--lumo-contrast-5pct)")
                                .setPadding("1rem");
                container.add(to, scroller);

                body = new Div();
                body.getStyle().set("flex-grow", "1");
                body.add(title);
                add(container, body);
        }

        public void setTitle(String title) {
                this.title.setText(title);
        }

        public void addToBody(Div container) {
                body.add(container);
        }

        public void addToBody(Component... components) {
                body.add(components);
        }

        private SideNav getSideNav() {
                SideNav sideNav = new SideNav();
                sideNav.addItem(
                                new SideNavItem("Bài viết", "/blog/view", VaadinIcon.RECORDS.create()),
                                new SideNavItem("Cộng tác viên", "/collaborators/view",
                                                VaadinIcon.USER_HEART.create()),
                                new SideNavItem("Service", "/service", VaadinIcon.COG.create()),
                                new SideNavItem("Logout", "/logout", VaadinIcon.SIGN_OUT.create()));
                return sideNav;
        }
}
