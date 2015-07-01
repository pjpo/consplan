package pjpo.github.com.consplan.samples;

import java.util.Collection;

import com.github.pjpo.consplan.library.model.Employee;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.ui.Grid;

@SuppressWarnings("serial")
public class EmployeesSummaryGrid extends Grid {

	public EmployeesSummaryGrid() {
        setSizeFull();

        setSelectionMode(SelectionMode.SINGLE);

        // Sets the container for this container
        final BeanItemContainer<Employee> container = new BeanItemContainer<Employee>(
                Employee.class);
        setContainerDataSource(container);
        setColumns("name", "timePart");
        setColumnOrder("name", "timePart");

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
    
    /**
     * Sets the content of employees grid
     * @param employees
     */
    public void setEmployees(final Collection<Employee> employees) {
    	getContainer().removeAllItems();
    	getContainer().addAll(employees);
    }

    public void refresh(final Employee employee) {
        // We avoid updating the whole table through the backend here so we can
        // get a partial update for the grid
        final BeanItem<Employee> item = getContainer().getItem(employee);
        if (item != null) {
            // Updated element
            @SuppressWarnings("unchecked")
			MethodProperty<Employee> p = (MethodProperty<Employee>) item.getItemProperty("name");
            p.fireValueChange();
        } else {
            // New product
            getContainer().addBean(employee);
        }
    }

    public void remove(final Employee employee) {
        getContainer().removeItem(employee);
    }

}
