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
		if (urlDecoded.getPath().size() == 1 && urlDecoded.getPath().get(0).equals("form")) {
			form.setEditedEmployee(new Employee());
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
