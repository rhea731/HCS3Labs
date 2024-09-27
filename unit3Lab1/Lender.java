package unit3Lab1;
/**
*  @author Rhea Goyal
*  Collaborators:
*  Teacher Name: Ms. Bailey
*  Period: 5
*  Due Date: September 30, 2024
*/
public class Lender
{
	private static final int MONTHS_PER_YEAR = 12;
	
	private String lender;			     // name of lending institution
	private double monthlyRate;	    // monthly interest rate
	private double minPayment;      // minimum payment amount
	private double monthlyFee;       // additional monthly fee
	
	/** Constructs a Lender for the given lending institution
	 *  given the loan information
	 *  @param lender name of lending institution
	 *  @param rate annual interest rate, where 10.5 means 10.5%
	 *  @param minPayment minimum monthly payment amount
	 *  @param fee an additional monthly fee
	 */
	public Lender(String lender, double rate, double minPayment, double fee)
	{
		this.lender = lender;
		this.monthlyRate = rate / MONTHS_PER_YEAR / 100;
		this.minPayment = minPayment;
		this.monthlyFee = fee;
	}
	
	
	public double getMonthlyPayment(double loanAmount, int years)
	{
		int months = years * MONTHS_PER_YEAR;
		double rateCalc = Math.pow(1 + monthlyRate, months);
		double total = loanAmount * rateCalc * monthlyRate / (rateCalc - 1);
		return total;
	}
	
	/** Retrieves the lender's name
	 *  @return name of lender
	 */
	public String getName()
	{
		return lender;
	}
	
	/** Retrieves the annual interest rate
	 *  @return annual interest rate
	 */
	public double getAnnualRate()
	{
		return monthlyRate * MONTHS_PER_YEAR * 100;
	}
		 	
	/** Retrieves the minimum monthly payment to be paid
	 *  @return minimum monthly payment amount
	 */
	public double getMinimumPayment()
	{
		return minPayment;
	}
	
	/** Retrieves the mandatory monthly fee
	 *  @return additional monthly fee
	 */
	public double getMonthlyFee()
	{
		return monthlyFee;
	}
}

