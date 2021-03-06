package com.wzd.utils;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Base64编/解码工具类
 * 
 * @author lujuzhi
 * 
 */
@SuppressWarnings("restriction")
public class Base64Util {

	private static char[] codec_table = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
			'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b',
			'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'+', '/' };

	/**
	 * Base64解码
	 * 
	 * @param content
	 *            字符串
	 * @return String
	 */
	public static String decode(String content) {
		try {
			byte[] b = new BASE64Decoder().decodeBuffer(content);
			return new String(b);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Base64解码
	 * 
	 * @param content
	 *            字符串
	 * @return byte[]
	 */
	public static byte[] decodeToBytes(String content) {
		try {
			return new BASE64Decoder().decodeBuffer(content);
		} catch (IOException e) {
			e.printStackTrace();
			return new byte[] {};
		}
	}

	/**
	 * Base64编码
	 * 
	 * @param content
	 *            字节数组
	 * @return String
	 */
	public static String encode(byte[] content) {
		try {
			return new BASE64Encoder().encode(content);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String encodeForLong(byte[] a) {
		int totalBits = a.length * 8;
		int nn = totalBits % 6;
		int curPos = 0;// process bits
		StringBuffer toReturn = new StringBuffer();
		while (curPos < totalBits) {
			int bytePos = curPos / 8;
			switch (curPos % 8) {
			case 0:
				toReturn.append(codec_table[(a[bytePos] & 0xfc) >> 2]);
				break;
			case 2:

				toReturn.append(codec_table[(a[bytePos] & 0x3f)]);
				break;
			case 4:
				if (bytePos == a.length - 1) {
					toReturn.append(codec_table[((a[bytePos] & 0x0f) << 2) & 0x3f]);
				} else {
					int pos = (((a[bytePos] & 0x0f) << 2) | ((a[bytePos + 1] & 0xc0) >> 6)) & 0x3f;
					toReturn.append(codec_table[pos]);
				}
				break;
			case 6:
				if (bytePos == a.length - 1) {
					toReturn.append(codec_table[((a[bytePos] & 0x03) << 4) & 0x3f]);
				} else {
					int pos = (((a[bytePos] & 0x03) << 4) | ((a[bytePos + 1] & 0xf0) >> 4)) & 0x3f;
					toReturn.append(codec_table[pos]);
				}
				break;
			default:
				// never hanppen
				break;
			}
			curPos += 6;
		}
		if (nn == 2) {
			toReturn.append("==");
		} else if (nn == 4) {
			toReturn.append("=");
		}
		return toReturn.toString();

	}
}
