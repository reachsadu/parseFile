import java.util.Arrays;

public class ConfigRecord {
	private String[] columns = new String[25];
	private String key;

	public ConfigRecord(String[] columns) {
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
