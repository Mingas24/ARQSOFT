package com.example.OrderManagement.domain.entities;

import com.example.OrderManagement.domain.valueObjects.SandwichId;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sandwichOrder", schema = "orderdb")
public class SandwichOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "sandwichId")
    private SandwichId sandwichId;

    @Column(name = "sandwichAmount")
    private Integer sandwichAmount;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
