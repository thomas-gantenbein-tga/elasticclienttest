package elasticclienttest;

import java.util.LinkedHashMap;
import java.util.Map;

public class Timer {
	private static Map<String, Long> timeList = new LinkedHashMap<>();
	private static long previousTimeTaken = 0;

	static void takeTime(String message) {
		if (previousTimeTaken == 0) {
			timeList.put(message, 0l);
			previousTimeTaken = System.currentTimeMillis();
		} else {
			timeList.put(message, System.currentTimeMillis() - previousTimeTaken);
			previousTimeTaken = System.currentTimeMillis();
		}
	}

	static void printTimes() {
		Long gesamtZeitInMillis = 0l;
		for(Map.Entry<String, Long> entry : timeList.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
			gesamtZeitInMillis += entry.getValue();
		}
		System.out.println("Gesamtzeit: " + gesamtZeitInMillis);
		previousTimeTaken = 0;
	}

}
