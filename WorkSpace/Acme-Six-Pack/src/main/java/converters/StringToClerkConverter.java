package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ClerkRepository;
import domain.Clerk;

@Component
@Transactional
public class StringToClerkConverter implements Converter<String, Clerk> {

	@Autowired
	ClerkRepository clerkRepository;

	@Override
	public Clerk convert(String text) {
		Clerk result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = clerkRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
