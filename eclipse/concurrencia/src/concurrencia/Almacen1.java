package concurrencia;

import es.upm.babel.cclib.*;

// TODO: importar la clase de los sem�foros.

/**
 * Implementaci�n de la clase Almacen que permite el almacenamiento
 * de 1 producto y el uso simult�neo del almacen por varios threads.
 */
class Almacen1 implements Almacen {
   // Producto a almacenar: null representa que no hay producto
   private Producto almacenado = null;

   // TODO: declaraci�n e inicializaci�n de los sem�foros
   // necesarios
   Semaphore almacenar;
   Semaphore extraer;
   private volatile int n_productos;
   public Almacen1() {
	   n_productos = 0;
	   almacenar = new Semaphore(1);
	   extraer = new Semaphore(0);
   }

   public void almacenar(Producto producto) {// poner en rojo el semaforo
      // TODO: protocolo de acceso a la secci�n cr�tica y c�digo de
      // sincronizaci�n para poder almacenar.
	   if(n_productos > 0) {
		   almacenar.await();
	   }
      // Secci�n cr�tica
      almacenado = producto;
      n_productos++;
      
      almacenar.signal();
      // TODO: protocolo de salida de la secci�n cr�tica y c�digo de
      // sincronizaci�n para poder extraer.
   }

   public Producto extraer() {//poner en verde el semaforo
      Producto result;

      // TODO: protocolo de acceso a la secci�n cr�tica y c�digo de
      // sincronizaci�n para poder extraer.
      if(n_productos == 0)
    	  extraer.await();
      else
    	  extraer.signal();
      // Secci�n cr�tica
      result = almacenado;
      almacenado = null;
      n_productos--;
      
      extraer.await();

      // TODO: protocolo de salida de la secci�n cr�tica y c�digo de
      // sincronizaci�n para poder almacenar.

      return result;
   }
}