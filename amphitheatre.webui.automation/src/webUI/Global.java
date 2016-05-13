package webUI;

import java.util.LinkedHashSet;
import java.util.Set;

public class Global {
	public static Set<String> environmentsUsed = new LinkedHashSet<String>();
		
	public static int getDifferentIntForEachEnvironment(String environment){
		int result = 1;
		
		environmentsUsed.add(environment);

		int i=0;
		for (String s : environmentsUsed) {
			i++;
		    if(s.equals(environment)){
		    	result = i;
		    	break;
		    }
		}

		return result;
	}
}
