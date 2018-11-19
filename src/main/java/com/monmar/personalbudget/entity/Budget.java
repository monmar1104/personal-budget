package com.monmar.personalbudget.entity;

import lombok.*;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "budget_date_from")
    private LocalDate budgetDateFrom;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "budget_date_to")
    private LocalDate budgetDateTo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "budget_creation_date")
    private LocalDate budgetCreationDate;

//    @OneToMany(fetch = FetchType.LAZY,
//                mappedBy = "budget",
//                cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
//    private List<BudgetDetail> budgetDetailList;
//    
    @ManyToOne(fetch = FetchType.LAZY ,
    			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "budget_user_id")
    private User budgetUser;
}
