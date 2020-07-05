package main.com.dragonsoft.content;

import org.springframework.content.commons.repository.ContentStore;

import main.com.dragonsoft.credentials.User;
import main.com.dragonsoft.users.UserCV;

public interface UserPdfStore extends ContentStore<UserCV, String> {

}
