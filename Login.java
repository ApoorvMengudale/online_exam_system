
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.*;
import java.awt.event.*;  
import javax.swing.*;  
import java.net.*;
import java.text.*;
import java.io.*;
import java.util.Random;
 
public class Login extends JFrame implements ActionListener
{
 JButton SUBMIT;
 JPanel panel;
 
 JLabel label1,label2;
 public static JTextField  text1,text2;
    private OnlineTest OnlineTest;
  Login()
  {
  label1 = new JLabel();
  label1.setText("Student Name:");
  text1 = new JTextField(15);

  label2 = new JLabel();
  label2.setText("Roll No");
  text2 = new JTextField(15);
 
  SUBMIT=new JButton("SUBMIT");
  
  panel=new JPanel(new GridLayout(3,1));
  panel.add(label1);
  panel.add(text1);
  panel.add(label2);
  panel.add(text2);
  panel.add(SUBMIT);
  add(panel,BorderLayout.CENTER);
  SUBMIT.addActionListener(this);
  setTitle("LOGIN FORM");
  }


 public void actionPerformed(ActionEvent ae)
  {
  if((text1.getText().isEmpty() == true) || (text2.getText().isEmpty() == true))
  {
          JOptionPane.showMessageDialog(this,"Please Enter you Name and RollNo");
          dispose();
  }
  else
  {
          dispose();
          OnlineTest tc  = new OnlineTest("Online Test Of Java");
  }
}
 public static void main(String arg[])
  {
  try
  {
  Login frame=new Login();
  frame.setSize(600,400);
  frame.setVisible(true);
  }
  catch(Exception e)
  {JOptionPane.showMessageDialog(null, e.getMessage());}
  }
 
}
class OnlineTest extends JFrame implements ActionListener,Runnable  
{  
    public static int[] myNumbers = null;    
    JFrame jframe = new JFrame();
    Thread t=null;  
    int hours=0, minutes=0, seconds=0;  
    String timeString = "";  
    JButton b;
    JLabel l;  
    JRadioButton jb[]=new JRadioButton[5];  
    JButton b1,b2;  
    ButtonGroup bg;
    static int count=0;
    public int current=0;
    int abc = 1;
    int x=1,y=1,now=0;  
    int m[]=new int[10];      
    private Object text2;
    OnlineTest(String s)  
    {  
        super(s); 
         t = new Thread(this);  
        t.start();  
      
    b=new JButton();  
    b.setBounds(460,30,100,50);  
    jframe.add(b);  
    jframe.setSize(300,400);  
    jframe.setLayout(null);   
     
        l=new JLabel();  
        jframe.add(l);  
        bg=new ButtonGroup();  
        for(int i=0;i<5;i++)  
        {  
            jb[i]=new JRadioButton();     
            jframe.add(jb[i]);  
            bg.add(jb[i]);  
        }  
        b1=new JButton("Next");  
        b2=new JButton("Finish");
        b2.setEnabled(false);  
        b1.addActionListener(this);  
        b2.addActionListener(this);  
        jframe.add(b1);
        jframe.add(b2);
        rando();
        set();  
        l.setBounds(30,40,450,20);  
        jb[0].setBounds(50,80,100,20);  
        jb[1].setBounds(50,110,100,20);  
        jb[2].setBounds(50,140,100,20);  
        jb[3].setBounds(50,170,100,20);  
        b1.setBounds(100,240,100,30);  
        b2.setBounds(270,240,100,30);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        jframe.setLayout(null);  
        jframe.setLocation(250,100);  
        jframe.setVisible(true);  
        jframe.setSize(600,350);  
    }  
    public void actionPerformed(ActionEvent e)  
    {  
        if(e.getSource()==b1)  
        { 
            if(abc == 1)
            {
            current = myNumbers[0];
            }
            if(abc == 2)
            {
            current = myNumbers[1];
            }
            if(abc == 3)
            {
            current = myNumbers[2];
            }
            if(abc == 4)
            {
            current = myNumbers[3];
            }
            
            if(abc == 5)
            {
            current = myNumbers[4];
            }
            if(abc == 6)
            {
            current = myNumbers[5];
            }
            
            if(abc == 7)
            {
            current = myNumbers[6];
            }
            if(abc == 8)
            {
            current = myNumbers[7];
            }
            if(abc == 9)
            {
            current = myNumbers[8];
            }if(abc == 10)
            {
            current = myNumbers[9];
            }
            
            abc++;
            if(check())  
                count=count+1; 
            set();    
            if(abc > 10)  
            {  
                b1.setEnabled(false);
                b2.setEnabled(true);  
                b2.setText("Finish");  
            }  
        }  
        if(e.getActionCommand().equals("Bookmark"))  
        {  
            JButton bk=new JButton("Bookmark"+x);  
            bk.setBounds(480,20+30*x,100,30);  
            add(bk);  
            bk.addActionListener(this);  
            m[x]=current;  
            x++;  
            //current++;  
            set();    
            if(abc > 10)  
                b2.setText("Finish");  
            setVisible(false);  
            setVisible(true);  
        }  
        for(int i=0,y=1;i<x;i++,y++)  
        {  
        if(e.getActionCommand().equals("Bookmark"+y))  
        {  
            if(check())  
                count=count+1;  
            now=current;  
            current=m[y];  
            set();  
            ((JButton)e.getSource()).setEnabled(false);  
            current=now;  
        }  
        }  
      
        if(e.getActionCommand().equals("Finish"))  
        {  
            
            if(check())  
                count=count+1;  
            //current++;  
            //System.out.println("correct ans="+count);  
            //JOptionPane.showMessageDialog(this,"correct ans="+count); 
            
            try
    {
        
        Socket sk=new Socket("localhost",5890);
		BufferedReader sin=new BufferedReader(new InputStreamReader(sk.getInputStream()));
		PrintStream sout=new PrintStream(sk.getOutputStream());
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		String s,s1,s2;
		while (  true )
		{
			System.out.print("Client : ");
			s = Login.text1.getText();;
                        s1 =Login.text2.getText();;
                        s2 =Integer.toString(count);;
			sout.println(s);
                        sout.println(s1);
                        sout.println(s2);
                        
                        //if ( s.equalsIgnoreCase("BYE") )
                        //{
                           
                        //}
			
			System.out.print("Server : "+s+"\n");
                        System.out.print("Server : "+s1+"\n");
                        System.out.print("Server : "+s2+"\n");
                        
                        System.out.println("Connection ended by client");
 			   break;
  			
		}
		 sk.close();
		 sin.close();
		 sout.close();
 		stdin.close();
        //new OnlineTest("Online Test Of Java");  
    }catch(Exception ae)
    {}
            System.exit(0);  
        }  
    } 
    public void rando()
    {
        try
      {
          myNumbers = new int[10];
        Random r = new Random();
          int total_elements_cnt = 0;
          boolean loop_status = true;
          while(loop_status)
          {
              int next_num = r.nextInt(10)+1;          
              if(!isCompleted()){
                  if(!isDuplicate(next_num)){
                      myNumbers[total_elements_cnt] = next_num;
                      total_elements_cnt++;
                  }else{
                      continue;
                  }
              }else{
                  loop_status = false;
              }
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
    }
    public static boolean isCompleted(){
      boolean status = true;
      for (int i = 0; i < myNumbers.length; i++){
          if(myNumbers[i]==0){
              status = false;
              break;
          }
      }
      return  status;
  }
  public static boolean isDuplicate(int num){
      boolean status = false;
      for (int i = 0; i < myNumbers.length; i++){
          if(myNumbers[i]== num){
              status = true;
              break;
          }
      }
      return  status;
  }
    public void run() {  
      try {  
          
         while (true) {  
  
            Calendar cal = Calendar.getInstance();  
            hours = cal.get( Calendar.HOUR_OF_DAY );  
            if ( hours > 12 ) hours -= 12;  
            minutes = cal.get( Calendar.MINUTE );  
            seconds = cal.get( Calendar.SECOND );  
  
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");  
            Date date = cal.getTime();  
            timeString = formatter.format( date );  
  
            printTime();  
  
            t.sleep( 1000 );  // interval given in milliseconds  
         }  
      }  
      catch (Exception e) { }  
 }
      
public void printTime(){  
b.setText(timeString);  
String tim = "00:00:00";
if(timeString.equals(tim))
{
    System.exit(0);
}
}

    void set()  
    {   
        jb[4].setSelected(true);  
        if(current==1)  
        {  
            l.setText("Que1: Which one among these is not a primitive datatype?");  
            jb[0].setText("int");jb[1].setText("Float");jb[2].setText("boolean");jb[3].setText("char");   
        }  
        if(current==2)  
        {  
            l.setText("Que2: Which class is available to all the class automatically?");  
            jb[0].setText("Swing");jb[1].setText("Applet");jb[2].setText("Object");jb[3].setText("ActionEvent");  
        }  
        if(current==3)  
        {  
            l.setText("Que3: Which package is directly available to our class without importing it?");  
            jb[0].setText("swing");jb[1].setText("applet");jb[2].setText("net");jb[3].setText("lang");  
        }  
        if(current==4)  
        {  
            l.setText("Que4: String class is defined in which package?");  
            jb[0].setText("lang");jb[1].setText("Swing");jb[2].setText("Applet");jb[3].setText("awt");  
        }  
        if(current==5)  
        {  
            l.setText("Que5: Which institute is best for java coaching?");  
            jb[0].setText("Utek");jb[1].setText("Aptech");jb[2].setText("SSS IT");jb[3].setText("jtek");  
        }  
        if(current==6)  
        {  
            l.setText("Que6: Which one among these is not a keyword?");  
            jb[0].setText("class");jb[1].setText("int");jb[2].setText("get");jb[3].setText("if");  
        }  
        if(current==7)  
        {  
            l.setText("Que7: Which one among these is not a class? ");  
            jb[0].setText("Swing");jb[1].setText("Actionperformed");jb[2].setText("ActionEvent");  
                        jb[3].setText("Button");  
        }  
        if(current==8)  
        {  
            l.setText("Que8: which one among these is not a function of Object class?");  
            jb[0].setText("toString");jb[1].setText("finalize");jb[2].setText("equals");  
                        jb[3].setText("getDocumentBase");         
        }  
        if(current==9)  
        {  
            l.setText("Que9: which function is not present in Applet class?");  
            jb[0].setText("init");jb[1].setText("main");jb[2].setText("start");jb[3].setText("destroy");  
        }  
        if(current==10)  
        {  
            l.setText("Que10: Which one among these is not a valid component?");  
            jb[0].setText("JButton");jb[1].setText("JList");jb[2].setText("JButtonGroup");  
                        jb[3].setText("JTextArea");  
        }  
        l.setBounds(30,40,450,20);  
        for(int i=0,j=0;i<=90;i+=30,j++)  
            jb[j].setBounds(50,80+i,200,20);  
    }  
    boolean check()  
    {  
        if(current==1)  
            return(jb[3].isSelected());  
        if(current==2)  
            return(jb[3].isSelected());  
        if(current==3)  
            return(jb[3].isSelected());  
        if(current==4)  
            return(jb[3].isSelected());  
        if(current==5)  
            return(jb[3].isSelected());  
        if(current==6)  
            return(jb[3].isSelected());  
        if(current==7)  
            return(jb[3].isSelected());  
        if(current==8)  
            return(jb[3].isSelected());  
        if(current==9)  
            return(jb[3].isSelected());  
        if(current==10)  
            return(jb[3].isSelected());  
        return false;  
    }    
}  