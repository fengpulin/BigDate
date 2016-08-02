import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;

public class Text {
	public static void main(String args[]) {
		String path = "./input/friend";
		File file = new File(path);
		try {
			InputStream inputStream = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			inputStream.read(bytes);
			String string = new String(bytes);
			System.out.println(string);
			String[] strs = StringUtils.split(string,"\t");
			for (int i = 0; i < strs.length; i++) {
				System.out.print(strs[i]+" ");
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
