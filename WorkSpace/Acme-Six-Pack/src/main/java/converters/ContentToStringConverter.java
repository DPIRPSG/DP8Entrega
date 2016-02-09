package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Content;

@Component
@Transactional
public class ContentToStringConverter implements Converter<Content, String> {
	
	@Override
	public String convert(Content content) {
		String result;

		if (content == null)
			result = null;
		else
			result = String.valueOf(content.getId());

		return result;
	}

}