package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Customer;

@Component
@Transactional
public class ConsumerToStringConverter implements Converter<Customer, String> {
	
	@Override
	public String convert(Customer consumer) {
		String result;

		if (consumer == null)
			result = null;
		else
			result = String.valueOf(consumer.getId());

		return result;
	}

}