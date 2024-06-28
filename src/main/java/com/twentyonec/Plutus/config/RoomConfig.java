package com.twentyonec.Plutus.config;

import java.util.List;
import java.util.Map;

import com.twentyonec.Plutus.hotel.OccupancyType;
import com.twentyonec.Plutus.hotel.Room;

public class RoomConfig extends Config{
	private static String CONFIG_PATH = "room_config";
	
	private static String GROUND_PATH = "ground_floor";
	private static String FIRST_PATH = "first_floor";
	private static String SECOND_PATH = "second_floor";
	
	public RoomConfig() {
        super(CONFIG_PATH);
    }
	
	private Room initializeRoom(Map<String, Object> roomData) {
        String id = (String) roomData.get("id");
        boolean isTV = (boolean) roomData.get("is_tv");
        boolean isAC = (boolean) roomData.get("is_ac");
        String type = (String) roomData.get("type");

        OccupancyType occupancyType = OccupancyType.valueOf(type.toUpperCase());

        return new Room(id, occupancyType, isAC, isTV, false);
    }
	
	public Room[] getGroundFloor() {
        List<Map<String, Object>> groundFloorData = (List<Map<String, Object>>) yamlData.get(GROUND_PATH);
        Room[] groundFloorRooms = new Room[groundFloorData.size()];

        for (int i = 0; i < groundFloorData.size(); i++) {
            groundFloorRooms[i] = initializeRoom(groundFloorData.get(i));
        }

        return groundFloorRooms;
    }
	
	public Room[] getFirstFloor() {
        List<Map<String, Object>> firstFloorData = (List<Map<String, Object>>) yamlData.get(FIRST_PATH);
        Room[] firstFloorRooms = new Room[firstFloorData.size()];

        for (int i = 0; i < firstFloorData.size(); i++) {
        	firstFloorRooms[i] = initializeRoom(firstFloorData.get(i));
        }

        return firstFloorRooms;
    }
	
	public Room[] getSecondFloor() {
        List<Map<String, Object>> secondFloorData = (List<Map<String, Object>>) yamlData.get(SECOND_PATH);
        Room[] secondFloorRooms = new Room[secondFloorData.size()];

        for (int i = 0; i < secondFloorData.size(); i++) {
        	secondFloorRooms[i] = initializeRoom(secondFloorData.get(i));
        }

        return secondFloorRooms;
    }

}
