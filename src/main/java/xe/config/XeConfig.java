package xe.config;

import com.xe.xecdApiClient.config.XecdApiConfigBean;
import com.xe.xecdApiClient.service.XecdApiService;
import com.xe.xecdApiClient.service.XecdApiServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XeConfig {

    @Bean
    public XecdApiService xeApiService() {
        XecdApiConfigBean xeConfig = new XecdApiConfigBean();
        xeConfig.setAccountId("");
        xeConfig.setApiKey("");
        return XecdApiServiceFactory.createXecdAPIService(xeConfig);
    }

}
