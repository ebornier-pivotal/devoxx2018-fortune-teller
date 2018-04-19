package io.spring.cloud.samples.fortuneteller.ui.services.fortunes;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.lang.Throwable;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@RibbonClient(name = "fortune-client", configuration = FortuneServiceConfiguration.class)
public class FortuneService {


	 private static Logger log = org.slf4j.LoggerFactory.getLogger(FortuneService.class);


	// In Spring Cloud Brixton release and after, a RestTemplate is not auto injected
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
	   return new RestTemplate();
	}
	
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackFortune")
    public Fortune randomFortune() {
        log.info("/randomFortune called"); 
//	return restTemplate.getForObject("https://fortune/random", Fortune.class);
         return restTemplate.getForObject("http://fortune/random", Fortune.class); 
   }

    private Fortune fallbackFortune(Throwable e) {
        e.printStackTrace();
        return new Fortune("42L", "Your future is unclear.");
    }
}
