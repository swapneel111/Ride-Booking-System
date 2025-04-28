package rideBookingSystem;

public class Driver extends User {
	private boolean available=true;

	public Driver(String id, String name) {
		super(id, name);
		this.available=true;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available=available;
	}
	
	@Override
	
	public void showProfile() {
		System.out.println(this.name+" "+this.available);
	}
	

}
