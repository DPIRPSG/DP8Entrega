package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.WareHouse;

@Component
@Transactional
public class WareHouseToStringConverter implements Converter<WareHouse, String> {
	
	@Override
	public String convert(WareHouse wareHouse) {
		String result;

		if (wareHouse == null)
			result = null;
		else
			result = String.valueOf(wareHouse.getId());

		return result;
	}

}