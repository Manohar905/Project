package com.spring.shoppingCart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.spring.shoppingCart.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public String addCustomer(Customer c) {
		String to_return = "failure";
		if(getCustomerByName(c.getUsername())!=null){
			to_return="User name already exists";
			return to_return;
		}
		
		String query = "insert into customer values (?,?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			// to connect to database where dastasource is refered in spring.xml
			ps = con.prepareStatement(query);
			ps.setString(1, c.getUsername());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getFirstname());
			ps.setString(4, c.getLastname());
			ps.setString(5, c.getEmail_id());
			ps.setString(6, c.getAddress());
			ps.setInt(7, c.getPincode());
			ps.setString(8, c.getContact());
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Customer saved with id=" + c.getUsername());
				to_return = "sucess";
			} else
				System.out.println("Customer save failed with id="
						+ c.getUsername());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return to_return;
	}

	@Override
	public Customer getCustomerByName(String username) {
		String query = "select * from customer where username=?";
		Customer c = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				c = new Customer();
				c.setUsername(username);
				c.setPassword(rs.getString("password"));
				c.setFirstname(rs.getString("firstname"));
				c.setLastname(rs.getString("lastname"));
				c.setEmail_id(rs.getString("email_id"));
				c.setAddress(rs.getString("address"));
				c.setPincode(rs.getInt("pincode"));
				c.setContact(rs.getString("contact"));

				System.out.println("Customer Found::" + username);
			} else {
				System.out.println("No Customer found with username="
						+ username);
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}

	@Override
	public boolean checklogin(String username, String password) {
		// TODO Auto-generated method stub
		boolean to_return = false;
		String query = "select * from customer where username=? and password=?";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				System.out.println("Customer found with username=" + username);
				to_return = true;
			} else {
				System.out.println("No Customer found with username="
						+ username);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return to_return;
	}
}
