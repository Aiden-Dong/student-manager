#!/usr/bin/env bash

PID_FILE="student-manager.pid"

if [[ ! -f ${PID_FILE} ]]
then
  echo "[INFO] server is not start"
  exit 0
fi

kill -sigkill $(cat ${PID_FILE})

rm -f ${PID_FILE}