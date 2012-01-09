/*
 * Author: Helen Shin
 * Date: December 22, 2011
 * ThoughtWorks Application
 * Problem Two: Sales Taxes
 */
public class Product {
	String item;
	String detail;
	double unitPrice;
	double tax;
	double taxedPrice;
	int qty;
	boolean exempt;


	public Product(String product, double price, int quantity){
		detail = product;
		unitPrice = price;
		qty = quantity;
		taxedPrice = 0; 
		
		//GET IMPORT DUTY
		if (isImported(product)){
			tax += SalesTax.getImportTax(unitPrice);
			taxedPrice = unitPrice+tax;
			item = product.substring(product.indexOf("imported")+"imported".length()+1);
		}
		
		//SALES TAX EXEMPT?
		item =getItem(product);
		exempt= SalesTax.isExempt(item);

		//GET SALES TAX
		if(exempt){
			taxedPrice += 0;
			tax+=0;
		}else {
			tax+=SalesTax.getSalesTax(unitPrice);
			taxedPrice=tax+unitPrice;
		}
	}

	public boolean isImported(String product){
		return product.contains("imported");
	}
	
	public String getItem(String product){
		//REMOVE ___OF 
		if(product.contains("of")){
			item=product.substring(product.indexOf("of")+3);
		} else item = product;
		
		//REMOVE PLURAL ("s")
		if (item.charAt(item.length()-1)=='s')
			item=item.substring(0, item.length()-1);
		
		return item;
	}

}
