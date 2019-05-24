package elasticclienttest;

import java.io.IOException;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

public class SimpleSearch {

	public static void main(String[] args) throws IOException {

		try (RestHighLevelClient client = ElasticClient.getElasticClient()) {
			GetRequest request = new GetRequest("posts", "_iHqymoBxSxTtPpQyHrt");
			String[] includes = new String[]{"message"};
			String[] excludes = Strings.EMPTY_ARRAY;
			FetchSourceContext fetchSourceContext =
			        new FetchSourceContext(true, includes, excludes);
			request.fetchSourceContext(fetchSourceContext);
			Timer.takeTime("Finished assembling request");
			GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
			Timer.takeTime("Got a response");
			Timer.printTimes();
			System.out.println(getResponse.getSource().toString());
		}
	}

}
