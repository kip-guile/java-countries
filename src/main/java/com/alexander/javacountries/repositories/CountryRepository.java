package com.alexander.javacountries.repositories;

import com.alexander.javacountries.models.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
