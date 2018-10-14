JAVAC=javac
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: jar

%.class: %.java
	$(JAVAC) $<
    
jar: $(classes)
	jar cmf manifest.mf TicTacToe.jar $(classes)

clean:
	rm -f *.class *.jar