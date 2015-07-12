package pjpo.github.com.consplan.samples;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

/**
 * A converter that transforms localdatetimes to strings
 */
@SuppressWarnings("serial")
public class DateTimeConverter implements  Converter<Date, LocalDateTime> {
	
	@Override
	public LocalDateTime convertToModel(
			final Date value,
			final Class<? extends LocalDateTime> targetType,
			final Locale locale)
			throws ConversionException {
		if (value == null) 
			return null;
		else 
			// date object contains a number of milliseconds since the "epoch" - 1 January 1970, 00:00:00 UTC
			return value.toInstant().atZone(ZoneOffset.UTC).toLocalDateTime();
	}

	@Override
	public Date convertToPresentation(
			final LocalDateTime value,
			final Class<? extends Date> targetType,
			final Locale locale)
			throws ConversionException {
		if (value == null)
			return null;
		else
			// date object contains a number of milliseconds since the "epoch" - 1 January 1970, 00:00:00 UTC
			return Date.from(value.atZone(ZoneOffset.UTC).toInstant());
	}

	@Override
	public Class<LocalDateTime> getModelType() {
		return LocalDateTime.class;
	}

	@Override
	public Class<Date> getPresentationType() {
		return Date.class;
	}

}
