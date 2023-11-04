package concurrencia;

public class CC_02_Carrera {


	private static final int N = 200;
	private  static volatile int n = 0;

	public static class Suma extends Thread{
		@Override
		public void run() {
			n = n + 1;
		}
	}
	public static class Resta extends Thread{
		@Override
		public void run() {	
			n = n - 1;
		}
	}
	public static void main(String[] args) throws InterruptedException{
		int m = 310;
		for(int j = 1; j<=m ;j++) {
			for (int i = 0;i<N;i++) {
				Thread suma = new Suma();
				Thread resta = new Resta();
				suma.start();
				resta.run();
			}
			System.out.println("n = "+ n +" | iter : "+ j);
		}
		System.out.println("\n" + "END");
	}

}