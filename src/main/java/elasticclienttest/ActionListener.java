package elasticclienttest;

import org.elasticsearch.action.index.IndexResponse;

public class ActionListener implements org.elasticsearch.action.ActionListener<IndexResponse> {

	@Override
	public void onFailure(Exception arg0) {
		System.out.println("failure!");

	}

	@Override
	public void onResponse(IndexResponse arg0) {
		System.out.println("success");

	}

}
