import java.util.*;
import java.io.*;
/*
 * Author: Helen Shin
 * Date: December 21, 2011
 * ThoughtWorks Application
 * Problem Two: Sales Taxes
 */
public class ShoppingBasket {
	ArrayList <Product> items = new ArrayList<Product>();
	double total; 
	double taxes;
	
	public ShoppingBasket(){
		total=0;
		taxes=0;
	}
	

	public static void main(String[]args) throws IOException{
		ShoppingBasket basket = new ShoppingBasket();
		
		//FILE INPUT
		Scanner in=null;
		FileReader reader= new FileReader("input.txt");
		in= new Scanner(reader);

		// temp vars
		int qty;
		double price;
		String temp="";
		String detail="";
		
		//PARSE INPUT FILE
		while (in.hasNext()){
			if(!in.hasNextInt()){
				basket.printer("Error: Wrong format input");
			}
			qty = in.nextInt();
			detail += in.next();
			while(!in.hasNextDouble()){
				if ((temp=in.next()).equals("at")){
					break;
				}else detail+=" "+temp;
			}
			price = in.nextDouble();
			temp = detail;
			basket.addItem(detail, price, qty, basket.items);
			detail="";
			temp="";
			
			
		} //END OF PARSING INPUT
		
		
		// PRINT EACH ITEM
		for(int i=0; i<basket.items.size(); i++){
			Product p = basket.items.get(i);
			basket.printer(p.qty + " ");
			basket.printer(p.detail+": ");
			basket.numPrinter(p.taxedPrice);
			basket.taxes += p.tax * p.qty;
			basket.total += p.taxedPrice * p.qty;
			basket.printer("\n");
		}
		
		basket.printer("Sales Taxes: ");
		basket.numPrinter(basket.taxes);
		basket.printer("\n");
		
		basket.printer("Total: ");
		basket.numPrinter(basket.total);
		
		//EXIT
		in.close();
	}
	
	public void numPrinter(double x){
		System.out.printf("%5.2f", x);
	}
	
	public void printer(String x){
		System.out.print(x);
	}
	
	
	public boolean removeItem(String product, double price, ArrayList<Product> list){
		if (list.isEmpty())	return false;
		for(int i=0; i<list.size(); i++){
			if (list.get(i).detail.equals(product)){
				list.remove(list.get(i));
				return true;
			}
		}
		return false;
	}
	
	public void addItem(String item, double price, int qty, ArrayList<Product> list){
		Product p = new Product(item, price, qty);
		list.add(p);	
	}
}
