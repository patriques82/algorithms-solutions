package fundamentals.union_find;

/**
 * Created by patriknygren on 2017-04-22.
 */
abstract class UFBase implements UF {
    protected final int[] id;
    protected int count;

    UFBase(int N) {
        id = new int[N];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
        count = N;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int count() {
        return count;
    }
}


