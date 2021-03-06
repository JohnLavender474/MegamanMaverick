package com.game.core;

import com.game.Message;
import com.game.MessageListener;

/**
 * The interface Message dispatcher.
 */
public interface IMessageDispatcher {

    /**
     * Add listener.
     *
     * @param messageListener the message listener
     */
    void addListener(MessageListener messageListener);

    /**
     * Remove listener.
     *
     * @param messageListener the message listener
     */
    void removeListener(MessageListener messageListener);

    /**
     * Add message.
     *
     * @param message the message
     */
    void addMessage(Message message);

    /**
     * Update message dispatcher.
     *
     * @param delta the delta
     */
    void updateMessageDispatcher(float delta);

}
