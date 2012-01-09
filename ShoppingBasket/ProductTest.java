import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ProductTest {

	private Product book;
	private Product music;
	private Product chocolateBar;
	private Product importedChoc;
	private Product importedPerfume;
	private Product pills;
	
	@BeforeClass
	public static void oneTimeSetUp(){
		System.out.println("Testing Product");
		System.out.println("@BeforeClass - oneTimeSetup");
	}
	
	@Before 
	public void setUp(){
		System.out.println("@Before - Setup");
		int qty=1;
		book = new Product("book", 12.49, qty);
		music = new Product("music CD", 14.99, qty);
		chocolateBar= new Product("chocolate bar", .85, qty);
		importedChoc= new Product("imported box of chocolates", 10.00, qty);
		importedPerfume= new Product("imported bottle of perfume", 47.50, qty);
		pills= new Product("packet of headache pills", 9.75, qty);
	}
	
	@After
	public void tearDown(){
		 book=null;
		 music=null;
		 chocolateBar=null;
		 importedChoc=null;
		 importedPerfume=null;
		 pills=null;
	}
	
	@Test
	//Constructor
	public void testProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsImported() {
		System.out.println("Product is imported...");
		
		assertTrue(book.isImported(book.detail)==false);
		assertTrue(music.isImported(music.detail)==false);
		assertTrue(chocolateBar.isImported(chocolateBar.detail)==false);
		assertTrue(importedChoc.isImported(importedChoc.detail)==true);
		assertTrue(importedPerfume.isImported(importedPerfume.detail)==true);
		assertTrue(pills.isImported(pills.detail)==false);
	}

	@Test
	public void testGetItem() {
		System.out.println("Parse and find the item in the description...");
		importedChoc.detail=importedChoc.detail.substring(
				importedChoc.detail.indexOf("imported")+"imported".length()+1);
		importedPerfume.detail=importedPerfume.detail.substring(
				importedPerfume.detail.indexOf("imported")+"imported".length()+1);
	
		assertTrue(book.getItem(book.detail).equals("book"));
		assertTrue(music.getItem(music.detail).equals("music CD"));
		assertTrue(chocolateBar.getItem(chocolateBar.detail).equals("chocolate bar"));
		assertTrue(importedChoc.getItem(importedChoc.detail).equals("chocolate"));
		assertTrue(importedPerfume.getItem(importedPerfume.detail).equals("perfume"));
		assertTrue(pills.getItem(pills.detail).equals("headache pill"));
	}

}
