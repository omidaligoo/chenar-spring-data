package com.chenar.spring.data.upside.base.country;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryDomain {

    private String isoAlpaha3;

    private String isoAlpaha2;

    private String numeric;

    private String showingText;

    private String enumText;
    
}
