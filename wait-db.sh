#!/bin/bash

HOST=${DB_HOST:-mysql}
PORT=${DB_PORT:-3306}
MAX_WAIT=60
WAIT=0

echo "Waiting for $HOST:$PORT to be available..."

while ! timeout 1 bash -c "</dev/tcp/$HOST/$PORT" 2>/dev/null; do
  sleep 2
  WAIT=$((WAIT + 2))
  if [ "$WAIT" -ge "$MAX_WAIT" ]; then
    echo " Timeout: $HOST:$PORT not reachable after $MAX_WAIT seconds."
    exit 1
  fi
  echo "Still waiting for $HOST:$PORT..."
done

echo "$HOST:$PORT is now reachable."
exec java -jar /app/app.jar
