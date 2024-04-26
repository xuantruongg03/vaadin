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

        /**
         * This method is called before navigating to the view.
         * It checks if the user is logged in and forwards to the login page if not.
         *
         * @param event the BeforeEnterEvent triggered when navigating to the view
         */
        @Override
        public void beforeEnter(BeforeEnterEvent event) {
                if (!isUserLoggedIn()) {
                        event.forwardTo("login");
                }
        }

        /**
         * Checks if the user is logged in.
         *
         * @return true if the user is logged in, false otherwise.
         */
        private boolean isUserLoggedIn() {
                VaadinSession session = VaadinSession.getCurrent();
                if (session.getAttribute("isLogin") != null) {
                        return (boolean) session.getAttribute("isLogin");
                }
                return false;
        }

        private H1 title;
        private Div body;

        /**
         * Constructs a new CustomLayout.
         * 
         * This class represents a custom layout for a Vaadin application. It includes a header section with an avatar and user information, a title, a side navigation menu, and a body section.
         * 
         * The header section includes a `Div` element with a `Span` for the username and a `Span` for the name. It also includes an `Image` element for the avatar.
         * 
         * The title section is represented by an `H1` element with customizable styling.
         * 
         * The side navigation menu is obtained from the `getSideNav()` method.
         * 
         * The body section is represented by a `Div` element with customizable styling.
         * 
         * @see Div
         * @see Span
         * @see Image
         * @see H1
         * @see SideNav
         * @see Scroller
         */
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

        /**
         * Sets the title of the custom layout.
         *
         * @param title the new title to be set
         */
        public void setTitle(String title) {
                this.title.setText(title);
        }

        /**
         * Adds the specified container to the body of this custom layout.
         *
         * @param container the container to be added to the body
         */
        public void addToBody(Div container) {
                body.add(container);
        }

        /**
         * Adds the specified components to the body of the custom layout.
         *
         * @param components the components to be added to the body
         */
        public void addToBody(Component... components) {
                body.add(components);
        }

        /**
         * Represents a side navigation menu.
         */
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
