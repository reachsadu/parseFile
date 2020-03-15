import java.util.ArrayList;
import java.util.List;

public class RecordProcessor {
	private List<Integer> insertList = new ArrayList<>();
	private List<Integer> deleteList = new ArrayList<>();
	private final int[] POSITION = { 0, 1, 2, 3, 4, -1, 1, 2, 3, -1, -1, -1, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,-1, -1 };
	
	public void process(List<CRRecord> crList, List<ConfigRecord> configList, String fileName) {
		int crSize = crList.size();
		int configSize = configList.size();
		int i = 0;
		int j = 0;
		for (; i < crSize && j < configSize;) {
			int match = crList.get(i).getKey().compareTo(configList.get(j).getKey());
			if (match == 0) {
				update(crList.get(i), configList.get(j));
				System.out.println(crList.get(i).getKey() + " - UPDATED.");
				i++;
				j++;
			} else if (match < 0) {
				insertList.add(i);
				System.out.println(crList.get(i).getKey() + " - will be Inserted."+i);
				i++;
			} else if (match > 0) {
				deleteList.add(j);
				System.out.println(configList.get(j).getKey() + " - will be Deleted."+j);
				j++;
			}
		}

		for (; i < crSize; i++) {
			insertList.add(i);
			System.out.println(crList.get(i).getKey() + " - will be Inserted.-->"+i);
		}

		/*
		 * for (; j < configSize; j++) { deleteList.add(j);
		 * System.out.println(configList.get(j).getKey() + " - will be Deleted -->."+j);
		 * }
		 */

		delete(configList);
		insert(crList, configList);

	}

	private void update(CRRecord crRecord, ConfigRecord configRecord) {
		for (int i = 0; i < configRecord.getColumns().length; i++) {
			if (POSITION[i] != -1) {
				configRecord.getColumns()[i] = crRecord.getColumns()[POSITION[i]];
			}
		}
	}

	private void delete(List<ConfigRecord> configList) {
		int i = 0;
		for (int pos : deleteList) {
			configList.remove(pos-i);
			i++;
		}
	}

	private void insert(List<CRRecord> crList, List<ConfigRecord> configList) {
		for (int pos : insertList) {
			CRRecord crRecord = crList.get(pos);
			String[] columns = new String[25];
			for (int i = 0; i < 25; i++) {
				if (POSITION[i] != -1) {
					columns[i] = crRecord.getColumns()[POSITION[i]];
				} else {
					columns[i] = "";
					columns[5] = "oo";
					columns[15] = "OUTER";
					columns[23] = "1";
					columns[24] = "1";
				}
			}
			ConfigRecord configRecord = new ConfigRecord(columns);
			configList.add(configRecord);
		}
	}

}
