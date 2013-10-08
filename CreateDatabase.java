import java.sql.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateDatabase{
	Connection conn = null;

	public void createGui(){
		final JButton button = new JButton("Create Database");
		final JFrame frame = new JFrame("Database Creator");
		final JPanel panel = new JPanel();
		final JTextField urlF = new JTextField("jdbc:mysql://localhost:3306/",10);
		final JTextField usernameF = new JTextField("root",10);
		final JPasswordField passwordF = new JPasswordField("root",10);
		final JTextField databaseNameF = new JTextField("db",10);
		JLabel urlL = new JLabel("                  URL");
		JLabel usernameL = new JLabel("         Username");
		JLabel passwordL = new JLabel("         Password");
		JLabel databaseNameL = new JLabel("Database Name");
		frame.setSize(250,200);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				char charPassword[] = passwordF.getPassword();
				String password  = new String(charPassword);
				createDatabase(urlF.getText(),usernameF.getText(),password,databaseNameF.getText());
				closeConnection();
				initateConnection(urlF.getText(),usernameF.getText(),password,databaseNameF.getText());
				createClockTable();
				createEmployeeTable();
				closeConnection();
			}
		});
		panel.add(urlL);
		panel.add(urlF);
		panel.add(databaseNameL);
		panel.add(databaseNameF);
		panel.add(usernameL);
		panel.add(usernameF);
		panel.add(passwordL);
		panel.add(passwordF);
		panel.add(button);
		frame.add(panel);
		panel.getRootPane().setDefaultButton(button);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public void createDatabase(String URL, String USERNAME, String PASSWORD, String DATABASENAME) {
		System.out.println("MySQL Connect Begin");
		String url = URL;
		String driver = "com.mysql.jdbc.Driver";
		String userName = USERNAME; 
		String password = PASSWORD;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url,userName,password);
			System.out.println("creating");
			Statement st = conn.createStatement();
			String table = "CREATE DATABASE `"+ DATABASENAME +"` ;";
			st.executeUpdate(table);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void initateConnection(String URL, String USERNAME, String PASSWORD, String DATABASENAME) {
		System.out.println("MySQL Connect Begin");
		String url = URL;
		String dbName = DATABASENAME;
		String driver = "com.mysql.jdbc.Driver";
		String userName = USERNAME; 
		String password = PASSWORD;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			System.out.println("Connected to the database");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeConnection(){
		try{
			conn.close();
			System.out.println("Disconnected from database");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createClockTable(){
		try{
		Statement st = conn.createStatement();
		String table = "CREATE TABLE Time_Clock"
		+ " (id INT(10) NOT NULL AUTO_INCREMENT,"
		+ "Id_Number       INT(10), "
		+ "Time_In          BIGINT, "
		+ "Time_Out          BIGINT," 
		+ "Total_Time          BIGINT,"
		+ "UNIQUE (id));"; 
		st.executeUpdate(table);
		System.out.println(table);
	    }
		catch(SQLException s){
			s.printStackTrace();
			System.out.println("Table all ready exists!");
		 }
	}
	
	public void createEmployeeTable(){
		try{
			Statement st = conn.createStatement();
			String table = "CREATE TABLE Employees"
			+ " (id INT(10) NOT NULL AUTO_INCREMENT, "
			+ "First_Name      VARCHAR(254), "                       
			+ "Last_Name       VARCHAR(254), "
			+ "Id_Number       INT(10), "
			+ "Level           VARCHAR(25), "
			+ "Wage            DOUBLE PRECISION, " 
			+ "UNIQUE (id));"; 
			st.executeUpdate(table);
			System.out.println(table);
		    }
			catch(SQLException s){
				s.printStackTrace();
				System.out.println("Table all ready exists!");
			 }
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CreateDatabase GUI = new CreateDatabase();
                GUI.createGui();
            }
        });
		
	}
}
