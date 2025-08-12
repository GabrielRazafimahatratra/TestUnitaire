package fr.emse.test;

import java.util.Vector;

class MoneyBag implements IMoney{
    private Vector<Money> fMonies = new Vector<Money>();
    
    MoneyBag(Money m1, Money m2) {
        appendMoney(m1);
        appendMoney(m2);
    }
    
    MoneyBag(Money bag[]) {
        for (int i = 0; i < bag.length; i++)
            appendMoney(bag[i]);
    }
    
    private void appendMoney(Money m) {
        if (fMonies.isEmpty()) {
            if (m.amount() != 0) {
                fMonies.add(m);
            }
        } else {
            int i = 0;
            while ((i < fMonies.size()) &&
                   (!(fMonies.get(i).currency().equals(m.currency()))))
                i++;
            if (i >= fMonies.size()) {
                if (m.amount() != 0) {
                    fMonies.add(m);
                }
            } else {
                int newAmount = fMonies.get(i).amount() + m.amount();
                if (newAmount == 0) {
                    fMonies.remove(i);
                } else {
                    fMonies.set(i, new Money(newAmount, m.currency()));
                }
            }
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        MoneyBag moneyBag = (MoneyBag) obj;
        if (fMonies.size() != moneyBag.fMonies.size()) return false;
        
        for (Money money : fMonies) {
            if (!moneyBag.fMonies.contains(money)) return false;
        }
        return true;
    }
    
    
    public IMoney add(IMoney m) {
        return m.addMoneyBag(this);
    }
    
    public IMoney addMoney(Money m) {
        MoneyBag result = new MoneyBag(new Money[0]);
        result.fMonies.addAll(this.fMonies);
        result.appendMoney(m);
        return result.simplify();
    }

    public IMoney addMoneyBag(MoneyBag s) {
        MoneyBag result = new MoneyBag(new Money[0]);
        result.fMonies.addAll(this.fMonies);
        for (Money money : s.fMonies) {
            result.appendMoney(money);
        }
        return result.simplify();
    }
    
    public IMoney simplify() {
        if (fMonies.size() == 1) {
            return fMonies.get(0);
        }
        return this;
    }
}