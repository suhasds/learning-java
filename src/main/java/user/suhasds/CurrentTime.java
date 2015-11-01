package user.suhasds;

import java.util.*;

public class CurrentTime {

	public static Date getTime() {
		return new Date();
	}
	
	public static void main(String[] args) {
		System.out.println(CurrentTime.getTime());
		for (String arg : args) {
			System.out.println(arg);	
		}
	}

}
