package user.suhasds;

import java.util.*;

public class CurrentTime {

	public static Date getTime() {
		return new Date();
	}
	
	public static void main(String[] args) {
		
		if (args.length > 0) {
			System.out.print(args[0]);	
		}
		
		System.out.println(CurrentTime.getTime());
		
	}

}
