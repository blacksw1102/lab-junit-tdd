package com.blacksw;

import com.blacksw.airport.Passenger;
import com.blacksw.mileage.Mileage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusPolicy {
    private Passenger mike;
    private Passenger john;
    private Mileage mileage;

    @Given("^마일리지와 일반 승객이 있는 상황에서$")
    public void 마일리지와_일반_승객이_있는_상황에서() throws Throwable {
        mike = new Passenger("Mile", false);
        mileage = new Mileage();
    }

    @When("^일반 승객이 가지고 있는 마일리지가 (\\d+)과 (\\d+)와 (\\d+)일 때$")
    public void 일반_승객이_가지고_있는_마일리지가_과_와_일_때(int mileage1, int mileage2, int mileage3) throws Throwable {
        mileage.addMileage(mike, mileage1);
        mileage.addMileage(mike, mileage2);
        mileage.addMileage(mike, mileage3);
    }

    @Then("^일반 승객의 보너스 포인트는 (\\d+)가 된다$")
    public void 일반_승객의_보너스_포인트는_가_된다(int points) throws Throwable {
        mileage.calculateGivenPoints();
        assertEquals(points, mileage.getPassengersPointsMap().get(mike));
    }

    @Given("^마일리지와 VIP 승객이 있는 상황에서$")
    public void 마일리지와_VIP_승객이_있는_상황에서() throws Throwable {
        john = new Passenger("John", true);
        mileage = new Mileage();
    }

    @When("^VIP 승객이 가지고 있는 마일리지가 (\\d+)과 (\\d+)와 (\\d+)일 때$")
    public void vip_승객이_가지고_있는_마일리지가_과_와_일_때(int mileage1, int mileage2, int mileage3) throws Throwable {
        mileage.addMileage(john, mileage1);
        mileage.addMileage(john, mileage2);
        mileage.addMileage(john, mileage3);
    }

    @Then("^VIP 승객의 보너스 포인트는 (\\d+)가 된다$")
    public void vip_승객의_보너스_포인트는_가_된다(int points) throws Throwable {
        mileage.calculateGivenPoints();
        assertEquals(points, mileage.getPassengersPointsMap().get(john));
    }

}
