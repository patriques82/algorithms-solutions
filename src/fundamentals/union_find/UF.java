package fundamentals.union_find;

// API
interface UF {

    void union(int p, int q);           // add connection between p and q

    int find(int p);                    // component identifier for p (0 to N-1)

    boolean connected(int p, int q);    // return true if p and q are in the same component

    int count();                        // number of id

}
