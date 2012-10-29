package com.greatmancode.javaserver.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.greatmancode.javaserver.plugin.Plugin;

public class EventManager {

	private Map<Class<?>, ListenerRegistration> eventList = new HashMap<Class<?>, ListenerRegistration>();
	
	public Event callEvent(Event event) {
		if (eventList.containsKey(event.getClass())) {
			eventList.get(event.getClass()).callEvent(event);
		}
		return event;
	}
	
	protected void registerEvents(Listener listener) {
		if (listener != null) {
			internalRegisterEvents(listener);
		}
	}
	
	//TODO: Do something with the plugin var.
	public void registerEvents(Plugin plugin, Listener listener) {
		if (plugin != null && listener != null) {
			internalRegisterEvents(listener);
		}
	}
	
	private void internalRegisterEvents(Listener listener) {
		if (listener != null) {
			Method[] methods = listener.getClass().getMethods();
			for (Method method : methods) {
				if (method.getAnnotation(EventHandler.class) != null) {
					Class<?>[] parameters = method.getParameterTypes();
					if (parameters.length == 1) {
						for (Class<?> parameter : parameters) {
							if (Event.class.isAssignableFrom(parameter)) {
								if (eventList.containsKey(parameter.getClass())) {
									eventList.get(parameter).addListener(listener, method);
								} else {
									ListenerRegistration registration = new ListenerRegistration();
									registration.addListener(listener, method);
									eventList.put(parameter, registration);
								}
								
							}
						}
					}
				}
			}
		}
	}
}
