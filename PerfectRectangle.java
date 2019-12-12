/**
 * The class finds whether all the co-ordinates create a perfect rectangle
 * @author Jyotsna Nakte
 */

class PerfectRectangle {
    class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toKey() {
            return x + "," + y;
        }
    }

    public  Node fromKey(String key) {
        return new Node(split(key, 0), split(key, 1));
    }

    public int split(String key, int index) {
        return Integer.parseInt(key.split(",")[index]);
    }
    
    public boolean isRectangleCover(int[][] rectangles) {
        // The logic is simple, to check for the All the point and the overlap between them
        Map<String, Integer> countOfCoordinate = new HashMap<>();
        int area = 0;
        for(int currentPoint[] : rectangles) {
            List<Node> currentNode = new ArrayList<>();
            area += (currentPoint[2] - currentPoint[0]) * (currentPoint[3] - currentPoint[1]);
            
            currentNode.add(new Node(currentPoint[0], currentPoint[1]));
            currentNode.add(new Node(currentPoint[0], currentPoint[3]));
            currentNode.add(new Node(currentPoint[2], currentPoint[1]));
            currentNode.add(new Node(currentPoint[2], currentPoint[3]));

            for(Node eachNode : currentNode) {
                if(!countOfCoordinate.containsKey(eachNode.toKey())) {
                    countOfCoordinate.put(eachNode.toKey(), 0);
                }
                countOfCoordinate.put(eachNode.toKey(), countOfCoordinate.get(eachNode.toKey()) + 1);
            }
        }
        List<String> corner = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : countOfCoordinate.entrySet()) {
            if(entry.getValue() % 2 != 0) {
                corner.add(entry.getKey());
            }
        }
        // Corner should contain 4 points
        if(corner.size() != 4) return false;
        Collections.sort(corner);//
        Node lowBottom = fromKey(corner.get(0));
        Node top = fromKey(corner.get(3));
            
        if(((top.x - lowBottom.x) * (top.y - lowBottom.y)) != area) {
            return false;
        }
        return true;
    }
}
