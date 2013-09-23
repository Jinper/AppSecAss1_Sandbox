

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

/*
* Author: Jin Pan
* Assigment1: Truing Compelte Sandbox
**/

public class Sandbox {

	String[] wordList={"SET","ADD","SUB","MUL","DIV"} ;
	ArrayList<String> lineList = new ArrayList(50);
	ArrayList<String> varList = new ArrayList(50);
	ArrayList<Integer> numList = new ArrayList(50);
	
	//constructor
	//the para must be the filepath of your file which is like D:/java/xxx.txt
	public Sandbox(String filePath){                                 
		try {
			 File file = new File(filePath); 
			 if (file.isFile() && file.exists()) {                  
				 InputStreamReader read = new InputStreamReader(new FileInputStream(file));   
			     BufferedReader bufferedReader = new BufferedReader(read);   
			     
			     String lineTXT = null; 
			     
			     while ((lineTXT = bufferedReader.readLine()) != null) {   
			         lineList.add(lineTXT.toString());
			         }   
			     read.close();
			     }
			else{
				System.out.println("Can not find the file!");
				}
			 } catch (Exception e) {
				 System.out.println("Erro occurs when read the file!");   
			     e.printStackTrace();
			     }
		processor();
		
	}
	
	// processor is the method to do the compiling work
	public void processor(){
		int count = lineList.size();
		
		for(int i=0;i<count;i++){
			String line = lineList.get(i);
			String[] element = line.split(" ");
			int space = element.length;
			
			// use the number of space to judge the command's format
			//3 space means the format is like "SET N 10"
			if(space==3){
				String cmd = element[0];
				String var = element[1];
				int val1 = Integer.parseInt(element[2]);
				
				if (cmd.equals("SET")){
					setVar(var,val1);
				}		
			}
			
			//5pace means the format is like "SET F3 ADD F1 F2"
			if(space==5){
				int finalVar = 0;
				String cmd = element[0];
				String var = element[1];
				String op = element[2];
				String val1 = element[3];
				String val2 = element[4];
				
				if (op.equals("ADD")){
					finalVar = add(val1,val2);
				}
				if (op.equals("SUB")){
					finalVar = sub(val1,val2);
				}
				if (op.equals("MUL")){
					finalVar = mul(val1,val2);
				}
				if (op.equals("DIV")){
					finalVar = div(val1,val2);
				}
				
				setVar(var,finalVar);
			}
			
		}
	}
	
	//setVar do the work to store the values and print them
	public void setVar(String s, int i){
		varList.add(s);
		numList.add(i);
		System.out.print(i +" ");
	}
	
	public int add(String s, String l){
		int index1= varList.indexOf(s);
		int index2= varList.indexOf(l);
		int finalVar = numList.get(index1) + numList.get(index2);
		return finalVar;
	}
	
	public int sub(String s, String l){
		int index1= varList.indexOf(s);
		int index2= varList.indexOf(l);
		int finalVar = numList.get(index1) - numList.get(index2);
		return finalVar;
	}
	
	public int mul(String s, String l){
		int index1= varList.indexOf(s);
		int index2= varList.indexOf(l);
		int finalVar = numList.get(index1) * numList.get(index2);
		return finalVar;
	}
	
	public int div(String s, String l){
		int index1= varList.indexOf(s);
		int index2= varList.indexOf(l);
		int finalVar = numList.get(index1)/numList.get(index2);
		return finalVar;
	}
	
	// main()
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Welcome to Jin's Sandbox");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        System.out.println("Enter the filepath (file path is like D:/java/xxx.txt):");
        str = br.readLine();
        
		Sandbox sandox = new Sandbox(str);

	}

}
