package com.lavanya;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloApplicationTest {

    @Test
    void calcBill() {
        HelloApplication hello=new HelloApplication();
//        assertEquals(hello.calcBill(5,10),20.13);
        assertEquals(hello.calcBill(5,10),20);
    }
}