import java.sql.*;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

//This class is made to be a GUI for the Foogle MySQL AWS RDS DB regarding Restaurant
public class CSS475_UI {
	//This is for the Application shell file that is the overall Foogle window
	protected Shell shell;
	//These are all the labels used in the GUI
	private Label lblFoogleRestaurantFinder;
	private Label lblNewCustomer;
	private Label lblName;
	private Label lblHomeCity;
	private Label lblPhone;
	private Label lblEmail;
	private Label lblDentotesRequired;
	private Label lblRestaurantCity;
	private Label lblRating;
	private Label lblYourName;
	private Label lblRestaurantName;
	private Label lblWriteAReview;
	private Label label_1;
	private Label label_2;
	private Label label_3;
	private Label label_4;
	private Label lblComments;
	private Label lblRestaurantHoursInfo;
	private Label lblRHrestName;
	private Label lblRHrestCity;
	//These are the text boxes used in the GUI
	private Text txtCity;
	private Text txtCuisine;
	private Text txtMin;
	private Text txtFoodItem;
	private Text txtAvgRating;
	private Text txtMax;
	private Text txtName;
	private Text txtHomeCity;
	private Text txtPhone;
	private Text txtEmail;
	private Text txtRestaurant;
	private Text txtCity_1;
	private Text txtUserName;
	private Text text;
	private Text txtWriteCommentsHere;
	private Text txtRCrestName;
	private Text txtRCrestCity;
	private Text txtRHrestName;
	private Text txtRHrestCity;
	private Text txtRRVrestName;
	private Text txtRRVrestCity;
	
	//These are the buttons used in the GUI
	private Button btnRandomRestaurant;
	private Button btnAddCustomer;
	private Button btnMostExpensiveFood;
	private Button btnMostExpensiveFoodByCategory;
	private Button btnLeastExpensiveFoodByCategory;
	private Button btnLeastExpensiveFoodByCity;
	private Button btnSubmitReview;
	private Button btnGetHours;
	//This the Connection object used to establish a connection	
	public static Connection conn = null;

	 //Main program to run the GUI and attempt to connect to the AWS RDS instance.
	public static void main(String[] args) {
		//First try block catch to make a new Window
		try {
			//Second try block to connect to the AWS RDS instance
			try {
				//This url is the hostname of the AWS RDS instance and has the provided DB name
				String url1 = "jdbc:mysql://mysql-uwb-css475-db.civewz6zb6nl.us-west-2.rds.amazonaws.com/Restaurant_DB";
				//Grader credentials are used for login
				String user = "grader";
				String password = "graderpass";
				//Connection object is initialized based on prior credentials
				conn = DriverManager.getConnection(url1, user, password);
				
				if (conn != null) {
					//JOptionPane.showMessageDialog(null,"Successfully Connected to the AWS RDS Restaurant_DB test");
					System.out.println("Successfully Connected to the AWS RDS Restaurant_DB test");
				}
			}
			//Catch block to showcase an error message on failed connection
			catch (SQLException ex) {
				System.out.println("Connection failed, user credentials, host endpoint, database selection may be invalid.");
				ex.printStackTrace();
			}
			CSS475_UI window = new CSS475_UI();
			window.open();	
		} 
		//Catch block for an issue with opening the Window
		catch (Exception windowIssue) {
			windowIssue.printStackTrace();
		}
	}
	
	// Open the window for the GUI 
	public void open() {
		//Make a new display
		Display display = Display.getDefault();
		createContents();
		//Open the shell
		shell.open();
		//Make the shell layout
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	//Create the contents within the shell
	protected void createContents() {
		//title of window
		shell = new Shell();
		shell.setSize(912, 718);
		shell.setText("Foogle's Application");

		//Search terms for finding a restaurant.
		txtCity = new Text(shell, SWT.BORDER);
		txtCity.setText("City");
		txtCity.setBounds(118, 52, 78, 26);
		
		txtCuisine = new Text(shell, SWT.BORDER);
		txtCuisine.setText("Cuisine");
		txtCuisine.setBounds(118, 84, 78, 26);

		txtMin = new Text(shell, SWT.BORDER);
		txtMin.setText("0");
		txtMin.setBounds(118, 116, 42, 26);

		txtFoodItem = new Text(shell, SWT.BORDER);
		txtFoodItem.setText("Food Item");
		txtFoodItem.setBounds(118, 148, 85, 26);
		
		//Label creations for text boxes
		Label lblCity = new Label(shell, SWT.NONE);
		lblCity.setBounds(10, 55, 70, 20);
		lblCity.setText("*CITY:");

		Label lblCuisine = new Label(shell, SWT.NONE);
		lblCuisine.setBounds(10, 87, 70, 20);
		lblCuisine.setText("*CUISINE:");

		Label lblPriceRange = new Label(shell, SWT.NONE);
		lblPriceRange.setBounds(10, 116, 103, 20);
		lblPriceRange.setText("PRICE RANGE:");

		Label lblFoodItem = new Label(shell, SWT.NONE);
		lblFoodItem.setBounds(10, 148, 85, 20);
		lblFoodItem.setText("FOOD ITEM:");

		Label label = new Label(shell, SWT.NONE);
		label.setBounds(166, 119, 12, 20);
		label.setText("-");
		
		//Text boundary definitions
		txtMax = new Text(shell, SWT.BORDER);
		txtMax.setText("1000");
		txtMax.setBounds(179, 116, 42, 26);
		//Button creation
		Button btnSearch = new Button(shell, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String city = txtCity.getText();
					String cuisine = txtCuisine.getText();

					String query = "SELECT name, address FROM RESTAURANT "
							+ "WHERE rCity = '" + city + "' AND cuisine = '" + cuisine + "';";

					// create the java statement
					Statement st = conn.createStatement();

					// execute the query, and get a java resultset
					ResultSet rs = st.executeQuery(query);

					// iterate through the java resultset
					while (rs.next())
					{
						String name = rs.getString("name");
						String address = rs.getString("address");  
						// print the results
						//JOptionPane.showMessageDialog(null, "Your query: \n" + query + "\nResult: \n" + price + " " + food);
						System.out.format("%s, %s\n", name, address);
					}
					st.close();
				} 
				catch(Exception query) {
					System.err.println("Got an exception! ");
					System.err.println(query.getMessage());
				}
			}
		});

		btnSearch.setBounds(72, 222, 90, 30);
		btnSearch.setText("Search");
		
		//Additional labels to help define the GUI
		lblFoogleRestaurantFinder = new Label(shell, SWT.NONE);
		lblFoogleRestaurantFinder.setBounds(10, 0, 202, 20);
		lblFoogleRestaurantFinder.setText("FOOGLE RESTAURANT FINDER");

		lblNewCustomer = new Label(shell, SWT.NONE);
		lblNewCustomer.setBounds(369, 55, 103, 20);
		lblNewCustomer.setText("New Customer");

		lblName = new Label(shell, SWT.NONE);
		lblName.setBounds(324, 84, 59, 20);
		lblName.setText("*Name:");

		lblHomeCity = new Label(shell, SWT.NONE);
		lblHomeCity.setBounds(305, 116, 78, 20);
		lblHomeCity.setText("Home City:");

		lblPhone = new Label(shell, SWT.NONE);
		lblPhone.setBounds(324, 148, 59, 20);
		lblPhone.setText("*Phone:");

		lblEmail = new Label(shell, SWT.NONE);
		lblEmail.setBounds(324, 180, 59, 20);
		lblEmail.setText("*Email:");

		txtName = new Text(shell, SWT.BORDER);
		txtName.setText("Name");
		txtName.setBounds(393, 81, 78, 26);

		txtHomeCity = new Text(shell, SWT.BORDER);
		txtHomeCity.setText("Home City");
		txtHomeCity.setBounds(393, 113, 95, 26);

		txtPhone = new Text(shell, SWT.BORDER);
		txtPhone.setText("Phone");
		txtPhone.setBounds(393, 145, 95, 26);

		txtEmail = new Text(shell, SWT.BORDER);
		txtEmail.setText("Email");
		txtEmail.setBounds(393, 177, 78, 26);
		
		//Button to add a customer
		btnAddCustomer = new Button(shell, SWT.NONE);
		btnAddCustomer.setBounds(369, 209, 116, 30);
		btnAddCustomer.setText("Add Customer");
		btnAddCustomer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String name = txtName.getText();
					String phoneNumber = txtPhone.getText();
					String email = txtEmail.getText();
					// Randomize the user ID
					int uID = (int)(Math.random() * (Integer.MAX_VALUE - 501) + 501);
					//SQL query
					String query = "INSERT INTO RUSER  (userID, name) " + 
							"VALUES (?, ?)";
					
					//Prepares the Java statement
				    PreparedStatement preparedStmt = conn.prepareStatement(query);
					preparedStmt.setLong (1, uID);
					preparedStmt.setString (2, name);
					preparedStmt.execute();

					query = "INSERT INTO CONTACT (email, phone_number, uID, counter)" +
							"VALUES (?, ?, ?, ?)";
					preparedStmt = conn.prepareStatement(query);
					preparedStmt.setString (1, email);
					preparedStmt.setString(2, phoneNumber);
					preparedStmt.setLong (3, uID);
					preparedStmt.setLong(4, 1);
					preparedStmt.execute();

					//JOptionPane.showMessageDialog(null, name + ", your information has been saved.");
				} 
				catch(Exception query) {
					System.err.println("Got an exception! ");
					System.err.println(query.getMessage());
				}
			}
		});
		
		//Button to showcase the most expensive food on click
		btnMostExpensiveFood = new Button(shell, SWT.NONE);
		btnMostExpensiveFood.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			@Override
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				try
				{ 
					String query = "SELECT name, price\r\n" + 
							"FROM FOOD_ENTRIES\r\n" + 
							"WHERE FOOD_ENTRIES.price = (SELECT MAX(price) FROM FOOD_ENTRIES);";

					Statement st = conn.createStatement();

					ResultSet rs = st.executeQuery(query);

					while (rs.next())
					{
						String price = rs.getString("price");
						String food = rs.getString("name");   
						//JOptionPane.showMessageDialog(null, "Your query: \n" + query + "\nResult: \n" + price + " " + food);
						System.out.format("%s, %s\n", price, food);
					}
					st.close();
				}
				catch (Exception query)
				{
					System.err.println("Got an exception! ");
					System.err.println(query.getMessage());
				}
			}
		});
		btnMostExpensiveFood.setToolTipText("Queries the most expensive food");
		btnMostExpensiveFood.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnMostExpensiveFood.setBounds(4, 536, 217, 30);
		btnMostExpensiveFood.setText("Most Expensive Food");
		
		//Query for most expensive food by category
		btnMostExpensiveFoodByCategory = new Button(shell, SWT.NONE);
		btnMostExpensiveFoodByCategory.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			@Override
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				try
				{ 
					String query = 
							"SELECT FOOD_ENTRIES.price , FOOD_ENTRIES.name, RESTAURANT.cuisine\r\n" +
									"FROM FOOD_ENTRIES, RESTAURANT\r\n" +
									"WHERE FOOD_ENTRIES.r_menu_id = RESTAURANT.rID AND FOOD_ENTRIES.price IN(SELECT MAX(price)\r\n" +
									"FROM FOOD_ENTRIES AS FE, MENU AS M, RESTAURANT AS R\r\n" +
									"WHERE FE.r_menu_id = M.r_id AND M.r_id = R.rID\r\n" +
									"GROUP BY cuisine)\r\n" +
									"ORDER BY price ASC;";

					Statement st = conn.createStatement();

					ResultSet rs = st.executeQuery(query);

					while (rs.next())
					{
						String price = rs.getString("price");
						String food = rs.getString("name");  
						String name = rs.getString("cuisine");
						//JOptionPane.showMessageDialog(null, "Your query: \n" + query + "\nResult: \n" + price + " " + food);
						System.out.format("%s, %s, %s\n", price, food, name);
					}
					st.close();
				}
				catch (Exception query)
				{
					System.err.println("Got an exception! ");
					System.err.println(query.getMessage());
				}
			}
		});
		btnMostExpensiveFoodByCategory.setToolTipText("Queries the most expensive food by ethnicity");
		btnMostExpensiveFoodByCategory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnMostExpensiveFoodByCategory.setBounds(274, 536, 235, 30);
		btnMostExpensiveFoodByCategory.setText("Most Expensive Food by Ethnicity");

		btnLeastExpensiveFoodByCategory = new Button(shell, SWT.NONE);
		btnLeastExpensiveFoodByCategory.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			@Override
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				try
				{ 
					String query = 
							"SELECT FOOD_ENTRIES.price , FOOD_ENTRIES.name, RESTAURANT.cuisine\r\n" +
									"FROM FOOD_ENTRIES, RESTAURANT\r\n" +
									"WHERE FOOD_ENTRIES.r_menu_id = RESTAURANT.rID AND FOOD_ENTRIES.price IN(SELECT MIN(price)\r\n" +
									"FROM FOOD_ENTRIES AS FE, MENU AS M, RESTAURANT AS R\r\n" +
									"WHERE FE.r_menu_id = M.r_id AND M.r_id = R.rID\r\n" +
									"GROUP BY cuisine)\r\n" +
									"ORDER BY price ASC;";

					Statement st = conn.createStatement();

					ResultSet rs = st.executeQuery(query);

					while (rs.next())
					{
						String price = rs.getString("price");
						String food = rs.getString("name");  
						String name = rs.getString("cuisine");
						//JOptionPane.showMessageDialog(null, "Your query: \n" + query + "\nResult: \n" + price + " " + food);
						System.out.format("%s, %s, %s\n", price, food, name);
					}
					st.close();
				}
				catch (Exception query)
				{
					System.err.println("Got an exception! ");
					System.err.println(query.getMessage());
				}
			}
		});
		btnLeastExpensiveFoodByCategory.setToolTipText("Queries the least expensive food by ethnicity");
		btnLeastExpensiveFoodByCategory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnLeastExpensiveFoodByCategory.setBounds(274, 572, 235, 30);
		btnLeastExpensiveFoodByCategory.setText("Least Expensive Food by Ethnicity");


		//Queries the least expensive food by city
		btnLeastExpensiveFoodByCity = new Button(shell, SWT.NONE);
		btnLeastExpensiveFoodByCity.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			@Override
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				try
				{ 
					String query = 
							"SELECT *\r\n" +
									"FROM FOOD_ENTRIES, CITY\r\n" +
									"WHERE FOOD_ENTRIES.price IN(SELECT MIN(price)\r\n" +
									"FROM FOOD_ENTRIES AS FE, MENU, RESTAURANT, CITY\r\n" +
									"WHERE FE.r_menu_id = r_id AND r_id = rID AND rCity = CITY.name)\r\n" +
									"ORDER BY price ASC;";

					Statement st = conn.createStatement();

					ResultSet rs = st.executeQuery(query);

					while (rs.next())
					{
						String price = rs.getString("price");
						String food = rs.getString("name");  
						String menu_id = rs.getString("r_menu_id");
						String city = rs.getString("CITY.name");
						//JOptionPane.showMessageDialog(null, "Your query: \n" + query + "\nResult: \n" + price + " " + food);
						System.out.format("%s, %s, %s, %s\n", price, food, menu_id, city);
					}
					st.close();
				}
				catch (Exception query)
				{
					System.err.println("Got an exception! ");
					System.err.println(query.getMessage());
				}
			}
		});
		btnLeastExpensiveFoodByCity.setToolTipText("Queries the least expensive food in each city");
		btnLeastExpensiveFoodByCity.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnLeastExpensiveFoodByCity.setBounds(4, 572, 235, 30);
		btnLeastExpensiveFoodByCity.setText("Least Expensive Food in Each City");
		
		//Labels to write a review
		lblWriteAReview = new Label(shell, SWT.NONE);
		lblWriteAReview.setBounds(271, 296, 103, 20);
		lblWriteAReview.setText("Write a Review");

		lblRestaurantName = new Label(shell, SWT.NONE);
		lblRestaurantName.setBounds(25, 321, 129, 20);
		lblRestaurantName.setText("*Restaurant Name:");

		lblYourName = new Label(shell, SWT.NONE);
		lblYourName.setBounds(63, 397, 85, 20);
		lblYourName.setText("*Your Name:");

		lblRating = new Label(shell, SWT.NONE);
		lblRating.setBounds(42, 433, 112, 20);
		lblRating.setText("*Rating (out of 5):");

		lblComments = new Label(shell, SWT.NONE);
		lblComments.setBounds(347, 321, 78, 20);
		lblComments.setText("Comments:");

		txtRestaurant = new Text(shell, SWT.BORDER);
		txtRestaurant.setText("Restaurant");
		txtRestaurant.setBounds(160, 320, 155, 26);

		lblRestaurantCity = new Label(shell, SWT.NONE);
		lblRestaurantCity.setBounds(35, 346, 112, 20);
		lblRestaurantCity.setText("*Restaurant City:");

		txtCity_1 = new Text(shell, SWT.BORDER);
		txtCity_1.setText("City");
		txtCity_1.setBounds(160, 352, 155, 26);

		txtUserName = new Text(shell, SWT.BORDER);
		txtUserName.setText("User Name");
		txtUserName.setBounds(160, 394, 155, 26);

		text = new Text(shell, SWT.BORDER);
		text.setText("#");
		text.setBounds(160, 427, 23, 26);

		txtWriteCommentsHere = new Text(shell, SWT.BORDER);
		txtWriteCommentsHere.setBounds(347, 341, 247, 112);

		lblDentotesRequired = new Label(shell, SWT.NONE);
		lblDentotesRequired.setBounds(293, 10, 301, 20);
		lblDentotesRequired.setText("IMPORTANT: * dentotes required input fields");
		
		//Button to submit a review
		btnSubmitReview = new Button(shell, SWT.NONE);
		btnSubmitReview.setBounds(271, 459, 123, 30);
		btnSubmitReview.setText("Submit Review");
		btnSubmitReview.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String comment = txtWriteCommentsHere.getText();
					String restName = txtRestaurant.getText();
					String restCity = txtCity_1.getText();
					String userName = txtUserName.getText();
					int rating = Integer.parseInt(text.getText());
					String query = "INSERT INTO REVIEW (rating, uID, rID, review_day, message) " +
							"VALUES (?, (SELECT userID FROM RUSER WHERE name = ? ), " + 
							"(SELECT rID FROM RESTAURANT WHERE name = ? AND rCity = ? ), curdate(), ? );";

					// create the java statement
					PreparedStatement preparedStmt = conn.prepareStatement(query);
					preparedStmt.setLong (1, rating);
					preparedStmt.setString (2, userName);
					preparedStmt.setString (3, restName);
					preparedStmt.setString (4, restCity);
					preparedStmt.setString (5, comment);

					preparedStmt.execute();

					//JOptionPane.showMessageDialog(null, name + ", your information has been saved.");
				} 
				catch(Exception query) {
					System.err.println("Got an exception! ");
					System.err.println(query.getMessage());
				}
			}
		});


		label_1 = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label_1.setBounds(267, 37, 2, 239);

		label_2 = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label_2.setBounds(548, 36, 2, 240);

		label_3 = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label_3.setBounds(621, 282, 2, 357);

		label_4 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_4.setBounds(10, 282, 845, 2);

		Label lblRestaurantContactInfo = new Label(shell, SWT.NONE);
		lblRestaurantContactInfo.setBounds(634, 84, 161, 20);
		lblRestaurantContactInfo.setText("Restaurant Contact Info");

		Label lblRCrestName = new Label(shell, SWT.NONE);
		lblRCrestName.setBounds(592, 110, 123, 20);
		lblRCrestName.setText("*Restaurant Name:");

		Label lblRCrestCity = new Label(shell, SWT.NONE);
		lblRCrestCity.setBounds(593, 140, 112, 20);
		lblRCrestCity.setText("*Restaurant City:");
		
		//Button to get the contact information
		Button btnGetContact = new Button(shell, SWT.NONE);
		btnGetContact.setBounds(652, 180, 110, 30);
		btnGetContact.setText("Get Contact");
		btnGetContact.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String restName = txtRCrestName.getText();
					String cityName = txtRCrestCity.getText();

					String query = "SELECT DISTINCT RC.email, RC.phone_number\r\n" +
							"FROM RCONTACT AS RC, RESTAURANT AS R\r\n" + 
							"WHERE rest_ID IN (SELECT rID FROM RESTAURANT WHERE\r\n" + 
							"name = '" + restName + "' AND rCity = '" + cityName + "' );";

					Statement st = conn.createStatement();

					ResultSet rs = st.executeQuery(query);

					while (rs.next())
					{
						String email = rs.getString("email");
						String phone = rs.getString("phone_number");  
						//JOptionPane.showMessageDialog(null, "Your query: \n" + query + "\nResult: \n" + price + " " + food);
						System.out.format("%s, %s\n", email, phone);
					}
					st.close();
				} 
				catch(Exception query) {
					System.err.println("Got an exception! ");
					System.err.println(query.getMessage());
				}
			}
		});

		Label label_5 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_5.setBounds(621, 444, 234, 2);

		lblRestaurantHoursInfo = new Label(shell, SWT.NONE);
		lblRestaurantHoursInfo.setBounds(680, 459, 161, 20);
		lblRestaurantHoursInfo.setText("Restaurant Hours Info");

		lblRHrestName = new Label(shell, SWT.NONE);
		lblRHrestName.setText("*Restaurant Name:");
		lblRHrestName.setBounds(629, 485, 123, 20);

		lblRHrestCity = new Label(shell, SWT.NONE);
		lblRHrestCity.setText("*Restaurant City:");
		lblRHrestCity.setBounds(629, 510, 112, 20);
		
		//Button to get hours
		btnGetHours = new Button(shell, SWT.NONE);
		btnGetHours.setBounds(715, 550, 90, 30);
		btnGetHours.setText("Get Hours");
		btnGetHours.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String restName = txtRHrestName.getText();
					String cityName = txtRHrestCity.getText();

					String query = "SELECT DISTINCT H.time_name, H.start_time, H.end_time\r\n" +
							"FROM HOURS AS H, RESTAURANT AS R\r\n" + 
							"WHERE rest_ID IN (SELECT rID FROM RESTAURANT WHERE\r\n" + 
							"name = '" + restName + "' AND rCity = '" + cityName + "' );";

					Statement st = conn.createStatement();

					ResultSet rs = st.executeQuery(query);

					while (rs.next())
					{
						String tName = rs.getString("time_name");
						String sTime = rs.getString("start_time");  
						String eTime = rs.getString("end_time");
						//JOptionPane.showMessageDialog(null, "Your query: \n" + query + "\nResult: \n" + price + " " + food);
						System.out.format("%s, %s, %s\n", tName, sTime, eTime);
					}
					st.close();
				} 
				catch(Exception query) {
					System.err.println("Got an exception! ");
					System.err.println(query.getMessage());
				}
			}
		});

		txtRCrestName = new Text(shell, SWT.BORDER);
		txtRCrestName.setText("Name");
		txtRCrestName.setBounds(722, 109, 116, 26);

		txtRCrestCity = new Text(shell, SWT.BORDER);
		txtRCrestCity.setText("City Name");
		txtRCrestCity.setBounds(711, 140, 127, 26);

		txtRHrestName = new Text(shell, SWT.BORDER);
		txtRHrestName.setText("Name");
		txtRHrestName.setBounds(758, 479, 126, 26);

		txtRHrestCity = new Text(shell, SWT.BORDER);
		txtRHrestCity.setText("City Name");
		txtRHrestCity.setBounds(747, 510, 137, 26);

		Label lblRestaurantReviewsInfo = new Label(shell, SWT.NONE);
		lblRestaurantReviewsInfo.setText("Restaurant Reviews Info");
		lblRestaurantReviewsInfo.setBounds(671, 296, 161, 20);

		Label lblrestaurantName = new Label(shell, SWT.NONE);
		lblrestaurantName.setText("*Restaurant Name:");
		lblrestaurantName.setBounds(629, 322, 123, 20);

		Label lblrestaurantCity = new Label(shell, SWT.NONE);
		lblrestaurantCity.setText("*Restaurant City:");
		lblrestaurantCity.setBounds(630, 352, 112, 20);
		
		//Button to get reviews
		Button btnGetReviews = new Button(shell, SWT.NONE);
		btnGetReviews.setText("Get Reviews");
		btnGetReviews.setBounds(689, 392, 110, 30);
		btnGetReviews.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String restName = txtRRVrestName.getText();
					String cityName = txtRRVrestCity.getText();

					String query = "SELECT DISTINCT RV.rating, RV.message, U.name\r\n" +
							"FROM REVIEW AS RV, RESTAURANT AS R, RUSER AS U\r\n" + 
							"WHERE RV.uID = U.userID AND RV.rID IN (SELECT rID FROM RESTAURANT WHERE\r\n" + 
							"name = '" + restName + "' AND rCity = '" + cityName + "' );";

					Statement st = conn.createStatement();

					ResultSet rs = st.executeQuery(query);

					while (rs.next())
					{
						String rating = rs.getString("rating");
						String message = rs.getString("message");  
						String userName = rs.getString("name");
						//JOptionPane.showMessageDialog(null, "Your query: \n" + query + "\nResult: \n" + price + " " + food);
						System.out.format("%s, %s, %s\n", rating, message, userName);
					}
					st.close();
				} 
				catch(Exception query) {
					System.err.println("Got an exception! ");
					System.err.println(query.getMessage());
				}
			}
		});
		//Text box for restaurant names and cities
		txtRRVrestName = new Text(shell, SWT.BORDER);
		txtRRVrestName.setText("Name");
		txtRRVrestName.setBounds(759, 321, 116, 26);

		txtRRVrestCity = new Text(shell, SWT.BORDER);
		txtRRVrestCity.setText("City Name");
		txtRRVrestCity.setBounds(748, 352, 127, 26);

		Label label_6 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_6.setBounds(10, 503, 605, 2);

		Label lblQuickSearch = new Label(shell, SWT.NONE);
		lblQuickSearch.setBounds(204, 510, 85, 20);
		lblQuickSearch.setText("Quick Search");
		//Button for a random restaurant
		btnRandomRestaurant = new Button(shell, SWT.NONE);
		btnRandomRestaurant.setBounds(192, 609, 150, 30);
		btnRandomRestaurant.setText("Random Restaurant");
		btnRandomRestaurant.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					int rand = (int)(Math.random() * (248));

					String query = "SELECT name, address, rCity "
							+ "FROM RESTAURANT WHERE rID = " + rand + ";";

					// create the java statement
					Statement st = conn.createStatement();

					// execute the query, and get a java resultset
					ResultSet rs = st.executeQuery(query);

					// iterate through the java resultset
					while (rs.next())
					{
						String name = rs.getString("name");
						String address = rs.getString("address");  
						String city = rs.getString("rCity");
						// print the results
						//JOptionPane.showMessageDialog(null, "Your query: \n" + query + "\nResult: \n" + price + " " + food);
						System.out.format("%s, %s, %s\n", name, address, city);
					}
					st.close();
				} 
				catch(Exception query) {
					System.err.println("Got an exception! ");
					System.err.println(query.getMessage());
				}
			}
		});
	}
}
