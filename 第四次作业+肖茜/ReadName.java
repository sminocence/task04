package test04;
import java.io.*;
import java.util.*;

/*
1�����������µ��ı��ļ��ж�ȡ�����е�����������ӡ���ظ�������
���ظ��Ĵ����������ظ���������
1,����,28
2,����,35
3,����,28
4,����,35
5,����,28
6,����,35
7,����,28
8,����,35
 */

public class ReadName {
	public static void main(String[] args) {
		
		ArrayList<String> name = new ArrayList<String>();
		try {			
			FileReader file = new FileReader("F:/������������/���Ĵ�����/test.txt");
			BufferedReader names =  new BufferedReader(file);
			String str = names.readLine();			
			while(str != null){
				String []s = str.split(",");
				name.add(s[1]);
				str = names.readLine();
			}
			names.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}		
		printNames(name);
	}
	
	//��ӡ���ظ��������ʹ�������
	public static void printNames(ArrayList<String> name) {
		
		Map<String, Integer> string = new HashMap<String, Integer>();				
		for (String s : name) {			
			boolean value = string.containsKey(s);
			if (!value) {				
				string.put(s, 1);				
			} else {				
				int num = string.get(s).intValue();
				num++;
				string.put(s, num);
			}
		}
		
		//���򷽷�
		for (Map.Entry<String, Integer> entry : string.entrySet()) {  
			String key = entry.getKey();  
			Integer value = entry.getValue();
			ArrayList<Names> names = new ArrayList<Names>();			
			if (value >= 2) {	        	        
		        Names na = new Names(key, value);
		        names.add(na);								
	            Collections.sort(name);
			}				   		
		    for (Names a : names) {
	            System.out.println(a);
	        }
		}
	}	
}

class Names implements Comparable<Names> {
	String name;
	int num;
	
	Names(String name, int num) {
		this.name = name;
		this.num = num;
	}
	
	public String toString(){
		return "�ظ�����Ϊ��" + num + " " + "�ظ�����Ϊ��" + name;
	}
	
	public int compareTo(Names name) {
        if (this.num > name.num) {
            return 1;
        } else {
            return -1;
        }
    }    
}
