import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//This is a client interface that can communicate with the chatbot(that was created in assignment 2)
public class clientSide extends JFrame {
	static Socket socket = null;
	static DataInputStream input;
	static DataOutputStream output;
	static boolean fetch;
	static DataInputStream Newinput;
	static DataOutputStream Newoutput;
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
					clientSide frame = new clientSide();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		new Thread(new Runnable() {
			public void run() {
				try {
					fetch = false;//switching of socket is not needed for now
					initialize();
					socket = new Socket("localhost",3535);//connects to server. Need to change to server's IP address if the server is on different computer. In this case, the server is on the same machine.
					String message="";
					input = new DataInputStream(socket.getInputStream());
					output = new DataOutputStream(socket.getOutputStream());
					while(true) {
					message = input.readUTF();
					textArea.append("--> (bot): "+message+ "\n");
					textArea.getCaret().setDot(Integer.MAX_VALUE);
					}
				}catch(Exception e)//This scenario is when customer is asking to talk to a real person, the chatroom would switch to a different socket of manager server for real-time messaging
				{
					textArea.append("Reconnecting...\n");
					textArea.getCaret().setDot(Integer.MAX_VALUE);
					try {
						input.close();
						output.close();
						socket.close();
						fetch = true;//switching of socket is needed 
						Socket sssocket = new Socket("localhost",3536);
						 Newinput = new DataInputStream(sssocket.getInputStream());
						 Newoutput = new DataOutputStream(sssocket.getOutputStream());
						String message="";
						textArea.append("Reconnected\n");
						textArea.getCaret().setDot(Integer.MAX_VALUE);
						while(true) {
							message = Newinput.readUTF();
							textArea.append("--> (manager): "+message+ "\n");
							textArea.getCaret().setDot(Integer.MAX_VALUE);
							}
						
					} catch (IOException e1) {//if the connection is failed
						textArea.append("fail to reconnect");
						e1.printStackTrace();
					}
					}
				}
			}
		).start();
	}
	public static void initialize() {
		textArea = new JTextArea();
		textField = new JTextField();
		btnNewButton = new JButton("Send");
	}

	/**
	 * Create the frame.
	 */
	public clientSide() {
		setTitle("Customer Service Chatroom");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 650);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.info);
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
				textArea.append("--> (client): "+ message+"\n");
				textArea.getCaret().setDot(Integer.MAX_VALUE);
				if(!fetch)
				output.writeUTF(message);
				else
				Newoutput.writeUTF(message);
				
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
