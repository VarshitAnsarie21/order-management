package com.ordermanagementsystem.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class OrderProductDTO {
    @NotNull
    private Long productId;

    @Min(1)
    private Integer quantity;
}