package top.zhangdengzhen.demo.server.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zhangdengzhen.demo.dao.Complain;
import top.zhangdengzhen.demo.mapper.ComplainMapper;
import top.zhangdengzhen.demo.server.ComplainServer;

import java.util.List;

@Service
public class ComplainServerImp implements ComplainServer {
    @Autowired
    private ComplainMapper complainMapper;
    @Override
    public int insert(Complain complain) {
        return complainMapper.insert(complain);
    }

    @Override
    public int delete(int id) {
        return complainMapper.deleteById(id);
    }

    @Override
    public List<Complain> selectPage(int currIndex, int pageSize) {
        return complainMapper.selectPage(currIndex,pageSize);
    }

    @Override
    public int Complaincount() {
        return complainMapper.Complaincount();
    }
}
