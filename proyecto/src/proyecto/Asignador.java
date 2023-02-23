package proyecto;

public class Asignador {
	
	private static int idsProceso=0;
	
	private static int idsProductos=0;
	
	public synchronized static int asignarIdProceso() {
		return idsProceso++;
	}
	

	public synchronized static int asignarIdProducto() {
		return idsProductos++;
	}

}
