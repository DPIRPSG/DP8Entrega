package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CustomizationInfoRepository;
import domain.CustomizationInfo;

@Component
@Transactional
public class StringToCustomizationInfoConverter implements Converter<String, CustomizationInfo> {

	@Autowired
	CustomizationInfoRepository customizationInfoRepository;

	@Override
	public CustomizationInfo convert(String text) {
		CustomizationInfo result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = customizationInfoRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
