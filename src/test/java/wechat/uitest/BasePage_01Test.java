package wechat.uitest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BasePage_01Test {
    //创建一个basePage_01的类变量
    private static BasePage_01 basePage_01;

    /**
     * 第一步，引用BasePage_01对象，实例一个basePage_01对象
     */
    @BeforeAll
    static void beforeAll(){
        //首先引用BasePage_01对象，实例一个basePage_01对象
        basePage_01 =new BasePage_01();
    }
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void run() {
        //获取资源路径，并调用basePage_01里的load方法进行读取和解析,用uiAuto_02进行接收
        UiAuto_02 uiAuto_02 = basePage_01.load("/wechatuitest/webuiauto.yaml");
        //执行run方法
        basePage_01.run(uiAuto_02);
    }

    @Test
    void runPOM(){
        //加载目录下的所有_page.yaml文件
        basePage_01.loadPages("src/main/resources/wechatuitest");
        //加载单个文件
        UiAuto_02 uiauto=basePage_01.load("/wechatuitest/webuiauto_03.yaml");
        basePage_01.run(uiauto);

    }

    /**
     * 第二步，编写load测试方法
     * 首先load一个资源，再用ObjectMapper拿到资源代表的类
     */
    @Test
    void load() throws JsonProcessingException {
        //获取资源路径，并调用basePage_01里的load方法进行读取和解析,用uiAuto_02进行接收
        UiAuto_02 uiAuto_02 = basePage_01.load("/wechatuitest/uiauto.yaml");
        //创建一个mapper对象，用来读取uiAuto_02的内容
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(uiAuto_02));
    }
}