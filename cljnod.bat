@echo off
:: palisades.lakes (at) gmail (dot) com
:: 2017-10-09

::set GC=-XX:+AggressiveHeap -XX:+UseStringDeduplication 
set GC=

set COMPRESSED=
::set COMPRESSED=-XX:CompressedClassSpaceSize=3g 

set TRACE=
::set TRACE=-XX:+PrintGCDetails -XX:+TraceClassUnloading -XX:+TraceClassLoading

set PROF=
::set PROF=-Xrunhprof:cpu=samples,depth=128,thread=y,doe=y
set DISS=-javaagent:"lib/nodisassemble-0.1.3.jar"

::set THRUPUT=-d64 -server -XX:+AggressiveOpts 
set THRUPUT=-d64 -server
::set THRUPUT=

::set XMX=-Xms29g -Xmx29g -Xmn11g 
set XMX=-Xms12g -Xmx12g -Xmn5g 

set OPENS=--add-opens java.base/java.lang=ALL-UNNAMED
set CP=-cp ./src/scripts/clojure;lib/*

set JAVA_HOME=C:\Program Files\Java\jdk-9
set JAVA="%JAVA_HOME%\bin\java"

set CMD=%JAVA% %THRUPUT% -ea -dsa -Xbatch %DISS% %GC% %PROF% %XMX% %COMPRESSED% %TRACE% %OPENS% %CP% clojure.main %*
echo %CMD%
%CMD%
