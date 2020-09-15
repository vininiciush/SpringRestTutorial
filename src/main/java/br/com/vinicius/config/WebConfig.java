package br.com.vinicius.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.vinicius.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");
	
	public void extendMessageConverter(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
	}
	
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		//Content negotiation via extenção

//		configurer.favorParameter(false).
//		ignoreAcceptHeader(false).
//		defaultContentType(MediaType.APPLICATION_JSON).
//		mediaType("json", MediaType.APPLICATION_JSON).
//		mediaType("xml", MediaType.APPLICATION_XML).
//		mediaType("x-yaml", MEDIA_TYPE_YAML);
		
		//Content negotiation via query param
		
//		configurer.favorPathExtension(false).
//		favorParameter(true).
//		parameterName("mediaType").
//		ignoreAcceptHeader(true).
//		useRegisteredExtensionsOnly(false).
//		defaultContentType(MediaType.APPLICATION_JSON).
//		mediaType("json", MediaType.APPLICATION_JSON).
//		mediaType("xml", MediaType.APPLICATION_XML).
//		mediaType("x-yaml", MEDIA_TYPE_YAML);
		
		//Content negotiation via header param
		
		configurer.favorPathExtension(false).
		favorParameter(false).
		ignoreAcceptHeader(false).
		useRegisteredExtensionsOnly(false).
		defaultContentType(MediaType.APPLICATION_JSON).
		mediaType("json", MediaType.APPLICATION_JSON).
		mediaType("xml", MediaType.APPLICATION_XML).
		mediaType("x-yaml", MEDIA_TYPE_YML);
	}
}
