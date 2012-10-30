/*
 * This file is part of javaserver.
 *
 * Copyright (c) 2011-2012,
 * 							${project.organization.name} <${url}/>
 *
 * javaserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * javaserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with javaserver.  If not, see <http://www.gnu.org/licenses/>.
 */
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
