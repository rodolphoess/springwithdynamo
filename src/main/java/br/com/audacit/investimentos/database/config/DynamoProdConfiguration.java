package br.com.audacit.investimentos.database.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.WebIdentityTokenCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!local")
public class DynamoProdConfiguration {

    @Value("${aws.region:sa-east-1}")
    private String region;

    @Bean
    @Primary
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(amazonDynamoDb());
    }

    private AmazonDynamoDB amazonDynamoDb() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(region)
                .withClientConfiguration(new ClientConfiguration())
                .withCredentials(WebIdentityTokenCredentialsProvider.create())
                .build();
    }

}
