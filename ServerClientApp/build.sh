#!/usr/bin/env bash
# build.sh – Compile all sources into out/
set -e
SRC_DIR="$(dirname "$0")/src"
OUT_DIR="$(dirname "$0")/out"

mkdir -p "$OUT_DIR"
javac -d "$OUT_DIR" \
  "$SRC_DIR/common/Message.java" \
  "$SRC_DIR/server/Server.java" \
  "$SRC_DIR/server/ClientHandler.java" \
  "$SRC_DIR/client/Client.java" \
  "$SRC_DIR/ServerMain.java" \
  "$SRC_DIR/ClientMain.java"

echo "Build successful – class files written to $OUT_DIR"
