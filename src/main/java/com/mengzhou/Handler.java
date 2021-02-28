package com.mengzhou;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.mengzhou.aws.AwsUtils;
import com.mengzhou.twitter.client.TwitterClientFactory;
import com.mengzhou.twitter.joke.JokeFactory;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.time.LocalDate;
import java.util.Map;

public class Handler implements RequestHandler<Map<String,String>, String> {

    @Override
    public String handleRequest(Map<String, String> stringStringMap, Context context) {
        String awsRegion = AwsUtils.getAwsRegion();
        Twitter twitter = TwitterClientFactory.of(awsRegion);
        LocalDate localDate = LocalDate.now();
        String joke = JokeFactory.getJoke(localDate);
        try {
            twitter.updateStatus(joke);
        } catch (TwitterException e) {
            throw new RuntimeException(e);
        }

        return "200 OK";
    }
}
