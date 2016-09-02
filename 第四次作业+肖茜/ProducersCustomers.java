package test04;

/*
 * 3�������ߺ��������߳�ͬ������  
 *  ����һ�����ӣ����ڴ�Ų�Ʒ��
 *  ���������߲���������Ʒ�������Ӳ���������¿��Լ����ţ�������ȴ���������һֱ�ӳ���ȡ�����ӿ���ȴ���
 */

public class ProducersCustomers {
	public static void main(String[] args) {
		ProductStack stack = new ProductStack();
		Producer pro = new Producer(stack);
		Customer cus = new Customer(stack);
		new Thread(pro).start();
		new Thread(cus).start();				
	}
}

//��Ʒ��
class Product {
	int num;
	Product(int num) {
		this.num = num;		
	}
	public String toString() {
		return "��Ʒ���Ϊ:" + num;
	}
}

//�Ų�Ʒ����
class ProductStack {
	int index = 0;
	Product[] arr= new Product[10];
	
	public synchronized void push(Product pro) {
		while(index == arr.length) {
			try{
				this.wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
		arr[index] = pro;
		index++;		
	}
	
	public synchronized Product pop() {
		while(index == 0) {
			try{
				this.wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
		index--;
		return arr[index];
	}
}

//��������
class Producer implements Runnable {
	ProductStack box = null;
	Producer(ProductStack box) {
		this.box = box;
	}
	public void run(){
		for(int i = 0; i < 10 ; i++) {			
			Product p = new Product(i);
			box.push(p);
			System.out.println("�����ˣ�  " + p);		
			try {
				Thread.sleep(10);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}

//��������
class Customer implements Runnable {
	ProductStack box = null;
	Customer(ProductStack box) {
		this.box = box;
	}
	public void run(){
		for(int i = 0; i < 10 ; i++) {						
			Product p = box.pop();
			System.out.println("�����ˣ�  " + p);		
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}
