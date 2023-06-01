package top.zhangdengzhen.demo.server;

import top.zhangdengzhen.demo.dao.Category;

import java.util.List;

public interface CategoryServer {
    List<Category> selectAll();
    int insert(Category category);
    int delete(int id);
}
