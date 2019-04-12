package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import model.exceptions.DomainException;

public class Reservation {
	
	private Integer numberRoom;
	private Date checkin;
	private Date checkout;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {
	}
	
	public Reservation(Integer numberRoom, Date checkin, Date checkout) throws DomainException {
		if(!checkout.after(checkin)) {
			throw new DomainException("Error in reservation: checkout date must be after checkin date");
		}
		this.numberRoom = numberRoom;
		this.checkin = checkin;
		this.checkout = checkout;
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
	
	public void updateDates(Date checkIn, Date checkOut) throws DomainException {
		
		Date now = new Date();
		if(checkIn.before(now) || checkOut.before(now))
			throw new DomainException("Reservation dates for update must be future dates");
		if(!checkOut.after(checkIn)) 
			throw new DomainException("Error in reservation: checkout date must be after checkin date");
		
		this.checkin = checkIn;
		this.checkout = checkOut;
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
