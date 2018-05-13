package Register;

import java.sql.*;
/**
 * Singleton class about database such as create table, add data and update data.
 * @author Hayato Kawai
 *
 */
public class Database {

	public static Database instance;
	public String id;
	public String password;
	public int level;
	public int round;
	public int money;
	public int team;
	public int unit1;
	public int unit2;
	public int unit3;
	public int unit4;
	public int heroDamage;
	public int weaponSlot;
	public int save;
	
	
	private Database() {
	}
	
	public  static Database getInstance() {
		if(instance == null) instance = new Database();
		return instance;
	}
	
	/**
     * Connect to the test.db database
     * @return the Connection object
     */
	public Connection connect() {
		// SQLite connection string
		String url = "jdbc:sqlite:Member.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {}
		return conn;
	}
	
	public void creatDatabase() {
		try (Connection conn = this.connect()) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
			}
		} catch (SQLException e) {}
	}

	public void creatTable() {
		String sql = "CREATE TABLE Member " +
						" (USERNAME    TEXT, " +
						" PASSWORD    TEXT, " +
						" LEVEL       INT, " +
						" ROUND       INT, " +
						" MONEY       INT, " +
						" TEAM        INT, " +
						" UNIT1       INT, " +
						" UNIT2       INT, " +
						" UNIT3       INT, " +
						" UNIT4       INT, " +
						" HERODAMAGE  INT, " +
						" WEAPONSLOT  INT, " +
						" SAVE  INT)";
		
		try {
			Connection conn = this.connect();
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (Exception e) {}
	}

	/**
     * Insert a new row into the Member table
     * @param useranem for member
     * @param password for member
     */
    public void addNewMember(String username , String password ) {
        String sql = "INSERT INTO Member(USERNAME,PASSWORD,LEVEL,ROUND,MONEY,TEAM,UNIT1,UNIT2,UNIT3,UNIT4,HERODAMAGE,WEAPONSLOT,SAVE)"
        		+ " VALUES(?,?,1,1,0,0,0,0,0,0,1,0,0)";
 
        try {Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    /**
     * Get the member from username and password.
     * @param username for member.
     * @param password for member.
     */
    public boolean getMember(String username,String pass){
               String sql = "SELECT *"
                          + "FROM Member WHERE USERNAME = ? AND PASSWORD =?;";
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            // set the value
            pstmt.setString(1,username);
            pstmt.setString(2,pass);
            ResultSet rs  = pstmt.executeQuery();
            if(!rs.next()) return false;
            // loop through the result set
            	id 		   =  rs.getString("USERNAME");
                password   =  rs.getString("PASSWORD");
                level	   =  rs.getInt("LEVEL");
                round 	   =  rs.getInt("ROUND");
                money	   =  rs.getInt("MONEY");
                team       =  rs.getInt("TEAM");
                unit1      =  rs.getInt("UNIT1");
                unit2      =  rs.getInt("UNIT2");
                unit3 	   =  rs.getInt("UNIT3");
                unit4 	   =  rs.getInt("UNIT4");
                heroDamage =  rs.getInt("HERODAMAGE");
                weaponSlot =  rs.getInt("WEAPONSLOT");
                save 	   =  rs.getInt("SAVE");
            
        } catch (SQLException e) {
        }
        return true;
    	  }
    
	
    /**
     * Update data of a member specified by the USERNAME.
     * @param username of member.
     * @param level that player reach.
     * @param round that player reach in level.
     * @param money is money in player's purse
     * @param team is status for team.
     * @param unit1 is status for unit1.
     * @param unit2 is status for unit2.
     * @param unit3 is status for unit3.
     * @param unit4 is status for unit4.
     * @param weaponDamage is damage that player's weapon can do
     * @param weaponSlot is slot of weapon that player use.
     */
    public void update(String username , int level , int round , int money , int team , int unit1 ,
    		int unit2 , int unit3 , int unit4 , int weaponDamage , int weaponSlot ){
    	username = ""+ username + "";
    	String sql = "UPDATE Member SET LEVEL = ? , "
    								 + "ROUND = ? , "
    								 + "MONEY = ? , "
    								 + "TEAM = ? , "
    								 + "UNIT1 = ? , "
    								 + "UNIT2 = ? , "
    								 + "UNIT3 = ? , "
    								 + "UNIT4 = ? , "
    								 + "HERODAMAGE = ? , "
    								 + "WEAPONSLOT = ? , "
    								 + "SAVE = 1 "
    								 + "WHERE USERNAME = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
        	pstmt.setInt(1, level);
        	pstmt.setInt(2, round);
        	pstmt.setInt(3, money);
        	pstmt.setInt(4, team);
        	pstmt.setInt(5, unit1);
        	pstmt.setInt(6, unit2);
        	pstmt.setInt(7, unit3);
        	pstmt.setInt(8, unit4);
        	pstmt.setInt(9, weaponDamage);
        	pstmt.setInt(10, weaponSlot);
        	pstmt.setString(11, username);
            // update 
        	
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
    }

