package com.mdm.order_service.dto;

public record OrderRequest(
        long id,
        String orderNumber,
        String skuCode,
        double price,
        int quantity
) {
}
