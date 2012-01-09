
public class Card {
	Suit suit;
	Numbers num;
	private boolean discarded;
	Card(){}
	Card(Suit s, Numbers n){
		suit=s; num=n; discarded=false;
	}
	Card(Card c){
		suit=c.suit; num=c.num; discarded=false;
	}
	
	public String toString(){
		return suit.toString()+ " "+ num.toString() + " " ;
	}
	public Suit getSuit (){
		return suit;
	}
	public Numbers getNum(){
		return num;
	}
	public boolean isDiscarded(){
		return discarded;
	}
	public void setDiscarded(boolean b){
		discarded=b;
	}
}
