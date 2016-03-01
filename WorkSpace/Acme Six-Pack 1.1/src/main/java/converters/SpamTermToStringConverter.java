package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.SpamTerm;

@Component
@Transactional
public class SpamTermToStringConverter implements Converter<SpamTerm, String> {
	
	@Override
	public String convert(SpamTerm spamTerm) {
		String result;

		if (spamTerm == null)
			result = null;
		else
			result = String.valueOf(spamTerm.getId());

		return result;
	}

}