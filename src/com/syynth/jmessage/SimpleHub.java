/**
 * Filename: SimpleHub.java
 * Created by syynth on 6/23/14.
 */

package com.syynth.jmessage;

import java.util.ArrayList;
import java.util.HashMap;

public class SimpleHub implements JMessageHub {

  private HashMap<Class<?>, ArrayList<JMessageReceiver>> mapHandlers;

  public SimpleHub() {
    mapHandlers = new HashMap<>();
  }

  @Override
  public <TMessage extends JMessage> void publish(Class<TMessage> messageClass, TMessage message) {
    if (mapHandlers.containsKey(messageClass)) { // we can do nothing if no subscribers are available
      ArrayList<JMessageReceiver> list = mapHandlers.get(messageClass);
      for (JMessageReceiver rec : list) {
        rec.notifyReceiver(message);
      }
    }
  }

  @Override
  public <TMessage extends JMessage, TRec extends JMessageReceiver<TMessage>> JMessageToken<TMessage>
    subscribe(Class<TMessage> messageClass, TRec handler) {
    ArrayList<JMessageReceiver> list;
    if (mapHandlers.containsKey(messageClass)) {
      list = mapHandlers.get(messageClass);
    } else {
      list = new ArrayList<>();
      list.add(handler);
    }
    mapHandlers.put(messageClass, list);
    return new JMessageToken<TMessage>(handler, messageClass);
  }

  @Override
  public <TMessage extends JMessage> void unsubsribe(JMessageToken<TMessage> token) {
    if (mapHandlers.containsKey(token.getMessageClass())) {
      ArrayList<JMessageReceiver> list = mapHandlers.get(token.getMessageClass());
      list.remove(token.getHandler());
    }
  }
}
