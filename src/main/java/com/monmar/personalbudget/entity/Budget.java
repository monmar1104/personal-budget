package com.monmar.personalbudget.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
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
    
    @Column(name = "budget_description")
    private String budgetDescription;

    @Column(name = "budget_date_from")
    private LocalDate budgetDateFrom;

    @Column(name = "budget_date_to")
    private LocalDate budgetDateTo;

    @Column(name = "budget_creation_date")
    private LocalDate budgetCreationDate;

    @OneToMany(fetch = FetchType.LAZY,
                mappedBy = "budget",
                cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private List<BudgetDetail> categoryList;
    
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "budget_user_id")
    private User budgetUser;
}
