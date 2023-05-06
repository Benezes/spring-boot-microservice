package com.mnz.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_order_line_items")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderLineId;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;


}
