package pjpo.github.com.consplan.samples;

import java.util.Collection;
import java.util.ResourceBundle;

import pjpo.github.com.consplan.dao.EmployeesDao;
import pjpo.github.com.consplan.model.Employee;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid.SelectionModel;
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
	
    private final EmployeesSummaryGrid grid;
    
    private final EmployeesSummaryLogic logic;
    
    private final EmployeeForm form;
    
    public EmployeesSummaryView() {
    	
    	setSizeFull();
    	addStyleName("employees-summary-view");
    	
    	// Sets the top bar (CRUD actions)
    	final HorizontalLayout topLayout = createTopBar();

    	// Sets the content of grids
    	this.grid = new EmployeesSummaryGrid();
    	// Sets logic for grid
    	this.logic = new EmployeesSummaryLogic(this, new EmployeesDao());
    	
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
       	
    	// Creates the employee form
    	form = new EmployeeForm(logic, logic.getEmployeesDao());
        addComponent(form);
    }

    public HorizontalLayout createTopBar() {
    	final Button newEmployee = new Button(resourceText.getString("NewEmployee"));
    	newEmployee.addStyleName(ValoTheme.BUTTON_PRIMARY);
    	newEmployee.setIcon(FontAwesome.PLUS_CIRCLE);
    	newEmployee.addClickListener((event) -> {
    		logic.newEmployee();
    	});

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
    	logic.enter(event.getParameters());
    }

    public void clearSelection() {
        grid.getSelectionModel().reset();
    }
    
    public void selectRow(final Employee row) {
        ((SelectionModel.Single) grid.getSelectionModel()).select(row);
    }

    public Employee getSelectedRow() {
        return grid.getSelectedRow();
    }
    
    public void editEmployee(final Employee employee) {
        if (employee != null) {
            form.addStyleName("visible");
            form.setEnabled(true);
        } else {
            form.removeStyleName("visible");
            form.setEnabled(false);
        }
        form.editEmployee(employee);
    }

    public void showEmployees(final Collection<Employee> employees) {
        grid.setEmployees(employees);
    }

    public void refreshEmployee(final Employee employee) {
        grid.refresh(employee);
        grid.scrollTo(employee);
    }

    public void removeEmployee(final Employee employee) {
        grid.remove(employee);
    }
    
}
