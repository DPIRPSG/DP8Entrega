package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.CommentedEntity;

@Component
@Transactional
public class CommentedEntityToStringConverter implements Converter<CommentedEntity, String> {

	@Override
	public String convert(CommentedEntity commentedEntity) {
		String result;

		if (commentedEntity == null)
			result = null;
		else
			result = String.valueOf(commentedEntity.getId());

		return result;
	}
	
}
