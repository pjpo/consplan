package pjpo.github.com.consplan.samples;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.DateField;

/**
 * A converter that transforms localdatetimes to strings
 */
@SuppressWarnings("serial")
public class DateTimeConverter implements  Converter<Date, LocalDateTime> {

	final private DateTimeFormatter formatter;
	
	public DateTimeConverter(final DateField dateField) {
		// Get formatter from datefield
		if (dateField.getDateFormat() != null) {
			formatter = DateTimeFormatter.ofPattern(
					dateField.getDateFormat(), dateField.getLocale());
		}
		// No formatter defined, create a default one
		else {
			final DateTimeFormatterBuilder formatterBuilder =
					new DateTimeFormatterBuilder();
			formatter = formatterBuilder.toFormatter(
					dateField.getLocale());
		}

	}
	
	@Override
	public LocalDateTime convertToModel(
			final Date value,
			final Class<? extends LocalDateTime> targetType,
			final Locale locale)
			throws ConversionException {
		try {
			return LocalDateTime.parse(value, formatter);
		} catch (DateTimeParseException ex) {
			throw new ConversionException(ex);
		}
	}

	@Override
	public Date convertToPresentation(
			final LocalDateTime value,
			final Class<? extends String> targetType,
			final Locale locale)
			throws ConversionException {
		return value.format(formatter);

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
