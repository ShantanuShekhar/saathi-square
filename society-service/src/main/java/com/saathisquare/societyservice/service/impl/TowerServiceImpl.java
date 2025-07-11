package com.saathisquare.societyservice.service.impl;

import com.saathisquare.societyservice.model.Society;
import com.saathisquare.societyservice.model.Tower;
import com.saathisquare.societyservice.repository.TowerRepository;
import com.saathisquare.societyservice.service.TowerService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TowerServiceImpl implements TowerService {

	private final TowerRepository towerRepository;

	@Override
	public Tower getOrCreateTower(String towerName, Society society) {
		return towerRepository.findAll().stream().filter(t -> t.getName().equalsIgnoreCase(towerName)
				&& t.getSociety().getSocietyId().equals(society.getSocietyId())).findFirst().orElseGet(() -> {
					Tower tower = Tower.builder().name(towerName).society(society).build();
					return towerRepository.save(tower);
				});
	}
}

//if (flatRequest.getTowerName() != null && !flatRequest.getTowerName().isBlank()) {
//    Tower tower = towerService.getOrCreateTower(flatRequest.getTowerName(), society);
//    flat.setTower(tower);
//} else {
//    flat.setTower(null); // No tower scenario
//}
