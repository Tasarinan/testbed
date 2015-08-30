package vote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicNameValuePair;

public class Vote {

	private static String VOTE = "http://drift.chinaswt.cn/handler/doweb.ashx";
	// private static String VOTE =
	// "https://www.google.com/accounts/ClientLogin";
	private static String[] openIds = { "oh4J3t7aLRAlyu_-YqNwKqy2uQ60",
			"oh4J3tyz2f9d5TVghAGkelJ5_D_I", "oh4J3t24zKmVknyG2LJCUVleMML0",
			"oh4J3t-2HZKrpLvuBYi1VmTQF-RA", "oh4J3tyZv9UE0vvi6eo3q5NXYs9Q",
			"oh4J3txDVzbcOZlHzOrGvKd6fdbk", "oh4J3t0i0HTOVt2vNbo711zVcqkg",
			"oh4J3tyud5F_KzVvwS3CbHwD8N5g", "oh4J3t8fRSxsiolCfry_vnrvK_do",
			"oh4J3t6ghcq80b1cc2ZXSUeRK7Gs", "oh4J3tx3Sj92EJaS0DAEsh3ZRlRw",
			"oh4J3t5oXv40YVyN8gax-yS1ZuoA", "oh4J3t7Agkt7JXvEIlRUJfDk8OMU",
			"oh4J3t_Bnrn4YWcKAvpZ2U1PWKmc", "oh4J3t6R0UvxHRnRxNRXbOCsUujo",
			"oh4J3t1t7S04dx7F-DxNNKXkqsVU", "oh4J3t5QHMJXmDtW3-hko4m66P5Q",
			"oh4J3t9rB7vTuooN6jMPa3W7hQHo", "oh4J3t7PF5R8aT7gIxiItbPBdYs4",
			"oh4J3t9ZmsS9QMZDj3gcIvG06Sfs", "oh4J3t8oDuXsiQdXQfznZdQfkCYI",
			"oh4J3t7Tw7z8ioZjURUs-JVETD9Q", "oh4J3t8AA0m-YWWckWIGHWBzYdH0",
			"oh4J3t7DAqv8ZcQ-i2X85Tz6vdY0", "oh4J3t0i0HTOVt2vNbo711zVcqkg",
			"oh4J3t8TPhrKtdspXTIjZzDu9BS4", "oh4J3t7QDhEHSLCJCmO-LIZ9XEmk",
			"oh4J3tzhUcxldGrle-dvvZ3YGUfc", "oh4J3t79Wfe3tS2DVeDt969CjFjc",
			"oh4J3tzT_iKh_h4TgvYRWj1iWKeI", "oh4J3tzMD50Rnqv252jmwmIXhk2k",
			"oh4J3t5RpbiqlhC1_y8iX1QLc49Q", "oh4J3t3uZj_p-PVS2yNDACJolHsk",
			"oh4J3t_4clk3Vsy7_fC4jDEBI2Jk", "oh4J3t7Azuv4hfcsnmi-yDK1htXo",
			"oh4J3t7aLRAlyu_-YqNwKqy2uQ60", "oh4J3tz0ETJkUBV-2wve67I4DHpY",
			"oh4J3t6qhSEB2HicSFC6DU1EWCSc", "oh4J3t3VrThf-C7JvgJ6UVWOx0C0",
			"oh4J3txG9siMiiI-8AqzdEuAQuU8", "oh4J3t3SIjq6t5oc6wsSAlLbWr1c",
			"oh4J3t8fRSxsiolCfry_vnrvK_do", "oh4J3tyud5F_KzVvwS3CbHwD8N5g",
			"oh4J3t7bEBO9o2DVidOBCoV-HLys", "oh4J3t0rro6xgLRyEW_W-fegYWDE",
			"oh4J3txDVzbcOZlHzOrGvKd6fdbk", "oh4J3t_GvNJrq-unIjim0XLDW7P0",
			"oh4J3t2j92ZqociZxlKbN1wWwMQ8", "oh4J3t78cw8NAr6aF1G5EfUK2ThI",
			"oh4J3t-KgZ8Cz7DBqnP0YRUa32nM", "oh4J3t_8e1AuAUlWE8xMX_bT1h_I",
			"oh4J3t7REoW00sX8cCVt0MaVp1Zo", "oh4J3t38u88w7P46JcHsKsc5STtM",
			"oh4J3t9uyF-eUGJblmY7N_EnFFOY", "oh4J3t0kCFGfB3eeLx4OggVK2xGo",
			"oh4J3tx3Sj92EJaS0DAEsh3ZRlRw", "oh4J3twBXq_Gb610OuhVPMTyrAFQ",
			"oh4J3t0_h19RICUmFEndKYfdqn-E", "oh4J3t7dgV2V7GI8sy6n5nypWm5k",
			"oh4J3t24zKmVknyG2LJCUVleMML0", "oh4J3t-3aQYULYGcRfE0a3CbkkPo",
			"oh4J3t1EOAPMIpd-MJ1dPHDFGKQo", "oh4J3t8J5_DBenKHWsXSvg1tF20E",
			"oh4J3tx3Sj92EJaS0DAEsh3ZRlRw", };

	private static List<String> list = new ArrayList<String>();

	/**
	 * @param args
	 * @throws IOException
	 * @throws HttpException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws HttpException, IOException,
			InterruptedException {

		parse();
		// TODO Auto-generated method stub
		int count = 0;
		// for (int i = 0; i < count; i++) {

		int i = 1;
		// int j = 0;
		/*
		 * for (j = 0; j < openIds.length; j++) { i = 1; while (true) {
		 * System.out.println("==>  第 " + i++ + " 次投票."); if (false ==
		 * vote(openIds[j])) break; if (i > 11) break; Thread.sleep(10000); } }
		 */
		//for (String ls : list) {
			// System.out.println(ls);
			while(true)
			{
				String uid = generateuid();
				i = 0;
			while (true) {

				if (false == vote(uid))
					break;
				if (i > 11)
					break;
				System.out.println("==>  第 " + count++ + " 次投票.");
				// Thread.sleep(1000);
			}
			}
		//}

	}

	private static void parse() {

		File file = new File("d:\\temp\\1.txt");
		boolean fileExists = file.exists();
		if (true == fileExists) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(
						"d:\\temp\\1.txt"));
				String str;
				while ((str = in.readLine()) != null) {
					String[] temp = str.split(",");
					for (int i = 0; i < temp.length; i++) {
						String[] temp2 = temp[i].split(":");
						for (int j = 0; j < temp2.length; j++) {
							if (temp2[j].startsWith("\"oh4J")) {
								String temp3 = temp2[j].substring(1, 29);
								// System.out.println(temp3);
								if (!list.contains(temp3)) {
									// System.out.println(temp3);
									list.add(temp3);
								}
							}
						}
					}
				}
				// System.out.println(str);
			} catch (IOException e) {
			}
		}
		// list = Arrays.asList(openIds);
		for (int j = 0; j < openIds.length; j++) {
			if (!list.contains(openIds[j])) {
				// System.out.println(temp3);
				list.add(openIds[j]);
			}
		}
	}

	private static void test() {

		try {
			HttpGet request = new HttpGet("http://www.vogella.com");
			HttpHost proxy = new HttpHost("87.254.212.120", 8080);
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(
					proxy);
			CloseableHttpClient client = HttpClients.custom()
					.setRoutePlanner(routePlanner).build();
			HttpResponse response = client.execute(request);
			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				System.out.print(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean vote(String openId) throws IOException,
			HttpException, UnsupportedEncodingException {

		HttpHost proxy = new HttpHost("10.144.1.10", 8080);
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(
				proxy);
		//CloseableHttpClient client = HttpClients.custom()
		//		.setRoutePlanner(routePlanner).build();
		 CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(VOTE);
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("action", "voteeyes"));
			// nameValuePairs.add(new BasicNameValuePair("openid",
			// "oh4J3t7HxU_t-gabKLX_3OLGt1-M"));
			// nameValuePairs.add(new BasicNameValuePair("openid",
			// "oh4J3tx3Sj92EJaS0DAEsh3ZRlRw"));
			nameValuePairs.add(new BasicNameValuePair("openid", openId));
			nameValuePairs.add(new BasicNameValuePair("shareopenid",
					"oh4J3t-3aQYULYGcRfE0a3CbkkPo"));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {

				if (line.equalsIgnoreCase("{\"code\":2}")) {
					String key = line.substring(5);
					// System.out.println("break" + line);
					return false;
				}
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static String generateuid() {

		UUID uuid = UUID.randomUUID();

		byte[] uuidArr = asByteArray(uuid);

		
			// Convert a byte array to base64 string
			String s = Base64.encodeBase64String(uuidArr);

			String trimmed = s.split("=")[0];
            return "oh4J3t"+trimmed;
	}

	public static byte[] asByteArray(UUID uuid) {

		long msb = uuid.getMostSignificantBits();
		long lsb = uuid.getLeastSignificantBits();
		byte[] buffer = new byte[16];

		for (int i = 0; i < 8; i++) {
			buffer[i] = (byte) (msb >>> 8 * (7 - i));
		}
		for (int i = 8; i < 16; i++) {
			buffer[i] = (byte) (lsb >>> 8 * (7 - i));
		}

		return buffer;

	}

	public static UUID toUUID(byte[] byteArray) {

		long msb = 0;
		long lsb = 0;
		for (int i = 0; i < 8; i++)
			msb = (msb << 8) | (byteArray[i] & 0xff);
		for (int i = 8; i < 16; i++)
			lsb = (lsb << 8) | (byteArray[i] & 0xff);
		UUID result = new UUID(msb, lsb);

		return result;
	}
}
