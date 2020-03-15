import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
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
		this.key = keyNo ;
		this.fn = fileName ;
		this.schemaName = schema;
	}

	public List<CRRecord> generateCRRecord() {
		List<CRRecord> crList = new ArrayList<>();
		
//		  try (Stream<String> lines = Files.lines(Paths.get(file))) { crList =
//		  lines
//		  .filter(p -> p.startsWith(this.filter))
//		  .map(FileReader::crMapping)
//		  .sorted((r1, r2) -> r1.getKey().compareTo(r2.getKey()))
//		  .collect(Collectors.toList());
//		  System.out.println("crList: " + crList); System.out.println("crList Size: " +
//		  crList.size()); } catch (Exception e) { e.printStackTrace(); }
		 
		
		Pattern key1 = Pattern.compile(this.key);
		Pattern schemaName = Pattern.compile(this.schemaName);
		Pattern tableName = Pattern.compile(this.fn);
		//Pattern empType = Pattern.compile("(Contract|Permanent)");
		try (Stream<String> stream = Files.lines(Paths.get(file))) { crList =
			 stream
			 .map(FileReader::crMapping)	
			 .filter(s -> key1.matcher(s.getColumns()[0]).matches())
			 .filter(s -> schemaName.matcher(s.getColumns()[2]).matches())
			 .filter(s -> tableName.matcher(s.getColumns()[3]).matches())
			 .sorted( (r1, r2) -> r1.getKey().compareTo(r2.getKey()))
			 .collect(Collectors.toList());	
					
		    //System.out.println("--->"+ crList);
		    System.out.println("--->"+ crList.size());
		} catch (IOException e) {
		    e.printStackTrace();
		}

//		try (Stream<String> lines = Files.lines(Paths.get(file))) {
//			crList = lines.map(FileReader::crMapping)
//					.filter(p -> p.startsWith(this.key))
//					.sorted((r1, r2) -> r1.getKey().compareTo(r2.getKey())).collect(Collectors.toList());
//			//System.out.println("crList: " + crList);
//			System.out.println("crList Size: " + crList.size());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return crList;
	}

	private static CRRecord crMapping(String line) {
		return new CRRecord(line.split(";"));
	}

	public List<ConfigRecord> generateConfigRecord() {
		List<ConfigRecord> crList = new ArrayList<>();

		
//		  try (Stream<String> lines = Files.lines(Paths.get(file))) { crList =
//		  lines.filter(p -> p.startsWith(this.filter)).map(FileReader::configMapping)
//		  .sorted((r1, r2) ->
//		  r1.getKey().compareTo(r2.getKey())).collect(Collectors.toList());
//		  //System.out.println("configList: " + crList);
//		  System.out.println("configList Size: " + crList.size()); } catch (Exception
//		  e) { e.printStackTrace(); }
		 

		Pattern key1 = Pattern.compile(this.key);
		Pattern schemaName = Pattern.compile(this.schemaName);
		Pattern tableName = Pattern.compile(this.fn);
		//Pattern empType = Pattern.compile("(Contract|Permanent)");
		try (Stream<String> stream = Files.lines(Paths.get(file))) { crList =
			 stream
			 .map(FileReader::configMapping)	
			 .filter(s -> key1.matcher(s.getColumns()[0]).matches())
			 .filter(s -> schemaName.matcher(s.getColumns()[2]).matches())
			 //.filter(s -> tableName.matcher(s.getColumns()[3]).matches())
			 .sorted( (r1, r2) -> r1.getKey().compareTo(r2.getKey()))
			 .collect(Collectors.toList());	
					
		    //System.out.println("--->"+ crList);
		    System.out.println("--->"+ crList.size());
		} catch (IOException e) {
		    e.printStackTrace();
		}		
		
		
//		  try (Stream<String> lines = Files.lines(Paths.get(file))) { crList =
//		  lines.filter(p -> p.contains(this.key) && p.contains(this.schemaName) && p.contains(this.fn)).map(FileReader::configMapping) .sorted((r1, r2) ->
//		  r1.getKey().compareTo(r2.getKey())).collect(Collectors.toList());
//		  System.out.println("configList: " + crList);
//		  System.out.println("configList Size: " + crList.size()); } catch (Exception
//		  e) { e.printStackTrace(); }
		 

		return crList;
	}

	private static ConfigRecord configMapping(String line) {
		return new ConfigRecord(line.split(";"));
	}

}
