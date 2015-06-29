package pjpo.github.com.consplan.samples;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;

@SuppressWarnings("serial")
public class EmployeesSummaryView extends Panel implements View {

	/**
	 * String definition for this view
	 */
    public static final String NAME = EmployeesSummaryView.class.getName();

    public EmployeesSummaryView() {

        Link lnk = new Link("Count", new ExternalResource("#!"
                + CountView.NAME));
        setContent(lnk);

    }
    
    @Override
	public void enter(ViewChangeEvent event) {

	}

}
