package com.mdm.order_service.dto;
import java.math.BigDecimal;

public record OrderResponse(
        long id,
        String skuCode,
        BigDecimal price,
        int quantity
) {
}
