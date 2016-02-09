package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Storage;

@Component
@Transactional
public class StorageToStringConverter implements Converter<Storage, String> {
	
	@Override
	public String convert(Storage storage) {
		String result;

		if (storage == null)
			result = null;
		else
			result = String.valueOf(storage.getId());

		return result;
	}

}