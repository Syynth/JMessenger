/**
 * Filename: JMessageHub.java
 * Created by syynth on 6/22/14.
 */

package com.syynth.jmessage;

public interface JMessageHub {

  public <TMessage extends JMessage> void publish(Class<TMessage> messageClass, TMessage message);

  public <TMessage extends JMessage, TReceiver extends JMessageReceiver<TMessage>> JMessageToken<TMessage>
    subscribe(Class<TMessage> messageClass, TReceiver handler);

  public <TMessage extends JMessage> void unsubsribe(JMessageToken<TMessage> token);

}
