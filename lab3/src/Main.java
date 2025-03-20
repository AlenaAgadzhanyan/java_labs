import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<DeviceBase> devices = new ArrayList<>();

        EthernetAdapter adapter1 = new EthernetAdapter("Intel", 50.0f, "67890", 1000, "00:11:22:33:44:55");
        Monitor monitor1 = new Monitor("LG", 200.0f, "ABCDE", 1920, 1080);

        // Добавление уникальных объектов
        if (!devices.contains(adapter1)) {
            devices.add(adapter1);
        }
        if (!devices.contains(monitor1)) {
            devices.add(monitor1);
        }

        // Вывод характеристик каждого объекта
        for (DeviceBase device : devices) {
            System.out.println(device);

            System.out.println("--------------------");
        }
    }
}