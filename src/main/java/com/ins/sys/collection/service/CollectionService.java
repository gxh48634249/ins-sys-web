package com.ins.sys.collection.service;

import com.ins.sys.collection.domain.CollectionInfo;
import com.ins.sys.tools.SimpleService;
import java.util.List;


import javax.transaction.Transactional;

public interface CollectionService extends SimpleService<CollectionInfo> {


    public  List<CollectionInfo>   findbyUsrsId(String  UsrsId);


}
