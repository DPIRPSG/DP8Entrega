package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.FeePayment;

@Component
@Transactional
public class FeePaymentToStringConverter implements Converter<FeePayment, String> {
	
	@Override
	public String convert(FeePayment feePayment) {
		String result;

		if (feePayment == null)
			result = null;
		else
			result = String.valueOf(feePayment.getId());

		return result;
	}

}