package iamandyoureagency;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * The lambda handler.
 *
 * All story, characters, dialog and source code are for educational purposes only.
 * Any use of this application and the characters defined for profit are reserved 
 * by the copyright holder, Kalab Oster.
 *
 * Copyright © 2017 Kalab Oster.
 *
 */
public final class IamAndYoureAgencySpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds = new HashSet<String>();
    static {
        supportedApplicationIds.add("amzn1.ask.skill.25c6d298-c629-416a-a4c9-d7b3d5861cc8");
    }

    public IamAndYoureAgencySpeechletRequestStreamHandler() {
        super(new IamAndYoureAgencySpeechlet(), supportedApplicationIds);
    }
}
