package concurrencia;

public class CC_01_Threads extends Thread{
	private static final int N = 10;
	private int name;
	
	public CC_01_Threads(int i) {
		name = i;
	}
	
	@Override
	public void run(){ 
		System.out.println("The tread " + name +" has started"+'\n');			
		try {
			long t = (long) (Math.random()*1000);
			System.out.println("The thread "+ name +" will be sleeping for "+ t +" miliseconds"+'\n');
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("The tread " + name +" has finished"+'\n');

	}

	public static void main(String[] args) throws InterruptedException {
		Thread [] hilos = new Thread[N];
		System.out.println("START"+'\n');
		for (int i = 0;i<N;i++) {
			Thread h = new CC_01_Threads(i);
			hilos[i] = h;
			h.start();
		}
		for (int i = 0;i<N;i++) {
			hilos[i].join();
		}
		System.out.println("END");
		
	}

}