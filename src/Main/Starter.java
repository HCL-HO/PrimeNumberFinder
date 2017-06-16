package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.text.DateFormatter;

import Calculation.Finder;

public class Starter {

	public static void main(String[] args) {
		
//		new Finder().loadNumbers();
		SimpleDateFormat DF = new SimpleDateFormat("hh:mm:ss");
		long start = System.currentTimeMillis();
		
		Finder find = new Finder();

		find.loadNumbers();
		find.getNumbers(1);
		
		System.out.println("Time taken: "+ (System.currentTimeMillis()- start)/1000 + "/sec");
			
//			System.out.println("--------------------------------------------");
//			List<Integer> list1 = find.loadListByBatch(BR, 2);
//			for(int i : list1){
//				System.out.println(i);
//
//			}
//			
//			System.out.println("--------------------------------------------");
//			List<Integer> list2 = find.loadListByBatch(BR, 2);
//			for(int i : list2){
//				System.out.println(i);
//
//			}


	}

}
