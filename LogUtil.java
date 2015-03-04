public class LogUtil {
	/** デバック用 */
	private static final String TAG = "LOG";

	/** ログレベル VERBOSE */
	public static final int VERBOSE = android.util.Log.VERBOSE;
	/** ログレベル DEBUG */
	public static final int DEBUG = android.util.Log.DEBUG;
	/** ログレベル INFO */
	public static final int INFO = android.util.Log.INFO;
	/** ログレベル WARN */
	public static final int WARN = android.util.Log.WARN;
	/** ログレベル ERROR */
	public static final int ERROR = android.util.Log.ERROR;

	/** 出力先フラグ {@link android.util.Log} */
	private static boolean OUTPUT_LOG = true;

	/**
	 * 出力先設定(Log)
	 *
	 * @param output true:有効、 false:無効
	 */
	public static void setOutputLog(boolean output) {
		OUTPUT_LOG = output;
	}

	/**
	 * ログ出力
	 */
	public static void v() {
		if (OUTPUT_LOG) {
			outputLog(getStackTrace(), VERBOSE, "");
		}
	}

	/**
	 * ログ出力
	 *
	 * @param msg メッセージ
	 */
	public static void v(String msg) {
		if (OUTPUT_LOG) {
			outputLog(getStackTrace(), VERBOSE, msg);
		}
	}

	/**
	 * ログ出力
	 *
	 * @param msg メッセージ
	 * @param tr 例外
	 */
	public static void v(String msg, Throwable tr) {
		if (OUTPUT_LOG) {
			outputLog(getStackTrace(), VERBOSE, msg, tr);
		}
	}

	/**
	 * ログ出力
	 */
	public static void d() {
		if (OUTPUT_LOG) {
			outputLog(getStackTrace(), DEBUG, "");
		}
	}

	/**
	 * ログ出力
	 *
	 * @param msg メッセージ
	 */
	public static void d(String msg) {
		if (OUTPUT_LOG) {
			outputLog(getStackTrace(), DEBUG, msg);
		}
	}

	/**
	 * ログ出力
	 *
	 * @param msg メッセージ
	 * @param tr 例外
	 */
	public static void d(String msg, Throwable tr) {
		if (OUTPUT_LOG) {
			outputLog(getStackTrace(), DEBUG, msg, tr);
		}
	}

	/**
	 * ログ出力
	 */
	public static void i() {
		if (OUTPUT_LOG) {
			outputLog(getStackTrace(), INFO, "");
		}
	}

	/**
	 * ログ出力
	 *
	 * @param msg メッセージ
	 */
	public static void i(String msg) {
		if (OUTPUT_LOG) {
			outputLog(getStackTrace(), INFO, msg);
		}
	}

	/**
	 * ログ出力
	 *
	 * @param msg メッセージ
	 * @param tr 例外
	 */
	public static void i(String msg, Throwable tr) {
		if (OUTPUT_LOG) {
			outputLog(getStackTrace(), INFO, msg, tr);
		}
	}

	/**
	 * ログ出力
	 */
	public static void w() {
		if (OUTPUT_LOG) {
			outputLog(getStackTrace(), WARN, "");
		}
	}

	/**
	 * ログ出力
	 *
	 * @param msg メッセージ
	 */
	public static void w(String msg) {
		if (OUTPUT_LOG) {
			outputLog(getStackTrace(), WARN, msg);
		}
	}

	/**
	 * ログ出力
	 *
	 * @param msg メッセージ
	 * @param tr 例外
	 */
	public static void w(String msg, Throwable tr) {
		if (OUTPUT_LOG) {
			outputLog(getStackTrace(), WARN, msg, tr);
		}
	}

	/**
	 * ログ出力
	 */
	public static void e() {
		if (OUTPUT_LOG) {
			outputLog(getStackTrace(), ERROR, "");
		}
	}

	/**
	 * ログ出力
	 *
	 * @param msg メッセージ
	 */
	public static void e(String msg) {
		if (OUTPUT_LOG) {
			outputLog(getStackTrace(), ERROR, msg);
		}
	}

	/**
	 * ログ出力
	 *
	 * @param msg メッセージ
	 * @param tr 例外
	 */
	public static void e(String msg, Throwable tr) {
		if (OUTPUT_LOG) {
			outputLog(getStackTrace(), ERROR, msg, tr);
		}
	}

	/**
	 * スタックトレースを取得
	 *
	 * @param tr 例外
	 * @return スタックトレース
	 */
	public static String getStackTraceString(Throwable tr) {
		return android.util.Log.getStackTraceString(tr);
	}

	/**
	 * ログ出力(スタックトレース付き)
	 *
	 * @param traceTag タグ
	 * @param level ログレベル
	 * @param msg メッセージ
	 * @param tr 例外
	 */
	private static void outputLog(StackTraceElement traceTag, int level, String msg, Throwable tr) {
		StringBuilder builder = new StringBuilder();

		// 1行目メッセージ表示
		if (!TextUtils.isEmpty(msg)) {
			outputLog(traceTag, level, msg);
		}

		// 2行目例外クラス名表示
		builder.append(tr.getClass().getName()).append(" ").append(tr.getMessage());
		outputLog(traceTag, level, builder.toString());

		// 3行目例外メッセージ表示
		StackTraceElement[] ele = tr.getStackTrace();
		for (int i = 0; i < ele.length; i++) {
			builder.setLength(0);
			builder.append("  at ").append(ele[i].toString());
			outputLog(traceTag, level, builder.toString());
		}
	}

	/**
	 * ログ出力 (書式:YYYY/MM/DD HH:MM:SS, [LogLevel], <<ClassName#MethodName:linenumber>>, LogMsg)
	 *
	 * @param tag タグ
	 * @param level ログレベル
	 * @param msg メッセージ
	 */
	@SuppressLint("DefaultLocale")
	private static void outputLog(StackTraceElement tag, int level, String msg) {
		StringBuffer buffer = new StringBuffer();
		String info = null;

		// 1つ上の階層の情報(ファイル名:行数), メソッド名
		buffer.setLength(0);
		String fullName = tag.getClassName();
		String className = fullName.substring(fullName.lastIndexOf(".") + 1);
		String methodName = tag.getMethodName();
		int lineNumber = tag.getLineNumber();
		info = buffer.append("<<").append(className).append("#").append(methodName).append(":").append(lineNumber).append(">> ").toString();

		// android.util.Logへ出力
		buffer.setLength(0);
		Log.println(level, TAG, buffer.append(info).append(msg).toString());
	}

	/**
	 * スタックトレースから呼び出し元の基本情報を取得。
	 *
	 * @return StackTraceElement
	 */
	private static StackTraceElement getStackTrace() {
		// 現在のスタックトレースを取得。
		// 0:VM 1:スレッド 2:getStackTraceInfo() 3:outputLog() 4:logDebug()等 5:呼び出し元
		return Thread.currentThread().getStackTrace()[5];
	}
}
