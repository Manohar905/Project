package com.spring.shoppingCart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.spring.shoppingCart.model.Mycart;

public class MycartDAOImpl implements MycartDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean addToMycart(Mycart mycart) {
		// TODO Auto-generated method stub
		boolean to_return = false;
		if (findInMycart(mycart.getCodes())) {
			// item already present in my cart
			System.out
					.println(mycart.getCodes() + " already present in mycart");
			to_return = false;
		} else {
			// new item to my cart
			String query = "insert into mycart values(?,?,?,?)";
			Connection con = null;
			PreparedStatement ps = null;
			try {
				con = dataSource.getConnection();
				ps = con.prepareStatement(query);
				ps.setString(1, mycart.getCodes());
				ps.setInt(2, mycart.getQuantity());
				ps.setInt(3, mycart.getPrice());
				ps.setInt(4, mycart.getTotal());
				int out = ps.executeUpdate();
				if (out == 0) {
					to_return = false;
					System.out.println(mycart.getCodes()
							+ " NOT added to mycart");
				} else {
					System.out.println(mycart.getCodes()
							+ " added to mycart succesfully");
					to_return = true;
				}
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
		}
		return to_return;
	}

	@Override
	public boolean deleteFromMycart(String code) {
		// TODO Auto-generated method stub
		boolean to_return = false;
		String query = "delete from mycart where codes=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, code);
			int out = ps.executeUpdate();
			if (out == 0) {
				System.out.println(code + " NOT deleted from mycart");
				to_return = false;
			} else {
				System.out.println(code + " deleted from mycart");
				to_return = true;
			}
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
	public boolean editQuantity(String code, int quantity) {
		// TODO Auto-generated method stub
		boolean to_return = false;
		String query = "update mycart set quantity=?,total=price*quantity where codes=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, quantity);
			ps.setString(2, code);
			int out = ps.executeUpdate();
			if (out == 0) {
				System.out.println(code + " NOT Updated in mycart");
				to_return = false;
			} else {
				System.out.println(code + " Updated in mycart to::" + quantity);
				to_return = true;
			}
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
	public boolean findInMycart(String code) {
		// TODO Auto-generated method stub
		boolean to_return = false;
		String query = "select * from mycart where codes=?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, code);
			rs = ps.executeQuery();
			if (rs.next()) {
				to_return = true;
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

	@Override
	public void clearMycart() {
		// TODO Auto-generated method stub
		String query = "truncate table mycart";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.executeUpdate();
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
	}

	@Override
	public int countMycart() {
		// TODO Auto-generated method stub
		int to_return = 0;
		String query = "select sum(quantity) from mycart";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			rs.next();
			to_return = rs.getInt(1);
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

	@Override
	public List<Mycart> getMycart() {
		// TODO Auto-generated method stub
		String query = "select * from mycart";

		List<Mycart> mycartlist = new ArrayList<Mycart>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Mycart mycart = new Mycart(rs.getString("codes"),
						rs.getInt("quantity"), rs.getInt("price"),
						rs.getInt("total"));
				mycartlist.add(mycart);
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
		System.out.println("returning " + mycartlist.size() + " items");
		return mycartlist;
	}

	@Override
	public int totalMycart() {
		// TODO Auto-generated method stub
		int to_return = 0;
		String query = "select sum(total) from mycart";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			rs.next();
			to_return = rs.getInt(1);
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
