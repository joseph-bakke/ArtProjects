package main;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import processing.core.PApplet;

public class Main extends PApplet {

	private String csv = "data/Spending.csv";
	private BufferedReader br;
	private HashMap<String, Expenses> data;
	
	public void setup() {
		
		size(1024, 760, P3D);
		background(0);
		
		data = new HashMap<String, Expenses>();
		
		String line = "";
		String delim = ",";
		
		try {
			br = new BufferedReader(new FileReader(csv));
			
			while((line = br.readLine()) != null ) {
				
				String splitLine[] = line.split(delim);
				String date = splitLine[0];
				String type = splitLine[1];
				float amount = Float.parseFloat(splitLine[2].substring(2,splitLine[2].length()-1));
				
				if( data.containsKey(date) ) {
					Expenses e = data.get(date);
					
					if( e.expenseExists(type) ) { 
						e.addExpense(type, amount);
					} else {
						e.newExpense(type, amount);
					}
				} else {
					Expenses e = new Expenses(date, this);
					e.newExpense(type, amount);
					data.put(date, e);
					
				}
			}
			
			br.close();
			
			Set<String> keys = data.keySet();
			Iterator<String> it = keys.iterator();
			
			/*while( it.hasNext() ) {
				System.out.println(data.get(it.next()));
			}*/
			
		} catch (FileNotFoundException e ) {
			e.printStackTrace();
		} catch (IOException e ) {
			e.printStackTrace();
		}
	}
	
	public void draw() {
		Set<String> keys = data.keySet();
		Iterator<String> it = keys.iterator();
		
		while( it.hasNext() ) {
			data.get(it.next()).draw();
		}
		
		//data.get(it.next()).draw();
	}
}
