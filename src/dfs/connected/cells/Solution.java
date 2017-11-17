package dfs.connected.cells;

import java.util.Scanner;

public class Solution {

    static boolean[][] visited;

    static int grid[][];

    static int n;
    static int m;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        grid = new int[n][m];
        for(int grid_i=0; grid_i < n; grid_i++){
            for(int grid_j=0; grid_j < m; grid_j++){
                grid[grid_i][grid_j] = in.nextInt();
            }
        }

        visited = new boolean[n][m];

        int maxSize = 0;
        int currentSize;

        for(int i=0; i < n; i++){
            for(int j=0; j < m; j++){
                if ((!visited[i][j]) && (grid[i][j] == 1)) {
                    currentSize = dfs (i, j, 0);
                    if (currentSize > maxSize) {
                        maxSize = currentSize;
                    }
                }
            }
        }

        System.out.println(maxSize);
    }

    static int dfs(int i, int j, int counter) {
        visited[i][j] = true;
        counter++;

        for (int k = i - 1; k < i + 2; k++) {
            for (int l = j - 1; l < j + 2; l++) {
                if ((k >= 0)&&(l >=0 )&&(k < n)&&(l < m)) {
                    if ((grid[k][l] == 1) && (!visited[k][l])) {
                        counter = dfs(k, l, counter);
                    }
                }
            }
        }

        return counter;
    }
}