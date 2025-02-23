import java.util.*;

public class BitStringFlicking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(" ");
            String command = parts[0];
            
            if (command.equals("N")) {
                int num = Integer.parseInt(parts[1]);
                System.out.println(notOperation(num));
            } else if (command.equals("A") || command.equals("O") || command.equals("X")) {
                int num1 = Integer.parseInt(parts[1]);
                int num2 = Integer.parseInt(parts[2]);
                System.out.println(bitwiseOperation(command, num1, num2));
            } else {
                int shiftAmount = Integer.parseInt(parts[1]);
                int num = Integer.parseInt(parts[2]);
                System.out.println(shiftOperation(command, shiftAmount, num));
            }
        }
        scanner.close();
    }
    
    private static int notOperation(int num) {
        int bitLength = Integer.toBinaryString(num).length();
        int mask = (1 << bitLength) - 1;
        return (~num) & mask;
    }
    
    private static int bitwiseOperation(String command, int num1, int num2) {
        return switch (command) {
            case "A" -> num1 & num2;
            case "O" -> num1 | num2;
            case "X" -> num1 ^ num2;
            default -> 0;
        };
    }
    
    private static int shiftOperation(String command, int shift, int num) {
        String binary = Integer.toBinaryString(num);
        
        return switch (command) {
            case "RS" -> num >> shift;
            case "LS" -> num << shift;
            case "RC" -> rotateRight(binary, shift);
            case "LC" -> rotateLeft(binary, shift);
            default -> 0;
        };
    }
    
    private static int rotateRight(String binary, int shift) {
        int len = binary.length();
        shift %= len;
        String rotated = binary.substring(len - shift) + binary.substring(0, len - shift);
        return Integer.parseInt(rotated, 2);
    }
    
    private static int rotateLeft(String binary, int shift) {
        int len = binary.length();
        shift %= len;
        String rotated = binary.substring(shift) + binary.substring(0, shift);
        return Integer.parseInt(rotated, 2);
    }
}
