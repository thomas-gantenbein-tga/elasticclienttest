package elasticclienttest;

import java.io.IOException;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class SimpleIndex {

	public static void main(String[] args) throws IOException {

		try (RestHighLevelClient client = ElasticClient.getElasticClient()) {
			IndexRequest request = new IndexRequest("posts");
			// request.id("1");
			String jsonString = "{" + "\"user\":\"kimchy\"," + "\"postDate\":\"2013-01-30\","
					+ "\"message\":\"trying out Elasticsearch\"" + "}";
			request.source(jsonString, XContentType.JSON);
			Timer.takeTime("Zu indexierendes Dokument zusammengestellt");

			for (int i = 0; i < 10000; i++) {
				client.index(request, RequestOptions.DEFAULT);
			}

			Timer.takeTime("Indexierung abgeschlossen");
			Timer.printTimes();
		}
	}

}
