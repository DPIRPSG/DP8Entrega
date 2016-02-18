package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.SpamTermRepository;
import domain.SpamTerm;

@Component
@Transactional
public class StringToSpamTermConverter implements Converter<String, SpamTerm> {

	@Autowired
	SpamTermRepository spamTermRepository;

	@Override
	public SpamTerm convert(String text) {
		SpamTerm result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = spamTermRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
