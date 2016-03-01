package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.BookingRepository;
import domain.Booking;

@Component
@Transactional
public class StringToBookingConverter implements Converter<String, Booking> {

	@Autowired
	BookingRepository bookingRepository;

	@Override
	public Booking convert(String text) {
		Booking result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = bookingRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
