package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.OrderItem;

@Component
@Transactional
public class OrderItemToStringConverter implements Converter<OrderItem, String> {
	
	@Override
	public String convert(OrderItem orderItem) {
		String result;

		if (orderItem == null)
			result = null;
		else
			result = String.valueOf(orderItem.getId());

		return result;
	}

}