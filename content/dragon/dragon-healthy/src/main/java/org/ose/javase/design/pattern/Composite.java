// Component <- Leaf
// Component <- Composite
// Component *...1 Composite
// Client <-> Component

package org.ose.javase.design.pattern;

import java.util.ArrayList;
import java.util.List;

// Client
public class Composite {
    public static void main(String[] args) {
        // Create a component tree
        Component compositeAccount = new CompositeAccount();

        // Add all customer accounts
        compositeAccount.add(new DepositAccount("DA001", 100));
        compositeAccount.add(new DepositAccount("DA002", 150));
        compositeAccount.add(new SavingsAccount("SA001", 200));

        // Get composite balance for the customer
        System.out.println(compositeAccount.getBalance());
    }
}

abstract class Component {
    protected List<Component> children = new ArrayList<Component>();

    public abstract double getBalance();

    public void add(Component component) {
        children.add(component);
    }

    public Component getChild(int i) {
        return children.get(i);
    }
}

// Composite
class CompositeAccount extends Component {
    @Override
    public double getBalance() {
        double totalBalance = 0;
        for (Component component : children) {
            totalBalance = totalBalance + component.getBalance();
        }
        return totalBalance;
    }
}

// Leaf
class DepositAccount extends Component {
    private String accountNo;
    private double accountBalance;

    public DepositAccount(String accountNo, double accountBalance) {
        super();
        this.accountNo = accountNo;
        this.accountBalance = accountBalance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    @Override
    public double getBalance() {
        return accountBalance;
    }
}

class SavingsAccount extends Component {
    private String accountNo;
    private double accountBalance;

    public SavingsAccount(String accountNo, double accountBalance) {
        super();
        this.accountNo = accountNo;
        this.accountBalance = accountBalance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    @Override
    public double getBalance() {
        return accountBalance;
    }
}