// JeremyLimKL : Last update 5/7
import java.sql.Date;

// LoanClass is used to store the loan information.
public class LoanClass {
    private int loanID;
    private int accountID;
    private double loanOriginal;
    private double interestRate;
    private double loanRemaining;
    private double loanMonthly;
    private Date loanStartDate;
    private int loanPeriod;

    // Constructor for LoanClass object.
    public LoanClass(int loanID, int accountID, double loanOriginal, double interestRate, double loanRemaining,
            double loanMonthly, Date loanStartDate, int loanPeriod) {
        this.loanID = loanID;
        this.accountID = accountID;
        this.loanOriginal = loanOriginal;
        this.interestRate = interestRate;
        this.loanRemaining = loanRemaining;
        this.loanMonthly = loanMonthly;
        this.loanStartDate = loanStartDate;
        this.loanPeriod = loanPeriod;
    }

    // Returns the LoanClass object's information.
    public int getLoanID() {
        return loanID;
    }
    public int getAccountID() {
        return accountID;
    }
    public double getLoanOriginal() {
        return loanOriginal;
    }
    public double getInterestRate() {
        return interestRate;
    }
    public double getLoanRemaining() {
        return loanRemaining;
    }
    public double getLoanMonthly() {
        return loanMonthly;
    }
    public Date getLoanStartDate() {
        return loanStartDate;
    }
    public int getLoanPeriod() {
        return loanPeriod;
    }
}
