package com.greatmancode.javaserver.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ListenerRegistration {

	private Map<Listener, Method> list = new HashMap<Listener, Method>();
	
	public void addListener(Listener listener, Method method) {
		if (listener != null && method != null) {
			list.put(listener, method);
		}
	}
	
	protected void callEvent(Event event) {
		for (Entry<Listener, Method> methodList : list.entrySet()) {
			try {
				methodList.getValue().invoke(methodList.getKey(), event);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
