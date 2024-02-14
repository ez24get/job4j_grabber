package ru.job4j.grabber;

import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;
import ru.job4j.quarz.AlertRabbit;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store {

    private Connection connection;

    public PsqlStore(Properties config) throws SQLException {
        try {
            Class.forName(config.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        connection = DriverManager.getConnection(config.getProperty("url"),
                config.getProperty("username"),
                config.getProperty("password"));
    }

    private static Properties readProperties() {
        Properties properties = new Properties();
        try (InputStream in = AlertRabbit.class.getClassLoader().
                getResourceAsStream("db/grabber/grabber.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private Post createPost(ResultSet resultSet) throws SQLException {
        return new Post(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getString("link"),
                resultSet.getTimestamp("created").toLocalDateTime()
        );
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             "INSERT INTO post(name, description, link, created)"
                                     + " VALUES (?, ?, ?, ?)"
                                     + " ON CONFLICT (link)"
                                     + " DO NOTHING;")) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getDescription());
            statement.setString(3, post.getLink());
            statement.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> result = new ArrayList<>();
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM post;")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(createPost(resultSet));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Post findById(int id) {
        Post post = new Post();
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT id, name, description, link, created"
                             + " FROM items WHERE id = ?")) {
            statement.setInt(1, id);
            statement.execute();
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    post = createPost(resultSet);
                } else {
                    post = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Post post = new Post();
        DateTimeParser dateTimeParser = new HabrCareerDateTimeParser();
        LocalDateTime time = dateTimeParser.parse("2007-12-03T10:15:30+01:00");
        post.setTitle("Java");
        post.setDescription("bla-bla");
        post.setLink("link");
        post.setCreated(time);
        Properties properties = readProperties();
        try (PsqlStore psqlPost = new PsqlStore(properties)) {
            psqlPost.save(post);
            System.out.println(psqlPost.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}