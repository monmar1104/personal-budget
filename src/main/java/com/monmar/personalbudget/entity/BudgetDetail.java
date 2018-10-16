package com.monmar.personalbudget.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "budget_details")
public class BudgetDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_det_id")
    private int budgetDetailId;

    @Column(name = "budget_det_amount")
    private BigDecimal budgetDetailAmount;

    @Enumerated
    @Column(name = "budget_det_operation_type")
    private OperationType budgetDetailOperationType;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "budget_det_budget_id")
    private Budget budget;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "budget_det_cat_id")
    private Category category;

}
