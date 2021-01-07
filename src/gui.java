import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import board.*;
import pieces.*;
import logic.*;
import move.Move;

class gui extends JFrame {
	public static void main(String[] args) throws BadLocationException {
		new gui();
	}
	int prevX = -1;
	int prevY = -1;
	int index; 
	int move_Index;
	Board board;
	boolean isClassicGame=true;
	private ArrayList<Board> gameHistory=new ArrayList<Board>();
	private ArrayList<Move> moves=new ArrayList<Move>();
	boolean chosen = false;
	boolean turn = true;
	JTextPane pane;
	JScrollPane scrollPane;
	Document doc;
	SimpleAttributeSet attributeSet;
	 
	/**
	 * constructor for gui class will Create a Chinese chess game
	 * @throws BadLocationException 
	 */
	gui() throws BadLocationException {
		JTextField name1 = new JTextField();
		JTextField name2 = new JTextField();
		Object[] message = {
		    "Player1's name:", name1,
		    "Player2's name", name2
		};
		Container cp = this.getContentPane();  
        pane = new JTextPane();  
        attributeSet = new SimpleAttributeSet();  
        StyleConstants.setBold(attributeSet, true);   
        pane.setCharacterAttributes(attributeSet, true);  
        attributeSet = new SimpleAttributeSet();  
        StyleConstants.setForeground(attributeSet, Color.black);
        doc = pane.getStyledDocument();  
        doc.insertString(doc.getLength(), "Chinese Chess Game!\n", attributeSet);  
        scrollPane = new JScrollPane(pane);
        pane.setEditable(false);
		JOptionPane.showConfirmDialog(null, message, "Enter user name", JOptionPane.OK_CANCEL_OPTION);
		this.setTitle(name1.getText()+" VS "+name2.getText());
		this.setResizable(false);	
		final JMenuBar menuBar=new JMenuBar();	
		this.pupulateMenubar(menuBar);
		this.setJMenuBar(menuBar);
		this.setLayout(new BorderLayout());
		this.setLocation(600, 120);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp.add(scrollPane, BorderLayout.EAST);  
		this.setSize(1300, 1000);
		this.setVisible(true);
		this.addMouseListener(new Monitor());
		this.board = new Board();
		this.board.constructDefaultBoard();
		gameHistory.add(board.CopyChessBoard());
		index=0;		
		move_Index=0;
	}
	
	//create the menu bar for the game
	private void pupulateMenubar(final JMenuBar menuBar) {
		menuBar.add(createOperationMenu());		
	}

	private JMenu createOperationMenu() {
		final JMenu fileMenu=new JMenu("Operation");
		final JMenuItem classicGame=new JMenuItem("start a new classic chess game");
		classicGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isClassicGame=true;
				restart();
				repaint();
			}
		});
		final JMenuItem customGame=new JMenuItem("start a new blind chess game");
		customGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isClassicGame=false;
				restart();
				repaint();
			}
		});
		final JMenuItem undo=new JMenuItem("undo");
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index==0 || !isClassicGame) {
					return;
				}
				index=index-1;
				move_Index=move_Index-1;
				board=gameHistory.get(index).CopyChessBoard();
				chosen=false;
				turn=!turn;
				prevX = -1;
				prevY = -1;
				repaint();
			}
		});
		final JMenuItem redo=new JMenuItem("redo");
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index+1>=gameHistory.size() || !isClassicGame) {
					return;
				}
				index=index+1;
				move_Index=move_Index+1;
				board=gameHistory.get(index).CopyChessBoard();
				chosen=false;
				turn=!turn;
				prevX = -1;
				prevY = -1;
				repaint();
			}
		});
		fileMenu.add(classicGame);
		fileMenu.add(customGame);
		fileMenu.add(undo);
		fileMenu.add(redo);
		return fileMenu;		
	}

	//restart
	private void restart() {
		chosen=false;
		turn=true;
		prevX = -1;
		prevY = -1;
		board = new Board();
		if (isClassicGame) {
			board.constructDefaultBoard();
		}
		else {
			board.constructBlindChessBoard();
		}
		gameHistory=new ArrayList<Board>();
		gameHistory.add(board.CopyChessBoard());
		index=0;
		move_Index=0;
		moves=new ArrayList<Move>();
		repaint();
	}

	/**
	 * function to draw the board of a Chinese chess game.
	 * 
	 * @param g the graphics to be drawn.
	 * @return void.
	 */
	public void drawBoard(Graphics g) {
		int boarderDist = 60;
		g.setColor(Color.orange);
		int borderX=400;
		int borderY=44;
		g.fillRect(borderX, borderY, 760, 900);
		// draw rows and columns in the board.
		for (int column = 0; column < 8; column++) {
			for (int row = 0; row < 9; row++) {
				if (row == 4) {
					// draw the river
					g.draw3DRect(boarderDist+borderX, 320 + boarderDist+borderY, 640, 80, false);
					continue;
				}
				g.draw3DRect(boarderDist + 80 * column+borderX, boarderDist + 80 * row+borderY, 80, 80, true);
			}
		}
		g.setColor(Color.black);
		g.setFont(new Font("楷体", Font.BOLD, 50));
		g.drawString("楚河", 80 + boarderDist+borderX, 380 + boarderDist+borderY);
		g.drawString("汉界", 420 + boarderDist+borderX, 380 + boarderDist+borderY);
		g.setColor(Color.black);
		for (int column = 0; column < 8; column++) {
			g.drawLine(boarderDist + 80 * column+borderX, boarderDist+borderY, boarderDist + 80 * column+borderX, 320 + boarderDist+borderY);
			g.drawLine(boarderDist + 80 * column+borderX, 400 + boarderDist+borderY, boarderDist + 80 * column+borderX, 720 + boarderDist+borderY);
		}

		for (int row = 0; row < 9; row++) {
			g.drawLine(boarderDist+borderX, boarderDist + 80 * row+borderY, 640 + boarderDist+borderX, boarderDist + 80 * row+borderY);
		}
		// draw the palace
		g.drawLine(240 + boarderDist+borderX, boarderDist+borderY, 400 + boarderDist+borderX, 160 + boarderDist+borderY);
		g.drawLine(400 + boarderDist+borderX, boarderDist+borderY, 240 + boarderDist+borderX, 160 + boarderDist+borderY);
		g.drawLine(240 + boarderDist+borderX, 720 + boarderDist+borderY, 400 + boarderDist+borderX, 560 + boarderDist+borderY);
		g.drawLine(400 + boarderDist+borderX, 720 + boarderDist+borderY, 240 + boarderDist+borderX, 560 + boarderDist+borderY);
	}

	//draw valid moves
	public void drawValidMoves(Graphics g, Piece piece) {
		int borderX=400;
		int borderY=44;
		ArrayList<Point> validMoves=piece.getValidMoves();
		for (int counter = 0; counter < validMoves.size(); counter++) { 		      
	          Point validMove=validMoves.get(counter);
	          g.setColor(Color.green);
	          g.fillOval(60 + (8 - (int)validMove.getX()) * 80 - 5+borderX, 60 + (9 - (int)validMove.getY()) * 80 - 5+borderY, 10, 10);
	      }   
	}
	
	//draw the number of killed pieces in the game. 
	public void drawKilledPieces(Graphics g) {
		myPair[] ramaining_pieces=board.get_remaining_pieces();
		int borderX=30;
		int borderY=44+80;
		Board temp_board=new Board();
		ArrayList<Piece> temp_pieces=temp_board.constructTempPieces();
		int counter=0;
		g.setColor(Color.black);
		g.drawString("Killed Pieces", 30, 120);
		for (int j = 3; j < 10; j++) {
			for (int i = 0; i < 2; i++) {
				int width=0;
				int piece_no=2;
				if (i==1)
					width=90;
				Piece p=temp_pieces.get(counter);
				if (p instanceof General)
					piece_no=1;
				if (p instanceof Soldier)
					piece_no=5;
				if (i==0)
					piece_no=piece_no-ramaining_pieces[j-3].get_red();
				else
					piece_no=piece_no-ramaining_pieces[j-3].get_black();
				g.setColor(Color.yellow);
				g.fillOval(45 + (i) * 80 - 32+borderX+width, 60 + (9 - j) * 80 - 32+borderY, 64, 64);		
				if (p.getPlayer())
					g.setColor(Color.red);
				else
					g.setColor(Color.black);
				g.drawString(p.getName(), 45 + (i) * 80 - 32 + 8+borderX+width, 60 + (9 - j) * 80 - 32 + 48+borderY);
				g.setColor(Color.black);
				g.drawString(":"+Integer.toString(piece_no), 45 + (i) * 80 - 32 + 8+borderX+60+width, 60 + (9 - j) * 80 - 32 + 48+borderY);
				counter++;
			}
		}
	}
	
	//function to draw pieces in the game.
	public void drawPieces(Graphics g) {
		int borderX=400;
		int borderY=44;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
				Piece p = board.getBoard()[i][j];
				if (p == null)
					continue;
				if (i == prevX && j == prevY)
					g.setColor(Color.white);
				else
					g.setColor(Color.yellow);
				g.fillOval(60 + (8 - p.getX()) * 80 - 32+borderX, 60 + (9 - p.getY()) * 80 - 32+borderY, 64, 64);
				if (p.getPlayer())
					g.setColor(Color.red);
				else
					g.setColor(Color.black);
				if (p.is_visible) {
					g.drawString(p.getName(), 60 + (8 - p.getX()) * 80 - 32 + 8+borderX, 60 + (9 - p.getY()) * 80 - 32 + 48+borderY);
				}
				else
					g.drawString(" ?", 60 + (8 - p.getX()) * 80 - 32 + 8+borderX, 60 + (9 - p.getY()) * 80 - 32 + 48+borderY);
			}
		}
	}

	/**
	 * function to draw the the Chinese chess board as well as pieces
	 * @param g the graphics to be drawn.          
	 * @return void.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		this.drawBoard(g);
		this.drawPieces(g);
		this.drawKilledPieces(g);
		if (prevX!=-1 && prevY!=-1) 
			this.drawValidMoves(g, board.getBoard()[prevX][prevY]);
		try {
			doc.remove(0, doc.getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		StyleConstants.setForeground(attributeSet, Color.black);
		try {
			doc.insertString(doc.getLength(), "    红方        黑方\n", attributeSet);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		for (int i =0;i<move_Index;i++){
			String newLine="";
			if (i%2==1)
				newLine="\n";
			if (i>1 && moves.get(i).get_flag()) {
				StyleConstants.setForeground(attributeSet, Color.red);
		        try {
					doc.insertString(doc.getLength(), moves.get(i).get_move()+newLine, attributeSet);
				} catch (BadLocationException e) {
					e.printStackTrace();
				} 
			}
			else {
				StyleConstants.setForeground(attributeSet, Color.black);
		        try {
					doc.insertString(doc.getLength(), moves.get(i).get_move()+newLine, attributeSet);
				} catch (BadLocationException e) {
					e.printStackTrace();
				} 
			}
	    }
	}

	/**
	 * function to move pieces in the game Nothing will happen if click on the 
	 * position with no piece.
	 * @param point the position of the piece to be moved.
	 * @return void.
	 */
	public void movePoint(Point point) {
		int borderX=400;
		int borderY=44;
		int x = 8 - (int) (point.getX() - 28-borderX) / 80;
		int y = 9 - (int) (point.getY() - 28-borderY) / 80;
		if (x>9 || x<0 || y>9 || y<0) {
			return;
		}
		if (chosen) {
			Piece p = board.getBoard()[prevX][prevY];
			//moving rules
			if (!p.canMove(x, y)) {
				JOptionPane.showMessageDialog(this, "invalid move", "invalid move!", 0);
				return;
			}
			Move current_move;
			if (board.getBoard()[x][y]==null)
				current_move=new Move(false,p.move_to_string(x, y));
			else
				current_move=new Move(true,p.move_to_string(x, y));
			board.getBoard()[x][y] = p;
			p.setPosition(x, y);
			board.getBoard()[prevX][prevY] = null;
			chosen = false;
			turn=!turn;
			prevX=-1;
			prevY=-1;
			if (index+1<gameHistory.size()) {
				ArrayList<Board> newGameHistory=new ArrayList<Board>();
				ArrayList<Move> newMoves=new ArrayList<Move>();
				for (int counter = 0; counter < index+1; counter++) { 		      
					newGameHistory.add(gameHistory.get(counter));
			    }   
				for (int counter = 0; counter < move_Index; counter++) { 		      
					newMoves.add(moves.get(counter));
			    }   
				
				gameHistory=newGameHistory;
				moves=newMoves;
			}
			gameHistory.add(board.CopyChessBoard());
			moves.add(current_move);
			index=index+1;
			move_Index=move_Index+1;
			//logic
			Logic logic=new Logic(board,turn);
			if (logic.gameState()==1) {
				JOptionPane.showMessageDialog(this, "player1 wins", "player1 wins!", 0);
				restart();
				return;
			}
			else if (logic.gameState()==0) {
				JOptionPane.showMessageDialog(this, "player2 wins", "player2 wins!", 0);
				restart();
				return;
			}
			else {
				return;
			}
		}
		chosen = true;
		//empty tile
		Piece p = board.getBoard()[x][y];
		if (p == null) {
			prevX = -1;
			prevY = -1;
			chosen = false;
			return;
		}
		//turn
		if (p.getPlayer()!=turn) {
			JOptionPane.showMessageDialog(this, "not your turn", "not your turn!", 0);
			chosen=false;
			prevX=-1;
			prevY=-1;
			return;
		}
		p.is_visible=true;
		prevX = x;
		prevY = y;
		boolean is_stale=true;
		for (int i=0; i<9; i++) {
			for (int j=0; j<10; j++) {
				if (p.canMove(i, j))
					is_stale=false;
			}
		}
		if (is_stale) {
			if (p.getPlayer())
				JOptionPane.showMessageDialog(this, "player2 wins", "player2 wins!", 0);
			else
				JOptionPane.showMessageDialog(this, "player1 wins", "player1 wins!", 0);
			restart();
			repaint();
			return;
		}
	}
	class Monitor extends MouseAdapter {
		/**
		 * function to catch the click actions of the player
		 * @param e the mouse event catched.
		 * @return void.
		 */
		public void mouseReleased(MouseEvent e) {
			JFrame f = (JFrame) e.getSource();
				((gui) f).movePoint(new Point(e.getX(), e.getY()));
				f.repaint(); 
		}
	}
}