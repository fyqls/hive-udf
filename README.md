# (c) Copyright 2013-2015 Transwarp, Inc.
Code Compile:

a)
   $ mvn package

OR

b) $ javac -cp /where/is/your/hive-exec-0.12.0-tdh40.jar:/where/is/your/hadoop-common-2.5.2-transwarp.jar io/transwarp/inceptor/udf/TimeFormat.java
   $ javac -cp /where/is/your/hive-exec-0.12.0-tdh40.jar:/where/is/your/hadoop-common-2.5.2-transwarp.jar io/transwarp/inceptor/udf/Sum.java
   $ cd src/main/java && jar -cf UDF-Sample-<version>.jar  -C . .

Function Register:
  copy the UDF-Sample-<verion>.jar to /tmp in the node of inceptor server. (CAUTION: We choose /tmp to avoid permission problems.)
  $ mv UDF-Sample-<version>.jar /tmp (OR scp /where/is/your/UDF-Sample.jar $USER@$INCEPTOR_SERVER:/tmp)
  Register the function. (CAUTION: the function will be removed after restarting inceptor server, so redo this part is needed.)
  $ transwarp -t -h $INCEPTOR_SERVER
    > add jar /tmp/UDF-Sample-<version>.jar;
    > create temporary function xxx as 'io.transwarp.inceptor.udf.zzz';
    > select xxx($COLUMN_EXPECTED) from dual;

Updating:
When code improved and new jar is ready, Runnung add jar /tmp/UDF-Sample-<version>.jar again will overwrite the old one, but drop the old function is necessary.
    $ transwarp -t -h $INCEPTOR_SERVER
    > add jar /tmp/UDF-Sample-<version>.jar;
    > drop temporary function xxx;
    > create temporary function xxx as 'io.transwarp.inceptor.udf.zzz';
    > select xxx($COLUMN_EXPECTED) from dual;
# hive-udf
