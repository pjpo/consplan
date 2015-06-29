package pjpo.github.com.consplan.samples;

import java.util.ResourceBundle;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class EmployeesSummaryView  extends CssLayout implements View {

	/**
	 * String definition for this view
	 */
    public static final String NAME = "EmployeesSummary";
    
	/**
	 *  Source of localized text
	 */
    private final ResourceBundle resourceText = ResourceBundle.getBundle("pjpo.github.com.consplan.samples.EmployeesGrid", UI.getCurrent().getLocale());;
	
    public EmployeesSummaryView() {

    	setSizeFull();
    	addStyleName("employees-summary-view");
    	
    	// Sets the top bar (CRUD actions)
    	final HorizontalLayout topLayout = createTopBar();

    	// Sets the content of grids
    	final EmployeesGrid grid = new EmployeesGrid();

    	// full content
    	final VerticalLayout barAndGridLayout = new VerticalLayout();
    	barAndGridLayout.addComponent(topLayout);
    	barAndGridLayout.addComponent(grid);
    	barAndGridLayout.setMargin(true);
    	barAndGridLayout.setSpacing(true);
    	barAndGridLayout.setSizeFull();
    	barAndGridLayout.setExpandRatio(grid, 1);
    	barAndGridLayout.setStyleName("employees-summary-layout");

    	// Sets the full content as content
    	addComponent(barAndGridLayout);
    }

    public HorizontalLayout createTopBar() {
    	final Button newEmployee = new Button(resourceText.getString("NewEmployee"));
    	newEmployee.addStyleName(ValoTheme.BUTTON_PRIMARY);
    	newEmployee.setIcon(FontAwesome.PLUS_CIRCLE);

    	HorizontalLayout topLayout = new HorizontalLayout();
    	topLayout.setSpacing(true);
    	topLayout.setWidth("100%");
    	topLayout.addComponent(newEmployee);
    	topLayout.setExpandRatio(newEmployee, 1);
    	topLayout.setStyleName("top-bar");
    	return topLayout;
    }

    @Override
    public void enter(ViewChangeEvent event) {
    	// Do nothing
    }

}
