package com.lfp.mr.test;

import org.apache.commons.lang.StringUtils;

public class Text {
	public static void main(String args[]) {
		String[] words = StringUtils.split(FileTools.readFile("./input/wc"), " ");
		for(String word:words){
			System.out.println(word);
		}
	}
}
