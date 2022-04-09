package com.hf.project.common.function;

import com.hf.project.common.entity.MailRecipient;

@FunctionalInterface
public interface MailRecipientHandler {

    MailRecipient getMailRecipient();

}
