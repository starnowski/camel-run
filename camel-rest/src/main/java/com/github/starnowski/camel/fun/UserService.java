package com.github.starnowski.camel.fun;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class UserService {

    // use a tree map so they become sorted
    private final Map<String, User> users = new TreeMap<String, User>();

    public UserService() {
        users.put("123", new User(123, "John Doe"));
        users.put("456", new User(456, "Jane Doe"));
    }

    /**
     * Gets a user by the given id
     *
     * @param id  the id of the user
     * @return the user, or <tt>null</tt> if no user exists
     */
    public User getUser(String id) {
        return users.get(id);
    }

    /**
     * List all users
     *
     * @return the list of all users
     */
    public Collection<User> listUsers() {
        return users.values();
    }

    /**
     * Updates or creates the given user
     *
     * @param user the user
     */
    public void updateUser(User user) {
        users.put("" + user.getId(), user);
    }
}