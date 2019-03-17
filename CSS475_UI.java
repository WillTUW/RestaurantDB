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


public class CSS475_UI {

	protected Shell shell;
	private Text txtCity;
	private Text txtCuisine;
	private Text txtPriceRange;
	private Text txtFoodItem;
	private Text txtAvgRating;
	private Text txtMin;
	private Label lblFoogleRestaurantFinder;
	private Label lblNewCustomer;
	private Label lblName;
	private Label lblHomeCity;
	private Label lblPhone;
	private Label lblEmail;
	private Text txtName;
	private Text txtHomeCity;
	private Text txtPhone;
	private Text txtEmail;

	private Button btnAddCustomer;
	private Button btnMostExpensiveFood;
	private Button btnMostExpensiveFoodByCategory;
	private Button btnLeastExpensiveFoodByCity;

	// creates three different Connection objects
	public static Connection conn = null;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			try {
				// connect way #1

				String url1 = "jdbc:mysql://mysql-uwb-css475-db.civewz6zb6nl.us-west-2.rds.amazonaws.com/Restaurant_DB";
				String user = "grader";
				String password = "graderpass";

				conn = DriverManager.getConnection(url1, user, password);

				if (conn != null) {
					//JOptionPane.showMessageDialog(null,"Successfully Connected to the AWS RDS Restaurant_DB test");
					System.out.println("Successfully Connected to the AWS RDS Restaurant_DB test");
				}


				//	alternative connections
				// connect way #2
				//	            String url2 = "jdbc:mysql://mysql-uwb-css475-db.civewz6zb6nl.us-west-2.rds.amazonaws.com/Restaurant_DB?user=grader&password=graderpass";
				//	            conn = DriverManager.getConnection(url2);
				//	            if (conn != null) {
				//	            	JOptionPane.showMessageDialog(null,"Connected to the AWS RDS Restaurant_DB test 2");
				//	            }
				/*
	            // connect way #3
	            String url3 = "jdbc:mysql://mysql-uwb-css475-db.civewz6zb6nl.us-west-2.rds.amazonaws.com/Restaurant_DB";
	            Properties info = new Properties();
	            info.put("user", "grader");
	            info.put("password", "graderpass");

	            conn = DriverManager.getConnection(url3, info);
	            if (conn != null) {
	            	JOptionPane.showMessageDialog(null,"Connected to the AWS RDS Restaurant_DB test 3");
	            }
				 */
			} catch (SQLException ex) {
				System.out.println("Connection failed, user credentials, host endpoint, database selection may be invalid.");
				ex.printStackTrace();
			}
			CSS475_UI window = new CSS475_UI();
			window.open();

		} catch (Exception windowIssue) {
			windowIssue.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(995, 718);
		shell.setText("Foogle's Application");

		txtCity = new Text(shell, SWT.BORDER);
		txtCity.setText("City");
		txtCity.setBounds(118, 52, 78, 26);

		txtCuisine = new Text(shell, SWT.BORDER);
		txtCuisine.setText("Cuisine");
		txtCuisine.setBounds(118, 84, 78, 26);

		txtPriceRange = new Text(shell, SWT.BORDER);
		txtPriceRange.setText("MAX");
		txtPriceRange.setBounds(118, 116, 42, 26);

		txtFoodItem = new Text(shell, SWT.BORDER);
		txtFoodItem.setText("Food Item");
		txtFoodItem.setBounds(118, 148, 85, 26);

		txtAvgRating = new Text(shell, SWT.BORDER);
		txtAvgRating.setText("Avg. Rating");
		txtAvgRating.setBounds(118, 180, 95, 26);

		Label lblCity = new Label(shell, SWT.NONE);
		lblCity.setBounds(10, 55, 70, 20);
		lblCity.setText("CITY:");

		Label lblCuisine = new Label(shell, SWT.NONE);
		lblCuisine.setBounds(10, 87, 70, 20);
		lblCuisine.setText("CUISINE:");

		Label lblPriceRange = new Label(shell, SWT.NONE);
		lblPriceRange.setBounds(10, 116, 103, 20);
		lblPriceRange.setText("PRICE RANGE:");

		Label lblFoodItem = new Label(shell, SWT.NONE);
		lblFoodItem.setBounds(10, 148, 85, 20);
		lblFoodItem.setText("FOOD ITEM:");

		Label lblAvgRating = new Label(shell, SWT.NONE);
		lblAvgRating.setBounds(10, 180, 102, 20);
		lblAvgRating.setText("AVG. RATING:");

		Label label = new Label(shell, SWT.NONE);
		label.setBounds(166, 119, 12, 20);
		label.setText("-");

		txtMin = new Text(shell, SWT.BORDER);
		txtMin.setText("MIN");
		txtMin.setBounds(179, 116, 42, 26);

		Button btnSearcj = new Button(shell, SWT.NONE);
		btnSearcj.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnSearcj.setBounds(61, 221, 90, 30);
		btnSearcj.setText("Search");

		lblFoogleRestaurantFinder = new Label(shell, SWT.NONE);
		lblFoogleRestaurantFinder.setBounds(10, 0, 202, 20);
		lblFoogleRestaurantFinder.setText("FOOGLE RESTAURANT FINDER");

		lblNewCustomer = new Label(shell, SWT.NONE);
		lblNewCustomer.setBounds(393, 52, 103, 20);
		lblNewCustomer.setText("New Customer");

		lblName = new Label(shell, SWT.NONE);
		lblName.setBounds(335, 84, 48, 20);
		lblName.setText("Name:");

		lblHomeCity = new Label(shell, SWT.NONE);
		lblHomeCity.setBounds(305, 116, 78, 20);
		lblHomeCity.setText("Home City:");

		lblPhone = new Label(shell, SWT.NONE);
		lblPhone.setBounds(335, 148, 48, 20);
		lblPhone.setText("Phone:");

		lblEmail = new Label(shell, SWT.NONE);
		lblEmail.setBounds(335, 180, 48, 20);
		lblEmail.setText("Email:");

		txtName = new Text(shell, SWT.BORDER);
		txtName.setText("Name");
		
		txtName.setBounds(393, 81, 78, 26);

		txtHomeCity = new Text(shell, SWT.BORDER);
		txtHomeCity.setText("Home City");
		String homeCity = "%" + txtHomeCity.getText() + "%";
		txtHomeCity.setBounds(393, 113, 95, 26);

		txtPhone = new Text(shell, SWT.BORDER);
		txtPhone.setText("Phone");
		txtPhone.setBounds(393, 145, 95, 26);

		txtEmail = new Text(shell, SWT.BORDER);
		txtEmail.setText("Email");
		txtEmail.setBounds(393, 177, 78, 26);

		btnAddCustomer = new Button(shell, SWT.NONE);
		btnAddCustomer.setBounds(355, 221, 103, 30);
		btnAddCustomer.setText("Add Customer");
		btnAddCustomer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String name = txtName.getText();
					String phoneNumber = txtPhone.getText();
					String email = txtEmail.getText();
					int uID = (int)(Math.random() * (Integer.MAX_VALUE - 501) + 501);
					String query = "INSERT INTO RUSER  (userID, name) " + 
							"VALUES (?, ?)";

					System.out.println(uID);

					// create the java statement
					PreparedStatement preparedStmt = conn.prepareStatement(query);
					preparedStmt.setLong (1, uID);
					preparedStmt.setString (2, name);
					System.out.println(name);
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

		btnMostExpensiveFood = new Button(shell, SWT.NONE);
		btnMostExpensiveFood.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			@Override
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				try
				{ 
					System.out.println("DONE");
					// our SQL SELECT query. 
					// if you only need a few columns, specify them by name instead of using "*"
					String query = "SELECT name, price\r\n" + 
							"FROM FOOD_ENTRIES\r\n" + 
							"WHERE FOOD_ENTRIES.price = (SELECT MAX(price) FROM FOOD_ENTRIES);";

					// create the java statement
					Statement st = conn.createStatement();

					// execute the query, and get a java resultset
					ResultSet rs = st.executeQuery(query);

					// iterate through the java resultset
					while (rs.next())
					{
						String price = rs.getString("price");
						String food = rs.getString("name");   
						// print the results
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
		btnMostExpensiveFood.setBounds(61, 287, 217, 30);
		btnMostExpensiveFood.setText("Most Expensive Food");

		btnMostExpensiveFoodByCategory = new Button(shell, SWT.NONE);
		btnMostExpensiveFoodByCategory.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			@Override
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				try
				{ 
					System.out.println("DONE");
					// our SQL SELECT query. 
					// if you only need a few columns, specify them by name instead of using "*"
					String query = 
							"SELECT FOOD_ENTRIES.price , FOOD_ENTRIES.name, RESTAURANT.cuisine\r\n" +
									"FROM FOOD_ENTRIES, RESTAURANT\r\n" +
									"WHERE FOOD_ENTRIES.r_menu_id = RESTAURANT.rID AND FOOD_ENTRIES.price IN(SELECT MAX(price)\r\n" +
									"FROM FOOD_ENTRIES AS FE, MENU AS M, RESTAURANT AS R\r\n" +
									"WHERE FE.r_menu_id = M.r_id AND M.r_id = R.rID\r\n" +
									"GROUP BY cuisine)\r\n" +
									"ORDER BY price ASC;";

					// create the java statement
					Statement st = conn.createStatement();

					// execute the query, and get a java resultset
					ResultSet rs = st.executeQuery(query);

					// iterate through the java resultset
					while (rs.next())
					{
						String price = rs.getString("price");
						String food = rs.getString("name");  
						String name = rs.getString("cuisine");
						// print the results
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
		btnMostExpensiveFoodByCategory.setBounds(52, 312, 235, 30);
		btnMostExpensiveFoodByCategory.setText("Most Expensive Food by Ethnicity");

		btnMostExpensiveFoodByCategory = new Button(shell, SWT.NONE);
		btnMostExpensiveFoodByCategory.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			@Override
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				try
				{ 
					System.out.println("DONE");
					// our SQL SELECT query. 
					// if you only need a few columns, specify them by name instead of using "*"
					String query = 
							"SELECT FOOD_ENTRIES.price , FOOD_ENTRIES.name, RESTAURANT.cuisine\r\n" +
									"FROM FOOD_ENTRIES, RESTAURANT\r\n" +
									"WHERE FOOD_ENTRIES.r_menu_id = RESTAURANT.rID AND FOOD_ENTRIES.price IN(SELECT MIN(price)\r\n" +
									"FROM FOOD_ENTRIES AS FE, MENU AS M, RESTAURANT AS R\r\n" +
									"WHERE FE.r_menu_id = M.r_id AND M.r_id = R.rID\r\n" +
									"GROUP BY cuisine)\r\n" +
									"ORDER BY price ASC;";

					// create the java statement
					Statement st = conn.createStatement();

					// execute the query, and get a java resultset
					ResultSet rs = st.executeQuery(query);

					// iterate through the java resultset
					while (rs.next())
					{
						String price = rs.getString("price");
						String food = rs.getString("name");  
						String name = rs.getString("cuisine");
						// print the results
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
		btnMostExpensiveFoodByCategory.setToolTipText("Queries the least expensive food by ethnicity");
		btnMostExpensiveFoodByCategory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnMostExpensiveFoodByCategory.setBounds(52, 337, 235, 30);
		btnMostExpensiveFoodByCategory.setText("Least Expensive Food by Ethnicity");



		//--------------------------DOESNT WORK PROPERLY--------------------------------------
		btnLeastExpensiveFoodByCity = new Button(shell, SWT.NONE);
		btnLeastExpensiveFoodByCity.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			@Override
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				try
				{ 
					System.out.println("DONE");
					// our SQL SELECT query. 
					// if you only need a few columns, specify them by name instead of using "*"
					String query = 
							"SELECT *\r\n" +
									"FROM FOOD_ENTRIES, CITY\r\n" +
									"WHERE FOOD_ENTRIES.price IN(SELECT MIN(price)\r\n" +
									"FROM FOOD_ENTRIES AS FE, MENU, RESTAURANT, CITY\r\n" +
									"WHERE FE.r_menu_id = r_id AND r_id = rID AND rCity = CITY.name)\r\n" +
									"ORDER BY price ASC;";

					// create the java statement
					Statement st = conn.createStatement();

					// execute the query, and get a java resultset
					ResultSet rs = st.executeQuery(query);

					// iterate through the java resultset
					while (rs.next())
					{
						String price = rs.getString("price");
						String food = rs.getString("name");  
						String menu_id = rs.getString("r_menu_id");
						String city = rs.getString("CITY.name");
						// print the results
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
		btnLeastExpensiveFoodByCity.setBounds(52, 362, 235, 30);
		btnLeastExpensiveFoodByCity.setText("Least Expensive Food in Each City");

	}
}
