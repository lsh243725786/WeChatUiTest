package wechat.uitest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * 自动化领域建模
 */
public class BasePage_01 {
    //创建一个PageObjectModel的list: 存下页面的对象
    List<PageObjectModel> pages = new ArrayList<>();

    /**
     *  点击方法
     */
    public void click(HashMap<String,Object> map){
        System.out.println("click");
        System.out.println(map);
    }

    /**
     * 输入方法
     */
    public void sendKeys(HashMap<String,Object> map){
        System.out.println("sendKey");
        System.out.println(map);

    }

    /**
     * PO 操作方法
     * @param map
     */
    public void action(HashMap<String,Object> map){
        System.out.println("action");
        System.out.println(map);

//        如果是page级别的关键字
        if (map.containsKey("page")) {
            String action = map.get("action").toString();
            String pageName = map.get("page").toString();
            pages.forEach(pom-> System.out.println(pom.name));

            pages.stream()
                    .filter(pom -> pom.name.equals(pageName))
                    .findFirst()
                    .get()
                    .methods.get(action).forEach(step -> {
                action(step);
            });
        } else {
//            自动化级别
            if (map.containsKey("click")) {
                HashMap<String, Object> by = (HashMap<String, Object>) map.get("click");
                click(by);
            }

            if (map.containsKey("sendKeys")) {
                sendKeys(map);
            }
        }
    }

    /**
     *  查找方法
     */
    public void find(){
    }

    /**
     * 获取文本方法
     */
    public void getText(){}

    /**
     * 运行的操作，读对应的对象进行运行
     */
    public void run(UiAuto_02 uiAuto_02){
        //流式读取uiAuto_02类里的steps内容，根据每一个步骤，执行不同的调用方法
        uiAuto_02.steps.stream().forEach(m->{
            //如果m.keySet()包含click，执行click操作
            if (m.keySet().contains("click")){
                //m.get获取click里面的值，强制转化为(HashMap<String,Object>)属性，再用by接收
                HashMap<String,Object> by = (HashMap<String,Object>) m.get("click");
                //执行click操作
                click(by);
            }
            //因为sendKeys里面的值多样，就直接传入一个m对象
            if (m.containsKey("sendKeys")){
                //执行click操作
                sendKeys(m);
            }
            if (m.containsKey("action")){
                action(m);
            }
        });
    }

    /**
     * 加载对象内容，在run方法进行执行;
     * 读取yaml文件
     * @param path 文件路径
     * @return 返回UiAuto_02对象
     */
    public UiAuto_02 load(String path){
        //mapper的初始化
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        UiAuto_02 uiauto = null;
        try {
            //读取传入的yaml文件的值，用uiauto来接收
            uiauto = mapper.readValue(
                    //读取资源文件，强行转化为UiAuto_02资源模型
                    BasePage_01.class.getResourceAsStream(path),
                    UiAuto_02.class
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uiauto;
    }

    /**
     * 加载页面对象内容-生成单个页面PO
     * 读取yaml文件
     * @param path 文件路径
     * @return 返回PageObjectModel对象
     */
    public PageObjectModel loadPage(String path){
        //mapper的初始化
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        //创建一个空的pageObjectModel对象
        PageObjectModel pageObjectModel = null;
        try {
            //读取传入的yaml文件的值，用uiauto来接收
            pageObjectModel = mapper.readValue(
                    //读取资源文件，强行转化为PageObjectModel资源模型
                    new File(path),
                    PageObjectModel.class
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pageObjectModel;
    }

    /**
     * 加载页面对象内容-生成多个页面PO
     * @param dir 目录
     */
    public void loadPages(String dir) {
        //用流式对传入的目录下每个文件进行解析
        Stream.of(new File(dir).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains("_page");
            }
        })).forEach(path -> {
            path = dir + "/" + path;
            System.out.println(path);
            pages.add(loadPage(path));
        });
    }
}
