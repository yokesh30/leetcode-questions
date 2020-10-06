import java.util.*;

public class Graphs {
    Integer vertices;
    LinkedList<Integer> children[];

    public Graphs(Integer d) {
        vertices = d;
        children = new LinkedList[vertices];
        for (int i = 0; i < d; i++) {
            children[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest) {
        children[src].add(dest);
    }

    public void printGraph(Graphs graph) {
        for (int v = 0; v < graph.vertices; v++) {
            System.out.println("Adjacency list of vertex " + v);
            System.out.print("head");
            for (Integer pCrawl : graph.children[v]) {
                System.out.print(" -> " + pCrawl);
            }
            System.out.println("\n");
        }
    }

    public boolean hasPathBetweenNodes(Graphs graphs, Integer n, Integer e) {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        Iterator<Integer> iterator;
        Integer u;
        while (!q.isEmpty()) {
            Integer x = q.poll();
            iterator = graphs.children[x].iterator();
            while (iterator.hasNext()) {
                u = iterator.next();
                if (e == u) return true;
                else q.add(u);
            }
        }
        return false;
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        int[] disc = new int[n], low = new int[n];
        // use adjacency list instead of matrix will save some memory, adjmatrix will cause MLE
        List<Integer>[] graph = new ArrayList[n];
        List<List<Integer>> res = new ArrayList<>();
        Arrays.fill(disc, -1); // use disc to track if visited (disc[i] == -1)
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        // build graph
        for (int i = 0; i < connections.size(); i++) {
            int from = connections.get(i).get(0), to = connections.get(i).get(1);
            graph[from].add(to);
            graph[to].add(from);
        }

        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                dfs(i, low, disc, graph, res, i);
            }
        }
        return res;
    }

    int time = 0; // time when discover each vertex

    private void dfs(int u, int[] low, int[] disc, List<Integer>[] graph, List<List<Integer>> res, int pre) {
        disc[u] = low[u] = ++time; // discover u
        for (int j = 0; j < graph[u].size(); j++) {
            int v = graph[u].get(j);
            if (v == pre) {
                continue; // if parent vertex, ignore
            }
            if (disc[v] == -1) { // if not discovered
                dfs(v, low, disc, graph, res, u);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u]) {
                    // u - v is critical, there is no path for v to reach back to u or previous vertices of u
                    res.add(Arrays.asList(u, v));
                }
            } else { // if v discovered and is not parent of u, update low[u], cannot use low[v] because u is not subtree of v
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public int numberofIslands(char[][] grid) {
        if (grid == null || grid.length < 1) return 0;
        int numofIslands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    numofIslands += dfs(grid, i, j);
                }
            }
        }
        return numofIslands;
    }

    private static final int[][] DIRS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int minSteps(char[][] grid) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        grid[0][0] = 'D'; // mark as visited
        for (int steps = 1; !q.isEmpty(); steps++) {
            for (int sz = q.size(); sz > 0; sz--) {
                Point p = q.poll();

                for (int[] dir : DIRS) {
                    int r = p.r + dir[0];
                    int c = p.c + dir[1];

                    if (isSafe(grid, r, c)) {
                        if (grid[r][c] == 'X') return steps;
                        grid[r][c] = 'D';
                        q.add(new Point(r, c));
                    }
                }
            }
        }
        return -1;
    }

    private static boolean isSafe(char[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] != 'D';
    }


    private static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int dfs(char[][] grid, int i, int j) {
        if (i < 0 || i > grid.length || j < 0 || j > grid[0].length || grid[i][j] == '0') return 0;
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        return 1;
    }

    public static void main(String args[]) {
        Graphs graphs = new Graphs(5);
        graphs.addEdge(0, 1);
        graphs.addEdge(1, 2);
        graphs.addEdge(2, 3);
        graphs.addEdge(3, 4);
        graphs.addEdge(0, 4);
        graphs.addEdge(1, 4);
        graphs.printGraph(graphs);
        boolean b = graphs.hasPathBetweenNodes(graphs, 1, 0);
        System.out.println(" There is a path between 1 and 0: " + b);
        b = graphs.hasPathBetweenNodes(graphs, 1, 4);
        System.out.println(" There is a path between 1 and 4: " + b);
        b = graphs.hasPathBetweenNodes(graphs, 4, 1);
        System.out.println(" There is a path between 4 and 1: " + b);

        List<List<Integer>> input = new ArrayList<>();
        ArrayList<Integer> a1 = new ArrayList<>();
        a1.add(0);
        a1.add(1);
        input.add(a1);
        ArrayList<Integer> a2 = new ArrayList<>();
        a2.add(0);
        a2.add(2);
        input.add(a2);
        ArrayList<Integer> a3 = new ArrayList<>();
        a3.add(1);
        a3.add(3);
        input.add(a3);
        ArrayList<Integer> a4 = new ArrayList<>();
        a4.add(2);
        a4.add(3);
        input.add(a4);
        ArrayList<Integer> a5 = new ArrayList<>();
        a5.add(2);
        a5.add(5);
        input.add(a5);
        ArrayList<Integer> a6 = new ArrayList<>();
        a6.add(5);
        a6.add(6);
        input.add(a6);
        ArrayList<Integer> a7 = new ArrayList<>();
        a7.add(3);
        a7.add(4);
        input.add(a7);

        graphs.criticalConnections(7, input);

        char[][] grid = {{'O', 'O', 'O', 'O'},
                {'D', 'O', 'D', 'O'},
                {'O', 'O', 'O', 'O'},
                {'X', 'D', 'D', 'O'}};
        int i = graphs.minSteps(grid);
    }
}
