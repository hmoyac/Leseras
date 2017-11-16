@ECHO OFF

rem Example usage:
rem Schtrn_java Person_bad.xml Person.sch

rem Make sure the Schematron.jar file and the JAXP implementation of an XSLT processor is available
SET CLASSPATH=".\Saxon\saxon.jar;.\Java\Schematron.jar"

cls
echo ---------------------------------------------------------------
echo Running Schematron validation with the following specification:
echo    - XML File Being Validated: %1
echo    - Schematron schema: %2
echo ---------------------------------------------------------------

rem D:\Work\Schematron\Embedded\Book_RNG\Package>Schtrn_java D:\temp\singles_split.xml D:\temp\tournament_sch.xml

rem D:\Work\Schematron\Embedded\Book_RNG\Package>Schtrn_java D:\Work\Schematron\Test\Tournament\singles_split.xml D:\Work\Schematron\Test\Tournament\tournament_sch.xml phase=Split


rem A maximum of three parameters can be specified in this batch file...
if '%3 == ' goto no-param
if '%5 == ' goto one-param
if '%7 == ' goto two-params
if '%9 == ' goto three-params

rem No parameters
:no-param
java -classpath "%CLASSPATH%" com.topologi.schematron.SchtrnValidator %1 %2
goto end

rem One parameter
:one-param
java -classpath "%CLASSPATH%" com.topologi.schematron.SchtrnValidator %1 %2 %3=%4
goto end

rem Two parameters
:two-params
java -classpath "%CLASSPATH%" com.topologi.schematron.SchtrnValidator %1 %2 %3=%4 %5=%6
goto end

rem Three parameters
:three-params
java -classpath "%CLASSPATH%" com.topologi.schematron.SchtrnValidator %1 %2 %3=%4 %5=%6 %7=%8

:end

