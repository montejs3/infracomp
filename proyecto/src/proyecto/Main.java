package proyecto;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	
	 try (Scanner scanner = new Scanner(System.in)) {
         
         System.out.println("Ingrese el tamaño de los buzones de inicio y de fin: ");
         int sizeBuzonIniFini = scanner.nextInt();

         System.out.println("Ingrese el tamaño de los buzones intermedios: ");
         int sizeBuzonIntermedio = scanner.nextInt();

         System.out.println("Ingrese la cantidad de subconjuntos: ");
         int cantSubconjuntos = scanner.nextInt();

         // creacion subconjuntos
         ArrayList<String> subconjuntos = crearSubconjuntos(cantSubconjuntos);

         // creacion buzones
         Buzon buzonInicial = new Buzon(sizeBuzonIniFini);
         Buzon buzonFinal = new Buzon(sizeBuzonIniFini);
         Buzon[][] buzonesIntermedios = crearBuzonesIntermedios(sizeBuzonIntermedio);

         // creacion procesos
         ProcesoInicial procesoInicial = new ProcesoInicial(buzonInicial, subconjuntos);
         ProcesoFinal procesoFinal = new ProcesoFinal(buzonFinal);
         ProcesoIntermedio[][] procesosIntermedios = crearProcesoIntermedio(buzonInicial, buzonFinal, buzonesIntermedios);

         // ejecucion procesos
         procesoInicial.start();
         for (int i = 0; i < 3; i++) {
             for (int j = 0; j < 3; j++) {
                 procesosIntermedios[i][j].start();
             }
         }
         procesoFinal.start();
     }
 }

}
