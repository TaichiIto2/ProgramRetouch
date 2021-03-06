package beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


/**
 * 購入データ
 * @author d-yamaguchi
 *
 */
public class BuyDataBeans  implements Serializable {
	private int id;
	private int userId; //s
	private int totalPrice;
	private int delivertMethodId;
	private Timestamp buyDate;

	private String deliveryMethodName;
	private int deliveryMethodPrice;

	public BuyDataBeans(int id, int totalPrice, Timestamp buyDate, int delivertMethodId, int UserId,
			int deliveryMethodPrice, String deliveryMethodName) {
		this.id = id;
		this.totalPrice = totalPrice;
		this.buyDate = buyDate;
		this.delivertMethodId = delivertMethodId;
		this.userId = UserId;
		this.deliveryMethodPrice = deliveryMethodPrice;
		this.deliveryMethodName = deliveryMethodName;
	}



	public BuyDataBeans() {
		// TODO 自動生成されたコンストラクター・スタブ
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}


	public int getDelivertMethodId() {
		return delivertMethodId;
	}
	public void setDelivertMethodId(int delivertMethodId) {
		this.delivertMethodId = delivertMethodId;
	}
	public Timestamp getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Timestamp buyDate) {
		this.buyDate = buyDate;
	}
	public String getDeliveryMethodName() {
		return deliveryMethodName;
	}
	public void setDeliveryMethodName(String deliveryMethodName) {
		this.deliveryMethodName = deliveryMethodName;
	}

	public String getFormatDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH時mm分");
		return sdf.format(buyDate);
	}
	public int getDeliveryMethodPrice() {
		return deliveryMethodPrice;
	}
	public void setDeliveryMethodPrice(int deliveryMethodPrice) {
		this.deliveryMethodPrice = deliveryMethodPrice;
	}


}
