package wechat.uitest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class WebTest {

    private static BasePage_01 basePage_01;

    /**
     * 完成通用的初始化测试配置
     */
    @BeforeAll
    static void BbforeAll(){
        // TODO: 2020/7/29 完成所有测试用例执行前的操作
    }

    @BeforeEach
    void beforeEach(){
        // TODO: 2020/7/29 每个测试用例执行之前的操作的通用处理
    }

    @AfterAll
    static void afterAll(){
        // TODO: 2020/7/29 完成所有测试用例执行之后的操作，关闭浏览器和数据清理
    }


    /**
     * 参数化测试用例构建
     * @param uiAuto_02 从外部进行读取uiAuto_02对象
     * @MethodSource   在同类名称要有个同名的方法名
     */
    @ParameterizedTest(name = "{index} {1}")
    @MethodSource
    void classic(UiAuto_02 uiAuto_02, String path){

        basePage_01.run(uiAuto_02);
    }

//    /**
//     * 创建一个同名的方法，对应@MethodSource的要求
//     * 实现对参数化数据的供应-单个参数化文件读取
//     * @return
//     */
//    static Stream<UiAuto_02> classic(){
//        //basePage_01加载的时候，给一个默认的"web"，后面会根据参数化的文件来确认是web还是其他
//        basePage_01 = UiAutoFactory.create("web");
//        //load一个uiAuto_02的对象
//        UiAuto_02 uiAuto_02 = basePage_01.load("/wechatuitest/webuiauto.yaml");
//        //返回一个Stream的流式对象：uiAuto_02
//        return Stream.of(uiAuto_02);
//    }

    /**
     * 进行改造-多个参数化文件进行读取
     * 创建一个同名的方法，对应@MethodSource的要求
     * 实现对参数化数据的供应
     * @return
     */
    static List<Arguments> classic(){
        //basePage_01加载的时候，给一个默认的"web"，后面会根据参数化的文件来确认是web还是其他
        basePage_01 = UiAutoFactory_04.create("web");
        //创建一个列表，初始化一个all对象，用来继承和接收
        List<Arguments> all  = new ArrayList<>();
        //创建一个Arrays.asList存放所有资源路径，
        // .stream().forEach(path->()流式读取资源路径,再用load接收资源路径
        Arrays.asList(
                "/wechatuitest/webuiauto.yaml",
                "/wechatuitest/webuiauto_01.yaml",
                "/wechatuitest/webuiauto_02.yaml"
        ).stream().forEach(path->{
            UiAuto_02 uiAuto_02 = basePage_01.load(path);
            uiAuto_02.description=path;
            //读出来的每个对象，add添加到all列表里
            all.add(arguments(uiAuto_02, path));
        });
        //返回一个Stream的流式对象：all
        return all;
    }
}
