package pjpo.github.com.consplan.samples;

import pjpo.github.com.consplan.ConsPlan;
import pjpo.github.com.consplan.dao.EmployeesDao;
import pjpo.github.com.consplan.model.Employee;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;

public class EmployeesSummaryLogic {

    private EmployeesSummaryView view;

    private Employee selectedEmployee;
    
    private EmployeesDao employeesDao; 
    
    public EmployeesSummaryLogic(
    		final EmployeesSummaryView view,
    		final EmployeesDao employeesDao) {
        this.view = view;
        this.employeesDao = employeesDao;
    }

    public void init() {
        editEmployee(null);

        view.showEmployees(employeesDao.getEmployees());
    }

    public void cancelProduct() {
        setFragmentParameter("");
        view.clearSelection();
        view.editEmployee(null);
    }

    /**
     * Update the fragment without causing navigator to change view
     */
    private void setFragmentParameter(final String employeeId) {
        String fragmentParameter;
        if (employeeId == null || employeeId.isEmpty()) {
            fragmentParameter = "";
        } else {
            fragmentParameter = employeeId;
        }

        Page page = ConsPlan.get().getPage();
        page.setUriFragment("!" + EmployeesSummaryView.NAME + "/"
                + fragmentParameter, false);
    }

    public void enter(ViewChangeEvent event) {
    	// Do nothing
    }

    private Employee findEmployee(final Long employeeId) {
        return employeesDao.getById(employeeId);
    }

    public void saveEmployee(final Employee employee) {
        view.clearSelection();
        view.editEmployee(null);
        view.refreshEmployee(employee);
        setFragmentParameter("");
    }

    public void deleteEmployee(final Employee employee) {
    	employeesDao.deleteEmployee(employee.getEmployeeId());

        view.clearSelection();
        view.editProduct(null);
        view.removeEmployee(product);
        setFragmentParameter("");
    }

    public void editEmployee(final Employee employee) {
        if (employee == null) {
            setFragmentParameter("");
        } else {
            setFragmentParameter(employee.getEmployeeId() + "");
        }
        view.editEmployee(employee);
    }

    public void newEmployee() {
        view.clearSelection();
        setFragmentParameter("new");
        view.editProduct(new Employee());
    }

    public void rowSelected(final Employee employee) {
            view.editEmployee(employee);
        }
    }

}
