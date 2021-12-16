package com.chenar.spring.data.jpa.domain;

import com.chenar.spring.data.upside.model.GeneralField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class UpModel extends GeneralField<Long> {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public UpModel() {
        super();
    }
}
