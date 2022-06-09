package ASSIGNMENT_JAVA5.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ASSIGNMENT_JAVA5.Intercepter.LoggerIntercepter;


@Configuration
public class IntercepterConfig implements WebMvcConfigurer{
	@Autowired
	LoggerIntercepter loggerInter;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(this.loggerInter).addPathPatterns("/**").excludePathPatterns("/home","/login","/register");
	}
	
	

}
