package com.barexas.service.user;


import com.barexas.entities.CustomUser;
import com.google.gson.Gson;

import java.io.*;

/**
 * UserService implementation witch uses GSON lib.
 */
public class UserServiceJsonImpl implements UserService{

    private static String JSON_DIRECTORY = "users";
    private static String JSON_FORMAT = ".json";

    private Gson gson;

    @Override
    public CustomUser getUserByLogin(String login) {
        File file = new File(JSON_DIRECTORY+"/"+login+JSON_FORMAT);
        if (!file.exists()) {
            return null;
        }
        return this.getUserFromFile(file);
    }

    private CustomUser getUserFromFile(File file) {
        CustomUser user = null;
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            user = gson.fromJson(sb.toString(), CustomUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void addUser(CustomUser customUser) {
        File file = new File(JSON_DIRECTORY+"/"+customUser.getLogin()+JSON_FORMAT);
        String json = gson.toJson(customUser);
        try(FileWriter writer = new FileWriter(file)){
            file.createNewFile();
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(CustomUser customUser) {
        File file = new File(JSON_DIRECTORY+"/"+customUser.getLogin()+JSON_FORMAT);
        String json = gson.toJson(customUser);
        try(FileWriter writer = new FileWriter(file, false)){
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserServiceJsonImpl(Gson gson) {
        File file = new File(JSON_DIRECTORY);
        file.mkdirs();
        this.gson = gson;
    }

    public static String getJsonDirectory() {
        return JSON_DIRECTORY;
    }

    public static void setJsonDirectory(String jsonDirectory) {
        JSON_DIRECTORY = jsonDirectory;
    }

    public static String getJsonFormat() {
        return JSON_FORMAT;
    }

    public static void setJsonFormat(String jsonFormat) {
        JSON_FORMAT = jsonFormat;
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
