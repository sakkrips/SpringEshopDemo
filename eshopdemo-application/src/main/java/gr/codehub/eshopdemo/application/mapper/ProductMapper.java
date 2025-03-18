package gr.codehub.eshopdemo.application.mapper;

import gr.codehub.eshopdemo.application.dto.ProductDTO;
import gr.codehub.eshopdemo.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDTO(Product product);

    Product toEntity(ProductDTO productDTO);
} 