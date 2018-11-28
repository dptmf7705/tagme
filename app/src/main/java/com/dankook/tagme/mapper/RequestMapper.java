package com.dankook.tagme.mapper;

import com.dankook.tagme.data.remote.InsertOrderRequest;
import com.dankook.tagme.data.remote.StoreDetailRequest;
import com.dankook.tagme.data.remote.StoreListRequest;
import com.dankook.tagme.data.source.user.UserRepository;
import com.dankook.tagme.model.UserVO;

public class RequestMapper {

    public static StoreDetailRequest storeDetailMapping(int storeKey){

        return new StoreDetailRequest(storeKey);
    }

    public static StoreListRequest storeListMapping(int categoryKey, int pageIndex, int pageUnit){

        return new StoreListRequest(categoryKey, pageIndex, pageUnit);
    }

    public static InsertOrderRequest InsertOrdertMapping(int storeKey, int tableNumber, int menuKey, int amount){

        int userKey = 1;

        UserVO user = UserRepository.getInstance().getLoginUser();
        if(user != null){
            userKey = user.getUsrkey();
        }
        return new InsertOrderRequest(userKey, storeKey, tableNumber, menuKey, amount);
    }
}
