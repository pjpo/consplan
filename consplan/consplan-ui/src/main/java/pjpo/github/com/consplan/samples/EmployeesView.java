package pjpo.github.com.consplan.samples;

import pjpo.github.com.consplan.model.Employee;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

@SuppressWarnings("serial")
public class EmployeesView extends BaseView {

	public final static String NAME = "EmployeesView";
	
	private final EmployeesSummaryView2 summary;
	
	private final EmployeeForm2 form;
	
	public EmployeesView(final Navigator navigator) {
		summary = new EmployeesSummaryView2(navigator);
		form = new EmployeeForm2();
		setSizeFull();
	}
	
	@Override
	public void enter(ViewChangeEvent event, UrlDecoded urlDecoded) {
		// If form asked, show it
		if (urlDecoded.getPath().size() == 2 && urlDecoded.getPath().get(0).equals("form")) {
			// See if we are in a create action or in a modify action
			Employee editedEmployee = null;
			if (urlDecoded.getPath().get(1).equals("modify")) {
				// Gets the selected id
				if (urlDecoded.getParameters().containsKey("id")) {
					try {
						final Long EmployeeId = Long.decode(urlDecoded.getParameters().get("id"));
					} catch (Exception ex) {
						// Do nothing, create only element
					}
				}
				form.setEditedEmployee(new Employee());
			}
			// If employee not set, create new employee
			if (editedEmployee == null)
				editedEmployee = new Employee();
			
			// Show this employee in form
			form.setEditedEmployee(editedEmployee);
			
			removeAllComponents();
			addComponent(form);
		}
		// no form asked, show list of employees
		else {
			removeAllComponents();
			addComponent(summary);
		}
	}

}
