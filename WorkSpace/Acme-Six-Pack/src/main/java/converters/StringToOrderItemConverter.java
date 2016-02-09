package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.OrderItemRepository;
import domain.OrderItem;

@Component
@Transactional
public class StringToOrderItemConverter implements Converter<String, OrderItem> {

	@Autowired
	OrderItemRepository orderItemRepository;

	@Override
	public OrderItem convert(String text) {
		OrderItem result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = orderItemRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
