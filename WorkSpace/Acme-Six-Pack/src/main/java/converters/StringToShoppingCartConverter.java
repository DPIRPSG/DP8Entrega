package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ShoppingCartRepository;
import domain.ShoppingCart;

@Component
@Transactional
public class StringToShoppingCartConverter implements Converter<String, ShoppingCart> {

	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Override
	public ShoppingCart convert(String text) {
		ShoppingCart result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = shoppingCartRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
