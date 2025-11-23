public class FenwickTree<T extends Number> {
    private T[] tree;

    @SuppressWarnings("unchecked")
    public FenwickTree(int size) {
        this.tree = (T[]) new Number[size]; // Number содержит все подклассы которые связаны с числами
        initializeZeros();
    }

    @SuppressWarnings("unchecked")
    private void initializeZeros() {
        for (int i = 0; i < tree.length; i++) {
            if (tree instanceof Integer[]) {
                tree[i] = (T) Integer.valueOf(0);
            } else if (tree instanceof Long[]) {
                tree[i] = (T) Long.valueOf(0L);
            } else if (tree instanceof Short[]) {
                tree[i] = (T) Short.valueOf((short) 0);
            }
        }
    }
    private void check_types(Class<T> type) {
        if (type != Integer.class && type != Long.class && type != Short.class) {
            throw new IllegalArgumentException("Недопустимый тип для данного алгоритма");
        }
    }

    @SuppressWarnings("unchecked")
    public void build(T[] arr) {
        check_types((Class<T>) arr.getClass().getComponentType()); //arr.getClass() чтоб получить класс объекта массива arr, .getComponentType() для массива возвращает тип элементов этого массива
        this.tree = java.util.Arrays.copyOf(arr, arr.length); //создаёт новый массив путём копирования элементов из существующего массива
        for (int i = 0; i < arr.length; i++) {
            this.update(i, arr[i]);
        }
    }

    public T get(int index) {
        T sumToCurrent = this.prefixSum(index);
        if (index == 0) {
            return sumToCurrent;
        }
        T sumToPrevious = this.prefixSum(index - 1);
        return subtract(sumToCurrent, sumToPrevious);
    }

    public void update(int index, T value) {
        T oldValue = this.get(index);
        T difference = subtract(value, oldValue);
        this.updateByDelta(index, difference);
    }


    // Внутренний метод для обновления по дельте
    private void updateByDelta(int index, T difference) {
        int currentIndex = index;
        while (currentIndex < tree.length) {
            tree[currentIndex] = add(tree[currentIndex], difference);
            currentIndex = currentIndex | (currentIndex + 1);
        }
    }


    public T rangeSum(int left, int right) {
        if (left > right) {
            throw new IllegalArgumentException("Начальный индекс больше конечного!");
        }

        T sumSection = this.prefixSum(right);
        if (left == 0) {
            return sumSection;
        }
        T sumToStart = this.prefixSum(left - 1);
        return subtract(sumSection, sumToStart);
    }

    public T prefixSum(int index) {
        if (index >= tree.length) {
            throw new IllegalArgumentException("Индекс выходит за границы массива");
        }

        T result = createZero();
        int currentIndex = index;

        while (currentIndex >= 0) {
            result = add(result, tree[currentIndex]);

            if (currentIndex == 0) {
                break;
            }

            currentIndex = (currentIndex & (currentIndex + 1)) - 1;
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private T add(T a, T b) {
        return switch (a) {
            case Integer i -> (T) Integer.valueOf(a.intValue() + b.intValue());
            case Long l -> (T) Long.valueOf(a.longValue() + b.longValue());
            case Short i -> (T) Short.valueOf((short) (a.shortValue() + b.shortValue()));
            case null, default -> throw new IllegalStateException("Неподдерживаемый тип");
        };
    }

    @SuppressWarnings("unchecked")
    private T subtract(T a, T b) {
        return switch (a) {
            case Integer i -> (T) Integer.valueOf(a.intValue() - b.intValue());
            case Long l -> (T) Long.valueOf(a.longValue() - b.longValue());
            case Short i -> (T) Short.valueOf((short) (a.shortValue() - b.shortValue()));
            case null, default -> throw new IllegalStateException("Неподдерживаемый тип");
        };
    }

    @SuppressWarnings("unchecked")
    private T createZero() {
        return switch (tree[0]) {
            case Integer i -> (T) Integer.valueOf(0);
            case Long l -> (T) Long.valueOf(0L);
            case Short i -> (T) Short.valueOf((short) 0);
            case null, default -> throw new IllegalStateException("Неподдерживаемый тип");
        };
    }




}