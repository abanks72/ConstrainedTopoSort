// Sincere Banks

import java.util.*;
import java.io.*;

public class ConstrainedTopoSort
{
  private int[][] matrix;
  private int numVertices;

  // Data Strcture Portion
  public ConstrainedTopoSort(String filename) throws IOException
  {
    File file = new File(filename);
    Scanner scanner = new Scanner(file);
    this.numVertices = scanner.nextInt();

    matrix = new int[numVertices][numVertices];

    int numUpcoming;
    for (int i=0; i<numVertices; i++)
    {
      numUpcoming = scanner.nextInt();
      for (int j=0; j<numUpcoming; j++)
        matrix[i][scanner.nextInt()-1] = 1;
    }
  }
  // Parts of Dr. Szumlanski Source Code from Topological Sorting Algorithim
  public boolean hasConstrainedTopoSort(int x, int y) throws IOException
  {
    int[] incoming = new int[matrix.length];

    for (int i=0; i<matrix.length; i++)
      for (int j=0; j<matrix.length; j++)
        incoming[j] += ((matrix[i][j] == 1) ? 1 : 0);

   // ArrayList instead of a Queue - Primary difference in code
   ArrayList<Integer> vertices = new ArrayList<Integer>();

   for (int i=0; i<matrix.length; i++)
    if (incoming[i] == 0)
    {
      if (i+1 == x)
        vertices.add(0,i);
      else
        vertices.add(i);
    }

    int swap;
    int[] result = new int[numVertices + 1];
    int current = 0;

    while (!vertices.isEmpty() && current < matrix.length)
    {
      if (vertices.get(0).intValue()+1 == y)
      {
        if(vertices.size() > 1)
        {
          swap = vertices.get(1).intValue();
          vertices.set(1, vertices.get(0));
          vertices.set(0, swap);
        }
      }
      int vertex = vertices.get(0).intValue();

      result[current++] = vertices.get(0).intValue()+1;
      vertices.remove(0);

      for (int i=0; i<matrix.length; i++)
      {
        if (matrix[vertex][i] == 1 && --incoming[i] == 0)
        vertices.add(i);
      }
    }

    for (int i=0; i<result.length; i++)
    {
      if (result[i] == y)
        return false;
      else if (result[i] == x)
        return true;
    }

    return false;
  }
  public static double difficultyRating()
  {
    return 3;
  }
  public static double hoursSpent()
  {
    return 5;
  }
}
