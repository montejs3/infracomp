package proyecto;

public class Buffer {

	private int capacidad;
	private int capacidadActual;

	public Buffer(int capacidad) {
		this.capacidad = capacidad;
		capacidadActual = 0;
	}
	
	public synchronized void entrar(int color, int id, Proceso proceso) throws InterruptedException {
		if ( color ==  1) { //naranja
			System.out.println("Intenta entrar proceso naranja " +id );
			while(capacidadActual >= capacidad) {
				
				System.out.println("el proceso " +id + " se durmio ");
				Thread.yield();
			}
			
			System.out.println("Entraen el buffer proceso : " +id );
			capacidadActual++;
			System.out.println("Personas en buffer  " +capacidadActual);
		}else {
				System.out.println("Intenta entrar proceso azul" +id );
				while(capacidadActual >= capacidad) {
					try {
						System.out.println("el proceso " +id + " se durmio ");
						wait();
					}catch (InterruptedException e) {}
				}
				System.out.println("Entraen el buffer proceso : " +id );
				capacidadActual++;
				System.out.println("Personas en buffer  " +capacidadActual);
			
		
		}
		
	}
	
	public synchronized void caminar(Persona persona) {
		try {
			persona.camino=1;
			wait(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public synchronized void salir(int id,int direccion, Persona persona) {
		if (direccion == 1 && persona.camino == 1 && persona.entro == 1) {
			System.out.println("Persona " +id +"Salio de caminar");
			personasCaminandoIzqDer--;
			
		}else if (persona.camino == 1 && persona.entro == 1) {
			personasCaminandoDerIzq--;
			System.out.println("Persona " +id +"Salio de caminar");
		}
		
		
			notifyAll();
		
	}
	
	
	
}
