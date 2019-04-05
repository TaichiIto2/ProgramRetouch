package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import base.DBManager;
import beans.BuyDataBeans;
import beans.BuyHistoBeans;

/**
 *
 * @author d-yamaguchi
 *
 */
public class BuyDAO {


	/**
	 * 購入情報登録処理
	 * @param bdb 購入情報
	 * @throws SQLException 呼び出し元にスローさせるため
	 */
	public static int insertBuy(BuyDataBeans bdb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		int autoIncKey = -1;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement(
					"INSERT INTO t_buy(user_id,total_price,delivery_method_id,create_date) VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, bdb.getUserId());
			st.setInt(2, bdb.getTotalPrice());
			st.setInt(3, bdb.getDelivertMethodId());
			st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				autoIncKey = rs.getInt(1);
			}
			System.out.println("inserting buy-datas has been completed");

			return autoIncKey;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 購入IDによる購入情報検索
	 * @param buyId
	 * @return BuyDataBeans
	 * 				購入情報のデータを持つJavaBeansのリスト
	 * @throws SQLException
	 * 				呼び出し元にスローさせるため
	 */
	public static BuyDataBeans getBuyDataBeansByBuyId(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement(
					"SELECT * FROM t_buy"
							+ " JOIN m_delivery_method"
							+ " ON t_buy.delivery_method_id = m_delivery_method.id"
							+ " WHERE t_buy.id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();

			BuyDataBeans bdb = new BuyDataBeans();
			if (rs.next()) {
				bdb.setId(rs.getInt("id"));
				bdb.setTotalPrice(rs.getInt("total_price"));
				bdb.setBuyDate(rs.getTimestamp("create_date"));
				bdb.setDelivertMethodId(rs.getInt("delivery_method_id"));
				bdb.setUserId(rs.getInt("user_id"));
				bdb.setDeliveryMethodPrice(rs.getInt("price"));
				bdb.setDeliveryMethodName(rs.getString("name"));
			}

			System.out.println("searching BuyDataBeans by buyID has been completed");

			return bdb;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	//UserIdから情報を参照
	public static List<BuyDataBeans> getBuyDataBeansByUserId(int userid) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		List<BuyDataBeans>  buyList = new ArrayList<BuyDataBeans>();

		try {
			con = DBManager.getConnection();

			st = con.prepareStatement(
					"SELECT * FROM t_buy"
							+ " JOIN m_delivery_method"
							+ " ON t_buy.delivery_method_id = m_delivery_method.id"
							+ " WHERE t_buy.user_id = ?"
							+ " ORDER BY create_date DESC");
			st.setInt(1, userid);

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int totalPrice = rs.getInt("total_price");
				Timestamp buyDate = rs.getTimestamp("create_date");
				int delivertMethodId = rs.getInt("delivery_method_id");
				int userId = rs.getInt("user_id");
				int deliveryMethodPrice = rs.getInt("price");
				String deliveryMethodName = rs.getString("name");
				BuyDataBeans data = new BuyDataBeans(id, totalPrice, buyDate, delivertMethodId, userId, deliveryMethodPrice, deliveryMethodName );

				buyList.add(data);
				System.out.println(id);
			}
			System.out.println("searching BuyDataBeans by UserID has been completed");

			return buyList;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}

		}
	}


	//購入詳細 下部 購入詳細
	public static   List<BuyHistoBeans> buyDetail(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		List<BuyHistoBeans>  buyHist = new ArrayList<BuyHistoBeans>();

		try {
			con = DBManager.getConnection();

			st = con.prepareStatement(
					"SELECT * FROM t_buy_detail"
							+ " join  m_item"
							+ " on t_buy_detail.item_id = m_item.id"
							+ " WHERE t_buy_detail.buy_id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int itemprice = rs.getInt("price");
				int itemid = rs.getInt("item_id");
				String itemname = rs.getString("name");
				int buyid = rs.getInt("buy_id");

				System.out.println(itemname);
				BuyHistoBeans data = new BuyHistoBeans (id, itemprice, itemid, itemname, buyid);
				buyHist.add(data);
			}
			System.out.println("searching BuyDataBeans by UserID has been completed");

			return buyHist;

		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}

		}
	}

	//購入詳細 上部
	public static BuyHistoBeans getBuyhis(int Id) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = DBManager.getConnection();

			st = con.prepareStatement(
					"SELECT * FROM t_buy"
							+ " JOIN m_delivery_method"
							+ " ON t_buy.delivery_method_id = m_delivery_method.id"
							+ " WHERE t_buy.id = ?");
			st.setInt(1, Id);

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int totalPrice = rs.getInt("total_price");
				Timestamp buyDate = rs.getTimestamp("create_date");
				int delivertMethodId = rs.getInt("delivery_method_id");
				int deliveryMethodPrice = rs.getInt("price");
				String deliveryMethodName = rs.getString("name");


				return new BuyHistoBeans(id, totalPrice, buyDate, delivertMethodId, deliveryMethodPrice, deliveryMethodName);

			}
			System.out.println("searching BuyDataBeans by UserID has been completed");

		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}

		}
		return null;
	}
}


