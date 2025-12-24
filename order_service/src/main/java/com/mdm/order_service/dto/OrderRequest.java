package com.mdm.order_service.dto;

import java.math.BigDecimal;

public record OrderRequest(
        String skuCode,
        BigDecimal price,
        int quantity
) {
}
