package cbai.web.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cbai.web.app.common.dto.MenuDto;
import cbai.web.app.common.services.IIndexService;

@Service
public class IndexService implements IIndexService {

    @Override
    public void execute(String projectId, List<MenuDto> menuList) {
        menuList.add(new MenuDto("サンプル", "/sample/", "サンプル画面"));
//        menuList.add(new MenuDto("文件处理", "/fileconvert/", "自定义文件处理"));
    }

}
