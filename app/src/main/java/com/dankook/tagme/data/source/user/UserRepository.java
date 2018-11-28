package com.dankook.tagme.data.source.user;

import com.dankook.tagme.model.UserVO;

public class UserRepository {

    private static UserRepository userRepository;

    private UserRepository(){

    }

    public static UserRepository getInstance(){
        if(userRepository == null){
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    private UserVO loginUser;

    public UserVO getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(UserVO loginUser) {
        this.loginUser = loginUser;
    }
}
