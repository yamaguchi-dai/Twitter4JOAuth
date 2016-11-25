package jums;

import java.util.ArrayList;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
/*
 * このクラスにツイッター関係をまとめることで、なんどもコンシューマーキーを書く必要がありません。
 * このクラスがもつメソッドとしては、
 * 1:リクエストトークンを発行するメソッド
 * 2:ログインしてくれたuserの名前を取得メソッド
 */
public class TwitterHelper {

	public TwitterHelper() {
	}

	
	//今回は自分のアクセストークンは使いません(認証してくれた人のものを使うため
	String consumerkey = "";
	String consumersecret = "";
	

	// リクエストトークンの取得
	public RequestToken getRequestToken() {
		try {

			//このtwitterっというのはconsumerkeyで指定されたアプリケーションのことを指します。
			Twitter twitter = new TwitterFactory().getInstance();
			twitter.setOAuthConsumer(consumerkey, consumersecret); 

			//アプリケーションに対してのリクエストトークンを発行
			RequestToken requestToken = twitter.getOAuthRequestToken();
			return requestToken;

		} catch (TwitterException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return null;
	}

	//名前の取得
	public String setApi(RequestToken requestToken, String pin) {
		try {

			Twitter twitter = new TwitterFactory().getInstance();// Twitterオブジェクト作成
			twitter.setOAuthConsumer(consumerkey, consumersecret); // アプリケーションのconsumer
			AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, pin);
			twitter.setOAuthAccessToken(accessToken);// 自分のアクセストークンをTwitterオブジェクトに格

			User user = twitter.verifyCredentials();// Userオブジェクトを作成
			String name = user.getName();
			return name;
		} catch (TwitterException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return null;
	}

}
