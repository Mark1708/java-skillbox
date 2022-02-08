public class Country {
    private String name;
    private int count;
    private double area;
    private String capitalName;
    private boolean isNearSea;

    public Country(String name, int count, double area, String capitalName, boolean isNearSea) {
        this.name = name;
        this.count = count;
        this.area = area;
        this.capitalName = capitalName;
        this.isNearSea = isNearSea;
    }

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getCapitalName() {
        return capitalName;
    }

    public void setCapitalName(String capitalName) {
        this.capitalName = capitalName;
    }

    public boolean isNearSea() {
        return isNearSea;
    }

    public void setNearSea(boolean nearSea) {
        isNearSea = nearSea;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", area=" + area +
                ", capitalName='" + capitalName + '\'' +
                ", isNearSea=" + isNearSea +
                '}';
    }
}
