package io.transwarp.inceptor.udf;
import org.apache.hadoop.hive.ql.exec.UDF;
public class changeIntToIP extends UDF{
  
  public String evaluate(int i){  
    return ((i >> 24) & 0xFF) + "." + ((i >> 16) & 0xFF) + "."  
        + ((i >> 8) & 0xFF) + "." + (i & 0xFF);    
  }

}
