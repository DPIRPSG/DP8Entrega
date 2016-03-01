package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CommentedEntityRepository;
import domain.CommentedEntity;

@Component
@Transactional
public class StringToCommentedEntityConverter implements Converter<String, CommentedEntity> {
	
	@Autowired
	CommentedEntityRepository commentedEntityRepository;

	@Override
	public CommentedEntity convert(String text) {
		CommentedEntity result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = commentedEntityRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
	
}
