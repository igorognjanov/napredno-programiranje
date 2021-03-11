package mk.ukim.finki.aud3.zadaca1;

import java.util.Scanner;

public class Bank {
    private Account[] accounts;

    public Bank(Account[] accounts) {
        this.accounts = accounts;

    }

    public double totalAssets(){
        double zbir = 0.0;
        for(int i=0; i<accounts.length; i++){
            zbir += accounts[i].getAmount ();
        }
        return zbir;
    }

    public void addInterest(){
        for(int i=0; i<accounts.length; i++){
            if(accounts[i] instanceof InterestBearingAccount) {
                InterestBearingAccount interestBearingAccount = (InterestBearingAccount) accounts[i];
                interestBearingAccount.addInterest ();
            }
        }
    }

    public void addAcount(Account a){
        Account [] temp = new Account[accounts.length + 1];
        for(int i=0; i<accounts.length; i++) temp[i] = accounts[i];
        temp[accounts.length] = a;
        accounts = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        int n = scanner.nextInt();
        Account[] accounts = new Account[n];
        for(int i=0; i<n; i++){
            String name = scanner.next ();
            int id = scanner.nextInt ();
            double amount = scanner.nextDouble ();
            accounts[i] = new InterestCheckingAccount (name, id, amount);
        }
        PlatinumCheckingAccount acc = new PlatinumCheckingAccount ("Kral", 20012, 100);
        double interest = scanner.nextDouble ();
        InterestCheckingAccount.changeInterest (interest);
        Bank bank = new Bank(accounts);
        bank.addAcount (acc);
        bank.addInterest ();
        System.out.println (bank.totalAssets ());
    }

}
