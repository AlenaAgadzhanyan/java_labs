import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Device> devices = new ArrayList<>();

        Device device1 = new Device("Samsung", 100.0f, "12345");
        EthernetAdapter adapter1 = new EthernetAdapter("Intel", 50.0f, "67890", 1000, "00:11:22:33:44:55");
        Monitor monitor1 = new Monitor("LG", 200.0f, "ABCDE", 1920, 1080);
        Device device2 = new Device("Samsung", 100.0f, "12345"); // Дубликат device1

        // Добавление уникальных объектов
        if (!devices.contains(device1)) {
            devices.add(device1);
        }
        if (!devices.contains(adapter1)) {
            devices.add(adapter1);
        }
        if (!devices.contains(monitor1)) {
            devices.add(monitor1);
        }
        if (!devices.contains(device2)) { // Не будет добавлен, так как равен device1
            devices.add(device2);
        }

        // Вывод характеристик каждого объекта
        for (Device device : devices) {
            System.out.println(device);

            // Пример использования методов базового класса
            device.replace();
            device.recognize();
            device.installDriver();
            device.removeDriver();

            System.out.println("--------------------");
        }
    }
}