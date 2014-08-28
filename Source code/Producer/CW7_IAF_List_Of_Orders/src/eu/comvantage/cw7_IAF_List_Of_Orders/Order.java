package eu.comvantage.cw7_IAF_List_Of_Orders;

public class Order {
	private String id;
	private int statuscode;
	private int statuscheckinout;
	private Task task;
	private String client;
	private String processor;
	private int shirtimage;
	private int logoimage;
	private FactSheet factsheet;
	private String shirttype;
	private String orderdate;
	private String delivered;
	private String payment;
	private String duration;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	public int getShirtimage() {
		return shirtimage;
	}
	public void setShirtimage(int shirtimage) {
		this.shirtimage = shirtimage;
	}
	public int getLogoimage() {
		return logoimage;
	}
	public void setLogoimage(int logoimage) {
		this.logoimage = logoimage;
	}
	public FactSheet getFactsheet() {
		return factsheet;
	}
	public void setFactsheet(FactSheet factsheet) {
		this.factsheet = factsheet;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public String getShirttype() {
		return shirttype;
	}
	public void setShirttype(String shirttype) {
		this.shirttype = shirttype;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public String getDelivered() {
		return delivered;
	}
	public void setDelivered(String delivered) {
		this.delivered = delivered;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getStatuscheckinout() {
		return statuscheckinout;
	}
	public void setStatuscheckinout(int statuscheckinout) {
		this.statuscheckinout = statuscheckinout;
	}
}