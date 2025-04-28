package rideBookingSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RideBookingSystem {
	List<Customer> customers=new ArrayList<>();
	List<Driver> drivers=new ArrayList<>();
	List<Ride> rides=new ArrayList<>();
	
	public void registerCustomer(Customer customer) {
        customers.add(customer);
    }

    public void registerDriver(Driver driver) {
        drivers.add(driver);
    }
    
    public Ride bookRide(Customer customer) {
        Optional<Driver> driverOpt = drivers.stream().filter(Driver::isAvailable).findFirst();
        if (driverOpt.isPresent()) {
            Driver driver = driverOpt.get();
            driver.setAvailable(false);
            Ride ride = new Ride(customer, driver);
            rides.add(ride);
            return ride;
        } else {
            throw new InvalidRideException("No available drivers!");
        }
    }
    
    public void saveRides() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("rides.txt"))) {
            for (Ride ride : rides) {
                bw.write(ride.rideDetails());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDriversFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("drivers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    Driver driver = new Driver(parts[0].trim(), parts[1].trim());
                    registerDriver(driver);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDriversToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("drivers.txt"))) {
            for (Driver driver : drivers) {
                bw.write(driver.id + "," + driver.name);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAllDrivers() {
        for (Driver driver : drivers) {
            driver.showProfile();
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

}
