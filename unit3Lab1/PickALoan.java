package unit3Lab1;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Prompts the user for the name of the input file, reads in the lender information,
 * and determines the recommended loan for each student, writing the output to a file.
 *
 * @author Rhea Goyal
 * Collaborators:
 * Teacher Name: Ms. Bailey
 * Period: 5
 * Due Date: September 30, 2024
 */
public class PickALoan
{
	public static void main(String[] args)
	{
		boolean keepGoing = true;
		while (keepGoing)
		{
			// Ask user for input file
			String inputFileName = getInputFileName();
			try
			{
				Scanner in = new Scanner(new File(inputFileName));
				// Add code here to read lender data, build output file name,
				// and determine the best student loan options
				
				List<Lender> l = readLenderData(in);
				String outputFileName = getOutputFileName(inputFileName);
				determineBestLoan(in, l, outputFileName);
				PrintWriter out = new PrintWriter("outputFileName");
				
				// Check if user wants to continue
				keepGoing = checkContinue();
			}
			catch(FileNotFoundException e)
			{
				System.exit(1);;
			}
			
		}
	}
	
	/** Retrieves the name of an existing input file from the user
	 *  @return file name of an existing text file to read
	 */
	private static String getInputFileName()
	{
		Scanner scan = new Scanner(System.in);
		boolean b = true;
		String name = "";
		while(b) {
			System.out.println("Enter file with loan information: ");
			name = scan.next();
			if(new File (name).exists())
				b = false;
			else
				System.out.println("File not found. Try again.\n");
		}
		scan.close();
		return name;
	}
	
	private static String getOutputFileName(String inputName)
	{
		String [] arr = inputName.split("//.");
		return arr[0] + ".out";
	}
	
	private static boolean checkContinue()
	{
		Scanner scan = new Scanner(System.in);
		boolean ans = false;
		System.out.println("Would you like to continue <yes/no>? ");
		String s = scan.next();
		if(s.toUpperCase().charAt(0) == 'Y')
			ans = true;
					
		scan.close();
		return ans;
	}
	
	private static List<Lender> readLenderData(Scanner scan)
	{
		List <Lender> lenders = new ArrayList<>();
		int num = scan.nextInt();
		for(int i = 0; i < num; i++){
			String name = scan.nextLine().trim();
			double rate = scan.nextDouble();
			double min = scan.nextDouble();
			double fees = scan.nextDouble();
			scan.nextLine();
			lenders.add(new Lender(name, rate, min, fees));
		}
		return lenders;
	}
		
	private static void determineBestLoan(Scanner scan, List<Lender> lenders, String outputFile) 
			throws FileNotFoundException
	{
		PrintWriter out = new PrintWriter(outputFile);
		int num = scan.nextInt();
		for(int i = 0; i < num; i++){
			double [] payment = new double[lenders.size()];
			double min = Double.MAX_VALUE;
			Lender best = new Lender("", 0, 0, 0);
			String name = scan.nextLine();
			for(Lender l : lenders) {
				double curr = l.getMonthlyPayment(scan.nextDouble(), scan.nextInt());
				scan.nextLine();
				if(curr < min) {
					best = l;
					min = curr;
				}
			}
			out.printf("%s should choose the loan from %s for %.2f per month.%n", 
					name, best.getName(), min);
		}		
	}
}
