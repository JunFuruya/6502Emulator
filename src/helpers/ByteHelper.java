package helpers;

public class ByteHelper {
	private static StringBuilder builder = new StringBuilder();
	private static char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	public static String getHexString(byte[] byteData) {
		int len = byteData.length;
		for (int i = 0; i < len; i++) {
			builder.append(chars[(byteData[i] >>> 4) & 0x0F]); // 符号なし4ビット右シフト（上の桁）
			builder.append(chars[byteData[i] & 0x0F]); // 下の桁
		}

		String hexNum = builder.toString();

		// 値を消去する。（お作法）
		builder.setLength(0);

		return hexNum;
	}

	/**
	 * byte と String を比較する
	 * @return
	 */
	public static boolean isSame(byte[] byteData, String text) {
		if (getHexString(byteData).equals(new String(text))) {
			return true;
		}
		return false;
	}
}