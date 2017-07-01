import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Canvas;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import java.awt.Scrollbar;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.Window.Type;

public class GUI_Cipher {

	private JFrame frmAdfgvxCipher;
	private JTextField txtTypeInThe;
	private JLabel lblCipherdText;
	private JTextArea textArea;
	private JTextField textFieldKeyWord;
	private JLabel lblEnterAFour;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Cipher window = new GUI_Cipher();
					window.frmAdfgvxCipher.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	

	/**
	 * Create the application.
	 */
	public GUI_Cipher() {
		initialize();
	}

	public void encipher(){
		ADFGVX cipher = new ADFGVX(txtTypeInThe.getText(), textFieldKeyWord.getText()); //Creates the char[][]
		
		String tfKW = textFieldKeyWord.getText();
		
		cipher.fillAlpha(); // Fills the array with A-9
		//cipher.fillRandomAlpha(); // Fills the array, randomly, with A-9
		
		cipher.newCipherText(); // Creates the 1D array containing the cipher text
		String[][] theCipherGrid = cipher.newCipherGrid(); // Fill the 2D array with the cipher text

		cipher.printArray(); //Prints a regular A-9 array	
		cipher.printArray(theCipherGrid); //Prints the cipher text array
		
		cipher.replaceText();
		cipher.createPostTranspose(tfKW.substring(0,1).charAt(0), tfKW.substring(1,2).charAt(0), tfKW.substring(2,3).charAt(0), tfKW.substring(3,4).charAt(0));
		cipher.cipheredWord();
		
		textArea.setText(cipher.ppEncipher);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frmAdfgvxCipher = new JFrame();
		frmAdfgvxCipher.setType(Type.UTILITY);
		frmAdfgvxCipher.setResizable(false);
		frmAdfgvxCipher.getContentPane().setFont(new Font("Yu Gothic UI", Font.PLAIN, 30));
			
		frmAdfgvxCipher.getContentPane().setBackground(Color.BLACK);
		frmAdfgvxCipher.setFont(null);
		frmAdfgvxCipher.setTitle("ADFGVX Cipher\r\n");
		frmAdfgvxCipher.setBackground(Color.LIGHT_GRAY);
		frmAdfgvxCipher.setBounds(0, 0, 587, 588);
		frmAdfgvxCipher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdfgvxCipher.getContentPane().setLayout(null);
		
		JLabel lblEnterTheMessage = new JLabel("Enter the message you want to encipher:");
		lblEnterTheMessage.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterTheMessage.setToolTipText("Press the button to encipher the message to typed into the text.");
		lblEnterTheMessage.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblEnterTheMessage.setForeground(new Color(255, 255, 255));
		lblEnterTheMessage.setBackground(new Color(255, 255, 255));
		lblEnterTheMessage.setBounds(12, 181, 330, 27);
		frmAdfgvxCipher.getContentPane().add(lblEnterTheMessage);
		
		txtTypeInThe = new JTextField();
		txtTypeInThe.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		txtTypeInThe.setBounds(12, 221, 330, 38);
		frmAdfgvxCipher.getContentPane().add(txtTypeInThe);
		txtTypeInThe.setColumns(10);
		txtTypeInThe.getText();
		txtTypeInThe.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						encipher();
					}
				});
		
		
		Button button = new Button("ENCIPHER");
		button.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		button.setForeground(Color.BLACK);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encipher();
			}
		});
		
		button.setBounds(350, 221, 98, 38);
		frmAdfgvxCipher.getContentPane().add(button);
		
		lblCipherdText = new JLabel("Cipher'd Text:");
		lblCipherdText.setHorizontalAlignment(SwingConstants.LEFT);
		lblCipherdText.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblCipherdText.setForeground(new Color(255, 255, 255));
		lblCipherdText.setBounds(12, 265, 167, 27);
		frmAdfgvxCipher.getContentPane().add(lblCipherdText);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		textArea.setLineWrap(true);
		textArea.setBounds(12, 305, 558, 235);
		frmAdfgvxCipher.getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("ADFGVX CIPHER\r\n");
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 45));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(12, 13, 375, 53);
		frmAdfgvxCipher.getContentPane().add(lblNewLabel);
		
		textFieldKeyWord = new JTextField();
		textFieldKeyWord.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		textFieldKeyWord.setBounds(12, 130, 167, 38);
		frmAdfgvxCipher.getContentPane().add(textFieldKeyWord);
		textFieldKeyWord.setColumns(10);
		
		lblEnterAFour = new JLabel("Enter a keyword (Must be four letters long and have no repeat letters):");
		lblEnterAFour.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblEnterAFour.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterAFour.setForeground(Color.WHITE);
		lblEnterAFour.setBounds(12, 92, 558, 27);
		frmAdfgvxCipher.getContentPane().add(lblEnterAFour);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("F:\\Java\\projects\\ADFGVX_Cipher_7.1.17\\images\\starryN.jpg"));
		lblNewLabel_1.setBounds(0, 0, 584, 561);
		frmAdfgvxCipher.getContentPane().add(lblNewLabel_1);
	}
}
