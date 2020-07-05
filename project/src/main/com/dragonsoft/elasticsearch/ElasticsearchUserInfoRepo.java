package main.com.dragonsoft.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import main.com.dragonsoft.users.UserInfo;

@Repository
public interface ElasticsearchUserInfoRepo extends ElasticsearchRepository<UserInfo, Long> {
 
}