package com.monmar.personalbudget.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private int categoryId;

    @Column(name = "cat_name")
    @NotNull(message = "Category name is required")
    private String categoryName;

    @Enumerated
    @Column(name = "cat_operation_type")
    @NotNull(message = "Operation type is required")
    private OperationType categoryOperationType;


//    @OneToMany(fetch = FetchType.LAZY,
//            mappedBy = "category",
//            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
//    private List<BudgetDetail> budgetList;
//
//    @OneToMany(fetch = FetchType.LAZY,
//            mappedBy = "category",
//            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
//    private List<FinancialTransaction> financialTransactionList;

}
