/**
 * The class finds the number of regions.
 * @author Jyotsna Nakte
 */
import java.util.Arrays;

class RegionCutBySlashes {

    public static int[] parentRelations;

    public int findParent(int x) {
        if(parentRelations[x] != x) {
            parentRelations[x] = findParent(parentRelations[x]);
        }
        return parentRelations[x];
    }

    public void connectParent(int x, int y) {
        int parentX = findParent(x);
        int parentY = findParent(y);
        parentRelations[parentX] = parentY;
    }

    public int regionsBySlashes(String[] grid) {
        // We divide a grid diagonally into 4 part of triangle as North, East, West and south
        // We connect a grid if it empty space. If we found "/" then we dont connect the north to the east grid and west to south
        // Similar for the "/" pattern
        // Then Everyone one in a region would have same parent so number of unique parent would be equal to region

        int lengthOfGrid = grid.length;
        parentRelations = new int[4 * lengthOfGrid * lengthOfGrid];

        for(int i = 0; i < parentRelations.length; i++) {
            parentRelations[i] = i;
        }
        int count = 0;
        for(int column = 0; column < lengthOfGrid; column++) {
            String currentRow = grid[column];
            for(int row = 0; row < lengthOfGrid; row++) {
                int north = count++;
                int east = count++;
                int south = count++;
                int west = count++;

                // Connect the current grid
                if(currentRow.charAt(row) != '/') {
                    connectParent(north, east);
                    connectParent(west, south);
                }
                if(currentRow.charAt(row) != '\\') {
                    connectParent(north, west);
                    connectParent(east, south);
                }

                // Connect the adjancent grid
                if(column > 0) {
                    connectParent(north, north - lengthOfGrid * 4 + 2);
                }
                if(column < lengthOfGrid - 1) {
                    connectParent(south, lengthOfGrid * 4 + south - 2);
                }
                if(row < lengthOfGrid - 1) {
                    connectParent(east, east + 6);
                }
                if(row > 0) {
                    connectParent(west, west - 6);
                }
            }
        }
        int region = 0;
        for(int i = 0; i < parentRelations.length; i++) {
            if(i == parentRelations[i]) {
                region++;
            }
        }        
        return  region;
    }
}