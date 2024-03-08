import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("data.txt");
            String fileName = sc.nextLine();
            System.out.println("Scegli un'azione:\n1. Cripta il contenuto\n2. Decripta il contenuto\n3. Stampa il contenuto");
            int choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    encryptFile(fileName);
                    break;
                case 2:
                    decryptFile(fileName);
                    break;
                case 3:
                    printDecryptedContent(fileName);
                    break;
                default:
                    System.out.println("Scelta non valida");
            }
            
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void encryptFile(String fileName) throws IOException {
        StringBuilder encryptedContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int c;
            while ((c = br.read()) != -1) {
                // Algoritmo di crittografia di Cesare
                encryptedContent.append((char) (c + 3));
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("encrypted.txt"))) {
            // Algoritmo di cifratura XOR
            for (char ch : encryptedContent.toString().toCharArray()) {
                bw.write(ch ^ 5);
            }
        }

        System.out.println("Contenuto criptato e salvato su file 'encrypted.txt'");
    }

    private static void decryptFile(String fileName) throws IOException {
        StringBuilder decryptedContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int c;
            while ((c = br.read()) != -1) {
                // Algoritmo di decrittografia XOR
                decryptedContent.append((char) (c ^ 5));
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("decrypted.txt"))) {
            // Algoritmo di decrittografia di Cesare
            for (char ch : decryptedContent.toString().toCharArray()) {
                bw.write(ch - 3);
            }
        }

        System.out.println("Contenuto decriptato e salvato su file 'decrypted.txt'");
    }

    private static void printDecryptedContent(String fileName) throws IOException {
        StringBuilder decryptedContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int c;
            while ((c = br.read()) != -1) {
                // Algoritmo di decrittografia XOR
                decryptedContent.append((char) (c ^ 5));
            }
        }

        System.out.println("Contenuto decriptato:");
        System.out.println(decryptedContent.toString());
    }
}