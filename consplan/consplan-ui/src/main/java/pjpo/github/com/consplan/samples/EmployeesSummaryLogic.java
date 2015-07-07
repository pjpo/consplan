package pjpo.github.com.consplan.samples;

import pjpo.github.com.consplan.ConsPlan;
import pjpo.github.com.consplan.dao.EmployeesDao;
import pjpo.github.com.consplan.model.Employee;

import com.vaadin.server.Page;

public class EmployeesSummaryLogic {

    private EmployeesSummaryView view;
    
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

    public void cancelEmployee() {
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

    public void enter(final String employeeId) {
        if (employeeId != null && !employeeId.isEmpty()) {
            if (employeeId.equals("new")) {
                newEmployee();
            } else {
            	// Ensure this is selected even if coming directly here from
            	// login
            	try {
            		long eid = Long.parseLong(employeeId);
            		Employee employee = findEmployee(eid);
                    view.selectRow(employee);
                } catch (NumberFormatException e) {
                }
            }
        }    }

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
        view.editEmployee(null);
        view.removeEmployee(employee);
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
        view.editEmployee(employeesDao.newEmployee());
    }

    public void rowSelected(final Employee employee) {
    	this.editEmployee(employee);
    }
    
    public EmployeesDao getEmployeesDao() {
    	return employeesDao;
    }
}

