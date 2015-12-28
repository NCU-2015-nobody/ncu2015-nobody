import java.awt.*;
import java.util.ArrayList;

public interface CDCSDM {
    public void loadMap(String mapfile);
    public ArrayList<Boolean> checkObstacle(Point position, int direction, int attackRange);
    public boolean checkTrap(Point position);
}
