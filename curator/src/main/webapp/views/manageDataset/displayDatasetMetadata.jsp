<%@ page
	import="java.io.File"
	import="java.util.List"
	import="java.util.ArrayList"
	import="java.util.Iterator"
	import="java.util.Map"
	import="java.util.Hashtable"
	import="gov.nasa.jpl.oodt.cas.curation.policymgr.CurationPolicyManager"
	import="gov.nasa.jpl.oodt.cas.curation.util.HTMLEncode"
%>
<%@page import="gov.nasa.jpl.oodt.cas.metadata.util.PathUtils"%>
<%@page import="gov.nasa.jpl.oodt.cas.curation.servlet.CuratorConfMetKeys"%>
<%@page import="gov.nasa.jpl.oodt.cas.filemgr.structs.ProductType"%><script type="text/javascript" src="js/jquery/jquery.js"></script>

<div class="wizardContent">
	<h4>Dataset Metadata For: <%=session.getAttribute("dsCollection") %> / <%=session.getAttribute("ds") %></h4>

	<div>
		<h5>Defined metadata key/value pairs in <%=session.getAttribute("dsCollection") %> : <%=session.getAttribute("ds") %> </h5>
		
		<form action="updateDatasetMetaData" method="POST"> 
		<table id="metaDataEditor">
		<thead><tr><th>Key</th><th>Value</th></thead><tbody>
<%
			String policyName = (String)session.getAttribute("dsCollection");
			String productTypeName = (String)session.getAttribute("ds");
	        String stagingAreaPath = PathUtils.replaceEnvVariables(getServletContext().getInitParameter(CuratorConfMetKeys.STAGING_AREA_PATH));
	        String policyDirPath = PathUtils.replaceEnvVariables(getServletContext().getInitParameter(CuratorConfMetKeys.POLICY_UPLOAD_PATH));    
			
			
			CurationPolicyManager cpm = new CurationPolicyManager(policyDirPath, stagingAreaPath);
			Map<String, ProductType> types = cpm.getProductTypes(policyName);
			ProductType type = types.get(productTypeName);
			
			String tableClose = "</tbody></table>";			
			
			
			if (type.getTypeMetadata() != null && type.getTypeMetadata().getHashtable().keySet().size() > 0) {		
				int even_row = 1;
				for (Object k : type.getTypeMetadata().getHashtable().keySet()) {
					String key = (String)k;
					String value = type.getTypeMetadata().getMetadata(key);
					
					/*
					 * PubMedID field requires special
					 * handling because of HTML hyperlink
					 * content.
					 */
					if (key.equals("PubMedID"))
						value = HTMLEncode.encode(value);
							
					String keyField = "\t\t\t\t<td class=\"key\">" + key + "</td>";
					
					String valueField = "\t\t\t\t<td class=\"value\"><input type=\"text\" id=\"value_" + key +
										"\" name=\"value_" + key + "\" value=\"" + value + "\" /></td>";
						
					out.print("\t\t\t<tr class=\"");
					
					// set even/odd row class for styling
					if (even_row == 1) 
						out.println("odd\">");
					else 
						out.println("even\">");
					
					out.println(keyField);				
					out.println(valueField);
					
					out.println("\t\t\t</tr>");
					even_row = 1 - even_row;
				}
			} else {
				out.println("<tr class=\"odd\"><td>");
				out.println("No metadata values defined.");
				out.println("</td></tr>");			
			}			
			out.println(tableClose);
			// hide the Save Changes button if no metadata is found
			if (type.getTypeMetadata() != null && type.getTypeMetadata().getHashtable().keySet().size() > 0) {
				out.println("<input type=\"hidden\" name=\"dsCollection\" value=\""+ policyName +"\"/>");
				out.println("<input type=\"hidden\" name=\"ds\" value=\""+ productTypeName + "\"/>");
				out.println("<input type=\"hidden\" name=\"step\" value=\"displayDatasetMetadata\"/>");
				out.println("<input type=\"hidden\" name=\"action\" value=\"SAVEALL\"/>");		
				out.println("<br><input type=\"submit\" id=\"submitButton\" value=\"Save changes\"/>");
			}
			out.println("</form>");	
		%> 

	</div>
</div>