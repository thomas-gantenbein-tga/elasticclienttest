package elasticclienttest;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class ElasticClient {

	static RestHighLevelClient getElasticClient() {
		Timer.takeTime("Start");
		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY,
				new UsernamePasswordCredentials("elastic", "jzTKYvfuYAtbu9XkYKHzRcTy"));

		RestClientBuilder builder = RestClient
				.builder(new HttpHost("a905d31dfa9849039bad0130ed983a5f.eu-central-1.aws.cloud.es.io", 9243, "https"))
				.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
					public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
						return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
					}
				});
		RestHighLevelClient client = new RestHighLevelClient(builder);
		Timer.takeTime("RestClient initialisiert");
		return client;
	}

}
