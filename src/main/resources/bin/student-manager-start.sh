#!/usr/bin/env bash

SERVER_PATH=$(dirname $0)/..

PID_FILE="student-manager.pid"
SERVER="org.university.stm.start.StudentManagerApplication"

CONF_PATH=${SERVER_PATH}/conf
LIB_PATH=${SERVER_PATH}/lib
LOG_PATH=${SERVER_PATH}/logs
SQL_PATH=${SERVER_PATH}/sql

function INIT_PREPARE()
{
    if [[ ! -d ${LOG_PATH} ]]
    then
        mkdir ${LOG_PATH}
    fi
    rm -f ${LOG_PATH}/gc.log
}

function SERVER_RUNNER()
{
    RUNNER=""
    JAVA_OPTS="-server -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -Xms512M -Xmx4G -XX:PermSize=512m -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:${LOG_PATH}/gc.log"

    if [[ -n "${JAVA_HOME}" ]]
    then
        RUNNER="${JAVA_HOME}/bin/java"
    elif [ "$(which java)" ] && [ $? -eq 0 ]
    then
        RUNNER="java"
    else
        echo "[ERROR] java is invalid"
        exit 1
    fi

    for v_jar in $(find ${LIB_PATH} -name "*.jar" -print)
    do
      HEC_CLASSPATH=${HEC_CLASSPATH}:${v_jar}
    done

    HEC_CLASSPATH=${HEC_CLASSPATH}:${CONF_PATH}:${SQL_PATH}

    nohup ${RUNNER} ${JAVA_OPTS} -cp ${HEC_CLASSPATH} ${SERVER} &

    PID=$!
    echo "Stucent Manager  running in : ${PID}"
    echo ${PID} > ${PID_FILE}
}

INIT_PREPARE
SERVER_RUNNER