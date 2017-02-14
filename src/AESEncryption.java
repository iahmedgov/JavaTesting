import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher; 
import javax.crypto.spec.IvParameterSpec; 
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

//import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException; 

public class AESEncryption { 

	private static final String CIPHER_TRANSFORM = "AES/CBC/PKCS5Padding";

	//Sample Java Code for Encryption

	public static String encrypt(final String plainMessage, final byte[] symKeyData) {
		final byte[] encodedMessage = plainMessage.getBytes(Charset.forName("UTF8"));
		try {
			final Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORM);
			final int blockSize = cipher.getBlockSize();
			// create the key
			final SecretKeySpec symKey = new SecretKeySpec(symKeyData, "AES");
			// generate random IV using block size
			final byte[] ivData = new byte[blockSize];
			final SecureRandom rnd = SecureRandom.getInstance("SHA1PRNG");
			rnd.nextBytes(ivData);
			final IvParameterSpec iv = new IvParameterSpec(ivData);
			cipher.init(Cipher.ENCRYPT_MODE, symKey, iv);
			final byte[] encryptedMessage = cipher.doFinal(encodedMessage);
			// concatenate IV and encrypted message
			final byte[] ivAndEncryptedMessage = new byte[ivData.length + encryptedMessage.length];
			System.arraycopy(ivData, 0, ivAndEncryptedMessage, 0, blockSize);
			System.arraycopy(encryptedMessage, 0, ivAndEncryptedMessage, blockSize,
					encryptedMessage.length);
			final String ivAndEncrypted = DatatypeConverter.printHexBinary(ivAndEncryptedMessage);
			return ivAndEncrypted;
		} catch (InvalidKeyException e) {
			throw new IllegalArgumentException("key argument does not contain a valid AES key");
		} catch (GeneralSecurityException e) {
			throw new IllegalStateException("Unexpected exception during encryption", e);
		}
	}

	//Sample Java Code for Decryption

	public static String decrypt(final String ivAndEncryptedMessageHex, final byte[] symKeyData) {
		final byte[] ivAndEncryptedMessage =
				DatatypeConverter.parseHexBinary(ivAndEncryptedMessageHex);
		try {
			final Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORM);
			final int blockSize = cipher.getBlockSize();
			// create the key
			final SecretKeySpec symKey = new SecretKeySpec(symKeyData, "AES");
			// retrieve random IV from start of the received message
			final byte[] ivData = new byte[blockSize];
			System.arraycopy(ivAndEncryptedMessage, 0, ivData, 0, blockSize);
			final IvParameterSpec iv = new IvParameterSpec(ivData);
			// retrieve the encrypted message itself
			final byte[] encryptedMessage = new byte[ivAndEncryptedMessage.length-blockSize];
			System.arraycopy(ivAndEncryptedMessage, blockSize, encryptedMessage, 0,	encryptedMessage.length);
			cipher.init(Cipher.DECRYPT_MODE, symKey, iv);
			final byte[] encodedMessage = cipher.doFinal(encryptedMessage);
			// concatenate IV and encrypted message
			final String message = new String(encodedMessage, Charset.forName("UTF8"));
			return message;
		} catch (InvalidKeyException e) {
			throw new IllegalArgumentException("key argument does not contain a valid AES key");
		} catch (BadPaddingException e) {
			return null;
		} catch (GeneralSecurityException e) {
			throw new IllegalStateException("Unexpected exception during decryption", e);
		}
	}

	//How Key was Created
	public static String generateNewRandomKey() throws NoSuchAlgorithmException {
		SecureRandom rnd = SecureRandom.getInstance("SHA1PRNG");
		byte[] bytes = new byte[16];
		rnd.nextBytes(bytes);		
		return DatatypeConverter.printHexBinary(bytes);
	}
	//How Key is Read
	public static byte[] readKeyfile(String keyfile) throws FileNotFoundException, IOException {
		//byte[] keyBytes = FileUtils.readFileToByteArray(new File("C:/tmp" + "/" + keyfile));
		byte[] keyBytes = keyfile.getBytes();

		return DatatypeConverter.parseHexBinary(new String(keyBytes));
	}	




	public static void main(String[] args) throws IOException, IOException, NoSuchAlgorithmException {

		String payLoad ="{\r\n" + 
				"	\"CP_ID\": \"594\",\r\n" + 
				"	\"CP_MKey\": \"3oqVwXEstHKx9AD2ufhvJvDLNQ2D7rdPjQNd2sNxhy0=\",\r\n" + 
				"	\"Clerk_Key\": \"9fIWvAe58MxnDSKeBjSpmzgvdSsFyb3b4Tmn3XbxFIs=\",\r\n" + 
				"	\"TPSID\": \"123456\",\r\n" + 
				"	\"PATIENT_ID\": \"123456\",\r\n" + 
				"	\"LastName\": \"Smith\",\r\n" + 
				"	\"FirstName\": \"John\",\r\n" + 
				"	\"Address\": \"123 Main Street\",\r\n" + 
				"	\"City\": \"Arlington\",\r\n" + 
				"	\"State\": \"VA\",\r\n" + 
				"	\"Zip\": \"22201\",\r\n" + 
				"	\"Phone\": \"703-894-5000\",\r\n" + 
				"	\"Phone2\": \"703-894-5001\",\r\n" + 
				"	\"Email\": \"johnsmith@email.com\",\r\n" + 
				"	\"DateTime\": \"1481235147\",\r\n" + 
				"	\"Claim_ID\": \"345678\",\r\n" + 
				"	\"tags\": [\"POS\"],\r\n" + 
				"	\"Transaction_amount\": \"990.00\",\r\n" + 
				"	\"transactions\": [{\r\n" + 
				"		\"transactionId\": \"1\",\r\n" + 
				"		\"units\": \"2\",\r\n" + 
				"		\"cpt\": \"CH0001\",\r\n" + 
				"		\"tax\": \".08\",\r\n" + 
				"		\"discount\": \".10\",\r\n" + 
				"		\"shortDesc\": \"test\",\r\n" + 
				"		\"longDesc\": \"test long\",\r\n" + 
				"		\"price\": \"990.00\",\r\n" + 
				"		\"total\": \"990.00\"\r\n" + 
				"	}]\r\n" + 
				"}\r\n" + 
				"";

		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().disableHtmlEscaping()
				.registerTypeAdapter(Double.class, new BadDoubleDeserializer()).create();
		VericlePayload vLoad = new VericlePayload();		  


		String key =	"E341182A00BAD968B8B3FBFAC7D7032D";	       

		String encryptMessage = encrypt(payLoad,readKeyfile(key) );
		System.out.println(encryptMessage  );	 


		try {
			String decryptMessage = AESEncryption.decrypt(encryptMessage,AESEncryption.readKeyfile(key) );
			System.out.println(decryptMessage);	
			vLoad = gson.fromJson(decryptMessage, VericlePayload.class);	        			
			System.out.println("vLoad toJSON()   \n" + gson.toJson(vLoad));

			System.out.println("vLoad tags()   \n" + vLoad.getTags().get(0));
			System.out.println("vLoad transactions()   \n" + gson.toJson(vLoad.getTransactions()));

		} catch(JsonParseException e){
			e.printStackTrace();
		}

		//generating a new key for every encrypt and decrypt routine

		//FileUtils.writeStringToFile(new File("C:/tmp/example_key.hex"), generateNewRandomKey() );	        

	}

	public static class BadDoubleDeserializer implements JsonDeserializer<Double> {

		@Override
		public Double deserialize(JsonElement element, Type type, JsonDeserializationContext context)
				throws JsonParseException {
			try {
				System.out.println("Inside BadDoubleDeserializer ");
				System.out.println("element.getAsString() " + element.getAsString());
				System.out.println("parseDouble " + Double.parseDouble(element.getAsString().replace("", "0.00")));
				return Double.parseDouble(element.getAsString().replace("", "0.00"));
			} catch (NumberFormatException e) {
				throw new JsonParseException(e);
			}
		}

	}

}