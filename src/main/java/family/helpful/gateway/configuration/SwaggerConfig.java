package family.helpful.gateway.configuration;

import com.fasterxml.jackson.databind.JsonNode;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

   @Bean
   public Docket api() {
       return new Docket(DocumentationType.SWAGGER_2)
               .directModelSubstitute(JsonNode.class, Object.class)
               .apiInfo(apiInfo())
               .select()
               .paths(PathSelectors.any())
               .apis(RequestHandlerSelectors.basePackage("family.helpful"))
               .build()
               .ignoredParameterTypes(KeycloakAuthenticationToken.class)
               .useDefaultResponseMessages(false);
   }

   private static ApiInfo apiInfo() {
       final Contact contact = new Contact("Helpful.family (GitHub)", "https://github.com/helpfulfamily", null);
       return new ApiInfoBuilder()
               .title("ATG Member Service")
               .contact(contact)
               .version("1.0")
               .build();
   }

   @Bean
   public UiConfiguration uiConfig() {
       return UiConfigurationBuilder.builder()
               .displayRequestDuration(true)
               .validatorUrl("") // Workaround for https://github.com/springfox/springfox/issues/2201, should be null according to spec
               .build();
   }
}
