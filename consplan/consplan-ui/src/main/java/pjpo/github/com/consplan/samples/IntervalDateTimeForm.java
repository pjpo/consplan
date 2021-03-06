package pjpo.github.com.consplan.samples;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;
import java.util.function.Function;

import com.github.pjpo.consplan.library.utils.IntervalDateTime;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.declarative.Design;

@SuppressWarnings("serial")
@DesignRoot
public class IntervalDateTimeForm extends BaseView {

	public enum ExitStatus {
		SAVE, CANCEL, DELETE
	}
	
	public static final String NAME = "IntervalDateTimeForm";

	private final Map<String, Function<Map<String, String>, IntervalDateTime>> enterActions = new HashMap<>();
	
	private final Map<String, BiConsumer<ExitStatus, IntervalDateTime>> exitActions = new HashMap<>();

	private BeanFieldGroup<IntervalDateTime> fieldGroup;
	
	private Boolean newInterval = true;
			
	private PopupDateField start;
	
	private PopupDateField end;
	
	// Stored for remembering what is edited
	
	private String originValue = "";
	
	private IntervalDateTime intervalValue = null;
	
	// Actions button
	
	private Button save;
	
	private Button cancel;
	
	private Button delete;
	
	public IntervalDateTimeForm(final ResourceBundle resource) {
		// Builds the UI
		Design.read("IntervalDateTimeForm.html", this);
		
		// Captions internationalization
		start.setCaption(resource.getString("StartCaption"));
		end.setCaption(resource.getString("EndCaption"));
		
		final DateTimeConverter converter = new DateTimeConverter();
		start.setResolution(Resolution.SECOND);
		start.setLenient(true);
		start.setConverter(converter);
		
		end.setResolution(Resolution.SECOND);
		end.setLenient(true);
		end.setConverter(converter);
		
		// Sets the form elements
		fieldGroup = new BeanFieldGroup<IntervalDateTime>(IntervalDateTime.class);
        fieldGroup.bindMemberFields(this);
        
        // triggers event when a value has been changed
        for (final Field<?> f : fieldGroup.getFields()) {
            f.addValueChangeListener((event) -> formHasChanged());
        }
        
        // Set actions when committing fieldgroup
        fieldGroup.addCommitHandler(new CommitHandler() {

            @Override
            public void preCommit(final CommitEvent commitEvent) throws CommitException {
            }

            @Override
            public void postCommit(final CommitEvent commitEvent) throws CommitException {
            }
        });

        save.addClickListener((event) -> {
        	try {
        		fieldGroup.commit();
        		
        		// only if validation succeeds
        		final IntervalDateTime intervalValue = fieldGroup.getItemDataSource().getBean();
        		exitActions.get(originValue).accept(ExitStatus.SAVE, intervalValue);
        	} catch (CommitException e) {
        		final Notification n = new Notification(
        				"Please re-check the fields", Type.ERROR_MESSAGE);
        		n.setDelayMsec(500);
        		n.show(getUI().getPage());
        	}
        });

        cancel.addClickListener((event) -> exitActions.get(originValue).accept(ExitStatus.CANCEL, null));
  
        delete.addClickListener((event) -> exitActions.get(originValue).accept(ExitStatus.DELETE, null));

    }

    public void register(
    		final String origin,
    		final Function<Map<String, String>, IntervalDateTime> enterAction,
    		final BiConsumer<ExitStatus, IntervalDateTime> exitAction) {
    	enterActions.put(origin, enterAction);
    	exitActions.put(origin, exitAction);
    }
    
	@Override
	public void enter(final ViewChangeEvent event,
			final UrlDecoded urlDecoded) {
		final String origin = urlDecoded.getParameters().get("origin");
		final Function<Map<String, String>, IntervalDateTime> enterAction =
				origin == null ? null : enterActions.get(origin);
		// No origin asked or this origin has not been registered
		if (origin == null || enterAction == null) {
			originValue = "";
			intervalValue = null;
			this.setEnabled(false);
			this.editValue(intervalValue);
		}
		else {
			// This origin is registered, fire the enter action which returns the
			// current interval
			originValue = origin;
			intervalValue = enterAction.apply(urlDecoded.getParameters());
			this.setEnabled(true);
			this.editValue(intervalValue);
		}
	}
	
	public void editValue(IntervalDateTime interval) {
		if (interval == null) {
            interval = new IntervalDateTime();
        }
		// Sets the edited element
		fieldGroup.setItemDataSource(new BeanItem<IntervalDateTime>(interval));
        // before the user makes any changes, disable validation error indicator
        // of the elements
        start.setValidationVisible(false);
        end.setValidationVisible(false);
	}
	
    private void formHasChanged() {
        // show validation errors after the user has changed something
        start.setValidationVisible(true);
        end.setValidationVisible(true);
        
        // only intervals that are not new should be removable
        delete.setEnabled(!newInterval);
    }

	public Boolean isNewInterval() {
		return newInterval;
	}

	public void setNewInterval(final Boolean newInterval) {
		this.newInterval = newInterval;
	}
    
}