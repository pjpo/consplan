package pjpo.github.com.consplan.samples;

import java.util.ResourceBundle;

import pjpo.github.com.consplan.model.Employee;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class EmployeesSummaryGrid2 extends Grid {

    private final ResourceBundle resourceText = ResourceBundle.getBundle("pjpo.github.com.consplan.samples.EmployeesGrid", UI.getCurrent().getLocale());

	public EmployeesSummaryGrid2() {
		
        setSelectionMode(SelectionMode.SINGLE);

        // Sets the container for this container
        final BeanItemContainer<Employee> container = new BeanItemContainer<Employee>(
                Employee.class);
        setContainerDataSource(container);
        setColumns("name", "timePart");
        setColumnOrder("name", "timePart");
        getDefaultHeaderRow().getCell("name").setText(resourceText.getString("GridNameHeader"));
        getDefaultHeaderRow().getCell("timePart").setText(resourceText.getString("GridTimePartHeader"));
        
        // Align columns using a style generator
        setCellStyleGenerator((cellReference) -> {
        	if (cellReference.getPropertyId().equals("name"))
        		return "align-left";
        	else if (cellReference.getPropertyId().equals("timePart"))
        		return "align-right";
        	else
        		return null;
            });

	}
	
    @SuppressWarnings("unchecked")
	private BeanItemContainer<Employee> getContainer() {
        return (BeanItemContainer<Employee>) super.getContainerDataSource();
    }

    public void refresh(final Employee employee) {
        // We avoid updating the whole table through the backend here so we can
        // get a partial update for the grid
        final BeanItem<Employee> item = getContainer().getItem(employee);
        if (item != null) {
            // Updated employee
            @SuppressWarnings("unchecked")
			MethodProperty<Employee> p = (MethodProperty<Employee>) item.getItemProperty("name");
            p.fireValueChange();
        } else {
            // New employee
            getContainer().addBean(employee);
        }
    }

    public void remove(final Employee employee) {
        getContainer().removeItem(employee);
    }
    
    @Override
    public Employee getSelectedRow() throws IllegalStateException {
        return (Employee) super.getSelectedRow();
    }

}
