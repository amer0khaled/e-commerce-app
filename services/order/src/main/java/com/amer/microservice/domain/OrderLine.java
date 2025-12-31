package com.amer.microservice.orderline;

import com.amer.microservice.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private Integer productId;

    @Column(nullable = false)
    @Positive
    private Integer quantity;

    @Column(precision = 19, scale = 2, nullable = false)
    @Positive
    private BigDecimal unitPrice;

    @Transient
    public BigDecimal getTotalPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    protected void setOrder(Order order) {
        this.order = order;
    }



}
