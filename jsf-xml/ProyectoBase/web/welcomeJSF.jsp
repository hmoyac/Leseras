<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="ice" uri="http://www.icesoft.com/icefaces/component"%>
<f:view>
	<ice:outputHtml>
		<ice:outputHead>
			<title>JSP Page</title>
			<ice:outputStyle href="./xmlhttp/css/xp/xp.css"/>
		</ice:outputHead>
		<ice:outputBody>
			<ice:form id="form1">

				<ice:panelGroup styleClass="componentBox">

				<ice:panelGroup styleClass="synopsisBox inputFileContainer">
					<ice:outputText value="#{msgs['page.inputFile.synopsis']}"/>
					<ice:outputText value="#{msgs['page.inputFile.instructions']}"/>
				</ice:panelGroup>

				<!-- file upload example -->
				<ice:panelGroup styleClass="exampleBox inputFileContainer">

					<!-- file upload usage with progress callback. -->
							<ice:inputFile id="inputFileName"
										   progressListener="#{inputFileController.fileUploadProgress}"
						  actionListener="#{inputFileController.uploadFile}"/>
					<!-- progress bar, percent value is upated via progressListener-->

							<ice:outputProgress value="#{inputFileController.fileProgress}"
							   styleClass="uploadProgressBar"/>

					<!-- Dipslay File Upload messages -->
							<!--<ice:outputText value="#{msgs['page.inputFile.messages.label']}"/>-->
							<h:messages layout="table"
										globalOnly="false"
										showDetail="true"
					   showSummary="true"/>
				</ice:panelGroup>

				<ice:panelGroup styleClass="exampleBox firstChildTitle inputFileContainer">

				<ice:outputText
					value="#{msgs['page.inputFile.history.label']}"/>

				<ice:dataTable
					width="450px"
					value="#{inputFileController.fileList}"
					var="file">
				<ice:column>
					<f:facet name="header">
						<ice:outputLabel
							value="#{msgs['page.inputFile.history.name.label']}"/>
					</f:facet>
					<ice:outputText value="#{file.fileInfo.fileName}"/>
				</ice:column>
				<ice:column>
					<f:facet name="header">
						<ice:outputLabel
							value="#{msgs['page.inputFile.history.size.label']}"/>
					</f:facet>
					<ice:outputText value="#{file.sizeFormatted}"/>
				</ice:column>
				<ice:column>
					<f:facet name="header">
						<ice:outputText
							value="#{msgs['page.inputFile.history.content.label']}"/>
					</f:facet>
					<ice:outputText value="#{file.fileInfo.contentType}"/>
				</ice:column>
				<ice:column>
					<f:facet name="header">
						<ice:outputText
							value="#{msgs['page.inputFile.history.remove.label']}"/>
					</f:facet>
					<ice:commandLink
						value="#{msgs['page.inputFile.history.remove.label']}"
						actionListener="#{inputFileController.removeUploadedFile}">
					<f:param name="fileName" value="#{file.fileInfo.fileName}"/>
					</ice:commandLink>
				</ice:column>
				</ice:dataTable>

				</ice:panelGroup>
				</ice:panelGroup>

			</ice:form>
		</ice:outputBody>
	</ice:outputHtml>
</f:view> 