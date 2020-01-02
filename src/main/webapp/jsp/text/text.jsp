<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="gb2312">

    <title>Twitter Bootstrap表单验证 - 站长素材</title>

	  <meta name="viewport" content="width=device-width">

    <link href="${pageContext.request.contextPath}/jsp/text/style.css" rel="stylesheet">

  	<script src="${pageContext.request.contextPath}/jsp/text/js/modernizr-2.5.3.min.js"></script>

  </head>
  <body>
		<div class="container">
		    <div class="page-header">
			    <h1>Twitter Bootstrap风格的jQuery表单验证</h1>
		    </div>
				<div class="row">
				<div id="maincontent" class="span8">

				<div class="tabbable">
					<div class="tab-content">
				    <div id="demo" class="tab-pane active">
				    	<div class="alert alert-success">
				    		<h4>NOTES</h4>
				    		<ul>
				    			<li>To receive feedback, fill in a field and tab to the next. To get negative feedback, only enter one character.</li>
						    	<li>For explanations, see the tabs above for the code, and of course check out the plugin documentation.</li>
						    </ul>
				    	</div><!-- notes .alert -->
						<form action="" id="contact-form" class="form-horizontal">
						  <fieldset>
						    <legend>Sample Contact Form <small>(will not submit any information)</small></legend>

						    <div class="control-group">
						      <label class="control-label" for="name">Your Name</label>
						      <div class="controls">
						        <input type="text" class="input-xlarge" name="name" id="name">
						      </div>
						    </div>
						    <div class="control-group">
						      <label class="control-label" for="email">Email Address</label>
						      <div class="controls">
						        <input type="text" class="input-xlarge" name="email" id="email">
						      </div>
						    </div>
						    <div class="control-group">
						      <label class="control-label" for="subject">Subject</label>
						      <div class="controls">
						        <input type="text" class="input-xlarge" name="subject" id="subject">
						      </div>
						    </div>
						    <div class="control-group">
						      <label class="control-label" for="message">Your Message</label>
						      <div class="controls">
						        <textarea class="input-xlarge" name="message" id="message" rows="3"></textarea>
						      </div>
						    </div>
	              <div class="form-actions">
			            <button type="submit" class="btn btn-primary btn-large">Submit</button>
	    			      <button type="reset" class="btn">Cancel</button>
	        			</div>
						  </fieldset>
						</form>
					</div><!-- .tab-pane -->
				</div><!-- .tab-content -->
				</div><!-- .tabbable -->

				</div><!-- .span -->
			</div><!-- .row -->

      <hr>

    </div> <!-- .container -->

<!-- ==============================================
		 JavaScript below! 															-->

<!-- jQuery via Google + local fallback, see h5bp.com -->
	  <script src="${pageContext.request.contextPath}/jsp/text/assets/js/jquery-1.7.1.min.js"></script>

<!-- Bootstrap JS -->
	  <script src="${pageContext.request.contextPath}/jsp/text/assets/js/bootstrap.min.js"></script>

<!-- Validate plugin -->
		<script src="${pageContext.request.contextPath}/jsp/text/assets/js/jquery.validate.min.js"></script>

<!-- Prettify plugin -->
		<script src="${pageContext.request.contextPath}/jsp/text/assets/js/prettify/prettify.js"></script>

<!-- Scripts specific to this page -->
		<script src="${pageContext.request.contextPath}/jsp/text/script.js"></script>

		<script>
			// Activate Google Prettify in this page
				addEventListener('load', prettyPrint, false);

			$(document).ready(function(){

				// Add prettyprint class to pre elements
					$('pre').addClass('prettyprint linenums');

			});

		</script>
<div style="text-align:center;clear:both">
<p>适用浏览器：IE8、360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗.</p>
<p>来源：<a href="http://sc.chinaz.com/" target="_blank">站长素材</a></p>
</div>
  </body>
</html>
