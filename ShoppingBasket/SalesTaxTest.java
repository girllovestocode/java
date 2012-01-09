import static org.junit.Assert.*;

import org.junit.Test;


public class SalesTaxTest {

	@Test
	public void testGetImportTaxedPrice() {
		double price = 10.00;
		double result= SalesTax.getImportTaxedPrice(price);
		double expected= 10.50;
		assertTrue(result==expected);
		 price = 47.50;
		 result= SalesTax.getImportTaxedPrice(price);
		 expected= 49.90;
		assertTrue(result==expected);
		 price = 27.99;
		 result= SalesTax.getImportTaxedPrice(price);
		 expected= 29.39; 
		assertTrue(result==expected);
		 price = 11.25;
		 result= SalesTax.getImportTaxedPrice(price);
		 expected= 11.85;
		assertTrue(result==expected);
		
	}

	@Test
	public void testGetSalesTaxedPrice() {
		double price = 18.99;
		double result= SalesTax.getSalesTaxedPrice(price);
		double expected= 20.89;
		assertTrue(result==expected);
	}

	@Test
	public void testGetImportTax() {
		double price = 10.00;
		double result= SalesTax.getImportTax(price);
		double expected= 0.50;
		assertTrue(result==expected);
		price = 47.50;
		 result= SalesTax.getImportTax(price);
		 expected= 2.40;
		assertTrue(result==expected);
		 price = 27.99;
		 result= SalesTax.getImportTax(price);
		 expected=1.40;
		assertTrue(result==expected);
		 price = 11.25;
		 result= SalesTax.getImportTax(price);
		 expected= 0.60;
		assertTrue(result==expected);
	}

	@Test
	public void testGetSalesTax() {
		double price = 18.99;
		double result= SalesTax.getSalesTax(price);
		double expected= 1.90;
		assertTrue(result==expected);	
		
		
	}


	@Test
	public void testCalculateTax() {
		double price = 10.00;
		double taxRate = .05;
		double result= SalesTax.calculateTax(price, taxRate);
		double expected= 10.50;
		assertTrue(result==expected);
		
		 price = 14.99;
		 taxRate = .10;
		 result= SalesTax.calculateTax(price, taxRate);
		 expected= 16.49;
		assertTrue(result==expected);
		
		
	}

	@Test
	public void testGetCategory() {
		String product = "book";
		SalesTax.Exempt result = SalesTax.getCategory(product);
		SalesTax.Exempt expected= SalesTax.Exempt.BOOK;
		assertTrue(result.equals(expected));
		product = "pill";
		 result = SalesTax.getCategory(product);
		expected= SalesTax.Exempt.MEDICAL;
		assertTrue(result.equals(expected));
		product = "chocolate";
		 result = SalesTax.getCategory(product);
		expected= SalesTax.Exempt.FOOD;
		assertTrue(result.equals(expected));
		product = "perfume";
		 result = SalesTax.getCategory(product);
		expected= SalesTax.Exempt.NA;
		assertTrue(result.equals(expected));
		product = "music CD";
		 result = SalesTax.getCategory(product);
		expected= SalesTax.Exempt.NA;
		assertTrue(result.equals(expected));
		
	}

	@Test
	public void testIsExempt() {
		String product = "book";
		boolean result = SalesTax.isExempt(product);
		boolean expected= true;
		assertTrue(result==expected);
			
		product = "pill";
		 result = SalesTax.isExempt(product);
		 expected= true;
		assertTrue(result==expected);
			
		product = "chocolate";
		 result = SalesTax.isExempt(product);
		 expected= true;
		assertTrue(result==expected);
		
		product = "perfume";
		 result = SalesTax.isExempt(product);
		 expected= false;
		assertTrue(result==expected);
		
		product = "music CD";
		 result = SalesTax.isExempt(product);
		 expected= false;
		assertTrue(result==expected);
		
	}

}
