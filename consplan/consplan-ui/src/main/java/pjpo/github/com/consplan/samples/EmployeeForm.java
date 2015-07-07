package pjpo.github.com.consplan.samples;

import java.util.ResourceBundle;

import pjpo.github.com.consplan.dao.EmployeesDao;
import pjpo.github.com.consplan.model.Employee;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("serial")
public class EmployeeForm extends CssLayout {
	
	private static final String FORMITEM = "employee-form-item"; 
	
	private static final String FORMSTYLE = "employee-form-wrapper"; 

	private TextField name;
	
	private TextField timePart;
    
	private BeanFieldGroup<Employee> fieldGroup;

	private Button save;
	
	private Button cancel;

	private Button delete;
	
	/**
	 *  Source of localized text
	 */
    private final ResourceBundle resourceText = ResourceBundle.getBundle("pjpo.github.com.consplan.samples.EmployeesGrid", UI.getCurrent().getLocale());

    public EmployeeForm(final EmployeesSummaryLogic logic, final EmployeesDao employeesDao) {
    	
    	setHeight(100f, Sizeable.Unit.PERCENTAGE);
    	
    	final Label nameLabel = new Label(resourceText.getString("FormNameLabel"));
		final Label timePartLabel = new Label(resourceText.getString("FormTimePartLabel"));
		name = new TextField();
		timePart = new TextField();

		final CssLayout nameLayout = new CssLayout();
		nameLayout.addStyleName(FORMITEM);
		nameLayout.addComponent(nameLabel);
		nameLayout.addComponent(name);
		nameLayout.setWidth(100f, Sizeable.Unit.PERCENTAGE);
		addComponent(nameLayout);

		final CssLayout timePartLayout = new CssLayout();
		timePartLayout.addStyleName(FORMITEM);
		timePartLayout.addComponent(timePartLabel);
		timePartLayout.addComponent(timePart);
		timePartLayout.setWidth(100f, Sizeable.Unit.PERCENTAGE);
		addComponent(timePartLayout);
		
		final CssLayout buttonsLayout = new CssLayout();
		save = new Button(resourceText.getString("FormSaveLabel"));
		buttonsLayout.addComponent(save);
		cancel = new Button(resourceText.getString("FormCancelLabel"));
		buttonsLayout.addComponent(cancel);
		delete = new Button(resourceText.getString("FormDeleteLabel"));
		buttonsLayout.addComponent(delete);
		buttonsLayout.setWidth(100f, Sizeable.Unit.PERCENTAGE);
		addComponent(buttonsLayout);
		
		addStyleName(FORMSTYLE);

        fieldGroup = new BeanFieldGroup<Employee>(Employee.class);
        fieldGroup.bindMemberFields(this);

        // perform validation and enable/disable buttons while editing
        final ValueChangeListener valueListener = new ValueChangeListener() {
        	@Override
        	public void valueChange(ValueChangeEvent event) {
        		formHasChanged();
        	}
        };
        
        for (final Field<?> f : fieldGroup.getFields()) {
            f.addValueChangeListener(valueListener);
        }

        fieldGroup.addCommitHandler(new CommitHandler() {

            @Override
            public void preCommit(CommitEvent commitEvent)
                    throws CommitException {
            }

            @Override
            public void postCommit(CommitEvent commitEvent)
                    throws CommitException {
            	employeesDao.updateEmployee(fieldGroup.getItemDataSource().getBean());
            }
        });


        save.addClickListener((event) -> {
        	try {
        		fieldGroup.commit();
        		
        		// only if validation succeeds
        		Employee employee = fieldGroup.getItemDataSource().getBean();
        		logic.saveEmployee(employee);
        	} catch (CommitException e) {
        		final Notification n = new Notification(
        				"Please re-check the fields", Type.ERROR_MESSAGE);
        		n.setDelayMsec(500);
        		n.show(getUI().getPage());
        	}
        });

        cancel.addClickListener((event) -> {
        	logic.cancelEmployee();
        });

        delete.addClickListener((event) -> {
        	Employee employee = fieldGroup.getItemDataSource().getBean();
        	logic.deleteEmployee(employee);
        });

    }
	
    public void editEmployee(Employee employee) {
        if (employee == null) {
            employee = new Employee();
        }
        // 
        fieldGroup.setItemDataSource(new BeanItem<Employee>(employee));

        // before the user makes any changes, disable validation error indicator
        // of the product name field (which may be empty)
        name.setValidationVisible(false);

        // Scroll to the top
        // As this is not a Panel, using JavaScript
        String scrollScript = "window.document.getElementById('" + getId()
                + "').scrollTop = 0;";
        Page.getCurrent().getJavaScript().execute(scrollScript);
    }

    private void formHasChanged() {
        // show validation errors after the user has changed something
        name.setValidationVisible(true);

        // only products that have been saved should be removable
        boolean canRemoveEmployee = false;
        BeanItem<Employee> item = fieldGroup.getItemDataSource();
        if (item != null) {
        	Employee employee = item.getBean();
        	canRemoveEmployee = employee.getEmployeeId() != -1;
        }
        delete.setEnabled(canRemoveEmployee);
    }
}
