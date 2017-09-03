package iamandyoureagency;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import org.eclipse.jetty.server.session.JDBCSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Iam and you be through your talk. And htis is the speechlet to begin.
 *
 * All story, characters, dialog and source code are for educational purposes only.
 * Any use of this application and the characters defined for profit are reserved 
 * by the copyright holder, Kalab Oster.
 *
 * Copyright © 2017 Kalab Oster.
 */
public class IamAndYoureAgencySpeechlet implements Speechlet {


    private static final Logger log = LoggerFactory.getLogger(IamAndYoureAgencySpeechlet.class);


    /**
     * Stored session attribute defined start time
     */
    private static final String SESSION_START_TIME_KEY = "start-time";


    /**
     * Stored session attribute defined start time
     */
    private static final String SESSION_END_TIME_KEY = "end-time";


    /**
     * Stored session attribute defined start time
     */
    private static final String MEMBER_UUID_KEY = "member-guid";




    /**
     * Stored session attribute defined stage
     */
    private static final String SESSION_STAGE_KEY = "stage";

    /**
     * Stage 0 for the stranger.
     */
    private static final String STRANGER_STAGE = "Stranger";

    /**
     * Stage 1 everyone i the beginning state of friend.
     */
    private static final String FRIEND_STAGE = "Friend";

    /**
     * Stage 2 the adviser state, so now we are talking about Iam and Youre Agency.
     */
    private static final String ADVISER_STAGE = "Adviser";

    /**
     * Stage 3 the contributor state to try to become a founder with a story or code.
     */
    private static final String CONTRIBUTOR_STAGE = "Contributor";


    /**
     * Boolean if adviser NDA accepted
     */
    private static final String  ADVISER_NDA_ACCEPT_KEY = "adviser-nda-accept";

    /**
     * Boolean If Plan Heard
     */
    private static final String  ADVISER_KNOW_FOUR_PART_PLAN_KEY = "adviser-know-four-part-plan";


    /**
     * Boolean if adviser NDA contributor
     */
    private static final String CONTRIBUTOR_NDA_ACCEPT_KEY = "contributor-nda-accept";

    /**
     * Boolean to know if a title has been given.
     */
    private static final String CONTRIBUTOR_CONTRIBUTE_TITLE_KEY = "adviser-contribute-title";


    @Override
    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {

        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

        //Define UUID.
        UUID uuid = java.util.UUID.randomUUID();

        //Define time.
        LocalDateTime localDateTime = LocalDateTime.now();

        // Set session state and define begin time.
        session.setAttribute(SESSION_STAGE_KEY, STRANGER_STAGE);
        session.setAttribute(SESSION_START_TIME_KEY, localDateTime.toString());
        session.setAttribute(SESSION_END_TIME_KEY, null);
        // Set and create keys to track an adviser's state
        session.setAttribute(ADVISER_NDA_ACCEPT_KEY, false);
        session.setAttribute(ADVISER_KNOW_FOUR_PART_PLAN_KEY, false);
        // SEt and create keys to track a contributor's state.
        session.setAttribute(CONTRIBUTOR_NDA_ACCEPT_KEY, false);
        session.setAttribute(CONTRIBUTOR_CONTRIBUTE_TITLE_KEY, false);
        // Set the member guuid that will be remembered and have the title appended upoon once the title is spoken.
        session.setAttribute(MEMBER_UUID_KEY, uuid);

    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {

        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

        return handleSentencePitch();
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {

        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

        Intent intent = request.getIntent();
        String intentText = (intent != null) ? intent.getName() : null;


        //The only time yes is used a
        if ("AMAZON.YesIntent".equals(intentText) && session.getAttribute(SESSION_STAGE_KEY).equals(STRANGER_STAGE)) {

            return handleAdviserPitch(session);
            
        } else if ("DefineAdviserIntent".equals(intentText) && session.getAttribute(SESSION_STAGE_KEY).equals(FRIEND_STAGE)){

            return handleDefineAdviser();

        } else if ("DefineAdviserNDAIntent".equals(intentText) && (session.getAttribute(SESSION_STAGE_KEY).equals(FRIEND_STAGE)
                || session.getAttribute(SESSION_STAGE_KEY).equals(ADVISER_STAGE))){

            return handleDefineAdviserNDA();

        } else if ("AgreeAdviserNDAIntent".equals(intentText) && session.getAttribute(SESSION_STAGE_KEY).equals(FRIEND_STAGE)){

            return handleAdviserAgreeNDA(session);

        } else if ("DefineFourPhasePlanIntent".equals(intentText) && session.getAttribute(SESSION_STAGE_KEY).equals("")){

            return handleDefineFourPhasePlan(session);

        } else if ("DefineContributorIntent".equals(intentText) && session.getAttribute(SESSION_STAGE_KEY).equals("")){

            return handleDefineContributor(session);

        } else if ("DefineContributorNDAIntent".equals(intentText) && session.getAttribute(SESSION_STAGE_KEY).equals("")){

            return handleDefineContributorNDA(session);

        } else if ("AgreeContributorNDAIntent".equals(intentText) && session.getAttribute(SESSION_STAGE_KEY).equals("")){

            return handleAgreeContributorNDA(session);

        } else if ("AMAZON.HelpIntent".equals(intentText)){

            return handleHelp(session);

        } else if ("AMAZON.CancelIntent".equals(intentText)){

            return handleExit(session);
        } else {

            return handleUnknown(session);
        }

    }

    @Override
    public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {

        log.info("onSessionEnd requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());


        //Define time.
        LocalDateTime localDateTime = LocalDateTime.now();
        // Store end time
        session.setAttribute(SESSION_END_TIME_KEY, localDateTime.toString());

        //TODO: Store session data in DynamoDB or S3

    }


    /**
     *
     *       HANDLES
     *
     * Get responses methods just get the response called from within the handles.
     *
     */

    /**
     * A simple pitch for any stranger.
     *
     * @return SpeechletResponse OneSentencePitch
     */
    public SpeechletResponse handleSentencePitch(){

        return getSentencePitchResponse();
    }

    /**
     * Sets one as a friend and begins the pitch to be an adviser.
     *
     * @param session The session of the skill.
     * @return This returns the Adviser pitch response.
     */
    public SpeechletResponse handleAdviserPitch(final Session session){

        session.setAttribute(SESSION_STAGE_KEY, FRIEND_STAGE);

        return getAdviserPitchResponse();
    }


    /**
     * Returns a response to the intent of what is adviser.
     *
     * @return This returns the Adviser definition response.
     */
    public SpeechletResponse handleDefineAdviser(){

        return getDefineAdviserResponse();
    }


    /**
     * Returns a response to the intent of wanting to know about the adviser NDA.
     *
     * @return This returns the Adviser NDA definition response.
     */
    public SpeechletResponse handleDefineAdviserNDA(){

        return getDefineAdviserNDAResponse();
    }

    /**
     * The handle to change the state to adviser and set the boolean to true to be sure to tell through the story
     *
     * @param session The session of the skill.
     * @return This returns the Adviser NDA definition response.
     */
    public SpeechletResponse handleAdviserAgreeNDA(final Session session){

        session.setAttribute(SESSION_STAGE_KEY, ADVISER_STAGE);
        session.setAttribute(ADVISER_NDA_ACCEPT_KEY, true);

        return getAgreeAdvisorNDAResponse();
    }

    /**
     * The handle to tell of the plan then prompt to see of user wants to be a contributor.
     *
     * @param session The session state passed to update.
     * @return The returns more requests to prompt the user and keep the story going.
     */
    public SpeechletResponse handleDefineFourPhasePlan(final Session session){

        session.setAttribute(ADVISER_KNOW_FOUR_PART_PLAN_KEY, true);

        return getDefineFourPhasePlanResponse();
    }



    public SpeechletResponse handleDefineContributor(final Session session){

        return getDefineContributorResponse();
    }



    public SpeechletResponse handleDefineContributorNDA(final Session session){

        return getDefineContributorNDAResponse();
    }



    public SpeechletResponse handleAgreeContributorNDA(final Session session){

        return getAgreeContributorNDAResponse();
    }

    public SpeechletResponse handleHelp(final Session session){

        return getHelpResponse(session);
    }




    public SpeechletResponse handleExit(final Session session){

        return getExitResponse(session);
    }



    public SpeechletResponse handleUnknown(final Session session){


        return getUnknownResponse(session);
    }


    /**
     *
     *       GET RESPONSES
     *
     * Get responses methods just get the response called from within the handles.
     *
     */


    public SpeechletResponse getSentencePitchResponse(){

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(IamAndYoureAgencyText.SentencePitchSpeech.get());


        PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
        repromptSpeech.setText(IamAndYoureAgencyText.SentencePitchReprompt.get());

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptSpeech);

        return SpeechletResponse.newAskResponse(speech, reprompt);
    }


    public SpeechletResponse getAdviserPitchResponse(){

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(IamAndYoureAgencyText.AdviserSpeech.get());


        PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
        repromptSpeech.setText(IamAndYoureAgencyText.AdviserReprompt.get());

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptSpeech);

        return SpeechletResponse.newAskResponse(speech, reprompt);

    }


    public SpeechletResponse getDefineAdviserResponse(){

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(IamAndYoureAgencyText.AdviserDefinitionSpeech.get());

        PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
        repromptSpeech.setText(IamAndYoureAgencyText.AdviserDefinitionReprompt.get());

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptSpeech);

        return SpeechletResponse.newAskResponse(speech, reprompt);

    }


    public SpeechletResponse getDefineAdviserNDAResponse(){

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(IamAndYoureAgencyText.AdviserNDADefinitionSpeech.get());

        PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
        repromptSpeech.setText(IamAndYoureAgencyText.AdviserNDADefinitionReprompt.get());

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptSpeech);

        return SpeechletResponse.newAskResponse(speech, reprompt);

    }

    public SpeechletResponse getAgreeAdvisorNDAResponse(){

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(IamAndYoureAgencyText.AdviserAgreeSpeech.get());

        PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
        repromptSpeech.setText(IamAndYoureAgencyText.AdviserAgreeReprompt.get());

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptSpeech);

        return SpeechletResponse.newAskResponse(speech, reprompt);
    }


    public SpeechletResponse getDefineFourPhasePlanResponse(){

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(IamAndYoureAgencyText.Phase1.get() + IamAndYoureAgencyText.Phase2.get() +
                IamAndYoureAgencyText.Phase3.get() + IamAndYoureAgencyText.Phase4.get() +
                IamAndYoureAgencyText.ContributorSpeech.get());

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt);

    }


    public SpeechletResponse getDefineContributorResponse(){

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(IamAndYoureAgencyText.ContributorDefinitionSpeech.get());

        PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
        repromptSpeech.setText(IamAndYoureAgencyText.ContributorDefinitionReprompt.get());

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptSpeech);

        return SpeechletResponse.newAskResponse(speech, reprompt);

    }


    public SpeechletResponse getDefineContributorNDAResponse(){

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(IamAndYoureAgencyText.ContributorNDADefinitionSpeech.get());

        PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
        repromptSpeech.setText(IamAndYoureAgencyText.ContributorNDADefinitionReprompt.get());

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptSpeech);

        return SpeechletResponse.newAskResponse(speech, reprompt);

    }


    public SpeechletResponse getAgreeContributorNDAResponse(){

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(IamAndYoureAgencyText.ContributorAgreeSpeech.get());

        PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
        repromptSpeech.setText(IamAndYoureAgencyText.ContributorAgreeReprompt.get());

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptSpeech);

        return SpeechletResponse.newAskResponse(speech, reprompt);

    }

    public SpeechletResponse getHelpResponse(final Session session){

        String speechText;

        if(session.getAttribute(SESSION_STAGE_KEY) == STRANGER_STAGE ){

            speechText = IamAndYoureAgencyText.HelpStranger.get();

        } else if (session.getAttribute(SESSION_STAGE_KEY) == FRIEND_STAGE) {

            speechText = IamAndYoureAgencyText.HelpFriend.get();

        } else if (session.getAttribute(SESSION_STAGE_KEY) == ADVISER_STAGE) {

            speechText = IamAndYoureAgencyText.HelpAdviser.get();

        } else if (session.getAttribute(SESSION_STAGE_KEY) == CONTRIBUTOR_STAGE) {

            speechText = IamAndYoureAgencyText.HelpContributor.get();

        } else {

            speechText = IamAndYoureAgencyText.HelpStranger.get();
        }

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        PlainTextOutputSpeech textReprompt = new PlainTextOutputSpeech();
        textReprompt.setText(IamAndYoureAgencyText.HelpReprompt.get());

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(textReprompt);


        return SpeechletResponse.newAskResponse(speech, reprompt);
    }

    public SpeechletResponse getExitResponse(Session session){

        String speechText = new String();

        if(session.getAttribute(SESSION_STAGE_KEY) == STRANGER_STAGE ){

            speechText = IamAndYoureAgencyText.ExitStranger.get();

        } else if (session.getAttribute(SESSION_STAGE_KEY) == FRIEND_STAGE) {

            speechText = IamAndYoureAgencyText.ExitFriend.get();

        } else if (session.getAttribute(SESSION_STAGE_KEY) == ADVISER_STAGE) {

            speechText = IamAndYoureAgencyText.ExitAdviser.get();

        } else if (session.getAttribute(SESSION_STAGE_KEY) == CONTRIBUTOR_STAGE) {

            speechText = IamAndYoureAgencyText.ExitContributor.get();

        }

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech);


    }

    public SpeechletResponse getUnknownResponse(Session session){

        String speechText = new String();

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech);


    }


}
