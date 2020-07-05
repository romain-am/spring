package main.com.dragonsoft.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.com.dragonsoft.clients.ClientInfo;
import main.com.dragonsoft.credentials.User;

public class CustomSort {
	
	public static void alphabetClientInfo(List<ClientInfo> list) {
		if (list.size() > 0) {
			Collections.sort(list, new Comparator<ClientInfo>() {
				@Override
				public int compare(final ClientInfo object1, final ClientInfo object2) {
					return object1.getName().compareTo(object2.getName());
				}
			});
		}
	}
	
	public static void alphabetUser(List<User> list) {
		if (list.size() > 0) {
			Collections.sort(list, new Comparator<User>() {
				@Override
				public int compare(final User object1, final User object2) {
					return object1.getUsername().compareTo(object2.getUsername());
				}
			});
		}
	}

}
