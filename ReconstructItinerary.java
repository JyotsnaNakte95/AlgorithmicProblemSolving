/**
 * The class reconstructs new itinerary.
 * @author Jyotsna Nakte
 */
class ReconstructItinerary {
    HashMap<String, PriorityQueue<String>> hm = new HashMap<>();
        List<String> result = new ArrayList<String>();
    public List<String> findItinerary(List<List<String>> tickets) {
        for(int i=0;i<tickets.size();i++){
            PriorityQueue<String> pq;
            if(hm.containsKey(tickets.get(i).get(0))){
                pq = hm.get(tickets.get(i).get(0));
            }
            else{
                pq = new PriorityQueue<>();
            }
            pq.add(tickets.get(i).get(1));
            hm.put(tickets.get(i).get(0), pq);
            }
       
        System.out.println(hm);      
        String inidepart = "JFK";
        dfs(inidepart);
        return result;
    }
    
    public void dfs(String current) {        
        PriorityQueue<String> pq = hm.get(current);       
        while(pq != null && !pq.isEmpty()) {
            dfs(pq.poll());           
        }
        result.add(0, current);
    }    
}