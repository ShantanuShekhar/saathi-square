package com.saathisquare.societyservice.service;

import com.saathisquare.societyservice.model.Society;
import com.saathisquare.societyservice.model.Tower;

public interface TowerService {

	Tower getOrCreateTower(String towerName, Society society);

}
