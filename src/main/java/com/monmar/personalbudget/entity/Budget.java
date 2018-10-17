package com.monmar.personalbudget.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id")
    private int budgetId;

    @Column(name = "budget_name")
    private String budgetName;

    @Column(name = "creation_date")
    private LocalDate budgetDate;


    @OneToMany(fetch = FetchType.LAZY,
                mappedBy = "budget",
                cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private List<BudgetDetail> categoryList;
}
