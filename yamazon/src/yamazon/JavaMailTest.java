package yamazon;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailTest {

	static String msgText = "メール送信テスト";

	public static void main(String[] args) {

		String to = "live11211@gmail.com"; // 送信先メールアドレス設定
		String from = "live11211@gmail.com"; // 送信元メールアドレス

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("live11211", "WjTfgCWGhcaGT84"); //ログインID, パスワード
			}
		});

		Message msg = new MimeMessage(session);

		try {
			msg.setFrom(new InternetAddress(from)); // 送信元メールアドレスを設定
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false)); // 送信先メールアドレスを設定
			msg.setSubject("Mail Send Test"); // メール件名を設定
			msg.setSentDate(new Date()); // メール送信日を設定
			msg.setText(msgText); // メール本文を設定
			Transport.send(msg);
			System.out.println("Done");
		} catch (MessagingException msgex) {

			System.out.println("メール送信テストでエラーが発生しました。");

			msgex.printStackTrace();
		}
	}
}
