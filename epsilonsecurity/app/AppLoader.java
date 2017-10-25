import play.Application;
import play.ApplicationLoader;

public class AppLoader implements ApplicationLoader {

    @Override
    public Application load(Context context) {
        return new CustomComponents(context).application();
    }
}

