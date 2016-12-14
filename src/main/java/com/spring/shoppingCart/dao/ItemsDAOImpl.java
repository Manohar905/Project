package com.spring.shoppingCart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.spring.shoppingCart.model.Items;
import com.spring.shoppingCart.model.ItemsImage;

public class ItemsDAOImpl implements ItemsDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public String addItems(ItemsImage itemsImage) {
		String to_return = "failure";
		if (getItemDetails(itemsImage.getCode()) == null) {
			String query = "insert into items values (?,?,?,?,?,?,?)";
			Connection con = null;
			PreparedStatement ps = null;
			try {
				con = dataSource.getConnection();
				ps = con.prepareStatement(query);
				ps.setString(1, itemsImage.getCategory());
				ps.setString(2, itemsImage.getName());
				ps.setString(3, itemsImage.getCode());
				ps.setString(4, itemsImage.getDescription());
				ps.setInt(5, itemsImage.getPrice());
				ps.setInt(6, itemsImage.getQuantity());
				ps.setString(7, itemsImage.getImage().getOriginalFilename());
				int out = ps.executeUpdate();
				if (out != 0) {
					System.out.println("Item saved with code=" + itemsImage.getCode());
					to_return = "sucess";
				} else {
					System.out.println("Item save failed with code=" + itemsImage.getCode());
					to_return = "Item not added --- DB connectivity error";
				}
			} catch (SQLException e) {
				System.out.println(e.toString());
			} finally {
				try {
					ps.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			to_return = itemsImage.getCode() + " was already present";
		}
		return to_return;
	}

	@Override
	public ArrayList<String> searchItems(String code) {

		ArrayList<String> to_return = new ArrayList<String>();
		String query = "select code from items where code like ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, "%" + code + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				to_return.add(rs.getString("code"));
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
	public Items getItemDetails(String code) {

		String query = "select * from items where code=?";
		Items items = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, code);
			rs = ps.executeQuery();
			if (rs.next()) {
				items = new Items();
				items.setCategory(rs.getString("category"));
				items.setName(rs.getString("name"));
				items.setCode(rs.getString("code"));
				items.setDescription(rs.getString("description"));
				items.setPrice(rs.getInt("price"));
				items.setQuantity(rs.getInt("quantity"));
				items.setImage_location(rs.getString("image_location"));

				System.out.println("Item Found with code ::" + code);
			} else {
				System.out.println("No Item found with code ::" + code);
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
		return items;
	}

	@Override
	public int countToGetAllItems(String search) {
		String query = "select count(*) from items where code like ? order by locate(?,code)";
		int to_return = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, "%" + search + "%");
			ps.setString(2, search);
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
		System.out.println(to_return + "Items found in first search ::" + search);
		return to_return;
	}

	@Override
	public List<Items> getAllItemsList(String search) {
		int items_count = countToGetAllItems(search);
		if (items_count == 0) {
			return null;
		} else {
			String query = "select * from items where code like ? order by locate(?,code)";

			List<Items> itemslist = new ArrayList<Items>();
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				con = dataSource.getConnection();
				ps = con.prepareStatement(query);
				ps.setString(1, "%" + search + "%");
				ps.setString(2, search);
				rs = ps.executeQuery();
				while (rs.next()) {
					Items item = new Items();
					item.setCategory(rs.getString("category"));
					item.setName(rs.getString("name"));
					item.setCode(rs.getString("code"));
					item.setDescription(rs.getString("description"));
					item.setPrice(rs.getInt("price"));
					item.setQuantity(rs.getInt("quantity"));
					item.setImage_location(rs.getString("image_location"));
					itemslist.add(item);
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
			return itemslist;
		}
	}

	@Override
	public List<Items> getAllItemsList() {
		String query = "select * from items";

		List<Items> itemslist = new ArrayList<Items>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Items item = new Items();
				item.setCategory(rs.getString("category"));
				item.setName(rs.getString("name"));
				item.setCode(rs.getString("code"));
				item.setDescription(rs.getString("description"));
				item.setPrice(rs.getInt("price"));
				item.setQuantity(rs.getInt("quantity"));
				item.setImage_location(rs.getString("image_location"));
				itemslist.add(item);
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
		System.out.println("returning " + itemslist.size() + " items");
		return itemslist;
	}

	@Override
	public List<String> getAllNamesList() {
		// TODO Auto-generated method stub
		String query = "select name from items group by name";

		List<String> nameslist = new ArrayList<String>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				nameslist.add(rs.getString(1));
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
		System.out.println("returning " + nameslist.size() + " names");
		return nameslist;
	}

	@Override
	public List<String> getAllCategoriesList() {
		String query = "select category from items group by category";

		List<String> categorieslist = new ArrayList<String>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				categorieslist.add(rs.getString(1));
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
		System.out.println("returning " + categorieslist.size() + " categories");
		return categorieslist;
	}

	@Override
	public List<Items> getConditionalItemsList(String search, String[] categories, String[] names, int maxPrice) {
		// TODO Auto-generated method stub
		String query = "select * from items where price<=? ";

		String namesCondition = "";
		if (names.length != 0) {
			for (int i = 0; i < names.length; i++) {
				if (i == 0) {
					namesCondition += "and ( name='" + names[i] + "'";
				} else {
					namesCondition += " or name='" + names[i] + "'";
				}
			}
			namesCondition += " ) ";
		}

		String categoriesCondition = "";
		if (categories.length != 0) {
			for (int i = 0; i < categories.length; i++) {
				if (i == 0) {
					categoriesCondition += "and ( category='" + categories[i] + "'";
				} else {
					categoriesCondition += " or category='" + categories[i] + "'";
				}
			}
			categoriesCondition += " ) ";
		}

		query += categoriesCondition + namesCondition + "and code like ? order by locate(?,code)";
		System.out.println(query);
		List<Items> nameslist = new ArrayList<Items>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, maxPrice);
			ps.setString(2, "%" + search + "%");
			ps.setString(3, search);
			rs = ps.executeQuery();
			while (rs.next()) {
				Items item = new Items();
				item.setCategory(rs.getString("category"));
				item.setName(rs.getString("name"));
				item.setCode(rs.getString("code"));
				item.setDescription(rs.getString("description"));
				item.setPrice(rs.getInt("price"));
				item.setQuantity(rs.getInt("quantity"));
				item.setImage_location(rs.getString("image_location"));
				nameslist.add(item);
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
		System.out.println("returning " + nameslist.size() + " items");
		return nameslist;
	}

	@Override
	public boolean editItems(Items items) {
		// TODO Auto-generated method stub

		boolean to_return = false;
		String query = "update items set price=?,quantity=? where code=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, items.getPrice());
			ps.setInt(2, items.getQuantity());
			ps.setString(3, items.getCode());

			int out = ps.executeUpdate(); // verify
			if (out != 0) {
				System.out.println("Item Edited with code=" + items.getCode());
				to_return = true;
			} else
				System.out.println("Item editing failed with code=" + items.getCode());
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
	public boolean deleteItems(String code) {
		// TODO Auto-generated method stub

		boolean to_return = false;
		String query = "delete from items where code=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, code);

			int out = ps.executeUpdate(); // verify
			if (out != 0) {
				System.out.println("Item Deleted with code=" + code);
				to_return = true;
			} else
				System.out.println("Item deleting failed with code=" + code);
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
	public int getmaxPrice() {
		// TODO Auto-generated method stub
		int to_return = 5000;
		String query = "select max(price) from items";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			if (rs.next()) {
				to_return = rs.getInt(1);
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
