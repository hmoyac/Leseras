@ECHO OFF

rem Example usage:
rem EmbRNG_java Sample.xml PurchaseOrder_embedded.rng

rem Make sure the Schematron.jar file and the JAXP implementation of an XSLT processor is available
SET CLASSPATH=".\Saxon\saxon.jar;.\Java\Schematron.jar;.\Jing\jing.jar"

cls
echo ---------------------------------------------------------------
echo Running RELAX-NG validation with embedded Schematron rules:
echo    - XML File Being Validated: %1
echo    - RELAX-NG schema: %2
echo ---------------------------------------------------------------

rem A maximum of three parameters can be specified in this batch file...
if '%3 == ' goto no-param
if '%5 == ' goto one-param
if '%7 == ' goto two-params
if '%9 == ' goto three-params

rem No parameters
:no-param
java -classpath "%CLASSPATH%" com.topologi.schematron.EmbRNGValidator %1 %2
goto end

rem One parameter
:one-param
java -classpath "%CLASSPATH%" com.topologi.schematron.EmbRNGValidator %1 %2 %3=%4
goto end

rem Two parameters
:two-params
java -classpath "%CLASSPATH%" com.topologi.schematron.EmbRNGValidator %1 %2 %3=%4 %5=%6
goto end

rem Three parameters
:three-params
java -classpath "%CLASSPATH%" com.topologi.schematron.EmbRNGValidator %1 %2 %3=%4 %5=%6 %7=%8

:end

