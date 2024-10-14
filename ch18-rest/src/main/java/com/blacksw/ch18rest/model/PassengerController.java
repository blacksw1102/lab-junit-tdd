package com.blacksw.ch18rest.model;

import com.blacksw.ch18rest.exceptions.PassengerNotFoundException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PassengerController {

    @Autowired
    private PassengerRepository repository;

    @Autowired
    private Map<String, Country> countriesMap;

    @GetMapping("/passengers")
    List<Passenger> findAll() {
        return repository.findAll();
    }

    @PostMapping("/passengers")
    @ResponseStatus(HttpStatus.CREATED)
    Passenger createPassenger(@RequestBody Passenger passenger) {
        return repository.save(passenger);
    }

    @GetMapping("/passengers/{id}")
    Passenger findPassenger(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new PassengerNotFoundException(id));
    }

    @PatchMapping("/passengers/{id}")
    Passenger patchPassenger(@RequestBody Map<String, String> updates, @PathVariable Long id) {
        // 1. repository에서 id passenger 탐색 시도
        return repository.findById(id).map(passenger -> {
            // 2. 탐색 성공
            // 2.1 updates의 name 값이 null이 아니면 passenger에 set
            String name = updates.get("name");
            if (!Strings.isEmpty(name)) {
                passenger.setName(name);
            }
            // 2.2 updates의 country 값이 null이 아니면 passenger에 set
            Country country = countriesMap.get(updates.get("country"));
            if (country != null) {
                passenger.setCountry(country);
            }
            // 2.3 updates의 isRegistered 값이 null이 아니면 passenger에 set
            boolean isRegistered = "true".equalsIgnoreCase(updates.get("isRegistered"));
            passenger.setIsRegistered(isRegistered);
            // 2.4 repository에 persist
            return repository.save(passenger);
        }).orElseThrow(() -> new PassengerNotFoundException(id));
    }

    @DeleteMapping("/passengers/{id}")
    void deletePassenger(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
