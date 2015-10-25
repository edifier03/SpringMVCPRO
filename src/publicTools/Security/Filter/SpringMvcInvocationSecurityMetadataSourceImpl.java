package publicTools.Security.Filter;

import java.util.Collection;
import java.util.HashMap;

import org.jboss.weld.security.spi.SecurityServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class SpringMvcInvocationSecurityMetadataSourceImpl 
	implements FilterInvocationSecurityMetadataSource{
	
	private static final Logger logger = LoggerFactory.getLogger(SpringMvcInvocationSecurityMetadataSourceImpl.class);
	
	private SecurityServices  securityService;
	
	public SpringMvcInvocationSecurityMetadataSourceImpl (SecurityServices  securityService)
	{
		this.securityService = securityService;
	}
	
	//所有资源和权限的映射
//	private HashMap<RequestMatcher,Collection<CongfigAttribute>> requestMap = new HashMap<RequestMatcher,Collection<CongfigAttribute>>();
	
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object arg0)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
