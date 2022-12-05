SRCS = $(shell find * -name "*.java")

CLASSES = $(SRCS:.java=.class)

$(CLASSES): $(SRCS)
	@echo Compiling...
	@echo $(SRCS) > sources.txt
	javac @sources.txt
	@echo

all: $(CLASSES)

run: $(CLASSES)
	java -classpath src avajlauncher.AvajLauncher scenario.txt

clean:
	rm -f $(CLASSES)

re: clean all