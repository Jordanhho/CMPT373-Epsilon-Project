
import play.ApplicationLoader;
import play.routing.Router;
import play.routing.RoutingDslComponentsFromContext;

public class CustomComponents extends RoutingDslComponentsFromContext
    implements play.filters.components.NoHttpFiltersComponents {

    CustomComponents(ApplicationLoader.Context context) {
        super(context);
    }

    @Override
    public Router router() {
        return routingDsl()
                .build();
    }
}
