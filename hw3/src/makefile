JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -protected -splitindex -use -author -version -d ./javadoc
RM = rm
JR = java

CLASSES = \
		IthOccurenceofSubstring.java \
		FindItemsBetweenNumbers.java \
		ContiguousSubarrays.java \
		ColoredBlocks.java \
		SnakeCombinationsInMatrix.java \
		Q4.java \
		Driver.java

all : Driver.class

run :
	$(JR) Main

classes : $(CLASSES:.java=.class)

%.class : %.java
	$(JC) $(JFLAGS) $<

doc:
	$(JD) $(JDFLAGS) *.java

clean:
	$(RM) *.class

cleandoc:
	$(RM) -r ./javadoc