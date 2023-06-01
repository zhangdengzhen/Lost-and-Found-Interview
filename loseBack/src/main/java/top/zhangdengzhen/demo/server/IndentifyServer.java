package top.zhangdengzhen.demo.server;

import top.zhangdengzhen.demo.dao.Indentify;

import java.util.List;

public interface IndentifyServer {
    int insert(Indentify indentify);

    Indentify select(String openid);
}
