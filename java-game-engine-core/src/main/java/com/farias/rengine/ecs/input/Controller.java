package com.farias.rengine.ecs.input;

import com.farias.rengine.ecs.Component;
import com.farias.rengine.GameEngine;
import com.farias.rengine.io.InputSystem;

public class Controller extends Component {
	private String[] buttons;
	
	public Controller() {
		this.buttons = new String[InputSystem.KEY_LAST];
	}
	
	@Override
	public void onInit() {
		System.out.println("Controller component initialized");
	}
	
	@Override
	public void update(float deltaTime) {
		for (int i = 0; i < buttons.length; i++) {
			String button = buttons[i];
			if (GameEngine.getInput().isKeyPressed(i)) {
				String keyPressEvent = button + "_pressed";
				if (GameEngine.getEventSystem().isRegistered(keyPressEvent)) {
					GameEngine.getEventSystem().triggerEvent(keyPressEvent);
				}
			}
			if (GameEngine.getInput().isKeyReleased(i)) {
				String keyReleasedEvent = button + "_released";
				if (GameEngine.getEventSystem().isRegistered(keyReleasedEvent)) {
					GameEngine.getEventSystem().triggerEvent(keyReleasedEvent);
				}
			}
		}
	}
	
	public void addButton(int code, String button) {
		buttons[code] = button;
		String keyPressEvent = button + "_pressed";
		String keyReleasedEvent = button + "_released";
		GameEngine.getEventSystem().registerEvent(keyPressEvent);
		GameEngine.getEventSystem().registerEvent(keyReleasedEvent);
	}
}
