import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

import java.util.HashMap;
import java.util.Map;

public class AddItemDynamo {


    public static void main(String[] args) throws Exception {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Movies");

        int year = 2021;
        String title = "The Best Movie Ever 2";

        final Map<String, Object> infoMap = new HashMap<String, Object>();
        infoMap.put("plot", "The sequel nobody asked for");
        infoMap.put("rating", 0);

        try {
            System.out.println("Adding new item...");
            PutItemOutcome outcome = table
                    .putItem(new Item().withPrimaryKey("year", year, "title", title).withMap("info", infoMap));

            System.out.println("PutItem '" + title + "' succeeded:\n" + outcome.getPutItemResult());

        }
        catch (Exception e) {
            System.err.println("Unable to add item: " + year + " " + title);
            System.err.println(e.getMessage());
        }

    }

}
