package com.hyeonil.registerweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.sqlite.SQLiteConfig;


public class DB {
    String dbFileName = "";

    Connection connection;
    
    DB() {
        this.dbFileName = "c:/kopo/registerDB.db";
    }

    private void open() {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			this.connection = DriverManager.getConnection("jdbc:sqlite:/" + this.dbFileName, config.toProperties());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void close() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		this.connection = null;
	}
	
	public void createTable() {
		String query = "CREATE TABLE member (`idx` INTEGER PRIMARY KEY AUTOINCREMENT, `userType` TEXT,`name` TEXT, `regId` TEXT, `password` TEXT, "
						+ "`gender` TEXT, `address` TEXT, `created` TEXT)";
		this.open();
		try {
			Statement statement = this.connection.createStatement();
			statement.execute(query);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}

	public void insertData(Member member) {
		String query = "INSERT INTO member (`userType`, `name`, `regId`, `password`, `gender`, `address`, `created`) VALUES (?, ?, ?, ?, ?, ?, ?)";
		this.open();
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, member.userType);
			statement.setString(2, member.name);
			statement.setString(3, member.regId);
			statement.setString(4, member.password);
			statement.setInt(5, member.gender);
			statement.setString(6, member.address);			
			statement.setString(7, member.created);
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}


	public ArrayList<Member> selectData() {
		String query = "SELECT * FROM member";
		ArrayList<Member> list = new ArrayList<Member>();
		this.open();
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet resultSet =  statement.executeQuery();
			while (resultSet.next()) {
				Member member = new Member();
				member.idx = resultSet.getInt("idx");
				member.userType = resultSet.getInt("userType");
				member.name = resultSet.getString("name");
				member.regId = resultSet.getString("regId");
				member.password = resultSet.getString("password");
				member.gender = resultSet.getInt("gender");
				member.address = resultSet.getString("address");
				member.created = resultSet.getString("created");
				list.add(member);
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return list;
	}
	
	
	public Member selectDetailData(int idx) {
		String query = "SELECT * FROM member WHERE idx = ?";
		Member member = new Member();
		this.open();
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, idx);
			ResultSet resultSet =  statement.executeQuery();
			if (resultSet.next()) {
				member.idx = resultSet.getInt("idx");
				member.userType = resultSet.getInt("userType");
				member.name = resultSet.getString("name");
				member.regId = resultSet.getString("regId");
				member.password = resultSet.getString("password");
				member.gender = resultSet.getInt("gender");
				member.address = resultSet.getString("address");
				member.created = resultSet.getString("created");
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return member;
	}
	
	public Member selectDetailData(String regId) {
		String query = "SELECT * FROM member WHERE regId = ?";
		Member member = new Member();
		this.open();
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, regId);
			ResultSet resultSet =  statement.executeQuery();
			if (resultSet.next()) {
				member.idx = resultSet.getInt("idx");
				member.userType = resultSet.getInt("userType");
				member.name = resultSet.getString("name");
				member.regId = resultSet.getString("regId");
				member.password = resultSet.getString("password");
				member.gender = resultSet.getInt("gender");
				member.address = resultSet.getString("address");
				member.created = resultSet.getString("created");
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return member;
	}
	
	
	
	public boolean deleteData(int idx) {
		String query = "DELETE FROM member WHERE idx = ?";
		Member member = new Member();
		this.open();
        int result = 0;
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, idx);
			result = statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		
        if (result > 0) {
            return true;
        }
        return false;
	}

	public void updateData(Member member) {
		String query = "UPDATE member SET userType = ?, name = ? , regId = ?, password = ?, gender = ?, address = ? WHERE idx = ?";
		
		//String query = "UPDATE member SET name = ? WHERE idx = ?";
		this.open();
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, member.userType);
			statement.setString(2, member.name);
			statement.setString(3, member.regId);
			statement.setString(4, member.password);
			statement.setInt(5, member.gender);
			statement.setString(6, member.address);			
			statement.setInt(7, member.idx);
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}

	public boolean loginData(String regId, String password) {
		String query = "SELECT * FROM member WHERE regId = ? AND password = ?";
		Member member = new Member();
		this.open();
        boolean result = false;
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, regId);
			statement.setString(2, password);
			ResultSet resultSet =  statement.executeQuery();
			if (resultSet.next()) {
				member.regId = resultSet.getString("regId");
				member.password = resultSet.getString("password");
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(member.regId != null && member.password != null) {
			result = true;
		};
		
		this.close();
		return result;
	}
}
