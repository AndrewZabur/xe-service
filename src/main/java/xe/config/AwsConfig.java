package xe.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Bean
    public AmazonS3 amazonS3Client() {
        Regions clientRegion = Regions.EU_WEST_1;
        return AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .build();
    }

}
