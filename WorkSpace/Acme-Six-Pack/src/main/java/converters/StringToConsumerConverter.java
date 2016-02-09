package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ConsumerRepository;
import domain.Customer;

@Component
@Transactional
public class StringToConsumerConverter implements Converter<String, Customer> {

	@Autowired
	ConsumerRepository consumerRepository;

	@Override
	public Customer convert(String text) {
		Customer result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = consumerRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
