package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ExchangeRate;

@Component
@Transactional
public class ExchangeRateToStringConverter implements Converter<ExchangeRate, String> {
	
	@Override
	public String convert(ExchangeRate exchangeRate) {
		String result;

		if (exchangeRate == null)
			result = null;
		else
			result = String.valueOf(exchangeRate.getId());

		return result;
	}

}