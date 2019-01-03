package quiz_card;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
/*
 * Zachary Moncur
 * 1/3/19
 * Quiz card builder
 * 
 * This class will allow the user to create a set
 * of flash cards and save them for future use.
 * To be used with the Flash_Card player class
 */
	public class Flash_Card_Builder {
	private JTextArea question;
	private JTextArea answer;
	private ArrayList<Flash_Card> cardList;
	private JFrame frame;

	public static void main(String[] args) {
		Flash_Card_Builder builder = new Flash_Card_Builder();
		builder.go();
	}

	public void go() {
	// Building the main GUI
		frame = new JFrame("Flash Card Builder");
		JPanel mainPanel = new JPanel();
		Font bigFont = new Font("comicsans",Font.BOLD,24);
		
	// Building the question area
		question = new JTextArea(6,20);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setFont(bigFont);
		
	// Building a scroll wheel for the questions
		JScrollPane qScroller = new JScrollPane(question);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
	// Building the answer area
		answer = new JTextArea(6,20);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(bigFont);
		
	// Building a scroll wheel for the answers
		JScrollPane aScroller = new JScrollPane(answer);
		aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
	// Creating the "Next Card" feature
		JButton nextButton = new JButton("Next Card");
		
	// Card array containing the card list
		cardList = new ArrayList<Flash_Card>();
		
	// Adding the labels
		JLabel qLabel = new JLabel("Question: ");
		JLabel aLabel = new JLabel("Answer: ");
		
	// Adding things to the panel
		mainPanel.add(qLabel);
		mainPanel.add(qScroller);
		mainPanel.add(aLabel);
		mainPanel.add(aScroller);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new NextCardListener());
		
	// Creating a menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
	
	// Adding save and new menu items
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		newMenuItem.addActionListener(new NewMenuListener());
		saveMenuItem.addActionListener(new SaveMenuListener());
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem); 
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		
	// Adjusting the frame 
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(500, 600);
		frame.setVisible(true);
	}
	
	
	// Adding in the Action Listeners
	public class NextCardListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Flash_Card card = new Flash_Card(question.getText(), answer.getText());
			cardList.add(card);
			clearCard();
		}
	}
	
	
	// Clearing the menu
	public class NewMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			cardList.clear();
			clearCard();
		}
	}
	
	
	// Creating a new Save file
	public class SaveMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Flash_Card card = new Flash_Card(question.getText(), answer.getText());
			cardList.add(card);
			
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile());
		}
	}
	
	
	// Clears out the card setting question/answer to blank strings
	private void clearCard() {
	question.setText("");
	answer.setText("");
	question.requestFocus();
	}

	
	private void saveFile(File file) {
	try {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		for (Flash_Card card:cardList) {
			writer.write(card.getQuestion()+"/");
			writer.write(card.getAnswer()+"/");
		}
		writer.close();
		
		}catch (IOException ex) {
			System.out.println("Couldn't write the cardList out");
			ex.printStackTrace();
		}
	}
	
}
