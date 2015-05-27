
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author CherylRuo
 */
public class MSTTest {

    private static int number_of_vertices;
    private static final int MAX_VALUE = 999;

    public static int[][] readMatrix() {
        Scanner in;
        int adjacency_matrix[][] = null;
        File file = new File("src/input.txt");
        try {
            in = new Scanner(file);
            String[] number = in.nextLine().split(":");
            number_of_vertices = Integer.parseInt(number[1].trim());
            adjacency_matrix = new int[number_of_vertices + 1][number_of_vertices + 1];
            while (in.hasNextLine()) {
                String[] splited = in.nextLine().split(",");
                if (splited.length > 2) {
                    String sourceStr = splited[0].trim();
                    String destinationStr = splited[1].trim();
                    String weightStr = splited[2].trim();

                    int sourceveValue = Integer.parseInt(sourceStr.substring(1));
                    int destinationValue = Integer.parseInt(destinationStr.substring(1));
                    int weightValue = Integer.parseInt(weightStr);
                    // System.out.println(sourceStr + " " + destinationStr + " " + weightValue);

                    adjacency_matrix[sourceveValue][destinationValue] = weightValue;
                } else {
                    break;
                }
            }
            for (int i = 1; i <= number_of_vertices; i++)
            {
                for (int j = 1; j <= number_of_vertices; j++)
                {
                    if (i == j)
                    {
                        adjacency_matrix[i][j] = 0;
                        continue;
                    }
                    if (adjacency_matrix[i][j] == 0)
                    {
                        adjacency_matrix[i][j] = MAX_VALUE;
                    }
                }
            }
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MSTTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adjacency_matrix;
    }
    public static void printMatrix(int[][] adjacency_matrix )
    {
        //print matrix
        System.out.println("The adjecent matrix is ");
        for (int i = 1; i <= number_of_vertices; i++) {
            System.out.print("\t" + "V" + i);
        }
        System.out.println();
        for (int source = 1; source <= number_of_vertices; source++) {
            System.out.print("V" + source + "\t");
            for (int destination = 1; destination <= number_of_vertices; destination++) {
                System.out.print(adjacency_matrix[source][destination] + "\t");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(out);
        int adjacency_matrix[][] = readMatrix();
        KruskalMST kruskalAlgorithm = new KruskalMST(number_of_vertices);
        printMatrix(adjacency_matrix);
        System.out.println("Use Kruskal Algorithm");
        kruskalAlgorithm.K_Algorithm(adjacency_matrix);
        adjacency_matrix = readMatrix();
        printMatrix(adjacency_matrix);
        PrimMST prims = new PrimMST(number_of_vertices);
        System.out.println("Use Prim Algorithm");
        prims.primAlgorithm(adjacency_matrix);
        prims.printMST();
    }
}
