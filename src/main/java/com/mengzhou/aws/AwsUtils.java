package com.mengzhou.aws;

public class AwsUtils {
    public static String getAwsRegion() {
        return System.getenv().getOrDefault("AWS_REGION", "ca-central-1");
    }

    private AwsUtils() {
    }
}
