package pjpo.github.com.consplan;

import javax.servlet.annotation.WebServlet;

import pjpo.github.com.consplan.samples.MainScreen;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 *
 */
@SuppressWarnings("serial")
@Theme("consplantheme")
@Widgetset("pjpo.github.com.consplan.ConsPlanWidgetset")
@Viewport("user-scalable=no,initial-scale=1.0")
public class ConsPlan extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	// Responsiveness setting
    	Responsive.makeResponsive(this);
    	// Sets parameters of Locale
        setLocale(vaadinRequest.getLocale());

        // Set content
        setContent(new MainScreen(this));
    }

    @WebServlet(urlPatterns = "/*", name = "ConsPlanServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ConsPlan.class, productionMode = false)
    public static class ConsPlanServlet extends VaadinServlet {
    }
}
