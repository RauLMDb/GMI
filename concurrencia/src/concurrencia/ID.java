package concurrencia;

import org.jcsp.lang.*;
public class ID {
	private static final int CAPACIDAD = 10;
	private static volatile int huecos = CAPACIDAD;
	static int i = 1;
	static class Inc implements CSProcess {
		ChannelOutput out;
		ChannelInput in;
		int id;
		public Inc(int id,ChannelOutput out,ChannelInput in) {
			this.out = out;
			this.in = in;
			this.id = id;
		}
		public void run() {
			while(true){
//				int n = (int) (CAPACIDAD*(Math.random()/Math.nextDown(1.0)));
				int n = 1;
				if (n > huecos) {
					System.out.println("inc "+id+"espera "+n);
					out.write(0);
					in.read();
					System.out.println("inc "+id+ "no  espera ");
				}
				System.out.println( id+ " Enviando "+ n);
				out.write(n);
			}
		}
	}
	static class Dec implements CSProcess {
		ChannelOutput out;
		ChannelInput in;
		int id;
		public Dec(int id,ChannelOutput out,ChannelInput in) {
			this.out = out;
			this.in = in;
			this.id = id;
		}
		public void run() {
			while(true) {
//				int n = (int) (CAPACIDAD*(Math.random()/Math.nextDown(1.0)));
				int n = 1;
				if (n > CAPACIDAD-huecos) {
					System.out.println("dec "+id+" espera "+ -1*n);
					out.write(0);
					in.read();
					System.out.println("dec "+id+" no espera ");
				}
				System.out.println(id+" Retirando " + n);
				out.write(-1*n);
			}
		}
	}
	static class Contador implements CSProcess {
		ChannelInput in;
		public Contador(ChannelInput in) {
			this.in = in;
		}
		public void run() {
			int n = 0;
			
			while (true) {
				int x = (int) in.read();
				n = n + x;
				huecos = CAPACIDAD - n;
				if (huecos<0) System.out.println("¡capacidad sorbrepasada! " + n);
				if(x != 0)	System.out.println("\n ACTUALIZACION "+i+" :"+n+"\n");
				i++;
			}
		}
	}
	public static void main(String[] args) {
		Any2OneChannel c = Channel.any2one();
		CSProcess inc1 = new Inc(1, c.out(), c.in());
		CSProcess dec1 = new Dec(1, c.out(), c.in());
		CSProcess inc2 = new Inc(2, c.out(), c.in());
		CSProcess dec2 = new Dec(2, c.out(), c.in());
		CSProcess cont = new Contador(c.in());
		new Parallel(new CSProcess[]{inc1, dec1, dec2, inc2 ,cont}).run();
	}
}