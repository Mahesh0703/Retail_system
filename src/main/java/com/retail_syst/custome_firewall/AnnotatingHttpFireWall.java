package com.retail_syst.custome_firewall;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.firewall.FirewalledRequest;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.security.web.firewall.StrictHttpFirewall;

public class AnnotatingHttpFireWall extends StrictHttpFirewall {

	public static final String HTTP_HEADER_REQUEST_REJECTED_FLAG = "X-HttpFirewall-RequestRejectedFlag";
	
	public static final String HTTP_HEADER_REQUEST_REJECTED_REASON = "X-HttpFirewall-RequestRejectedReason";
	
	
	public AnnotatingHttpFireWall()
    {
        super();
        return;
    }
	
	@Override
    public FirewalledRequest getFirewalledRequest(final HttpServletRequest request)
    {
        try
        {
            this.inspect(request); // Perform any additional checks that the naive "StrictHttpFirewall" misses.
            return super.getFirewalledRequest(request);
        } catch (RequestRejectedException ex) {
            final String requestUrl = request.getRequestURL().toString();

            // Override some of the default behavior because some requests are
            // legitimate.
            if (requestUrl.contains(";jsessionid="))
            {
                // Do not block non-cookie serialized sessions. Google's crawler does this often.
            } else {
                // Log anything that is blocked so we can find these in the catalina.out log.
                // This will give us any information we need to make
                // adjustments to these special cases and see potentially
                // malicious activity.
          
                // Mark this request as rejected.
                request.setAttribute(HTTP_HEADER_REQUEST_REJECTED_REASON, Boolean.TRUE);
                request.setAttribute(HTTP_HEADER_REQUEST_REJECTED_REASON, ex.getMessage());
            }

            // Suppress the RequestBlockedException and pass the request through
            // with the additional attribute.
            return new FirewalledRequest(request)
            {
                @Override
                public void reset()
                {
                    return;
                }
            };
        }
    }
	
	 @Override
	    public HttpServletResponse getFirewalledResponse(final HttpServletResponse response)
	    {
	        // Note: The FirewalledResponse class is not accessible outside the package.
	        return super.getFirewalledResponse(response);
	    }

	public void inspect(final HttpServletRequest request) throws RequestRejectedException
    {
        final String requestUri = request.getRequestURI(); // path without parameters
//        final String requestUrl = request.getRequestURL().toString(); // full path with parameters

        if (requestUri.endsWith("/wp-login.php"))
        {
            throw new RequestRejectedException("The request was rejected because it is a vulnerability scan.");
        }

        if (requestUri.endsWith(".php"))
        {
            throw new RequestRejectedException("The request was rejected because it is a likely vulnerability scan.");
        }

        return; // The request passed all custom tests.
    }
}
