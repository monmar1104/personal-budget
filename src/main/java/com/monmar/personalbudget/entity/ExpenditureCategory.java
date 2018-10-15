package com.monmar.personalbudget.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "category")
public class ExpenditureCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    @Getter
    @Setter
    private int id;

    @Column(name = "cat_name")
    @Getter
    @Setter
    private String categoryName;





}
