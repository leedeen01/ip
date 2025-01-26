#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

if [ -e "../src/main/data/Mavis.txt" ]
then
    rm ../src/main/data/Mavis.txt  # Remove the file if it exists
    touch ../src/main/data/Mavis.txt  # Create the file again
    chmod 644 ../src/main/data/Mavis.txt  # Corrected path

fi

# compile all the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java/mavis:../src/main/java/mavis/command:../src/main/java/mavis/task -Xlint:none -d ../bin ../src/main/java/mavis/*.java ../src/main/java/mavis/command/*.java ../src/main/java/mavis/task/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin mavis.Mavis < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi