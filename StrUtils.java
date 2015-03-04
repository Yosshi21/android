/**
 * Utilsクラス
 *
 */
public class StrUtils {

	/**
	 *  getText風に文字列を取得。
	 *  getResources().getString(R.string.someString) の
	 *  シンタックスシュガー
	 */
	public static String getText(String target_string_name,
		Activity activity_instance) {
		// リソースIDを取得
		int target_string_id = activity_instance
			.getResources()
			.getIdentifier(
				target_string_name,
				"string",
				activity_instance.getPackageName()
			);

		// リソースから取得
		String target_string = getText(target_string_id, 
			activity_instance);

		return target_string;
	}

	/**
	 * 上記メソッドを型セーフにしたもの
	 */
	public static String getText(int target_string_id, 
		Activity activity_instance) {
		// リソースから取得
		String target_string = activity_instance
			.getResources()
			.getString( target_string_id )
		;

		return target_string;
	}
}
