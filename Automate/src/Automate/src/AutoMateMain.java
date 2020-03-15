import java.util.Comparator;
import java.util.List;

public class AutoMateMain {

	public static void main(String[] args) {
		FileReader crReader = new FileReader("C:\\POC_SADU\\CR.txt", "5000","COMPANY_ZZ" ,"DEPARTMENT");
		List<CRRecord> crList = crReader.generateCRRecord();
		
		FileReader configReader = new FileReader("C:\\POC_SADU\\myconfig.txt","5000","COMPANY_ZZ","DEPARTMENT");
		List<ConfigRecord> configList = configReader.generateConfigRecord();
		
		RecordProcessor processor = new RecordProcessor();
		processor.process(crList, configList,"DEPARTMENT");
		
		//System.out.println("Final Recod:" + configList);
		
		configList.sort(new Comparator<ConfigRecord>() {

			@Override
			public int compare(ConfigRecord o1, ConfigRecord o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
			
		});
		FileWrite fileWrite = new FileWrite("C:\\POC_SADU\\newmyconfig.txt", configList);
		fileWrite.write();
		
	}
}
