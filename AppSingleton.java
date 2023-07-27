/**
 *
 * @author Ashmin Karki
 */
public class AppSingleton {

    private static CarPark instance;

    public static synchronized CarPark getInstance() {
        if (instance == null) {
             instance = new CarPark();
        }
        return instance;
    }
}
