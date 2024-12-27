package chat;

import org.json.JSONObject;

public class Message {
	private String content;
	private String timestamp;
	private int userId;



	public Message(String content, String timestamp, int userId) {
		this.content = content;
		this.timestamp = timestamp;
		this.userId = userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public String getContent() {
		return content;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Message{" +
				"content='" + content + '\'' +
				", timestamp='" + timestamp + '\'' +
				", userId=" + userId +
				'}';
	}

	// Add more methods as needed for message handling
	public JSONObject toJson(){
		JSONObject json = new JSONObject();
        json.put("content", content);
        json.put("timestamp", timestamp);
		json.put("userId", userId);
        return json;
	}
	public static Message fromJson(JSONObject json){
        String content = json.getString("content");
        String timestamp = json.getString("timestamp");
		int userId = json.getInt("userId");
        return new Message(content, timestamp, userId);
    }
}
