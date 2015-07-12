package pjpo.github.com.consplan.samples;

import java.util.Locale;

import com.github.pjpo.consplan.library.utils.IntervalDateTime;
import com.vaadin.data.util.converter.Converter;

@SuppressWarnings("serial")
public class IntervalDateTimeConverter implements Converter<String, IntervalDateTime> {

	@Override
	public IntervalDateTime convertToModel(String value,
			Class<? extends IntervalDateTime> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String convertToPresentation(IntervalDateTime value,
			Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<IntervalDateTime> getModelType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<String> getPresentationType() {
		// TODO Auto-generated method stub
		return null;
	}

}
