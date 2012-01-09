class Suit {
	static final int CLUBS = 1;
	static final int DIAMONDS = 2;
	static final int HEARTS = 3;
	static final int SPADES = 4;
	static final int ONEEYE= -1;
	static final int TWOEYE= -2;
	
	int suitValue;    
	
	Suit(int i) { suitValue = i; }

	public String toString() {
		switch (suitValue) {     
		case CLUBS: return "♣";       
		case DIAMONDS: return "♦";
		case HEARTS: return "♥";
		case SPADES: return "♠";
		case ONEEYE: return "O.-";
		case TWOEYE: return "O.O";
		
		default: return "error";
		}
	}
}
