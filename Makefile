OUTPUT_BIN = out/
OUTPUT_JAR_MAIN = sim.jar
MANIFEST_MAIN = src/MANIFEST-MAIN.MF

all: 
	@echo Compiling...
	@mkdir -p $(OUTPUT_BIN)
	@javac -encoding UTF8 -d $(OUTPUT_BIN) -sourcepath src/ src/edu/cx4230/simulator/App.java
	@echo Creating $(OUTPUT_JAR_MAIN)...
	@jar -cmf $(MANIFEST_MAIN) $(OUTPUT_JAR_MAIN) -C $(OUTPUT_BIN) .
	@echo Done.


clean:
	@echo Cleaning...
	@rm -rf $(OUTPUT_BIN)
