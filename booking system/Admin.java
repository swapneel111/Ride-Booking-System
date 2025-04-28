package rideBookingSystem;
import java.lang.*;
import java.util.*;

@SecurityCheck(role="Admin")

public class Admin extends User {

	public Admin(String id, String name) {
		super(id, name);
	}
	
	@Override
	public void showProfile() {
		Class<SecurityCheck> c=SecurityCheck.class;
		SecurityCheck s=c.getDeclaredAnnotation(SecurityCheck.class);
		System.out.println(this.name+ " "+ "Role "+ s.role());
	}
	
	 public void removeDriver(List<Driver> drivers, String driverId) {
	        try {
	            Class<Admin> cl = Admin.class;
	            SecurityCheck securityCheck = cl.getDeclaredAnnotation(SecurityCheck.class);
	            if (securityCheck != null && "Admin".equals(securityCheck.role())) {
	                Iterator<Driver> iterator = drivers.iterator();

	                while (iterator.hasNext()) {
	                    Driver driver = iterator.next();
	                    if (driver.id.equals(driverId)) {
	                        iterator.remove();
	                        System.out.println("Driver with ID " + driverId + " has been removed.");
	                        return;
	                    }
	                }
	                System.out.println("Driver ID " + driverId + " not found.");
	            } else {
	                System.out.println("You do not have permission to remove drivers.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
