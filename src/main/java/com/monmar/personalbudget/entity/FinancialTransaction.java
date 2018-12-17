package com.monmar.personalbudget.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "financial_transaction")
@NamedNativeQuery(
	    name = "Stat",
	    query =
	    		"select f.category.categoryName as category, DATE_FORMAT(f.transactionDate,'%M,%Y') as month, SUM(f.transactionAmount) as amount "
	    				+ "from FinancialTransaction f  "
	    				+ "where f.category.categoryId=:categoryId and f.transactionDate between :dateFrom and :dateTo and f.transactionUser.id=:userId "
	    				+ "group by category, month",
	    resultSetMapping = "Stat"
	)
@SqlResultSetMapping(
	    name = "Stat",
	    classes = @ConstructorResult(
	        targetClass = Stat.class,
	        columns = {
	            @ColumnResult(name = "category"),
	            @ColumnResult(name = "month"),
	            @ColumnResult(name = "amount")
	        }
	    )
	)

public class FinancialTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fin_transaction_id")
    private int transactionId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fin_transaction_date")
    @NotNull(message = "Date is required")
    private LocalDate transactionDate;

    @Column(name = "fin_transaction_amount")
    @NotNull(message = "Amount is required")
    private double transactionAmount;

    @Column(name = "fin_transaction_description")
    private String transactionDescription;

    @ManyToOne(
    			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "fin_transaction_cat_id")
//    @NotNull(message = "Category is required")
    private Category category;
    
    @ManyToOne(fetch = FetchType.LAZY,
    			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "fin_transaction_user_id")
    private User transactionUser;

}
