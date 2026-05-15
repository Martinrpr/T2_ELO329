.PHONY: all run doc clean

JAVAFX_PATH = C:/Users/Carlos/javafx21/lib
MODS        = javafx.controls,javafx.fxml
JAVAC       = "C:/Program Files/Android/Android Studio/jbr/bin/javac"
JAVA        = "C:/Program Files/Android/Android Studio/jbr/bin/java"
JAVADOC     = "C:/Program Files/Android/Android Studio/jbr/bin/javadoc"

all:
	$(JAVAC) --module-path "$(JAVAFX_PATH)" --add-modules $(MODS) -d . src/*.java

run: all
	$(JAVA) --module-path "$(JAVAFX_PATH)" --add-modules $(MODS) Stage4

doc:
	$(JAVADOC) --module-path "$(JAVAFX_PATH)" --add-modules $(MODS) -d doc -private \
	    src/Equipo.java src/EloTelTag.java src/EloTelTagView.java src/Tablet.java src/TabletView.java

clean:
	rm -f *.class
