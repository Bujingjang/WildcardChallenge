package Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.List;

public class Record {
	
	private HashMap<Integer, RecordValues> record;
	
	public Record() {
		this.record = new HashMap<>();
	}
	
	public void setCharLocAtIndex(int idx, short charLoc) {
		
		if (!this.record.containsKey(idx)) {
			this.record.put(idx, new RecordValues(charLoc));
		}
		
		else {
			this.record.get(idx).setCharLoc(charLoc);
		}
		
	}
	
	public void incFoundCountAtIndex(int idx) {
		this.record.get(idx).incrementFoundCount();
	}
	
	public short getCharLocAtIndex(int idx) {
		return this.record.get(idx).getCharLoc();
	}
	
	public int getFoundCountAtIndex(int idx) {
		return this.record.get(idx).getFoundCount();
	}
	
	public Set<Integer> getKeySet () {
		return this.record.keySet();
	}
	
	public boolean hasIndex(int idx) {
		return this.record.containsKey(idx);
	}

	public ArrayList<Integer> filterKeyWithTotalFoundCount (int foundCount) {

		ArrayList<Integer> filteredKey = new ArrayList<>();
		Set<Integer> keySet = this.getKeySet();
		List<Integer> keys = new ArrayList<Integer>(keySet);

		for(int i = 0; i < keys.size(); i++) {
			// System.out.println("keys: " + keys.get(i));
			// System.out.println("get found count: " + this.getFoundCountAtIndex(keys.get(i)));
			if (this.getFoundCountAtIndex(keys.get(i)) == foundCount) {
				filteredKey.add(keys.get(i));
			}
		}

		return filteredKey;

	}
	
}
