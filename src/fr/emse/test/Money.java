package fr.emse.test;

import java.util.Objects;

public class Money implements IMoney{
	
	private int fAmount;
    private String fCurrency;
    
    public Money(int amount, String currency) {
        fAmount = amount;
        fCurrency = currency;
    }
    
    public int amount() {
        return fAmount;
    }
    
    public String currency() {
        return fCurrency;
    }
    
//    public Money add(Money m) {
//        return new Money(amount() + m.amount(), currency());
//    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Money)) return false;
        Money other = (Money) obj;
        return fAmount == other.fAmount && fCurrency.equals(other.fCurrency);
    }
    
    
    public IMoney add(IMoney m) {
        return m.addMoney(this);
    }
    
    public IMoney addMoney(Money m) {
        if (m.currency().equals(currency()))
            return new Money(amount() + m.amount(), currency());
        return new MoneyBag(this, m);
    }
    
    public IMoney addMoneyBag(MoneyBag s) {
        return s.addMoney(this);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(fAmount, fCurrency);
    }

}
