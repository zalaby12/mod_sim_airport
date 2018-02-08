OUTPUT_BIN = out/
FLIGHTS = flights/
OUTPUT_JAR_MAIN = sim.jar
MANIFEST_MAIN = src/MANIFEST-MAIN.MF

all: clean
	@echo Compiling...
	@mkdir -p $(OUTPUT_BIN)
	@mkdir -p $(FLIGHTS)
	@javac -encoding UTF8 -d $(OUTPUT_BIN) -sourcepath src/ src/edu/cx4230/simulator/App.java
	@echo Creating $(OUTPUT_JAR_MAIN)...
	@jar -cmf $(MANIFEST_MAIN) $(OUTPUT_JAR_MAIN) -C $(OUTPUT_BIN) .
	@java -jar $(OUTPUT_JAR_MAIN)
	@echo Done.


clean:
	@echo Cleaning...
	@rm -rf $(OUTPUT_BIN)
	@rm -rf $(FLIGHTS)
