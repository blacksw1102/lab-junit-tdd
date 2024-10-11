package com.blacksw;

import com.blacksw.extension.DataAccessObjectParameterResolver;
import com.blacksw.extension.DatabaseOperationExtension;
import com.blacksw.extension.ExecutionContextExtension;
import com.blacksw.extension.LogPassengerExistsExceptionExtension;
import com.blacksw.jdbc.PassengerDao;
import com.blacksw.jdbc.PassengerExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

// 테스트를 수행할 수 있는 상태인지 확인하는 extension을 설정한다. => ExecutionContextExtension.class
// 테스트 사이클에 따라 데이터베이스 특정 작업을 수행하는 extension을 설정한다 => DatabaseOperationExtension.class
@ExtendWith({ ExecutionContextExtension.class,
        DatabaseOperationExtension.class,
        DataAccessObjectParameterResolver.class,
        LogPassengerExistsExceptionExtension.class})
public class PassengerTest {

    private PassengerDao passengerDao;

    // PassengerDao를 의존 주입받는다.
    public PassengerTest(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    @DisplayName("Passenger 객체 생성 정보와 toString() 정보가 일치하는지 검증한다.")
    @Test
    void testPassenger() {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        assertEquals("Passenger John Smith with identifier : 123-456-789", passenger.toString());
    }

    @DisplayName("신규 Passenger 정보를 DB에 정상 저장하는 행위를 검증한다.")
    @Test
    void testInsertPassenger() throws PassengerExistsException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);
        assertEquals("John Smith", passengerDao.getById("123-456-789").getName());
    }

    @DisplayName("기 Passenger 정보를 수정해서 DB에 업데이트하는 행위를 검증한다.")
    @Test
    void testUpdatePassenger() throws PassengerExistsException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);
        passengerDao.update("123-456-789", "Michael Smith");
        Passenger updatePassenger = passengerDao.getById("123-456-789");
        assertEquals("Michael Smith", updatePassenger.getName());
    }

    @DisplayName("기 Passenger 정보를 DB에서 제거하는 행위를 검증한다.")
    @Test
    void testDeletePassenger() throws PassengerExistsException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);
        passengerDao.delete(passenger);
        assertNull(passengerDao.getById("123-456-789"));
    }

    @DisplayName("기 Passenger을 DB에 중복 저장할 시, PassengerExistsException 발생하는지 검증")
    @Test
    void testInsertExistsPassenger() throws PassengerExistsException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);
        assertThrows(PassengerExistsException.class, () -> passengerDao.insert(passenger));
    }

}
