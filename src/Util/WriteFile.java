package Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
	public void write(String s){
		System.out.println("--------------write---------------------");
		if(s.length()<=0){
			return;
		}
		try {
			BufferedWriter BW = new BufferedWriter(new FileWriter(new File("PrimeNumber"), true));
			BW.newLine();
			BW.write(s);
			BW.flush();
			BW.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writeLastNum(String s){
		System.out.println("--------------writeLastNum---------------------");
		if(s.length()<=0){
			return;
		}
		try {
			BufferedWriter BW = new BufferedWriter(new FileWriter(new File("TheLastNum")));
			BW.write(s);
			BW.flush();
			BW.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
