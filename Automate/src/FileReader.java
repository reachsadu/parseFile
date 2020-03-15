import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

	private String file;
	private String filter;
	private String key;
	private String fn;
	private String schemaName;

	public FileReader(String file, String keyNo, String schema,  String fileName) {
		this.file = file;
		this.filter = keyNo + ";";
		this.key = keyNo;
		this.fn = fileName;
		this.schemaName = schema;
	}

	public List<CRRecord> generateCRRecord() {
		List<CRRecord> crList = new ArrayList<>();
		/*
		 * try (Stream<String> lines = Files.lines(Paths.get(file))) { crList =
		 * lines.filter(p -> p.startsWith(this.filter)).map(FileReader::crMapping)
		 * .sorted((r1, r2) ->
		 * r1.getKey().compareTo(r2.getKey())).collect(Collectors.toList());
		 * System.out.println("crList: " + crList); System.out.println("crList Size: " +
		 * crList.size()); } catch (Exception e) { e.printStackTrace(); }
		 */

		try (Stream<String> lines = Files.lines(Paths.get(file))) {
			crList = lines.filter(p -> p.contains(this.key) && p.contains(this.schemaName) && p.contains(this.fn)).map(FileReader::crMapping)
					.sorted((r1, r2) -> r1.getKey().compareTo(r2.getKey())).collect(Collectors.toList());
			System.out.println("crList: " + crList);
			System.out.println("crList Size: " + crList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return crList;
	}

	private static CRRecord crMapping(String line) {
		return new CRRecord(line.split(";"));
	}

	public List<ConfigRecord> generateConfigRecord() {
		List<ConfigRecord> crList = new ArrayList<>();

		
		  try (Stream<String> lines = Files.lines(Paths.get(file))) { crList =
		  lines.filter(p -> p.startsWith(this.filter)).map(FileReader::configMapping)
		  .sorted((r1, r2) ->
		  r1.getKey().compareTo(r2.getKey())).collect(Collectors.toList());
		  System.out.println("configList: " + crList);
		  System.out.println("configList Size: " + crList.size()); } catch (Exception
		  e) { e.printStackTrace(); }
		 

		
		 /* try (Stream<String> lines = Files.lines(Paths.get(file))) { crList =
		  lines.filter(p -> p.contains(this.key) &&
		  p.contains(this.fn)).map(FileReader::configMapping) .sorted((r1, r2) ->
		  r1.getKey().compareTo(r2.getKey())).collect(Collectors.toList());
		  System.out.println("configList: " + crList);
		  System.out.println("configList Size: " + crList.size()); } catch (Exception
		  e) { e.printStackTrace(); }*/
		 

		return crList;
	}

	private static ConfigRecord configMapping(String line) {
		return new ConfigRecord(line.split(";"));
	}

}
