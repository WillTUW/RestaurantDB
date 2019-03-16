import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class UI {

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

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UI window = new UI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
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
		shell.setSize(531, 316);
		shell.setText("SWT Application");
		
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
		lblFoogleRestaurantFinder.setBounds(147, 10, 202, 20);
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

	}
}
