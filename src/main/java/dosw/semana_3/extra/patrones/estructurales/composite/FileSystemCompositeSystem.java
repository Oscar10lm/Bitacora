package dosw.semana_3.extra.patrones.estructurales.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * EJERCICIO PRÁCTICO: SISTEMA DE ARCHIVOS (Composite)
 * ============================================================================
 *
 * Un explorador de archivos maneja archivos individuales y carpetas que 
 * pueden contener:
 * - Archivos
 * - Otras carpetas (carpetas dentro de carpetas)
 * 
 * El sistema debe poder calcular el tamaño total en KB de cualquier elemento, 
 * sin importar si es un archivo simple o una carpeta con muchos elementos anidados.
 * 
 * Para obtener el tamaño total de una carpeta es necesario recorrer todos sus 
 * elementos (archivos y subcarpetas) y sumar sus tamaños usando recursión.
 */
public class FileSystemCompositeSystem {

    // ==========================================
    // 1. COMPONENTE (La Interfaz Común)
    // ==========================================
    public interface FileSystemElement {
        int getSize();
        void printStructure(String indent);
    }

    // ==========================================
    // 2. HOJA (El Archivo)
    // ==========================================
    // No tiene hijos. Almacena el tamaño real.
    public static class File implements FileSystemElement {
        private String name;
        private int sizeInKb;

        public File(String name, int sizeInKb) {
            this.name = name;
            this.sizeInKb = sizeInKb;
        }

        @Override
        public int getSize() {
            return sizeInKb;
        }

        @Override
        public void printStructure(String indent) {
            System.out.println(indent + "- [Archivo] " + name + " (" + sizeInKb + " KB)");
        }
    }

    // ==========================================
    // 3. COMPUESTO / CONTENEDOR (La Carpeta)
    // ==========================================
    // Puede contener Hojas (Archivos) y otros Compuestos (Carpetas).
    public static class Folder implements FileSystemElement {
        private String name;
        private List<FileSystemElement> children = new ArrayList<>();

        public Folder(String name) {
            this.name = name;
        }

        public void addElement(FileSystemElement element) {
            children.add(element);
        }

        public void removeElement(FileSystemElement element) {
            children.remove(element);
        }

        @Override
        public int getSize() {
            int totalSize = 0;
            // DELEGACIÓN RECURSIVA: La carpeta delega el cálculo a sus hijos.
            // Si el hijo es archivo, devuelve su peso. 
            // Si el hijo es carpeta, vuelve a hacer este mismo bucle por dentro.
            for (FileSystemElement child : children) {
                totalSize += child.getSize();
            }
            return totalSize;
        }

        @Override
        public void printStructure(String indent) {
            System.out.println(indent + "+ [Carpeta] " + name);
            for (FileSystemElement child : children) {
                child.printStructure(indent + "   ");
            }
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // 1. Creamos archivos sueltos (Hojas)
        FileSystemElement txtFile = new File("notas.txt", 15);
        FileSystemElement docFile = new File("informe.docx", 250);
        FileSystemElement imgFile1 = new File("foto_vacaciones.jpg", 3500);
        FileSystemElement imgFile2 = new File("meme.png", 450);
        FileSystemElement sysFile = new File("config.ini", 5);

        // 2. Creamos carpetas y anidamos (Contenedores)
        Folder imagesFolder = new Folder("Mis Imagenes");
        imagesFolder.addElement(imgFile1);
        imagesFolder.addElement(imgFile2);

        Folder documentsFolder = new Folder("Documentos Importantes");
        documentsFolder.addElement(txtFile);
        documentsFolder.addElement(docFile);
        
        // 3. Anidamos carpetas dentro de carpetas
        Folder userFolder = new Folder("Usuario_Camilo");
        userFolder.addElement(sysFile);
        userFolder.addElement(imagesFolder);
        userFolder.addElement(documentsFolder);

        // 4. El cliente interactúa con la raíz sin saber qué tan profunda es
        System.out.println(">>> EXPLORADOR DE ARCHIVOS:");
        userFolder.printStructure("");

        System.out.println("\n>>> CALCULANDO TAMAÑO TOTAL EN DISCO...");
        // Esta sola llamada desencadena la recursión por todo el árbol
        int totalSize = userFolder.getSize();
        System.out.println("Tamaño total de 'Usuario_Camilo': " + totalSize + " KB");
    }
}
