import java.io.BufferedReader;
import java.io.File;
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.Collections;

public class Server{
     
    int port;
    public static String s,s1,s2;
    ServerSocket server=null;
    Socket client=null;
    ExecutorService pool = null;
    int clientcount=0;
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);
    JFrame f = new JFrame();
    
      public static void main(String[] args) throws IOException {
        Server serverobj=new Server(5890);
        serverobj.startServer();
         
       }
    
    Server(int port){
        this.port=port;
        pool = Executors.newFixedThreadPool(5);
        
        model.addColumn("Name");
        model.addColumn("Rollno");
        model.addColumn("Marks");
        f.setSize(300, 300);
        f.add(new JScrollPane(table));
        f.setVisible(true);
       
    }

    public void toExcel(JTable table, File file){
		try{
			TableModel model = table.getModel();
			FileWriter excel = new FileWriter(file);

			for(int i = 0; i < model.getColumnCount(); i++){
				excel.write(model.getColumnName(i) + "\t");
			}

			excel.write("\n");

			for(int i=0; i< model.getRowCount(); i++) {
				for(int j=0; j < model.getColumnCount(); j++) {
					excel.write(model.getValueAt(i,j).toString()+"\t");
				}
				excel.write("\n");
			}

			excel.close();
		}catch(Exception e){ System.out.println(e); }
	}
    
    public void startServer() throws IOException {
       
        server=new ServerSocket(5890);
        System.out.println("Server Booted");
        System.out.println("Any client can stop the server by sending -1");
        
      
        while(true)
        {
            client=server.accept();
            clientcount++;
            ServerThread runnable= new ServerThread(client,clientcount,this);
            pool.execute(runnable);
            
            
        }
        
        
    }

    public class ServerThread implements Runnable {
        
        
        Server server=null;
        Socket client=null;
        BufferedReader cin;
        PrintStream cout;
        
        Scanner sc=new Scanner(System.in);
        int id;
        
        
        ServerThread(Socket client, int count ,Server server ) throws IOException {
            
    
            this.client=client;
            this.server=server;
            this.id=count;
            System.out.println("Connection "+id+"established with client "+client);
            
            cin=new BufferedReader(new InputStreamReader(client.getInputStream()));
            cout=new PrintStream(client.getOutputStream());
        
        }

        @Override
        public void run() {
             
            
    

          
            int x=1;
         try{
         while(true){
             
                        s= cin.readLine();
  			s1=cin.readLine();
                        s2=cin.readLine();
			System. out.print("Client("+id+") :"+s+"\n");
                        System. out.print("Client("+id+") :"+s1+"\n");
                        System. out.print("Client("+id+") :"+s2+"\n");
			System.out.print("Server : ");
		        model.addRow(new Object[] { s, s1,s2 });
                        //s=stdin.readLine();
                            s=sc.nextLine();
                          
                        if (s.equalsIgnoreCase("bye"))
                        {
                            cout.println("BYE");
                            x=0;
                            System.out.println("Connection ended by server");
                            toExcel(table, new File("C:\\Users\\Desktop\\PGBI.xls"));
                            break;
                            
                        }
			cout.println(s);
		}
		
                     
                cin.close();
                client.close();
		cout.close();
                if(x==0) {
			System.out.println( "Server cleaning up." );
			System.exit(0);
                }
         } 
         catch(IOException ex){
             System.out.println("Error : "+ex);
         }
            
 		
        }
    }
    
 
}

