package pjpo.github.com.consplan.samples;

import java.util.ResourceBundle;

import pjpo.github.com.consplan.model.Employee;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.declarative.Design;

@SuppressWarnings("serial")
public class EmployeeForm2 extends CssLayout {

	public final static String NAME = "EmployeeForm2";
	
	private TextField name;
	
	private TextField timePart;
    
	private Button save;
	
	private Button cancel;

	private final BeanFieldGroup<Employee> fieldGroup;

	/**
	 *  Source of localized text
	 */
    private final ResourceBundle resourceText = ResourceBundle.getBundle("pjpo.github.com.consplan.samples.EmployeesForm", UI.getCurrent().getLocale());

    public EmployeeForm2() {
    	// Builds the UI
		Design.read("EmployeesForm.html", this);

    	// Sets I18N labels
    	name.setCaption(resourceText.getString("Name"));
    	timePart.setCaption(resourceText.getString("TimePart"));
    	save.setCaption(resourceText.getString("Save"));
    	cancel.setCaption(resourceText.getString("Cancel"));

    	// Sets the fields needing validation in Employee
        fieldGroup = new BeanFieldGroup<Employee>(Employee.class);
        fieldGroup.bindMemberFields(this);

        // perform validation and enable/disable buttons while editing
        final ValueChangeListener valueListener = 
        		(event) -> formHasChanged();
        
        for (final Field<?> f : fieldGroup.getFields()) {
            f.addValueChangeListener(valueListener);
        }

        save.addClickListener((event) -> {
        	try {
        		fieldGroup.commit();
        		
        		// Do everything needed if validation succeeds

        	} catch (CommitException e) {
        		final Notification n = new Notification(
        				"Please re-check the fields", Type.ERROR_MESSAGE);
        		n.setDelayMsec(500);
        		n.show(getUI().getPage());
        	}
        });

        cancel.addClickListener((event) -> {
        	// Do everything needed if validation fails
        });

    }

    public Employee getEditedEmployee() {
    	return fieldGroup.getItemDataSource().getBean();
    }
    
    public void setEditedEmployee(final Employee employee) {
    	fieldGroup.setItemDataSource(new BeanItem<Employee>(employee));

        // before the user makes any changes, disable validation error indicator
        // of the employee (which may be empty)
        name.setValidationVisible(false);
        timePart.setValidationVisible(false);

        // Scroll to the top
        // As this is not a Panel, using JavaScript
        final String scrollScript = "window.document.getElementById('" + getId()
                + "').scrollTop = 0;";
        Page.getCurrent().getJavaScript().execute(scrollScript);
    }

    private void formHasChanged() {
        // show validation errors after the user has changed something
        name.setValidationVisible(true);
        timePart.setValidationVisible(true);
    }
}
