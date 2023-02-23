package proyecto;

import java.util.Scanner;

public class Main {
	
	private final static int ETAPAS = 3;
	

	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el número de procesos por etapa: " );
		int procesosPorEtapa = scanner.nextInt();
		System.out.println("Ingrese el número de productos que se deben crear: " );
		int productosACrear = scanner.nextInt();
		System.out.println("Ingrese el tamaño de los buzones (este tamaño no aplica para el último buzón): " );
		int tamanoBuzon = scanner.nextInt();
		scanner.close();
		
		
		Buffer buzonFinal = new Buffer(Integer.MAX_VALUE,3);
		
		//procesoFinal = new ProcesoRojo(buzonFinal, procesosPorEtapa * productosACrear);
		Buffer buzon1 = new Buffer(tamanoBuzon,1);
		Buffer buzon2 = new Buffer(tamanoBuzon,2);
	
		
		
		
		for (int i = 1; i <= ETAPAS; i++) {
			
			for (int j = 0; j < procesosPorEtapa; j++) {
				if (i == 1) {
					if(j == 0) {
						new Proceso("naranja", Asignador.asignarIdProceso(),buzon1 , null, 1, productosACrear).start();
						
					}
					else {
						new Proceso("azul", Asignador.asignarIdProceso(),buzon1 , null, 1, productosACrear).start();
					}
				}
				else if(i == 2) {
					if(j == 0) {
						new Proceso("naranja", Asignador.asignarIdProceso(),buzon2 , buzon1, 2, productosACrear).start();
					}
					else {
						new Proceso("azul", Asignador.asignarIdProceso(),buzon2 , buzon1, 2, productosACrear).start();
					}
				}
				else if(i == 3) {
					if(j == 0) {
						new Proceso("naranja", Asignador.asignarIdProceso(),buzonFinal , buzon2, 3, productosACrear).start();
					}
					else {
						new Proceso("azul", Asignador.asignarIdProceso(),buzonFinal , buzon2, 3, productosACrear).start();
					}
				}
			}
		}
		
		
		//procesoFinal.start();
	}

}
