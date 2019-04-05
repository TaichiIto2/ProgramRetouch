package beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BuyHistoBeans implements Serializable{
	private int id;
	private int userId;
	private int totalPrice;
	private int delivertMethodId;
	private Timestamp buyDate;

	private String deliveryMethodName;
	private int deliveryMethodPrice;

	private int itemid;
	private String itemname;
	private int itemprice;

	private int buyid;

	public BuyHistoBeans (int id, int itemprice, int itemid, String itemname, int buyid) {
		this.id = id;
		this.itemid = itemid;
		this.itemprice = itemprice;
		this.itemname = itemname;
		this.buyid = buyid;
	}

	public BuyHistoBeans (int id, int totalPrice, Timestamp buyDate, int delivertMethodId, int deliveryMethodPrice, String deliveryMethodName) {
		this.id = id;
		this.totalPrice = totalPrice;
		this.buyDate = buyDate;
		this.delivertMethodId = delivertMethodId;
		this.deliveryMethodPrice = deliveryMethodPrice;
		this.deliveryMethodName = deliveryMethodName;
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

	public int getDeliveryMethodPrice() {
		return deliveryMethodPrice;
	}

	public void setDeliveryMethodPrice(int deliveryMethodPrice) {
		this.deliveryMethodPrice = deliveryMethodPrice;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public int getBuyid() {
		return buyid;
	}

	public void setBuyid(int buyid) {
		this.buyid = buyid;
	}
	public int getItemprice() {
		return itemprice;
	}
	public void setItemprice(int itemprice) {
		this.itemprice = itemprice;
	}
	public String getFormatDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH時mm分");
		return sdf.format(buyDate);
	}


}
