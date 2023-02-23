package proyecto;

import java.util.ArrayList;

public class Buffer {
	
	private int numero ;
	private ArrayList<String> productos;
	private int capacidad;
	public int capacidadActual;
	private int totalAzul;
	private int totalNaranja;
	public ArrayList<Producto>  lproductosN = new ArrayList<Producto>()  ;
	public ArrayList<Producto>  lproductosA = new ArrayList<Producto>()  ;

	public Buffer(int capacidad, int numero) {
		this.numero = numero;
		this.capacidad = capacidad;
		this.totalAzul=0;
		this.totalNaranja=0;
		capacidadActual = 0;
	}
	
	public synchronized void entregar(Proceso proceso) throws InterruptedException {
		
		if ( proceso.color ==  "naranja") { //naranja
			System.out.println("Intenta entrar proceso naranja " +proceso.id );
			if(capacidadActual + proceso.productosActuales >= capacidad) {
				
				System.out.println("el proceso " +proceso.id + " se durmio ");
				Thread.yield();
			}
			
			System.out.println("Entraen el buffer proceso : " +proceso.id );
		
			for (Producto producto : proceso.lproductos) {
				lproductosN.add(producto);
				}
			proceso.lproductos.clear();
			totalNaranja+= proceso.productosActuales;
		
			capacidadActual= capacidadActual + proceso.productosActuales;
			System.out.println("Productos en buffer " +numero +" " +capacidadActual);
			proceso.productosActuales=-2;
			notifyAll();
		}else {
				System.out.println("Intenta entrar proceso azul " +proceso.id );
				while(capacidadActual + proceso.productosActuales >= capacidad) {
					try {
						System.out.println("el proceso " +proceso.id + " se durmio ");
						wait();
					}catch (InterruptedException e) {}
				}
				System.out.println("Entraen el buffer proceso : " +proceso.id );
				for (Producto producto : proceso.lproductos) {
					lproductosA.add(producto);
				
					}
				proceso.lproductos.clear();
				totalAzul+= proceso.productosActuales;
			
				capacidadActual= capacidadActual + proceso.productosActuales;
				System.out.println("Productos en buffer  " +numero +" " +capacidadActual);
				proceso.productosActuales=-1;
				notifyAll();
			
		
		}
		
	}

	public synchronized void pedir(Proceso proceso) {
		
		if ( proceso.color ==  "naranja") { //naranja
			System.out.println("Intenta pedir productos  proceso naranja " +proceso.id );
			if(totalNaranja<=proceso.totalProductos) {
				
				System.out.println("el proceso " +proceso.id + " se durmio ");
				Thread.yield();
			}
			
			System.out.println("El buffer entrega porductos al  proceso : " +proceso.id );
			totalNaranja= totalNaranja -proceso.totalProductos;
			for (Producto producto : lproductosN) {
				//falta eliminar producto
				proceso.lproductos.add(producto);
				}
			
			proceso.productosActuales= proceso.totalProductos;
			capacidadActual= capacidadActual - proceso.totalProductos;
			System.out.println("Productos en buffer  " +numero +" " +capacidadActual);
			notifyAll();
		}else {
				System.out.println("Intenta pedir productos  proceso azul " +proceso.id );
				while(totalAzul<=proceso.totalProductos) {
					try {
						System.out.println("el proceso +" +proceso.id + " se durmio ");
						wait();
					}catch (InterruptedException e) {}
				}
				System.out.println("El buffer entrega porductos al  proceso "+proceso.id );
				for (Producto producto : lproductosA) {
					//eliminar producto
					proceso.lproductos.add(producto);
					}
			
				totalAzul= totalAzul - proceso.totalProductos;
				proceso.productosActuales=proceso.totalProductos;;
				capacidadActual= capacidadActual - proceso.totalProductos;
				System.out.println("Productos en buffer  "+numero +" " +capacidadActual);
				notifyAll();
		
		}

		
		
	}
	
	
	
	
}
