import java.util.Objects;

public class EthernetAdapter implements DeviceBase, Driver {
    private String manufacturer;
    private float price;
    private String serialNumber;
    private int speed;
    private String mac;

    public EthernetAdapter(String manufacturer, float price, String serialNumber, int speed, String mac) {
        this.manufacturer = manufacturer;
        this.price = price;
        this.serialNumber = serialNumber;
        this.speed = speed;
        this.mac = mac;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String getSerialNumber() {
        return serialNumber;
    }

    @Override
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public void replace() {
        System.out.println("Ethernet Adapter replaced.");
    }

    @Override
    public void recognize() {
        System.out.println("Ethernet Adapter recognized.");
    }

    @Override
    public void installDriver() {
        System.out.println("Ethernet Adapter driver installed.");
    }

    @Override
    public void removeDriver() {
        System.out.println("Ethernet Adapter driver removed.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EthernetAdapter that = (EthernetAdapter) o;
        return Float.compare(that.getPrice(), getPrice()) == 0 &&
               speed == that.speed &&
               Objects.equals(getManufacturer(), that.getManufacturer()) &&
               Objects.equals(getSerialNumber(), that.getSerialNumber()) &&
               Objects.equals(mac, that.mac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getManufacturer(), getPrice(), getSerialNumber(), speed, mac);
    }

    @Override
    public String toString() {
        return "EthernetAdapter{" +
               "manufacturer='" + getManufacturer() + '\'' +
               ", price=" + getPrice() +
               ", serialNumber='" + getSerialNumber() + '\'' +
               ", speed=" + speed +
               ", mac='" + mac + '\'' +
               '}';
    }
}