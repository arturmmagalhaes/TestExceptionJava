package entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
	
	private Integer numberRoom;
	private Date checkin;
	private Date checkout;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {
	}
	
	public Reservation(Integer numberRoom, Date checkin2, java.util.Date checkout2) {
		this.numberRoom = numberRoom;
		this.checkin = checkin2;
		this.checkout = checkout2;
	}

	public Integer getNumberRoom() {
		return numberRoom;
	}

	public void setNumberRoom(Integer numberRoom) {
		this.numberRoom = numberRoom;
	}

	public Date getCheckin() {
		return checkin;
	}

	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}
	
	public String updateDates(Date checkIn, Date checkOut) {
		
		Date now = new Date();
		if(checkIn.before(now) || checkOut.before(now))
			return "Reservation dates for update must be future dates";
		if(!checkout.after(checkin)) 
			return "Error in reservation: checkout date must be after checkin date";
		
		this.checkin = checkIn;
		this.checkout = checkOut;
		
		return null;
	}
	
	public long duration() {
		long diff = checkout.getTime() - checkin.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	@Override
	public String toString() {
		return "Room " + getNumberRoom() 
		+ ", check-in: " + sdf.format(getCheckin()) 
		+ ", check-out: " +	sdf.format(getCheckout()) + ", " 
		+ duration() + " nights";
	}
}
