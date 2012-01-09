/*
 * Date: 11/1/2011
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SequenceServer {

	public static void main(String args[]) throws Exception{
		ServerSocket listener = new ServerSocket(8901);
		System.out.println(" Sequence Server is Running");
		try {
			while (true){
				Game game = new Game();
				Game.Player playerA = game.new Player(listener.accept(), 'A');
				Game.Player playerB = game.new Player(listener.accept(), 'B');
				playerA.setOpponent(playerB);
				playerB.setOpponent(playerA);
				game.currentPlayer = playerA;
				playerA.start();
				playerB.start();
			}

		} finally {
			listener.close();
		}
	}
}

class Game{
	private Player [] board = new Player[100];
	Player currentPlayer;
	private static Deck deck = new Deck();
	public int _index(int index){
		//board[index]=1;
		return index;
	}

	public boolean _diagonalPos(int mymoves[]){
		//save the starting location index
		int start=-1;
		int x=40; 

		for (int i=0; i<5; i++){
			if ((mymoves[x+i]==1) && (mymoves[x+i-9]==1)&&(mymoves[x+i-9-9]==1) && (mymoves[x+i-9-9-9]==1)&&(mymoves[x+i-9-9-9-9]==1)){
				start=x+i;
				_index(start);

				return true;							
			} else if ((mymoves[10+x+i]==1) && (mymoves[10+x+i-9]==1)&&(mymoves[10+x+i-9-9]==1) && (mymoves[10+x+i-9-9-9]==1)&&(mymoves[10+x+i-9-9-9-9]==1)){
				start=10+x+i;
				_index(start);
				return true;							
			}else if ((mymoves[20+x+i]==1) && (mymoves[20+x+i-9]==1)&&(mymoves[20+x+i-9-9]==1) && (mymoves[20+x+i-9-9-9]==1)&&(mymoves[20+x+i-9-9-9-9]==1)){
				start=20+x+i;
				_index(start);
				return true;							
			}else if ((mymoves[30+x+i]==1) && (mymoves[30+x+i-9]==1)&&(mymoves[30+x+i-9-9]==1) && (mymoves[30+x+i-9-9-9]==1)&&(mymoves[30+x+i-9-9-9-9]==1)){
				start=30+x+i;
				_index(start);
				return true;							
			}else if ((mymoves[40+x+i]==1) && (mymoves[40+x+i-9]==1)&&(mymoves[40+x+i-9-9]==1) && (mymoves[40+x+i-9-9-9]==1)&&(mymoves[40+x+i-9-9-9-9]==1)){
				start=40+x+i;
				_index(start);
				return true;							
			}else if ((mymoves[50+x+i]==1) && (mymoves[50+x+i-9]==1)&&(mymoves[50+x+i-9-9]==1) && (mymoves[50+x+i-9-9-9]==1)&&(mymoves[50+x+i-9-9-9-9]==1)){
				start=50+x+i;
				_index(start);
				return true;							
			}

		}

		return false;
	}
	public boolean _diagonalNeg(int mymoves[]){
		//save the starting location index
		int start=-1;
		int x=0; 

		for (int i=0; i<5; i++){
			if ((mymoves[x+i]==1) && (mymoves[x+i+11]==1)&&(mymoves[x+i+11+11]==1) && (mymoves[x+i+11+11+11]==1)&&(mymoves[x+i+11+11+11+11]==1)){
				start=x+i;
				_index(start);
				return true;							
			} else if ((mymoves[10+x+i]==1) && (mymoves[10+x+i+11]==1)&&(mymoves[10+x+i+11+11]==1) && (mymoves[10+x+i+11+11+11]==1)&&(mymoves[10+x+i+11+11+11+11]==1)){
				start=10+x+i;
				_index(start);
				return true;							
			}else if ((mymoves[20+x+i]==1) && (mymoves[20+x+i+11]==1)&&(mymoves[20+x+i+11+11]==1) && (mymoves[20+x+i+11+11+11]==1)&&(mymoves[20+x+i+11+11+11+11]==1)){
				start=20+x+i;
				_index(start);
				return true;							
			}else if ((mymoves[30+x+i]==1) && (mymoves[30+x+i+11]==1)&&(mymoves[30+x+i+11+11]==1) && (mymoves[30+x+i+11+11+11]==1)&&(mymoves[30+x+i+11+11+11+11]==1)){
				start=30+x+i;
				_index(start);
				return true;							
			}else if ((mymoves[40+x+i]==1) && (mymoves[40+x+i+11]==1)&&(mymoves[40+x+i+11+11]==1) && (mymoves[40+x+i+11+11+11]==1)&&(mymoves[40+x+i+11+11+11+11]==1)){
				start=40+x+i;
				_index(start);
				return true;							
			}else if ((mymoves[50+x+i]==1) && (mymoves[50+x+i+11]==1)&&(mymoves[50+x+i+11+11]==1) && (mymoves[50+x+i+11+11+11]==1)&&(mymoves[50+x+i+11+11+11+11]==1)){
				start=50+x+i;
				_index(start);
				return true;							
			}
		}
		return false;
	}
	public boolean _across(int mymoves[]){
		//save the starting location index
		int start=-1;
		int x=0;
		while(x<100){

			for (int i=0; i<5; i++){
				if ((mymoves[x+i]==1) && (mymoves[x+i+1]==1)&&(mymoves[x+i+1+1]==1) && (mymoves[x+i+1+1+1]==1)&&(mymoves[x+i+1+1+1+1]==1)){
					start=i;
					_index(start);
					return true;							
				}
			}
			x+=10;
		}
		return false;
	}
	public boolean _up(int mymoves[]){
		//save the starting location index
		int start=-1;
		for (int i=0; i<59; i++){
			if ((mymoves[i]==1) && (mymoves[i+10]==1)&&(mymoves[i+10+10]==1) && (mymoves[i+10+10+10]==1)&&(mymoves[i+10+10+10+10]==1)){
				start=i;
				_index(start);
				return true;							
			}
		}
		return false;
	}
	/*
	 * Returns whether there is a winner  
	 */	
	public boolean hasWinner(Player player){
		//two sequence of one player
		if (player.count == 2) 
			return true;
		return false;
	}

	public boolean isSequence(Player player){
		//duplicates....
		if (player.count==0){
			if (( _across(player.moves)) || (_diagonalNeg(player.moves)) || _diagonalPos(player.moves) || _up(player.moves) ){
				player.count=1;
				
				return true; 
			}
		} else {
			//second sequence should be distinctive 
			// or be part from the first one
			return false;
		}

		return false;
	}

	//returns whether there are no more empty squares
	public boolean boardFilledUp(){
		for(int i=0; i<board.length; i++){
			if(board[i] == null){
				return false;
			}
		}
		return true;
	}


	//also update the board status
	public synchronized boolean legalMove(int location, Player player){

		//cant put cards in the wilds (4 free spots)
		if (	(location == 0)  ||
				(location == 90) ||
				(location == 9)  ||
				(location == 99) 
				)
			return false;

		System.out.println("last move: "+player.lastLocation);
		if (player == currentPlayer && board[location]==null){
			board[location] = currentPlayer;
			currentPlayer = currentPlayer.opponent;
			currentPlayer.otherPlayerMoved(location);
			return true;
		}
		//if sequence locked


		//if one eyed jack
		//remove
		//if two eyed jack
		//add



		//add wherever otherwise if untaken
		return false;
	}


	/*
	 * Class for helper threads 
	 * A Player is identified by a color
	 * For communication with the client 
	 * the player has a socket with input and output streams
	 * use text to communicate the status of game
	 */
	class Player extends Thread{
		char piece;
		Player opponent;
		Socket socket;
		BufferedReader input;
		PrintWriter output;
		PrintStream out;
		String lastMove; 
		int lastLocation;
		int [] moves = new int [100]; //1 if occupied; 0 not occupied;
		int count;
		Hand hand;

		/*
		 * Constructor
		 * initialize stream fields, 
		 * display welcome messages
		 */
		public Player(Socket socket, char piece){
			this.socket = socket;
			this.piece = piece;
			this.count = 0; 
			//set the 4 wild spaces as occupied
			moves[0]=1; //board[0]=1;
			moves[99]=1; //board[99]=1;
			moves[9]=1; //board[9]=1;
			moves[90]=1; //board[90]=1;

			//DEAL 7 CARDS to hand
			this.hand = new Hand();
			deck.shuffle();
			for(int i=0; i<hand.cards.length; i++){
				this.hand.setCard(i, deck.dealCard());
			}
			try{
				//INITIALIZE
				input = new BufferedReader(
						new InputStreamReader(socket.getInputStream(),"UTF-8"));
				output = new PrintWriter(socket.getOutputStream(), true);
				//use out for the symbols (less efficient for others)
				out = new PrintStream(socket.getOutputStream(), true,"UTF8");

				//WELCOME
				output.println("WELCOME " + piece);
				//DEAL CARDS
				for(int i=0; i<hand.cards.length; i++){
					out.println("INITIAL_DEAL "+this.hand.cards[i].toString());
				}
				output.println("MESSAGE Welcome! Wait for opponent to connect");

			} catch (IOException e){
				System.out.println("No opponent player: " + e);
			}
		} // end the constructor

		/*
		 * Set opponent
		 */
		public void setOpponent(Player opponent){
			this.opponent = opponent;
		}

		/*
		 * Handles the otherPlayerMoved message
		 */
		public void otherPlayerMoved(int location){
			output.println("OPPONENT_MOVED " + location);
			if(hasWinner(currentPlayer) || hasWinner(this.opponent))
				output.println("DEFEAT");
			if(boardFilledUp())
				output.println( "TIE ");
		}

		/*
		 * RUN	
		 */
		public void run(){
			//the thread is only started after connection

			try{

				//tell the first player that it is her turn
				if(piece == 'A'){
					output.println("MESSAGE You are blue. Your turn");

				} else {output.println("MESSAGE You are green. Blue's turn");}
				//Repeatedly get commands from Clients & process
				while(true){
					String command = input.readLine();

					if(command.startsWith("MOVE")){		
						int location = Integer.parseInt(command.substring(5,command.indexOf('H')-1));
						this.lastLocation = location;
						int hindex = Integer.parseInt(command.substring(command.indexOf('H')+2));

						System.out.println("hindex : "+hindex);
						//VALID MOVE?
						if(legalMove(location, this)){
							output.println("VALID_MOVE "+location);
							this.hand.setCard(hindex, deck.dealCard());
							
							String str = this.hand.cards[hindex].toString();
							
							out.println("DEAL "+hindex + " C "+str);
							board[location] = currentPlayer;
							currentPlayer.moves[location]=1;

							output.println(isSequence(currentPlayer) ? "SEQUENCE" : "");
							//output.println(hasWinner(currentPlayer) ? "VICTORY" :
								//boardFilledUp() ? "TIE" : "");

						}		 else {
							output.println("MESSAGE ILLEGAL MOVE");
						}
						
					}



					//REMOVE FOR ONE EYED JACK
					else if(command.startsWith("REMOVE")){

					}

					else if (command.startsWith("QUIT")){
						return;
					}
				}
			} catch(IOException e){
				System.out.println("Player died: " + e);
			} finally{
				try{
					socket.close();
				} catch(IOException e){}
			}
		} //end run

	} //end class Player
}
