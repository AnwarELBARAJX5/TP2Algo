# Nom de la classe principale (sans extension)
MAIN_CLASS = Main

JAR_FILE = TP3RandomTrees.jar
# Dossiers
SRC_DIR = src
OUT_DIR = out
RES_DIR = resources

USER_NAME = $(shell whoami)
ARCHIVE_NAME = $(USER_NAME)_renduTP2.zip

JFLAGS = -d $(OUT_DIR) -cp $(SRC_DIR)

SOURCES = $(shell find $(SRC_DIR) -name "*.java")

# Cible par défaut
all: compile jar

init:
	mkdir -p $(OUT_DIR)

compile: init
	javac $(JFLAGS) $(SOURCES)


jar: compile
	jar cfe $(JAR_FILE) $(MAIN_CLASS) -C $(OUT_DIR) .


exec: compile
	java -cp $(OUT_DIR) $(MAIN_CLASS)

demo: exec

clean:
	rm -rf $(OUT_DIR)
	rm -f $(JAR_FILE)
	rm -f *.zip

zip: clean
	zip -r $(ARCHIVE_NAME) $(SRC_DIR) $(RES_DIR) Makefile README.md *.csv

test:
	@echo "Pas de tests unitaires définis."

.PHONY: all init compile jar exec demo clean zip test