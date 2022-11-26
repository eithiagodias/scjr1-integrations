package br.com.fiap.agrodrones.producer;

import br.com.fiap.agrodrones.producer.dto.DroneInfoDTO;
import br.com.fiap.agrodrones.producer.service.DroneInfoService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.fiap.agrodrones.producer")
public class ProducerApplication {

	@Value("${server.port}")
	private String serverPort;

	@Value("${fiap.api.path}")
	private String contextPath;

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Bean
	ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean servlet = new ServletRegistrationBean(new CamelHttpTransportServlet(), contextPath + "/*");
		servlet.setName("CamelServlet");
		return servlet;
	}


	@Component
	class RestApi extends RouteBuilder {

		@Override
		public void configure() throws Exception {
			CamelContext context = new DefaultCamelContext();

			rest("/api/")
					.id("api-route")
					.post("/drone-info")
					.produces(MediaType.APPLICATION_JSON)
					.consumes(MediaType.APPLICATION_JSON)
					.bindingMode(RestBindingMode.auto)
					.type(DroneInfoDTO.class)
					.enableCORS(true)
					.to("direct:remoteService");

			from("direct:remoteService")
					.id("direct-route")
					.tracing()
					.log(">>> id_drone: ${body.id_drone}")
					.process(new Processor() {
						@Override
						public void process(Exchange exchange) throws Exception {
							DroneInfoDTO dto = (DroneInfoDTO) exchange.getIn().getBody();
							try {
								DroneInfoService.processDroneInfo(dto);
								exchange.getIn().setBody(dto);
								exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
							} catch (Exception e) {
								exchange.getIn().setBody(e.getMessage());
								exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
							}
						}
					});


		}
	}

}