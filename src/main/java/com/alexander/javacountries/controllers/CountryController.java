package com.alexander.javacountries.controllers;

import com.alexander.javacountries.models.Country;
import com.alexander.javacountries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {
    @Autowired
    CountryRepository comrepos;

    //http://localhost:2019/names/all
    @GetMapping(value="names/all", produces = {"application/json"})
    public ResponseEntity<?> ListAllCountries() {
        List<Country> myList = new ArrayList<>();
        comrepos.findAll().iterator().forEachRemaining(myList::add);
        myList.sort((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()));
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }
}
