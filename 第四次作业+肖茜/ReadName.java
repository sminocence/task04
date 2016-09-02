package test04;
import java.io.*;
import java.util.*;

/*
1、从类似如下的文本文件中读取出所有的姓名，并打印出重复的姓名
和重复的次数，并按重复次数排序
1,张三,28
2,李四,35
3,张三,28
4,王五,35
5,张三,28
6,李四,35
7,赵六,28
8,田七,35
 */

public class ReadName {
	public static void main(String[] args) {
		
		ArrayList<String> name = new ArrayList<String>();
		try {			
			FileReader file = new FileReader("F:/极客网工作室/第四次任务/test.txt");
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
	
	//打印出重复的姓名和次数方法
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
		
		//排序方法
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
		return "重复次数为：" + num + " " + "重复名字为：" + name;
	}
	
	public int compareTo(Names name) {
        if (this.num > name.num) {
            return 1;
        } else {
            return -1;
        }
    }    
}
