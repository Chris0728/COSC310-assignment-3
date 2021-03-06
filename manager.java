import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//This is a manager interface that can communicate with the client (when client ask to talk to manager)
public class manager extends JFrame {
	static ServerSocket serversocket = null;
	static Socket socket = null;
	static DataInputStream input;
	static DataOutputStream output;
	
	private static JPanel contentPane;
	private static JTextField textField;
	private static JTextArea textArea;
	private static JButton btnNewButton;
	private static JScrollPane scrollPane_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manager frame = new manager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		new Thread(new Runnable() {
			public void run() {
				try {
					serversocket = new ServerSocket(3536);
					socket = serversocket.accept();
					input = new DataInputStream(socket.getInputStream());
					output = new DataOutputStream(socket.getOutputStream());
					String message = "Hello there, I am the Customer Service representative. How can I help you today?";
					output.writeUTF(message);
					textArea.append("--> (manager): "+message+ "\n");
					textArea.getCaret().setDot(Integer.MAX_VALUE);
					while(true) {
						message = input.readUTF();
						textArea.append("--> (Client): "+message+ "\n");
						textArea.getCaret().setDot(Integer.MAX_VALUE);
					}
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}).start();
	}
	public static void initialize() {
		textArea = new JTextArea();
		textField = new JTextField();
		btnNewButton = new JButton("Send");
	}

	/**
	 * Create the frame.
	 */
	public manager() {
		setTitle("Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 650);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 668, 579);
		contentPane.add(panel);
		panel.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 33, 626, 440);
		panel.add(scrollPane_1);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 25));
		textArea.setLineWrap(true);
		scrollPane_1.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 25));
		textField.setBounds(21, 494, 464, 64);
		panel.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				String message ="";
				message = textField.getText();
				textArea.append("--> (manager): "+ message+"\n");
				textArea.getCaret().setDot(Integer.MAX_VALUE);
				output.writeUTF(message);
				textField.setText("");
				
			}catch(Exception EE)
			{
				System.out.println(EE);
			}
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(506, 494, 141, 64);
		panel.add(btnNewButton);
		
	}

}