package com.alexander.javacountries.controllers;

import com.alexander.javacountries.models.Country;
import com.alexander.javacountries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {
    @Autowired
    CountryRepository comrepos;

    private List<Country> findCountries(List<Country> myList, CheckCountry tester) {
        List<Country> tempList = new ArrayList<>();
        for (Country c : myList) {
            if (tester.test(c)) {
                tempList.add(c);
            }
        }
        return tempList;
    }

    //http://localhost:2019/names/all
    @GetMapping(value="names/all", produces = {"application/json"})
    public ResponseEntity<?> ListAllCountries() {
        List<Country> myList = new ArrayList<>();
        comrepos.findAll().iterator().forEachRemaining(myList::add);
        myList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    //http://localhost:2019/names/start/u
    @GetMapping(value = "names/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> ListAllByFirstName(@PathVariable char letter) {
        List<Country> myList = new ArrayList<>();
        comrepos.findAll().iterator().forEachRemaining(myList::add);
        List<Country> resList = findCountries(myList, c -> c.getName().charAt(0) == letter);
        return new ResponseEntity<>(resList, HttpStatus.OK);
    }

    //http://localhost:2019/population/total
    @GetMapping(value = "population/total", produces = {"application/json"})
    public ResponseEntity<?> ListTotalPopulation() {
        List<Country> myList = new ArrayList<>();
        comrepos.findAll().iterator().forEachRemaining(myList::add);
        long totalPopulation = 0;
        for (Country c : myList) {
            totalPopulation = totalPopulation + c.getPopulation();
        }
        System.out.println(("The total population is " + totalPopulation));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
