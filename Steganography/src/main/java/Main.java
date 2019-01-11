import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Write  path to image | Example : C:\\Users\\User\\... ");
        String path = scanner.nextLine();

        System.out.println("Write name of image:");
        String name = scanner.nextLine();
        String Name_decoded = name+"_decoded";

        System.out.println("Write extension of image:");
        String extension = scanner.nextLine();

        System.out.println("Write the message, you want to be encoded in image");
        String encodeMessage = scanner.nextLine();


        Steganography steganography = new Steganography();

        steganography.encode(path,name,extension,Name_decoded,encodeMessage);

        String Masage_decoded;
        Masage_decoded = steganography.decode(path, Name_decoded);
        System.out.println(Masage_decoded);



    }
}

