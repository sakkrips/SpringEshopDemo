package gr.codehub.eshopdemo.application.mapper;

import gr.codehub.eshopdemo.application.dto.OrderDTO;
import gr.codehub.eshopdemo.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "productIds", source = "products", qualifiedByName = "mapProductListToIds")
    OrderDTO toDTO(Order order);

    @Mapping(target = "customer", ignore = true) // Will be set in Service Layer
    @Mapping(target = "products", ignore = true) // Will be set in Service Layer
    Order toEntity(OrderDTO orderDTO);

    @Named("mapProductListToIds")
    static List<Long> mapProductListToIds(List<gr.codehub.eshopdemo.domain.model.Product> products) {
        return products.stream().map(gr.codehub.eshopdemo.domain.model.Product::getId).collect(Collectors.toList());
    }
} 