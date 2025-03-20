import java.util.Objects;

public class Device {
    private String manufacturer;
    private float price;
    private String serialNumber;

    public Device(String manufacturer, float price, String serialNumber) {
        this.manufacturer = manufacturer;
        this.price = price;
        this.serialNumber = serialNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    // Базовые методы
    public void replace() {
        System.out.println("Устройство заменено.");
    }

    public void recognize() {
        System.out.println("Устройство распознано.");
    }

    public void installDriver() {
        System.out.println("Драйвер установлен.");
    }

    public void removeDriver() {
        System.out.println("Драйвер удален.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Float.compare(device.price, price) == 0 &&
               Objects.equals(manufacturer, device.manufacturer) &&
               Objects.equals(serialNumber, device.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, price, serialNumber);
    }

    @Override
    public String toString() {
        return "Device{" +
               "manufacturer='" + manufacturer + '\'' +
               ", price=" + price +
               ", serialNumber='" + serialNumber + '\'' +
               '}';
    }
}