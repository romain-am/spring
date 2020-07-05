package main.com.dragonsoft.elasticsearch;

import java.util.List;

import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import main.com.dragonsoft.users.UserInfo;

@Service
public class ElasticsearchUserInfoService {
	
	@Autowired
	private ElasticsearchUserInfoRepo userInfoRepo;
	
	public List<UserInfo> findUsersWithCvs(String input){
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withIndices("user_info_index")
				  .withQuery(QueryBuilders.multiMatchQuery(input)
				    .field("languages")
				    .field("middlewares")
				    .field("databasesList")
				    .field("operating_system")
				    .field("frameworks")
				    .type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
				  .withPageable(new PageRequest(0, 10))
				  .build();
		return userInfoRepo.search(searchQuery).getContent();
	}
}
