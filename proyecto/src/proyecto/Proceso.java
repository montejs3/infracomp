package proyecto;

import java.util.ArrayList;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Proceso  extends Thread{


	private String nombre;
	public String color;
	public int id;
	private Buffer bufferSalida;
	private Buffer bufferEntrega;
	private int etapa ;
	public int totalProductos;
	public int productosActuales;
	public ArrayList<Producto>  lproductos = new ArrayList<Producto>()  ;
	
	
	
	public Proceso(String color, int id, Buffer bufferSalida, Buffer bufferEntrega, int etapa, int totalProductos) {
		super();
		this.color = color;
		this.id = id;
		this.bufferSalida = bufferSalida;
		this.bufferEntrega = bufferEntrega;
		this.etapa = etapa;
		this.totalProductos = totalProductos;
		this.nombre = "Proceso :  +id " +" color : " +color +" etapa : " +etapa;
		this.productosActuales=0;
		
	
	}
	
	
	public void run() {
		System.out.println("Proceso : " +id +" color : " +color +" etapa : " +etapa +" Creado ");
		
		if(etapa == 1 && productosActuales== 0) {
			
			llenarProductos(totalProductos,color);
		}
		
		if(etapa == 1 && productosActuales== totalProductos){
				try {
					bufferSalida.entregar(this);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
		}
		
		
		if(etapa == 2 && productosActuales== 0) {
		
			bufferEntrega.pedir(this);
		
			
		}
		
		if(etapa == 2 && productosActuales== totalProductos){
			
			try {
				bufferSalida.entregar(this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}
		
if(etapa == 3 && productosActuales== 0) {
			

		bufferEntrega.pedir(this);
	
	
		}
		
		if(etapa == 3 && productosActuales== totalProductos){
			try {
				bufferSalida.entregar(this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}
		
		
		
		
	}
	
	


	public void asignarProductos(Producto producto) {
		lproductos.add(producto);
	}
	
	private void llenarProductos(int totalProductos2, String color2) {
		
		for (int i = 1; i <= totalProductos2; i++) {
			Producto hola = new Producto(Asignador.asignarIdProducto(),color2);
			this.lproductos.add(hola);
			this.productosActuales++;
		}
		
		System.out.println("Proceso: " +id +" Etapa: " +etapa +" productosActuales: " +productosActuales);
		
	}
	
	
	@Override
	public String toString() {
		return  nombre;
	}

	
	
	
	
	
	
	
	
	
	
	

}
