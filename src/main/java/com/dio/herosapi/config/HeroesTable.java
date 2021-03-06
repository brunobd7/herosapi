package com.dio.herosapi.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.dio.herosapi.constants.HeroesConstant;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType; //attributes type
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput; //storage
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;  // attributes names
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

import java.util.Arrays;

@Configuration
@EnableDynamoDBRepositories
public class HeroesTable {

    public static void main(String[] args) {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(HeroesConstant.HEROES_ENDPOINT_DYNAMO, HeroesConstant.REGION_DYNAMO))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        String tableName = "Heroes_Table";

        try{

            Table table = dynamoDB.createTable(tableName,
                    Arrays.asList( new KeySchemaElement("id",KeyType.HASH)),
                    Arrays.asList(new AttributeDefinition("id",ScalarAttributeType.S)),
                    new ProvisionedThroughput(5L,5l));

            table.waitForActive();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }




    }

}
