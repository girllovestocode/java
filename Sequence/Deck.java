
public class Deck {
	//Sequence plays with two decks
	Card[] deck1;
	Card[] deck2;
	Card[] deck;
	
	private int cardsUsed;

	public Deck(){
		deck1=new Card[52];
		deck2=new Card[52];
		deck=new Card[52+52];

		for(int i=0; i<deck1.length; i++){
			deck1[i]=new Card(new Suit(i/13+1),new Numbers(i%13+1));			
			deck2[i]=new Card(new Suit(i/13+1),new Numbers(i%13+1));				
		}
	
		/*
		 * DEBUG: PRINT OUT THE DECK
		System.out.println("deck");
		for(int i=0; i<deck1.length; i++){
			System.out.println("i: "+i+" "+deck1[i].toString());
		}
		*/
		
		//one eyed jack
		deck1[10]=new Card(new Suit(-1),new Numbers(11));
		deck1[23]=new Card(new Suit(-1),new Numbers(11));
		deck2[10]=new Card(new Suit(-1),new Numbers(11));
		deck2[23]=new Card(new Suit(-1),new Numbers(11));

		//two eyed jack
		deck1[36]=new Card(new Suit(-2),new Numbers(11));
		deck1[49]=new Card(new Suit(-2),new Numbers(11));
		deck2[36]=new Card(new Suit(-2),new Numbers(11));
		deck2[49]=new Card(new Suit(-2),new Numbers(11));

		for(int i=0; i<deck1.length; i++){
			deck[i]=deck1[i];
		}
		for(int i=0; i<deck2.length;i++){
			deck[deck1.length+i]=deck2[i];
		}

		
		cardsUsed=0;
	}

	public void shuffle(){
		for(int i=0; i<deck.length; i++){
			int k=(int)(Math.random()*52*2);
			Card t=deck[i]; 
			deck[i]=deck[k];
			deck[k]=t;
		}

		for(int i=0; i<deck.length;i++){
			deck[i].setDiscarded(false);
		}
		cardsUsed=0;
	}

	public int cardsLeft(){
		//returns the number of cards that are still left to play in the deck
		return 52+52-cardsUsed;
	}
	public Card dealCard(){
		//Deals one card from the deck and returns it
		if(cardsUsed == 52+52){
			shuffle();
		}
		cardsUsed++;
		return deck[cardsUsed-1];
	}
	public String toString(){
		String t="";
		for(int i=0; i<104; i++)
			if ((i+1)%5 ==0)
				t=t+deck[i]+"\n";
			else t=t+deck[i];
			return t;
		}
	

}
