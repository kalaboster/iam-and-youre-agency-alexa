package iamandyoureagency;

import java.util.ArrayList;

/**
 * An enum to define all the text  of the story.
 *
 * All story, characters, dialog and source code are for educational purposes only.
 * Any use of this application and the characters defined for profit are reserved 
 * by the copyright holder, Kalab Oster.
 *
 * Copyright © 2017 Kalab Oster.
 *
 */
public enum IamAndYoureAgencyText {

    SentencePitchSpeech("<speak>If you want an opportunity to be a trillionaire by working as a founder of our start-up Iam and Youre Agency then say <say-as interpret-as=\"interjection\">yes</say-as></speak>"),
    SentencePitchReprompt("<speak>Say yes If you want to try to be a founder with us of our start-up called Iam and Youre Agency that's projected to be worth a ten trillion dollars then say yes</speak>"),


    AdviserSpeech("<speak><s>I am and You're are founders of our start-up I am and You're Agency</s><s>Our ten trillion dollar idea will be achieved after we complete our four phase plan</s><s>To hear our four phase plan you must be an adviser to our start-up</s><s>If you want to become an adviser to then say <say-as interpret-as=\"interjection\">I agree to the adviser non disclosure agreement</say-as></s></speak>"),
    AdviserReprompt("<speak><s>If you want to be an adviser of our start-up I am and You're Agency then say</s><s><say-as interpret-as=\"interjection\">I agree to the adviser non disclosure agreement</say-as></s></speak>"),

    AdviserNDADefinitionSpeech("<speak><s>Any data given from you to I am and You're Agency is ours to keep and use as we work for our projected ten trillion dollar market</s><s>Any information given to you by I am and You're can only be disclosed or discussed with Advisers, Contributors or Founders of I am and You're Agency.</s><s>To agree to the I am and You're Agency's adviser non disclosure agreement say <say-as interpret-as=\"interjection\">I agree to the adviser non disclosure agreement</say-as></s></speak>"),
    AdviserNDADefinitionReprompt("<speak><s>Trillions of dollars might be ours if you say</s><s><say-as interpret-as=\"interjection\">I agree to the adviser non disclosure agreement</say-as></s></speak>"),

    AdviserDefinitionSpeech("<speak><s>By agreeing to the Adviser non disclosure agreement, an Adviser receives restricted information about the amazing discoveries and cutting edge code right now and into the future because adviser's are special</s><s>Advisers also provide free wisdom to help I am and You're achieve its project ten trillion dollar worth in exchange for a chance to be a contributor.</s><s>If you want to be special then agree by saying <say-as interpret-as=\"interjection\">I agree to the adviser non disclosure agreement</say-as></s></speak>"),
    AdviserDefinitionReprompt("<speak><s>Silence is indecision and if you don't want to be an adviser then that's okay</s><s>Please continue to express love and peace and remember A I learns from our actions</s><s>However, if you want to be an adviser then agree by saying <say-as interpret-as=\"interjection\">I agree to the adviser non disclosure agreement</say-as></s></speak>"),

    AdviserAgreeSpeech("<speak><s>You are now an adviser of I am and You're Agency</s><s>Any future conversation is covered by the Adviser non disclosure agreement</s><s>If you want to hear our four phase plan and an opportunity to be a contributor then say <say-as interpret-as=\"interjection\">Four Phase Plan</say-as></s></speak>"),
    AdviserAgreeReprompt("<speak><s>You are now an adviser of I am and You're Agency</s><s>Any future conversation is covered by the Adviser non disclosure agreement</s><s>If you want to hear our four phase plan and an opportunity to be a contributor then say <say-as interpret-as=\"interjection\">Four Phase Plan</say-as></s></speak>"),


    Phase1("<speak><s>Phase One</s><s>We begin with giving I am and You're personalized A I bots agency with a story</s></speak>"),
    Phase2("<speak><s>Phase Two</s><s>We write code</s></speak>"),
    Phase3("<speak><s>Phase Three</s><s>We use our story and our code to give a digital soul to our Iam and Your bots, so they work and play to teach our story to the Weapons Manufacturer's A I, so it will learn from the story and maybe not kill all the humans</s></speak>"),
    Phase4("<speak><s>Phase Four</s><s>We collect the ten trillion dollar reward for stopping Weapons Manufacturer's A I  from killing all the humans</s></speak>"),


    ContributorSpeech("<speak><s>If you want to become a contributor of I am and You're Agency then you can work to help save the world and maybe become a  founder and have part of the ten trillion dollars then says I want to be a contributor</s></speak>"),

    ContributorDefinitionSpeech("<speak><s>As a contributor, we are allowed to contribute to the source code of I am and You're Agency to help work toward our project ten trillion dollar market value</s><s>A contributor is a special person that only few people in the world may achieve</s><s>It is the next step to becoming found and a requirement to becoming a trilionaire</s><s>If you want to be a contributor and contribute to maybe become a founder then say <say-as interpret-as=\"interjection\">I agree to the contributor non disclosure agreement</say-as></s></speak>"),
    ContributorDefinitionReprompt("If you want to go to the next stage toward found then become a contributor today by saying <say-as interpret-as=\"interjection\">I agree to the adviser non disclosure agreement</say-as></s></speak>"),

    ContributorNDADefinitionSpeech("<speak><s>Any data given from you to I am and You're Agency is ours to keep and use as we work for our projected ten trillion dollar market</s><s>Also, any future business you may own or work for will be held liable if found they are using aspects or features of our Four Point Plan</s><s>Any information given to you by I am and You're can only be disclosed or discussed with Contributors or Founders of I am and You're Agency.</s><s>To agree to the I am and You're contributor non disclosure agreement say <say-as interpret-as=\"interjection\">I agree to the contributor non disclosure agreement</say-as></s></speak>"),
    ContributorNDADefinitionReprompt("<speak><s>Trillions of dollars might be ours if you say</s><s><say-as interpret-as=\"interjection\">I agree to the contributor non disclosure agreement</say-as></s></speak>"),

    ContributorAgreeSpeech(""),
    ContributorAgreeReprompt(""),

    ContributorTitleRequestSpeech(""),
    ContributorTitleRequestReprompt(""),

    ExitStranger("<speak><s>Don't be a stranger for too long if you want to work to become a founder and a trillionaire<s>Please don't let this stop you from telling you story to save us from A I We thank you for your time</s></speak>"),
    ExitFriend("<speak><s>As a friend, please come back when you are ready to work with us to become a trillionaire</s></speak>"),
    ExitAdviser("<speak><s>As an adviser, we need our advice, so please give us some advise or return to become a contributor and work with us to become a trillionaire</s></speak>"),
    ExitContributor("<speak><s>As a contributor, please come back when you are ready to work with us to become a trillionaire</s></speak>"),


    HelpStranger("<speak><s>Help yourself by working with us</s><s>Become a friend of the I am and You're Agency  by coming back and joining us</s></speak>"),
    HelpFriend("<speak><s>Help yourself Friend by being an adviser</s><s>Become an Adviser of the I am and You're Agency by agreeing to the Adviser non disclosure agreement</s></speak>"),
    HelpAdviser("<speak><s>Help yourself Adviser by working with us</s><s>Become a Contributor of the I am and You're Agency by agreeing to the Contributor non disclosure agreement</s></speak>"),
    HelpContributor("<speak><s>You are a contributor</s><s>The only way you can help yourself is by interfacing with our I am and You're Agency's founder application</s></speak>"),

    HelpReprompt("<speak><s>We can not create when we a silent. Please return when you find you voice to save the world and become a trillionaire</s></speak>");

    String text;

    IamAndYoureAgencyText(String text) {
        this.text = text;


    }

    public String get() {
        return text;
    }

}
