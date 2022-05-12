package cbai.web.app.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cbai.util.FileUtil;
import cbai.web.app.common.dto.OptionDto;
import cbai.web.app.fileconvert.form.FileConvertForm;
import cbai.web.app.fileconvert.services.IFileConvertService;

@Service
public class FileConvertService implements IFileConvertService {

    @Override
    public File execute(FileConvertForm form, File outDir, File file) {
        form.setOutLog("TEST");
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        FileUtil.writeStringToFile("TEST", new File(outDir, "Test.txt"), "UTF-8");
        return outDir;
    }

    @Override
    public List<OptionDto> getOperations(FileConvertForm form) {
        List<OptionDto> options = new ArrayList<OptionDto>();
        options.add(new OptionDto("1", "テスト１"));
        options.add(new OptionDto("2", "テスト２"));
        return options;
    }

}
