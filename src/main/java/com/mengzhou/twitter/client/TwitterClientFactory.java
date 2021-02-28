package com.mengzhou.twitter.client;

import com.mengzhou.twitter.secrets.TwitterSecrets;
import com.mengzhou.twitter.secrets.TwitterSecretsFactory;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterClientFactory {

    public static Twitter of(String awsRegion) {
        TwitterSecrets twitterSecrets = TwitterSecretsFactory.of(awsRegion);

        ConfigurationBuilder cb = new ConfigurationBuilder()
                .setOAuthConsumerKey(twitterSecrets.getOAuthConsumerKey())
                .setOAuthConsumerSecret(twitterSecrets.getOAuthConsumerSecret())
                .setOAuthAccessToken(twitterSecrets.getOAuthAccessToken())
                .setOAuthAccessTokenSecret(twitterSecrets.getOAuthAccessTokenSecret());
        TwitterFactory twitterFactory = new TwitterFactory(cb.build());
        return twitterFactory.getInstance();
    }
}
