package GUI;

import javax.swing.*;

public class GUI extends JFrame {

   private static final long serialVersionUID = 1L;
   JButton prenota;
   JLabel  nameLabel;
   JLabel numLabel;
   JTextField t;
   JTextField seats;

   public GUI() {
      super("Swing");

      nameLabel = new JLabel("Nome evento: ");
      t = new JTextField(16);
      numLabel = new JLabel("Posti da Prenotare: ");
      seats = new JTextField(3);
      prenota = new JButton("Prenota");

      MyListener step_handler  = new MyListener(this);
      prenota.addActionListener(step_handler);

      JPanel Panel = new JPanel();
      Panel.add(nameLabel);
      Panel.add(t);
      Panel.add(numLabel);
      Panel.add(seats);
      Panel.add(prenota);
      
      getContentPane().add(Panel);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
      pack();
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
               new GUI();
         }
      });
   }
}