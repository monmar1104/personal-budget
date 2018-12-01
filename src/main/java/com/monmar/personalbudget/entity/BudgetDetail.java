package com.monmar.personalbudget.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
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
    @NotNull(message = "Amount id requried")
    private BigDecimal budgetDetailAmount;

    @Column(name = "budget_det_description")
    private String budgetDetailDescription;

    @ManyToOne(
//    		fetch = FetchType.LAZY,
    			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "budget_det_budget_id")
    @NotNull
    private Budget budget;

    @ManyToOne(
//    		fetch = FetchType.LAZY,
    			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "budget_det_cat_id")
    @NotNull
    private Category category;

}
