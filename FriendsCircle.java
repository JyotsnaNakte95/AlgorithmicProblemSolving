/**
 * The class finds number of friends circle in classroom.
 * @author Jyotsna Nakte
 */
class FriendsCircle {
    boolean[] visited;   
    void dfs(int[][] grid, int currentPeople) {
        visited[currentPeople] = true;
        for(int j = 0; j < grid.length; j++) {
            if(currentPeople != j && grid[currentPeople][j] == 1 && !visited[j]) {         
                dfs(grid, j);
            }
        }
    }
   public int findCircleNum(int[][] grid) {
        if (grid == null || grid.length == 0) {
      return 0;
    }
        int friendcircle=0;
        visited = new boolean[grid.length];
        for(int i=0;i<grid.length;i++){
            if (!visited[i]) {
                friendcircle=friendcircle+1;
                dfs(grid, i);
            }
        }
        
        return friendcircle;
    }
}
