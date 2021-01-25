package yamazon;

public class ItemBean {

	private String itemID;
	private String itemName;
	private String itemMaker;
	private String Price;
	private String Stock;
	private String Image;
	private String SelectCnt;

	public String getitemID() {
		return itemID;
	}

	public void setitemID(String itemid) {
		itemID = itemid;

	}

	public String getitemName() {
		return itemName;
	}

	public void setitemName(String itemname) {
		itemName = itemname;
	}

	public String getitemMaker() {
		return itemMaker;
	}

	public void setitemMaker(String itemmaker) {
		itemMaker = itemmaker;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getStock() {
		return Stock;
	}

	public void setStock(String stock) {
		Stock = stock;
	}
	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getSelectCnt() {
		return SelectCnt;
	}
	public void setSelectCnt(String selectcnt) {
		SelectCnt = selectcnt;
	}
}
