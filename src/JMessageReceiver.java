/**
 * Filename: JMessageReceiver.java
 * Created by syynth on 6/22/14.
 */
public interface JMessageReceiver<TMessage extends JMessage> {

  public void notifyReceiver(TMessage message);

}
