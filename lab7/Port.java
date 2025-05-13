import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {

    private static final int NUM_PLATFORMS = 5;
    private static final int TRUCK_CAPACITY = 10;
    private static final int[] SHIP_CAPACITIES = {100, 120, 150};

    private final Queue<Integer> platforms = new LinkedList<>(); // Queue для представления платформ
    private final Lock lock = new ReentrantLock(); // Lock для синхронизации доступа к платформам и грузу
    private final Condition platformAvailable = lock.newCondition(); // Condition для ожидания доступности платформы
    private int totalCargo = 0; // Общее количество груза в порту

    public Port() {
        // Изначально все платформы свободны
        for (int i = 0; i < NUM_PLATFORMS; i++) {
            platforms.offer(i);
        }
    }

    public static void main(String[] args) {
        Port port = new Port();

        // Создаем ExecutorService для грузовиков и кораблей
        ExecutorService truckExecutor = Executors.newFixedThreadPool(10); // 10 грузовиков
        ExecutorService shipExecutor = Executors.newFixedThreadPool(3);  // 3 корабля

        // Запускаем грузовики
        for (int i = 0; i < 10; i++) {
            truckExecutor.execute(port::unloadTruck);
        }

        // Запускаем корабли
        for (int i = 0; i < 3; i++) {
            int shipCapacity = SHIP_CAPACITIES[new Random().nextInt(SHIP_CAPACITIES.length)];
            shipExecutor.execute(() -> port.loadShip(shipCapacity));
        }

        // Завершаем работу ExecutorService после завершения всех задач (в данном примере - никогда, т.к. грузовики и корабли бесконечны)
        //  В РЕАЛЬНОМ ПРИЛОЖЕНИИ НЕОБХОДИМО ПРЕДУСМОТРЕТЬ УСЛОВИЯ ОСТАНОВКИ!!!
        // truckExecutor.shutdown();
        // shipExecutor.shutdown();
        // try {
        //     truckExecutor.awaitTermination(1, TimeUnit.MINUTES);
        //     shipExecutor.awaitTermination(1, TimeUnit.MINUTES);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
    }

    // Метод для разгрузки грузовика
    public void unloadTruck() {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(random.nextInt(1000)); // Имитация времени в пути грузовика

                lock.lock();
                try {
                    while (platforms.isEmpty()) {
                        System.out.println("Грузовик ждет доступную платформу...");
                        platformAvailable.await(); // Ждем, пока платформа не освободится
                    }

                    int platformNumber = platforms.poll(); // Получаем номер свободной платформы
                    System.out.println("Грузовик разгружается на платформе " + platformNumber);
                    totalCargo += TRUCK_CAPACITY;
                    System.out.println("Добавлено груза: " + TRUCK_CAPACITY + " тонн.  Всего груза: " + totalCargo + " тонн.");

                    // Уведомляем корабли, что появился новый груз
                    platformAvailable.signalAll();

                    Thread.sleep(random.nextInt(500)); // Имитация времени разгрузки

                    platforms.offer(platformNumber); // Освобождаем платформу
                    platformAvailable.signalAll(); // Уведомляем другие грузовики и корабли о доступности платформы
                    System.out.println("Платформа " + platformNumber + " освобождена.");

                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Очень важно восстановить флаг прерывания!
                return; // Завершаем поток
            }
        }
    }

    // Метод для загрузки корабля
    public void loadShip(int shipCapacity) {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(random.nextInt(2000)); // Имитация времени прибытия корабля

                lock.lock();
                try {
                    System.out.println("Корабль прибыл. Вместимость: " + shipCapacity + " тонн.  Текущий груз в порту: " + totalCargo + " тонн.");
                    while (totalCargo < 10) { // Небольшое изменение: ждем хотя бы 10 тонн груза перед началом загрузки
                         System.out.println("Корабль ждет груз.  Текущий груз: " + totalCargo + " тонн.");
                         platformAvailable.await(); // Ждем, пока не появится достаточно груза
                     }

                    int loadedCargo = Math.min(shipCapacity, totalCargo); // Определяем, сколько груза можно загрузить
                    totalCargo -= loadedCargo;
                    System.out.println("Корабль загрузил " + loadedCargo + " тонн. Осталось груза: " + totalCargo + " тонн.");

                    Thread.sleep(random.nextInt(1000)); // Имитация времени загрузки

                    System.out.println("Корабль отплыл.");
                    platformAvailable.signalAll();  // Уведомляем грузовики о возможном появлении места на платформах
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // Завершаем поток
            }
        }
    }
}