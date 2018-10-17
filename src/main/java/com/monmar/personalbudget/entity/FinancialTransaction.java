package com.monmar.personalbudget.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "financial_transaction")
public class FinancialTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fin_transaction_id")
    private int transactionId;

    @Column(name = "fin_transaction_date")
    private LocalDate transactionDate;

    @Column(name = "fin_transaction_amount")
    private BigDecimal transactionAmount;

    @Column(name = "fin_transaction_description")
    private String transactionDescription;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "fin_transaction_cat_id")
    private Category category;

}
