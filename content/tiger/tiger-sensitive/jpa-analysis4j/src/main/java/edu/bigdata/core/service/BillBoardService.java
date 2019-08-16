package edu.bigdata.core.service;

import edu.bigdata.core.entity.Billboard;
import edu.bigdata.core.mapper.BillboardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gwd on 2016/4/16.
 */
@Service
@Transactional
public class BillBoardService {

    @Autowired
    private BillboardMapper billboardMapper;

    public List<Billboard> getBillboards(){
        return billboardMapper.getBillboards();
    }
}
