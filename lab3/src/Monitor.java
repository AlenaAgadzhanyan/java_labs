import java.util.Objects;

public class Monitor implements DeviceBase, Driver {
    private String manufacturer;
    private float price;
    private String serialNumber;
    private int resolutionX;
    private int resolutionY;

    public Monitor(String manufacturer, float price, String serialNumber, int resolutionX, int resolutionY) {
        this.manufacturer = manufacturer;
        this.price = price;
        this.serialNumber = serialNumber;
        this.resolutionX = resolutionX;
        this.resolutionY = resolutionY;
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

    public int getResolutionX() {
        return resolutionX;
    }

    public void setResolutionX(int resolutionX) {
        this.resolutionX = resolutionX;
    }

    public int getResolutionY() {
        return resolutionY;
    }

    public void setResolutionY(int resolutionY) {
        this.resolutionY = resolutionY;
    }

    @Override
    public void replace() {
        System.out.println("Monitor replaced.");
    }

    @Override
    public void recognize() {
        System.out.println("Monitor recognized.");
    }

     @Override
    public void installDriver() {
        System.out.println("Monitor driver installed.");
    }

    @Override
    public void removeDriver() {
        System.out.println("Monitor driver removed.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monitor monitor = (Monitor) o;
        return Float.compare(monitor.getPrice(), getPrice()) == 0 &&
               resolutionX == monitor.resolutionX &&
               resolutionY == monitor.resolutionY &&
               Objects.equals(getManufacturer(), monitor.getManufacturer()) &&
               Objects.equals(getSerialNumber(), monitor.getSerialNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getManufacturer(), getPrice(), getSerialNumber(), resolutionX, resolutionY);
    }

    @Override
    public String toString() {
        return "Monitor{" +
               "manufacturer='" + getManufacturer() + '\'' +
               ", price=" + getPrice() +
               ", serialNumber='" + getSerialNumber() + '\'' +
               ", resolutionX=" + resolutionX +
               ", resolutionY=" + resolutionY +
               '}';
    }
}