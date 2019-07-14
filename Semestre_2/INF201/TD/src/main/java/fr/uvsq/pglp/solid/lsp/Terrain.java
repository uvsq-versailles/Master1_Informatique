package fr.uvsq.pglp.solid.lsp;

import java.util.ArrayList;
import java.util.List;

public class Terrain {
	private List<Robot> robotList;
	private List<RobotMobile> mobileList;

	public Terrain() {
		robotList = new ArrayList<Robot>();
		mobileList = new ArrayList<RobotMobile>();
	}
	
	public void avancerTous() {
		for (RobotMobile robotMobile : mobileList) {
			robotMobile.avance();
		}
	}

	
}
