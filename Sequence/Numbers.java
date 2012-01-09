//Holds the numeric value of each card

public class Numbers {
	int num;
	
	//Constructor
	Numbers(int i){
		num=i;
	}
	
	public String toString(){
		if(num>1 && num<11)
			return String.valueOf(num);
		else if(num==1) return "A";
		else if(num==11) return "J";
		else if(num==12) return "Q";
		else if(num==13) return "K";
		else return "error";
	}
	
	public int getValue(){
		return num;
	}
}
