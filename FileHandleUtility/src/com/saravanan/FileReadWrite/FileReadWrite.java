package com.saravanan.FileReadWrite;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReadWrite {
	
	public static List<String> readFile(String filePath,boolean flag,String existStr,String replacStr) {
		BufferedReader bufRead = null;
		FileReader fileRead = null;
		List<String> list=null;

		try {
			fileRead = new FileReader(filePath);
			bufRead = new BufferedReader(fileRead);
			list=new ArrayList<>();
			String lineTxt = null;

			while ((lineTxt = bufRead.readLine()) != null) {
				if(flag) {
					lineTxt=lineTxt.replace(existStr, replacStr);
					list.add(lineTxt);
					list.add("\n");
				}else {
				   System.out.println(lineTxt);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(bufRead!=null)
				  bufRead.close();
				if(fileRead!=null)
				  fileRead.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(flag) return list;
		else return null;
	}
	
	public static void writeFile(String filePath,List<String> input) {
		FileWriter writer=null;
		try {
			writer=new FileWriter(filePath);
			for(String txt:input) { 
				writer.write(txt);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		   try {
			if(writer!=null)
				writer.close();
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
		}
	}

	public static void main(String[] args) {
		
		String filePath="data.txt";
		
		
		/*create new file*/
		File file=new File(filePath);   
		try {
			if(file.createNewFile())
				System.out.println("File created");
			else
				System.out.println("File Already present");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*File Writing*/
		List<String> list=new ArrayList<>();
		list.add("I have started CODTECH internship");
		list.add("\n");
		list.add("All Task inprogress to get completion certification");
		FileReadWrite.writeFile(filePath, list);    
		
		/*Read File*/
		System.out.println("\n");
		System.out.println("*******Reading File*******");
		FileReadWrite.readFile(filePath,false,null,null);
		
		/*modify File*/
		System.out.println("\n");
		System.out.println("*******Modify File content*******");
		List<String> res=FileReadWrite.readFile(filePath,true,"inprogress","Completed");
		FileReadWrite.writeFile(filePath, res);   
		
		/*check the modification*/
		System.out.println("\n");
		System.out.println("*******After Modification Reading File*******");
		FileReadWrite.readFile(filePath,false,null,null);
		
	}

}
