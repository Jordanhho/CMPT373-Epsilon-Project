package email;

import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;

import javax.inject.Inject;

public class MailerService {

    @Inject
    private MailerClient mailerClient;

    private String body =
            "Here is your schedule for the week of 7/20/2015:" + "\n"
                    + "Saturday, July 25, 2015 8:00 AM - 4:00 PM, BK" + "\n"
                    + "You have a total of 8 hours, 1 shifts and 0 closes." + "\n"
                    + "If you have any questions about your schedule, please contact me, in person or by phone, as soon as possible." + "\n"
                    + "Regards," + "\n"
                    + "Your manager." + "\n"
                    + "Note: This email account is used for information distribution only.  No replies nor email should be sent directly to this account.";


    public void sendEmail() {
        Email email = new Email()
                .setSubject("SSEP - Your next week schedule")
                .setFrom("joshredditbot@gmail.com")
                .addTo("joshvocal@gmail.com")
                .setBodyText(body);

        mailerClient.send(email);
    }
}
