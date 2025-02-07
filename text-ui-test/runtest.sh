#!/usr/bin/env bash

# Get the directory of the script (text-ui-test/)
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

# Navigate to the project root
PROJECT_ROOT="$SCRIPT_DIR/.."

# Define paths relative to the project root
BIN_DIR="$PROJECT_ROOT/bin"
MAVIS_TXT="$PROJECT_ROOT/src/main/data/Mavis.txt"
SRC_DIR="$PROJECT_ROOT/src/main/java"
JAVA_BIN="$BIN_DIR"
INPUT_FILE="$SCRIPT_DIR/input.txt"
EXPECTED_FILE="$SCRIPT_DIR/EXPECTED.TXT"
ACTUAL_FILE="$SCRIPT_DIR/ACTUAL.TXT"
EXPECTED_UNIX_FILE="$SCRIPT_DIR/EXPECTED-UNIX.TXT"

# Create bin directory if it doesn't exist
if [ ! -d "$BIN_DIR" ]; then
    mkdir "$BIN_DIR"
fi

# Delete previous output
if [ -e "$ACTUAL_FILE" ]; then
    rm "$ACTUAL_FILE"
fi

# Reset Mavis.txt
if [ -e "$MAVIS_TXT" ]; then
    rm "$MAVIS_TXT"
fi
touch "$MAVIS_TXT"
chmod 644 "$MAVIS_TXT"

# Compile Java code into bin folder
if ! javac -cp "$SRC_DIR/mavis:$SRC_DIR/mavis/command:$SRC_DIR/mavis/task" -Xlint:none -d "$JAVA_BIN" "$SRC_DIR/mavis/"*.java "$SRC_DIR/mavis/command/"*.java "$SRC_DIR/mavis/task/"*.java; then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Run program, feed commands from input.txt, redirect output to ACTUAL.TXT
java -classpath "$JAVA_BIN" mavis.Mavis < "$INPUT_FILE" > "$ACTUAL_FILE"

# Convert to UNIX format
cp "$EXPECTED_FILE" "$EXPECTED_UNIX_FILE"
dos2unix "$ACTUAL_FILE" "$EXPECTED_UNIX_FILE"

# Compare output with expected
diff "$ACTUAL_FILE" "$EXPECTED_UNIX_FILE"
if [ $? -eq 0 ]; then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
