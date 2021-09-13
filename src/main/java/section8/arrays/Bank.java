package section8.arrays;

import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<>();
    }

    public boolean addBranch(String branchName) {
        if (findBranch(branchName) != null) return false;
        return branches.add(new Branch(branchName));
    }

    public boolean addCustomer(
            String branchName, String customerName, double initialTransaction) {
        Branch branch = findBranch(branchName);
        if (branch == null) return false;

        return branch.newCustomer(customerName, initialTransaction);
    }

    public boolean addCustomerTransaction(
            String branchName, String customerName, double transaction) {
        Branch branch = findBranch(branchName);
        if (branch == null) return false;
        return branch.addCustomerTransaction(customerName, transaction);
    }

    private Branch findBranch(String branchName) {
        for (Branch branch : branches) {
            if (branch.getName().equals(branchName)) return branch;
        }
        return null;
    }

    public boolean listCustomers(String branchName, boolean printTransactions) {
        Branch branch = findBranch(branchName);
        if (branch == null) return false;
        ArrayList<Customer> customers = branch.getCustomers();
        System.out.println("Customer details for branch " + branch.getName());

        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            System.out.println("Customer: " +
                    customer.getName() + "[" + (i + 1) + "]");

            if (printTransactions) {
                ArrayList<Double> transactions = customer.getTransactions();
                System.out.println("Transactions");
                for (int j = 0; j < transactions.size(); j++) {
                    System.out.println("[" + (j + 1) + "]  Amount " + transactions.get(j));
                }
            }
        }
        return true;
    }
}
