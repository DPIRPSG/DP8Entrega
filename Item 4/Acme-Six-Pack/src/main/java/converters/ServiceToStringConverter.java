package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ServiceEntity;

@Component
@Transactional
public class ServiceToStringConverter implements Converter<ServiceEntity, String> {

	@Override
	public String convert(ServiceEntity service) {
		String result;

		if (service == null)
			result = null;
		else
			result = String.valueOf(service.getId());

		return result;
	}
}
