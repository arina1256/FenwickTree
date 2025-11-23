

public class FenwickTree{
    private long[] tree;
    public FenwickTree(int size) {
        this.tree = new long[size];
    }


    public void build(int[] arr) {
        this.tree = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            this.update(i, arr[i]);
        }
    }


    public long get(int index) {
        long sumToCurrent = this.prefixSum(index);
        if (index == 0) {
            return sumToCurrent;
        }
        long sumToPrevious = this.prefixSum(index - 1);
        return sumToCurrent - sumToPrevious;
    }

    public void update(int index, long value) {
        long oldValue = this.get(index);
        long difference = value - oldValue;
        int currentIndex = index;
        while (currentIndex < tree.length) {
            tree[currentIndex] += difference;
            currentIndex = currentIndex | (currentIndex + 1);
        }
    }


    public long rangeSum(int left, int right) {

        if (left > right) {
            throw new IllegalArgumentException("Начальный индекс больше конечного!");
        }
        long sumSection = this.prefixSum(right);
        if (left == 0) {
            return sumSection;
        }
        long sumToStart = this.prefixSum(left - 1);
        return sumSection- sumToStart;
    }
    public void printTree() {
        System.out.println("Fenwick Tree structure:");
        for (int i = 0; i < tree.length; i++) {
            System.out.printf("tree[%d] = %d (covers: ", i, tree[i]);
            int start = i & (i + 1);
            for (int j = start; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println(")");
        }
    }
    public long prefixSum(int index) {
        if (index >= tree.length) {
            throw new IllegalArgumentException("Индекс выходит за границы массива");
        }

        long result = 0;
        int currentIndex = index;
        while (currentIndex >= 0) {
            result += tree[currentIndex];
            if (currentIndex == 0) {
                break;
            }
            currentIndex = (currentIndex & (currentIndex + 1)) - 1;
        }

        return result;
    }
}