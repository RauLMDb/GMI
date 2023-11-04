package aed.individual5;


import es.upm.aedlib.Entry;
import es.upm.aedlib.Pair;
import es.upm.aedlib.map.*;


public class TempUtils {
	public static Map<String,Integer> maxTemperatures
	(long startTime,long endTime,TempData[] tempData) {
		Map<String,Integer> res = new HashTableMap<>();
		if (tempData == null || tempData.length == 0) return res;
		String location = null;
		int temperature = 0;
		for (TempData ciudad : tempData) {
			if (inTime(startTime,endTime,ciudad)) { 
				location = ciudad.getLocation();
				temperature = ciudad.getTemperature();
				Integer temp = res.put(location,temperature);
				if (temp != null && temp > temperature) 
					res.put(location, temp);
			}
		}
		return res ;
	}

	public static Pair<String,Integer> maxTemperatureInComunidad
	(long startTime, long endTime,String region, TempData[] tempData, Map<String,String> comunidadMap) {
		if (endTime == 0 || region == null || tempData == null || 
			tempData.length == 0 || comunidadMap == null || comunidadMap.size() == 0) return null;
		Pair<String,Integer> res = null;
		Pair<String,Integer> aux = new Pair<>("",0);
		String location = null;
		int temperature = 0;
		Map<String,String> comunidadMapAux = new HashTableMap<>();
		for (Entry<String,String> ciudad : comunidadMap) {
			if (ciudad.getValue().equals(region)) comunidadMapAux.put(ciudad.getKey(),ciudad.getValue());
		}
		for (TempData ciudad : tempData) {
			location = ciudad.getLocation();
			temperature = ciudad.getTemperature();
			if (comunidadMapAux.containsKey(location) && 
				aux.getRight() <= temperature && inTime(startTime,endTime,ciudad)) {
				aux.setLeft(location);
				aux.setRight(temperature);
			}
		}
		if (!aux.getLeft().equals(""))res = aux;
		return res;
	}
	private static boolean inTime (long startTime,long endTime,TempData ciudad) {
		boolean res = false;
		if (ciudad.getTime() >= startTime && ciudad.getTime() <= endTime) res = true;
		return res;
	}
}
