package concurrencia;

import es.upm.babel.cclib.*;

public class CC_02_Carrera {


	private static final int N = 10;
	private static final int M = 20;
	private  static int n = 0;
	static Semaphore s = new Semaphore(1);

	public static class Suma extends Thread{
		@Override
		public void run() {
			n = n + 1;
		}
	}
	public static class Resta extends Thread{
		@Override
		public void run() {	
			s.await();
			n = n - 1;
		}
	}
	public static void main(String[] args) throws InterruptedException{
		for(int j = 1; j<=M ;j++) {
			for (int i = 0;i<N;i++) {
				Thread suma = new Suma();
				Thread resta = new Resta();
				suma.start();
				resta.start();
			}
			System.out.println("n = "+ n +" | iter : "+ j);
		}
		System.out.println("\n" + "END");
	}

}