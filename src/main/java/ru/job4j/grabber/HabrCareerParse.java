package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {

    public static final String PREFIX = "/vacancies?page=";
    public static final String SUFFIX = "&q=Java%20developer&type=all";
    private final DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    private String retrieveDescription(String link) throws IOException {
        Connection connection = Jsoup.connect(link);
        Document document = connection.get();
        Elements descriptionBody = document.select(".vacancy-description__text");
        return Jsoup.parse(descriptionBody.html()).wholeText();
    }

    private List<Post> createPost(String link) throws IOException {
        List<Post> result = new ArrayList<>();
        for (int pageNumber = 1; pageNumber < 6; pageNumber++) {
            DateTimeParser dateTimeParse = new HabrCareerDateTimeParser();
            Post post = new Post();
            String fullLink = "%s%s%d%s".formatted(link, PREFIX, pageNumber, SUFFIX);
            Connection connection = Jsoup.connect(fullLink);
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkElement = titleElement.child(0);
                Element date = row.select(".vacancy-card__date").first();
                Element vacancyDate = date.child(0);
                String dateTime = vacancyDate.attr("datetime");
                String linkOut = String.format("%s%s", link, linkElement.attr("href"));
                post.setTitle(titleElement.text());
                post.setLink(linkOut);
                post.setCreated(dateTimeParse.parse(dateTime));
                try {
                    post.setDescription(retrieveDescription(linkOut));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                result.add(post);
            });
        }
        return result;
    }

    @Override
    public List<Post> list(String link) {
        List<Post> result = new ArrayList<>();
        try {
            result.addAll(createPost(link));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}