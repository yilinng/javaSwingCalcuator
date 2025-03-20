package mycompany;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.UIManager;



public class Home {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("Button.disabledShadow"));
		frame.setSize(230, 320);
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setTitle("calcuator");
		frame.setResizable(false);
		
		//https://stackoverflow.com/questions/2539985/positioning-child-panels-using-flowlayout
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setSize(230, 40);
		//https://stackoverflow.com/questions/5854005/setting-horizontal-and-vertical-margins
		panel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel resultLabel = new JLabel("");
		resultLabel.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		resultLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(resultLabel);
		
		JLabel answerLabel = new JLabel("0");
		answerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		answerLabel.setFont(new Font("BankGothic Md BT", Font.PLAIN, 18));
		panel.add(answerLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setSize(230, 280);
		frame.getContentPane().add(panel_1);

		Operation operation = new Operation();
		
		JButton dotBtn = new JButton(".");
		dotBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operation.dotClickAction();
				answerLabel.setText(operation.getResult());
			}
		});
		
		JButton nb5Btn = new JButton("5");
		nb5Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operation.defaultNbAction();
				operation.setResult("5");
				operation.afterNbClickAction(); 
				resultLabel.setText(operation.getResult());
				answerLabel.setText(String.valueOf(operation.getAnswer()));
			}
		});
		
		
		JButton backBtn = new JButton("B");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operation.backAction();
				resultLabel.setText(operation.getResult());
				answerLabel.setText(String.valueOf(operation.getAnswer()));
			}
		});
		panel_1.setLayout(new GridLayout(0, 4, 0, 0));
		backBtn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(backBtn);
		
		JButton nb8Btn = new JButton("8");
		nb8Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operation.defaultNbAction();
				operation.setResult("8");
				operation.afterNbClickAction(); 
				resultLabel.setText(operation.getResult());
				answerLabel.setText(String.valueOf(operation.getAnswer()));
			}
		});
		
		JButton cleanBtn = new JButton("c");
		cleanBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operation.InitVal();
				operation.afterNbClickAction();
				resultLabel.setText(operation.getResult());
				answerLabel.setText(String.valueOf(operation.getAnswer()));
			}
		});
		cleanBtn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(cleanBtn);
		
		JButton dividedBtn = new JButton("/");
		dividedBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operation.haveMutiOperation();
				operation.concatSliceStrWithOperation("/");
				resultLabel.setText(operation.getResult());
			}
		});
		dividedBtn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(dividedBtn);
		
		JButton nb7Btn = new JButton("7");
		nb7Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operation.defaultNbAction();
				operation.setResult("7");
				operation.afterNbClickAction(); 
				resultLabel.setText(operation.getResult());
				answerLabel.setText(operation.getResult());
			}
		});
		
		JButton multiplyBtn = new JButton("*");
		multiplyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operation.haveMutiOperation();
				operation.concatSliceStrWithOperation("*");
				resultLabel.setText(operation.getResult());
			}
		});
		multiplyBtn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(multiplyBtn);
		nb7Btn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(nb7Btn);
		nb8Btn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(nb8Btn);
		
		JButton nb4Btn = new JButton("4");
		nb4Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operation.defaultNbAction();
				operation.setResult("4");
				operation.afterNbClickAction(); 
				resultLabel.setText(operation.getResult());
				answerLabel.setText(String.valueOf(operation.getAnswer()));
			}
		});
		
		JButton minusBtn = new JButton("-");
		minusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operation.haveMutiOperation();
				operation.concatSliceStrWithOperation("-");
				resultLabel.setText(operation.getResult());
			}
		});
		
		JButton nb9Btn = new JButton("9");
		nb9Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operation.defaultNbAction();
				operation.setResult("9");
				operation.afterNbClickAction(); 
				resultLabel.setText(operation.getResult());
				answerLabel.setText(String.valueOf(operation.getAnswer()));
			}
		});
		nb9Btn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(nb9Btn);
		minusBtn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(minusBtn);
		nb4Btn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(nb4Btn);
		nb5Btn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(nb5Btn);
		
		JButton nb6Btn = new JButton("6");
		nb6Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operation.defaultNbAction();
				operation.setResult("6");
				operation.afterNbClickAction(); 
				resultLabel.setText(operation.getResult());
				answerLabel.setText(String.valueOf(operation.getAnswer()));
			}
		});
		nb6Btn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(nb6Btn);
		
		JButton nb2Btn = new JButton("2");
		nb2Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operation.defaultNbAction();
				operation.setResult("2");
				operation.afterNbClickAction(); 
				resultLabel.setText(operation.getResult());
				answerLabel.setText(String.valueOf(operation.getAnswer()));
			}
		});
		
		JButton plusBtn = new JButton("+");
		plusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operation.haveMutiOperation();
				operation.concatSliceStrWithOperation("+");
				resultLabel.setText(operation.getResult());
			}
		});
		plusBtn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(plusBtn);
		
		JButton nb1Btn = new JButton("1");
		nb1Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operation.defaultNbAction();
				operation.setResult("1");
				operation.afterNbClickAction(); 
				resultLabel.setText(operation.getResult());
				answerLabel.setText(String.valueOf(operation.getAnswer()));
			}
		});
		nb1Btn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(nb1Btn);
		nb2Btn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(nb2Btn);
		
		JButton nb3Btn = new JButton("3");
		nb3Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operation.defaultNbAction();
				operation.setResult("3");
				operation.afterNbClickAction(); 
				resultLabel.setText(operation.getResult());
				answerLabel.setText(String.valueOf(operation.getAnswer()));
			}
		});
		nb3Btn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(nb3Btn);
		
		JButton totalBtn = new JButton("=");
		totalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operation.totalClickAction();
				resultLabel.setText("");
				answerLabel.setText(operation.getResult());
			}
		});
		totalBtn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(totalBtn);
		
		JButton nb0Btn = new JButton("0");
		nb0Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operation.nb0Action();
				operation.afterNbClickAction(); 
				resultLabel.setText(operation.getResult());
				answerLabel.setText(String.valueOf(operation.getAnswer()));
			}
		});
		
		nb0Btn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(nb0Btn);
		
		JLabel label = new JLabel("");
		panel_1.add(label);
		dotBtn.setFont(new Font("BankGothic Md BT", Font.PLAIN, 16));
		panel_1.add(dotBtn);
		
		JLabel label_1 = new JLabel("");
		panel_1.add(label_1);
		
	}

}
