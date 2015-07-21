package pjpo.github.com.consplan.samples;

import pjpo.github.com.consplan.dao.EmployeesDao;
import pjpo.github.com.consplan.model.Employee;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

@SuppressWarnings("serial")
public class EmployeesView extends BaseView {

	public final static String NAME = "EmployeesView";
	
	private final EmployeesSummaryView2 summary;
	
	private final EmployeeForm2 form;
	
	private final EmployeesDao dao;
	
	public EmployeesView(
			final Navigator navigator,
			final EmployeesDao employeesDao) {
		summary = new EmployeesSummaryView2(navigator, employeesDao);
		form = new EmployeeForm2();
		dao = employeesDao;
		setSizeFull();
	}
	
	@Override
	public void enter(
			final ViewChangeEvent event,
			final UrlDecoded urlDecoded) {
		// if new element added, add a new element in form
		if (urlDecoded.getPath().size() == 1 && urlDecoded.getPath().get(0).equals("new")) {
			form.setEditedEmployee(new Employee());

			removeAllComponents();
			addComponent(form);
		}
		// if update asked, update it
		else if (urlDecoded.getPath().size() == 1 && urlDecoded.getPath().equals("update")
				&& urlDecoded.getParameters().containsKey("id")) {
			try {
				final Long employeeId = Long.decode(urlDecoded.getParameters().get("id"));
				final Employee employee = dao.getById(employeeId);
				form.setEditedEmployee(employee);
				
				removeAllComponents();
				addComponent(form);
			} catch (Exception ex) {
				removeAllComponents();
				addComponent(summary);
			}
		}
		// If delete asked, delete and show summary
		else if (urlDecoded.getPath().size() == 1 && urlDecoded.getPath().equals("delete")
				&& urlDecoded.getParameters().containsKey("id")) {
			removeAllComponents();
			addComponent(summary);
		}
		// Default action = show summary
		else {
			removeAllComponents();
			addComponent(summary);
		}
	}

}
