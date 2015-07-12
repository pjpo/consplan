package pjpo.github.com.consplan.samples;

import java.util.Collection;
import java.util.ResourceBundle;

import pjpo.github.com.consplan.dao.EmployeesDao;
import pjpo.github.com.consplan.model.Employee;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid.SelectionModel;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@DesignRoot
public class EmployeesSummaryView2 extends CssLayout implements View {

	/**
	 * String definition for this view
	 */
    public static final String NAME = "EmployeesSummary";
    
	/**
	 *  Source of localized text
	 */
    private final ResourceBundle resourceText = ResourceBundle.getBundle("pjpo.github.com.consplan.samples.EmployeesGrid", UI.getCurrent().getLocale());
	
    // Elements from the ui
    
    private EmployeesSummaryGrid grid;
    
    private Button create;
    
    private Button delete;
    
    public EmployeesSummaryView2() {
    	// Builds the UI
		Design.read("EmployeesSummaryView.html", this);

		create.addClickListener((event) -> {
			
		});
		
    }

    @Override
    public void enter(final ViewChangeEvent event) {
    	// in event, number of element selected
    }

}
