package gr.codehub.eshopdemo.application.mapper;

import gr.codehub.eshopdemo.application.dto.CustomerDTO;
import gr.codehub.eshopdemo.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "createdAt", ignore = true) // Ignore fields that should not be mapped
    CustomerDTO toDTO(Customer customer);

    @Mapping(target = "createdAt", ignore = true) // Ignore createdAt when converting back
    Customer toEntity(CustomerDTO customerDTO);
} 