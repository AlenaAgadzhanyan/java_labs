public class Ship {
    private String name;
    private String category; // "Tug", "Passenger", "Cargo"
    private double tonnage;  // Тоннаж
    private double transportCost; // Стоимость перевозки
    private double length;      // Длина (для определения габаритов)

    public Ship(String name, String category, double tonnage, double transportCost, double length) {
        this.name = name;
        this.category = category;
        this.tonnage = tonnage;
        this.transportCost = transportCost;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getTonnage() {
        return tonnage;
    }

    public double getTransportCost() {
        return transportCost;
    }

    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", tonnage=" + tonnage +
                ", transportCost=" + transportCost +
                ", length=" + length +
                '}';
    }
}