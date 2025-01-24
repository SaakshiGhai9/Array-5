// Time Complexity : O(m * n) each worker bike pair is processedonce
// Space complexity : O(m * n) All worker pairs are stored in m * n 
import java.util.*;

public class AssignBikes {
    public int[] assignBikes (int [][] workers, int[][] bikes){
        // hashmap tp store worker bike pair grouped by distance
        Map<Integer, List<int []>> distanceMap = new HashMap<>();
        int msxDistance = 0;

        // Populate the hashmap with (workerIndex , bikeIndex) pairs
        for ( int i =0; i < workers.length; i++){
            for( int j =0; j < bikes.length; j++){
                int distance = Math.abs( workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                distanceMap.computeIfAbsent(distance, k -> new ArrayList<>()).add( new int []{i, j});
                msxDistance = Math.max(msxDistance, distance);;
            }
        }
        // Arrays to track assigned workers and bike
        boolean [] workerAssigned = new boolean [workers.length];
        boolean [] bikeAssigned = new boolean [bikes.length];
        int [] result = new int [workers.length];
        Arrays.fill(result, -1);

        // process the distance in increasing order
        for ( int d =0; d <=msxDistance; d++){
            if(! distanceMap.containsKey(d)) continue;

            for( int [] pair : distanceMap.get(d)){
                int worker = pair [0];
                int bike = pair [1];

                // assign the bike to worker if not assigned

                if( ! workerAssigned[worker] && !bikeAssigned[bike]){
                    result[worker] = bike;
                    workerAssigned [worker] = true;
                    bikeAssigned[bike] = true;
                }
            }

        }

        return result;

    }

    public static void main (String [] args){
        AssignBikes solution = new AssignBikes();
        int [][] workers = {{0,0},{2,1}};
        int [][] bikes =  {{1,2}, {3,3}};
        System.out.println(Arrays.toString(solution.assignBikes(workers, bikes))); // Output: [0, 1]

        workers = new int[][] {{0, 0}, {1, 1}, {2, 0}};
        bikes = new int[][] {{1, 0}, {2, 2}, {2, 1}};
        System.out.println(Arrays.toString(solution.assignBikes(workers, bikes))); // Output: [0, 2, 1]
    }

}
