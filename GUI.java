/******************************************************

Title: HL English Vocabulary Trainer
Course: ICS4U7

******************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.*;

public class GUI implements ActionListener{
		//login screen GUI elements
		private static JFrame loginFrame = new JFrame();
		private static JPanel loginPanel = new JPanel();
		private static JLabel usernameLabel = new JLabel();
		private static JLabel passwordLabel = new JLabel();
		private static JTextField usernameTextField = new JTextField();
		private static JPasswordField passwordField = new JPasswordField();
		private static JButton loginButton = new JButton("Log in");
		private static JButton registerButton = new JButton("Register");
		private static String user;
		
		//register screen GUI elements
		private static JFrame registerFrame = new JFrame();
		private static JPanel registerPanel = new JPanel();
		private static JLabel usernameLabel2 = new JLabel();
		private static JLabel passwordLabel2 = new JLabel();
		private static JTextField usernameTextField2 = new JTextField();
		private static JPasswordField passwordField2 = new JPasswordField();
		private static JLabel confirmPasswordLabel = new JLabel();
		private static JPasswordField confirmPasswordField = new JPasswordField();
		private static JButton backButton = new JButton("Back");
		private static JButton createButton = new JButton("Create Account");
		
		//main menu GUI elements
		private static JFrame menuFrame = new JFrame();
		private static JPanel menuPanel = new JPanel();
		private static JLabel welcomeLabel1 = new JLabel();
		private static JLabel welcomeLabel2 = new JLabel();
		private static JButton learnButton = new JButton("Learn New Words");
		private static JButton testButton = new JButton ("Test Your Knowledge");
		private static JButton profileButton = new JButton ("User Profile");
		private static JButton exitButton = new JButton ("Exit");
		
		//learn GUI elements 
		private static JFrame learnFrame = new JFrame();
		private static JPanel learnPanel = new JPanel();
		private static JLabel wordLabel = new JLabel();
		private static JLabel definitionLabel = new JLabel();
		private static JButton nextWordButton = new JButton("Next Word");
		private static JButton mainMenuButton1 = new JButton("Main Menu");
		private static JButton showDefButton = new JButton("Show Definition");
		private static JButton hideDefButton = new JButton ("Hide Definition");
		private static String word;
		private static Key wordKey;
		
		//test menu GUI elements
		private static JPanel testMenuPanel = new JPanel();
		private static JLabel testMenuLabel  = new JLabel("Select a testing format", SwingConstants.CENTER);
		private static JButton mcButton = new JButton("Multiple Choice");
		private static JButton matchingButton = new JButton ("Matching");
		private static JButton tfButton = new JButton("True/False");
		private static JButton mainMenuButton2 = new JButton("Main Menu");
		
		//profile GUI elements
		private static JPanel profilePanel = new JPanel(){
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D)g;
				g.drawLine(200, 0, 200, 600);
				g.drawLine(0, 90, 200, 90);
			}
		};
		private static JLabel usernameProfileLabel = new JLabel();
		private static JLabel wordsLearnedLabel = new JLabel();
		private static JLabel wordsLearned = new JLabel();
		private static JLabel wordsMasteredLabel = new JLabel();
		private static JLabel wordsMastered = new JLabel();
		private static JLabel correctAnswersLabel = new JLabel();
		private static JLabel correctAnswers = new JLabel();
		private static JLabel milestoneTitle = new JLabel();
		private static JLabel milestone1 = new JLabel();
		private static JLabel milestone2 = new JLabel();
		private static JLabel milestone3 = new JLabel();
		private static JLabel milestone4 = new JLabel();
		private static JLabel milestone5 = new JLabel();
		private static JLabel milestone6 = new JLabel();
		private static JLabel milestone7 = new JLabel();
		private static JLabel milestone8 = new JLabel();
		private static JButton mainMenuButton3 = new JButton("Main Menu");
		
		//Error Display Elements
		private static JFrame errorFrame = new JFrame();
		private static JPanel errorPanel = new JPanel();
		private static JLabel errorLabel = new JLabel("", SwingConstants.CENTER);
		private static JButton okButton = new JButton("Ok");
		
		//database elements
		private static Accounts acc;
		private static Hashmap dictionary;
		private static ArrayList <String> learnedKeys = new ArrayList<String>();
		private static String[] keys;
		private static Hashmap learnedWords;
		private static Questions q;
		private static JSONObject learned;
		private static String[] tempKeys;
		private static int NUMWORDS = 6624;
		
		//testing elements
		private static String tfWord;
		private static String mcWord;
		private static boolean tfAnswer;
		private static int mcAnswer;
		private static int[] matchAnswer;
		private static String[] matchWords;
		private static boolean[] matchResults;
		
		//tf
		private static JFrame tfFrame = new JFrame();
		private static JPanel tfPanel = new JPanel();
		private static JLabel tfQuestionLabel = new JLabel("Is the definition for the given word correct?");
		private static JLabel tfWordLabel = new JLabel();
		private static JLabel tfDefinitionLabel = new JLabel();	
		private static JButton trueButton = new JButton("True");		
		private static JButton falseButton = new JButton("False");
		private static JButton tfNextButton = new JButton("Next Word");
		private static JButton tfExitButton = new JButton("Exit");
		
		//correct screen
		private static JFrame correctFrame = new JFrame();
		private static JPanel correctPanel = new JPanel();
		private static JLabel correctLabel = new JLabel("", SwingConstants.CENTER);
		private static JButton okButton2 = new JButton("Ok");
		
		//incorrect screen 
		private static JFrame incorrectFrame = new JFrame();
		private static JPanel incorrectPanel = new JPanel();
		private static JLabel incorrectLabel = new JLabel("", SwingConstants.CENTER);
		private static JButton okButton3 = new JButton("Ok");
		
		//mc
		private static JFrame mcFrame = new JFrame(); 
		private static JPanel mcPanel = new JPanel();
		private static JLabel mcQuestionLabel = new JLabel("Select the correct definition for the following word:");
		private static JLabel mcWordLabel = new JLabel();
		private static JRadioButton mcAnswer1 = new JRadioButton();
		private static JRadioButton mcAnswer2 = new JRadioButton();	
		private static JRadioButton mcAnswer3 = new JRadioButton();	
		private static JRadioButton mcAnswer4 = new JRadioButton();	
		private static JButton mcNextButton = new JButton("Next Word");
		private static JButton mcExitButton = new JButton("Exit");
		private static ButtonGroup mcGroup = new ButtonGroup();
		
		//match
		private static JFrame matchFrame = new JFrame();
		private static JPanel matchPanel = new JPanel();
		private static JLabel matchQuestionLabel = new JLabel("Match the following words with their definitions:");	
		private static JLabel matchWordLabel1 = new JLabel();
		private static JLabel matchWordLabel2 = new JLabel();
		private static JLabel matchWordLabel3 = new JLabel();
		private static JLabel matchWordLabel4 = new JLabel();
		private static JLabel matchDefinition1 = new JLabel();
		private static JLabel matchDefinition2 = new JLabel();
		private static JLabel matchDefinition3 = new JLabel();
		private static JLabel matchDefinition4 = new JLabel();
		private static JTextField matchTextField1 = new JTextField();
		private static JTextField matchTextField2 = new JTextField();
		private static JTextField matchTextField3 = new JTextField();
		private static JTextField matchTextField4 = new JTextField();
		private static JButton matchCheckButton = new JButton("Check Answer");
		private static JButton matchNextButton = new JButton("Next");
		private static JButton matchExitButton = new JButton("Exit");
		
		//match incorrect
		private static JFrame matchIncorrectFrame = new JFrame();
		private static JPanel matchIncorrectPanel = new JPanel();
		private static JLabel matchIncorrectLabel = new JLabel("Incorrect. Here are the answers:");
		private static JLabel matchIncAnswerLabel1 = new JLabel("New label");
		private static JLabel matchIncAnswerLabel2 = new JLabel("New label");		
		private static JLabel matchIncAnswerLabel3 = new JLabel("New label");	
		private static JLabel matchIncAnswerLabel4 = new JLabel("New label");		
		private static JButton matchIncOkButton = new JButton("Ok");
	
	/**
	 * displays the login screen
	 */
	
	public static void loginScreen(){
		loginFrame.setSize(350, 200);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.add(loginPanel);
		loginFrame.setTitle("Login");
		
		loginPanel.setLayout(null);
		
		usernameLabel.setText("Username");
		usernameLabel.setBounds(30, 20, 80, 25);
		loginPanel.add(usernameLabel);
		
		passwordLabel.setText("Password");
		passwordLabel.setBounds(30, 50, 80, 25);
		loginPanel.add(passwordLabel);
		
		usernameTextField.setBounds(120, 20, 160, 25);
		loginPanel.add(usernameTextField);
		
		passwordField.setBounds(120, 50, 160, 25);
		loginPanel.add(passwordField);
		
		loginButton.setBounds(40, 80, 100, 25);
		loginPanel.add(loginButton);
		
		registerButton.setBounds(150, 80, 100, 25);
		loginPanel.add(registerButton);

		loginFrame.setVisible(true);
		loginFrame.setResizable(false);
	}
	
	/**
	 * displays the register screen
	 */
	
	public static void registerScreen(){
		loginFrame.setVisible(false);
				
		registerFrame.setSize(350, 200);
		registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registerFrame.add(registerPanel);
		registerFrame.setTitle("Register");
				
		registerPanel.setLayout(null);
				
		usernameLabel2.setText("Username");
		usernameLabel2.setBounds(10, 20, 80, 25);
		registerPanel.add(usernameLabel2);
				
		passwordLabel2.setText("Password");
		passwordLabel2.setBounds(10, 50, 80, 25);
		registerPanel.add(passwordLabel2);
				
		usernameTextField2.setBounds(140, 20, 165, 25);
		registerPanel.add(usernameTextField2);
				
		passwordField2.setBounds(140, 50, 165, 25);
		registerPanel.add(passwordField2);
				
		confirmPasswordLabel.setText("Confirm Password"); 
		confirmPasswordLabel.setBounds(10, 80, 120, 25);
		registerPanel.add(confirmPasswordLabel);
				
		confirmPasswordField.setBounds(140, 80, 165, 25);
		registerPanel.add(confirmPasswordField);
				
		backButton.setBounds(20, 110, 100, 25);
		registerPanel.add(backButton);
				
		createButton.setBounds(130, 110, 150, 25);
		registerPanel.add(createButton);
				
		registerFrame.setVisible(true);
		registerFrame.setResizable(false);
	}
	
	/**
	 * displays the main menu
	 */
	
	public static void menu(){
		menuFrame.setSize(800, 600); //4:3 is a common aspect ratio
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.add(menuPanel);
		menuFrame.setTitle("Main Menu");
		
		menuPanel.setLayout(null);
		
		welcomeLabel1.setText("Welcome to");
		welcomeLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel1.setFont(new Font("Arial", Font.PLAIN, 14));
		welcomeLabel1.setBounds(100, 50, 600, 25);
		menuPanel.add(welcomeLabel1);
		
		welcomeLabel2.setText("Vocabulary Trainer");
		welcomeLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel2.setFont(new Font("Arial", Font.PLAIN, 54));
		welcomeLabel2.setBounds(100, 55, 600, 100);
		menuPanel.add(welcomeLabel2);
		
		learnButton.setBounds(150, 225, 500, 50);
		menuPanel.add(learnButton);
		
		testButton.setBounds(150, 300, 500, 50);
		menuPanel.add(testButton);
		
		profileButton.setBounds(150, 375, 500, 50);
		menuPanel.add(profileButton);
		
		exitButton.setBounds(250, 450, 300, 50);
		menuPanel.add(exitButton);
		
		menuFrame.setVisible(true);
		menuFrame.setResizable(false);
	}

	/**
	 * displays the testing interface
	 */

	public static void learn(){ 
		learnFrame.setSize(740, 500);
		learnFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		learnFrame.add(learnPanel);
		learnFrame.setTitle("Learn New Words");
		
		learnPanel.setLayout(null);
		
		word = q.learn();
		wordKey = new Key(word);
		
		wordLabel.setText(word);
		wordLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		wordLabel.setBounds(85, 50, 600, 50);
		learnPanel.add(wordLabel);
		
		definitionLabel.setText("<html><p>" + dictionary.get(wordKey) + "</p></html>");
		definitionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		definitionLabel.setBounds(85, 75, 600, 100);
		
		hideDefButton.setBounds(120, 200, 200, 50);
		learnPanel.add(hideDefButton);
		
		showDefButton.setBounds(420, 200, 200, 50);
		learnPanel.add(showDefButton);
		
		nextWordButton.setBounds(120, 275, 500, 50);
		learnPanel.add(nextWordButton);
		
		mainMenuButton1.setBounds(220, 350, 300, 50);
		learnPanel.add(mainMenuButton1);
		
		learnFrame.setVisible(true);
		learnFrame.setResizable(false);
	}
	
	/**
	 * displays the testing menu
	 */
	
	public static void testMenu(){
		menuFrame.remove(menuPanel);
		menuFrame.add(testMenuPanel);
		menuFrame.setTitle("Test Format Selection");
		
		testMenuPanel.setLayout(null);
		
		testMenuLabel.setText("Select a testing format");
		testMenuLabel.setFont(new Font("Arial", Font.PLAIN, 54));
		testMenuLabel.setBounds(100, 55, 600, 100);
		testMenuPanel.add(testMenuLabel);
		
		mcButton.setBounds(150, 225, 500, 50);
		testMenuPanel.add(mcButton);
		
		matchingButton.setBounds(150, 300, 500, 50);
		testMenuPanel.add(matchingButton);
		
		tfButton.setBounds(150, 375, 500, 50);
		testMenuPanel.add(tfButton);
		
		mainMenuButton2.setBounds(250, 450, 300, 50);
		testMenuPanel.add(mainMenuButton2);
		
		menuFrame.setVisible(true);
		menuFrame.setResizable(false);
	}
	
	/**
	 * displays the profile
	 */
	
	public static void profile(){	
		menuFrame.remove(menuPanel);
		menuFrame.add(profilePanel);
		menuFrame.setTitle("Your Profile");
		
		profilePanel.setLayout(null);
		
		usernameProfileLabel.setText(user);
		usernameProfileLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameProfileLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		usernameProfileLabel.setBounds(20, 10, 160, 50);
		profilePanel.add(usernameProfileLabel);
	
		String[] stats = acc.getStats();
		
		wordsLearnedLabel.setText("Words Learned:");
		wordsLearnedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		wordsLearnedLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		wordsLearnedLabel.setBounds(20, 100, 150, 30);
		profilePanel.add(wordsLearnedLabel);

		wordsLearned.setText(String.valueOf(learnedKeys.size()));
		wordsLearned.setHorizontalAlignment(SwingConstants.CENTER);
		wordsLearned.setFont(new Font("Arial", Font.PLAIN, 24));
		wordsLearned.setBounds(50, 150, 100, 30);
		profilePanel.add(wordsLearned);
		
		wordsMasteredLabel.setText("Words Mastered:");
		wordsMasteredLabel.setHorizontalAlignment(SwingConstants.CENTER);
		wordsMasteredLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		wordsMasteredLabel.setBounds(20, 200, 150, 30);
		profilePanel.add(wordsMasteredLabel);
		
		wordsMastered.setText(stats[1]);
		wordsMastered.setHorizontalAlignment(SwingConstants.CENTER);
		wordsMastered.setFont(new Font("Arial", Font.PLAIN, 24));
		wordsMastered.setBounds(50, 250, 100, 30);
		profilePanel.add(wordsMastered);
		
		correctAnswersLabel.setText("Correct Answers:");
		correctAnswersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		correctAnswersLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		correctAnswersLabel.setBounds(20, 300, 150, 50);
		profilePanel.add(correctAnswersLabel);
		
		correctAnswers.setText(stats[2]);
		correctAnswers.setHorizontalAlignment(SwingConstants.CENTER);
		correctAnswers.setFont(new Font("Arial", Font.PLAIN, 24));
		correctAnswers.setBounds(50, 350, 100, 30);
		profilePanel.add(correctAnswers);
		
		milestoneTitle.setText("Achievements/Milestones");
		milestoneTitle.setHorizontalAlignment(SwingConstants.CENTER);
		milestoneTitle.setFont(new Font("Arial", Font.PLAIN, 32));
		milestoneTitle.setBounds(200, 0, 600, 90);
		profilePanel.add(milestoneTitle);
		
		milestone1.setText("1. Not achieved");
		milestone1.setHorizontalAlignment(SwingConstants.CENTER);
		milestone1.setFont(new Font("Arial", Font.PLAIN, 20));
		milestone1.setBounds(200, 90, 600, 45);
		profilePanel.add(milestone1);
		
		if(Integer.parseInt(stats[0]) >= 10){
			milestone1.setText("1. Learn 10 words");
		}
		
		milestone2.setText("2. Not achieved");
		milestone2.setHorizontalAlignment(SwingConstants.CENTER);
		milestone2.setFont(new Font("Arial", Font.PLAIN, 20));
		milestone2.setBounds(200, 135, 600, 45);
		profilePanel.add(milestone2);
		
		if(Integer.parseInt(stats[2]) >= 10){
			milestone2.setText("2. Answer correctly 10 times");
		}
		
		milestone3.setText("3. Not achieved");
		milestone3.setHorizontalAlignment(SwingConstants.CENTER);
		milestone3.setFont(new Font("Arial", Font.PLAIN, 20));
		milestone3.setBounds(200, 180, 600, 45);
		profilePanel.add(milestone3);
		
		if(Integer.parseInt(stats[1]) >= 10){
			milestone3.setText("3. Master 10 words");
		}
		
		milestone4.setText("4. Not achieved");
		milestone4.setHorizontalAlignment(SwingConstants.CENTER);
		milestone4.setFont(new Font("Arial", Font.PLAIN, 20));
		milestone4.setBounds(200, 225, 600, 45);
		profilePanel.add(milestone4);
		
		if(Integer.parseInt(stats[0]) >= 100){
			milestone4.setText("4. Learn 100 words");
		}
		
		milestone5.setText("5. Not achieved");
		milestone5.setHorizontalAlignment(SwingConstants.CENTER);
		milestone5.setFont(new Font("Arial", Font.PLAIN, 20));
		milestone5.setBounds(200, 270, 600, 45);
		profilePanel.add(milestone5);
		
		if(Integer.parseInt(stats[0]) >= 100){
			milestone5.setText("5. Answer correctly 100 times");
		}
		
		milestone6.setText("6. Not achieved");
		milestone6.setHorizontalAlignment(SwingConstants.CENTER);
		milestone6.setFont(new Font("Arial", Font.PLAIN, 20));
		milestone6.setBounds(200, 315, 600, 45);
		profilePanel.add(milestone6);
		
		if(Integer.parseInt(stats[2]) >= 100){
			milestone6.setText("6. Master 100 words");
		}
		
		milestone7.setText("7. Not achieved");
		milestone7.setHorizontalAlignment(SwingConstants.CENTER);
		milestone7.setFont(new Font("Arial", Font.PLAIN, 20));
		milestone7.setBounds(200, 360, 600, 45);
		profilePanel.add(milestone7);
		
		if(Integer.parseInt(stats[2]) >= 500){
			milestone7.setText("7. Master 500 words");
		}
		
		milestone8.setText("8. Not achieved");
		milestone8.setHorizontalAlignment(SwingConstants.CENTER);
		milestone8.setFont(new Font("Arial", Font.PLAIN, 20));
		milestone8.setBounds(200, 405, 600, 45);
		profilePanel.add(milestone8);
		
		if(Integer.parseInt(stats[2]) >= 1000){
			milestone8.setText("8. Master 1000 words");
		}
		
		mainMenuButton3.setBounds(20, 400, 150, 50);
		profilePanel.add(mainMenuButton3);
		
		menuFrame.setVisible(true);
		menuFrame.setResizable(false);
	}
	
	/**
	 * displays an error message
	 * @param msg the error message that will be displayed
	 */
	
	public static void errorMsg(String msg){	
		errorFrame.setSize(240, 120);
		errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		errorFrame.add(errorPanel);
		errorFrame.setTitle("Error");
		
		errorPanel.setLayout(null);
		
		errorLabel.setText(msg);
		errorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		errorLabel.setBounds(15, 10, 180, 30);
		errorPanel.add(errorLabel);
		
		okButton.setBounds(70, 50, 50, 20);
		errorPanel.add(okButton);
		
		errorFrame.setVisible(true);
		errorFrame.setResizable(false);
	}
	
	/**
	 * displays the true/false question interface
	 */
	
	public static void tf(){	
		TF tf = q.tf();
		
		tfFrame.setTitle("True/False");
		tfFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tfFrame.setBounds(100, 100, 450, 400);

		tfFrame.setContentPane(tfPanel);
		tfPanel.setLayout(null);
		
		tfQuestionLabel.setText("Is the definition for the given word correct?");
		tfQuestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tfQuestionLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		tfQuestionLabel.setBounds(57, 25, 326, 30);
		tfPanel.add(tfQuestionLabel);
		
		tfWord = tf.getWord();
		
		tfWordLabel.setText(tfWord);
		tfWordLabel.setBounds(57, 74, 300, 14);
		tfPanel.add(tfWordLabel);

		tfDefinitionLabel.setText("<html><p>" + tf.getDefinition() + "</p></html>");
		tfDefinitionLabel.setVerticalAlignment(SwingConstants.TOP);
		tfDefinitionLabel.setBounds(57, 99, 344, 100);
		tfPanel.add(tfDefinitionLabel);
		
		trueButton.setBounds(99, 230, 89, 23);
		tfPanel.add(trueButton);
		
		falseButton.setBounds(256, 230, 89, 23);
		tfPanel.add(falseButton);
		
		tfExitButton.setBounds(176, 310, 89, 23);
		tfPanel.add(tfExitButton);
		
		tfNextButton.setBounds(99, 270, 246, 23);
		tfPanel.add(tfNextButton);
		
		tfAnswer = tf.getAnswer();
		
		tfFrame.setVisible(true);
		tfFrame.setResizable(false);
	}
	
	/**
	 * displays the correct message
	 */
	
	public static void correct(){
		correctFrame.setSize(200, 120);
		correctFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		correctFrame.setContentPane(correctPanel);
		correctFrame.setTitle("Correct");
		
		correctPanel.setLayout(null);
		
		correctLabel.setText("Correct!");
		correctLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		correctLabel.setBounds(15, 10, 160, 30);
		correctPanel.add(correctLabel);
		
		okButton2.setBounds(70, 50, 50, 20);
		correctPanel.add(okButton2);
		
		correctFrame.setVisible(true);
		correctFrame.setResizable(false);
	}
	
	/**
	 * displays the incorrect message
	 */
	
	public static void incorrect(){
		incorrectFrame.setSize(200, 120);
		incorrectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		incorrectFrame.setContentPane(incorrectPanel);
		incorrectFrame.setTitle("Incorrect");
		
		incorrectFrame.setLayout(null);
		
		incorrectLabel.setText("Incorrect :(");
		incorrectLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		incorrectLabel.setBounds(15, 10, 160, 30);
		incorrectPanel.add(incorrectLabel);
		
		okButton3.setBounds(70, 50, 50, 20);
		incorrectPanel.add(okButton3);
		
		incorrectFrame.setVisible(true);
		incorrectFrame.setResizable(false);
	}
	
	/**
	 * displays the multiple choice question interface
	 */
	
	public static void mc(){
		MC mc = q.mc();
		
		mcFrame.setTitle("Multiple Choice");
		mcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mcFrame.setBounds(100, 100, 600, 450);

		mcFrame.setContentPane(mcPanel);
		mcPanel.setLayout(null);
		
		mcQuestionLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		mcQuestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mcQuestionLabel.setBounds(45, 23, 485, 20);
		mcPanel.add(mcQuestionLabel);
		
		mcWord = mc.getWord();
		
		mcWordLabel.setText(mcWord);
		mcWordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		mcWordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		mcWordLabel.setBounds(45, 59, 92, 30);
		mcPanel.add(mcWordLabel);
		
		mcAnswer1.setText("<html><p>" + mc.getDefinition1() + "</p></html>");
		mcAnswer1.setVerticalAlignment(SwingConstants.TOP);
		mcAnswer1.setBounds(45, 90, 485, 50);
		mcPanel.add(mcAnswer1);
		
		mcAnswer2.setText("<html><p>" + mc.getDefinition2() + "</p></html>");
		mcAnswer2.setVerticalAlignment(SwingConstants.TOP);
		mcAnswer2.setBounds(45, 143, 485, 50);
		mcPanel.add(mcAnswer2);
		
		mcAnswer3.setText("<html><p>" + mc.getDefinition3() + "</p></html>");
		mcAnswer3.setVerticalAlignment(SwingConstants.TOP);
		mcAnswer3.setBounds(45, 195, 485, 50);
		mcPanel.add(mcAnswer3);
		
		mcAnswer4.setText("<html><p>" + mc.getDefinition4() + "</p></html>");
		mcAnswer4.setVerticalAlignment(SwingConstants.TOP);
		mcAnswer4.setBounds(45, 245, 485, 50);
		mcPanel.add(mcAnswer4);
		
		mcGroup.add(mcAnswer1);
		mcGroup.add(mcAnswer2);
		mcGroup.add(mcAnswer3);
		mcGroup.add(mcAnswer4);
		
		mcAnswer = mc.getAnswer();
		
		mcNextButton.setBounds(81, 310, 423, 23);
		mcPanel.add(mcNextButton);
		
		mcExitButton.setBounds(175, 344, 239, 23);
		mcPanel.add(mcExitButton);
		
		mcFrame.setResizable(false);
		mcFrame.setVisible(true);
	}
	
	/**
	 * displays the matching question interface
	 */
	
	public static void match(){
		Match match = q.match();
		
		matchFrame.setTitle("Matching");
		matchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		matchFrame.setBounds(100, 100, 675, 546);
		
		matchPanel.setFont(new Font("Arial", Font.PLAIN, 11));
		matchFrame.setContentPane(matchPanel);
		matchPanel.setLayout(null);
		
		
		matchQuestionLabel.setText("Match the following words with their definitions:");
		matchQuestionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		matchQuestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		matchQuestionLabel.setBounds(145, 22, 396, 45);
		matchPanel.add(matchQuestionLabel);
		
		matchWordLabel1.setText("1. " + match.getWord1());
		matchWordLabel1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		matchWordLabel1.setBounds(60, 88, 127, 28);
		matchPanel.add(matchWordLabel1);
		
		matchWordLabel2.setText("2. " + match.getWord2());
		matchWordLabel2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		matchWordLabel2.setBounds(60, 160, 127, 28);
		matchPanel.add(matchWordLabel2);
		
		matchWordLabel3.setText("3. " + match.getWord3());
		matchWordLabel3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		matchWordLabel3.setBounds(60, 233, 127, 28);
		matchPanel.add(matchWordLabel3);
		
		matchWordLabel4.setText("4. " + match.getWord4());
		matchWordLabel4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		matchWordLabel4.setBounds(60, 310, 127, 28);
		matchPanel.add(matchWordLabel4);
		
		matchDefinition1.setText("<html><p>" + match.getDefinition1() + "</p></html>");
		matchDefinition1.setVerticalAlignment(SwingConstants.TOP);
		matchDefinition1.setBounds(228, 97, 404, 52);
		matchPanel.add(matchDefinition1);
		
		matchDefinition2.setText("<html><p>" + match.getDefinition2() + "</p></html>");
		matchDefinition2.setVerticalAlignment(SwingConstants.TOP);
		matchDefinition2.setBounds(228, 169, 404, 52);
		matchPanel.add(matchDefinition2);
		
		matchDefinition3.setText("<html><p>" + match.getDefinition3() + "</p></html>");
		matchDefinition3.setVerticalAlignment(SwingConstants.TOP);
		matchDefinition3.setBounds(228, 242, 404, 52);
		matchPanel.add(matchDefinition3);
		
		matchDefinition4.setText("<html><p>" + match.getDefinition4() + "</p></html>");
		matchDefinition4.setVerticalAlignment(SwingConstants.TOP);
		matchDefinition4.setBounds(228, 319, 404, 52);
		matchPanel.add(matchDefinition4);
		
		matchTextField1.setBounds(193, 94, 25, 20);
		matchTextField1.setColumns(10);
		matchPanel.add(matchTextField1);
		
		matchTextField2.setColumns(10);
		matchTextField2.setBounds(193, 166, 25, 20);
		matchPanel.add(matchTextField2);
		
		matchTextField3.setColumns(10);
		matchTextField3.setBounds(193, 239, 25, 20);
		matchPanel.add(matchTextField3);
		
		matchTextField4.setColumns(10);
		matchTextField4.setBounds(193, 316, 25, 20);
		matchPanel.add(matchTextField4);
		
		matchCheckButton.setBounds(162, 403, 309, 35);
		matchPanel.add(matchCheckButton);
		
		matchExitButton.setBounds(183, 463, 107, 28);
		matchPanel.add(matchExitButton);
		
		matchNextButton.setBounds(340, 463, 107, 28);
		matchPanel.add(matchNextButton);
		
		matchAnswer = match.getAnswer();
		matchWords = match.getWords();
		
		matchFrame.setResizable(false);
		matchFrame.setVisible(true);
	}
	
	/**
	 * displays the answers to the matching question
	 */
	
	public static void matchIncorrect(){
		matchIncorrectFrame.setTitle("Incorrect");
		matchIncorrectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		matchIncorrectFrame.setBounds(100, 100, 400, 300);
		matchIncorrectFrame.setContentPane(matchIncorrectPanel);
		matchIncorrectPanel.setLayout(null);
		
		matchIncorrectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		matchIncorrectLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		matchIncorrectLabel.setBounds(30, 27, 336, 19);
		matchIncorrectPanel.add(matchIncorrectLabel);
		
		matchIncAnswerLabel1.setText(String.valueOf(matchAnswer[0]));
		matchIncAnswerLabel1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		matchIncAnswerLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		matchIncAnswerLabel1.setBounds(40, 66, 300, 19);
		matchIncorrectPanel.add(matchIncAnswerLabel1);
		
		matchIncAnswerLabel2.setText(String.valueOf(matchAnswer[1]));
		matchIncAnswerLabel2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		matchIncAnswerLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		matchIncAnswerLabel2.setBounds(40, 108, 300, 19);
		matchIncorrectPanel.add(matchIncAnswerLabel2);
		
		matchIncAnswerLabel3.setText(String.valueOf(matchAnswer[2]));
		matchIncAnswerLabel3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		matchIncAnswerLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		matchIncAnswerLabel3.setBounds(40, 148, 300, 19);
		matchIncorrectPanel.add(matchIncAnswerLabel3);
		
		matchIncAnswerLabel4.setText(String.valueOf(matchAnswer[3]));
		matchIncAnswerLabel4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		matchIncAnswerLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		matchIncAnswerLabel4.setBounds(40, 192, 300, 19);
		matchIncorrectPanel.add(matchIncAnswerLabel4);
		
		matchIncOkButton.setBounds(156, 222, 89, 23);
		matchIncorrectPanel.add(matchIncOkButton);
		
		matchIncorrectFrame.setResizable(false);
		matchIncorrectFrame.setVisible(true);
	}
	
	/**
	 * Sets up ActionListeners for all buttons in the GUI and assigns them to the appropriate buttons
	 */
	
	public static void setUpActionListeners(){
	
		ActionListener login = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				user = usernameTextField.getText();
				String password = passwordField.getText();
				
				if(Accounts.check(user, password)){
					dictionary = new Hashmap();
					
					Key k;
					Value v;
		
					try{
						JSONParser parser = new JSONParser();
						JSONObject obj = (JSONObject) parser.parse(new FileReader("wordsDatabase.json"));
						BufferedReader br = new BufferedReader(new FileReader("words5.txt"));
						String temp;
						tempKeys = new String[NUMWORDS];
						int count = 0;
						
						JSONParser jp = new JSONParser();
						learned = (JSONObject) jp.parse(new FileReader(user + "Learned.json"));
						Set<String> learnedSet = learned.keySet();
						String [] tempArr;
						learnedKeys.addAll(learnedSet);
						
						keys = new String [tempKeys.length - learnedKeys.size()];
						
						if(learnedKeys.size() == 0){
							while ((temp = br.readLine()) != null){
								k = new Key(temp);
								v = new Value((String) obj.get(temp));
								keys[count] = temp;                     
								count++;
								dictionary.add(k, v);
							}
						}
						else{
							while ((temp = br.readLine()) != null){
								k = new Key(temp);
								v = new Value((String) obj.get(temp));
								tempKeys[count] = temp;                     
								count++;
								dictionary.add(k, v);
							}
							
							for(int i = 0; i < (tempKeys.length - learnedKeys.size()); i++){
								for(int j = 0; j < learnedKeys.size(); j++){
									if (tempKeys[i].equals(learnedKeys.get(j))){
										continue;
									}
									else{
										keys[i] = tempKeys[i];
									}
								}
							}
						}
						br.close();
					}
					catch (Exception ex){
						ex.printStackTrace();
					}
					acc = new Accounts (user, learned.toJSONString());
					q = new Questions(dictionary, keys, learnedKeys, acc);
					
					loginFrame.dispose();
					menu();
				}
				else{
					errorMsg("Wrong username or password.");
				}		
			}
		};
		
		ActionListener register = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				registerScreen();
			}
		};
		
		ActionListener back = new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e){
				registerFrame.dispose();
				//registerFrame.setVisible(false);
				loginFrame.setVisible(true);
			}
		};
			
		ActionListener create = new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e){
				String user = usernameTextField2.getText();
				String password = passwordField2.getText();
				String confirmPass = confirmPasswordField.getText();
				if(user.chars().count() <= 12){
					if(confirmPass.equals(password)){
						if(Accounts.register(usernameTextField2.getText(), passwordField2.getText())){
					
							registerFrame.dispose(); 
							loginFrame.setVisible(true);
						}
						else{
							errorMsg("Sorry, that username is taken");
						}
					}
					
					else{
						errorMsg("Your passwords do not match");
					}
				}
				else{
					errorMsg("Username must be <12 characters.");
				}
			}
			//maybe success label
		};
		
		ActionListener learn = new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e){
				menuFrame.setVisible(false);
				learn(); 
			}
		};
		
		ActionListener test = new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e){
				testMenu();
			}
		};
		
		ActionListener profile = new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e){ 
				profile();
			}
		};
		
		ActionListener exit = new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		};
		
		ActionListener nextWord = new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e){
				word = q.learn();
				wordKey = new Key(word);
			
				learnPanel.remove(definitionLabel);
				learnFrame.setVisible(true);
				learnFrame.setResizable(false);
				
				wordLabel.setText(word);
				definitionLabel.setText("<html><p>" + dictionary.get(wordKey) + "</p></html>");
			}
		};
		
		ActionListener mainMenu = new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e){
				learnFrame.setVisible(false);
				menuFrame.remove(testMenuPanel);
				menuFrame.remove(profilePanel);
				menuFrame.add(menuPanel);
				menuFrame.setTitle("Main Menu");
				menuFrame.setVisible(true);
				menuFrame.setResizable(false);
			}
		};
		
		ActionListener showDef = new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e){
				learnPanel.add(definitionLabel);
				learnFrame.setVisible(true);
				learnFrame.setResizable(false);
			}
		};
		
		ActionListener hideDef = new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e){
				learnPanel.remove(definitionLabel);
				learnFrame.setVisible(true);
				learnFrame.setResizable(false);
			}
		};
		
		ActionListener mc = new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e){
				if(learnedKeys.size() > 1){
					menuFrame.setVisible(false);
					mc();
				}
				else{
					errorMsg("Learn more words first!");
				}
			}
		};
		
		ActionListener match = new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e){
				if(learnedKeys.size() > 4){
					menuFrame.setVisible(false);
					match();
				}
				else{
					errorMsg("Learn more words first");
				}
			}
		};
		
		ActionListener tf = new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e){
				if(learnedKeys.size() > 1){
					menuFrame.setVisible(false);
					tf();
				}
				else{
					errorMsg("Learn more words first!");
				}
			}
		};
		
		ActionListener ok = new ActionListener(){
			//Override
			public void actionPerformed(ActionEvent e){
				errorFrame.setVisible(false);
			}
		};
		
		ActionListener t = new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e){
				if(tfAnswer){
					//correct
					correct();
					acc.addMastery(tfWord);
				}
				else{
					incorrect();
				}
			}
		};
		
		ActionListener f = new ActionListener(){
			//Override
			public void actionPerformed(ActionEvent e){
				if(tfAnswer){
					//wrong
					incorrect();
				}
				else{
					correct();
					acc.addMastery(tfWord);
				}
			}
		};
		
		ActionListener ok2 = new ActionListener(){
			//Override
			public void actionPerformed(ActionEvent e){
				correctFrame.setVisible(false);
				incorrectFrame.setVisible(false);
			}
		};
		
		ActionListener tfExit = new ActionListener(){
			//Override
			public void actionPerformed(ActionEvent e){
				tfFrame.setVisible(false);
				menuFrame.setVisible(true);
			}
		};
		
		ActionListener tfNext = new ActionListener(){
			//Override
			public void actionPerformed(ActionEvent e){
				TF tf = q.tf();
				tfWordLabel.setText(tf.getWord());
				tfDefinitionLabel.setText("<html><p>" + tf.getDefinition() + "</p></html>");
				tfAnswer = tf.getAnswer();
			}
		};
		
		ActionListener mc1 = new ActionListener(){
			//Override
			public void actionPerformed(ActionEvent e){
				if(mcAnswer == 1){
					//wrong
					correct();
					acc.addMastery(mcWord);
				}
				else{
					incorrect();
				}
			}
		};
		
		ActionListener mc2 = new ActionListener(){
			//Override
			public void actionPerformed(ActionEvent e){
				if(mcAnswer == 2){
					//wrong
					correct();
					acc.addMastery(mcWord);
				}
				else{
					incorrect();
				}
			}
		};
		
		ActionListener mc3 = new ActionListener(){
			//Override
			public void actionPerformed(ActionEvent e){
				if(mcAnswer == 3){
					//wrong
					correct();
					acc.addMastery(mcWord);
				}
				else{
					incorrect();
				}
			}
		};
		
		ActionListener mc4 = new ActionListener(){
			//Override
			public void actionPerformed(ActionEvent e){
				if(mcAnswer == 4){
					//wrong
					correct();
					acc.addMastery(mcWord);
				}
				else{
					incorrect();
				}
			}
		};
		
		ActionListener mcExit = new ActionListener(){
			//Override
			public void actionPerformed(ActionEvent e){
				mcFrame.setVisible(false);
				menuFrame.setVisible(true);
			}
		};
		
		ActionListener mcNext = new ActionListener(){
			//Override
			public void actionPerformed(ActionEvent e){
				MC mc = q.mc();
		
				mcWordLabel.setText(mc.getWord());
				mcAnswer1.setText("<html><p>" + mc.getDefinition1() + "</p></html>");		
				mcAnswer2.setText("<html><p>" + mc.getDefinition2() + "</p></html>");	
				mcAnswer3.setText("<html><p>" + mc.getDefinition3() + "</p></html>");	
				mcAnswer4.setText("<html><p>" + mc.getDefinition4() + "</p></html>");
				mcAnswer = mc.getAnswer();
			}
		};
		
		ActionListener matchCheck = new ActionListener(){
			//Override
			public void actionPerformed(ActionEvent e){
				int count = 0;
				matchResults = new boolean[4];
				ArrayList<JTextField> texts = new ArrayList<JTextField>();
				texts.add(matchTextField1);
				texts.add(matchTextField2);
				texts.add(matchTextField3);
				texts.add(matchTextField4);
				
				try{ //can use arraylists to hold textfields, labels, etc.
					for(int i = 0; i < 4; i++){
						if(Integer.parseInt((texts.get(i)).getText()) == matchAnswer[i]){
							matchResults[i] = true;
							count++;
						}
						else{
							matchResults[i] = false;
						}
					}
					if(count == 4){
						correct();
						acc.addMastery(matchWords);
					}
					else{
						matchIncorrect();
						acc.addMastery(matchWords, matchResults);
					}
				}
				catch (Exception ex){
					errorMsg("Invalid input");
				}
			}
		};
		
		ActionListener matchExit = new ActionListener(){
			//Override
			public void actionPerformed(ActionEvent e){
				matchFrame.setVisible(false);
				menuFrame.setVisible(true);
			}
		};
		
		ActionListener matchNext = new ActionListener(){
			//Override
			public void actionPerformed(ActionEvent e){
				Match match = q.match();
		
				matchWordLabel1.setText("1. " + match.getWord1());		
				matchWordLabel2.setText("2. " + match.getWord2());
				matchWordLabel3.setText("3. " + match.getWord3());		
				matchWordLabel4.setText("4. " + match.getWord4());
				matchDefinition1.setText("<html><p>" + match.getDefinition1() + "</p></html>");	
				matchDefinition2.setText("<html><p>" + match.getDefinition2() + "</p></html>");
				matchDefinition3.setText("<html><p>" + match.getDefinition3() + "</p></html>");
				matchDefinition4.setText("<html><p>" + match.getDefinition4() + "</p></html>");
				
				matchAnswer = match.getAnswer();
				matchWords = match.getWords();
			}
		};
		
		ActionListener matchOk = new ActionListener(){
			//Override
			public void actionPerformed(ActionEvent e){
				matchIncorrectFrame.setVisible(false);
				matchFrame.setVisible(true);
			}
		};

		loginButton.addActionListener(login);
		registerButton.addActionListener(register);
		backButton.addActionListener(back);
		createButton.addActionListener(create);
		learnButton.addActionListener(learn);
		testButton.addActionListener(test);
		profileButton.addActionListener(profile);
		exitButton.addActionListener(exit);
		nextWordButton.addActionListener(nextWord);
		mainMenuButton1.addActionListener(mainMenu);
		showDefButton.addActionListener(showDef);
		hideDefButton.addActionListener(hideDef);
		mcButton.addActionListener(mc);
		matchingButton.addActionListener(match);
		tfButton.addActionListener(tf);
		mainMenuButton2.addActionListener(mainMenu);
		mainMenuButton3.addActionListener(mainMenu);
		okButton.addActionListener(ok);
		trueButton.addActionListener(t);
		falseButton.addActionListener(f);
		okButton2.addActionListener(ok2);
		okButton3.addActionListener(ok2);
		tfExitButton.addActionListener(tfExit);
		tfNextButton.addActionListener(tfNext);
		mcAnswer1.addActionListener(mc1);
		mcAnswer2.addActionListener(mc2);
		mcAnswer3.addActionListener(mc3);
		mcAnswer4.addActionListener(mc4);
		mcExitButton.addActionListener(mcExit);
		mcNextButton.addActionListener(mcNext);
		matchCheckButton.addActionListener(matchCheck);
		matchExitButton.addActionListener(matchExit);
		matchNextButton.addActionListener(matchNext);
		matchIncOkButton.addActionListener(matchOk);
	}
	
	/**
	 * placeholder so that the compiler detects the existence of an actionPerformed method
	 */
	
	public void actionPerformed(ActionEvent e){
	}
	
	/**
	 * updates the "learnedKeys" arraylist and "keys" array after the user learns a new word
	 */
	
	public static void updateLearned(){
		try{
			BufferedReader br = new BufferedReader(new FileReader("words5.txt"));
			String temp;
			int count = 0;
			JSONParser jp = new JSONParser();
			learned = (JSONObject) jp.parse(new FileReader(user + "Learned.json"));
			Set<String> learnedSet = learned.keySet();
			String [] tempArr;
			learnedKeys = new ArrayList<String>();
			learnedKeys.addAll(learnedSet);
							
			keys = new String [tempKeys.length - learnedKeys.size()];
							
			if(learnedKeys.size() == 0){
				while ((temp = br.readLine()) != null){
					keys[count] = temp;                     
					count++;
				}
			}
			else{
				while ((temp = br.readLine()) != null){
					tempKeys[count] = temp;                     
					count++;
				}
								
				for(int i = 0; i < (tempKeys.length - learnedKeys.size()); i++){
					for(int j = 0; j < learnedKeys.size(); j++){
						if (tempKeys[i].equals(learnedKeys.get(j))){
							continue;
						}
						else{
							keys[i] = tempKeys[i];
						}
					}
				}
			}
			br.close();
			q = new Questions(dictionary, keys, learnedKeys, acc);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
}