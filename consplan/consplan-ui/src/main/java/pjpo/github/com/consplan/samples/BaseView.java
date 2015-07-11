package pjpo.github.com.consplan.samples;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;

@SuppressWarnings("serial")
public abstract class BaseView extends CssLayout implements View {
    
	private static Logger LOGGER = Logger.getLogger(BaseView.class.getName(), "ErrorMessages");
	
	@Override
	public void enter(final ViewChangeEvent event) {
		enter(event, parseParameters(event.getParameters()));
	}

	private Map<String, String> parseParameters(final String parametersString) {
		// If parameters start with '/', remove it
		if ((parametersString != null) && (!parametersString.isEmpty()) && (parametersString.charAt(0) == '/')) {
            return parseParameters(parametersString.substring(1));
        }
		
		// Creates the parameters map
		final Map<String, String> parameters = new HashMap<>();
		
		// No parameters, return the void map
		if ((parametersString == null) || (parametersString.isEmpty())) {
            return parameters;
        }
		// Parse parameters
		else {
			for (String parameterString : parametersString.split("&")) {
				// Position of equal
	            final int sepIdx = parameterString.indexOf('=');
	            try {
		            // Separator exists, separates name and value
		            if (sepIdx > 0) {
		            	final String name = URLDecoder.decode(parameterString.substring(0, sepIdx), "UTF-8");
		            	final String value = URLDecoder.decode(parameterString.substring(sepIdx + 1, parameterString.length()), "UTF-8");
		            	parameters.put(name, value);
		            }
		            // No separator, name is value
		            else {
		            	final String name = URLDecoder.decode(parameterString, "UTF-8");
		            	parameters.put(name, name);
		            }
                } catch (UnsupportedEncodingException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
			}
			return parameters;
        }
		
	}
	
	public abstract void enter(final ViewChangeEvent event, final Map<String, String> parameters);

}
