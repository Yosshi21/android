/**
* 基底アクティビティ。
*/
public abstract class BaseActivity extends Activity {

	/**
	 *  文字列を取得
	 */
	public String _(String target_string_name) {
		return CommonUtil.getText(target_string_name, this);
	}

	/**
	 *  getTextの型セーフ版
	 */
	public String _(int target_string_id) {
		return CommonUtil.getText(target_string_id, this);
	}
}
