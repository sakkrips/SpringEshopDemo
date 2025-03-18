package gr.codehub.eshopdemo.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private LocalDateTime createdAt;
} 