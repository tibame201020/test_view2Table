package com.mademo.view2table.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Table("Account_TESTView")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account_TESTView implements Serializable {
    @Id
    @Column("AccId")
    private Long AccId;

    private String TEXTS;

    private String EMAIL;
}
