210: Course Schedule II Problem : JAVA Solution
```
/*
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses 
you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, 
return an empty array.

Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. 
Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.

Hints:

- This problem is equivalent to finding the topological order in a directed graph. 
  If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
- Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining 
  the basic concepts of Topological Sort.
- Topological sort could also be done via BFS.
*/

/*
Thoughs:
Return the fianl topologically sorted list with BFS.
Refering to Course Schedule I.
Instead of just couting the item when inDegree == 0, we add them into a list.
*/
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null) {
            return new int[numCourses];
        }

        List[] inDegreeEdges = new ArrayList[numCourses];
        int[] dependencyCount = new int[numCourses];
        
        // Initialize
        for (int i = 0; i < numCourses; i++) inDegreeEdges[i] = new ArrayList<>();
        
        // Build indegree edges, and dependency count
        for (int[] prerequisite : prerequisites) {
            inDegreeEdges[prerequisite[1]].add(prerequisite[0]);
            dependencyCount[prerequisite[0]]++;
        }
        
        // BFS: 1) prepare the sink/leaf node
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (dependencyCount[i] == 0) queue.add(i);
        }

        List<Integer> sortedList = new ArrayList<>();
        while (!queue.isEmpty()) {
            int leafNode = queue.poll();
            sortedList.add(leafNode); // leaf node as prerequisite, sits in front
            List<Integer> dependencies = inDegreeEdges[leafNode];
            for (int node : dependencies) {
                if (--dependencyCount[node] == 0) queue.add(node);
            }
        }
        
        // If cycle exist (missing node in final sorted list), then return [], no topological sorted result
        if (sortedList.size() != numCourses) {
            return new int[0];
        }

        int[] rst = new int[sortedList.size()];
        for (int i = 0; i < sortedList.size(); i++) {
            rst[i] = sortedList.get(i);
        }
        return rst;
    }
}

// DFS
class Solution {
    int[] visited;
    List[] inDegreeEdges;
    List<Integer> sortedList = new ArrayList<>();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return new int[numCourses];
        
        inDegreeEdges = buildInDegreeEdges(numCourses, prerequisites);
        visited = new int[numCourses];
        
        // DFS serach && marking visited
        for (int i = 0; i < numCourses; i++) {
            if(!dfs(i)) return new int[]{ };
        }
        
        int[] rst = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            rst[i] = sortedList.get(i);
        }
        
        return rst;
    }    
    
    public boolean dfs(int node) {
        if (visited[node] == 1) return true;
        if (visited[node] == -1) return false; // cyclic
        
        visited[node] = -1;
        List<Integer> dependencies = inDegreeEdges[node];
        for (Integer childNode : dependencies) {
            if (!dfs(childNode)) return false;
        }
        visited[node] = 1;
        sortedList.add(0, node);
        return true;
    }

    private List[] buildInDegreeEdges(int numCourses, int[][] prerequisites) {
        List[] inDegreeEdges = new ArrayList[numCourses];
        // Initialize
        for (int i = 0; i < numCourses; i++) inDegreeEdges[i] = new ArrayList<Integer>();
        
        // Build graph inDegreeEdges
        for (int[] prerequisite : prerequisites) inDegreeEdges[prerequisite[1]].add(prerequisite[0]);
        return inDegreeEdges;
    }
}

//Another One

public class Solution {
	HashMap<Integer, List<Integer>> map;
	int[] visited;
    int seq;
    int[] order;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
    	order = new int[numCourses];
    	seq = numCourses - 1;
        visited = new int[numCourses];
        map = new HashMap<Integer, List<Integer>>();
        //Put all start node into map.
        for (int i = 0; i < prerequisites.length; i++) {
        	if (!map.containsKey(prerequisites[i][1])) {
        		map.put(prerequisites[i][1], new ArrayList<Integer>());
        	}
        	map.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        //dfs on each start node in the pair
        for (int i = 0; i < numCourses; i++) {
        	if (!dfs(i)) {
        		return new int[]{};
        	}
        }
        return order;
    }

    public boolean dfs (int node) {
    	if (visited[node] == 1) {//has been through this path, true.
    		return true;
    	}
    	if (visited[node] == -1) {//visiting a visited node from a deper level node, cycle
    		return false;
    	}
    	//mark it -1 then after dfs mark it 1. Marking and backtracking skills
    	visited[node] = -1;

    	//Visit each child and make sure there is no cycle.
    	if (map.containsKey(node)) {
    		for (int nextNode : map.get(node)) {
    			if (!dfs(nextNode)) {
    				return false;
    			}
    		}
    	}
    	visited[node] = 1;
        order[seq--] = node;
    	return true;
    }

}






```
