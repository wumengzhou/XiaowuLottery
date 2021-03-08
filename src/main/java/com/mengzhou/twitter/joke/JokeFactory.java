package com.mengzhou.twitter.joke;

import com.google.common.collect.ImmutableList;

import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class JokeFactory {

    private static final String DATE_SIGNATURE = "今天是%s。";

    private static final List<String> BUYING_JOKES = ImmutableList.of(
            "小吴买了一张彩票",
            "巴韭特说买股票不如买彩票，所以小吴买了一张。",
            "小吴亲测，用AmEx卡买彩票中奖率比较高，所以今天又用AmEx买了一张。",
            "美股大跌，小吴割肉转投彩票。"
    );

    private static final List<String> REDEEMING_JOKES = ImmutableList.of(
            "小吴并没有中奖。",
            "小吴还没有中奖，请再买一张。",
            "小吴的彩票说请再接再厉。",
            "小吴用AmEx买的彩票没有中奖。"
    );

    public static String getJoke(ChronoLocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月dd日，EEE", new Locale("zh"));
        String dateInZh = localDate.format(formatter);
        return String.format(DATE_SIGNATURE, dateInZh) + getJokeTemplate(localDate);
    }

    private static String getJokeTemplate(ChronoLocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE", new Locale("en"));
        String weekday = localDate.format(formatter).toLowerCase();
        List<String> jokeCandidates;
        switch (weekday) {
            case "tue":
            case "sat":
                jokeCandidates = REDEEMING_JOKES;
                break;
            default:
                jokeCandidates = BUYING_JOKES;
        }

        return getRandomJoke(jokeCandidates);
    }

    private static String getRandomJoke(List<String> jokeCandidates) {
        int size = jokeCandidates.size();
        Random random = new Random();
        return jokeCandidates.get(random.nextInt(size));
    }
}
