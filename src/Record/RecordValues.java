package Record;

public class RecordValues {
	
	private short charLoc;
	private short foundCount;
	
	public RecordValues (short charLoc) {
		this.charLoc = charLoc;
		this.foundCount = 0;
	}
	
	public void setCharLoc (short newCharLoc) {
		this.charLoc = newCharLoc;
	}
	
	public short getCharLoc () {
		return this.charLoc;
	}
	
	public void incrementFoundCount () {
		this.foundCount++;
	}
	
	public short getFoundCount () {
		return this.foundCount;
	}
	
	
	
}
