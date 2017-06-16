package Calculation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Util.WriteFile;

public class Finder {
	/*
	 * 1) INIT- Load each line from record to initialize
	 * 2) BUSSINESS- getNumbers to perform business algorithm
	 * 3) READEXISTINGDATA- loadListByBatch to read existing
	 * 4) WRITENEWDATA- batchWriteLog in the BUSSINESS to record the new data
	 * 
	 *  
	 */
	List<Integer> numList = new ArrayList<Integer>();
	int newNum;
	WriteFile writer = new WriteFile();
	int batchNum = 0;
	int batchSize = 15;
	int theLastNum = 2;
	
	public List<Integer> loadNumbers(){
		System.out.println("--------------loadNumbers---------------------");
		String line;
		String fullMes = "";
		String[] nums;
		try {
//			BufferedReader BR =  new BufferedReader(new FileReader(new File("PrimeNumber")));
//			line = BR.readLine();
//			while(line != null){
//				fullMes += line;
//				line = BR.readLine();
//			}
//			BR.close();	
			
			BufferedReader BR =   new BufferedReader(new FileReader(new File("TheLastNum")));
			line = BR.readLine();
			if(line != null){
				theLastNum = Integer.parseInt(line.split(",")[0]);
				batchNum = Integer.parseInt(line.split(",")[1]);	
			}
			
			nums = fullMes.split(",");
			System.out.println("Numbers Stored: "+ nums.length);
			
//			for(String s : nums){
//				int num = (s.isEmpty())? 2 : Integer.parseInt(s);
//				numList.add(num);
//				if(num > theLastNum){
//					break;
//				}
//			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return numList;
		
	}
	
	public void getNumbers(int batch){
//		numList = list;
		System.out.println("--------------getNumbers---------------------");
		Collections.sort(numList);
		newNum = theLastNum;
		int found = 0;
		int size = batch * batchSize;
		while(found < size){
			if(proccessor(newNum)){
				numList.add(newNum);
				System.out.println("Number found:" + newNum);
				newNum++;
				found++;
				batchWriteLog(found);
			}
			newNum++;
		}
		
		System.out.println("--------------End---------------------");
	}

	private void batchWriteLog(int found) {
		if(found % batchSize == 0){
			// make String
			String results ="";
//			int startNum = (batchNum*batchSize == 0)? 0: (batchNum*batchSize) +1;
			for(int i = 0 ; i< numList.size(); i++){
				results += String.valueOf(numList.get(i)) + ",";
			}
			System.out.println("--------------Finish Batch: "+ batchNum+ "---------------------------");
			batchNum++;
			writer.writeLastNum(String.valueOf(numList.get(numList.size()-1))+","+String.valueOf(batchNum));
			writer.write(results);
			numList.clear();
		}
	}

	private boolean proccessor(int newNum) {
//		System.out.println("--------------proccessor---------------------");
//		System.out.println("newNumb: " + newNum);
		BufferedReader BR;
		try {
		BR = new BufferedReader(new FileReader(new File("PrimeNumber")));
		List<Integer> list = loadListByBatch(BR, 2);
		int i = 0;
		
		while(newNum % list.get(i) != 0){
				if(list.size()-1 == i){
					list = loadListByBatch(BR, 2);
					i = 0;
					if(list.size()==0){
						return true;
					}
				} else {
					i++;
				}
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Integer> loadListByBatch(BufferedReader BR, int batchSize){
		String line;
		int lineNum = 1;
		String mes = "";
		String[] nums = new String[]{};
		List<Integer> numsList = new ArrayList<Integer>();
		try {
			while((line=BR.readLine())!=null){
				System.out.println("loadListByBatch: StartLoop");
					if(line.isEmpty()){
						System.out.println("loadListByBatch: Empty line");
					} else {
						System.out.println("loadListByBatch: Valid line");
						mes += line;
						lineNum++;
						if(lineNum>batchSize){
							break;
						}
					}
			}
			if(mes!=null){
				System.out.println("loadListByBatch: Finish");
				nums = mes.split(",");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(String s: nums){
			if(!s.isEmpty()){
				numsList.add(Integer.parseInt(s));	
			}
		}
		return numsList;
	}
}
