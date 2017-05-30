Onitama: 
	mkdir -p .classes/
	javac -cp .classes/ *.java
	mv *.class .classes/

run:
	java -cp .classes/ Onitama

runjar:
	java -jar Onitama.jar

jar: 
	rm -f Onitama.jar
	echo Main-Class: Onitama >manifest.txt
	echo Class-Path: *.class >>manifest.txt
	jar cvfm Onitama.jar manifest.txt -C .classes/ .
	rm manifest.txt

clean:
	rm -f *.class
	rm -rf .classes
