package com.ordermanagementsystem.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
public class OrderRequestDTO {
    @NotNull
    private Long customerId;

    @NotEmpty
    private List<OrderProductDTO> products;
}

