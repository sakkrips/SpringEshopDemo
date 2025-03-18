package gr.codehub.eshopdemo.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Long id;
    private Long customerId;
    private List<Long> productIds;
    private String status;
} 