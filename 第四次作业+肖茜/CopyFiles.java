package test04;
import java.io.*;
import java.util.ArrayList;

/*
 * 2�����ʵ�� ����һ����E:\Geek�����ļ��У�
 * ������һ���ļ����µ����к�׺��Ϊ".java" ���ļ�
 * ���Ƶ�"E:\Geek"�ļ����У�ע��"\"��ת���ַ����⣩
 */

public class CopyFiles {
	public static void main(String[] args) throws Exception {
		
		String pathRead = "E:\\Copy";
		String pathWrite = "E:\\Geek";
		File fWrite = new File(pathWrite);
		File fRead = new File(pathRead);		
		if(!(fRead.exists() && fRead.isDirectory())) 
			throw new Exception("Ŀ¼������");	
		if(!fWrite.exists()) fWrite.mkdir();		
		ArrayList<String> listFileName = new ArrayList<String>();         
        ArrayList<String> listFiles = new ArrayList<String>();
        getAllFileName(pathRead, listFileName);
        
        //��ȡ.java�ļ�
        for (String name : listFileName) {         	
        	if(name.endsWith(".java")) listFiles.add(name);         	
        } 
        
        for (String name : listFiles) {                                   
            FileInputStream  fis = null;
            FileOutputStream fos = null;           
            try { 
            	File a = new File(name);
            	if (!a.exists())
                    new FileNotFoundException();
            	System.out.println(a.getName());
                fis =  new FileInputStream(a.getAbsolutePath());
                fos = new FileOutputStream(new File(pathWrite,a.getName()));                
                copy(fis,fos);               
                fis.close();
                fos.close();
            } catch (Exception e){
            	e.printStackTrace();
              }            
        }	             
    }
	
	//copy����
	public static void copy(FileInputStream in,FileOutputStream out) throws Exception {
		 int len = 0;
         byte[] b = new byte[1024];
         while((len = in.read(b)) != -1) {
             out.write(b,0,len);
         }
    }
	
    //��ȡһ���ļ����µ������ļ�
	public static void getAllFileName(String path,ArrayList<String> fileName) {
        File file = new File(path);
        File [] files = file.listFiles();               
        for (File a : files) {
            if(a.isDirectory()) getAllFileName(a.getPath(), fileName);            
            fileName.add(a.getPath()); 
        }
    }  
}

