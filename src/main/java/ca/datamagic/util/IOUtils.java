/**
 * 
 */
package ca.datamagic.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

/**
 * @author Greg
 *
 */
public final class IOUtils {
	private static final Logger logger = Logger.getLogger(IOUtils.class.getName());
	private static final int BUFFER_SIZE = 4096;
    
	public static InputStream getRequestStream(String urlSpec) throws IOException {
		URL url = new URL(urlSpec);
		HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(false);
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(2000);
        connection.setRequestProperty("accept", "image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8");
        connection.setRequestProperty("accept-encoding", "gzip, deflate, br");
        connection.setRequestProperty("accept-language", "en-US,en;q=0.9");
        connection.setRequestProperty("sec-ch-ua", "\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"");
        connection.setRequestProperty("sec-ch-ua-mobile", "?0");
        connection.setRequestProperty("sec-fetch-dest", "image");
        connection.setRequestProperty("sec-fetch-mode", "no-cors");
        connection.setRequestProperty("sec-fetch-site", "cross-site");
        connection.setRequestProperty("User-Agent", "(datamagic.ca,gregorymaggio@gmail.com)");
        connection.connect();
        return connection.getInputStream();
	}
	
	public static String readEntireStream(InputStream inputStream) throws IOException {
        StringBuffer textBuffer = new StringBuffer();
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = 0;
        while ((bytesRead = inputStream.read(buffer, 0, buffer.length)) > 0) {
            textBuffer.append(new String(buffer, 0, bytesRead));
        }
        return textBuffer.toString();
    }
	
	public static byte[] readEntireByteArray(InputStream inputStream) throws IOException {
        List<Byte> list = new ArrayList<Byte>();
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = 0;
        while ((bytesRead = inputStream.read(buffer, 0, buffer.length)) > 0) {
            for (int ii = 0; ii < bytesRead; ii++) {
                list.add(buffer[ii]);
            }
        }
        buffer = new byte[list.size()];
        for (int ii = 0; ii < list.size(); ii++) {
            buffer[ii] = list.get(ii).byteValue();
        }
        return buffer;
    }
	
	public static void writeEntireByteArray(byte[] bytes, String fileName) throws IOException {
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(fileName);
			outputStream.write(bytes);
		} finally {
			if (outputStream != null) {
				closeQuietly(outputStream);
			}
		}
	}
	
	public static byte[] readEntireByteArray(String fileName) throws IOException {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(fileName);
			return readEntireByteArray(inputStream);
		} finally {
			if (inputStream != null) {
				closeQuietly(inputStream);
			}
		}
	}
	
	public static void closeQuietly(InputStream inputStream) {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Throwable t) {
            logger.warning(t.getMessage());
        }
    }

    public static void closeQuietly(OutputStream outputStream) {
        try {
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Throwable t) {
            logger.warning(t.getMessage());
        }
    }
    
    public static void closeQuietly(HttpsURLConnection connection) {
    	try {
            if (connection != null) {
            	connection.disconnect();
            }
        } catch (Throwable t) {
            logger.warning(t.getMessage());
        }
    }
}
