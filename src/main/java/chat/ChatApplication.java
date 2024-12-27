package chat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Scanner;

public class ChatApplication {
		User user=null;
	public void startChat(){


		JSONArray chatHistory = loadChatHistory();
		System.out.println("\033[0;30m\033[48;5;51mВітаємо в чаті\033[0m");
		while(true) {
			if (user != null) {
				System.out.println("\033[38;5;51mВи увійшли як: " + user.getUsername()+"\033[0m");

				while (user != null) {
					System.out.println("\033[38;5;51mЗробіть свій вибір:");
					System.out.println("1 - Написати повідомлення:");
					System.out.println("2 - Показати історію чату");
					System.out.println("3 - Вийти з аккаунта");
					System.out.println("0 - Закрити програму\033[0m");

					int choice = getInteger();
					switch (choice) {
						case 1:
							System.out.println("\033[38;5;51mВведіть повідомлення:\033[0m");
							String message = getString();
							JSONObject messageObject = new JSONObject();
							messageObject.put("user", user.getUsername());
							messageObject.put("message", message);
							chatHistory.put(messageObject);
							saveChatHistory(chatHistory);
							break;
						case 2:
							displayChatHistory(chatHistory);
							break;
						case 3:
							user = null;
							System.out.println("\033[38;5;51mВи вийшли з чата\033[0m");

							break;
						case 0:
							System.out.println("\033[38;5;51mЗакриваємо програму\033[0m");
							saveChatHistory(chatHistory);
							System.exit(0);
							return;
						default:
					}

				}
			}
			else {
				while (user == null) {
					System.out.println("\033[38;5;51mЗробіть свій вибір:");
					System.out.println("1 - Вхід:");
					System.out.println("2 - Реєстрація");
					System.out.println("0 - Закрити програму\033[0m");

					int choice = getInteger();
					UserManager userManager = new UserManager();
					switch (choice) {
						case 1:
							System.out.println("\033[38;5;51mВведіть username:\033[0m");
							String username = getString();
							System.out.println("\033[38;5;51mВведіть password:\033[0m");
							String password = getString();
							User user = userManager.getUserByUsername(username);

							if (user != null && user.getPassword().equals(password)) {
								this.user = user;
								break;
							}
							else {
								System.out.println("\033[31mНевірна комбінація username та password\033[0m");
								continue;
							}
						case 2:
							System.out.println("\033[38;5;51mВведіть username:\033[0m");
							String newUsername = getString();
							System.out.println("\033[38;5;51mВведіть password:\033[0m");
							String newPassword = getString();
							userManager.registerUser(newUsername, newPassword);
							System.out.println("\033[38;5;51mРеєстрація успішна!\033[0m");
							continue;

						case 0:
							System.out.println("\033[38;5;51mЗакриваємо програму\033[0m");
							saveChatHistory(chatHistory);
							System.exit(0);
							return;
						default:
					}
				}
			}
		}


	}
	public JSONArray loadChatHistory(){
		File file=new File("chatHistory.json");
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
	public void saveChatHistory(JSONArray chatHistory){
		File file=new File("chatHistory.json");
        try(FileWriter writer=new FileWriter(file)){
            writer.write(chatHistory.toString());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
	}
	public void displayChatHistory(JSONArray chatHistory){
		System.out.println("Chat History:");
		for(int i=0; i<chatHistory.length(); i++){
            System.out.println(chatHistory.getJSONObject(i).getString("user") + ": " + chatHistory.getJSONObject(i).getString("message"));
        }
	}
	public static int getInteger(){
		Scanner scanner = new Scanner(System.in);
		int number;
		do {
			try {
				number = scanner.nextInt();
				scanner.nextLine();
				break;
			} catch (java.util.InputMismatchException e) {
				System.out.println("\033[0;31mВведіть ціле число. Спробуйте ще раз.\033[0m");
				scanner.nextLine();
			}
		} while (true);
		return number;
	}
	public static String getString(){
		Scanner scanner = new Scanner(System.in);
		String newString;
		do {
			newString = scanner.nextLine();
			if(newString.isEmpty()){
				System.out.println("\033[0;31mПоле не може бути пустим. Спробуйте ще раз.\033[0m");
			} else {
				break;
			}
		}while(true);
		return newString;
	}
}
