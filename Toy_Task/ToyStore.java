import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ToyStore {
    private List<Toy> toys;
    private int totalWeight;

    public ToyStore() {
        toys = new ArrayList<>();
        totalWeight = 0;
    }

    public void addToy(Toy toy) {
        toys.add(toy);
        totalWeight += toy.getWeight();
    }

    public void removeToy(Toy toy) {
        toys.remove(toy);
        totalWeight -= toy.getWeight();
    }

    public Toy getRandomToy() {
        Random random = new Random();
        int randomWeight = random.nextInt(totalWeight) + 1; // генерируем случайный вес игрушки
        int currentWeight = 0;
        for (Toy toy : toys) {
            currentWeight += toy.getWeight();
            if (randomWeight <= currentWeight) {
                return toy;
            }
        }
        return null; // если игрушек нет, возвращаем null
    }

    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (!command.equals("exit")) {
            System.out.println("Введите команду (add, remove, play, exit):");
            command = scanner.nextLine();

            switch (command) {
                case "add":
                    System.out.println("Введите название игрушки:");
                    String name = scanner.nextLine();
                    System.out.println("Введите вес игрушки:");
                    int weight = scanner.nextInt();
                    scanner.nextLine();
                    toyStore.addToy(new Toy(name, weight));
                    System.out.println("Игрушка добавлена.");
                    break;
                case "remove":
                    System.out.println("Введите название игрушки:");
                    String nameToRemove = scanner.nextLine();
                    Toy toyToRemove = null;
                    for (Toy toy : toyStore.toys) {
                        if (toy.getName().equals(nameToRemove)) {
                            toyToRemove = toy;
                            break;
                        }
                    }
                    if (toyToRemove != null) {
                        toyStore.removeToy(toyToRemove);
                        System.out.println("Игрушка удалена.");
                    } else {
                        System.out.println("Игрушка не найдена.");
                    }
                    break;
                case "play":
                    Toy randomToy = toyStore.getRandomToy();
                    if (randomToy != null) {
                        System.out.println("Вы выиграли игрушку: " + randomToy.getName());
                    } else {
                        System.out.println("В магазине нет игрушек.");
                    }
                    break;
                case "exit":
                    System.out.println("До свидания!");
                    break;
                default:
                    System.out.println("Неизвестная команда.");
                    break;
            }
        }

        scanner.close();
    }
}

class Toy {
    private String name;
    private int weight;

    public Toy(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}
