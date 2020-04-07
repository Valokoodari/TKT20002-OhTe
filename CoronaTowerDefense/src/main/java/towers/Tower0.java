package towers;

public class Tower0 {
    private double[] position;
    private int[] upgrades;
    private String name;
            
    public Tower0(double x, double y) {
        this.position = new double[2];
        this.position[0] = x;
        this.position[1] = y;
        
        this.upgrades = new int[2];
        
        this.name = "Ethanol Gunner";
    }
    
    public void upgrade(int path) {
        if (path < 0 || path >= this.upgrades.length) {
        	return;
        }
        if (this.upgrades[path] < 4) {
            this.upgrades[path]++;
        }
    }
    
    public boolean inRange(double x, double y) {
        double range = 2.6 + this.upgrades[1] * 0.25;
        double dx2 = Math.pow(Math.abs(this.position[0] - x), 2);
        double dy2 = Math.pow(Math.abs(this.position[1] - y), 2);
        double distance = Math.sqrt(dx2 + dy2);
        
        return distance <= range;
    }
}
