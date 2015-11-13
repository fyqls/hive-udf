package io.transwarp.inceptor.udf;

/**
 * Created by qls on 9/30/15.
 */

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;



public final class UrlFormat extends UDF {

    private final Text ret;

    public UrlFormat() {

        ret = new Text();

    }



    public Text evaluate(final Text t) {

        if (t == null) {

            return null;

        }



        String srcUrl = t.toString().toLowerCase();

        String tmp = srcUrl;

        if (srcUrl.contains("://")) {

            tmp = srcUrl.substring(srcUrl.indexOf("://") + 3);

        }



        String destUrl = tmp;

        if (tmp.contains("/")) {

            destUrl = tmp.substring(0, tmp.indexOf("/"));

        }



        ret.set(destUrl);

        return ret;

    }

}