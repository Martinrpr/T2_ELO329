JAVAFX_PATH = C:/Users/Carlos/javafx21/lib
MODS        = javafx.controls,javafx.fxml
JAVAC       = "C:/Program Files/Android/Android Studio/jbr/bin/javac"
JAVA        = "C:/Program Files/Android/Android Studio/jbr/bin/java"

all:
	$(JAVAC) --module-path "$(JAVAFX_PATH)" --add-modules $(MODS) -d . src/*.java

run: all
	$(JAVA) --module-path "$(JAVAFX_PATH)" --add-modules $(MODS) Stage2

clean:
	rm -f *.class
