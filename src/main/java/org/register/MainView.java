package org.register;

import api.Usuario;
import api.UsuarioAPI;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@PageTitle("Registrar")
@Route
public class MainView extends Div {

    public MainView() {
        TextField nombre = new TextField("Nombre");
        TextField apellidos = new TextField("Apellidos");
        TextField username = new TextField("Usuario");
        PasswordField pass = new PasswordField("Contraseña");

        Button aceptar = new Button("Aceptar");
        Button login = new Button("Login");
        login.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_SUCCESS);

        VerticalLayout vl = new VerticalLayout();
        HorizontalLayout hltitle = new HorizontalLayout();
        HorizontalLayout hl1 = new HorizontalLayout();
        HorizontalLayout hl2 = new HorizontalLayout();
        HorizontalLayout hl3 = new HorizontalLayout();

        hltitle.add(new H1("Registrarse"));
        hl1.add(nombre, apellidos);
        hl2.add(username, pass);
        hl3.add(aceptar,login);
        vl.add(hltitle,hl1,hl2,hl3);
        add(vl);

        aceptar.addClickListener(e->{
            Usuario user = new Usuario(nombre.getValue(), apellidos.getValue(), username.getValue(), pass.getValue());
            UsuarioAPI uapi = new UsuarioAPI();
            uapi.aniadirUsuario(user);
            Notification notification = Notification.show("Usuario registrado");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            UI.getCurrent().getPage().reload();
        });
        login.addClickListener(e->{
            UI.getCurrent().getPage().setLocation("https://proyectos2-pastillero.herokuapp.com/login");

        });

    }
}
