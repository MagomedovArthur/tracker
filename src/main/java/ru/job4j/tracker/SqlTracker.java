package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {

    private Connection cn;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    private void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws SQLException {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement statement =
                     cn.prepareStatement("INSERT INTO items(name, created) VALUES (?, ?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            Timestamp timestampFromLDT = Timestamp.valueOf(item.getCreated());
            statement.setTimestamp(2, timestampFromLDT);
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        try (PreparedStatement statement =
                     cn.prepareStatement("UPDATE items SET name = ? WHERE id = ?")) {
            statement.setString(1, item.getName());
            statement.setInt(2, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement =
                     cn.prepareStatement("DELETE FROM items WHERE id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> allItems = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement("SELECT * FROM items")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    LocalDateTime localDateTime = timestamp.toLocalDateTime();
                    allItems.add(new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            localDateTime
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allItems;
    }

    @Override
    public List<Item> findByName(String itemName) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement("SELECT * FROM items WHERE name = ?")) {
            statement.setString(1, itemName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    LocalDateTime localDateTime = timestamp.toLocalDateTime();
                    items.add(new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            localDateTime
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        try (PreparedStatement statement = cn.prepareStatement("SELECT * FROM items WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    LocalDateTime localDateTime = timestamp.toLocalDateTime();
                    item = new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            localDateTime
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}