package pjpo.github.com.consplan.samples;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;

@SuppressWarnings("serial")
public abstract class BaseView extends CssLayout implements View {
    
	private static Logger LOGGER = Logger.getLogger(BaseView.class.getName(), "ErrorMessages");
	
	protected class UrlDecoded {
		private List<String> path;
		private Map<String, String> parameters;
		public List<String> getPath() {
			return path;
		}
		public void setPath(List<String> path) {
			this.path = path;
		}
		public Map<String, String> getParameters() {
			return parameters;
		}
		public void setParameters(Map<String, String> parameters) {
			this.parameters = parameters;
		}
	}
	
	@Override
	final public void enter(final ViewChangeEvent event) {
		enter(event, parseParameters(event.getParameters()));
	}

	private UrlDecoded parseParameters(String urlString) {
		// Sets the null string as an empty string
		if (urlString == null) {
			urlString = "";
		}
		
		// If parameters start with '/', remove it
		if (!urlString.isEmpty() && urlString.charAt(0) == '/') {
            return parseParameters(urlString.substring(1));
        }
		
		// Creates the decoded url element
		final UrlDecoded urlDecoded = new UrlDecoded();
		final Map<String, String> parameters = new HashMap<>();
		final List<String> path = new ArrayList<>();
		urlDecoded.setParameters(parameters);
		urlDecoded.setPath(path);
		
		// finds the first occurence of '?'
		final int paramsSepIdx = urlString.indexOf('?');
		
		String pathsString = "", parametersString = "";
		// Separator exists, separate pathString with parametersString
		if (paramsSepIdx > 0) {
			pathsString = urlString.substring(0, paramsSepIdx);
			parametersString = urlString.substring(paramsSepIdx + 1, urlString.length());
		}
		// No params separator, url is only path
		else {
			pathsString = urlString;
		}
		
		// Parse path
		for (final String pathString : pathsString.split("/")) {
			try {
				path.add(URLDecoder.decode(pathString, "UTF-8"));
			} catch (UnsupportedEncodingException ex) {
				LOGGER.log(Level.SEVERE, null, ex);
			}
		}
		
		// Parse parameters
		for (final String parameterString : parametersString.split("&")) {
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
		
		return urlDecoded;
		
	}
	
	public abstract void enter(final ViewChangeEvent event, final UrlDecoded urlDecoded);

}
