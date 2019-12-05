package de.uniba.dsg.dsam.backend.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class OrderMessageDrivenBean implements MessageListener {

    public OrderMessageDrivenBean() {}

    public void onMessage(Message message) {

    }
}
