package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Collectible;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import model.characters.Character;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GUI2 extends JFrame implements ActionListener{
	private JButton rightS;
	private JButton leftS;
	private JPanel panel=new JPanel(new GridLayout(1, 2));;
	private JFrame frame=new JFrame();
	private JPanel panel2;
	private JPanel startPanel;
	private JPanel MovePanel;
	private JPanel ClickPanel;
	private JPanel buttonsPanel;
	private JButton StartGame;
	private JButton up;
	private JButton down;
	private JButton left;
	private JButton right;
	private JButton EndTurn;
	private JButton Cure;
	private JButton attack;
	private JButton SpecialAction;
	private JButton SetCharacter;
	private JButton SetTarget;
	private JLabel image=new JLabel();
	private JPanel WinPanel= new JPanel();
	private JPanel losePanel=new JPanel();
	private JPanel IntroPanel=new JPanel();
	private JPanel LetterPanel=new JPanel();
	private JPanel readyPanel=new JPanel();
	private JPanel groupPanel=new JPanel();
	private JButton ready;
	private JLabel Startpic=new JLabel();
	private JLabel Letterpic=new JLabel();
	private JLabel zombiePanel=new JLabel();
	
	private JButton newGame;
	private int i;
	private Hero selectedhero;
	private Character selectedTarget;
	private JButton [][] buttons=new JButton[15][15];
	private int k;
	private int n;
	private JLabel information= new JLabel("hi");
	private String[] images=setArrayImg();
	private Clip soundClip = null;
	private Clip soundClip2=null;
	
	public GUI2(){
		String sound = "Last of us Audio.wav";
        play(sound);
		frame.setSize(1225,750);
		frame.setTitle("The Last Of Us - Legacy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        IntroPanel.setSize(1225,600);
        IntroPanel.setLayout(new BorderLayout());
        Startpic.setIcon(new ImageIcon("Startpic.jpg"));
        newGame= new JButton("New Game") ;
        newGame.setForeground(Color.WHITE);
        newGame.setBackground(Color.DARK_GRAY);
        newGame.addActionListener(this);
        IntroPanel.add(Startpic , BorderLayout.CENTER);
        IntroPanel.add(newGame ,BorderLayout.SOUTH);
        IntroPanel.setBackground(Color.DARK_GRAY);
        IntroPanel.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(IntroPanel);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setVisible(true);
        
        
}
	public void letter() {
		IntroPanel.removeAll();
        IntroPanel.setVisible(false);
		LetterPanel.setSize(1225,600);
		LetterPanel.setLayout(new FlowLayout());
        Letterpic.setIcon(new ImageIcon("letter.png"));
        LetterPanel.add(Letterpic );
        LetterPanel.setBackground(Color.DARK_GRAY);
        readyPanel.setSize(100,100);
        readyPanel.setLayout(new BorderLayout());
        ready= new JButton("I'm Ready!") ;
        ready.setForeground(Color.WHITE);
        ready.setBackground(Color.DARK_GRAY);
        ready.addActionListener(this);
        readyPanel.add(ready ,BorderLayout.SOUTH);
        readyPanel.setBackground(Color.DARK_GRAY);
        LetterPanel.setVisible(true);
        readyPanel.setVisible(true);
        groupPanel.setSize(1000,600);
        groupPanel.setLayout(new BorderLayout());
        groupPanel.add(LetterPanel);
        groupPanel.add(readyPanel);
        groupPanel.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(groupPanel);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setVisible(true);
	}
	public void select() {
		groupPanel.removeAll();
        groupPanel.setVisible(false);
		try {
			Game.loadHeroes("Heroes.csv");
			
		}
		catch(IOException e) {
			JOptionPane.showMessageDialog(null, "No available Heroes", "Error",
					JOptionPane.ERROR_MESSAGE); //edit
			return;
		}
        panel.setSize(1225,600);
        
        panel.setBackground(Color.lightGray);
        leftS=new JButton();
        leftS.setIcon(new ImageIcon("Leftw.png"));
        leftS.addActionListener(this);
        leftS.setBackground(Color.DARK_GRAY);
        panel.add(leftS);
        panel.add(new JLabel(new ImageIcon("Joel start.png")));
        JLabel temp = new JLabel(HeroString(Game.availableHeroes.get(i)));;
		temp.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		panel.add(temp);
        i=0;
       
        rightS=new JButton();
        rightS.setIcon(new ImageIcon("Rightw.png"));
        rightS.addActionListener(this);
        rightS.setForeground(Color.WHITE);
        rightS.setBackground(Color.DARK_GRAY);
        panel.add(rightS);
        
        
        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        panel2=new JPanel();
        
        selectedhero =Game.availableHeroes.get(i);
        StartGame=new JButton("Start Game");
        StartGame.addActionListener(this);
        StartGame.setForeground(Color.WHITE);
        StartGame.setBackground(Color.DARK_GRAY);
        panel2.add(StartGame);
        panel2.setBackground(Color.DARK_GRAY);
        panel2.setVisible(true);
        

        frame.add(panel2);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.add(panel2, BorderLayout.SOUTH);
        panel2.setSize(1225,100); 
        frame.setVisible(true);
        StartGame.setSize(1225, 100);
        

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== newGame) {
			letter();
		}
		if(e.getSource()== ready) {
			select();
		}
			if (e.getSource()==rightS) {
				if(i==7) {
					i=0;
				}
				else {
					i++;
				}
				selectedhero=Game.availableHeroes.get(i);
				this.panel.removeAll();
				panel.add(leftS);
				panel.add(new JLabel(new ImageIcon(images[i])));
				JLabel temp = new JLabel(HeroString(Game.availableHeroes.get(i)));;
				temp.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
				panel.add(temp);
				
				panel.add(rightS);
				panel.setVisible(true);
				frame.setVisible(true);
			}
			
			if (e.getSource()==leftS) {
				if(i==0) {
					i=7;
				}
				else {
					i--;
				}
				selectedhero=Game.availableHeroes.get(i);
				panel.setVisible(false);
				panel.removeAll();
				panel.add(leftS);
				panel.add(new JLabel(new ImageIcon(images[i])));
				JLabel temp = new JLabel(HeroString(Game.availableHeroes.get(i)));;
				temp.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
				panel.add(temp);
				panel.setVisible(true);
				panel.add(rightS);
			}
			
			for (int i=0;i<buttons.length;i++) {
				for (int j=0;j<buttons.length;j++) {
					if(e.getSource()==buttons[i][j]) {
					if (Game.map[i][j] instanceof CharacterCell) {
					if (((CharacterCell) Game.map[i][j]).getCharacter() instanceof Hero) {
						selectedTarget=(Hero) ((CharacterCell) Game.map[i][j]).getCharacter();
						JOptionPane.showMessageDialog(null, CurrentHeroStringMsg((Hero)selectedTarget) , null,
								JOptionPane.PLAIN_MESSAGE);
					}
					else if ((((CharacterCell) Game.map[i][j]).getCharacter() instanceof Zombie)) {
						selectedTarget=(Character) ((CharacterCell) Game.map[i][j]).getCharacter();
						
//						JOptionPane.showMessageDialog(null, CurrentZombieString((Zombie)selectedTarget) , null,
//								JOptionPane.WARNING_MESSAGE);
						zombiePanel.setText(CurrentZombieString((Zombie)selectedTarget));
						zombiePanel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
						zombiePanel.setForeground(Color.WHITE);
					}
					else {
						selectedTarget=selectedhero;
						zombiePanel.setText("");

						}
					}
				} 
					}
			}
			if(e.getSource()==StartGame) {
				start();
				
				
			}
			if(e.getSource()==up) {
				 try {	int size=Game.heroes.size();
					 	int vacCount=selectedhero.getVaccineInventory().size();
					 	int SupCount=selectedhero.getSupplyInventory().size();
					 	int prevHp=selectedhero.getCurrentHp();
				    	selectedhero.move(Direction.DOWN);
				    	play("walking sound.wav");
				    	ShowMap();
				    	information.setText(CurrentHeroString(selectedhero));
				    	int newHp=selectedhero.getCurrentHp();
				    	if (newHp<prevHp) {
				    		JOptionPane.showMessageDialog(null, "you have fallen into a trap cell", null,
									JOptionPane.WARNING_MESSAGE);
				    		HeroDied(size);
				    		
				    	}
				    	if(vacCount<selectedhero.getVaccineInventory().size() || SupCount<selectedhero.getSupplyInventory().size() ) {
				    		play("Collect sound.wav");
				    	}
				    	else {
				    		play("walking sound.wav");
				    	}

			    }
			    catch (NotEnoughActionsException e1)
			    		 {
			    	JOptionPane.showMessageDialog(null, "Not enough actions", null,
							JOptionPane.ERROR_MESSAGE); //edit
					return;
			    }
			    catch(MovementException e1) {
			    	JOptionPane.showMessageDialog(null, "Wrong Movement", null,
							JOptionPane.ERROR_MESSAGE);
			    	return;
			    }
				
			}
			if(e.getSource()==down) {
				 try { 	int size=Game.heroes.size();
					 	int vacCount=selectedhero.getVaccineInventory().size();
				 		int SupCount=selectedhero.getSupplyInventory().size();
					    int prevHp=selectedhero.getCurrentHp();
				    	selectedhero.move(Direction.UP);
				    	play("walking sound.wav");
				    	ShowMap();
				    	information.setText(CurrentHeroString(selectedhero));
				    	int newHp=selectedhero.getCurrentHp();
				    	if (newHp<prevHp) {
				    		JOptionPane.showMessageDialog(null, "you have fallen into a trap cell", null,
									JOptionPane.WARNING_MESSAGE);
				    		HeroDied(size);
				    		
				    	}
				    	if(vacCount<selectedhero.getVaccineInventory().size() || SupCount<selectedhero.getSupplyInventory().size() ) {
				    		play("Collect sound.wav");
				    	}
				    	else {
				    		play("walking sound.wav");
				    	}

			    }
			    catch (NotEnoughActionsException e1)
			    		 {
			    	JOptionPane.showMessageDialog(null, "Not enough actions", null,
							JOptionPane.ERROR_MESSAGE); //edit
					return;
			    }
			    catch(MovementException e1) {
			    	JOptionPane.showMessageDialog(null, "Wrong Movement", null,
							JOptionPane.ERROR_MESSAGE);
			    	return;
			    }
				
			}
			if(e.getSource()==left) {
				 try {	int size=Game.heroes.size();
					 	int vacCount=selectedhero.getVaccineInventory().size();
					 	int SupCount=selectedhero.getSupplyInventory().size();
					 	int prevHp=selectedhero.getCurrentHp();
				    	selectedhero.move(Direction.LEFT);
				    	play("walking sound.wav");
				    	ShowMap();
				    	information.setText(CurrentHeroString(selectedhero));
				    	int newHp=selectedhero.getCurrentHp();
				    	if (newHp<prevHp) {
				    		JOptionPane.showMessageDialog(null, "you have fallen into a trap cell", null,
									JOptionPane.WARNING_MESSAGE);
				    		HeroDied(size);
				 }
				    	if(vacCount<selectedhero.getVaccineInventory().size() || SupCount<selectedhero.getSupplyInventory().size() ) {
				    		play("Collect sound.wav");
				    	}
				    	else {
				    		play("walking sound.wav");
				    	}
				    }
			    
			    catch (NotEnoughActionsException e1)
			    		 {
			    	JOptionPane.showMessageDialog(null, "Not enough actions", null,
							JOptionPane.ERROR_MESSAGE); //edit
					return;
			    }
			    catch(MovementException e1) {
			    	JOptionPane.showMessageDialog(null, "Wrong Movement", null,
							JOptionPane.ERROR_MESSAGE);
			    	return;
			    }
				
			}
			if(e.getSource()==right) {
				 try {	int size=Game.heroes.size();
					 	int vacCount=selectedhero.getVaccineInventory().size();
					 	int SupCount=selectedhero.getSupplyInventory().size();
					 	int prevHp=selectedhero.getCurrentHp();
				    	selectedhero.move(Direction.RIGHT);
				    	
				    	ShowMap();
				    	information.setText(CurrentHeroString(selectedhero));
				    	int newHp=selectedhero.getCurrentHp();
				    	if (newHp<prevHp) {
				    		JOptionPane.showMessageDialog(null, "you have fallen into a trap cell", null,
									JOptionPane.WARNING_MESSAGE);
				    		HeroDied(size);
				    		
				    	}
				    	if(vacCount<selectedhero.getVaccineInventory().size() || SupCount<selectedhero.getSupplyInventory().size() ) {
				    		play("Collect sound.wav");
				    	}
				    	else {
				    		play("walking sound.wav");
				    	}
			    }
			    catch (NotEnoughActionsException e1)
			    		 {
			    	JOptionPane.showMessageDialog(null, "Not enough actions", null,
							JOptionPane.ERROR_MESSAGE); //edit
					return;
			    }
			    catch(MovementException e1) {
			    	JOptionPane.showMessageDialog(null, "Wrong Movement", null,
							JOptionPane.ERROR_MESSAGE);
			    	return;
			    }
				
			}
			
			if(e.getSource()==EndTurn) {
				try {
					int size=Game.heroes.size();
					Game.endTurn();
					play("End turn sound.wav");
					HeroDied(size);
					ShowMap();
					information.setText(CurrentHeroString(selectedhero));
					
				}
				catch(NotEnoughActionsException e1){
					JOptionPane.showMessageDialog(null, "Not Enough Actions", null,
							JOptionPane.ERROR_MESSAGE);
			    	return;
				}
				catch(InvalidTargetException e1){
					JOptionPane.showMessageDialog(null, "Invalid target", null,
							JOptionPane.ERROR_MESSAGE);
			    	return;
				}
			}
			
			if(e.getSource()==attack) {
				try {
					selectedhero.setTarget(selectedTarget);
					int size=Game.heroes.size();
					selectedhero.attack();
					HeroDied(size);
					play("Attack sound.wav");
					ShowMap();
					information.setText(CurrentHeroString(selectedhero));
					selectedhero.setTarget(null);
					selectedTarget=null;
					zombiePanel.setText("");
			}
				catch(NotEnoughActionsException e1) {
					JOptionPane.showMessageDialog(null, "Not Enough Actions", null,
							JOptionPane.ERROR_MESSAGE);
			    	return;
				}
				catch(InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null, "Invalid Target", null,
							JOptionPane.ERROR_MESSAGE);
			    	return;
				}
			}
			
			if(e.getSource()==SpecialAction) {
				try {
					selectedhero.setTarget(selectedTarget);
					selectedhero.useSpecial();
					play("use special sound.wav");
					ShowMap();
					information.setText(CurrentHeroString(selectedhero));
			}
				catch(NoAvailableResourcesException e1) {
					JOptionPane.showMessageDialog(null, "No Available Resource", null,
							JOptionPane.ERROR_MESSAGE);
			    	return;
				}
				catch(InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null, "Invalid Target", null,
							JOptionPane.ERROR_MESSAGE);
			    	return;
					
				}
			}
			
			if(e.getSource()==Cure) {
				try {
					selectedhero.setTarget(selectedTarget);
					selectedhero.cure();
					play("cure sound.wav");
					ShowMap();
					information.setText(CurrentHeroString(selectedhero));
					selectedhero.setTarget(null);
					selectedTarget=null;
					zombiePanel.setText("");
				}
				catch(NoAvailableResourcesException e1) {
					JOptionPane.showMessageDialog(null, "No Available Resource", null,
							JOptionPane.ERROR_MESSAGE);
			    	return;
				}
				catch(InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null, "Invalid Target", null,
							JOptionPane.ERROR_MESSAGE);
			    	return;
					
				} catch (NotEnoughActionsException e1) {
					JOptionPane.showMessageDialog(null, "You do not have enough actions", null,
							JOptionPane.ERROR_MESSAGE);
			    	return;
				}
			}
			
			if(e.getSource()==SetCharacter) {
				if (selectedTarget instanceof Hero) {
				selectedhero=(Hero) selectedTarget;
				System.out.println(selectedhero.getName());
				information.setText(CurrentHeroString(selectedhero));
				play("Switch character.wav");
				
			}
		}
			if(Game.checkWin()) {
				if(startPanel!=null) {
	        	startPanel.setVisible(false);
	        	ClickPanel.setVisible(false);
	        	buttonsPanel.setVisible(false);
	        	MovePanel.setVisible(false);
	        	startPanel.removeAll();
	        	ClickPanel.removeAll();
	        	buttonsPanel.removeAll();
	        	MovePanel.removeAll();
	        	image.setIcon(new ImageIcon("happy.jpg"));
	        	WinPanel.add(image);
	        	WinPanel.setVisible(true);
	        	frame.setContentPane(WinPanel);
	        	frame.setVisible(true);
	        	soundClip2.close();
	        	play("Win and lose sound.wav");
				}
	        }
		
	        else if(Game.checkGameOver()){
	        	if(startPanel!=null) {
	        	startPanel.setVisible(false);
	        	ClickPanel.setVisible(false);
	        	buttonsPanel.setVisible(false);
	        	MovePanel.setVisible(false);
	        	startPanel.removeAll();
	        	ClickPanel.removeAll();
	        	MovePanel.removeAll();
	        	buttonsPanel.removeAll();
	        	image.setIcon(new ImageIcon("sad.jpg"));
	        	losePanel.add(image);
	        	losePanel.setVisible(true);
	        	frame.setContentPane(losePanel);
	        	frame.setVisible(true);
	        	soundClip2.close();
	        	play("Win and lose sound.wav");
	        	}
	        	
	        }
			
			
	}
			
			
		
	
	
	public void HeroDied(int size) {
		if(Game.heroes.size()<size) {
			play("hero death sound.wav");
			JOptionPane.showMessageDialog(null, "One of your heroes has died", null,
					JOptionPane.INFORMATION_MESSAGE);
			if(Game.heroes.size()!=0) {
				selectedhero=Game.heroes.get(0);
				information.setText(CurrentHeroString(selectedhero));
			}
		}
		
		
		
	}
	
	
	
	
	
	
	public String[] setArrayImg() {
		String[]res=new String[8];
		res[0]="Joel start.png";
		res[1]="Ellie start.png";
		res[2]="Tess start.png";
		res[3]="Riley start.png";
		res[4]="Tommy start.png";
		res[5]="Billy start.png";
		res[6]="David start.png";
		res[7]="Henry start.png";
		return res;
		}
	
	public static String HeroString(Hero h) {
		String s;
		String type="";
		if(h instanceof Fighter)
			type="Fighter";
		else 
			if(h instanceof Medic)
				type="Medic";
			else
				if(h instanceof Explorer)
					type="Explorer";
		
		s="<html><p>"+"Name: "+h.getName()+"<br/>"+
		"Hero Type: "+type+"<br/>"+
		"Max Health Points: "+h.getMaxHp()+"<br/>"+
		"Attack Damage: "+h.getAttackDmg()+"<br/>"+
		"Max Actions: "+h.getMaxActions()+"</p></html>";
		
		return s;
	}
	
	
	public static String CurrentHeroString(Hero h) {
		String s;
		String type="";
		if(h instanceof Fighter)
			type="Fighter";
		else 
			if(h instanceof Medic)
				type="Medic";
			else
				if(h instanceof Explorer)
					type="Explorer";
		
		s="<html><p>"+"Name: "+h.getName()+"<br/>"+
		"Hero Type: "+type+"<br/>"+
		"Current Health Points: "+h.getCurrentHp()+"<br/>"+
		"Attack Damage: "+h.getAttackDmg()+"<br/>"+
		"Remaining Actions: "+h.getActionsAvailable()+"<br/>"+
		"Used Special?: "+h.isSpecialAction()+"<br/>"
		+"Number of Vaccines: "+h.getVaccineInventory().size()+"<br/>"+
		"Number of Supplies: "+h.getSupplyInventory().size()+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+
		"Move Options: "+"</p></html>";
		
		return s;
	}
	
	public static String CurrentHeroStringMsg(Hero h) {
		String s;
		String type="";
		if(h instanceof Fighter)
			type="Fighter";
		else 
			if(h instanceof Medic)
				type="Medic";
			else
				if(h instanceof Explorer)
					type="Explorer";
		
		s="<html><p>"+"Name: "+h.getName()+"<br/>"+
		"Hero Type: "+type+"<br/>"+
		"Max Health Points: "+h.getCurrentHp()+"<br/>"+
		"Attack Damage: "+h.getAttackDmg()+"<br/>"+
		"Remaining Actions: "+h.getActionsAvailable()+"<br/>"+
		"Used Special?: "+h.isSpecialAction()+"<br/>"
		+"Number of Vaccines: "+h.getVaccineInventory().size()+"<br/>"+
		"Number of Supplies: "+h.getSupplyInventory().size()+"<br/>"+"</p></html>";
		
		return s;
	}
	
	public static String CurrentZombieString(Zombie z) {
		String s;
		String type="";
		s="<html><p>"+"Target Zombie information: "+"<br/>"+"<br/>"+"Name: "+z.getName()+"<br/>"+
		"Current Health Points: "+z.getCurrentHp()+"<br/>"+
		"Attack Damage: "+z.getAttackDmg()+"<br/>"+
		"</p></html>";
		
		return s;
	}
	
	public void start() {
		soundClip.close();
		play("Start game sound.wav");
		Game.startGame(selectedhero);
		panel.removeAll();
		panel2.removeAll();
		
		panel.setVisible(false);
		panel2.setVisible(false);
		
		
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.DARK_GRAY);
		buttonsPanel.setLayout(new FlowLayout());
		
		ClickPanel = new JPanel();
		ClickPanel.setBackground(Color.DARK_GRAY);
		ClickPanel.setLayout(new BorderLayout()); //move
		
		frame.getContentPane().setLayout(null);
		
		zombiePanel.setText("");
//		MovePanel.add(zombiePanel, 0);
		frame.getContentPane().add(zombiePanel);
		zombiePanel.setBounds(1000,540,250,150);
		
		startPanel = new JPanel();
		startPanel.setBackground(Color.DARK_GRAY);
		startPanel.setLayout(new GridLayout(15,15));
		startPanel.setBounds(0,0,950,700); 
		frame.getContentPane().add(startPanel, BorderLayout.EAST);
		startPanel.setVisible(true); //Map
		

		
		MovePanel=new JPanel(new BorderLayout());
		MovePanel.setBackground(Color.DARK_GRAY);
		
		up=new JButton();
		up.setIcon(new ImageIcon("Up.png"));
		up.setBackground(Color.LIGHT_GRAY);
		up.addActionListener(this);
		MovePanel.add(up , BorderLayout.NORTH);
		
		down=new JButton();
		down.setBackground(Color.LIGHT_GRAY);
		down.setIcon(new ImageIcon("Down.png"));
		down.addActionListener(this);
		MovePanel.add(down , BorderLayout.SOUTH);
		
		left=new JButton();
		left.setBackground(Color.LIGHT_GRAY);
		left.setIcon(new ImageIcon("Left.png"));
		left.addActionListener(this);
		MovePanel.add(left, BorderLayout.WEST);
		
		right=new JButton();
		right.setBackground(Color.LIGHT_GRAY);
		right.setIcon(new ImageIcon("Right.png"));
		right.addActionListener(this);
		MovePanel.add(right ,BorderLayout.EAST);


		MovePanel.setVisible(true);
		MovePanel.setBounds(1000,390,150,150);
		
		frame.getContentPane().add(buttonsPanel);
		frame.getContentPane().add(MovePanel);
		frame.getContentPane().add(ClickPanel);
		
		attack=new JButton("Attack");
		attack.setBackground(Color.LIGHT_GRAY);
		buttonsPanel.add(attack);
		attack.addActionListener(this);
		
		SpecialAction=new JButton("Special Action");
		SpecialAction.setBackground(Color.LIGHT_GRAY);
		buttonsPanel.add(SpecialAction);
		SpecialAction.addActionListener(this);
		
		Cure=new JButton("Cure");
		Cure.setBackground(Color.LIGHT_GRAY);
		buttonsPanel.add(Cure);
		Cure.addActionListener(this);
		
		EndTurn=new JButton("EndTurn");
		EndTurn.setBackground(Color.LIGHT_GRAY);
		buttonsPanel.add(EndTurn);
		EndTurn.addActionListener(this);
		
		SetCharacter=new JButton("Set Character");
		SetCharacter.setBackground(Color.LIGHT_GRAY);
		buttonsPanel.add(SetCharacter);
		SetCharacter.addActionListener(this);
		
		
		buttonsPanel.setBounds(950,70,260,300);
		buttonsPanel.setVisible(true);
		information=new JLabel(CurrentHeroString(selectedhero));;
		information.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		information.setForeground(Color.WHITE);
		buttonsPanel.add(information);
		
		
		buttons= new JButton[15][15];
		for(int i=0; i<buttons.length;i++) {
			for(int j=0; j<buttons.length;j++) {
				buttons[i][j]=new JButton();
				buttons[i][j].setSize(63, 60);
				buttons[i][j].addActionListener(this);
			    startPanel.add(buttons[i][j]);}
			}
		ShowMap();
		play("28. All Gone [The Outside].wav");
		
	}
	
	public void play(String sound) {
        AudioInputStream audio = null;
		try {
			audio= AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
			if(sound=="28. All Gone [The Outside].wav") {
				 try {
					 soundClip2 = AudioSystem.getClip();
					soundClip2.open(AudioSystem.getAudioInputStream(new File("28. All Gone [The Outside].wav")));
					 soundClip2.loop(Clip.LOOP_CONTINUOUSLY);
					 soundClip2.start();

				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		if(sound!="28. All Gone [The Outside].wav"){
        
		try {
			soundClip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
        try {
			soundClip.open(audio);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
        soundClip.start();
		}
	}
	
	public void ShowMap() {
		for(int i=0; i<buttons.length;i++) {
			for(int j=0; j<buttons.length;j++) {
				if (Game.map[i][j].isVisible()==true) {
					buttons[i][j].setIcon(new ImageIcon("Visible cell.png"));
					if (Game.map[i][j] instanceof CharacterCell) {
						if (((CharacterCell) Game.map[i][j]).getCharacter() instanceof Hero) {
							String characterName=((CharacterCell) Game.map[i][j]).getCharacter().getName()+".png";

									buttons[i][j].setIcon(new ImageIcon(characterName));
							}
						
						if (((CharacterCell) Game.map[i][j]).getCharacter() instanceof Zombie) {
							buttons[i][j].setIcon(new ImageIcon("Zombie on cell.png"));
						}
					}
					else if (Game.map[i][j] instanceof CollectibleCell) {
						Collectible c=((CollectibleCell)Game.map[i][j]).getCollectible();
						if (c instanceof Vaccine) {
							buttons[i][j].setIcon(new ImageIcon("Vaccine.png"));
						}
						else 
							buttons[i][j].setIcon(new ImageIcon("Supply.png"));
					}

				}
				else {
					buttons[i][j].setIcon(new ImageIcon("Not visible cell 2.png"));
				}
	}	zombiePanel.setText("");
		}
	}
	
	public static void main(String [] args) {
		GUI2 g= new GUI2();
	}
}