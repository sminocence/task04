package test04;

/*
 * 3、生产者和消费者线程同步问题  
 *  定义一个池子，用于存放产品。
 *  定义生产者不断生产产品，当池子不满的情况下可以继续放，满了则等待。消费者一直从池子取，池子空则等待。
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

//产品类
class Product {
	int num;
	Product(int num) {
		this.num = num;		
	}
	public String toString() {
		return "产品编号为:" + num;
	}
}

//放产品的类
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

//生产者类
class Producer implements Runnable {
	ProductStack box = null;
	Producer(ProductStack box) {
		this.box = box;
	}
	public void run(){
		for(int i = 0; i < 10 ; i++) {			
			Product p = new Product(i);
			box.push(p);
			System.out.println("生产了：  " + p);		
			try {
				Thread.sleep(10);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}

//消费者类
class Customer implements Runnable {
	ProductStack box = null;
	Customer(ProductStack box) {
		this.box = box;
	}
	public void run(){
		for(int i = 0; i < 10 ; i++) {						
			Product p = box.pop();
			System.out.println("消费了：  " + p);		
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}
