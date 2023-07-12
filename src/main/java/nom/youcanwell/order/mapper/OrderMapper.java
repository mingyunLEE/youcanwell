package nom.youcanwell.order.mapper;

import nom.youcanwell.order.dto.OrderDto;
import nom.youcanwell.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    Order postInfoToOrder(OrderDto.OrderPostinfo postinfo);
}
