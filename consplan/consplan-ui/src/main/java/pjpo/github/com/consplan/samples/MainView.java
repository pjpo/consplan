package pjpo.github.com.consplan.samples;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;

@SuppressWarnings("serial")
public class MainView extends Panel implements View {

    public static final String NAME = "main";

    public MainView() {

        Link lnk = new Link("Count", new ExternalResource("#!"
                + CountView.NAME));
        setContent(lnk);

    }
    
    @Override
	public void enter(ViewChangeEvent event) {

	}

}
