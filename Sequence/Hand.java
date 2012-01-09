
public class Hand {
	Card[] cards;

	public Hand(){
		//for 2 payers 7 cards each
		cards = new Card[7];
	}

	public void setCard(int i, Card c){
		cards[i]=c;
	}

	public String toString(){
		String str = "";

		for (int i=0; i<cards.length; i++){
			str += "\t"+(i+1) + ": ";
			str += cards[i];
			if(cards[i].isDiscarded() == true)
				str += " Discarded";
			str+="\n";
		}
		return str;
	}

	public boolean isPair(){
		//to see if two are same cards 
		//(match suit and number)
		int counter=0;

		//Put each cards numeric value into array
		String[] values = new String[7];
		String[] suits= new String[7];

		for(int i=0; i<cards.length; i++){
			values[i]=cards[i].getNum().toString();
			suits[i]=cards[i].getSuit().toString();
		}

		//Loop through the values
		//Compare each value to all values
		//If exactly two matches are made, then true
		for (int i=0; i<values.length; i++){	
			for (int j=0; j<cards.length; j++){
				if(
						(values[i].equals(cards[j].getNum().toString()))
						&&
						(suits[i].equals(cards[j].getSuit().toString()))){
					counter++;
					if (counter==2){
						System.out.println("Pair: "+cards[j].getSuit()+" "+ cards[j].getNum());
						return true;
					}
				}
			}
			counter=0;
		}
		return false;
	}

	public void discardCard(int index){
		if(cards[index].isDiscarded()==false)
			cards[index].setDiscarded(true);
		else cards[index].setDiscarded(false);
	}

	public void replaceDiscarded(Deck deck){
		for(int i=0; i<cards.length;i++){
			if(cards[i].isDiscarded()==true)
				setCard(i,deck.dealCard());
		}
	}

	public boolean isThreeOfAKind() {
		//Match Suit (3)
		String[] values = new String[7];
		int counter = 0;

		for(int i = 0; i < cards.length; i++){
			values[i] = cards[i].getSuit().toString();
		}

		for(int x = 0; x < values.length; x++){
			for(int y = 0; y < cards.length; y++){
				if(values[x].equals(cards[y].getSuit().toString()))
					counter++;
				if(counter == 3)
					return true;

			}
			counter = 0;
		}

		return false;
	}	public boolean isFourOfAKind() {
		String[] values = new String[7];
		int counter = 0;

		for(int i = 0; i < cards.length; i++){
			values[i] = cards[i].getSuit().toString();
		}

		//Same process as isPair(), except return true for 4 matches
		for(int x = 0; x < values.length; x++){
			for(int y = 0; y < cards.length; y++){
				if(values[x].equals(cards[y].getSuit().toString()))
					counter++;
				if(counter == 4)
					return true;

			}
			counter = 0;
		}

		return false;
	}



}
