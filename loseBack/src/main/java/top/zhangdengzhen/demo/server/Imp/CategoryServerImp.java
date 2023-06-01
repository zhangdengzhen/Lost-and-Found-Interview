package top.zhangdengzhen.demo.server.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zhangdengzhen.demo.dao.Category;
import top.zhangdengzhen.demo.mapper.CategoryMapper;
import top.zhangdengzhen.demo.server.CategoryServer;

import java.util.List;

@Service
public class CategoryServerImp implements CategoryServer {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectList(null);
    }

    @Override
    public int insert(Category category) {
        return categoryMapper.insert(category);
    }

    @Override
    public int delete(int id) {
        return categoryMapper.deleteById(id);
    }
}
