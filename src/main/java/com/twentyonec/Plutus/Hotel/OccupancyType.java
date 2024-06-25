package com.twentyonec.Plutus.Hotel;

public enum OccupancyType {
	
	SINGLE("400"),
	DOUBLE("600"),
	TRIPLE("800"),
	QUADRUPLE("1000");
	
	private final String occupancyType;
	
	OccupancyType(final String occupancyType) {
		this.occupancyType = occupancyType;
	}
	
	public String getPrice() {
        return this.occupancyType;
    }

}