<?xml version="1.0" encoding="iso-8859-1"?>
<sch:schema xmlns:sch="http://www.ascc.net/xml/schematron">

	<!--========================================================================
		SEG�N TIPO DE SERVICIO DEBE VENIR : METROS C�BICOS AP Y/O METROS C�BICOS AS
	=========================================================================-->
	<!--<sch:pattern name="Espacios de nombres">
		<sch:rule context="/*">
			<sch:assert test="namespace-uri(.)=''"> Debe remover espacio de nombre de archivo xml en <value-of select="namespace-uri(.)"/></sch:assert>
		</sch:rule>
	</sch:pattern>-->

    <sch:pattern name="Tipo Servicio AP">
		<sch:rule context="Servicio[@tipoServicio='1']/Rango">
            <sch:report test="MetrosCubicosAP='' or MetrosCubicosAS!=''">
                S�lo debe informar MetrosCubicosAP en
                Limite[codigoLimite(<value-of select="ancestor::Limite/@codigoLimite"/>)]/
                Localidad[codigoLocalidad(<value-of select="ancestor::Localidad/@codigoLocalidad"/>),
                          codigoComuna(<value-of select="ancestor::Localidad/@codigoComuna"/>)]/
                Cliente[tipoCliente(<value-of select="ancestor::Cliente/@tipoCliente"/>)]/
                Servicio[tipoServicio(1)]/
                Rango[codigoRango(<value-of select="@codigoRango"/>)]
            </sch:report>
		</sch:rule>
	</sch:pattern>
	<sch:pattern name="Tipo Servicio AS">
		<sch:rule context="Servicio[@tipoServicio='2']/Rango">
            <sch:report test="MetrosCubicosAS='' or MetrosCubicosAP!=''">
                S�lo debe informar MetrosCubicosAS en
                Limite[codigoLimite(<value-of select="ancestor::Limite/@codigoLimite"/>)]/
                Localidad[codigoLocalidad(<value-of select="ancestor::Localidad/@codigoLocalidad"/>),
                          codigoComuna(<value-of select="ancestor::Localidad/@codigoComuna"/>)]/
                Cliente[tipoCliente(<value-of select="ancestor::Cliente/@tipoCliente"/>)]/
                Servicio[tipoServicio(2)]/
                Rango[codigoRango(<value-of select="@codigoRango"/>)]
            </sch:report>
		</sch:rule>
	</sch:pattern>
	<sch:pattern name="AP y AS (C/S Pozo)">
		<sch:rule context="Servicio[@tipoServicio='3' or @tipoServicio='5']/Rango">
            <sch:assert test="MetrosCubicosAP!='' and MetrosCubicosAS!=''">
                Deben informar MetrosCubicosAP y MetrosCubicosAS en
                Limite[codigoLimite(<value-of select="ancestor::Limite/@codigoLimite"/>)]/
                Localidad[codigoLocalidad(<value-of select="ancestor::Localidad/@codigoLocalidad"/>),
                          codigoComuna(<value-of select="ancestor::Localidad/@codigoComuna"/>)]/
                Cliente[tipoCliente(<value-of select="ancestor::Cliente/@tipoCliente"/>)]/
                Servicio[tipoServicio(<value-of select="ancestor::Servicio/@tipoServicio"/>)]/
                Rango[codigoRango(<value-of select="@codigoRango"/>)]
            </sch:assert>
		</sch:rule>
	</sch:pattern>

</sch:schema>