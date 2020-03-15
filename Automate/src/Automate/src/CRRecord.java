import java.util.Arrays;

public class CRRecord {
	private String[] columns = new String[6];
	private String key;

	public CRRecord(String[] columns) {
		this.columns = columns;
		key = this.columns[3] + ":" + this.columns[4];
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "CRRecord [columns=" + Arrays.toString(columns) + ", key=" + key + "]";
	}

}
