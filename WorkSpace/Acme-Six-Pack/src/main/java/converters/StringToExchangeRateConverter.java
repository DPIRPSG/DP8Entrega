package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ExchangeRateRepository;
import domain.ExchangeRate;

@Component
@Transactional
public class StringToExchangeRateConverter implements Converter<String, ExchangeRate> {

	@Autowired
	ExchangeRateRepository exchangeRateRepository;

	@Override
	public ExchangeRate convert(String text) {
		ExchangeRate result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = exchangeRateRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
