package Record;

public interface RecordInterface {
	
	void insertIndex (int idx, int charLoc);
	
	void setCharLocAtIndex(int idx, int newCharLoc);
	
	void incFoundCountAtIndex(int idx);
	
	int getCharLocAtIndex(int idx);
	
	int getFoundCountAtIndex(int idx);

}
