//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        FenwickTree arr = new FenwickTree(5);
        int[] from1to5 = {1,2,3,4,5};
        arr.build(from1to5);
        for (int i = 0; i < 5; i++) {
            System.out.println("   tree[" + i + "] = " + arr.get(i));
        }

        // Тест 3: Считаем суммы
        System.out.println("\n3. Считаем суммы:");
        System.out.println("   Сумма от 0 до 2: " + arr.rangeSum(0, 2)); // 1+2+3 = 6
        System.out.println("   Сумма от 1 до 3: " + arr.rangeSum(1, 3)); // 2+3+4 = 9
        System.out.println("   Сумма от 0 до 4: " + arr.rangeSum(0, 4)); // 1+2+3+4+5 = 15

        // Тест 4: Обновляем значение
        System.out.println("\n4. Обновляем tree[2] с 3 на 10:");
        arr.update(2, 10);
        System.out.println("   Новое значение tree[2] = " + arr.get(2));
        System.out.println("   Новая сумма от 0 до 2: " + arr.rangeSum(0, 2)); // 1+2+10 = 13
        System.out.println("   Новая сумма от 0 до 4: " + arr.rangeSum(0, 4)); // 1+2+10+4+5 = 22


        // Тест 5: Частичные суммы
        System.out.println("\n5. Частичные суммы (от начала):");
        for (int i = 0; i < 5; i++) {
            System.out.println("   Сумма от 0 до " + i + ": " + arr.prefixSum(i));
        }
    }
}