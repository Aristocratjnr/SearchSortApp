import java.util.*;
// app to demonstrate search and sort algorithms
// search algorithms: linear search, binary search
// sort algorithms: bubble sort, quick sort
// time complexity: O(n) for linear search, O(log n) for binary search, O(n^2) for bubble sort, O(n log n) for quick sort
// input: number of elements in the list, elements, algorithm type, search/sort algorithm, value to search
// output: search result, sorted list, running time, time complexity
public class SearchSortApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();

        // Reading list from user
        System.out.println("Enter the number of elements in the list:");
        int n = scanner.nextInt();
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            list.add(scanner.nextInt());
        }

        // Choosing algorithm type
        System.out.println("Choose algorithm type: 1 for Search, 2 for Sort");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Choose search algorithm: 1 for Linear Search, 2 for Binary Search");
            int searchChoice = scanner.nextInt();
            System.out.println("Enter the value to search:");
            int searchValue = scanner.nextInt();

            if (searchChoice == 2) {
                // Binary search requires sorted list
                Collections.sort(list);
            }

            long startTime = System.nanoTime();
            boolean found = (searchChoice == 1) ? linearSearch(list, searchValue) : binarySearch(list, searchValue);
            long endTime = System.nanoTime();

            System.out.println("Search result: " + (found ? "Found" : "Not Found"));
            System.out.println("Running time (nanoseconds): " + (endTime - startTime));
            System.out.println("Time complexity: " + (searchChoice == 1 ? "O(n)" : "O(log n)"));
        } else if (choice == 2) {
            System.out.println("Choose sorting algorithm: 1 for Bubble Sort, 2 for Quick Sort");
            int sortChoice = scanner.nextInt();

            long startTime = System.nanoTime();
            if (sortChoice == 1) {
                bubbleSort(list);
            } else {
                quickSort(list, 0, list.size() - 1);
            }
            long endTime = System.nanoTime();

            System.out.println("Sorted list: " + list);
            System.out.println("Running time (nanoseconds): " + (endTime - startTime));
            System.out.println("Time complexity: " + (sortChoice == 1 ? "O(n^2)" : "O(n log n)"));
        } else {
            System.out.println("Invalid choice");
        }

        scanner.close();
    }

    public static boolean linearSearch(List<Integer> list, int value) {
        for (int element : list) {
            if (element == value) {
                return true;
            }
        }
        return false;
    }

    public static boolean binarySearch(List<Integer> list, int value) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) == value) {
                return true;
            }
            if (list.get(mid) < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public static void bubbleSort(List<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    // Swap list[j] and list[j+1]
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    public static void quickSort(List<Integer> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    public static int partition(List<Integer> list, int low, int high) {
        int pivot = list.get(high);
        int i = (low - 1); // Index of smaller element
        for (int j = low; j < high; j++) {
            if (list.get(j) < pivot) {
                i++;
                // Swap list[i] and list[j]
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        // Swap list[i+1] and list[high] (or pivot)
        int temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }
}
