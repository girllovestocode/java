import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Knit {
	public enum Term {KNIT, PURL}
	boolean odd;
	int co; 
	int rows;
	int start;
	int end;
	String [] row;
	String[][] pattern;
	int x, y;
	public static void main(String[] args) throws IOException, InterruptedException{
		Knit k=new Knit();
		k.x=0; k.y=0;

		//FILE INPUT
		Scanner in=null;
		FileReader reader= new FileReader("heart.txt");
		in= new Scanner(reader);
		String c;
		c=in.next();


		if (c.startsWith("CO")){
			k.co = in.nextInt();
			k.printer("Cast On "+k.co+" ");
		}
		k.x = k.co;
		c=in.next();

		if (c.startsWith("R")){
			k.rows = in.nextInt();
			k.printer(k.rows+" Rows ");
		}
		k.y = k.rows;
		k.pattern = new String[k.y][k.x];

		if (k.y%2!=0) k.odd=true; 

		for (int row = k.y-1; row>=0; row--){
			if(k.odd){
				for(int column = k.x-1; column>=0; column--){
					//k.row[column] = in.next();
					k.pattern[row][column] = in.next();

				}
				k.odd = false;
			}//end odd
			else{
				for (int column = 0; column<k.x; column++){
					//String temp = in.next();
					k.pattern[row][column] = in.next();
					k.odd = true;
				}
			}//end even
		}//end for



		for(int i=0; i<k.y; i++){
			for (int j=0; j<k.x; j++){
				k.printer(k.pattern[i][j]);
			}
			k.printer("\n");
		}

		k.printer("\n flip k with p \n");

		for(int i=0; i<k.y; i++){
			if (i%2==1){
				for (int j=0; j<k.x; j++){
					if (k.pattern[i][j].equals("k"))
						k.pattern[i][j]="p";
					else k.pattern[i][j]="k";
				}
			}
		}
		for(int i=0; i<k.y; i++){
			for (int j=0; j<k.x; j++){
				k.printer(k.pattern[i][j]);
			}
			k.printer("\n");
		}		
		


		in.close(); 

	}

	public void printer(String x){
		System.out.print(x);
	}

}
