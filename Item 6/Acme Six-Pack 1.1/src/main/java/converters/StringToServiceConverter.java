package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ServiceRepository;

import domain.ServiceEntity;

@Component
@Transactional
public class StringToServiceConverter implements Converter<String, ServiceEntity> {

	@Autowired
	ServiceRepository serviceRepository;

	@Override
	public ServiceEntity convert(String text) {
		ServiceEntity result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = serviceRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
