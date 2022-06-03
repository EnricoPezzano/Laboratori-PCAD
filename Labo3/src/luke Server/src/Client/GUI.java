package Client;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException; 


class GUI {
	
	String out;
	Socket clientSocket=null;
	JFrame baseFrame; 
	JPanel contentPane;
	static JTextField t_area;
	final JTextArea tf;
	JButton btnstampa;
	JButton btnprenota;
	JLabel label;
	JLabel l;
	JPanel Result;
	JPanel Btt;
	JPanel Print;
	Boolean azione=false;
	
	public GUI(Socket s){
		
		this.clientSocket=s;
		
		//create object of JFrame
		baseFrame =new JFrame(); 
		baseFrame.setTitle("Base Container"); 
		 
		//create object of JPanel
		contentPane=new JPanel(); 
		//contentPane.setBackground(Color.pink);
	
		//create object of JTextArea 
		t_area=new JTextField(20);
			
		t_area.setBounds(50,30, 150,100); 
		 
		//create object of JTextField
		tf=new JTextArea(); 
		tf.setBounds(100,100, 150, 100); 
		Result=new JPanel(); 
		Result.add(tf); 
	
		btnstampa = new JButton("Stampa"); 
		//Create a JButton object per stampare 
		btnstampa.setBounds(50,50,75,35); 
		btnprenota = new JButton("Prenota"); 
		//Create a JButton object per prenotare 
		btnprenota.setBounds(50,50,75,35); 
		Btt=new JPanel(); 
		Btt.add(btnprenota); 
		Btt.add(btnstampa); 
		
		//create object of JLabel
		label = new JLabel("Prenota o stampa gli eventi");
		label.setBounds(50,50,75,35);
		Print=new JPanel(); 
		Print.add(label); 
		
		l = new JLabel("");
		
		contentPane.add(Print); 
		//set dimensions for button 
		contentPane.add(t_area); 
		contentPane.add(Btt);
		contentPane.add(Btt); 
		
		t_area.getDocument().addDocumentListener( new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent de) {
				warn();
			}

			@Override
			public void removeUpdate(DocumentEvent de) {
				warn();
			}

			@Override
			public void insertUpdate(DocumentEvent de) {
				warn();
			}
			public void warn() {
				l.setText(t_area.getText());
			}
			});

	
		
		//action che quando clicchi prenota stampa output
		btnprenota.addActionListener(new MyListener(s, tf, l, azione=false));  


		//action che quando clicchi prenota stampa output
		btnstampa.addActionListener(new MyListener(s, tf, l, azione=true));
		
		contentPane.add(Result); 
		contentPane.setSize(400, 400);
		baseFrame.setSize(400, 400);
		baseFrame.add(contentPane); 
		baseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		baseFrame.setVisible(true); 
		}	
}