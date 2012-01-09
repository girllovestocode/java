import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/* 
 * Author: Helen Shin
 * Date: 10/27/2011
 * SequenceClient.Java
 * 
 */
/* 
 * TODO
 * SEQUENCE CLIENT
 * 
 */
public class SequenceClient {
	//GUI
	JFrame mainFrame;
	JPanel mainPanel;
	JPanel top;
	JPanel handPanel;
	JLabel messageLabel = new JLabel("");
	JButton discarded;
	JButton play;
	JButton[]myCards = new JButton[7];
	JPanel[] p=new JPanel[100]; //background
	String [] board=new String[100];
	JCheckBox [] handListener = new JCheckBox[7];

	private static Color lightblue = new Color(153,217,234);
	private static Color lightgreen = new Color(34,177,76);
	private static Color mycolor = lightblue;
	private static Color opponentcolor = lightgreen;

	Card [] myhand = new Card[7];
	//1 = occupied; 2=sequenced; 0=null;
	int []occupy = new int[100];
	int index;
	int hindex;
	String move;
	private static Deck deckOfCards;
	ArrayList checkboxList;
	ArrayList options;
	private static int PORT = 8901;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public SequenceClient(String serverAddress) throws Exception{
		//Setup Networking
		socket = new Socket(serverAddress, PORT);
		in = new BufferedReader(new InputStreamReader(
				socket.getInputStream(),"UTF-8"));
		out = new PrintWriter(socket.getOutputStream(), true);
		buildGUI();

		//set the board
		for (int i=0; i<100; i++){
			occupy[i]=0;
		}
	}
	/*
	 * Run the client as an application
	 * 1. Open Connection to Server
	 * 2. Start Thread
	 */
	public static void main(String[] args) throws Exception{

		while (true){
			String serverAddress = (args.length ==0) ? "localhost" : args[1] ;
			SequenceClient client = new SequenceClient(serverAddress);
			client.play();
			if(!client.wantsToPlayAgain()){
				break;
			}
		}//end while

	}//end main
	private boolean wantsToPlayAgain (){
		int response = JOptionPane.showConfirmDialog(mainFrame,
				"Want to play again?",
				"Sequence?",
				JOptionPane.YES_NO_OPTION);
		mainFrame.dispose();
		return response == JOptionPane.YES_OPTION;
	}

	/*
	 * Main thread: Listen for messages from Server
	 * 
	 */
	public void play() throws Exception{
		String response;
		String m; 
		String o;
		o="light green";	

		mycolor = lightblue;
		opponentcolor = lightgreen;

		handPanel.setVisible(false);
		play.setVisible(false);
		discarded.setVisible(false);
		messageLabel.setVisible(true);

		/* DECODE SERVER's messages*/
		try {
			//STARTING
			response = in.readLine();
			System.out.println("response: "+response);
			//WELCOME
			if(response.startsWith("WELCOME ")){
				char piece = response.charAt(8);
				if (piece =='A'){				
					mycolor = lightblue;
					opponentcolor = lightgreen;
					System.out.println("Player A: Blue");
					messageLabel.setText(response);
					messageLabel.setVisible(true);
				}
				else {
					mycolor = lightgreen;
					opponentcolor = lightblue;
					System.out.println("Player B: Green");
				}

				response = in.readLine();
				//SET Hand
				if(response.startsWith("INITIAL_DEAL")){
					for (int i=0; i<7; i++){
						String str = response.substring(12);
						setHand(i,str);	
						response = in.readLine();
					}
				}
			} //WELCOME

			//PLAY
			while (true){
				handPanel.setVisible(true);
				play.setVisible(true);
				discarded.setVisible(true);
				messageLabel.setVisible(true);
				response = in.readLine();
				System.out.println("response: "+response);



				// MY MOVE
				if(response.startsWith("VALID_MOVE")){
					int loc = Integer.parseInt(response.substring(11));
					//madeMove(loc);
					/*
					response = in.readLine();
					System.out.println("Line 156: response: "+response);
					 */

					p[loc].setBackground(mycolor);
					occupy[loc]=1;
					
					response = in.readLine();
					String card = response.substring(response.indexOf('C')+2);
					int hindex=Integer.parseInt(response.substring(response.indexOf('L')+2,response.indexOf('C')-1));
					System.out.println("Card: "+card + " hindex: "+hindex);
					setHand(hindex, card);	
					
					messageLabel.setText("Valid move, Opponent's Turn");
					play.setVisible(false);
					discarded.setVisible(false);
					/*
					if(response.startsWith("DEAL")){
						String card = response.substring(response.indexOf('C')+1);
						int hindex=Integer.parseInt(response.substring(response.indexOf('L')+1,response.indexOf('C')));
						setHand(hindex, card);	

					}
					*/
				}
				//DEAL single card
				else if (response.startsWith("OPPONENT_MOVED")){
					int loc = Integer.parseInt(response.substring(15));
					p[loc].setBackground(opponentcolor);
					messageLabel.setText("Opponent moved, Your turn");
					play.setVisible(true);
					discarded.setVisible(true);

				} else if (response.startsWith("VICTORY")) {
					messageLabel.setText("You win!");
					break;
				}  else if (response.startsWith("SEQUENCE")) {
					messageLabel.setText("SEQUENCE!");
					break;
				} else if (response.startsWith("DEFEAT")) {
					messageLabel.setText("You lose");
					break;
				} else if (response.startsWith("TIE")) {
					messageLabel.setText("You tied");
					break;
				} else if (response.startsWith("MESSAGE")) {
					messageLabel.setText(response.substring(8));
				}

			}
			out.println("QUIT");
		} finally{
			socket.close();
		}
	}

	public void setupBoard(){
		for(int i=2; i<10;i++){				
			board[i-1]="♠ "+i;
		}
		board[0]="♠♥♦♣"; board[10-1]="♠♥♦♣"; 	board[91-1]="♠♥♦♣"; board[100-1]="♠♥♦♣";
		int num=6;
		for(int i=11-1; i<15;i++){board[i]="♣ "+(num);	num--;;}
		board[21-1]="♣ 7"; board[31-1]="♣ 8"; board[41-1]="♣ 9"; board[51-1]="♣ 10"; board[61-1]="♣ Q";
		board[71-1]="♣ K"; board[81-1]="♣ A"; 
		board[16-1]="♥ A";
		board[17-1]="♥ K";
		board[18-1]="♥ Q";
		board[19-1]="♥ 10"; board[29-1]="♥ 9"; board[39-1]="♥ 8"; board[49-1]="♥ 7";
		board[59-1]="♥ 6";  board[69-1]="♥ 5"; board[79-1]="♥ 4"; board[89-1]="♥ 3";  board[88-1]="♥ 2"; 
		board[20-1]="♠ 10"; board[30-1]="♠ Q"; board[40-1]="♠ K"; board[50-1]="♠ A";
		board[22-1]="♠ A";	
		for(int i=23-1; i<29-1;i++){ board[i]="♦ "+(i-20); }
		board[38-1]="♦ 8"; board[48-1]="♦ 9"; board[58-1]="♦ 10"; board[68-1]="♦ Q"; board[78-1]="♦ K"; board[77-1]="♦ A"; 
		board[32-1]="♠ K"; board[42-1]="♠ Q"; board[52-1]="♠ 10"; board[62-1]="♠ 9"; board[72-1]="♠ 8"; board[82-1]="♠ 7"; 
		board[33-1]="♣ 6"; board[43-1]="♣ 7"; board[53-1]="♣ 8"; board[63-1]="♣ 9"; board[73-1]="♣ 10";
		board[34-1]="♣ 5"; board[35-1]="♣ 4"; board[36-1]="♣ 3"; board[37-1]="♣ 2";

		board[44-1]="♥ 6"; board[54-1]="♥ 7"; board[64-1]="♥ 8"; 
		board[45-1]="♥ 5"; board[46-1]="♥ 4"; board[47-1]="♥ A";
		board[55-1]="♥ 2"; board[56-1]="♥ 3"; board[57-1]="♥ K";
		board[65-1]="♥ 9"; board[66-1]="♥ 10";board[67-1]="♥ Q";

		board[74-1]="♣ Q"; board[75-1]="♣ K"; board[76-1]="♣ A"; 
		board[83-1]="♠ 6"; board[84-1]="♠ 5"; board[85-1]="♠ 4"; board[86-1]="♠ 3"; board[87-1]="♠ 2"; 
		board[60-1]="♦ 2"; board[70-1]="♦ 3"; board[80-1]="♦ 4"; board[90-1]="♦ 5";
		board[92-1]="♦ A"; board[93-1]="♦ K"; board[94-1]="♦ Q"; board[95-1]="♦ 10";
		board[96-1]="♦ 9"; board[97-1]="♦ 8"; board[98-1]="♦ 7"; board[99-1]="♦ 6";
	}

	public void buildGUI(){
		setupBoard();

		//Setup Frame
		mainFrame = new JFrame("Play Sequence!");
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		mainFrame.getContentPane().add(background);
		GridLayout grid = new GridLayout(10,10);
		mainPanel = new JPanel(grid);
		background.add(BorderLayout.CENTER, mainPanel);

		//MAKE GAMEBOARD
		checkboxList = new ArrayList();
		for(int i=0; i<100; i++){
			p[i]=new JPanel();
			JCheckBox c = new JCheckBox();
			JLabel l = new JLabel(board[i]);
			checkboxList.add(c);

			p[i].add(c);
			p[i].add(l);
			if (l.toString().contains("♥"))
				l.setForeground(Color.red);
			if (l.toString().contains("♦"))
				l.setForeground(Color.red);
			c.setSelected(false);
			//boardList.add(c);
			p[i].setBackground(Color.white);
			mainPanel.add(p[i]);
		} //end loop

		Font font = new Font(Font.SANS_SERIF,Font.BOLD, 20);
		messageLabel.setFont(font);
		messageLabel.setBackground(Color.white);

		//Hand
		handPanel = new JPanel();
		handPanel.setLayout(new BoxLayout(handPanel, BoxLayout.Y_AXIS));
		JLabel hand = new JLabel(" My Hand              ");
		handPanel.add(hand);
		deckOfCards= new Deck();
		deckOfCards.shuffle();
		//7 cards My Hand
		for (int i=0; i<myhand.length; i++){
			JPanel card = new JPanel();
			JCheckBox c = new JCheckBox();
			myCards[i] = new JButton();
			c.setSelected(false);
			handListener[i]=c;
			handPanel.add(c);
			card.add(c);
			card.add(myCards[i]);
			//listeners
			myCards[i].addActionListener(new CardListener());
			handPanel.add(card);			
		}

		//TOP BAR
		top = new JPanel();
		JLabel sequence = new JLabel(" ♠♥♦♣ SEQUENCE ♣♦♥♠   O.O WILD  O.- REMOVE      ");
		Font font2 = new Font(Font.MONOSPACED,Font.BOLD, 24);
		sequence.setFont(font2);
		top.add(sequence);
		play = new JButton();
		play.setText("Play");
		play.addActionListener(new PlayListener());
		top.add(play);
		discarded = new JButton("  Discarded Pile  ");
		top.add(discarded);
		mainFrame.getContentPane().add(BorderLayout.WEST,handPanel);
		mainFrame.getContentPane().add(BorderLayout.NORTH,top);
		mainFrame.getContentPane().add(BorderLayout.SOUTH,messageLabel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(0,0,900,600);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}// close buildGUI

	public void setHand(int i, String str){
		myCards[i].setText(str);
		if((myCards[i].toString().contains("♥")) || (myCards[i].toString().contains("♦")))
			myCards[i].setForeground(Color.RED);
		else 
			myCards[i].setForeground(Color.black);
	}
	public void moveBoard(){

	}


	public void selectCard(){
		//choose a card from MyCards[]

	}
	public boolean isOccupied(){

		//if not one eye
		//if not occupied already,
		//highlight the options of the two spots
		System.out.println(move);

		move=move.trim();
		options = new ArrayList();
		for(int i=0; i<100;i++){
			if(board[i].equals(move)){
				if((p[i].getBackground()==mycolor)||(p[i].getBackground()==opponentcolor))
					continue;
				options.add(i);
				p[i].setBackground(Color.yellow); //color of the person
			}
		}

		return false;
	}
	public void makeMove(){

	}
	public void madeMove(int loc){
		//color
		p[loc].setBackground(mycolor);
		occupy[loc]=1;
		messageLabel.setText("Valid move, Opponent's Turn");
		play.setVisible(false);
		discarded.setVisible(false);

	}
	/*
	 * PLAY Button was clicked - makeMove
	 */
	public class PlayListener implements ActionListener{
		public void actionPerformed(ActionEvent a){
			for(int i=0; i<checkboxList.size(); i++){
				JCheckBox jc = (JCheckBox)checkboxList.get(i);
				if(jc.isSelected()){
					p[i].setBackground(mycolor);
					index=i;
					System.out.println("MOVE " + index +" H "+ hindex);
					out.println("MOVE " + index + " H "+ hindex);
					jc.setSelected(false);
				}
			}

			//change highlighted colors
			for(int i=0; i<100; i++){
				if (p[i].getBackground()==Color.yellow)
					p[i].setBackground(Color.white);
			}

			//HAND
			for(int i=0; i<handListener.length; i++){
				JCheckBox jc=handListener[i];
				if(jc.isSelected()){
					hindex=i;
					//set discarded
					discarded.setText(myCards[hindex].getText());
					move = myCards[hindex].getText();
					myCards[hindex].setText(" ");
					jc.setSelected(false);
					messageLabel.setText("Opponent's turn");
				}
			} //for loop
			//show that it was discarded
			if ((discarded.getText().contains("♥")) || 
					(discarded.getText().contains("♦")))
				discarded.setForeground(Color.red);
			else discarded.setForeground(Color.black);
		}
	}

	/*
	 * Select MyHand[Card] Button to play
	 */

	public class CardListener implements ActionListener{
		public void actionPerformed(ActionEvent a){

			//click on card button to play
			for(int i=0; i<handListener.length; i++){
				JCheckBox jc=handListener[i];
				if(jc.isSelected()){
					hindex=i;
					move = myCards[hindex].getText();
					isOccupied();
					//moveBoard();
				}
			} //end for loop
		}//end actionperformed
	}
}