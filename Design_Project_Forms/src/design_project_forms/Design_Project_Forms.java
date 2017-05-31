/*
 Robert Coleman   3/7/2017---Updated on 3/8/2017
 Module 10 Course Project - Reports  
Create scrollable JTables to show the results of the following queries that you create in Lesson 6.
--Show a listing of all properties that are sold, under contract or available for sale. Include the Property address, price, status and Agent Name, Agent Phone Number.
--Show a listing of all properties that are Leased, or available for rent. Include the Property address, price, status and Agent Name, Agent Phone Number.
--Show a listing of all of the agents, their, assistants, and office location. Include the agent's name, phone number, assistant, name phone number and office location.
 */
package design_project_forms;

import javax.swing.JTabbedPane;// For the tabs
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
// Using AWT
import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;
import javax.swing.JButton;
// Using Swing
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
// For database connection
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;// For the tables 
import javax.swing.table.AbstractTableModel;


// Program
public final class Design_Project_Forms extends JPanel
{  
   // Main class
   public Design_Project_Forms() throws ClassNotFoundException, SQLException 
   {	
		JTabbedPane tabbValue = new JTabbedPane();
                tabbValue.setForeground(Color.BLUE);
                tabbValue.setBackground(Color.CYAN);
                //---------------------------------TAB 1
		JPanel newpropertiesPanel = createInnerPanel("New Properties");
		tabbValue.addTab("New Properties", newpropertiesPanel);
                //---------------------------------TAB 2
		tabbValue.setSelectedIndex(0);// Can set the start tab once the program runs
		JPanel newagentsPanel = createInnerPanel2("New Agents");
		tabbValue.addTab("New Agents", newagentsPanel);
             //===================================================================   
                //---------------------------------TAB 3
		JPanel PropertiessoldPanel = createInnerPanel3("Properties sold/ Under contract/ Available for sale");
		tabbValue.addTab("Properties Sold", PropertiessoldPanel);
                //---------------------------------TAB 4
		JPanel PropertiesleasedPanel = createInnerPanel4("Properties leased/ Available for rent");
		tabbValue.addTab("Properties Leased", PropertiesleasedPanel);
                //---------------------------------TAB 5
		JPanel agentsPanel = createInnerPanel5("Agent their assistants and office location");
		tabbValue.addTab("Agent", agentsPanel);
            //=====================================================================    
                
		// Add the tabbed pane to this panel.
		setLayout(new GridLayout(1,1));
		add(tabbValue);               
    }
   //---------------------------------TAB 1
    protected JPanel createInnerPanel(String text) throws ClassNotFoundException, SQLException 
    {
		JPanel PropertiesPanel = new JPanel();
                PropertiesPanel.setLayout(null);// Set to null so setBounds can be used
                
                //Title of tab
                JLabel PropertiesPanelDisplay = new JLabel(text);
                PropertiesPanelDisplay.setLayout(null);// Set to null so setBounds can be used
                PropertiesPanelDisplay.setBounds(300, 6, 150, 20); // Set the location of this in the frame
                PropertiesPanel.add(PropertiesPanelDisplay);
                
                // Propertie ID
		JLabel propertieID = new JLabel("Propertie ID:");
		propertieID.setBounds(1, 10, 90, 20); // Set the location of this in the frame
		propertieID.setForeground(Color.BLUE); // Set text color
		PropertiesPanel.add(propertieID);
                  // Getting input--Propertie ID
  	          DecimalFormat propertieIDInput = new DecimalFormat("000");
	          JFormattedTextField  propertieIDText = new JFormattedTextField(propertieIDInput);
      	          propertieIDText.setBounds(80, 10, 60, 20); // Set the location of this in the frame
      	          PropertiesPanel.add(propertieIDText);
                 
                
                // Agent Number
		JLabel agentNumber = new JLabel("Agent Number:");
		agentNumber.setBounds(1, 40, 90, 20); // Set the location of this in the frame
		agentNumber.setForeground(Color.BLUE); // Set text color
		PropertiesPanel.add(agentNumber);
                  // Getting input--Agent Number
  	          DecimalFormat agentNumberInput = new DecimalFormat("0000");
	          JFormattedTextField  agentNumberText = new JFormattedTextField(agentNumberInput);
      	          agentNumberText.setBounds(90, 40, 60, 20); // Set the location of this in the frame
      	          PropertiesPanel.add(agentNumberText);
                
                // Status Code
		JLabel statusCode = new JLabel("Status Code:");
		statusCode.setBounds(1, 80, 90, 20); // Set the location of this in the frame
		statusCode.setForeground(Color.BLUE); // Set text color
		PropertiesPanel.add(statusCode);
                  // Getting input--Status Code
  	          DecimalFormat statusCodeInput = new DecimalFormat("000");
	          JFormattedTextField statusCodeText = new JFormattedTextField(statusCodeInput);
      	          statusCodeText.setBounds(80, 80, 60, 20); // Set the location of this in the frame
      	          PropertiesPanel.add(statusCodeText);
                
                // Rooms
		JLabel rooms = new JLabel("Rooms:");
		rooms.setBounds(1, 110, 90, 20); // Set the location of this in the frame
		rooms.setForeground(Color.ORANGE); // Set text color
		PropertiesPanel.add(rooms);
                  // Getting input--Rooms
  	          DecimalFormat roomsInput = new DecimalFormat("0000");
	          JFormattedTextField  roomsText = new JFormattedTextField(roomsInput);
      	          roomsText.setBounds(50, 110, 60, 20); // Set the location of this in the frame
      	          PropertiesPanel.add(roomsText);
                
                // Cost
		JLabel cost = new JLabel("Cost:");
		cost.setBounds(150, 110, 90, 20); // Set the location of this in the frame
		cost.setForeground(Color.ORANGE); // Set text color
		PropertiesPanel.add(cost);
                  // Getting input--Cost
  	          DecimalFormat costInput = new DecimalFormat("00000000.00");
	          JFormattedTextField  costText = new JFormattedTextField(costInput);
      	          costText.setBounds(185, 110, 90, 20); // Set the location of this in the frame
      	          PropertiesPanel.add(costText);
                
                // Address
		JLabel address = new JLabel("Address:");
		address.setBounds(1, 140, 90, 20); // Set the location of this in the frame
		address.setForeground(Color.ORANGE); // Set text color
		PropertiesPanel.add(address);
                  // Getting input--Address
                  JTextField addressText = new JTextField(20);
		  addressText.setBounds(60, 140, 250, 20); // Set the location of this in the frame
		  PropertiesPanel.add(addressText);
                
             
                //---------------Button------------------------//
   		//Submit Button
   		JButton sumitButton = new JButton("SUMIT");
      		sumitButton.setBounds(260, 260, 150, 25); // Set the location of this in the frame
      		PropertiesPanel.add(sumitButton);
                     
      		sumitButton.addActionListener(new ActionListener()// Submit button functionality 
      		{
                         
                  public void actionPerformed(ActionEvent e)
                  {
                    // What the button will do goes here!
                      //===================Inserting Data into Database=========================================
                      try 
                      {
                          
                     Class.forName("com.mysql.jdbc.Driver");
                     Connection conn = null;
                     conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/robsrealtors","Bob", "rasmussen"); 
                        // SQL queries with data input by the user
			String sql = "INSERT INTO propertie (propertieID,rooms,cost,Address,agentNumber,statusCode) " +
					"Values ('"+propertieIDText.getText()+"'," + "'"+roomsText.getText()+"'," +
                                                 "'"+costText.getText()+"'," +
                                                 "'"+addressText.getText()+"'," +
                                                 "'"+agentNumberText.getText()+"'," +
                                                 "'"+statusCodeText.getText()+"')";

			Statement st = conn.createStatement();
			st.execute(sql);
                         // Display window telling the user the data has beed send to the server
                         JOptionPane.showMessageDialog( PropertiesPanel,"Data was upload to the database!");                           
                      } 
                      catch (SQLException ex) 
                      {
                          Logger.getLogger(Design_Project_Forms.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (ClassNotFoundException ex) {
                          Logger.getLogger(Design_Project_Forms.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      //========================================================================================
                  }  
                 }); 
		
		return PropertiesPanel;
    }
    //---------------------------------TAB 2   
    protected JPanel createInnerPanel2(String text)throws ClassNotFoundException, SQLException 
    {
                JPanel AgentsPanel = new JPanel();
                AgentsPanel.setLayout(null);// Set to null so setBounds can be used
                
                //Title of tab
                JLabel AgentsPanelDisplay = new JLabel(text);
                AgentsPanelDisplay.setLayout(null);// Set to null so setBounds can be used
                AgentsPanelDisplay.setBounds(300, 6, 150, 20); // Set the location of this in the frame
                AgentsPanel.add(AgentsPanelDisplay);
                
                //Agent number
		JLabel agentNumber  = new JLabel("Agent Number:");
		agentNumber .setBounds(1, 10, 90, 20); // Set the location of this in the frame
		agentNumber .setForeground(Color.BLUE); // Set text color
		AgentsPanel.add(agentNumber );  
                  // Getting input--Agent Number
  	          DecimalFormat agentNumberInput = new DecimalFormat("0000");
	          JFormattedTextField  agentNumberText = new JFormattedTextField(agentNumberInput);
      	          agentNumberText.setBounds(90, 10, 60, 20); // Set the location of this in the frame
      	          AgentsPanel.add(agentNumberText);
                
                // Office Number
		JLabel officeNumber = new JLabel("Office Number:");
		officeNumber.setBounds(1, 40, 90, 20); // Set the location of this in the frame
		officeNumber.setForeground(Color.BLUE); // Set text color
		AgentsPanel.add(officeNumber);
                  // Getting input--Office Number
  	          DecimalFormat officeNumberInput = new DecimalFormat("000");
	          JFormattedTextField  officeNumberText = new JFormattedTextField(officeNumberInput);
      	          officeNumberText.setBounds(90, 40, 60, 20); // Set the location of this in the frame
      	          AgentsPanel.add(officeNumberText);
                
                // Client Number
		JLabel clientNumber = new JLabel("Client Number:");
		clientNumber.setBounds(1, 80, 90, 20); // Set the location of this in the frame
		clientNumber.setForeground(Color.BLUE); // Set text color
		AgentsPanel.add(clientNumber);
                  // Getting input--Client Number
  	          DecimalFormat clientNumberInput = new DecimalFormat("00000");
	          JFormattedTextField  clientNumberText = new JFormattedTextField(clientNumberInput);
      	          clientNumberText.setBounds(90, 80, 60, 20); // Set the location of this in the frame
      	          AgentsPanel.add(clientNumberText);
                
                // Assistant Number
		JLabel assistantNumber = new JLabel("Assistant Number:");
		assistantNumber.setBounds(1, 110, 190, 20); // Set the location of this in the frame
		assistantNumber.setForeground(Color.BLUE); // Set text color
		AgentsPanel.add(assistantNumber);
                  // Getting input--Assistant Number
  	          DecimalFormat assistantNumberInput = new DecimalFormat("0000");
	          JFormattedTextField  assistantNumberText = new JFormattedTextField(assistantNumberInput);
      	          assistantNumberText.setBounds(115, 110, 60, 20); // Set the location of this in the frame
      	          AgentsPanel.add(assistantNumberText);
                
                 // Propertie ID
		JLabel propertieID = new JLabel("Propertie ID:");
		propertieID.setBounds(225, 110, 90, 20); // Set the location of this in the frame
		propertieID.setForeground(Color.BLUE); // Set text color
		AgentsPanel.add(propertieID);
                  // Getting input--Propertie ID
  	          DecimalFormat propertieIDInput = new DecimalFormat("000");
	          JFormattedTextField  propertieIDText = new JFormattedTextField(propertieIDInput);
      	          propertieIDText.setBounds(300, 110, 60, 20); // Set the location of this in the frame
      	          AgentsPanel.add(propertieIDText);
                
                // Agent Last Name
		JLabel agentLName = new JLabel("Last name:");
		agentLName.setBounds(1, 140, 90, 20); // Set the location of this in the frame
		agentLName.setForeground(Color.ORANGE); // Set text color
		AgentsPanel.add(agentLName);
                   // Getting Agent Last Name
                   JTextField agentLNameText = new JTextField(20);
		   agentLNameText.setBounds(70, 140, 120, 20); // Set the location of this in the frame
		   AgentsPanel.add(agentLNameText);
                
                // Agent First Name
		JLabel agentFName = new JLabel("First name:");
		agentFName.setBounds(220, 140, 90, 20); // Set the location of this in the frame
		agentFName.setForeground(Color.ORANGE); // Set text color
		AgentsPanel.add(agentFName);
                   // Getting Agent First Name
                   JTextField agentFNameText = new JTextField(20);
		   agentFNameText.setBounds(290, 140, 120, 20); // Set the location of this in the frame
		   AgentsPanel.add(agentFNameText);
                
                // Agent Address
		JLabel agentAddress = new JLabel("Address:");
		agentAddress.setBounds(1, 180, 90, 20); // Set the location of this in the frame
		agentAddress.setForeground(Color.ORANGE); // Set text color
		AgentsPanel.add(agentAddress);
                  // Getting Agent Address
                  JTextField agentAddressText = new JTextField(20);
		  agentAddressText.setBounds(60, 180, 250, 20); // Set the location of this in the frame
		  AgentsPanel.add(agentAddressText);
                
                // Agent Phone
		JLabel agentPhone = new JLabel("Phone:");
		agentPhone.setBounds(1, 205, 90, 20); // Set the location of this in the frame
		agentPhone.setForeground(Color.ORANGE); // Set text color
		AgentsPanel.add(agentPhone);
                JLabel agentPhoneFormat = new JLabel("Phone format ###-###-####");
		agentPhoneFormat.setBounds(1, 225, 170, 20); // Set the location of this in the frame
		agentPhoneFormat.setForeground(Color.ORANGE); // Set text color
		AgentsPanel.add(agentPhoneFormat);
                  // Getting Agent Phone
                  JTextField agentPhoneText = new JTextField(20);
		  agentPhoneText.setBounds(60, 205, 90, 20); // Set the location of this in the frame
		  AgentsPanel.add(agentPhoneText);
                
                // Agent Hire Date
		JLabel agentHireDate = new JLabel("Hire Date:");
		agentHireDate.setBounds(170, 205, 90, 20); // Set the location of this in the frame
		agentHireDate.setForeground(Color.ORANGE); // Set text color
		AgentsPanel.add(agentHireDate);
                JLabel agentHireDateFormat = new JLabel("Date Format yyyy-mm-dd");
		agentHireDateFormat.setBounds(220, 225, 290, 20); // Set the location of this in the frame
		agentHireDateFormat.setForeground(Color.ORANGE); // Set text color
		AgentsPanel.add(agentHireDateFormat);
                  // Getting Agent Hire Date
                  SimpleDateFormat agentHireDateInput = new SimpleDateFormat("yyyy-MM-dd");
                  JFormattedTextField agentHireDateText = new JFormattedTextField(agentHireDateInput);
		  agentHireDateText.setBounds(235, 205, 90, 20); // Set the location of this in the frame
		  AgentsPanel.add(agentHireDateText);
                
                 //---------------Button------------------------//
   		//Submit Button
   		JButton sumitButton2 = new JButton("SUMIT");
      		sumitButton2.setBounds(260, 260, 150, 25); // Set the location of this in the frame
      		 AgentsPanel.add(sumitButton2);
      		
      		sumitButton2.addActionListener(new ActionListener()// Submit button functionality 
      		{	 
                  public void actionPerformed(ActionEvent e)
                  {	 
                    // What the button will do goes here!
                      //===================Inserting Data into Database=========================================
                      try 
                      {
                          
                       Class.forName("com.mysql.jdbc.Driver");
                       Connection conn2 = null;
                       conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/robsrealtors","Bob", "rasmussen"); 
                       /*
                        // SQL queries with data input by the user
			String sql2 = "INSERT INTO agent (agentNumber,agentlastName,agentfirstName,agentAddress,agentphone,agenthireDate,officeNumber,clientNumber,assistantNumber,propertieID) " +
					             "Values ('"+agentNumberText.getText()+"'," +
						             "'"+agentLNameText.getText()+"'," +
                                                             "'"+agentFNameText.getText()+"'," +
                                                             "'"+agentAddressText.getText()+"'," +
                                                             "'"+agentPhoneText.getText()+"'," +
                                                             "'"+agentHireDateText.getText()+"'," +
                                                             "'"+officeNumberText.getText()+"'," +
                                                             "'"+clientNumberText.getText()+"'," +
                                                             "'"+assistantNumberText.getText()+"'," +
                                                             "'"+propertieIDText.getText()+"')";

			Statement st2 = conn2.createStatement();
			st2.execute(sql2);
                        */
                       // Change to PreparedStatement statement 3/8/2017!
                        PreparedStatement statement  = conn2.prepareStatement("INSERT INTO Agent (agentNumber,agentlastName,agentfirstName,agentAddress,agentphone,agenthireDate,officeNumber,"
                                                                            + "clientNumber,assistantNumber,propertieID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                        statement.setString(1, agentNumberText.getText());
                        statement.setString(2, agentLNameText.getText());
                        statement.setString(3, agentFNameText.getText());
                        statement.setString(4, agentAddressText.getText());
                        statement.setString(5, agentPhoneText.getText());
                        statement.setString(6, agentHireDateText.getText());
                        statement.setString(7, officeNumberText.getText());
                        statement.setString(8, clientNumberText.getText());
                        statement.setString(9, assistantNumberText.getText());
                        statement.setString(10, propertieIDText.getText()); 
                        statement.executeUpdate();
                         // Display window telling the user the data has beed send to the server
                         JOptionPane.showMessageDialog( AgentsPanel,"Data was upload to the database!");
                                                     
                      } 
                      catch (SQLException ex) 
                      {
                          Logger.getLogger(Design_Project_Forms.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (ClassNotFoundException ex) {
                          Logger.getLogger(Design_Project_Forms.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      //========================================================================================   
                  }
                 }); 
		
		return  AgentsPanel;            
    }
    //---------------------------------TAB 3---------- scrollable JTable !
    protected JPanel createInnerPanel3(String text)throws ClassNotFoundException, SQLException 
    {
                JPanel  SoldpropertiesPanel = new JPanel();
                SoldpropertiesPanel.setLayout(null);// Set to null so setBounds can be used
                
                //Title of tab
                JLabel SoldpropertiesPanelDisplay = new JLabel(text);
                SoldpropertiesPanelDisplay.setLayout(null);// Set to null so setBounds can be used
                SoldpropertiesPanelDisplay.setBounds(300, 6, 300, 20); // Set the location of this in the frame
                SoldpropertiesPanel.add(SoldpropertiesPanelDisplay);
                
                // For scroll table
                Vector columnNames = new Vector();
                Vector data = new Vector();
                try 
                {

                 Class.forName("com.mysql.jdbc.Driver").newInstance();
                 Connection conn3 = null;
                 conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/robsrealtors","Bob", "rasmussen");
                 String sql3 = "Select Address, cost, statusCode, agentfirstName, agentlastName, agentphone from Agent left join Propertie on Agent.agentNumber = Propertie.agentNumber\n" +
                               "WHERE propertie.statusCode = 100 ||  propertie.statusCode = 400\n" +
                               "|| propertie.statusCode = 200;";
                 Statement statement = conn3.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql3);
                 ResultSetMetaData metaData = resultSet.getMetaData();
                 int columns = metaData.getColumnCount();
                 for (int i = 1; i <= columns; i++) 
                 {
                  columnNames.addElement(metaData.getColumnName(i));
                 }
                 while (resultSet.next()) 
                 {
                   Vector row = new Vector(columns);
                     for (int i = 1; i <= columns; i++) 
                     {
                      row.addElement(resultSet.getObject(i));
                     }
                     data.addElement(row);
                  }
                   resultSet.close();
                  statement.close();
                } 
                catch (Exception e) 
                {
                System.out.println(e);
                } 
                // Display table!
                JTable table = new JTable(data, columnNames);
                TableColumn column;
                for (int i = 0; i < table.getColumnCount(); i++) 
                {
                  column = table.getColumnModel().getColumn(i);
                  column.setMaxWidth(250);
                }
                JScrollPane scrollPane = new JScrollPane(table);
               
                scrollPane.setBounds(1, 40, 850, 200); // Set the location of this in the frame
                table.setEnabled(false); // Set cells not non-editable
                SoldpropertiesPanel.add(scrollPane);
                
                JLabel Statuscodes = new JLabel("Status codes:    100 = sold\n" +
                                                               "   400 = under contract\n" +
                                                               "   200 = available for sale\n" +
                                                               "   500 = available for rent\n" +
                                                               "   30 = leased");
                
      		Statuscodes.setBounds(1, 260, 650, 25); // Set the location of this in the frame
                Statuscodes.setForeground(Color.red);
      		SoldpropertiesPanel.add(Statuscodes);
                
                return SoldpropertiesPanel;
    }
    //---------------------------------TAB 4---------- scrollable JTable !
    protected JPanel createInnerPanel4(String text)throws ClassNotFoundException, SQLException 
    {
                JPanel  LeasedpropertiesPanel = new JPanel();
                LeasedpropertiesPanel.setLayout(null);// Set to null so setBounds can be used
                
                //Title of tab
                JLabel SoldpropertiesPanelDisplay = new JLabel(text);
                SoldpropertiesPanelDisplay.setLayout(null);// Set to null so setBounds can be used
                SoldpropertiesPanelDisplay.setBounds(300, 6, 300, 20); // Set the location of this in the frame
                LeasedpropertiesPanel.add(SoldpropertiesPanelDisplay);
                
                // For scroll table
                Vector columnNames = new Vector();
                Vector data = new Vector();
                try 
                {

                 Class.forName("com.mysql.jdbc.Driver").newInstance();
                 Connection conn4 = null;
                 conn4 = DriverManager.getConnection("jdbc:mysql://localhost:3306/robsrealtors","Bob", "rasmussen");
                 String sql4 = "Select Address, cost, statusCode, agentfirstName, agentlastName, agentphone  \n" +
                               "from Agent left join Propertie on Agent.agentNumber = Propertie.agentNumber\n" +
                               "WHERE propertie.statusCode = 500 ||  propertie.statusCode = 30;";
                 Statement statement = conn4.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql4);
                 ResultSetMetaData metaData = resultSet.getMetaData();
                 int columns = metaData.getColumnCount();
                 for (int i = 1; i <= columns; i++) 
                 {
                  columnNames.addElement(metaData.getColumnName(i));
                 }
                 while (resultSet.next()) 
                 {
                   Vector row = new Vector(columns);
                     for (int i = 1; i <= columns; i++) 
                     {
                      row.addElement(resultSet.getObject(i));
                     }
                     data.addElement(row);
                  }
                   resultSet.close();
                  statement.close();
                } 
                catch (Exception e) 
                {
                System.out.println(e);
                } 
                // Display table!
                JTable table = new JTable(data, columnNames);
                TableColumn column;
                for (int i = 0; i < table.getColumnCount(); i++) 
                {
                  column = table.getColumnModel().getColumn(i);
                  column.setMaxWidth(250);
                }
                JScrollPane scrollPane2 = new JScrollPane(table);
                scrollPane2.setBounds(1, 40, 850, 200); // Set the location of this in the frame
                table.setEnabled(false); // Set cells not non-editable
                LeasedpropertiesPanel.add(scrollPane2);
                
                JLabel Statuscodes = new JLabel("Status codes:    100 = sold\n" +
                                                               "   400 = under contract\n" +
                                                               "   200 = available for sale\n" +
                                                               "   500 = available for rent\n" +
                                                               "   30 = leased");
                
      		Statuscodes.setBounds(1, 260, 650, 25); // Set the location of this in the frame
                Statuscodes.setForeground(Color.red);
      		LeasedpropertiesPanel.add(Statuscodes);
                
                return LeasedpropertiesPanel;
    }
    //---------------------------------TAB 5---------- scrollable JTable !
    protected JPanel createInnerPanel5(String text)throws ClassNotFoundException, SQLException 
    {
                JPanel  agentPanel = new JPanel();
                agentPanel.setLayout(null);// Set to null so setBounds can be used
                
                //Title of tab
                JLabel agentPanelDisplay = new JLabel(text);
                agentPanelDisplay.setLayout(null);// Set to null so setBounds can be used
                agentPanelDisplay.setBounds(300, 6, 300, 20); // Set the location of this in the frame
                agentPanel.add(agentPanelDisplay);
                
                // For scroll table
                Vector columnNames = new Vector();
                Vector data = new Vector();
                try 
                {

                 Class.forName("com.mysql.jdbc.Driver").newInstance();
                 Connection conn5 = null;
                 conn5 = DriverManager.getConnection("jdbc:mysql://localhost:3306/robsrealtors","Bob", "rasmussen");
                 String sql5 = "Select agentfirstName, agentlastName, agentphone, lastName, firstName, phone, officeNumber from Agent left join assistant on Agent.agentNumber = assistant.agentNumber\n" +
                               "ORDER BY agentfirstName;";
                 Statement statement = conn5.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql5);
                 ResultSetMetaData metaData = resultSet.getMetaData();
                 int columns = metaData.getColumnCount();
                 for (int i = 1; i <= columns; i++) 
                 {
                  columnNames.addElement(metaData.getColumnName(i));
                 }
                 while (resultSet.next()) 
                 {
                   Vector row = new Vector(columns);
                     for (int i = 1; i <= columns; i++) 
                     {
                      row.addElement(resultSet.getObject(i));
                     }
                     data.addElement(row);
                  }
                   resultSet.close();
                  statement.close();
                } 
                catch (Exception e) 
                {
                System.out.println(e);
                } 
                // Display table!
                JTable table = new JTable(data, columnNames);
                TableColumn column;
                for (int i = 0; i < table.getColumnCount(); i++) 
                {
                  column = table.getColumnModel().getColumn(i);
                  column.setMaxWidth(250);
                }
                JScrollPane scrollPane3 = new JScrollPane(table);
                scrollPane3.setBounds(1, 40, 900, 200); // Set the location of this in the frame
                table.setEnabled(false); // Set cells not non-editable
                agentPanel.add(scrollPane3);
                
                return agentPanel;
    }
     
    //  Main function
    public static void main(String[] args) throws ClassNotFoundException, SQLException 
    {
        
        
		JFrame frame = new JFrame("Robs Realtors");
		frame.addWindowListener(new WindowAdapter() 
                {
                        @Override
			public void windowClosing(WindowEvent e) 
                        {
				System.exit(0);// Close program
			}
		});
                // The window values
		frame.getContentPane().add(new Design_Project_Forms(),BorderLayout.CENTER);
		frame.setSize(950, 500);
                frame.setLocationRelativeTo(null);// Center the window frame to the computer screen
                frame.setResizable(false);//makes window not re sizable with the mouse
		frame.setVisible(true);
    } 
}





 

 




