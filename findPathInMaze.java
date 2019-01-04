// Write a program that solves the maze

// Output
// Log coordinates as you go
// When you find 9 log out "Victory"
// If you cannot find 9 log out "Defeat"

// [
//     [1,5,9,5,5],
//     [0,5,0,0,0],
//     [0,5,0,5,0],
//     [0,0,0,5,0],
//     [0,5,5,5,0]
// ]
import java.util.*;

public class Path{
public void findPathInMaze(int[][] maze) {
    boolean[][] visited = new boolean[maze.length][maze[0].length];
    //key: cur position; val: last postion
    HashMap<Integer, Integer> pathMap = new HashMap<>();
    pathMap.put(0, -1);
    if(!dfs(maze, 0, 0, visited, pathMap)) {
        System.out.println("Defeat");
    }
    return;
}

public boolean dfs(int[][] maze, int x, int y, boolean[][] visited, HashMap<Integer, Integer> pathMap) {
    if(maze[x][y] == 9) {
        System.out.println("Victory");
        printPathMap(pathMap, x, y, maze[0].length);
        return true;
    }
    boolean ret = false;
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    visited[x][y] = true;
    for(int[] dir : dirs) {
        int newX = x + dir[0], newY = y + dir[1];
        if(newX < 0 || newY < 0 || newX >= maze.length || newY >= maze[0].length || maze[newX][newY] == 5 || visited[newX][newY]) {
            continue;
        }
        pathMap.put(newX * maze[0].length + newY, x * maze[0].length + y);
        ret |= dfs(maze, newX, newY, visited, pathMap);
        pathMap.remove(newX * maze[0].length + newY);
    }
    visited[x][y] = false;
    return ret;
}

public void printPathMap(HashMap<Integer, Integer> pathMap, int x, int y, int len) {
    List<int[]> path = new ArrayList<int[]>();
    int pos = x * len + y;
    while(pathMap.containsKey(pos) && pathMap.get(pos) != -1) {
        path.add(0, new int[]{x, y});
        int curPos = pathMap.get(pos);
        x = curPos / len;
        y = curPos % len;
        pos = curPos;
    }
    path.add(0, new int[]{x, y});
    for(int[] p : path) {
        System.out.println("<" + p[0] + "," + p[1] + ">");
    }
}

public static void main(String[] args) {
    Path path = new Path();
    int[][] maze = {
        {1,5,5,5,5},
        {0,5,0,0,0},
        {0,5,0,5,0},
        {0,0,0,5,0},
        {0,5,5,5,9}
    };
    path.findPathInMaze(maze);
}
}
