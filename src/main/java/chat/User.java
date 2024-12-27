package chat;

import org.json.JSONObject;

public class User {

	public String username;
	public String password;





	public User(String username, String password) {
		this.username = username;
		this.password = password;

	}

	public User(JSONObject jsonObject) {
		this.username = jsonObject.getString("username");
        this.password = jsonObject.getString("password");
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	public JSONObject toJson(){
		JSONObject json = new JSONObject();
		json.put("username", username);
		json.put("password", password);


		return json;
	}
	public static User fromJson(JSONObject json){

		String username = json.getString("username");
		String password = json.getString("password");

		return new User(username,password);
	}

	public String getUsername() {
		return username;
	}
}
