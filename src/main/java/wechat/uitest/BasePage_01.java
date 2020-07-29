package wechat.uitest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * 自动化领域建模
 */
public class BasePage_01 {

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
    public void sendKey(HashMap<String,Object> map){
        System.out.println("sendKey");
        System.out.println(map);

    }

    public void action(HashMap<String,Object> map){
        System.out.println("action");
        System.out.println(map);
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
                sendKey(m);
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

}
