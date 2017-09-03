# Iam and Youre Agent Alexa

### License

All story, characters, dialog and source code are for educational purposes only.
Any use of this application and the characters defined for profit are reserved 
by the copyright holder, Kalab Oster.

Copyright © 2017 Kalab Oster. All Rights Reserved.


### Purpose

An application to save the world and words by writing a story.

### Register Alexa with dev account.

1. Go to http://alexa.amazon.com
2. Login.
3. Follows steps to setup echo with account and development.


### Build Skill

1. cd ~/iam-and-youre-agency-alexa
2. export JAVA_HOME=/usr/lib/jvm/java-8-oracle
3. ./gradlew fatJar


### Deploy Lambda

1. Get aws account.
2. Login
3. Select Region to be US-East N. Virginia as they have lambda.
3. Select Lambda service
4. Select 'New Function'
5. 'Select blueprint' choose 'Blank Function'
6. 'Configure triggers' choose 'Alexa Skills Kit'
7. Click next.
8. Configure function
    - Name Function: agencyAlexa
    - Description: Iam and Youre Agency Alexa for Ten Trillion Dollars
    - Runtime: Java 8
    - Lamabda Function Code
        - Code entry type: BLANK
        - Function Package: UPLOAD jar file build in build skill step.
    - Lambda function handler and role
        - Create new role from template
        - Enter role Name
        - Select Policy templates list, select Simple Microservice permissions.
    Leave other settings default and save.


### Instillation of Alexa app Intallation

1. Go to https://developer.amazon.com/
2. Login.
3. Go to in menu and click on 'Alexa'
4. In the appearing 'Alexa Skills Kit' window below, click on 'Get Started >'
5. Click on 'Add New Skill'
6. Configure Skill
7. Configure Skill Information
    - Skill Type: Custom Interaction Model
    - Language: U.S. English
    - Name: Iam and Youre Agency Alexa
    - Invocation Name: Make me rich.
    - Click 'Save'
    - Click 'Next.
8. Configure Interaction Model
    - Copy contents of IntentSchema.json and paste onto the beginning field area of 'Intent Schema'.
    - Copy contents of SampleUtterances.txt and paste on the last text field are of 'Sample Utterances'.
    - Click Next.
9. Configure 'Configuration.'
    - Service Endpoint Type:  Select AWS Lambda ARN (Amazon Resource Name)
    - Service Endpoint Type: Input arn of Lambda.



