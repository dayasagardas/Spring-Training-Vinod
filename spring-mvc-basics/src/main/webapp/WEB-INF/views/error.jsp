<%@ include file="header.jspf" %>

<div class="alert alert-danger">
<h3>Hey, we encountered an error</h3>
</div>

<p>Please try after sometime. If the error persists, please contact 
<a href="mailto:helpdesk@kvinod.com">helpdesk@kvinod.com</a></p>


<pre>${stackTrace}</pre>

<%@ include file="footer.jspf" %>