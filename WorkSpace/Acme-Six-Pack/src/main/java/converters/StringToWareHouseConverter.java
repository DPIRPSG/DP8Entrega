package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.WareHouseRepository;
import domain.WareHouse;

@Component
@Transactional
public class StringToWareHouseConverter implements Converter<String, WareHouse> {

	@Autowired
	WareHouseRepository wareHouseRepository;

	@Override
	public WareHouse convert(String text) {
		WareHouse result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = wareHouseRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
