package com.yinuo.socket.factory;

import com.yinuo.socket.assembler.Assembler;
import com.yinuo.socket.assembler.MessageAssembler;
import com.yinuo.socket.service.ClientService;
import com.yinuo.socket.service.MessageService;
import org.apache.log4j.Logger;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-31
 */
public class ServiceFactory {
    static final Logger LOGGER = Logger.getLogger(ServiceFactory.class);

    static class ServiceFactoryHolder {
        static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return ServiceFactoryHolder.INSTANCE;
    }

    private ServiceFactory() {
        messageService = new MessageService(new ClientService());
        assembler = new MessageAssembler();
    }

    MessageService messageService;
    Assembler assembler;

    public MessageService getMessageService() {
        return messageService;
    }

    public Assembler getAssembler() {
        return assembler;
    }
}
