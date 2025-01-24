// Time Complexity O(n) as the robot moves only 1 circle to detect if it is a cycle or not
// Space Complexity O(1) because no extra space is used
public class RobotCycle {
    public boolean isRobotBounded(String instructions){
        int x = 0, y = 0; // origin position of robot
        int dir = 0; // robot is aat north moving in clockwise direction 0= N, 1 = E, 2 = S and 3 = W

        // for the directions array
        int [][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        // go through the cycle of instructions
        for ( char instruction : instructions.toCharArray()){
            if(instruction == 'G'){
                x += dirs[dir][0];
                y += dirs[dir][1];
            } else if (instruction == 'L'){
                dir = (dir + 3 ) % 4;
            } else if (instruction == 'R'){
                dir = (dir + 1 ) % 4;
            }
        }

        return (x==0 && y==0) || (dir !=0); // the robot is in cycle only if it returns to origin or the  direction changes or it

    }

    public static void main (String [] srgs){
        RobotCycle robot = new RobotCycle();
        System.out.println(robot.isRobotBounded("GGLLGG")); // Output: true
        System.out.println(robot.isRobotBounded("GG"));      // Output: false
        System.out.println(robot.isRobotBounded("GL"));      // Output: true

    }
}
