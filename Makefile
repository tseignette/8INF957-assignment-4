all: source run

source:
	javac -d bin src/*.java

run:
	java -cp bin SmartParking

clean:
	rm -R bin/*.class
