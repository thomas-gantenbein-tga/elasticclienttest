package elasticclienttest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.elasticsearch.client.Response;

public class SimpleSearchWithApacheHttpClient {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		Timer.takeTime("Start");
		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY,
				new UsernamePasswordCredentials("elastic", "jzTKYvfuYAtbu9XkYKHzRcTy"));

		try (CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider)
				.build();) {
			Timer.takeTime("Client konstruiert");
			HttpGet getRequest = new HttpGet(
					"https://a905d31dfa9849039bad0130ed983a5f.eu-central-1.aws.cloud.es.io:9243/posts/_doc/_iHqymoBxSxTtPpQyHrt");
			HttpResponse response = client.execute(getRequest);
			Timer.takeTime("Response erhalten");
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			Timer.takeTime("Response gelesen");
			Timer.printTimes();
			System.out.println(result);

		}

	}

}
