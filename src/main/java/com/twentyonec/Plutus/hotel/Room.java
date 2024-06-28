package com.twentyonec.Plutus.hotel;

public class Room {
	
	private String roomID;
	private boolean booked;
	private OccupancyType occupancyType;
	private boolean isAC;
	private boolean isTV;
	private boolean isExtra;
	private int extra = 100;
	private int price = 0;
	private int deposit = 0;
	
	public Room(String roomID, OccupancyType occupancyType, boolean isAC, boolean isTV, boolean isExtra) {
        this.roomID = roomID;
        this.occupancyType = occupancyType;
        this.isAC = isAC;
        this.isTV = isTV;
        this.isExtra = isExtra;

        this.setupRevenue();
        
        //TODO get booked or not from storage.
    }
	
	private void setupRevenue() {
		
		this.price += Integer.parseInt(this.occupancyType.getPrice());
		switch(occupancyType) {
		
		case SINGLE:
			if (isTV)
				price+=100;
			if (isAC)
				price+=300;
			break;
			
		case DOUBLE:
			if (isTV)
				price+=50;
			if (isAC)
				price+=550;
			break;
			
		case TRIPLE:
			if (isTV)
				price+=100;
			if (isAC)
				price+=500;
			break;
			
		case QUADRUPLE:
			if (isTV)
				price+=200;
			if (isAC)
				price+=900;
			if (isExtra)
				extra+=100;
			break;
		}
		if (isExtra)
			price+=extra;
		deposit = 3 * price / 8;
		
	}
	
	public int[] getPrice() {
		return new int[]{deposit, price};
	}


}