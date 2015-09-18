package eu.artemis.demanes.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

/**
 * LogEntry
 *
 * @author leeuwencjv
 * @version 0.1
 * @since 3 feb. 2015
 *
 */
public class LogEntry {

	private final int level;

	private final String message;

	private final String tag;

	private final String source;

	private final Throwable exception;

	private final long time;

	public LogEntry(long time, String source, int level, String tag,
			String message, Throwable t) {
		this.level = level;
		this.message = message;
		this.tag = tag;
		this.source = source;
		this.exception = t;
		this.time = time;
	}

	public LogEntry(long time, String source, int level, String message,
			Throwable t) {
		this(time, source, level, null, message, t);
	}

	public LogEntry(String source, int level, String tag, String message,
			Throwable t) {
		this(System.currentTimeMillis(), source, level, null, message, t);
	}

	public LogEntry(String source, int level, String tag, String message) {
		this(System.currentTimeMillis(), source, level, tag, message, null);
	}

	public LogEntry(String source, int level, String message, Throwable t) {
		this(System.currentTimeMillis(), source, level, null, message, t);
	}

	public LogEntry(String source, int level, String message) {
		this(System.currentTimeMillis(), source, level, null, message, null);
	}

	public String getSource() {
		return source;
	}

	public Throwable getException() {
		return exception;
	}

	public int getLevel() {
		return level;
	}

	public String getTag() {
		return tag;
	}

	public String getMessage() {
		return message;
	}

	public long getTime() {
		return time;
	}

	@Override
	public String toString() {
		String strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS")
				.format(this.getTime());
		StringBuilder sb = new StringBuilder("[").append(strDate).append("] ");
		if (this.getSource() == null) {
			sb.append("- [undefined] ");
		} else {
			sb.append("- [").append(this.getSource()).append("] ");
		}
		if (this.getTag() != null) {
			sb.append("- <").append(this.getTag()).append("> ");
		}
		sb.append("- ").append(this.getMessage());
		if (this.getException() != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			this.getException().printStackTrace(pw);
			sb.append("\n").append(sw.toString());
		}
		return sb.toString();
	}

}
