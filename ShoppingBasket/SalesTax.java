import java.util.ArrayList;

/*
 * Author: Helen Shin
 * Date: December 22, 2011
 * ThoughtWorks Application
 * Problem Two: Sales Taxes
 */
public class SalesTax {
	private static final double SALES_TAX = .10;
	private static final double IMPORT_TAX = .05;
	public enum Exempt {BOOK, FOOD, MEDICAL, NA }

	private static ArrayList<String> book; 
	private static ArrayList<String> food;
	private static ArrayList<String> medical;

	static{
		 book = new ArrayList<String>();
		 book.add("book");
		 
		 food= new ArrayList<String>();
		 food.add("chocolate");
		 
		 medical= new ArrayList<String>();
		 medical.add("headache pill");
		 
	}
	public static double getImportTaxedPrice(double price){
		price += getImportTax(price); 
		return price;
	}

	public static double getSalesTaxedPrice(double price){
		price += getSalesTax(price); 
		return price;
	}

	public static double getImportTax(double price){
		return calculateTax(price, IMPORT_TAX);
	}

	public static double getSalesTax(double price){
		return calculateTax(price, SALES_TAX);
	}

	public static double calculateTax(double price, double taxRate){
		double dollar=0;
		double cents=0;
		double tax=0;
		tax=taxRate*price;
		if (tax>1){
			dollar = (int)tax;
			cents =tax%dollar;

			//ROUNDING
			double mod=Math.round((cents*100)%5);
			cents=cents*100;
			if(mod>0){
				cents=mod+cents;
			}
			cents = Math.floor(cents);
			cents/=100;
			tax=dollar+cents;
		}
		return tax;
	}

	public static Exempt getCategory(String product){
		if(SalesTax.food.contains(product))
			return Exempt.FOOD;
		else if(SalesTax.medical.contains(product))
			return Exempt.MEDICAL;
		else if(SalesTax.book.contains(product))
			return Exempt.BOOK;
		return Exempt.NA;
	}

	public static boolean isExempt(String product){
		if (SalesTax.getCategory(product)!=Exempt.NA){
			return true;
		}
		return false; 
	}

}
