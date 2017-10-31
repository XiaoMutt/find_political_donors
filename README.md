# find_political_donors

## InsightDataScience Data Engineering Coding Challenge


### How to Run
* Java 1.7+ installed.
* To run the program use the findpd.jar file in the src folder, or compile the source code.
* See also the Javadoc in the src folder.
* The provided run.sh calls "java -jar dist/findpd.jar input/itcont.txt"
* To use findpd.jar directly:
	* java -jar findpd.jar input_file_path. This will save the output file in the ../output/ folder relative to the input file.
	* java -jar findpd.jar input_file_path output_path. This will save the two output files in the designated output_folder.

* For large data files (eg. 4GB), use -Xms to initialize a big heap size (eg. -Xms4g). This will free JVM from frequently allocating memory and increase speed significantly.

### Folder Content
|folder|content|
|:---|:---|
|input|input file|
|insight_testsuite|testsuite files|
|output|output files|
|src|source code, compiled jar file, and Javadoc|
|test|JUnit test files|


### Other Notations

* Although the TRANSACTION_AMT is indicated as number(14,2), after examine the data files from FEC, it is certain that the data are integer in the data file. Therefore, it is defined as integers to increase processing speed and save space.
* The median is round up to the closest integer in the direction of +∞. Therefore, 0.5 will be rounded to 1, while -0.5 will be rounded to 0.