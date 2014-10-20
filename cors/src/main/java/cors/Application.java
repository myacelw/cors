package cors;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
	return (ServletContext servletContext) -> {
	    final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	    characterEncodingFilter.setEncoding("UTF-8");
	    characterEncodingFilter.setForceEncoding(false);
	    servletContext.addFilter("characterEncodingFilter", characterEncodingFilter).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
	};
    }
    
   
 //	private String basename = "WEB-INF/messages";
//	private String encoding = "utf-8";
//	private int cacheSeconds = -1;
//  
//	@Bean
//	public MessageSource messageSource() {
//		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		if (StringUtils.hasText(this.basename)) {
//			messageSource
//					.setBasenames(commaDelimitedListToStringArray(trimAllWhitespace(this.basename)));
//		}
//		messageSource.setDefaultEncoding(this.encoding);
//		messageSource.setCacheSeconds(this.cacheSeconds);
//		return messageSource;
//	}

}
