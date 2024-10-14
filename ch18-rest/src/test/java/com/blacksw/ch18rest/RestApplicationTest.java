package com.blacksw.ch18rest;

import com.blacksw.ch18rest.beans.FlightBuilder;
import com.blacksw.ch18rest.exceptions.PassengerNotFoundException;
import com.blacksw.ch18rest.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(FlightBuilder.class)
public class RestApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Flight flight;

    @Autowired
    private Map<String, Country> countriesMap;

    @MockBean
    private PassengerRepository passengerRepository;

    @MockBean
    private CountryRepository countryRepository;

    @Test
    void testGetAllCountries() throws Exception {
        // mockito 모의 설정
        when(countryRepository.findAll()).thenReturn(new ArrayList<>(countriesMap.values()));
        mvc.perform(get("/countries"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));

        // api 검증
        verify(countryRepository, times(1)).findAll();
    }

    @Test
    void testGetAllPassenger() throws Exception {
        // mockito 모의 설정
        when(passengerRepository.findAll()).thenReturn(new ArrayList<>(flight.getPassengers()));
        
        // api 검증
        mvc.perform(get("/passengers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(20)));
    }

    @Test
    void testPassengerNotFound() throws Exception {
        // 예외 발생 검증
        Throwable throwable = assertThrows(ServletException.class, () -> mvc.perform(get("/passengers/30")).andExpect(status().isNotFound()));
        // ServletException.class의 원인 검증
        assertEquals(PassengerNotFoundException.class, throwable.getCause().getClass());
    }

    @Test
    void testPostPassenger() throws Exception {
        // mockito 모의 설정
        Passenger passenger = new Passenger("Peter Michelsen");
        passenger.setCountry(countriesMap.get("US"));
        passenger.setIsRegistered(false);
        when(passengerRepository.save(passenger)).thenReturn(passenger);

        // api 검증
        mvc.perform(post("/passengers")
                .content(new ObjectMapper().writeValueAsString(passenger))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Peter Michelsen")))
                .andExpect(jsonPath("$.country.codeName", is("US")))
                .andExpect(jsonPath("$.country.name", is("USA")))
                .andExpect(jsonPath("$.registered", is(Boolean.FALSE)));

        // passengerRepository.save가 1회 호출되었는지 검증.
        verify(passengerRepository, times(1)).save(passenger);
    }

    @Test
    void testPatchPassenger() throws Exception {
        // mockito 모의 설정
        Passenger passenger = new Passenger("Sophia Graham");
        passenger.setCountry(countriesMap.get("UK"));
        passenger.setIsRegistered(false);
        when(passengerRepository.findById(1L)).thenReturn(Optional.of(passenger));
        when(passengerRepository.save(passenger)).thenReturn(passenger);
        String updates = "{\"name\":\"Sophia Jones\", \"country\":\"AU\", \"isRegistered\":\"true\"}";

        // api 검증
        mvc.perform(patch("/passengers/1")
                .content(updates)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // 메서드 호출 횟수 검증
        verify(passengerRepository, times(1)).findById(1L);
        verify(passengerRepository, times(1)).save(passenger);
    }

    @Test
    public void testDeletePassenger() throws Exception {
        mvc.perform(delete("/passengers/4"))
                .andExpect(status().isOk());

        verify(passengerRepository, times(1)).deleteById(4L);
    }

}