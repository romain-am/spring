package main.com.dragonsoft.content;

import org.springframework.content.commons.repository.ContentStore;

import main.com.dragonsoft.credentials.User;

public interface UserImageStore extends ContentStore<User, String> {

}
