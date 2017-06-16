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
			BufferedReader BR =  new BufferedReader(new FileReader(new File("PrimeNumber")));
			line = BR.readLine();
			while(line != null){
				fullMes += line;
				line = BR.readLine();
			}
			BR.close();	
			
			BR =  new BufferedReader(new FileReader(new File("TheLastNum")));
			line = BR.readLine();
			if(line != null){
				theLastNum = Integer.parseInt(line.split(",")[0]);
				batchNum = Integer.parseInt(line.split(",")[1]);	
			}
			
			nums = fullMes.split(",");
			System.out.println("Numbers Stored: "+ nums.length);
			
			for(String s : nums){
				int num = (s.isEmpty())? 2 : Integer.parseInt(s);
				numList.add(num);
				if(num > theLastNum){
					break;
				}
			}

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return numList;
		
	}
	
	public void getNumbers(List<Integer> list, int batch){
		numList = list;
		System.out.println("--------------getNumbers---------------------");
		Collections.sort(numList);
		newNum = theLastNum;
		int found = 0;
		int size = batch * batchSize;
		while(found < size){
			if(proccessor(list, newNum)){
				list.add(newNum);
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
			int startNum = (batchNum*batchSize == 0)? 0: batchNum*batchSize +1;
			for(int i = startNum ;  i<= (batchNum+1)*batchSize; i++){
				results += String.valueOf(numList.get(i)) + ",";
			}
			System.out.println("--------------Batch: "+ batchNum);
			batchNum++;
			writer.writeLastNum(String.valueOf(numList.get(numList.size()-1))+","+String.valueOf(batchNum));
			writer.write(results);
		}
	}

	private boolean proccessor(List<Integer> list, int newNum) {
//		System.out.println("--------------proccessor---------------------");
//		System.out.println("newNumb: " + newNum);
		int i = 0;
		while(newNum % list.get(i) != 0){
			if(list.size()-1 == i){
				return true;
			} else {
				i++;
			}
		}
		return false;
	}
}
