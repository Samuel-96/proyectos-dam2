import java.io.*;
import java.util.Scanner;

public class RandomAccessFileACopiar
{
    //atributos
    private static RandomAccessFile raf = null; //creo la referencia
    private static Scanner sc = null;
    private static final int TAM = 216;
            /*4 + (40*2) + 8 + (40*2) + 4 + (20*2) = 216
             id + titulo + precio + autor + anyo + editorial*/

    public static void main(String[] args) //metodo main
    {
        sc = new Scanner(System.in);
        try
        {
            raf = new RandomAccessFile("libros.dat", "rw");
        }
        catch (FileNotFoundException e)
        {
            System.err.println(e.getMessage());
        }

        menu();
    }

    public static void liberaBufer() //metodo sencillo para liberar buffer
    {
        sc.nextLine();
    }

    public static void menu() //metodo para mostrar el menu
    {
        System.out.println("\nSistema de gestión de biblioteca.");
        System.out.println("1. Dar de alta un libro.");
        System.out.println("2. Consultar un libro.");
        System.out.println("3. Eliminar un libro de la biblioteca.");
        System.out.println("4. Listar todos los libros de la biblioteca.");
        System.out.println("5. Listar todos los libros del área de sinónimos");
        System.out.print("Elije una opción: ");
        int opc = sc.nextInt();

        while (true) {
            switch (opc) {
                case 1:
                    altaLibro();
                    break;
                case 2:
                    consultaLibro();
                    break;
                //case 3: eliminaLibro(); break;
                case 4:
                    listarLibros();
                    break;
                case 5: listarAreaSinonimos();
                default:
                    System.exit(0);
            }
            System.out.println("\nSistema de gestión de biblioteca.");
            System.out.println("1. Dar de alta un libro.");
            System.out.println("2. Consultar un libro.");
            System.out.println("3. Eliminar un libro de la biblioteca.");
            System.out.println("4. Listar todos los libros de la biblioteca.");
            System.out.print("Elije una opción: ");
            opc = sc.nextInt();
        }
    }

    public static void altaLibro() {
        System.out.print("\nIntroduce el ID del libro: "); int id = sc.nextInt(); liberaBufer();
        System.out.print("Introduce el titulo del libro: "); String titulo = sc.nextLine();
        System.out.print("Introduce el precio del libro: "); double precio = sc.nextDouble();
        liberaBufer();
        System.out.print("Introduce el autor del libro: "); String autor = sc.nextLine();
        System.out.print("Introduce el año de publicación del libro: "); int anyo = sc.nextInt();
        liberaBufer();
        System.out.print("Introduce la editorial: "); String editorial = sc.nextLine();//id del random access file

        try {
            if (raf.length() == 0) //es la primera vez que se da un alta, no hace falta comparar id
            {
                raf.seek((long) (id - 1) * TAM); //me posiciono y escribo en raf
                raf.writeInt(id);
                StringBuilder sb = new StringBuilder(titulo); //creo un stringbuffer con el nombre
                sb.setLength(40); //limito los caracteres para que no excedan
                raf.writeChars(sb.toString()); //writeChars, con UTF da problemas
                raf.writeDouble(precio);
                sb = new StringBuilder(autor);
                sb.setLength(40);
                raf.writeChars(sb.toString());
                raf.writeInt(anyo);
                sb = new StringBuilder(editorial);
                sb.setLength(20);
                raf.writeChars(sb.toString());
                System.out.println("Libro añadido a Fichero de Acceso Aleatorio");
            }
            else //es la segunda vez o mas que se da de alta
            {
                raf.seek((long) (id - 1) * TAM); //me posiciono

                if (raf.read() == -1) //compruebo para ver si la posicion esta vacia, si lo esta puedo escribir en raf
                {
                    raf.seek((long) (id - 1) * TAM);
                    raf.writeInt(id);
                    StringBuilder sb = new StringBuilder(titulo); //creo un stringbuffer con el nombre
                    sb.setLength(40); //limito los caracteres para que no excedan
                    raf.writeChars(sb.toString());
                    raf.writeDouble(precio);
                    sb = new StringBuilder(autor); //creo un stringbuffer con el nombre
                    sb.setLength(40);
                    raf.writeChars(sb.toString());
                    raf.writeInt(anyo);
                    sb = new StringBuilder(editorial); //creo un stringbuffer con el nombre
                    sb.setLength(20);
                    raf.writeChars(sb.toString());
                    System.out.println("Libro añadido a raf");
                }
                else //si la posicion esta ocupada, va al area de sinonimos
                {
                    areaSinonimos(id, titulo, precio, autor, anyo, editorial);
                }
            }
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static void consultaLibro() {
        int id;String titulo = "";double precio;String autor="";int anyo;String editorial="";
        int idConsulta;
        System.out.print("Introduce el ID del libro para consultar sus datos: ");
        idConsulta = sc.nextInt();
        try {
                raf.seek((long) (idConsulta - 1) * TAM);
                id = raf.readInt();
                for (int i = 0; i < 40; i++)
                    titulo = titulo + raf.readChar();
                precio = raf.readDouble();
                for (int i = 0; i < 40; i++)
                    autor = autor + raf.readChar();
                anyo = raf.readInt();
                for (int i = 0; i < 20; i++)
                    editorial = editorial + raf.readChar();
                System.out.println("\nId: " + id);
                System.out.println("Título: " + titulo);
                System.out.println("Precio: " + precio);
                System.out.println("Autor: " + autor);
                System.out.println("Año: " + anyo);
                System.out.println("Editorial: " + editorial);
            }
        catch (EOFException eof)
        {
            System.out.println("No hay ningun libro registrado con ese ID");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void listarLibros() {
        int id;
        String titulo="";
        double precio;
        String autor="";
        int anyo;
        String editorial="";
        long pos = 0;
        try {
            raf.seek(pos);
            System.out.println("Tamaño raf: " + raf.length());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true)
            {
                try
                {
                    id = raf.readInt();
                    for (int i = 0; i < 40; i++)
                        titulo = titulo + raf.readChar();
                    precio = raf.readDouble();
                    for (int i = 0; i < 40; i++)
                        autor = autor + raf.readChar();
                    anyo = raf.readInt();
                    for (int i = 0; i < 20; i++)
                        editorial = editorial + raf.readChar();
                    System.out.println("------------------------------------");
                    System.out.println("Id: " + id);
                    System.out.println("Título: " + titulo);
                    System.out.println("Precio: " + precio);
                    System.out.println("Autor: " + autor);
                    System.out.println("Año: " + anyo);
                    System.out.println("Editorial: " + editorial);
            }
            catch (EOFException eof)
            {
                break; //si llega al final del archivo salgo del bucle
            }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }//fin while
    }

    public static void listarAreaSinonimos()
    {
        try(FileInputStream fis = new FileInputStream("sinonimos.dat");
            DataInputStream dis = new DataInputStream(fis))
        {
            int id; String titulo; double precio; String autor; int anyo; String editorial;
            while (true)
            {
                id = dis.readInt();
                titulo = dis.readUTF();
                precio = dis.readDouble();
                autor = dis.readUTF();
                anyo = dis.readInt();
                editorial = dis.readUTF();

                System.out.println("------------------------------------");
                System.out.println("Id: " + id);
                System.out.println("Título: " + titulo);
                System.out.println("Precio: " + precio);
                System.out.println("Autor: " + autor);
                System.out.println("Año: " + anyo);
                System.out.println("Editorial: " + editorial);
            }
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Archivo no encontrado.");
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    public static void areaSinonimos(int id, String titulo, double precio, String autor, int anyo, String editorial)
    {
        try(FileOutputStream fos = new FileOutputStream("sinonimos.dat",true);
            DataOutputStream dos = new DataOutputStream(fos))
        {
            dos.writeInt(id);
            dos.writeUTF(titulo);
            dos.writeDouble(precio);
            dos.writeUTF(autor);
            dos.writeInt(anyo);
            dos.writeUTF(editorial);
            System.out.println("Entrada añadida al área de sinónimos, ya que en el raf había una entrada con mismo id");
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}