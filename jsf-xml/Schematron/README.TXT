This ZIP file contains the jar files (both binary and source) for
the XSLT implementation of Schematron using Java as well as the
Java implementation of an embedded Schematron validator for RELAX-NG
schemas.

When extracted in a directory the follwing directory structure will
be created:

Schematron
   |
   --- Jing
   |
   --- Saxon
   |
   --- Scripts
   |
   --- Java
        |
        --- doc

* The Schematron base directory will contain all the example XML
and Schema files as well as four batch files used for validation.

* The Jing directory contatins the Java jar file for Jing (jing.jar)
that is used for RELAX-NG validation.

* The Saxon directory contains the Java jar file for Saxon (saxon.jar)
that is used for Schematron validation.

* The Scripts directory contains the XSLT stylesheets used for
Schematron validation and embedded Schematron validation.

* The Java directory contains the Java jar file (Schematron.jar)
for the Schematron implementation and the Java source files
(Schematron_src.jar).

* The Java/doc directory contains the Javadocs for the Java Schematron
implementation.


Example files
-------------
The example used to illustrate the power of Schematron and embedded
Schematron rules is very simple. It is a small XML file with the
following structure:

<?xml version="1.0" encoding="UTF-8"?>
<add sum="6">
	<item>2</item>
	<item>4</item>
</add>

The requirements are: "the 'add' element must have at least one
'item' child element and the 'sum' attribute is required. The value
of each 'item' element must be a number and the value of the 'sum'
attribute must equal the sum of all the 'item' elements."

The examples will show how this can be done by Schematron alone
or by embedded a Schematron rule in the RELAX-NG schema to check
the non-structural constraint. The examples will be shown both
with and without namespaces.

Without namespace              add_good.xml
                               add_bad.xml
                               add.sch
                               add.rng

With namespace                 add_good_namespace.xml
                               add_bad_namespace.xml
                               add_namespace.sch
                               add_namespace.rng


USAGE
-----
All the examples can be run and tested by using the two batch files:
Schtrn_java.bat and EmbRNG_java.bat

* The Schtrn_java.bat file is used to validate an XML instance
document against a Schematron schema using the Schematron Java
implementation (Schematron.jar)

* The EmbRNG_java.bat file is used to validate against both RELAX-NG
and embedded Schematron rules using the Java implementation
(Schematron.jar)


Example usage of Schtrn_java.bat
--------------------------------
cd <your_extract_dir>\Schematron
Schtrn_java.bat add_bad.xml add.sch

Example usage of EmbRNG_java.bat
--------------------------------
cd <your_extract_dir>\Schematron
EmbRNG_java.bat add_bad.xml add.rng


Feel free to email me at: edde_se@hotmail.com if you have any
questions, comments or problems.