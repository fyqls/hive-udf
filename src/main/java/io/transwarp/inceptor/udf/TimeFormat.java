package io.transwarp.inceptor.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
import java.nio.ByteBuffer; 
/*
 * Get the quarter from a string input.
 * Sample\n: 
 * select _FUNC_(2014-12-05 12:07:47.087) from src limit 1;
 *   2014-12-05 12:0
 * select _FUNC_(2014-12-05 12:47:47.087) from src limit 1;
 *   2014-12-05 12:3
 */
public final class TimeFormat extends UDF {
  private final Text ret;
  public TimeFormat() {
    ret = new Text();
  }
  public Text evaluate(final Text t) {
    if (t == null) {
      return null; 
    }
    ByteBuffer buf = ByteBuffer.wrap(t.getBytes(), 0, t.getLength());
    String tmp = new String(buf.array(), 0, 14);
    ret.set(tmp + Integer.parseInt(new String(buf.array(), 14, 16)) / 15);
    return ret;
  }
}

