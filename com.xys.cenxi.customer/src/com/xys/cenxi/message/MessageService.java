package com.xys.cenxi.message;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MessageService {
	
	private static MessageService instance;
	
	private static Map<String, List<IMessageListener>> listenerPool = new HashMap<String, List<IMessageListener>>();
	
	private MessageService(){
	}
	
	public static MessageService getInstance(){
		if(instance == null){
			instance = new MessageService();
		}
		
		return instance;
	}
	
	public  void postMessage(Object msg){
		if(msg == null){
			System.err.println("dddddddddddddddd");
			System.err.println(listenerPool);
			return;
		}
		String key = msg.getClass().getName();
		List<IMessageListener> lis = listenerPool.get(key);
		if(lis == null)
			return;
		
		for(IMessageListener listener : lis){
			listener.handerMessage(msg);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public  void addMessageListener(Class msgClass, IMessageListener listener){
		String key = msgClass.getName();
		List<IMessageListener> listeners = listenerPool.get(key);
		if(listeners == null){
			listeners = new LinkedList<IMessageListener>();
			listeners.add(listener);
			listenerPool.put(key, listeners);
		}else{
			listeners.add(listener);
		}
	}
	

	@SuppressWarnings("rawtypes")
	public  void removeMessageListener(Class msgClass, IMessageListener listener){
		String key = msgClass.getName();
		List<IMessageListener> listeners = listenerPool.get(key);
		if(listeners != null){
			int index = 0;
			for(IMessageListener lis : listeners){
				if(lis.getClass().getName().equals(listener.getClass().getName())){
					listeners.remove(index);
					break;
				}
				index++;
			}
		}
	}
}
