package com.dankook.tagme.mapper;

import com.dankook.tagme.data.remote.StoreDetailRequest;
import com.dankook.tagme.data.remote.StoreListRequest;

public class RequestMapper {

    public static StoreDetailRequest storeDetailMapping(int storeKey){

        return new StoreDetailRequest(storeKey);
    }

    public static StoreListRequest storeListMapping(int categoryKey, int pageIndex, int pageUnit){

        return new StoreListRequest(categoryKey, pageIndex, pageUnit);
    }
}
