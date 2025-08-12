package fr.emse.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoneyTest {


	private Money m12CHF;
    private Money m14CHF;
    private Money expected;

    @BeforeEach
    public void setUp() {
        m12CHF = new Money(12, "CHF");
        m14CHF = new Money(14, "CHF");
        expected = new Money(26, "CHF");
    }

    @Test
    public void testSimpleAdd() {
        Money result = m12CHF.add(m14CHF);
        assertTrue(expected.equals(result));
    }

    @Test
    public void testEquals() {
        Money m12CHFbis = new Money(12, "CHF");
        assertTrue(m12CHF.equals(m12CHFbis));
    }


}
