package email;

import com.google.inject.AbstractModule;

public class MailerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MailerServiceCron.class).asEagerSingleton();
    }
}
