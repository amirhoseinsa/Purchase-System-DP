import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * 
 * @author amir hosein Created on 20 May 2018
 */
public class DB {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.h2.Driver";
	static final String DB_URL = "jdbc:h2:~/test";

	// Database credentials
	static final String USER = "sa";
	static final String PASS = "";

	public static void main(String[] args) {
		DB db = new DB();

		db.createTable("CREATE TABLE   ITEMS " + "(id INTEGER not NULL, "
				+ " name VARCHAR(255) not NULL, " + " price INTEGER not NULL, "
				+ " PRIMARY KEY ( id ))");

		db.createTable("CREATE TABLE   ORDERS_BILL "
				+ "(id bigint auto_increment, " + " Price INTEGER not NULL, "
				+ " PRIMARY KEY ( id ))");

		db.createTable("CREATE TABLE   ORDERS_DETAIL "
				+ "(id bigint auto_increment, "
				+ " ItemID INTEGER not NULL, "
				+ " amount INTEGER not NULL, "
				+ " billID INTEGER not NULL, "
				+ " PRIMARY KEY ( id ) , FOREIGN KEY ( billID ) REFERENCES ORDERS_BILL( id ) )");

		db.addRecordsToITEMS();
	}

	private void createTable(String sql) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute a query
			System.out.println("Creating table in given database...");
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");

			// Clean-up environment
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
	}

	private void addRecordsToITEMS() {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql = "INSERT INTO ITEMS " + "VALUES (100, 'Zara', 18)";

			stmt.executeUpdate(sql);
			sql = "INSERT INTO ITEMS " + "VALUES (101, 'TV', 250)";

			stmt.executeUpdate(sql);
			sql = "INSERT INTO ITEMS " + "VALUES (102, 'Laptop', 300)";

			stmt.executeUpdate(sql);
			System.out.println("Inserted records into the table...");

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// Close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void readRecordsAndFillItems(ArrayList<Item> items) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql = "SELECT id, name, price FROM ITEMS";
			ResultSet rs = stmt.executeQuery(sql);

			// Add finish to Array list, It will used for terminate the process
			// of taking orders.
			items.add(new Item(0, "finish", -1));

			while (rs.next()) {
				int id = rs.getInt("id");
				int price = rs.getInt("price");
				String name = rs.getString("name");

				items.add(new Item(id, name, price));
			}

			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// Close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void addRecordsToORDERS(ArrayList<Cart> carts, double price) {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			// Add bill to ORDERS_BILL
			String sql = "INSERT INTO ORDERS_BILL " + "VALUES (default, "
					+ price + ")";

			stmt.executeUpdate(sql);

			// Get the foreign key from ORDERS_BILL
			sql = "SELECT TOP 1 * FROM ORDERS_BILL ORDER BY id DESC";
			ResultSet rs = stmt.executeQuery(sql);

			int id = -1;
			while (rs.next()) {
				id = rs.getInt("id");
			}

			// Add ORDERS_DETAIL
			for (Cart currentCart : carts) {

				int itemID = currentCart.getItem().getId();
				int amountItem = currentCart.getAmount();

				sql = "INSERT INTO ORDERS_DETAIL " + "VALUES (default, "
						+ itemID + ", " + amountItem + "," + id + ")";
				stmt.executeUpdate(sql);
			}

			System.out.println("Inserted records into the table...");

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			System.out.println("Can't insert your record! :(");
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("Can't insert your record! :(");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void readORDERS_DETAIL() {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql = "SELECT id , ItemID , amount , billID FROM ORDERS_DETAIL";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				int ItemID = rs.getInt("ItemID");
				int amount = rs.getInt("amount");
				int billID = rs.getInt("billID");

				System.out.println(id + " " + ItemID + " " + amount + " "
						+ billID);
			}
			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// Close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}