package tech.salvas.eifapi.configs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DOConfig {
    @Value("${do.space.key}")
    private String spaceKey;

    @Value("${do.space.secret}")
    private String spaceSecret;

    @Value("${do.space.endpoint}")
    private String spaceEndpoint;

    @Value("${do.space.region}")
    private String spaceRegion;

    @Bean
    public AmazonS3 getS3() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(spaceKey, spaceSecret);
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(spaceEndpoint, spaceRegion))
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
    }
}
