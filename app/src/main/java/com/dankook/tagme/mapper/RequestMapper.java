package com.dankook.tagme.mapper;

import com.dankook.tagme.data.remote.StoreDetailRequest;
import com.dankook.tagme.data.remote.StoreListRequest;

public class RequestMapper {

    public static StoreDetailRequest storeDetailRequestMapping(int storeKey){
        return new StoreDetailRequest(storeKey);
    }

    public static StoreListRequest storeListRequestMapping(int categoryKey){
        return new StoreListRequest(categoryKey);
    }
}
