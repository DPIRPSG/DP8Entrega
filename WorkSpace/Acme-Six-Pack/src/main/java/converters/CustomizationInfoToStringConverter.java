package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.CustomizationInfo;

@Component
@Transactional
public class CustomizationInfoToStringConverter implements Converter<CustomizationInfo, String> {
	
	@Override
	public String convert(CustomizationInfo customizationInfo) {
		String result;

		if (customizationInfo == null)
			result = null;
		else
			result = String.valueOf(customizationInfo.getId());

		return result;
	}

}