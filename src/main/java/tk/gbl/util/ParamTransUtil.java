package tk.gbl.util;

import tk.gbl.util.log.LoggerUtil;

public class ParamTransUtil {
	
	
	public static int[] trans(String str) {
		int rIds[] = null;
		if(str.length()>0) {
			int count = 0;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == ',') {
					count++;
				}
			}
			int[] ids = new int[count];
			for(int i=0;;i++) {
				int index = str.indexOf(',');
				String sId = str.substring(0, index);
				try {
					ids[i] = Integer.parseInt(sId);
				} catch (Exception e){
					LoggerUtil.error("ParamTransUtil.trans  String to Int", e);
				}
				str = str.substring(index + 1);
				if(str.length()<=1) {
					break;
				}
			}
			rIds = ids;
		}
		
		return rIds;
	}
	
	
}
