import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class TEST {
//clé
	static int a;
	static int b;
	static boolean clef=false;
	// alphabet
	static final char T[]={'a','b','c','d','e','f','g','h','i','j'
			,'k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	static String espace="";
	
	public static void main(String[] args) {
		
		// Interface Graphic
		
		Color col=new Color(50, 20, 5);
		Color transp=new Color(0, 0, 0, (float)0.6);
		Font fon=new Font("arial", Font.BOLD, 13);
		
		
	JFrame Fenetre=new JFrame();
		Container background= Fenetre.getContentPane();
		JPanel principale=new JPanel (null);
		JTextArea text=new JTextArea();
		JTextPane textC=new JTextPane();
		JLabel entrer=new JLabel("Text Entrer");
		JLabel resultat=new JLabel("Text Résultat");
		JTextField A = new JTextField();
		JTextField B = new JTextField();
		JButton cle = new JButton("Valider");
		JLabel ab=new JLabel("Donner la clef de chiffrement ( a , b )");
		JLabel affin=new JLabel("Chiffrement Affine");
		

		ab.setFont(fon); ab.setForeground(col);
		ab.setBounds(322, 255, 500, 15);

		affin.setFont(fon.deriveFont((float)30)); ab.setForeground(col);
		affin.setBounds(300, 45, 500, 45);
		principale.add(affin);
		
		
		A.setBounds(320, 280, 100, 30);		
		B.setBounds(460, 280, 100, 30);
		cle.setBounds(380, 320, 100, 30);
		
		
		
		
		
		//clé
		cle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i=0,j=0;
				String sA=A.getText(),sB=B.getText();
				
				while(i<sA.length())
				{ if(isNumber(sA.charAt(i)))	 i++;
				else break;
				}
				while(j<sB.length())
				{
				  if(isNumber(sB.charAt(j)))j++;
				  else break;
				}
				
				
				if(i==sA.length()&&j==sB.length()){
				
				a=Integer.parseInt(sA);
				b=Integer.parseInt(sB);
				if (Pgcd(a,26) != 1){
					JOptionPane.showMessageDialog(null, "a n'admet pas un inverse");
					B.setText("    b");B.setForeground(transp);
					A.setText("    a");A.setForeground(transp);
					clef=false;
				}else {JOptionPane.showMessageDialog(null, "Clef accepté");
				clef=true;
				JLabel cc = new JLabel("la clef est ("+a+','+b+")");}
				
				}else JOptionPane.showMessageDialog(null, "L'entrer doit être un entier");
			}
		});
		
		
		
		entrer.setFont(new Font("arial", Font.ITALIC, 20));
		entrer.setForeground(col);
		resultat.setFont(new Font("arial", Font.ITALIC, 20));
		
		
		
		text.setBounds(230, 120, 400, 120);
		textC.setBounds(230, 370, 400, 120);
		
		textC.setEditable(false);
		textC.setText("( Résultat )");
		textC.setForeground(col);
		textC.setFont(new Font("comic sans ms", Font.BOLD, 19));
		textC.setBorder(BorderFactory.createLineBorder(col, 2));
		TitledBorder bord=BorderFactory.createTitledBorder(BorderFactory.createLineBorder(col,2), "Entrer le texte");
		bord.setTitleFont(fon); bord.setTitleColor(col); text.setBorder(bord);

		StyledDocument doc = textC.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		
		//focus
		
		B.setText("    b");B.setForeground(transp);
		A.setText("    a");A.setForeground(transp);
		A.setFont(new Font("arial", Font.BOLD, 13));
		B.setFont(new Font("arial", Font.BOLD, 13));
A.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(A.getText().equals("")) {A.setText("    a");
				A.setForeground(transp);}
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				A.setForeground(col);
				A.setText("");
			}
		});
B.addFocusListener(new FocusListener() {
	
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(B.getText().equals("")) { B.setText("    b");
		B.setForeground(transp);}
	}
	
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		B.setForeground(col);
		B.setText("");
	}
});
         

		// botton
		JButton chiffrer=new JButton("Chiffrer");
		JButton dechiffrer=new JButton("Déchiffrer");
		
		chiffrer.setBounds(650, 260, 120, 45);
		dechiffrer.setBounds(110, 260, 120, 45);
		
				
		chiffrer.setIcon(new ImageIcon(new ImageIcon("c.png").getImage().getScaledInstance(20, 25, Image.SCALE_SMOOTH)));
		dechiffrer.setIcon(new ImageIcon(new ImageIcon("d.png").getImage().getScaledInstance(20, 25, Image.SCALE_SMOOTH)));
		cle.setIcon(new ImageIcon(new ImageIcon("clef.png").getImage().getScaledInstance(20, 25, Image.SCALE_SMOOTH)));


		//chiffrer
          chiffrer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(clef){
			String stext=text.getText();

			if(isAlphabet(stext)){
			int v,n=stext.length();
			String stextC = "";
			char c;
			for(int i=0;i<n;i++){
				if(stext.charAt(i)!=' '){
				c=Character.toLowerCase(stext.charAt(i));
				v=(Indice(c)*a+b)%26;
				c=T[v];
				stextC=stextC+c;
				if(i==30 || i==60 || i==90) stextC=stextC+"\n";
				}
				else{
					espace=espace+"'"+i+"'";
				}
			}
			textC.setText(stextC);
			System.out.println(espace);
			}else JOptionPane.showMessageDialog(null, "Text n'est pas valide");
			}else JOptionPane.showMessageDialog(null, "Aucun clef valide");
			}
		});


//déchiffrer
   dechiffrer.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(clef){
	String stext=text.getText();
	if(isAlphabet(stext)){
	int v,n=stext.length();
	String stextC = "";
	char c;
	
	int aprime= CalculeAi();
	System.out.println("prime:"+aprime);
	for(int i=0;i<n;i++){
		
		c=Character.toLowerCase(stext.charAt(i));
		if(c!=' '){
		v=Indice(c)-b;
		while(v<0) v=v+26;
		v=(v*aprime)%26;
		c=T[v];
		stextC=stextC+c;
		if(i==30 || i==60 || i==90) stextC=stextC+"\n";
		}
		
		
	}
	textC.setText(stextC);
	//System.out.println(espace);
		}else JOptionPane.showMessageDialog(null, "Text n'est pas valide");
	}else JOptionPane.showMessageDialog(null, "Aucun clef valide");
	}
});

		
		

        principale.add(A);
        principale.add(ab);
        principale.add(B);
        principale.add(cle);
		principale.add(chiffrer);
		principale.add(dechiffrer);
		principale.add(text);
		principale.add(textC);
		background.add(principale);
	// Methode pour la fenetre
	Fenetre.setTitle("Chiffrement Affine");
	Fenetre.setSize(860, 600);
	Fenetre.setDefaultCloseOperation(Fenetre.EXIT_ON_CLOSE);
	Fenetre.setLocationRelativeTo(null);
	Fenetre.setVisible(true);
	Fenetre.setResizable(false);
		
		
		
		
		//Menu
	JMenuBar menu=new JMenuBar();
	JMenu fichier=new JMenu("Fichier");
	JMenu edition=new JMenu("Edition");
	JMenu aide=new JMenu("Aide");
	JMenuItem exit=new JMenuItem("Sortir");
	JMenuItem x =new JMenuItem("?");
	
	x.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(null, "Le chiffre affine est une méthode de cryptographie basée sur un chiffrement par substitution mono-alphabétique, \nc'est-à-dire que la lettre d'origine n'est remplacée que par une unique autre lettre (il faut enrer une clé valide \n pour pouvoir chiffrer un texte)");
		}
		
	});aide.add(x);
	exit.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.exit(3);
			
		}
	});
	
	fichier.add(exit);
	menu.add(fichier);
	menu.add(edition);
	menu.add(aide);
	Fenetre.setJMenuBar(menu);
	
	
		
		
		
		
	}
	
	static int Indice(char c){
		for(int i=0;i<26;i++){
			if(c==T[i]) return i;
		}
		return -1;
	}
	static int taille(){
		int t=0;
		for(int i=0;i<espace.length();i++){
			if(espace.charAt(i)=='\'') t++;
		}
		return t/2;
		
	}
	
	
	
	
	
	static int CalculeAi(){
		
		for(int i = 0; i< 26  ; i++){
			if((a*i)%26==1) return i;
		}
		
		return -1;
	}

	static boolean isAlphabet(String s){
		for(int i=0;i<s.length();i++){
			if(!isAlphabet(s.charAt(i)))
				return false;
		}
		return true;
	}

	static boolean isAlphabet(char c){
		c=Character.toLowerCase(c);
		for(int i=0;i<T.length;i++){
			if(c==T[i]||c==' ') return true ;
		}
		return false;
		
	}
	
	static boolean isNumber(char c){
		switch(c){
		case '0':return true;
		case '1':return true;
		case '2':return true;
		case '3':return true;
		case '4':return true;
		case '5':return true;
		case '6':return true;
		case '7':return true;
		case '8':return true;
		case '9':return true;
			default:return false;
				
		}
	}
	static int Pgcd(int a,int b) {
		
		while (a*b != 0){
			if(a>b) a=a-b;
			else b=b-a;
			
		}
		if(a == 0)return b;
		else return a;
	}

}
