<?xml version="1.0" encoding="UTF-8"?>
<sch:schema xmlns:sch="http://www.ascc.net/xml/schematron">
	<sch:ns prefix="t" uri="http://www.topologi.com/add"/>
	<sch:pattern name="Check structure">
		<sch:rule context="/">
			<sch:assert test="t:add">The root element must be add.</sch:assert>
		</sch:rule>	
		<sch:rule context="t:add">
			<sch:assert test="@sum">The element add must have a sum attribute</sch:assert>
			<sch:assert test="count(*) = count(t:item)">The element add can only have item child elements.</sch:assert>
			<sch:assert test="count(t:item) >= 1">The element add must have at least one item element.</sch:assert>
		</sch:rule>
		<sch:rule context="item">
			<sch:assert test="number(.)">The content of the item element must be a number.</sch:assert>
		</sch:rule>
	</sch:pattern>
	<sch:pattern name="Check math">
		<sch:rule context="t:add">
			<sch:assert test="@sum = sum(t:item)">The value of the sum attribute should be the
			sum of all the values in the item child elements.</sch:assert>
		</sch:rule>
	</sch:pattern>
</sch:schema>
