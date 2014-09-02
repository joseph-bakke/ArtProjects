package main;                                                                                                               
                                                                                                                            
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import processing.core.PApplet;
                                                                                                                            
public class Expenses {                                                                                                      
                                                                                                                            
	private HashMap<String, Float> dailyExpenses;
	private String date;
	private PApplet parent;
	private float total;
	                                                                                                                        
	public Expenses(String d, PApplet p) {
		this.parent = p;
		this.date = d;
		this.total=0;
		dailyExpenses = new HashMap<String, Float>();                                                                              
	}
	
	public void newExpense(String type, Float amount) { 
		dailyExpenses.put(type, amount);
		total+=amount;
	}
	
	public void addExpense(String type, Float amount) {
		if( dailyExpenses.containsKey(type) ) { 
			dailyExpenses.put(type, dailyExpenses.get(type) + amount);
			total+=amount;
		} else {
			System.err.println("Something fucked up");
			System.exit(0);
		}
		
	}
	
	public boolean expenseExists(String k) {
		return dailyExpenses.containsKey(k);
	}
	
	public float largestValue() {
		Float[] values = new Float[dailyExpenses.size()];
		Collection<Float> v = dailyExpenses.values();
		values = v.toArray(values);
		float max = 0;
		
		for( int i = 0 ; i < values.length ; i++ ) {
			if( values[i] > max ) {
				max = values[i];
			}
		}
		
		return max;
	}
	
	public float minimumValue() {
		Float[] values = new Float[dailyExpenses.size()];
		Collection<Float> v = dailyExpenses.values();
		values = v.toArray(values);
		float min = 0;
		
		for( int i = 0 ; i < values.length ; i++ ) {
			if( values[i] < min ) {
				min = values[i];
			}
		}
		
		return min;
	}
	
	public String toString() {
		Iterator<String> it = dailyExpenses.keySet().iterator();
		
		String output = "";
		
		output = "date: " + date;
		
		while( it.hasNext() ) {
			String key = it.next();
			
			output += "\n\t" + key + ": " + dailyExpenses.get(key);
		}
		
		return output;
	}
	
	public void draw() {
		float avg = (this.largestValue() + this.minimumValue())/2;
		float max = this.largestValue();
		float min = this.minimumValue();
		Float[] maxRads = new Float[dailyExpenses.size()];
		Float[] rads = new Float[dailyExpenses.size()];
		Float[] rots = new Float[dailyExpenses.size()];
		Float[] tran = new Float[dailyExpenses.size()];
		String[] type = new String[dailyExpenses.size()];
		
		Iterator<String> it = dailyExpenses.keySet().iterator();
		
		int i = 0;
		while( it.hasNext() ) {
			type[i] = it.next();
			
			float amt = dailyExpenses.get(type[i]);
		
			tran[i] = new Float(parent.height/(2*(0)));
			rots[i] = new Float(0.0);
			maxRads[i] = new Float(0.0);
			rads[i] = new Float(0.0);
			
			i++;
		}
		
		while(true) {
			parent.background(255);
			
			for( i = 0 ; i < dailyExpenses.size() ; i++ ) {
				parent.pushMatrix();
				
					parent.translate(parent.width/2, tran[i]);
					parent.rotateX(rots[i]);
					parent.ellipse(0,0,rads[i],rads[i]);
					
				parent.popMatrix();
				
				if( rads[i] < maxRads[i] ) { rads[i]++; }
			}
			
			if( parent.mousePressed ) { break; }
		}
	}
                                                                                                                            
}                                                                                                                           
