import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int startX = -1, startY = -1;
        
        List<int[]> litters = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    startX = i;
                    startY = j;
                } else if (ch == 'L') {
                    litters.add(new int[]{i, j});
                }
            }
        }
        
        int numLitters = litters.size();
        int fullMask = (1 << numLitters) - 1;
        
        int[][] litterIndex = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(litterIndex[i], -1);
        }
        for (int i = 0; i < numLitters; i++) {
            int[] l = litters.get(i);
            litterIndex[l[0]][l[1]] = i;
        }
        
        int[][][] bestEnergy = new int[m][n][1 << numLitters];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }
        
        Queue<int[]> queue = new LinkedList<>();
        int initialMask = 0;
        if (litterIndex[startX][startY] != -1) {
            initialMask |= (1 << litterIndex[startX][startY]);
        }
        
        queue.offer(new int[]{startX, startY, initialMask, energy});
        bestEnergy[startX][startY][initialMask] = energy;
        
        int steps = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] curr = queue.poll();
                int x = curr[0];
                int y = curr[1];
                int mask = curr[2];
                int remEnergy = curr[3];
                
                if (mask == fullMask) {
                    return steps;
                }
                
                // If energy is 0 and we are NOT on a reset cell 'R', we cannot expand further
                if (remEnergy == 0 && classroom[x].charAt(y) != 'R') {
                    continue;
                }
                
                for (int[] d : dirs) {
                    int nx = x + d[0];
                    int ny = y + d[1];
                    
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || classroom[nx].charAt(ny) == 'X') {
                        continue;
                    }
                    
                    int nextEnergy = remEnergy - 1;
                    char cell = classroom[nx].charAt(ny);
                    
                    if (cell == 'R') {
                        nextEnergy = energy;
                    }
                    
                    int nextMask = mask;
                    if (cell == 'L') {
                        int idx = litterIndex[nx][ny];
                        if (idx != -1) {
                            nextMask |= (1 << idx);
                        }
                    }
                    
                    if (nextEnergy > bestEnergy[nx][ny][nextMask]) {
                        bestEnergy[nx][ny][nextMask] = nextEnergy;
                        queue.offer(new int[]{nx, ny, nextMask, nextEnergy});
                    }
                }
            }
            steps++;
        }
        
        return -1;
    }
}