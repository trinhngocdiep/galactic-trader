COMPILE AND BUILD
* JDK8 to compile
* Maven, JUnit, Mockito to build and run tests

RUN
* Please run the Main class with a command line arguments to specify the input test file. The file path should be an absolute path.
For example: $ %java_home%/bin/java -cp target/classes com.thoughtworks.galactic.trader.demo.Main C:/test.txt

CLASS DESIGN
* GalacticCalculator class as the dictionary to store galactic term definitions. 
	The memory is implemented with two maps:
	+ a map from galactic number literal (glob, prok, etc) -> roman literal (I, V, X, etc)
	+ a map from product name (silver, gold, etc) -> unit value (10, 25, etc)
* Definition parser classes (NumeralDefinitionParser, UnitDefinitionParser) to parse the input definitions 
	and store them in the dictionary.
* Query parser classes (GalacticNumberQueryParser, CreditValueQueryParser) to parse 
	and answer the input query based on the definitions in the dictionary.
* RomanToDecimalConverter to convert roman number to decimal number. 
* Main class as a simple command-line based program to demo the runtime behavior.

ASSUMPTION ABOUT THE INPUT FILE
* The file content should be lines of text describing the galactic transactions.
* All the definitions must occur before the queries.