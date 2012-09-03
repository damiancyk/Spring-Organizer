package net.organizer.controller;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.mobile.device.site.SitePreferenceUtils;

import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.DecoratorMapper;
import com.opensymphony.module.sitemesh.Page;
import com.opensymphony.module.sitemesh.mapper.ConfigDecoratorMapper;


/**
 * This mapper is based on the ParameterDecoratorMapper that comes with SiteMesh.
 * This Mapper targets Spring Mobile and will apply a mobile decorator only if
 * the devices this request originates from is in fact a mobile device.
 *
 * As a result this mapper has a hard dependency onto Spring Mobile.
 *
 * @author Gunnar Hillert
 * @version $id$
 *
 * @see com.opensymphony.module.sitemesh.DecoratorMapper
 */
public class SpringMobileParameterDecoratorMapper extends ConfigDecoratorMapper {
    private String decoratorName = null;
 
    public void init(Config config, Properties properties, DecoratorMapper parent) throws InstantiationException {
        super.init(config, properties, parent);
        decoratorName = properties.getProperty("decorator.name", "mobile");
    }
 
    public Decorator getDecorator(final HttpServletRequest request, final Page page) {
        SitePreference sitePreference = SitePreferenceUtils.getCurrentSitePreference(request);
 
        if(sitePreference != null && sitePreference.isMobile()){
            return getNamedDecorator(request, decoratorName);
        }
 
        return super.getDecorator(request, page);
    }

}