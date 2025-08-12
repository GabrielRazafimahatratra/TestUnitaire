package fr.emse.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoneyTest {


	private Money m12CHF;
    private Money m14CHF;
    private Money expected;
    private Money f12CHF, f14CHF, f7USD, f21USD;
    private MoneyBag fMB1, fMB2;

    @BeforeEach
    public void setUp() {
        // Variables pour testSimpleAdd et testEquals
        m12CHF = new Money(12, "CHF");
        m14CHF = new Money(14, "CHF");
        expected = new Money(26, "CHF");
        
        // Variables pour les tests d'intégration
        f12CHF = new Money(12, "CHF");
        f14CHF = new Money(14, "CHF");
        f7USD = new Money(7, "USD");
        f21USD = new Money(21, "USD");
        fMB1 = new MoneyBag(f12CHF, f7USD);
        fMB2 = new MoneyBag(f14CHF, f21USD);
    }

    @Test
    public void testSimpleAdd() {
        IMoney result = m12CHF.add(m14CHF);
        assertTrue(expected.equals(result));
    }

    @Test
    public void testEquals() {
        Money m12CHFbis = new Money(12, "CHF");
        assertTrue(m12CHF.equals(m12CHFbis));
    }
    
    @Test
    public void testMixedSimpleAdd() {
        // [12 CHF] + [7 USD] == {[12 CHF][7 USD]}
        Money bag[] = { f12CHF, f7USD };
        MoneyBag expected = new MoneyBag(bag);
        assertEquals(expected, f12CHF.add(f7USD));
    }

    @Test
    public void testBagSimpleAdd() {
        // {[12 CHF][7 USD]} + [14 CHF] == {[26 CHF][7 USD]}
        MoneyBag bag = new MoneyBag(f12CHF, f7USD);
        Money expected[] = { new Money(26, "CHF"), f7USD };
        MoneyBag expectedBag = new MoneyBag(expected);
        assertEquals(expectedBag, bag.add(f14CHF));
    }

    @Test
    public void testSimpleBagAdd() {
        // [14 CHF] + {[12 CHF][7 USD]} == {[26 CHF][7 USD]}
        MoneyBag bag = new MoneyBag(f12CHF, f7USD);
        Money expected[] = { new Money(26, "CHF"), f7USD };
        MoneyBag expectedBag = new MoneyBag(expected);
        assertEquals(expectedBag, f14CHF.add(bag));
    }

    @Test
    public void testBagBagAdd() {
        // {[12 CHF][7 USD]} + {[14 CHF][21 USD]} == {[26 CHF][28 USD]}
        Money expected[] = { new Money(26, "CHF"), new Money(28, "USD") };
        MoneyBag expectedBag = new MoneyBag(expected);
        assertEquals(expectedBag, fMB1.add(fMB2));
    }
    

}
