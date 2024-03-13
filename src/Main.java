import controller.HomeController;
import view.HomePage;
import view.NewPage;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        HomeController homeController = new HomeController();
        homeController.load();
    }
}
