//package views;
//
//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//import java.awt.Frame;
//import java.awt.GridLayout;
//import java.awt.Panel;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//import engine.Game;
//import model.characters.Hero;
//import model.characters.Zombie;
//import model.collectibles.Collectible;
//import model.collectibles.Vaccine;
//import model.world.CharacterCell;
//import model.world.CollectibleCell;
//
//public class ShowMap extends GUI{
//	public ShowMap(JButton [][] buttons, JFrame frame, JPanel startPanel) {
//		frame.add(panel, BorderLayout.CENTER);
//        frame.setLocationRelativeTo(null);
//        panel2=new JPanel();
//        StartGame=new JButton("Start Game");
//        panel2.add(StartGame);
//        panel2.setVisible(true);
//        frame.add(panel2);
//        frame.add(panel2, BorderLayout.SOUTH);
//        panel2.setSize(1225,100); 
//        
//        //start game stuff
//        StartGame.setSize(1225, 100);
//        StartGame.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Game.startGame(selectedhero);
//				//line frame.getconentpane.setlayout bnull da eli bywade kol haga 
//				panel.removeAll();
//				panel2.removeAll();
//				panel.setVisible(false);
//				panel2.setVisible(false);
//				buttonsPanel = new JPanel();
//				ClickPanel = new JPanel();
//				ClickPanel.setLayout(new BorderLayout());
//				frame.getContentPane().setLayout(null);
//				
//				
//			//	startPanel = new JPanel();
//				startPanel.setLayout(new GridLayout(15,15));
//				startPanel.setBounds(0,0,950,700); //275  
//				frame.getContentPane().add(startPanel, BorderLayout.EAST);
//				
//				startPanel.setVisible(true);
//				
//				buttonsPanel.setLayout(new FlowLayout());
//				
//				JButton attack=new JButton("Attack");
//				JButton SpecialAction=new JButton("Special Action");
//				JButton Cure=new JButton("Cure");
//				JButton EndTurn=new JButton("EndTurn");
//				JButton SetCharacter=new JButton("Set Character");
//				buttonsPanel.add(EndTurn);
//				buttonsPanel.add(SetCharacter);
//				
//				buttonsPanel.add(Cure);
//				buttonsPanel.add(attack);
//				buttonsPanel.add(SpecialAction);
//				
//				buttonsPanel.setBounds(950,70,260,350);
//				buttonsPanel.setVisible(true);
//				JLabel information=new JLabel(CurrentHeroString(selectedhero));
//				buttonsPanel.add(information);}
//});
//        
//	}
//}
