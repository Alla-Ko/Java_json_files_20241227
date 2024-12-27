package chat;

import org.json.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserManager {
	private List<User> users;
	public UserManager() {
        users = new ArrayList<>();
		loadUsers(); // load users from file or database

    }
	public void registerUser(String username, String password) {
		User user = new User(username, password);
        users.add(user);
		saveUsers(); // save users from file or database
    }
	public User getUserByUsername(String username){

		//System.out.println("Users: " + users.size());

		for(User user : users){
			String currentUserName = user.getUsername();

			if(Objects.equals(currentUserName, username)) {
				//System.out.println(currentUserName);
				return user;
			}
		}
		return null;
	}
	public JSONArray loadFromFile(String filename){
		File file=new File(filename);
		if(!file.exists()){
			return new JSONArray();
		}
		try(BufferedReader reader=new BufferedReader(new FileReader(file))){
			StringBuilder jsonData = new StringBuilder();
			String line;
			while((line=reader.readLine()) != null){
				jsonData.append(line);
			}
			return new JSONArray(jsonData.toString());
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}
	private void loadUsers(){
		List<User> loadedUsers=new ArrayList<User>();
		JSONArray jsonArray=loadFromFile("users.json");
        for(int i=0;i<jsonArray.length();i++){
            User user=new User(jsonArray.getJSONObject(i));
	        loadedUsers.add(user);
        }
		users=loadedUsers;
	}
	private void saveUsers(){
		JSONArray jsonArray=new JSONArray();
        for(User user: users){
            jsonArray.put(user.toJson());
        }
        saveToFile("users.json",jsonArray);
	}
	private void saveToFile(String filename, JSONArray jsonArray){
		File file=new File(filename);
        try(FileWriter writer=new FileWriter(file)){
            writer.write(jsonArray.toString(2));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
	}
}
