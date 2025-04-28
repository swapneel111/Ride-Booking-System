package rideBookingSystem;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface SecurityCheck {
	String role();

}
