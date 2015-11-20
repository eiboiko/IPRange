import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class UserInterface extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JScrollPane scrollPane;
	private JTextArea textArea;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface frame = new UserInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserInterface() {
		setTitle("Check Available IP Address");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(60, 22, 100, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(220, 22, 100, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);

		JButton go = new JButton("Go!");
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String firstIP = textField.getText();
				String secondIP = textField_1.getText();
				IpRange ip = new IpRange(firstIP, secondIP);
				if (ip.getIpRange() == null) {
					textArea.setText("No available adresses!");
				} else {
					for (String i : ip.getIpRange()) {
						textArea.append(i + "\n");
					}
				}
			}
		});
		go.setBounds(92, 49, 139, 23);
		contentPane.add(go);

		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(66, 103, 191, 148);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane);

		JLabel lblFirstIp = new JLabel("First IP:");
		lblFirstIp.setBounds(10, 25, 46, 14);
		contentPane.add(lblFirstIp);

		JLabel lblLastIp = new JLabel("Last IP:");
		lblLastIp.setBounds(170, 25, 46, 14);
		contentPane.add(lblLastIp);

		JLabel lblAvailableIp = new JLabel("Available IP address:");
		lblAvailableIp.setBounds(80, 85, 120, 14);
		contentPane.add(lblAvailableIp);
	}
}