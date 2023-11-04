package cc.controlReciclado;

import es.upm.babel.cclib.ConcIO;

public class ControladorContenedor extends Thread {
  private final ApiContenedor contenedor;
  private final ControlReciclado cr;

  public ControladorContenedor (ApiContenedor contenedor,
                                ControlReciclado cr) {
    this.contenedor = contenedor;
    this.cr = cr;
  }

  public void run () {
    while (true) {
      ConcIO.printfnl("Contenedor preparado");

      cr.prepararSustitucion();

      ConcIO.printfnl("Inicio de sustitucion");
      contenedor.sustituir();
      ConcIO.printfnl("Fin de sustitucion");

      cr.notificarSustitucion();

      // Retardo para provocar potenciales entrelazados
      try {
        Thread.sleep(100);
      } catch (InterruptedException x) {}
    }
  }
}
