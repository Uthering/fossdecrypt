import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Main {
	enum CipherMode {
		DECRYPT, ENCRYPT;
	}

	private static String passPhrase = "UGxheWVy";
	private static String initVector = "tu89geji340t89u2";
	public static final Charset workSet = Charset.forName("UTF-8");

	public static void main(String[] args) {
		try {
			String fName = args[0];
			String text = readFile(fName);

			int dwellersCount = 0;
			if (args.length > 1) {
				System.out.println("Add super dwellers up to " + args[1]);
				dwellersCount = Integer.parseInt(args[1]);
			}
			String content = null;

			if (Base64.isArrayByteBase64(text.getBytes())) {
				String json = cipher2string(text, CipherMode.DECRYPT);

				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				JsonObject obj = gson.fromJson(json, JsonObject.class);

				DwellerHelper.listDwellers(obj);

				fName = setExtension(fName, "js");
				content = gson.toJson(obj);
			} else {
				Gson gson = new GsonBuilder().create();
				JsonObject obj = gson.fromJson(text, JsonObject.class);

				try {
					DwellerHelper.listDwellers(obj);
					DwellerHelper.updateDwellersStats(obj, 10);
					DwellerHelper.addItemsForDwellers(obj, new EquipWeapon("Fatman_Mirv"),
							new EquipOutfit("PowerArmor_MkVI"));
					if (dwellersCount > 0) {
						DwellerHelper.addUberDwellers(obj, dwellersCount);
					}
					DwellerHelper.listDwellers(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}

				fName = "new." + setExtension(fName, "sav");
				content = textWrap(cipher2string(gson.toJson(obj), CipherMode.ENCRYPT), 76, '\n');
			}
			if (null != content) {
				saveFile(fName, content);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	public static String textWrap(String txt, int border, char sep) {
		StringBuilder sb = new StringBuilder();
		int len = txt.length();
		int idx = 0;
		while (idx < len) {
			if (idx > 0) {
				sb.append(sep);
			}
			if (idx + border > len) {
				sb.append(txt.substring(idx));
			} else {
				sb.append(txt.substring(idx, idx + border));
			}
			idx += border;
		}
		return sb.toString();
	}

	public static String setExtension(String fName, String newExt) {
		if (!newExt.startsWith(".")) {
			newExt = "." + newExt;
		}
		if (!fName.endsWith(newExt)) {
			int dot = fName.lastIndexOf('.');
			if (dot >= 0) {
				fName = fName.substring(0, dot) + newExt;
			} else {
				fName += newExt;
			}
		}
		return fName;
	}

	private static String cipher2string(String text, CipherMode mode) {
		SecretKeyFactory factory = null;
		try {
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

			PBEKeySpec pbeKeySpec = null;
			try {
				pbeKeySpec = new PBEKeySpec(passPhrase.toCharArray(), initVector.getBytes("ASCII"), 1000, 384);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			Key secretKey = factory.generateSecret(pbeKeySpec);
			byte[] key = new byte[32];
			byte[] iv = new byte[16];
			System.arraycopy(secretKey.getEncoded(), 0, key, 0, 32);
			System.arraycopy(secretKey.getEncoded(), 32, iv, 0, 16);

			SecretKeySpec secret = new SecretKeySpec(key, "AES");
			AlgorithmParameterSpec ivSpec = new IvParameterSpec(initVector.getBytes());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			String data;
			switch (mode) {
			case DECRYPT:
				System.out.println("Decrypting data...");
				cipher.init(Cipher.DECRYPT_MODE, secret, ivSpec);
				byte[] decodedValue = new byte[0];
				decodedValue = Base64.decodeBase64(text.getBytes(workSet));
				byte[] decryptedVal = cipher.doFinal(decodedValue);
				data = new String(decryptedVal, workSet);
				return data;
			case ENCRYPT:
				System.out.println("Encrypting data...");
				cipher.init(Cipher.ENCRYPT_MODE, secret, ivSpec);
				byte[] result = new byte[0];
				result = cipher.doFinal(text.getBytes(workSet));
				data = Base64.encodeBase64String(result);
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String readFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, workSet);
	}

	private static void saveFile(String path, String content) throws IOException {
		File file = new File(path);
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file, false), workSet);
		writer.write(content);
		writer.close();
		file = null;
	}
}
