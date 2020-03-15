import java.io.FileWriter;
import java.util.List;

public class FileWrite {

	private String file;
	private List<ConfigRecord> configList;
	private final String HEADER = "KEYNO;DATABASE_NAME;SCHEMA_NAME;DB_TABLE_NAME;COLUMN_NAME;ALIAS_LP_TABLE;SOURCE_DB_NAME;SOURCE_SCHEMA_NAME;SOURCE_TABLE_NAME;LP_DB_NAME;LOOK_SCHEMA_NAME;LOOK_TABLE_NAME;SOURCE_NPI_COLUMN;LOOK_SZ_COLUMN;SZ_DERIVATION;JOIN_TYPE;JOIN_CRITERIA;WHERE_CRITERIA;SOURCE_KEY_COL;LP_KEY_COL;TARGET_DB_NAME;TARGET_SCHEMA_NAME;TARGET_TABLE_NAME;LEVEL;KEY_PRIORITY\n";

	public FileWrite(String file, List<ConfigRecord> configList) {
		super();
		this.file = file;
		this.configList = configList;
	}

	public void write() {
		try (FileWriter myWriter = new FileWriter(file, false)) {
			myWriter.write(HEADER);
			for (ConfigRecord record : configList) {
				String[] columns = record.getColumns();
				myWriter.write(String.join(";", columns) + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
