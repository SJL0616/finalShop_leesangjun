package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileDAO {


	enum FileName {
		BOARD("board.txt"), MEMBER("member.txt"), ITEM("item.txt"), CART("cart.txt");
		private String name;
		FileName(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		
	
	}

	private FileDAO() {
		
	}

	private static FileDAO instance = new FileDAO();

	static public FileDAO getInstance() {
		return instance;
	}
	
	private int save(FileName name) {
		Path path = Paths.get("src/files/" + name.getName());
		File file = new File(path.toString());
		
		try (FileWriter fw = new FileWriter(file,false)){
			
			switch (name) {
	        case BOARD: 
	        	fw.write(BoardDAO.getInstance().getStringData());
			break;
			case MEMBER:
	        	fw.write(MemberDAO.getInstance().getStringData());
			break;
			case ITEM:
	        	fw.write(ItemDAO.getInstance().getStringData());
			break;
			case CART:
	        	fw.write(CartDAO.getInstance().getStringData());
			break;
		    }
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
			//System.out.println("파일이 이미 있음");
		}
		return 1;
	}
	
    private int load(FileName name) {
    	Path path = Paths.get("src/files/" + name.getName());
		File file = new File(path.toString());
    	try (FileReader fr = new FileReader(file)){
			BufferedReader br  =new BufferedReader(fr);
			String next = null;
			StringBuffer sb = new StringBuffer();
			while((next = br.readLine())!= null) {
				sb.append(next +"\n");
			}
			switch (name) {
		        case BOARD: 
				    BoardDAO.getInstance().parseData(sb.toString().split("\n"));
				break;
				case MEMBER:
					MemberDAO.getInstance().parseData(sb.toString().split("\n"));
				break;
				case ITEM:
					ItemDAO.getInstance().parseData(sb.toString().split("\n"));
				break;
				case CART:
					CartDAO.getInstance().parseData(sb.toString().split("\n"));
				break;
			}
		} catch (IOException e) {
			return 0;
			//System.out.println("파일이 이미 있음");
		}
    	return 1;
	}
    
	public int dataLoad() {

		int result = 0;
		result += load(FileName.BOARD);
		result += load(FileName.MEMBER);
		result += load(FileName.ITEM);
		result += load(FileName.CART);
		return result;
	}
	
	public int dataSave() {

		int result = 0;
		result += save(FileName.BOARD);
		result += save(FileName.MEMBER);
		result += save(FileName.ITEM);
		result += save(FileName.CART);
		return result;
	}
	

}
