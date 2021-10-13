package com.crud.tasks.domain;


import java.util.ArrayList;
import java.util.List;

public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
    private List<String> mailCcs = new ArrayList<>();

    public static class MailBuilder {
        private String mailTo;
        private String subject;
        private String message;
        private List<String> mailCcs = new ArrayList<>();

        public MailBuilder mailTo(String mailTo) {
            this.mailTo = mailTo;
            return this;
        }

        public MailBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public MailBuilder message(String message) {
            this.message = message;
            return this;
        }

        public MailBuilder mailCc(String mailCc) {
            mailCcs.add(mailCc);
            return this;
        }

        public Mail build() {
            return new Mail(mailTo, subject, message, mailCcs);
        }
    }

    private Mail(String mailTo, String subject, String message, List<String> mailCcs) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.message = message;
        this.mailCcs = new ArrayList<>(mailCcs);
    }

    public String getMailTo() {
        return mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getMailCcs() {
        return mailCcs;
    }
}
