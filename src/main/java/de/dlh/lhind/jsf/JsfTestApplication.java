package de.dlh.lhind.jsf;

import com.sun.faces.config.ConfigureListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContainerInitializer;

@SpringBootApplication
public class JsfTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsfTestApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean facesServletRegistration() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new FacesServlet(), "*.jsf");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}

    @Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<>(new ConfigureListener());
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
//            servletContext.setInitParameter("primefaces.THEME", "bootstrap");
//            servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", Boolean.TRUE.toString());
            servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
//            servletContext.setInitParameter("primefaces.FONT_AWESOME", Boolean.TRUE.toString());
//            servletContext.setInitParameter("primefaces.UPLOADER", "commons");
        };
    }
}
