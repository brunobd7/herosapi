package com.dio.herosapi.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.dio.herosapi.constants.HeroesConstant;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType; //attributes type
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput; //storage
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;  // attributes names
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;

import java.util.Arrays;

public class HeroesData {

    public static void main(String[] args) {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration( new AwsClientBuilder.EndpointConfiguration(HeroesConstant.HEROES_ENDPOINT_DYNAMO,HeroesConstant.REGION_DYNAMO))
                .build();


        DynamoDB dynamoDb = new DynamoDB(client);

        Table table = dynamoDb.getTable("Heroes_Table");

        Item hero = new Item().withPrimaryKey("id",1)
                .withString("name", "Spider man")
                .withString("universe", "Marvel")
                .withNumber("movies",3);

        PutItemOutcome putItemOutcome = table.putItem(hero);

    }
}
