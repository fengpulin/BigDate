package com.lfp.mr.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileTools {

	private static final Logger log = Logger.getGlobal();

	/**
	 * 写文件
	 * 
	 * @param path
	 * @param str
	 * @return
	 */
	public static boolean witeFile(String path, String str) {
		if (path != null && str != null) {
			File file = new File(path);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
					log.log(Level.WARNING, "path not exists");
				}
			}

			byte[] bytes = str.getBytes();
			try {
				OutputStream out = new FileOutputStream(file);
				out.write(bytes);
				out.close();
				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	/**
	 * 读文件
	 * 
	 * @param path
	 * @return
	 */
	public static String readFile(String path) {
		File file = new File(path);
		String fileString = null;
		try {
			InputStream inputStream = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			inputStream.read(bytes);
			fileString = new String(bytes);
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileString;
	}
}
