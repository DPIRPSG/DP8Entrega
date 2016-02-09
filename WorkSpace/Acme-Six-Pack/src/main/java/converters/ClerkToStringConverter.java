package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Clerk;

@Component
@Transactional
public class ClerkToStringConverter implements Converter<Clerk, String> {
	
	@Override
	public String convert(Clerk clerk) {
		String result;

		if (clerk == null)
			result = null;
		else
			result = String.valueOf(clerk.getId());

		return result;
	}

}